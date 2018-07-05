package common.extern.olena.model.dataaccess;

import java.util.Vector;

import common.extern.olena.model.dataaccess.mvc.base.da_Model;

/**
 * @author Olena.Zagreba in Truegardener TEAM
 * @version ModelWeb 1.0, 2011. 12. 25 PM 5:28:36
 * @since ModelWeb 1.0
 */
public class daFatherModel
{
	private Vector<da_Model> m_arrDAs = null;
	public daFatherModel() throws Exception 	{
		m_arrDAs = new Vector<da_Model>();
	}
	/**
	 * @param objDA
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2011. 08. 21 PM 1:50:53
	 * @since ModelWeb 1.0
	 */
	public int _addDAColumn(da_Model objDA) {
		int result = -1;
		result = m_arrDAs.size();
		m_arrDAs.add(objDA);
		return result;
	}
	/**
	 * @param nIndex
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2011. 08. 21 PM 2:04:24
	 * @since ModelWeb 1.0
	 */
	public da_Model _getDaItem(int nIndex) {
		if(nIndex < 0 || nIndex >= m_arrDAs.size())
			return null;
		return m_arrDAs.get(nIndex);
	}
	/**
	 * @return
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 08. 26 PM 5:52:32
	 * @since ModelWeb 1.0
	 */
	public int _getSize() { return m_arrDAs.size(); }
	/**
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 13 PM 1:22:01
	 * @since ModelWeb 1.0
	 */
	public void _DeleteTables() throws Throwable {
		int nIndex;
		int nSize = _getSize();
		for(nIndex = 0; nIndex < nSize; nIndex++) {
			try{
				_getDaItem(nIndex).drop_Table();
			}
			catch(Throwable ex) { }
		}
	}
	/**
	 *
	 * @author Olena.Zagreba
	 * @version ModelWeb 1.0, 2012. 09. 13 PM 1:22:03
	 * @since ModelWeb 1.0
	 */
	public void _CreateTables() throws Throwable {
		int nIndex;
		int nSize = _getSize();
		for(nIndex = 0; nIndex < nSize; nIndex++) {
			try {
				_getDaItem(nIndex).create_Table();
			}
			catch(Throwable ex) { }
		}
	}
}
