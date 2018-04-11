package jus.aor.mobilagent.kernel;

import java.net.URI;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface _Courtage extends Remote {
	/**
	 * Ajoute un nouveau service à la liste du serveur donné
	 * 
	 * @param uri
	 *            l'URI du serveur proposant un nouvau service
	 * @param name
	 *            le nom du nouveau service proposé
	 */
	public void add(URI uri, String name) throws RemoteException;
	
	/**
	 * Retire un sevice de la liste du serveur donné
	 * 
	 * @param uri
	 *            l'URI du serveur ne proposant plus un service
	 * @param name
	 *            le nom du service n'étant plus proposé
	 */
	public void remove(URI uri, String name) throws RemoteException;
	
	/**
	 * Renvoie la liste des services proposés par le serveur donné
	 * 
	 * @param uri
	 *            l'URI du serveur recherché
	 * @return la liste des services proposés par le serveur donné
	 */
	public List<String> getServices(URI uri) throws RemoteException;
	
	/**
	 * Renvoie la liste des serveurs proposant un service demandé
	 * 
	 * @param service
	 *            le nom du service recherché
	 * @return la liste des serveurs proposant ce service
	 */
	public List<URI> getURI(String service) throws RemoteException;
}
