/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelo.Actividad;
import modelo.ActividadDAO;
import modelo.Monitor;
import modelo.MonitorDAO;
import modelo.Socio;
import modelo.SocioDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import vista.VistaActividad;
import vista.VistaAltaSocios;
import vista.VistaBajaSocios;
import vista.VistaCRUDActividad;
import vista.VistaCRUDMonitor;
import vista.VistaCRUDSocio;
import vista.VistaInicio;
import vista.VistaMensajes;
import vista.VistaMonitor;
import vista.VistaPrincipal;
import vista.VistaSocio;

public class ControladorPrincipal implements ActionListener {

    private SessionFactory sessionFactory = null;
    private Session session;
    private Transaction tr;
    private ControladorMonitor cMonitor;
    private ControladorSocio cSocio;
    private ControladorActividad cActividad;
    private VistaMensajes vMensajes = new VistaMensajes();
    private VistaPrincipal vPrincipal;
    private VistaInicio vInicio;
    private VistaSocio vSocio;
    private VistaMonitor vMonitor;
    private VistaActividad vActividad;
    private GestionTablasMonitor GTMonitor;
    private GestionTablasSocio GTSocio;
    private GestionTablasActividad GTActividad;
    private VistaCRUDMonitor vCRUDMonitor;
    private VistaCRUDSocio vCRUDSocio;
    private VistaCRUDActividad vCRUDActividad;
    private VistaAltaSocios vAltaSocio;
    private VistaBajaSocios vBajaSocio;
    private final String actionMonitor = "Gestión de Monitores";
    private final String actionSocio = "Gestión de Socios";
    private final String actionActividad = "Gestión de Actividades";

    public ControladorPrincipal(SessionFactory sesion) {
        this.sessionFactory = sesion;
        vInicio = new VistaInicio();
        vSocio = new VistaSocio();
        vMonitor = new VistaMonitor();
        vActividad = new VistaActividad();
        vPrincipal = new VistaPrincipal();
        GTMonitor = new GestionTablasMonitor(vMonitor);
        GTSocio = new GestionTablasSocio(vSocio);
        GTActividad = new GestionTablasActividad(vActividad);
        vCRUDMonitor = new VistaCRUDMonitor();
        vCRUDSocio = new VistaCRUDSocio();
        vCRUDActividad = new VistaCRUDActividad();
        vAltaSocio = new VistaAltaSocios();
        vBajaSocio = new VistaBajaSocios();

        addListener();
        vInicio.setSize(900, 500);
        vInicio.setLocation(0, 0);

        vPrincipal.setSize(900, 560);
        vPrincipal.setLocationRelativeTo(null);
        vPrincipal.content.removeAll();
        vPrincipal.content.add(vInicio);
        vPrincipal.content.revalidate();
        vPrincipal.content.repaint();
        vPrincipal.setVisible(true);
    }

    private void addListener() {
        vPrincipal.ItemInicio.addActionListener(this);
        vPrincipal.itemMonitores.addActionListener(this);
        vPrincipal.itemSocios.addActionListener(this);
        vPrincipal.abrirAltaSocios.addActionListener(this);
        vPrincipal.abrirBajaSocios.addActionListener(this);
        vPrincipal.itemActividades.addActionListener(this);
        vPrincipal.ItemSalir.addActionListener(this);
        vMonitor.nuevoMonitor.addActionListener(this);
        vMonitor.bajaMonitor.addActionListener(this);
        vMonitor.actualizacionMonitor.addActionListener(this);
        vSocio.nuevoSocio.addActionListener(this);
        vSocio.bajaSocio.addActionListener(this);
        vSocio.actualizacionSocio.addActionListener(this);
        vSocio.filtrarBoton.addActionListener(this);
        vActividad.nuevaActividad.addActionListener(this);
        vActividad.bajaActividad.addActionListener(this);
        vActividad.actualizacionActividad.addActionListener(this);
        vCRUDMonitor.insertarMonitor.addActionListener(this);
        vCRUDMonitor.cancelarMonitor.addActionListener(this);
        vCRUDSocio.insertarSocio.addActionListener(this);
        vCRUDSocio.cancelarSocio.addActionListener(this);
        vCRUDActividad.insertarActividad.addActionListener(this);
        vCRUDActividad.cancelarActividad.addActionListener(this);
        vAltaSocio.altaSocio.addActionListener(this);
        vAltaSocio.noaltaSocio.addActionListener(this);
        vAltaSocio.socioCampo.addActionListener(this);
        vBajaSocio.BajaSocio.addActionListener(this);
        vBajaSocio.noBajaSocio.addActionListener(this);
        vBajaSocio.socioCampo.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Volver":
                muestraPanel(vInicio);
                break;
            case actionMonitor:
                muestraPanel(vMonitor);
                GestionTablasMonitor.dibujarTablaMonitores(vMonitor);
                session = sessionFactory.openSession();
                tr = session.beginTransaction();
                try {
                    cMonitor = new ControladorMonitor(sessionFactory);
                    ArrayList<Monitor> lMonitores = cMonitor.pideMonitores();
                    GestionTablasMonitor.vaciarTablaMonitores();
                    GestionTablasMonitor.rellenarTablaMonitores(lMonitores);
                    tr.commit();
                } catch (Exception ex) {
                    tr.rollback();
                    vMensajes.mensaje("Error en la petición de Monitores");
                } finally {
                    if (session != null && session.isOpen()) {
                        session.close();
                    }
                }
                break;
            case actionSocio:
                muestraPanel(vSocio);
                GestionTablasSocio.dibujarTablaSocios(vSocio);

                session = sessionFactory.openSession();
                tr = session.beginTransaction();
                try {
                    cSocio = new ControladorSocio(sessionFactory);
                    ArrayList<Socio> lSocios = cSocio.pideSocios();
                    GestionTablasSocio.vaciarTablaSocios();
                    GestionTablasSocio.rellenarTablaSocios(lSocios);
                    tr.commit();
                } catch (Exception ex) {
                    tr.rollback();
                    vMensajes.mensaje("Error en la petición de Socios");
                } finally {
                    if (session != null && session.isOpen()) {
                        session.close();
                    }
                }
                break;
            case actionActividad:
                muestraPanel(vActividad);
                GestionTablasActividad.dibujarTablaActividades(vActividad);
                session = sessionFactory.openSession();
                tr = session.beginTransaction();
                try {
                    cActividad = new ControladorActividad(sessionFactory);
                    ArrayList<Actividad> lActividades = cActividad.pideActividades();
                    GestionTablasActividad.vaciarTablaActividades();
                    GestionTablasActividad.rellenarTablaActividades(lActividades);
                    tr.commit();
                } catch (Exception ex) {
                    tr.rollback();
                    vMensajes.mensaje("Error en la petición de Socios");
                } finally {
                    if (session != null && session.isOpen()) {
                        session.close();
                    }
                }
                break;
            case "abrirAltaSocios":
                session = sessionFactory.openSession();
                cargarSociosAltaBaja(vAltaSocio.socioCampo, session);
                muestraDialog(vAltaSocio);
                break;
            case "abrirBajaSocios":
                session = sessionFactory.openSession();
                cargarSociosAltaBaja(vBajaSocio.socioCampo, session);
                muestraDialog(vBajaSocio);
                break;
            case "nuevoMonitor": //boton desde la vista monitor para acceder a nuevos monitores

                vCRUDMonitor.codigoCampo.setText(codigoSiguienteMonitor());
                vCRUDMonitor.codigoCampo.setEditable(false);
                muestraDialog(vCRUDMonitor);
                try {
                    vCRUDMonitor.codigoCampo.setText("");
                    vCRUDMonitor.nombreCampo.setText("");
                    vCRUDMonitor.dniCampo.setText("");
                    vCRUDMonitor.correoCampo.setText("");
                    vCRUDMonitor.telefonoCampo.setText("");
                    vCRUDMonitor.fechaCampo.setDate(null);
                    cMonitor = new ControladorMonitor(sessionFactory);
                    ArrayList<Monitor> lMonitores = cMonitor.pideMonitores();
                    GestionTablasMonitor.vaciarTablaMonitores();
                    GestionTablasMonitor.rellenarTablaMonitores(lMonitores);
                } catch (Exception ex) {
                }

                break;

            case "bajaMonitor": //boton desde la vista monitor para acceder a baja monitores
                int fila = vMonitor.tablaMonitores.getSelectedRow();
                if (fila != -1) {
                    String id = (String) vMonitor.tablaMonitores.getValueAt(fila, 0);
                    cMonitor = new ControladorMonitor(sessionFactory);
                    cMonitor.bajaMonitor(vMonitor, id);
                    try {
                        ArrayList<Monitor> lMonitores = cMonitor.pideMonitores();
                        GestionTablasMonitor.vaciarTablaMonitores();
                        GestionTablasMonitor.rellenarTablaMonitores(lMonitores);
                    } catch (Exception ex) {
                    } finally {
                        if (session != null && session.isOpen()) {
                            session.close();
                        }
                    }

                } else {
                    vMensajes.mensaje("No se ha seleccionado un Monitor al que eliminar");
                }
                break;
            case "actualizacionMonitor": //boton desde la vista monitor para acceder a actualizar monitores
                int f = vMonitor.tablaMonitores.getSelectedRow();
                if (f != -1) {
                    session = sessionFactory.openSession();
                    tr = session.beginTransaction();
                    String id = (String) vMonitor.tablaMonitores.getValueAt(f, 0);
                    Monitor mon = session.get(Monitor.class, id);
                    vCRUDMonitor.codigoCampo.setText(mon.getCodMonitor());
                    vCRUDMonitor.codigoCampo.setEditable(false);
                    vCRUDMonitor.nombreCampo.setText(mon.getNombre());
                    vCRUDMonitor.dniCampo.setText(mon.getDni());
                    vCRUDMonitor.correoCampo.setText(mon.getCorreo());
                    vCRUDMonitor.telefonoCampo.setText(mon.getTelefono());
                    vCRUDMonitor.nickCampo.setText(mon.getNick());
                    try {
                        String fechaString = mon.getFechaEntrada();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaDate = dateFormat.parse(fechaString);
                        vCRUDMonitor.fechaCampo.setDate(fechaDate);

                    } catch (Exception ex) {
                    }
                    muestraDialog(vCRUDMonitor);
                    try {
                        vCRUDMonitor.codigoCampo.setText("");
                        vCRUDMonitor.nombreCampo.setText("");
                        vCRUDMonitor.dniCampo.setText("");
                        vCRUDMonitor.correoCampo.setText("");
                        vCRUDMonitor.telefonoCampo.setText("");
                        vCRUDMonitor.fechaCampo.setDate(null);
                        cMonitor = new ControladorMonitor(sessionFactory);
                        ArrayList<Monitor> lMonitores = cMonitor.pideMonitores();
                        GestionTablasMonitor.vaciarTablaMonitores();
                        GestionTablasMonitor.rellenarTablaMonitores(lMonitores);
                    } catch (Exception ex) {
                    } finally {
                        if (session != null && session.isOpen()) {
                            session.close();
                        }
                    }

                } else {
                    vMensajes.mensaje("No se ha seleccionado un Monitor al que actualizar");
                }
                break;
            case "InsertarMonitor": //boton desde la vista CRUD monitor para confirmar el dialogo
                String codigo = vCRUDMonitor.codigoCampo.getText().trim();
                String nombre = vCRUDMonitor.nombreCampo.getText().trim();
                String dni = vCRUDMonitor.dniCampo.getText().trim();
                String telefono = vCRUDMonitor.telefonoCampo.getText().trim();
                String correo = vCRUDMonitor.correoCampo.getText().trim();
                String nick = vCRUDMonitor.nickCampo.getText().trim();
                Date fecha = vCRUDMonitor.fechaCampo.getDate();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String fechaEntrada = dateFormat.format(fecha);

                if (codigo.isEmpty() || nombre.isEmpty() || dni.isEmpty() || telefono.isEmpty() || correo.isEmpty() || fecha == null || nick.isEmpty()) {
                    JOptionPane.showMessageDialog(vCRUDMonitor,
                            "Todos los campos son obligatorios. Por favor, rellena todos los campos.",
                            "Error de validación",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!dni.matches("\\d{8}[A-Z]")) {
                    JOptionPane.showMessageDialog(vCRUDMonitor,
                            "El DNI debe tener 8 dígitos seguidos de una letra mayúscula. Ejemplo: 12345678A",
                            "Error de validación",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!correo.matches(".+@.+\\..+")) {
                    JOptionPane.showMessageDialog(vCRUDMonitor,
                            "El correo electrónico no tiene un formato válido. Ejemplo: usuario@dominio.com",
                            "Error de validación",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!telefono.matches("\\d{9}")) {
                    JOptionPane.showMessageDialog(vCRUDMonitor,
                            "El teléfono debe tener exactamente 9 dígitos.",
                            "Error de validación",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Date hoy = new Date();
                if (!fecha.before(hoy)) {
                    JOptionPane.showMessageDialog(vCRUDMonitor,
                            "La fecha de entrada debe ser anterior a la fecha actual.",
                            "Error de validación",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Monitor nuevoMonitor = new Monitor(codigo, nombre, dni, telefono, correo, fechaEntrada, nick);
                cMonitor = new ControladorMonitor(sessionFactory);
                cMonitor.insertarMonitor(vCRUDMonitor, nuevoMonitor);
                break;
            case "CancelarMonitor": //boton desde la vista CRUD monitor para cancelar el dialogo
                vCRUDMonitor.dispose();
                break;
            case "nuevoSocio":
                vCRUDSocio.codCampo.setText(codigoSiguienteSocio());
                vCRUDSocio.codCampo.setEditable(false);
                muestraDialog(vCRUDSocio);

                try {
                    vCRUDSocio.codCampo.setText("");
                    vCRUDSocio.nombreCampo.setText("");
                    vCRUDSocio.dniCampo.setText("");
                    vCRUDSocio.correoCampo.setText("");
                    vCRUDSocio.telefonoCampo.setText("");
                    vCRUDSocio.fechaNacCampo.setDate(null);
                    vCRUDSocio.fechaEntradaCampo.setDate(null);
                    cSocio = new ControladorSocio(sessionFactory);
                    ArrayList<Socio> lSocios = cSocio.pideSocios();
                    GestionTablasSocio.vaciarTablaSocios();
                    GestionTablasSocio.rellenarTablaSocios(lSocios);
                } catch (Exception ex) {
                }
                break;
            case "InsertarSocio":

                codigo = vCRUDSocio.codCampo.getText();
                nombre = vCRUDSocio.nombreCampo.getText();
                dni = vCRUDSocio.dniCampo.getText();
                correo = vCRUDSocio.correoCampo.getText();
                telefono = vCRUDSocio.telefonoCampo.getText();
                Date fechaN = vCRUDSocio.fechaNacCampo.getDate();
                dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String fechaNac = dateFormat.format(fechaN);
                fecha = vCRUDSocio.fechaEntradaCampo.getDate();
                fechaEntrada = dateFormat.format(fecha);
                String seleccionado = (String) vCRUDSocio.categoriaCampo.getSelectedItem();
                Character c = seleccionado.charAt(0);

                // Validaciones previas
                if (codigo.isEmpty() || nombre.isEmpty() || dni.isEmpty() || correo.isEmpty() || telefono.isEmpty() || fechaN == null || fechaEntrada == null || seleccionado.isEmpty()) {
                    JOptionPane.showMessageDialog(vCRUDSocio,
                            "Todos los campos son obligatorios. Por favor, rellena todos los campos.",
                            "Error de validación",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!dni.matches("\\d{8}[A-Z]")) {
                    JOptionPane.showMessageDialog(vCRUDSocio,
                            "El DNI debe tener 8 dígitos seguidos de una letra mayúscula. Ejemplo: 12345678A",
                            "Error de validación",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!correo.matches(".+@.+\\..+")) {
                    JOptionPane.showMessageDialog(vCRUDSocio,
                            "El correo electrónico no tiene un formato válido. Ejemplo: usuario@dominio.com",
                            "Error de validación",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!telefono.matches("\\d{9}")) {
                    JOptionPane.showMessageDialog(vCRUDSocio,
                            "El teléfono debe tener exactamente 9 dígitos.",
                            "Error de validación",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                hoy = new Date();
                if (!fecha.before(hoy)) {
                    JOptionPane.showMessageDialog(vCRUDSocio,
                            "La fecha de entrada debe ser anterior a la fecha actual.",
                            "Error de validación",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validación de edad mayor a 18 años
                Calendar cal = Calendar.getInstance();
                cal.setTime(hoy);
                cal.add(Calendar.YEAR, -18);
                Date fechaMinimaNacimiento = cal.getTime();

                if (fechaN.after(fechaMinimaNacimiento)) {
                    JOptionPane.showMessageDialog(vCRUDSocio,
                            "El socio debe ser mayor de 18 años.",
                            "Error de validación",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Socio nuevoSoc = new Socio(codigo, nombre, dni, fechaNac, telefono, correo, fechaEntrada, c);
                cSocio = new ControladorSocio(sessionFactory);
                cSocio.insertarSocio(vCRUDSocio, nuevoSoc);
                break;
            case "CancelarSocio":
                vCRUDSocio.dispose();
                try {
                    vCRUDSocio.codCampo.setText("");
                    vCRUDSocio.nombreCampo.setText("");
                    vCRUDSocio.dniCampo.setText("");
                    vCRUDSocio.correoCampo.setText("");
                    vCRUDSocio.telefonoCampo.setText("");
                    vCRUDSocio.fechaNacCampo.setDate(null);
                    vCRUDSocio.fechaEntradaCampo.setDate(null);
                    cSocio = new ControladorSocio(sessionFactory);
                    ArrayList<Socio> lSocios = cSocio.pideSocios();
                    GestionTablasSocio.vaciarTablaSocios();
                    GestionTablasSocio.rellenarTablaSocios(lSocios);
                } catch (Exception ex) {
                }
                break;
            case "bajaSocio":
                fila = vSocio.tablaSocio.getSelectedRow();
                if (fila != -1) {
                    String id = (String) vSocio.tablaSocio.getValueAt(fila, 0);
                    cSocio = new ControladorSocio(sessionFactory);
                    cSocio.bajaSocio(vSocio, id);
                    try {
                        ArrayList<Socio> lSocios = cSocio.pideSocios();
                        GestionTablasSocio.vaciarTablaSocios();
                        GestionTablasSocio.rellenarTablaSocios(lSocios);
                    } catch (Exception ex) {
                    }

                } else {
                    vMensajes.mensaje("No se ha seleccionado un Socio al que eliminar");
                }
                break;
            case "actualizacionSocio":
                f = vSocio.tablaSocio.getSelectedRow();
                if (f != -1) {
                    session = sessionFactory.openSession();
                    tr = session.beginTransaction();
                    String id = (String) vSocio.tablaSocio.getValueAt(f, 0);
                    Socio soc = session.get(Socio.class, id);
                    vCRUDSocio.codCampo.setText(soc.getNumeroSocio());
                    vCRUDSocio.codCampo.setEditable(false);
                    vCRUDSocio.nombreCampo.setText(soc.getNombre());
                    vCRUDSocio.dniCampo.setText(soc.getDni());
                    vCRUDSocio.correoCampo.setText(soc.getCorreo());
                    vCRUDSocio.telefonoCampo.setText(soc.getTelefono());

                    try {
                        String fechaString = soc.getFechaNacimiento();
                        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaDate = dateFormat.parse(fechaString);
                        vCRUDSocio.fechaNacCampo.setDate(fechaDate);
                        fechaString = soc.getFechaEntrada();
                        fechaDate = dateFormat.parse(fechaString);
                        vCRUDSocio.fechaEntradaCampo.setDate(fechaDate);

                    } catch (Exception ex) {
                    }
                    muestraDialog(vCRUDSocio);
                    try {
                        cSocio = new ControladorSocio(sessionFactory);
                        ArrayList<Socio> lSocios = cSocio.pideSocios();
                        GestionTablasSocio.vaciarTablaSocios();
                        GestionTablasSocio.rellenarTablaSocios(lSocios);
                    } catch (Exception ex) {
                    } finally {
                        if (session != null && session.isOpen()) {
                            session.close();
                        }
                    }
                } else {
                    vMensajes.mensaje("No se ha seleccionado un Socio al que actualizar");
                }
                break;
            case "filtrarBoton":
                String texto = vSocio.filtrarCampo.getText().trim();
                String atributo = (String) vSocio.filtrarBox.getSelectedItem();
                try {
                    ArrayList<Socio> lSocios = cSocio.pideSociosFiltrado(texto, atributo);
                    GestionTablasSocio.vaciarTablaSocios();
                    GestionTablasSocio.rellenarTablaSocios(lSocios);
                } catch (Exception ex) {
                }
                break;
            case "nuevaActividad":
                cActividad = new ControladorActividad(sessionFactory);
                session = sessionFactory.openSession();
                vCRUDActividad.codCampo.setText(codigoSiguienteActividad());
                vCRUDActividad.codCampo.setEditable(false);
                cargarMonitoresResponsables(vCRUDActividad.monitorCampo, session);
                muestraDialog(vCRUDActividad);
                try {
                    vCRUDActividad.codCampo.setText("");
                    vCRUDActividad.nombreCampo.setText("");
                    vCRUDActividad.descripcionCampo.setText("");
                    vCRUDActividad.precioCampo.setText("");
                    vCRUDActividad.diaCampo.setSelectedIndex(0);
                    vCRUDActividad.horaCampo.setSelectedIndex(0);
                    ArrayList<Actividad> lActividades = cActividad.pideActividades();
                    GestionTablasActividad.vaciarTablaActividades();
                    GestionTablasActividad.rellenarTablaActividades(lActividades);
                } catch (Exception ex) {
                } finally {
                    if (session != null && session.isOpen()) {
                        session.close();
                    }
                }
                break;
            case "InsertarActividad":

                codigo = vCRUDActividad.codCampo.getText();
                nombre = vCRUDActividad.nombreCampo.getText();
                String descripcion = vCRUDActividad.descripcionCampo.getText();
                String precio = vCRUDActividad.precioCampo.getText();
                int precioBase = Integer.parseInt(precio);
                String dia = (String) vCRUDActividad.diaCampo.getSelectedItem();
                String h = (String) vCRUDActividad.horaCampo.getSelectedItem();
                int hora = Integer.parseInt(h);
                String monitor = (String) vCRUDActividad.monitorCampo.getSelectedItem();

                if (codigo.isEmpty() || nombre.isEmpty() || descripcion.isEmpty() || precio.isEmpty() || dia.isEmpty() || h.isEmpty() || monitor.isEmpty()) {
                    JOptionPane.showMessageDialog(vCRUDActividad,
                            "Todos los campos son obligatorios. Por favor, rellena todos los campos.",
                            "Error de validación",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    if (precioBase <= 0) {
                        JOptionPane.showMessageDialog(vCRUDActividad,
                                "El precio debe ser un valor positivo.",
                                "Error de validación",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(vCRUDActividad,
                            "El precio debe ser un número válido.",
                            "Error de validación",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                session = sessionFactory.openSession();
                tr = session.beginTransaction();
                try {
                    Monitor mon = session.createQuery("FROM Monitor WHERE nombre = :nombre", Monitor.class)
                            .setParameter("nombre", monitor)
                            .uniqueResult();

                    /* if (mon == null) {
                        JOptionPane.showMessageDialog(vCRUDActividad,
                                "El monitor seleccionado no existe.",
                                "Error de validación",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }*/
                    for (Actividad a : mon.getActividades()) {
                        if (a.getDia().contains(dia) && a.getHora() == hora) {
                            JOptionPane.showMessageDialog(vCRUDActividad,
                                    "El monitor seleccionado ya tiene una actividad asignada el mismo día y a la misma hora.",
                                    "Error de validación",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    session.close();

                    Actividad act = new Actividad(codigo, nombre, dia, hora, descripcion, precioBase, mon);
                    cActividad = new ControladorActividad(sessionFactory);
                    cActividad.insertarActividad(vCRUDActividad, act);
                    vCRUDActividad.dispose();
                } catch (Exception ex) {
                    tr.rollback();
                    vMensajes.mensaje("Error al confirmar tu transacción");
                } finally {
                    if (session != null && session.isOpen()) {
                        session.close();
                    }
                }

                break;
            case "CancelarActividad":
                vCRUDActividad.dispose();
                try {
                    vCRUDActividad.codCampo.setText("");
                    vCRUDActividad.nombreCampo.setText("");
                    vCRUDActividad.descripcionCampo.setText("");
                    vCRUDActividad.precioCampo.setText("");
                    vCRUDActividad.diaCampo.setSelectedIndex(0);
                    vCRUDActividad.horaCampo.setSelectedIndex(0);
                    cActividad = new ControladorActividad(sessionFactory);
                    ArrayList<Actividad> lActividades = cActividad.pideActividades();
                    GestionTablasActividad.vaciarTablaActividades();
                    GestionTablasActividad.rellenarTablaActividades(lActividades);
                } catch (Exception ex) {
                }
                break;
            case "bajaActividad":
                fila = vActividad.tablaActividad.getSelectedRow();
                if (fila != -1) {
                    String id = (String) vActividad.tablaActividad.getValueAt(fila, 0);
                    cActividad = new ControladorActividad(sessionFactory);
                    cActividad.bajaActividad(vActividad, id);
                    try {
                        ArrayList<Actividad> lActividades = cActividad.pideActividades();
                        GestionTablasActividad.vaciarTablaActividades();
                        GestionTablasActividad.rellenarTablaActividades(lActividades);
                    } catch (Exception ex) {
                    } finally {
                        if (session != null && session.isOpen()) {
                            session.close();
                        }
                    }

                } else {
                    vMensajes.mensaje("No se ha seleccionado un Socio al que eliminar");
                }
                break;
            case "actualizacionActividad":
                f = vActividad.tablaActividad.getSelectedRow();
                if (f != -1) {
                    session = sessionFactory.openSession();
                    tr = session.beginTransaction();
                    String id = (String) vActividad.tablaActividad.getValueAt(f, 0);
                    Actividad act = session.get(Actividad.class, id);
                    vCRUDActividad.codCampo.setText(act.getIdActividad());
                    vCRUDActividad.nombreCampo.setText(act.getNombre());
                    vCRUDActividad.descripcionCampo.setText(act.getDescripcion());
                    vCRUDActividad.precioCampo.setText(String.valueOf(act.getPrecioBaseMes()));
                    vCRUDActividad.diaCampo.setSelectedItem(act.getDia());
                    h = String.valueOf(act.getHora());
                    vCRUDActividad.horaCampo.setSelectedItem(h);

                    cargarMonitoresResponsables(vCRUDActividad.monitorCampo, session);
                    vCRUDActividad.monitorCampo.setSelectedItem(act.getMonitorResponsable().getNombre());

                    muestraDialog(vCRUDActividad);
                    try {
                        cActividad = new ControladorActividad(sessionFactory);
                        ArrayList<Actividad> lActividades = cActividad.pideActividades();
                        GestionTablasActividad.vaciarTablaActividades();
                        GestionTablasActividad.rellenarTablaActividades(lActividades);
                    } catch (Exception ex) {
                    } finally {
                        if (session != null && session.isOpen()) {
                            session.close();
                        }
                    }
                } else {
                    vMensajes.mensaje("No se ha seleccionado ninguna actividad a la que actualizar");
                }
                break;
            case "altaSocio":
                String nombreSoc = (String) vAltaSocio.socioCampo.getSelectedItem();
                String nombreAct = (String) vAltaSocio.actividadCampo.getSelectedItem();
                if (nombreSoc == null || nombreSoc.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(vAltaSocio,
                            "Por favor, selecciona un socio válido.",
                            "Error de validación",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (nombreAct == null || nombreAct.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(vAltaSocio,
                            "Por favor, selecciona una actividad válida.",
                            "Error de validación",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                cSocio = new ControladorSocio(sessionFactory);
                cSocio.altaActividadSocio(vAltaSocio, nombreSoc, nombreAct);
                break;
            case "AltaSocioChanged":
                cargarActividadesNoInscritas();
                break;
            case "noaltaSocio":
                vAltaSocio.dispose();
                break;
            case "BajaSocio":
                nombreSoc = (String) vBajaSocio.socioCampo.getSelectedItem();
                nombreAct = (String) vBajaSocio.actividadCampo.getSelectedItem();
                if (nombreSoc == null || nombreSoc.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(vBajaSocio,
                                "Por favor, selecciona un socio válido.",
                                "Error de validación",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (nombreAct == null || nombreAct.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(vBajaSocio,
                                "Por favor, selecciona una actividad válida.",
                                "Error de validación",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    cSocio = new ControladorSocio(sessionFactory);
                    cSocio.bajaActividadSocio(vBajaSocio, nombreSoc, nombreAct);
                break;
            case "BajaSocioChanged":
                cargarActividadesInscritas();
                break;
            case "noBajaSocio":
                vBajaSocio.dispose();
                break;
            case "Cerrar":
                System.exit(0);
                break;
        }
    }

    private void muestraPanel(JPanel panel) {
        panel.setSize(1000, 500);
        panel.setLocation(0, 0);

        vPrincipal.content.removeAll();
        vPrincipal.content.add(panel);
        vPrincipal.setLocationRelativeTo(null);
        vPrincipal.content.revalidate();
        vPrincipal.content.repaint();
    }

    private void muestraDialog(JDialog d) {
        d.setLocationRelativeTo(null);
        d.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        d.setVisible(true);
    }

    private String codigoSiguienteMonitor() {
        String nuevoCodigo = "";

        int filas = vMonitor.tablaMonitores.getRowCount();
        if (filas > 0) {
            String ultimoCodigo = "";
            for (int i = 0; i < filas; i++) {
                String codigoActual = vMonitor.tablaMonitores.getValueAt(i, 0).toString();
                if (codigoActual.compareTo(ultimoCodigo) > 0) {
                    ultimoCodigo = codigoActual;
                }
            }

            int numero = Integer.parseInt(ultimoCodigo.substring(1));
            nuevoCodigo = String.format("M%03d", numero + 1);
        }
        return nuevoCodigo;
    }

    private String codigoSiguienteSocio() {
        String nuevoCodigo = "";

        int filas = vSocio.tablaSocio.getRowCount();
        if (filas > 0) {
            String ultimoCodigo = "";
            for (int i = 0; i < filas; i++) {
                String codigoActual = vSocio.tablaSocio.getValueAt(i, 0).toString();
                if (codigoActual.compareTo(ultimoCodigo) > 0) {
                    ultimoCodigo = codigoActual;
                }
            }

            int numero = Integer.parseInt(ultimoCodigo.substring(1));
            nuevoCodigo = String.format("S%03d", numero + 1);
        }
        return nuevoCodigo;
    }

    private String codigoSiguienteActividad() {
        String nuevoCodigo = "";

        int filas = vActividad.tablaActividad.getRowCount();
        if (filas > 0) {
            String ultimoCodigo = "";
            for (int i = 0; i < filas; i++) {
                String codigoActual = vActividad.tablaActividad.getValueAt(i, 0).toString();
                if (codigoActual.compareTo(ultimoCodigo) > 0) {
                    ultimoCodigo = codigoActual;
                }
            }

            int numero = Integer.parseInt(ultimoCodigo.substring(2));
            nuevoCodigo = String.format("AC%d", numero + 1);
        }
        return nuevoCodigo;
    }

    public void cargarMonitoresResponsables(JComboBox<String> comboBox, Session session) {
        List<Monitor> monitores = session.createNamedQuery("Monitor.findAll", Monitor.class).list();
        comboBox.removeAllItems();
        for (Monitor monitor : monitores) {
            comboBox.addItem(monitor.getNombre());
        }
    }

    public void cargarSociosAltaBaja(JComboBox<String> comboBox, Session session) {
        List<Socio> socios = session.createNamedQuery("Socio.findAll", Socio.class).list();
        comboBox.removeAllItems();
        for (Socio s : socios) {
            comboBox.addItem(s.getNombre());
        }
    }

    private void cargarActividadesNoInscritas() {
        String socioNombre = (String) vAltaSocio.socioCampo.getSelectedItem();

        if (socioNombre != null) {
            try (Session session = sessionFactory.openSession()) {
                Socio socio = session.createQuery("FROM Socio WHERE nombre = :nombre", Socio.class)
                        .setParameter("nombre", socioNombre)
                        .uniqueResult();

                if (socio != null) {
                    List<Actividad> todasActividades = session.createQuery("FROM Actividad", Actividad.class).list();
                    Set<Actividad> actividadesInscritas = socio.getActividades();
                    todasActividades.removeAll(actividadesInscritas);
                    vAltaSocio.actividadCampo.removeAllItems();
                    for (Actividad actividad : todasActividades) {
                        vAltaSocio.actividadCampo.addItem(actividad.getNombre());
                    }
                }
            }
        }
    }

    private void cargarActividadesInscritas() {
        String socioNombre = (String) vBajaSocio.socioCampo.getSelectedItem();

        if (socioNombre != null) {
            try (Session session = sessionFactory.openSession()) {
                Socio socio = session.createQuery("FROM Socio WHERE nombre = :nombre", Socio.class)
                        .setParameter("nombre", socioNombre)
                        .uniqueResult();

                if (socio != null) {
                    Set<Actividad> actividadesInscritas = socio.getActividades();
                    vBajaSocio.actividadCampo.removeAllItems();
                    for (Actividad actividad : actividadesInscritas) {
                        vBajaSocio.actividadCampo.addItem(actividad.getNombre());
                    }
                }
            }
        }
    }

    public boolean verificarDisponibilidadMonitor(Session session, String monitorId, String dia, String hora) {
        String hql = "SELECT COUNT(a) FROM Actividad a WHERE a.monitorResponsable.id = :monitorId AND a.dia = :dia AND a.hora = :hora";
        Long count = session.createQuery(hql, Long.class)
                .setParameter("monitorId", monitorId)
                .setParameter("dia", dia)
                .setParameter("hora", hora)
                .uniqueResult();
        return count == 0;
    }
}
