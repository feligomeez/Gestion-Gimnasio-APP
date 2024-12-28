/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Actividad;
import modelo.ActividadDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import vista.VistaActividad;
import vista.VistaCRUDActividad;
import vista.VistaMensajes;

public class ControladorActividad {

    private SessionFactory sessionFactory = null;
    private Session session;
    private Transaction tr;
    private ActividadDAO actividadDAO = null;
    private VistaMensajes vMensajes = new VistaMensajes();

    public ControladorActividad(SessionFactory sesion) {
        this.sessionFactory = sesion;
        actividadDAO = new ActividadDAO();
    }

    public void insertarActividad(VistaCRUDActividad vCRUDActividad, Actividad a) {
        try {
            session = sessionFactory.openSession();
            tr = session.beginTransaction();
            session.saveOrUpdate(a);
            tr.commit();
            JOptionPane.showMessageDialog(vCRUDActividad,
                    "Transacción realizada correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            tr.rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }

    public void bajaActividad(VistaActividad vActividad, String id) {
        session = sessionFactory.openSession();
        tr = session.beginTransaction();

        Actividad act = session.get(Actividad.class, id);
        int result = JOptionPane.showConfirmDialog(vActividad, "Seguro que quieres eliminar la actividad " + act.getNombre() + "?", "Atención", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        switch (result) {
            case 0:
                session.delete(act);
                tr.commit();
                break;
            case 1:
                tr.rollback();
                break;
            case 2:
                tr.rollback();
                break;
            case -1:
                tr.rollback();
                break;
        }
    }

    public ArrayList<Actividad> pideActividades() throws Exception {
        ArrayList<Actividad> lActividad = ActividadDAO.listaActividades(sessionFactory);
        return lActividad;
    }
}
