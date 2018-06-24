package miage.lightControlNXT.persistence;

import javax.xml.bind.annotation.*;

/***/
public class User {
	
	//
	// FIELDS
	//
	
	/** Nom prénom */
	@XmlElement(name = "Login")
	private String login;
	/** Adresse MAC */
	@XmlElement(name = "MacAddress")
	private String macAddress;
	/** Administrateur ou utilisateur */
	@XmlElement(name = "IsAdmin")
	private boolean isAdmin;
	
	//
	// PROPERTIES
	//
	
	/** @return Nom de l'utilisateur */
	@XmlTransient()
	public String getLogin() {
		return login;
	}
	
	/** @param login Nom de l'utilisateur */
	public void setLogin(String login) {
		this.login = login;
	}
	
	/** @return Adresse MAC */
	@XmlTransient()
	public String getMacAddress() {
		return macAddress;
	}
	
	/** @param macAddress Adresse MAC */
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	
	/** @return Administrateur ou utilisateur */
	@XmlTransient()
	public boolean isAdmin() {
		return isAdmin;
	}
	
	/** @param isAdmin Administrateur ou utilisateur */
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	//
	// METHODS
	//
	
	/***/
	public User() {
		
	}
	
	/***/
	public User(String login, String macAdress, boolean isAdmin) {
		this.login      = login;
		this.macAddress = macAdress;
		this.isAdmin    = isAdmin;
	}
}
