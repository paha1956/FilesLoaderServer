<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>${userName}. База данных загруженных файлов</title>
<style type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</style>
</head>
<body>
  <h2>
  Пользователь: ${userName}
  </h2>
  <form method="get" action="/">
    <table cellspacing="10">
      <tr>
        <td>
        <button type="submit" style="width: 130px;">Сменить<br>пользователя</button>
        </td>
      </tr>
    </table>
  </form>

  <c:if test="${errorDataTimeSet==true}">
    </p>
    <p><h3 style="color:red">Период выборки задан ошибочно! Производится выборка данных за последние сутки.</h3>
    </p>
  </c:if>

  <p><h3>${listRange}</h3>
  </p>
  <p><h3>Установка периода выборки:</h3>
  </p>

  <form method="get" action="/filesdataprint">
      <input type="hidden" name="userName" value="${userName}">
      <table cellspacing="10">
        <tr>
          <td>От: дата </td>
          <td><input name="startDate" type="date" value="${defaultStartDate}"></td>
          <td> время </td>
          <td><input name="startTime" type="time" value="${defaultStartTime}"></td>
        </tr>
        <tr>
          <td>До: дата </td>
          <td><input name="stopDate" type="date" value="${defaultStopDate}"></td>
          <td> время </td>
          <td><input name="stopTime" type="time" value="${defaultStopTime}"></td>
        </tr>
        <tr>
          <td colspan="4">
            <button type="submit" style="width: 130px;">Обновить<br>данные</button>
          </td>
        </tr>
      </table>

  </form>

    <c:choose>
        <c:when test="${filesList==null}">
		    <h3>
			Список загруженных файлов, соответствующих периоду выборки, пуст.
		    </h3>
        </c:when>
        <c:otherwise>
		    <h3>
			Список загруженных файлов:<br>
		    </h3>

            <table border="1" cellspacing="0">
               <tr>
                 <th style="width: 120px;" align="center" bgcolor="silver">Дата</th>
                 <th style="width: 120px;" align="center" bgcolor="silver">Время</th>
                 <th style="width: 600px;" align="center" bgcolor="silver">URL</th>
                 <th style="width: 120px;" align="center" bgcolor="silver">Объём</th>
                 <th style="width: 120px;" align="center" bgcolor="silver">Длительность<br>закачки</th>
                 <th style="width: 120px;" align="center" bgcolor="silver">Скорость</th>
               </tr>
               <c:forEach items="${filesList}" var="fileRecord">
                 <tr>
                   <td align="center">${fileRecord.fileDate}</td>
                   <td align="center">${fileRecord.fileTime}</td>
                   <td align="left">${fileRecord.fileURL}</td>
                   <td align="center">${fileRecord.stringSize}</td>
                   <td align="center">${fileRecord.stringLoadTime}</td>
                   <td align="center">${fileRecord.stringLoadSpeed}</td>
                 </tr>
               </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</body>
</html>
