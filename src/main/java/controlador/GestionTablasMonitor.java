/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Monitor;
import vista.VistaMonitor;


public class GestionTablasMonitor {

    private static DefaultTableModel modeloTablaMonitores;
    
    public GestionTablasMonitor(VistaMonitor vMonitor){
        inicializarTablaMonitores(vMonitor);
    }

    public void inicializarTablaMonitores(VistaMonitor vMonitor) {
        modeloTablaMonitores = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int colum) {
                return false;
            }
        };
        vMonitor.tablaMonitores.setModel(modeloTablaMonitores);
    }

    public static void dibujarTablaMonitores(VistaMonitor vMonitor) {
        String[] columnasTabla = {"Código", "Nombre", "DNI",
            "Teléfono", "Correo", "Fecha Incorporación", "Nick"};
        modeloTablaMonitores.setColumnIdentifiers(columnasTabla);

        // Para no permitir el redimensionamiento de las columnas con el ratón
        vMonitor.tablaMonitores.getTableHeader().setResizingAllowed(false);
        vMonitor.tablaMonitores.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        // Así se fija el ancho de las columnas
        vMonitor.tablaMonitores.getColumnModel().getColumn(0).setPreferredWidth(80);
        vMonitor.tablaMonitores.getColumnModel().getColumn(1).setPreferredWidth(240);
        vMonitor.tablaMonitores.getColumnModel().getColumn(2).setPreferredWidth(100);
        vMonitor.tablaMonitores.getColumnModel().getColumn(3).setPreferredWidth(100);
        vMonitor.tablaMonitores.getColumnModel().getColumn(4).setPreferredWidth(220);
        vMonitor.tablaMonitores.getColumnModel().getColumn(5).setPreferredWidth(150);
        vMonitor.tablaMonitores.getColumnModel().getColumn(6).setPreferredWidth(60);
    }

    public static void rellenarTablaMonitores(ArrayList<Monitor> monitores) {
        Object[] fila = new Object[7];
        for (Monitor monitor : monitores) {
            fila[0] = monitor.getCodMonitor();
            fila[1] = monitor.getNombre();
            fila[2] = monitor.getDni();
            fila[3] = monitor.getTelefono();
            fila[4] = monitor.getCorreo();
            fila[5] = monitor.getFechaEntrada();
            fila[6] = monitor.getNick();
            modeloTablaMonitores.addRow(fila);
        }
    }

    public static void vaciarTablaMonitores() {
        while (modeloTablaMonitores.getRowCount() > 0) {
            modeloTablaMonitores.removeRow(0);
        }
    }
}
