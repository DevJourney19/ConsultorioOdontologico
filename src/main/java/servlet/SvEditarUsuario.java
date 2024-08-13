package servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.ControladoraLogica;
import logica.Usuario;

@WebServlet(name = "SvEditarUsuario", urlPatterns = {"/SvEditarUsuario"})
public class SvEditarUsuario extends HttpServlet {

    ControladoraLogica control = new ControladoraLogica();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Usuario usuarioEditar = control.encontrarUsuario(id);
        System.out.println(usuarioEditar.getId());
        System.out.println(usuarioEditar.getUsuario());
        System.out.println(usuarioEditar.getRol());
        HttpSession session = request.getSession();
        session.setAttribute("usuarioEditar", usuarioEditar);

        response.sendRedirect("editarUsuario.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Traemos el usuario encontrado en primer lugar ...
        Usuario usu = (Usuario) request.getSession().getAttribute("usuarioEditar");

        String nUsuario = request.getParameter("usuario");
        String contrasenia = request.getParameter("contrasenia");
        String rol = request.getParameter("rol");

        usu.setUsuario(nUsuario);
        usu.setPass(contrasenia);
        usu.setRol(rol);

        try {
            control.editarUsuario(usu);
        } catch (Exception ex) {
            Logger.getLogger(SvEditarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("SvCrearUsuario");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
