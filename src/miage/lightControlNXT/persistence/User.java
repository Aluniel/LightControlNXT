package miage.lightControlNXT.persistence;

public class User {
	
	//
	// FIELDS
	//
	
	/** Nom de l'utilisateur */
	private String login;
	/** Identifiant de l'utilisateur */
	private String macAdress;
	/** Administrateur ou utilisateur */
	private boolean isAdmin;
	
	//
	// PROPERTIES
	//
	
	/***/
	public String getLogin() {
		return login;
	}
	
	/***/
	public void setLogin(String login) {
		this.login = login;
	}
	
	/***/
	public String getMacAdress() {
		return macAdress;
	}
	
	/***/
	public void setMacAdress(String macAdress) {
		this.macAdress = macAdress;
	}
	
	/***/
	public boolean isAdmin() {
		return isAdmin;
	}
	
	/***/
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	//
	// METHODS
	//
	
	/***/
	public User(String Nom, String macAdress, boolean isAdmin) {
		
	}
	
	/***/
	public void serialize() {
		
	}
}
