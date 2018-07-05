package common.extern.olena.model.dataaccess.util.core.connections.storeconnect;

import java.sql.Connection;
import java.util.Vector;

import common.extern.olena.model.dataaccess.util.core.connections.ConnectSourceModel;
import common.extern.olena.model.dataaccess.util.core.connections.configDB_Model;
import common.extern.olena.model.dataaccess.util.core.connections.dbConn_Model;
import common.extern.olena.model.systemframework.Applicationlog;

public class storeConnect extends ConnectSourceModel{

	public storeConnect() {
		super();
	}

	/*******abstract override methods*********/
	@Override
	public void InitVariables()throws Throwable {
		Connection objConn = null; 
		configDB_Model configDBInfo = dbConn_Model.m_arrConfigDBs.get(m_nDbType);
		if(configDBInfo!=null) {
			Class.forName(configDBInfo.getConnClass());
			objConn = getConnectionObj(); 
			m_vcConn = new Vector<Connection>();
			m_vcConn.add(objConn);
		}
		else {
			throw new Exception(dbConn_Model.class.getSimpleName() + "Can't resolve info");
		}
	}
	
	@Override
	public Connection getConnection() throws Throwable { 
		try {
			if(m_vcConn!=null) {
				if(m_vcConn.size() < m_nLimitConnection) {	//Create
					Connection objConn = getConnectionObj(); 
					m_vcConn.add(objConn);
				}
				m_nIndexConnection++;
				if(m_nIndexConnection >= m_vcConn.size()) {
					m_nIndexConnection = 0;
				}
				if(m_vcConn.get(m_nIndexConnection)==null) {
					m_vcConn.remove(m_nIndexConnection);
					Connection objConn = getConnectionObj(); 
					m_vcConn.insertElementAt(objConn, m_nIndexConnection);
				}
				else {
					if(m_vcConn.get(m_nIndexConnection).isClosed()) {
						m_vcConn.remove(m_nIndexConnection);
						Connection objConn = getConnectionObj(); 
						m_vcConn.insertElementAt(objConn, m_nIndexConnection);
					}
				}
				return GetCurConnection(); 
			}
			else {
				Applicationlog.LogTruegardenerError("Database Connection Object Error...");
				return null;
			}
		}
		catch (Exception ex) 	{
			Applicationlog.LogTruegardenerError("Database Connection Error...");
			return null;
		}
	}
	
	@Override
	public void closeConnection(Connection objConn) throws Throwable { }
	/***public Methods***/
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 26 PM 3:39:03
	 * @since ModelWeb 1.0
	 */
	private synchronized Connection GetCurConnection() {
		try {
			if(m_vcConn!=null) {
				if(m_vcConn.size() > m_nIndexConnection && m_vcConn.get(m_nIndexConnection)!=null) {
					return m_vcConn.get(m_nIndexConnection);
				}
			}
		}
		catch (Exception ex) {
			Applicationlog.LogTruegardenerError("Current Database Connection Object Getting Error" + ex.getMessage());
		}
		return null;
	}
	
	private Vector<Connection> m_vcConn = null;
	
	private int m_nLimitConnection = -1;
	
	private int m_nIndexConnection = -1;

	/***getter and setter Methods***/
	public int getLimitConnection() { return m_nLimitConnection; }
	public void setLimitConnection(int limitConnection) { m_nLimitConnection = limitConnection; }
	
	public int getIndexConnection() { return m_nIndexConnection; }
	public void setIndexConnection(int indexConnection) { m_nIndexConnection = indexConnection; }
}
