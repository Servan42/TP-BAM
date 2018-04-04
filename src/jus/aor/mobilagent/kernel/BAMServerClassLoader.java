package jus.aor.mobilagent.kernel;

import java.net.URL;
import java.net.URLClassLoader;

public class BAMServerClassLoader extends URLClassLoader {

	public BAMServerClassLoader(URL[] arg0, ClassLoader classLoader) {
		super(arg0);
		// TODO Auto-generated constructor stub BAM
	}
	
	protected void addURL(URL url){
		// TODO
		System.out.println("NOT IMPLEMETED YET");
	}
	
	public String toString(){
		// TODO
		System.out.println("NOT IMPLEMETED YET");
		return null;
	}

}
