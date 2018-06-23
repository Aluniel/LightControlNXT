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
	/** Port du capteur de pr�sence */
	private final SensorPort presenceSensorPort = SensorPort.S2;
	/** Port du capteur de luminosit� */
	private final SensorPort lightSensorPort = SensorPort.S3;
	/** Port de la lumi�re */
	private final MotorPort lightControllerPort = MotorPort.A;
	
	//
	// FIELDS
	//	
	
	/** Etat de la pi�ce occup�/innocup�e */
	private boolean etatPiece = true;
	
	/** Capteur de contact */
	private ContactSensor contactSensor;
	/** Capteur de pr�sence */
	private PresenceSensor presenceSensor;
	/** Capteur de luminosit� */
	private LightSensor lightSensor;
	/** Lumi�re */
	private LightController lightController;
	
	//
	// PROPERTIES
	//
	
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
