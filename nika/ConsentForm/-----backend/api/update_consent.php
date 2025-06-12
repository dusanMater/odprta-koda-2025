<?php
require_once 'config.php';

if ($_SERVER['REQUEST_METHOD'] !== 'PUT') {
    echo json_encode(['success' => false, 'message' => 'Only PUT method allowed']);
    exit();
}

// extract ID from url
$uri = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);
$path = explode('/', $uri);
$id = null;

for ($i = 0; $i < count($path); $i++) {
    if (strpos($path[$i], 'update_consent.php') !== false && isset($path[$i + 1])) {
        $id = intval($path[$i + 1]);
        break;
    }
}

if (!$id) {
    echo json_encode(['success' => false, 'message' => 'Invalid or missing ID']);
    exit();
}

$input = json_decode(file_get_contents('php://input'), true);

if (!$input) {
    echo json_encode(['success' => false, 'message' => 'Invalid JSON']);
    exit();
}

// validate required fields
$required_fields = ['firstName', 'lastName', 'email', 'birthday', 'hasAgreed', 'signature'];
foreach ($required_fields as $field) {
    if (!isset($input[$field])) {
        echo json_encode(['success' => false, 'message' => "Missing required field: $field"]);
        exit();
    }
}

// validate email
if (!filter_var($input['email'], FILTER_VALIDATE_EMAIL)) {
    echo json_encode(['success' => false, 'message' => 'Invalid email format']);
    exit();
}

// validate date
if (!DateTime::createFromFormat('Y-m-d', $input['birthday'])) {
    echo json_encode(['success' => false, 'message' => 'Invalid date format. Use YYYY-MM-DD']);
    exit();
}

try {
    // check if id exists
    $checkStmt = $pdo->prepare("SELECT id FROM consent_forms WHERE id = ?");
    $checkStmt->execute([$id]);

    if (!$checkStmt->fetch()) {
        echo json_encode(['success' => false, 'message' => 'Consent form not found']);
        exit();
    }

    // update id
    $stmt = $pdo->prepare("
        UPDATE consent_forms 
        SET first_name = ?, last_name = ?, email = ?, birthday = ?, has_agreed = ?, signature = ?, updated_at = CURRENT_TIMESTAMP
        WHERE id = ?
    ");

    $result = $stmt->execute([
        $input['firstName'],
        $input['lastName'],
        $input['email'],
        $input['birthday'],
        $input['hasAgreed'] ? 1 : 0,
        $input['signature'],
        $id
    ]);

    if (!$result) {
        echo json_encode(['success' => false, 'message' => 'Failed to update consent form']);
        exit();
    }

    // fetch updated id
    $fetchStmt = $pdo->prepare("SELECT * FROM consent_forms WHERE id = ?");
    $fetchStmt->execute([$id]);
    $data = $fetchStmt->fetch();

    echo json_encode([
        'success' => true,
        'message' => 'Consent form updated successfully',
        'data' => [
            'id' => (int) $data['id'],
            'firstName' => $data['first_name'],
            'lastName' => $data['last_name'],
            'email' => $data['email'],
            'birthday' => $data['birthday'],
            'hasAgreed' => (bool) $data['has_agreed'],
            'signature' => $data['signature'],
            'submittedAt' => $data['submitted_at'],
            'updatedAt' => $data['updated_at']
        ]
    ]);

} catch (Exception $e) {
    echo json_encode(['success' => false, 'message' => 'Error: ' . $e->getMessage()]);
}
?>