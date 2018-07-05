package common.extern.olena.model.dataaccess.util.core.query.components.mysql;

import java.sql.Types;

import common.extern.olena.model.dataaccess.mvc.base.IItem_Model;
import common.extern.olena.model.dataaccess.mvc.base.tbl_Model;
import common.extern.olena.model.dataaccess.util.core.query.sqlQuery_Model;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.pageExpressionRelation;

public class mysqlSqlQuery  extends sqlQuery_Model{

	public mysqlSqlQuery(IItem_Model tableInfos) {
		super(tableInfos);
	}

	@Override
	protected void InitSQL() {
		m_strLimit = "limit";
	}

	@Override
	public String productQueryCreateTable() throws Throwable { 
		StringBuilder result = new StringBuilder("");

		result.append(String.format("CREATE TABLE IF NOT EXISTS `%1$s`", getItemModel().getTableName()));
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
				strDefault5Null = "DEFAULT NULL";
			}
			else {
				if(getItemModel().getFieldInfo(nIndex).isPrimaryAutoIncrease()) {
					strDefault5Null = "NOT NULL";
				}					
			}
			switch(nType) {
				case Types.BIGINT: {
					result.append(String.format("`%1$s` bigint(24) %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.INTEGER: {
					result.append(String.format("`%1$s` int(12) %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.SMALLINT: {
					result.append(String.format("`%1$s` smallint(6) %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.TINYINT: {
					result.append(String.format("`%1$s` TinyInt(3) %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.FLOAT: {
					result.append(String.format("`%1$s` float %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.DOUBLE: {
					result.append(String.format("`%1$s` double %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.BIT: {
					result.append(String.format("`%1$s` bit(1) %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.VARCHAR: {
					int nStringLen = itemModel.getFieldInfo(nIndex).getStringLength();
					result.append(String.format("`%1$s` varchar(%2$d) %3$s", itemModel.getFieldLabel(nIndex), nStringLen, strDefault5Null));
					break;
				}
				case Types.LONGVARCHAR: {
					result.append(String.format("`%1$s` longtext %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.DATE: {
					result.append(String.format("`%1$s` date %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.TIME: {
					result.append(String.format("`%1$s` time %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.TIMESTAMP: {
					result.append(String.format("`%1$s` timestamp NULL %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				default: { break; }
			}
			if(nIndex==tbl_Model.field_id) {
				if(itemModel.getFieldInfo(nIndex).isPrimaryAutoIncrease()) {
					result.append(" auto_increment");
				}
				strPrimaryKey = String.format(", PRIMARY KEY(`%1$s`)", itemModel.getFieldLabel(nIndex));
			}
		}
		result.append(strPrimaryKey);
		result.append(")");
		result.append("ENGINE=MyISAM DEFAULT CHARSET=utf8;");
		return result.toString();
	}
	
	@Override
	public String productQueryDropTable() throws Throwable {
		StringBuilder result = new StringBuilder("");
		result.append(String.format("DROP TABLE IF EXISTS `%1$s`", getItemModel().getTableName()));
		return result.toString();
	}
	
	@Override
	public String getPlusStringBack(String strPlus) {
		String result = "";
		result = m_strSpace + strPlus + m_strEqu + "concat" + "(" + strPlus + getCommaQuery(m_strQus) + ")";
		return result;
	}

	@Override
	public String getPlusStringForward(String strPlus) {
		String result = "";
		result = m_strSpace + strPlus + m_strEqu + "concat" + "(" + m_strQus + getCommaQuery(strPlus) + ")";
		return result;
	}
	
	@Override
	public String productQueryGetPrimaryID(IItem_Model infoItem) throws Throwable {
		String result = "";
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
			if(strWhere!=null) { 
				if(strWhere.trim().length() > 0) {
					result.append(m_strSpace + m_strWhere + m_strSpace + strWhere);
				}
			}
			if(strOrder!=null) { 
				if(strOrder.trim().length() > 0) {
					result.append(m_strSpace + m_strOrderby + m_strSpace + strOrder);
				}
			}
			if(nLimit!=-1) {	//Limited
				if(nPageCount!=-1) {	//Page count exist
					if(nPageSize != -1 && nPageIndex != -1) {	//Info related page exist
						if(nLimit < nPageSize){ result.append(m_strSpace + m_strLimit + m_strSpace + String.valueOf((nPageIndex * nPageSize)) + m_strComma + String.valueOf(nLimit)); }
						else { 					result.append(m_strSpace + m_strLimit + m_strSpace + String.valueOf((nPageIndex * nPageSize)) + m_strComma + String.valueOf(nPageSize)); }
					} 
					else {	//No info related with page
						result.append(m_strSpace + m_strLimit + m_strSpace + String.valueOf(nLimit));
					}
				}
				else {	//Unlimited Page count
					result.append(m_strSpace + m_strLimit + m_strSpace + String.valueOf(nLimit));
				}
			}
			else {	//제한개수가 없을때
				if(nPageCount!=-1) {
					if(nPageSize != -1 && nPageIndex != -1) {//페지에 관한 정보가 있을때
						result.append(m_strSpace + m_strLimit + m_strSpace + String.valueOf((nPageIndex * nPageSize) - 1) + m_strComma + String.valueOf(nLimit));
					}
					else { }
				}
				else {	//페지개수가 주어지지 않았을때
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
				if(strFrom.trim().length() > 0) {
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
}
