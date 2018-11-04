package Database_Classes;

public class Client extends Person{

    private Property propertyID;
    public Client(char[] zipcode, String streetAddress, char[] state, String firstName, String lastName,
                  char[] taxID, char memberType, String phoneNumber, int propertyID) {

        super(zipcode, streetAddress, state, firstName, lastName, taxID, memberType, phoneNumber);
        this.propertyID.setPropertyID(propertyID);
    }

    public void setPropertyID(int propertyID) {
        this.propertyID.setPropertyID(propertyID);
    }

    public int getPropertyID() {
        return this.propertyID.getPropertyID();
    }
}
