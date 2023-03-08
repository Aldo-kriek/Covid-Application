/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package covidregistrationform;
        
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.*;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Studio
 */
public class frmUser extends javax.swing.JFrame {
        Connection conMySQLConnectionString = null;
    /**
     * Creates new form frmUser
     */
    public frmUser() {
        initComponents();
        mLoadFirstName();
        mReadUserDetails();
       this.setTitle("Participants");
       
       
    }
    
    Boolean boolRecordExists = false;
    Boolean boolEdit = false;
    Boolean boolCreate= false;
    
    String ParticipantId;
    String Name;
    String Surname;
    String Contact;
    String Age;
    String Address;
    String Email;
    String Temperature;
    String Symptoms;
    String Exposure;
    
    
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
    
    String strRb1Yes;
    String strRb1No;
    
   
    
    private void mLoadFirstName()
    {
        
        String strDBConnectionString = "jdbc:mysql://localhost:3306/property Management";
        String DBUser = "root";
        String DBPassword = ""; 
        java.sql.Connection conMySQLConnectionString;
        Statement stStatement =null;
        ResultSet rs = null;
        
        
        try
        {
             conMySQLConnectionString = DriverManager.getConnection(strDBConnectionString, DBUser, DBPassword);
             stStatement = conMySQLConnectionString.createStatement();
             String strQuery = "Select name from tenant";
             stStatement.execute(strQuery);
             rs = stStatement.getResultSet();
             while (rs.next())
             {
                 cboFirstName.addItem(rs.getString(1));
             }
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
    
    
    
    private void mReadUserDetails()
    {
        String strDBConnectionString = "jdbc:mysql://localhost:3306/property Management";
        String DBUser = "root";
        String DBPassword = ""; 
        java.sql.Connection conMySQLConnectionString;
        Statement stStatement =null;
        ResultSet rs = null;
        
        try
        {
            conMySQLConnectionString = DriverManager.getConnection(strDBConnectionString, DBUser, DBPassword);
            stStatement = conMySQLConnectionString.createStatement();
            String strQuery = "Select * from tenant where name ='"+cboFirstName.getSelectedItem().toString()+"'";
            stStatement.execute(strQuery);
            rs = stStatement.getResultSet();
            while (rs.next())
            {
                ParticipantId = rs.getString(1);
                Name = rs.getString(2);
                Surname = rs.getString(3);
                Contact = rs.getString(4);
                Age = rs.getString(5);
                Address = rs.getString(6);
                Email = rs.getString(7);
                Temperature = rs.getString(8);
                Symptoms = rs.getString(9);
                Exposure = rs.getString(10);
                
            }
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
                        JOptionPane.showMessageDialog(null, "Connection String not closed" +"" +e);                    
                    }
        }
    }
    
    
    
    
    private void mSetValuesInGUI()
    {
        txtID.setText(ParticipantId);
        txtName.setText(Name);
        txtSurname.setText(Surname);
        txtContact.setText(Contact);
        txtAge.setText(Age);
        txtAddress.setText(Address);
        txtEmailAddress.setText(Email);
        txtTemperature.setText(Temperature);
        txtSymptom.setText(Symptoms);
        txtExposure.setText(Exposure);
    }
    
       
    private void mSetValueGUI()
    {
        txtID.setText(ParticipantId);
        txtName.setText(strName);
        txtSurname.setText(strSurname);
        txtContact.setText(strContact);
        txtAge.setText(strAge);
        txtAddress.setText(strAddress);
        txtEmailAddress.setText(strEmail);
        txtTemperature.setText(strTemperature);
        txtSymptom.setText(strSymptom);
        txtExposure.setText(strExposure);
    }

    
    
   

    

            
        private void mCreateUser()
    {
        java.sql.Connection conMySQLConnectionString = null;
        String URL = "jdbc:mysql://localhost:3306/tracking_register_db";
        String User = "root";
        String Password = "";
        
            
            
            
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

        
        private void mDelete()
    {
        String strDBConnectionString = "jdbc:mysql://localhost:3306/tracking_register_db";
        String DBUser = "root";
        String DBPassword = ""; 
        java.sql.Connection conMySQLConnectionString;
        Statement stStatement =null;
        ResultSet rs = null;
        
        try
        {
            conMySQLConnectionString = DriverManager.getConnection(strDBConnectionString, DBUser, DBPassword);
            String strQuery = "Delete FROM event_participants where ParticipantId = '" +  ParticipantId +"'and name = '" +  strName +"'  and surname = '" + strSurname +"' and contact = '"+ strContact +"' and age = '" + strAge +"' and address = '"+ strAddress +"' and email = '" + strEmail +"' and temperature = '"+ strTemperature  +"' and symptom = '"+ strSymptom +"' and exposure = '"+ strExposure;
            stStatement = conMySQLConnectionString.prepareStatement(strQuery);
            stStatement.execute(strQuery);
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
                JOptionPane.showMessageDialog(null, "Connection String not closed" + "" + e);
                
            }
        }
    }
        
        private void mReadDetails()
    {
        String strDBConnectionString = "jdbc:mysql://localhost:3306/tracking_register_db";
        String DBUser = "root";
        String DBPassword = ""; 
        java.sql.Connection conMySQLConnectionString;
        Statement stStatement =null;
        ResultSet rs = null;
        
        try
        {
            conMySQLConnectionString = DriverManager.getConnection(strDBConnectionString, DBUser, DBPassword);
            stStatement = conMySQLConnectionString.createStatement();
            String strQuery = "Select participantid, name, surname, contact, age, address, email, temperature, symptom, exposure FROM event_participants where name ='"+cboFirstName.getSelectedItem().toString()+"'";
            stStatement.execute(strQuery);
            rs = stStatement.getResultSet();
            while (rs.next())
            {
                ParticipantId = rs.getString(1);
                strName = rs.getString(2);
                strSurname = rs.getString(3);
                strContact = rs.getString(4);
                strAge = rs.getString(5);
                strAddress = rs.getString(6);
                strEmail = rs.getString(7);
                strTemperature = rs.getString(8);
                strSymptom = rs.getString(9);
                strExposure = rs.getString(10);
                
            }
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
                        JOptionPane.showMessageDialog(null, "Connection String not closed" +"" +e);                    
                    }
        }
    }
        
        private void mClearComboBox()
    {
        String[] arrArray = new String[0];
        javax.swing.DefaultComboBoxModel model = new javax.swing.DefaultComboBoxModel(arrArray);
        cboFirstName.setModel(model);
    }
    private void mClearVariables()
    {
        strName = "";
        strSurname = "";
        ParticipantId = "";
        strContact = "";
        strAge = "";
        strAddress = "";
        strEmail = "";
        strTemperature = "";
        strSymptom = "";
        strExposure = "";
    }
    
    private void mGetValuesFromGUI()
    {
        strName = txtName.getText();
        strSurname = txtSurname.getText();
        strContact = txtContact.getText();
        strAge = txtAge.getText();
        strAddress = txtAddress.getText();
        strEmail =txtEmailAddress.getText();
        strTemperature = txtTemperature.getText();
        strSymptom = txtSymptom.getText();
        strExposure = txtExposure.getText();
        
      
    }
    
    
     private void mUpdateActor()
    {
        String strDBConnectionString = "jdbc:mysql://localhost:3306/sakila";
        String DBUser = "root";
        String DBPassword = ""; 
        java.sql.Connection conMySQLConnectionString;
        Statement stStatement =null;
        ResultSet rs = null;
        
        try
        {
            conMySQLConnectionString = DriverManager.getConnection(strDBConnectionString, DBUser, DBPassword);
             stStatement = conMySQLConnectionString.createStatement();
             String strQuery = "UPDATE event_participants SET name = '"+ strName+"', last_name = '"+strSurname +"', contact = '"+strContact + "', age = '"+strAge +"', address = '"+strAddress + "', email = '"+strEmail + "', temperature = '"+strTemperature + "', symptom = '"+strSymptom + "', exposure = '"+strExposure + "'WHERE participantid = "+ ParticipantId;   
             stStatement.executeUpdate(strQuery);
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
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "Connection String not closed" + " " + e );
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

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        txtSurname = new javax.swing.JTextField();
        txtContact = new javax.swing.JTextField();
        txtAge = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtEmailAddress = new javax.swing.JTextField();
        txtTemperature = new javax.swing.JTextField();
        txtSymptom = new javax.swing.JTextField();
        txtExposure = new javax.swing.JTextField();
        txtID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTable = new javax.swing.JTable();
        cboFirstName = new javax.swing.JComboBox<>();
        btnSubmit = new javax.swing.JButton();
        btnAddTable = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnSubmit1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mnuSignOut = new javax.swing.JMenuItem();
        mnuExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mnuRegistration = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Participants");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 317, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("CANCEL");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 640, 100, 34));

        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 640, 100, 34));
        getContentPane().add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 540, 115, -1));

        txtSurname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSurnameActionPerformed(evt);
            }
        });
        getContentPane().add(txtSurname, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 540, 115, -1));

        txtContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContactActionPerformed(evt);
            }
        });
        getContentPane().add(txtContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 540, 116, -1));
        getContentPane().add(txtAge, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 540, 36, -1));
        getContentPane().add(txtAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 540, 271, -1));
        getContentPane().add(txtEmailAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 540, 268, -1));

        txtTemperature.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTemperatureActionPerformed(evt);
            }
        });
        getContentPane().add(txtTemperature, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 540, 48, -1));
        getContentPane().add(txtSymptom, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 540, 60, -1));
        getContentPane().add(txtExposure, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 540, 50, -1));
        getContentPane().add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 540, 37, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 520, 23, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Name");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 520, 36, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Surname");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 520, 53, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Contact ");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 520, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Age");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 520, 36, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Address");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 520, 54, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Email Address");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 520, 82, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Temp");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 520, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Symptom");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 520, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Exposure");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 520, 61, 22));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("EDIT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 450, 100, 34));

        tblTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ParticipantID", "Name", "Surname", "Contact", "Age", "Address", "Email", "Temperature", "Symptom", "Exposure"
            }
        ));
        jScrollPane1.setViewportView(tblTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 137, 1146, 260));

        cboFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFirstNameActionPerformed(evt);
            }
        });
        getContentPane().add(cboFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 410, 100, 29));

        btnSubmit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSubmit.setText("CREATE");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });
        getContentPane().add(btnSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 640, 100, 34));

        btnAddTable.setText("LOAD TABLE");
        btnAddTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTableActionPerformed(evt);
            }
        });
        getContentPane().add(btnAddTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 100, 160, 30));
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 0, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/covidregistrationform/blue-covid-banner.jpg"))); // NOI18N
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 720));

        btnSubmit1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSubmit1.setText("CREATE");
        btnSubmit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmit1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnSubmit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 640, 100, 34));

        jMenu1.setText("File");

        mnuSignOut.setText("Sign Out");
        mnuSignOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSignOutActionPerformed(evt);
            }
        });
        jMenu1.add(mnuSignOut);

        mnuExit.setText("Exit");
        mnuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuExitActionPerformed(evt);
            }
        });
        jMenu1.add(mnuExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Forms");

        mnuRegistration.setText("Registration");
        mnuRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRegistrationActionPerformed(evt);
            }
        });
        jMenu2.add(mnuRegistration);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mnuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mnuExitActionPerformed

    private void mnuSignOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSignOutActionPerformed
        frmMain frmMain = new frmMain();
        frmMain.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mnuSignOutActionPerformed

    private void mnuRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRegistrationActionPerformed
        frmRegisterForm frmRegisterForm = new frmRegisterForm();
        frmRegisterForm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mnuRegistrationActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        frmHome frmHome = new frmHome();
        frmHome.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
       
         mReadDetails();
            mDelete();
            mClearComboBox();
            mClearVariables();
            mLoadFirstName();
        
    }//GEN-LAST:event_btnDeleteActionPerformed

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

        else if (txtContact.getText().equals(""))

        {
            JOptionPane.showMessageDialog(null, "The field can not be left empty");
            txtContact.requestFocusInWindow();
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
            mCreateUser();
           

        }

        String strSymptoms =null;
        String strExposure =null;

        

        

    }//GEN-LAST:event_btnSubmitActionPerformed

    
    private void mAddTable()
    {
        java.sql.Connection conMySQLConnectionString = null;
        String URL = "jdbc:mysql://localhost:3306/tracking_register_db";
        String User = "root";
        String Password = "";
        Statement stSQLQuery = null;
        ResultSet rs = null;
        String strSQLQuery = null;
        

        try
        {
            conMySQLConnectionString = DriverManager.getConnection(URL, User, Password);  
            stSQLQuery = conMySQLConnectionString.createStatement();
            
            strSQLQuery = "SELECT * FROM event_participants";  
            rs = stSQLQuery.executeQuery(strSQLQuery);
              
          
                while(rs.next()) {
                    
                ParticipantId = rs.getString(1);
                Name = rs.getString(2);
                Surname = rs.getString(3);
                Contact = rs.getString(4);
                Age = rs.getString(5);
                Address = rs.getString(6);
                Email = rs.getString(7);
                Temperature = rs.getString(8);
                Symptoms = rs.getString(9);
                Exposure = rs.getString(10);
                
                
                String tbData[] = {ParticipantId,Name, Surname, Contact, Age, Address, Email, Temperature, Symptoms, Exposure};
                DefaultTableModel tblModel = (DefaultTableModel)tblTable.getModel();
                tblModel.addRow(tbData);
                
                }

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
            
             tblTable.setModel(DbUtils.resultSetToTableModel(rs));
        }
    }
    
    
    
    private void btnAddTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTableActionPerformed
        // TODO add your handling code here:
        
        mAddTable();
         
        
        
    }//GEN-LAST:event_btnAddTableActionPerformed

    private void txtContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContactActionPerformed

    private void txtSurnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSurnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSurnameActionPerformed

    private void txtTemperatureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTemperatureActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTemperatureActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        mReadDetails();
        mSetValuesInGUI();
        
        txtName.requestFocusInWindow();
        boolEdit=true;
        
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cboFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFirstNameActionPerformed

    }//GEN-LAST:event_cboFirstNameActionPerformed

    private void btnSubmit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmit1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSubmit1ActionPerformed

    
        
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
            java.util.logging.Logger.getLogger(frmUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmUser().setVisible(true);
            }});
            
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddTable;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton btnSubmit1;
    private javax.swing.JComboBox<String> cboFirstName;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem mnuExit;
    private javax.swing.JMenuItem mnuRegistration;
    private javax.swing.JMenuItem mnuSignOut;
    private javax.swing.JTable tblTable;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtEmailAddress;
    private javax.swing.JTextField txtExposure;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSurname;
    private javax.swing.JTextField txtSymptom;
    private javax.swing.JTextField txtTemperature;
    // End of variables declaration//GEN-END:variables
        
        }
