package common.extern.olena.model.dataaccess.util.core.query;

import common.extern.olena.model.dataaccess.mvc.base.IItem_Model;
import common.extern.olena.model.dataaccess.mvc.base.tbl_Model;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.*;

/**
 * SQL query creation Object
 *
 * @author Olena.Zagreba in Truegardener TEAM 
 * @version ModelWeb 1.0, 2011. 12. 26 PM 9:37:23
 * @since ModelWeb 1.0
 */
public abstract class sqlQuery_Model
{
	public sqlQuery_Model(IItem_Model tableInfos)
	{
		super();
		m_ItemModel = tableInfos;
		InitSQL();
	}
	/************Constans************/
	public final static int QUERY_SELECT = 0;
	public final static int QUERY_SELECT_FIELD = 1;
	public final static int QUERY_SELECT_DISTINCT = 2;
	public final static int QUERY_SELECT_LISTCOUNT = 3;
	public final static int QUERY_SELECT_GETMAX = 4;
	public final static int QUERY_SELECT_GETMIN = 5;
	public final static int QUERY_SELECT_GETSUM = 6;
	
	public final static int QUERY_INSERT = 10;
	public final static int QUERY_UPDATE = 100;
	public final static int QUERY_DELETE = 1000;
	public final static int QUERY_SPECIAL = 10000;
	
	public static final int EXPRESSION_EQU = 0;
	public static final int EXPRESSION_SMALL = 1;
	public static final int EXPRESSION_SMALLEQU = 2;
	public static final int EXPRESSION_LARGE = 3;
	public static final int EXPRESSION_LARGEEQU = 4;
	public static final int EXPRESSION_NOEQU = 5;
	public static final int EXPRESSION_LIKE = 6;
	public static final int EXPRESSION_ISNULL = 7;
	public static final int EXPRESSION_ISNOTNULL = 8;
	
	public static final int EXPRESSION_UPDATE_PLUSNUM = -10;
	public static final int EXPRESSION_UPDATE_NULL = -20;
	public static final int EXPRESSION_UPDATE_PLUSFORWSTR = -100;
	public static final int EXPRESSION_UPDATE_PLUSBACKSTR = -200;
	
	public static final int RELATION_AND = 0;
	public static final int RELATION_OR = 1;
	
	/************Variables************/
	
	/**Return records max count*/
	private int m_nMaxTop = 10000;
	protected IItem_Model m_ItemModel = null;

	//expression
	protected String m_strSpace = " ";
	protected String m_strComma = ",";
	protected String m_strQus = "?";
	protected String m_strEqu = "=";
	protected String m_strSmall = "<";
	protected String m_strSmallEqu = "<=";
	protected String m_strLarge = ">";
	protected String m_strLargeEqu = ">=";
	protected String m_strNoEqu = "<>";
	protected String m_strLike = "like";
	protected String m_strIsnull = "is null";
	protected String m_strIsnotnull = "is not null";
	//relation
	protected String m_strNULL = "NULL";

	protected String m_strAnd = "and";
	protected String m_strOr = "or";
	
	protected String m_strSelect = "select";
	protected String m_strInsert = "insert";
	protected String m_strUpdate = "update";
	protected String m_strDelete = "delete";
	
	protected String m_strFrom = "from";
	protected String m_strWhere = "where";
	
	protected String m_strSet = "set";
	protected String m_strValue = "values";
	
	protected String m_strLimit = null;
	protected String m_strDistinct = "distinct";
	protected String m_strCount = "count";
	protected String m_strSum = "sum";
	protected String m_strMax = "max";
	protected String m_strMin = "min";
	
	protected String m_strOrderby = "order by";
	protected String m_strOrder_ASC = "asc";
	protected String m_strOrder_DESC = "desc";
	
	/*****************getter and setter************************/
	public IItem_Model getItemModel() { return m_ItemModel; }
	public void setItemModel(IItem_Model itemModel) { m_ItemModel = itemModel; }
	
	public String getSpace() { return m_strSpace; }
	public String getComma() { return m_strComma; }
	public String getQus() { return m_strQus; }
	
	public String getEqu() { return m_strEqu; }
	public String getSmall() { return m_strSmall; }
	public String getSmallEqu() { return m_strSmallEqu; }
	
	public String getLarge() { return m_strLarge; }
	public String getLargeEqu() { return m_strLargeEqu;}
	public String getNoEqu() { return m_strNoEqu;}
	public String getLike() { return m_strLike; }
	public String getIsnull() { return m_strIsnull; }
	public String getIsnotnull() { return m_strIsnotnull; }
	
	public String getNULL() { return m_strNULL; }
	
	public String getSelect() { return m_strSelect; }
	public String getDelete() { return m_strDelete; }
	public String getInsert() { return m_strInsert; }
	public String getUpdate() { return m_strUpdate; }
	
	public String getWhere() { return m_strWhere; }
	public String getFrom() { return m_strFrom;	}
	
	public String getLimit() { return m_strLimit; }
	public String getDistinct() { return m_strDistinct; }
	public String getCount() { return m_strCount; }
	public String getSum() { return m_strSum; }
	public String getMax() { return m_strMax; }
	public String getMin() { return m_strMin; }

	public String getAnd() { return m_strAnd; }
	public String getOr() { return m_strOr; }	

	public String getOrderby() { return m_strOrderby; }
	public String getOrderAsc() { return m_strOrder_ASC; }
	public String getOrderDesc() { return m_strOrder_DESC; }
	
	/****************abstract methods****************/
	/**
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 26 PM 11:11:54
	 * @since ModelWeb 1.0
	 */
	protected abstract void InitSQL();
	/**
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, AM 11:15:48
	 * @since ModelWeb 1.0
	 */
	public abstract String productQueryCreateTable()throws Throwable;
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, AM 11:15:50
	 * @since ModelWeb 1.0
	 */
	public abstract String productQueryDropTable()throws Throwable;
	/**
	 * @param infoItem
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 04 AM 8:22:54
	 * @since ModelWeb 1.0
	 */
	public abstract String productQueryGetPrimaryID(IItem_Model infoItem) throws Throwable;
	/**
	 * @param strPlus
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 03. 04 AM 10:55:13
	 * @since ModelWeb
	 */
	public abstract String getPlusStringForward(String strPlus);
	/**
	 * @param strPlus
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 03. 04 AM 10:55:16
	 * @since ModelWeb
	 */
	public abstract String getPlusStringBack(String strPlus);
	/**
	 * @param nQueryType (select, insert, update, delete)
	 * @param strSelect
	 * @param strFrom
	 * @param strWhere
	 * @param strOrder
	 * @param nLimit
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 18 AM 11:01:38
	 * @since ModelWeb 1.0
	 */
	public abstract String getQuery(int nQueryType, String strSelect, String strFrom, String strWhere, String strOrder, pageExpressionRelation pageInfo);
	
	/*****************************Query******************************/
	/**
	 * @author Olena.Zagreba
	 * @version ModelWeb 2012. 12. 16 PM 6:27:25
	 * @since ModelWeb
	 */
	public String productQueryCreateSequence()throws Throwable {
		StringBuilder result = new StringBuilder("");
		if(getItemModel().getFieldInfo(tbl_Model.field_id).isPrimaryAutoIncrease()) {
			result.append(String.format("create sequence %1$s_SEQ minvalue 1 maxvalue 9999999999999999999999999 start with 1 increment by 1 cache 20\r\n", getItemModel().getTableName()));
		}
		return result.toString();
	}
	/**
	 * @param primaryKey
	 * @return Database Item
	 * @throws Throwable
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 25 PM 5:31:28
	 * @since ModelWeb 1.0
	 */
	public String productQueryGetPrimaryInfo(selectExpressionRelation selectList, Object primaryKey)throws Throwable {
		String result = "";
		try {	
			String strSelect = "", strWhere = "", strFrom = "";
			strSelect =	getProductQuerySelect(selectList);
			strFrom = m_ItemModel.getTableName();
			strWhere = getEquQuery(m_ItemModel.getFieldLabel(tbl_Model.field_id));
			result = getQuery(QUERY_SELECT_FIELD, strSelect, strFrom, strWhere, null, null);
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
//////////////////////////////////getList//////////////////////////////////////
//////////////////////////////////getList//////////////////////////////////////
//////////////////////////////////getList//////////////////////////////////////	
	/**
	 * @param selectFieldList
	 * @param whereList
	 * @param orderList
	 * @param nLimit
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 14 PM 2:06:34
	 * @since ModelWeb 1.0
	 */
	public String productQueryGetInfo(selectExpressionRelation selectList, whereExpressionRelation whereList, orderExpressionRelation orderList, pageExpressionRelation pageInfo)throws Throwable {
		String result = "";
		try {	
			String strSelect = "", strWhere = "", strFrom = "", strOrder = "";
			
			strSelect =	getProductQuerySelect(selectList);
			strFrom = m_ItemModel.getTableName();
			strWhere = getProductQueryWhere(whereList);
			strOrder = getProductQueryOrder(orderList);
			
			if(pageInfo==null) {pageInfo = new pageExpressionRelation();}
			if(pageInfo.getLimit()==-1) { pageInfo.setLimit(m_nMaxTop); } 
			result = getQuery(QUERY_SELECT_FIELD, strSelect, strFrom, strWhere, strOrder, pageInfo);
		}
		catch(Throwable ex) { throw ex; }
		finally
		{
		}
		return result;
	}

	/**
	 * @param selectList
	 * @param infoRelationItem
	 * @param orderList
	 * @param nLimit
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, PM 5:29:05
	 * @since ModelWeb 1.0
	 */
	public String productQueryGetItemInfoByAndEqu(selectExpressionRelation selectList, whereItemExpressionRelation infoRelationItem, orderExpressionRelation orderList, pageExpressionRelation pageInfo)throws Throwable {
		String result = "";
		try {	
			String strSelect = "", strWhere = "", strFrom = "", strOrder = "";

			strSelect =	getProductQuerySelect(selectList);
			strFrom = m_ItemModel.getTableName();
			strWhere = getProductQueryAndEquWhereByItem(infoRelationItem);
			strOrder = getProductQueryOrder(orderList);

			if(pageInfo==null) {pageInfo = new pageExpressionRelation();}
			if(pageInfo.getLimit()==-1) { pageInfo.setLimit(m_nMaxTop); } 
			result = getQuery(QUERY_SELECT_FIELD, strSelect, strFrom, strWhere, strOrder, pageInfo);
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}

	/**
	 * @param selectList
	 * @param infoRelationItem
	 * @param orderList
	 * @param nLimit
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, PM 5:29:03
	 * @since ModelWeb 1.0
	 */
	public String productQueryGetItemInfoByOrEqu(selectExpressionRelation selectList, whereItemExpressionRelation infoRelationItem, orderExpressionRelation orderList, pageExpressionRelation pageInfo)throws Throwable {
		String result = "";
		try {	
			String strSelect = "", strWhere = "", strFrom = "", strOrder = "";
			
			strSelect =	getProductQuerySelect(selectList);
			strFrom = m_ItemModel.getTableName();
			strWhere = getProductQueryOrEquWhereByItem(infoRelationItem);
			strOrder = getProductQueryOrder(orderList);
			
			if(pageInfo==null) {pageInfo = new pageExpressionRelation();}
			if(pageInfo.getLimit()==-1) { pageInfo.setLimit(m_nMaxTop); } 
			result = getQuery(QUERY_SELECT_FIELD, strSelect, strFrom, strWhere, strOrder, pageInfo);
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
	/**
	 * @param selectList
	 * @param infoRelationItem
	 * @param orderList
	 * @param pageInfo
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 04 AM 8:22:26
	 * @since ModelWeb 1.0
	 */
	public String productQueryGetItemInfoByAndLike(selectExpressionRelation selectList, whereItemExpressionRelation infoRelationItem, orderExpressionRelation orderList, pageExpressionRelation pageInfo)throws Throwable {
		String result = "";
		try {
			String strSelect = "", strWhere = "", strFrom = "", strOrder = "";
			strSelect =	getProductQuerySelect(selectList);
			strFrom = m_ItemModel.getTableName();
			strWhere = getProductQueryAndLikeWhereByItem(infoRelationItem);
			strOrder = getProductQueryOrder(orderList);
			if(pageInfo==null) {pageInfo = new pageExpressionRelation();}
			if(pageInfo.getLimit()==-1) { pageInfo.setLimit(m_nMaxTop); } 
			result = getQuery(QUERY_SELECT_FIELD, strSelect, strFrom, strWhere, strOrder, pageInfo);
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
	/**
	 * @param selectList
	 * @param infoRelationItem
	 * @param orderList
	 * @param nLimit
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, PM 6:34:30
	 * @since ModelWeb 1.0
	 */
	public String productQueryGetItemInfoByOrLike(selectExpressionRelation selectList, whereItemExpressionRelation infoRelationItem, orderExpressionRelation orderList, pageExpressionRelation pageInfo)throws Throwable {
		String result = "";
		try {	
			String strSelect = "", strWhere = "", strFrom = "", strOrder = "";
			
			strSelect =	getProductQuerySelect(selectList);
			strFrom = m_ItemModel.getTableName();
			strWhere = getProductQueryOrLikeWhereByItem(infoRelationItem);
			strOrder = getProductQueryOrder(orderList);
			
			if(pageInfo==null) {pageInfo = new pageExpressionRelation();}
			if(pageInfo.getLimit()==-1) { pageInfo.setLimit(m_nMaxTop); } 
			result = getQuery(QUERY_SELECT_FIELD, strSelect, strFrom, strWhere, strOrder, pageInfo);
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
	/**
	 * @param nDistinctField
	 * @param whereList
	 * @param orderList
	 * @param nLimit
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 14 PM 1:23:47
	 * @since ModelWeb 1.0
	 */
	public String productQueryGetDistinctListInfo(int nDistinctField, whereExpressionRelation whereList, orderExpressionRelation orderList, pageExpressionRelation pageInfo)throws Throwable {
		String result = "";
		try {
			String strSelect = "", strFrom = "", strWhere = "", strOrder = "";
			strSelect =	getDistinctQuery(m_ItemModel.getFieldLabel(nDistinctField)); 
			strFrom = m_ItemModel.getTableName();
			strWhere = getProductQueryWhere(whereList);
			strOrder = getProductQueryOrder(orderList);
			if(pageInfo==null) {pageInfo = new pageExpressionRelation();}
			if(pageInfo.getLimit()==-1) { pageInfo.setLimit(m_nMaxTop); }
			result = getQuery(QUERY_SELECT_DISTINCT, strSelect, strFrom, strWhere, strOrder, pageInfo);
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
	/**
	 * @param whereList
	 * @param strCountNum
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 15 PM 12:49:57
	 * @since ModelWeb 1.0
	 */
	public String productQueryGetListCountByInfo(whereExpressionRelation whereList, String strCountNum)throws Throwable {
		String result = "";
		try {
			String strSelect = "", strFrom = "", strWhere = "";
			
			strSelect =	getCountQuery(strCountNum);
			strFrom = m_ItemModel.getTableName();
			strWhere = getProductQueryWhere(whereList);
			
			result = getQuery(QUERY_SELECT_LISTCOUNT, strSelect, strFrom, strWhere, null, null);
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
	/**
	 * @param whereList
	 * @param strMaxNum
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 11. 01 AM 11:18:30
	 * @since ModelWeb 1.0
	 */
	public String productQueryGetListMaxByInfo(int nMaxField, whereExpressionRelation whereList, String strMaxNum)throws Throwable {
		String result = "";
		try {
			String strSelect = "", strFrom = "", strWhere = "";
			
			strSelect =	getMaxQuery(m_ItemModel.getFieldLabel(nMaxField), strMaxNum);
			strFrom = m_ItemModel.getTableName();
			strWhere = getProductQueryWhere(whereList);
			
			result = getQuery(QUERY_SELECT_GETMAX, strSelect, strFrom, strWhere, null, null);
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
	/**
	 * @param nMinField
	 * @param whereList 
	 * @param strMinNum
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 11. 01 AM 11:37:29
	 * @since ModelWeb 1.0
	 */
	public String productQueryGetListMinByInfo(int nMinField, whereExpressionRelation whereList, String strMinNum)throws Throwable {
		String result = "";
		try {
			String strSelect = "", strFrom = "", strWhere = "";
			strSelect =	getMinQuery(m_ItemModel.getFieldLabel(nMinField), strMinNum);
			strFrom = m_ItemModel.getTableName();
			strWhere = getProductQueryWhere(whereList);
			result = getQuery(QUERY_SELECT_GETMIN, strSelect, strFrom, strWhere, null, null);
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
	/**
	 * @param whereList 
	 * @param strSumNum
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2011. 08. 18 PM 5:19:05
	 * @since ModelWeb 1.0
	 */
	public String productQueryGetListSumByInfo(int nSumField, whereExpressionRelation whereList, String strSumNum)throws Throwable {
		String result = "";
		try {
			String strSelect = "", strFrom = "", strWhere = "";
			strSelect = getSumQuery(m_ItemModel.getFieldLabel(nSumField), strSumNum);
			strFrom = m_ItemModel.getTableName();
			strWhere = getProductQueryWhere(whereList);
			result = getQuery(QUERY_SELECT_GETSUM, strSelect, strFrom, strWhere, null, null);
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
	/**
	 * @param infoRelationItem
	 * @return succeed:Added database's key id, failed:-1
	 * @throws Throwable
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 26 PM 8:36:34
	 * @since ModelWeb 1.0
	 */
	public String productQueryGetInsertItem(insertItemExpressionRelation infoRelationItem) throws Throwable {
		String result = "";
		try {
			String strSelect = "", strFrom = "";
			strFrom = m_ItemModel.getTableName();
			strSelect = getProductQueryInsertItem(infoRelationItem);
			result = getQuery(QUERY_INSERT, strSelect, strFrom, null, null, null); 
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
	
	/**
	 * @param infoRelationItem 
	 * @return Succed:1, Failed:-1
	 * @throws Throwable
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 26 PM 8:36:52
	 * @since ModelWeb 1.0
	 */
	public String productQuerySetUpdateItem(updateItemExpressionRelation infoRelationItem) throws Throwable {
		String result = "";
		try {
			String strSelect = "", strFrom = ""; String strWhere = "";
			if(infoRelationItem.getRelations().isAvaiable(tbl_Model.field_id)) { strWhere+=getEquQuery(m_ItemModel.getFieldLabel(tbl_Model.field_id)); }
			strFrom = m_ItemModel.getTableName();
			strSelect = getProductQueryUpdateItem(infoRelationItem);
			result = getQuery(QUERY_UPDATE, strSelect, strFrom, strWhere, null, null);
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
	/**
	 * @param setUpdateList	
	 * @param setObList
	 * @param whereList 
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 14 PM 1:25:01
	 * @since ModelWeb 1.0
	 */
	public String productQuerySetUpdateByInfo(updateExpressionRelation setUpdateList, whereExpressionRelation whereList) throws Throwable {
		String result = "";
		try {
			String strSelect = "", strFrom = "", strWhere = "";
			strFrom = m_ItemModel.getTableName();
			strWhere = getProductQueryWhere(whereList);
			strSelect =	getProductQueryUpdate(setUpdateList);
			result = getQuery(QUERY_UPDATE, strSelect, strFrom, strWhere, null, null);
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
	/**
	 * @param setFieldList
	 * @param whereList
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 14 PM 1:25:15
	 * @since ModelWeb 1.0
	 */
	public String productQuerySetNullByInfo(setNullExpressionRelation setNullList, whereExpressionRelation whereList) throws Throwable {
		String result = "";
		try {
			String strSelect = "", strFrom = "", strWhere = "";
			strFrom = m_ItemModel.getTableName();
			strWhere = getProductQueryWhere(whereList);
			strSelect =	getProductQuerySetNull(setNullList);
			result = getQuery(QUERY_UPDATE, strSelect, strFrom, strWhere, null, null);
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
	/**
	 * @param primaryKey
	 * @throws Throwable
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 26 PM 8:39:04
	 * @since ModelWeb 1.0
	 */
	public String productQuerySetDeletePrimaryInfo(Object primaryKey) throws Throwable {
		String result = "";
		try {
			String strFrom = "", strWhere = "";			
			strFrom = m_ItemModel.getTableName();
			strWhere = getEquQuery(m_ItemModel.getFieldLabel(tbl_Model.field_id));
			result = getQuery(QUERY_DELETE, null, strFrom, strWhere, null, null);
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
	/**
	 * @param whereList 
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 14 PM 1:25:37
	 * @since ModelWeb 1.0
	 */
	public String productQuerySetDeleteByInfo(whereExpressionRelation whereList) throws Throwable {
		String result = "";
		try {
			String strSelect = "", strFrom = "", strWhere = "";
			strFrom = m_ItemModel.getTableName();
			strWhere = getProductQueryWhere(whereList); 
			result = getQuery(QUERY_DELETE, strSelect, strFrom, strWhere, null, null);
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
	/////////////////////////////////////Query//////////////////////////////////////
	/**
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 18 AM 9:56:00
	 * @since ModelWeb 1.0
	 */
	public String getProductQuerySelect(selectExpressionRelation selectList) throws Throwable {
		String result = "";
		try {	
			if(selectList==null) {
				selectList = new selectExpressionRelation();
			}			
			result = selectList.toSQLString(this);
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
	/**
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 18 AM 9:56:03
	 * @since ModelWeb 1.0
	 */
	public String getProductQueryWhere(whereExpressionRelation whereList) throws Throwable {
		String result = "";
		try {	
			if(whereList==null) {
				whereList = new whereExpressionRelation();
			}			
			result = whereList.toSQLString(this, -1);
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
	/**
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, PM 6:35:59
	 * @since ModelWeb 1.0
	 */
	public String getProductQueryAndEquWhereByItem(whereItemExpressionRelation infoRelationItem) throws Throwable {
		String result = "";
		try {	
			if(infoRelationItem==null) {
				infoRelationItem = new whereItemExpressionRelation(null);
			}			
			result = infoRelationItem.toSQLString(this, sqlQuery_Model.RELATION_AND, sqlQuery_Model.EXPRESSION_EQU);
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
	/**
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, PM 6:35:57
	 * @since ModelWeb 1.0
	 */
	public String getProductQueryOrEquWhereByItem(whereItemExpressionRelation infoRelationItem) throws Throwable {
		String result = "";
		try {	
			if(infoRelationItem==null) {
				infoRelationItem = new whereItemExpressionRelation(null);
			}			
			result = infoRelationItem.toSQLString(this, sqlQuery_Model.RELATION_OR, sqlQuery_Model.EXPRESSION_EQU);
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
	/**
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, PM 6:35:55
	 * @since ModelWeb 1.0
	 */
	public String getProductQueryAndLikeWhereByItem(whereItemExpressionRelation infoRelationItem) throws Throwable {
		String result = "";
		try {	
			if(infoRelationItem==null) {
				infoRelationItem = new whereItemExpressionRelation(null);
			}			
			result = infoRelationItem.toSQLString(this, sqlQuery_Model.RELATION_AND, sqlQuery_Model.EXPRESSION_LIKE);
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
	/**
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, PM 6:35:53
	 * @since ModelWeb 1.0
	 */
	public String getProductQueryOrLikeWhereByItem(whereItemExpressionRelation infoRelationItem) throws Throwable {
		String result = "";
		try {	
			if(infoRelationItem==null) {
				infoRelationItem = new whereItemExpressionRelation(null);
			}			
			result = infoRelationItem.toSQLString(this, sqlQuery_Model.RELATION_OR, sqlQuery_Model.EXPRESSION_LIKE);
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
	/**
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 18 AM 10:59:47
	 * @since ModelWeb 1.0
	 */
	public String getProductQueryOrder(orderExpressionRelation orderList) throws Throwable {
		String result = "";
		try {	
			if(orderList==null) {
				orderList = new orderExpressionRelation();
			}			
			result = orderList.toSQLString(this);
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
	/**
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 18 AM 10:09:57
	 * @since ModelWeb 1.0
	 */
	public String getProductQueryInsertItem(insertItemExpressionRelation infoRelationItem) throws Throwable{
		String result = "";
		try {	
			if(infoRelationItem==null) {
				infoRelationItem = new insertItemExpressionRelation(null);
			}			
			result = infoRelationItem.toSQLString(this);
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
	/**
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 18 AM 10:10:04
	 * @since ModelWeb 1.0
	 */
	public String getProductQueryUpdateItem(updateItemExpressionRelation infoRelationItem) throws Throwable {
		String result = "";
		try {	
			if(infoRelationItem==null) {
				infoRelationItem = new updateItemExpressionRelation(null);
			}			
			result = infoRelationItem.toSQLString(this);
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
	/**
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 18 AM 10:12:22
	 * @since ModelWeb 1.0
	 */
	public String getProductQueryUpdate(updateExpressionRelation infoUpdateList) throws Throwable {
		String result = "";
		try {	
			if(infoUpdateList==null) {
				infoUpdateList = new updateExpressionRelation();
			}			
			result = infoUpdateList.toSQLString(this);
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
	/**
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 18 AM 10:16:26
	 * @since ModelWeb 1.0
	 */
	public String getProductQuerySetNull(setNullExpressionRelation setNullList) throws Throwable {
		String result = "";
		try {
			if(setNullList==null) {
				setNullList = new setNullExpressionRelation();
			}			
			result = setNullList.toSQLString(this);
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
	/**
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 27 AM 10:58:48
	 * @since ModelWeb 1.0
	 */
	public String getDistinctQuery(String strField) {
		String query = "";
		if(strField!=null) 	{ query = m_strSpace + m_strDistinct + "(" + strField + ")"; }
		return query;
	}
	/**
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 27 AM 11:57:52
	 * @since ModelWeb 1.0
	 */
	public String getCountQuery(String strCount) {
		String query = "";
		query = m_strSpace + m_strCount + "(" + "*" + ")" + m_strSpace + "as" + m_strSpace + strCount;
		return query;
	}
	/**
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2011. 08. 18 PM 5:21:02
	 * @since ModelWeb 1.0
	 */
	public String getSumQuery(String strField, String strSum) {
		String query = "";
		query = m_strSpace + m_strSum + "(" + strField + ")" + m_strSpace + "as" + m_strSpace + strSum;
		return query;
	}
	/**
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 11. 01 AM 11:04:35
	 * @since ModelWeb 1.0
	 */
	public String getMaxQuery(String strField, String strMax) {
		String query = "";
		query = m_strSpace + m_strMax + "(" + strField + ")" + m_strSpace + "as" + m_strSpace + strMax;
		return query;
	}
	/**
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 11. 01 AM 11:04:35
	 * @since ModelWeb 1.0
	 */
	public String getMinQuery(String strField, String strMin) {
		String query = "";
		query = m_strSpace + m_strMin + "(" + strField + ")" + m_strSpace + "as" + m_strSpace + strMin;
		return query;
	}
	
	/**
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 27 AM 2:21:04
	 * @since ModelWeb 1.0
	 */
	public String getEquQuery(String strEqu) {
		String query = "";
		query = m_strSpace + strEqu + m_strEqu + m_strQus;
		return query;
	}
	/**
	 * 
	 * @param strField
	 * @param nPlus
	 * @return
	 * 
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 02. 10 AM 10:15:10
	 * @since ModelWeb 1.0
	 */
	public String getPlusNumQuery(String strPlus) {
		String query = "";
		query = m_strSpace + strPlus + m_strEqu + strPlus + "+" + m_strQus;
		return query;
	}
	/**
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 27 AM 2:21:32
	 * @since ModelWeb 1.0
	 */
	public String getLikeQuery(String strLike) {
		String query = "";
		query = m_strSpace + strLike + m_strLike + m_strSpace + m_strQus;
		return query;
	}
	/**
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 27 AM 10:15:37
	 * @since ModelWeb 1.0
	 */
	public String getAndQuery(String strAnd) {
		String query = "";
		query = m_strSpace + m_strAnd + m_strSpace+ strAnd;
		return query;
	}
	/**
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 27 AM 10:15:35
	 * @since ModelWeb 1.0
	 */
	public String getOrQuery(String strOr) {
		String query = "";
		query = m_strSpace + m_strOr + m_strSpace + strOr;
		return query;
	}
	/**
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 27 PM 1:16:48
	 * @since ModelWeb 1.0
	 */
	public String getCommaQuery(String strComma) {
		String query = "";
		query = m_strComma + m_strSpace + strComma;
		return query;
	}
	/**
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 27 PM 1:17:03
	 * @since ModelWeb 1.0
	 */
	public String getSetQuery(String strSet) {
		String query = "";
		query = m_strSpace + m_strSet + m_strSpace + strSet;
		return query;
	}
	/**
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 27 PM 1:17:15
	 * @since ModelWeb 1.0
	 */
	public String getFieldQuery(String strField) {
		String query = "";
		query = m_strSpace + "(" + strField + ")";
		return query;
	}
	/**
	 * @param strValue
	 * @return
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 27 PM 1:17:58
	 * @since ModelWeb 1.0
	 */
	public String getValueQuery(String strValue) {
		String query = "";
		query = m_strSpace + m_strValue + "(" + strValue + ")";
		return query;
	}
	/**
	 * @param strNull
	 * @return
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 27 PM 1:18:16
	 * @since ModelWeb 1.0
	 */
	public String getNullQuery(String strNull) {
		String query = "";
		query = m_strSpace + strNull + m_strEqu + m_strNULL;
		return query;
	}
	/**
	 * 
	 * @param strOrder
	 * @return
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 26 PM 11:11:41
	 * @since ModelWeb 1.0
	 */
	public String getOrderByBiggerQuery(String strOrder) {
		String query = "";
		query = m_strSpace + strOrder + m_strSpace + m_strOrder_ASC;
		return query;
	}
	/**
	 * 
	 * @param strOrder
	 * @return
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 26 PM 11:11:43
	 * @since ModelWeb 1.0
	 */
	public String getOrderBySmallerQuery(String strOrder) { 
		String query = "";
		query = m_strSpace + strOrder + m_strSpace + m_strOrder_DESC;
		return query;
	}
}
