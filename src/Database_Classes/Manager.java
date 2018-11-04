package Database_Classes;

public class Manager extends Person{

    private int salary;
//    private int officeID;
    private Person person;
    private Office office;

    public Manager(String firstName, String lastName, char[] taxID, char memberType, String phoneNumber,
                   char[] zipCode, String streetAddress, char[] state, int salary, int officeID) {

        super(firstName, lastName, taxID, memberType, phoneNumber, zipCode, streetAddress, state);

        person.setTaxID(taxID);
//        office.setOfficeID(officeID);
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
