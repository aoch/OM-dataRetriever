package com.netcracker.omdataretriever.db;

import com.netcracker.omdataretriever.model.OrderData;

/**
 * 
 * @author Andrew Och
 * @version %I%, %G%
 * @since 1.0
 *
 */
public interface IDBClient {
    
    /**
     * 
     * @param orderId
     * @return orderData
     */
    public OrderData retrieveOrder(String orderId);
    
    /**
     * 
     * @param orderId
     * @return results
     */
    public String excuteQuery(String orderId);
    
    /**
     * 
     * @return delimiter
     */
    public String getDelimiter();

    /**
     * @param delimiter the delimiter to set
     */
    public void setDelimiter(String delimiter);

}
