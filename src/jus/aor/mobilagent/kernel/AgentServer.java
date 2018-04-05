package jus.aor.mobilagent.kernel;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.*;

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
		// A COMPLETER
		// TODO
		System.out.println(this.toString() + " | Class AgentServer.java Method run : NOT FULLY IMPLEMENTED YET");
	
		running = true;
		Socket client;
		// Lorsque l'agent se presente
		client = s.accept();
		System.out.println("Client " + client.getInetAddress() + " connected to server " + this.toString());
		Agent agent = (Agent) this.getAgent(client);
		// Il active cet objet qui execute l'action que l'agent a à realiser sur ce server.
		agent.run();
	}
	
	private _Agent getAgent(Socket socket) throws IOException, ClassNotFoundException {
		System.out.println("Class AgentServer.java | Method getAgent : NOT FULLY IMPLEMETED YET");
		
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		BAMAgentClassLoader acl = (BAMAgentClassLoader) ois.readObject();
		
		AgentInputStream ais = new AgentInputStream(socket.getInputStream(), acl);
		ObjectStreamClass cl = (ObjectStreamClass) ais.readObject();
		Class<?> a = ais.resolveClass(cl);
		
		// Le server charge son code base...
		/*
		String codebase = (String) ais.readObject();
		// ...et l'objet représentant cet agent.
		ObjectStreamClass cl = (ObjectStreamClass) ais.readObject();
		Class<?> a = ais.resolveClass(cl);
		*/
		//agent = (Agent) ais.readObject();
		
		// Fermeture de l'input stream
		ais.close();
		
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
			return "mobilagent://" + InetAddress.getLocalHost().getHostName() + ":" + port;
		} catch (UnknownHostException e) {
			return "mobilagent://";
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
	 * restitue l'URI de ce serveur qui est de la forme :
	 * "mobilagent://<host>:<port>" ou null si cette opération échoue.
	 * 
	 * @return l'URI du serveur
	 */
	URI site() {
		try {
			return new URI("mobilagent://" + InetAddress.getLocalHost().getHostName() + ":" + port);
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
