package chatapp;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginFrame extends JFrame {

    public LoginFrame() {
        setTitle("Login");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        loginButton.setBackground(new Color(0, 120, 215));
        loginButton.setForeground(Color.WHITE);
        registerButton.setBackground(new Color(0, 120, 215));
        registerButton.setForeground(Color.WHITE);

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);

        JPanel panel = new JPanel();
        panel.add(loginButton);
        panel.add(registerButton);
        add(panel);

        loginButton.addActionListener(e -> {
            try (Connection conn = Database.connect()) {
                String query = "SELECT * FROM users WHERE username=? AND password=?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, usernameField.getText().trim());
                stmt.setString(2, new String(passwordField.getPassword()));
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    new MessengerFrame(usernameField.getText().trim()).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password.");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        registerButton.addActionListener(e -> {
            new RegisterFrame().setVisible(true);
            dispose();
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

  
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}


