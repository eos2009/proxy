package com.supwisdom.service.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库链接工具类 获取连接 关闭连接
 * 
 * @author fei.chen
 *
 */
public class DBUtils {

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 加载JDBC驱动
		String dbURL = "jdbc:sqlserver://219.229.222.142:1433; DatabaseName=JPzjk"; // 连接服务器和数据库JPzjk
		String userName = "sa"; // 默认用户名
		String userPwd = "suj8r"; // 密码
		Connection dbConn = null;
		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			return dbConn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbConn;
	}
	
	
	/**
	 * 获取财务数据库连接
	 * 
	 * @return
	 */
	public static Connection getCWConnection() {
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 加载JDBC驱动
		String dbURL = "jdbc:sqlserver://219.229.222.12\\MSSQLSRVER; DatabaseName=ndzhcx"; // 连接服务器和数据库JPzjk
		String userName = "Swsoft"; // 默认用户名
		String userPwd = "123"; // 密码
		Connection dbConn = null;
		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			return dbConn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbConn;
	}
	
	/**
	 * 获取一卡通中间库数据库连接
	 * 
	 * @return
	 */
	public static Connection getYKTConnection() {
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 加载JDBC驱动
		String dbURL = "jdbc:sqlserver://210.35.128.136:1433; DatabaseName=szxydata"; // 连接服务器和数据库szxydata
		String userName = "sa"; // 默认用户名
		String userPwd = "ykt222@jxnd"; // 密码
		Connection dbConn = null;
		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			return dbConn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbConn;
	}
	
	
	/**
	 * 获取通讯录数据库连接
	 * 
	 * @return
	 */
	public static Connection getAddressConnection() {
		String driverName = "oracle.jdbc.driver.OracleDriver"; // 加载JDBC驱动
//		String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl"; // 连接服务器和数据库
//		String userName = "uniportal_shou"; // 默认用户名
//		String userPwd = "kingstar"; // 密码
		
		String dbURL = "jdbc:oracle:thin:@(DESCRIPTION ="
				+ "(ADDRESS = (PROTOCOL = TCP)(HOST = 172.16.221.13)(PORT = 1521))"
				+ "(ADDRESS = (PROTOCOL = TCP)(HOST = 172.16.221.14)(PORT = 1521))"
				+ "(ADDRESS = (PROTOCOL = TCP)(HOST = 172.16.221.15)(PORT = 1521))"
				+ "(LOAD_BALANCE = yes)" + "(CONNECT_DATA ="
				+ "(SERVER = DEDICATED)" + "(SERVICE_NAME = xydb)"
				+ "(FAILOVER_MODE =" + "(TYPE = SELECT)" + "(METHOD = BASIC)"
				+ "(RETRIES = 180)" + "(DELAY = 5)" + ")" + ")" + ")"; // 连接服务器和数据库
		String userName = "sharedb"; // 默认用户名
		String userPwd = "kingstar"; // 密码
		Connection dbConn = null;
		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			return dbConn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbConn;
	}
	
	
	/**
	 * 获取通讯录数据库连接
	 * 
	 * @return
	 */
	public static Connection getJwConnection() {
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 加载JDBC驱动
		String dbURL = "jdbc:sqlserver://219.229.222.123:6318; DatabaseName=jwglxxxt"; // 连接服务器和数据库szxydata
		String userName = "Data_Center0324"; // 默认用户名
		String userPwd = "df#%@jdfae%def"; // 密码
		Connection dbConn = null;
		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			return dbConn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbConn;
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param conn
	 */
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
