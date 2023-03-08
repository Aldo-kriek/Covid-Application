/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package covidregistrationform;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
/**
 *
 * @author Studio
 */
public class frmRegisterForm extends javax.swing.JFrame {

    /**
     * Creates new form frmRegisterForm
     */
    public frmRegisterForm() {
        initComponents();
    }
    
    String strName;
    String strSurname;
    String strContact;
    String strAge;
    String strAddress;
    String strEmail;
    String strTemperature;
    String strSymptom;
    String strExposure;
    String sqlinsert;
    boolean boolRecordExists;
    String strRb1Yes;
    String strRb1No;
   
    
    private void mLoadInfo()
    {
        java.sql.Connection conMySQLConnectionString = null;
        String URL = "jdbc:mysql://localhost:3306/tracking_register_db";
        String User = "root";
        String Password = "Password";
        Statement stSQLQuery = null;
        ResultSet rs = null;
        String strSQLQuery = null;
        

        try
        {
            conMySQLConnectionString = DriverManager.getConnection(URL, User, Password);  
            stSQLQuery = conMySQLConnectionString.createStatement();
            strSQLQuery = "SELECT * FROM event_participants; ";  
            rs = stSQLQuery.executeQuery(strSQLQuery);
            ResultSetMetaData rsmt = rs.getMetaData();
            int intColumnCount = rsmt.getColumnCount();
            Vector vColumn = new Vector(intColumnCount);
            

            for (int i = 1; i<= intColumnCount; i++)
            {
                vColumn.add(rsmt.getColumnName(3));
            }
                    Vector vData = new Vector();
                    Vector vRow = new Vector();
                    
                    while (rs.next())
                    {
                        vRow = new Vector(intColumnCount);
                                for (int i=1; i<=intColumnCount; i++)
                    {
                                    vRow.add(rs.getObject(i));
                    }
                                vData.add(vRow);
                                
                                
                    }
                    

                    JFrame frame = new JFrame(); 
                    frame.setSize(1300,780); 
                    frame.setLocationRelativeTo(null); 
                    
                   
                    JPanel panel = new JPanel(); 
                    JTable jTable = new JTable(vData,vColumn);
                    JScrollPane jsp = new JScrollPane(jTable); 
                    panel.setLayout(new BorderLayout()); 
                    panel.add(jsp,BorderLayout.CENTER); 
                    frame.setContentPane(panel); 
                    frame.setVisible(true); 

                    
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error" + e);
        }
        finally
        {
            try
            {
                stSQLQuery.close();
                rs.close();
                conMySQLConnectionString.close();
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog (null, "Error Message");
            }
            
             
        }
    }

    
    
    private void mCreateUser()
    {
        java.sql.Connection conMySQLConnectionString = null;
        String URL = "jdbc:mysql://localhost:3306/tracking_register_db";
        String User = "root";
        String Password = "Password";
        
            if(rb1Yes.isSelected())
                {
                    strSymptom = "YES";
                }
                
                else if(rb1No.isSelected())
                {
                    strSymptom = "NO";
                }
            
            if(rb2Yes.isSelected())
                {
                    strExposure = "YES";
                }
                
                else if(rb2No.isSelected())
                {
                    strExposure = "NO";
                }
            
            
        try
        {
            conMySQLConnectionString = DriverManager.getConnection(URL, User, Password);
            Statement myStatement = conMySQLConnectionString.createStatement() ;
               
            sqlinsert = "insert into event_participants "+ "(name, surname, contact, age, address, email, temperature, symptom, exposure)"+"values('"+strName +"','"+ strSurname+"','"+ strContact+"','"+ strAge+ "','"+ strAddress+ "','"+ strEmail+ "','"+ strTemperature +"', '"+ strSymptom+ "','"+ strExposure + "')";
                myStatement.executeUpdate(sqlinsert);
                
            
                           
            
            JOptionPane.showMessageDialog(null, "Details has been Saved");
            }
        
       

        catch (Exception  e)
        {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    
    private void mClearFields()
    {
        txtName.setText("");
        txtSurname.setText("");
        txtContactNumber.setText("");
        txtAge.setText("");
        txtAddress.setText("");
        txtEmailAddress.setText("");
        txtTemperature.setText("");
        rb1Yes.setSelected(false);
        rb1No.setSelected(false);
        rb2Yes.setSelected(false);
        rb2No.setSelected(false);

    }
    
      private void mGetValuesFromGUI()
    {
        strName = txtName.getText();
        strSurname = txtSurname.getText();
        strContact = txtContactNumber.getText();
        strAge = txtAge.getText();
        strAddress = txtAddress.getText();
        strEmail =txtEmailAddress.getText();
        strTemperature = txtTemperature.getText();
        
        
       
         
      
    }
      
      private void mSetValuesToUpperCase()
    {
        strName = strName.toUpperCase();
        strSurname = strSurname.toUpperCase();
        strContact = strContact.toUpperCase();
        strAge = strAge.toUpperCase();
        strAddress = strAddress.toUpperCase();
        strEmail = strEmail.toUpperCase();
        strTemperature = strTemperature.toUpperCase();
        
        
    }
      
      private void mCheckIfItemsExistInTable()
    {
        String strDBConnectionString = "jdbc:mysql://localhost:3306/tracking_register_db";
        String strDBUser = "root";
        String strDBPassword = "Password";
        java.sql.Connection conMySQLConnectionString;
        Statement stStatement = null;
        ResultSet rs = null;
        
        try
        {
            conMySQLConnectionString = DriverManager.getConnection(strDBConnectionString, strDBUser, strDBPassword);
            stStatement = conMySQLConnectionString.createStatement();
            String strQuery = "Select * from event_participants ";  
            stStatement.execute(strQuery);
            rs=stStatement.getResultSet();
            
            boolRecordExists = rs.next();
        }
       catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        finally
        {
            try
            {
                stStatement.close();
            }
            catch (Exception e)
                    {
                        JOptionPane.showMessageDialog(null, "Connection String not Closed" + "" + e);
                        
                    }
        }
        
    }




    
                  

    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        lblCompleteFormHeader = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblSurname = new javax.swing.JLabel();
        lblContactNumber = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtSurname = new javax.swing.JTextField();
        txtContactNumber = new javax.swing.JTextField();
        lblAge = new javax.swing.JLabel();
        txtAge = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtEmailAddress = new javax.swing.JTextField();
        txtTemperature = new javax.swing.JTextField();
        lblAddress = new javax.swing.JLabel();
        lblEmailAddress = new javax.swing.JLabel();
        lblTemperature = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        rb1Yes = new javax.swing.JRadioButton();
        rb1No = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        rb2Yes = new javax.swing.JRadioButton();
        rb2No = new javax.swing.JRadioButton();
        btnClear = new javax.swing.JButton();
        btnSubmit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mnuSignIn = new javax.swing.JMenuItem();
        mnuExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registration");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("jButton1");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1242, 686, -1, -1));

        lblCompleteFormHeader.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblCompleteFormHeader.setForeground(new java.awt.Color(255, 255, 255));
        lblCompleteFormHeader.setText("Please complete this form when you enter the venue and wash hands with hand sanitiser");
        getContentPane().add(lblCompleteFormHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 1070, -1));

        lblName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblName.setForeground(new java.awt.Color(255, 255, 255));
        lblName.setText("Name");
        getContentPane().add(lblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 60, 30));

        lblSurname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSurname.setForeground(new java.awt.Color(255, 255, 255));
        lblSurname.setText("Surname");
        getContentPane().add(lblSurname, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, 80, -1));

        lblContactNumber.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblContactNumber.setForeground(new java.awt.Color(255, 255, 255));
        lblContactNumber.setText("Contact Number");
        getContentPane().add(lblContactNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 120, 20));

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });
        getContentPane().add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, 324, -1));
        getContentPane().add(txtSurname, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 150, 324, -1));
        getContentPane().add(txtContactNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 190, 324, -1));

        lblAge.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAge.setForeground(new java.awt.Color(255, 255, 255));
        lblAge.setText("Age");
        getContentPane().add(lblAge, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, 45, 27));
        getContentPane().add(txtAge, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 240, 89, -1));
        getContentPane().add(txtAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 280, 324, -1));
        getContentPane().add(txtEmailAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 310, 324, -1));
        getContentPane().add(txtTemperature, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 350, 91, -1));

        lblAddress.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAddress.setForeground(new java.awt.Color(255, 255, 255));
        lblAddress.setText("Address (Place/Area)");
        getContentPane().add(lblAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, -1, -1));

        lblEmailAddress.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblEmailAddress.setForeground(new java.awt.Color(255, 255, 255));
        lblEmailAddress.setText("Email Address");
        getContentPane().add(lblEmailAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 310, 110, 20));

        lblTemperature.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTemperature.setForeground(new java.awt.Color(255, 255, 255));
        lblTemperature.setText("Temperature");
        getContentPane().add(lblTemperature, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 350, 115, 20));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Have you had any COVID-19 symptoms: Fever (over 38 degrees), dry cough or shortness of breath?");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 400, -1, -1));

        rb1Yes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rb1Yes.setText("YES");
        rb1Yes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb1YesActionPerformed(evt);
            }
        });
        getContentPane().add(rb1Yes, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 440, -1, -1));

        rb1No.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rb1No.setText("NO");
        rb1No.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb1NoActionPerformed(evt);
            }
        });
        getContentPane().add(rb1No, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 480, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Have you been exposed to anyone with COVID-19 OR someone who has symptoms in the last 4 weeks?");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 530, -1, -1));

        rb2Yes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rb2Yes.setText("YES");
        rb2Yes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb2YesActionPerformed(evt);
            }
        });
        getContentPane().add(rb2Yes, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 570, -1, -1));

        rb2No.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rb2No.setText("NO");
        rb2No.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb2NoActionPerformed(evt);
            }
        });
        getContentPane().add(rb2No, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 610, -1, -1));

        btnClear.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        getContentPane().add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 650, 86, -1));

        btnSubmit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });
        getContentPane().add(btnSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 650, -1, -1));

        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 650, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/covidregistrationform/050620030611.jpg"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 1120, 740));

        jMenu1.setText("File");

        mnuSignIn.setText("Sign In");
        mnuSignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSignInActionPerformed(evt);
            }
        });
        jMenu1.add(mnuSignIn);

        mnuExit.setText("Exit");
        mnuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuExitActionPerformed(evt);
            }
        });
        jMenu1.add(mnuExit);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(1136, 785));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rb1YesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb1YesActionPerformed
        if (rb1Yes.isSelected())
            rb1No.setSelected(false);

    }//GEN-LAST:event_rb1YesActionPerformed

    private void rb1NoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb1NoActionPerformed
        if (rb1No.isSelected())
            rb1Yes.setSelected(false);
    }//GEN-LAST:event_rb1NoActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        frmHome frmHome = new frmHome();
        frmHome.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
       

        if (txtName.getText().equals(""))
        
        {
            JOptionPane.showMessageDialog(null, "The field can not be left empty");
            txtName.requestFocusInWindow();
        }
        
        else if (txtSurname.getText().equals(""))
                
        {
                JOptionPane.showMessageDialog(null, "The field can not be left empty");
                txtSurname.requestFocusInWindow();
        }      
        
        else if (txtContactNumber.getText().equals(""))
                
        {
                JOptionPane.showMessageDialog(null, "The field can not be left empty");
                txtContactNumber.requestFocusInWindow();
        }      
        
        else if (txtAge.getText().equals(""))
                
        {
                JOptionPane.showMessageDialog(null, "The field can not be left empty");
                txtAge.requestFocusInWindow();
        }      
        
        else if (txtAddress.getText().equals(""))
                
        {
                JOptionPane.showMessageDialog(null, "The field can not be left empty");
                txtAddress.requestFocusInWindow();
        }      
        
        else if (txtEmailAddress.getText().equals(""))
                
        {
                JOptionPane.showMessageDialog(null, "The field can not be left empty");
                txtEmailAddress.requestFocusInWindow();
        }      
        
        else if (txtTemperature.getText().equals(""))
                
        {
                JOptionPane.showMessageDialog(null, "The field can not be left empty");
                txtTemperature.requestFocusInWindow();
        }      
        
      
        
        else 
        {
                mGetValuesFromGUI();
                mSetValuesToUpperCase();
                mCheckIfItemsExistInTable();
                mCreateUser();
                mClearFields();

        }
     
        
        String strSymptoms =null;
        String strExposure =null;
       
        
        if(rb1Yes.isSelected())
        {
            strSymptoms = "Yes";
        }
        
        else if (rb1No.isSelected())
                
        {
                strSymptoms = "No";
        }      
        
        if(rb2Yes.isSelected())
        {
            strExposure = "Yes";
        }
        
        else if (rb2No.isSelected())
                
        {
                strExposure = "No";
        }      
        
      
        
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed

        mClearFields();
        
    }//GEN-LAST:event_btnClearActionPerformed

    private void rb2YesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb2YesActionPerformed
        if (rb2Yes.isSelected())
            rb2No.setSelected(false);
    }//GEN-LAST:event_rb2YesActionPerformed

    private void rb2NoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb2NoActionPerformed
        if (rb2No.isSelected())
            rb2Yes.setSelected(false);
    }//GEN-LAST:event_rb2NoActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void mnuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuExitActionPerformed
         System.exit(0);
    }//GEN-LAST:event_mnuExitActionPerformed

    private void mnuSignInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSignInActionPerformed
        frmLogin frmLogin = new frmLogin();
        frmLogin.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mnuSignInActionPerformed

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
            java.util.logging.Logger.getLogger(frmRegisterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmRegisterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmRegisterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmRegisterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmRegisterForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblAge;
    private javax.swing.JLabel lblCompleteFormHeader;
    private javax.swing.JLabel lblContactNumber;
    private javax.swing.JLabel lblEmailAddress;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblSurname;
    private javax.swing.JLabel lblTemperature;
    private javax.swing.JMenuItem mnuExit;
    private javax.swing.JMenuItem mnuSignIn;
    private javax.swing.JRadioButton rb1No;
    private javax.swing.JRadioButton rb1Yes;
    private javax.swing.JRadioButton rb2No;
    private javax.swing.JRadioButton rb2Yes;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtContactNumber;
    private javax.swing.JTextField txtEmailAddress;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSurname;
    private javax.swing.JTextField txtTemperature;
    // End of variables declaration//GEN-END:variables
}
