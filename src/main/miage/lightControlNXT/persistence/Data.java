package miage.lightControlNXT.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

/** Contient l'ensemble des données à persister */
@XmlRootElement(name = "LightControlData")
@XmlType(propOrder = {"t1", "t2", "t3", "standardConfiguration", "users"})
public class Data {
	
	//
	// FIELDS
	//
	
	/** Instance pour le Singleton */
	@XmlTransient
	private static Data instance;
	
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
	
	/** Liste des utilisateurs */
	@XmlElementWrapper(name = "Users")
	@XmlElement(name = "User")
	private List<User> users = new ArrayList<User>();
	
	//
	// PROPERTIES
	//
	
	/** @return Instance de l'objet contenant les données à persister */
	public static Data getData() {
		if(instance == null)
			createDefault();
		return instance;
	}
	
	/** @return Temporisation T1 en minutes */
	@XmlTransient()
	public int getT1() {
		return t1;
	}

	/** @param t1 Temporisation T1 en minutes */
	public void setT1(int t1) {
		this.t1 = t1;
	}
	
	/** @return Temporisation T2 en minutes */
	@XmlTransient()
	public int getT2() {
		return t2;
	}

	/** @param t2 Temporisation T2 en minutes */
	public void setT2(int t2) {
		this.t2 = t2;
	}
	
	/** @return Temporisation T3 en minutes */
	@XmlTransient()
	public int getT3() {
		return t3;
	}

	/** @param t3 Temporisation T3 en minutes */
	public void setT3(int t3) {
		this.t3 = t3;
	}
	
	/** @return Configuration standard */
	@XmlTransient()
	public Configuration getStandardConfiguration() {
		return standardConfiguration;
	}

	/** @return Liste des utilisateurs */
	@XmlTransient()
	public List<User> getUsers() {
		return users;
	}
	
	//
	// METHODS
	//

	/***/
	public Data() {
		instance = this;
	}
	
	/** Crée une instance par défault (destiné à la première utilisation de l'application ou à un reset complet des données) */
	public static void createDefault() {
		new Data();
		getData().setT1(15);
		getData().setT2(10);
		getData().setT3(2);
		getData().standardConfiguration = Configuration.getDefault();
	}
}
