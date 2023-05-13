<!DOCTYPE html>
<html>
<head>
    <title>My Polyclinic</title>
</head>
<body>
<h1>Polyclinic</h1><hr>
<h3>Запис до спеціаліста</h3>
<img src="/image/${image.id}" height="180px"/><br><br>
<b>${doctor.nameSurname}</b><br><br>
${doctor.specialization}<br><br>
<b>${doctor.phoneNumber}</b><br>
<hr>
<form action="/doctor/remove/${doctor.id}" method="post">
    <input type="submit" value="Видалити">
</form>
</body>
</html>