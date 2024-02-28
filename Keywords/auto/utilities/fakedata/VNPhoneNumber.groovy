package auto.utilities.fakedata

import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory

import internal.GlobalVariable

public class VNPhoneNumber extends DataFaker{

	public VNPhoneNumber() {
		super();
	}

	private final List<String> VN_PHONE_PREFIX = [
		"086",
		"096",
		"097",
		"098",
		"039",
		"038",
		"037",
		"036",
		"035",
		"034",
		"033",
		"032",
		"091",
		"094",
		"088",
		"083",
		"084",
		"085",
		"081",
		"082",
		"070",
		"079",
		"077",
		"076",
		"078",
		"089",
		"090",
		"093",
		"092",
		"099"
	];

	public String randomPhonePrefix() {
		return VN_PHONE_PREFIX[faker.random().nextInt(VN_PHONE_PREFIX.size())]
	}

	/**
	 *
	 * @return Random VietNam Phone Number
	 */
	public String randomPhoneNo() {
		return randomPhonePrefix() + faker.number().digits(7);
	}

	public String genPhone (String text) {
		if(text.equalsIgnoreCase("RANDOM")) {
			return genPhone()
		}else return text
	}
}
