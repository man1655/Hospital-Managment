package Hospital.Managment.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends JFrame implements ActionListener {

    JTextField textField;
    JPasswordField jpasswordField;
    JButton b1, b2, b3;

    // Constructor
    Login() {

        ImageIcon icon2 = new ImageIcon("D://Hospital Managment//Hospital Managment System//src//Icon//download.jpg");
        setIconImage(icon2.getImage());

        // For username
        JLabel labelname = new JLabel("Username");
        labelname.setBounds(40, 20, 100, 30);
        labelname.setFont(new Font("mvBoli", Font.BOLD, 16));
        labelname.setForeground(Color.BLACK);
        add(labelname);

        // For password
        JLabel labelpass = new JLabel("Password");
        labelpass.setBounds(40, 70, 100, 30);
        labelpass.setForeground(Color.BLACK);
        labelpass.setFont(new Font("mvBoli", Font.BOLD, 16));
        add(labelpass);

        textField = new JTextField();
        textField.setBounds(150, 20, 150, 30);
        textField.setMargin(new Insets(0, 6, 0, 6));
        textField.setFont(new Font("mv Boli", Font.PLAIN, 16));
        textField.setBackground(new Color(255, 179, 0));
        add(textField);

        jpasswordField = new JPasswordField();
        jpasswordField.setBounds(150, 70, 150, 30);
        jpasswordField.setMargin(new Insets(0, 6, 0, 6));
        jpasswordField.setBackground(new Color(255, 179, 0));
        add(jpasswordField);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon/dr.png"));
        Image i1 = icon.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon icon1 = new ImageIcon(i1);

        JLabel label = new JLabel(icon1);
        label.setBounds(300, -10, 400, 500);
        label.setVerticalAlignment(JLabel.TOP);
        label.setIconTextGap(-40);
        add(label);

        b1 = new JButton("Login");
        b1.setBounds(40, 140, 120, 30);
        b1.setFont(new Font("serif", Font.BOLD, 15));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Signup");
        b2.setBounds(180, 140, 120, 30);
        b2.setFont(new Font("serif", Font.BOLD, 15));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        add(b2);

        b3 = new JButton("Forgot Password");
        b3.setBounds(80, 200, 180, 30);
        b3.setFont(new Font("serif", Font.BOLD, 15));
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        b3.addActionListener(this);
        add(b3);

        getContentPane().setBackground(new Color(109, 164, 170));

        setSize(650, 300);
        setLocation(400, 270);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            // Login logic
            try {
                String user = textField.getText();
                String pass = new String(jpasswordField.getPassword()); // Retrieve password from JPasswordField

                // Check in database
                Conn c = new Conn();
                String sql = "SELECT * FROM login WHERE ID = '" + user + "' AND PW = '" + pass + "'";
                ResultSet rs = c.statement.executeQuery(sql);
                if (rs.next()) {
                    new Reception();
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else if (e.getSource() == b2) {
            // Signup logic
            try {
                String user = textField.getText();
                String pass = new String(jpasswordField.getPassword()); // Retrieve password from JPasswordField

                // Check username and password length
                if (user.length() <= 5 || pass.length() <= 5) {
                    JOptionPane.showMessageDialog(null, "Username and password must be greater than 5 characters.");
                    return;
                }

                // Check if username already exists
                Conn c = new Conn();
                String checkSql = "SELECT * FROM login WHERE ID = '" + user + "'";
                ResultSet rs = c.statement.executeQuery(checkSql);

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Username already exists.");
                } else {
                    // Insert new user into the database
                    String insertSql = "INSERT INTO login (ID, PW) VALUES ('" + user + "', '" + pass + "')";
                    c.statement.executeUpdate(insertSql);
                    JOptionPane.showMessageDialog(null, "Signup successful.");

                    // Clear fields after successful signup
                    textField.setText("");
                    jpasswordField.setText("");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else if (e.getSource() == b3) {
            // Forgot Password logic
            try {
                String user = textField.getText();

                // Check if username exists
                Conn c = new Conn();
                String checkSql = "SELECT * FROM login WHERE ID = '" + user + "'";
                ResultSet rs = c.statement.executeQuery(checkSql);

                if (rs.next()) {
                    // Username exists, allow user to reset password
                    String newPassword = JOptionPane.showInputDialog(null, "Enter your new password:");
                    String resetSql = "UPDATE login SET PW = '" + newPassword + "' WHERE ID = '" + user + "'";
                    c.statement.executeUpdate(resetSql);
                    JOptionPane.showMessageDialog(null, "Password successfully reset.");
                } else {
                    // Username does not exist
                    JOptionPane.showMessageDialog(null, "Username not found.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
}
