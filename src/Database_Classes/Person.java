package Database_Classes;

abstract public class Person {
    private String firstName;
    private String lastName;
    private char[] taxID;
    private String phoneNumber;
    private char[] zipCode;
    private String streetAddress;
    private char[] state;
    private char memberType; // what kind of designation the person is.


    public Person(String firstName, String lastName, char[] taxID, char memberType,
                  String phoneNumber, char[] zipCode, String streetAddress, char[] state){
        this.firstName = firstName;
        this.lastName = lastName;
        if(memberType == 1)
            this.memberType = memberType;

        this.phoneNumber = phoneNumber;
        this.taxID = taxID;
        this.state = state;
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;

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

    public char[] getState() {
        return state;
    }

    public char[] getZipCode() {
        return zipCode;
    }

    public String getStreetAddress() {
        return streetAddress;
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

    public void setZipCode(char[] zipCode) {
        this.zipCode = zipCode;
    }

    public void setState(char[] state) {
        this.state = state;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
}
