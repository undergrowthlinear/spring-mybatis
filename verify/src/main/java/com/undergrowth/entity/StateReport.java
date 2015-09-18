/*
 * Copyright (c) 2010 by XUANWU INFORMATION TECHNOLOGY CO.
 *             All rights reserved
 */
package com.undergrowth.entity;

import java.util.Date;

/**
 * State Report
 *
 * @author <a href="mailto:wanglianguang@139130.netGuang Wang</a>
 * @Data 2010-5-19
 * @Version 1.0.0
 */
public interface StateReport {
	public enum StateReportResult {
		DELIVERED(0), EXPIRED(1), UNDELIVERABLE(2), REJECTED(3), UNKNOWN(4), DELETED(
		        5), WAIT_SEND(6);
		private final int index;

		private StateReportResult(int index) {
			this.index = index;
		}

		public int getIndex() {
			return index;
		}

		public static StateReportResult getType(int index) {
			switch (index) {
			case 0:
				return DELIVERED;
			case 1:
				return EXPIRED;
			case 2:
				return UNDELIVERABLE;
			case 3:
				return REJECTED;
			case 5:
				return DELETED;
			case 6:
				return WAIT_SEND;
			default:
				return UNKNOWN;
			}
		}
	}

	public String getMsgID();

	public void setMsgID(String msgID);

	public Date getSubmitTime();

	public void setSubmitTime(Date submitTime);

	public Date getDoneTime();

	public void setDoneTime(Date doneTime);

	public StateReportResult getState();

	public void setState(StateReportResult state);

	public void setStateIndex(int index);

	public abstract void setSmscSequence(long smscSequence);

	public abstract long getSmscSequence();

	public abstract void setDestPhone(String destPhone);

	public abstract String getDestPhone();

	public abstract void setId(long id);

	public abstract long getId();
	
	public String getOriginResult();

	public void setOriginResult(String originResult);
	
	public int getUserID();
	
	public void setUserID(int userID);
	
	public int getEnterpriseID();
	
	public void setEnterpriseID(int enterpriseID);
	
	public String getPackID();
	
	public void setPackID(String packID);
    
	public int getChannelID();

	public void setChannelID(int channelID);
	
	public String getCustomMsgID();
	
	public void setCustomMsgID(String customMsgID);

}
