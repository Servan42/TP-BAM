package jus.aor.mobilagent.kernel;

import java.io.IOException;
import java.util.List;

import jus.aor.mobilagent.kernel.Jar;
import jus.aor.mobilagent.kernel._Action;

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
		System.out.println(toString() + " call() NOT IMPLEMETED YET");
		return 0;
	}

	/**
	 * l'action à entreprendre sur les serveurs d'hotels
	 */
	public _Action findHotel = new _Action() {
		private static final long serialVersionUID = -9129644307555501553L;

		@Override
		public void execute() {
			System.out.println("LFH execute findHotel sur " + currServName);
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
			_Service Telephones = currServ.getService("Telephones");
			List<Numero> liste = (List<Numero>)Telephones.call(listHotels);
			for(int i=0; i<liste.size(); i++)
				System.out.println(listHotels.get(i).name + " " + liste.get(i).numero);
		}
	};

	// ...
	public String toString() {
		return "Classe LookForHotel.java";
	}
}
