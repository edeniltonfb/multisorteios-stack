package com.multisorteios.common.enums;

import java.util.List;

import com.multisorteios.common.util.StringUtils;

public enum EventoSubTipo {
	RIFA_MILHAO {
		@Override
		public String getValue() {
			return "RMM";
		}

		@Override
		public EventoTipo getTipo() {
			return EventoTipo.RIFA;
		}

		@Override
		public String getDescricao() {
			return "Rifa Milhão";
		}

		@Override
		public boolean match(List<String> dezenas) {
			return false;
		}

		@Override
		public boolean match(String numero) {
			try {
				int n = Integer.parseInt(numero);
				return n >= 0 && n <= 9999999;
			} catch (Exception e) {
				return false;
			}
		}

		@Override
		public String format(int numero) {
			return StringUtils.formatMilhao(numero);
		}

		@Override
		public int quantidadeNumeros() {
			return 10000000;
		}
	},
	RIFA_CENTENA_MILHAR {
		@Override
		public String getValue() {
			return "RCM";
		}

		@Override
		public EventoTipo getTipo() {
			return EventoTipo.RIFA;
		}

		@Override
		public String getDescricao() {
			return "Rifa Centena de Milhar";
		}

		@Override
		public boolean match(List<String> dezenas) {
			return false;
		}

		@Override
		public boolean match(String numero) {
			try {
				int n = Integer.parseInt(numero);
				return n >= 0 && n <= 999999;
			} catch (Exception e) {
				return false;
			}
		}

		@Override
		public String format(int numero) {
			return StringUtils.formatCentenaMilhar(numero);
		}

		@Override
		public int quantidadeNumeros() {
			return 1000000;
		}
	},
	RIFA_DEZENA_MILHAR {
		@Override
		public String getValue() {
			return "RDM";
		}

		@Override
		public EventoTipo getTipo() {
			return EventoTipo.RIFA;
		}

		@Override
		public String getDescricao() {
			return "Rifa Dezena de Milhar";
		}

		@Override
		public boolean match(List<String> dezenas) {
			return false;
		}

		@Override
		public boolean match(String numero) {
			try {
				int n = Integer.parseInt(numero);
				return n >= 0 && n <= 99999;
			} catch (Exception e) {
				return false;
			}
		}

		@Override
		public String format(int numero) {
			return StringUtils.formatDezenaMilhar(numero);
		}

		@Override
		public int quantidadeNumeros() {
			return 100000;
		}
	},

	RIFA_MILHAR {
		@Override
		public String getValue() {
			return "RMI";
		}

		@Override
		public EventoTipo getTipo() {
			return EventoTipo.RIFA;
		}

		@Override
		public String getDescricao() {
			return "Rifa Milhar";
		}

		@Override
		public boolean match(List<String> dezenas) {
			return false;
		}

		@Override
		public boolean match(String numero) {
			try {
				int n = Integer.parseInt(numero);
				return n >= 0 && n <= 9999;
			} catch (Exception e) {
				return false;
			}
		}

		@Override
		public String format(int numero) {
			return StringUtils.formatMilhar(numero);
		}

		@Override
		public int quantidadeNumeros() {
			return 10000;
		}
	},

	RIFA_CENTENA {
		@Override
		public String getValue() {
			return "RCE";
		}

		@Override
		public EventoTipo getTipo() {
			return EventoTipo.RIFA;
		}

		@Override
		public String getDescricao() {
			return "Rifa Centena";
		}

		@Override
		public boolean match(List<String> dezenas) {
			return false;
		}

		@Override
		public boolean match(String numero) {
			try {
				int n = Integer.parseInt(numero);
				return n >= 0 && n <= 999;
			} catch (Exception e) {
				return false;
			}
		}

		@Override
		public String format(int numero) {
			return StringUtils.formatCentena(numero);
		}

		@Override
		public int quantidadeNumeros() {
			return 1000;
		}
	},

	RIFA_DEZENA {
		@Override
		public String getValue() {
			return "RDZ";
		}

		@Override
		public EventoTipo getTipo() {
			return EventoTipo.RIFA;
		}

		@Override
		public String getDescricao() {
			return "Rifa Dezena";
		}

		@Override
		public boolean match(List<String> dezenas) {
			return false;
		}

		@Override
		public boolean match(String numero) {
			try {
				int n = Integer.parseInt(numero);
				return n >= 0 && n <= 99;
			} catch (Exception e) {
				return false;
			}
		}

		@Override
		public String format(int numero) {
			return StringUtils.formatDezena(numero);
		}

		@Override
		public int quantidadeNumeros() {
			return 100;
		}
	},

	RIFA_GRUPO {
		@Override
		public String getValue() {
			return "RGR";
		}

		@Override
		public EventoTipo getTipo() {
			return EventoTipo.RIFA;
		}

		@Override
		public String getDescricao() {
			return "Rifa Grupo";
		}

		@Override
		public boolean match(List<String> dezenas) {
			return false;
		}

		@Override
		public boolean match(String numero) {
			try {
				int n = Integer.parseInt(numero);
				return n >= 1 && n <= 25;
			} catch (Exception e) {
				return false;
			}
		}

		@Override
		public String format(int numero) {
			return StringUtils.formatDezena(numero);
		}

		@Override
		public int quantidadeNumeros() {
			return 25;
		}
	},
	BOLAO_FEDERAL {
		@Override
		public String getValue() {
			return "BFE";
		}

		@Override
		public EventoTipo getTipo() {
			return EventoTipo.BOLAO;
		}

		@Override
		public String getDescricao() {
			return "Bolão Federal";
		}

		@Override
		public boolean match(List<String> dezenas) {
			return false;
		}

		@Override
		public boolean match(String numero) {
			try {
				int n = Integer.parseInt(numero);
				return n >= 0 && n <= 99999;
			} catch (Exception e) {
				return false;
			}
		}

		@Override
		public String format(int numero) {
			return StringUtils.formatDezena(numero);
		}

		@Override
		public int quantidadeNumeros() {
			return 0;
		}
	},
	BOLAO_10_DEZENAS {
		@Override
		public String getValue() {
			return "B10";
		}

		@Override
		public EventoTipo getTipo() {
			return EventoTipo.BOLAO;
		}

		@Override
		public String getDescricao() {
			return "Bolão 10 Dezenas";
		}

		@Override
		public boolean match(List<String> dezenas) {
			return false;
		}

		@Override
		public boolean match(String numero) {
			try {
				int n = Integer.parseInt(numero);
				return n >= 0 && n <= 99;
			} catch (Exception e) {
				return false;
			}
		}

		@Override
		public String format(int numero) {
			return StringUtils.formatDezena(numero);
		}

		@Override
		public int quantidadeNumeros() {
			return 0;
		}
	},
	BOLAO_10_DEZENAS_COM_ESCOLHA {
		@Override
		public String getValue() {
			return "E10";
		}

		@Override
		public EventoTipo getTipo() {
			return EventoTipo.BOLAO;
		}

		@Override
		public String getDescricao() {
			return "Bolão 10 Dezenas";
		}

		@Override
		public boolean match(List<String> dezenas) {
			return false;
		}

		@Override
		public boolean match(String numero) {
			try {
				int n = Integer.parseInt(numero);
				return n >= 0 && n <= 99;
			} catch (Exception e) {
				return false;
			}
		}

		@Override
		public String format(int numero) {
			return StringUtils.formatDezena(numero);
		}

		@Override
		public int quantidadeNumeros() {
			return 0;
		}
	};

	public abstract String getValue();

	public abstract EventoTipo getTipo();

	public abstract String getDescricao();

	public abstract boolean match(List<String> dezenas);

	public abstract boolean match(String numero);

	public abstract String format(int numero);

	public abstract int quantidadeNumeros();

	public static EventoSubTipo parse(String value) {

		if (value == null) {
			return null;
		}

		for (EventoSubTipo item : EventoSubTipo.values()) {
			if (value.equals(item.getValue())) {
				return item;
			}
		}

		return null;
	}

	public static String getValue(EventoSubTipo item) {
		if (item == null) {
			return null;
		}

		return item.getValue();
	}
	
	public static String getDescription(EventoSubTipo item) {
		if (item == null) {
			return null;
		}

		return item.getDescricao();
	}

}
