package compiler;

import java.util.ArrayList;

public class TreeNode {
	private int BaseX;  //for drawing
	private int BaseY;	//for drawing
	private String text;
	private ArrayList<TreeNode> children = new ArrayList<>();
	private int level;  //for drawing
	private TreeNode next = null;
	private TreeNode parent = null;
	private TreeNode previous = null;
	private String type;
	
	public TreeNode(String text) {
		this.text = text;
		this.level = 0;
	}
	public void addChild(TreeNode node){
		children.add(node);
		node.parent = this;
		updateNodeslevel(this, this.level);
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setNext(TreeNode next) {
		this.next = next;
		this.next.previous = this;
		next.level = this.level;
	}
	public TreeNode getNext(){
		return this.next;
	}
	public ArrayList<TreeNode> getChildren() {
		return this.children;
	}
	public static void contents(TreeNode node) {
		if(node != null) {
			for(TreeNode i : node.children) {
				contents(i);
			}
			if(node.parent != null)
				System.out.println(node.text + " " + node.level + " " + node.parent.text);
			else
				System.out.println(node.text + " " + node.level + " ");
			contents(node.next);
		}
	}
	public int getLevel() {
		return this.level;
	}
	private void updateNodeslevel(TreeNode node, int level) {
		if(node != null) {
			node.level = level;
			for(TreeNode child: node.children) {
				updateNodeslevel(child, level+1);
			}
			TreeNode temp = node;
			updateNodeslevel(temp.next, level);
		}
	}
	public int getBaseX() {
		return BaseX;
	}
	public void setBaseX(int baseX) {
		BaseX = baseX;
	}
	public int getBaseY() {
		return BaseY;
	}
	public void setBaseY(int baseY) {
		BaseY = baseY;
	}
	public TreeNode getParent() {
		return this.parent;
	}
	public int getChildNumber() {
		TreeNode parent = this.parent;
		if(parent != null)
			return parent.children.indexOf(this);
		return -1;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public TreeNode getPrevious() {
		return previous;
	}
	
}
