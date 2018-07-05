package common.extern.olena.model.dataaccess.util.core.query.queryProducts.sqlRelationItems;

import common.extern.olena.model.dataaccess.util.core.query.sqlQuery_Model;
import common.extern.olena.model.dataaccess.util.core.query.base.relationBase;

/**
 *
 * @author Olena.Zagreba
 * @version ModelWeb 1.0, 2012. 01. 13 AM 9:45:31
 * @since ModelWeb 1.0
 */
public class whereRelationItem extends relationBase
{
	public whereRelationItem(int relationPrev, int nFields, int nExpression, Object setObject) 
	{
		super();
		m_nRelationPrev = relationPrev;
		m_nFieldIndex = nFields;
		m_nExpression = nExpression;
		m_oSetObject = setObject;
	}
	public whereRelationItem(int relationPrev, String strFieldExpression, int nExpression, Object setObject) 
	{
		super();
		m_nRelationPrev = relationPrev;
		m_strFieldExpression = strFieldExpression;
		m_nExpression = nExpression;
		m_oSetObject = setObject;
	}
	private int m_nRelationPrev = -1;
	private int m_nFieldIndex = -1;
	private String m_strFieldExpression = null;
	
	private int m_nExpression = -1;
	private Object m_oSetObject = null;
	
	public int getRelationPrev() { return m_nRelationPrev;}
	public void setRelationPrev(int relationPrev) { m_nRelationPrev = relationPrev; }
	
	public int getFieldIndex() { return m_nFieldIndex; }
	public void setFieldIndex(int fieldIndex) { m_nFieldIndex = fieldIndex; }
	public String getFieldExpression() { return m_strFieldExpression; }
	public void setFieldExpression(String fieldExpression) { m_strFieldExpression = fieldExpression; }
	
	public int getExpression() { return m_nExpression; }
	public void setExpression(int expression) { m_nExpression = expression; }
	
	public Object getSetObject() { return m_oSetObject; }
	public void setSetObject(Object setObject) { m_oSetObject = setObject; }
	
	public String toSQLString(sqlQuery_Model queryModel, int nSeek) {
		StringBuilder result = new StringBuilder("");
		if(nSeek > 0) {
			result.append(queryModel.getSpace());
			if(m_nRelationPrev!=-1) {
				if(m_nRelationPrev==sqlQuery_Model.RELATION_AND) {
					result.append(queryModel.getSpace() + queryModel.getAnd()); 
				}
				else if(m_nRelationPrev==sqlQuery_Model.RELATION_OR) {
					result.append(queryModel.getSpace() + queryModel.getOr()); 
				}
			}
		}
		String strFieldExpression = null;
		if(m_nFieldIndex >= 0) {
			strFieldExpression = queryModel.getItemModel().getFieldLabel(m_nFieldIndex);
		}
		else {
			strFieldExpression = m_strFieldExpression;
		}
		if(strFieldExpression!=null) {
			switch(m_nExpression) {
			case sqlQuery_Model.EXPRESSION_EQU: {
				result.append(queryModel.getSpace())
				.append(strFieldExpression) 
				.append(queryModel.getEqu() + queryModel.getQus());
				break;
			}
			case sqlQuery_Model.EXPRESSION_SMALL: {
				result.append(queryModel.getSpace())
				.append(strFieldExpression) 
				.append(queryModel.getSmall() + queryModel.getQus());
				break;
			}
			case sqlQuery_Model.EXPRESSION_SMALLEQU: {
				result.append(queryModel.getSpace()) 
				.append(strFieldExpression) 
				.append(queryModel.getSmallEqu() + queryModel.getQus());
				break;
			}
			case sqlQuery_Model.EXPRESSION_LARGE: {
				result.append(queryModel.getSpace()) 
				.append(strFieldExpression) 
				.append(queryModel.getLarge() + queryModel.getQus());
				break;
			}
			case sqlQuery_Model.EXPRESSION_LARGEEQU: {
				result.append(queryModel.getSpace())
				.append(strFieldExpression) 
				.append(queryModel.getLargeEqu() + queryModel.getQus());
				break;
			}
			case sqlQuery_Model.EXPRESSION_NOEQU: {
				result.append(queryModel.getSpace())
				.append(strFieldExpression) 
				.append(queryModel.getNoEqu() + queryModel.getQus());
				break;
			}
			case sqlQuery_Model.EXPRESSION_LIKE: {
				result.append(queryModel.getSpace()) 
				.append(strFieldExpression) 
				.append(queryModel.getSpace()) 
				.append(queryModel.getLike() + queryModel.getSpace() + queryModel.getQus());
				break;
			}
			case sqlQuery_Model.EXPRESSION_ISNULL: {
				result.append(queryModel.getSpace()) 
				.append(strFieldExpression) 
				.append(queryModel.getSpace() + queryModel.getIsnull());
				break;
			}
			case sqlQuery_Model.EXPRESSION_ISNOTNULL: {
				result.append(queryModel.getSpace()) 
				.append(strFieldExpression) 
				.append(queryModel.getSpace() + queryModel.getIsnotnull());
				break;
			}
			default: { 
				result.append(queryModel.getSpace()).append(strFieldExpression); 
			}
			}
		}
		return result.toString();
	}
};
