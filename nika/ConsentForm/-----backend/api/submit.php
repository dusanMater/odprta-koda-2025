<?php
require_once 'config.php';

if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
    echo json_encode(['success' => false, 'message' => 'Only POST method allowed']);
    exit();
}

$input = json_decode(file_get_contents('php://input'), true);

if (!$input) {
    echo json_encode(['success' => false, 'message' => 'Invalid JSON']);
    exit();
}

// validate fields
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
    $stmt = $pdo->prepare("
        INSERT INTO consent_forms (first_name, last_name, email, birthday, has_agreed, signature)
        VALUES (?, ?, ?, ?, ?, ?)
    ");

    $stmt->execute([
        $input['firstName'],
        $input['lastName'],
        $input['email'],
        $input['birthday'],
        $input['hasAgreed'] ? 1 : 0,
        $input['signature']
    ]);

    $id = $pdo->lastInsertId();

    // fetch inserted record
    $result = $pdo->prepare("SELECT * FROM consent_forms WHERE id = ?");
    $result->execute([$id]);
    $data = $result->fetch();

    echo json_encode([
        'success' => true,
        'message' => 'Consent form submitted successfully',
        'data' => [
            'id' => (int) $data['id'],
            'firstName' => $data['first_name'],
            'lastName' => $data['last_name'],
            'email' => $data['email'],
            'birthday' => $data['birthday'],
            'hasAgreed' => (bool) $data['has_agreed'],
            'signature' => $data['signature'],
            'submittedAt' => $data['submitted_at']
        ]
    ]);

} catch (Exception $e) {
    echo json_encode(['success' => false, 'message' => 'Error: ' . $e->getMessage()]);
}
?>