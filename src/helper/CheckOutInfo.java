package helper;

public class CheckOutInfo{
	
	private String firstName;
	private String lastName;
	private String customerId;
	private String credCard;
	private String expDate;
	
	public CheckOutInfo(String firstName, String lastName, String customerId, String credCard, String expDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.customerId = customerId;
		this.credCard = credCard;
		this.expDate = expDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getCredCard() {
		return credCard;
	}

	public void setCredCard(String credCard) {
		this.credCard = credCard;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	
	
}