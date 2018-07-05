package common.extern.olena.model.facade.mvc.base;

import common.extern.olena.model.dataaccess.daFatherModel;
import common.extern.olena.model.facade.facadeFatherModel;
import common.extern.olena.model.rule.middleFatherModel;

public interface IFa_Model {
	/**
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011/12/30 PM 4:33:31
	 * @since ModelWeb 1.0
	 */
	public facadeFatherModel getFacadeModel();
	/**
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011/12/30 PM 4:33:31
	 * @since ModelWeb 1.0
	 */
	public middleFatherModel getMiddleModel();
	/**
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2012. 01. 02 PM 1:45:05
	 * @since ModelWeb 1.0
	 */
	public daFatherModel getDAModel();
}
