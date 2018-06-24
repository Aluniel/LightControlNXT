package miage.lightControlNXT.output;

import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;

public class DeskLight {

	//
	// FIELDS
	//
	
	private NXTMotor motor;
	
	//
	// PROPERTY
	//
	
	/** @return La lampe est-elle allumée */
	public boolean getIsLightOn() {
		return motor.getPower() > 10;
	}
	
	/** @param Allumer la lampe ? */
	public void setIsLightOn(boolean value) {
		if(value) {
			motor.setPower(100);
		}
		else {
			motor.setPower(0);
		}
	}
	
	//
	// METHODS
	//
	
	public DeskLight(MotorPort port) {
		motor = new NXTMotor(port);
	}
}
