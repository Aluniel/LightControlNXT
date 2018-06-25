package miage.lightControlNXT.persistence;

import static miage.lightControlNXT.persistence.Data.getData;
import static miage.lightControlNXT.system.ControlSystem.getControlSystem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.IllegalFormatException;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import lejos.nxt.comm.NXTConnection;

/***/
public class User implements Runnable {
	
	//
	// FIELDS
	//
	
	/** Nom prénom */
	@XmlElement(name = "Login")
	private volatile String login;
	/** Adresse MAC */
	@XmlElement(name = "MacAddress")
	private String macAddress;
	/** Administrateur ou utilisateur */
	@XmlElement(name = "IsAdmin")
	private volatile boolean isAdmin;
	/** Thread de communication avec le client */
	@XmlTransient()
	private Thread tListen;
	/** Thread de communication avec le client */
	@XmlTransient()
	private volatile Timer tSend;
	/** Connection NXT */
	@XmlTransient()
	private volatile NXTConnection connection;
	/***/
	@XmlTransient()
	private TimerTask sendData = new TimerTask() {
		
		@Override
		public void run() {
			DataOutputStream output = connection.openDataOutputStream();
			if(isAdmin()) {
				// TODO
			} else {
				try {
					output.writeUTF(Boolean.toString(getControlSystem().getDeskLight().getIsLightOn()) + ";" + Integer.toString(getControlSystem().getCeilingLight().getIntensity()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	};
	
	//
	// PROPERTIES
	//
	
	/** @return Nom de l'utilisateur */
	@XmlTransient()
	public String getLogin() {
		return login;
	}
	
	/** @param login Nom de l'utilisateur */
	public void setLogin(String login) {
		this.login = login;
	}
	
	/** @return Adresse MAC */
	@XmlTransient()
	public String getMacAddress() {
		return macAddress;
	}
	
	/** @param macAddress Adresse MAC */
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	
	/** @return Administrateur ou utilisateur */
	@XmlTransient()
	public boolean isAdmin() {
		return isAdmin;
	}
	
	/** @param isAdmin Administrateur ou utilisateur */
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	//
	// METHODS
	//
	
	/** Ajoute un utilisateur vide */
	public User() {
		
	}
	
	/** Ajoute un utilisateur */
	public User(String login, String macAdress, boolean isAdmin) {
		this.login      = login;
		this.macAddress = macAdress;
		this.isAdmin    = isAdmin;
	}
	
	/** Démarre un thread de communication avec l'utilisateur */
	public void connect(NXTConnection connection) {
		this.connection = connection;
		tListen = new Thread(this);
		tListen.setDaemon(true);
		tListen.start();
		tSend = new Timer();
		tSend.schedule(sendData, 50, 500);
	}

	/** Thread d'écoute de l'utilisateur */
	@Override
	public void run() {
		NXTConnection    connection = this.connection;
		DataInputStream  input      = connection.openDataInputStream();
		DataOutputStream output     = connection.openDataOutputStream();
		
		try {
			while(true) {
				String message = input.readUTF();
				int index 	   = message.indexOf(';');
				if(index < 1 || message.length() > index + 1)
					continue;
				String command = message.substring(0, index);
				String info    = message.substring(index + 1);
				
				try {
					// Commandes administrateur
					if(isAdmin) {
						switch(command) {
						// Paramètrage T1
						case "setT1":
							getData().setT1(Integer.parseInt(info));
							break;
						// Paramètrage T2
						case "setT2:":
							getData().setT2(Integer.parseInt(info));
							break;
						// Paramètrage T3
						case "setT3:":
							getData().setT3(Integer.parseInt(info));
							break;
						// Configuration standard
						case "SetStdConf":
							index = info.indexOf('|');
							if(index < 1 || info.length() < index + 1)
								continue;
							String deskLight    = info.substring(0, index);
							String ceilingLight = info.substring(index + 1);
							getData().getStandardConfiguration().setDeskLightOn(Boolean.parseBoolean(deskLight));
							getData().getStandardConfiguration().setCeilingLightIntensity(Integer.parseInt(ceilingLight));
							break;
						// Ajout d'utilisateur
						case "addUsr":
							index = info.indexOf('|');
							if(index < 1 || info.length() < index + 1)
								continue;
							String usrName    = info.substring(0, index);
							String usrAddress = info.substring(index + 1);
							getData().getUsers().add(new User(usrName, usrAddress, false));
							break;
						// Suppression d'utilisateur
						case "rmUsr":
							getData().getUsers().remove(getData().getUserFromMACAdress(info));
							break;
						// Passe un utilisateur en administrateur
						case "promUsr":
							getData().getUserFromMACAdress(info).setAdmin(true);
							break;
						// Passe un administrateur en utilisateur
						case "demUsr":
							getData().getUserFromMACAdress(info).setAdmin(false);
							break;
						}
					}
					
					// Commandes utilisateur
					switch(command) {
					// Lampe de bureau
					case "setCurDeskLight":
						getControlSystem().getDeskLight().setIsLightOn(Boolean.parseBoolean(info));
						break;
					// Plafonnier
					case "setCurrCeilLight":
						getControlSystem().getCeilingLight().setIntensity(Integer.parseInt(info));
						break;
					// Passage en configuration standard
					case "setStd":
						getData().getStandardConfiguration().Apply();
						break;
					}
				} catch (IllegalFormatException e) {
					e.printStackTrace();
				}				
			}
		} catch (IOException e) {
			connection.close();
		}	
	}
}
