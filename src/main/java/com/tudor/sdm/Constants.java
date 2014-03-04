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
		// Window titles
		TITLE("title"),
		WINDOW_MANAGE_CLIENTS_TITLE("window_manage_clients_title"),
		WINDOW_TITLE_LIST_SPORTS_SESSIONS("window_title_list_sports_sessions"),
		WINDOW_TITLE_ADD_EDIT_SPORTS_SESSIONS("window_title_add_edit_sports_sessions"),
		
		
		// Button labels
		BTN_MANAGE_CLIENTS_LABEL("btn_manage_clients_label"),
		BTN_ADD_NEW_SPORTS_SESSION_LABEL("btn_add_new_sports_session_label"),
		BTN_EDIT_SPORTS_SESSION_LABEL("btn_edit_sports_session_label"),
		BTN_GENERIC_SAVE_LABEL("btn_generic_save_label"),
		BTN_GENERIC_CANCEL_LABEL("btn_generic_cancel_label"),

		// Menu item labels
		MNU_ADMIN_LABEL("mnu_admin_label"),
		MNU_LIST_SPORTS_SESSION_TYPES("mnu_list_sports_session_types"),
		
		// Generic labels
		LBL_SPORTS_SESSION_NAME("lbl_sports_session_name"),
		LBL_SPORTS_SESSION_DURATION("lbl_sports_session_duration"),
		LBL_SPORTS_SESSION_PRICE("lbl_sports_session_price"),
		
		// Table columns
		TBL_COLUMN_ABLES_LIST_SPORTS_SESSIONS("tbl_column_ables_list_sports_sessions"),
		
		// Errors
		ERR_NOT_ENOUGH_SESSIONS_LEFT_ON_PASS("err_not_enough_sessions_left_on_pass"),
		ERR_INVALID_DATA_INPUTED_FOR_SPORTS_SESSION("err_invalid_data_inputed_for_sports_session"),
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
