package common.extern.olena.model.dataaccess.util.core.query.queryProducts;

import java.util.Vector;

import common.extern.olena.model.dataaccess.util.core.query.sqlQuery_Model;
import common.extern.olena.model.dataaccess.util.core.query.base.relationBase;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.sqlRelationItems.orderSetItem;

public class orderExpressionRelation extends relationBase {
	public orderExpressionRelation() {
		super();
		m_oRelations = new Vector<orderSetItem>();
	}
	private Vector<orderSetItem> m_oRelations = null;
	private int 				m_nseek = -1;

	public void addRelation(int field, boolean object) {
		m_oRelations.add(new orderSetItem(field, object));
	}
	public void addRelation(String expression, boolean object) {
		m_oRelations.add(new orderSetItem(expression, object));
	}
	public int getSeekInteger() {
		int result = -1;
		if(m_oRelations!=null) { result = m_oRelations.get(m_nseek).getInteger(); }
		return result; 
	}
	public String getSeekExpression() {
		String result = null;
		if(m_oRelations!=null) { result = m_oRelations.get(m_nseek).getExpression(); }
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
			String strOrderExpression = null;
			int nSize = m_oRelations.size();
			for(int i = 0; i < nSize; i++) {
				if(m_oRelations.get(i).getInteger()>=0) {
					strOrderExpression = queryModel.getItemModel().getFieldLabel(m_oRelations.get(i).getInteger());
				}
				else {
					strOrderExpression = m_oRelations.get(i).getExpression();
				}
				if(strOrderExpression!=null) {
					if((result.toString()).equals("")) {
						if(m_oRelations.get(i).getBoolean()) {
							result.append(queryModel.getOrderByBiggerQuery(strOrderExpression));
						}
						else {
							result.append(queryModel.getOrderBySmallerQuery(strOrderExpression));
						}
					}
					else {
						if(m_oRelations.get(i).getBoolean()) {
							result.append(queryModel.getComma() + queryModel.getOrderByBiggerQuery(strOrderExpression));
						}
						else {
							result.append(queryModel.getComma() + queryModel.getOrderBySmallerQuery(strOrderExpression));
						}
					}
				}
			}
		}
		return result.toString();
	}
}
