package Database_Classes;

public class Address {

    private char[] zipcode;
    private String streetAddress;
    private char[] state;

    public Address(char[] zipcode, String streetAddress, char[] state){
        this.state = state;
        this.zipcode = zipcode;
        this.streetAddress = streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setState(char[] state) {
        this.state = state;
    }

    public void setZipcode(char[] zipcode) {
        this.zipcode = zipcode;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public char[] getState() {
        return state;
    }

    public char[] getZipcode() {
        return zipcode;
    }
}
