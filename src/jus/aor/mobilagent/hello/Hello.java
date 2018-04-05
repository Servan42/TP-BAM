package jus.aor.mobilagent.hello;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import jus.aor.mobilagent.kernel.Agent;
import jus.aor.mobilagent.kernel._Action;

/**
 * Classe de test élémentaire pour le bus à agents mobiles
 * 
 * @author Morat
 */
public class Hello extends Agent {
	private static final long serialVersionUID = 7927326332211864657L;

	HashMap<String /*Address*/, String /*Action*/> etapes;
	
	/**
	 * construction d'un agent de type hello.
	 * 
	 * @param args
	 *            Route à parcourir
	 */
	public Hello(Object... args) {
		// TODO
		System.out.println(toString() + "Hello(Object...) NOT IMPLEMENTED YET");
		
		// Recuperation de la liste de serveurs et actions associees
//		for(int i=0; i<((List<String>)args[0]).size(); i++) {
//			etapes.put(((List<String>)args[0]).get(i), ((List<String>)args[1]).get(i));
//		}
	}

	/**
	 * l'action à entreprendre sur les serveurs visités
	 */
	protected _Action doIt = new _Action() {
		private static final long serialVersionUID = -9129644307555501553L;

		// TODO
		@Override
		public void execute() {
			// TODO Auto-generated method stub

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
