package com.datalint.xml.shared.xpath;

import com.datalint.xml.shared.IXPathExpression;

public class Less extends Join {
	public Less(IXPathExpression first, IXPathExpression second) {
		super(first, second);
	}

	@Override
	protected StringBuilder operator(StringBuilder target) {
		return target.append(_LESS_THAN);
	}
}
