package Database_Classes;

public class Property extends Address {

    private int propertyID;
    private char[] agentTaxID;
    private char[] sellerTaxID;
    private int listPrice;
    private String description;
    private int area;


    public Property(char[] zipcode, String streetAddress, char[] state, int propertyID, char[] agentTaxID,
                    char[] sellerTaxID, int listPrice, String description, int area) {
        super(zipcode, streetAddress, state);

        this.propertyID = propertyID;
        this.agentTaxID = agentTaxID;
        this.sellerTaxID = sellerTaxID;
        this.area = area;
        this.listPrice = listPrice;
        this.description = description;

    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public void setAgentTaxID(char[] agentTaxID) {
        this.agentTaxID = agentTaxID;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setListPrice(int listPrice) {
        this.listPrice = listPrice;
    }

    public void setSellerTaxID(char[] sellerTaxID) {
        this.sellerTaxID = sellerTaxID;
    }

    public char[] getSellerTaxID() {
        return sellerTaxID;
    }

    public char[] getAgentTaxID() {
        return agentTaxID;
    }

    public int getListPrice() {
        return listPrice;
    }

    public int getArea() {
        return area;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public String getDescription() {
        return description;
    }
}
