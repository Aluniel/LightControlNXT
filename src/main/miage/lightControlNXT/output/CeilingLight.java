package miage.lightControlNXT.output;

import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;

public class CeilingLight {

	//
	// FIELDS
	//
	
	private NXTMotor motor;
	
	//
	// PROPERTIES
	//
	
	/** @return intensité lumineuse */
	public int getIntensity() {
		return motor.getPower();
	}
	
	/** @param intensity intensité lumineuse */
	public void setIntensity(int intensity) {
		if(intensity < 0) {
			motor.setPower(0);
		} else if(intensity > 100) {
			motor.setPower(100);
		} else {
			motor.setPower(intensity);
		}
	}
	
	//
	// METHODS
	//
	
	public CeilingLight(MotorPort port) {
		motor = new NXTMotor(port);
	}

}
