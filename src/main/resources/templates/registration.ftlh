<#import "/spring.ftl" as spring />

<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Регистрация</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<div>
    <h1>Todolist</h1>
    <h2>Регистрация</h2>
    <@security.authorize access="isAnonymous()">
        <h3>Гость</h3>
    </@security.authorize>
    <form name="user" action="/registration" method="post">
        <@security.csrfInput />
        Имя:<br>
        <@spring.formInput "user.name"/>
        <@spring.showErrors "<br>"/>
        <br><br>
        Фамилия:<br>
        <@spring.formInput "user.surname"/>
        <@spring.showErrors "<br>"/>
        <br><br>
        Email:<br>
        <@spring.formInput "user.email"/>
        <@spring.showErrors "<br>"/>
        <br><br>
        Пароль:<br>
        <@spring.formInput "user.password" "" "password"/>
        <@spring.showErrors "<br>"/>
        <br><br>
        Подтверждение пароля:<br>
        <@spring.formInput "user.passwordConfirm" "" "password"/>
        <@spring.showErrors "<br>"/>
        <#if passwordError??><b>${passwordError}</b></#if>
        <br><br>
        <button type="submit">Зарегистрироваться</button><br>
    </form>
    <@security.authorize access="isAnonymous()">
        <h4><a href="/login">Войти</a></h4>
        <h4><a href="/">Главная</a></h4>
    </@security.authorize>
</div>
</body>
</html>