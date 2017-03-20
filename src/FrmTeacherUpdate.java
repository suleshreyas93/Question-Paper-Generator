/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 * 
 * 
 */
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;
import java.sql.*;
import java.util.Vector;
import mypack.*;
public class FrmTeacherUpdate extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmTeacherUpdate
     */
     String fnm,lnm,doj,contact,email,addr,tchr_id[],sql,sql1,temp,sub[],str_id,username;//username-currently logged in user
        int i=0,id=0,wish,tid,sub_id[];
        JTable tb;
        Vector v;
         String []snm1;
        boolean flag=false;
        
          public void showdetails()
    {
        if(connect_me.role.equalsIgnoreCase("admin"))
        {
            try
        {
            connect_me.st1=connect_me.con1.createStatement();
            sql1="select count(firstnm) from teachermaster";
            connect_me.rs1=connect_me.st1.executeQuery(sql1);
            while(connect_me.rs1.next())
            {
                id=connect_me.rs1.getInt(1);
                
            }
            tchr_id=new String[id];
            connect_me.st1.close();
            flag=true;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.toString());
        }
            try
            {
                connect_me.st1=connect_me.con1.createStatement();
                sql1="select firstnm,lastnm from teachermaster";
                connect_me.rs1=connect_me.st1.executeQuery(sql1);
                while(connect_me.rs1.next())
                {
                
                    fnm=connect_me.rs1.getString(1);
                    lnm=connect_me.rs1.getString(2);
                    temp=fnm+" "+lnm;
                    tchrnm.addItem(temp);
                    tchrnm1.addItem(temp);
               
                }
                tchrnm.setSelectedIndex(-1);
                tchrnm1.setSelectedIndex(-1);
                connect_me.st1.close();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(rootPane, e.toString());
            }
        
            try
           {
                i=0;
                connect_me.st1=connect_me.con1.createStatement();
                sql1="select tchr_Id from teachermaster";
                connect_me.rs1=connect_me.st1.executeQuery(sql1);
                while(connect_me.rs1.next())
                {
                    tchr_id[i]=connect_me.rs1.getString(1);
                    i++;
                }
                connect_me.st1.close();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(rootPane, e.toString());
            }
        }
        else if(connect_me.role.equalsIgnoreCase("teacher"))
        {
            try
            {
                connect_me.st1=connect_me.con1.createStatement();
                sql1="select firstnm,lastnm from teachermaster where tchr_id='"+connect_me.teachr_id+"'";
                connect_me.rs1=connect_me.st1.executeQuery(sql1);
                flag=false;
                if(connect_me.rs1.next())
                {
                
                    fnm=connect_me.rs1.getString(1);
                    lnm=connect_me.rs1.getString(2);
                    temp=fnm+" "+lnm;
                    tchrnm.addItem(temp);
                    tchrnm1.addItem(temp);
               
                }
                tchrnm.setEnabled(false);
                tchrnm1.setEnabled(false);
                connect_me.st1.close();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(rootPane, e.toString());
            }
            
            try
        {
            if(tchrnm.getSelectedIndex()!=-1 && flag==false)
            {
                connect_me.st=connect_me.con.createStatement();
                sql="select DOJ,contact,email_id,addr from teachermaster where tchr_id='"+connect_me.teachr_id+"'";

                connect_me.rs=connect_me.st.executeQuery(sql);
                DOJ.setEditable(false);
                if(connect_me.rs.next())
                {
                  
                    temp=connect_me.rs.getString(1);
                    contact=connect_me.rs.getString(2);
                    email=connect_me.rs.getString(3);
                    addr=connect_me.rs.getString(4);

                    DOJ.setText(temp.substring(0, 11));
                    Contact.setText(contact);
                    email_id.setText(email);
                    address.setText(addr);
                }
                connect_me.st.close();
            }

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.toString());
        }

        }
        
        
        
        
    }
          public FrmTeacherUpdate(String unm)
          {
              initComponents();
              connect_me.connect_all();
              
              
              Image im= Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/abstract-pastel-blue-white-background-design-34948659.jpg"));
      
        Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
        Image j= im.getScaledInstance(dim.width,dim.height, Image.SCALE_DEFAULT);
        ImageIcon img = new ImageIcon(j);
        
        jLabel13.setIcon(img);
        jLabel13.setSize(DisplayMenu.jDesktopPane1.getWidth(), DisplayMenu.jDesktopPane1.getHeight());
        
        jLabel12.setIcon(img);
        jLabel12.setSize(DisplayMenu.jDesktopPane1.getWidth(), DisplayMenu.jDesktopPane1.getHeight());
              username=unm;
              
              //connect_me.teachr_id=unm;
              showdetails();
        
        try
        {
            connect_me.st=connect_me.con.createStatement();
            sql="select stream_name from stream";
            connect_me.rs=connect_me.st.executeQuery(sql);
            while(connect_me.rs.next())
            {
                stcombo.addItem(connect_me.rs.getString(1));
            }
            connect_me.st.close();
            stcombo.setSelectedIndex(-1);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.toString());
        }
          }
    public FrmTeacherUpdate() {
        initComponents();
        
        connect_me.connect_all();       
       
        /*
        showdetails();
        
        try
        {
            connect_me.st=connect_me.con.createStatement();
            sql="select stream_name from stream";
            connect_me.rs=connect_me.st.executeQuery(sql);
            while(connect_me.rs.next())
            {
                stcombo.addItem(connect_me.rs.getString(1));
            }
            connect_me.st.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.toString());
        }*/
    }
    public void clearpd()
    {
        tchrnm.setSelectedIndex(-1);
        DOJ.setText("");
        Contact.setText("");
        email_id.setText("");
        address.setText("");
        //System.out.println("Shreyas");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        UpdateTab = new javax.swing.JTabbedPane();
        Pdtab = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tchrnm = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        DOJ = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Contact = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        email_id = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnupdate = new javax.swing.JButton();
        del = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        address = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        subtab = new javax.swing.JPanel();
        updatesub = new javax.swing.JButton();
        btnDel1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        stcombo = new javax.swing.JComboBox();
        semcombo = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        subjects = new javax.swing.JList();
        jLabel9 = new javax.swing.JLabel();
        tchrnm1 = new javax.swing.JComboBox();
        btndet = new javax.swing.JButton();
        jScrollBar1 = new javax.swing.JScrollBar();
        jLabel12 = new javax.swing.JLabel();

        jLabel10.setText("jLabel10");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Update Teacher Details");
        setOpaque(true);
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);

        UpdateTab.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        UpdateTab.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N

        Pdtab.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel1.setText("Select Teacher");
        Pdtab.add(jLabel1);
        jLabel1.setBounds(10, 30, 130, 16);

        tchrnm.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        tchrnm.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tchrnmItemStateChanged(evt);
            }
        });
        Pdtab.add(tchrnm);
        tchrnm.setBounds(190, 20, 270, 40);

        jLabel2.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel2.setText("Date Of Joining");
        Pdtab.add(jLabel2);
        jLabel2.setBounds(10, 90, 120, 20);

        DOJ.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        Pdtab.add(DOJ);
        DOJ.setBounds(190, 80, 210, 40);

        jLabel3.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel3.setText("Contact");
        Pdtab.add(jLabel3);
        jLabel3.setBounds(10, 140, 80, 30);

        Contact.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        Pdtab.add(Contact);
        Contact.setBounds(190, 140, 160, 30);

        jLabel4.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel4.setText("Email id");
        Pdtab.add(jLabel4);
        jLabel4.setBounds(10, 190, 100, 30);

        email_id.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        Pdtab.add(email_id);
        email_id.setBounds(190, 180, 350, 40);

        jLabel5.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel5.setText("Address");
        Pdtab.add(jLabel5);
        jLabel5.setBounds(10, 240, 70, 30);

        btnupdate.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        btnupdate.setText("Update");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });
        Pdtab.add(btnupdate);
        btnupdate.setBounds(150, 400, 120, 40);

        del.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        del.setText("Delete");
        del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delActionPerformed(evt);
            }
        });
        Pdtab.add(del);
        del.setBounds(340, 400, 130, 40);

        address.setColumns(20);
        address.setRows(5);
        jScrollPane1.setViewportView(address);

        Pdtab.add(jScrollPane1);
        jScrollPane1.setBounds(190, 240, 350, 90);

        jLabel13.setText("jLabel13");
        Pdtab.add(jLabel13);
        jLabel13.setBounds(0, 0, 600, 640);

        UpdateTab.addTab("Personal Details", Pdtab);

        subtab.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        subtab.setLayout(null);

        updatesub.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        updatesub.setText("Update");
        updatesub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatesubActionPerformed(evt);
            }
        });
        subtab.add(updatesub);
        updatesub.setBounds(130, 560, 170, 40);

        btnDel1.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        btnDel1.setText("Delete");
        btnDel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDel1ActionPerformed(evt);
            }
        });
        subtab.add(btnDel1);
        btnDel1.setBounds(350, 560, 170, 40);

        jTable1.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, java.awt.Color.black));
        jTable1.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Teacher Id", "Stream ", "Subject", "Semester"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        subtab.add(jScrollPane2);
        jScrollPane2.setBounds(70, 70, 450, 120);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel6.setText("Select Stream");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(60, 50, 90, 30);

        stcombo.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        stcombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                stcomboItemStateChanged(evt);
            }
        });
        jPanel1.add(stcombo);
        stcombo.setBounds(200, 40, 210, 30);

        semcombo.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        semcombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                semcomboItemStateChanged(evt);
            }
        });
        jPanel1.add(semcombo);
        semcombo.setBounds(200, 90, 110, 30);

        jLabel7.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel7.setText("Select Semester");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(60, 110, 110, 20);

        jLabel8.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel8.setText("Subjects");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(60, 170, 70, 20);

        subjects.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jScrollPane3.setViewportView(subjects);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(200, 140, 300, 170);

        subtab.add(jPanel1);
        jPanel1.setBounds(30, 220, 550, 330);

        jLabel9.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel9.setText("Select Teacher");
        subtab.add(jLabel9);
        jLabel9.setBounds(30, 20, 130, 16);

        tchrnm1.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        tchrnm1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tchrnm1ItemStateChanged(evt);
            }
        });
        subtab.add(tchrnm1);
        tchrnm1.setBounds(140, 10, 270, 40);

        btndet.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        btndet.setText("Show Details");
        btndet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndetActionPerformed(evt);
            }
        });
        subtab.add(btndet);
        btndet.setBounds(420, 10, 120, 40);
        subtab.add(jScrollBar1);
        jScrollBar1.setBounds(830, 0, 17, 710);

        jLabel12.setText("jLabel12");
        subtab.add(jLabel12);
        jLabel12.setBounds(0, 0, 610, 640);

        UpdateTab.addTab("Subject Details", subtab);

        getContentPane().add(UpdateTab, java.awt.BorderLayout.CENTER);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-625)/2, (screenSize.height-701)/2, 625, 701);
    }// </editor-fold>//GEN-END:initComponents

    private void tchrnmItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tchrnmItemStateChanged
        // TODO add your handling code here:

        try
        {
            if(tchrnm.getSelectedIndex()!=-1 && flag==true)
            {
                connect_me.st=connect_me.con.createStatement();
                sql="select DOJ,contact,email_id,addr from teachermaster where tchr_id='"+tchr_id[tchrnm.getSelectedIndex()]+"'";

                connect_me.rs=connect_me.st.executeQuery(sql);
                DOJ.setEditable(false);
                if(connect_me.rs.next())
                {
                  
                    temp=connect_me.rs.getString(1);
                    contact=connect_me.rs.getString(2);
                    email=connect_me.rs.getString(3);
                    addr=connect_me.rs.getString(4);

                    DOJ.setText(temp.substring(0, 11));
                    Contact.setText(contact);
                    email_id.setText(email);
                    address.setText(addr);
                }
                connect_me.st.close();
            }

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.toString());
        }

    }//GEN-LAST:event_tchrnmItemStateChanged

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
        if(tchrnm.getSelectedIndex()==-1)
        {
            JOptionPane.showMessageDialog(rootPane, "Select teacher");//Array index out of bounds 
            return;
        }
        if(DOJ.getText().equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, "Enter Date Of Joining");
            DOJ.grabFocus();
            return;
        }
        if(Contact.getText().equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, "Enter valid contact no.");
            Contact.grabFocus();
            return;

        }
        if(email_id.getText().equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, "Enter valid email id");
            email_id.grabFocus();
            return;
        }
        if(address.getText().equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, "Enter valid address");
            address.grabFocus();
            return;
        }
        if(connect_me.contact(Contact.getText())==false)
        {
            JOptionPane.showMessageDialog(rootPane,"Invalid Contact");
            Contact.grabFocus();
            return;
        }
        
        /*
        if(contact.equals(Contact.getText()) && email.equals(email_id.getText()) && addr.equals(address.getText()))
        {
            wish=JOptionPane.showConfirmDialog(rootPane, "Do you wish to update with the same details?");
            if(wish==0)
            {
                JOptionPane.showMessageDialog(rootPane,"Details updated successfully");
                clearpd();

                try
                {
                    UpdateTab.setSelectedIndex(1);
                    i=0;
                    connect_me.st= connect_me.con.createStatement();
                    sql="select t.teacher_id,st.stream_name,sub.sub_name,t.semester from teacher_subject t,subject sub,stream st where t.sub_id=sub.sub_id and t.stream_id=st.stream_id and t.teacher_id='"+tchr_id[tid]+"'";
                    connect_me.rs= connect_me.st.executeQuery(sql);

                    while(connect_me.rs.next())
                    {

                        jTable1.setValueAt(connect_me.rs.getString(1), i, 0);
                        jTable1.setValueAt(connect_me.rs.getString(2), i, 1);
                        jTable1.setValueAt(connect_me.rs.getString(3), i, 2);
                        jTable1.setValueAt(connect_me.rs.getString(4), i, 3);

                        i++;

                    }
                    connect_me.st.close();
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(rootPane, e.toString());
                }

            }
            else if(wish==1)
            {
                Contact.grabFocus();
            }
        }
        else*/
        {
            try
            {
                connect_me.st=connect_me.con.createStatement();
                sql="update teachermaster set contact='"+Contact.getText()+"',email_id='"+email_id.getText()+"',addr='"+address.getText()+"' where tchr_id='"+connect_me.teachr_id+"' ";
                connect_me.st.executeUpdate(sql);
                JOptionPane.showMessageDialog(rootPane, tchrnm.getSelectedItem()+" Updated Successfully");
                clearpd();
                UpdateTab.setSelectedIndex(1);
                /*i=0;
                connect_me.st= connect_me.con.createStatement();
                sql="select t.teacher_id,st.stream_name,sub.sub_name,t.semester from teacher_subject t,subject sub,stream st where t.sub_id=sub.sub_id and t.stream_id=st.stream_id and t.teacher_id='"+tchr_id[tid]+"'";
                connect_me.rs= connect_me.st.executeQuery(sql);

                while(connect_me.rs.next())
                {

                    jTable1.setValueAt(connect_me.rs.getString(1), i, 0);
                    jTable1.setValueAt(connect_me.rs.getString(2), i, 1);
                    jTable1.setValueAt(connect_me.rs.getString(3), i, 2);
                    jTable1.setValueAt(connect_me.rs.getString(4), i, 3);
                    i++;

                }*/
                connect_me.st.close();

            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(rootPane, e.toString());
            }
        }
    }//GEN-LAST:event_btnupdateActionPerformed

    private void delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delActionPerformed
        // TODO add your handling code here:
        tid=tchrnm.getSelectedIndex();

        try
        {
            if(tchrnm.getSelectedIndex()==-1)
        {
            JOptionPane.showMessageDialog(rootPane, "Select teacher");
            return;
        }
        if(DOJ.getText().equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, "Enter Date Of Joining");
            DOJ.grabFocus();
            return;
        }
        if(Contact.getText().equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, "Enter valid contact no.");
            Contact.grabFocus();
            return;

        }
        if(email_id.getText().equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, "Enter valid email id");
            email_id.grabFocus();
            return;
        }
        if(address.getText().equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, "Enter valid address");
            address.grabFocus();
            return;
        }
        if(connect_me.contact(Contact.getText())==false)
        {
            JOptionPane.showMessageDialog(rootPane,"Invalid Contact");
            Contact.grabFocus();
            return;
        }

            connect_me.st1=connect_me.con1.createStatement();
            sql="delete from teachermaster where tchr_id='"+tchr_id[tid]+"'";
            connect_me.st1.executeUpdate(sql);
            connect_me.st1.close();

            connect_me.st1=connect_me.con1.createStatement();
            sql="delete from login where userid='"+tchr_id[tid]+"'";
            connect_me.st1.executeUpdate(sql);
            connect_me.st1.close();

            JOptionPane.showMessageDialog(rootPane, "Records deleted successfully.");
            clearpd();

            tchrnm.removeAllItems();
            tchrnm1.removeAllItems();
            //tchrnm.setSelectedIndex(0);

            showdetails();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.toString());
        }

    }//GEN-LAST:event_delActionPerformed

    private void updatesubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatesubActionPerformed
        // TODO add your handling code here:
       
        int sub_index[]=subjects.getSelectedIndices();
        int wish2;
        int sid[],k=0;
        boolean flag=false;

        try
        {
            if(connect_me.role.equalsIgnoreCase("Admin"))
            {
                if(tchrnm1.getSelectedIndex()==-1)
                {
                    JOptionPane.showMessageDialog(rootPane, "Please select a teacher.");
                    return;
                }
                tid=tchrnm1.getSelectedIndex();
                Object []subnm= subjects.getSelectedValues();
                snm1=new String[subnm.length];
                for(i=0;i<subnm.length;i++)
                {   
                    snm1[i]= (String)subnm[i];
            
                }
                connect_me.teachr_id=tchr_id[tid];
                connect_me.st=connect_me.con.createStatement();
                String str="select count(sub_id) from teacher_subject where teacher_id='"+connect_me.teachr_id+"'";
            connect_me.rs=connect_me.st.executeQuery(str);
            if(connect_me.rs.next())
            {
                k=connect_me.rs.getInt(1);
            }
            sid=new int[k];
            
            
            connect_me.st.close();
            
            
            int c=0;
            connect_me.st=connect_me.con.createStatement();
            String str1="select (sub_id) from teacher_subject where teacher_id='"+connect_me.teachr_id+"'";
            connect_me.rs=connect_me.st.executeQuery(str1);
            while(connect_me.rs.next())
            {
                sid[c]=connect_me.rs.getInt(1);
                c++;
            }
            
            
            
            
            connect_me.st.close();
            
            if(stcombo.getSelectedIndex()==-1)
            {
                JOptionPane.showMessageDialog(this, "Please select Stream");
                return;
            }
            if(semcombo.getSelectedIndex()==-1)
            {
                JOptionPane.showMessageDialog(this, "Please select Semester");
                return;
            }
            if(sub_index.length<=0)
            {
                JOptionPane.showMessageDialog(this, "Please select Subjects");
                return;
            }
            
            

            connect_me.st1=connect_me.con1.createStatement();
            sql="select stream_id from stream where stream_name='"+stcombo.getSelectedItem()+"'";
            connect_me.rs1=connect_me.st1.executeQuery(sql);
            if(connect_me.rs1.next())
            {
                str_id=connect_me.rs1.getString(1);
            }

            wish=JOptionPane.showConfirmDialog(rootPane, "Do you want to update with existing records?");
            if(wish==0)
            {
                for(int list=0;list<sub_index.length;list++)
            {
                for(int dblist=0;dblist<sid.length;dblist++)
                {
                    if(sub_id[sub_index[list]]==sid[dblist])
                    {
                        flag=true;
                        //JOptionPane.showMessageDialog(rootPane, tchrnm1.getSelectedItem()+ " does not teach "+snm[list]+" subject");
                        break;
                    }
                    else
                    {
                        flag=false;
                    }
                    
                    
                    
                }
                if(flag==true)
                    {
                        JOptionPane.showMessageDialog(rootPane, tchrnm1.getSelectedItem()+ " teaches "+snm1[list]+" subject.Please select other subjects");
                        return;
                    }
                    
            }
                for( i=0;i<sub_index.length;i++)
                {
                    try
                    {
                        connect_me.st=connect_me.con.createStatement();
                        sql="insert into teacher_subject values('"+connect_me.teachr_id+"','"+str_id+"','"+sub_id[sub_index[i]]+"','"+semcombo.getSelectedItem()+"') ";
                        connect_me.st.executeUpdate(sql);
                        connect_me.st.close();

                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(rootPane, e.toString());
                    }
                }
                JOptionPane.showMessageDialog(rootPane, "Details Updated Successfully.");
                int r=jTable1.getRowCount();
            for(int j=0;j<r;j++)
            {
                jTable1.setValueAt("", j, 0);
                jTable1.setValueAt("", j, 1);
                jTable1.setValueAt("", j, 2);
                jTable1.setValueAt("", j, 3);
            }
            tchrnm1.setSelectedIndex(-1);
              stcombo.setSelectedIndex(-1);
            semcombo.setSelectedIndex(-1);
            subjects.setListData(new String[0]);
               
            }
            else if(wish==1)
            {
                connect_me.st=connect_me.con.createStatement();
                sql="delete from teacher_subject where teacher_id='"+connect_me.teachr_id+"'";
                connect_me.st.executeUpdate(sql);
                for( i=0;i< sub_index.length;i++)
                {
                    try
                    {
                        connect_me.st=connect_me.con.createStatement();
                        sql="insert into teacher_subject values('"+connect_me.teachr_id+"','"+str_id+"','"+sub_id[sub_index[i]]+"','"+semcombo.getSelectedItem()+"') ";
                        connect_me.st.executeUpdate(sql);
                        connect_me.st.close();

                    }

                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(rootPane, e.toString());
                    }
                }
                JOptionPane.showMessageDialog(rootPane, "Details Updated Successfully.");
                int r=jTable1.getRowCount();
            for(int j=0;j<r;j++)
            {
                jTable1.setValueAt("", j, 0);
                jTable1.setValueAt("", j, 1);
                jTable1.setValueAt("", j, 2);
                jTable1.setValueAt("", j, 3);
            }
            tchrnm1.setSelectedIndex(-1);
              stcombo.setSelectedIndex(-1);
            semcombo.setSelectedIndex(-1);
            subjects.setListData(new String[0]);
            }
            }
            else if(connect_me.role.equalsIgnoreCase("Teacher"))
            {
                Object []subnm= subjects.getSelectedValues();
                snm1=new String[subnm.length];
                for(i=0;i<subnm.length;i++)
                {   
                    snm1[i]= (String)subnm[i];
            
                }
                //connect_me.teachr_id=tchr_id[tid];
                connect_me.st=connect_me.con.createStatement();
                String str="select count(sub_id) from teacher_subject where teacher_id='"+connect_me.teachr_id+"'";
            connect_me.rs=connect_me.st.executeQuery(str);
            if(connect_me.rs.next())
            {
                k=connect_me.rs.getInt(1);
            }
            sid=new int[k];
            
            
            connect_me.st.close();
            
            
            int c=0;
            connect_me.st=connect_me.con.createStatement();
            String str1="select (sub_id) from teacher_subject where teacher_id='"+connect_me.teachr_id+"'";
            connect_me.rs=connect_me.st.executeQuery(str1);
            while(connect_me.rs.next())
            {
                sid[c]=connect_me.rs.getInt(1);
                c++;
            }
            
            
            
            
            connect_me.st.close();
            if(stcombo.getSelectedIndex()==-1)
            {
                JOptionPane.showMessageDialog(this, "Please select Stream");
                return;
            }
            if(semcombo.getSelectedIndex()==-1)
            {
                JOptionPane.showMessageDialog(this, "Please select Semester");
                return;
            }
            if(sub_index.length<=0)
            {
                JOptionPane.showMessageDialog(this, "Please select Subjects");
                return;
            }
            
            

            connect_me.st1=connect_me.con1.createStatement();
            sql="select stream_id from stream where stream_name='"+stcombo.getSelectedItem()+"'";
            connect_me.rs1=connect_me.st1.executeQuery(sql);
            if(connect_me.rs1.next())
            {
                str_id=connect_me.rs1.getString(1);
            }

            wish=JOptionPane.showConfirmDialog(rootPane, "Do you want to update with existing records?");
            if(wish==0)
            {
                for(int list=0;list<sub_index.length;list++)
            {
                for(int dblist=0;dblist<sid.length;dblist++)
                {
                    if(sub_id[sub_index[list]]==sid[dblist])
                    {
                        flag=true;
                        //JOptionPane.showMessageDialog(rootPane, tchrnm1.getSelectedItem()+ " does not teach "+snm[list]+" subject");
                        break;
                    }
                    else
                    {
                        flag=false;
                    }
                    
                    
                    
                }
                if(flag==true)
                    {
                        JOptionPane.showMessageDialog(rootPane, tchrnm1.getSelectedItem()+ " teaches "+snm1[list]+" subject.Please select other subjects");
                        return;
                    }
                    
            }
                for( i=0;i<sub_index.length;i++)
                {
                    try
                    {
                        connect_me.st=connect_me.con.createStatement();
                        sql="insert into teacher_subject values('"+connect_me.teachr_id+"','"+str_id+"','"+sub_id[sub_index[i]]+"','"+semcombo.getSelectedItem()+"') ";
                        connect_me.st.executeUpdate(sql);
                        connect_me.st.close();

                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(rootPane, e.toString());
                    }
                }
                JOptionPane.showMessageDialog(rootPane, "Details Updated Successfully.");
                int r=jTable1.getRowCount();
            for(int j=0;j<r;j++)
            {
                jTable1.setValueAt("", j, 0);
                jTable1.setValueAt("", j, 1);
                jTable1.setValueAt("", j, 2);
                jTable1.setValueAt("", j, 3);
            }
            //tchrnm1.setSelectedIndex(-1);
              stcombo.setSelectedIndex(-1);
            semcombo.setSelectedIndex(-1);
            subjects.setListData(new String[0]);
               
            }
            else if(wish==1)
            {
                connect_me.st=connect_me.con.createStatement();
                sql="delete from teacher_subject where teacher_id='"+connect_me.teachr_id+"'";
                connect_me.st.executeUpdate(sql);
                for( i=0;i< sub_index.length;i++)
                {
                    try
                    {
                        connect_me.st=connect_me.con.createStatement();
                        sql="insert into teacher_subject values('"+connect_me.teachr_id+"','"+str_id+"','"+sub_id[sub_index[i]]+"','"+semcombo.getSelectedItem()+"') ";
                        connect_me.st.executeUpdate(sql);
                        connect_me.st.close();

                    }

                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(rootPane, e.toString());
                    }
                }
                JOptionPane.showMessageDialog(rootPane, "Details Updated Successfully.");
                int r=jTable1.getRowCount();
            for(int j=0;j<r;j++)
            {
                jTable1.setValueAt("", j, 0);
                jTable1.setValueAt("", j, 1);
                jTable1.setValueAt("", j, 2);
                jTable1.setValueAt("", j, 3);
            }
            tchrnm1.setSelectedIndex(-1);
              stcombo.setSelectedIndex(-1);
            semcombo.setSelectedIndex(-1);
            subjects.setListData(new String[0]);
            }
            
            }
            
        
                
            

        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(rootPane, ex.toString());
        }
    }//GEN-LAST:event_updatesubActionPerformed

    private void btnDel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDel1ActionPerformed
        // TODO add your handling code here:
                int sid[];
                 int k=0;
        boolean flag=false;
        int s[]=subjects.getSelectedIndices();
        Object []subnm= subjects.getSelectedValues();
        String []snm= new String[subnm.length];
        for(i=0;i<subnm.length;i++)
        {
            snm[i]= (String)subnm[i];
            
        }
        try
        {
            if(connect_me.role.equalsIgnoreCase("Admin"))
            {
                if(tchrnm1.getSelectedIndex()==-1)
                {
                    JOptionPane.showMessageDialog(rootPane, "Please select a teacher.");
                    return;
                }
                if(stcombo.getSelectedIndex()==-1)
            {
                JOptionPane.showMessageDialog(rootPane, "Select Stream");
                return;
            }
            if(semcombo.getSelectedIndex()==-1)
            {
                JOptionPane.showMessageDialog(rootPane, "Select semester");
                return;
            }
            if(s.length<=0)
            {
                JOptionPane.showMessageDialog(rootPane, "Select subject");
                return;
            }
            connect_me.st=connect_me.con.createStatement();
            String str="select count(sub_id) from teacher_subject where teacher_id='"+tchr_id[tid]+"'";
            connect_me.rs=connect_me.st.executeQuery(str);
            if(connect_me.rs.next())
            {
                k=connect_me.rs.getInt(1);
            }
            sid=new int[k];
            
            
            connect_me.st.close();
            
            
            int c=0;
             connect_me.st=connect_me.con.createStatement();
            String str1="select (sub_id) from teacher_subject where teacher_id='"+tchr_id[tid]+"'";
            connect_me.rs=connect_me.st.executeQuery(str1);
            while(connect_me.rs.next())
            {
                sid[c]=connect_me.rs.getInt(1);
                c++;
            }
            
            
            
            
            connect_me.st.close();
            
            for(int list=0;list<s.length;list++)
            {
                for(int dblist=0;dblist<sid.length;dblist++)
                {
                    if(sub_id[s[list]]==sid[dblist])
                    {
                        flag=true;
                        //JOptionPane.showMessageDialog(rootPane, tchrnm1.getSelectedItem()+ " does not teach "+snm[list]+" subject");
                        break;
                    }
                    else
                    {
                        flag=false;
                    }
                    
                    
                    
                }
                if(flag==false)
                    {
                        JOptionPane.showMessageDialog(rootPane, tchrnm1.getSelectedItem()+ " does not teach "+snm[list]+" subject");
                    }
                    
            }
            if(flag==true)
            {
                for(int i=0;i<s.length;i++)
                {
                    connect_me.st1=connect_me.con1.createStatement();
                    sql="delete from teacher_subject where teacher_id='"+tchr_id[tid]+"' and sub_id='"+sub_id[s[i]]+"'";
                    connect_me.st1.executeUpdate(sql);
                    connect_me.st1.close();
                }
                

                JOptionPane.showMessageDialog(rootPane, "Records deleted successfully.");
                stcombo.setSelectedIndex(-1);
                semcombo.setSelectedIndex(-1);
                subjects.setListData(new String[0]);
            
            //JOptionPane.showMessageDialog(rootPane, "Details Updated Successfully.");
                int r=jTable1.getRowCount();
            for(int j=0;j<r;j++)
            {
                jTable1.setValueAt("", j, 0);
                jTable1.setValueAt("", j, 1);
                jTable1.setValueAt("", j, 2);
                jTable1.setValueAt("", j, 3);
            }
            tchrnm1.setSelectedIndex(-1);
            }
            }
            else if(connect_me.role.equalsIgnoreCase("Teacher"))
            {
                if(stcombo.getSelectedIndex()==-1)
            {
                JOptionPane.showMessageDialog(rootPane, "Select Stream");
                return;
            }
            if(semcombo.getSelectedIndex()==-1)
            {
                JOptionPane.showMessageDialog(rootPane, "Select semester");
                return;
            }
            if(s.length<=0)
            {
                JOptionPane.showMessageDialog(rootPane, "Select subject");
                return;
            }
            connect_me.st=connect_me.con.createStatement();
            String str="select count(sub_id) from teacher_subject where teacher_id='"+connect_me.teachr_id+"'";
            connect_me.rs=connect_me.st.executeQuery(str);
            if(connect_me.rs.next())
            {
                k=connect_me.rs.getInt(1);
            }
            sid=new int[k];
            
            
            connect_me.st.close();
            
            
            int c=0;
             connect_me.st=connect_me.con.createStatement();
            String str1="select (sub_id) from teacher_subject where teacher_id='"+connect_me.teachr_id+"'";
            connect_me.rs=connect_me.st.executeQuery(str1);
            while(connect_me.rs.next())
            {
                sid[c]=connect_me.rs.getInt(1);
                c++;
            }
            
            
            
            
            connect_me.st.close();
            
            for(int list=0;list<s.length;list++)
            {
                for(int dblist=0;dblist<sid.length;dblist++)
                {
                    if(sub_id[s[list]]==sid[dblist])
                    {
                        flag=true;
                        //JOptionPane.showMessageDialog(rootPane, tchrnm1.getSelectedItem()+ " does not teach "+snm[list]+" subject");
                        break;
                    }
                    else
                    {
                        flag=false;
                    }
                    
                    
                    
                }
                if(flag==false)
                    {
                        JOptionPane.showMessageDialog(rootPane, tchrnm1.getSelectedItem()+ " does not teach "+snm[list]+" subject");
                    }
                    
            }
            if(flag==true)
            {
                for(int i=0;i<s.length;i++)
                {
                    connect_me.st1=connect_me.con1.createStatement();
                    sql="delete from teacher_subject where teacher_id='"+connect_me.teachr_id+"' and sub_id='"+sub_id[s[i]]+"'";
                    connect_me.st1.executeUpdate(sql);
                    connect_me.st1.close();
                }
                

                JOptionPane.showMessageDialog(rootPane, "Records deleted successfully.");
                stcombo.setSelectedIndex(-1);
                semcombo.setSelectedIndex(-1);
                subjects.setListData(new String[0]);
            
            //JOptionPane.showMessageDialog(rootPane, "Details Updated Successfully.");
                int r=jTable1.getRowCount();
            for(int j=0;j<r;j++)
            {
                jTable1.setValueAt("", j, 0);
                jTable1.setValueAt("", j, 1);
                jTable1.setValueAt("", j, 2);
                jTable1.setValueAt("", j, 3);
            }
            //tchrnm1.setSelectedIndex(-1);
            }
            }
            
            
            
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.toString());
        }
    }//GEN-LAST:event_btnDel1ActionPerformed

    private void semcomboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_semcomboItemStateChanged
        // TODO add your handling code here:
        int j=0,k=0;
        try
        {

            if(stcombo.getSelectedIndex()!=-1)
            {

                if(semcombo.getSelectedIndex()!=-1)
                {

                    connect_me.st1=connect_me.con1.createStatement();
                    sql="select count(sub_name) from subject where semester='"+semcombo.getSelectedItem().toString()+"' and stream_id=(select stream_id from stream where stream_name='"+stcombo.getSelectedItem().toString()+"')";
                    connect_me.rs1=connect_me.st1.executeQuery(sql);
                    if(connect_me.rs1.next())
                    {

                        j=connect_me.rs1.getInt(1);
                    }
                    sub=new String[j];
                    sub_id=new int[j];
                    connect_me.st1.close();
                    sql="select sub_id,sub_name from subject where semester='"+semcombo.getSelectedItem().toString()+"'and stream_id=(select stream_id from stream where stream_name='"+stcombo.getSelectedItem().toString()+"')";

                    connect_me.st2= connect_me.con2.createStatement();
                    connect_me.rs1=connect_me.st2.executeQuery(sql);
                    while(connect_me.rs1.next())
                    {
                        sub_id[k]=connect_me.rs1.getInt(1);
                        sub[k]=connect_me.rs1.getString(2);

                        k++;
                    }

                    subjects.setListData(sub);
                }
            }

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.toString());
        }
    }//GEN-LAST:event_semcomboItemStateChanged

    private void tchrnm1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tchrnm1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tchrnm1ItemStateChanged

    private void btndetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndetActionPerformed
        // TODO add your handling code here:

        
        try
        {
            int r=jTable1.getRowCount();
            for(int j=0;j<r;j++)
            {
                jTable1.setValueAt("", j, 0);
                jTable1.setValueAt("", j, 1);
                jTable1.setValueAt("", j, 2);
                jTable1.setValueAt("", j, 3);
            }
            
            if(connect_me.role.equalsIgnoreCase("Admin"))
            {
                if(tchrnm1.getSelectedIndex()!=-1)
                {
                    tid=tchrnm1.getSelectedIndex();
                    i=0;
                    connect_me.st= connect_me.con.createStatement();
                    sql="select t.teacher_id,st.stream_name,sub.sub_name,t.semester from teacher_subject t,subject sub,stream st where t.sub_id=sub.sub_id and t.stream_id=st.stream_id and t.teacher_id='"+tchr_id[tid]+"'";
                    connect_me.rs= connect_me.st.executeQuery(sql);

                    while(connect_me.rs.next())
                    {

                        jTable1.setValueAt(connect_me.rs.getString(1), i, 0);
                        jTable1.setValueAt(connect_me.rs.getString(2), i, 1);
                        jTable1.setValueAt(connect_me.rs.getString(3), i, 2);
                        jTable1.setValueAt(connect_me.rs.getString(4), i, 3);

                        i++;

                    }
            
                    connect_me.st.close();
                }
                else
                {   
                    JOptionPane.showMessageDialog(rootPane, "Please select a teacher");
                    return;
                }
           
            }
            else if(connect_me.role.equalsIgnoreCase("Teacher"))
            {
                 i=0;
                    connect_me.st= connect_me.con.createStatement();
                    sql="select t.teacher_id,st.stream_name,sub.sub_name,t.semester from teacher_subject t,subject sub,stream st where t.sub_id=sub.sub_id and t.stream_id=st.stream_id and t.teacher_id='"+connect_me.teachr_id+"'";
                    connect_me.rs= connect_me.st.executeQuery(sql);

                    while(connect_me.rs.next())
                    {

                        jTable1.setValueAt(connect_me.rs.getString(1), i, 0);
                        jTable1.setValueAt(connect_me.rs.getString(2), i, 1);
                        jTable1.setValueAt(connect_me.rs.getString(3), i, 2);
                        jTable1.setValueAt(connect_me.rs.getString(4), i, 3);

                        i++;

                    }
            
                    connect_me.st.close();
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.toString());
        }
    }//GEN-LAST:event_btndetActionPerformed

    private void stcomboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_stcomboItemStateChanged
        // TODO add your handling code here:
        semcombo.removeAllItems();
        for(int a=1;a<=8;a++)
        {
            semcombo.addItem(a);
        }
        semcombo.setSelectedIndex(-1);
    }//GEN-LAST:event_stcomboItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Contact;
    private javax.swing.JTextField DOJ;
    private javax.swing.JPanel Pdtab;
    public javax.swing.JTabbedPane UpdateTab;
    private javax.swing.JTextArea address;
    private javax.swing.JButton btnDel1;
    private javax.swing.JButton btndet;
    private javax.swing.JButton btnupdate;
    private javax.swing.JButton del;
    private javax.swing.JTextField email_id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox semcombo;
    private javax.swing.JComboBox stcombo;
    private javax.swing.JList subjects;
    private javax.swing.JPanel subtab;
    private javax.swing.JComboBox tchrnm;
    private javax.swing.JComboBox tchrnm1;
    private javax.swing.JButton updatesub;
    // End of variables declaration//GEN-END:variables
}
