import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Annuaire extends UnicastRemoteObject implements _Annuaire, Serializable {
	HashMap<String, Numero> levrai; 
	private static final long serialVersionUID = -2;
	
	public Annuaire(String annuaireXML) throws RemoteException  {
		DocumentBuilder docBuilder;
		try {
			docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			/* Récupération de l'annuaire dans le fichier xml */
			Document annuaire = docBuilder.parse(new File(annuaireXML));
			NodeList list = annuaire.getElementsByTagName("Telephone");
			NamedNodeMap attrs;
			String name, numero;
			this.levrai = new HashMap<String, Numero>();
			/* acquisition de toutes les entrées de l'annuaire */
			for(int i=0; i<list.getLength(); i++) {
				attrs = list.item(i).getAttributes();
				name=attrs.getNamedItem("name").getNodeValue();
				numero=attrs.getNamedItem("numero").getNodeValue();
				levrai.put(name, new Numero(numero));
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Override
	public Numero get(String abonne) throws RemoteException {		
		if(levrai.containsKey(abonne))
			return levrai.get(abonne);
		else
			throw new RemoteException("L'HOTEL DEMANDE N'EXISTE PAS");
	}

}
