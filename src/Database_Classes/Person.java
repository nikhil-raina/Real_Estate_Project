package Database_Classes;

public class Person extends Address{
    private String firstName;
    private String lastName;
    private char[] taxID;
    private String phoneNumber;
    private char memberType; // what kind of designation the person is.

    public Person(char[] zipcode, String streetAddress, char[] state, String firstName,
                  String lastName, char[] taxID, char memberType, String phoneNumber) {
        super(zipcode, streetAddress, state);
        this.firstName = firstName;
        this.lastName = lastName;
        this.memberType = memberType;
        this.phoneNumber = phoneNumber;
        this.taxID = taxID;
    }

    public char getMemberType() {
        return memberType;
    }

    public char[] getTaxID() {
        return taxID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMemberType(char memberType) {
        this.memberType = memberType;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setTaxID(char[] taxID) {
        this.taxID = taxID;
    }
}
