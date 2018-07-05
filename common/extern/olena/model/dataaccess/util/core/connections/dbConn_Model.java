package common.extern.olena.model.dataaccess.util.core.connections;

import java.sql.Connection;
import java.util.Vector;

import common.extern.olena.model.dataaccess.util.core.connections.closeconnect.closeConnect;
import common.extern.olena.model.dataaccess.util.core.connections.storeconnect.storeConnect;
import common.extern.olena.model.systemframework.Applicationlog;

/**
 * Database connection part
 * @author Olena.Zagreba in Truegardener TEAM
 * @since 2011. 12. 24
 * @version ModelWeb 1.0
 */
public class dbConn_Model {
	public static final int CONMODE_CLOSE = 0;
	public static final int CONMODE_STORE = 1;
	//public static final int CONMODE_POOLING = 2;
	
	/**mysql database*/
	public static int DB_MYSQL;
	/**mssql database*/
	public static int DB_MSSQL;
	/**oracle database*/
	public static int DB_ORACLE;
	
	/**MicroSoft MDB database router setting*/
	public static int DB_MSMDB;
	//public static int DB_MSMDBODBC;

	public static Vector<configDB_Model> m_arrConfigDBs = new Vector<configDB_Model>();
	//strCnn = "";
	static {	//1:connRoot, 2:serverIp, 3:serverPort, 4:dbName, 5:userid, 6:password
		DB_MYSQL = addConfigDB("mysql", "com.mysql.jdbc.Driver", "jdbc:mysql:", "%1$s//%2$s:%3$s/%4$s?user=%5$s&password=%6$s", "%1$s//%2$s:%3$s/%4$s", "%1$s//%2$s:%3$s/?user=%5$s&password=%6$s", "%1$s//%2$s:%3$s");
		DB_MSSQL = addConfigDB("mssql", "net.sourceforge.jtds.jdbc.Driver", "jdbc:jtds:sqlserver:", "%1$s//%2$s:%3$s;DatabaseName=%4$s;user=%5$s;password=%6$s", "%1$s//%2$s:%3$s;DatabaseName=%4$s;", "%1$s//%2$s:%3$s;user=%5$s;password=%6$s", "%1$s//%2$s:%3$s;");
		DB_ORACLE = addConfigDB("oracle", "oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:", "%1$s%5$s/%6$s@%2$s:%3$s:%4$s", "%1$s@%2$s:%3$s:%4$s", "%1$s%5$s/%6$s@%2$s:%3$s", "%1$s@%2$s:%3$s");
		DB_MSMDB = addConfigDB("access", "sun.jdbc.odbc.JdbcOdbcDriver", "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)}", "%1$s;DBQ=%4$s", "%1$s;DBQ=%4$s", "%1$s;DBQ=%4$s", "%1$s;DBQ=%4$s");
		//DB_MSMDBODBC = addConfigDB("sun.jdbc.odbc.JdbcOdbcDriver", "jdbc:odbc:", "%1$s%4$s:user=%5$s;password=%6$s");
		//DB_MSMDBODBC = addConfigDB("sun.jdbc.odbc.JdbcOdbcDriver", "jdbc:odbc:authors:", "%1$s//%4$sDatabaseName=%4$s;user=%5$s;password=%6$s");
	}
	private int m_nConnectMode = -1;
	private ConnectSourceModel m_connectSource = null;
	
	public dbConn_Model(int nDriverType, String serverIp, String serverPort, String dbName, String userid, String password, boolean bAutoCommit) throws Throwable {
		try {
			m_nConnectMode = CONMODE_CLOSE;
			m_connectSource = new closeConnect();
			setConnectionInfo(nDriverType, serverIp, serverPort, dbName, userid, password);
			((closeConnect)m_connectSource).setAutoCommit(bAutoCommit);			
			m_connectSource.InitVariables();
		}
		catch (Exception ex) {
			throw new Exception("Database connection error...");
		}
	}
	
	/**
	 * 
	 * @param nDriverType
	 * @param serverIp 
	 * @param serverPort 
	 * @param dbName
	 * @param userid 
	 * @param password 
	 * @throws Exception
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @since 2011. 12. 24
	 * @version ModelWeb 1.0
	 */
	public dbConn_Model(int nDriverType, String serverIp, String serverPort, String dbName, String userid, String password, int nLimitConn) throws Throwable {
		try {
			m_nConnectMode = CONMODE_STORE;
			m_connectSource = new storeConnect();
			setConnectionInfo(nDriverType, serverIp, serverPort, dbName, userid, password);
			
			if(nLimitConn < 1) { nLimitConn = 1; }
			else {}
			((storeConnect)m_connectSource).setLimitConnection(nLimitConn);
			((storeConnect)m_connectSource).setIndexConnection(0);
			m_connectSource.InitVariables();
		}
		catch (Exception ex) {
			throw new Exception("Database connection error...");
		}
	}
	
	private void setConnectionInfo(int nDriverType, String serverIp, String serverPort, String dbName, String userid, String password) throws Throwable {
		if(m_connectSource!=null) {
			m_connectSource.setDbType(nDriverType);
			m_connectSource.setServerIp(serverIp);
			m_connectSource.setServerPort(serverPort);
			m_connectSource.setDBName(dbName);
			m_connectSource.setUserID(userid);
			m_connectSource.setPassword(password);		
		}
	}
	/**
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 26 PM 3:39:03
	 * @since ModelWeb 1.0
	 */
	public synchronized Connection getConnection() 	{
		Connection result = null;
		try { result = m_connectSource.getConnection(); }
		catch (Throwable ex) {
			Applicationlog.LogTruegardenerError("Database connection error...");
			Applicationlog.LogTruegardenerError(ex);
			return null;
		}
		return result;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 26 PM 3:39:03
	 * @since ModelWeb 1.0
	 */
	public void closeConnection(Connection objConn) {
		try {
			m_connectSource.closeConnection(objConn);
		}
		catch (Throwable ex) {
			Applicationlog.LogTruegardenerError("Database close error...");
			Applicationlog.LogTruegardenerError(ex);
		}
	}

	/**
	 * 
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 03. 02 PM 5:22:46
	 * @since ModelWeb
	 */
	public int getConnectMode() { return m_nConnectMode; }

	/**********************getter methods************************/
	public int getDbType() { return m_connectSource.getDbType(); }
	public String getServerIp() { return m_connectSource.getServerIp(); }
	public String getServerPort() { return m_connectSource.getServerPort(); }
	public String getDBName() { return m_connectSource.getDBName(); }
	public String getUserID() { return m_connectSource.getUserID(); }
	public String getPassword() { return m_connectSource.getPassword(); }
	
	/**********************util methods************************/
	/**
	 * 
	 * @param strConnClass
	 * @param strConnRoot
	 * @param strConnFormat
	 * @return
	 * 
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 PM 3:01:09
	 * @since ModelWeb 1.0
	 */
	public static int addConfigDB(String strDBKindName, String strConnClass, String strConnRootAll, String strConnFormat, String strConnNopass, String strConnNodb, String strConnNodbNoPass) {
		int result = -1;
		result = m_arrConfigDBs.size();
		configDB_Model itemConfigDB = new configDB_Model();
		
		itemConfigDB.setDBKindName(strDBKindName);
		itemConfigDB.setConnClass(strConnClass);
		itemConfigDB.setConnRoot(strConnRootAll);
		itemConfigDB.setConnFormat(strConnFormat);
		itemConfigDB.setConnFormatNopass(strConnNopass);
		itemConfigDB.setConnFormatNodb(strConnNodb);
		itemConfigDB.setConnFormatNodbNopass(strConnNodbNoPass);
		m_arrConfigDBs.add(itemConfigDB);
		return result;
	}
	
	/**
	 * 
	 * @param strDBKindName
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 03. 02 PM 5:22:12
	 * @since ModelWeb
	 */
	public static int getDBIndex(String strDBKindName) {
		int result = -1;
		if(strDBKindName!=null && m_arrConfigDBs!=null) {
			int nSize = m_arrConfigDBs.size();
			for(int nIndex = 0; nIndex < nSize; nIndex++) {
				if(strDBKindName.equals(m_arrConfigDBs.get(nIndex).getDBKindName())) {
					result = nIndex;
				}
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @param nDBKind
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 03. 02 PM 5:21:58
	 * @since ModelWeb
	 */
	public static String getDBString(int nDBKind) {
		String result = null;
		if(nDBKind!=-1 && m_arrConfigDBs!=null && nDBKind < m_arrConfigDBs.size()) {
			result = m_arrConfigDBs.get(nDBKind).getDBKindName();
		}
		return result;
	}
	
	/**
	 * 
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 03. 02 PM 5:21:37
	 * @since ModelWeb
	 */
	public static int getDriverSize() {
		int result = -1;
		result = m_arrConfigDBs.size();
		return result;
	}
}
