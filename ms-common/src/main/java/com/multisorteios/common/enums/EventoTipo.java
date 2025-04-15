	package com.multisorteios.common.enums;

public enum EventoTipo {

	RIFA{
		@Override
		public String getValue() {
			return "RIF";
		}
		@Override
		public String getDescricao() {
			return "Rifa";
		}
	},
	BOLAO{
		@Override
		public String getValue() {
			return "BOL";
		}
		
		@Override
		public String getDescricao() {
			return "Bolão";
		}
	};
	
	public abstract String getValue();
	public abstract String getDescricao();

	public static EventoTipo parse(String value) {

		if (value == null) {
			return null;
		}

		for (EventoTipo item : EventoTipo.values()) {
			if (value.equals(item.getValue())) {
				return item;
			}
		}

		return null;
	}

	public static String getValue(EventoTipo item) {
		if (item == null) {
			return null;
		}

		return item.getValue();
	}
	
}
