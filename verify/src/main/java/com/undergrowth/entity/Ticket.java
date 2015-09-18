/*
 * Copyright (c) 2010 by XUANWU INFORMATION TECHNOLOGY CO.
 *             All rights reserved
 */
package com.undergrowth.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Message ticket
 * 
 * @author <a href="mailto:wanglianguang@139130.netGuang Wang</a>
 * @Data 2010-6-4
 * @Version 1.0.0
 */
public class Ticket {

	private static final long END_TICKET_SEQENCENUM = -1L;

	/**
	 * 网关返回唯一标识
	 */
	private String msgID;

	/**
	 * 处理等待结束时间
	 */
	private Date deadline;

	/**
	 * 所属短信帧ID
	 */
	private Long frameID;

	/**
	 * 目标手机号码
	 */
	public String phone;

	/**
	 * 当前的短信类型
	 */
	private SMSType smsType;

	/**
	 * 发送特服号码
	 */
	private String specNumber;

	/**
	 * 为长短信分组发送时，短信的总数量
	 * */
	private int total;
	/**
	 * 当前短信所处分组中的序列号
	 */
	private int number;

	/**
	 * 前置内容
	 */
	private String preContent = "";

	/**
	 * 短信内容
	 */
	private String content;

	/**
	 * 后置内容
	 */
	private String sufContent = "";

	/**
	 * 组ID，用来标识一组长短信
	 */
	private int groupIdentity;

	/**
	 * MTO identity
	 */
	private int mtoID;

	/**
	 * 话单主键
	 */
	private Long id;

	/**
	 * CMPP协议序列号
	 */
	private Long sequenceNumber;

	/**
	 * 话单处理结果信息
	 */
	private String message;

	/**
	 * 父话单ID，长短信时使用
	 */
	private Long parentID;

	/**
	 * 用来标记业务处理失败次数
	 */
	private int failTime = 0;
	/**
	 * 当前话单状态
	 */
	private TicketState state = TicketState.WAIT;

	/**
	 * 当前话单所属的子话单
	 */
	private List<Ticket> subTickets = new ArrayList<Ticket>();

	/**
	 * 用户自定义消息 ID
	 */
	private String customMsgID;

	/**
	 * 提交时间
	 */
	private long submitTime;

	/**
	 * 提交响应时间
	 */
	private Date submitRespTime;

	/**
	 * Default constructor
	 */
	public Ticket() {

	}

	private Ticket(TicketState state) {
		this.sequenceNumber = END_TICKET_SEQENCENUM;
		this.state = state;
	}

	public Ticket(String phone, String content, String customMsgID) {
		this.phone = phone;
		// TODO: need handle
		this.smsType = SMSType.NSMS;
		this.content = content;
		this.state = TicketState.SYS_DENY;
		this.msgID = "";
		this.customMsgID = customMsgID;
	}

	/**
	 * 帧处理结束标志
	 * 
	 * @return
	 */
	public static Ticket createEndStatTicket() {
		return new Ticket(TicketState.INNER_END_FLAG);

	}

	/**
	 * Get msgType
	 * 
	 * @return the msgType
	 */
	public SMSType getSmsType() {
		return smsType;
	}

	/**
	 * Set msgType
	 * 
	 * @param msgType
	 *            the msgType to set
	 */
	public void setSmsType(SMSType msgType) {
		this.smsType = msgType;
	}

	/**
	 * Set msgType
	 * 
	 * @param msgType
	 *            the msgType to set
	 */
	public void setSmsTypeIndex(int index) {
		this.smsType = SMSType.getType(index);
	}

	/**
	 * Get total
	 * 
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * Set total
	 * 
	 * @param total
	 *            the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * Get number
	 * 
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Set number
	 * 
	 * @param number
	 *            the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * Get content
	 * 
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Set content
	 * 
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Get singlePhone
	 * 
	 * @return the singlePhone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Set singlePhone
	 * 
	 * @param phone
	 *            the singlePhone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Get massIdentity
	 * 
	 * @return the massIdentity
	 */
	public int getGroupIdentity() {
		return groupIdentity;
	}

	/**
	 * Set massIdentity
	 * 
	 * @param massIdentity
	 *            the massIdentity to set
	 */
	public void setGroupIdentity(int massIdentity) {
		this.groupIdentity = massIdentity;
	}

	/**
	 * Get mtoID
	 * 
	 * @return the mtoID
	 */
	public int getMtoID() {
		return mtoID;
	}

	/**
	 * Set mtoID
	 * 
	 * @param mtoID
	 *            the mtoID to set
	 */
	public void setMtoID(int mtoID) {
		this.mtoID = mtoID;
	}

	/**
	 * Get id
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set id
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public void setIdSeqNum(Long id) {
		this.id = id;
		this.sequenceNumber = id & 0xFFFFFFFFL;
	}

	/**
	 * Get parentID
	 * 
	 * @return the parentID
	 */
	public Long getParentID() {
		return parentID;
	}

	/**
	 * Set parentID
	 * 
	 * @param parentID
	 *            the parentID to set
	 */
	public void setParentID(Long parentID) {
		this.parentID = parentID;
	}

	/**
	 * Get frameID
	 * 
	 * @return the frameID
	 */
	public Long getFrameID() {
		return frameID;
	}

	/**
	 * Set frameID
	 * 
	 * @param frameID
	 *            the frameID to set
	 */
	public void setFrameID(Long frameID) {
		this.frameID = frameID;
	}

	/**
	 * Get subTickets
	 * 
	 * @return the subTickets
	 */
	public List<Ticket> getSubTickets() {
		return subTickets;
	}

	/**
	 * Set subTickets
	 * 
	 * @param subTickets
	 *            the subTickets to set
	 */
	public void setSubTickets(List<Ticket> subTickets) {
		this.subTickets = subTickets;
	}

	public void addSubTicket(Ticket ticket) {
		this.subTickets.add(ticket);
	}

	/**
	 * Get sequenceNumber
	 * 
	 * @return the sequenceNumber
	 */
	public Long getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * Set sequenceNumber
	 * 
	 * @param sequenceNumber
	 *            the sequenceNumber to set
	 */
	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	/**
	 * Get failTime
	 * 
	 * @return the failTime
	 */
	public int getFailTime() {
		return failTime;
	}

	/**
	 * Set failTime
	 * 
	 * @param failTime
	 *            the failTime to set
	 */
	public void setFailTime(int failTime) {
		this.failTime = failTime;
	}

	/**
	 * Get state
	 * 
	 * @return the state
	 */
	public TicketState getState() {
		return state;
	}

	/**
	 * Set state
	 * 
	 * @param state
	 *            the state to set
	 */
	public void setState(TicketState state) {
		this.state = state;
	}

	/**
	 * Set state
	 * 
	 * @param state
	 *            the state to set
	 */
	public void setStateIndex(int index) {
		this.state = TicketState.getState(index);
	}

	/**
	 * Get deadline
	 * 
	 * @return the deadline
	 */
	public Date getDeadline() {
		return deadline;
	}

	/**
	 * Set deadline
	 * 
	 * @param deadline
	 *            the deadline to set
	 */
	public void setdeadline(Date deadline) {
		this.deadline = deadline;
	}

	/**
	 * Add the attempts
	 */
	public void addFailTime() {
		failTime++;
	}

	/**
	 * Get state info
	 * 
	 * @return
	 */
	public int getStateIndex() {
		return state.getIndex();
	}

	/**
	 * Get msgID
	 * 
	 * @return the msgID
	 */
	public String getMsgID() {
		return msgID;
	}

	/**
	 * Set msgID
	 * 
	 * @param msgID
	 *            the msgID to set
	 */
	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}

	/**
	 * Get message
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Set message
	 * 
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public enum TicketState {
		WAIT(0), SUCCESS(1), REJECTED(2), INCORRUPT_DATA(3), ATTEMPT_FAILED(4), INNER_END_FLAG(
				5), SEQUENCE_ERROR(6), SYS_DENY(7);
		private final int index;

		private TicketState(int index) {
			this.index = index;
		}

		public int getIndex() {
			return index;
		}

		public static TicketState getState(int index) {
			switch (index) {
			case 0:
				return WAIT;
			case 1:
				return SUCCESS;
			case 2:
				return REJECTED;
			case 3:
				return INCORRUPT_DATA;
			case 4:
				return ATTEMPT_FAILED;
			case 6:
				return SEQUENCE_ERROR;
			case 7:
				return SYS_DENY;
			default:
				return INNER_END_FLAG;
			}
		}
	}

	public enum SMSType {
		NSMS(0), LPSMS(1), LSSMS(2), WP(3), MMS(4), MPSMS(5), MSSMS(6);
		private final int index;

		private SMSType(int index) {
			this.index = index;
		}

		public int getIndex() {
			return index;
		}

		public static SMSType getType(int index) {
			switch (index) {
			case 0:
				return NSMS;
			case 1:
				return LPSMS;
			case 2:
				return LSSMS;
			case 3:
				return WP;
			case 4:
				return MMS;
			case 5:
				return MPSMS;
			case 6:
				return MSSMS;
			default:
				return NSMS;
			}
		}
	}

	public String getSpecNumber() {
		return specNumber;
	}

	public void setSpecNumber(String specNumber) {
		this.specNumber = specNumber;
	}

	public String getCustomMsgID() {
		return customMsgID;
	}

	public void setCustomMsgID(String customMsgID) {
		this.customMsgID = customMsgID;
	}

	public long getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(long submitTime) {
		this.submitTime = submitTime;
	}

	public Date getSubmitRespTime() {
		return submitRespTime;
	}

	public void setSubmitRespTime(Date submitRespTime) {
		this.submitRespTime = submitRespTime;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getPreContent() {
		return preContent;
	}

	public void setPreContent(String preContent) {
		this.preContent = preContent;
	}

	public String getSufContent() {
		return sufContent;
	}

	public void setSufContent(String sufContent) {
		this.sufContent = sufContent;
	}

}
