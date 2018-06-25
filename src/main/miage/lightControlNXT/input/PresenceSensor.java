package miage.lightControlNXT.input;


import lejos.nxt.SensorPort;
import lejos.nxt.SoundSensor;
import miage.lightControlNXT.system.EtatPiece;

import static miage.lightControlNXT.system.ControlSystem.getControlSystem;

public class PresenceSensor implements Runnable {
	
	private SoundSensor sensor;
	
	public boolean isMovementDetected() {
		return sensor.readValue() > 10;
	}

	public PresenceSensor(SensorPort port) {
		sensor = new SoundSensor(port);
		
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		while(true) {
			if(isMovementDetected()) {
				getControlSystem().setEtatPiece(EtatPiece.occupied);
			}
		}
	}

}
