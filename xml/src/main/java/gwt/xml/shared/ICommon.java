package gwt.xml.shared;

import com.google.common.xml.XmlEscapers;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.annotation.Nullable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public interface ICommon {
	char _APOSTROPHE = '\'';
	char _AT = '@';
	char _COMMA = ',';
	char _EQUALS = '=';
	char _GREATER_THAN = '>';
	char _LESS_THAN = '<';
	char _LINE_FEED = '\n';
	char _QUOTE = '"';
	char _SLASH = '/';
	char _SPACE = ' ';
	char _TAB = '\t';
	char _UNDERSCORE = '_';

	String ALPHABET_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
	String ALPHABET_UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String AND_WITH_SPACE = " and ";
	String AT = "@";
	String COUNT_ALL = "count(*)";
	String DOLLAR = "$";
	String DOT = ".";
	String EMPTY = "";
	String LAST_F = "last()";
	String MINUS_WITH_SPACE = " - ";
	String ONE = "1";
	String OR_WITH_SPACE = " or ";
	String PLUS_WITH_SPACE = " + ";
	String POSITION_F = "position()";
	String SPACE = " ";
	String TEXT_F = "text()";
	String UNDERSCORE = "_";
	String WILDCARD = "*";
	String ZERO = "0";

	default int getAttributeInt(Element element, String name) {
		return parseInt(element.getAttribute(name));
	}

	default Integer getAttributeInteger(Element element, String name) {
		return parseInteger(element.getAttribute(name));
	}

	default String nonNullAttribute(Element element, String name) {
		return attribute(element, name, EMPTY);
	}

	default String attribute(Element element, String name, String substitution) {
		return element.hasAttribute(name) ? element.getAttribute(name) : substitution;
	}

	default String simpleName(Class<?> clazz) {
		return clazz.getSimpleName();
	}

	default int parseInt(String source) {
		return parseInt(source, 0);
	}

	default int parseInt(String source, int defaultValue) {
		if (!isEmpty(source))
			try {
				return Integer.parseInt(source);
			} catch (NumberFormatException e) {
				// Ignore
			}

		return defaultValue;
	}

	default Integer parseInteger(String source) {
		if (!isEmpty(source)) {
			try {
				return Integer.valueOf(source);
			} catch (NumberFormatException e) {
				// Ignore;
			}
		}

		return null;
	}

	default boolean isEven(int x) {
		return (x & 1) == 0;
	}

	default boolean isEmpty(@Nullable Object source) {
		return source == null || source.toString().isEmpty();
	}

	default String nonNull(@Nullable String source) {
		return nonNull(source, EMPTY);
	}

	default String nonNull(@Nullable String source, String substitution) {
		return source == null ? substitution : source;
	}

	default String escapeAttr(String attribute) {
		return XmlEscapers.xmlAttributeEscaper().escape(attribute);
	}

	default String escapeContent(String content) {
		return XmlEscapers.xmlContentEscaper().escape(content);
	}

	default UnsupportedOperationException createUoException(String operationName) {
		return new UnsupportedOperationException(getClass().getName() + " does not support operation " + operationName);
	}

	default List<Node> asList(NodeList nodeList) {
		return new NodeListWrapper(nodeList);
	}

	class NodeListWrapper extends AbstractList<Node> {
		private final NodeList nodeList;
		private final int length;

		private List<Node> extraList;

		private NodeListWrapper(NodeList nodeList) {
			this.nodeList = nodeList;
			length = nodeList.getLength();
		}

		@Override
		public void add(int index, Node node) {
			if (extraList == null)
				extraList = new ArrayList<>();

			extraList.add(index - length, node);
		}

		@Override
		public Node get(int index) {
			if (index < length)
				return nodeList.item(index);

			return extraList.get(index - length);
		}

		@Override
		public int size() {
			return extraList == null ? length : length + extraList.size();
		}
	}
}
