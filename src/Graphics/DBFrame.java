package Graphics;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Database.DBConector;
import Database.Person;
import Database.PersonTableModel;
import Graphics.CustomPanel.FilterPanel;
import Graphics.CustomPanel.InsertPanel;

/**
 * Main class which build GUI
 * @author KArt
 *
 */
public class DBFrame extends JFrame{

	private static final long serialVersionUID = -4128778042191346810L;
	private static int FRAME_WIDTH = 600;
	private static int FRAME_HEIGHT = 400;
	private JPanel contentPanel;
	public static JTextArea informAreaDB;
	private JTable contentTableDB;
	private DBConector dbc;
	private List<Person> listOfPersons;
	
	/**
	 * Build frame which show information about DB. Set main parameters.
	 */
	public DBFrame() {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("DBConsole");
		setLocationRelativeTo(null);
		initFrame();
		
	}
	
	/**
	 * Initialization all elements JFrame.
	 */
	private void initFrame(){
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		dbc = new DBConector();
				
		//Create NavigPanel
		JPanel navigPanel = new JPanel();
		navigPanel.setLayout(new BoxLayout(navigPanel, BoxLayout.PAGE_AXIS));
		mainPanel.add(navigPanel, BorderLayout.NORTH);
		
		//Create button Panel
		JPanel buttonPanel = new JPanel();
		navigPanel.add(buttonPanel);
		
		//Create List Properties
		JPanel listProperties = new JPanel();
		listProperties.setLayout(new BoxLayout(listProperties, BoxLayout.PAGE_AXIS));
		navigPanel.add(listProperties);
		
		listProperties.add(new JLabel("List properties"));
		JPanel listProp = new JPanel();
		final JTextField showRow = new JTextField("100");
		listProp.add(showRow);
		
		addButton(listProp, "Reload", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PersonTableModel persTabMod = new PersonTableModel(listOfPersons, Integer.parseInt(showRow.getText()));
				contentTableDB.setModel(persTabMod);
				
			}
		});
		
		
		listProperties.add(listProp);
		
		
		JPanel informPanel = new JPanel();
		mainPanel.add(informPanel, BorderLayout.SOUTH);
		
		
		getContentPane().add(mainPanel);
		
		//Add text field which will be display status and query
		informAreaDB = addTextField(informPanel);
		
		//Build content area
		
		contentPanel = new JPanel(new BorderLayout());
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		
		listOfPersons = dbc.listOfPersons();
		
		PersonTableModel persTabMod = new PersonTableModel(listOfPersons);
		
		buildContentPanel(persTabMod);
		
		//Add Button Close to NavigablePanel
		addButton(buttonPanel, "Insert New Record", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPanel.removeAll();
				
				contentPanel = new InsertPanel().getPanel(contentPanel);
				
				contentPanel.revalidate();
				contentPanel.repaint();
			}
		}
		);
		
		//Add Button with functional connect to DB to NavigablePanel
		addButton(buttonPanel, "Delete record", new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPanel.removeAll();
				
				contentPanel = new InsertPanel().getPanel(contentPanel);
				
				contentPanel.revalidate();
				contentPanel.repaint();
				
			}
		}
		);
		
		//Add Button List to NavigablePanel
		addButton(buttonPanel, "List", new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPanel.removeAll();
				
				listOfPersons = dbc.listOfPersons();
				
				PersonTableModel persTabMod = new PersonTableModel(listOfPersons);
				
				buildContentPanel(persTabMod);
				 
				contentPanel.revalidate();
				contentPanel.repaint();
				
			}
		}
		);	
		
	}
	
	/**
	 * Add JButton to the specified container with specific listener
	 * @param c - Container
	 * @param title - name button
	 * @param listener - listener for button
	 */
	public static void addButton (Container c, String title, ActionListener listener){
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}
	
	/**
	 * Add JTextArea to the specified container
	 * @param c - Container
	 * @return JTextArea
	 */
	public JTextArea addTextField(Container c){
		JTextArea area = new JTextArea();
		c.add(area);
		return area;
	}
	
	/**
	 * Build Content Panel which display table data
	 * @param persTabMod - model to represent data
	 */
	public void buildContentPanel(PersonTableModel persTabMod){
		
		//Build Filter Panel
		
		JPanel filterPanel = new JPanel();
		
		filterPanel = new FilterPanel().getPanel(filterPanel);
		contentPanel.add(filterPanel, BorderLayout.PAGE_START);
		
		
		//Table with data from DB
		TableData.update(persTabMod);
		contentTableDB = TableData.Instance;
		
		JScrollPane cont2Scroll = new JScrollPane(contentTableDB);
		
		cont2Scroll.setColumnHeaderView(TableData.Instance.getTableHeader());
		
		cont2Scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		cont2Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		contentPanel.add(cont2Scroll, BorderLayout.PAGE_END);
	}
	
}
