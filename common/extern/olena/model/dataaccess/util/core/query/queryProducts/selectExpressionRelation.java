package common.extern.olena.model.dataaccess.util.core.query.queryProducts;

import java.util.Vector;

import common.extern.olena.model.dataaccess.util.core.query.sqlQuery_Model;
import common.extern.olena.model.dataaccess.util.core.query.base.relationBase;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.sqlRelationItems.selectSetItem;

public class selectExpressionRelation extends relationBase 
{
	public selectExpressionRelation() 
	{
		super();
		m_oRelations = new Vector<selectSetItem>();
	}
	private Vector<selectSetItem> m_oRelations = null;
	private int 				m_nseek = -1;
	public void addRelation(int field) { m_oRelations.add(new selectSetItem(field)); }
	public void addRelation(String strFieldExpression) { m_oRelations.add(new selectSetItem(strFieldExpression)); }
	public Vector<selectSetItem> getRelations() { return m_oRelations; }
	public int getSeekInteger() {
		int result = -1;
		if(m_oRelations!=null) { result = m_oRelations.get(m_nseek).getFieldIndex(); }
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
		if(m_nseek < m_oRelations.size()) { m_nseek++; }
		else { return -1; }
		return m_nseek;
	}
	
	public String toSQLString(sqlQuery_Model queryModel) {
		StringBuilder result = new StringBuilder("");
		if(m_oRelations!=null) {
			if(m_oRelations.size() > 0) {
				int i;
				int nSize = m_oRelations.size();
				selectSetItem setItem = null;
				for(i = 0; i < nSize; i++) {
					setItem = m_oRelations.get(i);
					if(setItem.getFieldIndex()!=-1) {
						if((result.toString()).equals("")) {
							result.append(queryModel.getItemModel().getFieldLabel(setItem.getFieldIndex())); 
						}						
						else {
							result.append(queryModel.getCommaQuery(queryModel.getItemModel().getFieldLabel(setItem.getFieldIndex())));	
						}
					}
					else {
						if((result.toString()).equals("")) {
							result.append(setItem.getFieldExpression()); 
						}						
						else {
							result.append(queryModel.getCommaQuery(setItem.getFieldExpression()));	
						}
					}
				}
			}
			else {
				result.append("*");
			}
		}
		else { result.append("*"); }
		return result.toString();
	}
}
