package compiler;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class drawerWindow extends JFrame {
	private DrawingArea drawingArea;
	public drawerWindow(TreeNode node){
		super("Drawer Window");
		drawingArea = new DrawingArea(node);
		drawingArea.addComponentListener(new ComponentListener() {
			
			public void componentShown(ComponentEvent e) {
				drawingArea.repaint();
			}
			
			public void componentResized(ComponentEvent e) {
				drawingArea.repaint();
			}
			
			public void componentMoved(ComponentEvent e) {
				drawingArea.repaint();
			}
			
			public void componentHidden(ComponentEvent e) {
				drawingArea.repaint();
			}
		});
		setLayout(new BorderLayout());
		add(new JScrollPane(drawingArea), BorderLayout.CENTER);
		setSize(new Dimension(1000, 700));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
