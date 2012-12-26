package com.netcracker.omdataretriever.db;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;


/**
 * 
 * @author Andrew Och
 * @version %I%, %G%
 * @since 1.0
 * 
 */
public class QueryService {
    private static transient final Logger logger = Logger
            .getLogger(QueryService.class);

    private static final String queryClassPath = "/com/netcracker/omdataretriever/db/sql/";

    private static final String orderIdSqlFilename = "orderData";

    /**
     * TODO Remove, just for testing
     * 
     * @param args
     */
    public static void main(String[] args) {
        QueryService queryService = new QueryService();
        queryService.getOrderIdQuery();
    }

    private static QueryService queryService;

    private QueryService() {

    }

    public static QueryService getInstance() {
        if (queryService == null) {
            queryService = new QueryService();
        }
        return queryService;
    }

    /**
     * 
     * @param sqlFilename
     * @return sqlString
     */
    public String getOrderIdQuery() {

        logger.debug(String.format("orderIdSqlFilename: [%s]",
                new Object[] { orderIdSqlFilename }));

        String resourceFile = queryClassPath + orderIdSqlFilename + ".sql";

        logger.debug(String.format("resourceFile: [%s]",
                new Object[] { resourceFile }));

        String sqlString = "";

        try {
            InputStream inputStream = QueryService.class
                    .getResourceAsStream(resourceFile);
            StringWriter writer = new StringWriter();
            IOUtils.copy(inputStream, writer, "UTF-8");
            sqlString = writer.toString();
        } catch (IOException e) {
            logger.error(String.format(
                    "Failed to read sqlFilename: [%s] IOException: [%s]",
                    new Object[] { orderIdSqlFilename, e.getMessage() }));
        } catch (Exception e) {
            logger.error(String
                    .format("Failed to read sqlFilename: [%s] resourceFile: [%s] Exception: [%s]",
                            new Object[] { orderIdSqlFilename, resourceFile,
                                    e.getMessage() }));
        }

        logger.debug(String.format("sqlString: [%s]",
                new Object[] { sqlString }));
        return sqlString;
    }

}
