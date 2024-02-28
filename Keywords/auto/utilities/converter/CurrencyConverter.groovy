package auto.utilities.converter

import java.math.RoundingMode
import java.text.NumberFormat

/**
 * 
 * @author ChungND1
 * Convert currency for calculative type (BigDecimal)
 */
public class CurrencyConverter {

	def BigDecimal stringToCurrency(String stringCurrency) {
		BigDecimal num = 0
		String newString = stringCurrency.replaceAll("[^\\d]+", "")
		println(newString)
		return new BigDecimal(newString)
	}

	def String stringSpace(String stringCurrency) {

		String newString = stringCurrency.replaceAll(" ", "")
		println(newString)
		return newString
	}

	def String formatCurrency(BigDecimal value) {
		NumberFormat format = NumberFormat.getInstance(new Locale("en", "US"));
		format.setMinimumFractionDigits(2);
		format.setMaximumFractionDigits(5);
		format.setRoundingMode(RoundingMode.HALF_EVEN);
		return format.format(value);
	}

	def String formatCurrency(BigDecimal value,Locale locale) {
		NumberFormat format = NumberFormat.getCurrencyInstance(locale);
		format.setMinimumFractionDigits(2);
		format.setMaximumFractionDigits(5);
		format.setRoundingMode(RoundingMode.HALF_EVEN);
		return format.format(value);
	}

	def String formatUSDCurrency(BigDecimal value) {
		return formatCurrency(value,new Locale("en", "US"));
	}

	def String formatVNDCurrency(BigDecimal value) {
		return formatCurrency(value,new Locale("vi", "VN"));
	}
}
