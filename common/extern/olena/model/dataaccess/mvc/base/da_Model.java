package common.extern.olena.model.dataaccess.mvc.base;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Vector;

import org.w3c.dom.Document;

import common.extern.olena.model.dataaccess.daFatherModel;
import common.extern.olena.model.dataaccess.util.core.connections.dbConn_Model;
import common.extern.olena.model.dataaccess.util.core.query.sqlQuery_Model;
import common.extern.olena.model.dataaccess.util.core.query.components.mssql.msmdbSqlQuery;
import common.extern.olena.model.dataaccess.util.core.query.components.mssql.mssqlSqlQuery;
import common.extern.olena.model.dataaccess.util.core.query.components.mysql.mysqlSqlQuery;
import common.extern.olena.model.dataaccess.util.core.query.components.oracle.oracleSqlQuery;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.*;
import common.extern.olena.model.dataaccess.util.core.resultSets.recordSet_Model;
import common.extern.olena.model.dataaccess.util.core.resultSets.mssql.msmdbRecordSet;
import common.extern.olena.model.dataaccess.util.core.resultSets.mssql.mssqlRecordSet;
import common.extern.olena.model.dataaccess.util.core.resultSets.mysql.mysqlRecordSet;
import common.extern.olena.model.dataaccess.util.core.resultSets.oracle.oracleRecordSet;
import common.extern.olena.model.systemframework.Applicationlog;

/**
 * @author Olena.Zagreba in Truegardener TEAM
 * @since 2011. 12. 24
 * @version ModelWeb 1.0
 */
public abstract class da_Model
{
	/**Dabatase Connection*/
	protected dbConn_Model m_connDB = null; 
	/*** Items per Table field(Field)*/
	protected IItem_Model m_ItemModel = null;
	/**Query*/
	protected sqlQuery_Model m_SQLQueryProvider = null;
	/**Record*/
	protected recordSet_Model m_recordSet = null;
	/**Parent Database connection Model*/
	protected daFatherModel m_daParent = null;
	
	public da_Model() { }
	public da_Model(dbConn_Model conn, daFatherModel parentDA) {
		m_connDB = conn;
		m_daParent = parentDA;
		InitSQLAndResultSets(conn.getDbType());
	}
	public da_Model(dbConn_Model conn, daFatherModel parentDA, IItem_Model itemM){
		m_ItemModel = itemM;
		m_daParent = parentDA;
		m_connDB = conn;
		InitSQLAndResultSets(conn.getDbType());
	}
	public da_Model(dbConn_Model conn, daFatherModel parentDA, IItem_Model itemM, int nKind) {
		m_ItemModel = itemM; m_ItemModel.setKind(nKind);
		m_daParent = parentDA;
		m_connDB = conn;
		InitSQLAndResultSets(conn.getDbType());
	}
	protected void InitSQLAndResultSets(int nDbType) {
		if(nDbType==dbConn_Model.DB_MYSQL) {
			m_SQLQueryProvider = new mysqlSqlQuery(m_ItemModel);
			m_recordSet = new mysqlRecordSet(m_ItemModel);
			m_recordSet.setConnectionModel(m_connDB);
		}
		else if(nDbType==dbConn_Model.DB_MSSQL) {
			m_SQLQueryProvider = new mssqlSqlQuery(m_ItemModel);
			m_recordSet = new mssqlRecordSet(m_ItemModel);
			m_recordSet.setConnectionModel(m_connDB);
		}
		else if(nDbType==dbConn_Model.DB_MSMDB) {
			m_SQLQueryProvider = new msmdbSqlQuery(m_ItemModel);
			m_recordSet = new msmdbRecordSet(m_ItemModel);
			m_recordSet.setConnectionModel(m_connDB);
		}
		else if(nDbType==dbConn_Model.DB_ORACLE) {
			m_SQLQueryProvider = new oracleSqlQuery(m_ItemModel);
			m_recordSet = new oracleRecordSet(m_ItemModel);
			m_recordSet.setConnectionModel(m_connDB);
		}
	}
	/**
	 * @return
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @since ModelWeb 1.0
	 */
	public int getKind() { return m_ItemModel.getKind(); }
	/**
	 * @return
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @since ModelWeb 1.0
	 */
	public daFatherModel getDAModel() { return m_daParent; }
	/**
	 * @return
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @since ModelWeb 1.0
	 */
	public tbl_Model getTableModel() { return m_ItemModel.getTableModel(); }
	/**
	 * @return
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @since ModelWeb 1.0
	 */
	public IItem_Model getItemModel() { return m_ItemModel; }
	/**
	 * @param itemModel
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public void setItemModel(IItem_Model itemModel) {
		m_ItemModel = itemModel;
		if(m_recordSet!=null) {
			if(m_recordSet.getItemModel()==null) {
				m_recordSet.setItemModel(m_ItemModel);
			}
		}		
		if(m_SQLQueryProvider!=null) {
			if(m_SQLQueryProvider.getItemModel()==null) {
				m_SQLQueryProvider.setItemModel(m_ItemModel);
			}
		}
	}
	/**
	 * @return
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public Connection getConnection() { return m_connDB.getConnection(); }
	/**
	 * @return
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public recordSet_Model getRecordSet() { return m_recordSet; }
	/**
	 * @return
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public sqlQuery_Model getSQLQueryProvider() { return m_SQLQueryProvider; }
//////////////////////////////////Base//////////////////////////////////////
//////////////////////////////////Base//////////////////////////////////////
//////////////////////////////////Base//////////////////////////////////////
	/**
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public boolean create_Table()throws Throwable {
		boolean result = false;
		recordSet_Model objRecordSet = m_recordSet.clone();
		String query = "";
		try {
			query = m_SQLQueryProvider.productQueryCreateTable();
			objRecordSet.PrepareStatementUpdate(query);
			objRecordSet.executePrepareStatementUpdate();

			if(m_connDB.getDbType()==dbConn_Model.DB_ORACLE && getItemModel().getFieldInfo(tbl_Model.field_id).isPrimaryAutoIncrease())
			{
				query = m_SQLQueryProvider.productQueryCreateSequence();
				objRecordSet.PrepareStatementUpdate(query);
				objRecordSet.executePrepareStatementUpdate();
			}
			result = true;
		}
		catch(Throwable ex) {
			Applicationlog.LogTruegardenerError(ex.getMessage(), query);
			throw ex;
		}
		finally { objRecordSet.close(); }
		return result;
	}
	/**
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public boolean drop_Table()throws Throwable {
		boolean result = false;
		recordSet_Model objRecordSet = m_recordSet.clone(); 
		String query = "";
		try {
			query = m_SQLQueryProvider.productQueryDropTable();
			objRecordSet.PrepareStatementUpdate(query);
			objRecordSet.executePrepareStatementUpdate();
			result = true;
		}
		catch(Throwable ex) {
			Applicationlog.LogTruegardenerError(ex.getMessage(), query);
			throw ex;
		}
		finally { objRecordSet.close(); }
		return result;
	}
	/**
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public IItem_Model get_PrimaryInfo(selectExpressionRelation selectList, Object primaryKey)throws Throwable {
		IItem_Model result = null;
		recordSet_Model objRecordSet = m_recordSet.clone();
		String query = "";
		try {
			query = m_SQLQueryProvider.productQueryGetPrimaryInfo(selectList, primaryKey);
			objRecordSet.PrepareStatementSelect(query);
			objRecordSet.setObject(1, primaryKey);
			objRecordSet.executePrepareStatementSelect();
			result = objRecordSet.productGetInfoItem(selectList);
		}
		catch(Throwable ex) {
			Applicationlog.LogTruegardenerError(ex.getMessage(), query);
			throw ex;
		}
		finally { objRecordSet.close(); }
		return result;
	}
	/**
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public Vector<IItem_Model> get_Info(selectExpressionRelation selectList, whereExpressionRelation whereList, orderExpressionRelation orderList, pageExpressionRelation pgInfo)throws Throwable {
		Vector<IItem_Model> result = null;
		recordSet_Model objRecordSet = m_recordSet.clone();
		String query = "";
		try {
			query = m_SQLQueryProvider.productQueryGetInfo(selectList, whereList, orderList, pgInfo);
			objRecordSet.PrepareStatementSelect(query);
			objRecordSet.productSetWhereInfoItem(0, whereList);
			objRecordSet.executePrepareStatementSelect();
			result = objRecordSet.productGetInfoListItem(selectList, pgInfo);
		}
		catch(Throwable ex) {
			Applicationlog.LogTruegardenerError(ex.getMessage(), query);
			throw ex;
		}
		finally { objRecordSet.close(); }
		return result;
	}
	/**
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	@Deprecated
	public Vector<IItem_Model> get_InfoByAndEquByItem(selectExpressionRelation selectList, IItem_Model info, orderExpressionRelation orderList, pageExpressionRelation pgInfo)throws Throwable {
		Vector<IItem_Model> result = null;
		recordSet_Model objRecordSet = m_recordSet.clone();
		String query = "";
		try {
			whereItemExpressionRelation infoRelationItem = new whereItemExpressionRelation(info);
			
			query = m_SQLQueryProvider.productQueryGetItemInfoByAndEqu(selectList, infoRelationItem, orderList, pgInfo);
			objRecordSet.PrepareStatementSelect(query);
			objRecordSet.productSetWhereInfoItembyItem(0, info);
			objRecordSet.executePrepareStatementSelect();
			result = objRecordSet.productGetInfoListItem(selectList, pgInfo);
		}
		catch(Throwable ex) {
			Applicationlog.LogTruegardenerError(ex.getMessage(), query);
			throw ex;
		}
		finally { objRecordSet.close(); }
		return result;
	}
	/**
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	@Deprecated
	public Vector<IItem_Model> get_InfoByOrEquByItem(selectExpressionRelation selectList, IItem_Model info, orderExpressionRelation orderList, pageExpressionRelation pgInfo)throws Throwable {
		Vector<IItem_Model> result = null;
		recordSet_Model objRecordSet = m_recordSet.clone();
		String query = "";
		try {
			whereItemExpressionRelation infoRelationItem = new whereItemExpressionRelation(info);
			query = m_SQLQueryProvider.productQueryGetItemInfoByOrEqu(selectList, infoRelationItem, orderList, pgInfo);
			objRecordSet.PrepareStatementSelect(query);
			objRecordSet.productSetWhereInfoItembyItem(0, info);
			objRecordSet.executePrepareStatementSelect();
			result = objRecordSet.productGetInfoListItem(selectList, pgInfo);
		}
		catch(Throwable ex) {
			Applicationlog.LogTruegardenerError(ex.getMessage(), query);
			throw ex;
		}
		finally { objRecordSet.close(); }
		return result;
	}
	@Deprecated
	/**
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public Vector<IItem_Model> get_InfoByAndLikeByItem(selectExpressionRelation selectList, IItem_Model info, orderExpressionRelation orderList, pageExpressionRelation pgInfo)throws Throwable {
		Vector<IItem_Model> result = null;
		recordSet_Model objRecordSet = m_recordSet.clone();
		String query = "";
		try {
			whereItemExpressionRelation infoRelationItem = new whereItemExpressionRelation(info);
			
			query = m_SQLQueryProvider.productQueryGetItemInfoByAndLike(selectList, infoRelationItem, orderList, pgInfo);
			objRecordSet.PrepareStatementSelect(query);
			objRecordSet.productSetWhereInfoLikeItembyItem(0, info);
			objRecordSet.executePrepareStatementSelect();
			result = objRecordSet.productGetInfoListItem(selectList, pgInfo);
		}
		catch(Throwable ex) {
			Applicationlog.LogTruegardenerError(ex.getMessage(), query);
			throw ex;
		}
		finally { objRecordSet.close(); }
		return result;
	}
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	@Deprecated
	public Vector<IItem_Model> get_InfoByOrLikeByItem(selectExpressionRelation selectList, IItem_Model info, orderExpressionRelation orderList, pageExpressionRelation pgInfo)throws Throwable {
		Vector<IItem_Model> result = null;
		recordSet_Model objRecordSet = m_recordSet.clone();
		String query = "";
		try {
			whereItemExpressionRelation infoRelationItem = new whereItemExpressionRelation(info);
			
			query = m_SQLQueryProvider.productQueryGetItemInfoByOrLike(selectList, infoRelationItem, orderList, pgInfo);
			objRecordSet.PrepareStatementSelect(query);
			objRecordSet.productSetWhereInfoLikeItembyItem(0, info);
			objRecordSet.executePrepareStatementSelect();
			result = objRecordSet.productGetInfoListItem(selectList, pgInfo);
		}
		catch(Throwable ex) {
			Applicationlog.LogTruegardenerError(ex.getMessage(), query);
			throw ex;
		}
		finally { objRecordSet.close(); }
		return result;
	}
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public Vector<Object> get_DistinctListInfo(int nDistinctField, whereExpressionRelation whereList, orderExpressionRelation orderList, pageExpressionRelation pgInfo)throws Throwable {
		Vector<Object> result = null;
		recordSet_Model objRecordSet = m_recordSet.clone();
		String query = "";
		try {
			query = m_SQLQueryProvider.productQueryGetDistinctListInfo(nDistinctField, whereList, orderList, pgInfo);
			objRecordSet.PrepareStatementSelect(query);
			objRecordSet.productSetWhereInfoItem(0, whereList);
			objRecordSet.executePrepareStatementSelect();
			result = objRecordSet.productGetFirstInfoList(pgInfo);
		}
		catch(Throwable ex) {
			Applicationlog.LogTruegardenerError(ex.getMessage(), query);
			throw ex;
		}
		finally { objRecordSet.close(); }
		return result;
	}
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public long get_ListCountByInfo(whereExpressionRelation whereList, String strCountNum)throws Throwable {
		long result = 0;
		recordSet_Model objRecordSet = m_recordSet.clone();
		String query = "";
		try {
			query = m_SQLQueryProvider.productQueryGetListCountByInfo(whereList, strCountNum);
			objRecordSet.PrepareStatementSelect(query);
			objRecordSet.productSetWhereInfoItem(0, whereList);
			objRecordSet.executePrepareStatementSelect();
			Object resultCount = null;
			resultCount = objRecordSet.productGetFirstObjectInfo(strCountNum);
			result = Object2Long(resultCount);
		}
		catch(Throwable ex) {
			Applicationlog.LogTruegardenerError(ex.getMessage(), query);
			throw ex;
		}
		finally { objRecordSet.close(); }
		return result;
	}
	/**
	 * @param strMaxNum
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public long get_ListMaxByInfo(int nMaxField, whereExpressionRelation whereList, String strMaxNum)throws Throwable {
		long result = 0;
		recordSet_Model objRecordSet = m_recordSet.clone();
		String query = "";
		try {
			query = m_SQLQueryProvider.productQueryGetListMaxByInfo(nMaxField, whereList, strMaxNum);
			objRecordSet.PrepareStatementSelect(query);
			objRecordSet.productSetWhereInfoItem(0, whereList);
			objRecordSet.executePrepareStatementSelect();
			Object resultCount = null;
			resultCount = objRecordSet.productGetFirstObjectInfo(strMaxNum);
			result = Object2Long(resultCount);
		}
		catch(Throwable ex) {
			Applicationlog.LogTruegardenerError(ex.getMessage(), query);
			throw ex;
		}
		finally { objRecordSet.close(); }
		return result;
	}
	/**
	 * @param nMinField
	 * @param strMinNum
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public long get_ListMinByInfo(int nMinField, whereExpressionRelation whereList, String strMinNum)throws Throwable {
		long result = 0;
		recordSet_Model objRecordSet = m_recordSet.clone();
		String query = "";
		try {
			query = m_SQLQueryProvider.productQueryGetListMinByInfo(nMinField, whereList, strMinNum);
			objRecordSet.PrepareStatementSelect(query);
			objRecordSet.productSetWhereInfoItem(0, whereList);
			objRecordSet.executePrepareStatementSelect();
			Object resultCount = null;
			resultCount = objRecordSet.productGetFirstObjectInfo(strMinNum);
			result = Object2Long(resultCount);
		}
		catch(Throwable ex) {
			Applicationlog.LogTruegardenerError(ex.getMessage(), query);
			throw ex;
		}
		finally { objRecordSet.close(); }
		return result;
	}
	/**
	 * @param strCountNum
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public long get_ListSumByInfo(int nSumField, whereExpressionRelation whereList, String strSumNum)throws Throwable {
		long result = 0;
		recordSet_Model objRecordSet = m_recordSet.clone();
		String query = "";
		try {
			query = m_SQLQueryProvider.productQueryGetListSumByInfo(nSumField, whereList, strSumNum);
			objRecordSet.PrepareStatementSelect(query);
			objRecordSet.productSetWhereInfoItem(0, whereList);
			objRecordSet.executePrepareStatementSelect();
			Object resultCount = null;
			resultCount = objRecordSet.productGetFirstObjectInfo(strSumNum);
			result = Object2Long(resultCount);
		}
		catch(Throwable ex) {
			Applicationlog.LogTruegardenerError(ex.getMessage(), query);
			throw ex;
		}
		finally { objRecordSet.close(); }
		return result;
	}
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public Object set_Insert(IItem_Model infoItem) throws Throwable {
		Object result = null;
		recordSet_Model objRecordSet = m_recordSet.clone();
		String query = "";
		try {
			insertItemExpressionRelation infoRelationItem = new insertItemExpressionRelation(infoItem);
			product_GetPrimaryID(infoItem);
			
			query = m_SQLQueryProvider.productQueryGetInsertItem(infoRelationItem);
			objRecordSet.PrepareStatementInsert(query);
			objRecordSet.productSetInsertItem(infoRelationItem);
			result = objRecordSet.executePrepareStatementInsert();
			if(result==null) {
				result = infoItem.getAvaiable(tbl_Model.field_id);
			}
		}
		catch(Throwable ex) {
			Applicationlog.LogTruegardenerError(ex.getMessage(), query);
			throw ex;
		}
		finally { objRecordSet.close(); }
		return result;
	}
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public long set_InsertAndReturnLong(IItem_Model infoItem) throws Throwable {
		long result = -1;
		try {
			Object resultInsert = null;
			resultInsert = set_Insert(infoItem);
			result = Object2Long(resultInsert);
		}
		catch(Throwable ex) {
			throw ex;
		}
		finally { }
		return result;
	}
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @since ModelWeb 1.0
	 */
	public int set_Update(IItem_Model infoItem) throws Throwable {
		int result = -1;
		recordSet_Model objRecordSet = m_recordSet.clone();
		String query = "";
		try {
			if(infoItem.isAvaiable(tbl_Model.field_id)) {
				updateItemExpressionRelation infoRelationItem = new updateItemExpressionRelation(infoItem);
				query = m_SQLQueryProvider.productQuerySetUpdateItem(infoRelationItem);
				objRecordSet.PrepareStatementUpdate(query);
				objRecordSet.productSetUpdateItem(infoRelationItem);
				result = objRecordSet.executePrepareStatementUpdate();
			}
			else { result = -1; }
		}
		catch(Throwable ex) {
			Applicationlog.LogTruegardenerError(ex.getMessage(), query);
			throw ex;
		}
		finally { objRecordSet.close(); }
		return result;
	}
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public int set_UpdateByInfo(updateExpressionRelation setUpdateList, whereExpressionRelation whereList) throws Throwable {
		int result = -1;
		recordSet_Model objRecordSet = m_recordSet.clone();
		String query = "";
		try {
			query = m_SQLQueryProvider.productQuerySetUpdateByInfo(setUpdateList, whereList);
			objRecordSet.PrepareStatementUpdate(query);
			int nSeek = -1;
			nSeek = objRecordSet.productSetUpdatebyInfoItem(0, setUpdateList);
			nSeek = objRecordSet.productSetWhereInfoItem(nSeek, whereList);
			result = objRecordSet.executePrepareStatementUpdate();
		}
		catch(Throwable ex) 	{
			Applicationlog.LogTruegardenerError(ex.getMessage(), query);
			throw ex;
		}
		finally { objRecordSet.close(); }
		return result;
	}
	/**
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public int set_NullByInfo(setNullExpressionRelation setNullList, whereExpressionRelation whereExpression) throws Throwable {
		int result = -1;
		recordSet_Model objRecordSet = m_recordSet.clone();
		String query = "";
		try {
			query = m_SQLQueryProvider.productQuerySetNullByInfo(setNullList, whereExpression);
			objRecordSet.PrepareStatementUpdate(query);
			objRecordSet.productSetWhereInfoItem(0, whereExpression);
			result = objRecordSet.executePrepareStatementUpdate();
		}
		catch(Throwable ex) {
			Applicationlog.LogTruegardenerError(ex.getMessage(), query);
			throw ex;
		}
		finally { objRecordSet.close(); }
		return result;
	}
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @since ModelWeb 1.0
	 */
	public int set_Delete(Object primaryKey) throws Throwable {
		int result = -1;
		recordSet_Model objRecordSet = m_recordSet.clone();
		String query = "";
		try {
			query = m_SQLQueryProvider.productQuerySetDeletePrimaryInfo(primaryKey);
			objRecordSet.PrepareStatementUpdate(query);
			objRecordSet.setObject(1, primaryKey);
			result = objRecordSet.executePrepareStatementUpdate();
		}
		catch(Throwable ex) 	{
			Applicationlog.LogTruegardenerError(ex.getMessage(), query);
			throw ex;
		}
		finally { objRecordSet.close(); }
		return result;
	}
	/**
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public int set_DeleteByInfo(whereExpressionRelation whereList) throws Throwable {
		int result = -1;
		recordSet_Model objRecordSet = m_recordSet.clone();
		String query = "";
		try {
			query = m_SQLQueryProvider.productQuerySetDeleteByInfo(whereList);
			objRecordSet.PrepareStatementUpdate(query);
			objRecordSet.productSetWhereInfoItem(0, whereList);
			result = objRecordSet.executePrepareStatementUpdate();
		}
		catch(Throwable ex) {
			Applicationlog.LogTruegardenerError(ex.getMessage(), query);
			throw ex;
		}
		finally { objRecordSet.close(); }
		return result;
	}
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public Vector<IItem_Model> get_ListByEqu(selectExpressionRelation selectList, whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo)throws Throwable {
		Vector<IItem_Model> result = null;;
		try {	
			productUpdateEquWhereRelation(whereList, bAndOr);
			result = get_Info(selectList, whereList, orderList, pgInfo);			
		}
		catch(Throwable ex) { throw ex; }
		return result;
	}
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @since ModelWeb 1.0
	 */
	public Vector<IItem_Model> get_ListByLike(selectExpressionRelation selectList, whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo)throws Throwable {
		Vector<IItem_Model> result = null;;
		try {
			productUpdateLikeWhereRelation(likeList, bAndOr);
			result = get_Info(selectList, likeList, orderList, pgInfo);		
		}
		catch(Throwable ex) { throw ex; }
		return result;
	}
//////////////////////////////////getDistinctList//////////////////////////////////////
//////////////////////////////////getDistinctList//////////////////////////////////////
//////////////////////////////////getDistinctList//////////////////////////////////////
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public Vector<Object> get_DistinctListByEqu(int nDistinctField, whereExpressionRelation whereList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo)throws Throwable {
		Vector<Object> result = null;;
		try {	
			productUpdateEquWhereRelation(whereList, bAndOr);
			result = get_DistinctListInfo(nDistinctField, whereList, orderList, pgInfo);			
		}
		catch(Throwable ex) { throw ex; }
		return result;
	}
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @since ModelWeb 1.0
	 */
	public Vector<Object> get_DistinctListByLike(int nDistinctField, whereExpressionRelation likeList, boolean bAndOr, orderExpressionRelation orderList, pageExpressionRelation pgInfo)throws Throwable {
		Vector<Object> result = null;;
		try {	
			productUpdateEquWhereRelation(likeList, bAndOr);
			result = get_DistinctListInfo(nDistinctField, likeList, orderList, pgInfo);			
		}
		catch(Throwable ex) { throw ex; }
		return result;
	}
	/**
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @since ModelWeb 1.0
	 */
	public Vector<Object> get_DistinctListByQuery(String query, executeExpressionRelation executeList, pageExpressionRelation pgInfo) throws Throwable{
		Vector<Object>result = null;
		recordSet_Model objRecordSet = m_recordSet.clone();
		try {	
			objRecordSet.PrepareStatementSelect(query);
			objRecordSet.productSetExecuteQueryItem(executeList);
			result = objRecordSet.productGetFirstInfoList(pgInfo);
		}
		catch(Throwable ex) {
			Applicationlog.LogTruegardenerError(ex.getMessage(), query);
			throw ex;
		}
		finally { objRecordSet.close(); }
		return result;
	}
//////////////////////////////////getQuery//////////////////////////////////////
//////////////////////////////////getQuery//////////////////////////////////////
//////////////////////////////////getQuery//////////////////////////////////////
	/**
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @since ModelWeb 1.0
	 */
	public long get_NumValueByQuery(String query, executeExpressionRelation executeList, String numAsStr)throws Throwable {
		long result = 0;
		recordSet_Model objRecordSet = m_recordSet.clone();
		try {
			objRecordSet.PrepareStatementSelect(query);
			objRecordSet.productSetExecuteQueryItem(executeList);
			objRecordSet.executePrepareStatementSelect();
			Object resultCount = null;
			resultCount = objRecordSet.productGetFirstObjectInfo(numAsStr);
			result = Object2Long(resultCount);
		}
		catch(Throwable ex) {
			Applicationlog.LogTruegardenerError(ex.getMessage(), query);
			throw ex;
		}
		finally { objRecordSet.close(); }
		return result;
	}
//////////////////////////////////getCount//////////////////////////////////////
//////////////////////////////////getCount//////////////////////////////////////
//////////////////////////////////getCount//////////////////////////////////////
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public long get_ListCountByEqu(whereExpressionRelation whereList, boolean bAndOr)throws Throwable {
		long result = 0;
		try {
			productUpdateEquWhereRelation(whereList, bAndOr);
			result = get_ListCountByInfo(whereList, "countAsNum");			
		}
		catch(Throwable ex) { throw ex; }
		return result;
	}
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public long get_ListCountByLike(whereExpressionRelation likeList, boolean bAndOr)throws Throwable {
		long result = 0;
		try {	
			productUpdateLikeWhereRelation(likeList, bAndOr);
			result = get_ListCountByInfo(likeList, "countAsNum");			
		}
		catch(Throwable ex) { throw ex; }
		return result;
	}
	//////////////////////////////////getMax//////////////////////////////////////
	//////////////////////////////////getMax//////////////////////////////////////
	//////////////////////////////////getMax//////////////////////////////////////
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public long get_ListMaxByEqu(int nMaxField, whereExpressionRelation whereList, boolean bAndOr)throws Throwable {
		long result = 0;
		try {
			productUpdateEquWhereRelation(whereList, bAndOr);
			result = get_ListMaxByInfo(nMaxField, whereList, "maxAsNum");			
		}
		catch(Throwable ex) { throw ex; }
		return result;
	}
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public long get_ListMaxByLike(int nMaxField, whereExpressionRelation likeList, boolean bAndOr)throws Throwable {
		long result = 0;
		try {	
			productUpdateLikeWhereRelation(likeList, bAndOr);
			result = get_ListMaxByInfo(nMaxField, likeList, "maxAsNum");			
		}
		catch(Throwable ex) { throw ex; }
		return result;
	}
//////////////////////////////////getMin//////////////////////////////////////
//////////////////////////////////getMin//////////////////////////////////////
//////////////////////////////////getMin//////////////////////////////////////
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public long get_ListMinByEqu(int nMinField, whereExpressionRelation whereList, boolean bAndOr)throws Throwable {
		long result = 0;
		try {
			productUpdateEquWhereRelation(whereList, bAndOr);
			result = get_ListMinByInfo(nMinField, whereList, "minAsNum");			
		}
		catch(Throwable ex) { throw ex; }
		return result;
	}
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public long get_ListMinByLike(int nMinField, whereExpressionRelation likeList, boolean bAndOr)throws Throwable {
		long result = 0;
		try {	
			productUpdateLikeWhereRelation(likeList, bAndOr);
			result = get_ListMinByInfo(nMinField, likeList, "minAsNum");			
		}
		catch(Throwable ex) { throw ex; }
		return result;
	}
	//////////////////////////////////getSum//////////////////////////////////////
	//////////////////////////////////getSum//////////////////////////////////////
	//////////////////////////////////getSum//////////////////////////////////////
	/**
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public long get_ListSumByEqu(int nSumField, whereExpressionRelation whereList, boolean bAndOr)throws Throwable {
		long result = 0;
		try {
			productUpdateEquWhereRelation(whereList, bAndOr);
			result = get_ListSumByInfo(nSumField, whereList, "sumAsNum");			
		}
		catch(Throwable ex) { throw ex; }
		return result;
	}
	/**
	 * @param likeList
	 * @param bAndOr
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public long get_ListSumByLike(int nSumField, whereExpressionRelation likeList, boolean bAndOr)throws Throwable {
		long result = 0;
		try {	
			productUpdateLikeWhereRelation(likeList, bAndOr);
			result = get_ListSumByInfo(nSumField, likeList, "sumAsNum");			
		}
		catch(Throwable ex) { throw ex; }
		return result;
	}
	//////////////////////////////////Update//////////////////////////////////////
	//////////////////////////////////Update//////////////////////////////////////
	//////////////////////////////////Update//////////////////////////////////////
	/**
	 * @return success 1: failed:-1
	 * @throws Throwable
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @since ModelWeb 1.0
	 */
	public int set_UpdateByEqu(updateExpressionRelation setUpdateList, whereExpressionRelation  whereList, boolean bAndOr) throws Throwable {
		int result = -1;
		try {
			productUpdateEquWhereRelation(whereList, bAndOr);
			result = set_UpdateByInfo(setUpdateList, whereList);
		}
		catch(Throwable ex) {
			throw ex;
		}
		return result;
	}
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public int set_UpdateByLike(updateExpressionRelation setUpdateList, whereExpressionRelation likeList, boolean bAndOr) throws Throwable {
		int result = -1;
		try {
			productUpdateLikeWhereRelation(likeList, bAndOr);
			result = set_UpdateByInfo(setUpdateList, likeList);
		}
		catch(Throwable ex) { throw ex; }
		return result;
	}
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @since ModelWeb 1.0
	 */
	public int set_NullByEqu(setNullExpressionRelation setNullList, whereExpressionRelation whereList, boolean bAndOr) throws Throwable {
		int result = -1;
		try {
			productUpdateEquWhereRelation(whereList, bAndOr);
			result = set_NullByInfo(setNullList, whereList);
		}
		catch(Throwable ex) { throw ex; }
		return result;
	}
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public int set_NullByLike(setNullExpressionRelation setNullList, whereExpressionRelation likeList, boolean bAndOr) throws Throwable {
		int result = -1;
		try {
			productUpdateLikeWhereRelation(likeList, bAndOr);
			result = set_NullByInfo(setNullList, likeList);
		}
		catch(Throwable ex) { throw ex; }
		return result;
	}
	//////////////////////////////////Delete//////////////////////////////////////
	//////////////////////////////////Delete//////////////////////////////////////
	//////////////////////////////////Delete//////////////////////////////////////
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @since ModelWeb 1.0
	 */
	public int set_DeleteByEqu(whereExpressionRelation whereList, boolean bAndOr) throws Throwable {
		int result = -1;
		try {
			productUpdateEquWhereRelation(whereList, bAndOr);
			result = set_DeleteByInfo(whereList);
		}
		catch(Throwable ex) { throw ex; }
		return result;
	}
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @since ModelWeb 1.0
	 */
	public int set_DeleteByLike(whereExpressionRelation likeList, boolean bAndOr) throws Throwable {
		int result = -1;
		try {
			productUpdateEquWhereRelation(likeList, bAndOr);
			result = set_DeleteByInfo(likeList);
		}
		catch(Throwable ex) { throw ex; }
		return result;
	}
	/**
	 * @param infoItem
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public void product_GetPrimaryID(IItem_Model infoItem) throws Throwable {
		recordSet_Model objRecordSet = m_recordSet.clone();
		try {	
			if(infoItem!=null) {
				if(!infoItem.isAvaiable(tbl_Model.field_id) && m_connDB.getDbType()==dbConn_Model.DB_ORACLE) {
					Object nID = null;					
					String query = "";
					query = m_SQLQueryProvider.productQueryGetPrimaryID(infoItem);
					objRecordSet.PrepareStatementSelect(query);
					objRecordSet.executePrepareStatementSelect();
					nID = objRecordSet.productGetFirstObjectInfo(1);
					if(nID==null) { nID = 1; }
					infoItem.setAvaiable(tbl_Model.field_id, nID);
				}
			}
		}
		catch(Throwable ex) {
			objRecordSet.close();
		}
	}
	/**
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	public Vector<HashMap> getVH_Info(selectExpressionRelation selectList, whereExpressionRelation whereList, orderExpressionRelation orderList, pageExpressionRelation pgInfo)throws Throwable {
		Vector<HashMap> result = null;
		recordSet_Model objRecordSet = m_recordSet.clone();
		String query = "";
		try {
			query = m_SQLQueryProvider.productQueryGetInfo(selectList, whereList, orderList, pgInfo);
			objRecordSet.PrepareStatementSelect(query);
			objRecordSet.productSetWhereInfoItem(0, whereList);
			objRecordSet.executePrepareStatementSelect();
			result = objRecordSet.productGetInfoListHash(pgInfo);
		}
		catch(Throwable ex) {
			Applicationlog.LogTruegardenerError(ex.getMessage(), query);
			throw ex;
		}
		finally { objRecordSet.close(); }
		return result;
	}
	/**
	 */
	public Document getXD_Info(selectExpressionRelation selectList, whereExpressionRelation whereList, orderExpressionRelation orderList, pageExpressionRelation pgInfo, String strXmlRoot, String strXmlRow)throws Throwable {
		Document result = null;
		recordSet_Model objRecordSet = m_recordSet.clone();
		String query = "";
		try {
			query = m_SQLQueryProvider.productQueryGetInfo(selectList, whereList, orderList, pgInfo);
			objRecordSet.PrepareStatementSelect(query);
			objRecordSet.productSetWhereInfoItem(0, whereList);
			objRecordSet.executePrepareStatementSelect();
			result = objRecordSet.productGetInfoListXml(pgInfo, strXmlRoot, strXmlRow);
		}
		catch(Throwable ex) {
			Applicationlog.LogTruegardenerError(ex.getMessage(), query);
			throw ex;
		}
		finally { objRecordSet.close(); }
		return result;
	}
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	@SuppressWarnings("unchecked")
	public Object execute_Query(String query, executeExpressionRelation setExecuteList, int bSelectOrInsertOrUpdate)throws Throwable {
		Object result = null;
		recordSet_Model objRecordSet = m_recordSet.clone();
		try {
			if(bSelectOrInsertOrUpdate>=sqlQuery_Model.QUERY_SELECT && bSelectOrInsertOrUpdate < sqlQuery_Model.QUERY_INSERT) {
				objRecordSet.PrepareStatementSelect(query);
				objRecordSet.productSetExecuteQueryItem(setExecuteList);
				if (m_ItemModel != null) {
					objRecordSet.executePrepareStatementSelect();
					result = objRecordSet.productGetInfoListItem(null, null);
				}
				else {
					objRecordSet.executePrepareStatementSelect();
					result = recordSet_Model.toVectorHashData(objRecordSet.getResultSet()); 
				}
			}
			else if(bSelectOrInsertOrUpdate>=sqlQuery_Model.QUERY_INSERT && bSelectOrInsertOrUpdate < sqlQuery_Model.QUERY_UPDATE) {
				objRecordSet.PrepareStatementInsert(query);
				objRecordSet.productSetExecuteQueryItem(setExecuteList);				
				result = objRecordSet.executePrepareStatementInsert();
			}
			else if(bSelectOrInsertOrUpdate>=sqlQuery_Model.QUERY_UPDATE && bSelectOrInsertOrUpdate< sqlQuery_Model.QUERY_DELETE) {
				objRecordSet.PrepareStatementUpdate(query);
				objRecordSet.productSetExecuteQueryItem(setExecuteList);
				result = objRecordSet.executePrepareStatementUpdate();
			}
			else {
				objRecordSet.PrepareStatementSelect(query);
				objRecordSet.productSetExecuteQueryItem(setExecuteList);
				objRecordSet.executePrepareStatementSelect();
				result = recordSet_Model.toVectorHashData(objRecordSet.getResultSet());
			}
		}
		catch(Throwable ex) {
			Applicationlog.LogTruegardenerError(ex.getMessage(), query);
			throw ex;
		}
		finally { objRecordSet.close(); }
		return result;
	}
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @since ModelWeb 1.0
	 */
	@SuppressWarnings("unchecked")
	public Object execute_Query(String query, int bSelectOrInsertOrUpdate)throws Throwable {
		Object result = null;
		recordSet_Model objRecordSet = m_recordSet.clone();
		try {
			if(bSelectOrInsertOrUpdate>=sqlQuery_Model.QUERY_SELECT && bSelectOrInsertOrUpdate < sqlQuery_Model.QUERY_INSERT) {
				objRecordSet.executeStatementSelect(query);
				if (m_ItemModel != null) {
					result = objRecordSet.productGetInfoListItem(null, null);				
				}
				else { result = recordSet_Model.toVectorHashData(objRecordSet.getResultSet()); }
			}
			else if(bSelectOrInsertOrUpdate>=sqlQuery_Model.QUERY_INSERT && bSelectOrInsertOrUpdate < sqlQuery_Model.QUERY_UPDATE) {
				result = objRecordSet.executeStatementInsert(query);
			}
			else if(bSelectOrInsertOrUpdate>=sqlQuery_Model.QUERY_UPDATE && bSelectOrInsertOrUpdate < sqlQuery_Model.QUERY_DELETE) {
				result = objRecordSet.executeStatementUpdate(query);
			}
			else {
				objRecordSet.executeStatementSelect(query);
				result = recordSet_Model.toVectorHashData(objRecordSet.getResultSet());
			}
		}
		catch(Throwable ex) {
			Applicationlog.LogTruegardenerError(ex.getMessage(), query);
			throw ex;
		}
		finally { objRecordSet.close(); }
		return result;
	}
	//////////////////////////////private///////////////////////////////////////
	//////////////////////////////private///////////////////////////////////////
	//////////////////////////////private///////////////////////////////////////
	/**
	 * @param bAndOr
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	protected void productUpdateEquWhereRelation(whereExpressionRelation whereList, boolean bAndOr) throws Throwable {
		try {
			if(whereList!=null) {
				whereList.setEquWhereRelation(bAndOr);
			}
		}
		catch(Throwable ex) { throw ex; }
	}
	/**
	 * @param bAndOr
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	protected void productUpdateLikeWhereRelation(whereExpressionRelation whereList, boolean bAndOr) throws Throwable {
		try {	
			if(whereList!=null) {
				whereList.setLikeWhereRelation(bAndOr);
			}
		}
		catch(Throwable ex) { throw ex; }
	}
	/**
	 * @param resultObj
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @since ModelWeb 1.0
	 */
	private long Object2Long(Object resultObj)throws Throwable {
		long result = 0;
		try {
			if(resultObj instanceof Long) {
				result = (Long)resultObj;
			}
			else if(resultObj instanceof Integer) {
				result = (Integer)resultObj;
			}
			else if(resultObj instanceof Short) {
				result = (Short)resultObj;
			}
			else if(resultObj instanceof Byte) {
				result = (Byte)resultObj;
			}
			else if(resultObj instanceof BigDecimal) {
				result = ((BigDecimal)resultObj).toBigInteger().longValue();
			}
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
}
