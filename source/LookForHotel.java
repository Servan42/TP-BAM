import java.rmi.RemoteException;

/**
 * J<i>ava</i> U<i>tilities</i> for S<i>tudents</i>
 */

/**
 * Représente un client effectuant une requête lui permettant d'obtenir les
 * numéros de téléphone des hôtels répondant à son critère de choix.
 * 
 * @author Morat
 */
public class LookForHotel {
	/** le critère de localisaton choisi */
	private String localisation;

	// ...
	/**
	 * Définition de l'objet représentant l'interrogation.
	 * 
	 * @param args
	 *            les arguments n'en comportant qu'un seul qui indique le critère de
	 *            localisation
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
		//TODO durée de l'interrogation ??
	}

	public String[] lookForHotel() throws RemoteException {
		//TODO trouve le nom des hotels correspondants
		
	}
}
