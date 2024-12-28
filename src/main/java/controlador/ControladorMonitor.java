/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Dialog;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import javax.persistence.NoResultException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import modelo.Actividad;
import modelo.Monitor;
import modelo.MonitorDAO;
import modelo.SocioDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import vista.VistaActividad;
import vista.VistaCRUDMonitor;
import vista.VistaMensajes;
import vista.VistaMonitor;

public class ControladorMonitor {

    private Session session;
    private SessionFactory sessionFactory = null;
    private MonitorDAO monitorDAO = null;
    private VistaMensajes vMensajes = new VistaMensajes();
    private VistaActividad vAct = new VistaActividad();
    private Transaction tr;

    public ControladorMonitor(SessionFactory sesion) {
        this.sessionFactory = sesion;
        monitorDAO = new MonitorDAO();
    }

    public void insertarMonitor(VistaCRUDMonitor vCRUDMonitor, Monitor m) {
        session = sessionFactory.openSession();
        tr = session.beginTransaction();
        try {

            session.saveOrUpdate(m);
            tr.commit();
            JOptionPane.showMessageDialog(vCRUDMonitor,
                    "Monitor insertado correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            vCRUDMonitor.dispose();
        } catch (Exception ex) {
            tr.rollback();
            System.out.println("Error al insertar monitor " + ex.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void bajaMonitor(VistaMonitor vMonitor, String id) {
        session = sessionFactory.openSession();
        tr = session.beginTransaction();
        Monitor mon = session.get(Monitor.class, id);
        int result = JOptionPane.showConfirmDialog(vMonitor, "Seguro que quieres dar de baja a " + mon.getNombre() + "?", "Atención", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        switch (result) {
            case 0:
                session.delete(mon);
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
    
    public ArrayList<Monitor> pideMonitores() throws Exception {
        ArrayList<Monitor> lMonitores = MonitorDAO.listaMonitores(sessionFactory);
        return lMonitores;
    }

    public void informacionActividadPorMonitor() {
        Session sesion = sessionFactory.openSession();
        Transaction tr = sesion.beginTransaction();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca el dni del monitor a buscar: ");
        String dni = scanner.nextLine();
        try {
            Query consulta = sesion.createQuery("SELECT m FROM Monitor m WHERE m.dni = :dni", Monitor.class).setParameter("dni", dni);
            Monitor monitor = (Monitor) consulta.getSingleResult();
            Set<Actividad> actividades = monitorDAO.actividadesMonitor(sesion, monitor);
            //vAct.mostrarActividadNombreYPrecio(actividades);
        } catch (NoResultException e) {
            //vMensajes.mensajeConsola("No se encontró ningún monitor con el DNI proporcionado: " + dni);
        } catch (Exception e) {
            tr.rollback();
            //vMensajes.mensajeConsola("Error al mostrar actividades por monitor " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();

            }
        }
    }

    private void muestraDialog(JDialog d) {
        d.setLocationRelativeTo(null);
        d.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        d.setVisible(true);
    }

}
