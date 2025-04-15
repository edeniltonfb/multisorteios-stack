package com.multisorteios.common.util;

public class TimeSpan {
	
	private int milliseconds = 0;
	private int seconds = 0;
	private int minutes = 0;
	private int hours = 0;
	private int days = 0;
	private int months = 0;
	private int years =0;
	
	private long totalMilliseconds = 0;
	private long totalSeconds = 0;
	private long totalMinutes = 0;
	private long totalHours = 0;
	private long totalDays = 0;
	private long totalMonths = 0;
		
	public TimeSpan() {		
	}
	
	public TimeSpan(int hours, int minutes,int seconds) {
		super();
		this.seconds = seconds;
		this.minutes = minutes;
		this.hours = hours;
	}
	
	public TimeSpan(int days,int hours, int minutes,int seconds) {
		super();
		this.days = days;
		this.seconds = seconds;
		this.minutes = minutes;
		this.hours = hours;
	}
	
	
	public TimeSpan(int days,int hours, int minutes,int seconds,int milliseconds) {
		super();
		this.days = days;
		this.seconds = seconds;
		this.minutes = minutes;
		this.hours = hours;
		this.milliseconds = milliseconds;
	}

	public TimeSpan(int years, int months, int days, int hours, int minutes,
			int seconds, int milliseconds) {
		super();
		this.years = years;
		this.months = months;
		this.days = days;
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
		this.milliseconds = milliseconds;
	}

	/**
	 * Obtem numero de milissegundos do segundo.
	 * 	Ex: se for 2 segundos e 5 milissegundos, retorna apenas 5.
	 */
	public int getMilliseconds() {
		return milliseconds;
	}

	/**
	 * Obtem numero de segundos do minuto.
	 * 	Ex: se for 2 minutos e 5 segundos, retorna apenas 5.
	 */
	public int getSeconds() {
		return seconds;
	}

	/**
	 * Obtem numero de minutos da hora.
	 * 	Ex: se for 2 horas e 5 minutos, retorna apenas 5.
	 */
	public int getMinutes() {
		return minutes;
	}

	/**
	 * Obtem numero de horas do dia.
	 * 	Ex: se for 2 dias e 5 horas, retorna apenas 5.
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * Obtem numero de dias do mes.
	 * 	Ex: se for 2 meses e 5 dias, retorna apenas 5.
	 */
	public int getDays() {
		return days;
	}

	/**
	 * Obtem numero de meses.
	 */
	public int getMonths() {
		return months;
	}

	/**
	 * Obtem numero de anos.
	 */
	public int getYears() {
		return years;
	}

	/**
	 * Obtem o total de milissegundos.
	 * 	Ex: se for 2 segundos e 5 milissegundos, retorna 2005.
	 */
	public long getTotalMilliseconds() {
		return totalMilliseconds;
	}

	/**
	 * Obtem o total de segundos.
	 * 	Ex: se for 2 minutos e 5 segundos, retorna 125.
	 */
	public long getTotalSeconds() {
		return totalSeconds;
	}

	/**
	 * Obtem o total de minutos.
	 * 	Ex: se for 2 horas e 5 minutos, retorna 125.
	 */
	public long getTotalMinutes() {
		return totalMinutes;
	}

	/**
	 * Obtem o total de horas.
	 * 	Ex: se for 2 dias e 5 horas, retorna 53.
	 */
	public long getTotalHours() {
		return totalHours;
	}

	/**
	 * Obtem o total de dias.
	 * 	Ex: se for 2 meses e 5 dias, retorna 65 (considerando o mes ter 30 dias).
	 */
	public long getTotalDays() {
		return totalDays;
	}

	/**
	 * Obtem o total de meses, contagem apenas dos meses ignorando as datas.
	 * 	Ex: se for 1 ano 2 meses, retorna 14.
	 */
	public long getTotalMonths() {
		return totalMonths;
	}

	public void setTotalMilliseconds(long totalMilliseconds) {
		this.totalMilliseconds = totalMilliseconds;
	}

	public void setTotalSeconds(long totalSeconds) {
		this.totalSeconds = totalSeconds;
	}

	public void setTotalMinutes(long totalMinutes) {
		this.totalMinutes = totalMinutes;
	}

	public void setTotalHours(long totalHours) {
		this.totalHours = totalHours;
	}

	public void setTotalDays(long totalDays) {
		this.totalDays = totalDays;
	}

	/**
	 * Atualiza variavel que tem o total de meses de um intervalo.
	 * @param totalMonths total de meses
	 */
	public void setTotalMonths(long totalMonths) {
		this.totalMonths = totalMonths;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + days;
		result = prime * result + hours;
		result = prime * result + milliseconds;
		result = prime * result + minutes;
		result = prime * result + months;
		result = prime * result + seconds;
		result = prime * result + years;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		TimeSpan other = (TimeSpan) obj;
		if (days != other.days){
			return false;
		}
		if (hours != other.hours){
			return false;
		}
		if (milliseconds != other.milliseconds){
			return false;
		}
		if (minutes != other.minutes){
			return false;
		}
		if (months != other.months){
			return false;
		}
		if (seconds != other.seconds){
			return false;
		}
		if (years != other.years){
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TimeSpan [milliseconds=" + milliseconds + ", seconds="
				+ seconds + ", minutes=" + minutes + ", hours=" + hours
				+ ", days=" + days + ", months=" + months + ", years=" + years
				+ "]";
	}
	
}