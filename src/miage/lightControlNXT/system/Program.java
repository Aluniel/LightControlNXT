package miage.lightControlNXT.system;

import javax.xml.bind.JAXBException;

import miage.lightControlNXT.persistence.Data;
import miage.lightControlNXT.persistence.Persistence;
import miage.lightControlNXT.persistence.User;

public class Program {

	/** Point d'entrée de l'application */
	public static void main(String[] args) {
		new Data();
		Data.getInstance().setT1(5);
		Data.getInstance().setT2(10);
		Data.getInstance().setT3(15);
		Data.getInstance().getUsers().add(new User("Alexandre CABEL", "gnagna", true));
		try {
			Persistence.saveData("C:\test.xml");
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
