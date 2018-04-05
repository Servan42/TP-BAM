package jus.aor.mobilagent.kernel;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import jus.aor.mobilagent.kernel.AgentServer;
import jus.aor.mobilagent.kernel.Etape;

public class Agent implements _Agent {
	private static final long serialVersionUID = 1L;

	Route route;
	String currServ;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(this.toString() + " Method run : NOT IMPLEMETED YET");

	}

	@Override
	public void init(AgentServer agentServer, String serverName) {
		route = new Route(new Etape(agentServer.site(), _Action.NIHIL));
		currServ = serverName;
	}

	@Override
	public void reInit(AgentServer server, String serverName) {
		// TODO que faire de server ?
		currServ = serverName;
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
		// TODO
		System.out.println(this.toString() + " Method retour : NOT IMPLEMETED YET");
		return null;
	}

	protected _Service getService(String string) {
		// TODO
		System.out.println(this.toString() + " Method getService : NOT IMPLEMETED YET");
		return null;
	}

	private void move() {
		// TODO URI dans les etapes, URL demandé par move(URL), sucks
		System.out.println(this.toString() + " Method move : NOT IMPLEMETED YET");
		//move(route.get().server);
	}

	protected void move(URL url) {
		// TODO
		System.out.println(this.toString() + " Method move(URL) : NOT IMPLEMETED YET");
	}

	protected String route() {
		// TODO
		System.out.println(this.toString() + " Method route : NOT IMPLEMETED YET");
		return null;
	}

	public String toString() {
		return "Class Agent.java";
	}

}
