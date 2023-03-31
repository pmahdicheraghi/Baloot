<%@ page import="Market.MarketManager" %>
<%@ page import="Market.Commodity" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="Market.Category" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Commodities</title>
    <style>
        table {
            width: 100%;
            text-align: center;
        }
    </style>
</head>
<body>
<a href="/">Home</a>
<p id="username">username: <%= MarketManager.getInstance().getLoggedInUser()%></p>
<br>
<form action="" method="GET">
    <label>Search:</label>
    <input type="text" name="search" value="">
    <button type="submit" name="action" value="search_by_category">Search By Cagtegory</button>
    <button type="submit" name="action" value="search_by_name">Search By Name</button>
    <button type="submit" name="action" value="clear">Clear Search</button>
</form>
<br>
<form action="" method="GET">
    <label>Sort By:</label>
    <button type="submit" name="action" value="sort_by_rate">Rate</button>
</form>
<br>
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Provider Name</th>
        <th>Price</th>
        <th>Categories</th>
        <th>Rating</th>
        <th>In Stock</th>
        <th>Links</th>
    </tr>
    <%
        List<Commodity> commodities;
        StringBuilder tableRows = new StringBuilder();
        String search = request.getParameter("search");
        String action = request.getParameter("action");

        if (action == null) {
            commodities = MarketManager.getInstance().getCommoditiesList();
        } else if (action.equals("search_by_category")) {
            commodities = MarketManager.getInstance().getCommoditiesByCategory(Category.get(search));
        } else if (action.equals("search_by_name")) {
            commodities = MarketManager.getInstance().getCommoditiesByName(search);
        } else if (action.equals("sort_by_rate")) {
            commodities = MarketManager.getInstance().getCommoditiesSortedByRate();
        } else {
            commodities = MarketManager.getInstance().getCommoditiesList();
        }

        for (Commodity commodity: commodities) {
            tableRows.append("<tr>");
            tableRows.append("<td>").append(commodity.getId()).append("</td>");
            tableRows.append("<td>").append(commodity.getName()).append("</td>");
            tableRows.append("<td>").append(commodity.getProviderId()).append("</td>");
            tableRows.append("<td>").append(commodity.getPrice()).append("</td>");
            tableRows.append("<td>").append(commodity.getCategories()).append("</td>");
            tableRows.append("<td>").append(commodity.getRating()).append("</td>");
            tableRows.append("<td>").append(commodity.getInStock()).append("</td>");
            tableRows.append("<td><a href=\"/commodities/").append(commodity.getId()).append("\">Link</a></td>");
            tableRows.append("</tr>");
        }
    %>
    <%= tableRows.toString() %>
</table>
</body>
</html>
