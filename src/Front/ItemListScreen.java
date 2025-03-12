package Front;

import Back.Cart;
import Back.Menu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemListScreen extends JPanel {
    private DefaultListModel<List<String>> itemListModel;
    private JList<List<String>> itemList;
    private JPanel cartPanel;
    private JLabel totalPriceLabel;
    private Cart cart;

    public ItemListScreen(CardLayout cardLayout, JPanel panelContainer) {
        setLayout(new BorderLayout());
        cart = new Cart();


        //Top  Bar
        JPanel topBar = new JPanel();
        topBar.setLayout(new BorderLayout());

        JLabel label = new JLabel("Food Fighter", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        topBar.add(label, BorderLayout.CENTER);
        topBar.setBackground(new Color(204, 153, 255));
        topBar.setPreferredSize(new Dimension(0, 50));



        //Back Button
        JButton backButton = new JButton("BACK");
        backButton.setFont(new Font("Arial", Font.BOLD, 15));
        backButton.setBackground(new Color(204, 153, 255));
        backButton.setForeground(Color.BLACK);
        backButton.setPreferredSize(new Dimension(100,40));

        backButton.addActionListener(e->{
            cardLayout.previous(panelContainer);
        });

        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
        backButtonPanel.setOpaque(false);
        backButtonPanel.add(backButton);

        topBar.add(backButton, BorderLayout.EAST);
        add(topBar, BorderLayout.NORTH);


        itemListModel = new DefaultListModel<>();
        itemList = new JList<>(itemListModel);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemList.setCellRenderer(new ItemRenderer());



        JScrollPane scrollPane = new JScrollPane(itemList);
        add(scrollPane, BorderLayout.CENTER);


        //Cart Status
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BorderLayout());
        statusPanel.setPreferredSize(new Dimension(0, 160));
        add(statusPanel, BorderLayout.SOUTH);


        cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        JScrollPane cartScrollPane = new JScrollPane(cartPanel);
        cartScrollPane.setPreferredSize(new Dimension(0, 120));
        statusPanel.add(cartScrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        totalPriceLabel = new JLabel("Total Price: 0₩", SwingConstants.RIGHT);
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton checkoutButton = new JButton("Pay");
        checkoutButton.setFont(new Font("Arial", Font.BOLD, 16));
        checkoutButton.setBackground(new Color(0, 150, 0));
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.setPreferredSize(new Dimension(120, 40));

        bottomPanel.add(totalPriceLabel, BorderLayout.WEST);
        bottomPanel.add(checkoutButton, BorderLayout.EAST);
        statusPanel.add(bottomPanel, BorderLayout.SOUTH);


        // Double Click -> Add to cart
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

        //payment button
        checkoutButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to pay?",
                    "Payment Check",
                    JOptionPane.YES_NO_OPTION
            );

            if (result == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Payment completed!");
                cart.clear();
                updateCart();
            } else {
                JOptionPane.showMessageDialog(null, "Payment cancelled!");
            }
        });

    }

    private void updateCart() {
        cartPanel.removeAll();
        cartPanel.setLayout(new GridLayout(0,1,5,5));

        for (Map.Entry<String, Integer> entry : cart.getCartItems().entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            int price = cart.getItemPrice(itemName);

            JPanel itemRow = new JPanel(new BorderLayout());
            itemRow.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));


            JLabel itemLabel = new JLabel(itemName + " (" + price + "₩)" +" X"+quantity );

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
            JButton plusButton = new JButton("+");
            JButton minusButton = new JButton("-");
            JButton deleteButton = new JButton("X");

            Dimension buttonSize = new Dimension(50, 30);
            plusButton.setPreferredSize(buttonSize);
            minusButton.setPreferredSize(buttonSize);
            deleteButton.setPreferredSize(buttonSize);

            plusButton.setHorizontalAlignment(SwingConstants.CENTER);
            minusButton.setHorizontalAlignment(SwingConstants.CENTER);
            deleteButton.setHorizontalAlignment(SwingConstants.CENTER);

            buttonPanel.add(plusButton);
            buttonPanel.add(minusButton);
            buttonPanel.add(deleteButton);

            plusButton.addActionListener(e -> {
                cart.addItem(itemName, price);
                updateCart();
            });

            minusButton.addActionListener(e -> {
                cart.removeItem(itemName);
                updateCart();
            });

            deleteButton.addActionListener(e -> {
                cart.deleteItem(itemName);
                updateCart();
            });

            itemRow.add(itemLabel, BorderLayout.WEST);
            itemRow.add(buttonPanel, BorderLayout.EAST);

            cartPanel.add(itemRow);
        }

        totalPriceLabel.setText("Total Price: " + cart.getTotalPrice() + "₩");


        cartPanel.revalidate();
        cartPanel.repaint();
    }





    //add items on itemListModel
    public void setRestaurantInfo(String restaurantName) {
        itemListModel.clear();
        //check log
        System.out.println("Selected Restaurant: " + restaurantName);
        HashMap<String, List<List<String>>> items = Menu.getItemsForRestaurant(restaurantName);
        List<List<String>> menuItems=items.get(restaurantName);
        for (List<String> item : menuItems) {
            itemListModel.addElement(item);
        }

    }

    //Cart Interface
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
