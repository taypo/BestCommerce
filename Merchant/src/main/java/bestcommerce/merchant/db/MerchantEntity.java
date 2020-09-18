package bestcommerce.merchant.db;

import bestcommerce.merchant.model.Merchant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MerchantEntity {
	@Id
	@GeneratedValue
	private Long id;
	private String type;
	private String name;
	private String ownerName;
	private String address;
	private String phoneNumber;
	private String email;
	private String encryptedPassword;

	public MerchantEntity(String type, String name, String ownerName, String address, String phoneNumber, String email, String encryptedPassword) {
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

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public Merchant toMerchant() {
		return new Merchant(id, type, name, ownerName, address, phoneNumber, email, encryptedPassword);
	}
}
