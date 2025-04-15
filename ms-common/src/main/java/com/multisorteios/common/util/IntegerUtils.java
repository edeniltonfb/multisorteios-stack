package com.multisorteios.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO (henrique, 06/02/2014): seria melhor isso se NumberUtils e usar a
// interface Number, abrangendo vários outros objetos (Long, Short, etc)
public class IntegerUtils {
	
	/**
	 * Verifica se dois números inteiros são iguais. Se os dois forem nulos, retorna verdadeiro.
	 * @param i1 Primeiro Inteiro
	 * @param i2 Primeiro Inteiro
	 * @return
	 */
	public static boolean equals(Integer i1, Integer i2) {
		if(i1 == null ^ i2 == null) {
			return false;
		}
		
		if(i1 == null && i2 == null) {
			return true;
		}
		
		return i1.equals(i2);	
	}
	
	
	/**
	 * Verifica se dois números inteiros não são nulos e são iquais.
	 * @param i1 Primeiro Inteiro
	 * @param i2 Segundo Inteiro
	 * @return
	 */
	public static boolean equalsAndNotNull(Integer i1, Integer i2) {
		if(i1 == null || i2 == null) {
			return false;
		}
		
		return i1.equals(i2);	
	}
	
	

	public static boolean isNullOrZero(Integer value){
		return (value == null || value.equals(new Integer(0)));
	}
	
	/**
	 * Verifica se pelo menos um dos valores passados por parâmetro é nulo ou zero
	 * @param values
	 * @return true se houver pelo menos um valor nulo, false caso contrário
	 */
	public static boolean hasValueNullOrZero(Integer ... values){
		for(Integer value : values){
			if(isNullOrZero(value)){
				return true;
			}
		}
		return false;
	}

	public static boolean isNullOrZeroOrNegative(Integer value){
		return (isNullOrZero(value) || value < 0);
	}
	
	public static boolean isNegative(Integer value) {
		if(value == null){
			return false;
		}
		
		return value < 0;
	}

	public static boolean isNotNull(Object... itens){
		for(Object obj : itens){
			if(obj == null) {
				return false;
			}
		}
		return true;
	}

	public static Integer notNullValue(Integer value) {
		if(value == null){
			return 0;
		}
		return value;
	}

	public static Integer nullIfZero(Integer value) {
		if (value == null) {
			return null;
		}
		if (value == 0) {
			return null;
		}
		return value;
	}
	
	public static Integer zeroIfNull(Integer value) {
		if (value == null) {
			return 0;
		}
		return value;
	}

	public static Integer alternative(Integer value, Integer alternative) {
		if(value == null){
			return alternative;
		}
		return value;
	}


	/**
	 * Verifica se um número inteiro está fora do intervalo
	 * @param value - Valor a ser testado
	 * @param minValue - Valor mínimo permitido
	 * @param maxValue - Valor máximo permitido
	 * @param ignoreLimits - flag para determinar se os limites podem ser incluídos nos teste
	 * 						usar true para ignorar os limites e false para consirerá-los
	 * @return true se o valor estiver fora dos limites estabelecidos 
	 */
	public static boolean isOutOfRange(Integer value, Integer minValue, Integer maxValue, boolean ignoreLimits) {

		if (value == null) {
			return true;
		}

		if (ignoreLimits) {
			if (minValue != null && minValue > value) {
				return true;
			}

			if (maxValue != null && maxValue < value) {
				return true;
			}
		} else {
			if (minValue != null && minValue >= value) {
				return true;
			}

			if (maxValue != null && maxValue <= value) {
				return true;
			}
		}

		return false;

	}

	
	private static Pattern patterNumeric = Pattern.compile("^[0-9]+$");
	
	public static boolean isNumeric(String text){
		if(text == null){
			return false;
		}
		Matcher m = patterNumeric.matcher(text);
		return m.find();
	}


	public static boolean isEquals(Integer value, Integer otherValue) {
		
		//retorna true se os dois são nulos
		if(value == null && otherValue == null){
			return true;
		}
		
		//retorna false se somente um deles é nulo ('ou' exclusivo)
		if(value == null ^ otherValue == null){
			return false;
		}
		
		//retorna true se os dois forem iguais
		return value.equals(otherValue);
	}

	public static Integer valueOf(String text) {

		if (text == null) {
			return null;
		}

		Integer value = null;
		try {
			value = Integer.valueOf(text.trim());
		} catch (Exception e) {
			//
		}
		return value;
	}

}
