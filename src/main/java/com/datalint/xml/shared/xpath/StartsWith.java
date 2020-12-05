package com.datalint.xml.shared.xpath;

import com.datalint.xml.shared.IXPathExpression;

public class StartsWith extends ArityExpression {
	public StartsWith(IXPathExpression first, IXPathExpression second) {
		super(first, second);
	}

	@Override
	protected StringBuilder open(StringBuilder target) {
		return target.append("starts-with(");
	}
}