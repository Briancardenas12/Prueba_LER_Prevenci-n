
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class PersonaDaoJDBC {
    
    private static final String SQL_SELECT
            = "SELECT *FROM persona";
    private static final String SQL_SELECT_BY_ID
            = "SELECT id_persona, nombres, apellidos, cedula, fecha_nacimiento FROM persona WHERE id_persona=?";
    private static final String SQL_INSERT
            = "INSERT INTO persona(nombres, apellidos, cedula, fecha_nacimiento) VALUES(?,?,?,?)";
    private static final String SQL_UPDATE
            = "UPDATE persona SET nombres=?, apellidos=?, cedula=?, fecha_nacimiento=? WHERE id_persona=?";
    private static final String SQL_DELETE
            = "DELETE FROM persona WHERE id_persona=?";
    
    // Método para obtener la lista completa de las personas
    public List<Persona> listar(){
        List<Persona> personas = new ArrayList<>();
        // Conexión a la base de datos y consulta SQL
        try(Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_SELECT); ResultSet rs = stmt.executeQuery()){
            // Recorrido de resultados y creación de objetos persona
            while(rs.next()){
                int idPersona = rs.getInt("id_persona");
                String nombres = rs.getString("nombres");
                String apellidos = rs.getString("apellidos");
                String cedula = rs.getString("cedula");
                LocalDate fechaNacimiento = rs.getDate("fecha_nacimiento").toLocalDate();
                
                Persona persona = new Persona(idPersona, nombres, apellidos, cedula, fechaNacimiento);
                personas.add(persona);
            }
            
        } catch (Exception e) {
            System.out.println("Error al listas a las personas " + e);
        }
        return personas;
    }
    
    // Método para buscar una persona por su id
    public Persona buscar(Persona persona) {
        // Conexión a la base de datos y consulta SQL
        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_ID)) {
            stmt.setInt(1, persona.getIdPersona());
            // Recorrido de resultados y actualización del objeto Persona pasado como parámetro
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nombres = rs.getString("nombres");
                    String apellidos = rs.getString("apellidos");
                    String cedula = rs.getString("cedula");
                    LocalDate fechaNacimiento = rs.getDate("fecha_nacimiento").toLocalDate();
                    persona.setNombres(nombres);
                    persona.setApellidos(apellidos);
                    persona.setCedula(cedula);
                    persona.setFechaNacimiento(fechaNacimiento);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar a la persona: " + e.getMessage());
        }
        return persona;
    }
    
    // Método para insertar una nueva persona  en la base de datos
    public int insertar(Persona persona) {
        int rows = 0;
        // Conexión a la base de datos y consulta SQL
        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {
            // Se establecen los parámetros de la consulta preparada
            stmt.setString(1, persona.getNombres());
            stmt.setString(2, persona.getApellidos());
            stmt.setString(3, persona.getCedula());
            stmt.setDate(4, java.sql.Date.valueOf(persona.getFechaNacimiento()));
            // Se ejecuta la consulta y se obtiene el número de filas afectadas
            rows = stmt.executeUpdate();
        } catch (Exception e) {
            // Se maneja cualquier excepción que se produzca durante la ejecución de la consulta
            System.out.println("Error al insertar una persona: " + e.getMessage());
        }

        return rows;
    }
    
    // Método para actualizar una persona existente en la base de datos
    public int actualizar(Persona persona) {
        int rows = 0;
        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE)) {
            // Se establecen los parámetros de la consulta preparada
            stmt.setString(1, persona.getNombres());
            stmt.setString(2, persona.getApellidos());
            stmt.setString(3, persona.getCedula());
            stmt.setDate(4, java.sql.Date.valueOf(persona.getFechaNacimiento()));
            stmt.setInt(5, persona.getIdPersona());
            // Se ejecuta la consulta y se obtiene el número de filas afectadas
            rows = stmt.executeUpdate();
        } catch (Exception e) {
            // Se maneja cualquier excepción que se produzca durante la ejecución de la consulta
            System.out.println("Error al actualizar a la persona: " + e.getMessage());
        }
        return rows;
    }
    
    // Método para eliminar una persona existente en la base de datos
    public int eliminar(Persona persona) {
        int rows = 0;
        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_DELETE)) {
            // Se establecen los parámetros de la consulta preparada
            stmt.setInt(1, persona.getIdPersona());
            // Se ejecuta la consulta y se obtiene el número de filas afectadas
            rows = stmt.executeUpdate();
        } catch (Exception e) {
            // Se maneja cualquier excepción que se produzca durante la ejecución de la consulta
            System.out.println("Error al eliminar a la persona: " + e.getMessage());
        }
        return rows;
    }
    
}
