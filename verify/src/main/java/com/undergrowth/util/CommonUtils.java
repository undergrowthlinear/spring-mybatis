package com.undergrowth.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.undergrowth.entity.PMsgFrame;
import com.undergrowth.entity.PMsgPack;

/**
 * 
* @Description: TODO(这里用一句话描述这个类的作用)
* @Author <a href="zhangwu@wxchina.com">Wu.Zhang</a>
* @Date 2015年9月18日
* @Version 1.0.0
 */
public class CommonUtils {

	private int num;
	
	
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * 
	 * @return
	 */
	public  List<PMsgPack> produce() {
		// TODO Auto-generated method stub
		List<PMsgPack> pMsgPacks=new ArrayList<PMsgPack>();
		//产生大量PMsgPack PMsgFrame
		for (int i = 0; i < num; i++) {
			pMsgPacks.add(createMsgPack());
		}
		return pMsgPacks;
	}

	private  PMsgPack createMsgPack() {
		// TODO Auto-generated method stub
		PMsgPack pack=new PMsgPack();
		UUID uuid=UUID.randomUUID();
		int userId=2;
		int enterpriseID=1;
		int sendType=1;
		int msgType=1;
		int bizType=1;
		Date date=new Date();
		pack.setUserID(userId);
		pack.setEnterpriseID(enterpriseID);
		pack.setUuid(uuid.toString());
		pack.setBatchName("测试包批处理名称");
		pack.setSendType(sendType);
		pack.setMsgType(msgType);
		pack.setBizType(bizType);
		pack.setPostTime(date);
		pack.setEoeTime(date);
		pack.setBoeTime(date);
		pack.addFrame(createMsgFrame(pack));
		return pack;
	}

	private  PMsgFrame createMsgFrame(PMsgPack pack) {
		// TODO Auto-generated method stub
		PMsgFrame msgFrame=new PMsgFrame();
		msgFrame.setId(1l);
		msgFrame.setPackUUID(pack.getUuid());
		msgFrame.setUserID(pack.getUserID());
		msgFrame.setEnterpriseID(pack.getEnterpriseID());
		msgFrame.setAssignSpecIDs("2");
	    //设置pack的content
		msgFrame.setMsgType(pack.getMsgType());
		msgFrame.setBizType(pack.getBizType());
		msgFrame.setState(2);
		msgFrame.setSendType(pack.getSendType());
		msgFrame.setBiz_form(19);
		msgFrame.setLevel(0);
		msgFrame.setPostTime(pack.getPostTime());
		msgFrame.setReportState(true);
		
		/*
		(#{id},#{packUUID},#{userID},#{enterpriseID},#{assignSpecIDs},#{customNum},
		#{msgCount},#{pack},#{msgType},#{bizType},#{state},#{priority},#{sendType},
		#{bizForm.index},#{level},#{postTime},#{deadline},#{scheduleTime},#{boeTime},#{eoeTime},#{reportState},
		#{parameters},#{smil},#{title})
		 * */
		return msgFrame;
	}

	
	
}
