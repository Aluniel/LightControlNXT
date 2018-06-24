package miage.lightControlNXT.system;

import static miage.lightControlNXT.persistence.Data.getData;

import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
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
	
	//
	// PROPERTIES
	//
	
	/** @return Instance de la classe */
	public static ControlSystem getControlSystem() {
		return instance;
	}
	
	/** @return Etat de la pièce */
	public EtatPiece getEtatPiece() {
		return etatPiece;
	}
	
	/** @param etatPiece Etat de la pièce */
	private void setEtatPiece(EtatPiece etatPiece) {
		switch(etatPiece) {
			case occupied:
				// TODO : Vérifier que les lumières sont allumées
				Minuteur.cancelT1();
				Minuteur.startT2();
				break;
			case empty:
				if(this.etatPiece != etatPiece) {
					// TODO : Eteindre les lumières
				}
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
				// TODO : ouvrir un thread pour communiquer
			}
		}
	}
}