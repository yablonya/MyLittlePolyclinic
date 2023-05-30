<!DOCTYPE html>
<html>
<head>
    <title>My Polyclinic</title>
</head>
<body>
<h1>Polyclinic</h1><hr>
<h3>Редагуваня розкладу</h3>
<img src="/image/${image.id}" height="180px"/><br><br>
<b>${doctor.nameSurname}</b><br><br>
${doctor.specialization}<br><br>
<b>${doctor.phoneNumber}</b><br>
<hr>
<h3>Години прийому</h3>
<hr>
<h4>Сьогодні</h4><br>
<table border="1">
    <tr>
        <th><b>Година запису</b></th>
        <th><b>Видалення</b></th>
    </tr>
    <#list schedule as admission>
        <#if admission.user??>
            <#continue>
        </#if>
        <tr>
            <th>${admission.time}</th>
            <th>
                <form action="/doctor/${doctor.id}/remove-hour/${admission.id}" method="post">
                    <input type="hidden" name="_csrf" value=${_csrf.token}>
                    <input type="submit" value="Видалити">
                </form>
            </th>
        </tr>
    </#list>
</table>
<hr>
<form action="/doctor/${doctor.id}/add-hour" method="post">
    Година: <input type="text" name="hour"/><br><br>
    <input type="hidden" name="_csrf" value=${_csrf.token}>
    <input type="submit" value="Додати">
</form>
<hr>
<a href="/admin">До панелі адміністратора</a>
</body>
</html>
