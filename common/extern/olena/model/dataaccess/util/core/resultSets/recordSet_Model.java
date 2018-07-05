package common.extern.olena.model.dataaccess.util.core.resultSets;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import common.extern.olena.model.dataaccess.mvc.base.IItem_Model;
import common.extern.olena.model.dataaccess.mvc.base.item_Model;
import common.extern.olena.model.dataaccess.mvc.base.tbl_Model;
import common.extern.olena.model.dataaccess.util.core.connections.dbConn_Model;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.*;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.sqlRelationItems.selectSetItem;
import common.extern.olena.model.dataaccess.util.core.resultSets.mssql.msmdbRecordSet;
import common.extern.olena.model.dataaccess.util.core.resultSets.mssql.mssqlRecordSet;
import common.extern.olena.model.dataaccess.util.core.resultSets.mysql.mysqlRecordSet;
import common.extern.olena.model.dataaccess.util.core.resultSets.oracle.oracleRecordSet;
import common.extern.olena.model.systemframework.Applicationlog;
import common.extern.utils.DateTime;

/**
 * 레코드셋트클라스
 */	
public abstract class recordSet_Model {

	/**
	 * 구축자
	 * @param itemInfos
	 */
	public recordSet_Model(IItem_Model itemInfos) {
		InitializeVariables();
		m_ItemModel = itemInfos;
	}
	/************상수들************/
	/************상수들************/
	/************상수들************/
	protected static int statement_NORMAL = 0;
	protected static int statement_PREPARE = 1;
	/**private static int statement_CALLABLE = 2;*/
	public static int MIN_PAGE_SIZE = 1;
	public static int MAX_PAGE_SIZE = 1000;
	public static int DEFAULT_CURRENT_PAGE = 0;
	
	/************변수들************/
	/************변수들************/
	/************변수들************/
	
	/***테이블 마당(Field)항목이름*/
	protected IItem_Model m_ItemModel = null;
	/**명령형태(statement)*/
	protected int m_nStatementStyle = -1;
	/**자료기지접속객체모델*/
	protected dbConn_Model m_objConnectionModel;
	/**자료기지접속객체*/
	protected Connection m_objConnection;
	/**질의식*/
	protected String m_strSql;
	/**명령실행객체*/
	protected Statement m_statement;
	
	/***************레코드관련**********************/
	/**레코드모임객체(자료결과)*/
	protected ResultSet m_resultSet;
	/**레코드개수*/
	protected long m_nRecordCount;
	/**페지크기*/
	protected long m_nPageSize;
	/**페지개수*/
	protected long m_nPageCount;
	/**현제페지수*/
	protected long m_nCurPage;
	
	/**
	 * 변수들을 초기화하기 
	 * 
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 PM 2:58:25
	 * @since ModelWeb 1.0
	 */
	private void InitializeVariables() {
		m_objConnectionModel = null;
		m_statement = null;
		m_resultSet = null;
		m_strSql = "";
		m_nRecordCount = 0;
		m_nPageSize = MIN_PAGE_SIZE;
		m_nPageCount = 0;
		m_nCurPage = DEFAULT_CURRENT_PAGE;
		m_nStatementStyle = statement_NORMAL;
	}
	/*****************getter and setter************************/
	public void setConnectionModel(dbConn_Model connection) { m_objConnectionModel = connection; }
	
	public Connection getConnection() { return m_objConnection; }
	public void setConnection(Connection connection) { m_objConnection = connection; }
	
	public ResultSet getResultSet() { return m_resultSet; }
	public void setResultSet(ResultSet set) { m_resultSet = set; } 
	public IItem_Model getItemModel() { return m_ItemModel; }
	public void setItemModel(IItem_Model itemModel) { m_ItemModel = itemModel; }
	public long getPageSize() { return m_nPageSize; }
	public void setPageSize(long pageSize) { m_nPageSize = pageSize; }
	public long getCurPage() { return m_nCurPage; }
	public void setCurPage(long curPage) { m_nCurPage = curPage ;}
	public long getRecordCount() { return m_nRecordCount; }
	public long PageSize() { return m_nPageSize; }
	public long GetPageCount() { return m_nPageCount; }
	public long getCurrentPage() { return m_nCurPage; }
	
	/****************abstract methods****************/
	private void Debug_Query(String strSql) {
		if(Applicationlog.IS_DEBUG) {
			System.out.println(strSql);
			Applicationlog.LogTruegardener(strSql);
		}
	}
	/**
	 * @throws SQLException
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2012. 01. 04 AM 11:30:06
	 * @since ModelWeb 1.0
	 */
	public abstract Object executeStatementInsert(String strSql) throws SQLException;
	/**
	 * @throws SQLException
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2012. 01. 04 AM 11:39:08
	 * @since ModelWeb 1.0
	 */
	public abstract boolean PrepareStatementInsert(String strSql) throws SQLException; 
	/**
	 * @throws SQLException
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2012. 01. 04 AM 11:39:49
	 * @since ModelWeb 1.0
	 */
	public abstract Object executePrepareStatementInsert() throws SQLException;
	/*****************methods************************/
	/**
	 * @throws SQLException
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2012. 01. 04 AM 11:30:09
	 * @since ModelWeb 1.0
	 */
	public boolean executeStatementSelect(String strSql) throws SQLException {
		Debug_Query(strSql);
		m_strSql = strSql;
		if ((m_objConnectionModel == null) || m_strSql == null || "".equals(m_strSql)) { return false; }
		try {
			m_statement = m_objConnection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			if (m_statement == null)
				return false;

			m_resultSet = m_statement.executeQuery(m_strSql);
			if (m_resultSet == null) { return false; }
			m_resultSet.last();
			m_nRecordCount = m_resultSet.getRow();			
			m_resultSet.beforeFirst();			
			PageSize(m_nPageSize);
		}
		catch (SQLException ex) { throw ex; }
		return true;
	}
	/**
	 * @throws SQLException
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2012. 01. 04 AM 11:30:06
	 * @since ModelWeb 1.0
	 */
	public int executeStatementUpdate(String strSql) throws SQLException 	{
		Debug_Query(strSql);
		int result = -1;
		try {
			m_strSql = strSql;
			if ((m_objConnectionModel == null) || m_strSql == null || "".equals(m_strSql))
				return -1;
			if(m_statement== null) { m_statement = m_objConnection.createStatement(); }
			result = m_statement.executeUpdate(strSql);
		}
		catch(SQLException ex) { throw ex; }
		return result;
	}
	/**
	 * @throws SQLException
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2012. 01. 04 AM 11:31:00
	 * @since ModelWeb 1.0
	 */
	public boolean PrepareStatementSelect(String strSql) throws SQLException {
		Debug_Query(strSql);
		boolean result = false;
		try {
			m_strSql = strSql;
			if ((m_objConnectionModel == null) || m_strSql == null || "".equals(m_strSql)) { return false; }
			m_nStatementStyle = statement_PREPARE;
			m_statement = m_objConnection.prepareStatement(strSql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			if (m_statement == null) {  result = false; }
		}
		catch (SQLException ex) { throw ex; }
		return result;
	}
	/**
	 * @throws SQLException
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2012. 01. 04 AM 11:32:40
	 * @since ModelWeb 1.0
	 */
	public boolean executePrepareStatementSelect() throws SQLException {
		try {
			if ((m_objConnectionModel == null) || m_strSql == null || "".equals(m_strSql)) { return false; }
			m_resultSet = ((PreparedStatement)m_statement).executeQuery();
			if (m_resultSet == null) { return false; }
			m_resultSet.last();
			m_nRecordCount = m_resultSet.getRow();
			m_resultSet.beforeFirst();
			PageSize(m_nPageSize);
		}
		catch (SQLException ex) { throw ex; }
		return true;
	}
	/**
	 * @throws SQLException
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2012. 01. 04 AM 11:40:13
	 * @since ModelWeb 1.0
	 */
	public boolean PrepareStatementUpdate(String strSql) throws SQLException {
		Debug_Query(strSql);
		boolean result = false;
		try {
			m_strSql = strSql;
			if ((m_objConnectionModel == null) || m_strSql == null || "".equals(m_strSql)) { return false; }
			m_nStatementStyle = statement_PREPARE;
			m_statement = m_objConnection.prepareStatement(strSql);
			if (m_statement == null) { result = false; }
		}
		catch (SQLException ex) { throw ex; }
		return result;
	}
	/**
	 * @throws SQLException
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2012. 01. 04 AM 11:40:43
	 * @since ModelWeb 1.0
	 */
	public int executePrepareStatementUpdate() throws SQLException {
		int result = -1;
		try {
			if ((m_objConnectionModel == null) || m_strSql == null || "".equals(m_strSql)) { return -1; }
			result = ((PreparedStatement)m_statement).executeUpdate();
		}
		catch (SQLException ex) { throw ex; }
		return result;
	}
	/**
	 * Move to first
	 * @return
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 28 AM 9:34:22
	 * @throws SQLException 
	 * @since ModelWeb 1.0
	 */
	public boolean moveFirst() throws SQLException {
		if (m_resultSet == null) { return false; }
		try { return m_resultSet.first(); }
		catch (SQLException ex) { throw ex; }
	}
	/**
	 * move to last
	 * @return
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 28 AM 9:34:48
	 * @throws SQLException 
	 * @since ModelWeb 1.0
	 */
	public boolean moveLast() throws SQLException {
		if (m_resultSet == null) { return false; }
		try { return m_resultSet.last(); }
		catch (SQLException ex) { throw ex; }
	}
	/**
	 * next record
	 * @return
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 28 AM 9:31:05
	 * @throws SQLException 
	 * @since ModelWeb 1.0
	 */
	public boolean moveNext() throws SQLException {
		if (m_resultSet == null) { return false; }
		try { return m_resultSet.next(); }
		catch (SQLException ex)  { throw ex; }
	}
	/**
	 * previous record
	 * @return
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 28 AM 9:30:51
	 * @throws SQLException 
	 * @since ModelWeb 1.0
	 */
	public boolean movePrevious() throws SQLException {
		if (m_resultSet == null) { return false; }
		try { return m_resultSet.previous(); }
		catch (SQLException ex) { throw ex; }
	}
	/**
	 * @return
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 28 AM 9:30:49
	 * @throws SQLException 
	 * @since ModelWeb 1.0
	 */
	public boolean isFirst() throws SQLException {
		if (m_resultSet == null || m_nRecordCount == 0) { return true; }
		try { return m_resultSet.isBeforeFirst(); }
		catch (SQLException ex) { throw ex; }
	}
	/**
	 * @return
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 28 AM 9:30:48
	 * @throws SQLException 
	 * @since ModelWeb 1.0
	 */
	public boolean isLast() throws SQLException {
		if (m_resultSet == null || m_nRecordCount == 0) {  return true; }
		try { return m_resultSet.isAfterLast(); }
		catch (SQLException ex) { throw ex; }
	}
	/**
	 * @param nPageSize 페지 (규모)크기
	 * @return
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 29 PM 5:10:24
	 * @since ModelWeb 1.0
	 */
	public long PageSize(long nPageSize) {
		if (nPageSize <= 0) { nPageSize = MIN_PAGE_SIZE; }
		else if (nPageSize > MAX_PAGE_SIZE) { nPageSize = MAX_PAGE_SIZE; }
		m_nPageSize = nPageSize;
		if (m_nRecordCount == 0) {
			m_nPageCount = 0;
			m_nCurPage = 0;
		}
		else {
			long nPageCount = (m_nRecordCount / m_nPageSize);
			m_nPageCount = (nPageCount * m_nPageSize < m_nRecordCount) ? ++ nPageCount : nPageCount;
			if (m_nCurPage >= m_nPageCount) { m_nCurPage = m_nPageCount - 1; }
		}
		return m_nPageSize;
	}
	/**
	 * 1.Check absolute page validation.
	 * 2.goto current page first record(nAbsolutePage*m_nPageSize)
	 * @param nAbsolutePage
	 * @return
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 28 AM 9:20:18
	 * @throws SQLException 
	 * @since ModelWeb 1.0
	 */
	public int AbsolutePage(long nAbsolutePage) throws SQLException {
		if (m_resultSet == null) return 0;
		if (nAbsolutePage < 0) { nAbsolutePage = 0; }
		else if (nAbsolutePage > m_nPageCount - 1) { nAbsolutePage = m_nPageCount - 1; }
		if (nAbsolutePage < 0) { nAbsolutePage = 0; }
		m_nCurPage = nAbsolutePage;
		try {
			m_resultSet.absolute((int)(m_nCurPage * m_nPageSize + 1));
		}
		catch (SQLException ex) { throw ex;}
		return (int)m_nCurPage;
	}
	/**
	 * return zero-based-index.
	 * @return
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 27 PM 6:53:46
	 * @throws SQLException 
	 * @since ModelWeb 1.0
	 */
	public int NextPage() throws SQLException { 
		if ((m_nRecordCount <= 0) || (m_resultSet == null)) { return 0; }
		m_nCurPage++;
		m_nCurPage = AbsolutePage(m_nCurPage);
		return (int)m_nCurPage;
	}
	/**
	 * return zero-based-index.
	 * @return
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 27 PM 6:54:00
	 * @throws SQLException 
	 * @since ModelWeb 1.0
	 */
	public int PreviousPage() throws SQLException {
		if ((m_nRecordCount <= 0) || (m_resultSet == null)) { return 0; }
		m_nCurPage--;
		m_nCurPage = AbsolutePage(m_nCurPage);
		return (int)m_nCurPage;
	}
	/**
	 * return zero-based-index.
	 * @return
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 29 PM 5:09:26
	 * @throws SQLException 
	 * @since ModelWeb 1.0
	 */
	public int FirstPage() throws SQLException  {
		if ((m_nRecordCount <= 0) || (m_resultSet == null)) { return 0; }
		m_nCurPage = AbsolutePage(0);
		return (int)m_nCurPage;
	}
	/**
	 * return zero-based-index.
	 * @return
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 29 PM 5:09:13
	 * @throws SQLException 
	 * @since ModelWeb 1.0
	 */
	public int LastPage() throws SQLException {
		if ((m_nRecordCount <= 0) || (m_resultSet == null)) { return 0; }
		m_nCurPage = AbsolutePage(m_nPageCount - 1);
		return (int)m_nCurPage;
	}
	/**
	 * nPage is zero-based index. 
	 * return zero-based-index.
	 * @param nPage
	 * @return
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 29 PM 5:08:36
	 * @throws SQLException 
	 * @since ModelWeb 1.0
	 */
	public int GoToPage(int nPage) throws SQLException {
		if ((m_nRecordCount <= 0) || (m_resultSet == null)) { return 0; } 
		m_nCurPage = AbsolutePage(nPage);
		return (int)m_nCurPage;
	}
	/**
	 * This function move the record in page
	 * If EOF is true or record is after last record in
	 * page then return false.
	 * @return
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 29 PM 5:08:08
	 * @throws SQLException 
	 * @since ModelWeb 1.0
	 */
	public boolean NextElement() throws SQLException {
		if (isLast()) { return false; }
		if (GetCurRecordPosition() >= (m_nCurPage + 1) * m_nPageSize) { return false; }
		return moveNext();
	}

	/**
	 * This function retrun current record position
	 * as non-zero-based-index.
	 * @return
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 29 PM 5:07:21
	 * @throws SQLException 
	 * @since ModelWeb 1.0
	 */
	public int GetCurRecordPosition() throws SQLException 	{
		int nPosition = 0;
		if (m_resultSet == null) { return nPosition; }
		try { nPosition = m_resultSet.getRow(); }
		catch (SQLException ex) { throw ex; }
		return nPosition;
	}

	/**
	 * This function set record cursor to nPosition
	 * nPosition is none-zero-based index.
	 * @param nPosition
	 * @return
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 29 PM 5:06:51
	 * @throws SQLException 
	 * @since ModelWeb 1.0
	 */
	public int SetRecordPosition(int nPosition) throws SQLException {
		if (m_resultSet == null) { return nPosition; }
		if (nPosition < 1) { nPosition = 1; }
		else if (nPosition > m_nRecordCount) { nPosition = (int)m_nRecordCount; }
		try { m_resultSet.absolute(nPosition); }
		catch (SQLException ex) { throw ex; }
		return nPosition;
	}
/*****************************Get*********************************/
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 25 PM 5:31:28
	 * @since ModelWeb 1.0
	 */
	public IItem_Model productGetInfoItem(selectExpressionRelation selectList)
			throws Throwable {
		IItem_Model result = null;
		try {
			
			boolean bSelectNull = false;
			if(selectList==null) {	
				bSelectNull = true;
			}
			else if(selectList.getRelations()==null) {	
				bSelectNull = true;
			}
			if (bSelectNull) {
				if (moveNext()) {
					result = GetItemFromTableModel();
				}
			}
			else {
				if (moveNext()) {
					result = GetItemFromSelectExpression(selectList);
				}
			}
		} catch (Throwable ex) {
			throw ex;
		} finally {
		}
		return result;
	}
	/**
	 * @param metaFieldList
	 * @param metaTypeList
	 * @param doc
	 * @param strDocName
	 * @param strNodeName
	 * @return
	 * @throws SAXException
	 * @throws DOMException
	 * @throws SQLException
	 */
	public Node getXmlNodeFromMetaData(String[] metaFieldList, int[] metaTypeList, Document doc, String strNodeName) throws SAXException, DOMException, SQLException{
		Node elementNode = doc.createElement(strNodeName);
		Element elementAttList = null;
		int numberOfColumns = metaFieldList.length;
		for (int inx = 0; inx < numberOfColumns; inx++) {
			String fieldName = metaFieldList[inx];
			elementAttList = doc.createElement(fieldName);
			elementAttList.setTextContent(item_Model.getAvaiableXml(metaTypeList[inx], getObject(fieldName)));
			elementNode.appendChild(elementAttList);
		}
		return elementNode;
	}
	/**
	 * @param metaDataList
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	protected HashMap GetHashMapFromMetaData(String[] metaDataList) throws SQLException {
		HashMap hashInfo = new HashMap();
		int numberOfColumns = metaDataList.length;
		for (int inx = 0; inx < numberOfColumns; inx++) {
			String attributeName = metaDataList[inx];
			hashInfo.put(attributeName, getObject(attributeName));
		}
		return hashInfo;
	}
	/**
	 * @param rs
	 *            ResultSet
	 */
	@SuppressWarnings("unchecked")
	public static Vector toVectorHashData(ResultSet rs) throws SQLException {
		Vector result = null;
		try {
			result = new Vector();
			ResultSetMetaData rsmd = rs.getMetaData();
			final int numberOfColumns = rsmd.getColumnCount();
			String[] attributeNameList = new String[numberOfColumns];
			for (int nIndex = 0; nIndex < numberOfColumns; nIndex++) {
				final int index = nIndex+1;
				attributeNameList[nIndex] = rsmd.getColumnLabel(index);
			}
			
			while (rs.next()) {
				HashMap hashInfo = new HashMap();
				for (int nIndex = 0; nIndex < numberOfColumns; nIndex++) {
					final String attributeName = attributeNameList[nIndex];
					hashInfo.put(attributeName, rs.getObject(attributeName));
				}
				result.add(hashInfo);
			}
		} catch (SQLException se) {
			throw se;
		}
		return result;
	}
	/**
	 * @return
	 * @throws Exception
	 */
	protected IItem_Model GetItemFromTableModel() throws Exception{
		IItem_Model resultItem = m_ItemModel.getClass().newInstance();
		for(int i = 0; i < m_ItemModel.getTableModel().getLength(); i++) {
			resultItem.setAvaiable(i, getObject(i));
		}
		return resultItem;
	}
	/**
	 * @param selectList
	 * @return
	 * @throws Exception
	 */
	protected IItem_Model GetItemFromSelectExpression(selectExpressionRelation selectList) throws Exception{
		IItem_Model resultItem = m_ItemModel.getClass().newInstance();
		
		Vector<selectSetItem> lstSetItem = selectList.getRelations();
		int nSize = lstSetItem.size();
		selectSetItem setItem = null; 
		for (int i = 0; i < nSize; i++) {
			setItem = lstSetItem.get(i);
			resultItem.setAvaiable(setItem.getFieldIndex(), getObject(setItem.getFieldIndex()));
		}
		return resultItem;
	}
	/**
	 * 
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 15 PM 5:33:25
	 * @since ModelWeb 1.0
	 */
	public abstract Document productGetInfoListXml(pageExpressionRelation pageInfo, String strXmlRoot, String strXmlRow)throws Throwable;
	/**
	 * 
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 15 PM 5:33:25
	 * @since ModelWeb 1.0
	 */
	public abstract Vector<HashMap> productGetInfoListHash(pageExpressionRelation pageInfo)throws Throwable;
	/**
	 * 
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 15 PM 5:33:25
	 * @since ModelWeb 1.0
	 */
	public abstract Vector<IItem_Model> productGetInfoListItem(selectExpressionRelation selectList, pageExpressionRelation pageInfo)throws Throwable;
	/**
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 15 PM 5:33:24
	 * @since ModelWeb 1.0
	 */
	public abstract Vector<Object> productGetFirstInfoList(pageExpressionRelation pageInfo)throws Throwable;
	/**
	 * @param strCount
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 18 AM 10:47:36
	 * @since ModelWeb 1.0
	 */
	public Object productGetFirstObjectInfo(String strCount)throws Throwable {
		Object result = null;
		try {
			if(moveNext()) { result = getObject(strCount); }
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
	/**
	 * @param strCount
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 18 AM 10:47:36
	 * @since ModelWeb 1.0
	 */
	public Object productGetFirstObjectInfo(int nSeek) throws Throwable {
		Object result = null;
		try {
			if (moveNext()) {
				result = getObjectbySeek(nSeek);
			}
		} catch (Throwable ex) {
			throw ex;
		} finally {
		}
		return result;
	}
	
/** ***************************Set******************************** */
	/**
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 15 PM 5:33:30
	 * @since ModelWeb 1.0
	 */
	public int productSetWhereInfoItem(int nSeek, whereExpressionRelation whereList)throws Throwable {
		int result = -1;
		try {	
			if(whereList!=null) {
				result = nSeek;
				whereList.moveFirst();
				Object objSeek = null;
				do {
					objSeek = whereList.getPointObject();
					if(objSeek!=null) {
						result++;
						setObject(result, whereList.getPointInteger(), objSeek);
					}
				}
				while(whereList.moveNext()!=-1);
			}
		}
		catch (Throwable ex) {
			throw ex;
		} finally {
		}
		return result;
	}
	/**
	 * @param nSeek
	 * @param IItem_Model
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, PM 4:52:28
	 * @since ModelWeb 1.0
	 */
	public int productSetWhereInfoItembyItem(int nSeek, IItem_Model info)
			throws Throwable {
		int result = -1;
		try {
			if (info != null) {
				result = nSeek;
				int i;
				for (i = 0; i < info.getTableModel().getLength(); i++) {
					if (info.isAvaiable(i)) {
						result++;
						setObject(result, i, info.getAvaiable(i));
					}
				}
			}
		} catch (Throwable ex) {
			throw ex;
		} finally {
		}
		return result;
	}
	/**
	 * @param nSeek
	 * @param info
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 05 PM 2:58:51
	 * @since ModelWeb
	 */
	public int productSetWhereInfoLikeItembyItem(int nSeek, IItem_Model info)
			throws Throwable {
		int result = -1;
		try {
			if (info != null) {
				result = nSeek;
				for (int i = 0; i < info.getTableModel().getLength(); i++) {
					if (info.isAvaiable(i)) {
						result++;
						if (info.getTableModel().getType(i, m_ItemModel.getKind()) == Types.VARCHAR
								|| info.getTableModel().getType(i,m_ItemModel.getKind()) == Types.LONGVARCHAR) {
							setObject(result, i, "%" + (String) info.getAvaiable(i) + "%");
						} else {
							setObject(result, i, info.getAvaiable(i));
						}
					}
				}
			}
		} catch (Throwable ex) {
			throw ex;
		} finally {
		}
		return result;
	}
	/**
	 * @param infoRelationItem 
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 15 PM 5:33:32
	 * @since ModelWeb 1.0
	 */
	public void productSetInsertItem(
			insertItemExpressionRelation infoRelationItem) throws Throwable {
		try {
			if (infoRelationItem != null) {
				int i; int nSeek = 0;
				for (i = 0; i < m_ItemModel.getTableModel().getLength(); i++) {
					if (infoRelationItem.getRelations().isAvaiable(i)) {
						nSeek++;
						setObject(nSeek, i, infoRelationItem.getRelations().getAvaiable(i));
					}
				}
			}
		} catch (Throwable ex) {
			throw ex;
		} finally {
		}
	}
	/**
	 * @param infoItem 
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 15 PM 5:33:34
	 * @since ModelWeb 1.0
	 */
	public void productSetUpdateItem(updateItemExpressionRelation infoItem)
			throws Throwable {
		try {
			if (infoItem != null) {
				int i; int nSeek = 0;
				for (i = 1; i < m_ItemModel.getTableModel().getLength(); i++) {
					if (infoItem.getRelations().isAvaiable(i)) {
						nSeek++;
						setObject(nSeek, i, infoItem.getRelations().getAvaiable(i));
					}
				}
				if (infoItem.getRelations().isAvaiable(tbl_Model.field_id)) {
					nSeek++;
					setObject(nSeek, infoItem.getRelations().getAvaiable(tbl_Model.field_id));
				}
			}
		} catch (Throwable ex) {
			throw ex;
		} finally {
		}
	}
	/**
	 * @param nSeek 
	 * @param setUpdateLis
	 * @return Last position
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 15 PM 5:33:38
	 * @since ModelWeb 1.0
	 */
	public int productSetUpdatebyInfoItem(int nSeek, updateExpressionRelation setUpdateList) throws Throwable {
		int result = -1;
		try {
			if (setUpdateList != null) {
				result = nSeek;
				setUpdateList.moveFirst();
				int nFieldIndex = -1;
				Object objSeek = null;
				do {
					objSeek = setUpdateList.getSeekObject();
					if(objSeek!=null) {
						nFieldIndex = setUpdateList.getSeekSeekInteger();
						if(nFieldIndex!=-1) {
							result++;
							setObject(result, nFieldIndex, objSeek);
						}
						else {
							if(setUpdateList.getSeekExpression()!=null) {
								result++;
								setObject(result, objSeek);
							}
						}
					}
				} while (setUpdateList.moveNext() != -1);
			}
		} catch (Throwable ex) {
			throw ex;
		} finally {
		}
		return result;
	}
	/**
	 * 
	 * @param nSeek
	 * @param executeList
	 * @return
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 15 PM 5:33:40
	 * @since ModelWeb 1.0
	 */
	public void productSetExecuteQueryItem(executeExpressionRelation executeList) throws Throwable {
		try {
			int nSeek = -1;
			if (executeList != null) {
				executeList.moveFirst();
				Object objSeek = null;
				nSeek = 0;
				do {
					objSeek = executeList.getSeekObject();
					if (objSeek != null) {
						nSeek++;
						setObject(nSeek, objSeek);
					}
				} while (executeList.moveNext() != -1);
			}
		} catch (Throwable ex) {
			throw ex;
		} finally {
		}
	}
/** ***************************Getter and Setter******************************** */
	/**
	 * record int
	 * @param strFieldName
	 * @return
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 PM 12:00:14
	 * @since ModelWeb 1.0
	 */
	public int getInt(String strFieldName) throws SQLException {
		int nRtn = 0;
		if ((m_resultSet == null) || (strFieldName == null) || "".equals(strFieldName))
			return nRtn;
		try {
			nRtn = m_resultSet.getInt(strFieldName);
		} catch (SQLException ex) {
			throw ex;
		}
		return nRtn;
	}
	/**
	 * Getrecord int variable
	 * @param nFieldIndex 
	 * @return
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 PM 12:00:12
	 * @since ModelWeb 1.0
	 */
	public int getInt(int nFieldIndex) throws SQLException {
		int nRtn = 0;
		if ((m_resultSet == null) || (nFieldIndex == -1)) {
			return nRtn;
		}
		try {
			nRtn = m_resultSet.getInt(m_ItemModel.getFieldLabel(nFieldIndex));
		} catch (SQLException ex) {
			throw ex;
		}
		return nRtn;
	}
	/**
	 * @return
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 PM 12:00:10
	 * @since ModelWeb 1.0
	 */
	public int setInt(int nParameterIndex, short obj) throws SQLException {
		int result = -1;
		try {
			if (m_statement != null && m_nStatementStyle == statement_PREPARE) {
				((PreparedStatement) m_statement).setInt(nParameterIndex, obj);
				result = 1;
			}
		} catch (SQLException ex) {
			throw ex;
		}
		return result;
	}
	/**
	 * @param strFieldName
	 * @return
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:58:36
	 * @since ModelWeb 1.0
	 */
	public long getLong(String strFieldName) throws SQLException {
		long nRtn = 0;
		if ((m_resultSet == null) || (strFieldName == null) || "".equals(strFieldName)) {
			return nRtn;
		}
		try {
			nRtn = m_resultSet.getLong(strFieldName);
		} catch (SQLException ex) {
			throw ex;
		}
		return nRtn;
	}
	/**
	 * @param nFieldIndex
	 * @return
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:58:23
	 * @since ModelWeb 1.0
	 */
	public long getLong(int nFieldIndex) throws SQLException {
		long nRtn = 0;
		if ((m_resultSet == null) || (nFieldIndex == -1)) {
			return nRtn;
		}
		try {
			nRtn = m_resultSet.getLong(m_ItemModel.getFieldLabel(nFieldIndex));
		} catch (SQLException ex) {
			throw ex;
		}
		return nRtn;
	}
	/**
	 * prepareStatement객체에 long형변수를 설정하기
	 * @param nParameterIndex 파라메터인덱스
	 * @param obj
	 * @return
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:57:49
	 * @since ModelWeb 1.0
	 */
	public int setLong(int nParameterIndex, short obj) throws SQLException {
		int result = -1;
		try {
			if (m_statement != null && m_nStatementStyle == statement_PREPARE) {
				((PreparedStatement) m_statement).setLong(nParameterIndex, obj);
				result = 1;
			}
		} catch (SQLException ex) {
			throw ex;
		}
		return result;
	}
	/**
	 * 레코드의 String 형 변수를 얻기
	 * @param strFieldName 마당이름
	 * @return
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:56:55
	 * @since ModelWeb 1.0
	 */
	public String getString(String strFieldName) throws SQLException {
		String strRtn = "";
		if ((m_resultSet == null) || (strFieldName == null) || "".equals(strFieldName)) {
			return strRtn;
		}
		try {
			strRtn = m_resultSet.getString(strFieldName);
		} catch (SQLException ex) {
			throw ex;
		}
		return (strRtn == null) ? "" : strRtn;
	}
	/**
	 * Get record's String
	 * @param nFieldIndex 
	 * @return
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:56:30
	 * @since ModelWeb 1.0
	 */
	public String getString(int nFieldIndex) throws SQLException {
		String strRtn = "";
		if ((m_resultSet == null) || (nFieldIndex == -1)) {
			return strRtn;
		}
		try {
			strRtn = m_resultSet.getString(m_ItemModel
					.getFieldLabel(nFieldIndex));
		} catch (SQLException ex) {
			throw ex;
		}
		return strRtn;
	}
	/**
	 * @param nParameterIndex
	 * @param strParameter
	 * @return
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:56:02
	 * @since ModelWeb 1.0
	 */
	public int setString(int nParameterIndex, String strParameter)
			throws SQLException {
		int result = -1;
		try {
			if (m_statement != null && m_nStatementStyle == statement_PREPARE) {
				((PreparedStatement) m_statement).setString(nParameterIndex,
						strParameter);
			}
		} catch (SQLException ex) {
			throw ex;
		}
		return result;
	}
	/**
	 * @param strFieldName
	 * @return
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:53:18
	 * @since ModelWeb 1.0
	 */
	public boolean getBoolean(String strFieldName) throws SQLException {
		boolean bRtn = false;
		if ((m_resultSet == null) || (strFieldName == null) || "".equals(strFieldName))
			return bRtn;
		try {
			bRtn = m_resultSet.getBoolean(strFieldName);
		} catch (SQLException ex) {
			throw ex;
		}
		return bRtn;
	}
	/**
	 * @param nFieldIndex
	 * @return
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:53:16
	 * @since ModelWeb 1.0
	 */
	public boolean getBoolean(int nFieldIndex) throws SQLException {
		boolean bRtn = false;
		if ((m_resultSet == null) || (nFieldIndex == -1)) {
			return bRtn;
		}
		try {
			bRtn = m_resultSet.getBoolean(m_ItemModel.getFieldLabel(nFieldIndex));
		} catch (SQLException ex) {
			throw ex;
		}
		return bRtn;
	}
	/**
	 * @param nParameterIndex
	 * @param obj
	 * @return succed:1, failed:-1
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:52:03
	 * @since ModelWeb 1.0
	 */
	public int setBooolean(int nParameterIndex, boolean obj)
			throws SQLException {
		int result = -1;
		try {
			if (m_statement != null && m_nStatementStyle == statement_PREPARE) {
				((PreparedStatement) m_statement).setBoolean(nParameterIndex, obj);
				result = 1;
			}
		} catch (SQLException ex) {
			throw ex;
		}
		return result;
	}
	/**
	 * @param strFieldName
	 * @return
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:47:56
	 * @since ModelWeb 1.0
	 */
	public byte[] getBytes(String strFieldName) throws SQLException {
		byte[] byteArryRtn = new byte[0];
		if ((m_resultSet == null) || (strFieldName == null) || "".equals(strFieldName)) {
			return byteArryRtn;
		}
		try {
			byteArryRtn = m_resultSet.getBytes(strFieldName);
		} catch (SQLException ex) {
			throw ex;
		}
		return byteArryRtn;
	}
	/**
	 * 
	 * @param nFieldIndex
	 * @return
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:47:15
	 * @since ModelWeb 1.0
	 */
	public byte[] getBytes(int nFieldIndex) throws SQLException {
		byte[] byteArrayRtn = new byte[0];
		if ((m_resultSet == null) || (nFieldIndex == -1)) {
			return byteArrayRtn;
		}
		try {
			byteArrayRtn = m_resultSet.getBytes(m_ItemModel
					.getFieldLabel(nFieldIndex));
		} catch (SQLException ex) {
			throw ex;
		}
		return byteArrayRtn;
	}
	/**
	 * @param nParameterIndex 
	 * @param obj
	 * @return
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:47:19
	 * @since ModelWeb 1.0
	 */
	public int setBytes(int nParameterIndex, byte[] obj) throws SQLException {
		int result = -1;
		try {
			if (m_statement != null && m_nStatementStyle == statement_PREPARE) {
				((PreparedStatement) m_statement).setBytes(nParameterIndex, obj);
				result = 1;
			}
		} catch (SQLException ex) {
			throw ex;
		}
		return result;
	}
	/**
	 * @param strFieldName
	 * @return
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:47:56
	 * @since ModelWeb 1.0
	 */
	public byte getByte(String strFieldName) throws SQLException {
		byte byteRtn = -1;
		if ((m_resultSet == null) || (strFieldName == null) || "".equals(strFieldName)) {
			return byteRtn;
		}
		try {
			byteRtn = m_resultSet.getByte(strFieldName);
		} catch (SQLException ex) {
			throw ex;
		}
		return byteRtn;
	}
	/**
	 * 
	 * @param nFieldIndex
	 * @return
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:47:15
	 * @since ModelWeb 1.0
	 */
	public byte getByte(int nFieldIndex) throws SQLException {
		byte byteRtn = -1;
		if ((m_resultSet == null) || (nFieldIndex == -1)) {
			return byteRtn;
		}
		try {
			byteRtn = m_resultSet.getByte(m_ItemModel.getFieldLabel(nFieldIndex));
		} catch (SQLException ex) {
			throw ex;
		}
		return byteRtn;
	}
	/**
	 * @param nParameterIndex 
	 * @param obj
	 * @return
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:47:19
	 * @since ModelWeb 1.0
	 */
	public int setByte(int nParameterIndex, byte obj) throws SQLException {
		int result = -1;
		try {
			if (m_statement != null && m_nStatementStyle == statement_PREPARE) {
				((PreparedStatement) m_statement).setByte(nParameterIndex, obj);
				result = 1;
			}
		} catch (SQLException ex) {
			throw ex;
		}
		return result;
	}
	/**
	 * @param strFieldName 
	 * @return
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:45:28
	 * @since ModelWeb 1.0
	 */
	public Date getDate(String strFieldName) throws SQLException {
		Date dtRtn = null;
		if ((m_resultSet == null) || (strFieldName == null) || "".equals(strFieldName))
			return dtRtn;
		try {
			dtRtn = m_resultSet.getDate(strFieldName);
		} catch (SQLException ex) {
			throw ex;
		}
		return dtRtn;
	}
	/**
	 * 
	 * @param nFieldIndex
	 * @return
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:45:06
	 * @since ModelWeb 1.0
	 */
	public short getDate(int nFieldIndex) throws SQLException {
		short nRtn = 0;
		if ((m_resultSet == null) || (nFieldIndex == -1)) {
			return nRtn;
		}
		try {
			nRtn = m_resultSet.getShort(m_ItemModel.getFieldLabel(nFieldIndex));
		} catch (SQLException ex) {
			throw ex;
		}
		return nRtn;
	}
	/**
	 * @param nParameterIndex
	 * @param obj
	 * @return succeed:1, failed:-1
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:42:34
	 * @since ModelWeb 1.0
	 */
	public int setDate(int nParameterIndex, Date obj) throws SQLException {
		int result = -1;
		try {
			if (m_statement != null && m_nStatementStyle == statement_PREPARE) {
				((PreparedStatement) m_statement).setDate(nParameterIndex, obj);
				result = 1;
			}
		} catch (SQLException ex) {
			throw ex;
		}
		return result;
	}
	/**
	 * 
	 * @param strFieldName
	 * @return
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:35:34
	 * @since ModelWeb 1.0
	 */
	public double getDouble(String strFieldName) throws SQLException {
		double dbRtn = 0;
		if ((m_resultSet == null) || (strFieldName == null) || "".equals(strFieldName))
			return dbRtn;
		try {
			dbRtn = m_resultSet.getDouble(strFieldName);
		} catch (SQLException ex) {
			throw ex;
		}
		return dbRtn;
	}
	/**
	 * @param nFieldIndex 
	 * @return
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:35:32
	 * @since ModelWeb 1.0
	 */
	public double getDouble(int nFieldIndex) throws SQLException {
		double dbRtn = 0;
		if ((m_resultSet == null) || (nFieldIndex == -1)) {
			return dbRtn;
		}
		try {
			dbRtn = m_resultSet.getDouble(m_ItemModel
					.getFieldLabel(nFieldIndex));
		} catch (SQLException ex) {
			throw ex;
		}
		return dbRtn;
	}
	/**
	 * @param nParameterIndex
	 * @param obj
	 * @return succeed:1, failed:-1
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:34:59
	 * @since ModelWeb 1.0
	 */
	public int setDouble(int nParameterIndex, short obj) throws SQLException {
		int result = -1;
		try {
			if (m_statement != null && m_nStatementStyle == statement_PREPARE) {
				((PreparedStatement) m_statement).setDouble(nParameterIndex,
						obj);
				result = 1;
			}
		} catch (SQLException ex) {
			throw ex;
		}
		return result;
	}
	/**
	 * @param strFieldName 
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:26:04
	 * @since ModelWeb 1.0
	 */
	public float getFloat(String strFieldName) throws SQLException {
		float flRtn = 0;
		if ((m_resultSet == null) || (strFieldName == null) || "".equals(strFieldName)) {
			return flRtn;
		}
		try {
			flRtn = m_resultSet.getFloat(strFieldName);
		} catch (SQLException ex) {
			throw ex;
		}
		return flRtn;
	}
	/**
	 * @param nFieldIndex
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:26:02
	 * @since ModelWeb 1.0
	 */
	public float getFloat(int nFieldIndex) throws SQLException {
		float flRtn = 0;
		if ((m_resultSet == null) || (nFieldIndex == -1)) {
			return flRtn;
		}
		try {
			flRtn = m_resultSet
					.getFloat(m_ItemModel.getFieldLabel(nFieldIndex));
		} catch (SQLException ex) {
			throw ex;
		}
		return flRtn;
	}
	/**
	 * @param nParameterIndex
	 * @param obj
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:26:00
	 * @since ModelWeb 1.0
	 */
	public int setFloat(int nParameterIndex, float obj) throws SQLException {
		int result = -1;
		try {
			if (m_statement != null && m_nStatementStyle == statement_PREPARE) {
				((PreparedStatement) m_statement)
						.setFloat(nParameterIndex, obj);
				result = 1;
			}
		} catch (SQLException ex) {
			throw ex;
		}
		return result;
	}
	/**
	 * @param strFieldName
	 * @return
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:23:26
	 * @since ModelWeb 1.0
	 */
	public Time getTime(String strFieldName) throws SQLException {
		Time tmRtn = null;
		if ((m_resultSet == null) || (strFieldName == null)) {
			return tmRtn;
		}
		try {
			tmRtn = m_resultSet.getTime(strFieldName);
		} catch (SQLException ex) {
			throw ex;
		}
		return tmRtn;
	}
	/**
	 * @param nFieldIndex
	 * @return
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 08. 23 PM 2:53:11
	 * @since ModelWeb 1.0
	 */
	public Time getTime(int nFieldIndex) throws SQLException {
		Time tmRtn = null;
		if ((m_resultSet == null) || (nFieldIndex == -1)) {
			return tmRtn;
		}
		try {
			tmRtn = m_resultSet.getTime(m_ItemModel.getFieldLabel(nFieldIndex));
		} catch (SQLException ex) {
			throw ex;
		}
		return tmRtn;
	}
	/**
	 * @param strFieldName
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 08. 23 PM 2:57:08
	 * @since ModelWeb 1.0
	 */
	public Timestamp getTimeStamp(String strFieldName) throws SQLException {
		Timestamp tmRtn = null;
		if ((m_resultSet == null) || (strFieldName == null)) {
			return tmRtn;
		}
		try {
			tmRtn = m_resultSet.getTimestamp(strFieldName);
		} catch (SQLException ex) {
			throw ex;
		}
		return tmRtn;
	}
	/**
	 * @param nFieldIndex
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:23:23
	 * @since ModelWeb 1.0
	 */
	public Timestamp getTimeStamp(int nFieldIndex) throws SQLException {
		Timestamp tmRtn = null;
		if ((m_resultSet == null) || (nFieldIndex == -1)) {
			return tmRtn;
		}
		try {
			tmRtn = m_resultSet.getTimestamp(m_ItemModel
					.getFieldLabel(nFieldIndex));
		} catch (SQLException ex) {
			throw ex;
		}
		return tmRtn;
	}
	/**
	 * @param nParameterIndex
	 * @param obj
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:23:21
	 * @since ModelWeb 1.0
	 */
	public int setTimeStamp(int nParameterIndex, Timestamp obj)
			throws SQLException {
		int result = -1;
		try {
			if (m_statement != null && m_nStatementStyle == statement_PREPARE) {
				((PreparedStatement) m_statement).setTimestamp(nParameterIndex,
						obj);
				result = 1;
			}
		} catch (SQLException ex) {
			throw ex;
		}
		return result;
	}
	/**
	 * @param strFieldName
	 * @return
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 05 PM 2:54:03
	 * @since ModelWeb
	 */
	public Calendar GetCalendar(String strFieldName) throws SQLException {
		Calendar RtnCal = null;
		try {
			if ((m_resultSet == null) || (strFieldName == null)) {
				return RtnCal;
			}
			RtnCal = Calendar.getInstance();

			Date dtDate = getDate(strFieldName);
			Time tmTime = getTime(strFieldName);
			if (dtDate == null)
				return null;
			RtnCal.setTime(dtDate);

			if (tmTime != null) {
				Calendar calTemp = Calendar.getInstance();
				calTemp.setTime(tmTime);

				RtnCal.set(Calendar.HOUR_OF_DAY, calTemp
						.get(Calendar.HOUR_OF_DAY));
				RtnCal.set(Calendar.MINUTE, calTemp.get(Calendar.MINUTE));
				RtnCal.set(Calendar.SECOND, calTemp.get(Calendar.SECOND));
			}
		} catch (SQLException ex) {
			throw ex;
		}
		return RtnCal;
	}
	/**
	 * @param strFildName
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 08. 23 PM 2:59:29
	 * @since ModelWeb 1.0
	 */
	public Calendar GetCalendar(int nFieldIndex) throws SQLException {
		Calendar RtnCal = null;
		try {
			if ((m_resultSet == null) || (nFieldIndex == -1)) {
				return RtnCal;
			}
			RtnCal = Calendar.getInstance();
			Date dtDate = getDate(m_ItemModel.getFieldLabel(nFieldIndex));
			Time tmTime = getTime(m_ItemModel.getFieldLabel(nFieldIndex));
			if (dtDate == null)
				return null;
			RtnCal.setTime(dtDate);

			if (tmTime != null) {
				Calendar calTemp = Calendar.getInstance();
				calTemp.setTime(tmTime);
				RtnCal.set(Calendar.HOUR_OF_DAY, calTemp.get(Calendar.HOUR_OF_DAY));
				RtnCal.set(Calendar.MINUTE, calTemp.get(Calendar.MINUTE));
				RtnCal.set(Calendar.SECOND, calTemp.get(Calendar.SECOND));
			}
		} catch (SQLException ex) {
			throw ex;
		}
		return RtnCal;
	}
	/**
	 * @param strFieldName
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:15:53
	 * @since ModelWeb 1.0
	 */
	public short getShort(String strFieldName) throws SQLException {
		short nRtn = 0;
		if ((m_resultSet == null) || (strFieldName == null) || "".equals(strFieldName)) {
			return nRtn;
		}
		try {
			nRtn = m_resultSet.getShort(strFieldName);
		} catch (SQLException ex) {
			throw ex;
		}
		return nRtn;
	}
	/**
	 * 
	 * @param nFieldIndex
	 * @return
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:15:58
	 * @since ModelWeb 1.0
	 */
	public short getShort(int nFieldIndex) throws SQLException {
		short nRtn = 0;
		if ((m_resultSet == null) || (nFieldIndex == -1)) {
			return nRtn;
		}
		try {
			nRtn = m_resultSet.getShort(m_ItemModel.getFieldLabel(nFieldIndex));
		} catch (SQLException ex) {
			throw ex;
		}
		return nRtn;
	}
	/**
	 * 
	 * @param nParameterIndex
	 * @param obj
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:36:38
	 * @since ModelWeb 1.0
	 */
	public int setShort(int nParameterIndex, short obj) throws SQLException {
		int result = -1;
		try {
			if (m_statement != null && m_nStatementStyle == statement_PREPARE) {
				((PreparedStatement) m_statement)
						.setShort(nParameterIndex, obj);
				result = 1;
			}
		} catch (SQLException ex) {
			throw ex;
		}
		return result;
	}
	/**
	 * @param strFieldName
	 * @return
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 28 AM 9:29:03
	 * @throws SQLException 
	 * @since ModelWeb 1.0
	 */
	public Object getObject(String strFieldName) throws SQLException {
		Object oResult = null;
		try {
			if (!((m_resultSet == null) || (strFieldName == null) || "".equals(strFieldName))) {
				oResult = m_resultSet.getObject(strFieldName);
			}
		} catch (SQLException ex) {
		}
		return oResult;
	}
	/**
	 * 
	 * @param nFieldIndex
	 * @return
	 * @throws SQLException
	 * 
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 05 AM 11:16:04
	 * @since ModelWeb 1.0
	 */
	public Object getObject(int nFieldIndex) throws SQLException {
		Object oResult = null;
		try {
			if (!((m_resultSet == null) || (nFieldIndex == -1))) {
				int nType = m_ItemModel.getFieldType(nFieldIndex);
				switch(nType) {
					case Types.BIGINT: {
						oResult = m_resultSet.getLong(m_ItemModel.getFieldLabel(nFieldIndex));
						break;
					}
					case Types.INTEGER: {
						oResult = m_resultSet.getInt(m_ItemModel.getFieldLabel(nFieldIndex));
						break;
					}
					case Types.SMALLINT: {
						oResult = m_resultSet.getShort(m_ItemModel.getFieldLabel(nFieldIndex));
						break;
					}
					case Types.TINYINT: {
						oResult = m_resultSet.getByte(m_ItemModel.getFieldLabel(nFieldIndex));
						break;
					}
					case Types.FLOAT: {
						oResult = m_resultSet.getFloat(m_ItemModel.getFieldLabel(nFieldIndex));
						break;
					}
					case Types.DOUBLE: {
						oResult = m_resultSet.getDouble(m_ItemModel.getFieldLabel(nFieldIndex));
						break;
					}
					case Types.BIT: {
						oResult = m_resultSet.getBoolean(m_ItemModel.getFieldLabel(nFieldIndex));
						break;
					}
					case Types.VARCHAR: {
						oResult = m_resultSet.getString(m_ItemModel.getFieldLabel(nFieldIndex));
						break;
					}
					case Types.LONGVARCHAR: {
						oResult = m_resultSet.getString(m_ItemModel.getFieldLabel(nFieldIndex));
						break;
					}
					case Types.DATE: {
						oResult = m_resultSet.getDate(m_ItemModel.getFieldLabel(nFieldIndex));
						break;
					}
					case Types.TIME: {
						oResult = m_resultSet.getTime(m_ItemModel.getFieldLabel(nFieldIndex));
						break;
					}
					case Types.TIMESTAMP: {
						oResult = m_resultSet.getTimestamp(m_ItemModel.getFieldLabel(nFieldIndex));
						break;
					}
					default: {
						oResult = m_resultSet.getObject(m_ItemModel.getFieldLabel(nFieldIndex));
						break;
					}
				}
			}
		}
		catch (SQLException ex) {
			try {
				oResult = m_resultSet.getObject(m_ItemModel.getFieldLabel(nFieldIndex));
			}
			catch (SQLException e) { }
		}
		return oResult;
	}
	/**
	 * @param nIndex
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 05 PM 2:52:08
	 * @since ModelWeb
	 */
	public Object getObjectbySeek(int nIndex) throws SQLException {
		Object oResult = null;
		try {
			if (!((m_resultSet == null) || (nIndex <= 0))) {
				oResult = m_resultSet.getObject(nIndex);
			}
		} catch (SQLException ex) {
		}
		return oResult;
	}
	/**
	 * 
	 * @param strFieldName
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 28 AM 9:29:03
	 * @throws SQLException
	 * @since ModelWeb 1.0
	 */
	public int setObject(int nParameterIndex, Object obj) throws SQLException {
		int result = -1;
		try {
			if (m_statement != null && m_nStatementStyle == statement_PREPARE) {
				((PreparedStatement) m_statement).setObject(nParameterIndex,
						obj);
				result = 1;
			}
		} catch (SQLException ex) {
			throw ex;
		}
		return result;
	}
	/**
	 * @param strFieldName
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 28 AM 9:29:03
	 * @throws SQLException 
	 * @since ModelWeb 1.0
	 */
	public int setObject(int nParameterIndex, int nFieldIndex, Object obj) throws SQLException {
		int result = -1;
		boolean bValid = true;
		try {
			if (m_statement!=null && m_nStatementStyle==statement_PREPARE) {
				int nType = m_ItemModel.getFieldType(nFieldIndex);
				switch(nType) {
					case Types.BIGINT: {
						long Obj = -1;
						if(obj instanceof Long) { Obj = (Long)obj; }
						else if(obj instanceof Integer) { Obj = (Integer)obj; }
						else if(obj instanceof Short) { Obj = ((Short)obj).longValue(); }
						else if(obj instanceof Byte) { Obj = ((Byte)obj).longValue(); }
						else if(obj instanceof Float) { Obj = ((Float)obj).longValue(); }
						else if(obj instanceof Double) { Obj = ((Double)obj).longValue(); }
						else if(obj instanceof String) { Obj = Long.parseLong((String)obj); }
						else { bValid = false; }
						if(bValid) {
							((PreparedStatement)m_statement).setLong(nParameterIndex, Obj);
						}
						else {
							((PreparedStatement)m_statement).setObject(nParameterIndex, obj);
						}
						result = 1;
						break;
					}
					case Types.INTEGER: {
						int Obj = -1;
						if(obj instanceof Integer) { Obj = (Integer)obj; }
						else if(obj instanceof Long) { Obj = ((Long)obj).intValue(); }
						else if(obj instanceof Short) { Obj = ((Short)obj).intValue(); }
						else if(obj instanceof Byte) { Obj = ((Byte)obj).intValue(); }
						else if(obj instanceof Float) { Obj = ((Float)obj).intValue(); }
						else if(obj instanceof Double) { Obj = ((Double)obj).intValue(); }
						else if(obj instanceof String) { Obj = Integer.parseInt((String)obj); }
						else { bValid = false; }
						if(bValid) {
							((PreparedStatement)m_statement).setLong(nParameterIndex, Obj);
						}
						else {
							((PreparedStatement)m_statement).setObject(nParameterIndex, obj);
						}
						result = 1;
						break;
					}
					case Types.SMALLINT: {
						short Obj = -1;
						if(obj instanceof Short) { Obj = (Short)obj; }
						else if(obj instanceof Long) { Obj = ((Long)obj).shortValue(); }
						else if(obj instanceof Integer) { Obj = ((Integer)obj).shortValue(); }
						else if(obj instanceof Byte) { Obj = ((Byte)obj).shortValue(); }
						else if(obj instanceof Float) { Obj = ((Float)obj).shortValue(); }
						else if(obj instanceof Double) { Obj = ((Double)obj).shortValue(); }
						else if(obj instanceof String) { Obj = Short.parseShort((String)obj); }
						else { bValid = false; }
						if(bValid) {
							((PreparedStatement)m_statement).setShort(nParameterIndex, Obj);
						}
						else {
							((PreparedStatement)m_statement).setObject(nParameterIndex, obj);
						}
						result = 1;
						break;
					}
					case Types.TINYINT: {
						byte Obj = -1;
						if(obj instanceof Byte) { Obj = (Byte)obj; }
						else if(obj instanceof Long) { Obj = ((Long)obj).byteValue(); }
						else if(obj instanceof Integer) { Obj = ((Integer)obj).byteValue(); }
						else if(obj instanceof Short) { Obj = ((Short)obj).byteValue(); }
						else if(obj instanceof Float) { Obj = ((Float)obj).byteValue(); }
						else if(obj instanceof Double) { Obj = ((Double)obj).byteValue(); }
						else if(obj instanceof String) { Obj = Byte.parseByte((String)obj); }
						else { bValid = false; }
						if(bValid) {
							((PreparedStatement)m_statement).setByte(nParameterIndex, Obj);
						}
						else {
							((PreparedStatement)m_statement).setObject(nParameterIndex, obj);
						}
						result = 1;
						break;
					}
					case Types.FLOAT: {
						float Obj = -1;
						if(obj instanceof Float) { Obj = ((Float)obj).floatValue(); }
						else if(obj instanceof Long) { Obj = ((Long)obj).floatValue(); }
						else if(obj instanceof Integer) { Obj = ((Integer)obj).floatValue(); }
						else if(obj instanceof Short) { Obj = ((Short)obj).floatValue(); }
						else if(obj instanceof Byte) { Obj = ((Byte)obj).floatValue(); }
						else if(obj instanceof Double) { Obj = ((Double)obj).floatValue(); }
						else { bValid = false; }
						if(bValid) {
							((PreparedStatement)m_statement).setFloat(nParameterIndex, Obj);
						}
						else {
							((PreparedStatement)m_statement).setObject(nParameterIndex, obj);
						}
						result = 1;
						break;
					}
					case Types.DOUBLE: {
						double Obj = -1;
						if(obj instanceof Double) { Obj = ((Double)obj).doubleValue(); }
						else if(obj instanceof Long) { Obj = ((Long)obj).doubleValue(); }
						else if(obj instanceof Integer) { Obj = ((Integer)obj).doubleValue(); }
						else if(obj instanceof Short) { Obj = ((Short)obj).doubleValue(); }
						else if(obj instanceof Byte) { Obj = ((Byte)obj).doubleValue(); }
						else if(obj instanceof Float) { Obj = ((Float)obj).doubleValue(); }
						else { bValid = false; }
						if(bValid) {
							((PreparedStatement)m_statement).setDouble(nParameterIndex, Obj);
						}
						else {
							((PreparedStatement)m_statement).setObject(nParameterIndex, obj);
						}
						result = 1;
						break;
					}
					case Types.BIT: {
						boolean Obj = false;
						if(obj instanceof Long) { Obj = ((Long)obj)!= 0; }
						else if(obj instanceof Integer) { Obj = ((Integer)result)!= 0; }
						else if(obj instanceof Short) { Obj = (Short)obj!= 0; }
						else if(obj instanceof Boolean) { Obj = (Boolean)obj; }
						else if(obj instanceof String) { Obj = Boolean.parseBoolean((String)obj); }
						else { bValid = false; }
						if(bValid) {
							((PreparedStatement)m_statement).setBoolean(nParameterIndex, Obj);
						}
						else {
							((PreparedStatement)m_statement).setObject(nParameterIndex, obj);
						}
						result = 1;
						break;
					}
					case Types.VARCHAR: {
						((PreparedStatement)m_statement).setString(nParameterIndex, (String)obj);
						result = 1;
						break;
					}
					case Types.LONGVARCHAR: {
						((PreparedStatement)m_statement).setString(nParameterIndex, (String)obj);
						result = 1;
						break;
					}
					case Types.DATE: {
						if(obj instanceof Calendar) {
							((PreparedStatement)m_statement).setDate(nParameterIndex, new Date(((Calendar)obj).getTimeInMillis()));
						}
						else if(obj instanceof Date) {
							((PreparedStatement)m_statement).setDate(nParameterIndex, (Date)obj);
						}
						else if(obj instanceof String) {
							String strDateFormat = getItemModel().getFieldInfo(nFieldIndex).getDateFormat();
							if(strDateFormat!=null) {
								((PreparedStatement)m_statement).setDate(nParameterIndex, DateTime.GetDateFromString((String)obj, strDateFormat));
							}
						}
						else {
							((PreparedStatement)m_statement).setObject(nParameterIndex, obj);
						}
						result = 1;
						break;
					}
					case Types.TIME: {
						if(obj instanceof Calendar) {
							((PreparedStatement)m_statement).setTime(nParameterIndex, new Time(((Calendar)obj).getTimeInMillis()));
						}
						else if(obj instanceof Time) {
							((PreparedStatement)m_statement).setTime(nParameterIndex, (Time)obj);
						}
						else if(obj instanceof String) {
							String strTimeFormat = getItemModel().getFieldInfo(nFieldIndex).getTimeFormat();
							if(strTimeFormat!=null) {
								((PreparedStatement)m_statement).setTime(nParameterIndex, (Time)DateTime.GetTimeFromString((String)obj, strTimeFormat));
							}
						}
						else {
							((PreparedStatement)m_statement).setObject(nParameterIndex, obj);
						}
						result = 1;
						break;
					}
					case Types.TIMESTAMP: {
						if(obj instanceof Calendar) {
							((PreparedStatement)m_statement).setTimestamp(nParameterIndex, new Timestamp(((Calendar)obj).getTimeInMillis()));
						}
						else if(obj instanceof Time) {
							((PreparedStatement)m_statement).setTimestamp(nParameterIndex, (Timestamp)obj);
						}
						else if(obj instanceof String) {
							String strDateTimeFormat = getItemModel().getFieldInfo(nFieldIndex).getDateTimeFormat();
							if(strDateTimeFormat!=null) {
								((PreparedStatement)m_statement).setTimestamp(nParameterIndex, (Timestamp)DateTime.GetDateTimeFromString((String)obj, strDateTimeFormat));
							}
						}
						else {
							((PreparedStatement)m_statement).setObject(nParameterIndex, obj);
						}
						result = 1;
						break;
					}
					default: {
						((PreparedStatement)m_statement).setObject(nParameterIndex, obj);
						result = 1;
						break;
					}
				}
			}
		}
		catch (SQLException ex) {
			if (m_statement!=null && m_nStatementStyle==statement_PREPARE) {
				((PreparedStatement)m_statement).setObject(nParameterIndex, obj);
				result = 1;
			}
		}
		return result;
	}
	/**
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2011. 08. 22 PM 12:48:10
	 * @since ModelWeb 1.0
	 */
	public recordSet_Model clone() {
		recordSet_Model result = null;
		if(m_objConnectionModel.getDbType()==dbConn_Model.DB_MYSQL) {
			result = new mysqlRecordSet(m_ItemModel);
		}
		else if(m_objConnectionModel.getDbType()==dbConn_Model.DB_MSSQL) {
			result = new mssqlRecordSet(m_ItemModel);
		}
		else if(m_objConnectionModel.getDbType()==dbConn_Model.DB_MSMDB) {
			result = new msmdbRecordSet(m_ItemModel);
		}
		else if(m_objConnectionModel.getDbType()==dbConn_Model.DB_ORACLE) {
			result = new oracleRecordSet(m_ItemModel);
		}
		result.m_objConnectionModel = this.m_objConnectionModel;
		result.m_objConnection = result.m_objConnectionModel.getConnection();
		return result;
	}
	/**
	 * 
	 * @throws SQLException
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 03. 03 PM 1:40:08
	 * @since ModelWeb
	 */
	public void close() throws SQLException {
		if (m_resultSet != null) {
			try { m_resultSet.close(); }
			catch (SQLException ex) { throw ex; }
			m_resultSet = null;
			m_strSql = null;
		}
		if (m_statement != null) {
			try { m_statement.close(); }
			catch (SQLException ex) { throw ex; }
			m_statement = null;
			m_strSql = null;
		}
		if(m_objConnection != null) {
			m_objConnectionModel.closeConnection(m_objConnection);
			m_objConnection = null;
		}
	}
}