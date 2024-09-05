package Hospital.Managment.System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class All_Employee_info extends JFrame {
    All_Employee_info(){
        JPanel panel=new JPanel();
        panel.setBackground(new Color(176, 196, 222));
        panel.setBounds(5,5,920,490);
        panel.setLayout(null);
        add(panel);

       JTable table=new JTable();
       table.setBackground(new Color(176,196,222));
       table.setBounds(0,45,920,300);
       table.setFont(new Font("tahoma",Font.PLAIN,18));
       panel.add(table);

       try{
           Conn c=new Conn();
           String q=" select * from EmployeeInfo";
           ResultSet rs=c.statement.executeQuery(q);
           table.setModel(DbUtils.resultSetToTableModel(rs));


       }
       catch (Exception e){
           e.printStackTrace();
       }



        JLabel label=new JLabel("Name");
        label.setBounds(20,10,100,20);
        label.setFont(new Font("Serif",Font.BOLD,20));
        panel.add(label);

        JLabel label1=new JLabel("Age");
        label1.setBounds(180,10,150,20);
        label1.setFont(new Font("Serif",Font.BOLD,20));
        panel.add(label1);


//        JLabel label3=new JLabel("Gender");
//        label3.setBounds(220,10,100,20);
//        label3.setFont(new Font("Serif",Font.BOLD,20));
//        panel.add(label3);


//        JLabel label4=new JLabel("Job");
//        label4.setBounds(340,10,150,20);
//        label4.setFont(new Font("Serif",Font.BOLD,20));
//        panel.add(label4);



        JLabel label6=new JLabel("Phone_no");
        label6.setBounds(300,10,100,20);
        label6.setFont(new Font("Serif",Font.BOLD,20));
        panel.add(label6);

        JLabel label5=new JLabel("Salary");
        label5.setBounds(450,10,100,20);
        label5.setFont(new Font("Serif",Font.BOLD,20));
        panel.add(label5);


        JLabel label7=new JLabel("Gmail");
        label7.setBounds(600,10,100,20);
        label7.setFont(new Font("Serif",Font.BOLD,20));
        panel.add(label7);

        JLabel label8=new JLabel("Adharcard");
        label8.setBounds(750,10,100,20);
        label8.setFont(new Font("Serif",Font.BOLD,20));
        panel.add(label8);


        JButton button=new JButton("BACK");
        button.setBounds(350,450,150,30);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.white);
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
            }
        });
        panel.add(button);


        setUndecorated(true);
        setSize(930,500);
        setLocation(230,250);
        setLayout(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        new All_Employee_info();
    }
}
