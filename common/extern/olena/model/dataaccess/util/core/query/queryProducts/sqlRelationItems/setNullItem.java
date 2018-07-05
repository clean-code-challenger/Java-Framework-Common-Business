package common.extern.olena.model.dataaccess.util.core.query.queryProducts.sqlRelationItems;

import common.extern.olena.model.dataaccess.util.core.query.base.relationBase;

/**
 *
 * @author Olena.Zagreba
 * @version ModelWeb 1.0, 2012. 01. 13 AM 9:45:31
 * @since ModelWeb 1.0
 */
public class setNullItem extends relationBase
{
	public setNullItem(int nFields) 
	{
		super();
		m_nFieldIndex = nFields;
	}
	private int m_nFieldIndex = -1;
	public int getFieldIndex() { return m_nFieldIndex; }
};
