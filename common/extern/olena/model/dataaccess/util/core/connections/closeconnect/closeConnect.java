package common.extern.olena.model.dataaccess.util.core.connections.closeconnect;

import java.sql.Connection;

import common.extern.olena.model.dataaccess.util.core.connections.ConnectSourceModel;
import common.extern.olena.model.dataaccess.util.core.connections.configDB_Model;
import common.extern.olena.model.dataaccess.util.core.connections.dbConn_Model;
import common.extern.olena.model.systemframework.Applicationlog;

public class closeConnect extends ConnectSourceModel {

	@Override
	public void InitVariables() throws Throwable {
		configDB_Model configDBInfo = dbConn_Model.m_arrConfigDBs.get(m_nDbType);
		if(configDBInfo!=null) {
			Class.forName(configDBInfo.getConnClass());
		}
		else {
			throw new Exception(dbConn_Model.class.getSimpleName() + "Can not get the connection info");
		}
	}
	@Override
	public Connection getConnection() throws Throwable  { 
		Connection objConn = getConnectionObj();
		if(!isAutoCommit()) {
			objConn.setAutoCommit(false);
			objConn.commit();
		}
		return objConn;
	}

	@Override
	public void closeConnection(Connection objConn) throws Throwable {
		try {
			if(!isAutoCommit()) 		{
				objConn.rollback();
			}
			objConn.close();			
		}
		catch (Exception ex) {
			Applicationlog.LogTruegardenerError("Datbase Connection Error...");
		}
	}
	/** can server commit automatically?*/
	private boolean m_bAutoCommit = true;

	/***getter and setter Methods***/
	public boolean isAutoCommit() { return m_bAutoCommit; }
	public void setAutoCommit(boolean autoCommit) { m_bAutoCommit = autoCommit; }
}
