package common.extern.olena.model.dataaccess.util.core.query.components.oracle;

import java.sql.Types;

import common.extern.olena.model.dataaccess.mvc.base.IItem_Model;
import common.extern.olena.model.dataaccess.mvc.base.tbl_Model;
import common.extern.olena.model.dataaccess.util.core.query.sqlQuery_Model;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.pageExpressionRelation;

public class oracleSqlQuery  extends sqlQuery_Model{

	public oracleSqlQuery(IItem_Model tableInfos) {
		super(tableInfos);
	}
	@Override
	protected void InitSQL() {
		m_strLimit = "rownum";
	}

	@Override
	public String productQueryCreateTable() throws Throwable {
		StringBuilder result = new StringBuilder("");
		
		result.append(String.format("CREATE TABLE %1$s", getItemModel().getTableName()));
		result.append("(");
		int nIndex;
		String strPrimaryKey = "";
		String strDefault5Null = "";
		IItem_Model itemModel = getItemModel();
		int nLength = itemModel.getFieldLength();
		for(nIndex = 0; nIndex < nLength; nIndex++) {
			int nType = itemModel.getFieldType(nIndex);
			if(nIndex!=tbl_Model.field_id) {
				result.append(",");
				strDefault5Null = "";
			}
			else { strDefault5Null = "NOT NULL"; }
			switch(nType) {
				case Types.BIGINT: {
					result.append(String.format("%1$s NUMBER %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.INTEGER: {
					result.append(String.format("%1$s NUMBER %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.SMALLINT: {
					result.append(String.format("%1$s NUMBER %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.TINYINT: {
					result.append(String.format("%1$s NUMBER %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.FLOAT: {
					result.append(String.format("%1$s NUMBER %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.DOUBLE: {
					result.append(String.format("%1$s NUMBER %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.BIT: {
					result.append(String.format("%1$s NUMBER %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.VARCHAR: {
					int nStringLen = itemModel.getFieldInfo(nIndex).getStringLength();
					result.append(String.format("%1$s VARCHAR2(%2$d) LONG %d$s", itemModel.getFieldLabel(nIndex), nStringLen, strDefault5Null));
					break;
				}
				case Types.LONGVARCHAR: {
					result.append(String.format("%1$s LONG %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.DATE: {
					result.append(String.format("%1$s DATE %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.TIME: {
					result.append(String.format("%1$s DATE %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.TIMESTAMP: {
					result.append(String.format("%1$s DATE %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				default: { break; }
			}
			if(nIndex==tbl_Model.field_id) {
				strPrimaryKey = String.format(", CONSTRAINT PK_%1$s PRIMARY KEY (%2$s)", itemModel.getTableName(), itemModel.getFieldLabel(nIndex));
			}
		}
		result.append(strPrimaryKey);
		result.append(")\r\n");
		return result.toString();
	}
	@Override
	public String productQueryDropTable() throws Throwable {
		StringBuilder result = new StringBuilder("");
		result.append(String.format("DROP TABLE %1$s\r\n", getItemModel().getTableName()));
		return result.toString();
	}
	@Override
	public String getPlusStringBack(String strPlus) {
		String result = "";
		result = m_strSpace + strPlus + m_strEqu + strPlus + "+" + m_strQus;
		return result;
	}
	@Override
	public String getPlusStringForward(String strPlus) {
		String result = "";
		result = m_strSpace + strPlus + m_strEqu  + m_strQus + "+" + strPlus;
		return result;
	}
	@Override
	public String getQuery(int nQueryType, String strSelect, String strFrom, String strWhere, String strOrder, pageExpressionRelation pageInfo) {
		StringBuilder result = new StringBuilder("");

		if(pageInfo==null) { pageInfo = new pageExpressionRelation(); }
		long nLimit = pageInfo.getLimit();
		long nPageSize = pageInfo.getPageSize();
		long nPageIndex = pageInfo.getPageIndex();
		long nPageCount = pageInfo.getPageCount();
		if(nQueryType>=sqlQuery_Model.QUERY_SELECT && nQueryType < sqlQuery_Model.QUERY_INSERT) {
			result.append(m_strSelect + m_strSpace + strSelect);
			if(strFrom!=null) {
				if(strFrom.trim().length() > 0) {
					result.append(m_strSpace + m_strFrom + m_strSpace + strFrom);
				}
			}
			if(nLimit!=-1) { 
				if(nPageCount!=-1) { 
					if(nPageSize != -1 && nPageIndex != -1) { 
						if(strWhere!=null) {
							if(strWhere.trim().length() > 0) { 
								if(nLimit < nPageSize) {
									result.append(m_strSpace + m_strWhere);
									result.append(m_strSpace + "(");
									result.append("(" + strWhere + ")");
									result.append(m_strSpace + m_strAnd);
									result.append("(" + m_strLimit + ">" + String.valueOf(nPageIndex * nPageSize) + m_strAnd + m_strSpace + m_strLimit + "<=" + String.valueOf(nPageIndex * nPageSize + nLimit) + ")");
									result.append(")");
								}
								else { 
									result.append(m_strSpace + m_strWhere);
									result.append(m_strSpace + "(");
									result.append("(" + strWhere + ")");
									result.append(m_strSpace + m_strAnd);
									result.append("(" + m_strLimit + ">" + String.valueOf(nPageIndex * nPageSize) + m_strAnd + m_strSpace + m_strLimit + "<=" + String.valueOf( nPageIndex * nPageSize + nPageSize) + ")");
									result.append(")");
								}
							}
							else {
								if(nLimit < nPageSize) {
									result.append(m_strSpace + m_strWhere);
									result.append(m_strSpace + m_strLimit + ">" + String.valueOf(nPageIndex * nPageSize) + m_strAnd + m_strSpace + m_strLimit + "<=" + String.valueOf(nPageIndex * nPageSize + nLimit));
								}
								else {
									result.append(m_strSpace + m_strWhere);
									result.append(m_strSpace + m_strLimit + ">" + String.valueOf(nPageIndex * nPageSize) + m_strAnd + m_strSpace + m_strLimit + "<=" + String.valueOf(nPageIndex * nPageSize + nPageSize));
								}	
							}
						}
						else { 
							if(nLimit < nPageSize) {
								result.append(m_strSpace + m_strWhere);
								result.append(m_strSpace + m_strLimit + ">" + String.valueOf(nPageIndex * nPageSize) + m_strAnd + m_strSpace + m_strLimit + "<=" + String.valueOf(nPageIndex * nPageSize + nLimit));
							}
							else {
								result.append(m_strSpace + m_strWhere);
								result.append(m_strSpace + m_strLimit + ">" + String.valueOf(nPageIndex * nPageSize) + m_strAnd + m_strSpace + m_strLimit + "<=" + String.valueOf(nPageIndex * nPageSize + nPageSize));
							}
						}
					}
					else { 
						if(strWhere!=null) { 
							if(strWhere.trim().length() > 0) {
								result.append(m_strSpace + m_strWhere);
								result.append(m_strSpace + "(");
								result.append("(" + strWhere + ")");
								result.append(m_strSpace + m_strAnd);
								result.append("(" + m_strLimit + "<=" + String.valueOf(nLimit) + ")");
								result.append(")");
							}
							else { 
								result.append(m_strSpace + m_strWhere);
								result.append(m_strSpace + m_strLimit + "<=" + String.valueOf(nLimit));
							}
						}
						else {
							result.append(m_strSpace + m_strWhere);
							result.append(m_strSpace + m_strLimit + "<=" + String.valueOf(nLimit));
						}
					}
				}
				else {
					if(strWhere!=null) { 
						if(strWhere.trim().length() > 0) {
							result.append(m_strSpace + m_strWhere);
							result.append(m_strSpace + "(");
							result.append("(" + strWhere + ")");
							result.append(m_strSpace + m_strAnd);
							result.append("(" + m_strLimit + "<=" + String.valueOf(nLimit) + ")");
							result.append(")");
						}
						else {
							result.append(m_strSpace + m_strWhere);
							result.append(m_strSpace + m_strLimit + "<=" + String.valueOf(nLimit));
						}
					}
					else {
						result.append(m_strSpace + m_strWhere);
						result.append(m_strSpace + m_strLimit + "<=" + String.valueOf(nLimit));
					}
				}
				
			}
			else { 
				if(nPageCount!=-1) { 
					if(nPageSize != -1 && nPageIndex != -1) { 
						if(m_strWhere!=null) { 
							if(m_strWhere.trim().length() > 0) {
								result.append(m_strSpace + m_strWhere);
								result.append(m_strSpace + "(");
								result.append("(" + strWhere + ")");
								result.append(m_strSpace + m_strAnd);
								result.append("(" + m_strLimit + ">" + String.valueOf(nPageIndex * nPageSize) + m_strAnd + m_strSpace + m_strLimit + "<=" + String.valueOf(nPageIndex * nPageSize + nPageSize) + ")");
								result.append(")");
							}
							else {
								result.append(m_strSpace + m_strWhere);
								result.append(m_strSpace + "(");
								result.append("(" + strWhere + ")");
								result.append(m_strSpace + m_strAnd);
								result.append("(" + m_strLimit + ">" + String.valueOf(nPageIndex * nPageSize) + m_strAnd + m_strSpace + m_strLimit + "<=" + String.valueOf(nPageIndex * nPageSize + nPageSize) + ")");
								result.append(")");
							}
						}
					}
					else {
						if(m_strWhere!=null) { 
							if(m_strWhere.trim().length() > 0) {
								result.append(m_strSpace + m_strWhere + m_strSpace +  strWhere);
							}
						}
					}
				}
				else {
					if(m_strWhere!=null) {
						if(m_strWhere.trim().length() > 0) {
							result.append(m_strSpace + m_strWhere + m_strSpace +  strWhere);
						}
					}
				}
			}
			if(strOrder!=null) { 
				if(strOrder.trim().length() > 0) {
					result.append(m_strSpace + m_strOrderby + m_strSpace + strOrder);
				}
			}
		}
		else if(nQueryType>=sqlQuery_Model.QUERY_INSERT && nQueryType < sqlQuery_Model.QUERY_UPDATE) {
			if(strFrom!=null) { 
				if(strFrom.trim().length() > 0) {
					result.append(m_strInsert + m_strSpace + "into" + m_strSpace + strFrom);
					if(strSelect!=null) { 
						if(strSelect.trim().length() > 0) {
							result.append(m_strSpace + strSelect);
						}
					}
				}
			}
		}
		else if(nQueryType>=sqlQuery_Model.QUERY_UPDATE && nQueryType < sqlQuery_Model.QUERY_DELETE) {
			if(strFrom!=null) { 
				if( strFrom.trim().length() > 0) {
					result.append(m_strUpdate + m_strSpace + strFrom);
					if(strSelect!=null) { 
						if(strSelect.trim().length() > 0) {
							result.append(m_strSpace + strSelect);
						}
					}
				}
			}
			if(strWhere!=null) {
				if(strWhere.trim().length() > 0) {
					result.append(m_strSpace + m_strWhere + m_strSpace + strWhere);
				}
			}
		}
		else if(nQueryType>=sqlQuery_Model.QUERY_DELETE && nQueryType < sqlQuery_Model.QUERY_SPECIAL) {
			if(strFrom!=null) { 
				if(strFrom.trim().length() > 0) {
					result.append(m_strDelete + m_strSpace + m_strFrom + m_strSpace + strFrom);
				}
			}
			if(strWhere!=null) { 
				if(strWhere.trim().length() > 0) {
					result.append(m_strSpace + m_strWhere + m_strSpace + strWhere);
				}
			}
		}
		return result.toString();
	}
	
	/************************/
	/**
	 * @param infoItem
	 * @return
	 * @throws Throwable
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 04 AM 8:22:54
	 * @since ModelWeb 1.0
	 */
	public String productQueryGetPrimaryID(IItem_Model infoItem)throws Throwable {
		String result = "";
		try {
			String strSelect = "", strFrom = "";
			strFrom =	m_ItemModel.getTableName();
			strSelect =	m_ItemModel.getTableName() + "_SEQ.nextVal";
			result = 	getQuery(sqlQuery_Model.QUERY_SELECT_FIELD, strSelect, strFrom, null, null, null);
		}
		catch(Throwable ex) { throw ex; }
		finally { }
		return result;
	}
}
