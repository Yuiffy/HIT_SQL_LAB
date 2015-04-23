package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.sina.sae.util.SaeUserInfo;

/**
 * 构造参数为0为读，为其他为写
 */
public class SqlCon {
	private boolean online = false;///线上线下这个不同
	
	final static private String driver = "com.mysql.jdbc.Driver";
	final static private int WAIT = 10;
	final static private String URLW = "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_yuiffy";
	final static private String URLR = "jdbc:mysql://r.rdc.sae.sina.com.cn:3307/app_yuiffy";
	final static private String PASSWORD = "1234567";

	static private String user = SaeUserInfo.getAccessKey();
	static private String password = SaeUserInfo.getSecretKey();
	private String url = URLR;
	private Connection conn;

	public SqlCon() {
		if(!isOnline()){
			url = "jdbc:mysql://localhost:3306/COMPANY?characterEncoding=utf-8";
			user = "root";
			password = PASSWORD;
		}
		try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException e2) {
			e2.printStackTrace();
		} catch (IllegalAccessException e2) {
			e2.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		DriverManager.setLoginTimeout(WAIT);
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println("SqlCon连接服务器错啦！");
		}
		//System.out.println("SqlCon Over!");
	}

	/**
	 * @param x
	 *            为0为读，其他为写
	 */
	public void setWrite() {
		if (isOnline()) {
			this.url = SqlCon.URLW;
			try {
				conn = DriverManager.getConnection(url, user, password);
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("SqlCon连接写服务器错啦！");
			}
		}
	}

	public Connection getConn() {
		return this.conn;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public boolean isOnline() {
		return this.online;
	}

}
