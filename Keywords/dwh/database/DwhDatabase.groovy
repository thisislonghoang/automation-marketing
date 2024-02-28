package dwh.database

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil

import auto.utilities.database.DatabaseExecutor
import groovy.sql.GroovyRowResult


public class DwhDatabase {

	public static final Map DWH_DB_PARAMS = [
		url: 'jdbc:oracle:thin:@10.1.14.133:1521/TPBDWH2',
		user: 'u_tester',
		password: 'Tpb#2023',
		driver: 'oracle.jdbc.pool.OracleDataSource']

	//	public static final DatabaseExecutor ODH_DATABASE_MANAGER = new DatabaseExecutor()

	@Keyword
	public static String getEngagementStatus(String code) {
		GString query = """
        with data_config_last as 
        (select ENCRYPT_CODE 
        from odh_uat.CSTB_CDH_ENCRYPT_CONFIG 
        where rownum = '${code}'
        order by RUN_DATE desc) 
        select (select ENCRYPT_CODE from data_config_last)||odh_uat.FN_CDH_ENCRYPT_IID(SUBSTR(REPLACE(t.CIF, '  ', ''), 1, 8)) IID, t.GROUP_LAYER_1 ENGAGEMENT_RANK 
        from odh.IIAS_DATAIT_RB_RANKING t
    """

		GroovyRowResult result = DatabaseExecutor.selectData(DWH_DB_PARAMS, query);
		KeywordUtil.logInfo("IID: " + result.IID);
		KeywordUtil.logInfo("ENGAGEMENT_RANK: " + result.ENGAGEMENT_RANK);
		return result.IID + " " + result.ENGAGEMENT_RANK;
	}
}
