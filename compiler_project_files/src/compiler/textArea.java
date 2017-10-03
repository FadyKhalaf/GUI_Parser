package compiler;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class textArea extends JTextArea {
	public textArea() {
		Border innerBorder = BorderFactory.createEmptyBorder();
		Border outerBorder = BorderFactory.createTitledBorder("Code Editor");
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		setPreferredSize(new Dimension(400,700));
	}
}
