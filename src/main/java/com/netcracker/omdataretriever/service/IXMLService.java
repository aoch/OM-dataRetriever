package com.netcracker.omdataretriever.service;

import org.w3c.dom.Document;

/**
 * 
 * @author Andrew Och
 * @version %I%, %G%
 * @since 1.0
 * 
 */
public interface IXMLService {

    /**
     * marshal an Object to an XML DOM
     * 
     * @param object
     * @return xmlDocument
     */
    public abstract Document marshal(Object object);

    /**
     * Convert a String to an XML DOM
     * 
     * @param xmlString
     * @return xmlDocument
     */
    public abstract Document string2xml(String xmlString);

    /**
     * Un-marshal an Object to an XML DOM
     * 
     * @param xmlString
     * @param className
     * @return object
     */
    public abstract Object unmarshal(String xmlString, String className);

    /**
     * 
     * @param doc
     * @return xmlString
     */
    public String xml2String(Document doc);
    
    /**
     * 
     * @param xmlString
     * @return jsonString
     */
    public String xml2json(String xmlString);
}