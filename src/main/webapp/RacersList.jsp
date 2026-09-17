<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
    String season = request.getParameter("season");
    if (season == null || season.trim().isEmpty()) {
        RequestDispatcher dispatcher = application.getRequestDispatcher("/ErrorManager.jsp");
        dispatcher.forward(request, response);
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Список гонщиков</title>
</head>
<body>
    <h1>Список гонщиков (Сезон: <%= season %>)</h1>
    <%@include file="ListData.jsp"%>
    <br>
</body>
</html>