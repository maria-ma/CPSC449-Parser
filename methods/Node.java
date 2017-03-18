package methods;

import java.util.ArrayList;

public class Node {
	
	String type = null;
	String data = null;
	
	ArrayList<Node> children = new ArrayList<Node>();
	
	//constructor
	public Node(String intype){
		type = intype;
	}
	
	public void addChild(Node child){
		children.add(child);
	}
	public Node addChildReturn(Node child){
		children.add(child);
		return child;
	}
	
	public String getType(){
		return type;
	}
	
	public void setType(String input){
		type = input;
	}
	
	public String getData(){
		return data;
	}
	
	public void setData(String input){
		data=input;
	}
	
	public void printinfo(){
		System.out.println(type);
		System.out.println(data);
		System.out.println(children.size());
	}

}