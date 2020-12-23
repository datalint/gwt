package com.datalint.xml.shared.xml;

import com.datalint.xml.shared.IXmlExpression;
import com.google.common.xml.XmlEscapers;

public class XmlAttribute implements IXmlExpression {
	private final String name;
	private final String escapedValue;

	public XmlAttribute(String name, String value) {
		this.name = name;
		this.escapedValue = XmlEscapers.xmlAttributeEscaper().escape(value);
	}

	@Override
	public StringBuilder append(StringBuilder target) {
		return target.append(_SPACE).append(name).append(_EQUALS).append(_APOSTROPHE).append(escapedValue)
				.append(_APOSTROPHE);
	}
}