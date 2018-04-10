package jus.aor.mobilagent.kernel;

import java.util.List;
import jus.aor.mobilagent.kernel._Action;
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
	private static final long serialVersionUID = -4685428842247857670L;
	
	/** le critère de localisaton choisi */
	private String localisation;
	private ArrayList<Hotel> listHotels;

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
			System.out.println("LFH execute findHotel sur " + currServName);
			listHotels = (ArrayList<Hotel>) currServ.getService("Hotels").call(localisation);
			System.out.println("LFH execute findHotel sur " + currServName);
			for (int i = 0; i < listHotels.size(); i++) {
				Hotel hotel = listHotels.get(i);
				System.out.println(hotel.toString());
			}
		}
	};

	/**
	 * l'action à entreprendre sur le serveur d'annuaire
	 */
	public _Action findTelephone = new _Action() {
		private static final long serialVersionUID = -9129644307555501553L;

		@SuppressWarnings("unchecked")
		@Override
		public void execute() {
			System.out.println("LFH execute findTelephone sur " + currServName);
			List<Numero> liste = (List<Numero>)currServ.getService("Telephones").call(listHotels);
			for(int i=0; i<liste.size(); i++)
				System.out.println(listHotels.get(i).name + " " + liste.get(i).numero);
		}
	};

	// ...
	public String toString() {
		return "Classe LookForHotel.java";
	}
}
