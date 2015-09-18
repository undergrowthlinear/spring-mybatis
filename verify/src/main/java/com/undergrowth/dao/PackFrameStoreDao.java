package com.undergrowth.dao;

import java.util.Date;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.xuanwu.msggate.common.sbi.entity.Account;
import com.xuanwu.msggate.common.sbi.entity.MsgPack;
import com.xuanwu.msggate.common.sbi.entity.PackDeduct;
import com.xuanwu.msggate.common.sbi.entity.impl.PMsgPack;
import com.xuanwu.msggate.common.sbi.exception.CoreException;
import com.xuanwu.msggate.common.sbi.exception.DuplicateException;
import com.xuanwu.msggate.mtreceiver.dao.mapper.PackHandleMapper;

/**
 * 
* @Description: TODO(这里用一句话描述这个类的作用)
* @Author <a href="zhangwu@wxchina.com">Wu.Zhang</a>
* @Date 2015年9月18日
* @Version 1.0.0
 */
@Component
public class PackFrameStoreDao {

	SqlSessionFactory sessionFactory;

	@Autowired
	@Qualifier("outerSessionFactory")
	public void setSessionFactory(SqlSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	public void storePack(PMsgPack pack, Account account, PackDeduct packDeduct)
			throws CoreException {
		super.storePack(pack, account, packDeduct);
		preHandlePack(pack);
		
		SqlSession session = sessionFactory.openSession();
		SqlSession sessionBatch = null;
		try {
			if (packDeduct != null) {
				deductPack(account, packDeduct, session);
			}

			sessionBatch = sessionFactory.openSession(
					ExecutorType.BATCH, session.getConnection());
			PackHandleMapper mapper = sessionBatch
					.getMapper(PackHandleMapper.class);
			Date now = new Date();
			pack.setPostTime(now);

			try {
				mapper.insertPack(account.getId(), account.getParent().getId(),
						pack);
			} catch (Exception ex) {
				throw new DuplicateException(
						"the pack has been insert with uuid:" + pack.getUuid());
			}
			
			storeFrameTicket(account.getId(), account.getParent().getId(), pack, mapper, pack.getFrames(), now);
			
			if (packDeduct != null) {
				storePackDeduct(account.getCapitalAccount(), packDeduct, mapper, now);
				if (packDeduct.getParent() != null) {
					storePackDeduct(account.getCapitalAccount().getParent(),
							packDeduct.getParent(), mapper, now);
				}
			}
			
			// Update user account has sent message
			if(!account.isHasSentMessage()){
				mapper.updateHasSentMsg(account.getId());
			}

			// Update enterprise account has sent message
			if(!account.getParent().isHasSentMessage()){
				mapper.updateHasSentMsg(account.getParent().getId());
				Account proxyEnt = account.getParent().getParent();
				if(proxyEnt != null && !proxyEnt.isHasSentMessage()){
					mapper.updateHasSentMsg(proxyEnt.getId());
				}
			}
			sessionBatch.commit();
			session.commit();
		} catch (Exception e) {
			if(sessionBatch != null)
				sessionBatch.rollback();
			if(session != null)
				session.rollback();
			if(e instanceof CoreException){
				throw (CoreException)e;
			} else {
				throw new CoreException("Store pack failed: " + e.getMessage(), e);
			}
		} finally {
			session.close();
		}
		
		// Store Non-white list
		PMsgPack pMsgPack = (PMsgPack)pack;
		storeNonWhiteList(pMsgPack.getNonWhiteLists());
	}
	
	
}
