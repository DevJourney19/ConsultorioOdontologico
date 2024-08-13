package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.ControladoraLogica;
import logica.Usuario;

@WebServlet(name = "SvCrearUsuario", urlPatterns = {"/SvCrearUsuario"})
public class SvCrearUsuario extends HttpServlet {

    ControladoraLogica control = new ControladoraLogica();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Usuario> listaUsuarios = control.listaUsuarios();
//        HttpSession mysesion = request.getSession().setAttribute("listaUsuarios", listaUsuarios);
        HttpSession mysession = request.getSession();
        mysession.setAttribute("listaUsuarios", listaUsuarios);
        
        response.sendRedirect("verUsuarios.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String rol = request.getParameter("rol");
        String contrasenia = request.getParameter("contrasenia");

        Usuario usu = new Usuario();
        usu.setPass(contrasenia);
        usu.setRol(rol);
        usu.setUsuario(usuario);
        control.crearUsuario(usu);

        response.sendRedirect("index.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
