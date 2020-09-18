package bestcommerce.merchant.model;

public class Merchant {
	private Long id;
	private String type;
	private String name;
	private String ownerName;
	private String address;
	private String phoneNumber;
	private String email;
	private String encryptedPassword;

	public Merchant(String type, String name, String ownerName, String address, String phoneNumber, String email, String encryptedPassword) {
		this.type = type;
		this.name = name;
		this.ownerName = ownerName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.encryptedPassword = encryptedPassword;
	}

	public Long getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}
}
