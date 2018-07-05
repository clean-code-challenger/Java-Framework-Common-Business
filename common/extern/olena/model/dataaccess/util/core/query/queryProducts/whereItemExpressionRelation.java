package common.extern.olena.model.dataaccess.util.core.query.queryProducts;

import common.extern.olena.model.dataaccess.mvc.base.IItem_Model;
import common.extern.olena.model.dataaccess.util.core.query.sqlQuery_Model;
import common.extern.olena.model.dataaccess.util.core.query.base.relationBase;
public class whereItemExpressionRelation extends relationBase 
{
	public whereItemExpressionRelation(IItem_Model info) 
	{
		super();
		m_oRelations = info;
	}
	private IItem_Model m_oRelations = null;
	public IItem_Model getRelations() { return m_oRelations; }
	/**
	 * @param queryModel
	 * @param nRelationKind
	 * @param nExpKind
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, PM 6:30:41
	 * @since ModelWeb 1.0
	 */
	public String toSQLString(sqlQuery_Model queryModel, int nRelationKind, int nExpKind) {
		StringBuilder result = new StringBuilder("");
		if(m_oRelations!=null) {
			int nLength = queryModel.getItemModel().getTableModel().getLength();
			for(int i = 0; i < nLength; i++) {
				if(m_oRelations.isAvaiable(i)) {
					if("".equals(result.toString())) { }
					else {
						if(nRelationKind==sqlQuery_Model.RELATION_AND) {
							result.append(queryModel.getSpace() + queryModel.getAnd());
						}
						else if(nRelationKind==sqlQuery_Model.RELATION_OR) {
							result.append(queryModel.getSpace() + queryModel.getOr());
						}
					}
					if(nExpKind==sqlQuery_Model.EXPRESSION_EQU) { result.append(queryModel.getSpace() + queryModel.getItemModel().getFieldLabel(i) + queryModel.getEqu() + queryModel.getQus()); }
					else if(nExpKind==sqlQuery_Model.EXPRESSION_LIKE) 				{
						result.append(queryModel.getSpace() + queryModel.getItemModel().getFieldLabel(i) + queryModel.getSpace() + queryModel.getLike() + queryModel.getSpace() + queryModel.getQus());
					}
				}
			}
		}
		return result.toString();
	}
}

