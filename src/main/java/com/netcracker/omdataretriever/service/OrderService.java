package com.netcracker.omdataretriever.service;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import com.netcracker.omdataretriever.db.DBClientImpl;
import com.netcracker.omdataretriever.db.IDBClient;
import com.netcracker.omdataretriever.model.OrderData;

/**
 * Order Service provides the business logic to return an order in the format
 * required
 * 
 * @author Andrew Och
 * @version %I%, %G%
 * @since 1.0
 * 
 */
public class OrderService {
    private static transient final Logger logger = Logger
            .getLogger(OrderService.class);

    private IXMLService xmlService;

    private IDBClient dbClient;

    /**
     * Default constructor
     */
    public OrderService() {
        this(new XMLServiceImpl(), null);
    }

    /**
     * 
     * @param dbClient
     */
    public OrderService(IDBClient dbClient) {
        this(new XMLServiceImpl(), dbClient);
    }

    /**
     * 
     * @param datasource
     */
    public OrderService(DataSource datasource) {
        this(new XMLServiceImpl(), new DBClientImpl(datasource));
    }

    /**
     * 
     * @param xmlService
     * @param dbClient
     */
    public OrderService(IXMLService xmlService, IDBClient dbClient) {
        logger.debug("OrderService uses IDBClient impl and xmlService impl to return order");
        this.xmlService = xmlService;
        this.dbClient = dbClient;
    }

    /**
     * 
     * @param orderData
     * @return orderXml
     */
    public String orderAsXml(OrderData orderData) {
        Document xml = xmlService.marshal(orderData);
        return xmlService.xml2String(xml);
    }

    /**
     * 
     * @param orderId
     * @return orderXml
     */
    public String orderAsXml(String orderId) {
        Document xml = xmlService.marshal(dbClient.retrieveOrder(orderId));
        return xmlService.xml2String(xml);
    }

    /**
     * 
     * @param orderId
     * @return orderJson
     */
    public String orderAsJson(String orderId) {
        return xmlService.xml2json(orderAsXml(orderId));
    }

    /**
     * 
     * @param orderId
     * @return orderStr
     */
    public String orderAsText(String orderId) {
        return dbClient.excuteQuery(orderId);
    }

    /**
     * 
     * @param orderId
     * @return orderData
     */
    public OrderData retrieveOrder(String orderId) {
        return dbClient.retrieveOrder(orderId);
    }
}
