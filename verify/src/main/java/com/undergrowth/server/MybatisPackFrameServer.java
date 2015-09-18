package com.undergrowth.server;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.undergrowth.dao.PackFrameStoreDao;
import com.undergrowth.entity.PMsgPack;
import com.undergrowth.util.CommonUtils;

public class MybatisPackFrameServer {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-context.xml");
		//获取工具类
		CommonUtils commonUtils=context.getBean("commonUtils", CommonUtils.class);
		//产生消息
		List<PMsgPack> pMsgPacks=commonUtils.produce();
		//存入数据库
		PackFrameStoreDao packFrameStoreDao=context.getBean("packFrameStoreDao",PackFrameStoreDao.class);
		
		
	}

}
