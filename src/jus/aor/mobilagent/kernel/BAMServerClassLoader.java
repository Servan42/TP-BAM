package jus.aor.mobilagent.kernel;

import java.net.URL;
import java.net.URLClassLoader;

public class BAMServerClassLoader extends URLClassLoader {

	public BAMServerClassLoader(URL[] arg0, ClassLoader classLoader) {
		super(arg0, classLoader);	
	}

	protected void addURL(URL url) {
		super.addURL(url);
	}

	public String toString() {
		return "BAMServerClassLoader " + super.toString();
	}

}
