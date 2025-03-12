package Front;

import javax.swing.*;
import java.awt.*;

public class RestaurantScreen {
    private JPanel restaurantScreen;
    private CardLayout cardLayout;
    private JPanel panelContainer;

    public RestaurantScreen(CardLayout cardLayout, JPanel panelContainer) {
        this.cardLayout = cardLayout;
        this.panelContainer = panelContainer;
        createRestaurantScreen();
    }

    private void createRestaurantScreen() {
        restaurantScreen = new JPanel();
        restaurantScreen.setLayout(new BorderLayout());


        //Top Bar
        JPanel topBar = new JPanel();
        topBar.setLayout(new BorderLayout());

        JLabel label = new JLabel("Food Fighter", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        topBar.add(label, BorderLayout.CENTER);
        topBar.setBackground(new Color(204, 153, 255));
        topBar.setPreferredSize(new Dimension(0, 50));

        //Admin Button
        JButton adminButton = new JButton("ADMIN");
        adminButton.setFont(new Font("Arial", Font.BOLD, 15));
        adminButton.setBackground(new Color(204, 153, 255));
        adminButton.setForeground(Color.BLACK);
        adminButton.setPreferredSize(new Dimension(100,40));


        //Admin Button Logic
        adminButton.addActionListener(e->{
            JPasswordField passwordField = new JPasswordField();
            Object[] message = {"Enter Password", passwordField};

            int result = JOptionPane.showConfirmDialog(
                    null,
                    message,
                    "Admin Page",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if(result == JOptionPane.OK_OPTION){
                String enteredPassword = new String(passwordField.getPassword());
                String correctPassword = "0000";
                if(enteredPassword.equals(correctPassword)){
                    JOptionPane.showMessageDialog(null, "Access Granted");
                    cardLayout.show(panelContainer, "AdminScreen");
                }else{
                    JOptionPane.showMessageDialog(null, "Access Denied","Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
        backButtonPanel.setOpaque(false);
        backButtonPanel.add(adminButton);

        topBar.add(adminButton, BorderLayout.EAST);
        restaurantScreen.add(topBar, BorderLayout.NORTH);


        //Image Part
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new GridLayout(2, 2, 10, 10));
        imagePanel.setBackground(Color.WHITE);

        ImageIcon burgerKing = new ImageIcon("src/assets/image/burgerking.jpg");
        ImageIcon bongousse = new ImageIcon("src/assets/image/bongousse.jpg");
        ImageIcon isaacToast = new ImageIcon("src/assets/image/isaactoast.jpg");
        ImageIcon puradak = new ImageIcon("src/assets/image/puradak.jpg");

        burgerKing = resizeImage(burgerKing, 150, 150);
        bongousse = resizeImage(bongousse, 150, 150);
        isaacToast = resizeImage(isaacToast, 150, 150);
        puradak = resizeImage(puradak, 150, 150);

        JLabel imageLabel1 = new JLabel(burgerKing);
        JLabel imageLabel2 = new JLabel(bongousse);
        JLabel imageLabel3 = new JLabel(isaacToast);
        JLabel imageLabel4 = new JLabel(puradak);

        imageLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                updateItemListScreen("BurgerKing");
                cardLayout.show(panelContainer, "ItemListScreen");
            }
        });
        imageLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                updateItemListScreen("Bongousse");
                cardLayout.show(panelContainer, "ItemListScreen");
            }
        });
        imageLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                updateItemListScreen("IsaacToast");
                cardLayout.show(panelContainer, "ItemListScreen");
            }
        });
        imageLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                updateItemListScreen("Puradak");
                cardLayout.show(panelContainer, "ItemListScreen");
            }
        });

        imagePanel.add(imageLabel1);
        imagePanel.add(imageLabel2);
        imagePanel.add(imageLabel3);
        imagePanel.add(imageLabel4);

        restaurantScreen.add(imagePanel, BorderLayout.CENTER);


        //Bottom Bar
        JPanel bottomBar = new JPanel();
        bottomBar.setBackground(new Color(204, 153, 255));
        bottomBar.setPreferredSize(new Dimension(0, 50));
        restaurantScreen.add(bottomBar, BorderLayout.SOUTH);
    }


    //Get restaurants' name
    private void updateItemListScreen(String restaurantName) {
        Component[] components = panelContainer.getComponents();
        for (Component component : components) {
            if (component instanceof ItemListScreen) {
                ((ItemListScreen) component).setRestaurantInfo(restaurantName);
            }
        }
    }


    //Resizes image to fit the label
    private ImageIcon resizeImage(ImageIcon originalImage, int width, int height) {
        Image image = originalImage.getImage();
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);

        double aspectRatio = (double) imageWidth / imageHeight;

        int iWidth = width;
        int iHeight = height;

        if (width / aspectRatio <= height) {
            iHeight = (int) (width / aspectRatio);
        } else {
            iWidth = (int) (height * aspectRatio);
        }

        Image resizedImage = image.getScaledInstance(iWidth, iHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public JPanel getRestaurantScreen() {

        return restaurantScreen;
    }
}
