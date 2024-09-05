package Hospital.Managment.System;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import static Hospital.Managment.System.Reception.r88;

public class Logout extends JFrame {
    Logout(){

        Border border=BorderFactory.createLineBorder(Color.BLACK,3);
        JPanel panel=new JPanel();
        panel.setBounds(5,5,370,190);
        panel.setBackground(new Color(176,196,222));
        panel.setLayout(null);
        add(panel);

        JLabel label=new JLabel("Do You Sure Want To Logout ? ?");
        label.setBounds(5,5,390,70);
        label.setFont(new Font("Mv Boli",Font.ITALIC,20));

        panel.add(label);

        JButton button=new JButton("Logout");
        button.setBounds(30,150,120,30);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFocusable(false);
        button.setFont(new Font("serif",Font.BOLD,15));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Reception.r88.dispose();
               setVisible(false);
                new Login();

            }
        });
        panel.add(button);


        JButton button2=new JButton("Cancel");
        button2.setBounds(200,150,120,30);
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
        setSize(380,200);
        setLocation(500,300);
        setLayout(null);

        setVisible(true);

    }

  public static void main(String[] args) {
        new Logout();
    }
}
