<%-- 
    Document   : crearUsuario
    Created on : 9 ago 2024, 13:58:04
    Author     : danie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="complementos/header.jsp" %>
<%@include file="complementos/body.jsp" %>

<div class="p-5 bg-white">
    <div class="text-center">
        <h1 class="h4 text-gray-900 mb-4">Agregar Odontólogo</h1>
    </div>
    <form class="user" action="SvCrearUsuario" method="post">
        <div class="form-group ">


            <div class="col-lg-4 mx-auto mb-3 mb-sm-3 ">
                <input type="text" class="form-control form-control-user" id="dni" name="usuario"
                       placeholder="Usuario">
            </div>
            <div class="col-lg-4 mb-3 mx-auto mb-sm-3 ">
                <input type="password" class="form-control form-control-user" id="contrasenia" name="contrasenia"
                       placeholder="Contraseña">
            </div>
            <div class="col-lg-4 mx-auto mb-3 mb-sm-3 ">
                <input type="text" class="form-control form-control-user" id="dni" name="rol"
                       placeholder="Rol">
            </div>
            <button  class="btn btn-primary btn-user btn-block col-6 col-lg-2 mx-auto
                    text-center ">
                Agregar
            </button>
        </div>




    </form>

</div>


<%@include file="complementos/footer.jsp" %>
