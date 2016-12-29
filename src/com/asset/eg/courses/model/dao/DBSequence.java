package com.asset.eg.courses.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.asset.eg.courses.model.util.CoursesException;
import com.asset.eg.courses.model.util.CoursesLoggers;
import com.asset.eg.courses.model.util.ErrorsStrings;
import com.asset.eg.courses.model.util.OracleConnection;

public class DBSequence {
	public static int getNextId(Connection conn,String seqName) throws CoursesException{		
		
		String query = "SELECT "+seqName+".NEXTVAL FROM DUAL";
		PreparedStatement pstmnt = null;
		ResultSet rs = null;
		try {
			 pstmnt = conn.prepareStatement(query);
			rs = pstmnt.executeQuery();
		int sequenceValue = -1;
			while (rs.next()) {
				sequenceValue = rs.getInt(1);
				return sequenceValue;
			}
			return -1;		

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			CoursesException CE = new CoursesException(ErrorsStrings.DATA_BASE_SQLEXCEPTION_ERROR,ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			CE.initCause(e);
			CoursesLoggers.LUSERDAO.error(CE);
			CoursesLoggers.LUSERDAO.info(ErrorsStrings.DATA_BASE_SQLEXCEPTION_MESSAGE);
			throw CE;
		}finally{
			OracleConnection.closeOracleResultSet(rs);
			OracleConnection.closeOracleConnectionSTMNT(pstmnt);
		}		
	}
	
	
}
