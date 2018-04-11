/**
 * J<i>ava</i> U<i>tilities</i> for S<i>tudents</i>
 */
package jus.aor.mobilagent.kernel;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.org.apache.bcel.internal.generic.LoadClass;

import jus.aor.mobilagent.kernel.BAMAgentClassLoader;

/**
 * Le serveur principal permettant le lancement d'un serveur d'agents mobiles et
 * les fonctions permettant de déployer des services et des agents.
 * 
 * @author Morat
 */
public final class Server implements _Server {
	/** le nom logique du serveur */
	protected String name;
	/**
	 * le port où sera ataché le service du bus à agents mobiles. Par défaut on
	 * prendra le port 10140
	 */
	protected int port = 10140;
	/** le server d'agent démarré sur ce noeud */
	protected AgentServer agentServer;
	/** le nom du logger */
	protected String loggerName;
	/** le logger de ce serveur */
	protected Logger logger = null;

	/**
	 * Démarre un serveur de type mobilagent
	 * 
	 * @param port
	 *            le port d'écoute du serveur d'agent
	 * @param name
	 *            le nom du serveur
	 */
	public Server(final int port, final String name) {
		this.name = name;
		try {
			this.port = port;
			/* mise en place du logger pour tracer l'application */
			loggerName = "jus/aor/mobilagent/" + InetAddress.getLocalHost().getHostName() + "/" + this.name;
			logger = Logger.getLogger(loggerName);
			/* démarrage du server d'agents mobiles attaché à cette machine */
			agentServer = new AgentServer(port, name);
			new Thread(new Runnable() {
				public void run() {
					try {
						agentServer.run();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();

			/* temporisation de mise en place du server d'agents */
			Thread.sleep(1000);
		} catch (Exception ex) {
			// logger.log(Level.FINE, " erreur durant le lancement du serveur" + this, ex);
			System.out.println("Erreur durant le lancement du serveur " + ex);
			ex.printStackTrace();
			return;
		}
	}

	/**
	 * Ajoute le service caractérisé par les arguments
	 * 
	 * @param name
	 *            nom du service
	 * @param classeName
	 *            classe du service
	 * @param codeBase
	 *            codebase du service
	 * @param args
	 *            arguments de construction du service
	 */
	public final void addService(String name, String classeName, String codeBase, Object... args) {
		try {
			BAMAgentClassLoader bamacl = new BAMAgentClassLoader(codeBase, this.getClass().getClassLoader());
			bamacl.integrateCode(new Jar(codeBase));
			_Service<?> service = (_Service<?>) Class.forName(classeName).getConstructor(String.class)
					.newInstance(args[0]);
			agentServer.addService(name, service);
			Socket server = new Socket("localhost",4444);
			OutputStream os = server.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject("SERVER");
			oos.writeObject(name);
			oos.close();
		} catch (Exception ex) {
			// logger.log(Level.FINE, " erreur durant le lancement du serveur" + this, ex);
			System.out.println("Erreur durant le lancement du serveur : " + ex);
			ex.printStackTrace();
			return;
		}
	}

	/**
	 * deploie l'agent caractérisé par les arguments sur le serveur
	 * 
	 * @param classeName
	 *            classe du service
	 * @param args
	 *            arguments de construction de l'agent
	 * @param codeBase
	 *            codebase du service
	 * @param etapeAddress
	 *            la liste des adresse des étapes
	 * @param etapeAction
	 *            la liste des actions des étapes
	 */
	public final void deployAgent(String className, Object[] args, String codeBase, List<String> etapeAddress,
			List<String> etapeAction) {
		try {
			System.out.println("Création de l'agent... ");
			// FIXME Utiliser className, le nom de la classe donnée dans le xml
			// _Agent agent =
			// (_Agent)Class.forName(className).getConstructors()[0].newInstance(args);//getConstructor(String.class).newInstance("hey");
			_Agent agent = new LookForHotel((String) args[0], codeBase);
			agent.init(agentServer, name);
			System.out.println("Creation de la route de l'agent...");
			for (int i = 0; i < etapeAddress.size(); i++)
				agent.addEtape(new Etape(new URI(etapeAddress.get(i)),
						(_Action) (agent.getClass().getDeclaredField(etapeAction.get(i)).get(agent))));
			System.out.println("Lancement de l'agent...");
			new Thread(agent).start();
		} catch (Exception ex) {
			System.out.println("Erreur durant le lancement du serveur : " + ex);
			ex.printStackTrace();
			return;
		}
	}

	/**
	 * deploie l'agent caractérisé par les arguments sur le serveur
	 * 
	 * @param classeName
	 *            classe du service
	 * @param args
	 *            arguments de construction de l'agent
	 * @param codeBase
	 *            codebase du service
	 * @param list
	 *            ???
	 */
	public final void deployAgent(String classeName, Object[] args, String codeBase, List<ServiceDescriptor> list) {
		try {
			// A COMPLETER en terme de startAgent
			// TODO
			System.out.println(this.toString() + " Method deployAgent<ServiceDescriptor> : NOT IMPLEMETED YET");
		} catch (Exception ex) {
//			logger.log(Level.FINE, " erreur durant le lancement du serveur" + this, ex);
			return;
		}
	}

	/**
	 * Primitive permettant de "mover" un agent sur ce serveur en vue de son
	 * exécution immédiate.
	 * 
	 * @param agent
	 *            l'agent devant être exécuté
	 * @param loader
	 *            le loader à utiliser pour charger les classes.
	 * @throws Exception
	 */
	protected void startAgent(_Agent agent, BAMAgentClassLoader loader) throws Exception {
		// A COMPLETER
		// TODO
		System.out.println(this.toString() + " Method startAgent : NOT IMPLEMETED YET");
	}

	public String toString() {
		return "Class Server.java";
	}
}
