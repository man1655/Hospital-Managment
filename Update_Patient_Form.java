package Hospital.Managment.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Update_Patient_Form extends JFrame {

    Update_Patient_Form(){
        JPanel panel=new JPanel();
        panel.setBounds(5,5,880,490);
        panel.setBackground(new Color(176, 196, 222));
        panel.setLayout(null);
        add(panel);

        ImageIcon image=new ImageIcon("D:\\Hospital Managment\\Hospital Managment System\\src\\Icon\\updated.png");
        Image i1=image.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT);
        ImageIcon icon=new ImageIcon(i1);

        JLabel label=new JLabel(icon);
        label.setBounds(425,5,450,450);
        panel.add(label);

        JLabel label1=new JLabel("Update Patient Form");
        label1.setBounds(170,5,200,30);
        label1.setFont(new Font("Serif",Font.BOLD,20));
        panel.add(label1);

        JLabel label2=new JLabel("Name :");
        label2.setBounds(20,80,180,20);
        label2.setFont(new Font("Serif",Font.BOLD,18));
        panel.add(label2);

        Choice Name =new Choice();
        Name.setBounds(200,80,200,50);
        panel.add(Name);

        try {
            Conn c = new Conn();
            ResultSet rs = c.statement.executeQuery("select * from Patient_info");
            while (rs.next()) {
                Name.add(rs.getString(3));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }

        JLabel label3=new JLabel("Room Number:");
        label3.setBounds(20,130,200,20);
        label3.setFont(new Font("Serif",Font.BOLD,18));
        panel.add(label3);

        JTextField Room_no=new JTextField();
        Room_no.setBounds(200,130,200,30);
        panel.add(Room_no);

        JLabel label4=new JLabel("IN-Time ");
        label4.setBounds(20,170,200,20);
        label4.setFont(new Font("Serif",Font.BOLD,18));
        panel.add(label4);

        JTextField Intime=new JTextField();
        Intime.setBounds(200,170,200,30);

        panel.add(Intime);

        JLabel label5=new JLabel("Amount Paid (Rs):");
        label5.setBounds(20,210,200,20);
        label5.setFont(new Font("Serif",Font.PLAIN,18));
        panel.add(label5);

        JTextField Paid=new JTextField();
        Paid.setBounds(200,210,200,30);
        panel.add(Paid);

        JLabel label6=new JLabel("Pending Amount(Rs):");
        label6.setBounds(20,250,200,20);
        label6.setFont(new Font("Serif",Font.BOLD,18));
        panel.add(label6);

        JTextField Pending_Amount =new JTextField();
        Pending_Amount.setBounds(200,250,200,30);
        panel.add(Pending_Amount);

        JLabel label7=new JLabel("Update Time");
        label7.setBounds(20,290,200,30);
        label7.setFont(new Font("Serif",Font.BOLD,18));
        panel.add(label7);

        JTextField UpdateP=new JTextField();
        UpdateP.setBounds(200,290,200,30);
        panel.add(UpdateP);

        Date date=new Date();
        UpdateP.setText(""+date);
        UpdateP.setFont(new Font("Tahoma",Font.PLAIN,12));

        JButton button=new JButton("Update");
        button.setBounds(30,390,120,30);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFocusable(false);
        button.setFont(new Font("serif",Font.BOLD,15));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Intime.setEditable(false); // Make IN-Time field non-editable
                    Conn c = new Conn();
                    String sen = Name.getSelectedItem();
                    String Room = Room_no.getText();
                    String time2 = Intime.getText(); // Do not update IN-Time
                    String Amount = Paid.getText();

                    c.statement.executeUpdate("update Patient_info set Room_no='" + Room + "',Deposite='" + Amount + "' where Name='" + sen + "'");
                    JOptionPane.showMessageDialog(null, "Updated successfully");
                    setVisible(false);
                } catch (Exception E1) {
                    E1.printStackTrace();
                    JOptionPane.showMessageDialog(null, E1.getMessage());
                }
            }
        });
        panel.add(button);

        JButton button2=new JButton("Back");
        button2.setBounds(170,390,120,30);
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

        JButton button3=new JButton("Check");
        button3.setBounds(310,390,120,30);
        button3.setBackground(Color.BLACK);
        button3.setForeground(Color.WHITE);
        button3.setFocusable(false);
        button3.setFont(new Font("serif",Font.BOLD,15));
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = Name.getSelectedItem();
                try {
                    Conn c = new Conn();
                    String q = "select * from Patient_info where Name='" + id + "'";
                    ResultSet rs = c.statement.executeQuery(q);
                    if (rs.next()) {
                        Room_no.setText(rs.getString("Room_no"));
                        Paid.setText(rs.getString("Deposite"));
                        Intime.setText(rs.getString("Time")); // Show IN-Time
                    }

                    ResultSet rs1 = c.statement.executeQuery("select * from room");
                    if (rs1.next()) {
                        String price = rs1.getString("Price");
                        int amountPaid = Integer.parseInt(price) - Integer.parseInt(Paid.getText());
                        Pending_Amount.setText(String.valueOf(amountPaid));
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });
        panel.add(button3);

        setUndecorated(true);
        setSize(890,500);
        setBackground(Color.BLUE);
        setLocation(200,250);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Update_Patient_Form();
    }
}
