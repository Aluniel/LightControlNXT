package miage.lightControlNXT.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "LightControlData")
/** Contient l'ensemble des données à persister */
public class Data {
	
	//
	// FIELDS
	//
	
	/** Temporisation T1 en minutes */
	@XmlElement(name = "T1")
	private int t1 = 15;
	/** Temporisation T2 en minutes */
	@XmlElement(name = "T2")
	private int t2 = 10;
	/** Temporisation T2 en minutes */
	@XmlElement(name = "T3")
	private int t3 = 2;
	
	/** Configuration standard */
	@XmlElement(name = "StandardConfiguration")
	private Configuration standardConfiguration;
	
	/** Configuration courante */
	@XmlElement(name = "CurrentConfiguration")
	private Configuration currentConfiguration;
	
	/** Liste des utilisateurs */
	@XmlElementWrapper(name = "Users")
	@XmlElement(name = "User")
	private List<User> users = new ArrayList<User>();
	
	//
	// PROPERTIES
	//
	
	/** @return Temporisation T1 en minutes */
	public int getT1() {
		return t1;
	}

	/** @param t1 Temporisation T1 en minutes */
	public void setT1(int t1) {
		this.t1 = t1;
	}
	
	/** @return Temporisation T2 en minutes */
	public int getT2() {
		return t2;
	}

	/** @param t2 Temporisation T2 en minutes */
	public void setT2(int t2) {
		this.t2 = t2;
	}
	
	/** @return Temporisation T3 en minutes */
	public int getT3() {
		return t3;
	}

	/** @param t3 Temporisation T3 en minutes */
	public void setT3(int t3) {
		this.t3 = t3;
	}
	
	/** @return Configuration standard */
	public Configuration getStandardConfiguration() {
		return standardConfiguration;
	}

	/** @return Configuration courante */
	public Configuration getCurrentConfiguration() {
		return currentConfiguration;
	}

	/** @return Liste des utilisateurs */
	public List<User> getUsers() {
		return users;
	}
	
	//
	// METHODS
	//

	public Data() {
		
	}

}
