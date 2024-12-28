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

public class SocioDAO {

    /**
     * Inserta un nuevo objeto Socio en la base de datos.
     *
     * @param sesion La sesión de Hibernate utilizada para realizar la operación
     * de inserción.
     * @param socio El objeto Socio que se desea insertar en la base de datos.
     * @throws Exception Si ocurre un error durante la inserción del socio.
     */
    public void insertaSocio(Session sesion, Socio socio) throws Exception {
        sesion.save(socio);
    }

    /**
     * Recupera una lista de todos los socios almacenados en la base de datos.
     *
     * @param sessionFactory La fábrica de sesiones de Hibernate utilizada para
     * abrir sesiones.
     * @return Una lista de objetos Socio que representan a los socios en la
     * base de datos.
     */
    public static ArrayList<Socio> listaSocios(SessionFactory sessionFactory) {
        Session sesion = sessionFactory.openSession();
        ArrayList<Socio> socios = new ArrayList<>();
        try {
            Query<Socio> query = sesion.createQuery("FROM Socio", Socio.class);
            socios = (ArrayList<Socio>) query.getResultList();
        } catch (Exception e) {
            System.out.println("Error al listar los socios: " + e.getMessage());
        }
        return socios;
    }

    public static ArrayList<Socio> listaSociosFiltrados(SessionFactory sessionFactory, String texto, String atributo) {
        Session sesion = sessionFactory.openSession();
        ArrayList<Socio> socios = new ArrayList<>();
        try {
            String hql = "FROM Socio WHERE LOWER(" + atributo + ") LIKE LOWER(:texto)";
            Query<Socio> query = sesion.createQuery(hql, Socio.class);
            query.setParameter("texto", "%" + texto + "%");
            socios = (ArrayList<Socio>) query.getResultList();
        } catch (Exception e) {
            System.out.println("Error al listar los socios: " + e.getMessage());
        }
        return socios;
    }

    /**
     * Obtiene el conjunto de actividades asociadas a un socio específico.
     *
     * @param sesion La sesión de Hibernate utilizada para acceder a la base de
     * datos.
     * @param socio El objeto Socio cuya lista de actividades se desea
     * recuperar.
     * @return Un conjunto de objetos Actividad que representan las actividades
     * asociadas al socio.
     * @throws Exception Si ocurre un error al obtener las actividades del
     * socio.
     */
    public Set<Actividad> actividadesSocio(Session sesion, Socio socio) throws Exception {
        return socio.getActividades();
    }

    /**
     * Calcula la cuota mensual de un socio en función de las actividades que
     * realiza y de la categoría a la que pertenece.
     *
     * @param socio el socio para el cual se desea calcular la cuota mensual.
     * Debe tener asociadas actividades y una categoría válida.
     * @return la cantidad total que debe pagar el socio mensualmente considerando
     * las actividades inscritas y el descuento según su categoría.
     */
    public static double calcularCuotaMensual(Socio socio) {
        double descuento = obtenerDescuento(socio.getCategoria());
        double total = 0;

        Set<Actividad> actividades = socio.getActividades();
        for (Actividad actividad : actividades) {
            total += actividad.getPrecioBaseMes() * (1 - descuento);
        }

        return total;
    }

    /**
     * Obtiene el porcentaje de descuento asociado a la categoría de un socio.
     *
     * @param categoria la categoría del socio, representada por un carácter
     * 'A', 'B', 'C', 'D', 'E'.
     * @return el porcentaje de descuento correspondiente a la categoría,
     * expresado como un valor entre 0.0 (sin descuento) y 0.4 (40% de
     * descuento).
     * @throws IllegalArgumentException si la categoría proporcionada no es
     * válida.
     */
    private static double obtenerDescuento(Character categoria) {
        switch (categoria) {
            case 'A':
                return 0.0;
            case 'B':
                return 0.1;
            case 'C':
                return 0.2;
            case 'D':
                return 0.3;
            case 'E':
                return 0.4;
            default:
                throw new IllegalArgumentException("Categoría no válida: " + categoria);
        }
    }
}
