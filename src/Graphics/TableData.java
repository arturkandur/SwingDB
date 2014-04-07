package Graphics;
import javax.swing.JTable;

import Database.PersonTableModel;

/**
 * Represents data from DB table to graphic table view.
 * @author KArt
 *
 */
public class TableData extends JTable{
	public static final TableData Instance = new TableData();
	
	private static final long serialVersionUID = -1901191107163112819L;

	private TableData(){
		super();
	}
	
	/**
	 * Update data in table 
	 * @param PersonTableModel persTabMod - new table data
	 */
	public static void update(PersonTableModel persTabMod){
		Instance.setModel(persTabMod);
	}
	

}
