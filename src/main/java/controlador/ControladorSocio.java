/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import javax.persistence.NoResultException;
import javax.swing.JOptionPane;
import modelo.Actividad;
import modelo.Monitor;
import modelo.Socio;
import modelo.SocioDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import vista.VistaActividad;
import vista.VistaAltaSocios;
import vista.VistaBajaSocios;
import vista.VistaCRUDSocio;
import vista.VistaMensajes;
import vista.VistaSocio;


public class ControladorSocio {

    private SessionFactory sessionFactory = null;
    private Session session;
    private Transaction tr;
    private SocioDAO socioDao = null;
    private VistaMensajes vMensajes = new VistaMensajes();
    private VistaActividad vAct = new VistaActividad();

    public ControladorSocio(SessionFactory sesion) {
        this.sessionFactory = sesion;
        this.socioDao = new SocioDAO();

    }
    
    public void insertarSocio(VistaCRUDSocio vCRUDSocio, Socio s){
        session = sessionFactory.openSession();
                tr = session.beginTransaction();
                try {                    
                    session.saveOrUpdate(s);
                    tr.commit();
                    JOptionPane.showMessageDialog(vCRUDSocio,
                            "Socio insertado correctamente.",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);
                    vCRUDSocio.dispose();
                } catch (Exception ex) {
                    tr.rollback();
                    vMensajes.mensaje("Error al confirmar tu transacción");
                } finally {
                    if (session != null && session.isOpen()) {
                        session.close();
                    }
                }
    }
    
    public void bajaSocio(VistaSocio vSocio, String id){
        session = sessionFactory.openSession();
                    tr = session.beginTransaction();
                    
                    Socio soc = session.get(Socio.class, id);
                    int result = JOptionPane.showConfirmDialog(vSocio, "Seguro que quieres dar de baja a " + soc.getNombre() + "?", "Atención", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                    switch (result) {
                        case 0:
                            session.delete(soc);
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
    
    public ArrayList<Socio> pideSocios() throws Exception {
        ArrayList<Socio> lSocios = SocioDAO.listaSocios(sessionFactory);
        return lSocios;
    }
    
    public ArrayList<Socio> pideSociosFiltrado(String texto, String atributo) throws Exception {
        ArrayList<Socio> lSocios = SocioDAO.listaSociosFiltrados(sessionFactory, texto, atributo);
        return lSocios;
    }
    
    public void altaActividadSocio(VistaAltaSocios vAltaSocio, String nombreSoc, String nombreAct){
        session = sessionFactory.openSession();
                tr = session.beginTransaction();
                try {
                    Socio socio = (Socio) session.createNativeQuery("SELECT * FROM SOCIO s WHERE s.nombre = :nombre", Socio.class)
                             .setParameter("nombre", nombreSoc)
                             .uniqueResult();
                                      
                    if (socio == null) {
                        JOptionPane.showMessageDialog(vAltaSocio,
                                "El socio seleccionado no existe.",
                                "Error de validación",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Actividad actividad = session.createNativeQuery("SELECT * FROM ACTIVIDAD a WHERE a.nombre = :nombre", Actividad.class)
                            .setParameter("nombre", nombreAct)
                            .uniqueResult();
                    if (actividad == null) {
                        JOptionPane.showMessageDialog(vAltaSocio,
                                "La actividad seleccionada no existe.",
                                "Error de validación",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (socio.getActividades().contains(actividad)) {
                        JOptionPane.showMessageDialog(vAltaSocio,
                                "El socio ya está inscrito en esta actividad.",
                                "Error de validación",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    socio.getActividades().add(actividad);
                    actividad.getSocios().add(socio);
                    session.saveOrUpdate(socio);
                    session.saveOrUpdate(actividad);
                    tr.commit();
                    JOptionPane.showMessageDialog(vAltaSocio,
                            "Actividad añadida correctamente al socio.",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);
                    vAltaSocio.dispose();
                    } catch (Exception ex) {
                    tr.rollback();
                    JOptionPane.showMessageDialog(vAltaSocio,
                            "Error al añadir la actividad al socio.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } finally {
                    if (session != null && session.isOpen()) {
                        session.close();
                    }
                }
    }
    
    public void bajaActividadSocio(VistaBajaSocios vBajaSocio, String nombreSoc, String nombreAct){
        session = sessionFactory.openSession();
                tr = session.beginTransaction();
                try {                    
                    Socio socio = session.createNativeQuery("SELECT * FROM SOCIO s WHERE s.nombre = :nombre", Socio.class)
                            .setParameter("nombre", nombreSoc)
                            .uniqueResult();
                    if (socio == null) {
                        JOptionPane.showMessageDialog(vBajaSocio,
                                "El socio seleccionado no existe.",
                                "Error de validación",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Actividad actividad = session.createNativeQuery("SELECT * FROM ACTIVIDAD a WHERE a.nombre = :nombre", Actividad.class)
                            .setParameter("nombre", nombreAct)
                            .uniqueResult();

                    if (actividad == null) {
                        JOptionPane.showMessageDialog(vBajaSocio,
                                "La actividad seleccionada no existe.",
                                "Error de validación",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (!socio.getActividades().contains(actividad)) {
                        JOptionPane.showMessageDialog(vBajaSocio,
                                "El socio no está inscrito en la actividad seleccionada.",
                                "Error de validación",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    socio.getActividades().remove(actividad);
                    actividad.getSocios().remove(socio);
                    session.saveOrUpdate(socio);
                    session.saveOrUpdate(actividad);
                    tr.commit();
                    JOptionPane.showMessageDialog(vBajaSocio,
                            "Actividad eliminada correctamente del socio.",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);
                    vBajaSocio.dispose();
                } catch (Exception ex) {
                    tr.rollback();
                    JOptionPane.showMessageDialog(vBajaSocio,
                            "Error al eliminar la actividad del socio.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } finally {
                    if (session != null && session.isOpen()) {
                        session.close();
                    }
                }
    }
    
    public void altaSocio() {
        Session sesion = sessionFactory.openSession();
        Transaction tr = sesion.beginTransaction();
        Scanner scanner = new Scanner(System.in);
        try {
            String numS = "",
                    n = "",
                    dni = "",
                    tlf = "",
                    fnac = "",
                    mail = "",
                    fentrada = "",
                    ca = "";
            char cat = ' ';
            boolean encontrado = true;
            while(encontrado){
                encontrado = false;
                System.out.println("Introduzca el NUMERO DE SOCIO del socio a crear: ");
                numS = scanner.nextLine();
                System.out.println("Introduzca el NOMBRE del socio a crear: ");
                n = scanner.nextLine();
                System.out.println("Introduzca el DNI del socio a crear: ");
                dni = scanner.nextLine();
                System.out.println("Introduzca la FECHA DE NACIMIENTO del socio a crear: ");
                fnac = scanner.nextLine();
                System.out.println("Introduzca el TELEFONO del socio a crear: ");
                tlf = scanner.nextLine();
                System.out.println("Introduzca el CORREO del socio a crear: ");
                mail = scanner.nextLine();
                System.out.println("Introduzca la FECHA DE ENTRADA del socio a crear: ");
                fentrada = scanner.nextLine();
                System.out.println("Introduzca la CATEGORIA del socio a crear: ");
                ca = scanner.nextLine();
                cat = ca.charAt(0);

                Query consulta = sesion.createNamedQuery("Socio.findAll", Socio.class);
                ArrayList<Socio> socios = (ArrayList<Socio>) consulta.getResultList();
                for (Socio s : socios) {
                    if (s.getNumeroSocio().equalsIgnoreCase(numS) || s.getDni().equalsIgnoreCase(dni)) {
                        encontrado = true;
                        //vMensajes.mensajeConsola("Ya hay un Socio con ese dni o codigo de socio. Vuelve a introducir los datos...");
                        break;
                    }
                }
            }
  

            Socio nuevoSocio = new Socio(numS, n, dni, fnac, tlf, mail, fentrada, cat);
            socioDao.insertaSocio(sesion, nuevoSocio);
            tr.commit();
            //vMensajes.mensajeConsola("Socio insertado correctamente");

        } catch (Exception e) {
            tr.rollback();
           // vMensajes.mensajeConsola("Error en la insercion del socio");
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }
    
     public void informacionActividadPorSocio() {
        Session sesion = sessionFactory.openSession();
        Transaction tr = sesion.beginTransaction();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca el numero de socio del socio a buscar: ");
        String n = scanner.nextLine();
        try {
            Query consulta = sesion.createQuery("SELECT s FROM Socio s WHERE s.numeroSocio = :nSoc", Socio.class).setParameter("nSoc", n);
            Socio socio = (Socio) consulta.getSingleResult();
            Set<Actividad> actividades = socioDao.actividadesSocio(sesion, socio);
            //vAct.mostrarActividadNombreYPrecio(actividades);
        } catch (NoResultException e){
            //vMensajes.mensajeConsola("No se encontró ningún socio con el NUMERO DE SOCIO proporcionado: " + n);
        }catch (Exception e) {
            tr.rollback();
            //vMensajes.mensajeConsola("Error al mostrar actividades por socio " + e.getMessage());
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();

            }
        }
    }
}
