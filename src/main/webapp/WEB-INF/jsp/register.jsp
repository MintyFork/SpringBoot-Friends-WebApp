<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Rejestracja</title>

  <!-- Custom fonts for this template-->
  <link href="/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="/resources/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

  <div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
      <div class="card-body p-0">
        <!-- Nested Row within Card Body -->
        <div class="row">
          <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
          <div class="col-lg-7">
            <div class="p-5">
              <div class="text-center">
                <h1 class="h4 text-gray-900 mb-4">Zarejestruj się!</h1>
              </div>
              <sf:form class="user" id="usersForm" action="adduser" modelAttribute="user" enctype="multipart/form-data" method="POST">
                <div class="form-group">
                  <sf:input path="name" class="form-control form-control-user" id="name" placeholder="Podaj login" />
                </div>
                <div class="form-group">
                  <sf:input path="email" class="form-control form-control-user" id="email" placeholder="Podaj email" />
                </div>
                <div class="form-group row">
                  <div class="col-sm-6 mb-3 mb-sm-0">
                    <sf:password path="pass" class="form-control form-control-user" id="pass" placeholder="Podaj haslo" />
                  </div>
                  <div class="col-sm-6">
                    <input type="password" class="form-control form-control-user" id="exampleRepeatPassword" placeholder="Powtórz hasło">
                  </div>
                </div>
                <input type="submit" value="Zarejestruj konto!" class="btn btn-primary btn-user btn-block">
                <font class="medium">
                <sf:errors path="name"><hr>Imię nie może być puste</sf:errors>
                <sf:errors path="email"><hr>Adres email jest nieprawidłowy</sf:errors>
				<sf:errors path="pass"><hr>Hasło powinno zawierać dużą i małą literę, cyfrę oraz jeden ze znaków !, @, #, $</sf:errors>
				</font>
              </sf:form>
              <hr>
              <div class="text-center">
                <a class="small" href="forgot-password.html">Zapomniałeś hasła?</a>
              </div>
              <div class="text-center">
                <a class="small" href="login">Masz już konto? Zaloguj się!</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>

  <!-- Bootstrap core JavaScript-->
  <script src="/resources/vendor/jquery/jquery.min.js"></script>
  <script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="/resources/js/sb-admin-2.min.js"></script>

</body>

</html>
