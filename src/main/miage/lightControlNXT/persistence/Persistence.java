package miage.lightControlNXT.persistence;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Gère la persistance des données
 */
public class Persistence {
	
	/**
	 * Charge les données
	 * @throws JAXBException 
	 */
	public static void loadData(String input) throws JAXBException {
		File fileInput = new File(input);

		if(fileInput.exists()) {
			JAXBContext context = JAXBContext.newInstance(Data.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			unmarshaller.unmarshal(fileInput);
		}
		else {
			Data.createDefault();
		}
	}
	
	/**
	 * Enregistre les données
	 * @throws JAXBException 
	 */
	public static void saveData(String output) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Data.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.marshal(Data.getData(), new File(output));
	}

}
