/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.*;
import java.sql.*;
import mypack.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FrmFormat extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmFormat
     */
     Random R=new Random();
    String sql,fid="F-",exam_id,subject="";
    int question,mq=1,sub_id,max,total=0;
    String ppr_id;
    boolean flag=true;
    
    int []funit;
    int fin=0,tol=0,subCount=0,mainCount=0;
   int []fmarks;
   int []final_qid;
   int []fmainq;
   int []fsubq;
    public FrmFormat() {
        initComponents();
        
        
    }
     public FrmFormat(int mainquest,int sid,String eid,int tot)
    {
        initComponents();
        connect_me.connect_all();
        
       
           
            Image i=Toolkit.getDefaultToolkit().getImage(getClass().getResource("\\images\\abstract-pastel-blue-white-background-design-34948659.jpg"));
            Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
       
            Image js=i.getScaledInstance(dim.width, dim.height, Image.SCALE_REPLICATE);
            ImageIcon img=new ImageIcon(js);
            jLabel4.setIcon(img);
            jLabel4.setSize(DisplayMenu.jDesktopPane1.getWidth(), DisplayMenu.jDesktopPane1.getHeight());
        
            
        
        fid+=connect_me.autogen("format","format_id");
        
        ttlmarks.setEditable(false);
        subq1.setVisible(false);
        subq2.setVisible(false);
        subq3.setVisible(false);
        subq4.setVisible(false);
        btnGen.setEnabled(false);
        
        max=tot;
        mainQ.setText(mq+"");
        question=mainquest;
        sub_id=sid; 
        exam_id=eid;
         tol=max*question;
        done.setEnabled(false);
        
        show.setEnabled(false);
        
        try
        {
            int count;
            connect_me.st=connect_me.con.createStatement();
            sql="select unit from sub_unit where sub_id="+sub_id+"";
            connect_me.rs=connect_me.st.executeQuery(sql);
            if(connect_me.rs.next())
            {
                 count=connect_me.rs.getInt(1);
                for(int j=1;j<=count;j++)
                {
                    subunit1.addItem(j);
                    subunit2.addItem(j);
                    subunit3.addItem(j);
                    subunit4.addItem(j);
                  
                }
                subunit1.setSelectedIndex(-1);
                subunit2.setSelectedIndex(-1);
                subunit3.setSelectedIndex(-1);
                subunit4.setSelectedIndex(-1);
               
            }
            connect_me.st.close();
            
            connect_me.st=connect_me.con.createStatement();
            sql="select sub_name from subject where sub_id="+sub_id+"";
            connect_me.rs=connect_me.st.executeQuery(sql);
            if(connect_me.rs.next())
            {
                subject=connect_me.rs.getString(1);
            }
            
            
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.toString());
        }
    }
     
     public void generatepdf() {
        
        try
        {
           
               String branch="";
               int semester=0;
               String D="",su="",tm="";
               
               try
               {
                   connect_me.st=connect_me.con.createStatement();
                   sql="select stream.stream_name,subject.semester from subject inner join stream on subject.stream_id=stream.stream_id where sub_id="+sub_id+"";
                   connect_me.rs=connect_me.st.executeQuery(sql);
                   if(connect_me.rs.next())
                   {
                       branch=connect_me.rs.getString(1);
                       semester=connect_me.rs.getInt(2);
                   }
                   connect_me.st.close();
               }
               catch(Exception e)
               {
                   JOptionPane.showMessageDialog(rootPane, e.toString());
               }
                try
           {
               connect_me.st=connect_me.con.createStatement();
               sql="select count(mainq) from "+ppr_id+"";
               connect_me.rs=connect_me.st.executeQuery(sql);
               if(connect_me.rs.next())
               {
                   mainCount=connect_me.rs.getInt(1);
               }
               connect_me.st.close();
           }
           catch(Exception e)
           {
               JOptionPane.showMessageDialog(rootPane, e.toString());
           }
           
            
            su="Subject: "+subject;
            connect_me.st= connect_me.con.createStatement();
            sql= "select etype from exam_type where exam_id='"+exam_id+"'";
            connect_me.rs= connect_me.st.executeQuery(sql);
            String e= "";
            if(connect_me.rs.next())
            {
            e= connect_me.rs.getString(1);
            
            

              
            }
          connect_me.st.close();
          
          if(e.equals("external"))
          {
              tol=100;
              
          }
           
          tm="Total Marks: "+tol;
            
           String sm="Semester: "+semester;
           String bn="Branch: "+branch;
           if(tol==20)
           {
               D="Duration: 1 HOUR ";
           }
           else if(tol>20&&tol<=50)
           {
               D="Duration: 2 HOURS";
           }
           else if(tol>50)
           {
               D="Duration: 3 HOURS";
           }
           
           try
           {
               FileOutputStream file = new FileOutputStream(new File(subject+"_"+ppr_id+".pdf"));
               com.itextpdf.text.Document doc = new com.itextpdf.text.Document(PageSize.A4);
               PdfWriter.getInstance(doc, file);

               String subqs[]={"(a)","(b)","(c)","(d)"};
               
               doc.open();
               Paragraph par = new Paragraph("Bharat College Of Engineering");
               par.setAlignment(Element.ALIGN_CENTER);
               par.getFont().setStyle(Font.BOLDITALIC);
               doc.add(par);
              
               doc.add(new Paragraph(" "));
               
               PdfPTable pt=new PdfPTable(2);
               PdfPCell pt_cell;
               
                           
               String st[]=new String[]{su,tm};
               //for(int k=0;k<2;k++)
               {
                   Phrase p=new Phrase(st[0]);
                   p.getFont().setStyle(Font.BOLDITALIC);
                   pt_cell=new PdfPCell(p);
                   pt_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                   pt_cell.setBorder(0);
                   pt.addCell(pt_cell);
                   
                   p=new Phrase(st[1]);
                   p.getFont().setStyle(Font.BOLDITALIC);
                   pt_cell=new PdfPCell(p);
                   pt_cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                   pt_cell.setBorder(0);
                   pt.addCell(pt_cell);
               }
               pt.setWidthPercentage(100);
               doc.add(pt);
              // doc.add(new Paragraph(" "));
               
               PdfPTable pt1=new PdfPTable(2);
               PdfPCell pt1_cell;
               
                           
               String st1[]=new String[]{bn,D};
               //for(int k=0;k<2;k++)
               {
                   Phrase p1=new Phrase(st1[0]);
                   p1.getFont().setStyle(Font.BOLDITALIC);
                   pt1_cell=new PdfPCell(p1);
                   pt1_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                   pt1_cell.setBorder(0);
                   pt1.addCell(pt1_cell);
                   
                    p1=new Phrase(st1[1]);
                   p1.getFont().setStyle(Font.BOLDITALIC);
                   pt1_cell=new PdfPCell(p1);
                   pt1_cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                   pt1_cell.setBorder(0);
                   pt1.addCell(pt1_cell);
                   
               }
               pt1.setWidthPercentage(100);
               doc.add(pt1);
               //doc.add(new Paragraph(" "));
               
               Paragraph pa = new Paragraph(sm);
               pa.setAlignment(Element.ALIGN_LEFT);
               pa.getFont().setStyle(Font.BOLDITALIC);
               doc.add(pa);
               
               doc.add(new Paragraph(" "));
               doc.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------"));
               
               
               float columnWidth[]={5,5,85,5};
               PdfPTable tb = new PdfPTable(4);
               tb.setWidths(columnWidth);
               //tb.setWidthPercentage(columnWidth,pgSize);
               PdfPCell tb_cell;
               
               
               
               for(int i=1;i<=mainCount;i++)
               {
                   Phrase ph;
                   
                   
                   
                   int subq[],marks[];
                   String qstn[];
                   try
                   {
                       
                       
                       connect_me.st = connect_me.con.createStatement();
                       sql = "select count(subq) from "+ppr_id+" where mainq="+i;
                       connect_me.rs = connect_me.st.executeQuery(sql);
                       if (connect_me.rs.next()) 
                       {
                           subCount = connect_me.rs.getInt(1);
                       }
                       connect_me.st.close();
                   }
                   catch(Exception ex)
                   {
                       JOptionPane.showMessageDialog(rootPane, ex.toString());
                       
                   }   
                   
                   qstn=new String[subCount];
                   marks=new int[subCount];

                   
                   try
                   {
                       
                       int cnt=0;
                       connect_me.st = connect_me.con.createStatement();
                       sql = "select question,marks from "+ppr_id+" where mainq="+i;
                       connect_me.rs = connect_me.st.executeQuery(sql);
                       while (connect_me.rs.next())
                       {
                           qstn[cnt] = connect_me.rs.getString(1);
                           marks[cnt]=connect_me.rs.getInt(2);
                           cnt++;
                       }
                       connect_me.st.close();
                       
                   }
                   catch(Exception ex)
                   {
                       JOptionPane.showMessageDialog(rootPane, ex.toString());
                   }
                   
                   for(int j=0;j<subCount;j++)
                   {
                       if(flag)
                   {
                       ph= new Phrase(i+"");
                       ph.getFont().setStyle(Font.BOLDITALIC);
                       tb_cell = new PdfPCell(ph);
                       tb_cell.setBorder(0);
                       tb.addCell(tb_cell);
                       flag=false;
                   }
                   else
                   {
                       ph = new Phrase(" ");
                       ph.getFont().setStyle(Font.BOLDITALIC);
                       tb_cell = new PdfPCell(ph);
                       //tb_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                       tb_cell.setBorder(0);
                       tb.addCell(tb_cell);
                   }
                       
                       ph= new Phrase((subqs[j])+"");
                       ph.getFont().setStyle(Font.BOLDITALIC);
                       tb_cell = new PdfPCell(ph);
                          tb_cell.setBorder(0);
                       tb.addCell(tb_cell);
                       
                       ph= new Phrase(qstn[j]);
                       ph.getFont().setStyle(Font.BOLDITALIC);
                       tb_cell = new PdfPCell(ph);
                          tb_cell.setBorder(0);
                       tb.addCell(tb_cell);
                       
                       ph= new Phrase(marks[j]+"");
                       ph.getFont().setStyle(Font.BOLDITALIC);
                       tb_cell = new PdfPCell(ph);
                          tb_cell.setBorder(0);
                       tb.addCell(tb_cell);
                   }
                   flag=true;
               }
               
               doc.add(tb);
               doc.close();
               file.close();
      
           }
           catch(Exception ex)
           {
               JOptionPane.showMessageDialog(rootPane, ex.toString());
               
           }
           
           try
           {
                if (Desktop.isDesktopSupported())
                {
                       File f = new File( subject+"_"+ppr_id+".pdf");
                       Desktop.getDesktop().open(f);
                 }
           }
           catch(Exception ex)
           {
               JOptionPane.showMessageDialog(this, ex.toString());
           }
           }
        catch(SQLException ex)
        {
            Logger.getLogger(FrmFormat.class.getName()).log(Level.SEVERE, null,ex);
        }
        }
     
      public void clrnext()
    {
                subq1.setVisible(false);
                subq2.setVisible(false);
                subq3.setVisible(false);
                subq4.setVisible(false);
            
                
               subunit1.setSelectedIndex(-1);
                subunit2.setSelectedIndex(-1);
                subunit3.setSelectedIndex(-1);
                subunit4.setSelectedIndex(-1);
              
                
                submark1.setSelectedIndex(-1);
                submark2.setSelectedIndex(-1);
                submark3.setSelectedIndex(-1);
                submark4.setSelectedIndex(-1);
                
                
                subQ.setSelectedIndex(-1);
    }
      public void showpanels(int display)
    {
        JPanel []jp ={subq1, subq2, subq3, subq4};
        
        for(int i=0;i<jp.length;i++)
        {
            jp[i].setVisible(false);
        }
        
        for(int i= 0; i<display; i++)
        {
            jp[i].setVisible(true);
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
        jLabel2 = new javax.swing.JLabel();
        mainQ = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        subq1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        subunit1 = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        submark1 = new javax.swing.JComboBox();
        subq2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        subunit2 = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        submark2 = new javax.swing.JComboBox();
        jPanel8 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jComboBox14 = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        jComboBox15 = new javax.swing.JComboBox();
        jPanel9 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jComboBox16 = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        jComboBox17 = new javax.swing.JComboBox();
        jPanel10 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jComboBox18 = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();
        jComboBox19 = new javax.swing.JComboBox();
        subq3 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        subunit3 = new javax.swing.JComboBox();
        jLabel32 = new javax.swing.JLabel();
        submark3 = new javax.swing.JComboBox();
        jPanel16 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jComboBox30 = new javax.swing.JComboBox();
        jLabel34 = new javax.swing.JLabel();
        jComboBox31 = new javax.swing.JComboBox();
        jPanel17 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jComboBox32 = new javax.swing.JComboBox();
        jLabel36 = new javax.swing.JLabel();
        jComboBox33 = new javax.swing.JComboBox();
        jPanel18 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jComboBox34 = new javax.swing.JComboBox();
        jLabel38 = new javax.swing.JLabel();
        jComboBox35 = new javax.swing.JComboBox();
        jPanel19 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jComboBox36 = new javax.swing.JComboBox();
        jLabel40 = new javax.swing.JLabel();
        jComboBox37 = new javax.swing.JComboBox();
        jPanel20 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jComboBox38 = new javax.swing.JComboBox();
        jLabel42 = new javax.swing.JLabel();
        jComboBox39 = new javax.swing.JComboBox();
        jPanel21 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jComboBox40 = new javax.swing.JComboBox();
        jLabel44 = new javax.swing.JLabel();
        jComboBox41 = new javax.swing.JComboBox();
        jPanel22 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jComboBox42 = new javax.swing.JComboBox();
        jLabel46 = new javax.swing.JLabel();
        jComboBox43 = new javax.swing.JComboBox();
        subq4 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        subunit4 = new javax.swing.JComboBox();
        jLabel48 = new javax.swing.JLabel();
        submark4 = new javax.swing.JComboBox();
        jPanel24 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jComboBox46 = new javax.swing.JComboBox();
        jLabel50 = new javax.swing.JLabel();
        jComboBox47 = new javax.swing.JComboBox();
        jPanel25 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jComboBox48 = new javax.swing.JComboBox();
        jLabel52 = new javax.swing.JLabel();
        jComboBox49 = new javax.swing.JComboBox();
        jPanel26 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jComboBox50 = new javax.swing.JComboBox();
        jLabel54 = new javax.swing.JLabel();
        jComboBox51 = new javax.swing.JComboBox();
        jPanel27 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jComboBox52 = new javax.swing.JComboBox();
        jLabel56 = new javax.swing.JLabel();
        jComboBox53 = new javax.swing.JComboBox();
        jPanel28 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jComboBox54 = new javax.swing.JComboBox();
        jLabel58 = new javax.swing.JLabel();
        jComboBox55 = new javax.swing.JComboBox();
        jPanel29 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jComboBox56 = new javax.swing.JComboBox();
        jLabel60 = new javax.swing.JLabel();
        jComboBox57 = new javax.swing.JComboBox();
        jPanel30 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        jComboBox58 = new javax.swing.JComboBox();
        jLabel62 = new javax.swing.JLabel();
        jComboBox59 = new javax.swing.JComboBox();
        show = new javax.swing.JButton();
        next = new javax.swing.JButton();
        done = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ttlmarks = new javax.swing.JTextField();
        subQ = new javax.swing.JComboBox();
        btnGen = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Format");
        setVisible(true);

        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel2.setText("Main Question");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 20, 100, 30);

        mainQ.setEditable(false);
        mainQ.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jPanel1.add(mainQ);
        mainQ.setBounds(130, 20, 70, 30);

        jLabel3.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel3.setText("Sub Question ");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(210, 20, 90, 20);

        subq1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SUB QUESTION.1", 0, 0, new java.awt.Font("Modern No. 20", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        subq1.setOpaque(false);
        subq1.setLayout(null);

        jLabel7.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel7.setText("UNIT");
        subq1.add(jLabel7);
        jLabel7.setBounds(30, 60, 50, 16);

        subunit1.setFont(new java.awt.Font("Modern No. 20", 0, 14)); // NOI18N
        subq1.add(subunit1);
        subunit1.setBounds(20, 90, 60, 30);

        jLabel8.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel8.setText("MARKS");
        subq1.add(jLabel8);
        jLabel8.setBounds(140, 60, 50, 16);

        submark1.setFont(new java.awt.Font("Modern No. 20", 0, 14)); // NOI18N
        submark1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "5", "10", "20" }));
        submark1.setSelectedIndex(-1);
        submark1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                submark1ItemStateChanged(evt);
            }
        });
        subq1.add(submark1);
        submark1.setBounds(130, 90, 60, 30);

        jPanel1.add(subq1);
        subq1.setBounds(30, 120, 220, 150);

        subq2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SUB QUESTION.2 ", 0, 0, new java.awt.Font("Modern No. 20", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        subq2.setOpaque(false);
        subq2.setLayout(null);

        jLabel15.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel15.setText("UNIT");
        subq2.add(jLabel15);
        jLabel15.setBounds(30, 60, 50, 16);

        subunit2.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        subq2.add(subunit2);
        subunit2.setBounds(30, 90, 60, 30);

        jLabel16.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel16.setText("MARKS");
        subq2.add(jLabel16);
        jLabel16.setBounds(130, 60, 54, 16);

        submark2.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        submark2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "5", "10", "20", " " }));
        submark2.setSelectedIndex(-1);
        subq2.add(submark2);
        submark2.setBounds(140, 90, 60, 30);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SUB Q.1", 0, 0, null, new java.awt.Color(0, 0, 0)));
        jPanel8.setLayout(null);

        jLabel17.setText("UNIT");
        jPanel8.add(jLabel17);
        jLabel17.setBounds(30, 60, 34, 14);

        jComboBox14.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel8.add(jComboBox14);
        jComboBox14.setBounds(10, 90, 60, 30);

        jLabel18.setText("MARKS");
        jPanel8.add(jLabel18);
        jLabel18.setBounds(130, 60, 34, 14);

        jComboBox15.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel8.add(jComboBox15);
        jComboBox15.setBounds(120, 90, 60, 30);

        subq2.add(jPanel8);
        jPanel8.setBounds(250, 120, 220, 150);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SUB Q.1", 0, 0, null, new java.awt.Color(0, 0, 0)));
        jPanel9.setLayout(null);

        jLabel19.setText("UNIT");
        jPanel9.add(jLabel19);
        jLabel19.setBounds(30, 60, 34, 14);

        jComboBox16.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel9.add(jComboBox16);
        jComboBox16.setBounds(10, 90, 60, 30);

        jLabel20.setText("MARKS");
        jPanel9.add(jLabel20);
        jLabel20.setBounds(130, 60, 34, 14);

        jComboBox17.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel9.add(jComboBox17);
        jComboBox17.setBounds(120, 90, 60, 30);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SUB Q.1", 0, 0, null, new java.awt.Color(0, 0, 0)));
        jPanel10.setLayout(null);

        jLabel21.setText("UNIT");
        jPanel10.add(jLabel21);
        jLabel21.setBounds(30, 60, 34, 14);

        jComboBox18.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel10.add(jComboBox18);
        jComboBox18.setBounds(10, 90, 60, 30);

        jLabel22.setText("MARKS");
        jPanel10.add(jLabel22);
        jLabel22.setBounds(130, 60, 34, 14);

        jComboBox19.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel10.add(jComboBox19);
        jComboBox19.setBounds(120, 90, 60, 30);

        jPanel9.add(jPanel10);
        jPanel10.setBounds(250, 120, 220, 150);

        subq2.add(jPanel9);
        jPanel9.setBounds(250, 120, 220, 150);

        jPanel1.add(subq2);
        subq2.setBounds(310, 120, 220, 150);

        subq3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SUB QUESTION.3 ", 0, 0, new java.awt.Font("Modern No. 20", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        subq3.setOpaque(false);
        subq3.setLayout(null);

        jLabel31.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel31.setText("UNIT");
        subq3.add(jLabel31);
        jLabel31.setBounds(30, 60, 50, 16);

        subunit3.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        subq3.add(subunit3);
        subunit3.setBounds(20, 90, 60, 30);

        jLabel32.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel32.setText("MARKS");
        subq3.add(jLabel32);
        jLabel32.setBounds(130, 60, 54, 16);

        submark3.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        submark3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "5", "10", "20", " " }));
        submark3.setSelectedIndex(-1);
        subq3.add(submark3);
        submark3.setBounds(120, 90, 60, 30);

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SUB Q.1", 0, 0, null, new java.awt.Color(0, 0, 0)));
        jPanel16.setLayout(null);

        jLabel33.setText("UNIT");
        jPanel16.add(jLabel33);
        jLabel33.setBounds(30, 60, 34, 14);

        jComboBox30.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel16.add(jComboBox30);
        jComboBox30.setBounds(10, 90, 60, 30);

        jLabel34.setText("MARKS");
        jPanel16.add(jLabel34);
        jLabel34.setBounds(130, 60, 34, 14);

        jComboBox31.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel16.add(jComboBox31);
        jComboBox31.setBounds(120, 90, 60, 30);

        subq3.add(jPanel16);
        jPanel16.setBounds(250, 120, 220, 150);

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SUB Q.1", 0, 0, null, new java.awt.Color(0, 0, 0)));
        jPanel17.setLayout(null);

        jLabel35.setText("UNIT");
        jPanel17.add(jLabel35);
        jLabel35.setBounds(30, 60, 34, 14);

        jComboBox32.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel17.add(jComboBox32);
        jComboBox32.setBounds(10, 90, 60, 30);

        jLabel36.setText("MARKS");
        jPanel17.add(jLabel36);
        jLabel36.setBounds(130, 60, 34, 14);

        jComboBox33.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel17.add(jComboBox33);
        jComboBox33.setBounds(120, 90, 60, 30);

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SUB Q.1", 0, 0, null, new java.awt.Color(0, 0, 0)));
        jPanel18.setLayout(null);

        jLabel37.setText("UNIT");
        jPanel18.add(jLabel37);
        jLabel37.setBounds(30, 60, 34, 14);

        jComboBox34.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel18.add(jComboBox34);
        jComboBox34.setBounds(10, 90, 60, 30);

        jLabel38.setText("MARKS");
        jPanel18.add(jLabel38);
        jLabel38.setBounds(130, 60, 34, 14);

        jComboBox35.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel18.add(jComboBox35);
        jComboBox35.setBounds(120, 90, 60, 30);

        jPanel17.add(jPanel18);
        jPanel18.setBounds(250, 120, 220, 150);

        subq3.add(jPanel17);
        jPanel17.setBounds(250, 120, 220, 150);

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SUB Q.1", 0, 0, null, new java.awt.Color(0, 0, 0)));
        jPanel19.setLayout(null);

        jLabel39.setText("UNIT");
        jPanel19.add(jLabel39);
        jLabel39.setBounds(30, 60, 34, 14);

        jComboBox36.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel19.add(jComboBox36);
        jComboBox36.setBounds(10, 90, 60, 30);

        jLabel40.setText("MARKS");
        jPanel19.add(jLabel40);
        jLabel40.setBounds(130, 60, 34, 14);

        jComboBox37.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel19.add(jComboBox37);
        jComboBox37.setBounds(120, 90, 60, 30);

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SUB Q.1", 0, 0, null, new java.awt.Color(0, 0, 0)));
        jPanel20.setLayout(null);

        jLabel41.setText("UNIT");
        jPanel20.add(jLabel41);
        jLabel41.setBounds(30, 60, 34, 14);

        jComboBox38.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel20.add(jComboBox38);
        jComboBox38.setBounds(10, 90, 60, 30);

        jLabel42.setText("MARKS");
        jPanel20.add(jLabel42);
        jLabel42.setBounds(130, 60, 34, 14);

        jComboBox39.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel20.add(jComboBox39);
        jComboBox39.setBounds(120, 90, 60, 30);

        jPanel19.add(jPanel20);
        jPanel20.setBounds(250, 120, 220, 150);

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SUB Q.1", 0, 0, null, new java.awt.Color(0, 0, 0)));
        jPanel21.setLayout(null);

        jLabel43.setText("UNIT");
        jPanel21.add(jLabel43);
        jLabel43.setBounds(30, 60, 34, 14);

        jComboBox40.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel21.add(jComboBox40);
        jComboBox40.setBounds(10, 90, 60, 30);

        jLabel44.setText("MARKS");
        jPanel21.add(jLabel44);
        jLabel44.setBounds(130, 60, 34, 14);

        jComboBox41.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel21.add(jComboBox41);
        jComboBox41.setBounds(120, 90, 60, 30);

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SUB Q.1", 0, 0, null, new java.awt.Color(0, 0, 0)));
        jPanel22.setLayout(null);

        jLabel45.setText("UNIT");
        jPanel22.add(jLabel45);
        jLabel45.setBounds(30, 60, 34, 14);

        jComboBox42.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel22.add(jComboBox42);
        jComboBox42.setBounds(10, 90, 60, 30);

        jLabel46.setText("MARKS");
        jPanel22.add(jLabel46);
        jLabel46.setBounds(130, 60, 34, 14);

        jComboBox43.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel22.add(jComboBox43);
        jComboBox43.setBounds(120, 90, 60, 30);

        jPanel21.add(jPanel22);
        jPanel22.setBounds(250, 120, 220, 150);

        jPanel19.add(jPanel21);
        jPanel21.setBounds(250, 120, 220, 150);

        subq3.add(jPanel19);
        jPanel19.setBounds(540, 120, 220, 150);

        jPanel1.add(subq3);
        subq3.setBounds(600, 120, 220, 150);

        subq4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SUB QUESTION.4", 0, 0, new java.awt.Font("Modern No. 20", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        subq4.setOpaque(false);
        subq4.setLayout(null);

        jLabel47.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel47.setText("UNIT");
        subq4.add(jLabel47);
        jLabel47.setBounds(30, 60, 50, 16);

        subunit4.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        subq4.add(subunit4);
        subunit4.setBounds(30, 90, 60, 30);

        jLabel48.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel48.setText("MARKS");
        subq4.add(jLabel48);
        jLabel48.setBounds(130, 60, 54, 16);

        submark4.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        submark4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "5", "10", "20", " " }));
        submark4.setSelectedIndex(-1);
        subq4.add(submark4);
        submark4.setBounds(130, 90, 60, 30);

        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SUB Q.1", 0, 0, null, new java.awt.Color(0, 0, 0)));
        jPanel24.setLayout(null);

        jLabel49.setText("UNIT");
        jPanel24.add(jLabel49);
        jLabel49.setBounds(30, 60, 34, 14);

        jComboBox46.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel24.add(jComboBox46);
        jComboBox46.setBounds(10, 90, 60, 30);

        jLabel50.setText("MARKS");
        jPanel24.add(jLabel50);
        jLabel50.setBounds(130, 60, 34, 14);

        jComboBox47.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel24.add(jComboBox47);
        jComboBox47.setBounds(120, 90, 60, 30);

        subq4.add(jPanel24);
        jPanel24.setBounds(250, 120, 220, 150);

        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SUB Q.1", 0, 0, null, new java.awt.Color(0, 0, 0)));
        jPanel25.setLayout(null);

        jLabel51.setText("UNIT");
        jPanel25.add(jLabel51);
        jLabel51.setBounds(30, 60, 34, 14);

        jComboBox48.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel25.add(jComboBox48);
        jComboBox48.setBounds(10, 90, 60, 30);

        jLabel52.setText("MARKS");
        jPanel25.add(jLabel52);
        jLabel52.setBounds(130, 60, 34, 14);

        jComboBox49.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel25.add(jComboBox49);
        jComboBox49.setBounds(120, 90, 60, 30);

        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SUB Q.1", 0, 0, null, new java.awt.Color(0, 0, 0)));
        jPanel26.setLayout(null);

        jLabel53.setText("UNIT");
        jPanel26.add(jLabel53);
        jLabel53.setBounds(30, 60, 34, 14);

        jComboBox50.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel26.add(jComboBox50);
        jComboBox50.setBounds(10, 90, 60, 30);

        jLabel54.setText("MARKS");
        jPanel26.add(jLabel54);
        jLabel54.setBounds(130, 60, 34, 14);

        jComboBox51.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel26.add(jComboBox51);
        jComboBox51.setBounds(120, 90, 60, 30);

        jPanel25.add(jPanel26);
        jPanel26.setBounds(250, 120, 220, 150);

        subq4.add(jPanel25);
        jPanel25.setBounds(250, 120, 220, 150);

        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SUB Q.1", 0, 0, null, new java.awt.Color(0, 0, 0)));
        jPanel27.setLayout(null);

        jLabel55.setText("UNIT");
        jPanel27.add(jLabel55);
        jLabel55.setBounds(30, 60, 34, 14);

        jComboBox52.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel27.add(jComboBox52);
        jComboBox52.setBounds(10, 90, 60, 30);

        jLabel56.setText("MARKS");
        jPanel27.add(jLabel56);
        jLabel56.setBounds(130, 60, 34, 14);

        jComboBox53.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel27.add(jComboBox53);
        jComboBox53.setBounds(120, 90, 60, 30);

        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SUB Q.1", 0, 0, null, new java.awt.Color(0, 0, 0)));
        jPanel28.setLayout(null);

        jLabel57.setText("UNIT");
        jPanel28.add(jLabel57);
        jLabel57.setBounds(30, 60, 34, 14);

        jComboBox54.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel28.add(jComboBox54);
        jComboBox54.setBounds(10, 90, 60, 30);

        jLabel58.setText("MARKS");
        jPanel28.add(jLabel58);
        jLabel58.setBounds(130, 60, 34, 14);

        jComboBox55.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel28.add(jComboBox55);
        jComboBox55.setBounds(120, 90, 60, 30);

        jPanel27.add(jPanel28);
        jPanel28.setBounds(250, 120, 220, 150);

        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SUB Q.1", 0, 0, null, new java.awt.Color(0, 0, 0)));
        jPanel29.setLayout(null);

        jLabel59.setText("UNIT");
        jPanel29.add(jLabel59);
        jLabel59.setBounds(30, 60, 34, 14);

        jComboBox56.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel29.add(jComboBox56);
        jComboBox56.setBounds(10, 90, 60, 30);

        jLabel60.setText("MARKS");
        jPanel29.add(jLabel60);
        jLabel60.setBounds(130, 60, 34, 14);

        jComboBox57.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel29.add(jComboBox57);
        jComboBox57.setBounds(120, 90, 60, 30);

        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SUB Q.1", 0, 0, null, new java.awt.Color(0, 0, 0)));
        jPanel30.setLayout(null);

        jLabel61.setText("UNIT");
        jPanel30.add(jLabel61);
        jLabel61.setBounds(30, 60, 34, 14);

        jComboBox58.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel30.add(jComboBox58);
        jComboBox58.setBounds(10, 90, 60, 30);

        jLabel62.setText("MARKS");
        jPanel30.add(jLabel62);
        jLabel62.setBounds(130, 60, 34, 14);

        jComboBox59.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel30.add(jComboBox59);
        jComboBox59.setBounds(120, 90, 60, 30);

        jPanel29.add(jPanel30);
        jPanel30.setBounds(250, 120, 220, 150);

        jPanel27.add(jPanel29);
        jPanel29.setBounds(250, 120, 220, 150);

        subq4.add(jPanel27);
        jPanel27.setBounds(540, 120, 220, 150);

        jPanel1.add(subq4);
        subq4.setBounds(310, 290, 220, 150);

        show.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        show.setText("SHOW");
        show.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showActionPerformed(evt);
            }
        });
        jPanel1.add(show);
        show.setBounds(400, 20, 130, 30);

        next.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        next.setText("NEXT");
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });
        jPanel1.add(next);
        next.setBounds(70, 460, 170, 40);

        done.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        done.setText("DONE");
        done.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneActionPerformed(evt);
            }
        });
        jPanel1.add(done);
        done.setBounds(330, 460, 170, 40);

        jLabel1.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        jLabel1.setText("Total Marks :");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(560, 20, 90, 30);

        ttlmarks.setEditable(false);
        jPanel1.add(ttlmarks);
        ttlmarks.setBounds(670, 20, 60, 30);

        subQ.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        subQ.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
        subQ.setSelectedIndex(-1);
        subQ.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                subQItemStateChanged(evt);
            }
        });
        jPanel1.add(subQ);
        subQ.setBounds(320, 20, 60, 30);

        btnGen.setFont(new java.awt.Font("Modern No. 20", 1, 14)); // NOI18N
        btnGen.setText("GENERATE PAPER");
        btnGen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenActionPerformed(evt);
            }
        });
        jPanel1.add(btnGen);
        btnGen.setBounds(590, 460, 210, 40);

        jLabel4.setText("jLabel4");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(0, 0, 870, 520);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-885)/2, (screenSize.height-551)/2, 885, 551);
    }// </editor-fold>//GEN-END:initComponents

    private void submark1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_submark1ItemStateChanged
        // TODO add your handling code here:
        if(submark1.getSelectedIndex()!=-1)
        {
            //total+=Integer.parseInt(submark1.getSelectedItem().toString());
            //ttlmarks.setText(total+"");
        }
    }//GEN-LAST:event_submark1ItemStateChanged

    private void showActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showActionPerformed
        // TODO add your handling code here:

        int s=Integer.parseInt(subQ.getSelectedItem().toString());
        if((max/s)>=5)
        {
            showpanels(s);
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, "Question no. "+mainQ.getText()+" cannot have "+s+" sub questions."+max);
            subQ.setSelectedIndex(-1);
        }
    }//GEN-LAST:event_showActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        // TODO add your handling code here:
        if(subQ.getSelectedIndex()==-1)
        {
            JOptionPane.showMessageDialog(rootPane, "Please select valid sub questions");
            return;
        }
        
        JComboBox []unit={subunit1, subunit2, subunit3, subunit4};
        JComboBox []marks= {submark1, submark2, submark3, submark4 };
        int tally=0;
        Boolean flag=false,flag2=false;
        
        if(question>1)
        {
            int x= Integer.parseInt(subQ.getSelectedItem().toString());
            for(int z= 0;z<x; z++)
            {

                if(marks[z].getSelectedIndex()!=-1)
                {
                    tally+=Integer.parseInt(marks[z].getSelectedItem().toString());

                    if(subQ.getSelectedItem().equals("1"))
                    {
                        flag=true;
                    }

                    if(z<(x-1))
                    {
                        if(tally>=max)
                        {
                            JOptionPane.showMessageDialog(rootPane, "Marks Exceeding The Limit");
                            flag=false;
                            return;
                        }
                        else
                        {
                            flag=true;
                        }
                    }
                    else if(tally> max || tally< max)
                    {
                        JOptionPane.showMessageDialog(rootPane, "Total Marks For Question "+mainQ.getText()+" is "+max);
                        flag=false;
                        return;
                    }
                    else
                    {
                        flag2=true;
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(rootPane,"Please select the proper marks");
                    return;
                }

            }

            if(flag==true && flag2==true)
            {
                try
                {
                    int subQno=0;
                    total+=tally;
                    for(int z=0;z<x;z++)
                    {
                        subQno=z+1;
                        connect_me.st=connect_me.con.createStatement();
                        sql="insert into format values('"+fid+"','"+exam_id+"',"+mainQ.getText()+"," + subQno + ","+Integer.parseInt(unit[z].getSelectedItem().toString())+","+Integer.parseInt(marks[z].getSelectedItem().toString())+")";
                        //JOptionPane.showMessageDialog(rootPane, sql);
                        connect_me.st.executeUpdate(sql);
                        connect_me.st.close();
                    }
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(rootPane, e.toString());
                }
                mq++;
                JOptionPane.showMessageDialog(rootPane, "Question no. "+mainQ.getText()+" completed");
                clrnext();
                ttlmarks.setText(total+"");
                mainQ.setText(mq+"");
                question--;
                if(question==1)
                {
                    next.setEnabled(false);
                    done.setEnabled(true);
                }
            }

        }

       

    }//GEN-LAST:event_nextActionPerformed

    private void doneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneActionPerformed
        // TODO add your handling code here:

        btnGen.setEnabled(true);
        show.setEnabled(false);
        done.setEnabled(false);
        
        if(subQ.getSelectedIndex()==-1)
        {
            JOptionPane.showMessageDialog(rootPane, "Please select valid sub questions");
        }
        JComboBox []unit={subunit1, subunit2, subunit3, subunit4};
        JComboBox []marks= {submark1, submark2, submark3, submark4 };
        int tally=0;
        Boolean flag=false,flag2=false;
        int x= Integer.parseInt(subQ.getSelectedItem().toString());
        for(int z= 0;z<x; z++)
        {

            if(marks[z].getSelectedIndex()!=-1)
            {
                tally+=Integer.parseInt(marks[z].getSelectedItem().toString());

                if(subQ.getSelectedItem().equals("1"))
                {
                    flag=true;
                }

                if(z<(x-1))
                {
                    if(tally>=max)
                    {
                        JOptionPane.showMessageDialog(rootPane, "Marks Exceeding The Limit");
                        flag=false;
                        return;
                    }
                    else
                    {
                        flag=true;
                    }
                }
                else if(tally> max || tally< max)
                {
                    JOptionPane.showMessageDialog(rootPane, "Total Marks For Question "+mainQ.getText()+" is "+max);
                    flag=false;
                    return;
                }
                else
                {
                    flag2=true;
                }
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane,"Please selct the proper marks");
                return;
            }

        }

        if(flag==true && flag2==true)
        {
            try
            {
                total+=tally;
                for(int z=0;z<x;z++)
                {
                    connect_me.st=connect_me.con.createStatement();
                    sql="insert into format values('"+fid+"','"+exam_id+"',"+mainQ.getText()+"," + (z+1) + ","+Integer.parseInt(unit[z].getSelectedItem().toString())+","+Integer.parseInt(marks[z].getSelectedItem().toString())+")";
                    //JOptionPane.showMessageDialog(rootPane, sql);
                    connect_me.st.executeUpdate(sql);
                    connect_me.st.close();
                }
                ttlmarks.setText(total+"");
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(rootPane, e.toString());
            }
            JOptionPane.showMessageDialog(rootPane, "Question no. "+mainQ.getText()+"completed. \nFormat "+fid+" created.");
            clrnext();
        }

    }//GEN-LAST:event_doneActionPerformed

    private void btnGenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenActionPerformed
        // TODO add your handling code here:
        try
        {
            connect_me.st=connect_me.con.createStatement();
            connect_me.rs=connect_me.st.executeQuery("select count(unit) from format where format_id='"+fid+"'");
            if(connect_me.rs.next())
            {
                fin=connect_me.rs.getInt(1);
            }
            connect_me.st.close();

            //JOptionPane.showMessageDialog(rootPane, fin+"");

            final_qid=new int[fin];
            funit=new int[fin];
            fmarks=new int[fin];
            fmainq=new int[fin];
            fsubq=new int [fin];

            int z=0;
            connect_me.st=connect_me.con.createStatement();
            connect_me.rs=connect_me.st.executeQuery("select mainq,subq,unit,marks from format where format_id='"+fid+"'");
            while(connect_me.rs.next())
            {
                fmainq[z]=connect_me.rs.getInt(1);
                fsubq[z]=connect_me.rs.getInt(2);
                funit[z]=connect_me.rs.getInt(3);
                fmarks[z]=connect_me.rs.getInt(4);
                z++;
            }
            connect_me.st.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.toString());
        }

        int x=0, temp=0;
        int []qid;

        int final_cnt=0;
        boolean flag= false;

        for(int q=0;q<funit.length;q++)
        {
            try
            {
                connect_me.st= connect_me.con.createStatement();
                connect_me.rs= connect_me.st.executeQuery("select count(qid) from qmaster where sub_id="+sub_id+" and unit="+funit[q]+" and marks="+fmarks[q]);
                if(connect_me.rs.next())
                {
                    x= connect_me.rs.getInt(1);
                }
                connect_me.st.close();

            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(rootPane, ex.toString());
            }
            qid=new int[x];
            int i=0;
            try
            {
                connect_me.st= connect_me.con.createStatement();
                connect_me.rs= connect_me.st.executeQuery("select qid from qmaster where sub_id="+sub_id+" and unit="+funit[q]+" and marks="+fmarks[q]);
                while(connect_me.rs.next())
                {
                    qid[i]= connect_me.rs.getInt(1);
                    i++;
                }
                connect_me.st.close();

                temp=R.nextInt(qid.length);
                while(flag==false)
                {
                    for(int j=0;j<final_qid.length;j++)
                    {
                        if(final_qid[j]==qid[temp])
                        {
                            flag=true;
                            break;
                        }
                    }
                    if(flag==false)
                    {
                        break;
                    }
                    if(flag==true)
                    {
                        temp=R.nextInt(qid.length);
                        flag=false;
                    }
                }

                if(flag==false)
                {
                    final_qid[q]=qid[temp];
                    final_cnt++;
                }
                //s+=final_qid[q]+" ";
                //JOptionPane.showMessageDialog(rootPane, "Q no " +final_qid[q] );

            }

            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(rootPane, ex.toString());
            }

        }//end of for

        //table for generated question
        try
        {
            String eid="E_"+exam_id.substring(2);

            ppr_id=eid;
            connect_me.st=connect_me.con.createStatement();
            connect_me.rs=connect_me.st.executeQuery("select semester from subject where sub_id="+sub_id);
            if(connect_me.rs.next())
            {
                ppr_id+="_"+sub_id+ "_"+ connect_me.rs.getString(1);
            }
            connect_me.st.close();

        }//partial ppr_id generated
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.toString());
        }

        ppr_id=("p"+connect_me.q_ppr_id()).concat("_"+ppr_id);
        try
        {
            connect_me.st=connect_me.con.createStatement();
            connect_me.st.executeUpdate("insert into qppr_id values('"+ppr_id+"')");
            connect_me.st.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.toString());
        }

        //JOptionPane.showMessageDialog(rootPane, ppr_id);
        try
        {
            connect_me.st=connect_me.con.createStatement();
            //ppr=ppr.concat("_"+  s);
            //JOptionPane.showMessageDialog(rootPane,"create table "+ppr_id+" (exam_id varchar(10),mainq int,subq int,question varchar(500),marks int)" );
            connect_me.st.executeUpdate("create table "+ppr_id+" (exam_id varchar(10),mainq int,subq int,question varchar(500),marks int)");
            connect_me.st.close();

            for(int i=0;i<funit.length;i++)
            {
                connect_me.st=connect_me.con.createStatement();
                connect_me.st1=connect_me.con1.createStatement();
                String quest="";
                connect_me.rs1=connect_me.st1.executeQuery("select question from qmaster where qid="+final_qid[i]);
                if(connect_me.rs1.next())
                {
                    quest=connect_me.rs1.getString(1);
                }
                connect_me.st1.close();

                //JOptionPane.showMessageDialog(rootPane, "insert into "+ppr+" values('"+exam_id+"',"+fmainq[i]+","+fsubq[i]+",'"+quest+"',"+ fmarks[i]);
                connect_me.st.executeUpdate("insert into "+ppr_id+" values('"+exam_id+"',"+fmainq[i]+","+fsubq[i]+",'"+quest+"',"+ fmarks[i]+")");

                connect_me.st.close();
            }
            JOptionPane.showMessageDialog(rootPane, ppr_id+" generated");
            
            generatepdf();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.toString());
        }

    }//GEN-LAST:event_btnGenActionPerformed

    private void subQItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_subQItemStateChanged
        // TODO add your handling code here:
        if(subQ.getSelectedIndex()==-1)
        {
            
            return;
        }
        else
        {
            show.setEnabled(true);
        }
    }//GEN-LAST:event_subQItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGen;
    private javax.swing.JButton done;
    private javax.swing.JComboBox jComboBox14;
    private javax.swing.JComboBox jComboBox15;
    private javax.swing.JComboBox jComboBox16;
    private javax.swing.JComboBox jComboBox17;
    private javax.swing.JComboBox jComboBox18;
    private javax.swing.JComboBox jComboBox19;
    private javax.swing.JComboBox jComboBox30;
    private javax.swing.JComboBox jComboBox31;
    private javax.swing.JComboBox jComboBox32;
    private javax.swing.JComboBox jComboBox33;
    private javax.swing.JComboBox jComboBox34;
    private javax.swing.JComboBox jComboBox35;
    private javax.swing.JComboBox jComboBox36;
    private javax.swing.JComboBox jComboBox37;
    private javax.swing.JComboBox jComboBox38;
    private javax.swing.JComboBox jComboBox39;
    private javax.swing.JComboBox jComboBox40;
    private javax.swing.JComboBox jComboBox41;
    private javax.swing.JComboBox jComboBox42;
    private javax.swing.JComboBox jComboBox43;
    private javax.swing.JComboBox jComboBox46;
    private javax.swing.JComboBox jComboBox47;
    private javax.swing.JComboBox jComboBox48;
    private javax.swing.JComboBox jComboBox49;
    private javax.swing.JComboBox jComboBox50;
    private javax.swing.JComboBox jComboBox51;
    private javax.swing.JComboBox jComboBox52;
    private javax.swing.JComboBox jComboBox53;
    private javax.swing.JComboBox jComboBox54;
    private javax.swing.JComboBox jComboBox55;
    private javax.swing.JComboBox jComboBox56;
    private javax.swing.JComboBox jComboBox57;
    private javax.swing.JComboBox jComboBox58;
    private javax.swing.JComboBox jComboBox59;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField mainQ;
    private javax.swing.JButton next;
    private javax.swing.JButton show;
    private javax.swing.JComboBox subQ;
    private javax.swing.JComboBox submark1;
    private javax.swing.JComboBox submark2;
    private javax.swing.JComboBox submark3;
    private javax.swing.JComboBox submark4;
    private javax.swing.JPanel subq1;
    private javax.swing.JPanel subq2;
    private javax.swing.JPanel subq3;
    private javax.swing.JPanel subq4;
    private javax.swing.JComboBox subunit1;
    private javax.swing.JComboBox subunit2;
    private javax.swing.JComboBox subunit3;
    private javax.swing.JComboBox subunit4;
    private javax.swing.JTextField ttlmarks;
    // End of variables declaration//GEN-END:variables
}
