package com.netcracker.omdataretriever.cli;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.log4j.Logger;

import com.netcracker.omdataretriever.db.DBClientImpl;
import com.netcracker.omdataretriever.service.OrderService;

/**
 * The interrogation stage is where the application queries the CommandLine to
 * decide what execution branch to take depending on boolean options and uses
 * the option values to provide the application data. This stage is implemented
 * in the user code. The accessor methods on CommandLine provide the
 * interrogation capability to the user code. The result of the interrogation
 * stage is that the user code is fully informed of all the text that was
 * supplied on the command line and processed according to the parser and
 * Options rules.
 * <p/>
 * All Command Line Options the user selected at run time are accessed from this
 * class
 * 
 * @author Andrew Och
 * @version %I%, %G%
 * @since 1.0
 */
public class CliManager {
    private static transient final Logger logger = Logger
            .getLogger(CliManager.class);

    /**
     * Show invalid usage of options and error exit code
     * 
     * @param options
     */
    public static void invalidUsage(Options options) {
        boolean showUsage = true;
        // automatically generate the help statement
        HelpFormatter formatter = new HelpFormatter();
        formatter.setWidth(125);

        // Comparator to sort the args
        // formatter.setOptionComparator(comparator);

        formatter.printHelp(Args.APP_NAME, options, showUsage);
        System.exit(1);
    }

    /**
     * Show invalid usage of options.
     * 
     * @param options
     * @param msg
     */
    public static void invalidUsage(Options options, String msg) {
        System.out.println(msg);
        invalidUsage(options);
    }

    /**
     * Show help and error exit code
     * 
     * @param options
     */
    public static void inValidUsageOfHelp(Options options) {
        boolean showUsage = true;
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(Args.APP_NAME, options, showUsage);
        System.exit(1);
    }

    private CommandLine cmdLine;

    private Options options;

    /**
     * Default empty constructor
     */
    public CliManager() {
    }

    /**
     * @param cmdLine
     * @param options
     */
    public CliManager(CommandLine cmdLine, Options options) {
        this();
        this.cmdLine = cmdLine;
        this.options = options;
    }

    public CommandLine getCmdLine() {
        return cmdLine;
    }

    /**
     * @param optionValue
     * @return fileOS
     */
    public OutputStream getFileOS(String filename) {
        File file = new File(filename);
        File fileDir = file.getParentFile();
        if ((fileDir != null) && (!fileDir.exists())) {
            if (!fileDir.mkdirs()) {
                if (!fileDir.exists()) {
                    logger.error("Cannot create directory " + fileDir);
                }
            }
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
        }

        FileOutputStream fileOS = null;
        try {
            fileOS = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            logger.error(e.getLocalizedMessage());
        }
        return fileOS;
    }

    /**
     * @return the options
     */
    public Options getOptions() {
        return options;
    }

    /**
     * The meat of command line arguments. Do what the user wants!
     */
    public void interrogateArgs() {

        // Version options executes and exits
        if (cmdLine.hasOption(Args.VERSION)) {
            // Just print the version of this tool
            System.out.println(version());
            System.exit(0);
        }

        if (!cmdLine.hasOption(Args.ORDERID)) {
            String errorMsg = String.format("Sorry you MUST specify --%s.",
                    new Object[] { Args.ORDERID });
            logger.error(errorMsg);
            invalidUsage(options, errorMsg);
        }

        String orderId = cmdLine.getOptionValue(Args.ORDERID);

        if (!cmdLine.hasOption(Args.OUTPUT)) {
            String errorMsg = String
                    .format("Sorry you MUST specify --%s=\"XML\", --%s=\"JSON\" or --%s=\"TEXT\".",
                            new Object[] { Args.OUTPUT });
            logger.error(errorMsg);
            invalidUsage(options, errorMsg);
        }

        String output = cmdLine.getOptionValue(Args.OUTPUT);

        if (!(output.equalsIgnoreCase("XML") || output.equalsIgnoreCase("JSON") || output
                .equalsIgnoreCase("TEXT"))) {
            String errorMsg = String.format(
                    "Sorry you MUST specify --%s as XML, JSON or TEXT.",
                    new Object[] { Args.OUTPUT });
            logger.error(errorMsg);
            invalidUsage(options, errorMsg);
        }

        Map<String, String> variablesMap = new HashMap<String, String>();
        if (cmdLine.hasOption(Args.PROPERTY)) {
            variablesMap = new HashMap<String, String>(
                    (Map) cmdLine.getOptionProperties(Args.PROPERTY));
        }

        String dbUrl = "";
        if (cmdLine.hasOption(Args.DB_URL)) {
            dbUrl = cmdLine.getOptionValue(Args.DB_URL);
        } else {
            logger.error("Must provide Database URL in form <IP_Address:Port>");
            return;
        }

        String dbServiceName = "";
        if (cmdLine.hasOption(Args.DB_SERVICE_NAME)) {
            dbServiceName = cmdLine.getOptionValue(Args.DB_SERVICE_NAME);
        } else {
            logger.error("Must provide Database Service Name");
            return;
        }

        String dbUsername = "";
        if (cmdLine.hasOption(Args.DB_USERNAME)) {
            dbUsername = cmdLine.getOptionValue(Args.DB_USERNAME);
        } else {
            logger.error("Must provide Database username, e.g. JETSPEED");
            return;
        }

        String dbPassword = "";
        if (cmdLine.hasOption(Args.DB_PASSWORD)) {
            dbPassword = cmdLine.getOptionValue(Args.DB_PASSWORD);
        } else {
            logger.error("Must provide Database password");
            return;
        }

        OrderService orderService = new OrderService(new DBClientImpl(dbUrl,
                dbServiceName, dbUsername, dbPassword));

        // output as XML
        if (output.equalsIgnoreCase("XML")) {
            System.out.println(orderService.orderAsXml(orderId));
        }
        // output as json
        else if (output.equalsIgnoreCase("JSON")) {
            System.out.println(orderService.orderAsJson(orderId));
        }
        // Default output is text
        else {
            System.out.println(orderService.orderAsText(orderId));
        }
    }

    /**
     * Queries the command line entries, checks user args are valid
     */
    public void process() {
        if (!validHelpOption()) {
            inValidUsageOfHelp(options);
        }
        interrogateArgs();
    }

    /**
     * @param cmdLine
     *            the cmdLine to set
     */
    public void setCmdLine(CommandLine cmdLine) {
        this.cmdLine = cmdLine;
    }

    /**
     * @param options
     *            the options to set
     */
    public void setOptions(Options options) {
        this.options = options;
    }

    /**
     * @return valid
     */
    public boolean validHelpOption() {
        Option[] o = cmdLine.getOptions();
        if (cmdLine.hasOption(Args.HELP)) {
            if (o.length > 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Simply return the application name and version
     * 
     * @return version
     */
    public String version() {
        return Args.APP_NAME + " " + Args.VERSION_VALUE;
    }
}