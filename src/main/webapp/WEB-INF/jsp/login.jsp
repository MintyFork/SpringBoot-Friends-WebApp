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

  <title>Logowanie</title>

  <!-- Custom fonts for this template-->
  <link href="/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="/resources/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">
	
  <div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">

      <div class="col-xl-10 col-lg-12 col-md-9">

        <div class="card o-hidden border-0 shadow-lg my-5">
          <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
              <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
              <div class="col-lg-6">
                <div class="p-5">
                  <div class="text-center">
                    <h1 class="h4 text-gray-900 mb-4">Zaloguj się!</h1>
                  </div>
                  <form class="user" id="loginForm" action="/login" method="POST">	<!-- action="/login" - Akcja dla SpringSecurity w SecurityConfig.java -->
                    <div class="form-group">
                      <input type="email" class="form-control form-control-user" name="email" id="email" aria-describedby="emailHelp" placeholder="Wpisz adres email...">
                    </div>
                    <div class="form-group">
                      <input type="password" class="form-control form-control-user" name="pass" id="pass" placeholder="Podaj hasło...">
                    </div>
                    <div class="form-group">
                      <div class="custom-control custom-checkbox small">
                        <input type="checkbox" class="custom-control-input" id="customCheck">
                        <label class="custom-control-label" for="customCheck">Zapamiętaj mnie!</label>
                      </div>
                    </div>
						<input class="btn btn-primary btn-user btn-block" type="submit" value="Zaloguj">	
					<c:if test="${not empty param.error}"> <!-- Chwyta komunikat z SecurityConfig o błędzie logowania -->
						<hr>
						<font class="medium"><s:message code="error.logowanie"/></font>
					</c:if>
                  </form>
                  <hr>
                  <div class="text-center">
                    <a class="small" href="forgot-password.html">Zapomniałeś hasła?</a>
                  </div>
                  <div class="text-center">
                    <a class="small" href="register">Zarejestruj się!</a>
                  </div>
                </div>
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
