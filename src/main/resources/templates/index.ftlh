<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
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
<h3>Add task to list</h3>
<form action="/add" method="post">
    <table class="add-entry">
        <tr>
            <td>
                <label>Description</label>
                <label>
                    <input type="text" name="description">
                </label>
            </td>
            <td>
                <input type="submit" value="Add task">
            </td>
        </tr>
    </table>
</form>
<br>
<h2><#-- @ftlvariable name="taskSize" type="java.lang.Number" -->
Task list (task count : ${taskSize})</h2>
<table class="all-entry">
    <tr>
        <td>Task description</td>
        <td>Change the execution status</td>
        <td>Delete</td>
<#--        <td>Test</td>-->
    </tr>
    <#list taskList as task>
        <tr>
            <td>${task.description}</td>
            <td><a href="${'/reversTaskStatus/'+task.id}">executed</a></td>
            <td><a href="${'/delete/'+task.id}">delete</a></td>
            <td th:text="${task.executed?string('true', 'false')}  == 'true' ? 'true' : 'false'"></td>
<#--            <td>-->
<#--                <span th:if="${task.executed == true}">ACTIVE</span>-->
<#--                <span th:unless="${task.executed == false}">RETIRED</span>-->
<#--            </td>-->
        </tr>
    </#list>
</table>
</body>
</html>