package compiler;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class mainFrame extends JFrame {
	private textArea textArea;
	private JButton compileButton;
	private TablePanel tablePanel;
	private ArrayList<ArrayList<String>> Data = new ArrayList<>();
	private scanner scan;
	private Parser parser;
	private drawerWindow window;
	
	public mainFrame() {
		super("Tiny Language Compiler");
		textArea = new textArea();
		compileButton = new JButton("Compile");
		tablePanel = new TablePanel();
		tablePanel.setData(Data);
		scan = new scanner();
		parser = new Parser();
		
		tablePanel.showCanvasPanel(new CanvasPanel() {
			public void showCanvasPanel() {
				window = new drawerWindow(parser.getTree());
			}
		});
		
		setLayout(new BorderLayout());
		setSize(800,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		add(textArea, BorderLayout.WEST);
		add(compileButton, BorderLayout.SOUTH);
		add(tablePanel, BorderLayout.EAST);
		
		compileButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String codeText = textArea.getText();
				scan.setText(codeText);
				tablePanel.setData(scan.getData());
				tablePanel.refresh();
				parser.setTokens(scan.getData());
			}
		});
	}
}
