package common.extern.olena.model.dataaccess.util.core.query.queryProducts;

import java.util.Vector;

import common.extern.olena.model.dataaccess.util.core.query.sqlQuery_Model;
import common.extern.olena.model.dataaccess.util.core.query.base.relationBase;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.sqlRelationItems.setNullItem;


public class setNullExpressionRelation extends relationBase 
{
	public setNullExpressionRelation() 
	{
		super();
		m_oRelations = new Vector<setNullItem>();
	}
	private Vector<setNullItem> m_oRelations = null;
	private int 				m_nseek = -1;
	public void addRelation(int field) { m_oRelations.add(new setNullItem(field)); }
	public int getSeekInteger() { 
		int result = -1; if(m_oRelations!=null) { result = m_oRelations.get(m_nseek).getFieldIndex(); }
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
	public String toSQLString(sqlQuery_Model queryModel) {
		StringBuilder result = new StringBuilder("");
		if(m_oRelations!=null) {
			StringBuilder strSet = new StringBuilder("");
			int nSize = m_oRelations.size();
			setNullItem setItem = null;
			for(int i = 0; i < nSize; i++) {
				setItem = m_oRelations.get(i);
				if(setItem.getFieldIndex()!=-1) {
					if((strSet.toString()).equals("")) {strSet.append(queryModel.getNullQuery( queryModel.getItemModel().getFieldLabel(setItem.getFieldIndex()))); }
					else { strSet.append(queryModel.getCommaQuery(queryModel.getNullQuery(queryModel.getItemModel().getFieldLabel(setItem.getFieldIndex())))); }
				}					
			}
			if(!(strSet.toString()).equals("")) { result.append(queryModel.getSetQuery(strSet.toString())); }
		}
		return result.toString();
	}
}
