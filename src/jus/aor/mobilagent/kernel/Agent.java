package jus.aor.mobilagent.kernel;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;

import jus.aor.mobilagent.kernel.AgentServer;
import jus.aor.mobilagent.kernel.Etape;

public class Agent implements _Agent {
	private static final long serialVersionUID = 1L;

	private Route route;
	private transient AgentServer currServ;
	private transient String currServName;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(this.toString() + " Method run : NOT IMPLEMETED YET");

	}

	@Override
	public void init(AgentServer agentServer, String serverName) {
		route = new Route(new Etape(agentServer.site(), _Action.NIHIL));
		currServ = agentServer;
		currServName = serverName;
	}

	@Override
	public void reInit(AgentServer agentServer, String serverName) {
		if(route.hasNext)
			route.next();
		else
			retour().execute();
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
			
			public void execute() {System.out.println("J'AI FINI !");}
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
		// TODO currServ.sendAgent(this, url ?) ?
		System.out.println(this.toString() + " Method move(URL) : NOT IMPLEMETED YET");
	}

	protected String route() {
		return route.toString();
	}

	public String toString() {
		return "Class Agent.java";
	}

}
