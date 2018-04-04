import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

/**
 * J<i>ava</i> U<i>tilities</i> for S<i>tudents</i>
 */

/**
 * Représente un client effectuant une requête lui permettant d'obtenir les
 * numéros de téléphone des hôtels répondant à son critère de choix.
 * 
 * @author Morat
 * @argument la localisation demandée
 */
public class LookForHotel {
	/** le critère de localisaton choisi */
	private String localisation;

	// ...
	/**
	 * Définition de l'objet représentant l'interrogation.
	 * 
	 * @param args
	 *            les arguments n'en comportant qu'un seul qui indique le
	 *            critère de localisation
	 */
	public LookForHotel(String... args) {
		localisation = args[0];
	}

	/**
	 * réalise une interrogation
	 * 
	 * @return la durée de l'interrogation
	 * @throws RemoteException
	 */
	public long call() throws RemoteException {
		long timeDebut = new Date().getTime();
		// Appeler "à la main" tous les serveurs
		try {
			// Recupere les list d'hotels sur les serveurs
			_Chaine obj = (_Chaine) java.rmi.Naming.lookup("//localhost:1111/Chaine1");
			ArrayList<Hotel> listChaine1 = (ArrayList<Hotel>) obj.get(localisation);
			obj = (_Chaine) java.rmi.Naming.lookup("//localhost:2222/Chaine2");
			ArrayList<Hotel> listChaine2 = (ArrayList<Hotel>) obj.get(localisation);

			// Demande les numeros de téléphones à l'annuaire
			_Annuaire annuaire = (_Annuaire) java.rmi.Naming.lookup("//localhost:5555/Annuaire");
			Numero numero;
			
			System.out.println("Resultat chaine d'hotels 1 :");
			for(int i = 0; i < listChaine1.size(); i++){
				Hotel host = listChaine1.get(i);
				numero = annuaire.get(host.getName());
				System.out.println(listChaine1.get(i).toString() + " : " + numero.toString());
			}
			
			System.out.println("Resultat chaine d'hotels 2 :");
			for(int i = 0; i < listChaine2.size(); i++){
				numero = annuaire.get(listChaine2.get(i).getName());
				System.out.println(listChaine2.get(i).toString() + " : " + numero.toString());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		long timeFin = new Date().getTime();
		
		return timeFin - timeDebut;

	}
	
	public static void main(String args[]) {
		try {
			new LookForHotel(args[0]).call();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
