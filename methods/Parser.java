package methods;

import java.util.ArrayList;

public class Parser {
	
	public void parseExpr(String input, Node focus){		
		//expr := funcall|value
		if(input.startsWith("(")){
			focus.setType("funcall");
			parseFuncall(input.substring(1, input.length()-1),focus);
		}
		else{
			focus.setType("value");
			parseValue(input, focus);
		}
	}
	
	public void parseFuncall(String input, Node focus){
		//funcall := (identifier {expr}*)
		ArrayList<String> splitlist = new ArrayList<String>();
		chopper(splitlist,input);
		parseIdentifier(splitlist.get(0),focus.addChildReturn(new Node("identifier")));
		for(int i=1;i<splitlist.size();i++){
			parseExpr(splitlist.get(i),focus.addChildReturn(new Node("expr")));
		}
		
	}
	
	public void parseIdentifier(String input, Node focus){
		//identifier := alpha{alphanum}*
		focus.setData(input);
	}
	
	public void parseValue(String input, Node focus){
		//value := integer|float|string
		
		if(input.startsWith("\"")){
			focus.setType("String");
			input = input.substring(1, (input.length()-1));
			focus.setData(input);
		}
		else if ((input.startsWith("-")) || (input.startsWith("+")) || (Character.isDigit(input.charAt(0)))) {	
			boolean checkNum = checkDigit(input);
			if (checkNum == true) {		
				if(input.contains(".")){
					focus.setType("float");
					focus.setData(input);
				}
				else{
					focus.setType("int");
					focus.setData(input);
				}					
			}
			else
				System.out.println("throw unmatched type error here");
		}
		else
			System.out.println("throw unmatched type error here");
	}
	
	public boolean checkDigit(String input) {
		boolean digitOK = true;
		for (int i = 1; i < input.length(); i++) {
			if (!(Character.isDigit(input.charAt(i))) && (input.charAt(i) != '.'))
				digitOK = false;
		}
		return digitOK;
	}

	//CHOPPER IS HERE JUST FYI
	public void chopper(ArrayList<String> choppedlist,String input){
		
		if(input.length()==0){
			//do nothing plz
		}
		else if(input.substring(0,1).equals("\"")) {
			int count = 1;
			if (!(input.substring(1).contains("\"")))
				System.out.println("throw cant find end of string exception here");
			else {
				for(int i = 1; i < (input.substring(1,(input.lastIndexOf("\"")+1)).length()); i++)
					count++;
				choppedlist.add(input.substring(0,count+1));
			}
		}
		else if(!input.contains(" ")){
			choppedlist.add(input);
		}
		else if(!input.substring(0,1).equals("(")){
			choppedlist.add(substringinc(input,0,input.indexOf(" ")-1));
			if(input.indexOf(" ")+1<input.length()){
				chopper(choppedlist,input.substring(input.indexOf(" ")+1));
			}
		}
		else{
			int bracketcount = 1;
			int counter = 1;
			while(bracketcount>0){
				if(input.substring(counter,counter+1).equals("(")){
					bracketcount++;
					counter++;
				}
				else if(input.substring(counter,counter+1).equals(")")){
					bracketcount--;
					counter++;
				}
				else{
					counter++;
				}
			}
			choppedlist.add(substringinc(input,0,counter-1));
			if(counter+1<input.length()){
				chopper(choppedlist,input.substring(counter+1));
			}
			
		}
	}
	
	public static String substringinc(String input, int start,int end){
		if(end+1<input.length()){
			return input.substring(start,end+1);
		}
		else{
			return input.substring(start);
		}
	}
	


}
