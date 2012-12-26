package com.netcracker.omdataretriever.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * 
 * @author Andrew Och
 * @version %I%, %G%
 * @since 1.0
 * 
 */
@XmlRootElement(namespace = "http://com.netcracker.omdataretriever.model", name = "order")
public class OrderData {

    // XMLElementWrapper generates a wrapper element around XML representation
    @XmlElementWrapper(name = "order")
    // XmlElement sets the name of the entities
    @XmlElement(name = "task")
    private ArrayList<TaskData> taskData;

    /**
     * @param taskData
     *            the taskData to set
     */
    public void setTaskData(ArrayList<TaskData> taskData) {
        this.taskData = taskData;
    }

}
