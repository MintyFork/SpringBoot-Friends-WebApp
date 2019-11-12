<?php
$mysqli = new mysqli("localhost:3306", "root", "12345", "portal?serverTimezone=EST5EDT");
if($mysqli->connect_error) {
  exit('Could not connect');
}

$sql = "SELECT id, email, name, age, about
FROM user WHERE id = ?";

$stmt = $mysqli->prepare($sql);
$stmt->bind_param("s", $_GET['q']);
$stmt->execute();
$stmt->store_result();
$stmt->bind_result($id, $email, $name, $age, $about);
$stmt->fetch();
$stmt->close();

echo "<table>";
echo "<tr>";
echo "<th>ID</th>";
echo "<td>" . $id . "</td>";
echo "<th>Email</th>";
echo "<td>" . $email . "</td>";
echo "<th>Name</th>";
echo "<td>" . $name . "</td>";
echo "<th>Age</th>";
echo "<td>" . $age . "</td>";
echo "<th>About</th>";
echo "<td>" . $about . "</td>";
echo "</tr>";
echo "</table>";
?> 