package servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.ControladoraLogica;
import persistencia.exceptions.NonexistentEntityException;

@WebServlet(name = "SvEliminarUsuario", urlPatterns = {"/SvEliminarUsuario"})
public class SvEliminarUsuario extends HttpServlet {

    ControladoraLogica control = new ControladoraLogica();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Vamos a eliminar un usuario por medio de su id
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            control.eliminarUsuario(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SvEliminarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*Se va a ejecutar automaticamente el get (en la cual se encuentra la 
        lista de la base de datos actualizada, ya que se trae de ahi)
         */
        response.sendRedirect("SvCrearUsuario");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
