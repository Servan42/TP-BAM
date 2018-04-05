package jus.aor.mobilagent.kernel;

import java.net.URL;
import java.net.URLClassLoader;

public class BAMServerClassLoader extends URLClassLoader {

	public BAMServerClassLoader(URL[] arg0, ClassLoader classLoader) {
		super(arg0);
		// TODO Auto-generated constructor stub BAM
	}

	protected void addURL(URL url) {
		// TODO
		System.out.println(toString() + " addURL(URL) NOT IMPLEMETED YET");
	}

	public String toString() {
		// TODO
		return "Classe BAMServerClassLoader";
	}

}
