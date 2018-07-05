package common.extern.olena.model.dataaccess.mvc.apply;

import common.extern.olena.model.dataaccess.mvc.base.IItem_Model;
import common.extern.olena.model.dataaccess.mvc.base.item_Model;
import common.extern.olena.model.dataaccess.mvc.base.tbl_Model;
import common.extern.olena.model.dataaccess.util.core.query.queryProducts.fieldExpressionRelation;

/**
 * @author Olena.Zagreba in Truegardener TEAM
 *
 */
public class xTableModel extends tbl_Model implements IItem_Model
{
	private xImpItemModel m_itemModel = null;
	
	public xTableModel()
	{
		super();
		m_itemModel = new xImpItemModel(this);
	}
	
	public item_Model getItemModel() {
		return m_itemModel;
	}
	public Object getAvaiable(int nFieldIndex) {
		return m_itemModel.getAvaiable(nFieldIndex);
	}
	public String getAvaiableXml(int nFieldIndex) {
		return m_itemModel.getAvaiableXml(nFieldIndex);
	}
	public String getBeanNode(int nIndex) {
		return m_itemModel.getBeanNode(nIndex);
	}
	public int getFieldIndexFromBean(String strBean) {
		return m_itemModel.getFieldIndexFromBean(strBean);
	}
	public int getFieldIndexFromField(String strField) {
		return m_itemModel.getFieldIndexFromField(strField);
	}
	public int getFieldIndexFromXmlNode(String strXmlNode) {
		return m_itemModel.getFieldIndexFromXmlNode(strXmlNode);
	}
	public fieldExpressionRelation getFieldInfo(int nIndex) {
		return m_itemModel.getFieldInfo(nIndex);
	}
	public String getFieldLabel(int nIndex) {
		return m_itemModel.getFieldLabel(nIndex);
	}
	public int getFieldType(int nIndex) {
		return m_itemModel.getFieldType(nIndex);
	}
	public int getFieldLength() 
	{
		return m_itemModel.getFieldLength();
	}
	public int getKind() {
		return m_itemModel.getKind();
	}
	public tbl_Model getTableModel() {
		return m_itemModel.getTableModel();
	}
	public String getTableName() {
		return m_itemModel.getTableName();
	}
	public String getXmlNode(int nIndex) {
		return m_itemModel.getXmlNode(nIndex);
	}
	public void init(tbl_Model tbl_Model) {
		m_itemModel.init(tbl_Model);
	}
	public boolean isAvaiable(int nFieldIndex) {
		return m_itemModel.isAvaiable(nFieldIndex);
	}
	public void setAvaiable(int nFieldIndex, Object result) {
		m_itemModel.setAvaiable(nFieldIndex, result);
	}
	public void setAvaiableXml(String strNode, String result) {
		m_itemModel.setAvaiableXml(strNode, result);
	}
	public void setKind(int kind) {
		m_itemModel.setKind(kind);
	}
}
