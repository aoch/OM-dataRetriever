package com.netcracker.omdataretriever.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author Andrew Och
 * @version %I%, %G%
 * @since 1.0
 * 
 */
@XmlRootElement(namespace = "http://com.netcracker.omdataretriever.model", name = "task")
@XmlType(propOrder = { "id", "taskDefinitionId", "taskName", "description",
        "taskType", "taskStatus", "elapsedDate", "processFlowInstanceOid",
        "roleOid", "previousTaskInstanceOid", "assignedPerformedOid",
        "lastModifiedPerformedOid", "performedLastModifiedDate",
        "escalationFlag", "attachment", "serverName", "startDate", "endDate",
        "createDate", "writeLock", "modelInstanceOid", "errorCode",
        "errorPerformedOid" })
public class TaskData {

    private String id;

    private String taskDefinitionId;

    private String taskName;

    private String description;

    private String taskType;

    private String taskStatus;

    private String elapsedDate;

    private String processFlowInstanceOid;

    private String roleOid;

    private String previousTaskInstanceOid;

    private String assignedPerformedOid;

    private String lastModifiedPerformedOid;

    private String performedLastModifiedDate;

    private String escalationFlag;

    private String attachment;

    private String serverName;

    private String startDate;

    private String endDate;

    private String createDate;

    private String writeLock;

    private String modelInstanceOid;

    private String errorCode;

    private String errorPerformedOid;

    /**
     * @return the assignedPerformedOid
     */
    public String getAssignedPerformedOid() {
        return assignedPerformedOid;
    }

    /**
     * @return the attachment
     */
    public String getAttachment() {
        return attachment;
    }

    /**
     * @return the createDate
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the elapsedDate
     */
    public String getElapsedDate() {
        return elapsedDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @return the errorPerformedOid
     */
    public String getErrorPerformedOid() {
        return errorPerformedOid;
    }

    /**
     * @return the escalationFlag
     */
    public String getEscalationFlag() {
        return escalationFlag;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the lastModifiedPerformedOid
     */
    public String getLastModifiedPerformedOid() {
        return lastModifiedPerformedOid;
    }

    /**
     * @return the modelInstanceOid
     */
    public String getModelInstanceOid() {
        return modelInstanceOid;
    }

    /**
     * @return the taskName
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * @return the performedLastModifiedDate
     */
    public String getPerformedLastModifiedDate() {
        return performedLastModifiedDate;
    }

    /**
     * @return the previousTaskInstanceOid
     */
    public String getPreviousTaskInstanceOid() {
        return previousTaskInstanceOid;
    }

    /**
     * @return the processFlowInstanceOid
     */
    public String getProcessFlowInstanceOid() {
        return processFlowInstanceOid;
    }

    /**
     * @return the roleOid
     */
    public String getRoleOid() {
        return roleOid;
    }

    /**
     * @return the serverName
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @return the taskDefinitionId
     */
    public String getTaskDefinitionId() {
        return taskDefinitionId;
    }

    /**
     * @return the taskStatus
     */
    public String getTaskStatus() {
        return taskStatus;
    }

    /**
     * @return the taskType
     */
    public String getTaskType() {
        return taskType;
    }

    /**
     * @return the writeLock
     */
    public String getWriteLock() {
        return writeLock;
    }

    /**
     * @param assignedPerformedOid
     *            the assignedPerformedOid to set
     */
    public void setAssignedPerformedOid(String assignedPerformedOid) {
        this.assignedPerformedOid = assignedPerformedOid;
    }

    /**
     * @param attachment
     *            the attachment to set
     */
    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    /**
     * @param createDate
     *            the createDate to set
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param elapsedDate
     *            the elapsedDate to set
     */
    public void setElapsedDate(String elapsedDate) {
        this.elapsedDate = elapsedDate;
    }

    /**
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @param errorCode
     *            the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @param errorPerformedOid
     *            the errorPerformedOid to set
     */
    public void setErrorPerformedOid(String errorPerformedOid) {
        this.errorPerformedOid = errorPerformedOid;
    }

    /**
     * @param escalationFlag
     *            the escalationFlag to set
     */
    public void setEscalationFlag(String escalationFlag) {
        this.escalationFlag = escalationFlag;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param lastModifiedPerformedOid
     *            the lastModifiedPerformedOid to set
     */
    public void setLastModifiedPerformedOid(String lastModifiedPerformedOid) {
        this.lastModifiedPerformedOid = lastModifiedPerformedOid;
    }

    /**
     * @param modelInstanceOid
     *            the modelInstanceOid to set
     */
    public void setModelInstanceOid(String modelInstanceOid) {
        this.modelInstanceOid = modelInstanceOid;
    }

    /**
     * @param taskName
     *            the taskName to set
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * @param performedLastModifiedDate
     *            the performedLastModifiedDate to set
     */
    public void setPerformedLastModifiedDate(String performedLastModifiedDate) {
        this.performedLastModifiedDate = performedLastModifiedDate;
    }

    /**
     * @param previousTaskInstanceOid
     *            the previousTaskInstanceOid to set
     */
    public void setPreviousTaskInstanceOid(String previousTaskInstanceOid) {
        this.previousTaskInstanceOid = previousTaskInstanceOid;
    }

    /**
     * @param processFlowInstanceOid
     *            the processFlowInstanceOid to set
     */
    public void setProcessFlowInstanceOid(String processFlowInstanceOid) {
        this.processFlowInstanceOid = processFlowInstanceOid;
    }

    /**
     * @param roleOid
     *            the roleOid to set
     */
    public void setRoleOid(String roleOid) {
        this.roleOid = roleOid;
    }

    /**
     * @param serverName
     *            the serverName to set
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    /**
     * @param startDate
     *            the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @param taskDefinitionId
     *            the taskDefinitionId to set
     */
    public void setTaskDefinitionId(String taskDefinitionId) {
        this.taskDefinitionId = taskDefinitionId;
    }

    /**
     * @param taskStatus
     *            the taskStatus to set
     */
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     * @param taskType
     *            the taskType to set
     */
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    /**
     * @param writeLock
     *            the writeLock to set
     */
    public void setWriteLock(String writeLock) {
        this.writeLock = writeLock;
    }

}
