package miage.lightControlNXT.system;

import static miage.lightControlNXT.persistence.Data.getData;

import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
import miage.lightControlNXT.persistence.Configuration;
import miage.lightControlNXT.persistence.User;

/** Coeur de l'application */
public class ControlSystem extends ControlSystemInitializer {
	
	//
	// FIELDS
	//
	
	/** Instance de la classe */
	private static ControlSystem instance = new ControlSystem();
	/** Etat de la pièce occupé/innocupée */
	private EtatPiece etatPiece = EtatPiece.occupied;
	/** Configuration courante */
	private Configuration currentConfiguration = new Configuration();
	
	//
	// PROPERTIES
	//
	
	/** Restorer la configuration courante si qqn revient ? */
	public Boolean keepCurrentConfiguration = true;
	
	/** @return Instance de la classe */
	public static ControlSystem getControlSystem() {
		return instance;
	}
	
	/** @return Etat de la pièce */
	public EtatPiece getEtatPiece() {
		return etatPiece;
	}
	
	/** @param etatPiece Etat de la pièce */
	public void setEtatPiece(EtatPiece etatPiece) {
		switch(etatPiece) {
			case occupied:
				if(keepCurrentConfiguration) {
					currentConfiguration.Apply();
				} else {
					getData().getStandardConfiguration().Apply();
				}
				keepCurrentConfiguration = true;
				Minuteur.cancelT1();
				Minuteur.startT2();
				break;
			case empty:
				turnLightOff(this.etatPiece != etatPiece);
				Minuteur.startT1();
				break;
			case unknown:
				Minuteur.startT3();
				break;
		}
		this.etatPiece = etatPiece;
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
		while(true) {
			NXTConnection conn = Bluetooth.waitForConnection();
			
			// Identification de l'utilisateur
			User user = getData().getUserFromMACAdress(conn.getAddress());
			if(user != null) {
				user.connect(conn);
			}
		}
	}
	
	public void saveCurrentConfiguration() {
		currentConfiguration.setCeilingLightIntensity(getCeilingLight().getIntensity());
		currentConfiguration.setDeskLightOn(getDeskLight().getIsLightOn());
	}
	
	public void turnLightOff(Boolean saveCurrent) {
		if(saveCurrent) {
			saveCurrentConfiguration();
		}

		getCeilingLight().setIntensity(0);
		getDeskLight().setIsLightOn(false);
	}
}