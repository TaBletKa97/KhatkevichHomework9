package Task02;

import java.util.GregorianCalendar;
import java.util.HashMap;

public class Waybill {
    private static int numbers;
    private int number;
    private Company provider;
    private Company sender;
    private String reason;
    private HashMap<Product, Double> productsAndNum = new HashMap<>();
    private GregorianCalendar date;

    public Waybill(Company provider, Company sender, String reason, HashMap<Product, Double> productsAndNum, GregorianCalendar date) {
        this.provider = provider;
        this.sender = sender;
        this.reason = reason;
        this.productsAndNum = productsAndNum;
        this.date = date;
        numbers++;
        this.number = this.numbers;
    }

    public Company getProvider() {
        return provider;
    }

    public Company getSender() {
        return sender;
    }

    public String getReason() {
        return reason;
    }

    public HashMap<Product, Double> getProductsAndNum() {
        return productsAndNum;
    }

    public GregorianCalendar getDate() {
        return date;
    }
}
