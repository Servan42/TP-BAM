package jus.aor.mobilagent.kernel;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Le server qui supporte le modèle du bus à agents mobiles "mobilagent".
 * Lorsqu'un agent se présente, le serveur charge son codebase et l'objet
 * représentant cet agent, puis il active cet objet qui exécute l'action que
 * l'agent a à réaliser sur ce serveur.
 * 
 * @author Morat
 */
final class AgentServer {
	/** le logger de ce serveur */
	private Logger logger;
	/** La table des services utilisables sur ce serveur */
	private Map<String, _Service<?>> services;
	/** Le port auquel est attaché le serveur */
	private int port;
	/** l'état du serveur */
	private boolean running;
	/** la socket de communication du bus */
	private ServerSocket s;
	/** le nom logique de ce serveur */
	private String name;
	/** l'annuaire de services */
	private Courtage courtage = null;

	/**
	 * L'initialisation du server
	 * 
	 * @param port
	 *            the port où est attaché le service du bus à agents mobiles
	 * @param name
	 *            le nom du serveur
	 * @throws Exception
	 *             any exception
	 */
	AgentServer(int port, String name) throws Exception {
		this.name = name;
		logger = Logger.getLogger("jus.aor.mobilagent." + InetAddress.getLocalHost().getHostName() + "." + name);
		this.port = port;
		services = new HashMap<String, _Service<?>>();
		s = new ServerSocket(port);
	}

	/**
	 * le lancement du serveur
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	void run() throws IOException, ClassNotFoundException {
		running = true;
		Socket client;
		while (true) {
			client = s.accept();
			BAMAgentClassLoader acl = new BAMAgentClassLoader(this.getClass().getClassLoader());
			AgentInputStream ais = new AgentInputStream(client.getInputStream(), acl);
			String typeClient = (String) ais.readObject();
			System.out.println("Type client : " + typeClient);
			if (typeClient.equals("AGENT")) {
				System.out.println("Client " + client.getInetAddress() + " connected to server " + this.toString());
				Agent agent = (Agent) this.getAgent(client, ais, acl);
				agent.reInit(this, name);
				new Thread(agent).start();
			} else if (typeClient.equals("SERVER")) {
				try {
					putServiceInRMI(client, ais, acl);
				} catch (URISyntaxException | AlreadyBoundException e) {
					e.printStackTrace();
				}
			}
			ais.close();
		}
	}

	/**
	 * 
	 * @param client
	 * @param ais
	 * @param acl
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws AlreadyBoundException
	 */
	private void putServiceInRMI(Socket client, AgentInputStream ais, BAMAgentClassLoader acl)
			throws ClassNotFoundException, IOException, URISyntaxException, AlreadyBoundException {
		if (courtage == null) {
			// Creer courtage et le placer dans le registre
			courtage = new Courtage();
			LocateRegistry.createRegistry(5555);
			java.rmi.Naming.bind("//localhost:5555/Courtage", courtage);
		}

		String uri = (String) ais.readObject();
		String serviceName = (String) ais.readObject();
		System.out.println("AJOUT DU SERVICE " + serviceName + " DANS L'ANNUAIRE POUR " + uri);
		courtage.add(new URI(uri), serviceName);
	}

	private _Agent getAgent(Socket socket, AgentInputStream ais, BAMAgentClassLoader acl)
			throws IOException, ClassNotFoundException {
		Jar jar = (Jar) ais.readObject();
		acl.integrateCode(jar);
		_Agent agent = (_Agent) ais.readObject();
		return agent;
	}

	/**
	 * ajoute un service au serveur
	 * 
	 * @param name
	 *            le nom du service
	 * @param service
	 *            le service
	 */
	void addService(String name, _Service<?> service) {
		services.put(name, service);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		try {
			return "http://" + InetAddress.getLocalHost().getHostName() + ":" + port;
		} catch (UnknownHostException e) {
			return "http://";
		}
	}

	/**
	 * restitue le service de nom name ou null si celui-ci n'est pas attaché au
	 * serveur.
	 * 
	 * @param name
	 * @return le service souhaité ou null
	 */
	_Service<?> getService(String name) {
		return services.get(name);
	}

	/**
	 * restitue l'URI de ce serveur qui est de la forme : "http://<host>:<port>"
	 * ou null si cette opération échoue.
	 * 
	 * @return l'URI du serveur
	 */
	URI site() {
		try {
			return new URI("http://" + InetAddress.getLocalHost().getHostName() + ":" + port);
		} catch (Exception e) {
			return null;
		}
	}
}

/**
 * ObjectInputStream spécifique au bus à agents mobiles. Il permet d'utiliser le
 * loader de l'agent.
 * 
 * @author Morat
 */
class AgentInputStream extends ObjectInputStream {
	/**
	 * le classLoader à utiliser
	 */
	BAMAgentClassLoader loader;

	AgentInputStream(InputStream is, BAMAgentClassLoader cl) throws IOException {
		super(is);
		loader = cl;
	}

	protected Class<?> resolveClass(ObjectStreamClass cl) throws IOException, ClassNotFoundException {
		return loader.loadClass(cl.getName());
	}

	public String toString() {
		return "Class AgentInputStream";
	}
}
