package StoreProyect;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

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

    public int askForInt(String message, String value) {
        int result;
        do {
            try {
                result = Integer.parseInt(askForInput(message, value));
                if (result < 0) {
                    JOptionPane.showMessageDialog(null, "Please, insert a positive value.");
                    result = -1;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please, insert a correct value.");
                result = -1;
            }
        } while (result < 0);
        return result;
    }

    public double askForDouble(String message, String value) {
        double result;
        do {
            try {
                result = Double.parseDouble(askForInput(message, value));
                if (result < 0) {
                    JOptionPane.showMessageDialog(null, "Please, insert a positive value.");
                    result = -1;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please, insert a correct value.");
                result = -1;
            }
        } while (result < 0);
        return result;
    }

    public String askForName(String message, String value) {
        String result;
        Boolean validName=true;
        do {
            result = askForInput(message, value);
            if (result.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please, insert a correct value.");
            } else{
                validName = inventory.validName(result);
                if(validName){
                    JOptionPane.showMessageDialog(null, "It already exist a product with that name. Please, insert another name.");
                }
            }
        } while (result.trim().isEmpty() || validName);
        return result;
    }

    public String askForInput(String message, String value) {
        boolean flag = false;
        String nameProduct = new String();
        do {
            try {
                nameProduct = JOptionPane.showInputDialog(message, value);
            } catch (Exception e) {
                showMessageDialog(null, "Invalid input. Please, insert a correct value.");
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
        String newName = askForName(name, "");
        double newPrice = askForDouble(price, "");
        int newStock = askForInt(stock, "");
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
            return null;
        }
        return result;
    }

    public void updateTable(Product product, int selectedRow, int a) {
        switch (a) {
            case 0:
                String newName = askForName("Insert the new name of the Product: ", product.getName());
                Double newPrice = askForDouble("Insert the new price of the product: ", String.valueOf(product.getPrice()));
                int newStock = askForInt("Insert the new price of the product: ", String.valueOf(product.getStock()));
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInventory = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnEditProduct = new javax.swing.JButton();
        btnAddProduct = new javax.swing.JButton();
        removeProduct = new javax.swing.JButton();
        lblInventoryManagment = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(229, 229, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblInventory.setBackground(new java.awt.Color(240, 240, 240));
        tblInventory.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblInventory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Price", "Stock"
            }
        ));
        jScrollPane1.setViewportView(tblInventory);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 320, 270));

        txtSearch.setBackground(new java.awt.Color(240, 240, 240));
        txtSearch.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 314, -1));

        btnSearch.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 110, -1));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        btnEditProduct.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnEditProduct.setText("Edit Product");
        btnEditProduct.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEditProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditProductActionPerformed(evt);
            }
        });

        btnAddProduct.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnAddProduct.setText("Add Product");
        btnAddProduct.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProductActionPerformed(evt);
            }
        });

        removeProduct.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        removeProduct.setText("Remove Product");
        removeProduct.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        removeProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeProductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnAddProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(btnAddProduct)
                .addGap(64, 64, 64)
                .addComponent(btnEditProduct)
                .addGap(64, 64, 64)
                .addComponent(removeProduct)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 150, 310));

        lblInventoryManagment.setFont(new java.awt.Font("Lucida Handwriting", 1, 24)); // NOI18N
        lblInventoryManagment.setText("Inventory Managment");
        jPanel1.add(lblInventoryManagment, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 29, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductActionPerformed
        inventory.addProduct(getNewProductInfo());
        initObjects();
    }//GEN-LAST:event_btnAddProductActionPerformed

    private void btnEditProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditProductActionPerformed
        var results = getValueTable(evt);
        if (results != null) {
            Product product = (Product) results.get(0);
            int selectedRow = (int) results.get(1);
            updateTable(product, selectedRow, 0);
        }
    }//GEN-LAST:event_btnEditProductActionPerformed

    private void removeProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeProductActionPerformed
        var results = getValueTable(evt);
        if (results != null) {
            Product product = (Product) results.get(0);
            int selectedRow = (int) results.get(1);
            updateTable(product, selectedRow, 1);
        }
    }//GEN-LAST:event_removeProductActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String searchText = txtSearch.getText().toLowerCase();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        tblInventory.setRowSorter(sorter);
        var rowFilter = RowFilter.regexFilter("(?i)" + searchText);
        sorter.setRowFilter(rowFilter);
    }//GEN-LAST:event_btnSearchActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddProduct;
    private javax.swing.JButton btnEditProduct;
    private javax.swing.JButton btnSearch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblInventoryManagment;
    private javax.swing.JButton removeProduct;
    private javax.swing.JTable tblInventory;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
