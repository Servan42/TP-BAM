package jus.aor.mobilagent.kernel;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.Date;
import java.util.List;

public class Agent implements _Agent {
	private static final long serialVersionUID = 1L;

	protected Route route;
	protected transient AgentServer currServ;
	protected transient String currServName;
	protected Jar codeBase;
	protected long timeDebut;
	protected long timeFin;

	/**
	 * le constructeur auquel on donne le codebase
	 * 
	 * @param args
	 *            String le chemin vers le codebase (.jar)
	 */
	public Agent(Object... args) {
		try {
			codeBase = new Jar((String) args[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		if (timeDebut == 0)
			timeDebut = new Date().getTime();
		route.get().action.execute();
		route.next();
		if (route.hasNext)
			move();
		else
			retour().execute();
	}

	@Override
	public void init(AgentServer agentServer, String serverName) {
		route = new Route(new Etape(agentServer.site(), _Action.NIHIL));
		currServ = agentServer;
		currServName = serverName;
	}

	@Override
	public void reInit(AgentServer agentServer, String serverName) {
		currServ = agentServer;
		currServName = serverName;
	}

	@Override
	public void addEtape(Etape etape) {
		route.add(etape);
	}

	@Override
	public void init(List<ServiceDescriptor> list) {
		System.out.println(this.toString() + " Method init<ServiceDescriptor> : NOT IMPLEMETED YET");
	}

	protected _Action retour() {
		return new _Action() {
			private static final long serialVersionUID = -3742771451224038951L;

			public void execute() {
				System.out.println("Agent : Over.");
				timeFin = new Date().getTime();
				System.out.println("Elapsed time : " + (timeFin - timeDebut) + " ms");
			}
		};
	}

	@SuppressWarnings("rawtypes")
	protected _Service getService(String name) {
		return currServ.getService(name);
	}

	private void move() {
		try {
			System.out.println("Moving to " + route.get().server);
			move(route.get().server.toURL());
		} catch (Exception e) {
			System.out.println("Erreur à la récupération de l'URL");
			e.printStackTrace();
		}
	}

	protected void move(URL url) {
		try {
			Socket server = new Socket(url.getHost(), url.getPort());
			OutputStream os = server.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject("AGENT");
			oos.writeObject(codeBase);
			oos.writeObject(this);
			oos.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Erreur dans move(URL), host = " + url.getHost() + " port = " + url.getPort());
			e.printStackTrace();
		}
	}

	protected String route() {
		return route.toString();
	}

	public String toString() {
		return "Class Agent.java";
	}

}
