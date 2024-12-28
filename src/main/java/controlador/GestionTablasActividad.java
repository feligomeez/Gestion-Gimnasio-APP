/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Actividad;
import vista.VistaActividad;


public class GestionTablasActividad {
    private static DefaultTableModel modeloTablaActividades;
     
    public GestionTablasActividad(VistaActividad vActividad){
        inicializarTablaActividad(vActividad);
    } 
    
    public void inicializarTablaActividad(VistaActividad vActividad) {
        modeloTablaActividades = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int colum) {
                return false;
            }
        };
        vActividad.tablaActividad.setModel(modeloTablaActividades);
    }

    public static void dibujarTablaActividades(VistaActividad vActividad) {
        String[] columnasTabla = {"Código", "Nombre", "Día",
            "Hora", "Descripción", "Precio", "Monitor Responsable"};
        modeloTablaActividades.setColumnIdentifiers(columnasTabla);

        // Para no permitir el redimensionamiento de las columnas con el ratón
        vActividad.tablaActividad.getTableHeader().setResizingAllowed(false);
        vActividad.tablaActividad.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        // Así se fija el ancho de las columnas
        vActividad.tablaActividad.getColumnModel().getColumn(0).setPreferredWidth(40);
        vActividad.tablaActividad.getColumnModel().getColumn(1).setPreferredWidth(80);
        vActividad.tablaActividad.getColumnModel().getColumn(2).setPreferredWidth(70);
        vActividad.tablaActividad.getColumnModel().getColumn(3).setPreferredWidth(30);
        vActividad.tablaActividad.getColumnModel().getColumn(4).setPreferredWidth(280);
        vActividad.tablaActividad.getColumnModel().getColumn(5).setPreferredWidth(40);
        vActividad.tablaActividad.getColumnModel().getColumn(6).setPreferredWidth(170);
    
    }

    public static void rellenarTablaActividades(ArrayList<Actividad> actividades) {
        Object[] fila = new Object[7];
        for (Actividad actividad : actividades) {
            fila[0] = actividad.getIdActividad();
            fila[1] = actividad.getNombre();
            fila[2] = actividad.getDia();
            fila[3] = actividad.getHora();
            fila[4] = actividad.getDescripcion();
            fila[5] = actividad.getPrecioBaseMes();
            fila[6] = actividad.getMonitorResponsable();
            modeloTablaActividades.addRow(fila);
        }
    }

    public static void vaciarTablaActividades() {
        while (modeloTablaActividades.getRowCount() > 0) {
            modeloTablaActividades.removeRow(0);            
        }
    }
}
