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
<h3>Добавление списка задач</h3>
<form action="/add" method="post">
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
<#-- @ftlvariable name="tasklistSize" type="java.lang.Number" -->
<h2>Список списков задач (количество : ${tasklistSize})</h2>
    <table class="numbered-table">
        <tr class="table-header">
        <th>№</th>
        <th>Описание</th>
        <th>Удаление</th>
    </tr>
    <#list tasklistList as tasklist>
        <tr>
            <td></td>
            <td><a href="${'/tasklist/'+tasklist.id+'/tasks'}">${tasklist.description}</a></td>
            <td><a href="${'/delete/'+tasklist.id}">Удалить</a></td>
        </tr>
    </#list>
</table>
</body>
</html>