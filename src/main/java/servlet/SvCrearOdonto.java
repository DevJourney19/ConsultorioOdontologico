package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.ControladoraLogica;
import logica.Odontologo;

@WebServlet(name = "SvCrearOdonto", urlPatterns = {"/SvCrearOdonto"})
public class SvCrearOdonto extends HttpServlet {

    ControladoraLogica control = new ControladoraLogica();
//    SimpleDateFormat formatoDate = new SimpleDateFormat("yyyy-MM-dd");
    DateTimeFormatter formato = new DateTimeFormatterBuilder().append(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toFormatter();

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

        String dni = request.getParameter("dni");
        String especialidad = request.getParameter("especialidad");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String fechaNacS = request.getParameter("fechaNac");

        LocalDate fechaNac = LocalDate.parse(fechaNacS, formato);

        Odontologo odonto = new Odontologo();
        odonto.setDni(dni);
        odonto.setEspecialidad(especialidad);
        odonto.setNombre(nombre);
        odonto.setApellido(apellido);
        odonto.setDireccion(direccion);
        odonto.setTelefono(telefono);
        odonto.setFechaNac(fechaNac);

        control.crearOdontologo(odonto);
        response.sendRedirect("index.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
