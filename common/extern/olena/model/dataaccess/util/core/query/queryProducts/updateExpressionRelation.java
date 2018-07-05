package common.extern.olena.model.dataaccess.util.core.query.queryProducts;

import java.util.Vector;

import common.extern.olena.model.dataaccess.util.core.query.sqlQuery_Model;
import common.extern.olena.model.dataaccess.util.core.query.base.relationBase;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.sqlRelationItems.updateSetItem;


public class updateExpressionRelation extends relationBase 
{
	public updateExpressionRelation() 
	{
		super();
		m_oRelations = new Vector<updateSetItem>();
	}
	private Vector<updateSetItem> m_oRelations = null;
	private int 				m_nseek = -1;
	public void addRelation(int field, Object object) {
		m_oRelations.add(new updateSetItem(field, object));
	}
	public void addRelation(String expression) {
		m_oRelations.add(new updateSetItem(expression, null));
	}
	public void addRelation(String expression, Object object) {
		m_oRelations.add(new updateSetItem(expression, object));
	}
	public void addRelation(int updateType, int field, Object object) {
		if(updateType > 0) {
			updateType*=-1;
		}
		updateSetItem objectField = new updateSetItem(field, object);
		m_oRelations.add(new updateSetItem(updateType, objectField));
	}
	public int getSeekInteger() {
		int result = -1;
		if(m_oRelations!=null) { result = m_oRelations.get(m_nseek).getFieldIndex(); }
		return result; 
	}
	public String getSeekExpression() {
		String result = null;
		if(m_oRelations!=null) { result = m_oRelations.get(m_nseek).getExpression(); }
		return result; 
	}
	public int getSeekSeekInteger() {
		int result = -1;
		if(m_oRelations!=null) {
			int nSeekInteger = getSeekInteger();
			if(nSeekInteger!=-1) {
				switch(nSeekInteger) {
					case sqlQuery_Model.EXPRESSION_UPDATE_PLUSNUM: {
						updateSetItem oRelation = (updateSetItem)m_oRelations.get(m_nseek).getObject();
						if(oRelation!=null) { result = oRelation.getFieldIndex(); }
						break;
					}
					case sqlQuery_Model.EXPRESSION_UPDATE_NULL: {
						updateSetItem oRelation = (updateSetItem)m_oRelations.get(m_nseek).getObject();
						if(oRelation!=null) { result = oRelation.getFieldIndex(); }
						break;
					}
					case sqlQuery_Model.EXPRESSION_UPDATE_PLUSFORWSTR: {
						updateSetItem oRelation = (updateSetItem)m_oRelations.get(m_nseek).getObject();
						if(oRelation!=null) { result = oRelation.getFieldIndex(); }
						break;
					}
					case sqlQuery_Model.EXPRESSION_UPDATE_PLUSBACKSTR: {
						updateSetItem oRelation = (updateSetItem)m_oRelations.get(m_nseek).getObject();
						if(oRelation!=null) { result = oRelation.getFieldIndex(); }
						break;
					}
					default: {
						result = m_oRelations.get(m_nseek).getFieldIndex();
						break;
					}
				}
			}
		}
		return result; 
	}
	public Object getSeekObject() {
		Object result = null;
		if(m_oRelations!=null) {
			int nSeekInteger = getSeekInteger();
			switch(nSeekInteger) {
				case sqlQuery_Model.EXPRESSION_UPDATE_PLUSNUM: {
					updateSetItem oRelation = (updateSetItem)m_oRelations.get(m_nseek).getObject();
					if(oRelation!=null) { result = oRelation.getObject(); }
					break;
				}
				case sqlQuery_Model.EXPRESSION_UPDATE_NULL: {
					result = null;
					break;
				}
				case sqlQuery_Model.EXPRESSION_UPDATE_PLUSFORWSTR: {
					updateSetItem oRelation = (updateSetItem)m_oRelations.get(m_nseek).getObject();
					if(oRelation!=null) { result = oRelation.getObject(); }
					break;
				}
				case sqlQuery_Model.EXPRESSION_UPDATE_PLUSBACKSTR: {
					updateSetItem oRelation = (updateSetItem)m_oRelations.get(m_nseek).getObject();
					if(oRelation!=null) { result = oRelation.getObject(); }
					break;
				}
				default: {
					result = m_oRelations.get(m_nseek).getObject();
					break;
				}
			}
		}
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
		StringBuilder strSet = new StringBuilder("");
		if(m_oRelations!=null) {
			int nSize = m_oRelations.size();
			updateSetItem setItem = null;
			for(int i = 0; i < nSize; i++) {
				setItem = m_oRelations.get(i);
				int nFieldIndex =  setItem.getFieldIndex();
				if(nFieldIndex != -1) {
					switch(nFieldIndex) {
					case sqlQuery_Model.EXPRESSION_UPDATE_PLUSNUM: {
						updateSetItem oRelation = (updateSetItem)setItem.getObject();
						if(oRelation.getFieldIndex()!=-1) {
							if((strSet.toString()).equals("")) {
								strSet.append(queryModel.getPlusNumQuery(queryModel.getItemModel().getFieldLabel(oRelation.getFieldIndex())));	
							}
							else {
								strSet.append(queryModel.getPlusNumQuery(queryModel.getItemModel().getFieldLabel(oRelation.getFieldIndex())));
								strSet.append(queryModel.getCommaQuery(strSet.toString()));
							}
						}
						break;
					}
					case sqlQuery_Model.EXPRESSION_UPDATE_NULL: {
						updateSetItem oRelation = (updateSetItem)setItem.getObject();
						if(oRelation.getFieldIndex()!=-1) {
							if((strSet.toString()).equals("")) {
								strSet.append(queryModel.getPlusNumQuery(queryModel.getItemModel().getFieldLabel(oRelation.getFieldIndex())));	
							}
							else {
								strSet.append(queryModel.getNullQuery(queryModel.getItemModel().getFieldLabel(oRelation.getFieldIndex())));
								strSet.append(queryModel.getCommaQuery(strSet.toString()));
							}
						}
						break;
					}
					case sqlQuery_Model.EXPRESSION_UPDATE_PLUSFORWSTR: {
						updateSetItem oRelation = (updateSetItem)setItem.getObject();
						if(oRelation.getFieldIndex()!=-1) {
							if((strSet.toString()).equals("")) {
								strSet.append(queryModel.getPlusStringForward(queryModel.getItemModel().getFieldLabel(oRelation.getFieldIndex())));	
							}
							else {
								strSet.append(queryModel.getPlusStringForward(queryModel.getItemModel().getFieldLabel(oRelation.getFieldIndex())));
								strSet.append(queryModel.getCommaQuery(strSet.toString()));
							}
						}
						break;
					}
					case sqlQuery_Model.EXPRESSION_UPDATE_PLUSBACKSTR: {
						updateSetItem oRelation = (updateSetItem)setItem.getObject();
						if(oRelation.getFieldIndex()!=-1) {
							if((strSet.toString()).equals("")) {
								strSet.append(queryModel.getPlusStringBack(queryModel.getItemModel().getFieldLabel(oRelation.getFieldIndex())));	
							}
							else {
								strSet.append(queryModel.getPlusStringBack(queryModel.getItemModel().getFieldLabel(oRelation.getFieldIndex())));
								strSet.append(queryModel.getCommaQuery(strSet.toString()));
							}
						}
						break;
					}
					default: {
						if((strSet.toString()).equals("")) {
							strSet.append(queryModel.getEquQuery(queryModel.getItemModel().getFieldLabel(nFieldIndex)));	
						}
						else {
							strSet.append(queryModel.getCommaQuery(queryModel.getEquQuery(queryModel.getItemModel().getFieldLabel(nFieldIndex))));
						}
						break;
					}
					}
				}
				else {
					String strUpdateExpression =  setItem.getExpression();
					if(strUpdateExpression!=null) {
						if((strSet.toString()).equals("")) {
							strSet.append(strUpdateExpression);
						}
						else {
							strSet.append(queryModel.getCommaQuery(strUpdateExpression));
						}
					}
				}
			}
		}
		if(!(strSet.toString()).equals("")) { result.append(queryModel.getSetQuery(strSet.toString())); }
		return result.toString();
	}
}
