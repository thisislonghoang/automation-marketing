package auto.utilities.fakedata

import java.util.concurrent.TimeUnit

public class TPBank extends DataFaker{
	public TPBank() {
		super();
	}

	public static TpbOdh cdh() {
		return new TpbOdh();
	}

	/**
	 * @return Số Cif KH (8 ký tự)
	 */
	public String randomCifNo() {
		return faker.number().digits(8);
	}

	/**
	 * @return api ref no (16 ký tự)
	 */
	public String randomRefNo() {
		return "AUTO" + this.dateTime().getTimeInSecond();
	}

	/**
	 * @return Số tài khoản thanh toán thông thường (cifno + 3 ký tự)
	 */
	public String randomAccountNo() {
		return faker.number().digits(11);
	}
	public String gender() {
		return faker.gender().shortBinaryTypes().toUpperCase();
	}

	public String email(String user) {
		return user + "@tpb.com.vn";
	}
}
