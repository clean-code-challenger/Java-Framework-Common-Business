package common.extern.olena.model.facade;

import java.util.Vector;

import common.extern.olena.model.dataaccess.daFatherModel;
import common.extern.olena.model.facade.mvc.base.IFa_Model;
import common.extern.olena.model.rule.middleFatherModel;
/**
 * @author Olena.Zagreba in Truegardener TEAM
 * @version ModelWeb 1.0, 2011. 08. 21 PM 1:52:59
 * @version ModelWeb 1.0
 */
public class facadeFatherModel
{
	protected static middleFatherModel m_middleServices = null;
	protected static daFatherModel m_daServices = null;
	private static Vector<IFa_Model> m_arrFacades = null;
	
	public facadeFatherModel()
	{
		m_arrFacades = new Vector<IFa_Model>();
	}
	/**
	 * @param objRule
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2011. 08. 21 PM 1:52:59
	 * @since ModelWeb 1.0
	 */
	protected int _addFacadeColumn(IFa_Model objFacade)
	{
		int result = -1;
		result = m_arrFacades.size();
		m_arrFacades.add(objFacade);
		return result;
	}
	/**
	 * @param nIndex
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2011. 08. 21 PM 2:08:19
	 * @since ModelWeb 1.0
	 */
	protected static IFa_Model _getFacadeItem(int nIndex)
	{
		if(nIndex < 0 || nIndex >= m_arrFacades.size())
			return null;
		return m_arrFacades.get(nIndex);
	}
	/**
	 * @return
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 08. 26 PM 5:52:32
	 * @since ModelWeb 1.0
	 */
	protected int _getSize()
	{
		return m_arrFacades.size();
	}
}
