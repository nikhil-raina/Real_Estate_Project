public class demoUI {
	int accessLevel = -1;
	//-1 is no access
	
	String[] actions = {};
	
    public static void main(String[] args) {
    
    }
    public int getAccessLevel(String taxID){
    	//TODO: accessLevel
		return accessLevel;
    	
    }
    public String getSale(int PID ){
    	return "TODO: SALE INFO";
    }
    public String getCustomerInfo(int taxID) {
		return new String();
    	//if taxID is seller/buyer
    	//	return info
    	//else return null/error
    }
    public String getOfficeManager(int officeID) {
    	//return as formatted taxID
    	return new String();
    }
    public String getOfficeInfo(int officeID) {
		return null;
    }
    public int setOfferStatus(int PID) {
		return new Integer();
    	//input: property ID
    	//output: 0 for success, -1 for error.
    }
    
    
}