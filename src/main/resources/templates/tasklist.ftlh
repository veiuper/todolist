<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Todolist</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
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
<form action="/logout" method="post">
    <@security.csrfInput />
    <input type="submit" value="Выйти">
</form>
<h4><a href="/">Главная</a></h4>
<h4><a href="/tasklists">Мои списки задач</a></h4>
<h3>Добавление задачи в список: ${tasklist.description}</h3>
<form name="taskentity" action="/tasklist/add" method="post">
    <@security.csrfInput />
    <input type="hidden" name="tasklistId" value=${tasklist.id}>
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
<h2>Список задач (количество : ${taskList?size})</h2>
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
            <td>
                <form action="/tasklist/switchTaskStatus" method="post">
                    <@security.csrfInput />
                    <input type="hidden" name="tasklistId" value=${tasklist.id}>
                    <input type="hidden" name="taskId" value=${task.id}>
                    <input type="submit" value=<#if task.executed>Отменить выполнение<#else>Выполнить</#if>>
                </form>
            </td>
            <td>
                <form action="/tasklist/delete" method="post">
                    <@security.csrfInput />
                    <input type="hidden" name="tasklistId" value=${tasklist.id}>
                    <input type="hidden" name="taskId" value=${task.id}>
                    <input type="submit" value="Удалить">
                </form>
            </td>
        </tr>
    </#list>
</table>
</body>
</html>