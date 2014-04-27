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
		WINDOW_TITLE_ADD_EDIT_CLIENT("window_title_add_edit_client"),

		
		// Button labels
		BTN_MANAGE_CLIENTS_LABEL("btn_manage_clients_label"),
		BTN_ADD_NEW_SPORTS_SESSION_LABEL("btn_add_new_sports_session_label"),
		BTN_ADD_NEW_CLIENT_LABEL("btn_add_new_client_label"),
		BTN_EDIT_CLIENT_LABEL("btn_edit_client_label"),
		BTN_EDIT_SPORTS_SESSION_LABEL("btn_edit_sports_session_label"),
		BTN_GENERIC_SAVE_LABEL("btn_generic_save_label"),
		BTN_GENERIC_CANCEL_LABEL("btn_generic_cancel_label"),

		// Menu item labels
		MNU_ADMIN_LABEL("mnu_admin_label"),
		MNU_LIST_SPORTS_SESSION_TYPES("mnu_list_sports_session_types"),
		
		// Sports session labels
		LBL_SPORTS_SESSION_NAME("lbl_sports_session_name"),
		LBL_SPORTS_SESSION_DURATION("lbl_sports_session_duration"),
		LBL_SPORTS_SESSION_PRICE("lbl_sports_session_price"),

        //Client labels
        LBL_CLIENT_IBAN("lbl_client_iban"),
        LBL_CLIENT_COUNTRY("lbl_client_country"),
        LBL_CLIENT_CITY("lbl_client_city"),
        LBL_CLIENT_NAME("lbl_client_name"),
        LBL_CLIENT_ADDRESS("lbl_client_address"),
        LBL_CLIENT_DISTRICT("lbl_client_district"),
        LBL_CLIENT_PHONE_NUMBER("lbl_client_phone_number"),
        LBL_CLIENT_PERSONAL_NUMBER("lbl_client_personal_number"),

        //Generic labels
        LBL_LOADING_ANIMATION("lbl_loading_animation_text"),
		
		// Table columns
		TBL_COLUMN_TABLES_LIST_SPORTS_SESSIONS("tbl_column_tables_list_sports_sessions"),
		TBL_COLUMN_TABLES_LIST_CLIENTS("tbl_column_tables_list_clients"),

		// Errors
		ERR_NOT_ENOUGH_SESSIONS_LEFT_ON_PASS("err_not_enough_sessions_left_on_pass"),
		ERR_INVALID_DATA_INPUTED_FOR_SPORTS_SESSION("err_invalid_data_inputed_for_sports_session"),
		ERR_MAXSESSIONS_MUST_BE_GREATER_THAN_REMAININGSESSIONS("err_maxsessions_must_be_greater_than_remainingsessions"),
		ERR_MAXSESSIONS_MUST_BE_GREATER_THAN_ZERO("err_maxsessions_must_be_greater_than_zero"),
		ERR_REMAININGSESSIONS_MUST_BE_A_POSITIVE_NUMBER("err_remainingsessions_must_be_a_positive_number"),
        ERR_CLIENT_ADD_EDIT_NAME_INVALID("err_client_add_edit_name_invalid"),
        ERR_CLIENT_ADD_EDIT_PHONE_NO_INVALID("err_client_add_edit_phone_no_invalid");

        String stringName;

		private StringNames(String stringName) {
			this.stringName = stringName;
		}
		
		public String toString() {
			return stringName;
		}
	}
}
