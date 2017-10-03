package compiler;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

	private ArrayList<ArrayList<String>> Data;
	private String[] colNames = {"name", "val"};

	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getRowCount() {
		return Data.size();
	}
	
	

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colNames[column];
	}

	@Override
	public Object getValueAt(int row, int col) {
		ArrayList<String> val = Data.get(row);
		switch(col) {
		case 0:
			return val.get(0);
		case 1:
			return val.get(1);
		}
		return null;
	}
	
	public void setData(ArrayList<ArrayList<String>> Data) {
		this.Data = Data;
	}
	
}
