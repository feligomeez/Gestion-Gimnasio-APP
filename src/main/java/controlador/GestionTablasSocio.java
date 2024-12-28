/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelo.Socio;
import vista.VistaSocio;

public class GestionTablasSocio {

    private static DefaultTableModel modeloTablaSocios;

    public GestionTablasSocio(VistaSocio vSocio) {
        inicializarTablaSocios(vSocio);
    }

    public void inicializarTablaSocios(VistaSocio vSocio) {
        modeloTablaSocios = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int colum) {
                return false;
            }
        };
        vSocio.tablaSocio.setModel(modeloTablaSocios);
    }

    public static void dibujarTablaSocios(VistaSocio vSocio) {
        String[] columnasTabla = {"Socio", "Nombre", "DNI",
            "Fecha de Nacimiento", "Teléfono", "Correo", "Fecha de Alta", "Cat."};
        modeloTablaSocios.setColumnIdentifiers(columnasTabla);        

        // Para no permitir el redimensionamiento de las columnas con el ratón
        vSocio.tablaSocio.getTableHeader().setResizingAllowed(false);
        vSocio.tablaSocio.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        // Así se fija el ancho de las columnas
        vSocio.tablaSocio.getColumnModel().getColumn(0).setPreferredWidth(40);
        vSocio.tablaSocio.getColumnModel().getColumn(1).setPreferredWidth(200);
        vSocio.tablaSocio.getColumnModel().getColumn(2).setPreferredWidth(100);
        vSocio.tablaSocio.getColumnModel().getColumn(3).setPreferredWidth(140);
        vSocio.tablaSocio.getColumnModel().getColumn(4).setPreferredWidth(100);
        vSocio.tablaSocio.getColumnModel().getColumn(5).setPreferredWidth(280);
        vSocio.tablaSocio.getColumnModel().getColumn(6).setPreferredWidth(100);
        vSocio.tablaSocio.getColumnModel().getColumn(7).setPreferredWidth(40);

    }


    public static void rellenarTablaSocios(ArrayList<Socio> socios) {
        Object[] fila = new Object[8];
        for (Socio socio : socios) {
            fila[0] = socio.getNumeroSocio();
            fila[1] = socio.getNombre();
            fila[2] = socio.getDni();
            fila[3] = socio.getFechaNacimiento();
            fila[4] = socio.getTelefono();
            fila[5] = socio.getCorreo();
            fila[6] = socio.getFechaEntrada();
            fila[7] = socio.getCategoria();
            modeloTablaSocios.addRow(fila);
        }
    }

    public static void vaciarTablaSocios() {
        while (modeloTablaSocios.getRowCount() > 0) {
            modeloTablaSocios.removeRow(0);
        }
    }
}
