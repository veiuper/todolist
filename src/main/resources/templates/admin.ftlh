<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <title>Todolist</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<h1>Todolist</h1>
<div>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Email</th>
                <th>Password</th>
                <th>Role</th>
                <th>Locked</th>
                <th>Enabled</th>
            </tr>
        </thead>
        <tbody>
            <#list allUsers as user>
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.email}</td>
                    <td>${user.password}</td>
                    <td>${user.userRole}</td>
                    <td>${user.locked?c}</td>
                    <td>${user.enabled?c}</td>
                    <td>
                        <a href="${'/admin/delete/'+user.id}">Удалить</a>
                    </td>
                    <td>
                        <a href="${'/admin/switchLocked/'+user.id}"><#if user.locked>Разблокировать<#else>Заблокировать</#if></a>
                    </td>
                    <td>
                        <a href="${'/admin/switchEnabled/'+user.id}"><#if user.enabled>Запретить<#else>Разрешить</#if></a>
                    </td>
                </tr>
            </#list>
        </tbody>
    </table>
    <br>
    <form action="/logout" method="post">
        <@security.csrfInput />
        <input type="submit" value="Выйти">
    </form>
</div>
</body>
</html>