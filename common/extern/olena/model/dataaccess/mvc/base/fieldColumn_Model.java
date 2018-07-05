package common.extern.olena.model.dataaccess.mvc.base;

import common.extern.olena.model.dataaccess.util.core.query.queryProducts.fieldExpressionRelation;

/**
 * 
 * @author Olena.Zagreba in Truegardener TEAM
 * @since 2011. 12. 24
 * @version ModelWeb 1.0
 */
public class fieldColumn_Model
{
	/**Field info*/
	private String m_strFieldLabel = null;
	/**Field Type*/
	private int m_nFieldType = -1;
	/***Table's bean field*/
	private String m_strBeanField = null;
	/***Table's xml field*/
	private String m_strXmlField = null;
	private fieldExpressionRelation m_fieldInfo = null;
	
	public int getFieldType() { return m_nFieldType; }
	public void setFieldType(int fieldType) { m_nFieldType = fieldType; }
	
	public String getFieldLabel() { return m_strFieldLabel; }
	public void setFieldLabel(String fieldLabel) { m_strFieldLabel = fieldLabel; }
	
	public String getBeanField() { return m_strBeanField; }
	public void setBeanField(String strBeanNode) { m_strBeanField = strBeanNode; }
	
	public String getXmlField() { return m_strXmlField; }
	public void setXmlField(String xmlField) { m_strXmlField = xmlField; }
	
	public fieldExpressionRelation getFieldInfo() { return m_fieldInfo; }
	public void setFieldInfo(fieldExpressionRelation info) { m_fieldInfo = info; }
}