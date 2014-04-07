package Graphics.CustomPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Database.DBConector;
import Database.Person;
import Graphics.DBFrame;

/**
 * Build Insert Panel where can choose fields and values to insert or delete record.
 * @author KArt
 *
 */
public class InsertPanel extends CustomPanel{
	
	private DBConector dbc;
	
	/**
	 * Fill panel by elements to insert or delete record and return complete panel
	 * @param cont1 - panel to fill by insert elements 
	 * @return JPanel with functional insert panel
	 */
	@Override
	public JPanel getPanel(JPanel cont){
		
		JPanel insPanel = cont;
		insPanel.setLayout(new BoxLayout(insPanel, BoxLayout.PAGE_AXIS));
		
		JPanel actionBox = new JPanel();
		insPanel.add(actionBox);
		
		DBFrame.addButton(actionBox, "Insert", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
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
				dbc.insert(person);
				
			}
		});
		
		DBFrame.addButton(actionBox, "Delete", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
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
				dbc.delete(person);
				
			}
		});
		
		
		JPanel fieldBox = new JPanel();
		insPanel.add(fieldBox);
		
		fieldBox = setFields(fieldBox);
		
		
		return insPanel;
	}
	
	/**
	 * Fill panel required fields (CheckBox, TextField)
	 * @param panel
	 * @return Complete fill panel
	 */
	private JPanel setFields(JPanel panel){
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		chFirstName = new JCheckBox("firstName");
		panel.add(chFirstName, c);
		c.gridx = 1;
		c.gridy = 0;
		fieldFirstName = new JTextField("", 30);
		panel.add(fieldFirstName, c);
		
		c.gridx = 0;
		c.gridy = 1;
		chLastName = new JCheckBox("lastName");
		panel.add(chLastName, c);
		c.gridx = 1;
		c.gridy = 1;
		fieldLastName = new JTextField("", 30);
		panel.add(fieldLastName, c);
		
		c.gridx = 0;
		c.gridy = 2;
		chCity = new JCheckBox("City");
		panel.add(chCity, c);
		c.gridx = 1;
		c.gridy = 2;
		fieldCity = new JTextField("", 30);
		panel.add(fieldCity, c);
		
		c.gridx = 0;
		c.gridy = 3;
		chStreet = new JCheckBox("Street");
		panel.add(chStreet, c);
		c.gridx = 1;
		c.gridy = 3;
		fieldStreet = new JTextField("", 30);
		panel.add(fieldStreet, c);
		
		c.gridx = 0;
		c.gridy = 4;
		chBuilding = new JCheckBox("Building");
		panel.add(chBuilding, c);
		c.gridx = 1;
		c.gridy = 4;
		fieldBuilding = new JTextField("", 30);
		panel.add(fieldBuilding, c);
		
		c.gridx = 0;
		c.gridy = 5;
		chFlat = new JCheckBox("Flat");
		panel.add(chFlat, c);
		c.gridx = 1;
		c.gridy = 5;
		fieldFlat = new JTextField("", 30);
		panel.add(fieldFlat, c);
		
	return panel;
	}
	

}
