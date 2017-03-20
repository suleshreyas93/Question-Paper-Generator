/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
//import mypack.addimg;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;
import mypack.*;
import java.sql.*;

public class FrmQuestions extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmQuestions
     */
    int sid[],count=0,count1=0,q[],qid[],mainq,tot,uni,mark,s;
    String sql,subject,quest[],teacher_id="",sub_name="";
    boolean flag=false;
    String e_id;
    Dimension d;
    public FrmQuestions() {
        initComponents();
        
           
    }
    
    public FrmQuestions(String tid)//from display menu
    {
        initComponents();
        connect_me.connect_all();
        //teacher_id=tid;
        connect_me.teachr_id=tid;
        
        
        Image im= Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/abstract-pastel-blue-white-background-design-34948659.jpg"));
      
        Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
        Image j= im.getScaledInstance(dim.width,dim.height, Image.SCALE_DEFAULT);
        ImageIcon img = new ImageIcon(j);
        
        jLabel8.setIcon(img);
        jLabel8.setSize(DisplayMenu.jDesktopPane1.getWidth(), DisplayMenu.jDesktopPane1.getHeight());
        
        jLabel9.setIcon(img);
        jLabel9.setSize(DisplayMenu.jDesktopPane1.getWidth(), DisplayMenu.jDesktopPane1.getHeight());
        try
        {
            if(connect_me.role.equalsIgnoreCase("teacher"))
            {
                connect_me.st=connect_me.con.createStatement();
            sql="select count(s.sub_id) from subject s,teacher_subject t where t.teacher_id='"+connect_me.teachr_id+"' and t.sub_id=s.sub_id";
            connect_me.rs=connect_me.st.executeQuery(sql);
            if(connect_me.rs.next())
            {
                count=connect_me.rs.getInt(1);
            }
            sid=new int[count];
            connect_me.st.close();
            
            connect_me.st1=connect_me.con1.createStatement();
            sql="select s.sub_name,s.sub_id from subject s,teacher_subject t where t.teacher_id='"+connect_me.teachr_id+"' and t.sub_id=s.sub_id";
            connect_me.rs1=connect_me.st1.executeQuery(sql);
            
            int i=0;
            while(connect_me.rs1.next())
            {
                
                subject=connect_me.rs1.getString(1);
                subjects.addItem(subject);
                sid[i]=connect_me.rs1.getInt(2);
               // JOptionPane.showMessageDialog(rootPane, sid[i]+" "+i);
                sub.addItem(subject);             
                i++;
            }
            sub.setSelectedIndex(-1);
            subjects.setSelectedIndex(-1);
            unit.setSelectedIndex(-1);
            
            marks.removeAllItems();
            marks.addItem(5);
            marks.addItem(10);
            marks.addItem(20);
            marks.setSelectedIndex(-1);
            
            connect_me.st1.close();
            }
            else if(connect_me.role.equalsIgnoreCase("Admin"))
            {
                connect_me.st=connect_me.con.createStatement();
            sql="select count(s.sub_id) from subject s";
            connect_me.rs=connect_me.st.executeQuery(sql);
            if(connect_me.rs.next())
            {
                count=connect_me.rs.getInt(1);
            }
            sid=new int[count];
            connect_me.st.close();
            
            connect_me.st1=connect_me.con1.createStatement();
            sql="select s.sub_name,s.sub_id from subject s";
            connect_me.rs1=connect_me.st1.executeQuery(sql);
            
            int i=0;
            while(connect_me.rs1.next())
            {
                
                subject=connect_me.rs1.getString(1);
                subjects.addItem(subject);
                sid[i]=connect_me.rs1.getInt(2);
               
                sub.addItem(subject);             
                i++;
            }
            sub.setSelectedIndex(-1);
            subjects.setSelectedIndex(-1);
            unit.setSelectedIndex(-1);
            
            marks.removeAllItems();
            marks.addItem(5);
            marks.addItem(10);
            marks.addItem(20);
            marks.setSelectedIndex(-1);
            connect_me.st1.close();
            }
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.toString());
        }
    }
    public FrmQuestions(int sub_id,int u,int m,int mainQ,String eid,int ttl)//<-- insufficent question in the dtabase
    {
        flag=true;
        initComponents();
        connect_me.connect_all();
        s=sub_id;
        mainq=mainQ;
        uni=u;
        mark=m;
        e_id= eid;
        tot=ttl;
        
        try
        {
            connect_me.st=connect_me.con.createStatement();
            connect_me.rs=connect_me.st.executeQuery("select sub_name from subject where sub_id="+s+"");
            if(connect_me.rs.next())
            {
                sub_name=connect_me.rs.getString(1);
            }
            subjects.addItem(sub_name);
            connect_me.st.close();
        }
        catch(Exception e)
        {
            
        }
        
        
        units.addItem(uni);
        marks.removeAllItems();
        marks.addItem(mark);
        
        subjects.setEnabled(false);
        units.setEnabled(false);
        
        marks.setEnabled(false);
        
    }
    public void clearall()
    {
        //subjects.removeAllItems();
        subjects.setSelectedIndex(-1);
        //units.removeAllItems();
        units.setSelectedIndex(-1);
        addquest.setText("");
        marks.setSelectedIndex(-1);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addel = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        addQ = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        subjects = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        units = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        addquest = new javax.swing.JTextField();
        addq = new javax.swing.JButton();
        clr = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        marks = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        delQ = new javax.swing.JPanel();
        delete = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        sub = new javax.swing.JComboBox();
        unit = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        question = new javax.swing.JList();
        jLabel9 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Add/Delete Questions");
        setVisible(true);

        addel.setBackground(new java.awt.Color(255, 255, 255));

        addQ.setBackground(new java.awt.Color(255, 255, 255));
        addQ.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        addQ.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel2.setText("Select Subject");
        addQ.add(jLabel2);
        jLabel2.setBounds(20, 50, 100, 20);

        subjects.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        subjects.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                subjectsItemStateChanged(evt);
            }
        });
        addQ.add(subjects);
        subjects.setBounds(140, 30, 350, 50);

        jLabel3.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel3.setText("Units");
        addQ.add(jLabel3);
        jLabel3.setBounds(20, 140, 80, 30);

        units.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        addQ.add(units);
        units.setBounds(140, 120, 190, 50);

        jLabel4.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel4.setText("Questions");
        addQ.add(jLabel4);
        jLabel4.setBounds(20, 230, 90, 20);

        addquest.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        addQ.add(addquest);
        addquest.setBounds(140, 200, 380, 60);

        addq.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        addq.setText("Add Questions");
        addq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addqActionPerformed(evt);
            }
        });
        addQ.add(addq);
        addq.setBounds(130, 450, 240, 40);

        clr.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        clr.setText("Clear");
        clr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clrActionPerformed(evt);
            }
        });
        addQ.add(clr);
        clr.setBounds(380, 450, 230, 40);

        jLabel5.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel5.setText("Marks");
        addQ.add(jLabel5);
        jLabel5.setBounds(20, 320, 50, 16);

        marks.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "5", "10", "20" }));
        marks.setSelectedIndex(-1);
        addQ.add(marks);
        marks.setBounds(140, 310, 130, 50);

        jLabel8.setText("jLabel8");
        addQ.add(jLabel8);
        jLabel8.setBounds(0, 0, 730, 520);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addQ, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addQ, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
        );

        addel.addTab("Add Questions", jPanel1);

        delQ.setBackground(new java.awt.Color(255, 255, 255));
        delQ.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                delQFocusGained(evt);
            }
        });
        delQ.setLayout(null);

        delete.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        delete.setText("DELETE");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        delQ.add(delete);
        delete.setBounds(250, 440, 190, 50);

        jLabel1.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel1.setText("Select Question");
        delQ.add(jLabel1);
        jLabel1.setBounds(50, 220, 110, 30);

        jLabel6.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel6.setText("Select Subject");
        delQ.add(jLabel6);
        jLabel6.setBounds(50, 50, 110, 30);

        jLabel7.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel7.setText("Select Unit");
        delQ.add(jLabel7);
        jLabel7.setBounds(50, 130, 110, 30);

        sub.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        sub.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                subItemStateChanged(evt);
            }
        });
        delQ.add(sub);
        sub.setBounds(160, 50, 380, 50);

        unit.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        unit.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                unitItemStateChanged(evt);
            }
        });
        delQ.add(unit);
        unit.setBounds(160, 130, 120, 40);

        question.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jScrollPane1.setViewportView(question);

        delQ.add(jScrollPane1);
        jScrollPane1.setBounds(160, 200, 390, 200);

        jLabel9.setText("jLabel9");
        delQ.add(jLabel9);
        jLabel9.setBounds(0, 0, 730, 530);

        addel.addTab("Delete Questions", delQ);

        getContentPane().add(addel, java.awt.BorderLayout.CENTER);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-750)/2, (screenSize.height-585)/2, 750, 585);
    }// </editor-fold>//GEN-END:initComponents

    private void subjectsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_subjectsItemStateChanged
        // TODO add your handling code here:
        int i=subjects.getSelectedIndex();
        try
        {
            if(subjects.getSelectedIndex()!=-1 && flag==false)
            {
                units.removeAllItems();
                connect_me.st=connect_me.con.createStatement();
                sql="select unit from sub_unit where sub_id="+sid[i]+"";
                connect_me.rs=connect_me.st.executeQuery(sql);
                if(connect_me.rs.next())
                {
                    count1=connect_me.rs.getInt(1);
                }
                for(int j=1;j<=count1;j++)
                {
                    units.addItem(j);
                }
                connect_me.st.close();
            }

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.toString());
        }
    }//GEN-LAST:event_subjectsItemStateChanged

    private void addqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addqActionPerformed
        // TODO add your handling code here:
        try
        {
            if(subjects.getSelectedIndex()==-1)
            {
                JOptionPane.showMessageDialog(rootPane, "Please Select Subject");
                return;
            }
            if(units.getSelectedIndex()==-1)
            {
                JOptionPane.showMessageDialog(rootPane, "Please Select unit");
                return;
            }
            if(addquest.getText().equals(""))
            {
                JOptionPane.showMessageDialog(rootPane, "Enter a question");
                return;
            }
            if(marks.getSelectedIndex()==-1)
            {
                JOptionPane.showMessageDialog(rootPane, "Please Select marks");
                return;
            }
           
            
            connect_me.st=connect_me.con.createStatement();

            if(subjects.isEnabled())
            {
                sql="insert into qmaster values("+connect_me.idgen("qmaster", "qid")+","+sid[subjects.getSelectedIndex()]+",'"+addquest.getText()+"',"+Integer.parseInt(marks.getSelectedItem().toString())+","+Integer.parseInt(units.getSelectedItem().toString())+")";
            }
            else
            {
            sql="insert into qmaster values("+connect_me.idgen("qmaster", "qid")+","+s+",'"+addquest.getText()+"',"+mark+","+uni+")";
            }

            connect_me.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(rootPane, "Question Inserted Successfully.");
            if(flag==false)
            {
                addquest.setText("");
            marks.setSelectedIndex(-1);
            
            }
            else
            {
                JInternalFrame []x= DisplayMenu.jDesktopPane1.getAllFrames();
                for(int i=0;i<x.length; i++)
                {
                    x[i].dispose();
                }
                d=DisplayMenu.jDesktopPane1.getSize();
                FrmGenPaper gp=new FrmGenPaper(s,mainq,e_id,tot);
                gp.setSize(d);
                gp.setPreferredSize(d);
                gp.setBounds(0,0,gp.getWidth(),gp.getHeight());
                DisplayMenu.jDesktopPane1.add(gp);
            }
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.toString());
        }
    }//GEN-LAST:event_addqActionPerformed

    private void clrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clrActionPerformed
        // TODO add your handling code here:
        clearall();
    }//GEN-LAST:event_clrActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        int i=0;
        q=question.getSelectedIndices();
        try
        {
            if(sub.getSelectedIndex()==-1)
            {
                JOptionPane.showMessageDialog(rootPane, "Please Select Subject");
                return;
            }  
            if(unit.getSelectedIndex()==-1)
            {
                JOptionPane.showMessageDialog(rootPane, "Please Select unit");
                return;
            }
            if(q.length<=0)
            {
                JOptionPane.showMessageDialog(rootPane, "select Questions");
                return;
            }
            connect_me.st=connect_me.con.createStatement();
            sql="delete from qmaster where qid='"+qid[q[i]]+"'";
            connect_me.st.executeUpdate(sql);
            JOptionPane.showMessageDialog(rootPane, "Question Deleted Successfully");
            sub.setSelectedIndex(-1);
            unit.setSelectedIndex(-1);
            question.setListData(new String[0]);

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.toString());
        }

    }//GEN-LAST:event_deleteActionPerformed

    private void subItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_subItemStateChanged
        // TODO add your handling code here:
        //int i=sub.getSelectedIndex();
        try
        {
            if(sub.getSelectedIndex()!=-1)
            {
                unit.removeAllItems();
                connect_me.st=connect_me.con.createStatement();
                sql="select unit from sub_unit where sub_id=(select sub_id from subject where sub_name='"+sub.getSelectedItem().toString()+"')";
                connect_me.rs=connect_me.st.executeQuery(sql);
                if(connect_me.rs.next())
                {
                    count1=connect_me.rs.getInt(1);
                }
                for(int j=1;j<=count1;j++)
                {
                    unit.addItem("" + j);
                }
                connect_me.st.close();
            }

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.toString());
        }
    }//GEN-LAST:event_subItemStateChanged

    private void unitItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_unitItemStateChanged
        // TODO add your handling code here:
        int i=0;
        try
        {
            if(unit.getSelectedIndex()!=-1)
            {
                connect_me.st=connect_me.con.createStatement();
                sql="select isnull(count(question),0) from qmaster where sub_id="+sid[sub.getSelectedIndex()] +" and unit="+Integer.parseInt(unit.getSelectedItem().toString());
                connect_me.rs=connect_me.st.executeQuery(sql);
                if(connect_me.rs.next())
                {
                    count=connect_me.rs.getInt(1);
                }
                connect_me.st.close();
                if(count==0)
                {
                    return;
                }
                quest=new String[count];
                qid= new int[count];

                connect_me.st=connect_me.con.createStatement();
                sql="select question,qid from qmaster where sub_id="+sid[sub.getSelectedIndex()] +" and unit="+Integer.parseInt(unit.getSelectedItem().toString())+"";
                connect_me.rs=connect_me.st.executeQuery(sql);
                while(connect_me.rs.next())
                {

                    quest[i]=connect_me.rs.getString(1);
                    qid[i]=connect_me.rs.getInt(2);
                    i++;
                }
                question.setListData(quest);
                connect_me.st.close();
            }
        }

        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.toString());
        }

    }//GEN-LAST:event_unitItemStateChanged

    private void delQFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_delQFocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_delQFocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addQ;
    public javax.swing.JTabbedPane addel;
    private javax.swing.JButton addq;
    private javax.swing.JTextField addquest;
    private javax.swing.JButton clr;
    private javax.swing.JPanel delQ;
    private javax.swing.JButton delete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox marks;
    private javax.swing.JList question;
    private javax.swing.JComboBox sub;
    private javax.swing.JComboBox subjects;
    private javax.swing.JComboBox unit;
    private javax.swing.JComboBox units;
    // End of variables declaration//GEN-END:variables
}
