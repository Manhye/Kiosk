package Front;

import Back.Restaurant;
import Back.MenuItems;
import Back.Server;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;


public class AdminScreen extends JPanel { ;
    private CardLayout cardLayout;
    private JPanel adminScreen;
    private JPanel panelContainer;


    public AdminScreen(CardLayout cardLayout, JPanel panelContainer){
        this.cardLayout = cardLayout;
        this.panelContainer = panelContainer;
        initializeComponents();
    }

    private void initializeComponents(){
        adminScreen = new  JPanel(new BorderLayout());
        adminScreen.add(createTopBar(),BorderLayout.NORTH);
        adminScreen.add(createButtonPanel(),BorderLayout.CENTER);
    }

    private JButton addButton() {
        JButton addButton = new JButton("Add Menu");
        addButton.setFont(new Font("Arial", Font.BOLD, 20));
        addButton.setBackground(new Color(204, 153, 255));
        addButton.setPreferredSize(new Dimension(100, 40));


        addButton.addActionListener(e -> {
            try {
                showAdd();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        return addButton;
    }

    private JButton delButton() {
        JButton delButton = new JButton("Delete Menu");
        delButton.setFont(new Font("Arial", Font.BOLD, 20));
        delButton.setBackground(new Color(204, 153, 255));
        delButton.setPreferredSize(new Dimension(100, 40));

        delButton.addActionListener(e -> {
            try {
                showDel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        return delButton;
    }

    private void showAdd() throws IOException {
        JTextField name = new JTextField();
        JTextField price = new JTextField();
        JTextField description = new JTextField();
        Restaurant restaurant = new Restaurant();
        List<String> categories;
        categories = restaurant.getCategories();
        JComboBox<String> comboBox = new JComboBox<>();
        for(String cat: categories){
            comboBox.addItem(cat);
        }

        Object[] message = {
                "Restaurant:", comboBox,
                "Name:", name,
                "Price:", price,
                "Description:", description
        };

        int result = JOptionPane.showConfirmDialog(
                null,message, "Input Dialog",
                JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE
        );
        if(result == JOptionPane.OK_OPTION){
            String sRestaurantName = (String) comboBox.getSelectedItem();
            String sName = name.getText();
            String sPrice = price.getText();
            String sDescription = description.getText();
            MenuItems items = new MenuItems(sName,Integer.parseInt(sPrice),sDescription);

            Server server = new Server();
            server.save(sRestaurantName,items);
        }

    }

    private void showDel() throws IOException {
        Restaurant restaurant = new Restaurant();
        List<String> categories;
        categories = restaurant.getCategories();
        JTextField name = new JTextField();

        JComboBox<String> comboBox = new JComboBox<>();
        for(String cat: categories){
            comboBox.addItem(cat);
        }

        Object[] message = {
                "Restaurant:", comboBox,
                "Name:", name
        };

        int result = JOptionPane.showConfirmDialog(
                null,message, "Input Dialog",
                JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE
        );
        if(result == JOptionPane.OK_OPTION){
            String sRestaurantName = (String) comboBox.getSelectedItem();
            String sName = name.getText();
            Server server = new Server();
            if(server.deleteItem(sRestaurantName,sName)){
                JOptionPane.showMessageDialog(null,"Item deleted successfully");
            }else{
                JOptionPane.showMessageDialog(null,"Item does not exist");
            }
        }

    }

    private JPanel createButtonPanel(){
        JPanel buttonPanel = new JPanel(new GridLayout(2,1,0,0));
        JButton addButton = addButton();
        JButton delButton = delButton();
        buttonPanel.add(addButton);
        buttonPanel.add(delButton);
        return buttonPanel;
    }




    private JPanel createTopBar() {
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(204, 153, 255));
        topBar.setPreferredSize(new Dimension(0, 50));

        JLabel label = new JLabel("ADMIN", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 30));


        JButton backButton = createBackButton(cardLayout, panelContainer);
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        backButtonPanel.setOpaque(false);
        backButtonPanel.add(backButton);

        topBar.add(backButton, BorderLayout.EAST);
        add(topBar, BorderLayout.NORTH);

        topBar.add(label, BorderLayout.CENTER);
        topBar.add(backButton,BorderLayout.EAST);
        return topBar;
    }

    private JButton createBackButton(CardLayout cardLayout, JPanel panelContainer) {
        JButton backButton = new JButton("BACK");
        backButton.setFont(new Font("Arial", Font.BOLD, 15));
        backButton.setBackground(new Color(204, 153, 255));
        backButton.setForeground(Color.BLACK);
        backButton.setPreferredSize(new Dimension(100, 40));

        backButton.addActionListener(e -> cardLayout.show(panelContainer, "RestaurantScreen"));
        return backButton;
    }

    public JPanel getAdminScreen() {
        return adminScreen;
    }

}
