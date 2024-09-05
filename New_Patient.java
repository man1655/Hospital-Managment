package Hospital.Managment.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;
import java.sql.*;

public class New_Patient extends JFrame implements ActionListener {
    JComboBox<String> comboBox;
    JTextField textFieldnumber, textname, textfeilddiseas, textfeilddeposite;
    JRadioButton r1, r2;
    Choice c1;
    ButtonGroup genderGroup;
    JLabel date;
    JButton button, button2;

    public New_Patient() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 840, 540);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        // Patient image
        JLabel label = createImageLabel("icon/patient.png", 550, 150, 200, 200);
        panel.add(label);

        JLabel labelname = new JLabel("NEW PATIENT FORM");
        labelname.setBounds(100, 5, 260, 53);
        labelname.setFont(new Font("tahoma", Font.BOLD, 20));
        panel.add(labelname);

        addFormLabels(panel);

        comboBox = new JComboBox<>(new String[]{"Adhar Card", "Voter Id", "Driving Licence"});
        comboBox.setBounds(220, 80, 150, 30);
        comboBox.setBackground(new Color(3, 45, 48));
        comboBox.setForeground(Color.WHITE);
        panel.add(comboBox);

        textFieldnumber = new JTextField();
        textFieldnumber.setBounds(220, 120, 150, 30);
        panel.add(textFieldnumber);

        textname = new JTextField();
        textname.setBounds(220, 160, 150, 30);
        panel.add(textname);

        r1 = new JRadioButton("Male");
        r1.setFont(new Font("Tahoma", Font.BOLD, 14));
        r1.setForeground(Color.WHITE);
        r1.setBackground(new Color(109, 164, 170));
        r1.setBounds(220, 200, 80, 12);
        panel.add(r1);

        r2 = new JRadioButton("Female");
        r2.setFont(new Font("Tahoma", Font.BOLD, 14));
        r2.setForeground(Color.WHITE);
        r2.setBackground(new Color(109, 164, 170));
        r2.setBounds(300, 200, 80, 12);
        panel.add(r2);

        genderGroup = new ButtonGroup();
        genderGroup.add(r1);
        genderGroup.add(r2);

        textfeilddiseas = new JTextField();
        textfeilddiseas.setBounds(220, 235, 150, 30);
        panel.add(textfeilddiseas);

        c1 = new Choice();
        loadRoomChoices();
        c1.setBounds(220, 280, 150, 30);
        c1.setFont(new Font("Tahoma", Font.BOLD, 18));
        c1.setForeground(Color.white);
        c1.setBackground(new Color(3, 45, 48));
        panel.add(c1);

        Date date1 = new Date();
        date = new JLabel("" + date1);
        date.setBounds(220, 322, 300, 17);
        date.setForeground(Color.white);
        date.setFont(new Font("tahoma", Font.BOLD, 16));
        panel.add(date);

        textfeilddeposite = new JTextField();
        textfeilddeposite.setBounds(220, 360, 150, 30);
        panel.add(textfeilddeposite);

        button = new JButton("ADD");
        button.setBounds(35, 420, 150, 30);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFocusable(false);
        button.setFont(new Font("serif", Font.BOLD, 15));
        button.addActionListener(this);
        panel.add(button);

        button2 = new JButton("Back");
        button2.setBounds(250, 420, 150, 30);
        button2.setBackground(Color.BLACK);
        button2.setForeground(Color.WHITE);
        button2.setFocusable(false);
        button2.setFont(new Font("serif", Font.BOLD, 15));
        button2.addActionListener(this);
        panel.add(button2);

        setUndecorated(true);
        setSize(850, 500);
        setLayout(null);
        setLocation(250, 220);
        setVisible(true);
        setResizable(false);
    }

    private void loadRoomChoices() {
        try {
            Conn c = new Conn();
            ResultSet rs = c.statement.executeQuery("SELECT * FROM room where Availability ='true'");
            while (rs.next()) {
                c1.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add form labels
    private void addFormLabels(JPanel panel) {
        String[] labels = {"ID :", "Number", "Name:", "Gender:", "Patient Disease:", "Allocated Room No:", "Time :", "Deposit:"};
        int[] positions = {80, 120, 160, 200, 240, 280, 320, 360};
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setBounds(5, positions[i], 200, 30);
            label.setFont(new Font("tahoma", Font.BOLD, 16));
            panel.add(label);
        }
    }

    // Method to create image label
    private JLabel createImageLabel(String path, int x, int y, int width, int height) {
        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource(path));
        Image icon = image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(icon);
        JLabel label = new JLabel(imageIcon);
        label.setBounds(x, y, width, height);
        return label;
    }

    public static void main(String[] args) {
        new New_Patient();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            addPatient();
        } else if (e.getSource() == button2) {
            setVisible(false);
        }
    }

    private void addPatient() {
        Conn c = new Conn();
        String gender = r1.isSelected() ? "male" : (r2.isSelected() ? "female" : "");
        String idType = (String) comboBox.getSelectedItem();
        String idNumber = textFieldnumber.getText();
        String name = textname.getText();
        String disease = textfeilddiseas.getText();
        String roomNumber = c1.getSelectedItem();
        String admissionTime = date.getText();
        String deposit = textfeilddeposite.getText();

        // Add patient to the static LinkedList
        Patient patient = new Patient(idType, idNumber, name, gender, disease, roomNumber, admissionTime, deposit);
        LinkedList.addLast(patient); // Use static method from LinkedList class

        try {
            String query1 = "INSERT INTO Patient_info VALUES('" + idType + "','" + idNumber + "','" + name + "','" + gender + "','" + disease + "','" + roomNumber + "','" + admissionTime + "','" + deposit + "')";
            String query2 = "UPDATE room SET Availability = 'false' WHERE Room_no = '" + roomNumber + "'";
            String query3 = "INSERT INTO Available_Patient VALUES('" + idType + "','" + idNumber + "','" + name + "','" + gender + "','" + disease + "','" + roomNumber + "','" + admissionTime + "','" + deposit + "')";

            c.statement.executeUpdate(query1);
            c.statement.executeUpdate(query2);
            c.statement.executeUpdate(query3);

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    JOptionPane.showMessageDialog(null, "Added successfully");
                    setVisible(false);
                }
            });
        } catch (SQLException ex) {
            if (ex.getMessage().contains("Deposit amount must be less than the room price")) {
                JOptionPane.showMessageDialog(null, "Deposit amount must be less than the room price.");
            }
        } catch (Exception ex) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            });
            ex.printStackTrace();
        }
    }

    // Patient class
    static class Patient {
        private String idType;
        private String idNumber;
        private String name;
        private String gender;
        private String disease;
        private String roomNumber;
        private String admissionTime;
        private String deposit;

        public Patient(String idType, String idNumber, String name, String gender, String disease, String roomNumber, String admissionTime, String deposit) {
            this.idType = idType;
            this.idNumber = idNumber;
            this.name = name;
            this.gender = gender;
            this.disease = disease;
            this.roomNumber = roomNumber;
            this.admissionTime = admissionTime;
            this.deposit = deposit;
        }

        // Getters
        public String getIdType() {
            return idType;
        }

        public String getIdNumber() {
            return idNumber;
        }

        public String getName() {
            return name;
        }

        public String getGender() {
            return gender;
        }

        public String getDisease() {
            return disease;
        }

        public String getRoomNumber() {
            return roomNumber;
        }

        public String getAdmissionTime() {
            return admissionTime;
        }

        public String getDeposit() {
            return deposit;
        }
    }
}
