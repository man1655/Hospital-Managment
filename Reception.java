package Hospital.Managment.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Reception extends JFrame {

    private HashMap<String, ActionListener> buttonActions;
    private List<ButtonConfig> buttonConfigs;
    public static Reception r88;

    Reception() {
        // Initializing the button action map
        buttonActions = new HashMap<>();
        buttonConfigs = new ArrayList<>();
        initializeButtonActions();
        initializeButtonConfigs();


        ImageIcon icon = new ImageIcon("D:\\Hospital Managment\\Hospital Managment System\\src\\Icon\\download.jpg");
        setIconImage(icon.getImage());

        // Main panels
        JPanel panel = createMainPanel();
        add(panel);

        JPanel panel1 = createTopPanel();
        add(panel1);

        // Setting frame properties
        setSize(1950, 1090);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setVisible(true);
    }

    // Helper method to create the main panel (bottom panel)
    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(5, 160, 1525, 670);
        panel.setBackground(new Color(135, 206, 235));
        panel.setBorder(BorderFactory.createLineBorder(new Color(112, 128, 144)));

        // Adding image to the panel
        ImageIcon i12 = new ImageIcon(ClassLoader.getSystemResource("icon/Hospital-Management-System.jpg"));
        Image image12 = i12.getImage().getScaledInstance(1525, 680, Image.SCALE_DEFAULT);
        ImageIcon i22 = new ImageIcon(image12);
        JLabel label2 = new JLabel(i22);
        label2.setBounds(0, 0, 1525, 680);
        panel.add(label2);

        return panel;
    }

    // Helper method to create the top panel (upper buttons)
    private JPanel createTopPanel() {
        JPanel panel1 = new JPanel();
        panel1.setBounds(5, 5, 1525, 150);
        panel1.setBackground(new Color(176, 196, 222));
        panel1.setBorder(BorderFactory.createLineBorder(new Color(112, 128, 144)));
        panel1.setLayout(null);

        // Adding the ambulance image
        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("icon/amb.png"));
        Image image1 = i11.getImage().getScaledInstance(300, 150, Image.SCALE_DEFAULT);
        ImageIcon i21 = new ImageIcon(image1);
        JLabel label1 = new JLabel(i21);
        label1.setBounds(1000, 0, 300, 150);
        panel1.add(label1);

        // Adding buttons using the List of button configurations
        for (ButtonConfig config : buttonConfigs) {
            addButton(panel1, config.text, config.x, config.y);
        }

        return panel1;
    }

    // Helper method to initialize the button actions in the HashMap
    private void initializeButtonActions() {
        buttonActions.put("Add New Patient", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new New_Patient();
            }
        });
        buttonActions.put("Room", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Room();
            }
        });
        buttonActions.put("Department", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Department();
            }
        });
        buttonActions.put("Patient Info", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Patient_info1();
            }
        });
        buttonActions.put("All Employee Details", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new All_Employee_info();
            }
        });
        buttonActions.put("Patient discharge", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Pateint_Discharge();
            }
        });
        buttonActions.put("Update patient Details", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Update_Patient_Form();
            }
        });
        buttonActions.put("Hospital Ambulance", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Hospital_Ambulance();
            }
        });
        buttonActions.put("Search Room", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchRoom();
            }
        });
        buttonActions.put("Logout", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Logout();
            }
        });
        buttonActions.put("Available Patient", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Available_Patient();
            }
        });
    }


    private void initializeButtonConfigs() {
        buttonConfigs.add(new ButtonConfig("Add New Patient", 30, 15));
        buttonConfigs.add(new ButtonConfig("Room", 30, 58));
        buttonConfigs.add(new ButtonConfig("Department", 30, 100));
        buttonConfigs.add(new ButtonConfig("Available Patient", 270, 15));
        buttonConfigs.add(new ButtonConfig("All Employee Details", 270, 58));
        buttonConfigs.add(new ButtonConfig("Patient discharge", 270, 100));
        buttonConfigs.add(new ButtonConfig("Update patient Details", 510, 15));
        buttonConfigs.add(new ButtonConfig("Hospital Ambulance", 510, 58));
        buttonConfigs.add(new ButtonConfig("Search Room", 510, 100));
        buttonConfigs.add(new ButtonConfig("Logout", 750, 58));
        buttonConfigs.add(new ButtonConfig("Patient Info",750,15));
    }

    private void addButton(JPanel panel, String text, int x, int y) {
        JButton button = new JButton(text);
        button.setFont(new Font("mvBoli", Font.BOLD, 15));
        button.setFocusable(false);
        button.setBounds(x, y, 200, 30);
        button.setBackground(new Color(240, 240, 128));

        // Retrieve the action from the HashMap and set it for the button
        button.addActionListener(buttonActions.get(text));

        panel.add(button);
    }

    // Custom class to store button configurations
    static class ButtonConfig {
        String text;
        int x, y;

        ButtonConfig(String text, int x, int y) {
            this.text = text;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {

        r88= new Reception();
    }
}
