package compiler;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;



public class DrawingArea extends Canvas {
	private TreeNode baseNode;
	private ArrayList<Integer> acc_level_width = new ArrayList<>();
	public DrawingArea(TreeNode node) {
		this.baseNode = node;
		setSize(1000, 1000);
		renderComponts(node);
	}
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		drawComponent(g, this.baseNode, false);
		g.setColor(Color.BLUE);
		g.setFont(new Font(Font.SERIF, Font.BOLD, 15));
		drawComponent(g, this.baseNode, true);
		g.setColor(Color.BLACK);
		drawLines(g, this.baseNode);
		
	}

	
	private void renderComponts(TreeNode node) {
		if(node != null){
			int level = node.getLevel();
			if(level >= this.acc_level_width.size())
				acc_level_width.add(0);
			int newx = 0;
			int newx2 = 0;
			int def = 0;
			if(node.getParent() != null) {
				int num = node.getChildNumber();
				if(num == 0)
					newx = node.getParent().getBaseX() - 65;
				else if (num == 1)
					newx = node.getParent().getBaseX();
				else if (num == 2)
					newx = node.getParent().getBaseX() + 15;
			}
			if(node.getPrevious() != null) {
				TreeNode prev = node.getPrevious();
				newx2 = prev.getBaseX() + 100;
			}
			def = this.acc_level_width.get(level)+100;
			this.acc_level_width.remove(level);
			if (newx2 > newx && newx2 > def) {
				node.setBaseX(newx2);
				this.acc_level_width.add(level, newx2);
			}
			else if (newx > newx2 && newx > def) {
				node.setBaseX(newx);
				this.acc_level_width.add(level, newx);
			}
			else {
				node.setBaseX(def);
				this.acc_level_width.add(level, def);
			}
			node.setBaseY(level*100+100);
			for(TreeNode i: node.getChildren()) {
				renderComponts(i);
			}
			renderComponts(node.getNext());
		}
	}
	private void drawComponent(Graphics g , TreeNode node, boolean Text) {
		if(node != null) {
			if(Text) {
				String[] str = node.getText().split("\n");
				g.drawString(str[0], node.getBaseX()+10, node.getBaseY()+25);
				if(str.length > 1)
					g.drawString(str[1], node.getBaseX()+10, node.getBaseY()+40);
			}
			else {
				if(node.getType().equals("exp"))
					g.drawOval(node.getBaseX(), node.getBaseY(), 50, 50);
				else
					g.drawRect(node.getBaseX(), node.getBaseY(), 50, 50);
			}
			for(TreeNode i: node.getChildren()) {
				drawComponent(g, i, Text);
			}
			drawComponent(g, node.getNext(), Text);
		}
	}
	private void drawLines(Graphics g, TreeNode node) {
		if(node != null) {
			if(node.getParent() != null) {
				int baseX = node.getParent().getBaseX();
				int baseY = node.getParent().getBaseY();
				g.drawLine(node.getBaseX()+25, node.getBaseY(), baseX+25, baseY+50);
			}
			if(node.getNext() != null) {
				int baseX = node.getNext().getBaseX();
				int baseY = node.getNext().getBaseY();
				g.drawLine(node.getBaseX()+50, node.getBaseY()+25, baseX, baseY+25);
			}
			for(TreeNode i: node.getChildren()) {
				drawLines(g, i);
			}
			drawLines(g, node.getNext());
		}
	}
	
}
