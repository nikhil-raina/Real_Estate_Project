package Database_Classes;

public class Agent extends Person{

    private Person person; // taxID
    private int salary;
    private int commission;
    private Manager manager; // ID of Manager.
    private Office office; // ID of the office the agent will work in


    public Agent(char[] zipcode, String streetAddress, char[] state, String firstName, String lastName,
                 char[] taxID, char memberType, String phoneNumber, int salary, int commission,
                 char[] ManagerTaxID, int PrimaryOfficeID) {
        super(zipcode, streetAddress, state, firstName, lastName, taxID, memberType, phoneNumber);

        this.salary = salary;
        this.commission = commission;
        manager.setTaxID(ManagerTaxID);
        office.setOfficeID(PrimaryOfficeID);
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }

    public int getSalary() {
        return salary;
    }

    public int getCommission() {
        return commission;
    }
}
