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
	
	/** Emplacement du fichier de données */
	private static String fileData = "LightControlData.xml";
	
	/**
	 * Charge les données
	 * @throws JAXBException 
	 */
	public static void loadData() throws JAXBException {
		File fileInput = new File(fileData);

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
	public static void saveData() throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Data.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.marshal(Data.getData(), new File(fileData));
	}

}
