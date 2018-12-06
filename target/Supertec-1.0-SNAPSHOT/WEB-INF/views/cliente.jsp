<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../../../../favicon.ico">
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
              integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
              crossorigin="anonymous">
        <script
            src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>

        <title>Registro en SuperTec</title>

        <!-- Bootstrap core CSS -->

    </head>

    <body class="text-center">
        <div class="container col-4 ">
            <sf:form action="cliente_save" method="post"
                   commandName="cliente" class="form-signin">
                <img class="mb-4" src="../../assets/brand/bootstrap-solid.svg" alt=""
                     width="72" height="72">
                <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
                <div class="container">
                    <label for="inputPassword" class="sr-only">Ingresar un Usuario</label>
                    <sf:input type="text" path="usuario" placeholder="Ingrese Usuario" class="form" />

                </div>
                <div class="container">
                    <label for="inputEmail" class="sr-only">Email address</label>
                    <sf:input type="email" path="correo" class="form"
                              placeholder="Email address" />
                    <label for="inputPassword" class="sr-only">Password</label>
                    <sf:input type="password" path="contrasenia" class="form"
                              placeholder="Password" />
                    <label for="inputPassword" class="sr-only">Rut</label>
                    <sf:input type="text" path="rut" placeholder="11.111.111-k" class="form"/>

                    <label for="inputPassword" class="sr-only">Nombre Completo</label>
                    <sf:input type="text" path="nombre" placeholder="Ingrese Su Nombre" class="form" />
                    <label for="inputPassword" class="sr-only">Telefono</label>
                    <sf:input type="text" path="telefono" placeholder="cod+num"  class="form"/>

                    <label for="inputPassword" class="sr-only">Fecha de Nacimiento</label>
                    <sf:input type="date" path="fechaNacimiento" class="form" />

                    
                </div>
                    <button class="btn btn-lg btn-primary btn-block" type="submit" value="Guardar"/>
                    <p class="mt-5 mb-3 text-muted">&copy; SuperTec-2018</p>
            </sf:form>
        </div>
    </body>
</html>
