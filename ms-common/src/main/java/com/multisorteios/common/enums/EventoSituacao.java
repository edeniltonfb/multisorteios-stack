package com.multisorteios.common.enums;


public enum EventoSituacao {

	CRIADO {
		@Override
		public String getValue() {
			return "CRI";
		}

		@Override
		public String getDescription() {
			return "Criado";
		}
	}, EM_ANDAMENTO {
		@Override
		public String getValue() {
			return "AND";
		}
		
		@Override
		public String getDescription() {
			return "Em andamento";
		}
	}, PAUSADO {
		@Override
		public String getValue() {
			return "PAU";
		}
		
		@Override
		public String getDescription() {
			return "Pausado";
		}
	}, VENDAS_ENCERRADAS {
		@Override
		public String getValue() {
			return "VEN";
		}
		
		@Override
		public String getDescription() {
			return "Vendas Encerradas";
		}
	}, FINALIZADO {
		@Override
		public String getValue() {
			return "FIN";
		}
		
		@Override
		public String getDescription() {
			return "Finalizado";
		}
	}, CANCELADO {
		@Override
		public String getValue() {
			return "CAN";
		}
		
		@Override
		public String getDescription() {
			return "Cancelado";
		}
	}, INCONSISTENTE {
		@Override
		public String getValue() {
			return "ERR";
		}
		
		@Override
		public String getDescription() {
			return "Inconsistente";
		}
	};

	public abstract String getValue();
	public abstract String getDescription();
	
	public static String getDescriptionFromValue(String value) {
		EventoSituacao evento = parse(value);
		if(evento == null) {
			return "";
		}
		return evento.getDescription();
	}
	
	public static EventoSituacao parse(String value) {
		
		if(value == null) {
			return null;	
		}
		
		for(EventoSituacao item : EventoSituacao.values()) {
			if(value.equals(item.getValue())) {
				return item;
			}
		}
		
		return null;
	}
	
	public static String getValue(EventoSituacao item){
		if(item == null) {
			return null;
		}
		
		return item.getValue();
	}
}
