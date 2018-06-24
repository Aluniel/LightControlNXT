package miage.lightControlNXT.input;

import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public class ContactSensor implements Runnable {
	
	//
	// FIELDS
	//
	
	private volatile boolean stop = false;
	/** Instance du TouchSensor */
	private TouchSensor sensor;
	
	//
	// PROPERTIES
	//
	
	/** @return Etat du bouton */
	public boolean isPressed() {
		return sensor.isPressed();
	}
	
	//
	// METHODS
	//

	public ContactSensor(SensorPort port) {
		sensor = new TouchSensor(port);

		Thread thread = new Thread(this);
		thread.start();
	}
	
	/** Attend un changement d'état du bouton */
	@Override
	public void run() {
		boolean lastState = isPressed();
		while(!stop) {
			if(lastState != isPressed()) {
				if(isPressed()) {
					// TODO
				} else {
					// TODO
				}
				lastState = isPressed();
			}
		}
	}

}
