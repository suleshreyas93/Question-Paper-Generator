/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mypack;

/**
 *
 * @author Administrator
 */
import java.sql.*;
import javax.swing.JOptionPane;
public class connect_me {
    
    public static Connection con, con1, con2;
    public static ResultSet rs,rs1;
    public static Statement st,st1, st2;
    public static int count=0,id=0;
    public static String role="",teachr_id="";
    public static void connect_all()
    {
        try
        {
            /*
            
            String URL="jdbc:odbc:sps";
            String username="sa";
            String password="1234";
            String databasename="qp_gen";
            */
            
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            //con=DriverManager.getConnection(URL,username,password);
            con=DriverManager.getConnection("jdbc:odbc:ss");
            con1=DriverManager.getConnection("jdbc:odbc:ss");
            con2=DriverManager.getConnection("jdbc:odbc:ss");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Class Not Found");
        }
    }
    public static int autogen(String tbl_nm,String col_nm)//for format_id,stream_id,exam_id
    {
        int max= 0;
        try
        {
            
           st=con.createStatement();
           String s="select substring(" + col_nm + ", 3, 2) from " + tbl_nm;
           rs=st.executeQuery(s);
            while(rs.next())
            {
                count=Integer.parseInt((rs.getString(1)));
                if(max<count)
                {
                    max= count;
                }
            }
            st.close();
            max++;
           
        }
        catch(Exception e)
        {
            //return(011);
            JOptionPane.showMessageDialog(null, e.toString());
            
        }
        return(max);
        
    }
    public static int idgen(String tbl_nm,String col_nm)//subject id
    {
        int max=0;
        try
        {
            st=con.createStatement();
            String s1="select isnull(max("+col_nm+"),0)from "+tbl_nm+"";
            rs=st.executeQuery(s1);
            if(rs.next())
            {
                count=rs.getInt(1);
                count++;
                
            }
            return(count);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.toString());
            return 0;
        }
    }
    public static String userId(String fnm,String lnm)
    {
        try
        {
            st=con.createStatement();
            String s="select isnull(max(substring(tchr_id, 5, 2)), 0) from teachermaster where tchr_id like '" + fnm.substring(0, 2)+lnm.substring(0, 2) + "%'";
            rs=st.executeQuery(s);
            if(rs.next())
            {
                id= rs.getInt(1);
            }
            st.close();
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        id++;      
        return fnm.substring(0, 2)+lnm.substring(0, 2)+id;
    }
    public static boolean contact(String x)
    {
        char c[]=x.toCharArray();
        for(int i=0;i<c.length;i++)
        {
            if(Character.isDigit(c[i])==false)
            {
                return false;
            }
        }
        return true;
    }
    
    public static String q_ppr_id()
    {
        int tempid=0;
        String id="";
        int max=0;
        try
        {
            
            connect_me.st=connect_me.con.createStatement();
            connect_me.rs=connect_me.st.executeQuery("select substring(qpid,2,3) from qppr_id");
            while(connect_me.rs.next())
            {
                
                tempid=Integer.parseInt(connect_me.rs.getString(1));
                if(max<tempid)
                {
                    max=tempid;
                }
            }
        }
        catch(Exception ex)
        {
             JOptionPane.showMessageDialog(null, ex.toString());
        }
        max++;
        if(max<=9)
        {
            id="00" + max;
        }
        else if(max<=99)
        {
            id="0"+ max;
        }
        else
        {
            id=""+max;
        }
        return id;
    }
}
