package com.tudor.sdm;

public class Constants {
	
	public enum Defaults {
		
		LANGUAGE("en_US");
		
		private String value;
		Defaults(String value) {
			this.value = value;
		}
		
		public String toString() {
			return this.value;
		}
	}
	
	public enum DefaultErrorMessages {

		LANG_NOT_FOUND("The language file %s was not found.");
		
		String message;
		
		private DefaultErrorMessages(String message) {
			this.message = message;
		}
		
		public String toString() {
			return message;
		}
	}
	
	public enum StringNames {
		TITLE("title"),
		WINDOW_MANAGE_CLIENTS_TITLE("window_manage_clients_title"),
		BTN_MANAGE_CLIENTS_LABEL("btn_manage_clients_label");
		String stringName;
		
		private StringNames(String stringName) {
			this.stringName = stringName;
		}
		
		public String toString() {
			return stringName;
		}
	}
}
