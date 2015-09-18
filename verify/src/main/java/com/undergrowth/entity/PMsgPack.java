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

/**
 * Centrum message pack entity
 * 
 * @author <a href="mailto:wanglianguang@139130.net">Guang Wang</a>
 * @Data 2010-5-7
 * @Version 1.0.0
 */
public class PMsgPack {

	public String uuid;
	
	
	public int state;
	
	public int sendType;
	
	public int msgType;
	
	
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getSendType() {
		return sendType;
	}

	public void setSendType(int sendType) {
		this.sendType = sendType;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * The post time
	 */
	private Date postTime;
	/**
	 * The schedule time
	 */
	private Date scheduleTime;
	/**
	 * Deadline
	 */
	private Date deadline;

	/**
	 * Special service number
	 */
	private String specNum;

	/**
	 * Extend service number
	 */
	private String customNum;

	/**
	 * BOE time
	 */
	private Date boeTime;

	/**
	 * EOE time
	 */
	private Date eoeTime;

	/**
	 * The priority
	 */
	private int priority;
	/**
	 * The level of the message for the carrier
	 */
	private int level;

	/**
	 * The frames of the pack
	 */
	private List<PMsgFrame> frames = new ArrayList<PMsgFrame>();

	/**
	 * Version
	 */
	private int version;

	private int userID;

	private int enterpriseID;

	private int bizType;

	private String batchName;

	private boolean distinct;

	private String from;

	private String remark;

	private long offerTime;

	private String userName;

	private String password;

	private boolean isAudit;

	private int failTime;

	/**
	 * Parameters associated with the frame
	 */
	private Map<String, Object> parameters = new HashMap<String, Object>();

	/**
	 * Default constructor
	 */
	public PMsgPack() {
	}

	/**
	 * {@inheritDoc}
	 */
	public List<PMsgFrame> getFrames() {
		return frames;
	}

	public void removeFrame(PMsgFrame frame) {
		frames.remove(frame);
	}

	public Date getPostTime() {
		return postTime;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date line) {
		this.deadline = line;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getFrameCount() {
		return frames.size();
	}

	public Date getScheduleTime() {
		return scheduleTime;
	}

	public int getVersion() {
		return version;
	}

	public void addFrame(PMsgFrame frame) {
		frames.add(frame);
	}

	public String getCustomNum() {
		return customNum;
	}

	public void setCustomNum(String customNum) {
		this.customNum = customNum;
	}

	public String getSpecNum() {
		return specNum;
	}

	public int getPriority() {
		return priority;
	}

	public int getLevel() {
		return level;
	}

	public void setFrames(List<PMsgFrame> fames) {

		this.frames = fames;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	/**
	 * Set scheduleTime
	 * 
	 * @param scheduleTime
	 *            the scheduleTime to set
	 */
	public void setScheduleTime(Date scheduleTime) {
		this.scheduleTime = scheduleTime;
	}

	/**
	 * Set specNum
	 * 
	 * @param specNum
	 *            the specNum to set
	 */
	public void setSpecNum(String specNum) {
		this.specNum = specNum;
	}

	/**
	 * Set extNum
	 * 
	 * @param extNum
	 *            the extNum to set
	 */
	public void setExtNum(String extNum) {
		this.customNum = extNum;
	}

	/**
	 * Set version
	 * 
	 * @param version
	 *            the version to set
	 */
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

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getEnterpriseID() {
		return enterpriseID;
	}

	public void setEnterpriseID(int enterpriseID) {
		this.enterpriseID = enterpriseID;
	}

	public int getValidFrameCount() {
		int i = 0;
		for (PMsgFrame frame : frames) {
			/*
			 * if (frame.getState() != FrameState.ABANDON) i++;
			 */
			i++;
		}
		return i;
	}

	public int getValidTicketCount() {
		int i = 0;
		for (PMsgFrame frame : frames) {
			/*
			 * if (frame.getState() != FrameState.ABANDON) i +=
			 * frame.getMsgCount();
			 */
			i +=frame.getMsgCount();
		}
		return i;
	}

	public int getInValidTicketCount() {
		int i = 0;
		for (PMsgFrame frame : frames) {
			/*
			 * if (frame.getState() == FrameState.ABANDON) i +=
			 * frame.getMsgCount();
			 */
		}
		return i;
	}

	public void setBizType(int bizType) {
		this.bizType = bizType;
	}

	public int getBizType() {
		return bizType;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getBatchName() {
		return batchName;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public String getFrom() {
		return from;
	}

	public long getOfferTime() {
		return offerTime;
	}

	public void setOfferTime(long offerTime) {
		this.offerTime = offerTime;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
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
		for (String s : map.keySet()) {
			parameters.put(s, map.get(s));
		}
	}

	public boolean isAudit() {
		return isAudit;
	}

	public void setAudit(boolean isAudit) {
		this.isAudit = isAudit;
	}

	public int getFailTime() {
		return failTime;
	}

	public void setFailTime(int failTime) {
		this.failTime = failTime;
	}

	public void addFailTime() {
		failTime++;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
