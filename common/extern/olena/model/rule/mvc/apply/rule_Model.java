package common.extern.olena.model.rule.mvc.apply;

import common.extern.olena.model.dataaccess.daFatherModel;
import common.extern.olena.model.dataaccess.mvc.base.IItem_Model;
import common.extern.olena.model.rule.middleFatherModel;
import common.extern.olena.model.rule.mvc.base.IMiddle_Model;

/**
 *  
 * @author Olena.Zagreba
 * @version  2013-03-29 10:14:11
 */
public class rule_Model implements IMiddle_Model
{
	public rule_Model(middleFatherModel parentRule, daFatherModel parentDA, IItem_Model item) {
		m_ruleParent = parentRule;
		m_daParent = parentDA;
		m_ItemModel = item;
	}	
	private middleFatherModel m_ruleParent = null;
	private daFatherModel m_daParent = null;
	/***ruleBean*/
	private IItem_Model m_ItemModel = null;
	public IItem_Model getItemModel() { return m_ItemModel; }
	public void setItemModel(IItem_Model itemModel) { m_ItemModel = itemModel; }
	/**
	 * @return
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011/12/30 PM 4:33:31
	 * @since ModelWeb 1.0
	 */
	public middleFatherModel getMiddleModel() { return m_ruleParent;  }
	/**
	 * @return
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011/12/30 PM 4:33:13
	 * @since ModelWeb 1.0
	 */
	public daFatherModel getDAModel() { return m_daParent; }
}
