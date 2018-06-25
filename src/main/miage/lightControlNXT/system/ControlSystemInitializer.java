package miage.lightControlNXT.system;

import javax.xml.bind.JAXBException;

import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import miage.lightControlNXT.input.ContactSensor;
import miage.lightControlNXT.input.LightSensor;
import miage.lightControlNXT.input.PresenceSensor;
import miage.lightControlNXT.output.CeilingLight;
import miage.lightControlNXT.output.DeskLight;
import miage.lightControlNXT.persistence.Persistence;

class ControlSystemInitializer {
	
	//
	// CONSTANTS
	//
	
	/** Port du capteur de contact */
	private final SensorPort contactSensorPort = SensorPort.S1;
	/** Port du capteur de présence */
	private final SensorPort presenceSensorPort = SensorPort.S2;
	/** Port du capteur de luminosité */
	private final SensorPort lightSensorPort = SensorPort.S3;
	/** Port du plafonnier */
	private final MotorPort ceilingLightPort = MotorPort.A;
	/** Port de la lampe de bureau */
	private final MotorPort deskLightPort = MotorPort.B;
	/** Fichier de sauvegarde des données */
	private final String dataFile = "LightControlData.xml";
	
	//
	// FIELDS
	//
	
	/** Capteur de contact */
	private ContactSensor contactSensor;
	/** Capteur de présence */
	private PresenceSensor presenceSensor;
	/** Capteur de luminosité */
	private LightSensor lightSensor;
	/** Plafonnier */
	private CeilingLight ceilingLight;
	/** Lampe de bureau */
	private DeskLight deskLight;
	
	//
	// PROPERTIES
	//
	
	/** @return Capteur de contact */
	protected ContactSensor getContactSensor() {
		return contactSensor;
	}

	/** @return Capteur de présence */
	protected PresenceSensor getPresenceSensor() {
		return presenceSensor;
	}

	/** @return Capteur de luminosité */
	protected LightSensor getLightSensor() {
		return lightSensor;
	}

	/** @return Plafonnier */
	public CeilingLight getCeilingLight() {
		return ceilingLight;
	}
	
	/** @return Lampe de bureau */
	public DeskLight getDeskLight() {
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
	private void initializeSensors() {
		lightSensor     = new LightSensor(lightSensorPort);
		contactSensor   = new ContactSensor(contactSensorPort);
		presenceSensor  = new PresenceSensor(presenceSensorPort);
	}
	
	/** Initialise les lampes */
	private void initializeLights() {
		ceilingLight = new CeilingLight(ceilingLightPort);
		deskLight 	 = new DeskLight(deskLightPort);
	}
	
	/** Charge les données à partir du fichier de sauvegarde s'il existe */
	private void loadData() {
		try {
			Persistence.loadData();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
