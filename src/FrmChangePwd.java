/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;
import mypack.*;
import java.sql.*;
public class FrmChangePwd extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmChangePwd
     */ String pass,user;
    public FrmChangePwd(String unm) {
        initComponents();
        
        connect_me.connect_all();
        
        Image i= Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/abstract-pastel-blue-white-background-design-34948659.jpg"));
      
        Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
        Image j= i.getScaledInstance(dim.width,dim.height, Image.SCALE_REPLICATE);
        ImageIcon img = new ImageIcon(j);
        
        jLabel4.setIcon(img);
        jLabel4.setSize(DisplayMenu.jDesktopPane1.getWidth(), DisplayMenu.jDesktopPane1.getHeight());
        /*try
        {
           
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.toString());
        }*/
        user=unm;
        unmtxt1.setText(unm);
        unmtxt1.setEnabled(false);
        panel2.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        panel2 = new javax.swing.JPanel();
        lbl2 = new javax.swing.JLabel();
        lbl1 = new javax.swing.JLabel();
        compasstxt = new javax.swing.JPasswordField();
        btnchng = new javax.swing.JButton();
        newpwdtxt = new javax.swing.JPasswordField();
        panel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        unmtxt1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        pwd = new javax.swing.JPasswordField();
        btnval = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Change password");
        setVisible(true);

        jPanel1.setLayout(null);

        panel2.setMinimumSize(new java.awt.Dimension(250, 110));
        panel2.setOpaque(false);
        panel2.setLayout(null);

        lbl2.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        lbl2.setText("Confirm Password");
        panel2.add(lbl2);
        lbl2.setBounds(10, 90, 120, 16);

        lbl1.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        lbl1.setText("New Password");
        panel2.add(lbl1);
        lbl1.setBounds(10, 20, 110, 16);

        compasstxt.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        panel2.add(compasstxt);
        compasstxt.setBounds(140, 80, 150, 40);

        btnchng.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        btnchng.setText("Change Password");
        btnchng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchngActionPerformed(evt);
            }
        });
        panel2.add(btnchng);
        btnchng.setBounds(90, 140, 150, 40);

        newpwdtxt.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        panel2.add(newpwdtxt);
        newpwdtxt.setBounds(140, 20, 150, 40);

        jPanel1.add(panel2);
        panel2.setBounds(20, 240, 310, 200);

        panel3.setOpaque(false);
        panel3.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel5.setText("Username");
        panel3.add(jLabel5);
        jLabel5.setBounds(10, 10, 80, 20);

        unmtxt1.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        panel3.add(unmtxt1);
        unmtxt1.setBounds(100, 10, 160, 30);

        jLabel3.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel3.setText("Password");
        panel3.add(jLabel3);
        jLabel3.setBounds(10, 80, 80, 20);

        pwd.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        pwd.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pwdFocusLost(evt);
            }
        });
        panel3.add(pwd);
        pwd.setBounds(100, 80, 160, 30);

        btnval.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        btnval.setText("Validate");
        btnval.setOpaque(false);
        btnval.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvalActionPerformed(evt);
            }
        });
        panel3.add(btnval);
        btnval.setBounds(70, 150, 130, 40);

        jPanel1.add(panel3);
        panel3.setBounds(10, 50, 270, 240);

        jLabel4.setText("jLabel4");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(0, 0, 430, 480);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-439)/2, (screenSize.height-478)/2, 439, 478);
    }// </editor-fold>//GEN-END:initComponents

    private void btnchngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchngActionPerformed
        // TODO add your handling code here:
        String newpass,conpass;

        newpass= newpwdtxt.getText();
        conpass=compasstxt.getText();

        try
        {

            if(newpass.equals(""))
            {
                JOptionPane.showMessageDialog(rootPane, "Enter new password");
                newpwdtxt.grabFocus();
                return;
            }
            if(conpass.equals(""))
            {
                JOptionPane.showMessageDialog(rootPane, "Retype new password");
                compasstxt.grabFocus();
                return;
            }

            if(newpass.equals(conpass))
            {
                String sql1="update login set pwd='"+newpass+"' where userid='"+user+"'";
                connect_me.st=connect_me.con.createStatement();
                connect_me.st.executeUpdate(sql1);
                JOptionPane.showMessageDialog(rootPane, user+" Password Updated Successfully");

                //can change mre than once

                //unmtxt1.setText("");
                //pwd.setText("");
                newpwdtxt.setText("");
                compasstxt.setText("");

            }
            else
            {
                JOptionPane.showMessageDialog(rootPane, "Passwords Do Not Match");
                newpwdtxt.setText("");
                compasstxt.setText("");
                newpwdtxt.grabFocus();
            }

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.toString());
        }

    }//GEN-LAST:event_btnchngActionPerformed

    private void pwdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pwdFocusLost
        // TODO add your handling code here:

    }//GEN-LAST:event_pwdFocusLost

    private void btnvalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvalActionPerformed
        // TODO add your handling code here:
        //user=unmtxt1.getText();
        pass=pwd.getText();
        try
        {

            if(user.equals(""))
            {
                JOptionPane.showMessageDialog(rootPane, "Enter Username");
                unmtxt1.grabFocus();
                return;
            }
            if(pass.equals(""))
            {

                JOptionPane.showMessageDialog(rootPane, "Enter Password");
                pwd.grabFocus();
                return;

            }

            String sql="select userid,pwd from login where userid='"+user+"'";// and pwd='"+pass+"'";
            connect_me.st1=connect_me.con1.createStatement();
            connect_me.rs=connect_me.st1.executeQuery(sql);

            if(connect_me.rs.next())
            {
                if(pass.equals(connect_me.rs.getString(2)))
                {
                    panel2.setLocation(10, 60);
                    panel3.setVisible(false);
                    panel2.setVisible(true);
                    return;
                }
                else
                {
                    JOptionPane.showMessageDialog(rootPane, "Password invalid!");
                    pwd.setText("");
                    pwd.grabFocus();
                    return;
                }

            }
            JOptionPane.showMessageDialog(rootPane, "Enter valid username and password");
            unmtxt1.setText("");
            pwd.setText("");
            unmtxt1.grabFocus();
            connect_me.st1.close();
            connect_me.con1.close();

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.toString());
        }
    }//GEN-LAST:event_btnvalActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnchng;
    private javax.swing.JButton btnval;
    private javax.swing.JPasswordField compasstxt;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JPasswordField newpwdtxt;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel3;
    private javax.swing.JPasswordField pwd;
    private javax.swing.JTextField unmtxt1;
    // End of variables declaration//GEN-END:variables
}
