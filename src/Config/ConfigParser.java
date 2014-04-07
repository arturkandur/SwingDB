package Config;

import Config.Configuration;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * Parse configuration XML file with connecting parameters to database.
 * Return instance of {@link Config.Configuration}. 
 * @author KArt
 *
 */
public class ConfigParser {

	public static final String ELEMENT_DATABASE = "database";
	public static final String ELEMENT_SERVER = "server";
	public static final String ELEMENT_TABLE = "table";
	public static final String ELEMENT_FIELD = "field";
		
	public static final String ATTR_URI = "uri";
	public static final String ATTR_USER = "user";
	public static final String ATTR_PASSWORD = "password";
	public static final String ATTR_JDBC_DRIVER = "jdbcdriver";
	
	public static final String ATTR_TYPE = "type";
	public static final String ATTR_CONSTRAINTS = "constraints";
	
	/**
	 * 	
	 * @param Database Configuration XML File
	 * @return Instance of {@link Config.Configuration} with connecting parameters to DB.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Configuration parseConfig(File file) 
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(file);
		
		Configuration config = new Configuration();
		
		Element db = (Element) document.getElementsByTagName(ELEMENT_DATABASE).item(0);
		config.setDbURI(db.getAttribute(ATTR_URI));
		config.setDbUser(db.getAttribute(ATTR_USER));
		config.setDbPassword(db.getAttribute(ATTR_PASSWORD));
		config.setJdbcDriver(db.getAttribute(ATTR_JDBC_DRIVER));
		
		return config;
	}
	
	
}
