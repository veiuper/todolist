<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Войдите в свою учетную запись</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<h1>Todolist</h1>
<div>
    <form method="post" action="/login">
        <@security.csrfInput />
        <h2>Вход в систему</h2>
        <div>
            <label>
                <input autofocus name="username" placeholder="Email" type="text"/>
            </label>
            <label>
                <input name="password" type="password" placeholder="Пароль"/>
            </label>
            <button type="submit">Войти</button>
            <h4><a href="/">Главная</a></h4>
            <@security.authorize access="isAnonymous()">
                <h4><a href="/registration">Зарегистрироваться</a></h4>
            </@security.authorize>
        </div>
    </form>
</div>
</body>
</html>