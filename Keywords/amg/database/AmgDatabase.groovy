package amg.database

import java.sql.Date
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import org.junit.Assert

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil

import auto.utilities.database.DatabaseExecutor
import auto.utilities.fakedata.DateTime
import groovy.sql.GroovyRowResult

public class AmgDatabase {
	public static final Map AMG_DB_PARAMS = [
		url: 'jdbc:oracle:thin:@10.1.14.230:1521/iblive_230',
		user: 'automkt',
		password: 'Tpb#123',
		driver: 'oracle.jdbc.pool.OracleDataSource']

	@Keyword
	public static def verifyInsiderOutCardWhiteList(InsiderOutCardWhiteListModel modelObj) {
		String expectedIid = modelObj.getIid()
		GString query = "select * from INSIDER_OUT_CARD_WHITELIST Where IID = ${expectedIid}"
		GroovyRowResult result = DatabaseExecutor.selectData(AmgDatabase.AMG_DB_PARAMS, query)

		String actualIid = result.get('iid')
		String actualProduct = result.get('whitelist_product')
		String actualStatus = result.get('approval_status')

		//		KeywordUtil.logInfo('actualIid = ' + actualIid)
		//		KeywordUtil.logInfo('actualProduct = ' + actualProduct)
		//		KeywordUtil.logInfo('actualStatus = ' + actualStatus)

		Assert.assertEquals(expectedIid, actualIid)
		Assert.assertEquals(modelObj.getWhitelist_product(), actualProduct)
		Assert.assertEquals(modelObj.getApproval_status(), actualStatus)

		if(modelObj.getTime()==null) {
			Assert.assertEquals(modelObj.getTime(), result.get('time'))
		}else {

			//			DateTime dt = new DateTime()
			//
			//			ZonedDateTime zoneddDT = dt.parseString(modelObj.getTime())
			//
			//			String expectedTime = zoneddDT.format(DateTimeFormatter.ofPattern("dd/mm/yyyy hh:mm:ss"))
			//			KeywordUtil.logInfo('expectedTime = '+expectedTime)
			//
			//			String actualTime = result.get('time')
			//
			//			Assert.assertEquals(expectedTime, actualTime)
		}
	}
}
