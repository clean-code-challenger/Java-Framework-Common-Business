package common.extern.olena.model.dataaccess.util.core.query.queryProducts;

import common.extern.olena.model.dataaccess.mvc.base.IItem_Model;
import common.extern.olena.model.dataaccess.mvc.base.tbl_Model;
import common.extern.olena.model.dataaccess.util.core.query.sqlQuery_Model;
import common.extern.olena.model.dataaccess.util.core.query.base.relationBase;


public class updateItemExpressionRelation extends relationBase 
{
	public updateItemExpressionRelation(IItem_Model info) 
	{
		super();
		m_oRelations = info;
	}
	private IItem_Model m_oRelations = null;
	public IItem_Model getRelations() { return m_oRelations; }
	public String toSQLString(sqlQuery_Model queryModel) {
		StringBuilder result = new StringBuilder("");
		if(m_oRelations!=null) {
			StringBuilder strFields = new StringBuilder("");
			int nLength = queryModel.getItemModel().getTableModel().getLength();
			IItem_Model itemModel = null;
			for(int i = 0; i < nLength; i++) {
				itemModel = queryModel.getItemModel();
				if(m_oRelations.isAvaiable(i)) {
					if(i!=tbl_Model.field_id) {
						if((strFields.toString()).equals("")) {
							strFields.append(queryModel.getEquQuery(itemModel.getFieldLabel(i)));
						}
						else {
							strFields.append(queryModel.getCommaQuery(queryModel.getEquQuery(itemModel.getFieldLabel(i))));
						}
					}
				}					
			}
			result.append(queryModel.getSetQuery(strFields.toString()));
		}
		return result.toString();
	}
}
