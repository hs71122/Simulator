package org.cdapsimulator.configurations;

public class CommonConfig {

	
	/**
	 * JSON result keys
	 */
	public static String REQUEST_PARAMETERS_INVALID = "Invalid Values are provided";
	public static String LOGIN_SUCCESS = "Login Success";
	public static String MESSAGE = "message";
	public static String STATUS = "status";
	public static String VALUE = "value";
	public static String DB_ERROR = "db_error";
	public static String JSON_ERROR = "json_error";
	public static String USER_NOT_FOUND = "user_not_found";
	public static String USER_ALREADY_REGISTERED = "user_alredy_registered";
	public static String REQUEST_PARAMETERS_ARE_NULL = "request_parameters_are_null";
	public static String DATA_CONVERTING_ERROR = "data_convert_error";
	public static String SUCESSFULLY_UPDATE_USER_DETAILS = "sucessfully_update_user_details";
	public static String SUCESS_USER_ADD = "sucess_user_add";
	public static String SUCESS_ADD_RIDE = "sucess_add_ride";
	public static String SUCESS_FRIEND_REQUEST_ADD = "sucess_friend_request_add";
	public static String SUCESS_USER_MESSAGE_ADD = "sucess_user_message_add";
	public static String EMPTY_USER_MESSAGES = "empty_user_message";
	public static String SUCESS_USER_MESSAGE_UPDATE = "sucess_user_message_update";
	
	/**
	 * Messages for Views
	 */
	public static String VIEW_SUCESS_USER_ADD = "Successfully create account";
	public static String VIEW_LOGIN_SUCCESS = "Successfully Login";
	public static String VIEW_DB_ERROR = "Sorry serice is down.Please try again";
	public static String VIEW_USER_NOT_FOUND = "Please input correct user id and password";
	public static String VIEW_REQUEST_PARAMETERS_ARE_NULL = "Please fill all fields correctly";
	public static String VIEW_USER_ALREADY_REGISTERED = "UserId is allready registered.";
	
	/**
	 * Socket service configurations
	 */
	public final static String HOST = "ds043348.mongolab.com:43348";
	public final static String DB_NAME = "cdap";
	public final static String USER_NAME = "cdap";
	public final static String PASSWORD = "cdap";
	
	/**
	 * Session Keys
	 * 
	 */
	public final static String SESSION_USER = "session_user";
	
}
