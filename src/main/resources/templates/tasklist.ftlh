<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Todolist</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <style>
        table.numbered-table {
            counter-reset: rowNumber;
        }
        table.numbered-table tbody tr:not(.table-header) {
            counter-increment: rowNumber;
        }
        table.numbered-table tr td:first-child::before {
            content: counter(rowNumber);
        }
    </style>
</head>
<body>
<h1>Todolist</h1>
<form action="/">
    <button type="submit">Назад</button>
</form>
<h3>Добавление задачи в список: ${tasklist.description}</h3>
<form action="/tasklist/${tasklist.id}/add" method="post">
    <table>
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
<#-- @ftlvariable name="taskSize" type="java.lang.Number" -->
<h2>Список задач (количество : ${taskSize})</h2>
<table class="numbered-table">
    <tr class="table-header">
        <th>№</th>
        <th>Описание</th>
        <th>Изменение статуса выполнения</th>
        <th>Удаление</th>
    </tr>
    <#list taskList as task>
        <tr>
            <td></td>
            <td <#if task.executed> style="text-decoration: line-through;"</#if>>${task.description}</td>
            <td><a href="${'/tasklist/'+tasklist.id+'/switchTaskStatus/'+task.id}"><#if task.executed>Отменить выполнение<#else>Выполнить</#if></a></td>
            <td><a href="${'/tasklist/'+tasklist.id+'/delete/'+task.id}">Удалить</a></td>
        </tr>
    </#list>
</table>
</body>
</html>