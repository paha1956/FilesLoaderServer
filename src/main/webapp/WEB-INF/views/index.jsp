<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Вход в базу данных загруженных файлов</title>
<style type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</style>
</head>
<body>
    <div style="text-align:center">
	<h1>
	База данных загруженных файлов.<br>
	</h1>
	</div>

    <form method="get" action="filesdataprint">
      <table cellspacing="50">
        <tr>
          <td>Имя пользователя:</td>
          <td><input name="userName" type="text"/></td>
        </tr>
        <tr>
          <td colspan="2">
            <button type="submit">Загрузить<br>данные</button>
          </td>
        </tr>
      </table>
    </form>
</body>
</html>
