package compiler;

import java.util.ArrayList;



public class Parser {
	private ArrayList<ArrayList<String>> tokens = new ArrayList<ArrayList<String>>();
	private int pointer;
	private ArrayList<String> nextToken;
	TreeNode firstNode = null;
	public Parser() {}
	public void setTokens(ArrayList<ArrayList<String>> tokens) {
		this.tokens = tokens;
		pointer = 0;
		this.nextToken = this.tokens.get(0);
		GetTree();
	}
	private void GetTree() {
		firstNode = stmt_seq();
		setTypes(firstNode);
	}
	
	private TreeNode stmt_seq() {
		TreeNode temp = statement();
		TreeNode temp2 = temp;
		while(nextToken.get(0).equals(";")) {
			match(";");
			TreeNode next = statement();
			temp2.setNext(next);
			temp2 = next;
		}
		return temp;
	}
	private TreeNode statement() {
		TreeNode node = null;
		if(nextToken != null) {
			if(nextToken.get(0).equals("if"))
				node = if_stmt();
			else if (nextToken.get(0).equals("read"))
				node = read_stmt();
			else if (nextToken.get(0).equals("write"))
				node = write_stmt();
			else if (nextToken.get(0).equals("repeat"))
				node = repeat_stmt();
			else if (nextToken.get(1).equals("identifier"))
				node = assign_stmt();
		}
		return node;
	}
	private TreeNode read_stmt() {
		match("read"); // match read token
		ArrayList<String> iden = match("identifier"); // match identifier
		return new TreeNode("read" + "\n" + iden.get(0));
	}

	private TreeNode write_stmt() {
		match("write");
		TreeNode node = new TreeNode("write");
		TreeNode child = expression();
		node.addChild(child);
		return node;
	}
	private TreeNode assign_stmt() {
		ArrayList<String> id = match("identifier");
		match(":=");
		TreeNode node = new TreeNode("assign" + "\n" + id.get(0));
		TreeNode child = expression();
		node.addChild(child);
		return node;
	}
	
	private TreeNode repeat_stmt() {
		match("repeat");
		TreeNode node = new TreeNode("repeat");
		TreeNode body = stmt_seq();
		match("until");
		TreeNode test = expression();
		node.addChild(body);
		node.addChild(test);
		return node;
	}
	
	private TreeNode if_stmt() {
		match("if");
		TreeNode node = new TreeNode("if");
		TreeNode test = expression();
		match("then");
		TreeNode thenPart = stmt_seq();
		TreeNode elsePart = null;
		if(nextToken != null && nextToken.get(0).equals("else")) {
			match("else");
			elsePart = stmt_seq();
		}
		node.addChild(test);
		node.addChild(thenPart);
		if(elsePart != null)
			node.addChild(elsePart);
		return node;
	}
	
	private TreeNode expression() {
		TreeNode first = simple_exp();
		TreeNode op = null;
		if(nextToken.get(0).equals("<") || nextToken.get(0).equals("=")) {
			String temp = nextToken.get(0);
			if(temp.equals("<")) {
				match("<");
				op = new TreeNode("<");
			}
			else {
				match("=");
				op = new TreeNode("=");
			}
			TreeNode second = simple_exp();
			op.addChild(first);
			op.addChild(second);
		}
		if (op == null)
			return first;
		return op;
	}
	
	private TreeNode simple_exp() {
		TreeNode first = term();
		TreeNode op = null;
		if(nextToken.get(0).equals("+") || nextToken.get(0).equals("-")) {
			String temp = nextToken.get(0);
			if (temp.equals("+")) {
				match("+");
				op = new TreeNode("+");
			}
			else {
				match("-");
				op = new TreeNode("-");
			}
			TreeNode second = term();
			op.addChild(first);
			op.addChild(second);
		}
		if (op == null)
			return first;
		return op;
	}
	
	private TreeNode term() {
		TreeNode first = factor();
		TreeNode op = null;
		if(nextToken.get(0).equals("*") || nextToken.get(0).equals("/")) {
			String temp = nextToken.get(0);
			if (temp.equals("*")) {
				match("*");
				op = new TreeNode("*");
			}
			else {
				match("/");
				op = new TreeNode("/");
			}
			TreeNode second = factor();
			op.addChild(first);
			op.addChild(second);
		}
		if (op == null)
			return first;
		return op;
	}
	
	private TreeNode factor() {
		TreeNode temp;
		if(nextToken.get(0).equals("(")) {
			match("(");
			temp =  expression();
			match(")");
		}
		else if (nextToken.get(1).equals("number")) {
			ArrayList<String> num = match("number");
			temp = new TreeNode("const" + "\n" + num.get(0));
		}
		
		else {
			ArrayList<String>id = match("identifier");
			temp = new TreeNode("id" + "\n" + id.get(0));
		}
		return temp;
	}
	

	private ArrayList<String> match(String token) throws IndexOutOfBoundsException {
		ArrayList<String> temp = tokens.get(pointer++);
		if (pointer == tokens.size()) {
			nextToken = temp;
		}
		else 
			this.nextToken = tokens.get(pointer);
		if(token.equals("identifier")) {
			if (temp.get(1).equals(token))
				return temp;
		}
		else if(token.equals("number")) {
			if (temp.get(1).equals(token))
				return temp;
		}
		else if (temp.get(0).equals(token))
			return temp;
		return new ArrayList<>();
		
	}
	
	public TreeNode getTree(){
		return this.firstNode;
	}
	
	private void setTypes(TreeNode node) {
		if(node != null) {
			String text = node.getText();
			if(text.equals("if") || text.equals("repeat") || text.startsWith("read") || 
					text.startsWith("write") || text.startsWith("assign"))
				node.setType("statement");
			else
				node.setType("exp");
			for(TreeNode child: node.getChildren()) {
				setTypes(child);
			}
			setTypes(node.getNext());
		}
	}
}
