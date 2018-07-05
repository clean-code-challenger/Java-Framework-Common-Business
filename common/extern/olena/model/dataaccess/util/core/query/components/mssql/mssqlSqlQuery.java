package common.extern.olena.model.dataaccess.util.core.query.components.mssql;

import java.sql.Types;

import common.extern.olena.model.dataaccess.mvc.base.IItem_Model;
import common.extern.olena.model.dataaccess.mvc.base.tbl_Model;
import common.extern.olena.model.dataaccess.util.core.query.sqlQuery_Model;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.pageExpressionRelation;

public class mssqlSqlQuery extends sqlQuery_Model{

	public mssqlSqlQuery(IItem_Model tableInfos) {
		super(tableInfos);
	}

	@Override
	protected void InitSQL() {
		m_strLimit = "top";
	}
	
	@Override
	public String productQueryCreateTable() throws Throwable {
		StringBuilder result = new StringBuilder("");
		
		result.append(String.format("IF NOT EXISTS (select * from  dbo.sysobjects where id = object_id(N'[dbo].[%1$s]') and OBJECTPROPERTY(id, N'IsUserTable') = 1) CREATE TABLE [%1$s]", getItemModel().getTableName()));
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
				strDefault5Null = "NULL";
			}
			else {
				if(itemModel.getFieldInfo(nIndex).isPrimaryAutoIncrease()) {
					strDefault5Null = "IDENTITY (1, 1) NOT NULL";
				}
				else { strDefault5Null = "NOT NULL"; }
			}
			switch(nType) {
				case Types.BIGINT: {
					result.append(String.format("[%1$s] [bigint] %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.INTEGER: {
					result.append(String.format("[%1$s] [int] %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.SMALLINT: {
					result.append(String.format("[%1$s] [smallint] %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.TINYINT: {
					result.append(String.format("[%1$s] [smallint] %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.FLOAT: {
					result.append(String.format("[%1$s] [float] %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.DOUBLE: {
					result.append(String.format("[%1$s] [double] %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.BIT: {
					result.append(String.format("[%1$s] [bit] %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.VARCHAR: {
					int nStringLen = itemModel.getFieldInfo(nIndex).getStringLength();
					result.append(String.format("[%1$s] [nvarchar] (%2$d) %3$s", itemModel.getFieldLabel(nIndex), nStringLen, strDefault5Null));
					break;
				}
				case Types.LONGVARCHAR: {
					result.append(String.format("[%1$s] [ntext] %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.DATE: {
					result.append(String.format("[%1$s] [datetime] %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.TIME: {
					result.append(String.format("[%1$s] [datetime] %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				case Types.TIMESTAMP: {
					result.append(String.format("[%1$s] [datetime] %2$s", itemModel.getFieldLabel(nIndex), strDefault5Null));
					break;
				}
				default: { break; }
			}
			if(nIndex==tbl_Model.field_id) {
				strPrimaryKey = String.format(", CONSTRAINT [PK_%1$s] PRIMARY KEY CLUSTERED([%2$s]) ON [PRIMARY]", itemModel.getTableName(), itemModel.getFieldLabel(nIndex));
			}
		}
		result.append(strPrimaryKey);
		result.append(")");			
		return result.toString();
	}

	@Override
	public String productQueryDropTable() throws Throwable {
		StringBuilder result = new StringBuilder("");
		result.append(String.format("IF EXISTS (select * from  dbo.sysobjects where id = object_id(N'[dbo].[%1$s]') and OBJECTPROPERTY(id, N'IsUserTable') = 1) drop table [dbo].[%1$s]", getItemModel().getTableName()));
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
	public String productQueryGetPrimaryID(IItem_Model infoItem) throws Throwable {
		String result = "";
		return result;
	}
	
	@Override
	public String getQuery(int nQueryType, String strSelect, String strFrom, String strWhere, String strOrder, pageExpressionRelation pageInfo) {
		StringBuilder result = new StringBuilder("");

		if(pageInfo==null) { pageInfo = new pageExpressionRelation(); }
		long nLimit = pageInfo.getLimit();
		if(nQueryType>=sqlQuery_Model.QUERY_SELECT && nQueryType < sqlQuery_Model.QUERY_INSERT) {
			if(pageInfo.getLimit()!=-1) {	////Limited
				if(nQueryType==sqlQuery_Model.QUERY_SELECT_DISTINCT) {
					result.append(m_strSelect + m_strSpace + strSelect + m_strSpace + m_strLimit + m_strSpace + String.valueOf(nLimit));
				}
				else {
					result.append(m_strSelect + m_strSpace + m_strLimit + m_strSpace + String.valueOf(nLimit) + m_strSpace + strSelect);
				}
			}
			else {	////Unlimited
				result.append(m_strSelect + m_strSpace + strSelect);
			}
			
			if(strFrom!=null) {
				if(strFrom.trim().length() > 0) { result.append(m_strSpace + m_strFrom + m_strSpace + strFrom); }
			}
			if(strWhere!=null) {
				if(strWhere.trim().length() > 0) { result.append(m_strSpace + m_strWhere + m_strSpace + strWhere); }
			}
			if(strOrder!=null) { 
				if(strOrder.trim().length() > 0){ result.append(m_strSpace + m_strOrderby + m_strSpace + strOrder); }
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
					if(strWhere!=null) {
						if(strWhere.trim().length() > 0) {
							result.append(m_strSpace + m_strWhere + m_strSpace + strWhere);
						}
					}
				}
			}
		}
		return result.toString();
	}
}
