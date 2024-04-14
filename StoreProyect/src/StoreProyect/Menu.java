/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package StoreProyect;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.List;

public class Menu extends javax.swing.JFrame {
    private Inventory inventory;
    private ArrayList<Product> lstProducts;
    DefaultTableModel tableModel;
    String[] COLUMNS = {"Name", "Price", "Stock"};

    public Menu() {
        inventory = new Inventory();
        initComponents();
        lstProducts = inventory.getLstProducts();
        initObjects();
    }

     public String askUserInfo(String message, String value) {
        boolean flag=false;
        String nameProduct = new String();
        do {
            try {
                nameProduct = JOptionPane.showInputDialog(message, value);
            } catch (Exception e) {
                showMessageDialog(null, "Please, insert correct values");
                flag = true;
            }
        } while (flag);
        return nameProduct;
    }

    public Product getNewProductInfo() {
        Product product = new Product();
        String name = "Insert the name of the new Product :";
        String price = "Insert the price of the new product : ";
        String stock = "Insert the stock of the new product : ";
        String newName = askUserInfo(name, "");
        double newPrice = Double.parseDouble(askUserInfo(price, ""));
        int newStock = Integer.parseInt(askUserInfo(stock, ""));
        inventory.createProduct(product, newName, newPrice, newStock);
        return product;
    }
    
    public List<Object> getValueTable(ActionEvent e) {
        int selectedRow = tblInventory.getSelectedRow();
        List<Object> result = new ArrayList<>();
        if (selectedRow != -1) {
            // Get the values from the selected row
            String name = (String) tableModel.getValueAt(selectedRow, 0);
            double price = Double.parseDouble((String) tableModel.getValueAt(selectedRow, 1));
            int stock = Integer.parseInt((String) tableModel.getValueAt(selectedRow, 2));
            // Get the selected product
            Product selectedProduct = inventory.getProduct(name, price, stock);
            result.add(selectedProduct);
            result.add(selectedRow);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to edit.");
        }
        return result;
    }

    public void updateTable(Product product, int selectedRow, int a) {
        switch(a){
            case 0:
            String newName = askUserInfo("Insert the new name of the Product: ", product.getName());
            Double newPrice = Double.valueOf(askUserInfo("Insert the new price of the product: ", String.valueOf(product.getPrice())));
            int newStock = Integer.parseInt(askUserInfo("Insert the new price of the product: ", String.valueOf(product.getStock()))); 
            // Update the product
            inventory.updateProduct(product, newName, newPrice, newStock);
            // Update the model with the new values
            tableModel.setValueAt(newName, selectedRow, 0);
            tableModel.setValueAt(newPrice, selectedRow, 1);
            tableModel.setValueAt(newStock, selectedRow, 2);
            break;
            case 1:
            inventory.removeProduct(product);
            initObjects();
            break;
        }
    }

    private void initObjects() {
        String[][] data = new String[lstProducts.size()][3];
        for (int i = 0; i < lstProducts.size(); i++) {
            data[i][0] = lstProducts.get(i).getName();
            data[i][1] = String.valueOf(lstProducts.get(i).getPrice());
            data[i][2] = String.valueOf(lstProducts.get(i).getStock());
            tableModel = new DefaultTableModel(data, COLUMNS) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return true;
                }
            };
            tblInventory.setModel(tableModel);
            tblInventory.setAutoCreateRowSorter(true);
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblInventory = new javax.swing.JTable();
        btnAddProduct = new javax.swing.JButton();
        btnRemoveProduct = new javax.swing.JButton();
        lbInventory = new javax.swing.JLabel();
        btnEditProduct = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblInventory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "Price ", "Stock"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblInventory);
        if (tblInventory.getColumnModel().getColumnCount() > 0) {
            tblInventory.getColumnModel().getColumn(0).setResizable(false);
            tblInventory.getColumnModel().getColumn(1).setResizable(false);
            tblInventory.getColumnModel().getColumn(2).setResizable(false);
        }

        btnAddProduct.setText("Add Product");
        btnAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProductActionPerformed(evt);
            }
        });

        btnRemoveProduct.setText("Remove Product");
        btnRemoveProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveProductActionPerformed(evt);
            }
        });

        lbInventory.setFont(new java.awt.Font("Segoe UI Emoji", 3, 12)); // NOI18N
        lbInventory.setText("Inventory");

        btnEditProduct.setText("Edit Product");
        btnEditProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditProductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(lbInventory, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnAddProduct)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditProduct)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(btnRemoveProduct)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbInventory)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRemoveProduct)
                    .addComponent(btnAddProduct)
                    .addComponent(btnEditProduct))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRemoveProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveProductActionPerformed
        var results = getValueTable(evt);
        Product product = (Product) results.get(0);
        int selectedRow = (int) results.get(1);
        updateTable(product,selectedRow,1);
    }//GEN-LAST:event_btnRemoveProductActionPerformed

    private void btnAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductActionPerformed
        inventory.addProduct(getNewProductInfo());
        initObjects();
    }//GEN-LAST:event_btnAddProductActionPerformed

    private void btnEditProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditProductActionPerformed
        var results = getValueTable(evt);
        Product product = (Product) results.get(0);
        int selectedRow = (int) results.get(1);
        updateTable(product,selectedRow,0);
    }//GEN-LAST:event_btnEditProductActionPerformed

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddProduct;
    private javax.swing.JButton btnEditProduct;
    private javax.swing.JButton btnRemoveProduct;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbInventory;
    private javax.swing.JTable tblInventory;
    // End of variables declaration//GEN-END:variables
}
