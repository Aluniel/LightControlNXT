package miage.lightControlNXT.input;

import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public class ContactSensor extends TouchSensor {
	
	//
	// FIELDS
	//
	
	private boolean stop = false;
	
	//
	// METHODS
	//

	public ContactSensor(SensorPort port) {
		super(port);
		// TODO Auto-generated constructor stub
	}
	
	/** Ajoute une action lors de la fermeture d'une porte */
	public void addContactListener() {
		
	}
	
	/** Attend une action */
	public void waitForAction() {
		while(!stop) {
			// TODO : Attendre un this.isPressed()
		}
	}

}
