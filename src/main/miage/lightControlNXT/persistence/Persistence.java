package miage.lightControlNXT.persistence;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * G�re la persistance des donn�es
 */
public class Persistence {
	
	/** Emplacement du fichier de donn�es */
	private static String fileData = "LightControlData.xml";
	
	/**
	 * Charge les donn�es
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
	 * Enregistre les donn�es
	 * @throws JAXBException 
	 */
	public static void saveData() throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Data.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.marshal(Data.getData(), new File(fileData));
	}

}
