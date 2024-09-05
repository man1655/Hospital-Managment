package Hospital.Managment.System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Objects;

public class Hospital_Ambulance extends JFrame {

    Choice choice;
    Hospital_Ambulance(){
        JPanel panel=new JPanel();
        panel.setBounds(5,5,880,490);
        panel.setBackground(new Color(176, 196, 222));
        panel.setLayout(null);
        add(panel);

        JTable table=new JTable();
        table.setBackground(new Color(176,196,222));
        table.setBounds(0,190,880,180);
        table.setFont(new Font("tahoma",Font.PLAIN,18));
        panel.add(table);

        try{
            Conn c=new Conn();
            String q=" select * from Ambulance";
            ResultSet rs=c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(rs));


        }
        catch (Exception e){
            e.printStackTrace();
        }


        ImageIcon i11=new ImageIcon(ClassLoader.getSystemResource("icon/amb.png"));
        Image image1=i11.getImage().getScaledInstance(300,150,Image.SCALE_DEFAULT);
        ImageIcon i21=new ImageIcon(image1);
        JLabel label112=new JLabel(i21);
        label112.setBounds(600,0,300,150);
        panel.add(label112);


        JLabel label=new JLabel("Ambulance Services");
        label.setBounds(200,5,230,30);
        label.setFont(new Font("SERIF",Font.BOLD,20));
         panel.add(label);


        JLabel label1=new JLabel("Types :");
        label1.setBounds(40,80,100,20);
        label1.setFont(new Font("SERIF",Font.BOLD,20));
        panel.add(label1);

        choice=new Choice();
        choice.setBounds(150,80,150,20);
        choice.setFont(new Font("tahoma",Font.PLAIN,15));
        choice.setBackground(Color.BLACK);
        choice.setForeground(Color.white);
        choice.setFocusable(false);
        //choice.add("ENOVA");
        choice.add("OMNI");
        choice.add("G-Wagon");
        panel.add(choice);


        JLabel label2=new JLabel("Name");
        label2.setBounds(5,150,150,30);
        label2.setFont(new Font("SERIF",Font.BOLD,20));
        panel.add(label2);


        JLabel label3=new JLabel("Gender");
        label3.setBounds(190,150,150,30);
        label3.setFont(new Font("SERIF",Font.BOLD,20));
        panel.add(label3);


        JLabel label4=new JLabel("Vehicle");
        label4.setBounds(360,150,150,30);
        label4.setFont(new Font("SERIF",Font.BOLD,20));
        panel.add(label4);





        JLabel label6=new JLabel("Available");
        label6.setBounds(555,150,150,30);
        label6.setFont(new Font("SERIF",Font.BOLD,20));
        panel.add(label6);


        JLabel label7=new JLabel("Location");
        label7.setBounds(770,150,150,30);
        label7.setFont(new Font("SERIF",Font.BOLD,20));
        panel.add(label7);

        JButton button=new JButton("Display");
        button.setBounds(250,390,120,30);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFocusable(false);
        button.setFont(new Font("serif",Font.BOLD,15));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Conn c=new Conn();

                    if (Objects.equals(choice.getSelectedItem(), "OMNI")){
                        ResultSet rs=c.statement.executeQuery("select * from Ambulance where Car_name='"+choice.getSelectedItem()+"' ");
                     table.setModel(DbUtils.resultSetToTableModel(rs));
                    }
                    else{
                        ResultSet rs=c.statement.executeQuery("select * from Ambulance where Car_name='"+choice.getSelectedItem()+"' ");
                        table.setModel(DbUtils.resultSetToTableModel(rs));
                    }

                }
                catch (Exception E){
                    E.printStackTrace();
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
        setSize(890,500);
        setLocation(200,250);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Hospital_Ambulance();
    }
}
