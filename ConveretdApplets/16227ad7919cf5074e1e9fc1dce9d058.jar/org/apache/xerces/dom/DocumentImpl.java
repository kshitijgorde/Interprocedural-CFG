// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.apache.xerces.dom.events.MutationEventImpl;
import org.apache.xerces.dom.events.EventImpl;
import org.apache.xerces.domx.events.Event;
import org.apache.xerces.dom.traversal.TreeWalkerImpl;
import org.apache.xerces.domx.traversal.TreeWalker;
import org.apache.xerces.dom.traversal.NodeIteratorImpl;
import org.apache.xerces.domx.traversal.NodeIterator;
import org.apache.xerces.domx.traversal.NodeFilter;
import java.util.Enumeration;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Notation;
import org.w3c.dom.Entity;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.EntityReference;
import org.w3c.dom.Element;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Comment;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.DocumentType;
import java.util.Vector;
import java.util.Hashtable;
import org.apache.xerces.domx.events.DocumentEvent;
import org.apache.xerces.domx.traversal.DocumentTraversal;
import org.w3c.dom.Document;

public class DocumentImpl extends NodeImpl implements Document, DocumentTraversal, DocumentEvent
{
    static final long serialVersionUID = 515687835542616694L;
    protected DocumentTypeImpl docType;
    protected ElementImpl docElement;
    protected Hashtable identifiers;
    protected Vector iterators;
    protected Vector treeWalkers;
    protected boolean allowGrammarAccess;
    
    public DocumentImpl() {
        this(false);
    }
    
    public DocumentImpl(final boolean allowGrammarAccess) {
        super(null, null, null);
        super.ownerDocument = this;
        this.allowGrammarAccess = allowGrammarAccess;
    }
    
    public DocumentImpl(final DocumentType documentType) {
        this(documentType, false);
    }
    
    public DocumentImpl(final DocumentType documentType, final boolean b) {
        this(b);
        this.docType = this.docType;
        if (this.docType != null) {
            this.docType.ownerDocument = this;
        }
    }
    
    public short getNodeType() {
        return 9;
    }
    
    public String getNodeName() {
        return "#document";
    }
    
    public String getNodeValue() {
        return null;
    }
    
    public Node cloneNode(final boolean b) {
        final DocumentImpl documentImpl = new DocumentImpl();
        if (b) {
            for (NodeImpl nextSibling = (NodeImpl)this.getFirstChild(); nextSibling != null; nextSibling = nextSibling.nextSibling) {
                documentImpl.appendChild(documentImpl.importNode(nextSibling, true));
            }
        }
        return documentImpl;
    }
    
    public Node insertBefore(final Node node, final Node node2) throws DOMException {
        final short nodeType = node.getNodeType();
        if ((nodeType == 1 && this.docElement != null) || (nodeType == 10 && this.docType != null)) {
            throw new DOMExceptionImpl((short)3, "HIERARCHY_REQUEST_ERR");
        }
        super.insertBefore(node, node2);
        if (nodeType == 1) {
            this.docElement = (ElementImpl)node;
        }
        else if (nodeType == 10) {
            this.docType = (DocumentTypeImpl)node;
        }
        return node;
    }
    
    public Node removeChild(final Node node) throws DOMException {
        super.removeChild(node);
        final short nodeType = node.getNodeType();
        if (nodeType == 1) {
            this.docElement = null;
        }
        else if (nodeType == 10) {
            this.docType = null;
        }
        return node;
    }
    
    public void setNodeValue(final String s) throws DOMException {
        throw new DOMExceptionImpl((short)7, "NO_MODIFICATION_ALLOWED_ERR");
    }
    
    public Attr createAttribute(final String s) throws DOMException {
        if (!isXMLName(s)) {
            throw new DOMExceptionImpl((short)5, "INVALID_CHARACTER_ERR");
        }
        return new AttrImpl(this, s);
    }
    
    public CDATASection createCDATASection(final String s) throws DOMException {
        return new CDATASectionImpl(this, s);
    }
    
    public Comment createComment(final String s) {
        return new CommentImpl(this, s);
    }
    
    public DocumentFragment createDocumentFragment() {
        return new DocumentFragmentImpl(this);
    }
    
    public Element createElement(final String s) throws DOMException {
        if (!isXMLName(s)) {
            throw new DOMExceptionImpl((short)5, "INVALID_CHARACTER_ERR");
        }
        return new ElementImpl(this, s);
    }
    
    public EntityReference createEntityReference(final String s) throws DOMException {
        if (!isXMLName(s)) {
            throw new DOMExceptionImpl((short)5, "INVALID_CHARACTER_ERR");
        }
        return new EntityReferenceImpl(this, s);
    }
    
    public ProcessingInstruction createProcessingInstruction(final String s, final String s2) throws DOMException {
        if (!isXMLName(s)) {
            throw new DOMExceptionImpl((short)5, "INVALID_CHARACTER_ERR");
        }
        return new ProcessingInstructionImpl(this, s, s2);
    }
    
    public Text createTextNode(final String s) {
        return new TextImpl(this, s);
    }
    
    public DocumentType getDoctype() {
        if (super.syncChildren) {
            this.synchronizeChildren();
        }
        return this.docType;
    }
    
    public Element getDocumentElement() {
        if (super.syncChildren) {
            this.synchronizeChildren();
        }
        return this.docElement;
    }
    
    public NodeList getElementsByTagName(final String s) {
        return new DeepNodeListImpl(this, s);
    }
    
    public DOMImplementation getImplementation() {
        return DOMImplementationImpl.getDOMImplementation();
    }
    
    public DocumentType createDocumentType(final String s, final String s2, final String s3) throws DOMException {
        if (!isXMLName(s)) {
            throw new DOMExceptionImpl((short)5, "INVALID_CHARACTER_ERR");
        }
        return new DocumentTypeImpl(this, s, s2, s3);
    }
    
    public Entity createEntity(final String s) throws DOMException {
        return new EntityImpl(this, s);
    }
    
    public Notation createNotation(final String s) throws DOMException {
        return new NotationImpl(this, s);
    }
    
    public ElementDefinitionImpl createElementDefinition(final String s) throws DOMException {
        return new ElementDefinitionImpl(this, s);
    }
    
    public Node importNode(final Node node, boolean b) throws DOMException {
        NodeImpl nodeImpl = null;
        switch (node.getNodeType()) {
            case 1: {
                final Element element = this.createElement(node.getNodeName());
                final NamedNodeMap attributes = node.getAttributes();
                if (attributes != null) {
                    for (int i = 0; i < attributes.getLength(); ++i) {
                        element.setAttributeNode((Attr)this.importNode(attributes.item(i), true));
                    }
                }
                nodeImpl = (NodeImpl)element;
                break;
            }
            case 2: {
                nodeImpl = (NodeImpl)this.createAttribute(node.getNodeName());
                break;
            }
            case 3: {
                nodeImpl = (NodeImpl)this.createTextNode(node.getNodeValue());
                break;
            }
            case 4: {
                nodeImpl = (NodeImpl)this.createCDATASection(node.getNodeValue());
                break;
            }
            case 5: {
                nodeImpl = (NodeImpl)this.createEntityReference(node.getNodeName());
                b = false;
                break;
            }
            case 6: {
                final Entity entity = (Entity)node;
                final EntityImpl entityImpl = (EntityImpl)this.createEntity(node.getNodeName());
                entityImpl.setPublicId(entity.getPublicId());
                entityImpl.setSystemId(entity.getSystemId());
                entityImpl.setNotationName(entity.getNotationName());
                nodeImpl = entityImpl;
                break;
            }
            case 7: {
                nodeImpl = (ProcessingInstructionImpl)this.createProcessingInstruction(node.getNodeName(), node.getNodeValue());
                break;
            }
            case 8: {
                nodeImpl = (NodeImpl)this.createComment(node.getNodeValue());
                break;
            }
            case 10: {
                final DocumentTypeImpl documentTypeImpl = (DocumentTypeImpl)node;
                final DocumentTypeImpl documentTypeImpl2 = (DocumentTypeImpl)this.createDocumentType(documentTypeImpl.getNodeName(), documentTypeImpl.getPublicID(), documentTypeImpl.getSystemID());
                final NamedNodeMap entities = ((DocumentType)node).getEntities();
                final NamedNodeMap entities2 = documentTypeImpl2.getEntities();
                if (entities != null) {
                    for (int j = 0; j < entities.getLength(); ++j) {
                        entities2.setNamedItem(this.importNode(entities.item(j), true));
                    }
                }
                final NamedNodeMap notations = ((DocumentType)node).getNotations();
                final NamedNodeMap notations2 = documentTypeImpl2.getNotations();
                if (notations != null) {
                    for (int k = 0; k < notations.getLength(); ++k) {
                        notations2.setNamedItem(this.importNode(notations.item(k), true));
                    }
                }
                nodeImpl = documentTypeImpl2;
                break;
            }
            case 11: {
                nodeImpl = (NodeImpl)this.createDocumentFragment();
                break;
            }
            case 12: {
                final Notation notation = (Notation)node;
                final NotationImpl notationImpl = (NotationImpl)this.createNotation(node.getNodeName());
                notationImpl.setPublicId(notation.getPublicId());
                notationImpl.setSystemId(notation.getSystemId());
                nodeImpl = notationImpl;
                break;
            }
            default: {
                throw new DOMExceptionImpl((short)3, "HIERARCHY_REQUEST_ERR");
            }
        }
        if (b) {
            for (Node node2 = node.getFirstChild(); node2 != null; node2 = node2.getNextSibling()) {
                nodeImpl.appendChild(this.importNode(node2, true));
            }
        }
        return nodeImpl;
    }
    
    public void putIdentifier(final String s, final Element element) {
        if (element == null) {
            this.removeIdentifier(s);
        }
        if (super.syncData) {
            this.synchronizeData();
        }
        if (this.identifiers == null) {
            this.identifiers = new Hashtable();
        }
        this.identifiers.put(s, element);
    }
    
    public Element getIdentifier(final String s) {
        if (super.syncData) {
            this.synchronizeData();
        }
        if (this.identifiers == null) {
            return null;
        }
        return this.identifiers.get(s);
    }
    
    public void removeIdentifier(final String s) {
        if (super.syncData) {
            this.synchronizeData();
        }
        if (this.identifiers == null) {
            return;
        }
        this.identifiers.remove(s);
    }
    
    public Enumeration getIdentifiers() {
        if (super.syncData) {
            this.synchronizeData();
        }
        if (this.identifiers == null) {
            this.identifiers = new Hashtable();
        }
        return this.identifiers.keys();
    }
    
    public Element createElementNS(final String s, final String s2) throws DOMException {
        if (s == null || s.equals("")) {
            return new ElementImpl(this, s2);
        }
        return new ElementImpl(this, s, s2);
    }
    
    public Attr createAttributeNS(final String s, final String s2) throws DOMException {
        if (s == null || s.equals("")) {
            return new AttrImpl(this, s2);
        }
        return new AttrImpl(this, s, s2);
    }
    
    public NodeList getElementsByTagNameNS(final String s, final String s2) {
        return new DeepNodeListImpl(this, s, s2);
    }
    
    public NodeIterator createNodeIterator(final Node node, final short n, final NodeFilter nodeFilter) {
        return this.createNodeIterator(node, n, nodeFilter, true);
    }
    
    public NodeIterator createNodeIterator(final Node node, final int n, final NodeFilter nodeFilter, final boolean b) {
        final NodeIteratorImpl nodeIteratorImpl = new NodeIteratorImpl(this, node, n, nodeFilter, b);
        if (this.iterators == null) {
            this.iterators = new Vector();
        }
        this.iterators.addElement(nodeIteratorImpl);
        return nodeIteratorImpl;
    }
    
    public TreeWalker createTreeWalker(final Node node, final short n, final NodeFilter nodeFilter) {
        return this.createTreeWalker(node, n, nodeFilter, true);
    }
    
    public TreeWalker createTreeWalker(final Node node, final int n, final NodeFilter nodeFilter, final boolean b) {
        if (node == null) {
            throw new DOMExceptionImpl((short)9, "NOT_SUPPORTED_ERR");
        }
        final TreeWalkerImpl treeWalkerImpl = new TreeWalkerImpl(node, n, nodeFilter, b);
        if (this.treeWalkers == null) {
            this.treeWalkers = new Vector();
        }
        this.treeWalkers.addElement(treeWalkerImpl);
        return treeWalkerImpl;
    }
    
    public void removeNodeIterator(final NodeIterator nodeIterator) {
        if (nodeIterator == null) {
            return;
        }
        if (this.iterators == null) {
            return;
        }
        this.iterators.removeElement(nodeIterator);
    }
    
    public void removeTreeWalker(final TreeWalker treeWalker) {
        if (treeWalker == null) {
            return;
        }
        if (this.treeWalkers == null) {
            return;
        }
        this.treeWalkers.removeElement(treeWalker);
    }
    
    public Enumeration getNodeIterators() {
        if (this.iterators == null) {
            return null;
        }
        return this.iterators.elements();
    }
    
    public Enumeration getTreeWalkers() {
        if (this.treeWalkers == null) {
            return null;
        }
        return this.treeWalkers.elements();
    }
    
    public void removeNodeIterators() {
        this.iterators = null;
    }
    
    public void removeTreeWalkers() {
        this.treeWalkers = null;
    }
    
    public Event createEvent(final String s) throws DOMException {
        if ("Event".equals(s)) {
            return new EventImpl();
        }
        if ("MutationEvent".equals(s)) {
            return new MutationEventImpl();
        }
        throw new DOMExceptionImpl((short)101, null);
    }
    
    public static boolean isXMLName(final String s) {
        if (s == null) {
            return false;
        }
        final char[] array = new char[s.length()];
        s.getChars(0, s.length(), array, 0);
        if (!Character.isLetter(array[0]) && "_:".indexOf(array[0]) == -1) {
            return false;
        }
        for (int i = 1; i < s.length(); ++i) {
            final char c = array[i];
            final int type = Character.getType(c);
            if (!Character.isLetterOrDigit(c) && ".-_:".indexOf(c) == -1 && (type < 6 || type > 8 || (c >= '\u06dd' && c <= '\u06de') || (c >= '\u20dd' && c <= '\u20e0') || c >= '\u309b') && (type != 4 || (c >= '\u02d0' && c <= '\u0559') || (c >= '\u06e5' && c <= '\u06e6') || (c >= '\u309b' && c <= '\u309c')) && c != '·' && c != '\u0387') {
                return false;
            }
        }
        return true;
    }
    
    protected boolean isKidOK(final Node node, final Node node2) {
        if (this.allowGrammarAccess && node.getNodeType() == 10) {
            return node2.getNodeType() == 1;
        }
        return (NodeImpl.kidOK[node.getNodeType()] & 1 << node2.getNodeType()) != 0x0;
    }
}
