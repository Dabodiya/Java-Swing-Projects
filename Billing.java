/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package shoeshopmng;

import java.awt.Font;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;

/**
 *
 * @author hdoff
 */
public class Billing extends javax.swing.JFrame {

    /**
     * Creates new form Billing
     */
    Double q;
    long cartid;
    String auid;
    public Billing() {
        initComponents();
       
    }
    public Billing(String auid,int flag) {
        initComponents();
        try {
            DefaultTableModel model= (DefaultTableModel) shoetbl.getModel();
            Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/shoeshopdb","root","");
            PreparedStatement pt=c.prepareStatement("select id,brand,category,price,quantity,discount from shoes");
            ResultSet rs=pt.executeQuery();
            while(rs.next())
            {
                String id = rs.getString("id");
                String brand = rs.getString("brand");
                String type = rs.getString("category");

                // Numeric fields as double
                double price = rs.getDouble("price");
                double quantity = rs.getDouble("quantity");
                double discount = rs.getDouble("discount");

                // Format to 2 decimals
                String priceStr = String.format("%.2f", price);
                String quantityStr = String.format("%.2f", quantity);
                String discountstr = String.format("%.2f", discount);
                model.addRow(new String[]{id, brand, type, quantityStr, priceStr,discountstr+"%"});
            }
        } 
        catch (SQLException ex) {
            //Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        { 
        
        DefaultTableModel model= (DefaultTableModel) BillTbl.getModel();
            Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/shoeshopdb","root","");
            PreparedStatement pt=c.prepareStatement("select * from cart");
            ResultSet rs=pt.executeQuery();
            while(rs.next())
            {
                model.addRow(new String[] {rs.getString(1),rs.getString(2),
                rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
                rs.getString(7)+"%"});
            }
        }
        catch(SQLException ex)
        {
            
        }
        sidtb.setEditable(false);
        sbrandtb.setEditable(false);
        stypetb.setEditable(false);
        spricetb.setEditable(false);
        amttb.setEditable(false);
        Discounttb.setEditable(false);
        Afdtb.setEditable(false);
        billnotb.setEditable(false);
        //generating BillNo
        BillNoGenerator();
        
        this.auid= auid;
        if(flag == 0)
        {
        try {
            Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/shoeshopdb","root","");
            PreparedStatement pt=c.prepareStatement("select name from admin where id = ?");
            pt.setString(1, auid);
            ResultSet rs=pt.executeQuery();
            if (rs.next()) 
            {
                // assuming your column name is 'username' or similar
                String name = rs.getString(1);  
                aulbl.setText("Admin "+name);  
            }
            else 
            {
                // ⚠️ No record found — handle gracefully
                aulbl.setText("Admin");
            }
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Error : "+ e.getMessage());
        }
        }
        else 
        {
            try {
            Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/shoeshopdb","root","");
            PreparedStatement pt=c.prepareStatement("select name from users where id = ?");
            pt.setString(1, auid);
            ResultSet rs=pt.executeQuery();
            if (rs.next()) 
            {
                // assuming your column name is 'username' or similar
                String name = rs.getString(1);  
                aulbl.setText("User "+name);  
            }
            else 
            {
                // ⚠️ No record found — handle gracefully
                aulbl.setText("User");
            }
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Error : "+ e.getMessage());
        }
        }
       SearchFilter();

    }

    void SearchFilter()
    {
        DefaultTableModel model = (DefaultTableModel) shoetbl.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        shoetbl.setRowSorter(sorter);

        searchtb.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
        public void insertUpdate(javax.swing.event.DocumentEvent e) { search(); }
        public void removeUpdate(javax.swing.event.DocumentEvent e) { search(); }
        public void changedUpdate(javax.swing.event.DocumentEvent e) { search(); }

        private void search() {
        String text = searchtb.getText();
        if (text.trim().length() == 0) {
            sorter.setRowFilter(null); // show all rows if search box is empty
        } else {
            // filter rows based on any column containing the text
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
        }
        });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cnametb = new javax.swing.JTextField();
        ccontacttb = new javax.swing.JTextField();
        qtytb = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        aulbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        BillingArea = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        exitbtn = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        atbbtn = new javax.swing.JButton();
        resetbtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        BillTbl = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        shoetbl = new javax.swing.JTable();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        sbrandtb = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        sidtb = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        stypetb = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        spricetb = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        amttb = new javax.swing.JTextField();
        cnamelbl = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        Discountlbl = new javax.swing.JLabel();
        Discounttb = new javax.swing.JTextField();
        Afdlbl = new javax.swing.JLabel();
        Afdtb = new javax.swing.JTextField();
        billnotb = new javax.swing.JTextField();
        cdlbl = new javax.swing.JLabel();
        cdtb = new javax.swing.JTextField();
        searchtb = new javax.swing.JTextField();

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 51, 255));
        jButton5.setText("BACK");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 51, 255));
        jButton6.setText("Exit");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255), 5));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 35)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 255));
        jLabel8.setText("Billing");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(867, 867, 867)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        cnametb.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        cnametb.setForeground(new java.awt.Color(51, 51, 255));

        ccontacttb.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        ccontacttb.setForeground(new java.awt.Color(51, 51, 255));

        qtytb.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        qtytb.setForeground(new java.awt.Color(51, 51, 255));
        qtytb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qtytbActionPerformed(evt);
            }
        });
        qtytb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                qtytbKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                qtytbKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 255));
        jLabel14.setText("Quantity");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/shoeshopmng/usersmall.png"))); // NOI18N

        aulbl.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        aulbl.setForeground(new java.awt.Color(51, 51, 255));
        aulbl.setText("User");

        BillingArea.setEditable(false);
        BillingArea.setColumns(20);
        BillingArea.setRows(5);
        jScrollPane1.setViewportView(BillingArea);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 255));
        jLabel18.setText("Bill Number    - ");

        exitbtn.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        exitbtn.setForeground(new java.awt.Color(0, 51, 255));
        exitbtn.setText("Exit");
        exitbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitbtnMouseClicked(evt);
            }
        });
        exitbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitbtnActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton9.setForeground(new java.awt.Color(0, 51, 255));
        jButton9.setText("Print");
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
        });
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        atbbtn.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        atbbtn.setForeground(new java.awt.Color(0, 51, 255));
        atbbtn.setText("Add to Cart");
        atbbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                atbbtnMouseClicked(evt);
            }
        });

        resetbtn.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        resetbtn.setForeground(new java.awt.Color(0, 51, 255));
        resetbtn.setText("RESET");
        resetbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resetbtnMouseClicked(evt);
            }
        });
        resetbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetbtnActionPerformed(evt);
            }
        });

        BillTbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0), 2));
        BillTbl.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        BillTbl.setForeground(new java.awt.Color(51, 51, 255));
        BillTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Brand", "Type", "Quantity", "Price", "Total", "Discount %"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        BillTbl.setRowHeight(35);
        BillTbl.setRowMargin(1);
        BillTbl.setSelectionBackground(new java.awt.Color(255, 255, 0));
        BillTbl.setSelectionForeground(new java.awt.Color(51, 51, 255));
        BillTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BillTblMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(BillTbl);

        shoetbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0), 2));
        shoetbl.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        shoetbl.setForeground(new java.awt.Color(51, 51, 255));
        shoetbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Brand", "Type", "Quantity", "Price", "Discount %"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        shoetbl.setRowHeight(35);
        shoetbl.setRowMargin(1);
        shoetbl.setSelectionBackground(new java.awt.Color(255, 255, 0));
        shoetbl.setSelectionForeground(new java.awt.Color(51, 51, 255));
        shoetbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                shoetblMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(shoetbl);

        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton10.setForeground(new java.awt.Color(0, 51, 255));
        jButton10.setText("Remove Item");
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton10MouseClicked(evt);
            }
        });
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton11.setForeground(new java.awt.Color(0, 51, 255));
        jButton11.setText("Add to Bill");
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton11MouseClicked(evt);
            }
        });
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 255));
        jLabel15.setText("Brand");

        sbrandtb.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        sbrandtb.setForeground(new java.awt.Color(51, 51, 255));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 255));
        jLabel16.setText("Shoe Id");

        sidtb.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        sidtb.setForeground(new java.awt.Color(51, 51, 255));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 255));
        jLabel17.setText("Type");

        stypetb.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        stypetb.setForeground(new java.awt.Color(51, 51, 255));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 255));
        jLabel20.setText("Price");

        spricetb.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        spricetb.setForeground(new java.awt.Color(51, 51, 255));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 255));
        jLabel21.setText("Total Amount");

        amttb.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        amttb.setForeground(new java.awt.Color(51, 51, 255));

        cnamelbl.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        cnamelbl.setForeground(new java.awt.Color(51, 51, 255));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 255));
        jLabel11.setText("Client Contact -");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 255));
        jLabel12.setText("Client Name    -");

        jButton12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton12.setForeground(new java.awt.Color(0, 51, 255));
        jButton12.setText("Clear Cart");
        jButton12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton12MouseClicked(evt);
            }
        });
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        Discountlbl.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Discountlbl.setForeground(new java.awt.Color(51, 51, 255));
        Discountlbl.setText("Discount %");

        Discounttb.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Discounttb.setForeground(new java.awt.Color(51, 51, 255));

        Afdlbl.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Afdlbl.setForeground(new java.awt.Color(51, 51, 255));
        Afdlbl.setText("Amt. After Discount");

        Afdtb.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Afdtb.setForeground(new java.awt.Color(51, 51, 255));

        billnotb.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        billnotb.setForeground(new java.awt.Color(51, 51, 255));

        cdlbl.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        cdlbl.setForeground(new java.awt.Color(51, 51, 255));
        cdlbl.setText("Cash Discount ");

        cdtb.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        cdtb.setForeground(new java.awt.Color(51, 51, 255));
        cdtb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cdtbActionPerformed(evt);
            }
        });
        cdtb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cdtbKeyReleased(evt);
            }
        });

        searchtb.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        searchtb.setForeground(new java.awt.Color(51, 51, 255));
        searchtb.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        searchtb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchtbKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(aulbl)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15)
                                            .addComponent(sbrandtb, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(40, 40, 40)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel14)
                                            .addComponent(qtytb, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(43, 43, 43)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel16)
                                            .addComponent(sidtb, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel20)
                                            .addComponent(spricetb, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(39, 39, 39)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel17)
                                            .addComponent(stypetb, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(137, 137, 137)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel21)
                                            .addComponent(amttb, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(43, 43, 43)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Discountlbl)
                                            .addComponent(Discounttb, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(Afdlbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(Afdtb, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(32, 32, 32))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(searchtb, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(490, 490, 490)
                                .addComponent(exitbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(340, 340, 340)
                                .addComponent(atbbtn)
                                .addGap(91, 91, 91)
                                .addComponent(resetbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton12)
                                .addGap(100, 100, 100)
                                .addComponent(jButton11))
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(244, 244, 244)
                                .addComponent(cnamelbl))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ccontacttb, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel12)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel18)
                                                .addGap(2, 2, 2)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(billnotb)
                                            .addComponent(cnametb, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cdlbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cdtb, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(72, 72, 72)))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(291, 291, 291))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(aulbl)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(cnamelbl)
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(billnotb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(qtytb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sbrandtb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sidtb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spricetb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stypetb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(amttb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Discountlbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Discounttb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Afdlbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Afdtb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(resetbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(atbbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addComponent(searchtb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(cnametb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(ccontacttb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(cdlbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cdtb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton10)
                            .addComponent(jButton11)
                            .addComponent(jButton12))
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(exitbtn))
                .addGap(21, 21, 21))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

 int width = 66;

// Helper method to center text (Java 8 compatible)
String center(String text, int width) {
    int pad = (width - text.length()) / 2;
    if (pad < 0) pad = 0;
    return String.format("%" + (pad + text.length()) + "s", text);
}

// Helper method to repeat a character (Java 8 compatible)
String repeat(char c, int count) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < count; i++) sb.append(c);
    return sb.toString();
}

private void BillNoGenerator()
{
    String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        StringBuilder billNo = new StringBuilder();
        Random random = new Random();

        // Add 2 random alphabets
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(letters.length());
            billNo.append(letters.charAt(index));
        }

        // Add 4 random digits
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(numbers.length());
            billNo.append(numbers.charAt(index));
        }
       billnotb.setText(billNo.toString());
}

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        // TODO add your handling code here:
        new AdDash1().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        new Login1().setVisible(true);
        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jButton6MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void exitbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitbtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exitbtnActionPerformed

    private void exitbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitbtnMouseClicked
        new Login1(true).setVisible(true);
        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_exitbtnMouseClicked

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9MouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
        // ✅ Step 1: Print the bill
        boolean printed = BillingArea.print();

        if (printed) {
            JOptionPane.showMessageDialog(
                this,
                new JLabel("<html><span style='color:green; font-size:14px;'>Bill printed successfully!</span></html>")
            );
           BillingArea.setText("");
           BillNoGenerator();
           
        } else {
            JOptionPane.showMessageDialog(
                this,
                new JLabel("<html><span style='color:orange; font-size:14px;'>Print cancelled by user.</span></html>")
            );
            BillingArea.setText("");
            BillNoGenerator();
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(
            this,
            new JLabel("<html><span style='color:red; font-size:14px;'>Error while printing: " + e.getMessage() + "</span></html>"));
        BillingArea.setText("");
    }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void atbbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atbbtnMouseClicked
        // TODO add your handling code here:
        if(sbrandtb.getText().equals("") || sidtb.getText().equals("") || qtytb.getText().equals("") || spricetb.getText().equals("") || amttb.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this,"Please Enter All Details","Alert",JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            DefaultTableModel tblmodel= (DefaultTableModel) BillTbl.getModel();
            try
            {
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shoeshopdb","root","");
                     

                double qty = 0, price = 0, amt = 0, dis = 0;

                try {
                    qty = Double.parseDouble(qtytb.getText().trim());
                } catch (NumberFormatException e) {
                    qty = 0; // default if blank or invalid
                }

                try {
                    price = Double.parseDouble(spricetb.getText().trim());
                } catch (NumberFormatException e) {
                    price = 0;
                }

                try {
                    amt = Double.parseDouble(amttb.getText().trim());
                } catch (NumberFormatException e) {
                    amt = 0;
                }
                 try {
                    dis = Double.parseDouble(Discounttb.getText().trim());
                } catch (NumberFormatException e) {
                    dis = 0;
                }

                     PreparedStatement pst=con.prepareStatement("insert into cart values(?,?,?,?,?,?,?)");
                     pst.setLong(1,Long.parseLong(sidtb.getText()));
                     pst.setString(2, sbrandtb.getText());
                     pst.setString(3,stypetb.getText());
                     pst.setDouble(4, Double.parseDouble(qtytb.getText().trim()));
                     pst.setDouble(5, Double.parseDouble(spricetb.getText().trim()));
                     pst.setString(6, amttb.getText().trim());
                     pst.setDouble(7, Double.parseDouble(Discounttb.getText().trim()));
                     pst.executeUpdate();  
                     JOptionPane.showMessageDialog(this,"Added to cart");
                     
                tblmodel.addRow(new Object[]{
                    sidtb.getText(),
                    sbrandtb.getText(),
                    stypetb.getText(),
                    qty,   
                    price, 
                    amt,
                    dis+"%"
                });
                 sidtb.setText("");
                     sbrandtb.setText("");
                     stypetb.setText("");
                     spricetb.setText("");
                     qtytb.setText("");
                     amttb.setText("");
                     Discounttb.setText("");
                     Afdtb.setText("");
            }
            catch(SQLException e)
            {
                
            }
        }
    }//GEN-LAST:event_atbbtnMouseClicked

    private void resetbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetbtnMouseClicked
//        cnametb.setText("");
//        ccontacttb.setText("");
        sbrandtb.setText("");
        spricetb.setText("");
        qtytb.setText("");
        amttb.setText("");
        stypetb.setText("");
        sidtb.setText("");
        Discounttb.setText("");
        Afdtb.setText("");
    }//GEN-LAST:event_resetbtnMouseClicked

    private void resetbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetbtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_resetbtnActionPerformed

    private void BillTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BillTblMouseClicked
          DefaultTableModel tblmdl=(DefaultTableModel)shoetbl.getModel();
        int selectedrowindex=BillTbl.getSelectedRow();
        cartid=Long.parseLong(tblmdl.getValueAt(selectedrowindex,0).toString());
    }//GEN-LAST:event_BillTblMouseClicked

    private void shoetblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shoetblMouseClicked
        // TODO add your handling code here:
    DefaultTableModel tblmdl = (DefaultTableModel) shoetbl.getModel();
    int selectedrowindex = shoetbl.getSelectedRow();

    // Set text fields from selected row
    sidtb.setText(tblmdl.getValueAt(selectedrowindex, 0).toString());
    sbrandtb.setText(tblmdl.getValueAt(selectedrowindex, 1).toString());
    stypetb.setText(tblmdl.getValueAt(selectedrowindex, 2).toString());
    spricetb.setText(tblmdl.getValueAt(selectedrowindex, 4).toString());
    Discounttb.setText(tblmdl.getValueAt(selectedrowindex, 5).toString().replace("%",""));
    // 🧮 Auto-calculate amount if qty already entered
    String qtyText = qtytb.getText().trim();
    String priceText = spricetb.getText().trim();
    String discountstr = Discounttb.getText().trim();
    if (!qtyText.isEmpty() && qtyText.matches("\\d*\\.?\\d*")) { // numeric check
        try {
            double qty = Double.parseDouble(qtyText);
            double price = Double.parseDouble(priceText);
            double discount = Double.parseDouble(discountstr);
            double amount = qty * price;
            double afd = amount - (amount * discount / 100);
            amttb.setText(String.valueOf(amount));
            Afdtb.setText(String.valueOf(afd));
        } catch (NumberFormatException e) {
            amttb.setText("");
            Afdtb.setText("");// in case of invalid input
        }
    } else {
        amttb.setText("");
        Afdtb.setText("");// if no quantity, clear amount
    }
    }//GEN-LAST:event_shoetblMouseClicked

    private void jButton10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseClicked
      
    }//GEN-LAST:event_jButton10MouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:                                     
   DefaultTableModel model = (DefaultTableModel) BillTbl.getModel();

try {
    // Step 1: Check if cart has any data
    Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoeshopdb", "root", "");
    PreparedStatement check = c.prepareStatement("SELECT COUNT(*) FROM cart");
    ResultSet rs = check.executeQuery();
    rs.next();
    int rowCount = rs.getInt(1);

    if (rowCount == 0) {
        JOptionPane.showMessageDialog(
            this,
            new JLabel("<html><span style='color:orange; font-size:16px; font-weight:bold;'>Cart is already empty!</span></html>"),
            "Cart Status",
            JOptionPane.INFORMATION_MESSAGE
        );
        return;
    }

    // Step 2: Check if user selected any row
    int selectedRow = BillTbl.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(
            this,
            new JLabel("<html><span style='color:orange; font-size:14px; font-weight:bold;'>Please select at least one item from the cart!</span></html>"),
            "No Selection",
            JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    // Step 3: Get the cart ID properly (convert String → long)
    long cartid = Long.parseLong(model.getValueAt(selectedRow, 0).toString()); 

    // Step 4: Delete from DB
    PreparedStatement pst = c.prepareStatement("DELETE FROM cart WHERE id=?");
    
     int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure to remove this item from cart?",
            "Removing Item From Cart",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            // Step 2: Clear database table
            pst.setLong(1, cartid);
            pst.executeUpdate();
            // Step 5: Remove row from table
            model.removeRow(selectedRow);
            JOptionPane.showMessageDialog(
                this,
                new JLabel("<html><span style='color:red; font-size:16px; font-weight:bold;'>Item Removed from Cart Successfully!</span></html>"),
                "Cart Update",
                JOptionPane.INFORMATION_MESSAGE
            );
        }

} 
catch (Exception e) 
{
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, e.getMessage());
}
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        
        String cname;
        String contact;
        String billNo;
        String orderDate;
      DefaultTableModel model = (DefaultTableModel) BillTbl.getModel();

try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoeshopdb", "root", "")) {

    // Step 1: Check if cart is empty
    PreparedStatement checkCart = c.prepareStatement("SELECT COUNT(*) FROM cart");
    ResultSet rs = checkCart.executeQuery();
    rs.next();
    int count = rs.getInt(1);

    if (count == 0) {
        JOptionPane.showMessageDialog(
            this,
            new JLabel("<html><span style='color:orange; font-size:14px; font-weight:bold;'>Cart is empty! Nothing to bill.</span></html>"),
            "Cart Empty",
            JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    // Step 2: Validate client info
    if (cnametb.getText().trim().isEmpty() || ccontacttb.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(
            this,
            new JLabel("<html><span style='color:red; font-size:14px; font-weight:bold;'>Please Enter Client Name and Contact No!</span></html>"),
            "Missing Details",
            JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    // Step 3: Get all items from cart
    PreparedStatement getCart = c.prepareStatement(
        "SELECT c.id, s.brand, s.discount, c.quantity, s.price FROM cart c JOIN shoes s ON c.id = s.id"
    );
    ResultSet cartRS = getCart.executeQuery();

    // Step 4: Prepare bill header
    double subtotal = 0.0;
    double totaldiscount  = 0.0;
    BillingArea.setFont(new Font("Courier New", Font.PLAIN, 12));
    BillingArea.setText("");
    StringBuilder billText = new StringBuilder();

billText.append("************************* Shoe Shop Bill *************************\n");
billText.append(center("BILL RECEIPT", width)).append("\n");
billText.append(repeat('-', width)).append("\n");

// Customer & Bill Info Section
 cname = cnametb.getText().trim();
 contact = ccontacttb.getText().trim();
 billNo = billnotb.getText().trim();
 orderDate = new java.text.SimpleDateFormat("dd-MM-yyyy").format(new java.util.Date());

// Align left (name/contact) and right (bill no/date)
billText.append(String.format("%-30s %32s\n", "Customer: " + cname, "Bill No: " + billNo));
billText.append(String.format("%-30s %32s\n", "Contact: " + contact, "Order Date: " + orderDate));
billText.append(repeat('-', width)).append("\n");

// Column titles
billText.append(String.format("%-10s %-15s %-8s %-10s %-8s %-12s\n", "ID", "Brand", "Qty", "Price", "Disc%", "Amount"));
billText.append(repeat('-', width)).append("\n");

JSONArray shoesArray = new JSONArray();
while (cartRS.next()) {
    long id = cartRS.getLong("id");
    String brand = cartRS.getString("brand");
    double discount = cartRS.getDouble("discount");
    double qty = cartRS.getDouble("quantity");
    double price = cartRS.getDouble("price");
    double amount = qty * price;
    totaldiscount += (amount * discount / 100);
    amount -= (amount * discount / 100);
    subtotal += amount;
    JSONObject shoeObject = new JSONObject();
    shoeObject.put("id", id);
    shoeObject.put("brand", brand);
    shoeObject.put("discount", discount);
    shoeObject.put("quantity", qty);
    shoeObject.put("price", price);
    shoeObject.put("totaldiscount", totaldiscount);
    shoeObject.put("amount", amount);
    shoeObject.put("subtotal", subtotal);
    shoesArray.put(shoeObject);
    
    
    // Formatted item row
    billText.append(String.format("%-10s %-15s %-8.2f %-10.2f %-8.2f %-12.2f\n",
            id, brand, qty, price, discount, amount));
    
    try (PreparedStatement psUpdate = c.prepareStatement("UPDATE shoes SET quantity = quantity - ? WHERE id = ?")) 
    {
        psUpdate.setDouble(1, qty);  // reduce by quantity sold
        psUpdate.setLong(2, id);
        psUpdate.executeUpdate();
    }
    
}

String itemsJson = shoesArray.toString();

// Totals Section
billText.append(repeat('-', width)).append("\n");
double cashdis = 0.0;
if (!cdtb.getText().isEmpty()) cashdis = Double.parseDouble(cdtb.getText());
subtotal -= cashdis;
double gst = subtotal * 0.18;
double total = subtotal + gst;

billText.append(String.format("%-30s : %30.2f\n", "Subtotal", subtotal));
billText.append(String.format("%-30s : %30.2f\n", "Additional Discount", cashdis));
billText.append(String.format("%-30s : %30.2f\n", "GST (18%)", gst));
billText.append(String.format("%-30s : %30.2f\n", "Total", total));
billText.append(repeat('-', width)).append("\n");

// Congratulations message
billText.append(center("🎉Congratulations! You Saved ₹" + String.format("%.2f", (totaldiscount + cashdis)) + "rs. On This Order🎉.", width)).append("\n");
billText.append(center(repeat('*', width), width)).append("\n");
billText.append(center("Thank You for Shopping! Visit Again", width)).append("\n");
billText.append(center(repeat('*', width), width)).append("\n");

    BillingArea.setText(billText.toString());

    // ✅ Step 9: Clear cart in database AFTER printing bill
    PreparedStatement clearCart = c.prepareStatement("TRUNCATE TABLE cart");
    clearCart.executeUpdate();
    clearCart.close();

    // ✅ Step 10: Clear BillTbl (visually, to match empty cart)
    model.setRowCount(0);

    // Step 11: Reload shoe stock table
    DefaultTableModel shoeModel = (DefaultTableModel) shoetbl.getModel();
    shoeModel.setRowCount(0);

    PreparedStatement ps = c.prepareStatement("SELECT * FROM shoes");
    ResultSet rs1 = ps.executeQuery();
    while (rs1.next()) {
        Object[] row = {
            rs1.getLong("id"),
            rs1.getString("brand"),
            rs1.getString("category"),
            rs1.getDouble("quantity"),
            rs1.getDouble("price"),
            rs1.getDouble("discount")+"%"
        };
        shoeModel.addRow(row);
    }

    rs1.close();
    ps.close();
    
        Connection c1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoeshopdb", "root", "");
        PreparedStatement ps1 = c1.prepareStatement("INSERT INTO billing (bill_no, customer_name, customer_contact, items_details, cashdiscount, created_by, created_at) VALUES (?, ?, ?, ?, ?, ?, ?)");
         ps1.setString(1, billNo);
            ps1.setString(2, cname);
            ps1.setString(3, contact);
            ps1.setString(4, itemsJson);
            ps1.setDouble(5, Double.parseDouble(cdtb.getText()));
            ps1.setString(6, aulbl.getText());
            ps1.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            ps1.executeUpdate();
            
      // Step 12: Reset input fields
    cnametb.setText("");
    ccontacttb.setText("");
    cdtb.setText("");
    JOptionPane.showMessageDialog(
        this,
        new JLabel("<html><span style='color:green; font-size:15px; font-weight:bold;'>Bill generated and cart cleared successfully!</span></html>"),
        "Success",
        JOptionPane.INFORMATION_MESSAGE
    );

} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(
        this,
        new JLabel("<html><span style='color:red; font-size:14px; font-weight:bold;'>Error: " + e.getMessage() + "</span></html>"),
        "Error",
        JOptionPane.ERROR_MESSAGE
    );
}
    }//GEN-LAST:event_jButton11ActionPerformed

    private void qtytbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qtytbActionPerformed
           // TODO add your handling code here:
    }//GEN-LAST:event_qtytbActionPerformed

    private void qtytbKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtytbKeyTyped
        // TODO add your handling code here:
       
    }//GEN-LAST:event_qtytbKeyTyped

    private void qtytbKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtytbKeyReleased
        // TODO add your handling code here:
    if (sbrandtb.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please Select Any Item From Shoes Table!");
        qtytb.setText("");
        amttb.setText("");
        Afdtb.setText("");
        return;
    }

    String qtyText = qtytb.getText().trim(); // get text and remove spaces

    if (!qtyText.matches("\\d*\\.?\\d*")) {
        JOptionPane.showMessageDialog(this, "Please enter only numeric values!");
        qtytb.setText("");
        amttb.setText("");
        Afdtb.setText("");
        return;
    }

    if (qtyText.isEmpty()) {
        amttb.setText(""); // clear amount if quantity is empty
        Afdtb.setText("");
        return;
    }
    if(Double.parseDouble(qtyText)== 0.0)
    {
        JOptionPane.showMessageDialog(this, "Please enter more than 0 quantity!");
        return;
    }
    try {
        double q = Double.parseDouble(qtyText); // quantity entered
        double price = Double.parseDouble(spricetb.getText());
        double discountp = Double.parseDouble(Discounttb.getText().trim());

        if (q < 0) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity (>=0)");
            qtytb.setText("");
            amttb.setText("");
            Afdtb.setText("");
            return;
        }

        // Calculate amounts
        double res = q * price;
        double afd = res - (res * discountp / 100);
        amttb.setText(String.format("%.2f", res)); // Amount before discount
        Afdtb.setText(String.format("%.2f", afd)); // Amount after discount

        // ✅ Update shoes table quantity in DB
        try (Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/shoeshopdb", "root", "")) {

            // Get current stock for selected shoe
            PreparedStatement ps1 = c.prepareStatement("SELECT quantity FROM shoes WHERE id = ?");
            ps1.setLong(1, Long.parseLong(sidtb.getText())); // assuming you have selected shoe ID in sidtb
            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {
                double currentStock = rs.getDouble("quantity");

                if (q > currentStock) {
                    JOptionPane.showMessageDialog(this, "Insufficient stock! Current stock: " + currentStock);
                    qtytb.setText("");
                    amttb.setText("");
                    Afdtb.setText("");
                    return;
                }
            }

            rs.close();
            ps1.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating stock: " + e.getMessage());
        }

    } catch (NumberFormatException e) {
        amttb.setText("");
        Afdtb.setText("");
    }

    }//GEN-LAST:event_qtytbKeyReleased

    private void jButton12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12MouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) BillTbl.getModel();

try {
    Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoeshopdb", "root", "");
    PreparedStatement check = c.prepareStatement("SELECT COUNT(*) FROM cart");
    ResultSet rs = check.executeQuery();
    rs.next();
    int rowCount = rs.getInt(1);

    if (rowCount == 0) {
        JOptionPane.showMessageDialog(
            this,
            new JLabel("<html><span style='color:orange; font-size:16px; font-weight:bold;'>Cart is already empty!</span></html>"),
            "Cart Status",
            JOptionPane.INFORMATION_MESSAGE
        );
    } else {
        // Ask user for confirmation before clearing
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to clear the entire cart?",
            "Confirm Clear Cart",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            // Step 2: Clear database table
            PreparedStatement pst = c.prepareStatement("TRUNCATE TABLE cart");
            pst.executeUpdate();
            model.setRowCount(0);
            JOptionPane.showMessageDialog(
                this,
                new JLabel("<html><span style='color:red; font-size:16px; font-weight:bold;'>Cart Cleared Successfully!</span></html>"),
                "Cart Update",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, e.getMessage());
}
    }//GEN-LAST:event_jButton12ActionPerformed

    private void cdtbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cdtbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cdtbActionPerformed

    private void searchtbKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchtbKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_searchtbKeyReleased

    private void cdtbKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cdtbKeyReleased
        // TODO add your handling code here:
        if (!cdtb.getText().matches("\\d*\\.?\\d*")) {
        JOptionPane.showMessageDialog(this, "Please enter only numeric values!");
        return;
    }
    }//GEN-LAST:event_cdtbKeyReleased

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
            java.util.logging.Logger.getLogger(Billing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Billing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Billing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Billing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Billing().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Afdlbl;
    private javax.swing.JTextField Afdtb;
    private javax.swing.JTable BillTbl;
    private javax.swing.JTextArea BillingArea;
    private javax.swing.JLabel Discountlbl;
    private javax.swing.JTextField Discounttb;
    private javax.swing.JTextField amttb;
    private javax.swing.JButton atbbtn;
    private javax.swing.JLabel aulbl;
    private javax.swing.JTextField billnotb;
    private javax.swing.JTextField ccontacttb;
    private javax.swing.JLabel cdlbl;
    private javax.swing.JTextField cdtb;
    private javax.swing.JLabel cnamelbl;
    private javax.swing.JTextField cnametb;
    private javax.swing.JButton exitbtn;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField qtytb;
    private javax.swing.JButton resetbtn;
    private javax.swing.JTextField sbrandtb;
    private javax.swing.JTextField searchtb;
    private javax.swing.JTable shoetbl;
    private javax.swing.JTextField sidtb;
    private javax.swing.JTextField spricetb;
    private javax.swing.JTextField stypetb;
    // End of variables declaration//GEN-END:variables
}
