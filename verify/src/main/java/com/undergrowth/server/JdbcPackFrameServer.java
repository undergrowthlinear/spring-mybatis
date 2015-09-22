package com.undergrowth.server;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.undergrowth.dao.JdbcPackFrameStoreDao;
import com.undergrowth.entity.PMsgPack;
import com.undergrowth.util.CommonUtils;

public class JdbcPackFrameServer {

	private static final Logger LOGGER=LoggerFactory.getLogger(JdbcPackFrameServer.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-context.xml");
		//获取工具类
		CommonUtils commonUtils=context.getBean("commonUtils", CommonUtils.class);
		//产生消息
		List<PMsgPack> pMsgPacks=commonUtils.produce();
		//存入数据库
		JdbcPackFrameStoreDao packFrameStoreDao=context.getBean("jdbcPackFrameStoreDao",JdbcPackFrameStoreDao.class);
		
		long startTime = System.currentTimeMillis();
		
		for (PMsgPack pMsgPack : pMsgPacks) {
			packFrameStoreDao.storePack(pMsgPack);
		}
		
		/*packFrameStoreDao.storePacks(pMsgPacks);*/
		
		long endTime = System.currentTimeMillis();
		
		LOGGER.info("所有包处理的开始时间:" + startTime + "\t结束时间:" + endTime + "\t总共耗时:"
					+ (endTime - startTime)+"\t平均耗时:"+(endTime - startTime)/commonUtils.getNum());
	}

	

}
