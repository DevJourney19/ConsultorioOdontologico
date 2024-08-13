
<%@page import="java.util.List"%>
<%@page import="logica.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="complementos/header.jsp"%>
<%@include file="complementos/body.jsp"%>
<h1>Hello World!</h1>

<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Tables</h1>
    <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below.
        For more information about DataTables, please visit the <a target="_blank"
                                                                   href="https://datatables.net">official DataTables documentation</a>.</p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>
        </div>
        <div class="card-body">
            <%
                List<Usuario> listaUsuarios = (List<Usuario>) request.getSession().getAttribute("listaUsuarios");
            %>
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Usuario</th>
                            <th>Rol</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>Id</th>
                            <th>Usuario</th>
                            <th>Rol</th>
                            <th>Acciones</th>

                        </tr>
                    </tfoot>
                    <tbody>
                        <%for (Usuario usu : listaUsuarios) {%>
                        <tr>
                            <td><%=usu.getId()%></td>
                            <td><%=usu.getUsuario()%></td>
                            <td><%=usu.getRol()%></td>
                            <td style="display: flex; gap: 10px">
                                <!-- Formulario de editar -->
                                <!-- Aca primero se va a encontrar el usuario dependiendo del id, la cual se estableció por cada fila-->
                                <form action="SvEditarUsuario" method="get">
                                    <button style="background: gold; border: transparent" type="submit">Editar</button>
                                    <input type="hidden" id="id_eliminar" name="id" value="<%= usu.getId()%>">
                                </form>

                                <!-- Formulario de eliminar -->
                                <form action="SvEliminarUsuario" method="post">
                                    <button style="background: #ca2819; color: white; border: transparent" type="submit">Eliminar</button>
                                    <!-- Necesario para poder detectar el id que se va a eliminar-->
                                    <input type="hidden" id="id_editar" name="id" value="<%= usu.getId()%>">
                                </form>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


<%@include file="complementos/footer.jsp"%>
