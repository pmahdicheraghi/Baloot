<%@ page import="Market.MarketManager" %>
<%@ page import="Market.User" %>
<%@ page import="Market.Commodity" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User</title>
    <style>
        li {
            padding: 5px
        }

        table {
            width: 100%;
            text-align: center;
        }
    </style>
</head>
<body>
<a href="/">Home</a>
<ul>
    <%
        String username = MarketManager.getInstance().getLoggedInUser();
        User user = MarketManager.getInstance().getUserByUsername(username);
        StringBuilder buyList = new StringBuilder();
        int totalPrice = 0;
        for (Commodity commodity : MarketManager.getInstance().getBuyList(username)) {
            buyList.append("<tr>");
            buyList.append("<td>").append(commodity.getId()).append("</td>");
            buyList.append("<td>").append(commodity.getName()).append("</td>");
            buyList.append("<td>").append(commodity.getProviderId()).append("</td>");
            buyList.append("<td>").append(commodity.getPrice()).append("</td>");
            buyList.append("<td>").append(commodity.getCategories()).append("</td>");
            buyList.append("<td>").append(commodity.getRating()).append("</td>");
            buyList.append("<td>").append(commodity.getInStock()).append("</td>");
            buyList.append("<td><a href=\"/commodities/").append(commodity.getId()).append("\">Link</a></td>");
            buyList.append("<td>");
            buyList.append("<form action=\"\" method=\"POST\">");
            buyList.append("<input id=\"form_commodity_id\" type=\"hidden\" name=\"commodityId\" value=\"").append(commodity.getId()).append("\">");
            buyList.append("<button type=\"submit\">Remove</button>");
            buyList.append("</form>");
            buyList.append("</td>");
            buyList.append("</tr>");

            totalPrice += commodity.getPrice();
        }
        Object discount = request.getAttribute("discount");
        String discountCode = "";
        int discountPercent = 0;
        if (discount != null) {
            discountPercent = MarketManager.getInstance().getDiscountPercent(discount.toString());
            discountCode = discount.toString();
        }
    %>
    <li id="username">Username: <%= username%>
    </li>
    <li id="email">Email: <%= user.getEmail()%>
    </li>
    <li id="birthDate">Birth Date: <%= user.getBirthDay()%>
    </li>
    <li id="address">Address: <%= user.getAddress()%>
    </li>
    <li id="credit">Credit: <%= user.getCredit()%>
    </li>
    <li>Current Buy List Price: <%= totalPrice - totalPrice * discountPercent / 100%>
    </li>
    <li>
        <a href="/credit">Add Credit</a>
    </li>
    <li>
        <form action="" method="POST">
            <label>Submit & Pay</label>
            <input id="form_payment" type="hidden" name="purchase" value="<%= discountCode%>">
            <button type="submit">Payment</button>
        </form>
    </li>
</ul>
<table>
    <caption>
        <h2>Buy List</h2>
    </caption>
    <tbody>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Provider Name</th>
        <th>Price</th>
        <th>Categories</th>
        <th>Rating</th>
        <th>In Stock</th>
        <th></th>
        <th></th>
    </tr>
    <%= buyList.toString()%>
    </tbody>
</table>
<br><br>
<form action="" method="POST">
    <label>Discount code</label>
    <input id="discount" name="discount" value="<%= discountCode%>">
    <button type="submit">Apply</button>
</form>
</body>
</html>
