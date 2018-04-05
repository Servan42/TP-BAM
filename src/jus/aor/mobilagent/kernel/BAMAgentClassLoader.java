package jus.aor.mobilagent.kernel;

public class BAMAgentClassLoader extends ClassLoader {
	AgentInputStream ais;

	public BAMAgentClassLoader(String jarName, ClassLoader parent) {
		// TODO Definit toutes les classes fournies dans le fichier jarName
		System.out.println(toString() + " BAMAgentClassLoader(String, ClassLoader) NOT IMPLEMENTED YET");
	}

	public BAMAgentClassLoader(ClassLoader cl) {
		// TODO
		System.out.println(toString() + " BAMAgentClassLoader(ClassLoader) NOT IMPLEMENTED YET");
	}

	public void integrateCode(Jar jar) {
		// TODO
		System.out.println(toString() + " integrateCode(Jar) NOT IMPLEMENTED YET");
	}

	private String className(String cn) {
		// TODO
		System.out.println(toString() + " className(String) NOT IMPLEMENTED YET");
		return null;
	}

	public Jar extractCode() {
		// TODO
		System.out.println(toString() + " extractCode() NOT IMPLEMENTED YET");
		return null;
	}

	public String toString() {
		// TODO
		return "Classe BAMAgentClassLoader";
	}
}
