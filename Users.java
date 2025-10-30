/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package shoeshopmng;
import com.toedter.calendar.JDateChooser;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author hdoff
 */
public class Users extends javax.swing.JFrame {

/**
     * Creates new form Users
     */
        String auid;
    String adminname;    
    String previousuid = "0";
    private static final String SECRET_KEY = "123456789";  
    private static final String SALTVALUE = "abcdefg";
    
    public Users() {
        initComponents();
    }
    public Users(String auid) {
        initComponents();
        try
        {
            DefaultTableModel model= (DefaultTableModel)usrtbl.getModel();
            Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/shoeshopdb","root","");
            PreparedStatement pt=c.prepareStatement("select * from users");
            ResultSet rs=pt.executeQuery();
            while(rs.next())
            {
                model.addRow(new String[] {rs.getString(1),rs.getString(2),
                rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
                rs.getString(7),rs.getString(8),
                rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12)});
            }
            this.auid = auid;
            PreparedStatement pt2=c.prepareStatement("select name from admin where id = ?");
            pt2.setString(1, this.auid);
            ResultSet rs2 = pt2.executeQuery();
            if (rs2.next()) 
            {
              adminname = rs2.getString("Admin " +"name");
            }
            if(auid.equals("0"))
            {
                adminname = "Super Admin";      
            }
            adminlbl.setText(adminname);
        }
        catch (SQLException ex) 
        {
            //Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        jButton2.setVisible(false);
        SearchFilter();
    }
    
    void SearchFilter()
    {
        DefaultTableModel model = (DefaultTableModel) usrtbl.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        usrtbl.setRowSorter(sorter);

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
    
       public static String encrypt(String strToEncrypt)   
    {  
    try   
    {  
      /* Declare a byte array. */  
      byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};  
      IvParameterSpec ivspec = new IvParameterSpec(iv);        
      /* Create factory for secret keys. */  
      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");  
      /* PBEKeySpec class implements KeySpec interface. */  
      KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALTVALUE.getBytes(), 65536, 256);  
      SecretKey tmp = factory.generateSecret(spec);  
      SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");  
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  
      cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);  
      /* Retruns encrypted value. */  
      return Base64.getEncoder()  
      .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));  
    }   
    catch (Exception e)   
    {  
     
    }  
    return null;  
    }  
    
    
    public static String decrypt(String strToDecrypt)   
    {  
    try   
    {  
      /* Declare a byte array. */  
      byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};  
      IvParameterSpec ivspec = new IvParameterSpec(iv);  
      /* Create factory for secret keys. */  
      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");  
      /* PBEKeySpec class implements KeySpec interface. */  
      KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALTVALUE.getBytes(), 65536, 256);  
      SecretKey tmp = factory.generateSecret(spec);  
      SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");  
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");  
      cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);  
      /* Retruns decrypted value. */  
      return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));  
    }   
    catch (Exception e)   
    {  
      
    }  
    return null;  
    }  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        usrtbl = new javax.swing.JTable();
        adminicon = new javax.swing.JLabel();
        uidtb = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        unametb = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        uaddtb = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        ucontacttb = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        upasstb = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        udojtb = new javax.swing.JTextField();
        cbbtn = new javax.swing.JCheckBox();
        searchtb = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        adminlbl = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255), 5));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 35)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 255));
        jLabel8.setText("Users");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(571, 571, 571))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255), 8));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 255));
        jLabel9.setText("Stock");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 255));
        jLabel10.setText("Trust Worthy");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 255));
        jLabel11.setText("Multi Admin");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 255));
        jLabel12.setText("Effective Cost");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 255));
        jLabel13.setText("Multi User");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 255));
        jLabel14.setText("Quality");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel13))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))
                                .addGap(8, 8, 8))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel14)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel10)
                .addGap(84, 84, 84)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(86, 86, 86)
                .addComponent(jLabel14)
                .addGap(88, 88, 88)
                .addComponent(jLabel11)
                .addGap(81, 81, 81)
                .addComponent(jLabel12)
                .addGap(50, 50, 50))
        );

        usrtbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0), 2));
        usrtbl.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        usrtbl.setForeground(new java.awt.Color(51, 51, 255));
        usrtbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Address", "Contact", "Date Of Joining", "Password", "First Pet", "First School", "Created At", "Created By", "Updated At", "Updated By"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        usrtbl.setRowHeight(35);
        usrtbl.setRowMargin(1);
        usrtbl.setSelectionBackground(new java.awt.Color(255, 255, 0));
        usrtbl.setSelectionForeground(new java.awt.Color(51, 51, 255));
        usrtbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usrtblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(usrtbl);

        adminicon.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        adminicon.setForeground(new java.awt.Color(51, 51, 255));
        adminicon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/shoeshopmng/AdminIcon.png"))); // NOI18N

        uidtb.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        uidtb.setForeground(new java.awt.Color(51, 51, 255));
        uidtb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uidtbActionPerformed(evt);
            }
        });
        uidtb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                uidtbKeyReleased(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 255));
        jLabel17.setText("Name");

        unametb.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        unametb.setForeground(new java.awt.Color(51, 51, 255));
        unametb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unametbActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 255));
        jLabel18.setText("Address");

        uaddtb.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        uaddtb.setForeground(new java.awt.Color(51, 51, 255));
        uaddtb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uaddtbActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 255));
        jLabel19.setText("Contact");

        ucontacttb.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        ucontacttb.setForeground(new java.awt.Color(51, 51, 255));
        ucontacttb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ucontacttbActionPerformed(evt);
            }
        });
        ucontacttb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ucontacttbKeyReleased(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 255));
        jLabel20.setText("Password");

        upasstb.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        upasstb.setForeground(new java.awt.Color(51, 51, 255));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 255));
        jButton1.setText("SAVE");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 51, 255));
        jButton2.setText("UPDATE");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 51, 255));
        jButton3.setText("DELETE");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 51, 255));
        jButton4.setText("RESET");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 255));
        jLabel21.setText("Users Table");

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

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 255));
        jLabel22.setText("Date Of Joining");

        udojtb.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        udojtb.setForeground(new java.awt.Color(51, 51, 255));
        udojtb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                udojtbMouseClicked(evt);
            }
        });
        udojtb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                udojtbActionPerformed(evt);
            }
        });

        cbbtn.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        cbbtn.setForeground(new java.awt.Color(51, 51, 255));
        cbbtn.setText("Show Password");
        cbbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbtnActionPerformed(evt);
            }
        });

        searchtb.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        searchtb.setForeground(new java.awt.Color(51, 51, 255));
        searchtb.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        searchtb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchtbActionPerformed(evt);
            }
        });
        searchtb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchtbKeyReleased(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 255));
        jLabel23.setText("Aadhar Id");

        adminlbl.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        adminlbl.setForeground(new java.awt.Color(0, 204, 255));
        adminlbl.setText("Admin");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(uidtb, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel23))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(cbbtn, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel17)
                                                        .addComponent(unametb, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(44, 44, 44)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel18)
                                                        .addComponent(uaddtb, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(40, 40, 40)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel19)
                                                        .addComponent(ucontacttb, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(46, 46, 46)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel22)
                                                        .addComponent(udojtb, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(42, 42, 42)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel20)
                                                        .addComponent(upasstb, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1407, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(searchtb, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(523, 523, 523)
                                        .addComponent(jLabel21))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(375, 375, 375)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(39, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(adminicon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(adminlbl)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(136, 136, 136))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(adminicon)
                    .addComponent(adminlbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uidtb))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unametb))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uaddtb))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ucontacttb))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(upasstb))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(udojtb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbtn)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchtb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void uidtbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uidtbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uidtbActionPerformed

    private void unametbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unametbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unametbActionPerformed

    private void uaddtbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uaddtbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uaddtbActionPerformed

    private void ucontacttbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ucontacttbActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_ucontacttbActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        uidtb.setText("");
        unametb.setText("");
        uaddtb.setText("");
        ucontacttb.setText("");
        upasstb.setText("");
        udojtb.setText("");
          jButton1.setVisible(true);
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        // TODO add your handling code here:
        new AdDash1(auid).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
         new Login1().setVisible(true);
         this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jButton6MouseClicked

    private void udojtbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_udojtbActionPerformed
JDateChooser chooser = new JDateChooser();
chooser.setDateFormatString("dd-MMM-yyyy"); // "MMM" gives month name abbreviation, e.g., Jan, Feb

// Show dialog
int option = JOptionPane.showConfirmDialog(null, chooser, "Select Date", JOptionPane.OK_CANCEL_OPTION);

if(option == JOptionPane.OK_OPTION) {
    if(chooser.getDate() == null) {
        // Validation: No date selected
        JOptionPane.showMessageDialog(null,
            "Please select a valid date!",
            "Invalid Date",
            JOptionPane.WARNING_MESSAGE
        );
        udojtb.setText(""); // Clear field
    } else {
        // Date is selected, format it
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy"); // e.g., 08-Oct-2025
        udojtb.setText(sdf.format(chooser.getDate()));
    }
} else {
    // If user cancels, clear field
    udojtb.setText("");
}

    }//GEN-LAST:event_udojtbActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
                   // TODO add your handling code here:
                   if(uidtb.getText().equals("") || unametb.getText().equals("") || ucontacttb.getText().equals("") || upasstb.getText().equals("") || udojtb.getText().equals("") || uaddtb.getText().equals(""))
                   {
                       JOptionPane.showMessageDialog(this,"Please Enter all Values","Alert",JOptionPane.WARNING_MESSAGE);
                   }
                   else
                   {
                       String pass=upasstb.getText();
                       String encrptedstr=encrypt(pass);
                            new SetQuestion(uidtb.getText(),
                                                  unametb.getText(),
                                                    uaddtb.getText(),
                                                    ucontacttb.getText(),
                                                    udojtb.getText(),
                                                    encrptedstr,auid,previousuid,1).setVisible(true);
                            this.dispose();
                                       
                      
                   }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        if(uidtb.getText().equals("") || unametb.getText().equals("") || ucontacttb.getText().equals("") || upasstb.getText().equals("") || udojtb.getText().equals(""))
                   {
                       JOptionPane.showMessageDialog(this,"Please Enter all Values","Alert",JOptionPane.WARNING_MESSAGE);
                   }
        else
        {
            DefaultTableModel model=(DefaultTableModel)usrtbl.getModel();
     
         if(usrtbl.getSelectedRowCount()==1)
             {
                String id=uidtb.getText();
                 String name=unametb.getText();
                 String address=uaddtb.getText();
                 String phone=ucontacttb.getText();
                 String pass=upasstb.getText();
                String encrptedstr=encrypt(pass);
                 String dateofjoin=udojtb.getText();
             try
             {
                 new SetQuestion(id,name,address,phone,dateofjoin, encrptedstr,this.auid,previousuid,0).setVisible(true);
                 this.dispose();
             }
             catch(Exception e)
             {
                 
             }
             
         }
        }
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        DefaultTableModel model=(DefaultTableModel)usrtbl.getModel();
          int selectedRow = usrtbl.getSelectedRow();

        if (selectedRow == -1) 
        {
            JOptionPane.showMessageDialog(this, "Please select a row to delete!", "Alert", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Get ID from the selected row
        String id = model.getValueAt(selectedRow, 0).toString();

        int confirm = JOptionPane.showConfirmDialog(this,"Are you sure you want to delete this User?","Confirm Delete",JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) 
        {
            return;
        }
             try
             {
                 
                 Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/shoeshopdb","root","");
                 PreparedStatement pst=c.prepareStatement("delete from users where id='"+id+"'");
                 JOptionPane.showMessageDialog(this,"Data Deleted Successfully");
                 model.removeRow(usrtbl.getSelectedRow());
                 pst.executeUpdate();
                    uidtb.setText("");
                    unametb.setText("");
                    uaddtb.setText("");
                    ucontacttb.setText("");
                    upasstb.setText("");  
                    udojtb.setText("");
                  jButton1.setVisible(true);
                  jButton2.setVisible(false);
             }
             catch(Exception e)
             {
                 
             }
    }//GEN-LAST:event_jButton3MouseClicked

    private void usrtblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usrtblMouseClicked
        // TODO add your handling code here:
        jButton1.setVisible(false);
        jButton2.setVisible(true);
        DefaultTableModel tblmdl=(DefaultTableModel)usrtbl.getModel();
        int selectedrowindex=usrtbl.getSelectedRow();
//        Long.parseLong(tblmdl.getValueAt(selectedrowindex,0).toString());
        uidtb.setText(tblmdl.getValueAt(selectedrowindex,0).toString());
        previousuid = uidtb.getText();
        unametb.setText(tblmdl.getValueAt(selectedrowindex,1).toString());
        uaddtb.setText(tblmdl.getValueAt(selectedrowindex,2).toString());
        ucontacttb.setText(tblmdl.getValueAt(selectedrowindex,3).toString());
        udojtb.setText(tblmdl.getValueAt(selectedrowindex, 4).toString());
        upasstb.setText(decrypt(tblmdl.getValueAt(selectedrowindex,5).toString()));
 
    }//GEN-LAST:event_usrtblMouseClicked

    private void cbbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbtnActionPerformed
       boolean checked = cbbtn.isSelected();
        if ( upasstb.getEchoChar() != '\u0000' && checked == true) 
        {
            upasstb.setEchoChar('\u0000');
        } 
        else 
        {
            upasstb.setEchoChar('*');
        }       // TODO add your handling code here:
    }//GEN-LAST:event_cbbtnActionPerformed

    private void uidtbKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_uidtbKeyReleased
        // TODO add your handling code here:
         if (!uidtb.getText().matches("\\d*\\.?\\d*")) {
        JOptionPane.showMessageDialog(this, "Please enter only numeric values!");
        // Remove the last typed character
        if (uidtb.getText().length() > 0) {
            uidtb.setText("");
        }
        return;
    }
    }//GEN-LAST:event_uidtbKeyReleased

    private void udojtbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_udojtbMouseClicked
        // TODO add your handling code here:
       JDateChooser chooser = new JDateChooser();
chooser.setDateFormatString("dd-MMM-yyyy"); // "MMM" gives month name abbreviation, e.g., Jan, Feb

// Show dialog
int option = JOptionPane.showConfirmDialog(null, chooser, "Select Date", JOptionPane.OK_CANCEL_OPTION);

if(option == JOptionPane.OK_OPTION) {
    if(chooser.getDate() == null) {
        // Validation: No date selected
        JOptionPane.showMessageDialog(null,
            "Please select a valid date!",
            "Invalid Date",
            JOptionPane.WARNING_MESSAGE
        );
        udojtb.setText(""); // Clear field
    } else {
        // Date is selected, format it
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy"); // e.g., 08-Oct-2025
        udojtb.setText(sdf.format(chooser.getDate()));
    }
} else {
    // If user cancels, clear field
    udojtb.setText("");
}
    }//GEN-LAST:event_udojtbMouseClicked

    private void ucontacttbKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ucontacttbKeyReleased
        // TODO add your handling code here:
         if (!ucontacttb.getText().matches("\\d*\\.?\\d*")) {
        JOptionPane.showMessageDialog(this, "Please enter only numeric values!");
        // Remove the last typed character
        if (ucontacttb.getText().length() > 0) {
            ucontacttb.setText("");
        }
        return;
    }
    }//GEN-LAST:event_ucontacttbKeyReleased

    private void searchtbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchtbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchtbActionPerformed

    private void searchtbKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchtbKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_searchtbKeyReleased

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Users().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adminicon;
    private javax.swing.JLabel adminlbl;
    private javax.swing.JCheckBox cbbtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchtb;
    private javax.swing.JTextField uaddtb;
    private javax.swing.JTextField ucontacttb;
    private javax.swing.JTextField udojtb;
    private javax.swing.JTextField uidtb;
    private javax.swing.JTextField unametb;
    private javax.swing.JPasswordField upasstb;
    private javax.swing.JTable usrtbl;
    // End of variables declaration//GEN-END:variables
}
