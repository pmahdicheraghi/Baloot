<%@ page import="Market.MarketManager" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <style>
        li {
            padding: 5px
        }
    </style>
</head>
<body>
<ul>
    <li id="email">username: <%= MarketManager.getInstance().getLoggedInUser()%></li>
    <li>
        <a href="/commodities">Commodities</a>
    </li>
    <li>
        <a href="/buyList">Buy List</a>
    </li>
    <li>
        <a href="/credit">Add Credit</a>
    </li>
    <li>
        <a href="/logout">Log Out</a>
    </li>
</ul>
</body>
</html>
