package Config;

/**
 * Class which define connecting parameters to DB.
 * @author KArt
 *
 */
public class Configuration {
	private String dbURI;
	private String dbUser;
	private String dbPassword;
	private String jdbcDriver;
	
	public Configuration() {
	}
	
	public String getDbURI() {
		return dbURI;
	}

	public String getDbUser() {
		return dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}
	
	public String getJdbcDriver() {
		return jdbcDriver;
	}

	public void setJdbcDriver(String jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}

	public void setDbURI(String dbURI) {
		this.dbURI = dbURI;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
		
	public void printDBParams() {
		System.out.println("dbURI: " + dbURI);
		System.out.println("dbUser: " + dbUser);
		System.out.println("dbPassword: " + dbPassword);
		System.out.println("jdbcDriver: " + jdbcDriver);
	}

}
