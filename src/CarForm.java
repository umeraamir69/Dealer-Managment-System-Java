import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarForm extends JPanel {
    private JLabel makeLabel, modelLabel, variantLabel, carIdLabel, regNumberLabel, priceLabel, statusLabel, imageLabel, ratingLabel, colorLabel;
    private JComboBox<String> makeComboBox;
    private JTextField modelTextField, variantTextField, carIdTextField, regNumberTextField ,  imageTextField;
    private JSpinner yearSpinner;
    private JFormattedTextField priceFormattedTextField;
    private JCheckBox statusCheckBox;
    private JButton submitButton, cancelButton;
    private JSlider ratingSlider;
    private JButton colorButton;
    private Color selectedColor;
    public CarDealerManagementSystem data;

    public CarForm(CarDealerManagementSystem data) {
        this.data =data;

        setLayout(new GridLayout(15, 4, 60, 0));
        setPreferredSize(new Dimension(1000, 450));

        // Labels

        makeLabel = new JLabel("Make:");
        modelLabel = new JLabel("Model (Year):");
        variantLabel = new JLabel("Variant:");
        carIdLabel = new JLabel("Car ID:");
        regNumberLabel = new JLabel("Registration Number:");
        priceLabel = new JLabel("Price:");
        statusLabel = new JLabel("Status:");
        imageLabel = new JLabel("Image:");
        ratingLabel = new JLabel("Rating:");
        colorLabel = new JLabel("Color:");

        // Combo Box
        String[] makes = {"Toyota", "BMW", "Mercedes", "Honda", "Suzuki", "Audi", "KIA", "Changan", "Hyundai", "MG", "Perot"};
        makeComboBox = new JComboBox<>(makes);




        // Text Fields
        modelTextField = new JTextField();
        variantTextField = new JTextField();
        carIdTextField = new JTextField();
        regNumberTextField = new JTextField();
        imageTextField = new JTextField();

        // Spinner
        SpinnerModel yearSpinnerModel = new SpinnerNumberModel(2023, 1900, 2100, 1);
        yearSpinner = new JSpinner(yearSpinnerModel);

        // Formatted Text Field
        priceFormattedTextField = new JFormattedTextField();

        // Checkbox
        statusCheckBox = new JCheckBox();

        // Button
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");

        // Slider
        ratingSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
        ratingSlider.setMajorTickSpacing(1);
        ratingSlider.setPaintTicks(true);
        ratingSlider.setPaintLabels(true);

        // Color Button
        colorButton = new JButton("Select Color");
        colorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedColor = JColorChooser.showDialog(null, "Select a Color", selectedColor);
            }
        });

        // Add components to the frame
        add(makeLabel);
        add(makeComboBox);
        add(modelLabel);
        add(modelTextField);
        add(variantLabel);
        add(variantTextField);
        add(carIdLabel);
        add(carIdTextField);
        add(regNumberLabel);
        add(regNumberTextField);
        add(priceLabel);
        add(priceFormattedTextField);
        add(statusLabel);
        add(statusCheckBox);
        add(imageLabel);
        add(imageTextField);
        add(ratingLabel);
        add(ratingSlider);
        add(colorLabel);
        add(colorButton);
        add(submitButton);
        add(cancelButton);

cancelButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }
});

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {



                int rating = ratingSlider.getValue();

                if (makeComboBox.getSelectedItem() == null ||
                        modelTextField.getText().isEmpty() ||
                        variantTextField.getText().isEmpty() ||
                        carIdTextField.getText().isEmpty() ||
                        regNumberTextField.getText().isEmpty() ||
                        priceFormattedTextField.getText().isEmpty() ||
                        imageTextField.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(CarForm.this, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Process the form data
                    String make = (String) makeComboBox.getSelectedItem();
                    String model = modelTextField.getText();
                    String variant = variantTextField.getText();
                    String carId = carIdTextField.getText();
                    String image = imageTextField.getText();
                    String regNumber = regNumberTextField.getText();
                    float price = Float.parseFloat(priceFormattedTextField.getText());
                    boolean status = statusCheckBox.isSelected();
                    // Print the form data
                    System.out.println("Make: " + make);
                    System.out.println("Model Year: " + model);
                    System.out.println("Variant: " + variant);
                    System.out.println("Car ID: " + carId);
                    System.out.println("Registration Number: " + regNumber);
                    System.out.println("Price: " + price);
                    System.out.println("Status: " + status);
                    System.out.println("Image: " + image);
                    System.out.println("Rating: " + rating);
                    System.out.println("Color: " + selectedColor);

                    data.cars.add(new Car(make , model , variant ,carId ,regNumber , selectedColor.toString() , rating , image ,status ,price));

                    // Reset the form fields
                    resetFields();
                }
            }
        });


        setVisible(true);



    }

    private void resetFields() {
        // Clear the input fields
        makeComboBox.setSelectedItem(null);
        modelTextField.setText("");
        variantTextField.setText("");
        carIdTextField.setText("");
        regNumberTextField.setText("");
        priceFormattedTextField.setText("");
        imageTextField.setText("");
        ratingSlider.setValue(0);
        statusCheckBox.setSelected(false);
    }


}
