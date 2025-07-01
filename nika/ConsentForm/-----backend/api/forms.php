<?php
require_once 'config.php';

if ($_SERVER['REQUEST_METHOD'] !== 'GET') {
    echo json_encode(['success' => false, 'message' => 'Only GET method allowed']);
    exit();
}

try {
    // get query params
    $page = isset($_GET['page']) ? (int) $_GET['page'] : 1;
    $limit = isset($_GET['limit']) ? (int) $_GET['limit'] : 30;
    $search = isset($_GET['search']) ? trim($_GET['search']) : '';
    $sortBy = isset($_GET['sortBy']) ? $_GET['sortBy'] : 'submitted_at';
    $sortOrder = isset($_GET['sortOrder']) ? $_GET['sortOrder'] : 'desc';

    // validate sort params
    $allowedSortFields = ['id', 'first_name', 'last_name', 'email', 'birthday', 'submitted_at'];
    if (!in_array($sortBy, $allowedSortFields)) {
        $sortBy = 'submitted_at';
    }

    $sortOrder = strtolower($sortOrder) === 'asc' ? 'ASC' : 'DESC';

    // calc page offset
    $offset = ($page - 1) * $limit;

    // build search conditions
    $searchCondition = '';
    $searchParams = [];
    if (!empty($search)) {
        $searchCondition = "WHERE first_name LIKE ? OR last_name LIKE ? OR email LIKE ?";
        $searchParams = ["%$search%", "%$search%", "%$search%"];
    }

    // count total records
    $countSql = "SELECT COUNT(*) as total FROM consent_forms $searchCondition";
    $countStmt = $pdo->prepare($countSql);
    $countStmt->execute($searchParams);
    $total = $countStmt->fetch()['total'];

    // calculate total pages
    $totalPages = ceil($total / $limit);

    // fetch records
    $sql = "SELECT * FROM consent_forms $searchCondition ORDER BY $sortBy $sortOrder LIMIT $limit OFFSET $offset";

    $stmt = $pdo->prepare($sql);
    $stmt->execute($searchParams);
    $records = $stmt->fetchAll();

    // match data to android app format
    $data = [];
    foreach ($records as $record) {
        $data[] = [
            'id' => (int) $record['id'],
            'firstName' => $record['first_name'],
            'lastName' => $record['last_name'],
            'email' => $record['email'],
            'birthday' => $record['birthday'],
            'hasAgreed' => (bool) $record['has_agreed'],
            'signature' => $record['signature'],
            'submittedAt' => $record['submitted_at']
        ];
    }

    echo json_encode([
        'success' => true,
        'message' => 'Forms retrieved successfully',
        'data' => $data,
        'total' => (int) $total,
        'currentPage' => (int) $page,
        'totalPages' => (int) $totalPages
    ]);

} catch (Exception $e) {
    echo json_encode(['success' => false, 'message' => 'Error: ' . $e->getMessage()]);
}
?>