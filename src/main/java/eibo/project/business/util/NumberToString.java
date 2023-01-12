package eibo.project.business.util;

import java.text.DecimalFormat;

public class NumberToString {

	public static final double KILO 		= 1_000.0;
	public static final double MILLION 		= 1_000_000.0;
	public static final double BILLION 		= 1_000_000_000.0;
	public static final double TRILLION 	= 1_000_000_000_000.0;
	public static final double QUADRILLION 	= 1_000_000_000_000_000.0;

	public static String format(double number) {
		
		var abbreviation = "";
		var df = new DecimalFormat("##0.00");	// e.g. 123.45 or 0.12 or 32.10

		if (number / QUADRILLION > 1) {
			abbreviation = "Q";
			number /= QUADRILLION;
		} else if (number / TRILLION > 1) {
			abbreviation = "T";
			number /= TRILLION;
		} else if (number / BILLION > 1) {
			abbreviation = "B";
			number /= BILLION;
		} else if (number / MILLION > 1) {
			abbreviation = "M";
			number /= MILLION;
		} else if (number / KILO > 1) {			
			abbreviation = "K";
			number /= KILO;
		}

		return df.format(number) + " " + abbreviation;	
	}
	
}