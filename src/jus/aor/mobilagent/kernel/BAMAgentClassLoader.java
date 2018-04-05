package jus.aor.mobilagent.kernel;

import java.io.IOException;

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
