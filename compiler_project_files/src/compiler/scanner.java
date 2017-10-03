package compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class scanner {
	String text;
	ArrayList<ArrayList<String>> list = new ArrayList<>();
	public scanner() {
	}
	public void setText(String text) {
		this.text = text;
		processText();
	}
	private void processText() {
		//removing comments first.
		//first comment
		list.clear();
		int firstIndex = text.indexOf("{");
		int secondIndex = text.indexOf("}");
		if(firstIndex != -1 && secondIndex != -1)
			text = text.substring(0, firstIndex) + text.substring(secondIndex+1, text.length());
		text = text.replaceAll("\\{(.|\r\n)*\\}", "");
		text = text.trim();
		
		//reserved words and symbols
		Map<String, String> reservedWords = new HashMap<String, String>();
		reservedWords.put("if", "IF");
		reservedWords.put("then", "THEN");
		reservedWords.put("else", "ELSE");
		reservedWords.put("end", "END");
		reservedWords.put("repeat", "REPEAT");
		reservedWords.put("until", "UNTIL");
		reservedWords.put("read", "READ");
		reservedWords.put("write", "WRITE");
		
		Map<String, String> symbols = new HashMap<String, String>();
		symbols.put("+", "addition symbol");
		symbols.put("-", "subtraction symbol");
		symbols.put("*", "multiplication symbol");
		symbols.put("/", "division symbol");
		symbols.put("=", "equal symbol");
		symbols.put("<", "lessthan symbol");
		symbols.put("(", "left parathensis symbol");
		symbols.put(")", "right parathensis symbol");
		symbols.put(";", "semicolon symbol");
		symbols.put(":=", "assign symbol");
		
		//putting space before and after each special character (binary or unary ones)
		text = text.replace("+", " + " );
	    text = text.replace("-", " - ");
	    text = text.replace("*", " * ");
	    text = text.replace("/", " / ");
	    text = text.replace(":=", " .. ");
	    text = text.replace("=", " = ");
	    text = text.replace("<", " < ");
	    //text = text.replace("(", " ( ");
	    //text = text.replace(")", " ) ");
	    text = text.replace(";", " ; ");
	    text = text.replace(" .. ", " := ");
	    
	    String[] tokens = text.split(" ");
	    for(String token : tokens) {
	    	token = token.trim();
	    	ArrayList<String> temp = new ArrayList<>();
	    	if(reservedWords.containsKey(token)) {
	    		temp.add(token);
	    		temp.add(reservedWords.get(token));
	    		list.add(temp);
	    	}
	    	else if(symbols.containsKey(token)) {
	    		temp.add(token);
	    		temp.add(symbols.get(token));
	    		list.add(temp);
	    	}
	    	else if(token.matches("\\(\\d+\\)")) {
	    		temp.add(token);
	    		temp.add("number in parathensis");
	    		list.add(temp);
	    	}
	    	else if(token.matches("\\([a-z]+\\)")) {
	    		temp.add(token);
	    		temp.add("identifier in parathensis");
	    		list.add(temp);
	    	}
	    	else if(token.matches("[a-z]+")) {
	    		temp.add(token);
	    		temp.add("identifier");
	    		list.add(temp);
	    	}
	    	else if(token.matches("[0-9]+")) {
	    		temp.add(token);
	    		temp.add("number");
	    		list.add(temp);
	    	}
	    	
	    }
	}
	
	public ArrayList<ArrayList<String>> getData() {
		return list;
	}
}

