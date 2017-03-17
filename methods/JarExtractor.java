package methods;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

class JarExtractor {

	static JarFile jarFile = null;
	static Class command;

	public static void jarExtractor(String pathToJar, String className) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		//class and jar names here please ok thank you
		//String pathToJar = "Testerpack.jar";
		//String className = "Tester3"
		try {
			jarFile = new JarFile(pathToJar);
		}
		catch (Exception e) {
			FatalErrors.invalidFile(pathToJar);
		}

		Enumeration<JarEntry> e = jarFile.entries();

		URL[] urls = { new URL("jar:file:" + pathToJar+"!/") };
		URLClassLoader cl = URLClassLoader.newInstance(urls);
		boolean found = false;

		while (e.hasMoreElements()) {
		    JarEntry je = e.nextElement();
		    if(!je.getName().contains(className)||!je.getName().endsWith(".class")){
		        continue;
		    }
		    // -6 because of .class
		    found = true;
		    className = je.getName().substring(0,je.getName().length()-6);
		    className = className.replace('/', '.');
		    command = cl.loadClass(className);
		}

		if (found == false)
			FatalErrors.invalidClass(className);

	}

	public static void printFuncList(){
		Method[] methods = command.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			System.out.print("(");
			// get function name
			String methodName = methods[i].getName().toLowerCase();
			System.out.print(methodName);
			// get function types
			Class[] parameterTypes = methods[i].getParameterTypes();
			for (int j = 0; j < parameterTypes.length; j++) {
				String paramName = parameterTypes[j].getSimpleName().toLowerCase();
				paramName = checkName(paramName);
				System.out.print(" " + paramName);
			}
			System.out.print(") : ");
			// print return type
			String returnName = methods[i].getReturnType().getSimpleName().toLowerCase();
			returnName = checkName(returnName);
			System.out.println(returnName);
		}
	}

	public static String checkName(String tmp) {
		if (tmp.equals("integer"))
			tmp = tmp.substring(0,3);
		return tmp;
	}

}