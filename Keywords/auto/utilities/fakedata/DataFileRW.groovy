package auto.utilities.fakedata

public class DataFileRW {

	public static String readCellValue(String text) {
		String value = ''

		if(text.equalsIgnoreCase("NULL")) {
			value = null
		}else if(text.equalsIgnoreCase("EMPTY")) {
			value = ""
		}else {
			value = text
		}
		return value
	}
}
