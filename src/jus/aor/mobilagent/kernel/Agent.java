package jus.aor.mobilagent.kernel;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;

import jus.aor.mobilagent.kernel.AgentServer;
import jus.aor.mobilagent.kernel.Etape;

public class Agent implements _Agent {
	private static final long serialVersionUID = 1L;

	protected Route route;
	protected transient AgentServer currServ;
	protected transient String currServName;
	protected Jar codeBase;
	
	/**
	 * le constructeur auquel on donne le codebase
	 * 
	 * @param args
	 *            String le chemin vers le codebase (.jar)
	 */
	public Agent(Object... args) {
		try {
			codeBase = new Jar((String)args[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		route.get().action.execute();
		route.next();
		if(route.hasNext)
			move();
		else
			retour().execute();
	}

	@Override
	public void init(AgentServer agentServer, String serverName) {
		route = new Route(new Etape(agentServer.site(), _Action.NIHIL));
		currServ = agentServer;
		currServName = serverName;
		if(route.hasNext)
			move();
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
		// TODO Auto-generated method stub
		System.out.println(this.toString() + " Method init<ServiceDescriptor> : NOT IMPLEMETED YET");
	}

	protected _Action retour() {
		return new _Action() {
			private static final long serialVersionUID = -3742771451224038951L;
			
			public void execute() {
				System.out.println("J'AI FINI !");
			}
		};
	}

	protected _Service getService(String name) {
		return currServ.getService(name);
	}

	private void move() {
		try {
			move(route.get().server.toURL());
		} catch (MalformedURLException | NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	protected void move(URL url) {
		String[] addressAndPort = url.toString().split(":");
		try {
			Socket server = new Socket(addressAndPort[0], Integer.valueOf(addressAndPort[1]));
			OutputStream os = server.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(codeBase);
			oos.writeObject(this);
			server.close();
		} catch (NumberFormatException | IOException e) {
			System.out.println("Erreur dans move(URL), host = " + addressAndPort[0] + " port = " + addressAndPort[1]);
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
