package auto.utilities.database

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil

import groovy.sql.GroovyRowResult


/**
 *
 * @author ChungND1
 * JDBC Groovy
 */
public class DatabaseExecutor {

	/**
	 * Inserting Data
	 * @param dbConnectionParams
	 * @param query
	 * @return
	 */
	@Keyword
	public static def insertData(Map dbConnectionParams,GString query) {

		DatabaseConnection databaseConnection = new DatabaseConnection()
		def sql = databaseConnection.sqlInstance(dbConnectionParams)
		try {
			KeywordUtil.logInfo("Insert query: ${query}")
			sql.executeInsert(query)
		}catch(Exception e){
			throw new Exception("Failed to execute insert query: ${query}")
		}finally {
			sql.close()
		}
	}


	/**
	 * Inserting Data
	 * @param dbConnectionParams
	 * @param query
	 * @param dataMap
	 * @return
	 */
	@Keyword
	public static def insertData(Map dbConnectionParams,String query,Map data) {

		DatabaseConnection databaseConnection = new DatabaseConnection()
		def sql = databaseConnection.sqlInstance(dbConnectionParams)
		try {
			KeywordUtil.logInfo("Insert query: ${query}")
			sql.execute(query,data)
		}catch(Exception e){
			throw new Exception("Failed to execute insert query: ${query}")
		}finally {
			sql.close()
		}
	}


	/**
	 * Updating and Deleting Data
	 * @param dbConnectionParams
	 * @param query
	 * @return
	 */
	@Keyword
	public static def updateData(Map dbConnectionParams,GString query) {
		DatabaseConnection databaseConnection = new DatabaseConnection()
		def sql = databaseConnection.sqlInstance(dbConnectionParams)

		try {
			KeywordUtil.logInfo("Update query: ${query}")
			def count = sql.executeUpdate(query)
			KeywordUtil.logInfo("Number of affected rows: ${count}")
		}catch(Exception e){
			throw new Exception("Failed to execute update query: ${query}")
		}finally {
			sql.close()
		}
	}


	/**
	 * Querying the Database
	 * @param dbConnectionParams
	 * @param query
	 * @return
	 */
	@Keyword
	public static List<GroovyRowResult> selectData(Map dbConnectionParams,GString query) {
		DatabaseConnection databaseConnection = new DatabaseConnection()

		def sql = databaseConnection.sqlInstance(dbConnectionParams)

		try {

			KeywordUtil.logInfo("Select query: ${query}")

			List<GroovyRowResult> listRows = sql.rows(query)

			if(listRows.size()>0) {
				KeywordUtil.logInfo("Result rows number: "+listRows.size())
				return listRows
			}else {
				throw new Exception("Can not found valid data from database!")
			}
		}catch(Exception e){
			throw new Exception("Failed to execute select query: ${query}")
		}finally {

			sql.close()
		}
	}

	@Keyword
	public static List<Map<String, Object>> selectDataByString(Map dbConnectionParams, GString query) {
		DatabaseConnection databaseConnection = new DatabaseConnection();
		def sql = databaseConnection.sqlInstance(dbConnectionParams);

		try {
			KeywordUtil.logInfo("Select query: ${query}");

			List<Map<String, Object>> listRows = sql.rows(query);

			if (listRows.size() > 0) {
				KeywordUtil.logInfo("Result rows number: " + listRows.size());
				return listRows;
			} else {
				throw new Exception("Can not found valid data from database!");
			}
		} catch (Exception e) {
			throw new Exception("Failed to execute select query: ${query}");
		} finally {
			sql.close();
		}
	}
}
