package com.multisorteios.common.enums;

public enum FormaConfirmacaoPagamento {
	
	MANUAL{
		@Override
		public String getValue() {
			return "MN";
		}
		@Override
		public String getDescricao() {
			return "Manual";
		}
	},
	WEBHOOK{
		@Override
		public String getValue() {
			return "WH";
		}
		@Override
		public String getDescricao() {
			return "Webhook";
		}
	},
	SCHEDULE{
		@Override
		public String getValue() {
			return "SC";
		}
		@Override
		public String getDescricao() {
			return "Schedule";
		}
	};
	

	public static String getValue(FormaConfirmacaoPagamento item) {
		if (item == null) {
			return null;
		}

		return item.getValue();
	}
	
	public abstract String getValue();
	public abstract String getDescricao();
}
