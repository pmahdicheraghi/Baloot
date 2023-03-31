<%@ page import="Market.MarketManager" %>
<%@ page import="Market.Commodity" %>
<%@ page import="Market.Comment" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Commodity</title>
    <style>
        li {
            padding: 5px;
        }

        table {
            width: 100%;
            text-align: center;
        }
    </style>
</head>
<body>
<a href="/">Home</a>
<p id="username">username: <%= MarketManager.getInstance().getLoggedInUser()%></p>
<ul>
    <%
        int id = Integer.parseInt(request.getAttribute("commodityId").toString());
        Commodity commodity = MarketManager.getInstance().getCommodityById(id);
    %>
    <li id="id">Id: <%= commodity.getId()%></li>
    <li id="name">Name: <%= commodity.getName()%></li>
    <li id="providerName">Provider Name: <%= commodity.getProviderId()%></li>
    <li id="price">Price: <%= commodity.getPrice()%></li>
    <li id="categories">Categories: <%= commodity.getCategories()%></li>
    <li id="rating">Rating: <%= commodity.getRating()%></li>
    <li id="inStock">In Stock: <%= commodity.getInStock()%></li>
</ul>

<label>Add Your Comment:</label>
<form action="" method="POST">
    <input type="text" name="comment" value=""/>
    <button type="submit">submit</button>
</form>
<br>
<form action="" method="POST">
    <label>Rate(between 1 and 10):</label>
    <input type="number" id="rate" name="rate" min="1" max="10">
    <button type="submit">Rate</button>
</form>
<br>
<form action="" method="POST">
    <input name="add" hidden>
    <button type="submit">Add to BuyList</button>
</form>
<br/>
<table>
    <caption><h2>Comments</h2></caption>
    <tr>
        <th>username</th>
        <th>comment</th>
        <th>date</th>
        <th>likes</th>
        <th>dislikes</th>
    </tr>
    <tr>
        <%
            StringBuilder commentList = new StringBuilder();
            for (Comment comment : MarketManager.getInstance().getCommentListForCommodityById(id)) {
                commentList.append("<tr>");
                commentList.append("<td>").append(comment.getUsername()).append("</td>");
                commentList.append("<td>").append(comment.getComment()).append("</td>");
                commentList.append("<td>").append(comment.getDate()).append("</td>");
                commentList.append("<td><form action=\"\" method=\"POST\">");
                commentList.append("<label for=\"\">").append(comment.getLikes()).append("</label>");
                commentList.append("<input id=\"form_comment_id\" type=\"hidden\" name=\"like\" value=\"").append(comment.getId()).append("\"/>");
                commentList.append("<button type=\"submit\">like</button>");
                commentList.append("</form></td>");
                commentList.append("<td><form action=\"\" method=\"POST\">");
                commentList.append("<label for=\"\">").append(comment.getDislikes()).append("</label>");
                commentList.append("<input id=\"form_comment_id\" type=\"hidden\" name=\"dislike\" value=\"").append(comment.getId()).append("\"/>");
                commentList.append("<button type=\"submit\">dislike</button>");
                commentList.append("</form></td>");
                commentList.append("</tr>");
            };
        %>
        <%= commentList.toString()%>
</table>
<br><br>
<table>
    <caption><h2>Suggested Commodities</h2></caption>
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
        StringBuilder suggestedCommoditiesInfo = new StringBuilder();
        for (Commodity suggestedCommodity : MarketManager.getInstance().getSuggestedCommodities(commodity)) {
                suggestedCommoditiesInfo.append("<tr>");
                suggestedCommoditiesInfo.append("<td>").append(suggestedCommodity.getId()).append("</td>");
                suggestedCommoditiesInfo.append("<td>").append(suggestedCommodity.getName()).append("</td>");
                suggestedCommoditiesInfo.append("<td>").append(suggestedCommodity.getProviderId()).append("</td>");
                suggestedCommoditiesInfo.append("<td>").append(suggestedCommodity.getPrice()).append("</td>");
                suggestedCommoditiesInfo.append("<td>").append(suggestedCommodity.getCategories()).append("</td>");
                suggestedCommoditiesInfo.append("<td>").append(suggestedCommodity.getRating()).append("</td>");
                suggestedCommoditiesInfo.append("<td>").append(suggestedCommodity.getInStock()).append("</td>");
                suggestedCommoditiesInfo.append("<td><a href=\"/commodities/").append(suggestedCommodity.getId()).append("\">Link</a></td>");
                suggestedCommoditiesInfo.append("</tr>");
            }
    %>
    <%= suggestedCommoditiesInfo.toString()%>
</table>
</body>
</html>
