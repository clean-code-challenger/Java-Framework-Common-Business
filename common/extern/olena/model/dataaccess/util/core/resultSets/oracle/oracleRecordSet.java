package common.extern.olena.model.dataaccess.util.core.resultSets.oracle;

import java.io.StringReader;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

import common.extern.olena.model.dataaccess.mvc.base.IItem_Model;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.pageExpressionRelation;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.selectExpressionRelation;
import common.extern.olena.model.dataaccess.util.core.resultSets.recordSet_Model;

public class oracleRecordSet extends recordSet_Model{

	public oracleRecordSet(IItem_Model itemInfos) {
		super(itemInfos);
	}
	@Override
	public boolean PrepareStatementInsert(String strSql) throws SQLException {
		boolean result = false;
		try {
			m_strSql = strSql;
			if ((m_objConnectionModel == null) || m_strSql == null || "".equals(m_strSql)) { return false; }
			m_nStatementStyle = statement_PREPARE;
			m_statement = m_objConnection.prepareStatement(strSql);
			if (m_statement == null) { result = false; }
			else { result = true; }
		}
		catch (SQLException ex) { throw ex; }
		return result;
	}
	@Override
	public Object executePrepareStatementInsert() throws SQLException {
		Object result = null;
		try {
			if ((m_objConnectionModel == null) || m_strSql == null || "".equals(m_strSql)) { return null; }
			((PreparedStatement)m_statement).executeUpdate();
		}
		catch (SQLException ex) { throw ex; }
		return result;
	}
	@Override
	public Object executeStatementInsert(String strSql) throws SQLException {
		Object result = null;
		try {
			m_strSql = strSql;
			if ((m_objConnectionModel == null) || m_strSql == null || "".equals(m_strSql)) { return false; }
			if(m_statement== null) { m_statement = m_objConnection.createStatement(); }
			m_statement.executeUpdate(strSql);
		}
		catch(SQLException ex) { throw ex; }
		return result;
	}
	@Override
	public Vector<Object> productGetFirstInfoList(pageExpressionRelation pageInfo) throws Throwable {
		Vector<Object> result = new Vector<Object>();
		if(pageInfo==null) { pageInfo = new pageExpressionRelation(); }
		long nPageSize = pageInfo.getPageSize();
		long nPageIndex = pageInfo.getPageIndex();
		long nPageCount = pageInfo.getPageCount();
		try {
			if(nPageCount > 0) {
				if(nPageSize > 0) {	
					if(nPageIndex < 0) { pageInfo.setPageIndex(0); }
					long nIndex = nPageSize;
					while(moveNext()) {
						if(nIndex <= 0) { break; }
						result.add(getObjectbySeek(1));
						nIndex--;
					}
				}
				else {
					while(moveNext()) { result.add(getObjectbySeek(1)); }
				}
			}
			else {	
				if(nPageSize > 0) {
					pageInfo.setPageSize(PageSize(nPageSize));
					pageInfo.setPageCount(GetPageCount());
					pageInfo.setPageIndex(0);
					long nIndex = nPageSize;
					while(moveNext()) {
						if(nIndex <= 0) { break; }
						result.add(getObjectbySeek(1));
						nIndex--;
					}
				}
				else {
					while(moveNext()) { result.add(getObjectbySeek(1)); }
				}
			}					
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
	@Override
	public Vector<IItem_Model> productGetInfoListItem(selectExpressionRelation selectList, pageExpressionRelation pageInfo) throws Throwable {
		Vector<IItem_Model> result = new Vector<IItem_Model>();
		if(pageInfo==null) { pageInfo = new pageExpressionRelation(); }
		long nPageSize = pageInfo.getPageSize();
		long nPageIndex = pageInfo.getPageIndex();
		long nPageCount = pageInfo.getPageCount();
		try {
			IItem_Model resultItem = null;
			
			boolean bSelectNull = false;
			if(selectList==null) {
				bSelectNull = true;
			}
			else if(selectList.getRelations()==null) {
				bSelectNull = true;
			}
			if(bSelectNull) {
				if(nPageCount > 0) {
					if(nPageSize > 0) {
						if(nPageIndex < 0) { pageInfo.setPageIndex(0); }
						long nIndex = nPageSize;
						while(moveNext()) {
							if(nIndex <= 0) { break; }
							resultItem = GetItemFromTableModel();
							result.add(resultItem);
							nIndex--;
						}
					}
					else {
						while(moveNext()) {
							resultItem = GetItemFromTableModel();
							result.add(resultItem);
						}
					}
				}
				else {
					if(nPageSize > 0) {
						pageInfo.setPageSize(PageSize(nPageSize));
						pageInfo.setPageCount(GetPageCount());
						pageInfo.setPageIndex(0);
						long nIndex = nPageSize;
						while(moveNext()) {
							if(nIndex <= 0) { break; }
							resultItem = GetItemFromTableModel();
							result.add(resultItem);
							nIndex--;
						}
					}
					else {
						while(moveNext()) {
							resultItem = GetItemFromTableModel();
							result.add(resultItem);
						}
					}
				}					
			}
			else {
				if(nPageCount > 0) {
					if(nPageSize > 0) {
						if(nPageIndex < 0) { pageInfo.setPageIndex(0); }
						long nIndex = nPageSize;
						while(moveNext()) {
							if(nIndex <= 0) { break; }
							resultItem = GetItemFromSelectExpression(selectList);
							result.add(resultItem);
							nIndex--;
						}
					}
					else {
						while(moveNext()) {
							resultItem = GetItemFromSelectExpression(selectList);
							result.add(resultItem);
						}
					}
				}
				else {	
					if(nPageSize > 0) {
						pageInfo.setPageSize(PageSize(nPageSize));
						pageInfo.setPageCount(GetPageCount());
						pageInfo.setPageIndex(0);
						long nIndex = nPageSize;
						while(moveNext()) {
							if(nIndex <= 0) { break; }
							resultItem = GetItemFromSelectExpression(selectList);
							result.add(resultItem);
							nIndex--;
						}
					}
					else {
						while(moveNext()) {
							resultItem = GetItemFromSelectExpression(selectList);
							result.add(resultItem);
						}
					}
				}					
			}
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
	public Document productGetInfoListXml(pageExpressionRelation pageInfo, String strXmlRoot, String strXmlRow)throws Throwable {
		Document result = null;
		
		if(pageInfo==null) { pageInfo = new pageExpressionRelation(); }
		long nPageSize = pageInfo.getPageSize();
		long nPageIndex = pageInfo.getPageIndex();
		long nPageCount = pageInfo.getPageCount();
		try {
			ResultSetMetaData rsmd = getResultSet().getMetaData();
			final int numberOfColumns = rsmd.getColumnCount();
			String[] metaFieldList = new String[numberOfColumns];
			int [] metaTypeList = new int[numberOfColumns];
			for (int inx = 0; inx < numberOfColumns; inx++) {
				final int index = inx+1;
				metaFieldList[inx] = rsmd.getColumnLabel(index);
				metaTypeList[inx] = rsmd.getColumnType(index);
			}
			
			DOMParser dom; StringReader readerStr; InputSource source;
			String strXml = "<" + strXmlRoot + "/>";
			dom = new DOMParser();
			readerStr = new StringReader(strXml);
			source = new InputSource(readerStr);
			dom.parse(source); 
			result = dom.getDocument();
			Element eleDoc = result.getDocumentElement();
			Node elementNode = null; 
			
			if(nPageCount > 0) {
				if(nPageSize > 0) {
					if(nPageIndex < 0) { pageInfo.setPageIndex(0); }
					long nIndex = nPageSize;
					while(moveNext()) {
						if(nIndex <= 0) { break; }
						elementNode = getXmlNodeFromMetaData(metaFieldList, metaTypeList, result, strXmlRow);
						eleDoc.appendChild(elementNode);
						nIndex--;
					}
				}
				else {
					while(moveNext()) {
						elementNode = getXmlNodeFromMetaData(metaFieldList, metaTypeList, result, strXmlRow);
						eleDoc.appendChild(elementNode);
					}
				}
			}
			else {	
				if(nPageSize > 0) {	
					pageInfo.setPageSize(PageSize(nPageSize));
					pageInfo.setPageCount(GetPageCount());
					pageInfo.setPageIndex(0);
					long nIndex = nPageSize;
					while(moveNext()) {
						if(nIndex <= 0) { break; }
						elementNode = getXmlNodeFromMetaData(metaFieldList, metaTypeList, result, strXmlRow);
						eleDoc.appendChild(elementNode);
						nIndex--;
					}
				}
				else {
					while(moveNext()) {
						elementNode = getXmlNodeFromMetaData(metaFieldList, metaTypeList, result, strXmlRow);
						eleDoc.appendChild(elementNode);
					}
				}
			}					
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
		
	@SuppressWarnings({ "unchecked" })
	public Vector<HashMap> productGetInfoListHash(pageExpressionRelation pageInfo) throws Throwable {
		Vector<HashMap> result = new Vector<HashMap>();
		if(pageInfo==null) { pageInfo = new pageExpressionRelation(); }
		long nPageSize = pageInfo.getPageSize();
		long nPageIndex = pageInfo.getPageIndex();
		long nPageCount = pageInfo.getPageCount();
		try {
			HashMap hashInfo = null;
			ResultSetMetaData rsmd = getResultSet().getMetaData();
			final int numberOfColumns = rsmd.getColumnCount();
			String[] metaDataList = new String[numberOfColumns];

			for (int inx = 0; inx < numberOfColumns; inx++) {
				final int index = inx+1;
				metaDataList[inx] = rsmd.getColumnLabel(index);
			}
			if(nPageCount > 0) {
				if(nPageSize > 0) {
					if(nPageIndex < 0) { pageInfo.setPageIndex(0); }
					long nIndex = nPageSize;
					while(moveNext()) {
						if(nIndex <= 0) { break; }
						hashInfo = GetHashMapFromMetaData(metaDataList);
						result.add(hashInfo);
						nIndex--;
					}
				}
				else {
					while(moveNext()) {
						hashInfo = GetHashMapFromMetaData(metaDataList);
						result.add(hashInfo);
					}
				}
			}
			else {	
				if(nPageSize > 0) {
					pageInfo.setPageSize(PageSize(nPageSize));
					pageInfo.setPageCount(GetPageCount());
					pageInfo.setPageIndex(0);
					long nIndex = nPageSize;
					while(moveNext()) {
						if(nIndex <= 0) { break; }
						hashInfo = GetHashMapFromMetaData(metaDataList);
						result.add(hashInfo);
						nIndex--;
					}
				}
				else {
					while(moveNext()) {
						hashInfo = GetHashMapFromMetaData(metaDataList);
						result.add(hashInfo);
					}
				}
			}					
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
}
