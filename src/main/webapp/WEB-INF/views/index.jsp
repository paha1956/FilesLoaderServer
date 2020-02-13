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
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body class="text-center">
	<h1>
	<br>База данных загруженных файлов.<br><br>
	</h1>

    <form method="get" action="filesdataprint" class="form-signin">
      <div class="w-25 p-3">
        <input type="text" class="form-control" name="userName" placeholder="Имя клиента">
      </div>
      <div class="w-25 p-3">
        <button type="submit" class="btn btn-lg btn-primary btn-block">Загрузить данные</button>
      </div>
    </form>

</body>
</html>
