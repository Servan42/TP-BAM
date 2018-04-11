package jus.aor.mobilagent.kernel;

import java.io.FileNotFoundException;
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
		} catch (FileNotFoundException ex) {
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.parent = parent;
	}

	public BAMAgentClassLoader(ClassLoader parent) {
		this.parent = parent;
	}

	/**
	 * This function loads the agent's code on the server from the jar it
	 * transports
	 * 
	 * @param jar
	 */
	public void integrateCode(Jar jar) {
		for (Map.Entry<String, byte[]> item : jar) {
			if (findLoadedClass(this.className(item.getKey())) != null) {
				defineClass(this.className(item.getKey()), item.getValue(), 0, item.getValue().length);
			}
		}
		if (this.jar == null)
			this.jar = jar;
	}

	private String className(String cn) {
		return cn.substring(0, cn.indexOf(".class")).replace('/', '.');
	}

	/**
	 * Lors de l'envoi, recupère le code au niveau du classe loader
	 * 
	 * @return
	 */
	public Jar extractCode() {
		// TODO PAS SÛR DU TOUT
		return jar;
	}

	public String toString() {
		// TODO
		return "Classe BAMAgentClassLoader";
	}
}
