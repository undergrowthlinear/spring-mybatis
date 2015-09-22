/*
 * Copyright (c) 2010 by XUANWU INFORMATION TECHNOLOGY CO. 
 *             All rights reserved                         
 */
package com.undergrowth.util;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;



/**
 * @author <a href="mailto:wanglianguang@139130.net>Guang Wang</a>
 * @Data 2010-6-6
 * @Version 1.0.0
 */
public class MapTypeHandler extends BaseTypeHandler<Object> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			Object parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, XmlUtil.toXML((Map<String, Object>) parameter));
	}

	@Override
	public Object getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return XmlUtil.fromXML(rs.getString(columnName));
	}

	@Override
	public Object getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return XmlUtil.fromXML(cs.getString(columnIndex));
	}

	@Override
	public Object getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		// TODO Auto-generated method stub
		return XmlUtil.fromXML(rs.getString(columnIndex));
	}
}
