package auto.utilities.database

/**
 * 
 * @author chungnd1
 * JDBC driver information
 */
public enum DatabaseDriver {
	ORACLE("Oracle","oracle.jdbc.pool.OracleDataSource"),
	MYSQL("MySql","com.mysql.jdbc.Driver"),
	SQLSERVER("Sql Server","com.microsoft.sqlserver.jdbc.SQLServerDriver")

	private String name
	private String driver

	public String getName() {
		return this.name
	}

	public String getDriver() {
		return this.driver
	}

	DatabaseDriver(String name, String driver) {
		this.name = name
		this.driver = driver
	}
}