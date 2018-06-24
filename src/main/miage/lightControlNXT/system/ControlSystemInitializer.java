package miage.lightControlNXT.system;

import javax.xml.bind.JAXBException;

import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import miage.lightControlNXT.input.ContactSensor;
import miage.lightControlNXT.input.LightSensor;
import miage.lightControlNXT.input.PresenceSensor;
import miage.lightControlNXT.output.LightController;
import miage.lightControlNXT.persistence.Persistence;

class ControlSystemInitializer {
	
	//
	// CONSTANTS
	//
	
	/** Port du capteur de contact */
	private final SensorPort contactSensorPort = SensorPort.S1;
	/** Port du capteur de pr�sence */
	private final SensorPort presenceSensorPort = SensorPort.S2;
	/** Port du capteur de luminosit� */
	private final SensorPort lightSensorPort = SensorPort.S3;
	/** Port du plafonnier */
	private final MotorPort ceilingLightPort = MotorPort.A;
	/** Port de la lampe de bureau */
	private final MotorPort deskLightPort = MotorPort.B;
	/** Fichier de sauvegarde des donn�es */
	private final String dataFile = "LightControlData.xml";
	
	//
	// FIELDS
	//
	
	/** Capteur de contact */
	private ContactSensor contactSensor;
	/** Capteur de pr�sence */
	private PresenceSensor presenceSensor;
	/** Capteur de luminosit� */
	private LightSensor lightSensor;
	/** Plafonnier */
	private LightController ceilingLight;
	/** Lampe de bureau */
	private LightController deskLight;
	
	//
	// PROPERTIES
	//
	
	/** @return Capteur de contact */
	protected ContactSensor getContactSensor() {
		return contactSensor;
	}

	/** @return Capteur de pr�sence */
	protected PresenceSensor getPresenceSensor() {
		return presenceSensor;
	}

	/** @return Capteur de luminosit� */
	protected LightSensor getLightSensor() {
		return lightSensor;
	}

	/** @return Plafonnier */
	protected LightController getCeilingLight() {
		return ceilingLight;
	}
	
	/** @return Lampe de bureau */
	protected LightController getDeskLight() {
		return deskLight;
	}
	
	//
	// METHODS
	//

	/** Initialise les objets */
	protected ControlSystemInitializer() {
		initializeSensors();
		initializeLights();
		loadData();
	}
	
	/** Initialise les capteurs */
	public void initializeSensors() {
		lightSensor     = new LightSensor(lightSensorPort);
		contactSensor   = new ContactSensor(contactSensorPort);
		presenceSensor  = new PresenceSensor(presenceSensorPort);
	}
	
	/** Initialise les lampes */
	public void initializeLights() {
		ceilingLight = new LightController(ceilingLightPort);
		deskLight 	 = new LightController(deskLightPort);
	}
	
	/** Charge les donn�es � partir du fichier de sauvegarde s'il existe */
	public void loadData() {
		try {
			Persistence.loadData(dataFile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}