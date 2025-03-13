package Front;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class RestaurantScreen {
    private JPanel restaurantScreen;
    private CardLayout cardLayout;
    private JPanel panelContainer;

    public RestaurantScreen(CardLayout cardLayout, JPanel panelContainer) {
        this.cardLayout = cardLayout;
        this.panelContainer = panelContainer;
        initializeComponents();
    }

    private void initializeComponents() {
        restaurantScreen = new JPanel(new BorderLayout());
        restaurantScreen.add(createTopBar(), BorderLayout.NORTH);
        restaurantScreen.add(createImagePanel(), BorderLayout.CENTER);
        restaurantScreen.add(createBottomBar(), BorderLayout.SOUTH);
    }

    private JPanel createTopBar() {
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(204, 153, 255));
        topBar.setPreferredSize(new Dimension(0, 50));

        JLabel label = new JLabel("Food Fighter", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 30));

        JButton adminButton = createAdminButton();

        topBar.add(label, BorderLayout.CENTER);
        topBar.add(adminButton, BorderLayout.EAST);
        return topBar;
    }

    private JButton createAdminButton() {
        JButton adminButton = new JButton("ADMIN");
        adminButton.setFont(new Font("Arial", Font.BOLD, 15));
        adminButton.setBackground(new Color(204, 153, 255));
        adminButton.setForeground(Color.BLACK);
        adminButton.setPreferredSize(new Dimension(100, 40));

        adminButton.addActionListener(e -> showAdminLoginDialog());
        return adminButton;
    }

    private void showAdminLoginDialog() {
        JPasswordField passwordField = new JPasswordField();
        Object[] message = {"Enter Password", passwordField};

        int result = JOptionPane.showConfirmDialog(
                null, message, "Admin Page",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String enteredPassword = new String(passwordField.getPassword());
            if ("0000".equals(enteredPassword)) {
                JOptionPane.showMessageDialog(null, "Access Granted");
                cardLayout.show(panelContainer, "AdminScreen");
            } else {
                JOptionPane.showMessageDialog(null, "Access Denied", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private JPanel createImagePanel() {
        JPanel imagePanel = new JPanel(new GridLayout(2, 2, 10, 10));
        imagePanel.setBackground(Color.WHITE);

        String[] restaurantNames = {"BurgerKing", "Bongousse", "IsaacToast", "Puradak"};
        String[] imagePaths = {
                "src/assets/image/burgerking.jpg",
                "src/assets/image/bongousse.jpg",
                "src/assets/image/isaactoast.jpg",
                "src/assets/image/puradak.jpg"
        };

        for (int i = 0; i < restaurantNames.length; i++) {
            JLabel imageLabel = new JLabel(resizeImageToFitLabel(new ImageIcon(imagePaths[i]), 150, 150));
            addRestaurantClickListener(imageLabel, restaurantNames[i]);
            imagePanel.add(imageLabel);
        }

        return imagePanel;
    }

    private void addRestaurantClickListener(JLabel label, String restaurantName) {
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                try {
                    updateItemListScreen(restaurantName);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                cardLayout.show(panelContainer, "ItemListScreen");
            }
        });
    }

    private JPanel createBottomBar() {
        JPanel bottomBar = new JPanel();
        bottomBar.setBackground(new Color(204, 153, 255));
        bottomBar.setPreferredSize(new Dimension(0, 50));
        return bottomBar;
    }

    private void updateItemListScreen(String restaurantName) throws IOException {
        for (Component component : panelContainer.getComponents()) {
            if (component instanceof ItemListScreen) {
                ((ItemListScreen) component).setRestaurantInfo(restaurantName);
            }
        }
    }

    private ImageIcon resizeImageToFitLabel(ImageIcon originalImage, int width, int height) {
        Image image = originalImage.getImage();
        int newWidth = width;
        int newHeight = height;
        double aspectRatio = (double) image.getWidth(null) / image.getHeight(null);

        if (width / aspectRatio <= height) {
            newHeight = (int) (width / aspectRatio);
        } else {
            newWidth = (int) (height * aspectRatio);
        }

        return new ImageIcon(image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
    }

    public JPanel getRestaurantScreen() {
        return restaurantScreen;
    }
}
