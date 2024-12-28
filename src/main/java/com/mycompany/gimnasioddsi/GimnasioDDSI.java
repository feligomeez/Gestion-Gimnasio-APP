/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.gimnasioddsi;

import com.formdev.flatlaf.FlatDarculaLaf;
import controlador.ControladorConexion;
import javax.swing.UIManager;

public class GimnasioDDSI {

    public static void main(String[] args) {
        
        ControladorConexion controller = new ControladorConexion();
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception ex) {
            System.err.println("Mensaje de error");
        }
    }

}
