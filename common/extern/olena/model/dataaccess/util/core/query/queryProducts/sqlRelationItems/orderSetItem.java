package common.extern.olena.model.dataaccess.util.core.query.queryProducts.sqlRelationItems;

import common.extern.olena.model.dataaccess.util.core.query.base.relationBase;

/**
 *
 * @author Olena.Zagreba
 * @version ModelWeb 1.0, 2012. 01. 13 AM 9:45:31
 * @since ModelWeb 1.0
 */
public class orderSetItem extends relationBase
{
	public orderSetItem(int nFieldIndex, boolean bValue) 
	{
		super();
		m_nInteger = nFieldIndex;
		m_bTrue = bValue;
	}
	public orderSetItem(String strExpression, boolean bValue) 
	{
		super();
		m_strExpression = strExpression;
		m_bTrue = bValue;
	}
	private int m_nInteger = -1;
	private String m_strExpression = null;
	private boolean m_bTrue = false;
	
	public int getInteger() { return m_nInteger; }
	public String getExpression() { return m_strExpression; }
	public boolean getBoolean() { return m_bTrue; }
};
