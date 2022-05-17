<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />

<!DOCTYPE HTML>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Главная</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<div>
    <h1>Todolist</h1>
    <h2>Выбор действия</h2>
    <@security.authorize access="isAuthenticated()">
        <h3><@security.authentication property="principal.username" /></h3>
    </@security.authorize>
    <@security.authorize access="isAnonymous()">
        <h3>Гость</h3>
        <h4><a href="/login">Войти</a></h4>
        <h4><a href="/registration">Зарегистрироваться</a></h4>
    </@security.authorize>
    <@security.authorize access="isAuthenticated()">
        <h4><a href="/logout">Выйти</a></h4>
    </@security.authorize>
    <h4><a href="/admin">Пользователи (только администратор)</a></h4>
</div>
</body>
</html>