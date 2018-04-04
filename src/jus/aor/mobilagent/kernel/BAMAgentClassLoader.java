package jus.aor.mobilagent.kernel;

public class BAMAgentClassLoader extends ClassLoader {
	AgentInputStream ais;

	public BAMAgentClassLoader(String jarName, ClassLoader parent) {
		// TODO Definit toutes les classes fournies dans le fichier jarName
		System.out.println("NOT IMPLEMENTED YET");
	}

	public BAMAgentClassLoader(ClassLoader cl) {
		// TODO
		System.out.println("NOT IMPLEMENTED YET");
	}

	public void integrateCode(Jar jar) {
		// TODO
		System.out.println("NOT IMPLEMENTED YET");
	}

	private String className(String cn) {
		// TODO
		System.out.println("NOT IMPLEMENTED YET");
		return null;
	}

	public Jar extractCode() {
		// TODO
		System.out.println("NOT IMPLEMENTED YET");
		return null;
	}

	public String toString() {
		// TODO
		System.out.println("NOT IMPLEMENTED YET");
		return null;
	}
}
