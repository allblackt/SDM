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
		BTN_MANAGE_CLIENTS_LABEL("btn_manage_clients_label"),
		
		//Errors
		ERR_NOT_ENOUGH_SESSIONS_LEFT_ON_PASS("err_not_enough_sessions_left_on_pass"),
		ERR_MAXSESSIONS_MUST_BE_GREATER_THAN_REMAININGSESSIONS("err_maxsessions_must_be_greater_than_remainingsessions"),
		ERR_MAXSESSIONS_MUST_BE_GREATER_THAN_ZERO("err_maxsessions_must_be_greater_than_zero"),
		ERR_REMAININGSESSIONS_MUST_BE_A_POSITIVE_NUMBER("err_remainingsessions_must_be_a_positive_number");
		String stringName;
		
		private StringNames(String stringName) {
			this.stringName = stringName;
		}
		
		public String toString() {
			return stringName;
		}
	}
}
