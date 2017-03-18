package methods;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarLoader {
	
	public JarLoader(){
		
	}
	
	public Class jarLoad(String jarname, String classname) throws IOException, ClassNotFoundException{
		String pathToJar = jarname;
		Class returnthis = null;
		
		JarFile jarFile = new JarFile(pathToJar);
		Enumeration<JarEntry> e = jarFile.entries();

		URL[] urls = { new URL("jar:file:" + pathToJar+"!/") };
		URLClassLoader cl = URLClassLoader.newInstance(urls);

		while (e.hasMoreElements()) {
		    JarEntry je = e.nextElement();
		    if(!je.getName().contains(classname)||!je.getName().endsWith(".class")){
		        continue;
		    }
		    // -6 because of .class
		    String className = je.getName().substring(0,je.getName().length()-6); 
		    className = className.replace('/', '.');
		    returnthis = cl.loadClass(className);
		}
		System.out.println(returnthis);
	    return returnthis;
	}

}
