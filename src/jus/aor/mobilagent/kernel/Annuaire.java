package jus.aor.mobilagent.kernel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Annuaire implements _Annuaire, _Service {
	HashMap<String, Numero> annuaire;
	
	public Annuaire(String xml) {
		/* récupération d'informations de configuration */
		try {
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = docBuilder.parse(new File(xml));
			
			annuaire = new HashMap<String, Numero>();
			String name, numero = null;
			NodeList list = doc.getElementsByTagName("Telephone");
			NamedNodeMap attrs;
			/* acquisition de toutes les entrées de l'annuaire */
			for (int i = 0; i < list.getLength(); i++) {
				attrs = list.item(i).getAttributes();
				name = attrs.getNamedItem("name").getNodeValue();
				numero = attrs.getNamedItem("numero").getNodeValue();
				annuaire.put(name, new Numero(numero));
			}
		
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Numero get(String abonne) throws ParserConfigurationException, SAXException, IOException {
		return annuaire.get(abonne);
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * @param List<String>
	 *            la liste des abonnes
	 * @return List<Numero> la liste des numeros demandes
	 */
	public Object call(Object... params) throws IllegalArgumentException {
		List<Numero> retour = new ArrayList<Numero>();
		for(int i=0; i< ((List<String>)params[0]).size(); i++)
			try {
				retour.add(get(((List<Hotel>)params[0]).get(i).name));

			} catch (ParserConfigurationException | SAXException | IOException e) {
				e.printStackTrace();
			}
		return retour;
	}

}
