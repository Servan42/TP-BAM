import java.io.File;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/** 
 * 
 * @author CHARLOT Servan - CHANET Zoran
 * @arguments hostel.server1.xml hostel.server2.xml hostel.server3.xml hostel.server4.xml Annuaire.xml
 */
public class Server {
	public static void main(String args[]) {
		/* récupération d'informations de configuration */
		DocumentBuilder docBuilder;
		try {
			docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc;

			doc = docBuilder.parse(new File(args[0]));
			//On récupère les arguments pour la construction de Chaine
			String arguments = doc.getElementsByTagName("service").item(0).getAttributes().getNamedItem("args").getNodeValue();
			Chaine server1 = new Chaine(arguments);
			LocateRegistry.createRegistry(1111);
			java.rmi.Naming.bind("//localhost:1111/Chaine1", server1);
			
			doc = docBuilder.parse(new File(args[1]));
			//On récupère les arguments pour la construction de Chaine
			arguments = doc.getElementsByTagName("service").item(0).getAttributes().getNamedItem("args").getNodeValue();
			Chaine server2 = new Chaine(arguments);
			LocateRegistry.createRegistry(2222);
			java.rmi.Naming.bind("//localhost:2222/Chaine2", server2);
			
			doc = docBuilder.parse(new File(args[2]));
			//On récupère les arguments pour la construction de Chaine
			arguments = doc.getElementsByTagName("service").item(0).getAttributes().getNamedItem("args").getNodeValue();
			Chaine server3 = new Chaine(arguments);
			LocateRegistry.createRegistry(3333);
			java.rmi.Naming.bind("//localhost:3333/Chaine3", server3);
			
			doc = docBuilder.parse(new File(args[3]));
			//On récupère les arguments pour la construction de Chaine
			arguments = doc.getElementsByTagName("service").item(0).getAttributes().getNamedItem("args").getNodeValue();
			Chaine server4 = new Chaine(arguments);
			LocateRegistry.createRegistry(4444);
			java.rmi.Naming.bind("//localhost:4444/Chaine4", server4);

			Annuaire annuaire = new Annuaire(args[4]);
			LocateRegistry.createRegistry(5555);
			java.rmi.Naming.bind("//localhost:5555/Annuaire", annuaire);

		} catch (SAXException | IOException e1) {
			e1.printStackTrace();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
		

		
		
		// Crée et installe un Security Manager, necessaire seulement si le code est téléchargé
//		if(System.getSecurityManager() == null){
//			System.setSecurityManager(new SecurityManager());
//		}
	}
}
