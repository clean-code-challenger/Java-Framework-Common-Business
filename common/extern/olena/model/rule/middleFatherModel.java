package common.extern.olena.model.rule;

import java.util.Vector;

import common.extern.olena.model.dataaccess.daFatherModel;
import common.extern.olena.model.rule.mvc.base.IMiddle_Model;

/**
 * @author Olena.Zagreba in Truegardener TEAM
 * @since 2011. 12. 24
 * @version ModelWeb 1.0
 */
public class middleFatherModel
{
	protected daFatherModel m_daServices = null;
	private Vector<IMiddle_Model> m_arrMiddles = null;;

	protected middleFatherModel(daFatherModel service) throws Exception {
		m_daServices = service;
		m_arrMiddles = new Vector<IMiddle_Model>();
	}
	/**
	 * @param objRule
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2011. 08. 21 PM 1:52:59
	 * @since ModelWeb 1.0
	 */
	public int _addMiddleColumn(IMiddle_Model objRule) {
		int result = -1;
		result = m_arrMiddles.size();
		m_arrMiddles.add(objRule);
		return result;
	}
	/**
	 * @param nIndex
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2011. 08. 21 PM 2:04:23
	 * @since ModelWeb 1.0
	 */
	public IMiddle_Model _getMiddleItem(int nIndex) {
		if(nIndex < 0 || nIndex >= m_arrMiddles.size())
			return null;
		return m_arrMiddles.get(nIndex); 
	}
	/**
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 08. 26 PM 5:52:32
	 * @since ModelWeb 1.0
	 */
	public int _getSize() { return m_arrMiddles.size(); }
}
