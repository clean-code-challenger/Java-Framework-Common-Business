package common.extern.olena.model.rule.mvc.apply.inter;

import common.extern.olena.model.dataaccess.util.core.query.queryProducts.pageExpressionRelation;

/**
 */
public interface ITruegardenerRule extends IBaseRule
{
	/////////////////////////////페지에 관한 정보////////////////////
	public Object getPageInfo(pageExpressionRelation pageInfo);	
	/////////////////////////////페지에 관한 정보////////////////////
	public pageExpressionRelation getPageInfo(Object pageInfo);
}
