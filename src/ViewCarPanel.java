import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.net.URL;

public class ViewCarPanel extends JPanel {
    private JLabel makeLabel;
    private JLabel modelLabel;
    private JLabel variantLabel;
    private JLabel imageLabel;
    private JLabel colourLabel;
    private JLabel regNumberLabel;
    private JLabel carIdLabel;
    private JLabel ratingsLabel;

    private ArrayList<Car> carList; // Assuming you have an ArrayList of Car objects

    public ViewCarPanel(CarDealerManagementSystem data) {
        this.carList = data.cars;

        setLayout(new BorderLayout());
        setBackground(Color.decode("#27374D"));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                new LineBorder(Color.decode("#526D82"), 2)
        ));

        imageLabel = createLabel("Image:");
        makeLabel = createLabel("Make:");
        modelLabel = createLabel("Model:");
        variantLabel = createLabel("Variant:");
        colourLabel = createLabel("Colour:");
        regNumberLabel = createLabel("Registration Number:");
        carIdLabel = createLabel("Car ID:");
        ratingsLabel = createLabel("Ratings:");

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setBackground(Color.decode("#27374D"));

        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchValue = searchField.getText();
                Car foundCar = findCar(searchValue);
                if (foundCar != null) {
                    displayCarDetails(foundCar);
                } else {
                    clearCarDetails();
                }
            }
        });

        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JPanel detailsPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        detailsPanel.setBackground(Color.decode("#27374D"));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        detailsPanel.add(imageLabel);
        detailsPanel.add(new JLabel());
        detailsPanel.add(makeLabel);
        detailsPanel.add(new JLabel());
        detailsPanel.add(modelLabel);
        detailsPanel.add(new JLabel());
        detailsPanel.add(variantLabel);
        detailsPanel.add(new JLabel());
        detailsPanel.add(colourLabel);
        detailsPanel.add(new JLabel());
        detailsPanel.add(regNumberLabel);
        detailsPanel.add(new JLabel());
        detailsPanel.add(carIdLabel);
        detailsPanel.add(new JLabel());
        detailsPanel.add(ratingsLabel);
        detailsPanel.add(new JLabel());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.decode("#27374D"));

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Car selectedCar = findCar(searchField.getText());
                if (selectedCar != null) {
                    openEditPopup(selectedCar);
                }
            }
        });
        buttonPanel.add(editButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Car selectedCar = findCar(searchField.getText());
                if (selectedCar != null) {
                    removeCar(selectedCar);
                    clearCarDetails();
                }
            }
        });
        buttonPanel.add(deleteButton);

        add(searchPanel, BorderLayout.NORTH);
        add(detailsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.decode("#DDE6ED"));
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private void displayCarDetails(Car car) {
        makeLabel.setText("Make: " + car.getMake());
        modelLabel.setText("Model: " + car.getModel());
        variantLabel.setText("Variant: " + car.getVariant());
        imageLabel.setText("Image: " + car.getImage());
        colourLabel.setText("Colour: " + car.getColour());
        regNumberLabel.setText("Registration Number: " + car.getRegistrationNumber());
        carIdLabel.setText("Car ID: " + car.getCarID());
        ratingsLabel.setText("Ratings: " + car.getRating());

        ImageIcon imageIcon = getImageIcon(car.getImage());
        if (imageIcon != null) {
            imageLabel.setIcon(imageIcon);
        } else {
            imageLabel.setText("Image: Not Available");
        }
    }

    private ImageIcon getImageIcon(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            return new ImageIcon(ImageIO.read(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void clearCarDetails() {
        makeLabel.setText("Make: ");
        modelLabel.setText("Model: ");
        variantLabel.setText("Variant: ");
        imageLabel.setText("Image: ");
        colourLabel.setText("Colour: ");
        regNumberLabel.setText("Registration Number: ");
        carIdLabel.setText("Car ID: ");
        ratingsLabel.setText("Ratings: ");
    }

    private Car findCar(String searchValue) {
        for (Car car : carList) {
            if (car.getCarID().equals(searchValue) || car.getRegistrationNumber().equals(searchValue)) {
                return car;
            }
        }
        return null;
    }

    private void openEditPopup(Car car) {
        JDialog editDialog = new JDialog();
        editDialog.setTitle("Edit Car Details");
        editDialog.setSize(400, 300);
        editDialog.setLocationRelativeTo(null);
        editDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        editDialog.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField makeField = createTextField(car.getMake());
        JTextField modelField = createTextField(car.getModel());
        JTextField variantField = createTextField(car.getVariant());
        JTextField imageField = createTextField(car.getImage());
        JTextField colourField = createTextField(car.getColour());
        JTextField regNumberField = createTextField(car.getRegistrationNumber());
        JTextField carIdField = createTextField(car.getCarID());
        JTextField ratingsField = createTextField(String.valueOf(car.getRating()));

        contentPanel.add(createLabel("Make:"));
        contentPanel.add(makeField);
        contentPanel.add(createLabel("Model:"));
        contentPanel.add(modelField);
        contentPanel.add(createLabel("Variant:"));
        contentPanel.add(variantField);
        contentPanel.add(createLabel("Image:"));
        contentPanel.add(imageField);
        contentPanel.add(createLabel("Colour:"));
        contentPanel.add(colourField);
        contentPanel.add(createLabel("Registration Number:"));
        contentPanel.add(regNumberField);
        contentPanel.add(createLabel("Car ID:"));
        contentPanel.add(carIdField);
        contentPanel.add(createLabel("Ratings:"));
        contentPanel.add(ratingsField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Update the car object with the edited values
                car.setMake(makeField.getText());
                car.setModel(modelField.getText());
                car.setVariant(variantField.getText());
                car.setImage(imageField.getText());
                car.setColour(colourField.getText());
                car.setRegistrationNumber(regNumberField.getText());
                car.setRating(Double.parseDouble(ratingsField.getText()));

                // Display the updated car details
                displayCarDetails(car);

                // Close the editDialog
                editDialog.dispose();
            }
        });

        editDialog.add(contentPanel, BorderLayout.CENTER);
        editDialog.add(saveButton, BorderLayout.SOUTH);
        editDialog.setVisible(true);
    }

    private JTextField createTextField(String text) {
        JTextField textField = new JTextField(text);
        textField.setForeground(Color.decode("#DDE6ED"));
        textField.setBackground(Color.decode("#526D82"));
        textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return textField;
    }

    private void removeCar(Car car) {
        carList.remove(car);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Car Details");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        CarDealerManagementSystem data = new CarDealerManagementSystem();
        ViewCarPanel viewCarPanel = new ViewCarPanel(data);
        frame.add(viewCarPanel);
        frame.setVisible(true);
    }
}
