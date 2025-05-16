package chatapp;

import javax.swing.*;
import java.awt.*;

public class RecentMessagesFrame extends JFrame {
    public RecentMessagesFrame() {
        setTitle("Recent Messages");
        setSize(400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel comingSoon = new JLabel("Coming Soon", SwingConstants.CENTER);
        comingSoon.setFont(new Font("SansSerif", Font.BOLD, 30));
        comingSoon.setForeground(new Color(0, 120, 215));

        add(comingSoon, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }
}

