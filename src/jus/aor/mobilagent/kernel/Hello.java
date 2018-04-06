package jus.aor.mobilagent.kernel;

import jus.aor.mobilagent.kernel.Agent;
import jus.aor.mobilagent.kernel._Action;

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
	 * @param args
	 *            le serveur hébergeant initialement l'agent, le nom logique du
	 *            serveur d'agents
	 */
	public Hello(Object... args) {
		super(args[0]);
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
		return new _Action() { 
			private static final long serialVersionUID = -3742771451224038951L;
			
			public void execute() {System.out.println("J'AI FINI !");}
		};
	}
	
	public String toString() {
		return "Classe Hello.java";
	}
}
