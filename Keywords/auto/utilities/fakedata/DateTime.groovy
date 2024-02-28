package auto.utilities.fakedata

import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

public class DateTime extends DataFaker{
	public DateTime() {
		super();
	}

	public LocalDateTime getLocalDateTime() {
		LocalDateTime localDateTime = LocalDateTime.now()
		return localDateTime;
	}

	public ZonedDateTime parseString(String dateTimeString) {
		return ZonedDateTime .parse(dateTimeString)
	}
	
	public String getCurrentDate() {
		getLocalDateTime().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
	}

	public String getSlashCurrentDate() {
		return getLocalDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
	}

	public String getTimeInSecond() {
		return getLocalDateTime().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
	}

	public String getTimeInMilliSecond() {
		return getLocalDateTime().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"))
	}

	public String getTimeInSecondWithUTC() {
		return getLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
	}

	public String getCurrrentHourAndMinute() {
		return getLocalDateTime().format(DateTimeFormatter.ofPattern("HH:mm"))
	}

	public static String getDurationTime(String startTimeString, String endTimeString) {
		LocalDateTime startDateTime = LocalDateTime.parse(startTimeString, DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime endDateTime = LocalDateTime.parse(endTimeString, DateTimeFormatter.ISO_DATE_TIME);
		Duration duration = Duration.between(startDateTime, endDateTime);
		long hours = duration.toHours();
		long minutes = duration.toMinutes() % 60;
		long seconds = duration.getSeconds() % 60;
		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}

	public String returnToday (String format) {
		def date = new Date()
		def dateFormat = new SimpleDateFormat(format)
		String today = dateFormat.format(date)
		return today
	}

	public String convertDateOfBirth(String date) {
		String datePattern = "dd/MM/yyyy"
		DateTimeFormatter dob = DateTimeFormatter.ofPattern(datePattern)
		LocalDate parsedDate = LocalDate.parse(date, dob)
		return parsedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString()
	}
}














