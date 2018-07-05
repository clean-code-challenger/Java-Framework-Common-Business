package common.extern.olena.model.dataaccess.mvc.apply;

import common.extern.olena.model.dataaccess.mvc.base.IItem_Model;
import common.extern.olena.model.dataaccess.util.core.connections.dbConn_Model;
/**
 * Baic information definition class for database : Truegardener
 * This is used with TruegardenerBusiness and TruegardenerRule
 * Maybe parent class is useless. 
 * @author Olena.Zagreba 
 * @version  2013-03-26 08:14:12
 */
public class TruegardenerDA extends BaseDA
{
	public TruegardenerDA(dbConn_Model conn)
	{
		m_connDB = conn;
		InitSQLAndResultSets(conn.getDbType());
	}
	public TruegardenerDA(dbConn_Model conn, IItem_Model itemM) 
	{
		m_ItemModel = itemM;
		m_connDB = conn;
		InitSQLAndResultSets(conn.getDbType());
	}
	public TruegardenerDA(dbConn_Model conn, IItem_Model itemM, int nKind) {
		m_ItemModel = itemM; m_ItemModel.setKind(nKind);
		m_connDB = conn;
		InitSQLAndResultSets(conn.getDbType());
	}
}
