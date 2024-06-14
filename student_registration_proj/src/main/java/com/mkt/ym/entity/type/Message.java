package com.mkt.ym.entity.type;

public enum Message {

	ERROR {
		@Override
		public String getColor() {
			return "alert-danger";
		}
	},WARNING {
		@Override
		public String getColor() {
			return "alert-warn";
		}
	},SUCCESS {
		@Override
		public String getColor() {
			return "alert-success";
		}
	};
	
	private String message;
	
	public abstract String getColor();
	
	public void setMessage(String message) {
		this.message = message;
		
	}
	
	public String getMessage() {
		return message;
	}
}
