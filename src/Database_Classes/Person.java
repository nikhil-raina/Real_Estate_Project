package Database_Classes;

abstract public class Person {
    private String firstName;
    private String lastName;
    private char[] taxID;
    private char memberType; // what kind of designation the person is.
    private String phoneNumber;
//    private Object personType;

    public Person(String firstName, String lastName, char[] taxID, char memberType, String phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.memberType = memberType;
        this.phoneNumber = phoneNumber;
        this.taxID = taxID;

//        if(this.getMemberType() == 1){
//
//        }
//
//        else if(this.getMemberType() == 2){
//
//        }
//
//        else if(this.getMemberType() == 3){
//
//        }
//
//        else if(this.getMemberType() == 4){
//
//        }

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
