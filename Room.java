package Hospital.Managment.System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Room extends JFrame {
  JTable table;

    Room(){
     JPanel panel11=new JPanel();
     panel11.setBounds(5,5,880,490);
     //panel11.setLayout(null);
     panel11.setBackground(new Color(176, 196, 222));
     panel11.setLayout(null);
     add(panel11);

     ImageIcon icon=new ImageIcon("D:\\Hospital Managment\\Hospital Managment System\\src\\Icon\\roomm.png");
     Image i1=icon.getImage().getScaledInstance(250,200,Image.SCALE_DEFAULT);
     ImageIcon icon1=new ImageIcon(i1);

     JLabel label=new JLabel(icon1);
     label.setBounds(500,40,350,400);

     panel11.add(label);
        JLabel label12=new JLabel("Room no");
        label12.setFont(new Font("serif",Font.BOLD,20));
        label12.setBounds(10,10,140,20);
        panel11.add(label12);

        JLabel label13=new JLabel("Aviliablity");
        label13.setFont(new Font("serif",Font.BOLD,20));
        label13.setBounds(130,10,140,20);
        panel11.add(label13);

        JLabel label14=new JLabel("Price");
        label14.setFont(new Font("serif",Font.BOLD,20));
        label14.setBounds(250,10,140,20);
        panel11.add(label14);

        JLabel label15=new JLabel("Bed type");
        label15.setFont(new Font("serif",Font.BOLD,20));
        label15.setBounds(370,10,140,20);
        panel11.add(label15);


        JButton button=new JButton("Back");
        button.setFocusable(false);
        button.setBounds(300,450,150,30);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.white);
        panel11.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
       table=new JTable();
       table.setBounds(10,40,480,1000);
       table.setFont(new Font("tahoma",Font.PLAIN,20));
       table.setBackground(new Color(176,196,222));
       panel11.add(table);

       try {
           Conn c=new Conn();
           String q="select * from room";
           ResultSet rs=c.statement.executeQuery(q);
           table.setModel(DbUtils.resultSetToTableModel(rs));
       }
       catch (Exception e){
           e.printStackTrace();
       }



        setUndecorated(true);
        setSize(890,500);
        setBackground(Color.BLUE);
        setLocation(200,250);
        setLayout(null);
        setVisible(true);


    }

    public static void main(String[] args) {
        new Room();
    }
}
