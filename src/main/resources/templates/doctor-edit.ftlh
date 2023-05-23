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
<h3>Години прийому</h3>
<hr>
<h4>Сьогодні</h4>
<form action="/doctor/remove-hour/${doctor.id}" method="post">
    <#list schedule as admission>
        <div>
            <p>
                <#if admission.user??>
                    <#continue>
                </#if>

                ${admission.time}
                <input type="radio" name="time" id="admission" value="${admission.id}"/>
            </p>
        </div>
    </#list>
    <input type="hidden" name="_method" value="delete"/>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Видалити вибрану годину">
</form>
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