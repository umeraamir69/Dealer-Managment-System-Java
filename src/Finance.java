public class Finance {
    private double cashInHand;
    private double cashInBank;

    public void setCashInHand(double cashInHand) {
        this.cashInHand = cashInHand;
    }

    public double getCashInHand() {
        return cashInHand;
    }

    public void setCashInBank(double cashInBank) {
        this.cashInBank = cashInBank;
    }

    public double getCashInBank() {
        return cashInBank;
    }

    public void recordTransaction(double amount, boolean isCashInHand) {
        if (isCashInHand) {
            cashInHand += amount;
        } else {
            cashInBank += amount;
        }
    }

    public void printFinanceStatus() {
        System.out.println("Cash in Hand: " + cashInHand);
        System.out.println("Cash in Bank: " + cashInBank);
    }
}
