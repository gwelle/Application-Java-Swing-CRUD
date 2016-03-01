/**
 * 
 */
package gestion_magasins;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;


/**
 * @author admin
 *
 */
public class MyTableModel extends DefaultTableModel{
	
	
	private Vector data ;
	private Vector<String> tableColumnName ;
	/**
	 * 
	 * @param data
	 * @param tableColumnName
	 */
	public MyTableModel(Vector data, Vector<String> tableColumnName) {
		super(data,tableColumnName);
		//super.columnIdentifiers=tableColumnName;
		//super.dataVector=data;
		this.data = data ;
		this.tableColumnName = tableColumnName ;
	}

	/**
	 * Rendre les cellules de la JTable non éditables ;
	 * @param row
	 * @param colum
	 * @return
	 */
	public boolean isCellEditable(int row, int colum){
		
		return false ;
	}
}
