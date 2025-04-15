package com.multisorteios.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTime {
	/**yyyy-MM-dd*/
	public static final String MASCARA_DATA_DB = "yyyy-MM-dd";
	/**dd/MM/yyyy*/
	public static final String MASCARA_DATA_TELA = "dd/MM/yyyy";
	/**dd/MM/yyyy*/
	public static final String MASCARA_TIMESTAMP_TELA = "dd/MM/yyyy HH:mm:ss.SSS";
	/**yyyyMMddHHmmss*/
	public static final String MASCARA_DATA_REGISTRO = "yyyyMMddHHmmss";
	
	/**"yyyy-MM-dd HH:mm:ss.SSS"*/
	public static final String MASCARA_TIMESTAMP_DB = "yyyy-MM-dd HH:mm:ss.SSS";
	
	public static final String ddMM = "dd/MM";
	
	private static final String MASCARA_DATE_TO_MESDIA = "MMdd";
	
	public static final String MASCARA_DATAHORA_VIEW = "dd/MM/yyyy HH:mm:ss";

	/**HH:mm:ss.SSS*/
	public static final String MASCARA_TIMESTAMP_ONLY_TELA = "HH:mm:ss";
	
	public static String MASCARA_DATA_HORA_TELA = "dd/MM/yyyy HH:mm";
	
	/**00:00*/
	public static final String HORA_INICIAL_DIA = "00:00";
	
	/**23:59*/
	public static final String HORA_FINAL_DIA = "23:59";

	
	private Calendar calendar;
	
	//Constantes criadas para atender aos caprichos do PMD
	private static final int TAMANHO_PADRAO_REFERENCIA = 4;
	private static final int TAMANHO_ALTERANATIVO_REFERENCIA = 3;
	private static final int TAMANHO_CURTO_DIA_MES = 1;
	

	/***
	 * Calcula o hashCode do objeto.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((calendar == null) ? 0 : calendar.hashCode());
		return result;
	}

	/**
	 * Cria um DateTime, inicializado com a data atual.
	 */
	public DateTime() {
		calendar = Calendar.getInstance();
	}

	/**
	 * Cria um DateTime a partir de um Date.
	 * @param dt
	 */
	public DateTime(Date dt) {
		calendar = Calendar.getInstance();
		calendar.setTime(dt);
	}

	/***
	 * Cria um DateTime a partir de um Calendar.
	 * @param cal
	 */
	public DateTime(Calendar cal) {
		calendar = (Calendar) cal.clone();
	}

	/**
	 * Cria um DateTime a partir de um Unix Timestamp.
	 * @param date
	 */
	public DateTime(Long date) {
		calendar = Calendar.getInstance();
		calendar.setTime(new Date(date));
	}

	/**
	 * Construtor para construÃ§Ã£o a partir de valores inteiros de ano, mÃªs e dia.
	 * O padrÃ£o do mÃªs Ã© de 1 (janeiro) a 12 (dezembro)
	 */
	public DateTime(int year,int month,int day) {
		calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}
	/**
	 * Cria um DateTime a partir de um Timespan.
	 * @param timeSpan TimeSpan inicializador.
	 */
	public DateTime(TimeSpan timeSpan) {
		calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, timeSpan.getYears());
		calendar.set(Calendar.MONTH, timeSpan.getMonths());
		calendar.set(Calendar.DAY_OF_MONTH, timeSpan.getDays());
		calendar.set(Calendar.HOUR_OF_DAY, timeSpan.getHours());
		calendar.set(Calendar.MINUTE, timeSpan.getMinutes());
		calendar.set(Calendar.SECOND, timeSpan.getSeconds());
		calendar.set(Calendar.MILLISECOND, timeSpan.getMilliseconds());
	}
	
	
	
	/**
	 *
	 * @param Converte um Unix Timestamp para DateTime
	 * @return
	 */
	public static DateTime parse(Long time) {

		if(time == null){
			return null;
		}

		return new DateTime(time);

	}
	
	/**
	 *
	 * @param Converte um DateTime para Long (Unix Timestamp)
	 * @return
	 */
   public static Long toLong(DateTime dateTime) {

       if(dateTime == null){
           return null;
       }

		return dateTime.getTime().getTime();

   }
   
   /***
    * Retorna a data no formato YYYYMMDD inteiro.
    * @return
    */
   public int toIntInverse(){		
		Integer dia = this.getDay();
		Integer mes = this.getMonth();
		Integer ano = this.getYear();
		
		if (mes >= 10) {
			if (dia >= 10) {
				return Integer.parseInt(ano.toString() + mes.toString() + dia.toString());
			} else {
				return Integer.parseInt(ano.toString() + mes.toString() + "0" + dia.toString());
			}
		} else {
			if (dia >= 10) {
				return Integer.parseInt(ano.toString() + "0" + mes.toString() + dia.toString());
			} else {
				return Integer.parseInt(ano.toString() + "0" + mes.toString() + "0" + dia.toString());
			}
		}
		
   }
   
   
   /**
    * Converte um DateTime para String no formato informado.
    * @param dateTime
    * @param mask
    * @return
    */
	public static String toString(Date dateTime, String mask){
		return new DateTime(dateTime).toString(mask);
	}

	/**
	 * Retorna a um DateTime com a data atual.
	 * @return
	 */
	public static DateTime now() {
		return new DateTime();
	}

	/**
	 * Adiciona os milisegundos informados ao DateTime. Retorna um DateTime. 
	 * @param value Milisegundo
	 * @return
	 */
	public DateTime addMilliseconds(int value) {
		Calendar c = (Calendar) calendar.clone();
		c.add(Calendar.MILLISECOND, value);
		return new DateTime(c);
	}
	
	/**
	 * Adiciona os minutos informados ao DateTime. Retorna um DateTime. 
	 * @param value Minuto
	 * @return
	 */
	public DateTime addMinutes(int value) {
		Calendar c = (Calendar) calendar.clone();
		c.add(Calendar.MINUTE, value);
		return new DateTime(c);
	}

	/**
	 * Adiciona os segundos informados ao DateTime. Retorna um DateTime. 
	 * @param value Segundo
	 * @return DateTime com os segundos incrementados.
	 */
	public DateTime addSeconds(int value) {
		Calendar c = (Calendar) calendar.clone();
		c.add(Calendar.SECOND, value);
		return new DateTime(c);
	}
	
	/**
	 * Adiciona as horas informadas ao DateTime. Retorna um DateTime. 
	 * @param value Hora
	 * @return DateTime com os horas incrementadas.
	 */
	public DateTime addHours(int value) {
		Calendar c = (Calendar) calendar.clone();
		c.add(Calendar.HOUR, value);
		return new DateTime(c);
	}

	/**
	 * Adiciona os dias informados ao DateTime. Retorna um DateTime. 
	 * @param value Dia
	 * @return DateTime com os dias incrementados.
	 */
	public DateTime addDays(int value) {
		Calendar c = (Calendar) calendar.clone();
		c.add(Calendar.DAY_OF_MONTH, value);
		return new DateTime(c);
	}

	/**
	 * Adiciona os meses informados ao DateTime. Retorna um DateTime. 
	 * @param value MÃªs
	 * @return DateTime com os meses incrementados.
	 */
	public DateTime addMonths(int value) {
		Calendar c = (Calendar) calendar.clone();
		c.add(Calendar.MONTH, value);
		return new DateTime(c);

	}

	/**
	 * Adiciona os anos informados ao DateTime. Retorna um DateTime. 
	 * @param value Ano
	 * @return DateTime com os anos incrementados.
	 */
	public DateTime addYears(int value) {

		Calendar c = (Calendar) calendar.clone();
		c.add(Calendar.YEAR, value);

		return new DateTime(c);
	}
	
	/***
	 * Valor mÃ¡ximo do DateTime 9999-12-31 23:59:59.999.
	 */
	public static final DateTime MAX_VALUE = DateTime.max();
	
	/***
	 * Valor mÃ¡ximo do DateTime em formato UnixTimestamp.
	 */
	public static final long MAX_LONG_VALUE = DateTime.max().getTime().getTime();
	
	/***
	 * Valor mÃ¡ximo do DateTime 0001-01-01 00:00:00.000.
	 */
	public static final DateTime MIN_VALUE = DateTime.min();

	/***
	 * Valor mÃ­nimo do DateTime em formato Unix Timestamp.
	 */
	public static final long MIN_LONG_VALUE = DateTime.min().getTime().getTime();
	
	
	/***
	 * Valor mÃ¡ximo do DateTime. 9999-12-31 23:59:59.999
	 * @return
	 */
	public static DateTime max() 
	{
		DateTime dt =null;
		
		try {
			dt = DateTime.parse("9999-12-31 23:59:59.999");
		} catch (ParseException e) {
			
		}
		
		return dt;
	}
	/**
	 * Valor mÃ­nimo do DateTime. 0001-01-01 00:00:00.000 
	 * @return
	 */
	public static DateTime min() 
	{
		DateTime dt =null;
		
		try {
			dt = DateTime.parse("0001-01-01 00:00:00.000");
		} catch (ParseException e) {
			
		}
		
		return dt;
	}
	
	
	/**
	 * Verifica se a String informada representa um DateTime com o valor mÃ­nimo. 
	 * @param dateTime
	 * @return
	 */
	public static boolean isMinValue(String dateTime)
	{
		try {
			return isMinValue(DateTime.parse(dateTime));
		} catch (ParseException e) {
			return false;
		}
	}
	
	/**
	 * Verifica se o DateTime informado Ã© o valor mÃ­nimo.
	 * Se informaÃ§Ã£o de hora nÃ£o for informada, o sistema retorna o valor mÃ­nimo em Data (desconsiderando Hora).s
	 * @param dateTime
	 * @return
	 */
	public static boolean isMinValue(DateTime dateTime)
	{
		if(dateTime == null) {
			return false;
		}
		
		if(dateTime.getHour() == 0 && dateTime.getMinute() == 0 && dateTime.getSecond() == 0 && dateTime.getMillisecond() == 0)
		{
			return dateTime.getDate().equals(MIN_VALUE.getDate());
		}
				 
		return dateTime.equals(MIN_VALUE);		 
	}
	
	
	/**
	 * Verifica se a String informada representa um DateTime.max().
	 * @param dateTime Data a ser verificada.
	 * @return
	 */
	public static boolean isMaxValue(String dateTime)
	{
		try {
			return isMaxValue(DateTime.parse(dateTime));
		} catch (ParseException e) {
			return false;
		}
	}
	
	
	/**
	 * Verifica se o DateTime informado Ã© igual ao DateTime.max()
	 * @param dateTime DateTime a ser verificado. Se os valores de hora/minuto/segundo forem zero, o sistema compara data com data. 
	 * @return
	 */
	public static boolean isMaxValue(DateTime dateTime)
	{
		if(dateTime == null) {
			return false;
		}
		
		if(dateTime.getHour() == 0 && dateTime.getMinute() == 0 && dateTime.getSecond() == 0) {
			return dateTime.getDate().equals(max().getDate());
		}
				 
		return dateTime.equals(MAX_VALUE);	
	}
	
	/**
	 * Verifica se o DateTime informado Ã© igual ao DateTime.max()
	 * @param dateTime DateTime a ser verificado. Se os valores de hora/minuto/segundo forem zero, o sistema compara data com data. 
	 * @return
	 */
	public static boolean isMaxValue(Date date) {
		if(date == null){
			return false;
		}
		return isMaxValue(new DateTime(date));
	}


	/**
	 * Dia da semana.
	 * 
	 * @return
	 */
	public DayOfWeek getDayOfWeek() {
		return DayOfWeek.fromInt(calendar.get(Calendar.DAY_OF_WEEK) - 1);
	}

	/**
	 * Retorna a data atual em java.util.Date
	 * 
	 * @return
	 */
	public Date getTime() {
		return calendar.getTime();
	}

	/**
	 * Retorna o calendÃ¡rio com a Data atual.
	 * 
	 * @return
	 */
	public Calendar getCalendar() {
		return calendar;
	}

	/**
	 * Retorna a data zerando as horas, minutos, segundos e milisegundos.
	 * 
	 * @return
	 */
	public DateTime getDate() {

		Calendar c = (Calendar) calendar.clone();

		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		return new DateTime(c);
	}

	/**
	 * Retorna os miliseconds da data.
	 * 
	 * @return
	 */
	public int getMillisecond() {
		return calendar.get(Calendar.MILLISECOND);
	}

	/**
	 * Retorna os segundos.
	 * 
	 * @return
	 */
	public int getSecond() {
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * Retorna o minuto.
	 * 
	 * @return
	 */
	public int getMinute() {
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * Retorna a Hora.
	 * 
	 * @return
	 */
	public int getHour() {
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * Retorna o dia, comeÃ§ando de 1.
	 * 
	 * @return
	 */
	public int getDay() {
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Retorna o mÃªs da data, comeÃ§ando de 1 atÃ© 12.
	 */
	public int getMonth() {
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * Retorna o ano da data.
	 * 
	 * @return
	 */
	public int getYear() {
		return calendar.get(Calendar.YEAR);
	}

	@Override
	public String toString() {
		return toString("dd/MM/yyyy HH:mm:ss");
	}

	/**
	 * Retorna data no formato informado.
	 * */
	public String toString(String pattern) {
		return new SimpleDateFormat(pattern).format(calendar.getTime());
	}
	
	
	/**
	 * Converte uma String no formato(dd/MM/yyyy) para Date
	 * 
	 * @param data do tipo String
	 * @return Date
	 * @throws ParseException
	 */
	
	public static Date toDate(String data,String pattern) throws ParseException {
		return new SimpleDateFormat(pattern).parse(data);
	}
	

	/**
	 * Converte a data nos formatos dd/MM/yyyy e yyyy-MM-dd
	 * 
	 * @param dateTime
	 * @return
	 * @throws ParseException
	 */
	public static DateTime parse(String dateTime) throws ParseException {
		String format = "";
		if (dateTime.contains("/")) {
			format += "dd/MM/yyyy";
		} else {
			format += "yyyy-MM-dd";
		}

		if (dateTime.contains(":")) {
			format += " HH:mm:ss";
		}

		Date date = new SimpleDateFormat(format).parse(dateTime);
		return new DateTime(date);
	}
	
	/**
	 * Converte a data no formato passado por paÃ¢metro
	 * 
	 * @param dateTime
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static DateTime parse(String dateTime, String pattern) throws ParseException {
		// FIXME USAR TREADLOCAL PARA UMA INSTANCIA POR THREAD
		Date date = new SimpleDateFormat(pattern).parse(dateTime);
		return new DateTime(date);
	}


	/**
	 * Convete uma data em formato YYYYMMDD para DateTime.
	 */
	public static DateTime parseIntInverse(int dateIntInverse){
		
		int year = dateIntInverse / 10000;
				
		int monthDay = dateIntInverse % 10000;
		
		int month = monthDay / 100;
		
		int day = monthDay % 100;		
		
		return new DateTime(year,month,day);
		
	}

	/**
	 * Verifica se uma data Ã© menor que a data informada.
	 * 
	 * @param dt
	 *            Data a ser comparada.
	 * @return
	 */
	public boolean before(DateTime dt) {
		return calendar.getTime().before(dt.getTime());
	}

	/**
	 * Verifica se uma data Ã© menor que a data informada.
	 * 
	 * @param dt
	 *            Data do tipo Date a ser comparada.
	 * @return
	 */
	public boolean before(Date dt) {
		return calendar.getTime().before(dt);
	}

	/**
	 * Verifica se uma data Ã© maior que a data informada.
	 * 
	 * @param dt
	 *            Data a ser comparada.
	 * @return
	 */
	public boolean after(DateTime dt) {
		return calendar.getTime().after(dt.getTime());
	}

	/**
	 * Verifica se uma data Ã© maior que a data informada.
	 * 
	 * @param dt
	 *            Data do tipo Date a ser comparada.
	 * @return
	 */
	public boolean after(Date dt) {
		return calendar.getTime().after(dt);
	}

	/**
	 * Compara se uma data Ã© igual a outra. 
	 * 
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		DateTime other = (DateTime) obj;
		if (calendar == null) {
			if (other.calendar != null) {
				return false;
			}
		} else if (!calendar.getTime().equals(other.calendar.getTime())) {
			return false;
		}

		return true;
	}

	/**
	 * Adciona um Timespan ao DateTime atual. Retorna um DateTime.
	 * @param time timeSpan a ser adicionado.
	 * @return
	 */
	public DateTime add(TimeSpan time) {
		DateTime dt = new DateTime();

		dt.addYears(time.getYears());
		dt.addMonths(time.getMonths());
		dt.addDays(time.getDays());
		dt.addHours(time.getHours());
		dt.addMinutes(time.getMinutes());
		dt.addSeconds(time.getSeconds());
		dt.addMilliseconds(time.getMilliseconds());

		return dt;
	}
	
	/**
	 * Subtrai duas datas, retornando a diferenÃ§a em um objeto do tipo Timestamp
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static TimeSpan diff(Date date1, Date date2){
		if(date1 == null || date2 == null){
			return null;
		}
		
		DateTime dateTime = new DateTime(date1);
		
		return dateTime.subtract(new DateTime(date2));
	}

	/**
	 * Subtrai um DateTime de outro, retornando a diferenÃ§a em Timespan.
	 * @param dt
	 * @return
	 */
	public TimeSpan subtract(DateTime dt) {

		int milliseconds = 0, seconds = 0, minutes = 0, hours = 0, days = 0, months = 0, years = 0;

		Calendar afterTime = new GregorianCalendar();
		Calendar beforeTime = new GregorianCalendar();
		if(after(dt)){
			beforeTime.setTime(getTime());
			afterTime.setTime(dt.getTime());
		}else{
			beforeTime.setTime(dt.getTime());
			afterTime.setTime(getTime());
		}

		while(beforeTime.get(Calendar.MILLISECOND) != afterTime.get(Calendar.MILLISECOND)){
			milliseconds ++;
			beforeTime.add(Calendar.MILLISECOND, -1);
		}

		while(beforeTime.get(Calendar.SECOND) != afterTime.get(Calendar.SECOND)){
			seconds ++;
			beforeTime.add(Calendar.SECOND, -1);
		}

		while(beforeTime.get(Calendar.MINUTE) != afterTime.get(Calendar.MINUTE)){
			minutes ++;
			beforeTime.add(Calendar.MINUTE, -1);
		}

		while(beforeTime.get(Calendar.HOUR_OF_DAY) != afterTime.get(Calendar.HOUR_OF_DAY)){
			hours ++;
			beforeTime.add(Calendar.HOUR_OF_DAY, -1);
		}

		while(beforeTime.get(Calendar.DAY_OF_MONTH) != afterTime.get(Calendar.DAY_OF_MONTH)){
			days ++;
			beforeTime.add(Calendar.DAY_OF_MONTH, -1);
		}

		while(beforeTime.get(Calendar.MONTH) != afterTime.get(Calendar.MONTH)){
			months ++;
			beforeTime.add(Calendar.MONTH, -1);
		}

		while(beforeTime.get(Calendar.YEAR) != afterTime.get(Calendar.YEAR)){
			years ++;
			beforeTime.add(Calendar.YEAR, -1);
		}


		if(before(dt)){
			milliseconds = -milliseconds;
			seconds = -seconds;
			minutes = -minutes;
			hours = -hours;
			days = -days;
			months = -months;
			years = -years;
			
		}

		long totalMilliseconds = (this.getTime().getTime() - dt.getTime().getTime());
		TimeSpan result = new TimeSpan(years, months, days, hours, minutes, seconds, milliseconds);

		int totalSeconds = (int) (totalMilliseconds / 1000);
		int totalMinutes = totalSeconds / 60;
		int totalHours = totalMinutes / 60;
		int totalDays = totalHours / 24;
		int totalMonths = months;

		if (years != 0) {
			totalMonths += years * 12;
		}

		result.setTotalMonths(totalMonths);
		result.setTotalDays(totalDays);
		result.setTotalHours(totalHours);
		result.setTotalMinutes(totalMinutes);
		result.setTotalSeconds(totalSeconds);
		result.setTotalMilliseconds(totalMilliseconds);

		return result;

	}

	/**
	 * Verifica se o DateTime atual estÃ¡ em um ano Bisexto.
	 * @return
	 */
	public boolean isLeapYear() {

		if (this.getYear() % 4 != 0) {
			return false;
		}
		if (this.getYear() % 400 == 0) {
			return true;
		}
		if (this.getYear() % 100 == 0) {
			return false;
		}

		return this.getYear() % 4 == 0;
	}
	
	/**
	 * Verifica se o DateTime passado como parÃ¢metro Ã© nulo ou igual ao DateTime.max()
	 * @param dateTime
	 * @return
	 */
	public static boolean isNullOrMaxValue(Date dateTime) {
		return (dateTime == null || DateTime.isMaxValue(new DateTime(dateTime).getDate().getTime()));
	}
	
	
	/**
	 * Retorna a menor data entre duas dadas informadas.
	 * @param date1 Data 1
	 * @param date2 Data 2
	 * @return Menor data.
	 */
	public static DateTime min(Date date1, Date date2){
		// Se ambas as datas forem nulas, retorna nulo
		if(date1 == null && date2 == null){
			return null;
		}
		
		// Se date1 for nulo, necessariamente, date2 nÃ£o Ã©. Ela serÃ¡ o retorno
		if(date1 == null){
			return new DateTime(date2);
		}
		
		// Se date2 for nulo, necessariamente, date1 nÃ£o Ã©. Ela serÃ¡ o retorno		
		if(date2 == null){
			return new DateTime(date1);
		}
		
		// Se date1 Ã© anterior a date2, date1 Ã© o resultado
		if(date1.before(date2)){
			return new DateTime(date1);
		}
		
		// Se date2 Ã© anterior a date1, date2 Ã© o resultado		
		if(date2.before(date1)){
			return new DateTime(date2);
		}
		
		// Neste caso, as duas datas sÃ£o iguais
		return new DateTime(date1);
	}
	
	
	/**
	 * Retorna a maior data entre duas datas. 
	 * @param date1 Data 1
	 * @param date2 Data 2
	 * @return Maior Data
	 */
	public static DateTime max(Date date1, Date date2){
		// Se ambas as datas forem nulas, retorna nulo
		if(date1 == null && date2 == null){
			return null;
		}
		
		// Se date1 for nulo, necessariamente, date2 nÃ£o Ã©. Ela serÃ¡ o retorno
		if(date1 == null){
			return new DateTime(date2);
		}
		
		// Se date2 for nulo, necessariamente, date1 nÃ£o Ã©. Ela serÃ¡ o retorno		
		if(date2 == null){
			return new DateTime(date1);
		}
		
		// Se date1 Ã© posterior a date2, date1 Ã© o resultado
		if(date1.after(date2)){
			return new DateTime(date1);
		}
		
		// Se date2 Ã© posterior a date1, date2 Ã© o resultado		
		if(date2.after(date1)){
			return new DateTime(date2);
		}
		
		// Neste caso, as duas datas sÃ£o iguais
		return new DateTime(date1);
	}

	/**
	 * Realiza a comparaÃ§Ã£o entre duas datas desconsiderando as horas, minutos e segundos.
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Boolean isDateEquals(Date date1, Date date2)
	{
		if(date1 == null || date2 ==null)
		{
			return false;
		}
		
		return new DateTime(date1).getDate().equals(new DateTime(date2).getDate());
	}
	
	
	/**
	 * Realiza a comparaÃ§Ã£o entre duas datas desconsiderando as horas, minutos e segundos.
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Boolean isDateEquals(DateTime date1, DateTime date2)
	{
		if(date1 == null || date2 ==null)
		{
			return false;
		}
		
		return date1.getDate().equals(date2.getDate());
	}

	/**
	 * Verifica se o mes/dia passado como parametro eh valido.
	 * 	Dia entre 1 e 31 e Mes entre 1 e 12
	 * @param mesDia mes e dia com o formato MMdd
	 * @return Se eh valor valido ou nao
	 */
	public static boolean isMonthDayValid(Integer mesDia) {
		int month = getMonthByMonthDay(mesDia);
		if (month < 1 || month > 12) {
			return false;
		}
		int day = getDayByMonthDay(mesDia);
		if (day < 1 || day > 31) {
			return false;
		}
		return true;
	}

	/**
	 * Retorna o mes do mes/dia passado como parametro.
	 * @param mesDia mesDia mes e dia com o formato MMdd
	 * @return O mes
	 */
	public static int getMonthByMonthDay(Integer mesDia) {
		if (IntegerUtils.isNullOrZero(mesDia)) {
			return 0;
		}
		int mes = 0;
		if (mesDia.toString().length() == TAMANHO_PADRAO_REFERENCIA) {
			mes = Integer.parseInt(mesDia.toString().substring(0, 2));
		} else if (mesDia.toString().length() == TAMANHO_ALTERANATIVO_REFERENCIA) {
			mes = Integer.parseInt(mesDia.toString().substring(0, 1));
		}
		return mes;
	}

	/**
	 * Retorna o dia do mes/dia passado como parametro.
	 * @param mesDia mesDia mes e dia com o formato MMdd
	 * @return O dia
	 */
	public static int getDayByMonthDay(Integer mesDia) {
		if (IntegerUtils.isNullOrZero(mesDia)) {
			return 0;
		}
		int dia = 0;
		if (mesDia.toString().length() == TAMANHO_PADRAO_REFERENCIA) {
			dia = Integer.parseInt(mesDia.toString().substring(2, 4));
		} else if (mesDia.toString().length() == TAMANHO_ALTERANATIVO_REFERENCIA) {
			dia = Integer.parseInt(mesDia.toString().substring(1, 3));
		}
		return dia;
	}

	/**
	 * Obtem o mes/dia com formato MMdd a partir do dia e do mes
	 * @param dia Valor do dia
	 * @param mes Valor do mes
	 * @return retorna o valor do mes/dia
	 */
	public static int getMonthDay(Integer dia, Integer mes) {
		if (IntegerUtils.isNullOrZeroOrNegative(dia) || IntegerUtils.isNullOrZeroOrNegative(mes)) {
			return 0;
		}
		int feriado = 0;
		if (dia.toString().length() == TAMANHO_CURTO_DIA_MES) {
			feriado = Integer.parseInt("" + mes + 0 + dia);
		} else {
			feriado = Integer.parseInt("" + mes + dia);
		}
		return feriado;
	}
	
	
	@Deprecated
	/**
	 * Utilizar getDateAsInt()
	 * @return
	 */
	public static Integer getDataAsInt() {
		return getDateAsInt();
	}
	
	/**
	 * Retorna data atual em formato inteiro inverso.
	 * @return
	 */
	public static Integer getDateAsInt(){
		Integer result = 0;

		Calendar today = new GregorianCalendar();
		today.setTime(new Date());

		result += today.get(Calendar.DAY_OF_MONTH);
		result += 100 * (today.get(Calendar.MONTH) + 1);
		result += 10000 * (today.get(Calendar.YEAR));

		return result;
	}

	/**
	 * @return hora no formato HHmm como um inteiro
	 */
	public static Integer getHourAsInt() {
		Integer result = 0;

		Calendar now = new GregorianCalendar();
		now.setTime(new Date());

		result += now.get(Calendar.SECOND);
		result += 100 * (now.get(Calendar.MINUTE));
		result += 10000 * (now.get(Calendar.HOUR_OF_DAY));

		return result;
	}
	
	/**
	 * Realiza a comparaÃ§Ã£o entre duas datas desconsiderando as dia, mes e ano.
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Boolean isTimeEquals(DateTime date1, DateTime date2) {
		if (date1 == null || date2 == null) {
			return false;
		}

		return date1.getHour() == date2.getHour()
				&& date1.getMinute() == date2.getMinute()
				&& date1.getSecond() == date2.getSecond();
	}
	
	/**
	 * Realiza a comparaÃ§Ã£o entre duas datas Considerando os dia, mes e ano.
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Boolean isDateTimeEquals(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return false;
		}

		return date1.getTime() == date2.getTime();
	}
	
	/**
	 * Retorna hora da data passada no parÃ¢metro como um inteiro
	 * 
	 * @param date
	 * @return hora no formato HHmm como um inteiro
	 */
	public static Integer getHourAsInt(DateTime date) {
		Integer result = 0;

		Calendar cal = date.getCalendar();

		result += cal.get(Calendar.SECOND);
		result += 100 * (cal.get(Calendar.MINUTE));
		result += 10000 * (cal.get(Calendar.HOUR_OF_DAY));

		return result;
	}
	
	/**
	 * Retorna uma Data a partir de um nÃºmero inteiro passado como parÃ¢metro
	 * 
	 * @param date no formato aaaammdd
	 * @return data no formato dd/mm/aaaa
	 */
	public static Date getIntAsDate(Integer date) {
		Integer ano = 0;
		Integer mes = 0;
		Integer dia = 0;
		
		ano = date / 10000;
		mes = ((date%10000)/100) - 1;
		dia = date%100;
		
		Date result = new GregorianCalendar(ano, mes, dia).getTime();
		
		
		return result;
	}
	
	/**
	 * Retorna um nÃºmero inteiro no formato(aaaammdd) a partir de uma data passada como parÃ¢metro
	 * Utilizar getDateAsInt
	 * @param date no formato dd/mm/aaaa
	 * @return Inteiro no formato aaaammdd
	 */
	@Deprecated 
	public static Integer getDataAsInt(DateTime date) {
		return getDateAsInt();
	}
	
	/**
	 * Retorna um nÃºmero inteiro no formato(aaaammdd) a partir de uma data passada como parÃ¢metro
	 * 
	 * @param date no formato dd/mm/aaaa
	 * @return Inteiro no formato aaaammdd
	 */
	public static Integer getDateAsInt(DateTime date){
		Integer result = 0;
		Calendar cal = date.getCalendar();
		result += cal.get(Calendar.DAY_OF_MONTH);
		result += 100 * (cal.get(Calendar.MONTH) + 1);
		result += 10000 * (cal.get(Calendar.YEAR));

		return result;
	}
	
	
	@Deprecated
	public static Integer dateToAmRef(Date date) {
		return (date.getYear()+1900) * 100 + (date.getMonth() + 1);
	}
	
	
	/**
	 * Adicionar mÃ©todo no DateReference e remover este. NÃ£o utilizar.
	 * @param date
	 * @return
	 */
	@Deprecated
	public static Integer dateToMesDia(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(MASCARA_DATE_TO_MESDIA);
		return new Integer(sdf.format(date));
	}
	
	/**
	 * Converte a data no formato passado por parÃ¢metro
	 * 
	 * @param dateTime
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	
	private static Pattern patterDate = Pattern.compile("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$");
	/**
	 * Verifica se um data no formato "dd/MM/yyyy", do tipo String Ã© vÃ¡lida
	 * 
	 * @param String
	 * @return boolean
	 */
	public static boolean isDateValid(String date){
		
		Matcher m = patterDate.matcher(date);
		if(!m.matches()){
			return false;
		}
		
	    SimpleDateFormat df = new SimpleDateFormat(MASCARA_DATA_TELA);
	    df.setLenient(false);
        try {
            df.parse(date);
            return true;
        } catch (ParseException ex) {
            return false;
        }
	}
	
	/**
	 * @return hora no formato HHmmss mmmm como um inteiro
	 */
	public static Integer getHoraComMilissegundosAsInt() {
		return DateTime.getHourAsInt() * 1000 + Calendar.getInstance().get(Calendar.MILLISECOND);
	}

	/**
	 * Retorna o primeiro da semana, considerando que a semana comeÃ§a no Domingo
	 * @param date
	 * @return
	 */
	public static DateTime getFirstDayOfWeek(DateTime date){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setFirstDayOfWeek(Calendar.SUNDAY);
		calendar.setTime(date.getDate().getTime());

		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		calendar.set(Calendar.AM_PM, Calendar.AM);
		
		return new DateTime(calendar);
	}

	
	public static String convert(long timeSTamp) {
		Date date = new Date(timeSTamp);
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		String dateFormatted = formatter.format(date);
		
		return dateFormatted;
	}
	
	public static void main(String[] args) {
		System.out.println(convert(101000));
	}

	public static Date nullIfMaxDate(Date date) {
		if(date == null) {
			return null;
		}
		if(isDateEquals(DateTime.max().getTime(), date)) {
			return null;
		}
		return date;
	}
}
