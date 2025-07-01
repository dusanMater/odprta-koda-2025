<?php
require_once 'config.php';

if ($_SERVER['REQUEST_METHOD'] !== 'DELETE') {
    echo json_encode(['success' => false, 'message' => 'Only DELETE method allowed']);
    exit();
}

// get id from param
$id = isset($_GET['id']) ? (int) $_GET['id'] : 0;

if ($id <= 0) {
    echo json_encode(['success' => false, 'message' => 'Invalid ID']);
    exit();
}

try {
    // check if exists
    $checkStmt = $pdo->prepare("SELECT id FROM consent_forms WHERE id = ?");
    $checkStmt->execute([$id]);

    if (!$checkStmt->fetch()) {
        echo json_encode(['success' => false, 'message' => 'Record not found']);
        exit();
    }

    // delete by id
    $stmt = $pdo->prepare("DELETE FROM consent_forms WHERE id = ?");
    $stmt->execute([$id]);

    echo json_encode([
        'success' => true,
        'message' => 'Consent form deleted successfully'
    ]);

} catch (Exception $e) {
    echo json_encode(['success' => false, 'message' => 'Error: ' . $e->getMessage()]);
}
?>