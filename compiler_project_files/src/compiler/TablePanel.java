package compiler;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TablePanel extends JPanel {
	private JTable table;
	private TableModel tableModel;
	private JButton syntaxTreeButton = new JButton("Show Syntax Tree");
	private CanvasPanel canvasPanel;
		
	public TablePanel() {
		tableModel = new TableModel();
		table = new JTable(tableModel);
		syntaxTreeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvasPanel.showCanvasPanel();
			}
		});
		setPreferredSize(new Dimension(380,700));
		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);
		add(syntaxTreeButton, BorderLayout.SOUTH);
	}
	public void setData(ArrayList<ArrayList<String>> Data) {
		tableModel.setData(Data);
	}
	public void refresh() {
		tableModel.fireTableDataChanged();
	}
	public void showCanvasPanel(CanvasPanel p) {
		this.canvasPanel = p;
	}
}
