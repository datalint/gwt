package com.datalint.xml.shared.xml;

import java.util.List;

import com.datalint.xml.shared.IXmlExpression;

public class XmlSequence implements IXmlExpression {
	private final List<XmlElement> xmlElements;

	public XmlSequence(List<XmlElement> xmlElements) {
		this.xmlElements = xmlElements;
	}

	@Override
	public boolean isEmpty() {
		return xmlElements.isEmpty();
	}

	@Override
	public StringBuilder append(StringBuilder target) {
		for (XmlElement xmlElement : xmlElements) {
			xmlElement.append(target);
		}

		return target;
	}
}