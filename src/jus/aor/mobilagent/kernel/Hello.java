package jus.aor.mobilagent.kernel;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import jus.aor.mobilagent.kernel.Agent;
import jus.aor.mobilagent.kernel._Action;
import jus.aor.mobilagent.kernel.Route;

/**
 * Classe de test élémentaire pour le bus à agents mobiles
 * 
 * @author Morat
 */
public class Hello extends Agent {
	private static final long serialVersionUID = 7927326332211864657L;

	/**
	 * construction d'un agent de type hello.
	 * 
	 * // * @param args // * Route initialisee mais potentiellement incomplete,
	 * List<String> // * d'addresse de serveurs, List<String> d'actions associees
	 * 
	 * @param args
	 *            le serveur hébergeant initialement l'agent, le nom logique du
	 *            serveur d'agents
	 */
	public Hello(Object... args) {
		// this.route = (Route) args[0];
		// if (args[1] != null && args[2] != null)
		// for (int i = 0; i < ((List<String>) args[1]).size(); i++) {
		// try {
		// route.add(new Etape(new URI(((List<String>) args[1]).get(i)),
		// (_Action) this.getClass().getField(((List<String>)
		// args[2]).get(i)).get(this)));
		// } catch (IllegalArgumentException | IllegalAccessException |
		// NoSuchFieldException | SecurityException
		// | URISyntaxException e) {
		// e.printStackTrace();
		// }
		// }
		init((AgentServer)args[0], (String)args[1]);
	}

	/**
	 * l'action à entreprendre sur les serveurs visités
	 */
	public _Action doIt = new _Action() {
		private static final long serialVersionUID = -9129644307555501553L;

		@Override
		public void execute() {
			System.out.println("Hello execute doIt sur " + currServName);
		}
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see jus.aor.mobilagent.kernel.Agent#retour()
	 */
	@Override
	protected _Action retour() {
		// return ... TODO;
		System.out.println(toString() + "retour() NOT IMPLEMENTED YET");
		return null;
	}
	// ...

	public String toString() {
		return "Classe Hello.java";
	}
}
