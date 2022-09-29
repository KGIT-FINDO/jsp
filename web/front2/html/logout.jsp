
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    session.invalidate();
    response.sendRedirect("/front2/html/index.jsp");

%>
