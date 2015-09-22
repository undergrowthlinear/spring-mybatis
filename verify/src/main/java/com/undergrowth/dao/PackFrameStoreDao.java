package com.undergrowth.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.undergrowth.entity.PMsgFrame;
import com.undergrowth.entity.PMsgPack;
import com.undergrowth.entity.StateReport;
import com.undergrowth.entity.Ticket;
import com.undergrowth.mapper.PackFrameMapper;

/**
 * 
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author <a href="zhangwu@wxchina.com">Wu.Zhang</a>
 * @Date 2015年9月18日
 * @Version 1.0.0
 */
@Component
public class PackFrameStoreDao {

	private static final Logger logger = LoggerFactory
			.getLogger(PackFrameStoreDao.class);

	SqlSessionFactory sessionFactory;

	@Autowired
	@Qualifier("outerSessionFactory")
	public void setSessionFactory(SqlSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 入库前，对信息包进行预处理
	 * 
	 * @param pack
	 */
	protected void preHandlePack(PMsgPack pack) {
		for (PMsgFrame frame : pack.getFrames()) {
			// 模拟真实信息帧处理情况
			frame.setId(frame.getId());
		}
	}

	public void storePackOne(PMsgPack pack) {

		long startTime = System.currentTimeMillis();

		preHandlePack(pack);

		SqlSession session = sessionFactory.openSession();
		try {
			/*
			 * if (packDeduct != null) { deductPack(account, packDeduct,
			 * session); }
			 */
			PackFrameMapper mapper = session.getMapper(PackFrameMapper.class);
			Date now = new Date();
			pack.setPostTime(now);

			try {
				mapper.insertPack(pack.getUserID(), pack.getEnterpriseID(),
						pack);
			} catch (Exception ex) {
				logger.error("Store pack failed: "+ex.getMessage(),ex);
				logger.error("the pack has been insert with uuid:"
						+ pack.getUuid());
			}

			storeFrameTicket(pack.getUserID(), pack.getEnterpriseID(), pack,
					mapper, pack.getFrames(), now);
			/*insertFrameBatch(pack.getUserID(), pack.getEnterpriseID(), pack,
					mapper, pack.getFrames(), now);*/

			
			session.commit();
			long endTime = System.currentTimeMillis();
			logger.info("每一个包处理的开始时间:" + startTime + "\t结束时间:" + endTime + "\t总共耗时:"
					+ (endTime - startTime));
		} catch (Exception e) {
			if (session != null)
				session.rollback();
			logger.error("Store pack failed: " + e.getMessage(), e);
		} finally {
			session.close();
		}

	}
	
	
	public void storePack(PMsgPack pack) {

		long startTime = System.currentTimeMillis();

		preHandlePack(pack);

		SqlSession session = sessionFactory.openSession();
		SqlSession sessionBatch = null;
		try {
			/*
			 * if (packDeduct != null) { deductPack(account, packDeduct,
			 * session); }
			 */
			sessionBatch = sessionFactory.openSession(ExecutorType.BATCH,
					session.getConnection());
			PackFrameMapper mapper = sessionBatch
					.getMapper(PackFrameMapper.class);
			Date now = new Date();
			pack.setPostTime(now);

			try {
				mapper.insertPack(pack.getUserID(), pack.getEnterpriseID(),
						pack);
			} catch (Exception ex) {
				logger.error("Store pack failed: "+ex.getMessage(),ex);
				logger.error("the pack has been insert with uuid:"
						+ pack.getUuid());
			}

			storeFrameTicket(pack.getUserID(), pack.getEnterpriseID(), pack,
					mapper, pack.getFrames(), now);
			/*insertFrameBatch(pack.getUserID(), pack.getEnterpriseID(), pack,
					mapper, pack.getFrames(), now);*/

			sessionBatch.commit();
			session.commit();
			long endTime = System.currentTimeMillis();
			logger.info("每一个包处理的开始时间:" + startTime + "\t结束时间:" + endTime + "\t总共耗时:"
					+ (endTime - startTime));
		} catch (Exception e) {
			if (sessionBatch != null)
				sessionBatch.rollback();
			if (session != null)
				session.rollback();
			logger.error("Store pack failed: " + e.getMessage(), e);
		} finally {
			session.close();
		}

	}

	protected void storeFrameTicket(int userID, int enterpriseID,
			PMsgPack pack, PackFrameMapper mapper, List<PMsgFrame> frames,
			Date now) {
		for (PMsgFrame msgFrame : frames) {
			/*
			 * if(msgFrame.getBizForm() == BizForm.AUDIT_DOES_NOT_PASS)
			 * continue;
			 */

			msgFrame.setPostTime(now);

			/*
			 * if (msgFrame.getState() == FrameState.OVER)
			 * msgFrame.setCommitTime(new Date());
			 */

			if (msgFrame.getMsgType() < 0)
				msgFrame.setMsgType(pack.getMsgType());

			msgFrame.setPackUUID(pack.getUuid());
			msgFrame.setUserID(userID);
			msgFrame.setEnterpriseID(enterpriseID);
			mapper.insertFrame(msgFrame);

		}
		insertTickets(mapper, frames);
	}

	protected void insertFrameBatch(int userID, int enterpriseID,
			PMsgPack pack, PackFrameMapper mapper, List<PMsgFrame> frames,
			Date now) {
		for (PMsgFrame msgFrame : frames) {
			/*
			 * if(msgFrame.getBizForm() == BizForm.AUDIT_DOES_NOT_PASS)
			 * continue;
			 */

			msgFrame.setPostTime(now);

			/*
			 * if (msgFrame.getState() == FrameState.OVER)
			 * msgFrame.setCommitTime(new Date());
			 */

			if (msgFrame.getMsgType() < 0)
				msgFrame.setMsgType(pack.getMsgType());

			msgFrame.setPackUUID(pack.getUuid());
			msgFrame.setUserID(userID);
			msgFrame.setEnterpriseID(enterpriseID);
			

		}
		mapper.insertFrameBatch(frames);
		insertTickets(mapper, frames);
	}
	
	
	
	private void insertTickets(PackFrameMapper mapper, List<PMsgFrame> frames) {
		for (PMsgFrame msgFrame : frames) {
			/*
			 * if(msgFrame.getBizForm() == BizForm.AUDIT_DOES_NOT_PASS)
			 * continue;
			 */
			for (Ticket ticket : msgFrame.getTickets()){}
				//mapper.insertTicket(msgFrame, ticket);
		}
	}
	
	public void storeStateReports(List<StateReport> stateReports) throws Exception {
		SqlSession session = sessionFactory.openSession(ExecutorType.BATCH);
		try {
			PackFrameMapper mapper = session
					.getMapper(PackFrameMapper.class);
			for (StateReport stateReport : stateReports) {
				mapper.storeStateReport(stateReport);
			}
			session.commit();
		} finally {
			session.close();
		}
	}

}
