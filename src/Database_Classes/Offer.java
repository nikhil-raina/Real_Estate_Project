package Database_Classes;


import java.sql.Date;

public class Offer {

    private Property propertyID; //
    private int offerID;
    private Date date;
    private char[] buyerTaxID;
    private int offerAmount;
    private char offerStatus;

    public Offer(int propertyID, int offerID, Date date, char[] buyerTaxID, int offerAmount, char offerStatus){
        this.buyerTaxID = buyerTaxID;
        this.offerID = offerID;
        this.date = date;
        this.offerAmount = offerAmount;
        this.offerStatus = offerStatus;
        this.propertyID.setPropertyID(propertyID);
    }

    public void setBuyerTaxID(char[] buyerTaxID) {
        this.buyerTaxID = buyerTaxID;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setOfferAmount(int offerAmount) {
        this.offerAmount = offerAmount;
    }

    public void setOfferID(int offerID) {
        this.offerID = offerID;
    }

    public void setOfferStatus(char offerStatus) {
        this.offerStatus = offerStatus;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID.setPropertyID(propertyID);
    }


    public char getOfferStatus() {
        return offerStatus;
    }

    public char[] getBuyerTaxID() {
        return buyerTaxID;
    }

    public Date getDate() {
        return date;
    }

    public int getOfferAmount() {
        return offerAmount;
    }

    public int getOfferID() {
        return offerID;
    }

}
