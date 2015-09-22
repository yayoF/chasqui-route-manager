/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.route.manager.gui;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.UIManager;

/**
 *
 * @author Carlos H. Montenegro
 */
public class mainWindow extends javax.swing.JFrame {

    /**
     * Creates new form mainWindow
     */
    public mainWindow() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public mainWindow(String title){
        initComponents();
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mItemNewOrder = new javax.swing.JMenuItem();
        mItemCancelOrder = new javax.swing.JMenuItem();
        mItemTrucks = new javax.swing.JMenu();
        mItemCargaDatos = new javax.swing.JMenuItem();
        mItemUser = new javax.swing.JMenuItem();
        mItemClente = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        mItemProduct = new javax.swing.JMenuItem();
        menuConfig = new javax.swing.JMenu();
        mItemConfig = new javax.swing.JMenuItem();
        mItemEditProfiles = new javax.swing.JMenuItem();
        mItemEditUsers = new javax.swing.JMenuItem();
        mItemMap = new javax.swing.JMenu();
        mItemRealTime = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        mItemIncomeReport = new javax.swing.JMenuItem();
        mItemPurchaseReport = new javax.swing.JMenuItem();
        mItemGasPriceReport = new javax.swing.JMenuItem();
        mItemRemisionOrders = new javax.swing.JMenuItem();
        mItemReportHistory = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Pedidos");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        mItemNewOrder.setText("Nuevo");
        mItemNewOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemNewOrderActionPerformed(evt);
            }
        });
        jMenu1.add(mItemNewOrder);

        mItemCancelOrder.setText("Cancelar Pedido");
        mItemCancelOrder.setToolTipText("");
        mItemCancelOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemCancelOrderActionPerformed(evt);
            }
        });
        jMenu1.add(mItemCancelOrder);

        jMenuBar1.add(jMenu1);

        mItemTrucks.setText("Nuevo");
        mItemTrucks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemTrucksActionPerformed(evt);
            }
        });

        mItemCargaDatos.setText("Carga de Datos");
        mItemCargaDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemCargaDatosActionPerformed(evt);
            }
        });
        mItemTrucks.add(mItemCargaDatos);

        mItemUser.setText("Usuario");
        mItemUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemUserActionPerformed(evt);
            }
        });
        mItemTrucks.add(mItemUser);

        mItemClente.setText("Cliente");
        mItemClente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemClenteActionPerformed(evt);
            }
        });
        mItemTrucks.add(mItemClente);

        jMenuItem1.setText("Cami�n");
        jMenuItem1.setToolTipText("");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        mItemTrucks.add(jMenuItem1);

        mItemProduct.setText("Producto");
        mItemProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemProductActionPerformed(evt);
            }
        });
        mItemTrucks.add(mItemProduct);

        jMenuBar1.add(mItemTrucks);

        menuConfig.setText("Configuraci�n");
        menuConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuConfigActionPerformed(evt);
            }
        });

        mItemConfig.setText("Par�metros Generales");
        mItemConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemConfigActionPerformed(evt);
            }
        });
        menuConfig.add(mItemConfig);

        mItemEditProfiles.setText("Editar Perfiles");
        mItemEditProfiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemEditProfilesActionPerformed(evt);
            }
        });
        menuConfig.add(mItemEditProfiles);

        mItemEditUsers.setText("Editar Usuarios/Contrase�as");
        mItemEditUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemEditUsersActionPerformed(evt);
            }
        });
        menuConfig.add(mItemEditUsers);

        jMenuBar1.add(menuConfig);

        mItemMap.setText("Vista de Mapa");

        mItemRealTime.setText("En Tiempo Real");
        mItemRealTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemRealTimeActionPerformed(evt);
            }
        });
        mItemMap.add(mItemRealTime);

        jMenuBar1.add(mItemMap);

        jMenu4.setText("Documentos");

        mItemIncomeReport.setText("Reporte de Ingresos");
        mItemIncomeReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemIncomeReportActionPerformed(evt);
            }
        });
        jMenu4.add(mItemIncomeReport);

        mItemPurchaseReport.setText("Reporte de Compras por Cliente");
        mItemPurchaseReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemPurchaseReportActionPerformed(evt);
            }
        });
        jMenu4.add(mItemPurchaseReport);

        mItemGasPriceReport.setText("Reporte de Precios del Gas");
        mItemGasPriceReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemGasPriceReportActionPerformed(evt);
            }
        });
        jMenu4.add(mItemGasPriceReport);

        mItemRemisionOrders.setText("�rdenes de Remisi�n");
        jMenu4.add(mItemRemisionOrders);

        mItemReportHistory.setText("Historial de Reportes");
        mItemReportHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemReportHistoryActionPerformed(evt);
            }
        });
        jMenu4.add(mItemReportHistory);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1187, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 824, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuConfigActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_menuConfigActionPerformed

    private void mItemConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemConfigActionPerformed
        // TODO add your handling code here:
        JInternalFrame config = new configWindow();
        
        config.setLocation((this.getSize().width - config.getSize().width)/2, (this.getSize().height - config.getSize().height)/2);
        this.add(config);
        config.setVisible(true);
    }//GEN-LAST:event_mItemConfigActionPerformed

    private void mItemRealTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemRealTimeActionPerformed
        // TODO add your handling code here:
        JInternalFrame mapView = new realTimeMapWindow();
        mapView.setLocation((this.getSize().width - mapView.getSize().width)/2, (this.getSize().height - mapView.getSize().height)/2);
        this.add(mapView);
        mapView.setVisible(true);
    }//GEN-LAST:event_mItemRealTimeActionPerformed

    private void mItemEditProfilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemEditProfilesActionPerformed
        // TODO add your handling code here:
        JInternalFrame editProfiles = new profilesWindow();
        editProfiles.setLocation((this.getSize().width - editProfiles.getSize().width)/2, (this.getSize().height - editProfiles.getSize().height)/2);
        this.add(editProfiles);
        editProfiles.setVisible(true);      
    }//GEN-LAST:event_mItemEditProfilesActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        JInternalFrame truckView = new truckWindow();
        truckView.setLocation((this.getSize().width - truckView.getSize().width)/2, (this.getSize().height - truckView.getSize().height)/2);
        this.add(truckView);
        truckView.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void mItemClenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemClenteActionPerformed
        // TODO add your handling code here:
        JInternalFrame clientView = new clientWindow("Clientes");
        clientView.setLocation((this.getSize().width - clientView.getSize().width)/2, (this.getSize().height - clientView.getSize().height)/2);
        this.add(clientView);
        clientView.setVisible(true);
    }//GEN-LAST:event_mItemClenteActionPerformed

    private void mItemCargaDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemCargaDatosActionPerformed
        // TODO add your handling code here:
        JInternalFrame batchLoad = new loadWindow("Nueva Carga de Datos");

        batchLoad.setLocation((this.getSize().width - batchLoad.getSize().width)/2,(this.getSize().height- batchLoad.getSize().height)/2);
        this.add(batchLoad);
        batchLoad.setVisible(true);
    }//GEN-LAST:event_mItemCargaDatosActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void mItemCancelOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemCancelOrderActionPerformed
        // TODO add your handling code here:
        JInternalFrame cancelOrder = new cancelOrderWindow();
        cancelOrder.setLocation((this.getSize().width - cancelOrder.getSize().width)/2, (this.getSize().height - cancelOrder.getSize().height)/2);
        this.add(cancelOrder);
        cancelOrder.setVisible(true);
    }//GEN-LAST:event_mItemCancelOrderActionPerformed

    private void mItemNewOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemNewOrderActionPerformed
        // TODO add your handling code here:
        JInternalFrame newOrder = new orderWindow();
        newOrder.setLocation((this.getSize().width - newOrder.getSize().width)/2, (this.getSize().height - newOrder.getSize().height)/2);
        this.add(newOrder);
        newOrder.setVisible(true);
    }//GEN-LAST:event_mItemNewOrderActionPerformed

    private void mItemEditUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemEditUsersActionPerformed
        // TODO add your handling code here:
        JInternalFrame editUsers = new editUsersPassWindow();
        editUsers.setLocation((this.getSize().width - editUsers.getSize().width)/2, (this.getSize().height - editUsers.getSize().height)/2);
        this.add(editUsers);
        editUsers.setVisible(true);
    }//GEN-LAST:event_mItemEditUsersActionPerformed

    private void mItemPurchaseReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemPurchaseReportActionPerformed
        // TODO add your handling code here:
        JInternalFrame purchaseReport = new clientReportWindow();
        purchaseReport.setLocation((this.getSize().width - purchaseReport.getSize().width)/2, (this.getSize().height - purchaseReport.getSize().height)/2);
        this.add(purchaseReport);
        purchaseReport.setVisible(true);
    }//GEN-LAST:event_mItemPurchaseReportActionPerformed

    private void mItemIncomeReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemIncomeReportActionPerformed
        // TODO add your handling code here:
        JInternalFrame incomeReport = new incomeReportWindow();
        incomeReport.setLocation((this.getSize().width - incomeReport.getSize().width)/2, (this.getSize().height - incomeReport.getSize().height)/2);
        this.add(incomeReport);
        incomeReport.setVisible(true);
    }//GEN-LAST:event_mItemIncomeReportActionPerformed

    private void mItemGasPriceReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemGasPriceReportActionPerformed
        // TODO add your handling code here:
        JInternalFrame gasPriceReport = new gasPriceReportWindow();
        gasPriceReport.setLocation((this.getSize().width - gasPriceReport.getSize().width)/2, (this.getSize().height - gasPriceReport.getSize().height)/2);
        this.add(gasPriceReport);
        gasPriceReport.setVisible(true);
    }//GEN-LAST:event_mItemGasPriceReportActionPerformed

    private void mItemReportHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemReportHistoryActionPerformed
        // TODO add your handling code here:
        JInternalFrame reportHistory = new reportHistoryWindow();
        reportHistory.setLocation((this.getSize().width - reportHistory.getSize().width)/2, (this.getSize().height - reportHistory.getSize().height)/2);
        this.add(reportHistory);
        reportHistory.setVisible(true);
    }//GEN-LAST:event_mItemReportHistoryActionPerformed

    private void mItemProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemProductActionPerformed
        // TODO add your handling code here:
        JInternalFrame product = new productWindow();
        product.setLocation((this.getSize().width - product.getSize().width)/2, (this.getSize().height - product.getSize().height)/2);
        this.add(product);
        product.setVisible(true);
    }//GEN-LAST:event_mItemProductActionPerformed

    private void mItemTrucksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemTrucksActionPerformed
        // TODO add your handling code here:
        JInternalFrame truck = new truckWindow();
        truck.setLocation((this.getSize().width - truck.getSize().width)/2, (this.getSize().height - truck.getSize().height)/2);
        this.add(truck);
        truck.setVisible(true);
    }//GEN-LAST:event_mItemTrucksActionPerformed

    private void mItemUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemUserActionPerformed
        // TODO add your handling code here:
        JInternalFrame user = new userWindow();
        user.setLocation((this.getSize().width - user.getSize().width)/2, (this.getSize().height - user.getSize().height)/2);
        this.add(user);
        user.setVisible(true);
    }//GEN-LAST:event_mItemUserActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            /*
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }*/
             UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem mItemCancelOrder;
    private javax.swing.JMenuItem mItemCargaDatos;
    private javax.swing.JMenuItem mItemClente;
    private javax.swing.JMenuItem mItemConfig;
    private javax.swing.JMenuItem mItemEditProfiles;
    private javax.swing.JMenuItem mItemEditUsers;
    private javax.swing.JMenuItem mItemGasPriceReport;
    private javax.swing.JMenuItem mItemIncomeReport;
    private javax.swing.JMenu mItemMap;
    private javax.swing.JMenuItem mItemNewOrder;
    private javax.swing.JMenuItem mItemProduct;
    private javax.swing.JMenuItem mItemPurchaseReport;
    private javax.swing.JMenuItem mItemRealTime;
    private javax.swing.JMenuItem mItemRemisionOrders;
    private javax.swing.JMenuItem mItemReportHistory;
    private javax.swing.JMenu mItemTrucks;
    private javax.swing.JMenuItem mItemUser;
    private javax.swing.JMenu menuConfig;
    // End of variables declaration//GEN-END:variables
}
