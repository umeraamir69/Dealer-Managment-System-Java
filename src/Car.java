public class Car {
    private String make;
    private String model;
    private String variant;
    private String carID;
    private String registrationNumber;
    private String colour;
    private double rating;
    private String image;
    private boolean status;
    private double price;

    public void setMake(String make) {
        this.make = make;
    }

    public String getMake() {
        return make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getVariant() {
        return variant;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getCarID() {
        return carID;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void addCar(String make, String model, String variant, String carID, String registrationNumber,
                       String colour, double rating, String image, boolean status, double price) {
        setMake(make);
        setModel(model);
        setVariant(variant);
        setCarID(carID);
        setRegistrationNumber(registrationNumber);
        setColour(colour);
        setRating(rating);
        setImage(image);
        setStatus(status);
        setPrice(price);
    }

    public  Car(String make, String model, String variant, String carID, String registrationNumber,
                       String colour, double rating, String image, boolean status, double price) {
        setMake(make);
        setModel(model);
        setVariant(variant);
        setCarID(carID);
        setRegistrationNumber(registrationNumber);
        setColour(colour);
        setRating(rating);
        setImage(image);
        setStatus(status);
        setPrice(price);
    }

    public void updateCar(String variant, String registrationNumber, String colour, double rating,
                          String image, double price) {
        setVariant(variant);
        setRegistrationNumber(registrationNumber);
        setColour(colour);
        setRating(rating);
        setImage(image);
        setPrice(price);
    }

    public void printCar() {
        System.out.println("Car ID: " + carID);
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Variant: " + variant);
        System.out.println("Registration Number: " + registrationNumber);
        System.out.println("Colour: " + colour);
        System.out.println("Rating: " + rating);
        System.out.println("Image: " + image);
        System.out.println("Status: " + (status ? "Available" : "Sold"));
        System.out.println("Price: " + price);
    }

    public Car() {
        // Default constructor
    }


    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Car ID: ").append(carID).append("\n");
        details.append("Make: ").append(make).append("\n");
        details.append("Model: ").append(model).append("\n");
        details.append("variant: ").append(variant).append("\n");
        details.append("Price: PKR").append(price).append("\n");
        details.append("Registration Number: ").append(registrationNumber).append("\n");
        details.append("Colour: ").append(colour).append("\n");
        details.append("Rating: ").append(rating).append("\n");

        return details.toString();
    }

    public void saleCar() {
        setStatus(false);
    }
}
