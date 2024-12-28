/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import config.HibernateUtil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.hibernate.SessionFactory;
import vista.VistaConexion;
import vista.VistaMensajes;


public class ControladorConexion implements ActionListener {

    public VistaConexion vConexion;
    public VistaMensajes vMensajes;
    public static String user;
    public static String pass;
    private SessionFactory sessionFactory;

    public ControladorConexion() {
        vConexion = new VistaConexion();
        vMensajes = new VistaMensajes();

        addListeners();

        vConexion.setLocationRelativeTo(null);
        vConexion.setVisible(true);
        
    }

    private void addListeners() {
        vConexion.BotonEntrarAplicacion.addActionListener(this);
        vConexion.BotonCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "EntrarAplicacion":
                user = vConexion.CampoUsuario.getText();
                pass = new String(vConexion.CampoPassword.getPassword());
                sessionFactory = HibernateUtil.buildSessionFactory();
                if (sessionFactory == null) {
                    vMensajes.mensaje("Error al introducir las credenciales");
                } else {
                    vMensajes.mensaje("Conexión correcta con Hibernate" + "\nVa a acceder a la aplicación");
                    vConexion.dispose();
                    ControladorPrincipal controladorP = new ControladorPrincipal(sessionFactory);
                }
                break;
            case "CancelarAplicacion":
                vMensajes.mensaje("Salida correcta de la aplicación");
                vConexion.dispose();
                System.exit(0);
                break;
        }
    }

}
