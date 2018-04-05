package jus.aor.mobilagent.hello;

import jus.aor.mobilagent.kernel.Agent;
import jus.aor.mobilagent.kernel._Action;

/**
 * Classe de test élémentaire pour le bus à agents mobiles
 * 
 * @author Morat
 */
public class Hello extends Agent {
	String localisation;
	
	/**
	 * construction d'un agent de type hello.
	 * 
	 * @param args
	 *            aucun argument n'est requis
	 */
	public Hello(Object... args) {
		// TODO
		localisation = (String)args[0];
	}

	/**
	 * l'action à entreprendre sur les serveurs visités
	 */
	protected _Action doIt = new _Action() {
		//TODO Récupérer les hotels correspondant à la localisation
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
