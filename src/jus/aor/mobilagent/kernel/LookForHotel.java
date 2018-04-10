package jus.aor.mobilagent.kernel;

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
 */
public class LookForHotel extends Agent {
	/** le critère de localisaton choisi */
	private String localisation;

	// ...
	/**
	 * Définition de l'objet représentant l'interrogation.
	 * 
	 * @param args
	 *            String[] la localisation cherchée, la chemin vers le jar de
	 *            codeBase
	 */
	public LookForHotel(String... args) {
		super(args[1]);
		localisation = args[0];
	}

	/**
	 * réalise une interrogation
	 * 
	 * @return la durée de l'interrogation
	 * @throws RemoteException
	 */
	public long call() {
		// TODO
		long timeDebut = new Date().getTime();
		System.out.println(toString() + " call() NOT IMPLEMETED YET");
		long timeFin = new Date().getTime();
		System.out.println("Elapsed time : " + (timeFin - timeDebut));
		return timeFin - timeDebut;
	}

	/**
	 * l'action à entreprendre sur les serveurs d'hotels
	 */
	public _Action findHotel = new _Action() {
		private static final long serialVersionUID = -9129644307555501553L;

		@SuppressWarnings("unchecked")
		@Override
		public void execute() {
			ArrayList<Hotel> listChaine1 = (ArrayList<Hotel>) currServ.getService("Hotels").call(localisation);
			System.out.println("LFH execute findHotel sur " + currServName);
			for (int i = 0; i < listChaine1.size(); i++) {
				Hotel host = listChaine1.get(i);
				System.out.println(host.toString());
			}
		}
	};

	/**
	 * l'action à entreprendre sur le serveur d'annuaire
	 */
	public _Action findTelephone = new _Action() {
		private static final long serialVersionUID = -9129644307555501553L;

		@Override
		public void execute() {
			System.out.println("LFH execute findTelephone sur " + currServName);
		}
	};

	// ...
	public String toString() {
		return "Classe LookForHotel.java";
	}
}
