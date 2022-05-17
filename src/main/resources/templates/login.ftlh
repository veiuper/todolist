<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Войдите в свою учетную запись</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<h1>Todolist</h1>
<@security.authorize access="isAuthenticated()">
    <% response.sendRedirect("/"); %>
</@security.authorize>
<div>
    <form method="POST" action="/login">
        <h2>Вход в систему</h2>
        <div>
            <label>
                <input autofocus name="username" placeholder="Username" type="text"/>
            </label>
            <label>
                <input name="password" type="password" placeholder="Password"/>
            </label>
            <button type="submit">Войти</button>
            <h4><a href="/registration">Зарегистрироваться</a></h4>
        </div>
    </form>
</div>
</body>
</html>