
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="complementos/header.jsp"%>
<%@include file="complementos/body.jsp"%>
<h1>Hello everyone</h1>

<div class="p-5 bg-white">
    <div class="text-center">
        <h1 class="h4 text-gray-900 mb-4">Agregar Odontólogo</h1>
    </div>
    <form class="user" action="SvCrearOdonto" method="post">
        <div class="form-group row">
            <div class="col-sm-4 mb-3 mb-sm-0">
                <input type="text" class="form-control form-control-user" id="dni" name="dni"
                       placeholder="DNI">
            </div>
            <div class="col-sm-8 mb-sm-0">
                <input type="text" class="form-control form-control-user" id="especialidad" name="especialidad"
                       placeholder="Especialidad">
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-6 mb-3 mb-sm-0">
                <input type="text" class="form-control form-control-user"
                       id="nombre" name="nombre" placeholder="Nombre">
            </div>
            <div class="col-sm-6">
                <input type="text" class="form-control form-control-user"
                       id="apellido" name="apellido" placeholder="Apellido">
            </div>
        </div>
        <div class="form-group row">

            <div class="col-sm-6 mb-3 mb-sm-0">
                <input type="text" class="form-control form-control-user"
                       id="telefono" name="telefono" placeholder="Teléfono">
            </div>
            <div class="col-sm-6 mb-0 mb-sm-0">
                <input type="date" class="form-control form-control-user"
                       id="fechaNac" name="fechaNac" placeholder="Fecha Nac.">
            </div>
        </div>
        <div class="form-group ">
            <input type="text" class="form-control form-control-user" name="direccion" id="direccion"
                   placeholder="Dirección">
        </div>

        <button href="login.html" class="btn btn-primary btn-user btn-block">
            Agregar
        </button>

    </form>

</div>

<%@include file="complementos/footer.jsp"%>