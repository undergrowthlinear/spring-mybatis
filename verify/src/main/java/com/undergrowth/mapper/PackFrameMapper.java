package com.undergrowth.mapper;

import org.apache.ibatis.annotations.Param;

import com.undergrowth.entity.PMsgFrame;
import com.undergrowth.entity.PMsgPack;
import com.undergrowth.entity.Ticket;




public interface PackFrameMapper {

	/**
	 * Insert message pack
	 * 
	 * @param pack
	 */
	public void insertPack(@Param("userID") long userID,
			@Param("entID") long entID, @Param("pack") PMsgPack pack);

	
	/**
	 * Insert the mass frame
	 * 
	 * @param frame
	 * @param type
	 */
	public void insertFrame(PMsgFrame frame);
	
	
	/**
	 * Insert tickets
	 * 
	 * @param msgFrame
	 * @param ticket
	 */
	public void insertTicket(@Param("frame") PMsgFrame msgFrame,
			@Param("ticket") Ticket ticket);
}
