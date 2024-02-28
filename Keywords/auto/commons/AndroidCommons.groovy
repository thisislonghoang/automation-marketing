package auto.commons
import io.appium.java_client.android.nativekey.AndroidKey

/**
 * Android common functions
 * @author ChungND1
 *
 */
public class AndroidCommons {

	/**
	 * Get Android digit key
	 * @param text
	 * @return
	 */
	public AndroidKey getDigitKey(String text) {

		switch(text) {
			case "0": return AndroidKey.DIGIT_0
				break
			case "1": return AndroidKey.DIGIT_1
				break
			case "2": return AndroidKey.DIGIT_2
				break
			case "3": return AndroidKey.DIGIT_3
				break
			case "4": return AndroidKey.DIGIT_4
				break
			case "5": return AndroidKey.DIGIT_5
				break
			case "6": return AndroidKey.DIGIT_6
				break
			case "7": return AndroidKey.DIGIT_7
				break
			case "8": return AndroidKey.DIGIT_8
				break
			case "9": return AndroidKey.DIGIT_9
				break
			default : throw new Exception("Invalid key!")
		}
	}
}
