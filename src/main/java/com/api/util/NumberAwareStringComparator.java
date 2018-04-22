package com.api.util;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ivofreitas
 * 
 * Classe com nova implementação de "compare" para ordenação de TreeMap
 */
public class NumberAwareStringComparator implements Comparator<String> {

	private static final Pattern PATTERN = Pattern.compile("(\\D*)(\\d*)");

	@Override
	public int compare(String s1, String s2) {
		Matcher m1 = PATTERN.matcher(s1);
		Matcher m2 = PATTERN.matcher(s2);

		while (m1.find() && m2.find()) {

			int nonDigitCompare = m1.group(1).compareTo(m2.group(1));
			if (0 != nonDigitCompare) {
				return nonDigitCompare;
			}

			if (m1.group(2).isEmpty()) {
				return m2.group(2).isEmpty() ? 0 : -1;
			} else if (m2.group(2).isEmpty()) {
				return +1;
			}

			BigInteger n1 = new BigInteger(m1.group(2));
			BigInteger n2 = new BigInteger(m2.group(2));
			int numberCompare = n1.compareTo(n2);
			if (0 != numberCompare) {
				return numberCompare;
			}
		}

		return m1.hitEnd() && m2.hitEnd() ? 0 : m1.hitEnd() ? -1 : +1;
	}
}