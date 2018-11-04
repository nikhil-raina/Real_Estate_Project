package Database_Classes;

public class Office extends Address{

    private int officeID;
    private char[] managerID;


    public Office(char[] zipcode, String streetAddress, char[] state, int officeID, char[] managerID) {
        super(zipcode, streetAddress, state);
        this.officeID = officeID;
        this.managerID = managerID;
    }

    public void setOfficeID(int officeID) {
        this.officeID = officeID;
    }

    public void setManagerID(char[] managerID) {
        this.managerID = managerID;
    }

    public int getOfficeID() {
        return officeID;
    }

    public char[] getManagerID() {
        return managerID;
    }
}
