package common.extern.olena.model.dataaccess.util.core.query.queryProducts.sqlRelationItems;

import common.extern.olena.model.dataaccess.util.core.query.base.relationBase;

/**
 *
 * @author Olena.Zagreba
 * @version ModelWeb 1.0, 2012. 01. 13 AM 9:45:31
 * @since ModelWeb 1.0
 */
public class setObjectItem extends relationBase
{
	public setObjectItem(Object object) 
	{
		super();
		m_oObject = object;
	}
	private Object m_oObject = -1;
	public Object getObject() { return m_oObject; }
};
