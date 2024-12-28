/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;


public class VistaSocio extends javax.swing.JPanel {

    /**
     * Creates new form VistaInicio
     */
    public VistaSocio() {
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
        tablaSocio = new javax.swing.JTable();
        bajaSocio = new javax.swing.JButton();
        actualizacionSocio = new javax.swing.JButton();
        nuevoSocio = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        filtrarCampo = new javax.swing.JTextField();
        filtrarBoton = new javax.swing.JButton();
        filtrarBox = new javax.swing.JComboBox<>();

        setPreferredSize(new java.awt.Dimension(800, 500));

        labelInicio.setFont(new java.awt.Font("Gill Sans", 0, 36)); // NOI18N
        labelInicio.setForeground(new java.awt.Color(51, 102, 255));
        labelInicio.setText("Gestión de Socios");

        tablaSocio.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaSocio);

        bajaSocio.setText("Baja de Socio");
        bajaSocio.setActionCommand("bajaSocio");
        bajaSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bajaSocioActionPerformed(evt);
            }
        });

        actualizacionSocio.setText("Actualización de Socio");
        actualizacionSocio.setActionCommand("actualizacionSocio");

        nuevoSocio.setText("Nuevo Socio");
        nuevoSocio.setActionCommand("nuevoSocio");
        nuevoSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoSocioActionPerformed(evt);
            }
        });

        jLabel1.setText("Buscar:");

        filtrarCampo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtrarCampoActionPerformed(evt);
            }
        });

        filtrarBoton.setText("Filtrar");
        filtrarBoton.setActionCommand("filtrarBoton");
        filtrarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtrarBotonActionPerformed(evt);
            }
        });

        filtrarBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Dni", "Telefono", "Correo", "Categoria" }));
        filtrarBox.setSelectedIndex(-1);
        filtrarBox.setActionCommand("filtrarBoxChanged");
        filtrarBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtrarBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nuevoSocio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bajaSocio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(actualizacionSocio))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 874, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelInicio)
                        .addGap(132, 132, 132)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(filtrarBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filtrarCampo, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filtrarBoton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelInicio)
                    .addComponent(jLabel1)
                    .addComponent(filtrarCampo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filtrarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filtrarBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nuevoSocio)
                    .addComponent(bajaSocio)
                    .addComponent(actualizacionSocio))
                .addContainerGap(8, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoSocioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nuevoSocioActionPerformed

    private void bajaSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bajaSocioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bajaSocioActionPerformed

    private void filtrarCampoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtrarCampoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filtrarCampoActionPerformed

    private void filtrarBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtrarBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filtrarBoxActionPerformed

    private void filtrarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtrarBotonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filtrarBotonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton actualizacionSocio;
    public javax.swing.JButton bajaSocio;
    public javax.swing.JButton filtrarBoton;
    public javax.swing.JComboBox<String> filtrarBox;
    public javax.swing.JTextField filtrarCampo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelInicio;
    public javax.swing.JButton nuevoSocio;
    public javax.swing.JTable tablaSocio;
    // End of variables declaration//GEN-END:variables
}
