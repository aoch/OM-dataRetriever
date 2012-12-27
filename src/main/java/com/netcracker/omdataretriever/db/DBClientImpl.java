package com.netcracker.omdataretriever.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.netcracker.omdataretriever.model.OrderData;
import com.netcracker.omdataretriever.model.TaskData;

/**
 * Uses JDBC to query database
 * 
 * @author Andrew Och
 * @version %I%, %G%
 * @since 1.0
 * 
 */
public class DBClientImpl implements IDBClient {
    private static transient final Logger logger = Logger
            .getLogger(DBClientImpl.class);

    private static ResourceBundle resourceBundle = ResourceBundle
            .getBundle("com.netcracker.omdataretriever.db/config.databaseConfig");

    public static final String TNS_SERVICENAME = resourceBundle
            .getString("tns_service_name");

    public static final String THIN_CLIENT = resourceBundle
            .getString("thin_client");

    private String delimiter = "\t";

    private DataSource dataSource;

    private Connection connection = null;

    private String password;

    private String url;

    private String serviceName;

    private String username;

    private QueryService queryService = QueryService.getInstance();

    /**
     * 
     * @param dataSource
     */
    public DBClientImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 
     * @param dbUrl
     * @param serviceName
     * @param dbUsername
     * @param dbPassword
     */
    public DBClientImpl(String dbUrl, String dbServiceName, String dbUsername,
            String dbPassword) {
        this.username = dbUsername;
        this.password = dbPassword;
        this.serviceName = dbServiceName;
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("username: [%s], serviceName: [%s]",
                    new Object[] { this.username, this.serviceName }));
        }
        // setUrl MUST come after SID is set
        this.setUrl(dbUrl);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.netcracker.omdatatriever.db.IDBClient#excuteQuery(java.lang.String)
     */
    public String excuteQuery(String orderId) {
        StringBuffer results = new StringBuffer("");
        initConnectionStatement();

        String orderIdSql = queryService.getOrderIdQuery();
        try {
            PreparedStatement orderIdStatement = connection
                    .prepareStatement(orderIdSql);
            orderIdStatement.setString(1, orderId);
            orderIdStatement.setString(2, orderId);
            ResultSet resultSet = orderIdStatement.executeQuery();

            results.append(getColumnNames(resultSet));

            while (resultSet.next()) {
                results.append(resultSet.getString(1) + delimiter);
                results.append(resultSet.getString(2) + delimiter);
                results.append(resultSet.getString(3) + delimiter);
                results.append(resultSet.getString(4) + delimiter);
                results.append(resultSet.getString(5) + delimiter);
                results.append(resultSet.getString(6) + delimiter);
                results.append(resultSet.getString(7) + delimiter);
                results.append(resultSet.getString(8) + delimiter);
                results.append(resultSet.getString(9) + delimiter);
                results.append(resultSet.getString(10) + delimiter);
                results.append(resultSet.getString(11) + delimiter);
                results.append(resultSet.getString(12) + delimiter);
                results.append(resultSet.getString(13) + delimiter);
                results.append(resultSet.getString(14) + delimiter);
                results.append(resultSet.getString(15) + delimiter);
                results.append(resultSet.getString(16) + delimiter);
                results.append(resultSet.getString(17) + delimiter);
                results.append(resultSet.getString(18) + delimiter);
                results.append(resultSet.getString(19) + delimiter);
                results.append(resultSet.getString(20) + delimiter);
                results.append(resultSet.getString(21) + delimiter);
                results.append(resultSet.getString(22) + delimiter);
                results.append(resultSet.getString(23) + delimiter);
                results.append("\n");
            }
        } catch (SQLException e) {
            logger.error(String.format(
                    "Failed to read orderId: [%s] SQLException: [%s]",
                    new Object[] { orderId, e.getMessage() }));
        }

        return results.toString();
    }

    /**
     * all column names for a result set
     * 
     * @param resultSet
     * @return Column names
     * @throws SQLException
     */
    public String getColumnNames(ResultSet resultSet) throws SQLException {
        StringBuffer columns = new StringBuffer("");
        if (resultSet == null) {
            return columns.toString();
        }

        // get result set meta data
        ResultSetMetaData rsMetaData = resultSet.getMetaData();
        int numberOfColumns = rsMetaData.getColumnCount();

        // get the column names; column indexes start from 1
        for (int i = 1; i < numberOfColumns + 1; i++) {
            String columnName = rsMetaData.getColumnName(i);
            // Get the name of the column's table name
            String tableName = rsMetaData.getTableName(i);
            logger.debug(String.format("column: [%s] table: [%s]",
                    new Object[] { columnName, tableName }));
            columns.append(columnName + delimiter);
        }
        columns.append("\n");

        return columns.toString();
    }

    /**
     * @return the dataSource
     */
    public DataSource getDataSource() {
        return dataSource;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.String#excuteQuery()
     */
    public String getDelimiter() {
        return delimiter;
    }

    /**
     * 
     * @return url
     */
    public String getUrl() {
        return url;
    }

    private void initConnectionStatement() {
        try {

            // Create connection to database
            if (connection == null) {

                // DataSource passed in, e.g. via J2EE Container
                if (dataSource != null) {
                    connection = dataSource.getConnection();
                }
                // manually setup database connection e.g. from command line
                // input details
                else {
                    String dbDriver = resourceBundle.getString("db_driver");
                    logger.debug(String.format(
                            "DB Driver is: [%s] url: [%s] username: [%s]",
                            new Object[] { dbDriver, url, username }));

                    Class.forName(dbDriver);
                    connection = DriverManager.getConnection(url, username,
                            password);
                }
            }
        } catch (Exception e) {
            logger.fatal(String.format("Exception: [%s]",
                    new Object[] { e.getMessage() }));
        }
    }

    /**
     * This does NOT close the Statement
     * 
     * @param url
     * @param username
     * @param password
     * @param sql
     * @return resultSet
     */
    public ResultSet query(String url, String username, String password,
            String sql) {
        ResultSet resultSet = null;
        try {
            initConnectionStatement();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (Exception e) {
            logger.fatal(e.getMessage());
        }
        return resultSet;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.netcracker.omdatatriever.model.OrderData#retrieveOrder(java.lang.
     * String)
     */
    public OrderData retrieveOrder(String orderId) {
        OrderData orderData = new OrderData();
        ArrayList<TaskData> taskData = new ArrayList<TaskData>();
        initConnectionStatement();
        String orderIdSql = queryService.getOrderIdQuery();

        if (connection == null) {
            logger.fatal(String.format("Could NOT connect to database: [%s]",
                    new Object[] { url }));
            return orderData;
        }

        try {
            PreparedStatement orderIdStatement = connection
                    .prepareStatement(orderIdSql);
            orderIdStatement.setString(1, orderId);
            orderIdStatement.setString(2, orderId);
            ResultSet resultSet = orderIdStatement.executeQuery();

            while (resultSet.next()) {
                TaskData taskDataElement = new TaskData();
                taskDataElement.setId(resultSet.getString(1));
                taskDataElement.setTaskDefinitionId(resultSet.getString(2));
                taskDataElement.setTaskName(resultSet.getString(3));
                taskDataElement.setDescription(resultSet.getString(4));
                taskDataElement.setTaskType(resultSet.getString(5));
                taskDataElement.setTaskStatus(resultSet.getString(6));
                taskDataElement.setElapsedDate(resultSet.getString(7));
                taskDataElement.setProcessFlowInstanceOid(resultSet
                        .getString(8));
                taskDataElement.setRoleOid(resultSet.getString(9));
                taskDataElement.setPreviousTaskInstanceOid(resultSet
                        .getString(10));
                taskDataElement
                        .setAssignedPerformedOid(resultSet.getString(11));
                taskDataElement.setLastModifiedPerformedOid(resultSet
                        .getString(12));
                taskDataElement.setPerformedLastModifiedDate(resultSet
                        .getString(13));
                taskDataElement.setEscalationFlag(resultSet.getString(14));
                taskDataElement.setAttachment(resultSet.getString(15));
                taskDataElement.setServerName(resultSet.getString(16));
                taskDataElement.setStartDate(resultSet.getString(17));
                taskDataElement.setEndDate(resultSet.getString(18));
                taskDataElement.setCreateDate(resultSet.getString(19));
                taskDataElement.setWriteLock(resultSet.getString(20));
                taskDataElement.setModelInstanceOid(resultSet.getString(21));
                taskDataElement.setErrorCode(resultSet.getString(22));
                taskDataElement.setErrorPerformedOid(resultSet.getString(23));
                taskData.add(taskDataElement);
            }
        } catch (SQLException e) {
            logger.error(String.format(
                    "Failed to read orderId: [%s] SQLException: [%s]",
                    new Object[] { orderId, e.getMessage() }));
        }

        orderData.setTaskData(taskData);
        return orderData;
    }

    /**
     * @param dataSource
     *            the dataSource to set
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /*
     * (non-Javadoc)
     * 
     * @see void#excuteQuery(java.lang.String)
     */
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Takes an argument <code>127.0.0.1:1521</code> and splits it so that IP
     * and port set correctly
     * 
     * @param url
     */
    public void setUrl(String url) {
        String ip = "";
        String port = "";
        String ipPort = "";

        // User defined
        if (url != null && !url.equalsIgnoreCase("") && url.contains(":")) {
            ipPort = url;

        }
        // Default
        else {
            ipPort = resourceBundle.getString("url_database");
        }

        String[] ipPortArr = ipPort.split(":");
        ip = ipPortArr[0];
        port = ipPortArr[1];
        this.url = THIN_CLIENT
                + String.format(TNS_SERVICENAME, new Object[] { ip, port,
                        serviceName });
        String infoMsg = String.format(
                "url is null or empty, setting default value: %s",
                new Object[] { this.url });
        logger.info(infoMsg);
    }

    /**
     * 
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

}
