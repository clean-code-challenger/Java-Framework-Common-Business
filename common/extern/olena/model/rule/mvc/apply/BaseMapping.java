package common.extern.olena.model.rule.mvc.apply;

import common.extern.olena.model.dataaccess.daFatherModel;
import common.extern.olena.model.dataaccess.mvc.base.IItem_Model;
import common.extern.olena.model.rule.middleFatherModel;
import common.extern.olena.model.rule.mvc.base.IMiddle_Model;

/**
 * 기초매핑정의 클라스
 * 	이 클라스는 거시층과 자료기층의 중간층으로서 
 * mapping규칙 클라스의 속성을 이어받아 
 * 대면부와 자료기지사이의 자료교환을 진행합니다.
 */
public class BaseMapping extends mapping_Model implements IMiddle_Model{

	/**부모mapping층*/
	private middleFatherModel m_middleParent = null;
	/**부모dataaccess층*/
	private daFatherModel m_daParent = null;

	public BaseMapping(middleFatherModel parentMiddle, daFatherModel parentDA) throws Exception {
		super();
		m_middleParent = parentMiddle;
		m_daParent = parentDA;
	}
	
	public BaseMapping(middleFatherModel parentMiddle, daFatherModel parentDA, IItem_Model itemModel) throws Exception {
		super(itemModel);
		m_middleParent = parentMiddle;
		m_daParent = parentDA;
	}
	
	public BaseMapping(middleFatherModel parentMiddle, daFatherModel parentDA, IItem_Model itemModel, String strXmlRoot, String strXmlNode, String strNodeCount, String strXmlException) throws Exception {
		super(itemModel, strXmlRoot, strXmlNode, strNodeCount, strXmlException);
		m_middleParent = parentMiddle;
		m_daParent = parentDA;
	}
	public daFatherModel getDAModel() { return m_daParent; }
	public middleFatherModel getMiddleModel() { return m_middleParent; }
}
