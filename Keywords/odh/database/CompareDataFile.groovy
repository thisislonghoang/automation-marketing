package odh.database

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.util.KeywordUtil

public class CompareDataFile {

	@Keyword
	public static void compareData(String excelFilePath1, String excelFilePath2) {
		String filePath1 = getFilePath(excelFilePath1)
		String filePath2 = getFilePath(excelFilePath2)

		// Đọc dữ liệu từ hai file Excel và lưu vào map
		Map<String, String[]> map1 = readExcel(filePath1);
		Map<String, String[]> map2 = readExcel(filePath2);

		// In ra dữ liệu của 2 files và tổng số lượng row
//		printDataWithRowCount(map1, "File1");
//		KeywordUtil.logInfo("")
//		printDataWithRowCount(map2, "File2");
//		KeywordUtil.logInfo("")

		compareDataFile(map1, map2)
	}

	private static void compareDataFile(Map<String, String[]> map1, Map<String, String[]> map2) {
		// Duyệt qua từng row của file1
		for (Map.Entry<String, String[]> entry1 : map1.entrySet()) {
			String rowKey1 = entry1.getKey();
			String[] row1 = entry1.getValue();
			String column1Data1 = row1[0]; // Lấy giá trị ở cột đầu tiên của row1

			// Biến để kiểm tra xem đã tìm thấy dữ liệu khớp ở file2 chưa
			boolean foundMatch = false;

			// Duyệt qua từng row của file2 để tìm giá trị khớp với giá trị ở cột đầu tiên của row1
			for (Map.Entry<String, String[]> entry2 : map2.entrySet()) {
				String rowKey2 = entry2.getKey();
				String[] row2 = entry2.getValue();
				String column1Data2 = row2[0]; // Lấy giá trị ở cột đầu tiên của row2

				// Nếu tìm thấy giá trị khớp, thực hiện so sánh các giá trị từ cột thứ hai trở đi của hai row
				if (column1Data1.equals(column1Data2)) {
					foundMatch = true;

					// So sánh các giá trị từ cột thứ hai trở đi của hai row
					for (int i = 1; i < row1.length; i++) {
						if (!row1[i].equals(row2[i])) {
							// Nếu có bất kỳ sự khác biệt nào, đánh dấu là không khớp và in ra thông báo
							KeywordUtil.markFailed("Dữ liệu từ File1 không khớp với File2 tại dòng: " + rowKey1 + ", cột: " + (i + 1));
							KeywordUtil.logInfo("File1, " + "UUID: " + column1Data1 + ", dòng " + rowKey1 + ", cột " + (i + 1) + ": " + row1[i]);
							KeywordUtil.logInfo("File2, " + "UUID: " + column1Data2 + ", dòng " + rowKey2 + ", cột " + (i + 1) + ": " + row2[i]);
						}
					}

					// Dừng vòng lặp khi đã tìm thấy giá trị khớp
					break;
				}
			}

			// Nếu không tìm thấy giá trị khớp ở file2, in ra thông báo và giá trị không tìm thấy
			if (!foundMatch) {
				KeywordUtil.logInfo("Không tìm thấy dữ liệu " + column1Data1 + " trong File2");
				KeywordUtil.markFailed("Không tìm thấy dữ liệu " + column1Data1 + " trong File2");
			}
		}
	}

	// Hàm in ra dữ liệu của 1 file và số lượng row
	private static void printDataWithRowCount(Map<String, String[]> map, String fileName) {
		int rowCount = map.size();
		KeywordUtil.logInfo("Số lượng dòng của " + fileName + ": " + rowCount);
		KeywordUtil.logInfo("Dữ liệu của " + fileName + ":");
		for (Map.Entry<String, String[]> entry : map.entrySet()) {
			String key = entry.getKey();
			String[] rowData = entry.getValue();
			StringBuilder rowDataString = new StringBuilder();
			for (String cellData : rowData) {
				rowDataString.append(cellData).append("; ");
			}
			KeywordUtil.logInfo(key + ": " + rowDataString.toString());
		}
	}

	// Hàm đọc dữ liệu từ file Excel và trả về một map
	private static Map<String, String[]> readExcel(String filePath) {
		Map<String, String[]> dataMap = new HashMap<>();
		try {
			FileInputStream file = new FileInputStream(filePath);
			Workbook workbook = new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheetAt(0);

			// Bắt đầu đọc từ dòng thứ hai để bỏ qua dòng tiêu đề
			int rowNum = 1; // Bắt đầu từ dòng thứ hai
			for (Row row : sheet) {
				if (rowNum == 1) {
					// Bỏ qua dòng đầu tiên (dòng tiêu đề)
					rowNum++;
					continue;
				}

				List<String> rowData = new ArrayList<>();
				for (Cell cell : row) {
					String cellValue = cell.toString().trim();
					rowData.add(cellValue);
				}

				dataMap.put("Row " + rowNum, rowData.toArray(new String[0]));
				rowNum++;
			}

			workbook.close();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataMap;
	}

	private static String getFilePath(String excelFilePath) {
		String projectDir = RunConfiguration.getProjectDir()
		String filePath = projectDir + excelFilePath
		KeywordUtil.logInfo("Current path: " + filePath)
		return filePath
	}
}

