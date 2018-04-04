package jus.aor.mobilagent.hostel;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Annuaire implements _Annuaire {

	@Override
	public Numero get(String abonne) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub

		/* Récupération de l'annuaire dans le fichier xml */
		DocumentBuilder docBuilder = null;
		Document doc = null;
		docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		doc = docBuilder.parse(new File(abonne));

		String name, numero = null;
		NodeList list = doc.getElementsByTagName("Telephone");
		NamedNodeMap attrs;
		/* acquisition de toutes les entrées de l'annuaire */
		for (int i = 0; i < list.getLength(); i++) {
			attrs = list.item(i).getAttributes();
			name = attrs.getNamedItem("name").getNodeValue();
			numero = attrs.getNamedItem("numero").getNodeValue();
		}

		/* récupération d'informations de configuration */
		docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		doc = docBuilder.parse(new File(abonne));
		// On récupère les arguments pour la construction de Chaine
		String arguments = doc.getElementsByTagName("service").item(0).getAttributes().getNamedItem("args")
				.getNodeValue();

		return new Numero(numero);
		// return null;
	}

}
