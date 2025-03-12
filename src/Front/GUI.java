package Front;

import javax.swing.*;
import java.awt.*;

public class GUI {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel panelContainer;

    public GUI() {
        frame = new JFrame("Back.Kiosk Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        cardLayout = new CardLayout();
        panelContainer = new JPanel(cardLayout);

        // Add the screens
        panelContainer.add(new StartScreen(cardLayout, panelContainer), "StartScreen");
        panelContainer.add(new RestaurantScreen(cardLayout, panelContainer).getRestaurantScreen(), "RestaurantScreen");
        panelContainer.add(new ItemListScreen(cardLayout, panelContainer), "ItemListScreen");
        //panelContainer.add(new PaymentScreen(cardLayout, panelContainer), "PaymentScreen");
        panelContainer.add(new AdminScreen(cardLayout, panelContainer), "AdminScreen");

        frame.add(panelContainer);
        frame.setVisible(true);
    }


}
