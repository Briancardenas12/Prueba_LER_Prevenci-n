
package controlador;

import modelo.Persona;
import modelo.PersonaDaoJDBC;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


// Declaración del servlet y la URL asociada
@WebServlet(name = "ServletControlador", urlPatterns = {"/ServletControlador"})
public class ServletControlador extends HttpServlet {
    
    
    // Método para la edición de un personas
    protected void editarPersona(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idPersona = Integer.parseInt(request.getParameter("idPersona"));
        Persona persona = new PersonaDaoJDBC().buscar(new Persona(idPersona));
        request.setAttribute("persona", persona);
        String jspEditar = "/WEB-INF/vista/persona/editarPersona.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }
    
    // Método para la acción por defecto
    protected void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Se lista la información de todos los personas
        List<Persona> personas = new PersonaDaoJDBC().listar();
        System.out.println("persona = " + personas);
        // Se crea una sesión y se establecen los atributos "personas" y "totalPersonas"
        HttpSession sesion = request.getSession();
        sesion.setAttribute("personas", personas);
        sesion.setAttribute("totalPersonas", personas.size());

        // Se redirige al archivo "personas.jsp"
        response.sendRedirect("personas.jsp");
    }  
    
    
    protected void modificarPersona(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idPersona = Integer.parseInt(request.getParameter("idPersona"));
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String cedula = request.getParameter("cedula");
        LocalDate fechaNacimiento = LocalDate.parse(request.getParameter("fecha_nacimiento"));

        
        Persona persona = new Persona(idPersona, nombres, apellidos, cedula, fechaNacimiento);
        int registrosModificados = new PersonaDaoJDBC().actualizar(persona);
        this.accionDefault(request, response);
    }
    
    protected void eliminarPersona(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idPersona = Integer.parseInt(request.getParameter("idPersona"));
        Persona persona = new Persona(idPersona);
        int registrosModificados = new PersonaDaoJDBC().eliminar(persona);
        this.accionDefault(request, response);
    }
    
    protected void insertarPersona(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String cedula = request.getParameter("cedula");
        LocalDate fechaNacimiento = LocalDate.parse(request.getParameter("fecha_nacimiento"));

        Persona persona = new Persona(nombres, apellidos, cedula, fechaNacimiento);
        int registrosModificados = new PersonaDaoJDBC().insertar(persona);
        this.accionDefault(request, response);
    }
    
    // Método para el manejo de solicitudes GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            // Selección de la acción a realizar según el parámetro "accion" enviado
            switch (accion) {
                // En caso de que la acción sea "editar", se llama al método correspondiente
                case "editar":
                    this.editarPersona(request, response);
                    break;
                // En caso de que la acción sea "eliminar", se llama al método correspondiente
                case "eliminar":
                    this.eliminarPersona(request, response);
                    break;
                // Si no se encuentra una acción válida, se llama al método por defecto
                default:
                    this.accionDefault(request, response);
            }

        } else {
            // Si no se especifica una acción, se llama al método por defecto
            this.accionDefault(request, response);
        }
    }

   // Método para el manejo de solicitudes POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            // Selección de la acción a realizar según el parámetro "accion" enviado
            switch (accion) {
                // En caso de que la acción sea "insertar", se llama al método correspondiente
                case "insertar":
                    this.insertarPersona(request, response);
                    break;
                // En caso de que la acción sea "modificar", se llama al método correspondiente
                case "modificar":
                    this.modificarPersona(request, response);
                    break;
                // En caso de que la acción sea "eliminar", se llama al método correspondiente
                case "eliminar":
                    this.eliminarPersona(request, response);
                    break;
                // Si no se encuentra una acción válida, se llama al método por defecto
                default:
                    this.accionDefault(request, response);
            }

        } else {
            // Si no se especifica una acción, se llama al método por defecto
            this.accionDefault(request, response);
        }
    } 
}
