package auto.utilities.database

import com.kms.katalon.core.util.KeywordUtil

import groovy.sql.Sql

/**
 * Create database connection
 * @author ChungND1
 *
 */
public class DatabaseConnection {

	/**
	 * Create database connection
	 * @param url
	 * @param user
	 * @param password
	 * @param driver
	 * @return
	 */
	public Sql sqlInstance(String url,String user,String password, String driver) {
		DatabaseDriver driverEnum = DatabaseDriver.ORACLE

//		switch(driver) {
//			case DatabaseDriver.ORACLE.getDriver():
//				break
//
//			case DatabaseDriver.MYSQL.getDriver():
//				driverEnum = DatabaseDriver.MYSQL
//				break
//
//			case DatabaseDriver.SQLSERVER.getDriver():
//				driverEnum = DatabaseDriver.SQLSERVER
//				break
//
//			default:
//				throw new IllegalArgumentException("This database type is unsupported!")
//		}
		KeywordUtil.logger.logDebug("Created connection to: ${url}")
		return Sql.newInstance(url,user,password,driver)
	}

	public Sql sqlInstance(String url,String user,String password, DatabaseDriver driverEnum) {
		return sqlInstance(url,user,password,driverEnum.getDriver())
	}

	public Sql sqlInstance(Map dbConnectionParams) {
		String url = dbConnectionParams.get('url')
		String user = dbConnectionParams.get('user')
		String password = dbConnectionParams.get('password')
		String driver = dbConnectionParams.get('driver')
		return sqlInstance(url,user,password,driver)
	}
}
