package com.netcracker.omdataretriever.cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

/**
 * Each command line must define the set of options that will be used to define
 * the interface to the application. CLI uses the Options class, as a container
 * for Option instances.
 * 
 * All Command Line Options should be defined in this Class file.
 * 
 * @author Andrew Och
 * @version %I%, %G%
 * @since 1.0
 */
public class ArgumentDefinitions {

    private Options options;

    private Option help;

    private Option version;

    private Option orderid;
    
    private Option output;

    private Option dbUrl;

    private Option dbServiceName;

    private Option dbUsername;

    private Option dbPassword;

    private Option property;

    public ArgumentDefinitions() {
        defineBooleanOptions();
        defineArgumentOptions();
        createOptions();
    }

    private void createOptions() {
        options = new Options();

        // Add each option
        options.addOption(help);
        options.addOption(version);
        options.addOption(orderid);
        options.addOption(output);
        options.addOption(dbUrl);
        options.addOption(dbServiceName);
        options.addOption(dbUsername);
        options.addOption(dbPassword);
        options.addOption(property);
    }

    // Define are argument type options here
    private void defineArgumentOptions() {
        
        OptionBuilder.hasArg();
        OptionBuilder.withArgName(Args.ORDERID_ARG);
        OptionBuilder.withDescription(Args.ORDERID_DESC);
        orderid = OptionBuilder.create(Args.ORDERID);
        
        OptionBuilder.hasArg();
        OptionBuilder.withArgName(Args.OUTPUT_ARG);
        OptionBuilder.withDescription(Args.OUTPUT_DESC);
        output = OptionBuilder.create(Args.OUTPUT);
        
        OptionBuilder.hasArg();
        OptionBuilder.withArgName(Args.DB_URL_ARG);
        OptionBuilder.withDescription(Args.DB_URL_DESC);
        dbUrl = OptionBuilder.create(Args.DB_URL);

        OptionBuilder.hasArg();
        OptionBuilder.withArgName(Args.DB_SERVICE_NAME_ARG);
        OptionBuilder.withDescription(Args.DB_SERVICE_NAME_DESC);
        dbServiceName = OptionBuilder.create(Args.DB_SERVICE_NAME);

        OptionBuilder.hasArg();
        OptionBuilder.withArgName(Args.DB_USERNAME_ARG);
        OptionBuilder.withDescription(Args.DB_USERNAME_DESC);
        dbUsername = OptionBuilder.create(Args.DB_USERNAME);

        OptionBuilder.hasArg();
        OptionBuilder.withArgName(Args.DB_PASSWORD_ARG);
        OptionBuilder.withDescription(Args.DB_PASSWORD_DESC);
        dbPassword = OptionBuilder.create(Args.DB_PASSWORD);

        OptionBuilder.hasArgs(2);
        OptionBuilder.withArgName(Args.PROPERTY_ARG);
        OptionBuilder.withDescription(Args.PROPERTY_DESC);
        OptionBuilder.withValueSeparator();
        property = OptionBuilder.create(Args.PROPERTY);
    }

    // Define are flag type options here
    private void defineBooleanOptions() {
        help = new Option(Args.HELP, Args.HELP_DESC);
        version = new Option(Args.VERSION, Args.VERSION_DESC);
    }

    /**
     * Returns all available Command Line Options
     * 
     * @return the options
     */
    public Options getOptions() {
        return options;
    }

    /**
     * @param options
     *            the options to set
     */
    public void setOptions(Options options) {
        this.options = options;
    }
}