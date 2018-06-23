package miage.lightControlNXT.system;

import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import miage.lightControlNXT.input.*;
import miage.lightControlNXT.output.LightController;

/** Coeur de l'application */
public class ControlSystem {
	
	//
	// CONSTANTS
	//
	
	/** Port du capteur de contact */
	private final SensorPort contactSensorPort = SensorPort.S1;
	/** Port du capteur de présence */
	private final SensorPort presenceSensorPort = SensorPort.S2;
	/** Port du capteur de luminosité */
	private final SensorPort lightSensorPort = SensorPort.S3;
	/** Port de la lumière */
	private final MotorPort lightControllerPort = MotorPort.A;
	
	//
	// FIELDS
	//
	
	/** Temporisation T1 en minutes */
	private int t1 = 15;
	/** Temporisation T1 en minutes */
	private int t2 = 10;
	/** Temporisation T1 en minutes */
	private int t3 = 2;
	/** Etat de la pièce occupé/innocupée */
	private boolean etatPiece = true;
	
	/** Capteur de contact */
	private ContactSensor contactSensor;
	/** Capteur de présence */
	private PresenceSensor presenceSensor;
	/** Capteur de luminosité */
	private LightSensor lightSensor;
	/** Lumière */
	private LightController lightController;
	
	//
	// PROPERTIES
	//
	
	public int getT1() {
		return t1;
	}

	public void setT1(int t1) {
		this.t1 = t1;
	}
	
	public int getT2() {
		return t2;
	}

	public void setT2(int t2) {
		this.t2 = t2;
	}
	
	public int getT3() {
		return t3;
	}

	public void setT3(int t3) {
		this.t3 = t3;
	}
	
	//
	// METHODS
	//

	/**
	 * Initialise les champs
	 */
	public ControlSystem() {
		lightSensor     = new LightSensor(lightSensorPort);
		contactSensor   = new ContactSensor(contactSensorPort);
		presenceSensor  = new PresenceSensor(presenceSensorPort);
		lightController = new LightController(lightControllerPort);
	}
	
	/**
	 * Attends une connexion d'une application android utilisateur/administrateur
	 */
	public void waitForUserToConnect() {
		
	}
}
