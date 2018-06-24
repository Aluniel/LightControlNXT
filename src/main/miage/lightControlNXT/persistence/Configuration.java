package miage.lightControlNXT.persistence;

import static miage.lightControlNXT.system.ControlSystem.getControlSystem;

import javax.xml.bind.annotation.XmlElement;

public class Configuration {
	
	//
	// FIELDS
	//
	
	/** Etat de la lampe de bureau */
	@XmlElement(name = "IsDeskLightOn")
	private boolean isDeskLightOn;
	/** Etat du plafonnier */
	@XmlElement(name = "CeilingLightIntensity")
	private int ceilingLightIntensity;
	
	//
	// PROPERTIES
	//
	
	/** @return Etat de la lampe de bureau */
	public boolean isDeskLightOn() {
		return isDeskLightOn;
	}
	
	/** @param isDeskLightOn Etat de la lampe de bureau */
	public void setDeskLightOn(boolean isDeskLightOn) {
		this.isDeskLightOn = isDeskLightOn;
	}
	
	/** @return Etat du plafonnier */
	public int getCeilingLightIntensity() {
		return ceilingLightIntensity;
	}
	
	/** @param ceilingLightIntensity Etat du plafonnier */
	public void setCeilingLightIntensity(int ceilingLightIntensity) {
		if(ceilingLightIntensity < 0) {
			this.ceilingLightIntensity = 0;	
		} else if(ceilingLightIntensity > 100) {
			this.ceilingLightIntensity = 100;
		} else {
			this.ceilingLightIntensity = ceilingLightIntensity;
		}
	}
	
	//
	// METHODS
	//
	
	/***/
	public Configuration() {
		
	}
	
	/** @return configuration par défaut */
	public static Configuration getDefault() {
		Configuration conf = new Configuration();
		conf.setDeskLightOn(false);
		conf.setCeilingLightIntensity(0);
		return conf;
	}
	
	/** Applique la configuration */
	public void Apply() {
		getControlSystem().getCeilingLight().setIntensity(ceilingLightIntensity);
		getControlSystem().getDeskLight().setIsLightOn(isDeskLightOn);
	}
}
