package br.com.healthcare.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	private static final String DATE_PATTERN = "dd/MM/yyyy HH:mm:ss";
	private static final int[] weightTin = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
	
	public static String formatDateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
		return sdf.format(date);
	}
	
	  /**
     * Valida CNPJ
     *
     * @param tin
     * @return
     */
    public static boolean isValidCNPJ(final String tin) {
        if ((tin == null) || (tin.length() != 14) || tin.matches(tin.charAt(0) + "{14}")) return false;

        final Integer digit1 = calculate(tin.substring(0, 12), weightTin);
        final Integer digit2 = calculate(tin.substring(0, 12) + digit1, weightTin);
        return tin.equals(tin.substring(0, 12) + digit1.toString() + digit2.toString());
    }
    
    private static int calculate(final String str, final int[] weight) {
        int sum = 0;
        for (int i = str.length() - 1, digit; i >= 0; i--) {
            digit = Integer.parseInt(str.substring(i, i + 1));
            sum += digit * weight[weight.length - str.length() + i];
        }
        sum = 11 - sum % 11;
        return sum > 9 ? 0 : sum;
    }
}
