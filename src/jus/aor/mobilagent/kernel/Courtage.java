package jus.aor.mobilagent.kernel;

import java.net.URI;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Courtage extends UnicastRemoteObject {
	HashMap<URI, List<String>> courtage;

	public Courtage() throws RemoteException {
		courtage = new HashMap<URI, List<String>>();
	}

	public void add(URI uri, String name) throws RemoteException {
		if (courtage.containsKey(uri))
			courtage.get(uri).add(name);
		else {
			ArrayList<String> val = new ArrayList<String>();
			val.add(name);
			courtage.put(uri, val);
		}
	}

	public void remove(URI uri, String name) throws RemoteException {
		courtage.get(uri).remove(name);
		if (courtage.get(uri).size() == 0)
			courtage.remove(uri);
	}

	public List<String> getServices(URI uri) throws RemoteException {
		return courtage.get(uri);
	}

	public List<URI> getURI(String service) throws RemoteException {
		List<URI> retour = new ArrayList<URI>();
		for (URI uri : courtage.keySet())
			if (courtage.get(uri).contains(service))
				retour.add(uri);
		return retour;
	}
}
