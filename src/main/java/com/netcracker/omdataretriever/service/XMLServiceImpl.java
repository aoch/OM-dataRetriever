package com.netcracker.omdataretriever.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Set of helper methods to do common tasks to support XML marshal, unmarshal
 * and parsing.
 * 
 * @author Andrew Och
 * @version %I%, %G%
 * @since 1.0
 * 
 */
public class XMLServiceImpl implements IXMLService {
    private static transient final Logger logger = Logger
            .getLogger(XMLServiceImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.netcracker.omdatatriever.service.IXmlService#marshal(java.lang.Object
     * )
     */
    public Document marshal(Object object) {
        StringWriter stringXml = new StringWriter();
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(object, stringXml);
        } catch (JAXBException e) {
            logger.error(
                    String.format("JAXBException: [%s]",
                            new Object[] { e.getMessage() }), e);
        }
        return string2xml(stringXml.toString());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.netcracker.omdatatriever.service.IXmlService#string2xml(java.lang
     * .String)
     */
    public Document string2xml(String xmlString) {
        Document document = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xmlString));
            document = builder.parse(is);
        } catch (ParserConfigurationException e) {
            logger.error(String.format("ParserConfigurationException: [%s]",
                    new Object[] { e.getMessage() }), e);
        } catch (SAXException e) {
            logger.error(
                    String.format("SAXException: [%s]",
                            new Object[] { e.getMessage() }), e);
        } catch (IOException e) {
            logger.error(
                    String.format("IOException: [%s]",
                            new Object[] { e.getMessage() }), e);
        }
        return document;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.netcracker.omdatatriever.service.IXmlService#unmarshal(java.lang.
     * String, java.lang.String)
     */
    public Object unmarshal(String xmlString, String className) {
        Object object = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Class
                    .forName(className));
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            object = jaxbUnmarshaller.unmarshal(new ByteArrayInputStream(
                    xmlString.getBytes()));
        } catch (ClassNotFoundException e) {
            logger.error(String.format("ClassNotFoundException: [%s]",
                    new Object[] { e.getMessage() }), e);
        } catch (JAXBException e) {
            logger.error(
                    String.format("JAXBException: [%s]",
                            new Object[] { e.getMessage() }), e);
        }
        return object;
    }

    public String xml2String(Document doc) {
        String xmlString = "";
        TransformerFactory tfactory = TransformerFactory.newInstance();
        Transformer xform;
        try {
            xform = tfactory.newTransformer();
            Source src = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            Result result = new StreamResult(writer);

            try {
                xform.transform(src, result);
            } catch (TransformerException e) {
                logger.error(String.format("TransformerException: [%s]",
                        new Object[] { e.getMessage() }), e);
            }
            // We can now extract the DOM as a text string by using the toString
            // method on the StringWriter that we created.
            xmlString = writer.toString();
        } catch (TransformerConfigurationException e) {
            logger.error(String.format(
                    "TransformerConfigurationException: [%s]",
                    new Object[] { e.getMessage() }), e);
        }
        return xmlString;
    }

    public String xml2json(String xmlString) {
        String jsonString = "";
        try {
            InputStream is = new ByteArrayInputStream(xmlString.getBytes());
            String xml = IOUtils.toString(is);
            XMLSerializer xmlSerializer = new XMLSerializer();
            JSON json = xmlSerializer.read(xml);
            jsonString = json.toString(2);
        } catch (IOException e) {
            logger.error(
                    String.format("IOException: [%s]",
                            new Object[] { e.getMessage() }), e);
        }
        return jsonString;
    }
}
