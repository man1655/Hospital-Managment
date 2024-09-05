package Hospital.Managment.System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Objects;

public class SearchRoom extends JFrame
{
    Choice choice;
    JTable table;

    SearchRoom(){

        JPanel panel=new JPanel();
        panel.setBounds(5,5,690,490);
        panel.setBackground(new Color(176, 196, 222));
        panel.setLayout(null);
        add(panel);

        JLabel Status=new JLabel("Search For Room");
        Status.setBounds(250,11,186,31);
        Status.setForeground(Color.black);
        Status.setFont(new Font("Tahoma",Font.BOLD,20));
        panel.add(Status);


        JLabel Status1=new JLabel("Status");
        Status1.setBounds(70,70,70,20);
        Status1.setForeground(Color.black);
        Status1.setFont(new Font("Serif",Font.BOLD,18));
        panel.add(Status1);

        JLabel label12=new JLabel("Room no");
        label12.setFont(new Font("serif",Font.BOLD,20));
        label12.setBounds(20,110,140,20);
        panel.add(label12);

        JLabel label13=new JLabel("Aviliablity");
        label13.setFont(new Font("serif",Font.BOLD,20));
        label13.setBounds(180,110,140,20);
        panel.add(label13);

        JLabel label14=new JLabel("Price");
        label14.setFont(new Font("serif",Font.BOLD,20));
        label14.setBounds(350,110,140,20);
        panel.add(label14);

        JLabel label15=new JLabel("Bed type");
        label15.setFont(new Font("serif",Font.BOLD,20));
        label15.setBounds(530,110,140,20);
        panel.add(label15);
//        JLabel Status1=new JLabel("Search For Room");
//        Status1.setBounds(250,11,186,31);
//        Status1.setForeground(Color.black);
//        Status1.setFont(new Font("Tahoma",Font.BOLD,20));
//        panel.add(Status1);

        choice=new Choice();
        choice.setBounds(170,70,120,20);
        choice.add("Available");
        choice.add("Occupied");
        choice.setFocusable(false);
        panel.add(choice);



        table=new JTable();
        table.setBounds(0,140,700,210);
        table.setBackground(new Color(176,196,222));
        //table.setFont(new Font("tahoma", Font.PLAIN,16));
        table.setFont(new Font("tahoma",Font.PLAIN,18));
        table.setForeground(Color.BLACK);
        panel.add(table);


        try{

            Conn c=new Conn();
            String q=" Select * from room";
            ResultSet rs=c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(rs));

        }
        catch (Exception e){
            e.printStackTrace();
        }

        JButton button=new JButton("Search");
        button.setBounds(200,390,120,30);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFocusable(false);
        button.setFont(new Font("serif",Font.BOLD,15));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Objects.equals(choice.getSelectedItem(), "Available")){

                    Conn c=new Conn();
                    try {
                        String q = " Select * from room where Availability='true'";
                        ResultSet rs = c.statement.executeQuery(q);
                        table.setModel(DbUtils.resultSetToTableModel(rs));
                    }
                    catch(Exception E){
                        E.printStackTrace();
                    }
                }
                else{
                    Conn c=new Conn();
                    try {
                        String q2 = " Select * from room where Availability='false'";
                        ResultSet rs = c.statement.executeQuery(q2);
                        table.setModel(DbUtils.resultSetToTableModel(rs));
                    }
                    catch(Exception E){
                        E.printStackTrace();
                    }
                }


            }
        });
        panel.add(button);


        JButton button2=new JButton("Back");
        button2.setBounds(400,390,120,30);
        button2.setBackground(Color.BLACK);
        button2.setForeground(Color.WHITE);
        button2.setFocusable(false);
        button2.setFont(new Font("serif",Font.BOLD,15));
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        panel.add(button2);



        setUndecorated(true);
        setSize(700,500);
        setLocation(350,250);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new SearchRoom();
    }
}
