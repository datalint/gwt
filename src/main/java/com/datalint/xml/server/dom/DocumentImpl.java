package com.datalint.xml.server.dom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.*;

public class DocumentImpl extends NodeImpl implements Document {
	public static final String KEY_id = "id";

	private ElementImpl element;

	public DocumentImpl() {
		super(null);
	}

	@Override
	public String getNodeName() {
		return "#document";
	}

	@Override
	public short getNodeType() {
		return DOCUMENT_NODE;
	}

	@Override
	public Node cloneNode(boolean deep) {
		DocumentImpl document = new DocumentImpl();

		if (deep && element != null)
			document.element = (ElementImpl) element.cloneNode(true);

		return document;
	}

	@Override
	public Element getDocumentElement() {
		return element;
	}

	@Override
	public NodeList getChildNodes() {
		return new NodeListImpl(element == null ? Collections.emptyList() : Collections.singletonList(element));
	}

	@Override
	public Node getFirstChild() {
		return element;
	}

	@Override
	public Node getLastChild() {
		return element;
	}

	@Override
	public Element createElement(String tagName) {
		return new ElementImpl(this, tagName);
	}

	@Override
	public DocumentFragment createDocumentFragment() {
		return new DocumentFragmentImpl(this);
	}

	@Override
	public Text createTextNode(String data) {
		return new TextImpl(this, data);
	}

	@Override
	public Comment createComment(String data) {
		return new CommentImpl(this, data);
	}

	@Override
	public CDATASection createCDATASection(String data) {
		return new CDATASectionImpl(this, data);
	}

	@Override
	public ProcessingInstruction createProcessingInstruction(String target, String data) {
		return new ProcessingInstructionImpl(this, target, data);
	}

	/*
	 * This method is NOT optimized. Client side shall cache idMap by self through
	 * getElementsByTagName(*).
	 */
	@Override
	public Element getElementById(String elementId) {
		NodeList nodeList = getElementsByTagName("*");

		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);

			if (elementId.equals(element.getAttribute(KEY_id)))
				return element;
		}

		return null;
	}

	@Override
	public NodeList getElementsByTagName(String tagname) {
		List<Node> list;

		if (element == null)
			list = Collections.emptyList();
		else {
			list = new ArrayList<>();

			if (tagname.equals(WILDCARD) || element.getNodeName().equals(tagname))
				list.add(element);
		}

		return element == null ? new NodeListImpl(Collections.emptyList())
				: element.getElementsByTagName(list, tagname);
	}

	@Override
	public Node importNode(Node importedNode, boolean deep) {
		return importedNode;
	}

	@Override
	public Node insertBefore(Node newChild, Node refChild) {
		element = (ElementImpl) newChild;

		return newChild;
	}

	@Override
	public Node replaceChild(Node newChild, Node oldChild) {
		element = (ElementImpl) newChild;

		return oldChild;
	}

	@Override
	public Node removeChild(Node oldChild) {
		return element = null;
	}

	@Override
	public Node appendChild(Node newChild) {
		element = (ElementImpl) newChild;

		return newChild;
	}

	@Override
	public boolean hasChildNodes() {
		return element != null;
	}

	@Override
	public String getXmlEncoding() {
		return null;
	}

	@Override
	public boolean getXmlStandalone() {
		return false;
	}

	@Override
	public String getXmlVersion() {
		return null;
	}

	@Override
	public void setXmlStandalone(boolean xmlStandalone) {
	}

	@Override
	public void setXmlVersion(String xmlVersion) {
	}

	@Override
	public DocumentType getDoctype() {
		throw iCreateUoException("getDoctype");
	}

	@Override
	public DOMImplementation getImplementation() {
		throw iCreateUoException("getImplementation");
	}

	@Override
	public Attr createAttribute(String name) {
		throw iCreateUoException("createAttribute");
	}

	@Override
	public EntityReference createEntityReference(String name) {
		throw iCreateUoException("createEntityReference");
	}

	@Override
	public Element createElementNS(String namespaceURI, String qualifiedName) {
		throw iCreateUoException("createElementNS");
	}

	@Override
	public Attr createAttributeNS(String namespaceURI, String qualifiedName) {
		throw iCreateUoException("createAttributeNS");
	}

	@Override
	public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
		throw iCreateUoException("getElementsByTagNameNS");
	}

	@Override
	public String getInputEncoding() {
		throw iCreateUoException("getInputEncoding");
	}

	@Override
	public boolean getStrictErrorChecking() {
		throw iCreateUoException("getStrictErrorChecking");
	}

	@Override
	public void setStrictErrorChecking(boolean strictErrorChecking) {
		throw iCreateUoException("setStrictErrorChecking");
	}

	@Override
	public String getDocumentURI() {
		throw iCreateUoException("getDocumentURI");
	}

	@Override
	public void setDocumentURI(String documentURI) {
		throw iCreateUoException("setDocumentURI");
	}

	@Override
	public Node adoptNode(Node source) {
		throw iCreateUoException("adoptNode");
	}

	@Override
	public DOMConfiguration getDomConfig() {
		throw iCreateUoException("getDomConfig");
	}

	@Override
	public void normalizeDocument() {
		throw iCreateUoException("normalizeDocument");
	}

	@Override
	public Node renameNode(Node n, String namespaceURI, String qualifiedName) {
		throw iCreateUoException("renameNode");
	}

	@Override
	public String toString() {
		return element == null ? EMPTY : element.toString();
	}
}
