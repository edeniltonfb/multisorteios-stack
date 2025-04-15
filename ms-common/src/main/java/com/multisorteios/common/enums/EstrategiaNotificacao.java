	package com.multisorteios.common.enums;

public enum EstrategiaNotificacao {

	NUNCA{
		@Override
		public String getValue() {
			return "N";
		}
		@Override
		public String getDescricao() {
			return "Nunca";
		}
	},
	VENDA{
		@Override
		public String getValue() {
			return "V";
		}
		@Override
		public String getDescricao() {
			return "Venda";
		}
	},
	PERIODICO{
		@Override
		public String getValue() {
			return "P";
		}
		@Override
		public String getDescricao() {
			return "Periódico";
		}
	},
	AMBOS{
		@Override
		public String getValue() {
			return "A";
		}
		@Override
		public String getDescricao() {
			return "Ambos";
		}
	};
	
	public abstract String getValue();
	public abstract String getDescricao();

	public static EstrategiaNotificacao parse(String value) {

		if (value == null) {
			return null;
		}

		for (EstrategiaNotificacao item : EstrategiaNotificacao.values()) {
			if (value.equals(item.getValue())) {
				return item;
			}
		}

		return null;
	}

	public static String getValue(EstrategiaNotificacao item) {
		if (item == null) {
			return null;
		}

		return item.getValue();
	}
	
	public static String getDescription(EstrategiaNotificacao item) {
		if (item == null) {
			return null;
		}

		return item.getDescricao();
	}
}
