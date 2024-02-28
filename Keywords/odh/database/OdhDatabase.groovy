package odh.database

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.util.KeywordUtil

import auto.utilities.database.DatabaseExecutor

public class OdhDatabase {
	public static final Map<String, String> ODH_DB_PARAMS = Map.of(
	"url", "jdbc:oracle:thin:@10.1.14.133:1521/TPBDWH2",
	"user", "u_tester",
	"password", "Tpb#2023",
	"driver", "oracle.jdbc.pool.OracleDataSource");

	public static final Map<String, String> MARKETING_DB_PARAMS = Map.of(
	"url", "jdbc:oracle:thin:@10.1.14.230:1521/iblive_230",
	"user", "automkt",
	"password", "Tpb#123",
	"driver", "oracle.jdbc.pool.OracleDataSource");

	@Keyword
	public static String compareEngagementStatusData(String excelFilePath, String code, String condition) {
		List<Map<String, Object>> odhData = DatabaseExecutor.selectDataByString(ODH_DB_PARAMS, getOdhQuery(code));
		List<Map<String, Object>> marketingData = DatabaseExecutor.selectDataByString(MARKETING_DB_PARAMS, getMarketingQuery(condition));

		Map<String, String> odhMap = createDataMap(odhData, "IID", "ENGAGEMENT_RANK");
		Map<String, String> marketingMap = createDataMap(marketingData, "IID", "ENGAGEMENT_RANK");

		compareDataOdhWithAutoMkt(odhMap, marketingMap);
		compareWithExcelData(excelFilePath, marketingMap);

		return "Comparison completed";
	}

	private static GString getOdhQuery(String code) {
		return """
            with data_config_last as
            (select ENCRYPT_CODE
            from odh_uat.CSTB_CDH_ENCRYPT_CONFIG
            where rownum = '${code}'
            order by RUN_DATE desc)
            select (select ENCRYPT_CODE from data_config_last)||odh_uat.FN_CDH_ENCRYPT_IID(SUBSTR(REPLACE(t.CIF, '  ', ''), 1, 8)) IID, t.GROUP_LAYER_1 ENGAGEMENT_RANK
            from odh.IIAS_DATAIT_RB_RANKING t
            """;
	}

	private static GString getMarketingQuery(String condition) {
		return """
            SELECT IID, engagement_rank
            FROM AUTOMKT.TWT_DG_ENGAGEMENTS_STATUS
            WHERE 1 = '${condition}'
            """;
	}

	private static Map<String, String> createDataMap(List<Map<String, Object>> data, String keyField, String valueField) {
		Map<String, String> dataMap = new HashMap<>();
//		KeywordUtil.logInfo("IID: " + dataMap.get("IID") + ", ENGAGEMENT_RANK: " + dataMap.get("ENGAGEMENT_RANK"));
		for (Map<String, Object> row : data) {
			String key = row.get(keyField).toString();
			String value = row.get(valueField).toString();
			dataMap.put(key, value);
		}
		return dataMap;
	}

	private static void compareDataOdhWithAutoMkt(Map<String, String> odhMap, Map<String, String> marketingMap) {
		if (!odhMap.equals(marketingMap)) {
			KeywordUtil.logInfo("Data from both databases are not equal");

			for (String key : odhMap.keySet()) {
				if (!marketingMap.containsKey(key) || !marketingMap.get(key).equals(odhMap.get(key))) {
//					KeywordUtil.logInfo("IID: " + key + ", Value in ODH: " + odhMap.get(key) + ", Value in Marketing: " + marketingMap.get(key));
					KeywordUtil.markFailed("Data from ODH to Marketing is not synchronized!");
				}
			}
		} else {
			KeywordUtil.logInfo("Dữ liệu 2 cơ sở dữ liệu ODH và AutoMKT là khớp!");
		}
	}

	private static void compareWithExcelData(String excelFilePath, Map<String, String> marketingMap) {
		int mismatchCount = 0; // Khởi tạo biến đếm số lượng dòng không khớp
		Set<String> marketingInsiderIds = new HashSet<>(marketingMap.keySet());
		Set<String> excelInsiderIds = new HashSet<>();

		try {
			String projectDir = RunConfiguration.getProjectDir();
			String filePath = projectDir + excelFilePath;
			KeywordUtil.logInfo("Current path: " + filePath);

			FileInputStream file = new FileInputStream(filePath);
			Workbook workbook = new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheetAt(0);

			int rowCount = sheet.getPhysicalNumberOfRows();
			KeywordUtil.logInfo("Total rows in Excel: " + rowCount);

			// Bắt đầu đọc từ dòng thứ hai (index 1)
			for (int i = 1; i < rowCount; i++) {
				Row row = sheet.getRow(i); // Lấy dòng tại chỉ mục i

				Cell insiderIdCell = row.getCell(0);
				Cell engagementRankCell = row.getCell(1);

				if (insiderIdCell != null && engagementRankCell != null) {
					String insiderId = insiderIdCell.getStringCellValue();
					String engagementRank = engagementRankCell.getStringCellValue();
					excelInsiderIds.add(insiderId); // Thêm insiderId vào excelInsiderIds

					if (!marketingMap.containsKey(insiderId) || !marketingMap.get(insiderId).equals(engagementRank)) {
						KeywordUtil.markFailed("Dữ liệu Insider không khớp với AutoMKT!");
					}

					// Xóa insiderId khỏi marketingInsiderIds nếu nó được tìm thấy trong mapExcel
					marketingInsiderIds.remove(insiderId);
				}
			}

			// In ra các dòng trong mapExcel mà không có trong marketingMap
			for (String missingInsiderId : marketingInsiderIds) {
				KeywordUtil.logInfo("Missing data: UUID: " + missingInsiderId);
				mismatchCount++; // Tăng biến đếm với mỗi dòng không tìm thấy trong marketingMap
			}

			// In ra các dòng trong marketingMap mà không có trong mapExcel
			for (String missingInsiderId : excelInsiderIds) {
				if (!marketingMap.containsKey(missingInsiderId)) {
					KeywordUtil.logInfo("Excess data on Insider: UUID: " + missingInsiderId);
					mismatchCount++; // Tăng biến đếm với mỗi dòng không tìm thấy trong mapExcel
				}
			}

			KeywordUtil.logInfo("Total mismatched rows: " + mismatchCount); // In ra tổng số lượng dòng không khớp
			KeywordUtil.markFailed("Dữ liệu Insider không khớp với AutoMKT!");
			workbook.close();
			file.close();
		} catch (IOException e) {
			KeywordUtil.markFailed("Error reading Excel file: " + e.getMessage());
		}
	}

	//4.1.Update AuoMKT database into Insider
	@Keyword
	public static String updateEngagementStatusData(String engagementRank, String iid) {
		GString updateAutoMKT = """
		    update AUTOMKT.TWT_DG_ENGAGEMENTS_STATUS
			set engagement_rank = '${engagementRank}'
			where iid = '${iid}'
		"""
		try {
			DatabaseExecutor.updateData(MARKETING_DB_PARAMS, updateAutoMKT);
		} catch (Exception e) {
			println("Error update data into TWT_DG_ENGAGEMENTS_STATUS: ${e.message}")
		}

		//update xong khoảng 1 tháng mới đồng bộ lên insider => case này test maunual
	}
}



