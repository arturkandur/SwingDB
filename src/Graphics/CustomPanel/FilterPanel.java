package Graphics.CustomPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Database.DBConector;
import Database.PairFilter;
import Database.Person;
import Database.PersonTableModel;
import Graphics.DBFrame;
import Graphics.TableData;

/**
 * Build Filter Panel where can choose fields and values for filter phrase.
 * Also it build form for sorting function: comboBoxes choosing order and type of sorting.   
 * @author KArt
 *
 */
public class FilterPanel extends CustomPanel{

	private JComboBox<String> cb1FirstName;
	private JComboBox<String> cb1LastName; 
	private JComboBox<String> cb1City;     
	private JComboBox<String> cb1Street;   
	private JComboBox<String> cb1Building; 
	private JComboBox<String> cb1Flat;     
	
	private JComboBox<Integer> cb2FirstName;
	private JComboBox<Integer> cb2LastName; 
	private JComboBox<Integer> cb2City;     
	private JComboBox<Integer> cb2Street;   
	private JComboBox<Integer> cb2Building; 
	private JComboBox<Integer> cb2Flat;     
	
	private DBConector dbc;
	private JPanel filterPanel;
	
	/**
	 * Fill panel by filter elements and return complete panel
	 * @param cont1 - panel to fill by filter elements 
	 * @return JPanel with functional filter panel
	 * 
	 */
	@Override
	public JPanel getPanel(JPanel cont1){
		this.filterPanel = cont1;
		
		filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.PAGE_AXIS));
		
		JPanel actionBox = new JPanel();
		filterPanel.add(actionBox);
		
		DBFrame.addButton(actionBox, "Filter", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HashMap<Integer, PairFilter> personMap = new HashMap<Integer, PairFilter>();
				
				if (!((String) cb1FirstName.getSelectedItem()).equalsIgnoreCase("")){
					Integer index = (Integer) cb2FirstName.getSelectedItem();
					PairFilter element = new PairFilter("firstName", (String) cb1FirstName.getSelectedItem());
					personMap.put(index, element);
				}
				if (!((String) cb1LastName.getSelectedItem()).equalsIgnoreCase("")){
					int index = (Integer) cb2LastName.getSelectedItem();
					PairFilter element = new PairFilter("lastName", (String) cb1LastName.getSelectedItem());
					personMap.put(index, element);
						
				}
				if (!((String) cb1City.getSelectedItem()).equalsIgnoreCase("")){
					int index = (Integer) cb2City.getSelectedItem();
					PairFilter element = new PairFilter("city", (String) cb1City.getSelectedItem());
					personMap.put(index, element);
					
				}
				if (!((String) cb1Street.getSelectedItem()).equalsIgnoreCase("")){
					int index = (Integer) cb2Street.getSelectedItem();
					PairFilter element = new PairFilter("street", (String) cb1Street.getSelectedItem());
					personMap.put(index, element);
					
				}
				if (!((String) cb1Building.getSelectedItem()).equalsIgnoreCase("")){
					int index = (Integer) cb2Building.getSelectedItem();
					PairFilter element = new PairFilter("building", (String) cb1Building.getSelectedItem());
					personMap.put(index, element);
					
				}
				if (!((String) cb1Flat.getSelectedItem()).equalsIgnoreCase("")){
					int index = (Integer) cb2Flat.getSelectedItem();
					PairFilter element = new PairFilter("flat", (String) cb1Flat.getSelectedItem());
					personMap.put(index, element);
					
				}
				
				Person person = new Person(); 
				if (chFirstName.isSelected())
					person.setFirstName(fieldFirstName.getText());
				if (chLastName.isSelected())
					person.setLastName(fieldLastName.getText());
				if (chCity.isSelected())
					person.setCity(fieldCity.getText());
				if (chStreet.isSelected())
					person.setStreet(fieldStreet.getText());
				if (chBuilding.isSelected())
					person.setBuilding(Integer.parseInt(fieldBuilding.getText()));
				if (chFlat.isSelected())
					person.setFlat(Integer.parseInt(fieldFlat.getText()));
				
				dbc = new DBConector();
				ArrayList<Person>  listOfPersons = dbc.filter(person, personMap);
				
				
				PersonTableModel persTabMod = new PersonTableModel(listOfPersons);
				
				TableData.update(persTabMod);
				
			}
		});
		
		
		JPanel fieldBox = new JPanel();
		filterPanel.add(fieldBox);
		
		fieldBox = setFields(fieldBox);
		
		
		return filterPanel;
	}
	
	/**
	 * Fill panel required fields (CheckBox, TextField, 2 ComboBox)
	 * @param panel
	 * @return Complete fill panel
	 */
	private JPanel setFields(JPanel panel){
		String[] sortTypes = { "", "ASC", "DESC" };
		Integer[] coloumn = {1, 2, 3, 4, 5, 6};
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		chFirstName = new JCheckBox("firstName");
		panel.add(chFirstName, c);
		c.gridx = 1;
		c.gridy = 0;
		fieldFirstName = new JTextField("", 20);
		panel.add(fieldFirstName, c);
		c.gridx = 2;
		c.gridy = 0;
		cb1FirstName = new JComboBox<String>(sortTypes);
		panel.add(cb1FirstName, c);
		c.gridx = 3;
		c.gridy = 0;
		cb2FirstName = new JComboBox<Integer>(coloumn);
		panel.add(cb2FirstName, c);
		
		
		c.gridx = 0;
		c.gridy = 1;
		chLastName = new JCheckBox("lastName");
		panel.add(chLastName, c);
		c.gridx = 1;
		c.gridy = 1;
		fieldLastName = new JTextField("", 20);
		panel.add(fieldLastName, c);
		c.gridx = 2;
		c.gridy = 1;
		cb1LastName = new JComboBox<String>(sortTypes);
		panel.add(cb1LastName, c);
		c.gridx = 3;
		c.gridy = 1;
		cb2LastName = new JComboBox<Integer>(coloumn);
		panel.add(cb2LastName, c);
		
		c.gridx = 0;
		c.gridy = 2;
		chCity = new JCheckBox("City");
		panel.add(chCity, c);
		c.gridx = 1;
		c.gridy = 2;
		fieldCity = new JTextField("", 20);
		panel.add(fieldCity, c);
		c.gridx = 2;
		c.gridy = 2;
		cb1City = new JComboBox<String>(sortTypes);
		panel.add(cb1City, c);
		c.gridx = 3;
		c.gridy = 2;
		cb2City = new JComboBox<Integer>(coloumn);
		panel.add(cb2City, c);
		
		c.gridx = 0;
		c.gridy = 3;
		chStreet = new JCheckBox("Street");
		panel.add(chStreet, c);
		c.gridx = 1;
		c.gridy = 3;
		fieldStreet = new JTextField("", 20);
		panel.add(fieldStreet, c);
		c.gridx = 2;
		c.gridy = 3;
		cb1Street = new JComboBox<String>(sortTypes);
		panel.add(cb1Street, c);
		c.gridx = 3;
		c.gridy = 3;
		cb2Street = new JComboBox<Integer>(coloumn);
		panel.add(cb2Street, c);
		
		c.gridx = 0;
		c.gridy = 4;
		chBuilding = new JCheckBox("Building");
		panel.add(chBuilding, c);
		c.gridx = 1;
		c.gridy = 4;
		fieldBuilding = new JTextField("", 20);
		panel.add(fieldBuilding, c);
		c.gridx = 2;
		c.gridy = 4;
		cb1Building = new JComboBox<String>(sortTypes);
		panel.add(cb1Building, c);
		c.gridx = 3;
		c.gridy = 4;
		cb2Building = new JComboBox<Integer>(coloumn);
		panel.add(cb2Building, c);
		
		c.gridx = 0;
		c.gridy = 5;
		chFlat = new JCheckBox("Flat");
		panel.add(chFlat, c);
		c.gridx = 1;
		c.gridy = 5;
		fieldFlat = new JTextField("", 20);
		panel.add(fieldFlat, c);
		c.gridx = 2;
		c.gridy = 5;
		cb1Flat = new JComboBox<String>(sortTypes);
		panel.add(cb1Flat, c);
		c.gridx = 3;
		c.gridy = 5;
		cb2Flat = new JComboBox<Integer>(coloumn);
		panel.add(cb2Flat, c);
		
		return panel;
	}
	
	
}
