package Database;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * Table Model for Person
 * @author KArt
 *
 */
public class PersonTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 3191579999624345729L;
	private List<Person> persons;
	private int size;

    public PersonTableModel(List<Person> persons) {
        this.persons = new ArrayList<Person>(persons);
        this.size = persons.size();
    }
    
    public PersonTableModel(List<Person> persons, int size) {
        this.persons = new ArrayList<Person>(persons);
        this.size = size;
    }

    @Override
    public int getRowCount() {
        return size;
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int column) {
        String name = "??";
        switch (column) {
            case 0:
                name = "firstName";
                break;
            case 1:
                name = "lastName";
                break;
            case 2:
                name = "city";
                break;
            case 3:
                name = "street";
                break;
            case 4:
                name = "building";
                break;
            case 5:
                name = "flat";
                break;
        }
        return name;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class<?> type = String.class;
        switch (columnIndex) {
            case 0:
            case 1:
            case 2:
            case 3:
                type = String.class;
                break;
            case 4:
            case 5:
            	type = Integer.class;
                break;
        }
        return type;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Person person = persons.get(rowIndex);
        Object value = null;
        switch (columnIndex) {
            case 0:
                value = person.getFirstName();
                break;
            case 1:
                value = person.getLastName();
                break;
            case 2:
                value = person.getCity();
                break;
            case 3:
                value = person.getStreet();
                break;
            case 4:
                value = person.getBuilding();
                break;
            case 5:
                value = person.getFlat();
                break;
        }
        return value;
    }     
}        
