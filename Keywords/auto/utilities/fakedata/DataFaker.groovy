package auto.utilities.fakedata

import net.datafaker.Faker

public class DataFaker {
	public Faker faker;

	public DataFaker() {
		this.faker = new Faker();
	}

	public Faker getFaker() {
		return this.faker;
	}

	public static String randomUUID() {
		return UUID.randomUUID().toString()
	}

	public String randomName () {
		String name = "AUTO"
		int number = faker.number().numberBetween(1, 9)
		switch(number) {
			case 1: name = faker.name().lastName();break;
			case 2: name = faker.name().firstName();break;
			case 3: name = faker.leagueOfLegends().champion();break;
			case 4: name = faker.animal().name();break;
			default : name = faker.name().lastName();break;
		}
		return name.replaceAll("[-+.^:,'& ]","").trim().toUpperCase();
	}

	public static VNPhoneNumber vnPhone() {
		return new VNPhoneNumber();
	}

	public static NationalID nationalID() {
		return new NationalID();
	}

	public static TPBank tpbank() {
		return new TPBank();
	}

	public static VNName vnName() {
		return new VNName();
	}
	public static DateTime dateTime() {
		return new DateTime();
	}
}
