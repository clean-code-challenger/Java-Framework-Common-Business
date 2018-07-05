package common.extern.olena.model.infos.dataTransport;

/**
 *
 * @author Olena.Zagreba in Truegardener TEAM
 * @version ModelWeb 1.0, 2011. 12. 25 PM 1:49:07
 * @since ModelWeb 1.0
 */
public class key_WebDataProtocol_Model
{
	public String key_root = "root";					
	public String key_node = "node";				
	public String key_nodeCount = "count";				
	public String key_hasException = "exception";			
	
	public String getRootKey() { return key_root; }
	public void setRootKey(String rootKey) { this.key_root = rootKey; }
	
	public String getNodeKey() { return key_node; }
	public void setNodeKey(String nodeKey) { this.key_node = nodeKey; }
	
	public String getHasExceptionKey() { return key_hasException; }
	public void setHasExceptionKey(String hasExceptionKey) { this.key_hasException = hasExceptionKey; }
	
	public String getCountKey() { return key_nodeCount; }
	public void setCountKey(String countKey) { this.key_nodeCount = countKey; }
}
