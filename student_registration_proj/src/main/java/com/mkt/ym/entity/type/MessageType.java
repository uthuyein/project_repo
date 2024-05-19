package com.mkt.ym.entity.type;

public enum MessageType {

	WARNING {
		@Override
		String getIcon() {
			return "bi bi-info-circle-fill";
		}
	},ERROR {
		@Override
		String getIcon() {
			return "bi bi-exclamation-triangle-fill";
		}
	},SUCCESS {
		@Override
		String getIcon() {
			return "bi bi-check-circle-fill";
		}
	};
	
	abstract String getIcon();
	private String message;
	
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
}
