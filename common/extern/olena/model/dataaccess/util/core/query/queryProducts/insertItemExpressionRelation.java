package common.extern.olena.model.dataaccess.util.core.query.queryProducts;

import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

import common.extern.olena.model.dataaccess.mvc.base.IItem_Model;
import common.extern.olena.model.dataaccess.util.core.query.sqlQuery_Model;
import common.extern.olena.model.dataaccess.util.core.query.base.relationBase;

public class insertItemExpressionRelation extends relationBase 
{
	public insertItemExpressionRelation(IItem_Model info) 
	{
		super();
		m_oRelations = info;
	}
	private IItem_Model m_oRelations = null;
	public IItem_Model getRelations() { return m_oRelations; }
	public String toSQLString(sqlQuery_Model queryModel) {
		StringBuilder result = new StringBuilder("");
		if(m_oRelations!=null) {
			int nCount = 0;
			StringBuilder strFields = new StringBuilder("");
			StringBuilder strValues = new StringBuilder("");
			IntegerArray arrIndex = new IntegerArray();
			IItem_Model itemModel = queryModel.getItemModel();
			int nLength = itemModel.getTableModel().getLength();
			for(int i = 0; i < nLength; i++) {
				if(m_oRelations.isAvaiable(i)) {
					if((strFields.toString()).equals("")) {	strFields.append(itemModel.getFieldLabel(i)); }
					else { 						strFields.append(queryModel.getCommaQuery(itemModel.getFieldLabel(i))); }
					
					if((strValues.toString()).equals("")) {	strValues.append(queryModel.getQus()); }
					else { 						strValues.append(queryModel.getCommaQuery(queryModel.getQus())); }
					arrIndex.add(i);
					nCount++;
				}
			}
			result.append(queryModel.getFieldQuery(strFields.toString()));
			result.append(queryModel.getValueQuery(strValues.toString()));
		}
		return result.toString();
	}
}
