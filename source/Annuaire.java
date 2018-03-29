import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Annuaire extends UnicastRemoteObject implements _Annuaire {
	Document annuaire;
	private static final long serialVersionUID = -2;
	
	public Annuaire(String annuaireXML) throws RemoteException  {
		DocumentBuilder docBuilder;
		try {
			docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc;
			
			/* Récupération de l'annuaire dans le fichier xml */
			this.annuaire = docBuilder.parse(new File(annuaireXML));
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
	public Numero get(String abonne) {
		boolean found = false;
		String name, numero = "0";
		NodeList list = annuaire.getElementsByTagName("Telephone");
		NamedNodeMap attrs;
		/* acquisition de toutes les entrées de l'annuaire */
		for(int i =0; i<list.getLength() && !found;i++) {
			attrs = list.item(i).getAttributes();
			name=attrs.getNamedItem("name").getNodeValue();
			if(name.equals(abonne))
				found = true;
			numero=attrs.getNamedItem("numero").getNodeValue();
		}
		
		return (found?new Numero(numero):null);
	}

}
