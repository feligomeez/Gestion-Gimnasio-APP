/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 * 
 * @author feligomeez
 */
public class ActividadDAO {
    /**
     * Obtiene las actividades asociadas a un monitor específico.
     * 
     * 
     */
    public ActividadDAO() {
    }

    /**
     * Obtiene las actividades asociadas a un monitor específico.
     *
     * @param sesion La sesión de Hibernate utilizada para realizar la
     * operación.
     * @param monitor El objeto Monitor del cual se quieren obtener las
     * actividades.
     * @return Un conjunto (Set) de actividades asociadas al monitor.
     * @throws Exception Si ocurre algún error durante la operación.
     */
    public Set<Actividad> actividadesMonitor(Session sesion, Monitor monitor) throws Exception {
        return monitor.getActividades();
    }

    /**
     * Obtiene una lista de todas las actividades almacenadas en la base de
     * datos.
     *
     * @param sessionFactory La fábrica de sesiones de Hibernate utilizada para
     * abrir la sesión.
     * @return Una lista de todas las actividades disponibles en la base de
     * datos.
     */
    public static ArrayList<Actividad> listaActividades(SessionFactory sessionFactory) {
        Session sesion = sessionFactory.openSession();
        ArrayList<Actividad> actividades = new ArrayList<>();
        try {
            Query<Actividad> query = sesion.createQuery("FROM Actividad", Actividad.class);
            actividades = (ArrayList<Actividad>) query.getResultList();
        } catch (Exception e) {
            System.out.println("Error al listar las actividades: " + e.getMessage());
        }
        return actividades;
    }
}
