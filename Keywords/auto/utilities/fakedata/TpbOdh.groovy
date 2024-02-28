package auto.utilities.fakedata

import java.time.format.DateTimeFormatter

public class TpbOdh extends TPBank{
	public TpbOdh() {
		super();
	}
	
	/**
	 * @return period (Định dạng YYYYMM)
	 */
	public String randomPeriod() {
		return this.dateTime().getLocalDateTime().format(DateTimeFormatter.ofPattern("YYYYMM"))
	}
}
