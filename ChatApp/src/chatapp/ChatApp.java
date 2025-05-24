package chatapp;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.regex.*;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;

public class ChatApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}

class DBConnection {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp_database", "root", "Tebalelo123@");
    }
}

class LoginFrame extends JFrame {
    public LoginFrame() {
        setTitle("Login / Register");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField phoneField = new JTextField();

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        panel.add(new JLabel("Username:")); panel.add(usernameField);
        panel.add(new JLabel("Password:")); panel.add(passwordField);
        panel.add(new JLabel("Phone Number:")); panel.add(phoneField);
        panel.add(loginButton); panel.add(registerButton);

        loginButton.setBackground(Color.BLUE);
        loginButton.setForeground(Color.WHITE);
        registerButton.setBackground(Color.BLUE);
        registerButton.setForeground(Color.WHITE);

        add(panel, BorderLayout.CENTER);

        loginButton.addActionListener(e -> {
            try (Connection conn = DBConnection.getConnection()) {
                String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, usernameField.getText());
                stmt.setString(2, new String(passwordField.getPassword()));
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    new DashboardFrame(usernameField.getText());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid credentials");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String phone = phoneField.getText();

            if (!username.contains("_") || username.length() > 5) {
                JOptionPane.showMessageDialog(this, "Username must contain '_' and be max 5 chars");
                return;
            }

            if (!Pattern.matches("(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}", password)) {
                JOptionPane.showMessageDialog(this, "Password must be at least 8 characters, with a capital letter, number, and special character.");
                return;
            }

            if (!Pattern.matches("\\+27[6-8][0-9]{8}", phone)) {
                JOptionPane.showMessageDialog(this, "Phone must be a valid South African number with international code");
                return;
            }

            try (Connection conn = DBConnection.getConnection()) {
                String sql = "INSERT INTO users (username, password, phone) VALUES (?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.setString(3, phone);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Registered successfully!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        setVisible(true);
    }
}

class DashboardFrame extends JFrame {
    public DashboardFrame(String username) {
        setTitle("Dashboard - Welcome " + username);
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel("Welcome to Quick Chat", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JButton sendMsgBtn = new JButton("Send Message");
        JButton checkMsgBtn = new JButton("Check Recently Sent Messages");
        JButton quickBtn = new JButton("Quick");

        sendMsgBtn.setBackground(Color.BLUE);
        sendMsgBtn.setForeground(Color.WHITE);
        checkMsgBtn.setBackground(Color.BLUE);
        checkMsgBtn.setForeground(Color.WHITE);
        quickBtn.setBackground(Color.BLUE);
        quickBtn.setForeground(Color.WHITE);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.add(welcomeLabel);
        panel.add(sendMsgBtn);
        panel.add(checkMsgBtn);
        panel.add(quickBtn);
        add(panel);

        sendMsgBtn.addActionListener(e -> {
            new UserListFrame(username);
            dispose();
        });

        checkMsgBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Coming Soon");
        });

        quickBtn.addActionListener(e -> System.exit(0));

        setVisible(true);
    }
}

class UserListFrame extends JFrame {
    private String selectedUser;
    private String selectedPhone;
    private String senderUsername;
    private JList<String> userList;

    public UserListFrame(String senderUsername) {
        this.senderUsername = senderUsername;
        setTitle("Select a User to Message");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        
      
        listModel.addElement("Tebz_ - +27681234567");
        listModel.addElement("Suki_ - +27781234567");
        
  
        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT username, phone FROM users WHERE username != '" + senderUsername + "'");
            while (rs.next()) {
                String username = rs.getString("username");
                String phone = rs.getString("phone");
                listModel.addElement(username + " - " + phone);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        userList = new JList<>(listModel);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(userList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton openChatBtn = new JButton("Open Chat");
        JButton backBtn = new JButton("Back");
        
        openChatBtn.setBackground(Color.BLUE);
        openChatBtn.setForeground(Color.WHITE);
        backBtn.setBackground(Color.BLUE);
        backBtn.setForeground(Color.WHITE);

        buttonPanel.add(backBtn);
        buttonPanel.add(openChatBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        userList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selected = userList.getSelectedValue();
                if (selected != null) {
                    String[] parts = selected.split(" - ");
                    this.selectedUser = parts[0];
                    this.selectedPhone = parts[1];
                }
            }
        });

        openChatBtn.addActionListener(e -> {
            if (selectedUser == null) {
                JOptionPane.showMessageDialog(this, "Please select a user first");
                return;
            }
            new MessengerFrame(senderUsername, selectedUser, selectedPhone);
            dispose();
        });

        backBtn.addActionListener(e -> {
            new DashboardFrame(senderUsername);
            dispose();
        });

        setVisible(true);
    }
}

class MessengerFrame extends JFrame {
    JTextArea chatArea;
    Timer timer = new Timer();
    String currentSender;
    String currentReceiver;

    public MessengerFrame(String sender, String receiver, String receiverPhone) {
        this.currentSender = sender;
        this.currentReceiver = receiver;

        setTitle("Chat with " + receiver + " (" + receiverPhone + ")");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JTextField inputField = new JTextField();
        JButton sendBtn = new JButton("Send");
        JButton backBtn = new JButton("Back");
        sendBtn.setBackground(Color.BLUE);
        sendBtn.setForeground(Color.WHITE);
        backBtn.setBackground(Color.BLUE);
        backBtn.setForeground(Color.WHITE);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendBtn, BorderLayout.EAST);

        add(backBtn, BorderLayout.NORTH);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        loadMessages();
        startMessagePolling();

        sendBtn.addActionListener(e -> {
            String msg = inputField.getText().trim();
            if (msg.isEmpty()) return;
            
            try (Connection conn = DBConnection.getConnection()) {
                JSONObject json = new JSONObject();
                json.put("from", currentSender);
                json.put("to", currentReceiver);
                json.put("message", msg);
                
                String sql = "INSERT INTO messages (sender, receiver, content, delivered, read_status) VALUES (?, ?, ?, TRUE, FALSE)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, currentSender);
                stmt.setString(2, currentReceiver);
                stmt.setString(3, json.toString());
                stmt.executeUpdate();
                
                chatArea.append("Me: " + msg + " ✓\n");
                inputField.setText("");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        backBtn.addActionListener(e -> {
            timer.cancel();
            new UserListFrame(currentSender);
            dispose();
        });

        setVisible(true);
    }

    private void loadMessages() {
        try (Connection conn = DBConnection.getConnection()) {
            String sentSql = "SELECT * FROM messages WHERE sender = ? AND receiver = ?";
            PreparedStatement sentStmt = conn.prepareStatement(sentSql);
            sentStmt.setString(1, currentSender);
            sentStmt.setString(2, currentReceiver);
            ResultSet sentRs = sentStmt.executeQuery();

            while (sentRs.next()) {
                JSONObject json = new JSONObject(sentRs.getString("content"));
                boolean delivered = sentRs.getBoolean("delivered");
                boolean read = sentRs.getBoolean("read_status");
                String ticks = read ? " ✓✓ (read)" : delivered ? " ✓✓" : " ✓";
                chatArea.append("Me: " + json.getString("message") + ticks + "\n");
            }

         
            String receivedSql = "SELECT * FROM messages WHERE sender = ? AND receiver = ? AND delivered = TRUE";
            PreparedStatement receivedStmt = conn.prepareStatement(receivedSql);
            receivedStmt.setString(1, currentReceiver);
            receivedStmt.setString(2, currentSender);
            ResultSet receivedRs = receivedStmt.executeQuery();

            while (receivedRs.next()) {
                JSONObject json = new JSONObject(receivedRs.getString("content"));
                chatArea.append(currentReceiver + ": " + json.getString("message") + "\n");
                
               
                String updateSql = "UPDATE messages SET read_status = TRUE WHERE id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, receivedRs.getInt("id"));
                updateStmt.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void startMessagePolling() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try (Connection conn = DBConnection.getConnection()) {
                    String sql = "SELECT * FROM messages WHERE sender = ? AND receiver = ? AND delivered = FALSE";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, currentReceiver);
                    stmt.setString(2, currentSender);
                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()) {
                        JSONObject msg = new JSONObject(rs.getString("content"));
                        
                        SwingUtilities.invokeLater(() -> {
                            chatArea.append(currentReceiver + ": " + msg.getString("message") + "\n");
                        });

                    
                        String updateSql = "UPDATE messages SET delivered = TRUE, read_status = TRUE WHERE id = ?";
                        PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                        updateStmt.setInt(1, rs.getInt("id"));
                        updateStmt.executeUpdate();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 3000);
    }
}