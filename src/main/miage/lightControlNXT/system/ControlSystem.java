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
	/** Etat de la pi�ce occup�/innocup�e */
	private EtatPiece etatPiece = EtatPiece.occupied;
	
	//
	// PROPERTIES
	//
	
	/** @return Instance de la classe */
	public static ControlSystem getControlSystem() {
		return instance;
	}
	
	/** @return Etat de la pi�ce */
	public EtatPiece getEtatPiece() {
		return etatPiece;
	}
	
	/** @param etatPiece Etat de la pi�ce */
	private void setEtatPiece(EtatPiece etatPiece) {
		switch(etatPiece) {
			case occupied:
				// TODO : V�rifier que les lumi�res sont allum�es
				Minuteur.cancelT1();
				Minuteur.startT2();
				break;
			case empty:
				if(this.etatPiece != etatPiece) {
					// TODO : Eteindre les lumi�res
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