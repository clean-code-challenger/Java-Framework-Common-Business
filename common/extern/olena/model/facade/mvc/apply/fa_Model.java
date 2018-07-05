package common.extern.olena.model.facade.mvc.apply;

import common.extern.olena.model.dataaccess.daFatherModel;
import common.extern.olena.model.facade.facadeFatherModel;
import common.extern.olena.model.facade.mvc.base.IFa_Model;
import common.extern.olena.model.rule.middleFatherModel;

/**
 * facade기본모델
 * 업무에서 필수적인 기능들을 담고 있다.
 * 즉 facade어미객체와 중간층어미객체, 자료기지접속층의 어미객체를 가지고 있다.
 * 
 * @author Olena.Zagreba in Truegardener TEAM
 * @version ModelWeb 1.0, 2011/12/30 下午 4:33:29
 * @since ModelWeb 1.0
 */
public class fa_Model implements IFa_Model
{
	protected fa_Model(facadeFatherModel parentFacade, middleFatherModel parentMiddle, daFatherModel parentDA){
		m_facadeParent = parentFacade;
		m_middleParent = parentMiddle;
		m_daParent = parentDA;
	}
	/**부모facade층*/
	private facadeFatherModel m_facadeParent = null;
	/**부모Rule층*/
	private middleFatherModel m_middleParent = null;
	/**부모dataaccess층*/
	private daFatherModel m_daParent = null;
	
	/**
	 * 부모Facade객체 얻기
	 * 
	 * @return
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011/12/30 下午 4:33:31
	 * @since ModelWeb 1.0
	 */
	public facadeFatherModel getFacadeModel() { return m_facadeParent; }
	/**
	 * 부모Rule객체 얻기
	 * 
	 * @return
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011/12/30 下午 4:33:31
	 * @since ModelWeb 1.0
	 */
	public middleFatherModel getMiddleModel() { return m_middleParent; }
	/**
	 * 부모DA객체 얻기
	 * 
	 * @return
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2012. 01. 02 PM 1:45:05
	 * @since ModelWeb 1.0
	 */
	public daFatherModel getDAModel() { return m_daParent; }
}