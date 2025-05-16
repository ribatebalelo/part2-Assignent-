package chatapp;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MessengerFrame extends JFrame {
    JTextArea chatArea;
    JTextField messageField;
    String username;

    public MessengerFrame(String username) {
        this.username = username;
        setTitle("Messenger - " + username);
        setSize(500, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // === Top Panel ===
        JPanel topPanel = new JPanel(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to QuickChat", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        welcomeLabel.setForeground(new Color(0, 120, 215));

        JPanel rightButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton recentButton = new JButton("Show Recently Sent Messages");
        JButton quitButton = new JButton("Quit");

        recentButton.setBackground(new Color(0, 120, 215));
        recentButton.setForeground(Color.WHITE);
        quitButton.setBackground(new Color(0, 120, 215));
        quitButton.setForeground(Color.WHITE);

        rightButtonPanel.add(recentButton);
        rightButtonPanel.add(quitButton);

        topPanel.add(welcomeLabel, BorderLayout.CENTER);
        topPanel.add(rightButtonPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // === Chat Area ===
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(chatArea);
        add(scroll, BorderLayout.CENTER);

        // === Bottom Input Panel ===
        JPanel bottomPanel = new JPanel(new BorderLayout());
        messageField = new JTextField();
        JButton sendButton = new JButton("Send");

        sendButton.setBackground(new Color(0, 120, 215));
        sendButton.setForeground(Color.WHITE);

        bottomPanel.add(messageField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        // === Load past messages ===
        loadMessages();

        // === Actions ===
        sendButton.addActionListener(e -> {
            String msg = messageField.getText().trim();
            if (!msg.isEmpty()) {
                saveMessage(username, msg);
                chatArea.append(username + ": " + msg + "\n");
                messageField.setText("");
            }
        });

        quitButton.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });

        recentButton.addActionListener(e -> {
            new RecentMessagesFrame().setVisible(true);
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void saveMessage(String username, String message) {
        try (Connection conn = Database.connect()) {
            String sql = "INSERT INTO messages (username, message) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, message);
            stmt.executeUpdate();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saving message: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void loadMessages() {
        try (Connection conn = Database.connect()) {
            String sql = "SELECT message FROM messages WHERE username = ? ORDER BY timestamp ASC";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String msg = rs.getString("message");
                chatArea.append(username + ": " + msg + "\n");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading messages: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

