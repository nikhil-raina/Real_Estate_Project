package Database_Classes;

public class Manager extends Person{

    private int salary;
    private Person person;
    private Office office;


    public Manager(char[] zipcode, String streetAddress, char[] state, String firstName, String lastName,
                   char[] taxID, char memberType, String phoneNumber, int salary, int officeID) {

        super(zipcode, streetAddress, state, firstName, lastName, taxID, memberType, phoneNumber);
        this.person.setTaxID(taxID);
        this.office.setOfficeID(officeID);
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
