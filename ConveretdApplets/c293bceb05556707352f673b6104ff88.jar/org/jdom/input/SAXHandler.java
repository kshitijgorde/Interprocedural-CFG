// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.input;

import org.jdom.EntityRef;
import com.sun.java.util.collections.List;
import java.util.EmptyStackException;
import com.sun.java.util.collections.Collection;
import org.jdom.Attribute;
import org.xml.sax.Attributes;
import com.sun.java.util.collections.Iterator;
import org.xml.sax.SAXException;
import org.jdom.Element;
import org.jdom.Namespace;
import java.io.IOException;
import com.sun.java.util.collections.HashMap;
import org.xml.sax.Locator;
import com.sun.java.util.collections.LinkedList;
import java.util.Stack;
import org.jdom.Document;
import com.sun.java.util.collections.Map;
import org.xml.sax.DTDHandler;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler implements LexicalHandler, DeclHandler, DTDHandler
{
    private static final String CVS_ID = "@(#) $RCSfile: SAXHandler.java,v $ $Revision: 1.39 $ $Date: 2002/03/15 05:36:48 $ $Name: jdom_1_0_b8 $";
    private static final Map attrNameToTypeMap;
    private Document document;
    protected Stack stack;
    protected boolean atRoot;
    protected boolean inDTD;
    protected boolean inInternalSubset;
    protected boolean previousCDATA;
    protected boolean inCDATA;
    private boolean expand;
    protected boolean suppress;
    private int entityDepth;
    protected LinkedList declaredNamespaces;
    protected LinkedList availableNamespaces;
    private StringBuffer buffer;
    private StringBuffer textBuffer;
    private Map externalEntities;
    private JDOMFactory factory;
    private boolean ignoringWhite;
    private Locator locator;
    
    static {
        (attrNameToTypeMap = new HashMap(13)).put("CDATA", new Integer(1));
        SAXHandler.attrNameToTypeMap.put("ID", new Integer(2));
        SAXHandler.attrNameToTypeMap.put("IDREF", new Integer(3));
        SAXHandler.attrNameToTypeMap.put("IDREFS", new Integer(4));
        SAXHandler.attrNameToTypeMap.put("ENTITY", new Integer(5));
        SAXHandler.attrNameToTypeMap.put("ENTITIES", new Integer(6));
        SAXHandler.attrNameToTypeMap.put("NMTOKEN", new Integer(7));
        SAXHandler.attrNameToTypeMap.put("NMTOKENS", new Integer(8));
        SAXHandler.attrNameToTypeMap.put("NOTATION", new Integer(9));
        SAXHandler.attrNameToTypeMap.put("ENUMERATION", new Integer(10));
    }
    
    public SAXHandler(final Document document) throws IOException {
        this(new DefaultJDOMFactory());
        this.document = document;
    }
    
    public SAXHandler() throws IOException {
        this((JDOMFactory)null);
    }
    
    public SAXHandler(final JDOMFactory factory) throws IOException {
        this.inDTD = false;
        this.inInternalSubset = false;
        this.previousCDATA = false;
        this.inCDATA = false;
        this.expand = true;
        this.suppress = false;
        this.entityDepth = 0;
        this.buffer = new StringBuffer();
        this.textBuffer = new StringBuffer(4096);
        this.ignoringWhite = false;
        if (factory != null) {
            this.factory = factory;
        }
        else {
            this.factory = new DefaultJDOMFactory();
        }
        this.atRoot = true;
        this.stack = new Stack();
        this.declaredNamespaces = new LinkedList();
        (this.availableNamespaces = new LinkedList()).add(Namespace.XML_NAMESPACE);
        this.externalEntities = new HashMap();
        this.document = this.factory.document(null);
    }
    
    public Document getDocument() {
        return this.document;
    }
    
    public JDOMFactory getFactory() {
        return this.factory;
    }
    
    public void setExpandEntities(final boolean expand) {
        this.expand = expand;
    }
    
    public boolean getExpandEntities() {
        return this.expand;
    }
    
    public void setIgnoringElementContentWhitespace(final boolean ignoringWhite) {
        this.ignoringWhite = ignoringWhite;
    }
    
    public boolean getIgnoringElementContentWhitespace() {
        return this.ignoringWhite;
    }
    
    public void externalEntityDecl(final String name, final String publicID, final String systemID) throws SAXException {
        this.externalEntities.put(name, new String[] { publicID, systemID });
        if (!this.inInternalSubset) {
            return;
        }
        this.buffer.append("  <!ENTITY ").append(name);
        this.appendExternalId(publicID, systemID);
        this.buffer.append(">\n");
    }
    
    public void attributeDecl(final String eName, final String aName, final String type, final String valueDefault, final String value) throws SAXException {
        if (!this.inInternalSubset) {
            return;
        }
        this.buffer.append("  <!ATTLIST ").append(eName).append(" ").append(aName).append(" ").append(type).append(" ");
        if (valueDefault != null) {
            this.buffer.append(valueDefault);
        }
        else {
            this.buffer.append("\"").append(value).append("\"");
        }
        if (valueDefault != null && valueDefault.equals("#FIXED")) {
            this.buffer.append(" \"").append(value).append("\"");
        }
        this.buffer.append(">\n");
    }
    
    public void elementDecl(final String name, final String model) throws SAXException {
        if (!this.inInternalSubset) {
            return;
        }
        this.buffer.append("  <!ELEMENT ").append(name).append(" ").append(model).append(">\n");
    }
    
    public void internalEntityDecl(final String name, final String value) throws SAXException {
        if (!this.inInternalSubset) {
            return;
        }
        this.buffer.append("  <!ENTITY ");
        if (name.startsWith("%")) {
            this.buffer.append("% ").append(name.substring(1));
        }
        else {
            this.buffer.append(name);
        }
        this.buffer.append(" \"").append(value).append("\">\n");
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
        if (this.suppress) {
            return;
        }
        this.flushCharacters();
        if (this.atRoot) {
            this.document.addContent(this.factory.processingInstruction(target, data));
        }
        else {
            this.getCurrentElement().addContent(this.factory.processingInstruction(target, data));
        }
    }
    
    public void skippedEntity(final String name) throws SAXException {
        if (name.startsWith("%")) {
            return;
        }
        this.flushCharacters();
        this.getCurrentElement().addContent(this.factory.entityRef(name));
    }
    
    public void startPrefixMapping(final String prefix, final String uri) throws SAXException {
        if (this.suppress) {
            return;
        }
        final Namespace ns = Namespace.getNamespace(prefix, uri);
        this.declaredNamespaces.add(ns);
    }
    
    public void endPrefixMapping(final String prefix) throws SAXException {
        if (this.suppress) {
            return;
        }
        final Iterator itr = this.availableNamespaces.iterator();
        while (itr.hasNext()) {
            final Namespace ns = (Namespace)itr.next();
            if (prefix.equals(ns.getPrefix())) {
                itr.remove();
            }
        }
    }
    
    public void startElement(final String namespaceURI, final String localName, final String qName, final Attributes atts) throws SAXException {
        if (this.suppress) {
            return;
        }
        Element element = null;
        if (namespaceURI != null && !namespaceURI.equals("")) {
            String prefix = "";
            if (!qName.equals(localName)) {
                final int split = qName.indexOf(":");
                prefix = qName.substring(0, split);
            }
            final Namespace elementNamespace = Namespace.getNamespace(prefix, namespaceURI);
            element = this.factory.element(localName, elementNamespace);
        }
        else {
            element = this.factory.element(localName);
        }
        if (this.declaredNamespaces.size() > 0) {
            this.transferNamespaces(element);
        }
        for (int i = 0, len = atts.getLength(); i < len; ++i) {
            Attribute attribute = null;
            final String attLocalName = atts.getLocalName(i);
            final String attQName = atts.getQName(i);
            final int attType = this.getAttributeType(atts.getType(i));
            if (!attQName.startsWith("xmlns:")) {
                if (!attQName.equals("xmlns")) {
                    if (!attQName.equals(attLocalName)) {
                        final String attPrefix = attQName.substring(0, attQName.indexOf(":"));
                        attribute = this.factory.attribute(attLocalName, atts.getValue(i), attType, this.getNamespace(attPrefix));
                    }
                    else {
                        attribute = this.factory.attribute(attLocalName, atts.getValue(i), attType);
                    }
                    element.setAttribute(attribute);
                }
            }
        }
        this.flushCharacters();
        if (this.atRoot) {
            this.document.setRootElement(element);
            this.stack.push(element);
            this.atRoot = false;
        }
        else {
            this.getCurrentElement().addContent(element);
            this.stack.push(element);
        }
    }
    
    private void transferNamespaces(final Element element) {
        for (final Namespace ns : this.declaredNamespaces) {
            this.availableNamespaces.addFirst(ns);
            element.addNamespaceDeclaration(ns);
        }
        this.declaredNamespaces.clear();
    }
    
    private Namespace getNamespace(final String prefix) {
        for (final Namespace ns : this.availableNamespaces) {
            if (prefix.equals(ns.getPrefix())) {
                return ns;
            }
        }
        return Namespace.NO_NAMESPACE;
    }
    
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        if (this.suppress || length == 0) {
            return;
        }
        if (this.previousCDATA != this.inCDATA) {
            this.flushCharacters();
        }
        this.textBuffer.append(ch, start, length);
    }
    
    protected void flushCharacters() throws SAXException {
        if (this.textBuffer.length() == 0) {
            this.previousCDATA = this.inCDATA;
            return;
        }
        final String data = this.textBuffer.toString();
        this.textBuffer.setLength(0);
        if (this.previousCDATA) {
            this.getCurrentElement().addContent(this.factory.cdata(data));
        }
        else {
            this.getCurrentElement().addContent(this.factory.text(data));
        }
        this.previousCDATA = this.inCDATA;
    }
    
    public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException {
        if (this.suppress) {
            return;
        }
        if (this.ignoringWhite) {
            return;
        }
        if (length == 0) {
            return;
        }
        this.textBuffer.append(ch, start, length);
    }
    
    public void endElement(final String namespaceURI, final String localName, final String qName) throws SAXException {
        if (this.suppress) {
            return;
        }
        this.flushCharacters();
        try {
            final Element element = this.stack.pop();
            final List addl = element.getAdditionalNamespaces();
            if (addl.size() > 0) {
                this.availableNamespaces.removeAll(addl);
            }
        }
        catch (EmptyStackException ex1) {
            throw new SAXException("Ill-formed XML document (missing opening tag for " + localName + ")");
        }
        if (this.stack.empty()) {
            this.atRoot = true;
        }
    }
    
    public void startDTD(final String name, final String publicID, final String systemID) throws SAXException {
        this.flushCharacters();
        this.document.setDocType(this.factory.docType(name, publicID, systemID));
        this.inDTD = true;
        this.inInternalSubset = true;
    }
    
    public void endDTD() throws SAXException {
        this.document.getDocType().setInternalSubset(this.buffer.toString());
        this.inDTD = false;
        this.inInternalSubset = false;
    }
    
    public void startEntity(final String name) throws SAXException {
        ++this.entityDepth;
        if (this.expand || this.entityDepth > 1) {
            return;
        }
        if (name.equals("[dtd]")) {
            this.inInternalSubset = false;
            return;
        }
        if (!this.inDTD && !name.equals("amp") && !name.equals("lt") && !name.equals("gt") && !name.equals("apos") && !name.equals("quot") && !this.expand) {
            String pub = null;
            String sys = null;
            final String[] ids = (String[])this.externalEntities.get(name);
            if (ids != null) {
                pub = ids[0];
                sys = ids[1];
            }
            if (!this.atRoot && !this.stack.isEmpty()) {
                this.flushCharacters();
                final EntityRef entity = this.factory.entityRef(name, pub, sys);
                this.getCurrentElement().addContent(entity);
            }
            this.suppress = true;
        }
    }
    
    public void endEntity(final String name) throws SAXException {
        --this.entityDepth;
        if (this.entityDepth == 0) {
            this.suppress = false;
        }
        if (name.equals("[dtd]")) {
            this.inInternalSubset = true;
        }
    }
    
    public void startCDATA() throws SAXException {
        if (this.suppress) {
            return;
        }
        this.inCDATA = true;
    }
    
    public void endCDATA() throws SAXException {
        if (this.suppress) {
            return;
        }
        this.previousCDATA = true;
        this.inCDATA = false;
    }
    
    public void comment(final char[] ch, final int start, final int length) throws SAXException {
        if (this.suppress) {
            return;
        }
        this.flushCharacters();
        final String commentText = new String(ch, start, length);
        if (this.inDTD && this.inInternalSubset && !this.expand) {
            this.buffer.append("  <!--").append(commentText).append("-->\n");
            return;
        }
        if (!this.inDTD && !commentText.equals("")) {
            if (this.stack.empty()) {
                this.document.addContent(this.factory.comment(commentText));
            }
            else {
                this.getCurrentElement().addContent(this.factory.comment(commentText));
            }
        }
    }
    
    public void notationDecl(final String name, final String publicID, final String systemID) throws SAXException {
        if (!this.inInternalSubset) {
            return;
        }
        this.buffer.append("  <!NOTATION ").append(name);
        this.appendExternalId(publicID, systemID);
        this.buffer.append(">\n");
    }
    
    public void unparsedEntityDecl(final String name, final String publicID, final String systemID, final String notationName) throws SAXException {
        if (!this.inInternalSubset) {
            return;
        }
        this.buffer.append("  <!ENTITY ").append(name);
        this.appendExternalId(publicID, systemID);
        this.buffer.append(" NDATA ").append(notationName);
        this.buffer.append(">\n");
    }
    
    protected void appendExternalId(final String publicID, final String systemID) {
        if (publicID != null) {
            this.buffer.append(" PUBLIC \"").append(publicID).append("\"");
        }
        if (systemID != null) {
            if (publicID == null) {
                this.buffer.append(" SYSTEM ");
            }
            else {
                this.buffer.append(" ");
            }
            this.buffer.append("\"").append(systemID).append("\"");
        }
    }
    
    protected Element getCurrentElement() throws SAXException {
        try {
            return this.stack.peek();
        }
        catch (EmptyStackException ex1) {
            throw new SAXException("Ill-formed XML document (multiple root elements detected)");
        }
    }
    
    private int getAttributeType(final String typeName) {
        final Integer type = (Integer)SAXHandler.attrNameToTypeMap.get(typeName);
        if (type != null) {
            return type;
        }
        if (typeName != null && typeName.length() > 0 && typeName.charAt(0) == '(') {
            return 10;
        }
        return 0;
    }
    
    public void setDocumentLocator(final Locator locator) {
        this.locator = locator;
    }
    
    public Locator getDocumentLocator() {
        return this.locator;
    }
}
