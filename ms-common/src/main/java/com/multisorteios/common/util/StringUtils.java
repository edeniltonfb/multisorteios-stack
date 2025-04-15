package com.multisorteios.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.MaskFormatter;

import org.apache.commons.lang3.RandomStringUtils;

import com.multisorteios.common.exception.BusinessException;


public class StringUtils {

	private static final String DEFAULT_ENC_URLENCODE = "UTF-8";

	public static final String CPF_MASK = "###.###.###-##";
	public static final String CNPJ_MASK = "##.###.###/####-##";
	public static final String NIS_MASK = "###########-##";
	/**
	 * Variavel vazia;
	 */
	public static final String EMPTY = ""; 
	public static final Pattern PATTERN_LETRAS = Pattern.compile("[^a-zA-Z]");
	public static final Pattern PATTERN_NEG_LETRAS_NUMEROS = Pattern.compile("[^a-zA-Z0-9]");

	public static final String FORMATO_MOEDA_TEXTO = "##,###,###,##0.00";

	/** xxx.xxx,00 */
	public static final int FORMATO_MOEDA = 1;
	/** xxxxxxxxx */
	public static final int FORMATO_NUMERO_INTEIRO = 6;
	/** xxx.xxx,0000 */
	public static final int FORMATO_MOEDA_COMPLETA = 7;
	public static final String SPACE = " ";

	/**
	 * Utiliza o método Trim() da Classe String, porém verificando se a String está Nula, evitando NullPointers;
	 * @param text
	 * @return
	 */
	public static String trim(String text){
		if(text == null){
			return null;
		}

		return text.trim();
	}

	/**
	 * Verifica se a String é nula, vazia ou preenchida por espaços.
	 *
	 * @demanda 
	 * @param text
	 * @return
	 */
	public static boolean isNullOrEmpty(String text){
		return (text == null || "".equals(text.trim()));
	}

	/**
	 * Trunca a string no tamanho informado, desde que a string seja maior que o tamanho informado.
	 *
	 * @demanda 
	 * @param text texto a ser truncado.
	 * @param length tamanho final da string.
	 * @return
	 */
	public static String truncate(String text, int length)
	{
		if(text == null) {
			return null;
		}

		if(length <= 0){
			return "";
		}

		if(text.trim().length() > length) {
			return text.trim().substring(0, length - 1);
		}

		return text;
	}

	/**
	 * Trunca a string no tamanho informado, desde que a string seja maior que o tamanho informado.
	 * Adiciona a String compl no final do resultado.
	 * O tamanho da String final é o tamanho da string truncada + compl.
	 * Só adiciona o complemento se o tamanho da string text for menor que o length.
	 * @param text
	 * @param length
	 * @param compl
	 * @return
	 */
	public static String truncate(String text, int length, String compl)
	{
		if(text == null) {
			return null;
		}

		if(StringUtils.isNullOrEmpty(compl)){
			compl = EMPTY;
		}

		if(length == 0){
			return compl;
		}

		if(text.trim().length() > length) {
			String aux = text.trim().substring(0, length - 1);			
			return aux + compl;			
		}

		return text;		
	}


	@Deprecated
	//FIXME: Este método não deveria estar no StringUtils.
	/**
	 * Verifica se um número é nulo ou zero. 
	 * Utilizar o IntegerUtils.isNullOrZero.
	 *
	 * @demanda 
	 * @param number
	 * @return
	 */
	private static boolean isEmpty(Integer number){
		return (number == null || number.equals(0));
	}

	/**
	 * Verifica se uma lista de objetos é nula ou vazia.
	 * Utilizar CollectionsUtils.isNullOrEmpty
	 * @demanda 
	 * @param list
	 * @return
	 */
	@Deprecated
	private static boolean isEmpty(List<Object> list){
		return (list == null || list.isEmpty());
	}

	@SuppressWarnings("unchecked")
	//FIXME: Está no lugar errado. Deveria estar no ObjectUtils.	
	public static boolean isEmpty(Object value) {
		if(value == null){
			return true;
		} else if (value instanceof String) {
			return isNullOrEmpty((String ) value);
		} else if (value instanceof Integer) {
			return isEmpty((Integer ) value);
		} else if (value instanceof List) {
			return isEmpty((List<Object> ) value);
		}
		return false;
	}

	//FIXME: Está no lugar errado. Deveria estar no ObjectUtils.
	public static boolean isNotNull(Object... itens){
		for(Object obj : itens){
			if(obj == null){
				return false;
			}
		}
		return true;
	}

	//FIXME: Está no lugar errado. Deveria estar no ObjectUtils.
	public static Object notNullValue(Object value) {
		if(value instanceof Integer){
			return notNullValue((Integer) value);
		} else if(value instanceof String){
			return notNullValue((String) value);
		} else{
			return value;
		}
	}

	//FIXME: Está no lugar errado. Deveria estar no DoubleValue.
	public static Double notNullValue(Double value) {
		if(value == null){
			return 0.0;
		}
		return value;
	}

	/**
	 * Garante que uma string seja convertida para string vazia caso seja nula.
	 *
	 * @demanda 
	 * @param value String a ser garantida.
	 * @return Caso a string seja nula, retorna string vazia. Caso contrário, a própria string.
	 */
	public static String notNullValue(String value) {
		return notNullValue(value,EMPTY);
	}

	/**
	 * Garante que uma string seja convertida para string vazia caso seja nula.
	 * 
	 * @param value Valor a ser verificado.
	 * @param defaultValue valor padrão para ser utilizado, em caso de nulo.
	 * @return
	 */
	public static String notNullValue(String value, String defaultValue) {
		if(defaultValue == null){
			defaultValue = EMPTY;
		}

		if(value == null){
			return defaultValue;
		}
		return value;
	}


	/**
	 * Garante que uma string seja convertida para string vazia caso seja nula.
	 * 
	 * @param value Valor a ser verificado.
	 * @param defaultValue valor padrão para ser utilizado, em caso de nulo.
	 * @return
	 */
	public static String notNullAndNotEmptyValue(String value, String defaultValue) {
		if(defaultValue == null){
			throw new BusinessException("Parâmetro inválido para o métodos notNullAndNotEmptyValue");
		}

		if(isNullOrEmpty(value)){
			return defaultValue;
		}
		return value;
	}

	/**
	 * Utilizar o IntegerUtils.notNullValue
	 *
	 * @demanda 
	 * @param value
	 * @return
	 */
	@Deprecated
	public static Integer notNullValue(Integer value) {
		if(value == null){
			return 0;
		}
		return value;
	}

	/**
	 * Remove pontos (.), traços (-), barras(/) e vírgulas (,) de uma string.
	 *
	 * @demanda 
	 * @param text
	 * @return
	 */
	public static String removeSigns(String text) {
		if(text == null){
			return null;
		}
		text = text.replace(".", "");
		text = text.replace("-", "");
		text = text.replace("/", "");
		text = text.replace(",", "");
		text = text.replace("(", "");
		text = text.replace(")", "");
		text = text.replace(" ", "");
		return text.trim();
	}

	/**
	 * Retorna apenas os dígitos de uma string.
	 * @demanda 
	 * @param text
	 * @return
	 */
	public static String onlyDigits(String text) {
		if(text == null){
			return null;
		}
		return text.replaceAll("\\D", "").trim();
	}

	/**
	 * Aplica máscara em uma string utilizando o MaskFormatter.
	 *
	 * @demanda 
	 * @param text
	 * @param mask
	 * @return
	 */
	public static String applyMask(String text, String mask) {
		if(text == null || text.trim().equals("")){
			return text;
		}
		try{
			MaskFormatter mf = new MaskFormatter(mask);
			mf.setValueContainsLiteralCharacters(false);
			return mf.valueToString(text);
		} catch (ParseException e) {
			return text;
		}
	}

	/**
	 * Aplica máscara em um número Double utilizando o MaskFormatter.
	 *
	 * @demanda 
	 * @param value
	 * @param mask
	 * @return
	 */
	//FIXME: Deveria estar no DoubleUtils
	public static String applyMask(Double value, String mask) {
		try{
			return new DecimalFormat(mask).format(value);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static String applyMoneyMask(BigDecimal valorBigDecimal, boolean showCurrencySymbol) {

		// Configura os símbolos para o padrão brasileiro
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols(new Locale("pt", "BR"));
		simbolos.setCurrencySymbol("R$"); // Define o símbolo da moeda
		simbolos.setDecimalSeparator(',');
		simbolos.setGroupingSeparator('.');

		// Cria o DecimalFormat
		DecimalFormat formatoMoeda;
		if(showCurrencySymbol) {
			formatoMoeda = new DecimalFormat("¤ ###,##0.00", simbolos);
		}else {
			formatoMoeda = new DecimalFormat("###,##0.00", simbolos);
		}

		return formatoMoeda.format(valorBigDecimal);
	}

	/**
	 * Completa a String com zeros a esquerda.
	 * @param obj
	 * 			O objeto que vai ser completado.
	 * @param quant
	 * 			Tamanho total da String.
	 * @return
	 * 			A String do tamanho solicitado com zeros a esquerda
	 */
	public static String completeZerosEsquerda(Object obj, int quant){
		String texto = null;
		if (obj != null){
			if (obj instanceof Double) {
				texto = obj.toString();
				StringTokenizer numero = new StringTokenizer(texto,".");
				String inteiro = numero.nextToken();
				String decimal = numero.nextToken();
				if (decimal.length() == 1){
					decimal = decimal + "0";
				}
				texto = inteiro + decimal;
			}else{
				texto = obj.toString();
			}
		}else{
			texto = "";
		}
		int quantComplemento = 0;
		quantComplemento = quant - texto.length();
		StringBuilder textoComplemento = new StringBuilder("");
		for (int i = 0; i < quantComplemento; i++) {
			textoComplemento.append("0");
		}
		textoComplemento.append(texto);
		return textoComplemento.toString();
	}


	/**
	 * Completar zeros à esquerda.
	 * @param str String a ser completada.
	 * @param texto Texto a ser adicionado.
	 * @param quant Quantidade máxima da String no final.
	 * @return
	 */
	public static String completaEsquerda(String str, String texto, int quant) {
		int quantComplemento = 0;
		quantComplemento = quant - str.length();
		StringBuilder textoComplemento = new StringBuilder("");
		for (int i = 0; i < quantComplemento; i++) {
			textoComplemento.append(texto);
		}
		textoComplemento.append(str);
		return textoComplemento.toString();
	}

	public static String completaDireita(String str, String s, int quant) {
		int quantComplemento = 0;
		quantComplemento = quant - str.length();
		StringBuilder textoComplemento = new StringBuilder(str);
		for (int i = 0; i < quantComplemento; i++) {
			textoComplemento.append(s);
		}
		return textoComplemento.toString();
	}


	/**
	 * Retorna a localiza��o do Brasil dentro da API Java.
	 * 
	 * @return Um objeto da classe java.util.Locale .
	 */
	public static Locale localizacaoBrasil() {
		Locale retorno = new Locale("pt", "BR");
		return retorno;
	}

	/**
	 * Converte um objeto Double que cont�m um valor decimal para uma
	 * string. Este m�todo ir� formatar de acordo com o formato desejado.
	 * 
	 * @param valor
	 *            O objeto Double com o valor decimal a ser convertida.
	 * @param formato
	 *            O formato desejado, Ex:FORMATO_MOEDA_COMPLETA.
	 * @return O valor decimal convertido em string no formato desejado.
	 * @throws Exception
	 */
	public static String converterDoubleParaString(Double valor, int formato) throws Exception {
		String retorno = null;
		NumberFormat nFmt = NumberFormat.getNumberInstance(localizacaoBrasil());
		nFmt.setGroupingUsed(true);//para permitir xxx.xxx,00
		if (formato == FORMATO_MOEDA) {
			nFmt.setMinimumFractionDigits(2);
			nFmt.setMaximumFractionDigits(2);
			nFmt.setMinimumIntegerDigits(1);
			nFmt.setMaximumIntegerDigits(15);
		} else if (formato == FORMATO_NUMERO_INTEIRO) {
			nFmt.setMinimumFractionDigits(0);
			nFmt.setMaximumFractionDigits(0);
			nFmt.setMinimumIntegerDigits(1);
			nFmt.setMaximumIntegerDigits(9);
			nFmt.setGroupingUsed(false);
		} else if (formato == FORMATO_MOEDA_COMPLETA) {
			nFmt.setMinimumFractionDigits(2);
			nFmt.setMaximumFractionDigits(4);
			nFmt.setMinimumIntegerDigits(1);
			nFmt.setMaximumIntegerDigits(15);
		}
		retorno = nFmt.format(valor.doubleValue());
		return retorno;
	}

	//FIXME: Deveria estar no IntegerUtils.
	public static boolean hasAnyEmpty(Integer ... values) {
		for(Integer value : values){
			if(isEmpty(value)){
				return true;
			}
		}
		return false;
	}

	public static boolean hasAnyEmpty(String ... values) {
		for(String value : values){
			if(isNullOrEmpty(value)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Aplica um algor�tmo de Hash MD5 a partir de um array de string
	 * @param args - Array de String a partir do qual se deseja calcular o hash MD5.
	 * @return - Os 32 primeiros caracteres do resultado do c�lculo do Hash MD5. 
	 */
	public static String getMd5(String[] args) {
		String concat = "";

		for (int i = 0; i < args.length; i++) {

			concat += args[i];
		}

		MessageDigest md = null;

		try {

			md = MessageDigest.getInstance("MD5");		

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}

		BigInteger hash = new BigInteger(1, md.digest(concat.getBytes()));
		String result = hash.toString(16).toUpperCase();

		if(result.length() > 32){

			result = result.substring(0, 32);
		}

		return result;
	}

	public static String applyHashMD5(String text) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(text.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException | NullPointerException e ) {
		}
		return null;
	}

	/**
	 * Conta quantas vezes a chave aparece na palavra
	 * @param palavra - string onde sera efetuada a contagem
	 * @param chave - caracter ou string que define a chave da busca
	 * @return Quantidade de vezes que a chave aparece na palavra
	 */
	public static int countWords(String palavra, String chave){
		Pattern procuraPattern = Pattern.compile(chave);
		Matcher palavraMatcher = procuraPattern.matcher(palavra);
		int total = 0;
		while (palavraMatcher.find()) {
			total++;
		}
		return total;
	}



	public static String removeAccents(String str) {
		String input = (str==null)?"":str.trim();
		input = Normalizer.normalize(input, Normalizer.Form.NFD);  
		input = input.replaceAll("[^\\p{ASCII}]", "");  
		return input;
	}

	public static String removeAccentsAndSpaces(String str) {
		return removeAccents(str).replace(" ", "");
	}

	public static String addSpaces(String valor, int qtd) {
		StringBuilder stringBuilder = new StringBuilder(valor);
		if(valor==null){
			return null;
		}
		for(int i= 0;i<qtd;i++){
			stringBuilder.append(SPACE);
		}

		return stringBuilder.toString();
	}

	public static String convertToBase64(String text) throws UnsupportedEncodingException {
		String strBase64 = Base64.byteArrayToBase64(text.getBytes("UTF-8"));		
		return strBase64;
	}


	public static String convertFromBase64(String strBase64) throws IOException {
		String str = new String(Base64.base64ToByteArray(strBase64), "UTF-8");
		return str;
	}

	public static boolean isValidCPF(String cpf) {
		if(cpf == null){
			return false;
		}
		cpf = removeSigns(cpf);
		if(cpf.length() != 11){
			return false;
		}

		//[RDM #4411] considera-se erro CPF's formados por uma sequencia de numeros iguais
		if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") || cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555") || cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888") || cpf.equals("99999999999")) {
			return false;
		}

		int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
		try{
			Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
			Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
			return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
		}catch (NumberFormatException e){
			return false;
		}
	}

	public static boolean isValidCNPJCPF(String cnpjCpf, Integer tipoPessoa) {
		if(tipoPessoa == 4){
			return isValidCNPJ(cnpjCpf);
		}else if(tipoPessoa == 5){
			return isValidCPF(cnpjCpf);
		}else{
			return false;
		}
	}

	public static boolean isValidCNPJCPF(String cnpjCpf) {
		return isValidCNPJ(cnpjCpf) || isValidCPF(cnpjCpf);
	}

	public static boolean isValidCNPJ(String cnpj) {
		if(cnpj == null){
			return false;
		}

		cnpj = removeSigns(cnpj);

		final int cnpjLength = 14;		
		if (cnpj.length() != cnpjLength) {
			return false;
		}

		if ("00000000000000".equals(cnpj)) {
			return false;
		}

		if ("11111111111111".equals(cnpj)) {
			return false;
		}

		if ("22222222222222".equals(cnpj)) {
			return false;
		}

		if ("33333333333333".equals(cnpj)) {
			return false;
		}

		if ("44444444444444".equals(cnpj)) {
			return false;
		}

		if ("55555555555555".equals(cnpj)) {
			return false;
		}

		if ("66666666666666".equals(cnpj)) {
			return false;
		}

		if ("77777777777777".equals(cnpj)) {
			return false;
		}

		if ("88888888888888".equals(cnpj)) {
			return false;
		}

		if ("99999999999999".equals(cnpj)) {
			return false;
		}

		final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
		try{
			String parteSemDigito = cnpj.substring(0,12);
			Integer digito1 = calcularDigito(parteSemDigito, pesoCNPJ);
			Integer digito2 = calcularDigito(parteSemDigito + digito1, pesoCNPJ);
			return cnpj.equals(parteSemDigito + digito1.toString() + digito2.toString());
		}catch (NumberFormatException e){
			return false;
		}
	}

	private static int calcularDigito(String str, int[] peso) throws NumberFormatException {
		int soma = 0;
		for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
			digito = Integer.parseInt(str.substring(indice,indice+1));
			soma += digito*peso[peso.length-str.length()+indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}


	public static String formatarCnpjCpf(String cnpjCpf){

		if(isNullOrEmpty(cnpjCpf)){
			return null;
		}

		String value = removeSigns(cnpjCpf);
		try{
			if(value.length() == 11){
				return applyMask(value,CPF_MASK);
			}else if (value.length() == 14){
				return applyMask(value, CNPJ_MASK);
			}
		}catch(NumberFormatException e){
			return cnpjCpf;
		}catch (Exception e) {
			e.printStackTrace();
		}

		return cnpjCpf;
	}

	public static final String removeTudoExcetoLetras(String str) {
		Matcher m = PATTERN_LETRAS.matcher(str);
		return m.replaceAll("");
	}

	public static final String getStringAlfanumerica(String str) {
		Matcher m = PATTERN_NEG_LETRAS_NUMEROS.matcher(str);
		return m.replaceAll("");
	}


	public static List<String> quebrarTextoPorTamanho(String texto, int tamanho){

		if(isNullOrEmpty(texto) || tamanho <= 0){
			return null;
		}

		List<String> result = new ArrayList<>();
		while(texto.length() >= tamanho){
			result.add(texto.substring(0, tamanho));
			texto = texto.substring(tamanho);
		}

		if(texto.length() > 0){
			result.add(texto);
		}

		return result;

	}

	public static String applyCEPMask(Integer cep) {

		if(IntegerUtils.isNullOrZero(cep)){
			return "";
		}
		try{
			MaskFormatter mf = new MaskFormatter("##.###-###");
			mf.setValueContainsLiteralCharacters(false);
			return mf.valueToString(cep);
		} catch (ParseException e) {
			return "";
		}
	}



	/**
	 * Repete uma string N vezes.
	 * ex: repeatStr("x", 3) retorna: "xxx"
	 * 
	 * @param str
	 * @param times
	 * @return
	 */
	public static String repeatStr(String str, int times) {
		StringBuilder sb = new StringBuilder();
		for (int x = 0; x < times; x++) {
			sb.append(str);
		}
		return sb.toString();
	}


	/**
	 * Repete uma string N vezes com um separador especifico no meio.
	 * ex: repeatStr("?", ",") retorna: "?,?,?"
	 * 
	 * @param str
	 * @param times
	 * @param sep
	 * @return
	 */
	public static String repeatStr(String str, int times, String sep) {
		StringBuilder sb = new StringBuilder();
		for (int x = 0; x < times; x++) {
			if (x != 0) {
				sb.append(sep);
			}
			sb.append(str);
		}
		return sb.toString();
	}

	public static List<String> convertToListStrings(List<?> oldList) {
		List<String> newList = new ArrayList<String>(oldList.size());
		for (Object o : oldList) {
			newList.add(String.valueOf(o));
		}
		return newList;
	}

	/**
	 * testa se o valor passado por parâmetro é string vazia, retornando null em caso positivo
	 * @param text
	 * @return String
	 */

	public static String nullIfEmpty(String text) {
		if(isNullOrEmpty(text)){
			return null;
		}

		return text;
	}

	public static String emptyIfNull(String text) {
		if (text == null) {
			return "";
		}
		return text;
	}

	/**
	 * Valida se o email passada por parâmetro é um valor válido para  E-mails
	 * @param email
	 * @return true se o email for válido, false caso contrário
	 */
	public static boolean isEmailValid(String email) {
		if ((email == null) || (email.trim().length() == 0)){
			return false;
		}
		email = email.trim();

		String emailPattern = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
		Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * Remove as quebras de linha de um texto
	 * @param valor
	 * @return 
	 */
	public static String removeBreakLine(String text) {
		if(text == null){
			return null;
		}
		text = text.replaceAll("\n", " ");
		text = text.replaceAll("\r", "");
		text = text.replaceAll("\t", "");

		return text;
	}



	/**
	 * Função similar à join do python. Junta numa string, cada elemento de uma
	 * lista usando um separador específico.
	 * 
	 * Ignora pedaços nulos ou strings vazias.
	 * ex lista = [1, 2, 3], sep = "+", retorna string "1+2+3"
	 * 
	 * @param col lista
	 * @param sep separador
	 * @return string
	 */
	public static String join(Collection<?> col, String sep) {
		StringBuilder sb = new StringBuilder();
		for (Object o : col) {
			if(o != null){
				if (o instanceof String && StringUtils.isNullOrEmpty(o.toString())) {
					continue;
				}
				sb.append(sep).append(o.toString());
			}
		}
		String str = sb.toString();
		if (!str.isEmpty()) {
			str = str.substring(sep.length());
		}
		return str;
	}

	/***
	 * Concatena valores String separando-os com o separador informado.
	 * Ignora pedaços vazios ou nulos da String.
	 * @param separator Separador
	 * @param pieces Pedaços da String a ser concatenados.
	 * @return Valor concatenado.
	 */
	public static String join(String separator, String... pieces){
		StringBuilder sb = new StringBuilder();
		for (String o : pieces) {
			if(!StringUtils.isNullOrEmpty(o)){
				sb.append(separator).append(o.toString());
			}
		}
		String str = sb.toString();
		if (!str.isEmpty()) {
			str = str.substring(separator.length());
		}
		return str;
	}


	/**
	 * Igual String.split, mas retorna uma lista ao invés de array.
	 * Executa um trim() em cada string.
	 * @param str String a ser quebrada
	 * @param sep Separador para quebrar a string
	 * @return Lista com cada pedaço em uma posição.
	 */
	public static List<String> splitToList(String str, String sep) {
		String[] arr = str.split(Pattern.quote(sep));
		List<String> list = new ArrayList<>(arr.length);
		for (String string : arr) {
			list.add(string.trim());
		}
		return list;
	}


	public static String getUrlEncode(String str, String encoding) {
		try {
			if (str == null) {
				return null;
			}
			return URLEncoder.encode(str, encoding);
		} catch (UnsupportedEncodingException e) {
			return str;
		}
	}

	public static String getUrlEncode(String str) {
		return getUrlEncode(str, DEFAULT_ENC_URLENCODE);
	}

	public static String toStringOrEmpty(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString();
	}

	/***
	 * Verifica se duas strings são iguais.
	 * Se as duas forem nulas, retorna true.
	 * 
	 * @param each
	 * @param other
	 * @return
	 */
	public static boolean equals(String each, String other) {
		//(Edenilton) Momdificado devido ao PMD
		//Se ambos forem nulo, então são iguais
		if (each == null && other == null) {
			return true;
		}

		//Se somente um deles for nulo, então são diferentes
		if (each == null ^ other == null) {
			return false;
		}

		//Se nenhum dos dois for nulo, compara com equals
		return each.equals(other);
	}


	/**
	 * Verifica se duas strings são iguais sem considerar caixa.
	 * Se as Strings forem nulas, retorna true.
	 * 
	 * NullSafe
	 * 
	 * @param each primeira String a ser comparada.
	 * @param other segunda String a ser comparada.
	 * @return
	 */
	public static boolean equalsIgnoreCase(String each, String other) {

		if (each == null && other == null) {
			return true;
		}

		// Se somente um deles for nulo, então são diferentes
		if (each == null ^ other == null) {
			return false;
		}

		// Se nenhum dos dois for nulo, compara com equals
		return each.toUpperCase().equals(other.toUpperCase());
	}




	/**
	 * Verifica se uma string em "each" contém o valor definido em "other".
	 * Se uma das strings forem nulas, retorna falso. 
	 * 
	 *  NullSafe.
	 *  
	 * @param each String alvo.
	 * @param other String a ser pesquisada.
	 * @return
	 */
	public static boolean containsIgnoreCase(String each, String other) {	

		if (each == null && other == null) {
			return false;
		}

		// Se somente um deles for nulo, então são diferentes
		if (each == null ^ other == null) {
			return false;
		}

		//Se nenhum dos dois for nulo, compara com equals
		return each.toUpperCase().contains(other.toUpperCase());
	}

	/**
	 * Compara se duas strings são iguais, ignorando os espaços vazios no início e no fim.	 
	 * 
	 * @param each primeira string a ser comparada.
	 * @param other segunda string a ser comparada.
	 * @return
	 */
	public static boolean equalsWithTrim(String each, String other) {
		//(Edenilton) Momdificado devido ao PMD
		//Se ambos forem nulo, então são iguais
		if (each == null && other == null) {
			return true;
		}

		//Se somente um deles for nulo, então são diferentes
		if (each == null ^ other == null) {
			return false;
		}

		//Se nenhum dos dois for nulo, compara com equals
		return each.trim().equals(other.trim());
	}
	/**
	 * Coloca um prefixo e um sufixo em um string caso sela seja não nula e não
	 * vazia; retorna string em branco caso contrário.
	 * EX: surroundIfExists("ola", "(", ")") retorna: "(ola)"
	 * 
	 * @param value string
	 * @param prefix prefixo
	 * @param value sufix
	 * @return
	 */
	public static String surroundIfExists(String value, String prefix, String sufix) {
		if (prefix == null) {
			prefix = "";
		}
		if (sufix == null) {
			sufix = "";
		}
		if (!StringUtils.isNullOrEmpty(value)) {
			return prefix + value + sufix;
		}
		return "";
	}

	public static String formatDoubleWithoutScientifcNotation(Double valor){
		DecimalFormat df = new DecimalFormat("#");
		df.setMaximumFractionDigits(2);
		return df.format(valor);
	}

	public static String normalize(String textToNormalize) {
		textToNormalize = textToNormalize.replaceAll("[\\t\\n\\r ]+", " ").toUpperCase();		
		textToNormalize = removeAccents(textToNormalize);
		textToNormalize = textToNormalize.replaceAll("[^a-zA-Z0-9/\\-.,;:$?! ]+", "");
		textToNormalize = textToNormalize.trim();	
		return textToNormalize;
	}

	/**
	 * Converte um objeto Throwable para string, exibindo inclusive a pilha de
	 * chamada.
	 * 
	 * @param t Objeto throwable
	 * @return string contendo toda informação do throwable; ou string vazia
	 *         caso dê erro ao converter para string.
	 */
	public static String convertThrowableToString(Throwable t) {
		try {
			StringWriter sw = new StringWriter();
			t.printStackTrace(new PrintWriter(sw));
			return sw.toString();
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * Verifica a existencia das palavras "UPDATE, DELETE, INSERT, TRUNCATE e DROP" caso exista
	 * Emite uma mensagem para o usuario.
	 */
	public static boolean hasWord(String palavra, String chave){
		Pattern procuraPattern = Pattern.compile(chave);
		Matcher palavraMatcher = procuraPattern.matcher(palavra);
		while (palavraMatcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * Realiza a Conversão de numero Inteiro para Horas, Minutos e Segundos, 
	 * sendo que esse Inteiro deve conter 5 ou 6 caracteres representando HH:MM:SS
	 */
	public static String convertIntegerToTime(String horaMinutoSegundo) {
		final int MIN_CARACTERES = 5;
		StringBuilder horaFormatada = new StringBuilder();

		if(!StringUtils.isNullOrEmpty(horaMinutoSegundo)){

			if (horaMinutoSegundo.length() == MIN_CARACTERES || horaMinutoSegundo.length() == 6) {

				if(horaMinutoSegundo.length() == MIN_CARACTERES) {
					horaMinutoSegundo = "0" + horaMinutoSegundo;
				}

				for (int x = 0; x < horaMinutoSegundo.length(); x++) {
					char n = horaMinutoSegundo.charAt(x);
					horaFormatada.append(n);

					if (x == 1 || x == 3) {
						horaFormatada.append(":");
					}
				}
			}
			return horaFormatada.toString();
		}
		else {
			return horaMinutoSegundo;
		}
	}

	/**
	 * Testa se é um caracter Alfanumerico.
	 * @param c
	 * 		Objeto da Classe String com o caracter a ser testado.
	 * @return
	 * 		Um boolean true se for Alfanumerico e false se não for.
	 */
	private static boolean testaAlfanumerico(String c){
		//testa se é Alfanumerico, ponto ou espaço
		//!@#$%&*()-_+-/=<>[]{};:?\., ^~'+ ''''''
		String especiais = "!@#$%&*()-_+-/=<>[]{};:?., ^~'+—|";
		if (c.matches("([0-9]|[A-Za-z])") || especiais.contains(c)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Testa se o caracter com acento pode ser substituido por outro sem acento
	 * @param c
	 * 		Objeto da Classe String com o caracter a ser testado.
	 * @return
	 * 		Objeto da Classe String com o caracter substituido.
	 * @throws Exception
	 */
	private static String tiraAcentoCaracteres(String c) throws Exception{
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		if (map.containsKey(c)){
			map.put(c, (map.get(c) + 1));
		}else{
			map.put(c, new Integer(1));
		}
		if (c.equals("Ç")){
			return "C";
		}
		if (c.equals("ç")){
			return "c";
		}
		if (c.equals("Ã") || c.equals("Á") || c.equals("À") || c.equals("Â") || c.equals("Ä") || c.equals("Å")){
			return "A";
		}
		if (c.equals("ã") || c.equals("â") || c.equals("à") || c.equals("á") || c.equals("ä") || c.equals("å")){
			return "a";
		}
		if (c.equals("É") || c.equals("Ê") || c.equals("È") || c.equals("Ë")){
			return "E";
		}
		if (c.equals("ë") || c.equals("é") || c.equals("ê") || c.equals("è")){
			return "e";
		}
		if (c.equals("Í") || c.equals("Ì") || c.equals("Î") || c.equals("Ï")){
			return "I";
		}
		if (c.equals("ì") || c.equals("í") || c.equals("î") || c.equals("ï")){
			return "i";
		}
		if (c.equals("Ó") || c.equals("Ô") || c.equals("Õ") || c.equals("Ò") || c.equals("Ö")){
			return "O";
		}
		if (c.equals("ò") || c.equals("ó") || c.equals("ô") || c.equals("õ") || c.equals("ö")){
			return "o";
		}
		if (c.equals("Ú") || c.equals("Ü") || c.equals("Ù")){
			return "U";
		}
		if (c.equals("ù") || c.equals("ú") || c.equals("ü")){
			return "u";
		}

		return " ";
	}

	/**
	 * Metodo responsavel por tratar caracteres com acentuação.
	 * 
	 * Alguns carateres é possivel fazer a converção.
	 * Os que não forem possivel são substituidos por espaços em branco. 
	 * 
	 * @param texto
	 * 			Objeto da classe String com o texto original.
	 * @return
	 * 			Objeto da classe String com o texto limpo.
	 * @throws Exception
	 */
	public static String limparAcentuacao(String texto) throws Exception{
		StringBuilder retorno = new StringBuilder();
		for (int i = 0; i < texto.length(); i++) {
			if (testaAlfanumerico(texto.substring(i,i+1))){
				retorno.append(texto.substring(i,i+1));
			}else{
				retorno.append(tiraAcentoCaracteres(texto.substring(i,i+1)).trim());
			}
		}

		return retorno.toString();
	}

	/**
	 * Verifica se um texto, removendo os sinais de pontuação e os espaços, contém apenas números
	 * @param nome
	 * @return
	 */
	public static boolean hasNumberOnly(String text) {
		if(isNullOrEmpty(text)){
			return false; //Caso o texto nulo ou vazio necessite ser invalidado, deve ser feita a lógica à parte
		}

		text = removeSigns(text).replace(" ", "");

		return text.matches("\\d*");
	}


	/**
	 * Quebra uma String em uma lista de Strings, de forma que cada item da lista seja limitado ao número máximo de caracteres especificado. 
	 * @param text
	 * @param pattern
	 * @param maxSize
	 * @return
	 */
	public static List<String> splitToList(String text, String pattern, int maxSize){
		if(StringUtils.isNullOrEmpty(text) || pattern == null || pattern.length() == 0 || maxSize <= 0){
			return null;
		}

		List<String> tokens = splitToList(text, pattern);
		List<String> result = new ArrayList<>();
		StringBuilder line = new StringBuilder();
		for (String token : tokens) {

			if ((line + token).length() >= maxSize) {
				result.add(line.toString());
				line = new StringBuilder();
			}
			line.append(token + pattern);
		}

		if (line.length() > 0) {
			result.add(line.toString());
		}

		return result;

	}

	/**
	 * Formata o número e o ddd de um telefone em uma única String
	 * @param ddd
	 * @param numero
	 * @return
	 */
	public static String formatTelefone(Integer ddd, Integer numero){

		if(IntegerUtils.isNullOrZero(numero)){
			return null;
		}

		String mask = "";
		String text = "";

		if(IntegerUtils.isNullOrZero(ddd)){

			if(numero > 99999999){
				mask = "#####-####";
			}else{
				mask = "####-####";
			}
			text = "" + numero;

		}else{
			if(numero > 99999999){
				mask = "(##)#####-####";
			}else{
				mask = "(##)####-####";
			}
			text = "" + ddd + numero;
		}

		return applyMask(text, mask);
	}

	/**
	 * Completa uma String com uma quantidade de espaços em branco
	 */
	public static String completarComEspaco(String valor, int quantidadeEspaco) {
		String str = null;

		if (valor != null) {
			str = String.format("%-" + quantidadeEspaco + "s", valor);
		}

		return str;
	}

	/**
	 * Verifica se o texto está no padrão sugerido através de expressão regular
	 * @param padrao
	 * @param texto
	 * @return
	 */
	public static boolean validarExpressaoRegular(String padrao, String texto){
		return Pattern.matches(padrao, texto);
	}


	public static final DecimalFormat MILHAO_MASK = new DecimalFormat("0000000");
	public static final DecimalFormat CENTENA_MILHAR_MASK = new DecimalFormat("000000");
	public static final DecimalFormat DEZENA_MILHAR_MASK = new DecimalFormat("00000");
	public static final DecimalFormat MILHAR_MASK = new DecimalFormat("0000");
	public static final DecimalFormat CENTENA_MASK = new DecimalFormat("000");
	public static final DecimalFormat DEZENA_MASK = new DecimalFormat("00");

	public static String formatMilhao(int numero) {
		return MILHAO_MASK.format(numero);
	}
	public static String formatCentenaMilhar(int numero) {
		return CENTENA_MILHAR_MASK.format(numero);
	}
	public static String formatDezenaMilhar(int numero) {
		return DEZENA_MILHAR_MASK.format(numero);
	}

	public static String formatMilhar(int numero) {
		return MILHAR_MASK.format(numero);
	}

	public static String formatCentena(int numero) {
		return CENTENA_MASK.format(numero);
	}

	public static String formatDezena(int numero) {
		return DEZENA_MASK.format(numero);
	}

	public static String generateStringId(int size) {
		return RandomStringUtils.randomAlphanumeric(size);
	}

	public static void main(String[] args) {
		System.out.println(generateStringId(16));
		System.out.println(gerarIniciaisNome("Edenilton froés Barbosa"));
	}

	public static String gerarIniciaisNome(String nome) {
		if(nome == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder("");
		String[] tokens = nome.split(" ");
		for(String token : tokens) {
			if(Arrays.asList("de", "do", "dos", "da", "das").contains(token.trim().toLowerCase())) {
				continue;
			}
			sb.append(token.trim().charAt(0)).append(".");
		}

		return sb.toString().toUpperCase();
	}

	public static String revertEmail(String email) {
		if(isEmailValid(email)) {
			String address = email.substring(0, email.indexOf("@"));
			return new StringBuilder(address).reverse().append(email.substring(email.indexOf("@"))).toString();
		}
		return null;
	}

	private static int randomiza(int n) {
		int ranNum = (int) (Math.random() * n);
		return ranNum;
	}

	private static int mod(int dividendo, int divisor) {
		return (int) Math.round(dividendo - (Math.floor(dividendo / divisor) * divisor));
	}

	public static String gerarCPF() {
		int n = 9;
		int n1 = randomiza(n);
		int n2 = randomiza(n);
		int n3 = randomiza(n);
		int n4 = randomiza(n);
		int n5 = randomiza(n);
		int n6 = randomiza(n);
		int n7 = randomiza(n);
		int n8 = randomiza(n);
		int n9 = randomiza(n);
		int d1 = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10;

		d1 = 11 - (mod(d1, 11));

		if (d1 >= 10) {
			d1 = 0;
		}

		int d2 = d1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11;

		d2 = 11 - (mod(d2, 11));

		String retorno = null;

		if (d2 >= 10) {
			d2 = 0;
		}

		retorno = "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + d1 + d2;

		return retorno;
	}

	public static String formatWhatsApp(String number) {

		if(isNullOrEmpty(number)) {
			return null;
		}

		number = onlyDigits(number);

		if(number.length() == 12 || number.length() == 13) {
			number = number.substring(2);
		}

		if(number.length() == 10) {
			if(number.startsWith("11")) { //acrescentar o nono dígito somente para o ddd 11
				return "559" + number.substring(2); 
			}else {
				return "55"+number;
			}
		}

		if(number.length() == 11) {
			if(number.startsWith("11")) { //manter no nono dígito somente para o ddd 11
				return "55"+number; 
			}else {
				return "55"+number.substring(0,2) + number.substring(3);
			}
		}

		return null;
	}

	public static String mascararCelular(String celular) {

		if(celular == null) {
			return "";
		}
		celular = removeSigns(celular);

		return  "("+celular.substring(0,2) + ") *****-****";
	}
}
