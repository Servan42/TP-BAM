package jus.aor.mobilagent.kernel;

import java.io.IOException;
import java.util.Map;

public class BAMAgentClassLoader extends ClassLoader {
	AgentInputStream ais;
	String jarName;
	Jar jar;
	ClassLoader parent;

	public BAMAgentClassLoader(String jarName, ClassLoader parent) {
		this.jarName = jarName;
		try {
			this.jar = new Jar(jarName);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.parent = parent;
	}

	public BAMAgentClassLoader(ClassLoader parent) {
		this.parent = parent;
	}

	public void integrateCode(Jar jar) {
		for (Map.Entry<String, byte[]> item : jar) {
			if (findLoadedClass(item.getKey()) != null) {
				defineClass(null, item.getValue(), 0, item.getValue().length);
			}
		}
	}

	private String className(String cn) {
		// Peut etre...
		byte[] classname = jar.getClass(cn);
		return classname.toString();
	}

	/**
	 * Lors de l'envoi, recupère le code au niveau du classe loader
	 * 
	 * @return
	 */
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
