/*
 * Copyright (c) 2010 by XUANWU INFORMATION TECHNOLOGY CO. 
 *             All rights reserved                         
 */
package com.undergrowth.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * @author <a href="mailto:wanglianguang@139130.netGuang Wang</a>
 * @Data 2010-5-12
 * @Version 1.0.0
 */
public class PMsgFrame {

	/**
	 * Parent pack UUID
	 */
	
	
	
	protected int userID;

	protected int enterpriseID;

	protected int actualChannelId;

	private String packUUID;
	
	private int msgType;
	
	private int state;
	
	private String pack;
	
	
	
	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	public int getBizForm() {
		return bizForm;
	}

	public void setBizForm(int bizForm) {
		this.bizForm = bizForm;
	}

	private int sendType;
	
	private int bizForm;
	
	
	
	public int getBiz_form() {
		return bizForm;
	}

	public void setBiz_form(int bizForm) {
		this.bizForm = bizForm;
	}

	public int getSendType() {
		return sendType;
	}

	public void setSendType(int sendType) {
		this.sendType = sendType;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public String getPackUUID() {
		return packUUID;
	}

	public void setPackUUID(String packUUID) {
		this.packUUID = packUUID;
	}

	public boolean isStateReport() {
		return isStateReport;
	}

	public void setStateReport(boolean isStateReport) {
		this.isStateReport = isStateReport;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	/**
	 * Identity of the frame
	 */
	protected Long id;

	/**
	 * Extend number
	 */
	protected String customNum;

	/**
	 * The post time of the frame
	 */
	protected Date postTime;

	/**
	 * Commit the send time
	 */
	protected Date commitTime;

	/**
	 * The schedule time
	 */
	protected Date scheduleTime;

	/**
	 * The time of retrieve
	 */
	protected Date retrieveTime;

	/**
	 * The deadline of the frame
	 */
	protected Date deadline;

	/**
	 * The priority
	 */
	protected int priority;

	/**
	 * Begin of time range
	 */
	protected Date boeTime;

	/**
	 * End of time range
	 */
	protected Date eoeTime;

	/**
	 * State report state
	 */
	protected boolean isStateReport;

	/**
	 * Version
	 */
	protected int version;

	protected int bizType;

	/**
	 * Parameters associated with the frame
	 */
	protected Map<String, Object> parameters = new HashMap<String, Object>();

	/**
	 * Template attributes
	 */
	protected Map<String, Object> attributes = new HashMap<String, Object>();

	/**
	 * mms smil content
	 */
	protected String smil;
	/**
	 * mms title
	 */
	protected String title;

	protected boolean isPreCommit = false;

	public PMsgFrame() {
	}

	public Date getDeadline() {
		return deadline;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date time) {
		this.postTime = time;
	}

	public int getMsgCount(){
		return 1;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Date getScheduleTime() {
		return scheduleTime;
	}

	public void setScheduleTime(Date time) {
		this.scheduleTime = time;
	}

	public Date getRetrieveTime() {
		return retrieveTime;
	}

	public void setRetrieveTime(Date time) {
		this.retrieveTime = time;
	}

	public String getCustomNum() {
		return customNum;
	}

	public void setCustomNum(String extendNum) {
		this.customNum = extendNum;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Date getBoeTime() {
		return boeTime;
	}

	public void setBoeTime(Date boeTime) {
		this.boeTime = boeTime;
	}

	public Date getEoeTime() {
		return eoeTime;
	}

	public void setEoeTime(Date eoeTime) {
		this.eoeTime = eoeTime;
	}

	public boolean getReportState() {
		return isStateReport;
	}

	public void setReportState(boolean flag) {
		this.isStateReport = flag;
	}

	public void addParameter(String key, String value) {
		parameters.put(key, value);
	}

	public Object getParameter(String key) {
		return parameters.get(key);
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> map) {
		this.parameters = map;
	}

	/**
	 * Tickets associated with the frame
	 */
	protected List<Ticket> tickets = new ArrayList<Ticket>();

	/**
	 * The level of the message for the carrier
	 */
	protected int level;

	/**
	 * The whole special service number
	 */
	protected String specNumber;

	protected String assignSpecIDs;

	protected String assignChannelIDs;

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Date getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(Date commitTime) {
		this.commitTime = commitTime;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public String getSpecNumber() {
		return specNumber;
	}

	public void setSpecNumber(String specNumber) {
		this.specNumber = specNumber;
	}

	public String getWholeSpecNumber() {
		return specNumber + customNum;
	}

	public Object getAttribute(String key) {
		return attributes.get(key);
	}

	public void setAttribute(String key, Object value) {
		attributes.put(key, value);
	}

	public int getEnterpriseID() {
		return enterpriseID;
	}

	public void setEnterpriseID(int enterpriseID) {
		this.enterpriseID = enterpriseID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getBizType() {
		return bizType;
	}

	public void setBizType(int bizType) {
		this.bizType = bizType;
	}

	public String getAssignSpecIDs() {
		return assignSpecIDs;
	}

	public void setAssignSpecIDs(String assignSpecIDs) {
		this.assignSpecIDs = assignSpecIDs;
	}

	public String getAssignChannelIDs() {
		return assignChannelIDs;
	}

	public void setAssignChannelIDs(String assignChannelIDs) {
		this.assignChannelIDs = assignChannelIDs;
	}

	public int getActualChannelId() {
		return actualChannelId;
	}

	public void setActualChannelId(int actualChannelId) {
		this.actualChannelId = actualChannelId;
	}

	public String getSmil() {
		return smil;
	}

	public void setSmil(String smil) {
		this.smil = smil;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isPreCommit() {
		return isPreCommit;
	}

	public void setPreCommit(boolean isPreCommit) {
		this.isPreCommit = isPreCommit;
	}

}
