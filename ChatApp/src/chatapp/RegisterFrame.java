package chatapp;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RegisterFrame extends JFrame {
    public RegisterFrame() {
        setTitle("Register");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(9, 1));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField phoneField = new JTextField();

        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back");

        registerButton.setBackground(new Color(0, 120, 215));
        registerButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(0, 120, 215));
        backButton.setForeground(Color.WHITE);

        add(new JLabel("Username (must contain _ and ≤ 5 chars):"));
        add(usernameField);
        add(new JLabel("Password (min 8 chars, 1 capital, 1 number, 1 special):"));
        add(passwordField);
        add(new JLabel("Phone (+27XXXXXXXXX):"));
        add(phoneField);
        add(registerButton);
        add(backButton);

        registerButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            String phone = phoneField.getText().trim();

            if (!username.matches(".*_.*") || username.length() > 5) {
                JOptionPane.showMessageDialog(this, "Username must contain _ and be ≤ 5 characters.");
                return;
            }

            if (!password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$")) {
                JOptionPane.showMessageDialog(this, "Password is too weak.");
                return;
            }

            if (!phone.matches("^\\+27\\d{9}$")) {
                JOptionPane.showMessageDialog(this, "Phone must be a valid South African number.");
                return;
            }

            try (Connection conn = Database.connect()) {
                String sql = "INSERT INTO users(username, password, phone) VALUES (?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.setString(3, phone);
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Registered successfully!");
                new LoginFrame().setVisible(true);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        backButton.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegisterFrame());
    }
}

