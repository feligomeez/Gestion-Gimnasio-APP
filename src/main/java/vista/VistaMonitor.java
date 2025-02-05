/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;


public class VistaMonitor extends javax.swing.JPanel {

    /**
     * Creates new form VistaInicio
     */
    public VistaMonitor() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelInicio = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMonitores = new javax.swing.JTable();
        nuevoMonitor = new javax.swing.JButton();
        bajaMonitor = new javax.swing.JButton();
        actualizacionMonitor = new javax.swing.JButton();

        labelInicio.setFont(new java.awt.Font("Gill Sans", 0, 36)); // NOI18N
        labelInicio.setForeground(new java.awt.Color(51, 102, 255));
        labelInicio.setText("Gestión de Monitores");

        tablaMonitores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaMonitores);

        nuevoMonitor.setText("Nuevo Monitor");
        nuevoMonitor.setActionCommand("nuevoMonitor");
        nuevoMonitor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoMonitorActionPerformed(evt);
            }
        });

        bajaMonitor.setText("Baja de Monitor");
        bajaMonitor.setActionCommand("bajaMonitor");

        actualizacionMonitor.setText("Actualización de Monitor");
        actualizacionMonitor.setActionCommand("actualizacionMonitor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 874, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelInicio)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nuevoMonitor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bajaMonitor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(actualizacionMonitor)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(labelInicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nuevoMonitor)
                    .addComponent(bajaMonitor)
                    .addComponent(actualizacionMonitor))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoMonitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoMonitorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nuevoMonitorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton actualizacionMonitor;
    public javax.swing.JButton bajaMonitor;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelInicio;
    public javax.swing.JButton nuevoMonitor;
    public javax.swing.JTable tablaMonitores;
    // End of variables declaration//GEN-END:variables
}
