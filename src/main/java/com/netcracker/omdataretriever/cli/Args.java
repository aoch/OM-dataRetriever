package com.netcracker.omdataretriever.cli;

import java.util.ResourceBundle;

/**
 * Retrieve constants from properties file.
 * 
 * @author Andrew Och
 * @version %I%, %G%
 * @since 1.0
 * 
 */
public class Args {
    private static ResourceBundle resourceBundle = ResourceBundle
            .getBundle("com.netcracker.omdataretriever.cli.cliConfig");

    public static final String APP_NAME = resourceBundle.getString("app_name");

    public static final String HELP = resourceBundle.getString("help");

    public static final String HELP_DESC = resourceBundle
            .getString("help_desc");

    public static final String VERSION = resourceBundle.getString("version");

    public static final String VERSION_VALUE = resourceBundle
            .getString("version_value");

    public static final String VERSION_DESC = resourceBundle
            .getString("version_desc");

    public static final String OUTPUT = resourceBundle.getString("output");

    public static final String OUTPUT_DESC = resourceBundle
            .getString("output_desc");

    public static final String OUTPUT_ARG = resourceBundle
            .getString("output_arg");
    
    public static final String ORDERID = resourceBundle.getString("orderid");

    public static final String ORDERID_DESC = resourceBundle
            .getString("orderid_desc");

    public static final String ORDERID_ARG = resourceBundle
            .getString("orderid_arg");

    public static final String DB_URL = resourceBundle.getString("db_url");

    public static final String DB_URL_DESC = resourceBundle
            .getString("db_url_desc");

    public static final String DB_URL_ARG = resourceBundle
            .getString("db_url_arg");

    public static final String DB_SERVICE_NAME = resourceBundle
            .getString("db_service_name");

    public static final String DB_SERVICE_NAME_DESC = resourceBundle
            .getString("db_service_name_desc");

    public static final String DB_SERVICE_NAME_ARG = resourceBundle
            .getString("db_service_name_arg");

    public static final String DB_USERNAME = resourceBundle
            .getString("db_username");

    public static final String DB_USERNAME_DESC = resourceBundle
            .getString("db_username_desc");

    public static final String DB_USERNAME_ARG = resourceBundle
            .getString("db_username_arg");

    public static final String DB_PASSWORD = resourceBundle
            .getString("db_password");

    public static final String DB_PASSWORD_DESC = resourceBundle
            .getString("db_password_desc");

    public static final String DB_PASSWORD_ARG = resourceBundle
            .getString("db_password_arg");

    public static final String PROPERTY = resourceBundle.getString("property");

    public static final String PROPERTY_DESC = resourceBundle
            .getString("property_desc");

    public static final String PROPERTY_ARG = resourceBundle
            .getString("property_arg");

}