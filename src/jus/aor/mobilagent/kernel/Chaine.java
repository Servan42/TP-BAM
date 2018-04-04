package jus.aor.mobilagent.kernel;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Chaine extends UnicastRemoteObject implements _Chaine {
	private static final long serialVersionUID = -47;
	ArrayList<Hotel> hotels;

	public Chaine(String... args) throws RemoteException {
		hotels = new ArrayList<Hotel>();

		/*
		 * récupération des hôtels de la chaîne dans le fichier xml passé en 1er
		 * argument
		 */
		DocumentBuilder docBuilder = null;
		Document doc = null;
		try {
			docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			doc = docBuilder.parse(new File(args[0]));
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String name, localisation;
		NodeList list = doc.getElementsByTagName("Hotel");
		NamedNodeMap attrs;
		/* acquisition de toutes les entrées de la base d'hôtels */
		for (int i = 0; i < list.getLength(); i++) {
			attrs = list.item(i).getAttributes();
			name = attrs.getNamedItem("name").getNodeValue();
			localisation = attrs.getNamedItem("localisation").getNodeValue();
			hotels.add(new Hotel(name, localisation));
		}
	}

	@Override
	public List<Hotel> get(String localisation) {
		ArrayList<Hotel> result = new ArrayList<Hotel>();
		for (int i = 0; i < hotels.size(); i++)
			if (hotels.get(i).localisation.equals(localisation))
				result.add(hotels.get(i));

		return result;
	}

}