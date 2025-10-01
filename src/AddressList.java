import java.util.ArrayList;

public class AddressList {
    private ArrayList<String> addresses;

    public AddressList() {
        addresses = new ArrayList<>();
        addresses.add("вул. Шевченка, 10");
        addresses.add("вул. Франка, 5");
        addresses.add("вул. Лесі Українки, 7");
    }

    public boolean contains(String address) {
        return addresses.contains(address);
    }

    public ArrayList<String> getAllAddresses() {
        return addresses;
    }
}