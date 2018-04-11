package jus.aor.mobilagent.kernel;

import java.net.URI;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface _Courtage extends Remote {
	public void add(URI uri, String name) throws RemoteException;
	public void remove(URI uri, String name) throws RemoteException;
	public List<String> getServices(URI uri) throws RemoteException;
	public List<URI> getURI(String service) throws RemoteException;
}
