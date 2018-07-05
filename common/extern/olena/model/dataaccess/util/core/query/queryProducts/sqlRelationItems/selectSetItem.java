package common.extern.olena.model.dataaccess.util.core.query.queryProducts.sqlRelationItems;

import common.extern.olena.model.dataaccess.util.core.query.base.relationBase;

/**
 * query의 최소관계모형객체
 *
 * @author Olena.Zagreba
 * @version ModelWeb 1.0, 2012. 01. 13 AM 9:45:31
 * @since ModelWeb 1.0
 */
public class selectSetItem extends relationBase
{
	public selectSetItem(int nFields) {
		super();
		m_nFieldIndex = nFields;
	}
	public selectSetItem(String strFieldExpression) {
		super();
		m_strFieldExpression = strFieldExpression;
	}
	private int m_nFieldIndex = -1;
	private String m_strFieldExpression = null;
	
	public int getFieldIndex() { return m_nFieldIndex; }
	public void setFieldIndex(int fieldIndex) { m_nFieldIndex = fieldIndex; }
	public String getFieldExpression() { return m_strFieldExpression; }
	public void setFieldExpression(String fieldExpression) { m_strFieldExpression = fieldExpression; }
};
