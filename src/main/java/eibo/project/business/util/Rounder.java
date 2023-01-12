package eibo.project.business.util;

public class Rounder {
	
	public static final int DEFAULT_ROUNDING_DIGITS = 2;
	
	public static double round(double number) {
		return roundToDigits(number, DEFAULT_ROUNDING_DIGITS);
	}

	public static double roundToDigits(double number, int digits) {
		
		var factor = Math.pow(10, digits);			
		var rounded = (double) Math.round(number * factor) / factor; 
		
		return rounded;
	}
	
}
