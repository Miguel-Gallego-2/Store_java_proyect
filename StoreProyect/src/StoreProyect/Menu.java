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

    public String askUserInfo(String message, String value) {
        boolean flag = false;
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
            return null;
        }
        return result;
    }

    public void updateTable(Product product, int selectedRow, int a) {
        switch (a) {
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInventory = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnAddProduct = new javax.swing.JButton();
        btnEditProduct = new javax.swing.JButton();
        removeProduct = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblInventory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Price", "Stock"
            }
        ));
        jScrollPane1.setViewportView(tblInventory);

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnAddProduct.setText("Add Product");
        btnAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProductActionPerformed(evt);
            }
        });

        btnEditProduct.setText("Edit Product");
        btnEditProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditProductActionPerformed(evt);
            }
        });

        removeProduct.setText("Remove Product");
        removeProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeProductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtSearch)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addComponent(btnSearch)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAddProduct)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addComponent(btnEditProduct)
                .addGap(73, 73, 73)
                .addComponent(removeProduct)
                .addGap(85, 85, 85))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddProduct)
                    .addComponent(btnEditProduct)
                    .addComponent(removeProduct))
                .addGap(36, 36, 36))
        );

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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton removeProduct;
    private javax.swing.JTable tblInventory;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
