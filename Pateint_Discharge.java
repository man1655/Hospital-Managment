package Hospital.Managment.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Pateint_Discharge extends JFrame implements ActionListener {
    JTextField textField_id;
    JTextField textFieldRoomno;
    static  Choice c1;
    JLabel label10,label2;
    JLabel Intime;
    JButton buttonDischarge, buttonBack, buttonCheck;
    ResultSet patientRs;

    public Pateint_Discharge() {
        setupUI();
        populateCustomerChoice();
    }

    private void setupUI() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 690, 370);
        panel.setBackground(new Color(176, 196, 222));
        panel.setLayout(null);
        add(panel);

        ImageIcon icon = new ImageIcon("D:\\Hospital Managment\\Hospital Managment System\\src\\Icon\\Discharge.jpg");
        Image i1 = icon.getImage().getScaledInstance(325, 340, Image.SCALE_DEFAULT);
        ImageIcon icon1 = new ImageIcon(i1);

        JLabel label5 = new JLabel(icon1);
        label5.setBounds(345, 0, 450, 350);
        panel.add(label5);

        JLabel label = new JLabel("Check-Out");
        label.setBackground(Color.DARK_GRAY);
        label.setBounds(200, 10, 250, 20);
        label.setFont(new Font("ARIAS", Font.BOLD, 20));
        panel.add(label);

        JLabel label1 = new JLabel("Patient Id");
        label1.setBackground(Color.WHITE);
        label1.setBounds(20, 70, 130, 20);
        label1.setFont(new Font("ARIAS", Font.BOLD, 18));
        panel.add(label1);

        c1 = new Choice();
        c1.setBounds(150, 70, 150, 30);
        c1.setFocusable(false);
        panel.add(c1);

        label2 = new JLabel("Room Number");
        label2.setBackground(Color.WHITE);
        label2.setBounds(20, 120, 250, 20);
        label2.setFont(new Font("ARIAS", Font.BOLD, 18));
        panel.add(label2);

        label10 = new JLabel();
        label10.setBackground(Color.WHITE);
        label10.setBounds(200, 120, 250, 20);
        label10.setFont(new Font("ARIAS", Font.BOLD, 18));
        panel.add(label10);

        JLabel label3 = new JLabel("In-Time");
        label3.setBackground(Color.white);
        label3.setBounds(20, 170, 250, 20);
        label3.setFont(new Font("ARIAS", Font.BOLD, 18));
        panel.add(label3);

        Intime = new JLabel();
        Intime.setBackground(Color.white);
        Intime.setBounds(150, 170, 250, 20);
        Intime.setFont(new Font("ARIAS", Font.BOLD, 18));
        panel.add(Intime);

        JLabel label4 = new JLabel("Out-Time");
        label4.setBackground(Color.WHITE);
        label4.setBounds(20, 220, 250, 20);
        label4.setFont(new Font("ARIAS", Font.BOLD, 18));
        panel.add(label4);

        Date date = new Date();
        JLabel Outtime = new JLabel("" + date);
        Outtime.setBackground(Color.white);
        Outtime.setBounds(150, 220, 250, 20);
        Outtime.setFont(new Font("ARIAS", Font.BOLD, 18));
        panel.add(Outtime);

        buttonDischarge = new JButton("Discharge");
        buttonDischarge.setBackground(Color.black);
        buttonDischarge.setFocusable(false);
        buttonDischarge.setForeground(Color.WHITE);
        buttonDischarge.setBounds(20, 275, 130, 30);
        buttonDischarge.addActionListener(this);
        panel.add(buttonDischarge);

        buttonBack = new JButton("Back");
        buttonBack.setBackground(Color.black);
        buttonBack.setFocusable(false);
        buttonBack.setForeground(Color.WHITE);
        buttonBack.setBounds(150, 275, 130, 30);
        buttonBack.addActionListener(this);
        panel.add(buttonBack);

        buttonCheck = new JButton("Check");
        buttonCheck.setBackground(Color.black);
        buttonCheck.setFocusable(false);
        buttonCheck.setForeground(Color.WHITE);
        buttonCheck.setBounds(280, 275, 130, 30);
        buttonCheck.addActionListener(this);
        panel.add(buttonCheck);

        setUndecorated(true);
        setSize(700, 380);
        setLocation(370, 250);
        setLayout(null);
        setVisible(true);
    }

    private void populateCustomerChoice() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Conn c = new Conn();
                try {
                    ResultSet rs = c.statement.executeQuery("SELECT Number FROM Patient_info");
                    while (rs.next()) {
                        String patientNumber = rs.getString("Number");
                        c1.add(patientNumber);
                        //patientRs = c.statement.executeQuery("SELECT * FROM Patient_info WHERE Number='" + patientNumber + "'");


                        // Fetch patient details and add to LinkedList

                        // Add patient to the LinkedList

                    }
                } catch (SQLException e) {
                  //  JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
                    e.printStackTrace();
                } catch (Exception e) {
                   // JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    private void fetchPatientDetails() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Conn c = new Conn();
                ResultSet rs = null;
                try {
                    String selectedId = c1.getSelectedItem();

                    // Execute the query to get patient details
                    rs = c.statement.executeQuery("SELECT * FROM Patient_info WHERE Number='" + selectedId + "'");

                    if (rs.next()) {
                        // Set patient details to labels
                        label10.setText(rs.getString("Room_no"));
                        Intime.setText(rs.getString("Time"));
                    } else {
                        // Handle case where no data is found
                        label10.setText("No data");
                        Intime.setText("No data");
                    }
                } catch (SQLException e) {
                    // Show error message if there's an issue with the database
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
                        }
                    });
                    e.printStackTrace();
                } catch (Exception e) {
                    // Show error message for any other issues
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                        }
                    });
                    e.printStackTrace();
                } finally {
                    // Close ResultSet and Statement if they are not null
                    try {
                        if (rs != null && !rs.isClosed()) {
                            rs.close();
                        }
                        // Optionally close the Statement and Connection if needed (not shown here)
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

    private void dischargePatient() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Conn c = new Conn();
                ResultSet rs = null;
                try {
                    String selectedId = c1.getSelectedItem();

                    // Retrieve the room number for the patient
                    rs = c.statement.executeQuery("SELECT Room_no FROM Patient_info WHERE Number='" + selectedId + "'");
                    String roomNo = null;
                    if (rs.next()) {
                        roomNo = rs.getString("Room_no");
                    }

                    if (roomNo != null) {
                        // Delete the patient from the database
                        c.statement.executeUpdate("DELETE FROM Patient_info WHERE Number='" + selectedId + "'");

                        // Update room availability
                        c.statement.executeUpdate("UPDATE room SET Availability='true' WHERE Room_no='" + roomNo + "'");

                        // Remove the patient from the linked list


                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                JOptionPane.showMessageDialog(null, "Patient Discharged");
                                setVisible(false);
                            }
                        });
                    } else {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                JOptionPane.showMessageDialog(null, "Patient not found");
                            }
                        });
                    }
                } catch (SQLException e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
                        }
                    });
                    e.printStackTrace();
                } catch (Exception e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                        }
                    });
                    e.printStackTrace();
                } finally {
                    try {
                        if (rs != null && !rs.isClosed()) {
                            rs.close();
                        }
                        // Close the connection if needed (not shown here)
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonDischarge) {
            dischargePatient();
        } else if (e.getSource() == buttonCheck) {
            fetchPatientDetails();
        } else if (e.getSource() == buttonBack) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Pateint_Discharge();
    }
}
