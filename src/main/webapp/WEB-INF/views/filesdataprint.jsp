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
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
  <h4>
  Пользователь: ${userName}
  </h4>
  <form method="get" action="/" class="form-signin">
    <table cellspacing="10">
      <tr>
        <td>
        <button type="submit" class="btn btn-lg btn-primary btn-block">Сменить пользователя</button>
        </td>
      </tr>
    </table>
  </form>

  <c:if test="${errorDataTimeSet==true}">
    </p>
    <p><h5 style="color:red">Период выборки задан ошибочно! Производится выборка данных за последние сутки.</h5>
    </p>
  </c:if>

  <p><h5>${listRange}</h5>
  </p>
  <p><h5>Установка периода выборки:</h5>
  </p>

  <form method="get" action="/filesdataprint">
      <input type="hidden" name="userName" value="${userName}">
      <table cellspacing="10">
        <tr>
          <td>От: дата </td>
          <td><input class="form-control" name="startDate" type="date" value="${defaultStartDate}"></td>
          <td> время </td>
          <td><input class="form-control" name="startTime" type="time" value="${defaultStartTime}"></td>
        </tr>
        <tr>
          <td>До: дата </td>
          <td><input class="form-control" name="stopDate" type="date" value="${defaultStopDate}"></td>
          <td> время </td>
          <td><input class="form-control" name="stopTime" type="time" value="${defaultStopTime}"></td>
        </tr>
        <tr>
          <td colspan="4">
            <button type="submit" class="btn btn-lg btn-primary btn-block">Обновить данные</button>
          </td>
        </tr>
      </table>

  </form>

    <c:choose>
        <c:when test="${filesList==null}">
		    <h5>
			Список загруженных файлов, соответствующих периоду выборки, пуст.
		    </h5>
        </c:when>
        <c:otherwise>
		    <h5>
			Список загруженных файлов:<br>
		    </h5>

            <table class="table table-striped">
                 <thead class="thead-dark">
                 <tr>
                 <th style="width: 120px;" align="center" scope="col">Дата</th>
                 <th style="width: 120px;" align="center" bgcolor="silver">Время</th>
                 <th style="width: 600px;" align="center" bgcolor="silver">URL</th>
                 <th style="width: 120px;" align="center" bgcolor="silver">Объём</th>
                 <th style="width: 120px;" align="center" bgcolor="silver">Длительность<br>закачки</th>
                 <th style="width: 120px;" align="center" bgcolor="silver">Скорость</th>
                 </tr>
               </thead>
               <tbody>
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
               </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</body>
</html>
