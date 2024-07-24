package com.mkt.ym.entity.type;

public enum MessageType {

	ERROR {
		@Override
		public String getColor() {
			return "text-danger";
		}
	},WARNING {
		@Override
		public String getColor() {
			return "text-warning";
		}
	},SUCCESS {
		@Override
		public String getColor() {
			return "text-success";
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
