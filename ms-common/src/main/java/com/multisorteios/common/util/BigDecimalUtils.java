package com.multisorteios.common.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

// TODO (henrique, 06/02/2014): seria melhor isso se NumberUtils e usar a
// interface Number, abrangendo vários outros objetos (Long, Short, etc)
public class BigDecimalUtils {

	/**
	 * Modo de arredonamento padrão utilizado pelos métodos que não passam
	 * nenhum. Caso altere esse valor, deve alterar também os comentários dos
	 * métodos que o utilizam
	 */
	private static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_DOWN;

	private static final String[] UNIDADES = { "", "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito", "nove", "dez", "onze", "doze", "treze", "quatorze", "quinze", "dezesseis", "dezessete", "dezoito", "dezenove" };
	private static final String[] CENTENAS = { "", "cento", "duzentos", "trezentos", "quatrocentos", "quinhentos", "seiscentos", "setecentos", "oitocentos", "novecentos" };
	private static final String[] DEZENAS = { "", "", "vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta", "oitenta", "noventa" };
	private static final String[] MILHARES_SINGULAR = { "", "mil", "milhão", "bilhão", "trilhão" };
	private static final String[] MILHARES_PLURAL = { "", "mil", "milhões", "bilhões", "trilhões" };


	/**
	 * Verifica se dois BigDecimals são iguais.
	 * 
	 * Esse método é null-safe (se os dois forem nulos retorna verdadeiro; se apenas um for nulo, retorna false)
	 * 
	 * @param i1
	 *            Primeiro numero
	 * @param i2
	 *            Segundo numero
	 * @return
	 */
	public static boolean equals(BigDecimal i1, BigDecimal i2) {
		if (i1 == null ^ i2 == null) {
			return false;
		}

		if (i1 == null && i2 == null) {
			return true;
		}

		return (i1.compareTo(i2) == 0);
	}

	
	public static boolean isNullOrZero(BigDecimal value) {
		return (value == null || value.compareTo(BigDecimal.ZERO) == 0);
	}

	public static boolean isNullOrZeroOrNegative(BigDecimal value) {
		return (isNullOrZero(value) || value.compareTo(BigDecimal.ZERO) < 0);
	}
	
	/** Verifica se o valor passado é negativo
	 * 
	 * @param value Valor
	 * @return true se for negativo; false caso contrário
	 */
	public static boolean isNegative(BigDecimal value) {
		return value.compareTo(BigDecimal.ZERO) < 0;
	}

	public static BigDecimal notNullValue(BigDecimal value) {
		if (value == null) {
			return BigDecimal.ZERO;
		}
		return value;
	}
	
	public static BigDecimal notNullValueAndRound(BigDecimal value, int scale, RoundingMode roundingMode) {
		if (value == null) {
			return BigDecimal.ZERO;
		}
		return round(value, scale, roundingMode);
	}

	public static BigDecimal nullIfZero(BigDecimal value) {
		if (value == null) {
			return null;
		}
		if ((BigDecimal.ZERO).equals(value)) {
			return null;
		}
		return value;
	}

	/**
	 * Retorna o valor do double em BigDecimal com a precisão informada.
	 * Arredonda ROUND_HALF_UP. Como no regulamento operacional dos sistemas de pagamentos do BC.
	 * Quando a terceira casa decimal for igual ou superior a 5 (cinco) aumentar-se-á uma unidade ao valor da segunda casa
	 * decimal; se for inferior, o valor da segunda casa decimal será mantido. 
	 * @param value Valor a ser convertido.
	 * @param precision Precisão.
	 * @return
	 */
	public static BigDecimal valueOf(double value, int precision){
		BigDecimal bd = new BigDecimal(String.valueOf(value));
		bd = bd.setScale(precision,BigDecimal.ROUND_HALF_UP);
		return bd;
	}
	
	/**
	 * Retorna valor truncado na precisao desejada
	 * @param value Valor a ser truncado
	 * @param precision Precisao do truncamento (numero de casas decimais a considerar)
	 * @return Valor Truncado na Precisao
	 */
	public static BigDecimal truncValue(BigDecimal value, int precision) {
		if (value == null) {
			return null;
		}
		return value.setScale(precision, BigDecimal.ROUND_DOWN);
	}

	/**
	 * Retorna a divisao de dois numeros, truncando o valor com a precisao indicada
	 * @param numerator Numerador da divisao
	 * @param divisor Denominador da divisao
	 * @param precision Precisao utilizada para fazer a divisao (numero de casas decimais a considerar)
	 * @return Valor dividido
	 */
	public static BigDecimal divideTrunc(BigDecimal numerator, BigDecimal divisor, int precision) {
		if (numerator == null || isNullOrZero(divisor)) {
			return null;
		}
		return numerator.divide(divisor, precision, RoundingMode.DOWN);
	}

	/**
	 * @Autor Marcio Santos (parafraseando Elio Cruz em DoubleUtils - valorPorExtenso())
	 * 
	 * Método que converte um valor monetário numerico para literal
	 * @param valor
	 * @return
	 */
	public static String valorPorExtenso(BigDecimal valor) {
		final int LIMITE_TRILHOES = 15;
		
		if (BigDecimal.ZERO.compareTo(valor) == 0) {
			return ("zero");
		}

		long inteiro = valor.longValue(); // parte inteira do valor
		BigDecimal resto = valor.subtract(new BigDecimal(inteiro)); // parte fracionária do valor

		String vlrS = String.valueOf(inteiro);
		if (vlrS.length() > LIMITE_TRILHOES) {
			return ("Erro: valor superior a 999 trilhões.");
		}
		String s = "", saux, vlrP;
		String centavos = Integer.toString(resto.round(new MathContext(2)).multiply(new BigDecimal(100)).intValue());


		// definindo o extenso da parte inteira do valor
		int n, unid, dez, cent, tam, i = 0;
		boolean umReal = false, tem = false;
		while (!"0".equals(vlrS)) {
			tam = vlrS.length();
			// retira do valor a 1a. parte, 2a. parte, por exemplo, para
			// 123456789:
			// 1a. parte = 789 (centena)
			// 2a. parte = 456 (mil)
			// 3a. parte = 123 (milhões)
			if (tam > 3) {
				vlrP = vlrS.substring(tam - 3, tam);
				vlrS = vlrS.substring(0, tam - 3);
			} else { // última parte do valor
				vlrP = vlrS;
				vlrS = "0";
			}
			if (!"000".equals(vlrP)) {
				saux = "";
				if ("100".equals(vlrP)) {
					saux = "cem";
				} else {
					n = Integer.parseInt(vlrP, 10); // para n = 371, tem-se:
					cent = n / 100; // cent = 3 (centena trezentos)
					dez = (n % 100) / 10; // dez = 7 (dezena setenta)
					unid = (n % 100) % 10; // unid = 1 (unidade um)
					if (cent != 0) {
						saux = CENTENAS[cent];
					}
					if ((n % 100) <= 19) {
						if (saux.length() != 0) {
							saux = saux + " e " + UNIDADES[n % 100];
						} else {
							saux = UNIDADES[n % 100];
						}
					} else {
						if (saux.length() != 0) {
							saux = saux + " e " + DEZENAS[dez];
						} else {
							saux = DEZENAS[dez];
						}
						if (unid != 0) {
							if (saux.length() != 0) {
								saux = saux + " e " + UNIDADES[unid];
							} else {
								saux = UNIDADES[unid];
							}
						}
					}
				}
				if ("1".equals(vlrP) || "001".equals(vlrP)) {
					if (i == 0) { // 1a. parte do valor (um real)
						umReal = true;
					} else {
						saux = saux + " " + MILHARES_SINGULAR[i];
					}
				} else if (i != 0) {
					saux = saux + " " + MILHARES_PLURAL[i];
				}
				if (s.length() != 0) {
					s = saux + ", " + s;
				} else {
					s = saux;
				}
			}
			if (((i == 0) || (i == 1)) && s.length() != 0) {
				tem = true; // tem centena ou mil no valor
			}
			i = i + 1; // próximo qualificador: 1- mil, 2- milhão, 3- bilhão,
						// ...
		}

		if (s.length() != 0) {
			if (umReal) {
				s = s + " real";
			} else if (tem) {
				s = s + " reais";
			} else {
				s = s + " de reais";
			}
		}

		// definindo o extenso dos centavos do valor
		if (!"0".equals(centavos)) { // valor com centavos
			if (s.length() != 0) {// se não é valor somente com centavos
				s = s + " e ";
			}
			if ("1".equals(centavos)) {
				s = s + "um centavo";
			} else {
				n = Integer.parseInt(centavos, 10);
				if (n <= 19) {
					s = s + UNIDADES[n];
				} else { // para n = 37, tem-se:
					unid = n % 10; // unid = 37 % 10 = 7 (unidade sete)
					dez = n / 10; // dez = 37 / 10 = 3 (dezena trinta)
					s = s + DEZENAS[dez];
					if (unid != 0) {
						s = s + " e " + UNIDADES[unid];
					}
				}
				s = s + " centavos";
			}
		}
		return (s);
	}
	
	
	/**
	 * Compara considerando um número específico de casas decimais da parte
	 * fracionária e um modo de arredondamento.
	 * 
	 * @param val1
	 * @param val2
	 * @param scale Número de casas da parte fracionária
	 * @param roundMode Modo de arredondamento
	 * @return
	 */
	public static boolean equals(BigDecimal val1, BigDecimal val2, int scale, RoundingMode roundMode) {
		if (val1 == null ^ val2 == null) {
			return false;
		}

		if (val1 == null && val2 == null) {
			return true;
		}

		val1 = round(val1, scale, roundMode);
		val2 = round(val2, scale, roundMode);
		return val1.compareTo(val2)==0;
	}
	
	
	/**
	 * Compara considerando um número específico de casas decimais da parte
	 * fracionária e o modo de arredonamento <code>RoundingMode.HALF_DOWN</code>
	 * @param val1
	 * @param val2
	 * @param scale Número de casas da parte fracionária
	 * @param roundMode Modo de arredondamento
	 * @return
	 */
	public static boolean equals(BigDecimal val1, BigDecimal val2, int scale) {
		return equals(val1, val2, scale, DEFAULT_ROUNDING_MODE);
	}
	
	
	/**
	 * Realiza o arredondamento do número considerando um número específico de casas decimais
	 * da parte fracionária e um modo de arredondamento.
	 * 
	 * @param val
	 * @param scale Número de casas da parte fracionária
	 * @param roundMode Modo de arredondamento
	 * @return
	 */
	public static BigDecimal round(BigDecimal val, int scale, RoundingMode roundMode) {
		val = val.setScale(scale, roundMode);
		return val;
	}
	
	/**
	 * Realiza o arredondamento do número considerando um número específico de casas decimais
	 * da parte fracionária e o modo de arredondamento <code>RoundingMode.HALF_DOWN</code>
	 * 
	 * @param val
	 * @param scale Número de casas da parte fracionária
	 * @param roundMode Modo de arredondamento
	 * @return
	 */
	public static BigDecimal round(BigDecimal val, int scale) {
		return round(val, scale, DEFAULT_ROUNDING_MODE);
	}
	
	/**
	 * Formata o valor BigDecimal em moeda padrão do ambiente.
	 * @param value Valor a ser formatado.
	 * @return
	 */
	public static String formatMoney(BigDecimal value){
		return NumberFormat.getCurrencyInstance().format(value);
	}


	/**
	 * Formata o valor BigDecimal em duas casas decimais, aproximando o resultado. 
	 * @param value
	 * @return
	 */
	public static String formatTo2Decimals(BigDecimal value) {
		return String.valueOf(round(notNullValue(value), 2));
	}

	/**
	 * Método responsável por converter BigDecimal para String no formato(R$ 0.00)
	 */
	public static String parseToMoney(BigDecimal value) {
		try{
			if(value == null){
				return null;
			}
			
			NumberFormat formatter = new DecimalFormat("###,###,##0.00");
		
			return Currency.getInstance(new Locale("pt", "BR")).getSymbol() + formatter.format(value);
		}catch(NumberFormatException e){
			return null;
		}
	}
	
	
	/** Verifica se o primeiro valor é menor que o segundo
	 * 
	 * @param value1
	 * @param value2
	 * @return true value1 for menor que value2; false caso contrário
	 */
	public static boolean isLessThan(BigDecimal value1, BigDecimal value2) {
		return value1.compareTo(value2) < 0;
	}

	/** Verifica se o primeiro valor é maior que o segundo
	 * 
	 * @param value1
	 * @param value2
	 * @return true value1 for maior que value2; false caso contrário
	 */
	public static boolean isGreaterThan(BigDecimal value1, BigDecimal value2) {
		return value1.compareTo(value2) > 0;
	}
}
