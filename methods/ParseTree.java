package methods;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ParseTree {

	Class targetclass;
	Node root;
	
	public ParseTree(String inputtext, Class targetClass) throws ClassNotFoundException, IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		root = new Node(null);
		Parser parser = new Parser();
		parser.parseExpr(inputtext, root);
		targetclass = targetClass;
		//Jarloader loader = new Jarloader();
		//targetclass = loader.jarload("TESTME2.jar", "Tester1");

	}
	
	public void dotest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		if(root.getType().equalsIgnoreCase("funcall")){
			//root.printinfo();
			System.out.println(doFuncall(root));
		}
		else if((root.getType().equalsIgnoreCase("string")) || (root.getType().equalsIgnoreCase("float") || (root.getType().equalsIgnoreCase("int"))))
			System.out.println(root.getData());
		// else, throw parse exception
	}
	
	public Object doFuncall(Node focus) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class[] mylist = makeListClass(focus);
		Method targetmethod = targetclass.getMethod(focus.children.get(0).getData(),makeListClass(focus));
		return targetmethod.invoke(null,makeListArg(focus));
	
	}
	
	public Object[] makeListArg(Node focus) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Object[] classlist = new Object[focus.children.size()-1];
		for(int i=1;i<focus.children.size();i++){
			if(focus.children.get(i).getType().equalsIgnoreCase("string")){
				classlist[i-1]= focus.children.get(i).getData();
			}
			else if(focus.children.get(i).getType().equalsIgnoreCase("float")){
				classlist[i-1]= Float.parseFloat(focus.children.get(i).getData());
			}
			else if(focus.children.get(i).getType().equalsIgnoreCase("int")){
				//System.out.println(Integer.parseInt(focus.children.get(i).getData()));
				classlist[i-1]= Integer.parseInt(focus.children.get(i).getData());
			}
			else if(focus.children.get(i).getType().equalsIgnoreCase("funcall")){
				
				classlist[i-1]= doFuncall(focus.children.get(i));
			}
		}
		return classlist;
	}
	
	public Class[] makeListClass(Node focus) throws NoSuchMethodException, SecurityException{
		Class[] classlist = new Class[focus.children.size()-1];
		for(int i=1;i<focus.children.size();i++){
			if(focus.children.get(i).getType().equalsIgnoreCase("string")){
				classlist[i-1]= String.class;
			}
			else if(focus.children.get(i).getType().equalsIgnoreCase("float")){
				classlist[i-1]= float.class;
			}
			else if(focus.children.get(i).getType().equalsIgnoreCase("int")){
				classlist[i-1]= int.class;
			}
			else if(focus.children.get(i).getType().equalsIgnoreCase("funcall")){
				String secondname = focus.children.get(i).children.get(0).getData();
				//here
				Class[] secondlist = makeListClass(focus.children.get(i));
				Method targetmethod = targetclass.getMethod(secondname,secondlist);
				classlist[i-1]= targetmethod.getReturnType();
			}
		}
		return classlist;
	}
	
	
	
	public static String eatWhiteSpace(String input){
		while(input.contains("  ")){
			input=input.replace("  ", " ");
		}
		return input;
	}

}
