public class Receipt  {
    private String receiptID;

    public void setReceiptID(String receiptID) {
        this.receiptID = receiptID;
    }

    public String getReceiptID() {
        return receiptID;
    }

    public void generateReceipt() {
        System.out.println("Receipt ID: " + receiptID);

    }
}
