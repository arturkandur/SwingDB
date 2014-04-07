package Database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import Config.ConfigParser;
import Config.Configuration;
import Graphics.DBFrame;

/**
 * Class provide connection to DB and execute query to DB.
 * @author KArt
 *
 */
public class DBConector {
	private static Configuration config;	//Configuration parameters
	private static Connection conn;			//Database connection
	
	/**
	 * Default constructor {@link DBConector}.
	 * Initialize {@link Configuration} config file 
	 * and create connection to DB if it's not exist.
	 */
	public DBConector(){
		File fileConfig;
		
			fileConfig = new File("resources/config.xml");
			try {
				config = ConfigParser.parseConfig(fileConfig);
			} 
			catch (ParserConfigurationException | SAXException | IOException e) {
				e.printStackTrace();
			}
		
			if(conn == null)
				conn = getDBConnection();
	}
	
	/**
	 * Read configuration object and create connection to DB
	 * @return Connection to DB.
	 */
	public static Connection getDBConnection(){
		try{
	      //Class.forName(config.getJDBCDriver());
	      
	      StringBuilder sb = new StringBuilder(config.getDbURI());
	      sb.append("?ssl=true&");
	      sb.append("sslfactory=org.postgresql.ssl.NonValidatingFactory");
	      String url = sb.toString();
	      String user = config.getDbUser();
	      String password = config.getDbPassword();
	      conn = DriverManager.getConnection(url, user, password);
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	      System.exit(1);
	    }
	    return conn;
	}
	
	/**
	 * Execute insert query to DB to table persons
	 * @param person
	 */
	public void insert(Person person){
		PreparedStatement st;
		
		try {
			
			
			st = conn.prepareStatement("INSERT INTO persons (firstName, lastName, city, street, building, flat) "
			  		+ "VALUES (?, ?, ?, ?, ?, ?)");
			
			st.setString(1, person.getFirstName());
			st.setString(2, person.getLastName());
			st.setString(3, person.getCity());
			st.setString(4, person.getStreet());
			st.setInt(5, person.getBuilding());
			st.setInt(6, person.getFlat());
			st.executeUpdate();
			
			String statementText = st.toString();
			String query = statementText.substring( statementText.indexOf( ": " ) + 1 );
			DBFrame.informAreaDB.setText(query);
			
			st.close();
		} catch (SQLException se) {
		      System.err.println("Threw a SQLException inserting the values in table.");
		      System.err.println(se.getMessage());
		}
			
	}
	
	/**
	 * Execute delete query to DB to table persons.
	 * Delete row from table if exist firstName = person.firstName and lastName = person.lastName
	 * @param person
	 */
	public void delete(Person person){
		PreparedStatement st;
		
		try {
			
			st = conn.prepareStatement("DELETE FROM persons WHERE (firstName = ? AND lastName = ?)");
			
			st.setString(1, person.getFirstName());
			st.setString(2, person.getLastName());
			
			st.executeUpdate();
			
			String statementText = st.toString();
			String query = statementText.substring( statementText.indexOf( ": " ) + 1 );
			DBFrame.informAreaDB.setText(query);
			
			st.close();
		} catch (SQLException se) {
		      System.err.println("Threw a SQLException deleting the values in table.");
		      System.err.println(se.getMessage());
		}
			
	}
	
	/**
	 * Execute query to DB sort (ASC or DESC) and filter rows in table persons
	 * @param Person person - must consist of fields which set filter param. 
	 * @param HashMap<Integer, PairFilter> personMap - contains order of sorting fields. 
	 * @return List of filter persons 
	 */
	public ArrayList<Person> filter(Person person, HashMap<Integer, PairFilter> personMap){
		ArrayList<Person> listOfPersons = new ArrayList<Person>();
	    try{
	    	
	    	String filter = buildFilter(person);
	    	String sort = buildSort(personMap);
	    	Statement st = conn.createStatement();
	    	ResultSet rs = st.executeQuery("SELECT * FROM persons " + filter + sort);
	    	
	    	String statementText = st.toString();
			String query = statementText.substring( statementText.indexOf( ": " ) + 1 );
			DBFrame.informAreaDB.setText(query);
			
	      while ( rs.next()){
	    	Person newPerson = new Person();
	        newPerson.setFirstName(rs.getString ("firstName"));
	        newPerson.setLastName(rs.getString ("lastName"));
	        newPerson.setCity(rs.getString ("city"));
	        newPerson.setStreet(rs.getString ("street"));
	        newPerson.setBuilding(rs.getInt("building"));
	        newPerson.setFlat(rs.getInt("flat"));
	        listOfPersons.add(newPerson);
	      }
	      rs.close();
	      st.close();
	    }
	    catch (SQLException se) {
	      System.err.println("Threw a SQLException creating the list of persons.");
	      System.err.println(se.getMessage());
	    }
		
		return listOfPersons;	
	}
	
	/**
	 * Build phrase for DB query "ORDER BY"
	 * @param HashMap<Integer, PairFilter> personMap - contains order of sorting fields. 
	 * @return Complete String phrase "ORDER BY"
	 */
	private String buildSort(HashMap<Integer, PairFilter> personMap) {
		StringBuilder sb = new StringBuilder();
		
		if(!personMap.isEmpty())
			sb.append("ORDER BY ");
		
		for (Map.Entry<Integer, PairFilter> entry : personMap.entrySet()) {
		    
		    sb.append(entry.getValue().getName() + " " + entry.getValue().getPhrase() + ", ");
		}
		
		if(sb.length()>2)
		sb.deleteCharAt(sb.length()-2);
		
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	/**
	 * Build filter phrase for DB query
	 * @param Person person - must consist of fields which set filter param.
	 * @return Complete String filter phrase 
	 */
	private String buildFilter(Person person) {
		StringBuilder sb = new StringBuilder();
		
		if(!person.isEmpty()){
			sb.append("WHERE ( ");
		}
			
		if(person.getFirstName() != null){
			sb.append("firstName ILIKE '%"  + person.getFirstName() + "%'");
		}
		if(person.getLastName() != null){
			if(sb.length()>0)
				sb.append(" AND ");
			sb.append("lastName ILIKE '%" + person.getLastName() + "%'");
		}
		if(person.getCity() != null){
			if(sb.length()>0)
				sb.append(" AND ");
			sb.append("city ILIKE '%" + person.getCity() + "%'");
		}
		if(person.getStreet() != null){
			if(sb.length()>0)
				sb.append(" AND ");
			sb.append("street ILIKE '%" + person.getStreet() + "%'");
		}
		if(person.getBuilding() != null){
			if(sb.length()>0)
				sb.append(" AND ");
			sb.append("building ILIKE '%" + person.getBuilding() + "%'");
		}
		if(person.getFlat() != null){
			if(sb.length()>0)
				sb.append(" AND ");
			sb.append("flat ILIKE '%" + person.getFlat() + "%'");
		}
		
		if(!person.isEmpty()){
			sb.append(" )");
		}
		
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	/**
	 * Use connection to DB and get all records in table Persons
	 * @return List of {@link Person}
	 */
	public List<Person> listOfPersons(){
		
		List<Person> listOfPersons = new ArrayList<Person>();
	    try{
	      PreparedStatement st = conn.prepareStatement("SELECT * FROM persons");
	      ResultSet rs = st.executeQuery();
	      
	      String statementText = st.toString();
	      String query = statementText.substring( statementText.indexOf( ": " ) + 1 );
	      DBFrame.informAreaDB.setText(query);
			
	      while ( rs.next()){
	    	Person person = new Person();
	        
	        person.setFirstName(rs.getString ("firstName"));
	        person.setLastName(rs.getString ("lastName"));
	        person.setCity(rs.getString ("city"));
	        person.setStreet(rs.getString ("street"));
	        person.setBuilding(rs.getInt("building"));
	        person.setFlat(rs.getInt("flat"));
	        listOfPersons.add(person);
	      }
	      rs.close();
	      st.close();
	    }
	    catch (SQLException se) {
	      System.err.println("Threw a SQLException creating the list of persons.");
	      System.err.println(se.getMessage());
	    }
	    
	    return listOfPersons;
	}

	

}
