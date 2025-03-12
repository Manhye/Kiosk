package Front;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartScreen extends JPanel {
    public StartScreen(CardLayout cardLayout, JPanel panelContainer) {
        setLayout(new BorderLayout());
        setBackground(new Color(204, 153, 255));

        JLabel label = new JLabel("Food Fighter", SwingConstants.CENTER);
        JLabel label2 = new JLabel("Click Anywhere to Start", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label2.setFont(new Font("Arial", Font.BOLD, 10));
        add(label, BorderLayout.CENTER);
        add(label2, BorderLayout.SOUTH);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(panelContainer, "RestaurantScreen");
            }
        });
    }
}
