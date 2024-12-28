/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.Component;
import javax.swing.JOptionPane;


public class VistaMensajes {

    public void mensaje(String texto) {
        JOptionPane.showMessageDialog(null, texto, null, JOptionPane.INFORMATION_MESSAGE);
    }

    public void Mensaje(Component C, String tipoMensaje, String texto) {
        switch (tipoMensaje) {
            case "info":
                JOptionPane.showMessageDialog(C, texto, null,
                        JOptionPane.INFORMATION_MESSAGE);
                break;
            case "error":
                JOptionPane.showMessageDialog(C, texto, null,
                        JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
