<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Todolist</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <style>
        .all-entry {
            border-collapse: collapse;
            counter-reset: counter-tr;
        }
        .all-entry tbody tr:nth-child(n+2) {
            counter-increment: counter-tr;
        }
        .all-entry td,
        .all-entry tr:before {
            padding: 2px;
            border: 1px solid black;
        }
        .all-entry tbody tr:before {
            display: table-cell;
            vertical-align: middle;
        }
        .all-entry tbody tr:before,
        .all-entry b:after {
            content: counter(counter-tr);
        }
        .all-entry tbody tr:nth-child(-n+1):before {
            content: "№";
        }
    </style>
</head>
<body>
<h1>Todolist</h1>
<h3>Добавление задачи в список</h3>
<form action="/add" method="post">
    <table class="add-entry">
        <tr>
            <td>
                <label>Описание</label>
                <label>
                    <input type="text" name="description">
                </label>
            </td>
            <td>
                <input type="submit" value="Добавить">
            </td>
        </tr>
    </table>
</form>
<br>
<h2><#-- @ftlvariable name="taskSize" type="java.lang.Number" -->
Список задач (количество : ${taskSize})</h2>
<table class="all-entry">
    <tr>
        <td>Описание</td>
        <td>Изменение статуса выполнения</td>
        <td>Удаление</td>
    </tr>
    <#list taskList as task>
        <tr>
            <td <#if task.executed> style="text-decoration: line-through;"</#if>>${task.description}</td>
            <td><a href="${'/reversTaskStatus/'+task.id}"><#if task.executed>Отменить выполнение<#else>Выполнить</#if></a></td>
            <td><a href="${'/delete/'+task.id}">Удалить</a></td>
        </tr>
    </#list>
</table>
</body>
</html>