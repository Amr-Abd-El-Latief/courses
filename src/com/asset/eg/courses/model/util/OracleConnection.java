package com.asset.eg.courses.model.util;

import java.sql.*; // for standard JDBC programs

public class OracleConnection {
	private static final String URL = PropertiesConstants.labels.getString("URL");
	private static final String USER = PropertiesConstants.labels.getString("USER");
	private static final String PASS = PropertiesConstants.labels.getString("PASS");
	//private static Connection conn = null;

	public static Connection instantiateConnection() throws CoursesException {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			conn.setAutoCommit(false);			
			return conn;
		} catch (ClassNotFoundException ex) {

			CoursesException CE = new CoursesException(ErrorsStrings.CONNECT_TO_ORACLE_ERROR,ErrorsStrings.CONNECT_TO_ORACLE_MESSAGE);
			CE.initCause(ex);
			CoursesLoggers.ORACLECONNECTION.info(ErrorsStrings.CONNECT_TO_ORACLE_MESSAGE);
			CoursesLoggers.ORACLECONNECTION.error(CE);
			
			throw CE;

		} catch (SQLException e) {
			CoursesException CE = new CoursesException(ErrorsStrings.CONNECT_TO_ORACLE_ERROR,ErrorsStrings.CONNECT_TO_ORACLE_MESSAGE);
			CE.initCause(e);
			CoursesLoggers.ORACLECONNECTION.info(ErrorsStrings.CONNECT_TO_ORACLE_MESSAGE);
			CoursesLoggers.ORACLECONNECTION.error(CE);
			
			throw CE;
		}

	}

	public static Connection getOracleConnection() throws CoursesException {
				
				return instantiateConnection();

	}

	public static void closeOracleConnection(Connection conn) throws CoursesException {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				CoursesException CE = new CoursesException(ErrorsStrings.CLOSE_ORACLE_CONNECTION_ERROR,ErrorsStrings.CLOSE_ORACLE_CONNECTION_MESSAGE);
				CE.initCause(e);
				CoursesLoggers.ORACLECONNECTION.info(ErrorsStrings.CLOSE_ORACLE_CONNECTION_MESSAGE);
				CoursesLoggers.ORACLECONNECTION.error(CE);
				throw CE;
			}
		}
	}

	public static void closeOracleConnectionSTMNT(PreparedStatement pstmnt) throws CoursesException {
		if (pstmnt != null) {
			try {
				pstmnt.close();
			} catch (SQLException e) {
				CoursesException CE = new CoursesException(ErrorsStrings.CLOSE_ORACLE_CONNECTION_ERROR,ErrorsStrings.CLOSE_ORACLE_STATEMENT_MESSAGE);
				CE.initCause(e);
				CoursesLoggers.ORACLECONNECTION.info(ErrorsStrings.CLOSE_ORACLE_CONNECTION_MESSAGE);
				CoursesLoggers.ORACLECONNECTION.error(CE);
				throw CE;
			}
		}
	}

	public static void closeOracleResultSet(ResultSet rs) throws CoursesException {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				CoursesException CE = new CoursesException(ErrorsStrings.CLOSE_ORACLE_RESULT_SET_ERROR, ErrorsStrings.CLOSE_ORACLE_RESULT_SET_MESSAGE);
				CE.initCause(e);
				CoursesLoggers.ORACLECONNECTION.info(ErrorsStrings.CLOSE_ORACLE_RESULT_SET_MESSAGE);
				CoursesLoggers.ORACLECONNECTION.error(CE);
				throw CE;
			}
		}
	}

	public static void commitOracleConnection(Connection conn) throws CoursesException {
		if (conn != null) {
			try {
				conn.commit();
			} catch (SQLException e) {
				CoursesException CE = new CoursesException(ErrorsStrings.COMMIT_ORACLE_CONNECTION_ERROR,ErrorsStrings.COMMIT_ORACLE_CONNECTION_MESSAGE);
				CE.initCause(e);
				CoursesLoggers.ORACLECONNECTION.info(ErrorsStrings.COMMIT_ORACLE_CONNECTION_MESSAGE);
				CoursesLoggers.ORACLECONNECTION.error(CE);
				throw CE;
			}
		}
	}

	public static void rollBackOracleConnection(Connection conn) throws CoursesException {
		if (conn != null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				CoursesException CE = new CoursesException(ErrorsStrings.ROLLBACK_ORACLE_CONNECTION_ERROR,ErrorsStrings.ROLLBACK_ORACLE_CONNECTION_MESSAGE);
				CE.initCause(e);
				CoursesLoggers.ORACLECONNECTION.info(ErrorsStrings.ROLLBACK_ORACLE_CONNECTION_MESSAGE);
				CoursesLoggers.ORACLECONNECTION.error(CE);
				throw CE;
			}
		}
	}
}
