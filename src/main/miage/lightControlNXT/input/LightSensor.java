package miage.lightControlNXT.input;

import lejos.nxt.ColorSensor;
import lejos.nxt.SensorPort;

/** Capteur de luminosité */
public class LightSensor implements Runnable {
	
	/***/
	private ColorSensor sensor;
	
	/***/
	public int getLuminosity() {
		return sensor.getLightValue();
	}

	/***/
	public LightSensor(SensorPort port) {
		sensor = new ColorSensor(port);

		Thread thread = new Thread(this);
		thread.start();
	}

	/***/
	@Override
	public void run() {
		int lastLuminosity = getLuminosity();
		while(true) {
			if(lastLuminosity != getLuminosity()) {
				// TODO
			}
		}
	}

}
