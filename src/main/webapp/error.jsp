<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error</title>
</head>
<body>
    <h1><%= response.getStatus()%><br><%= exception.getMessage()%></h1>
</body>
</html>
