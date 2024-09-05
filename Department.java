
package Hospital.Managment.System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.*;

public class Department extends JFrame {
    ;

    Department(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        System.out.println(screenSize);

        System.out.println(width);
        System.out.println(height);

        JPanel panel10=new JPanel();
        panel10.setBounds(5,5,690,490);
        panel10.setLayout(null);
        panel10.setBackground(new Color(176, 196, 222));
        add(panel10);


        JTable table=new JTable();
        table.setBounds(0,50,690,350);
        table.setFont(new Font("tahoma",Font.PLAIN,18));
        table.setBackground(new Color(176,196,222));
        //table.setLayout(null);
        panel10.add(table);
try{
            Conn c=new Conn();
            String s="select * from department";
            ResultSet rs=c.statement.executeQuery(s);
            table.setModel(DbUtils.resultSetToTableModel(rs));

            //table.setModel(Db.reasultsetToTableModel());
          //  table.setModel(Db);


        }
        catch (Exception e){
            e.printStackTrace();
        }
        JLabel label10=new JLabel("Department");
       // label10.setBackground(Color.BLACK);
        label10.setBounds(20,11,250,20);
        label10.setFont(new Font("tahoma",Font.BOLD,20));
        panel10.add(label10);

        JLabel label11=new JLabel("Phone Number");
        label11.setBounds(431,11,160,18);
        label11.setFont(new Font("tahoma",Font.BOLD,20));
        panel10.add(label11);

        JButton btn=new JButton("Back");
        btn.setBounds(400,410,150,30);
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.white);
        panel10.add(btn);
        btn.setFocusable(false);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        setUndecorated(true);
        setSize(700,500);
        setLocation(350,250);
        setBackground(new Color(176, 196, 222));
        setLayout(null);
        setResizable(false);
        setVisible(true);

   }

    public static void main(String[] args) {
        new Department();
    }
}

