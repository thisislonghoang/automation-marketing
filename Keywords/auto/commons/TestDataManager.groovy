package auto.commons

import static com.kms.katalon.core.testdata.TestDataFactory.findTestData

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.util.KeywordUtil

public class TestDataManager {
	/**
	 * 
	 * @param data
	 * @param columnName
	 * @param cellValue
	 * @return first valid data Row
	 */
	public int getFirstValidDataRow(TestData data,String columnName,String cellValue) {
		int rowIndex = 0
		KeywordUtil.logInfo("Data Source Url: ${data.getSourceUrl()}")
		for(int i=1;i<=data.getAllData().size();i++) {
			if(data.getValue(columnName, i).equalsIgnoreCase(cellValue)) {
				rowIndex = i
				KeywordUtil.logInfo("Found valid data at Column: ${columnName} - RowIndex: ${rowIndex}")
				break
			}
		}

		if(rowIndex == 0) {
			throw new Exception("Can not found valid data at column ${columnName}!")
		}
		return rowIndex
	}

	public int getFirstValidDataRow(String testDataRelativeId,String columnName,String cellValue) {
		TestData data = TestDataFactory.findTestData(testDataRelativeId)
		return getFirstValidDataRow(data,columnName,cellValue)
	}

	public int getFirstValidDataRowByTCID(String testDataRelativeId,String tcID) {
		return getFirstValidDataRow(testDataRelativeId,"TCID",tcID)
	}
}
