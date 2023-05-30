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
<form action="/user/add-admission" method="post">
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

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Записатись">
</form>
<hr>
<a href="/">На головну</a>
</body>
</html>
