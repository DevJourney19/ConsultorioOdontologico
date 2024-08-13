<%-- 
    Document   : crearUsuario
    Created on : 9 ago 2024, 13:58:04
    Author     : danie
--%>

<%@page import="logica.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="complementos/header.jsp" %>
<%@include file="complementos/body.jsp" %>
<% Usuario usu = (Usuario) request.getSession().getAttribute("usuarioEditar");%>
<div class="p-5 bg-white">
    <div class="text-center">
        <h1 class="h4 text-gray-900 mb-4">Editar Usuario</h1>
    </div>
    <form class="user" action="SvEditarUsuario" method="post">
        <div class="form-group ">

            <div class="col-lg-4 mx-auto mb-3 mb-sm-3 ">
                <input type="text" class="form-control form-control-user" id="usuario" name="usuario"
                       placeholder="Usuario" value="<%= usu.getUsuario()%>">
            </div>
            <div class="col-lg-4 mb-3 mx-auto mb-sm-3 ">
                <input type="password" class="form-control form-control-user" id="contrasenia" name="contrasenia"
                       placeholder="Contraseña" value="<%= usu.getPass()%>">
            </div>
            <div class="col-lg-4 mx-auto mb-3 mb-sm-3 ">
                <input type="text" class="form-control form-control-user" id="rol" name="rol"
                       placeholder="Rol" value="<%= usu.getRol()%>">
            </div>
            <button class="btn btn-primary btn-user btn-block col-6 col-lg-2 mx-auto
                    text-center ">
                Guardar cambios
            </button>
        </div>

    </form>

</div>


<%@include file="complementos/footer.jsp" %>
