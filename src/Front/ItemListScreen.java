package Front;

import Back.Cart;
import Back.Discount;
import Back.MenuItems;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import Back.Server;

public class ItemListScreen extends JPanel {
    private DefaultListModel<List<String>> itemListModel;
    private JList<List<String>> itemList;
    private JPanel cartPanel;
    private JLabel totalPriceLabel;
    private Cart cart;
    private CardLayout cardLayout;
    private JPanel panelContainer;


    public ItemListScreen(CardLayout cardLayout, JPanel panelContainer) {
        this.cardLayout = cardLayout;
        this.panelContainer = panelContainer;

        setLayout(new BorderLayout());
        cart = new Cart();

        // Initialize the components
        initializeTopBar(cardLayout, panelContainer);
        initializeItemList();
        initializeCartPanel();
        initializeCartStatus();
    }

    private void initializeTopBar(CardLayout cardLayout, JPanel panelContainer) {
        JPanel topBar = new JPanel();
        topBar.setLayout(new BorderLayout());
        topBar.setBackground(new Color(204, 153, 255));
        topBar.setPreferredSize(new Dimension(0, 50));

        JLabel label = new JLabel("Food Fighter", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        topBar.add(label, BorderLayout.CENTER);

        JButton backButton = createBackButton(cardLayout, panelContainer);
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        backButtonPanel.setOpaque(false);
        backButtonPanel.add(backButton);

        topBar.add(backButton, BorderLayout.EAST);
        add(topBar, BorderLayout.NORTH);
    }

    private JButton createBackButton(CardLayout cardLayout, JPanel panelContainer) {
        JButton backButton = new JButton("BACK");
        backButton.setFont(new Font("Arial", Font.BOLD, 15));
        backButton.setBackground(new Color(204, 153, 255));
        backButton.setForeground(Color.BLACK);
        backButton.setPreferredSize(new Dimension(100, 40));

        backButton.addActionListener(e -> cardLayout.previous(panelContainer));
        return backButton;
    }

    private void initializeItemList() {
        itemListModel = new DefaultListModel<>();
        itemList = new JList<>(itemListModel);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemList.setCellRenderer(new ItemRenderer());

        JScrollPane scrollPane = new JScrollPane(itemList);
        add(scrollPane, BorderLayout.CENTER);

        itemList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    List<String> selectedItem = itemList.getSelectedValue();
                    if (selectedItem != null) {
                        String itemName = selectedItem.get(0);
                        int price = Integer.parseInt(selectedItem.get(1));
                        cart.addItem(itemName, price);
                        updateCart();
                    }
                }
            }
        });
    }

    private void initializeCartPanel() {
        cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        JScrollPane cartScrollPane = new JScrollPane(cartPanel);
        cartScrollPane.setPreferredSize(new Dimension(0, 120));
    }

    private void initializeCartStatus() {
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BorderLayout());
        statusPanel.setPreferredSize(new Dimension(0, 160));
        add(statusPanel, BorderLayout.SOUTH);

        statusPanel.add(cartPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        totalPriceLabel = new JLabel("Total Price: 0₩", SwingConstants.RIGHT);
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton checkoutButton = createCheckoutButton();
        bottomPanel.add(totalPriceLabel, BorderLayout.WEST);
        bottomPanel.add(checkoutButton, BorderLayout.EAST);
        statusPanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    private JButton createCheckoutButton() {
        JButton checkoutButton = new JButton("Pay");

        checkoutButton.setFont(new Font("Arial", Font.BOLD, 16));
        checkoutButton.setBackground(new Color(0, 150, 0));
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.setPreferredSize(new Dimension(120, 40));

        checkoutButton.addActionListener(e -> {
            JComboBox<Discount> discountComboBox = new JComboBox<>(Discount.values());
            int totalPrice = cart.getTotalPrice();
            Discount discount = (Discount) discountComboBox.getSelectedItem();

            double discountRate = discount.getRate();
            double finalPrice = totalPrice*(1-discountRate);


            int result = JOptionPane.showConfirmDialog(
                    null,
                    discountComboBox,
                    "Select Discount Type",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (result == JOptionPane.OK_OPTION) {
                int paymentCheck = JOptionPane.showConfirmDialog(
                        null,
                        "Discount: " + (discountRate * 100) + "%\n" +
                                "Original Price: " + totalPrice + "￦\n" +
                                "Final Price: " + finalPrice + "￦\n\n" +
                                "Do you want to proceed with the payment?",
                        "Payment Check",
                        JOptionPane.YES_NO_OPTION
                );
                if (paymentCheck == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Payment completed!");
                    cart.clear();
                    updateCart();
                    cardLayout.show(panelContainer, "RestaurantScreen");
                } else {
                    JOptionPane.showMessageDialog(null, "Payment cancelled!");
                }


            }
        });

        return checkoutButton;
    }

    private void updateCart() {
        cartPanel.removeAll();
        cartPanel.setLayout(new GridLayout(0, 1, 5, 5));

        for (Map.Entry<String, Integer> entry : cart.getCartItems().entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            int price = cart.getItemPrice(itemName);

            JPanel itemRow = createItemRow(itemName, price, quantity);
            cartPanel.add(itemRow);
        }

        totalPriceLabel.setText("Total Price: " + cart.getTotalPrice() + "₩");

        cartPanel.revalidate();
        cartPanel.repaint();
    }

    private JPanel createItemRow(String itemName, int price, int quantity) {
        JPanel itemRow = new JPanel(new BorderLayout());
        itemRow.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel itemLabel = new JLabel(itemName + " (" + price + "₩)" + " X" + quantity);
        itemRow.add(itemLabel, BorderLayout.WEST);

        JPanel buttonPanel = createItemButtons(itemName, price);
        itemRow.add(buttonPanel, BorderLayout.EAST);

        return itemRow;
    }

    private JPanel createItemButtons(String itemName, int price) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        JButton plusButton = createButton("+", e -> {
            cart.addItem(itemName, price);
            updateCart();
        });
        JButton minusButton = createButton("-", e -> {
            cart.removeItem(itemName);
            updateCart();
        });
        JButton deleteButton = createButton("X", e -> {
            cart.deleteItem(itemName);
            updateCart();
        });

        buttonPanel.add(plusButton);
        buttonPanel.add(minusButton);
        buttonPanel.add(deleteButton);

        return buttonPanel;
    }

    private JButton createButton(String text, java.awt.event.ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(50, 30));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.addActionListener(actionListener);
        return button;
    }

    // add items to itemListModel
    public void setRestaurantInfo(String restaurantName) throws IOException {
        itemListModel.clear();
        System.out.println("Selected Restaurant: " + restaurantName);

        List<MenuItems> menuItems = Server.getItemsForRestaurant(restaurantName);
        for (MenuItems item : menuItems) {
            List<String> menus = new ArrayList<>();
            menus.add(item.getName());
            menus.add(Integer.toString(item.getPrice()));
            menus.add(item.getDescription());
            itemListModel.addElement(menus);
        }
    }

    // Cart Interface
    private static class ItemRenderer extends JPanel implements ListCellRenderer<List<String>> {
        private JLabel nameLabel, priceLabel;
        private JTextArea descTextArea;

        public ItemRenderer() {
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            nameLabel = new JLabel();
            priceLabel = new JLabel();
            descTextArea = new JTextArea();

            nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
            priceLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            descTextArea.setFont(new Font("Arial", Font.ITALIC, 12));

            descTextArea.setPreferredSize(new Dimension(300, 30));
            descTextArea.setLineWrap(true);
            descTextArea.setWrapStyleWord(true);
            descTextArea.setEditable(false);
            descTextArea.setBackground(Color.WHITE);

            JPanel textPanel = new JPanel(new GridLayout(3, 1));
            textPanel.add(nameLabel);
            textPanel.add(priceLabel);
            textPanel.add(descTextArea);

            add(textPanel, BorderLayout.CENTER);
            setBackground(Color.WHITE);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends List<String>> list, List<String> value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            nameLabel.setText(value.get(0));
            priceLabel.setText(value.get(1) + "₩");
            descTextArea.setText(value.get(2));

            if (isSelected) {
                setBackground(new Color(173, 216, 230));
            } else {
                setBackground(Color.WHITE);
            }

            return this;
        }
    }
}
