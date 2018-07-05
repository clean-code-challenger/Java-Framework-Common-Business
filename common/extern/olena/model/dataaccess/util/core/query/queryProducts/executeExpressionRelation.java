package common.extern.olena.model.dataaccess.util.core.query.queryProducts; 
import java.util.Vector;

import common.extern.olena.model.dataaccess.util.core.query.base.relationBase;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.sqlRelationItems.setObjectItem;


public class executeExpressionRelation extends relationBase 
{
	public executeExpressionRelation() {
		super();
		m_oRelations = new Vector<setObjectItem>();
	}
	private Vector<setObjectItem> m_oRelations = null;
	private int 				m_nseek = -1;
	public void addRelation(Object object) {
		m_oRelations.add(new setObjectItem(object));
	}
	public Object getSeekObject() {
		Object result = null;
		if(m_oRelations!=null) { result = m_oRelations.get(m_nseek).getObject(); }
		return result; 
	}
	public int moveFirst() {
		m_nseek = 0;
		return m_nseek;
	}
	/**
	 * 
	 * @param relationCurrent
	 * @return
	 * 
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 14 PM 12:43:48
	 * @since ModelWeb 1.0
	 */
	public int moveNext() {
		m_nseek++;
		if(m_nseek < m_oRelations.size()) { }
		else { return -1; }
		return m_nseek;
	}
}
