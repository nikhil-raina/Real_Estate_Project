package Database_Classes;

public class Manager extends Person{

    private int salary;
    private int officeID;

    public Manager(String firstName, String lastName, char[] taxID, char memberType, String phoneNumber,
                   char[] zipCode, String streetAddress, char[] state, int salary, int officeID) {

        super(firstName, lastName, taxID, memberType, phoneNumber, zipCode, streetAddress, state);

        this.officeID = officeID;
        this.salary = salary;
    }

    public int getOfficeID() {
        return officeID;
    }

    public int getSalary() {
        return salary;
    }

    public void setOfficeID(int officeID) {
        this.officeID = officeID;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
