package common.extern.olena.model.dataaccess.util.core.query.queryProducts;

import java.util.Iterator;
import java.util.Vector;

import common.extern.olena.model.dataaccess.util.core.query.sqlQuery_Model;
import common.extern.olena.model.dataaccess.util.core.query.base.relationBase;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.sqlRelationItems.whereRelationItem;

public class whereExpressionRelation extends relationBase 
{
	public whereExpressionRelation() 
	{
		super();
		m_oRelations = new Vector<relationBase>();
	}
	public whereExpressionRelation(int relationPrev) 
	{
		super();
		m_oRelations = new Vector<relationBase>();
		m_nRelationPrev = relationPrev;
	}
	private Vector<relationBase> 	m_oRelations = null;
	private Iterator<relationBase>	m_oRelationsIterator = null;
	private int m_nRelationPrev = -1;
	private whereExpressionRelation	m_thisParent = null;
	private relationBase			m_seekObject = null;
	private whereRelationItem 		m_pointRelation = null;
	private whereExpressionRelation	m_pointParent = null;
	
	public int getRelationPrev() { return m_nRelationPrev;}
	public void setRelationPrev(int relationPrev) { m_nRelationPrev = relationPrev; }
	
	public void addExpressionRelation(whereExpressionRelation itemExpressionRelation) {
		m_oRelations.add(itemExpressionRelation);
		itemExpressionRelation.setThisParent(this);
	}
	public void addRelation(int relationPrev, int nFields, int nExpression, Object setObject) {
		m_oRelations.add(new whereRelationItem(relationPrev, nFields, nExpression, setObject));
	}
	public void addRelation(int relationPrev, String strFieldExpression, int nExpression, Object setObject) {
		m_oRelations.add(new whereRelationItem(relationPrev, strFieldExpression, nExpression, setObject));
	}
	public void addRelation(int nFields, Object setObject) {
		m_oRelations.add(new whereRelationItem(-1, nFields, -1, setObject));
	}
	public void addRelation(String strFieldExpression, Object setObject) {
		m_oRelations.add(new whereRelationItem(-1, strFieldExpression, -1, setObject));
	}
	public void addRelation(String strFieldExpression) {
		m_oRelations.add(new whereRelationItem(-1, strFieldExpression, -1, null));
	}
	/**
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 18 AM 10:22:07
	 * @since ModelWeb 1.0
	 */
	public void setEquWhereRelation(boolean bAndOr) throws Throwable {
		try {
			Vector<relationBase> relations = getRelations();
			if(relations==null) return;
			int nSize = relations.size();
			for(int i = 0; i < nSize; i++) {
				relationBase relationInfo = relations.get(i);
				if(relationInfo instanceof whereRelationItem) {
					if(bAndOr)  {
						((whereRelationItem)relationInfo).setRelationPrev(sqlQuery_Model.RELATION_AND);
					}
					else {
						((whereRelationItem)relationInfo).setRelationPrev(sqlQuery_Model.RELATION_OR);
					}
					((whereRelationItem)relationInfo).setExpression(sqlQuery_Model.EXPRESSION_EQU);
				}
				else if(relationInfo instanceof whereExpressionRelation) {
					if(bAndOr)  {
						((whereExpressionRelation)relationInfo).setRelationPrev(sqlQuery_Model.RELATION_AND);
					}
					else {
						((whereExpressionRelation)relationInfo).setRelationPrev(sqlQuery_Model.RELATION_OR);
					}
				}
			}
		}
		catch(Throwable ex) { throw ex; }
	}
	/**
	 * @param bAndOr
	 * @throws Throwable
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 18 AM 10:22:05
	 * @since ModelWeb 1.0
	 */
	public void setLikeWhereRelation(boolean bAndOr) throws Throwable {
		try {
			Vector<relationBase> relations = getRelations();
			if(relations==null) return;
			int nSize = relations.size();
			relationBase realtionInfo = null;
			for(int i = 0; i < nSize; i++) {
				realtionInfo = relations.get(i);
				if(realtionInfo instanceof whereRelationItem) {
					if(bAndOr) {
						((whereRelationItem)realtionInfo).setRelationPrev(sqlQuery_Model.RELATION_AND);
					}
					else {
						((whereRelationItem)realtionInfo).setRelationPrev(sqlQuery_Model.RELATION_OR);
					}
					((whereRelationItem)realtionInfo).setExpression(sqlQuery_Model.EXPRESSION_LIKE);
				}
				else if(realtionInfo instanceof whereExpressionRelation) {
					if(bAndOr) {
						((whereExpressionRelation)realtionInfo).setRelationPrev(sqlQuery_Model.RELATION_AND);
					}
					else {
						((whereExpressionRelation)realtionInfo).setRelationPrev(sqlQuery_Model.RELATION_OR);
					}
				}
			}
		}
		catch(Throwable ex) { throw ex; }
	}
	/**
	 * 
	 * @param parent
	 * 
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 19 AM 11:08:04
	 * @since ModelWeb 1.0
	 */
	private void setThisParent(whereExpressionRelation parent) { m_thisParent = parent; }
	public Vector<relationBase> getRelations() { return m_oRelations; }
	public Object getPointObject() {
		Object result = null;
		if(m_pointRelation!=null) { result = ((whereRelationItem)m_pointRelation).getSetObject(); }
		return result; 
	}
	public int getPointInteger()  {
		int result = -1;
		if(m_pointRelation!=null) { result = ((whereRelationItem)m_pointRelation).getFieldIndex(); }
		return result; 
	}
	/**
	 * 
	 * @return
	 * 
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 13 PM 1:57:58
	 * @since ModelWeb 1.0
	 */
	private whereRelationItem getPoint() {
		whereRelationItem result = null;
		if(m_seekObject!=null) {
			if(m_seekObject instanceof whereRelationItem) {
				result = (whereRelationItem)m_seekObject;
			}
			else if(m_seekObject instanceof whereExpressionRelation) {
				result = ((whereExpressionRelation)m_seekObject).getPoint();
			}
		}
		return result;
	}
	/**
	 * 
	 * @return
	 * 
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 13 PM 1:58:00
	 * @since ModelWeb 1.0
	 */
	private whereExpressionRelation getPointParent() {
		whereExpressionRelation result = null;
		if(m_seekObject!=null) {
			if(m_seekObject instanceof whereRelationItem) { return this; }
			else if(m_seekObject instanceof whereExpressionRelation) {
				result = ((whereExpressionRelation)m_seekObject).getPointParent();
			}
		}
		return result;
	}
	/**
	 * 
	 * @return
	 * 
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 17 AM 11:14:24
	 * @since ModelWeb 1.0
	 */
	public int moveFirst() {
		int result = -1;
		m_oRelationsIterator = m_oRelations.iterator();
		if(m_oRelationsIterator.hasNext()) {
			Object first = m_oRelationsIterator.next();
			if(first!=null) {
				if(first instanceof whereRelationItem) {
					m_pointRelation = (whereRelationItem)first;
					m_pointParent = this;
					result = 1;
				}
				else if(first instanceof whereExpressionRelation) {
					result = ((whereExpressionRelation)first).goFirst();
					if(result != -1) {
						m_pointRelation = ((whereExpressionRelation)first).getPoint();
						m_pointParent = ((whereExpressionRelation)first).getPointParent();
					}
				}	
			}
		}
		return result;
	}
	/**
	 *	첫위치로 가기(내부)
	 * 
	 * @return
	 * 
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 13 PM 1:59:00
	 * @since ModelWeb 1.0
	 */
	private int goFirst() {
		int result = -1;
		m_oRelationsIterator = m_oRelations.iterator();
		if(m_oRelationsIterator!=null) {
			relationBase first = m_oRelationsIterator.next();
			if(first!=null) {
				m_seekObject = first;
				if(first instanceof whereExpressionRelation) {
					result = ((whereExpressionRelation)first).goFirst();
				}
				else { result = 1; }
			}
		}
		return result;
	}
	/**
	 * 다음위치로 가기(외부)
	 * 
	 * @param relationCurrent
	 * @return
	 * 
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 01. 14 PM 12:43:48
	 * @since ModelWeb 1.0
	 */
	public int moveNext() {
		int result = -1; 
		if(m_pointParent!=null) {
			result = m_pointParent.goNextDown();
			if(result!=-1) {
				m_pointRelation = m_pointParent.getPoint();
				m_pointParent = m_pointParent.getPointParent();
				result = 1;
			}
			else {
				whereExpressionRelation resultSeek = m_pointParent.goNextUp();
				if(resultSeek!=null) {
					result = resultSeek.goNextDown();
					if(result!=-1) {
						m_pointRelation = resultSeek.getPoint();
						m_pointParent = resultSeek.getPointParent();
					}
				}
			}
		}
		return result;
	}
	/**
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 10. 30 PM 2:00:14
	 * @since ModelWeb 1.0
	 */
	private int goNextDown() {
		int result = -1;
		if(m_oRelationsIterator.hasNext()) {
			relationBase next = m_oRelationsIterator.next();
			if(next!=null) {
				m_seekObject = next;
				if(next instanceof whereRelationItem) { result = 1; }
				else if(next instanceof whereExpressionRelation) {
					result = ((whereExpressionRelation)next).goFirst();
					if(result != -1) {
						m_pointRelation = ((whereExpressionRelation)next).getPoint();
						m_pointParent = ((whereExpressionRelation)next).getPointParent();
					}
				}
			}
		}
		return result;
	}
	private whereExpressionRelation goNextUp() {
		whereExpressionRelation result = null;
		if(m_thisParent!=null) {
			if(!m_thisParent.m_oRelationsIterator.hasNext()) {
				result = m_thisParent.goNextUp();
			}
			else { result = m_thisParent; }
		}
		return result;
	}
	public String toSQLString(sqlQuery_Model queryModel, int nSeek) {
		StringBuilder result = new StringBuilder("");
		if(nSeek > 0) {
			result.append(queryModel.getSpace());
			if(m_nRelationPrev!=-1) {
				if(m_nRelationPrev==sqlQuery_Model.RELATION_AND) {
					result.append(queryModel.getSpace() + queryModel.getAnd()); 
				}
				else if(m_nRelationPrev==sqlQuery_Model.RELATION_OR) {
					result.append(queryModel.getSpace() + queryModel.getOr()); 
				}
			}
		}
		if(m_oRelations!=null && m_oRelations.size() > 0) {
			int i;
			result.append("(");
			int nSize = m_oRelations.size();
			for(i = 0; i < nSize; i++) {
				result.append(m_oRelations.get(i).toSQLString(queryModel, i));
			}
			result.append(")");
		}
		return result.toString();
	}
}
