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


public class MonitorDAO {
    
    /**
     * Recupera una lista de todos los monitores almacenados en la base de datos.
     *
     * @param sessionFactory La fábrica de sesiones de Hibernate utilizada para abrir sesiones.
     * @return Una lista de objetos que representan a los monitores en la base de datos.
     */
    public static ArrayList<Monitor> listaMonitores(SessionFactory sessionFactory) {
        Session sesion = sessionFactory.openSession();
         ArrayList<Monitor> monitores = new ArrayList<>();
        try {
            Query<Monitor> query = sesion.createQuery("FROM Monitor", Monitor.class);
            monitores = (ArrayList<Monitor>) query.getResultList();
        } catch (Exception e) {
            System.out.println("Error al listar los monitores: " + e.getMessage());
        }
        return monitores;
    }
     /**
     * Obtiene el conjunto de actividades asociadas a un monitor específico.
     *
     * @param sesion La sesión de Hibernate utilizada para acceder a la base de datos.
     * @param monitor El objeto Monitor cuya lista de actividades se desea recuperar.
     * @return Un conjunto de objetos Actividad que representan las actividades asociadas al monitor.
     * @throws Exception Si ocurre un error al obtener las actividades del monitor.
     */
    public Set<Actividad> actividadesMonitor(Session sesion, Monitor monitor) throws Exception {
        return monitor.getActividades();
    }
}
