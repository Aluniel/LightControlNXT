package miage.lightControlNXT.system;

/** Coeur de l'application */
public class ControlSystem extends ControlSystemInitializer {
	
	//
	// FIELDS
	//
	
	/** Instance de la classe */
	private static ControlSystem instance = new ControlSystem();
	/** Etat de la pièce occupé/innocupée */
	private boolean etatPiece = true;
	
	//
	// PROPERTIES
	//
	
	/** @return Instance de la classe */
	public static ControlSystem getControlSystem() {
		return instance;
	}
	
	//
	// METHODS
	//

	/**
	 * Initialise les champs
	 */
	private ControlSystem() {
		super();
	}
	
	/**
	 * Attends une connexion d'une application android utilisateur/administrateur
	 */
	public void waitForUserToConnect() {
		
	}
}
