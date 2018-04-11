package jus.aor.mobilagent.kernel;

import java.net.URI;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Annuaire de courtage pour le TP-BAM
 * 
 * @author CHANET Zoran
 *
 */
public class Courtage extends UnicastRemoteObject implements _Courtage {
	private static final long serialVersionUID = 1L;

	/** la liste de services proposes par agent */
	HashMap<URI, List<String>> courtage;

	public Courtage() throws RemoteException {
		courtage = new HashMap<URI, List<String>>();
	}

	/**
	 * Ajoute un nouveau service à la liste du serveur donné
	 * 
	 * @param uri
	 *            l'URI du serveur proposant un nouvau service
	 * @param name
	 *            le nom du nouveau service proposé
	 */
	public void add(URI uri, String name) throws RemoteException {
		if (courtage.containsKey(uri) && !courtage.get(uri).contains(name))
			courtage.get(uri).add(name);
		else {
			ArrayList<String> val = new ArrayList<String>();
			val.add(name);
			courtage.put(uri, val);
		}
	}

	/**
	 * Retire un sevice de la liste du serveur donné
	 * 
	 * @param uri
	 *            l'URI du serveur ne proposant plus un service
	 * @param name
	 *            le nom du service n'étant plus proposé
	 */
	public void remove(URI uri, String name) throws RemoteException {
		courtage.get(uri).remove(name);
		if (courtage.get(uri).size() == 0)
			courtage.remove(uri);
	}

	/**
	 * Renvoie la liste des services proposés par le serveur donné
	 * 
	 * @param uri
	 *            l'URI du serveur recherché
	 * @return la liste des services proposés par le serveur donné
	 */
	public List<String> getServices(URI uri) throws RemoteException {
		return courtage.get(uri);
	}

	/**
	 * Renvoie la liste des serveurs proposant un service demandé
	 * 
	 * @param service
	 *            le nom du service recherché
	 * @return la liste des serveurs proposant ce service
	 */
	public List<URI> getURI(String service) throws RemoteException {
		List<URI> retour = new ArrayList<URI>();
		for (URI uri : courtage.keySet())
			if (courtage.get(uri).contains(service))
				retour.add(uri);
		return retour;
	}
}
