package common.extern.olena.model.dataaccess.util.core.connections;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class ConnectSourceModel {
	
	public ConnectSourceModel() {
	}
	
	protected int m_nDbType = -1;
	protected String m_strServerIp = null;
	protected String m_strServerPort = null;
	protected String m_strDBName = null;
	protected String m_strUserID = null;
	protected String m_strPassword = null;
	
	/***********************getter and setter*************************/
	public int getDbType() { return m_nDbType; }
	public void setDbType(int dbType) { m_nDbType = dbType; }

	public String getDBName() { return m_strDBName; }
	public void setDBName(String name) { m_strDBName = name; }

	public String getPassword() { return m_strPassword; }
	public void setPassword(String password) { m_strPassword = password; }

	public String getServerIp() { return m_strServerIp; }
	public void setServerIp(String serverIp) { m_strServerIp = serverIp; }

	public String getServerPort() { return m_strServerPort; }
	public void setServerPort(String serverPort) { m_strServerPort = serverPort; }

	public String getUserID() { return m_strUserID; }
	public void setUserID(String userID) { m_strUserID = userID; }

	/***********************public methods***************************/
	public Connection getConnectionObj() throws Throwable {
		Connection result = null; 
		String strConn = null;
		configDB_Model configDBInfo = dbConn_Model.m_arrConfigDBs.get(m_nDbType);
		if(m_strDBName==null)
		{
			if(m_strPassword==null) {
				strConn = String.format(configDBInfo.getConnFormatNodbNopass(), configDBInfo.getConnRoot(), m_strServerIp, m_strServerPort);
				result = DriverManager.getConnection(strConn, m_strUserID, m_strPassword);
			}
			else if(m_strPassword.length() <= 0) {
				strConn = String.format(configDBInfo.getConnFormatNodbNopass(), configDBInfo.getConnRoot(), m_strServerIp, m_strServerPort);
				result = DriverManager.getConnection(strConn, m_strUserID, m_strPassword);
			}
			else {
				strConn = String.format(configDBInfo.getConnFormatNodb(), configDBInfo.getConnRoot(), m_strServerIp, m_strServerPort, m_strDBName, m_strUserID, m_strPassword);
				result = DriverManager.getConnection(strConn);
			}	
		}
		else if(m_strDBName.length() <= 0) {
			if(m_strPassword==null) {
				strConn = String.format(configDBInfo.getConnFormatNodbNopass(), configDBInfo.getConnRoot(), m_strServerIp, m_strServerPort);
				result = DriverManager.getConnection(strConn, m_strUserID, m_strPassword);
			}
			else if(m_strPassword.length() <= 0) {
				strConn = String.format(configDBInfo.getConnFormatNodbNopass(), configDBInfo.getConnRoot(), m_strServerIp, m_strServerPort);
				result = DriverManager.getConnection(strConn, m_strUserID, m_strPassword);
			}
			else {
				strConn = String.format(configDBInfo.getConnFormatNodb(), configDBInfo.getConnRoot(), m_strServerIp, m_strServerPort, m_strDBName, m_strUserID, m_strPassword);
				result = DriverManager.getConnection(strConn);
			}
		}
		else {
			if(m_strPassword==null) {
				strConn = String.format(configDBInfo.getConnFormatNopass(), configDBInfo.getConnRoot(), m_strServerIp, m_strServerPort, m_strDBName);
				result = DriverManager.getConnection(strConn, m_strUserID, m_strPassword);
			}
			else if(m_strPassword.length() <= 0) {
				strConn = String.format(configDBInfo.getConnFormatNopass(), configDBInfo.getConnRoot(), m_strServerIp, m_strServerPort, m_strDBName);
				result = DriverManager.getConnection(strConn, m_strUserID, m_strPassword);
			}
			else {
				strConn = String.format(configDBInfo.getConnFormat(), configDBInfo.getConnRoot(), m_strServerIp, m_strServerPort, m_strDBName, m_strUserID, m_strPassword);
				result = DriverManager.getConnection(strConn);
			}	
		}
		return result;
	}
	/***********************abstract methods*************************/
	public abstract Connection getConnection() throws Throwable;
	public abstract void closeConnection(Connection objConn) throws Throwable;
	public abstract void InitVariables() throws Exception, Throwable;
}
