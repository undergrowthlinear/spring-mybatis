package com.undergrowth.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.undergrowth.entity.PMsgFrame;
import com.undergrowth.entity.PMsgPack;
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
public class JdbcPackFrameStoreDao {

	private static final Logger logger = LoggerFactory
			.getLogger(JdbcPackFrameStoreDao.class);

	private DataSource dataSource;

	private Connection conn;

	private PreparedStatement ps,psFrame;

	@Autowired
	public DataSource getDataSource() {
		return dataSource;
	}

	private String sqlPackInsert = "insert into gsms_msg_pack_test_speed"
			+ "(USER_ID,ENTERPRISE_ID,ID,BATCH_NAME,SEND_TYPE,MSG_TYPE,BIZ_TYPE,"
			+ "POST_TIME,BOE_TIME,EOE_TIME,STATE,VALID_TICKETS,INVALID_TICKETS,VALID_FRAME_COUNT)"
			+ " values" + "(?,?,?,?,?,?,?," + "?,?,?,?,?,?,?)";

	private String sqlFrameInsert = "insert into gsms_msg_frame_test_speed"
			+ "(ID,MSG_PACK_ID,USER_ID,ENTERPRISE_ID,ASSIGN_SPECSVS_ID,MSG_TYPE,BIZ_TYPE"
			+ ",STATE,SEND_TYPE,BIZ_FORM,LEVEL,POST_TIME,STATE_REPORT,PARAMETERS)"
			+ " values" + "(?,?,?,?,?,?,?," + "?,?,?,?,?,?,?)";

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Connection getConnection() {

		try {
			if (conn == null)
				conn = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	private void setPackValue(PreparedStatement theStmt, PMsgPack pack) {
		try {
			theStmt.setObject(1, pack.getUserID());
			theStmt.setObject(2, pack.getEnterpriseID());
			theStmt.setObject(3, pack.getUuid());
			theStmt.setObject(4, pack.getBatchName());
			theStmt.setObject(5, pack.getSendType());
			theStmt.setObject(6, pack.getMsgType());
			theStmt.setObject(7, pack.getBizType());
			theStmt.setObject(8, pack.getPostTime());
			theStmt.setObject(9, pack.getEoeTime());
			theStmt.setObject(10, pack.getBoeTime());
			theStmt.setObject(11, pack.getState());
			theStmt.setObject(12, pack.getValidTicketCount());
			theStmt.setObject(13, pack.getInValidTicketCount());
			theStmt.setObject(14, pack.getValidFrameCount());
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(
					"PreparedStatement PMsgPack failed :" + e.getMessage(), e);
		}
	}

	private void setFrameValue(PreparedStatement theStmt, PMsgFrame msgFrame) {
		try {

			theStmt.setObject(1, msgFrame.getId());
			theStmt.setObject(2, msgFrame.getPackUUID());
			theStmt.setObject(3, msgFrame.getUserID());
			theStmt.setObject(4, msgFrame.getEnterpriseID());
			theStmt.setObject(5, msgFrame.getAssignSpecIDs());
			theStmt.setObject(6, msgFrame.getMsgType());
			theStmt.setObject(7, msgFrame.getBizType());
			theStmt.setObject(8, msgFrame.getState());
			theStmt.setObject(9, msgFrame.getSendType());
			theStmt.setObject(10, msgFrame.getBizForm());
			theStmt.setObject(11, msgFrame.getLevel());
			theStmt.setObject(12, msgFrame.getPostTime());
			theStmt.setObject(13, msgFrame.getReportState());
			theStmt.setObject(14, "<map/>");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(
					"PreparedStatement PMsgFrame failed :" + e.getMessage(), e);
		}

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

	public void storePack(PMsgPack pack) {

		long startTime = System.currentTimeMillis();
		preHandlePack(pack);
		try {

			Date now = new Date();
			pack.setPostTime(now);

			
			// 处理pack
			
			setPackValue(ps, pack);
			ps.execute();

			storeFrameTicket(pack.getUserID(), pack.getEnterpriseID(), pack,
					pack.getFrames(), now);

			
			long endTime = System.currentTimeMillis();
			logger.info("每一个包处理的开始时间:" + startTime + "\t结束时间:" + endTime
					+ "\t总共耗时:" + (endTime - startTime));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void storeFrameTicket(int userID, int enterpriseID,
			PMsgPack pack, List<PMsgFrame> frames, Date now) {
		if(frames.size()>0){
			/*try {
				ps=conn.prepareStatement(sqlFrameInsert);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
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

				setFrameValue(psFrame, msgFrame);
				try {
					psFrame.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			insertTickets(frames);
		}
		
	}

	private void insertTickets(List<PMsgFrame> frames) {
		for (PMsgFrame msgFrame : frames) {
			/*
			 * if(msgFrame.getBizForm() == BizForm.AUDIT_DOES_NOT_PASS)
			 * continue;
			 */
			for (Ticket ticket : msgFrame.getTickets()){}
				//mapper.insertTicket(msgFrame, ticket);
		}
	}

	public void storePacks(List<PMsgPack> pMsgPacks) {
		// TODO Auto-generated method stub
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			ps=conn.prepareStatement(sqlPackInsert);
			psFrame=conn.prepareStatement(sqlFrameInsert);
			
			
			for (PMsgPack pMsgPack : pMsgPacks) {
				storePack(pMsgPack);
			}
			
			
			conn.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
