package com.multisorteios.common.enums;

public enum Horario {

	FEDERAL {
		@Override
		public String getValue() {
			return "FED";
		}
		
		@Override
		public String getDescription() {
			return "Loteria Federal";
		}
	},NACIONAL {
		@Override
		public String getValue() {
			return "NAC";
		}
		
		@Override
		public String getDescription() {
			return "Loteria Nacional";
		}
	}, _19HORAS_BAHIA {
		@Override
		public String getValue() {
			return "19H";
		}
		
		@Override
		public String getDescription() {
			return "19hs - Bahia";
		}
	}, _15HORAS_BAHIA {
		@Override
		public String getValue() {
			return "15H";
		}
		
		@Override
		public String getDescription() {
			return "15hs - Bahia";
		}
	}, _21HORAS_BAHIA {
		@Override
		public String getValue() {
			return "21H";
		}
		
		@Override
		public String getDescription() {
			return "21hs - Bahia";
		}
	}, _18HORAS_RIO {
		@Override
		public String getValue() {
			return "18R";
		}
		
		@Override
		public String getDescription() {
			return "18hs - RIO";
		}
	}, _21HORAS_RIO {
		@Override
		public String getValue() {
			return "21R";
		}
		
		@Override
		public String getDescription() {
			return "21hs - RIO";
		}
	};

	public abstract String getValue();
	public abstract String getDescription();
	
	public static Horario parse(String value) {
		
		if(value == null) {
			return null;	
		}
		
		for(Horario item : Horario.values()) {
			if(value.equals(item.getValue())) {
				return item;
			}
		}
		
		return null;
	}
	
	public static String getValue(Horario item){
		if(item == null) {
			return null;
		}
		
		return item.getValue();
	}
	
	public static String getDescription(Horario item){
		if(item == null) {
			return null;
		}
		
		return item.getDescription();
	}
	
	public boolean isFederal(Horario item) {
		if(item == null) {
			return false;
		}
		
		return item == FEDERAL;
	}
}
