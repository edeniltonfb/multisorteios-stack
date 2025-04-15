package com.multisorteios.common.enums;

public enum BilheteSituacao {

	VALIDA {
		@Override
		public String getValue() {
			return "VAL";
		}
		
		@Override
		public String getDescription() {
			return "Pagamento Confirmado";
		}
	}, CANCELADA {
		@Override
		public String getValue() {
			return "CAN";
		}
		
		@Override
		public String getDescription() {
			return "Bilhete Cancelado";
		}
	}, AGUARDANDO_CONFIRMACAO_PAGAMENTO {
		@Override
		public String getValue() {
			return "ACG";
		}
		
		@Override
		public String getDescription() {
			return "Aguardando Confirmação do Pagamento";
		}
	};

	public abstract String getValue();
	public abstract String getDescription();
	
	public static BilheteSituacao parse(String value) {
		
		if(value == null) {
			return null;	
		}
		
		for(BilheteSituacao item : BilheteSituacao.values()) {
			if(value.equals(item.getValue())) {
				return item;
			}
		}
		
		return null;
	}
	
	public static String getValue(BilheteSituacao item){
		if(item == null) {
			return null;
		}
		
		return item.getValue();
	}
	
}
