package com.netcracker.omdataretriever;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.netcracker.omdataretriever.cli.ArgumentParser;

/**
 * @author Andrew Och
 * @version %I%, %G%
 * @since 1.0
 */
public class Main {
    private static Logger logger = Logger.getLogger(Main.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        checkJavaVersion();
        new ArgumentParser(args);
    }

    /**
     * Check the correct version of java is being used.
     */
    public static void checkJavaVersion() {
        Properties properties = System.getProperties();
        String javaVersion = (String) properties.get("java.version");
        if (javaVersion.startsWith("1.5") || javaVersion.startsWith("1.6")
                || javaVersion.startsWith("1.7")
                || javaVersion.startsWith("1.8")) {
            logger.debug(String.format("Java Version: [%s] OK",
                    new Object[] { javaVersion }));
        } else {
            String errorMsg = String
                    .format("This requires Java Version 6 or higher but your version is [%s] ",
                            new Object[] { javaVersion });
            System.out.println(errorMsg);
            System.err.println(errorMsg);
            System.exit(-1);
        }
    }

}
