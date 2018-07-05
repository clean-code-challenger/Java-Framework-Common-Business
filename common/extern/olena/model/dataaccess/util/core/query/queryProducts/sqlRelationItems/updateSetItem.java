package common.extern.olena.model.dataaccess.util.core.query.queryProducts.sqlRelationItems;

import common.extern.olena.model.dataaccess.util.core.query.base.relationBase;

/**
 * query의 최소관계모형객체
 *
 * @author Olena.Zagreba
 * @version ModelWeb 1.0, 2012. 01. 13 AM 9:45:31
 * @since ModelWeb 1.0
 */
public class updateSetItem extends relationBase
{
	public updateSetItem(int nValue, Object oValue) 
	{
		super();
		m_nFieldIndex = nValue;
		m_oObject = oValue;
	}
	
	public updateSetItem(String strExpression, Object oValue) 
	{
		super();
		m_strExpression = strExpression;
		m_oObject = oValue;
	}
	
	private String m_strExpression = null;
	private int m_nFieldIndex = -1;
	private Object m_oObject = null;
	
	public int getFieldIndex() { return m_nFieldIndex; }
	public String getExpression() { return m_strExpression; }
	public Object getObject() { return m_oObject; }
	
};
