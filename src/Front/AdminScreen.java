package Front;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminScreen extends JPanel {

    public AdminScreen(CardLayout cardLayout, JPanel panelContainer){
        setLayout(new BorderLayout());
        setBackground(new Color(204, 153, 255));

        JLabel label = new JLabel("Admin Screen", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        add(label, BorderLayout.CENTER);

        JButton addButton = new JButton("Add Menu");
        addButton.setFont(new Font("Arial", Font.BOLD, 20));
        addButton.setBackground(new Color(204, 153, 255));
        addButton.setForeground(Color.BLACK);
        addButton.setPreferredSize(new Dimension(200,50));

        JButton delButton = new JButton("Delete Menu");
        addButton.setFont(new Font("Arial", Font.BOLD, 20));
        addButton.setBackground(new Color(204, 153, 255));
        addButton.setForeground(Color.BLACK);
        addButton.setPreferredSize(new Dimension(200,50));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(addButton);
        buttonPanel.add(delButton);

        add(buttonPanel, BorderLayout.CENTER);

    }
}
