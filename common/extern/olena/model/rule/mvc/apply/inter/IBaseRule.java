package common.extern.olena.model.rule.mvc.apply.inter;

import java.util.HashMap;

import org.w3c.dom.Node;

import common.extern.olena.model.dataaccess.mvc.base.IItem_Model;
import common.extern.olena.model.infos.dataTransport.key_WebDataProtocol_Model;
import common.extern.olena.model.rule.mvc.base.IMiddle_Model;

public interface IBaseRule extends IMiddle_Model{
	public long insertData(Object data, Object mappingOption) throws Throwable;
	public long insertXml(String strXml, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable;
	public long insertXml(Node nodeXml, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable;
	public long insertJSon(String strJSon, Object mappingOption) throws Throwable;
	public long insertHashMap(HashMap hashInfo, Object mappingOption) throws Throwable;
	public long insertItem(IItem_Model item) throws Throwable;
	public boolean updateData(Object data, Object mappingOption) throws Throwable;
	public boolean updateXml(String strXml, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable;
	public boolean updateXml(Node nodeXml, key_WebDataProtocol_Model dataProtocol, Object mappingOption) throws Throwable;
	public boolean updateJSon(String data, Object mappingOption) throws Throwable;
	public boolean updateHashMap(HashMap hashInfo, Object mappingOption) throws Throwable;
	public boolean updateItem(IItem_Model item) throws Throwable;
	public boolean deleteData(long nDataNumber) throws Throwable;
	public boolean CheckValidate(IItem_Model item) throws Throwable;
	public boolean onCheckValidate(IItem_Model item) throws Throwable;
	public long onInsertItem(IItem_Model item) throws Throwable;
	public boolean onUpdateItem(IItem_Model item) throws Throwable;
	public boolean onDeleteItem(long nNo) throws Throwable;
}
