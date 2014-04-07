package Graphics.CustomPanel;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;


public abstract class CustomPanel {

	protected JCheckBox chFirstName;
	protected JCheckBox chLastName;
	protected JCheckBox chCity;
	protected JCheckBox chStreet;
	protected JCheckBox chBuilding;
	protected JCheckBox chFlat;
	
	protected JTextField fieldFirstName;
	protected JTextField fieldLastName;
	protected JTextField fieldCity;
	protected JTextField fieldStreet;
	protected JTextField fieldBuilding;
	protected JTextField fieldFlat;

	public abstract JPanel getPanel(JPanel cont);
}
