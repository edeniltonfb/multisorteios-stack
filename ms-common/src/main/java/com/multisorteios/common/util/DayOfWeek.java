package com.multisorteios.common.util;

public enum DayOfWeek {
	SUNDAY{
		@Override
		public String getNomeAbreviado() {
			return "DOM";
		}
	},
	MONDAY{
		@Override
		public String getNomeAbreviado() {
			return "SEG";
		}
	},
	TUESDAY{
		@Override
		public String getNomeAbreviado() {
			return "TER";
		}
	},
	WEDNESDAY{
		@Override
		public String getNomeAbreviado() {
			return "QUA";
		}
	},
	THURSDAY{
		@Override
		public String getNomeAbreviado() {
			return "QUI";
		}
	},	
	FRIDAY{
		@Override
		public String getNomeAbreviado() {
			return "SEX";
		}
	},
	SATURDAY{
		@Override
		public String getNomeAbreviado() {
			return "SÁB";
		}
	};
	
	private static DayOfWeek[] values = null;
    public static DayOfWeek fromInt(int i) {
        if(DayOfWeek.values == null) {
        	DayOfWeek.values = DayOfWeek.values();
        }
        return DayOfWeek.values[i];
    }
	public abstract String getNomeAbreviado();

}
