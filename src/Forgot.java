

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mypack.*;
import mypack.connect_me;

public class Forgot extends javax.swing.JFrame {

    /**
     * Creates new form Forgot
     */
     String unm,a,q="",pwd;
    public Forgot() {
        initComponents();
        connect_me.connect_all();
    }
    public Forgot(String user)
    {
         try {
             initComponents();
             connect_me.connect_all();
             unm=user;
             unmtxt.setText(user);
             
             connect_me.st=connect_me.con.createStatement();
             String sql="select * from login where userid='"+unm+"'";
             connect_me.rs=connect_me.st.executeQuery(sql);
             if(connect_me.rs.next())
             {
                 pwd=connect_me.rs.getString(2);
                 q=connect_me.rs.getString(3);
                 a=connect_me.rs.getString(4);
             }
             secq.setText(q);
             ans.grabFocus();
         }
            catch (SQLException ex) {
             Logger.getLogger(Forgot.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        unmtxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        secq = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ans = new javax.swing.JTextField();
        btnpass = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setLayout(null);
        jPanel1.add(jLabel1);
        jLabel1.setBounds(50, 100, 70, 0);

        jLabel2.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel2.setText("Username");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(80, 70, 90, 16);

        unmtxt.setEditable(false);
        unmtxt.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        unmtxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                unmtxtFocusLost(evt);
            }
        });
        jPanel1.add(unmtxt);
        unmtxt.setBounds(220, 60, 230, 30);

        jLabel3.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel3.setText("Security Question");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(80, 150, 120, 16);

        secq.setEditable(false);
        secq.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jPanel1.add(secq);
        secq.setBounds(220, 140, 240, 40);

        jLabel4.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel4.setText("Answer");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(80, 240, 90, 16);

        ans.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jPanel1.add(ans);
        ans.setBounds(220, 220, 240, 40);

        btnpass.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        btnpass.setText("Get Password");
        btnpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpassActionPerformed(evt);
            }
        });
        jPanel1.add(btnpass);
        btnpass.setBounds(170, 350, 140, 40);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/abstract-pastel-blue-white-background-design-34948659.jpg"))); // NOI18N
        jLabel5.setText("jLabel5");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(0, 0, 490, 470);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 490, 470);

        setSize(new java.awt.Dimension(508, 509));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void unmtxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_unmtxtFocusLost
        // TODO add your handling code here:
       
        
       
        
    }//GEN-LAST:event_unmtxtFocusLost

    private void btnpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpassActionPerformed
        // TODO add your handling code here:
        String answer=ans.getText();
        if(ans.equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, "Enter answer");
            ans.grabFocus();
            return;
        }
        if(answer.equalsIgnoreCase(a))
        {
            JOptionPane.showMessageDialog(rootPane, "Your password is "+pwd);
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, "Wrong Answer");
        }
        
    }//GEN-LAST:event_btnpassActionPerformed

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
            java.util.logging.Logger.getLogger(Forgot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Forgot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Forgot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Forgot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Forgot().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ans;
    private javax.swing.JButton btnpass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField secq;
    private javax.swing.JTextField unmtxt;
    // End of variables declaration//GEN-END:variables
}
