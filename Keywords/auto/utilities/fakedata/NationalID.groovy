package auto.utilities.fakedata

public class NationalID extends DataFaker{
	public NationalID() {
		super();
	}

	/**
	 * @return Số CCCD mới
	 */
	public String newID() {
		return faker.number().digits(12);
	}

	/**
	 * @return Số CMND/CMT cũ (9 digits)
	 */
	public String oldID() {
		return faker.number().digits(9);
	}

	/**
	 * @return random CCCD/CMT
	 */
	public String randomID() {
		return faker.bool().bool() ? newID() : oldID();
	}

	/**
	 *
	 * @return Mã hộ chiếu ( B hoặc C + 7 chữ số)
	 */
	public String passport(){
		return (faker.bool().bool() ? "B" : "C") +faker.number().digits(7);
	}
}
