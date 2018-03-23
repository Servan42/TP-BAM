import java.io.File;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

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
			LocateRegistry.createRegistry(2001);
			java.rmi.Naming.bind("//localhost:2001/Chaine1", server1);
			
			//TODO répéter de doc=... à ici en changeant arg[x]
			// les arguments seront hello.serverX.xml
			
		} catch (SAXException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
		// Crée et installe un Security Manager, necessaire seulement si le code est téléchargé
//		if(System.getSecurityManager() == null){
//			System.setSecurityManager(new SecurityManager());
//		}
		try {
//			Chaine server1 = new Chaine(new Hotel("Sofitel", "Paris"), new Hotel("Sofitel", "Lyon"));
//			Chaine server2 = new Chaine(new Hotel("Ibis1", "Grenoble"), new Hotel("Ibis", "Bordeaux"), new Hotel("Ibis2", "Grenoble"));
//			Chaine server3 = new Chaine(new Hotel("B&B", "Paris"), new Hotel("B&B1", "Lyon"), new Hotel("B&B2", "Lyon"));
//			Chaine server4 = new Chaine();
			
			LocateRegistry.createRegistry(1111);
			LocateRegistry.createRegistry(2018);
			LocateRegistry.createRegistry(3008);
			
//			java.rmi.Naming.bind("//localhost:1111/Chaine2", server2);
//			java.rmi.Naming.bind("//localhost:2018/Chaine3", server3);
//			java.rmi.Naming.bind("//localhost:3008/Chaine4", server4);
			
			System.out.println("Serveur et registre operationels");
		} catch(Exception e) {
			System.out.println("C'est la panique "+e.toString());
		}
	}
}
