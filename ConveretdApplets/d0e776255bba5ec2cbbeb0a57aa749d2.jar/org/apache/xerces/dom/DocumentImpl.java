// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.apache.xerces.dom.events.MutationEventImpl;
import org.apache.xerces.dom.events.EventImpl;
import org.w3c.dom.events.Event;
import org.w3c.dom.ranges.Range;
import org.w3c.dom.traversal.TreeWalker;
import org.w3c.dom.traversal.NodeIterator;
import org.w3c.dom.traversal.NodeFilter;
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
import java.util.Enumeration;
import org.w3c.dom.Node;
import org.w3c.dom.DOMException;
import org.w3c.dom.DocumentType;
import org.apache.xerces.utils.XMLCharacterProperties;
import java.util.Vector;
import java.util.Hashtable;
import org.w3c.dom.ranges.DocumentRange;
import org.w3c.dom.events.DocumentEvent;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.Document;

public class DocumentImpl extends ParentNode implements Document, DocumentTraversal, DocumentEvent, DocumentRange
{
    static final long serialVersionUID = 515687835542616694L;
    protected DocumentTypeImpl docType;
    protected ElementImpl docElement;
    protected String encoding;
    protected String version;
    protected boolean standalone;
    protected Hashtable identifiers;
    protected Vector iterators;
    protected Vector ranges;
    protected static int[] kidOK;
    protected Hashtable userData;
    protected Hashtable eventListeners;
    protected int changes;
    protected boolean allowGrammarAccess;
    protected boolean errorChecking;
    protected boolean mutationEvents;
    
    public DocumentImpl() {
        this(false);
        XMLCharacterProperties.initCharFlags();
    }
    
    public DocumentImpl(final boolean allowGrammarAccess) {
        super(null);
        this.changes = 0;
        this.errorChecking = true;
        this.mutationEvents = false;
        super.ownerDocument = this;
        this.allowGrammarAccess = allowGrammarAccess;
        XMLCharacterProperties.initCharFlags();
    }
    
    public DocumentImpl(final DocumentType documentType) {
        this(documentType, false);
        XMLCharacterProperties.initCharFlags();
    }
    
    public DocumentImpl(final DocumentType documentType, final boolean b) {
        this(b);
        if (documentType != null) {
            DocumentTypeImpl documentTypeImpl;
            try {
                documentTypeImpl = (DocumentTypeImpl)documentType;
            }
            catch (ClassCastException ex) {
                throw new DOMException((short)4, "DOM005 Wrong document");
            }
            (documentTypeImpl.ownerDocument = this).appendChild(documentType);
        }
        XMLCharacterProperties.initCharFlags();
    }
    
    public final Document getOwnerDocument() {
        return null;
    }
    
    public short getNodeType() {
        return 9;
    }
    
    public String getNodeName() {
        return "#document";
    }
    
    public Node cloneNode(final boolean b) {
        final DocumentImpl documentImpl = new DocumentImpl();
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        if (b) {
            Hashtable<Object, Object> hashtable = null;
            if (this.identifiers != null) {
                hashtable = new Hashtable<Object, Object>();
                final Enumeration<Object> keys = this.identifiers.keys();
                while (keys.hasMoreElements()) {
                    final Object nextElement = keys.nextElement();
                    hashtable.put(this.identifiers.get(nextElement), nextElement);
                }
            }
            for (ChildNode childNode = super.firstChild; childNode != null; childNode = childNode.nextSibling) {
                documentImpl.appendChild(documentImpl.importNode(childNode, true, hashtable));
            }
        }
        documentImpl.allowGrammarAccess = this.allowGrammarAccess;
        documentImpl.errorChecking = this.errorChecking;
        documentImpl.mutationEvents = this.mutationEvents;
        return documentImpl;
    }
    
    public Node insertBefore(final Node node, final Node node2) throws DOMException {
        final short nodeType = node.getNodeType();
        if (this.errorChecking && ((nodeType == 1 && this.docElement != null) || (nodeType == 10 && this.docType != null))) {
            throw new DOMException((short)3, "DOM006 Hierarchy request error");
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
    
    public Node replaceChild(final Node node, final Node node2) throws DOMException {
        super.replaceChild(node, node2);
        final short nodeType = node2.getNodeType();
        if (nodeType == 1) {
            this.docElement = (ElementImpl)node;
        }
        else if (nodeType == 10) {
            this.docType = (DocumentTypeImpl)node;
        }
        return node2;
    }
    
    public Attr createAttribute(final String s) throws DOMException {
        if (this.errorChecking && !isXMLName(s)) {
            throw new DOMException((short)5, "DOM002 Illegal character");
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
        if (this.errorChecking && !isXMLName(s)) {
            throw new DOMException((short)5, "DOM002 Illegal character");
        }
        return new ElementImpl(this, s);
    }
    
    public EntityReference createEntityReference(final String s) throws DOMException {
        if (this.errorChecking && !isXMLName(s)) {
            throw new DOMException((short)5, "DOM002 Illegal character");
        }
        return new EntityReferenceImpl(this, s);
    }
    
    public ProcessingInstruction createProcessingInstruction(final String s, final String s2) throws DOMException {
        if (this.errorChecking && !isXMLName(s)) {
            throw new DOMException((short)5, "DOM002 Illegal character");
        }
        return new ProcessingInstructionImpl(this, s, s2);
    }
    
    public Text createTextNode(final String s) {
        return new TextImpl(this, s);
    }
    
    public DocumentType getDoctype() {
        if (this.needsSyncChildren()) {
            this.synchronizeChildren();
        }
        return this.docType;
    }
    
    public String getEncoding() {
        return this.encoding;
    }
    
    public String getVersion() {
        return this.version;
    }
    
    public boolean getStandalone() {
        return this.standalone;
    }
    
    public Element getDocumentElement() {
        if (this.needsSyncChildren()) {
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
    
    public void setErrorChecking(final boolean errorChecking) {
        this.errorChecking = errorChecking;
    }
    
    public void setEncoding(final String encoding) {
        this.encoding = encoding;
    }
    
    public void setVersion(final String version) {
        this.version = version;
    }
    
    public void setStandalone(final boolean standalone) {
        this.standalone = standalone;
    }
    
    public boolean getErrorChecking() {
        return this.errorChecking;
    }
    
    public void setMutationEvents(final boolean mutationEvents) {
        this.mutationEvents = mutationEvents;
    }
    
    public boolean getMutationEvents() {
        return this.mutationEvents;
    }
    
    public DocumentType createDocumentType(final String s, final String s2, final String s3) throws DOMException {
        if (this.errorChecking && !isXMLName(s)) {
            throw new DOMException((short)5, "DOM002 Illegal character");
        }
        return new DocumentTypeImpl(this, s, s2, s3);
    }
    
    public Entity createEntity(final String s) throws DOMException {
        if (this.errorChecking && !isXMLName(s)) {
            throw new DOMException((short)5, "DOM002 Illegal character");
        }
        return new EntityImpl(this, s);
    }
    
    public Notation createNotation(final String s) throws DOMException {
        if (this.errorChecking && !isXMLName(s)) {
            throw new DOMException((short)5, "DOM002 Illegal character");
        }
        return new NotationImpl(this, s);
    }
    
    public ElementDefinitionImpl createElementDefinition(final String s) throws DOMException {
        if (this.errorChecking && !isXMLName(s)) {
            throw new DOMException((short)5, "DOM002 Illegal character");
        }
        return new ElementDefinitionImpl(this, s);
    }
    
    public Node importNode(final Node node, final boolean b) throws DOMException {
        return this.importNode(node, b, null);
    }
    
    private Node importNode(final Node node, boolean b, final Hashtable hashtable) throws DOMException {
        final boolean hasFeature = node.getOwnerDocument().getImplementation().hasFeature("XML", "2.0");
        Object o = null;
        switch (node.getNodeType()) {
            case 1: {
                Element element;
                if (!hasFeature || node.getLocalName() == null) {
                    element = this.createElement(node.getNodeName());
                }
                else {
                    element = this.createElementNS(node.getNamespaceURI(), node.getNodeName());
                }
                final NamedNodeMap attributes = node.getAttributes();
                if (attributes != null) {
                    for (int length = attributes.getLength(), i = 0; i < length; ++i) {
                        final Attr attr = (Attr)attributes.item(i);
                        if (attr.getSpecified()) {
                            final Attr attr2 = (Attr)this.importNode(attr, true, hashtable);
                            if (!hasFeature || attr.getLocalName() == null) {
                                element.setAttributeNode(attr2);
                            }
                            else {
                                element.setAttributeNodeNS(attr2);
                            }
                        }
                    }
                }
                if (hashtable != null) {
                    final Object value = hashtable.get(node);
                    if (value != null) {
                        if (this.identifiers == null) {
                            this.identifiers = new Hashtable();
                        }
                        this.identifiers.put(value, element);
                    }
                }
                o = element;
                break;
            }
            case 2: {
                if (hasFeature) {
                    if (node.getLocalName() == null) {
                        o = this.createAttribute(node.getNodeName());
                    }
                    else {
                        o = this.createAttributeNS(node.getNamespaceURI(), node.getNodeName());
                    }
                }
                else {
                    o = this.createAttribute(node.getNodeName());
                }
                if (!(node instanceof AttrImpl)) {
                    b = true;
                    break;
                }
                final AttrImpl attrImpl = (AttrImpl)node;
                if (attrImpl.hasStringValue()) {
                    ((AttrImpl)o).setValue(attrImpl.getValue());
                    b = false;
                    break;
                }
                b = true;
                break;
            }
            case 3: {
                o = this.createTextNode(node.getNodeValue());
                break;
            }
            case 4: {
                o = this.createCDATASection(node.getNodeValue());
                break;
            }
            case 5: {
                o = this.createEntityReference(node.getNodeName());
                ((EntityReferenceImpl)o).isReadOnly(false);
                break;
            }
            case 6: {
                final Entity entity = (Entity)node;
                final EntityImpl entityImpl = (EntityImpl)this.createEntity(node.getNodeName());
                entityImpl.setPublicId(entity.getPublicId());
                entityImpl.setSystemId(entity.getSystemId());
                entityImpl.setNotationName(entity.getNotationName());
                entityImpl.isReadOnly(false);
                o = entityImpl;
                break;
            }
            case 7: {
                o = this.createProcessingInstruction(node.getNodeName(), node.getNodeValue());
                break;
            }
            case 8: {
                o = this.createComment(node.getNodeValue());
                break;
            }
            case 10: {
                final DocumentType documentType = (DocumentType)node;
                final DocumentTypeImpl documentTypeImpl = (DocumentTypeImpl)this.createDocumentType(documentType.getNodeName(), documentType.getPublicId(), documentType.getSystemId());
                final NamedNodeMap entities = documentType.getEntities();
                final NamedNodeMap entities2 = documentTypeImpl.getEntities();
                if (entities != null) {
                    for (int j = 0; j < entities.getLength(); ++j) {
                        entities2.setNamedItem(this.importNode(entities.item(j), true, hashtable));
                    }
                }
                final NamedNodeMap notations = documentType.getNotations();
                final NamedNodeMap notations2 = documentTypeImpl.getNotations();
                if (notations != null) {
                    for (int k = 0; k < notations.getLength(); ++k) {
                        notations2.setNamedItem(this.importNode(notations.item(k), true, hashtable));
                    }
                }
                o = documentTypeImpl;
                break;
            }
            case 11: {
                o = this.createDocumentFragment();
                break;
            }
            case 12: {
                final Notation notation = (Notation)node;
                final NotationImpl notationImpl = (NotationImpl)this.createNotation(node.getNodeName());
                notationImpl.setPublicId(notation.getPublicId());
                notationImpl.setSystemId(notation.getSystemId());
                o = notationImpl;
                break;
            }
            default: {
                throw new DOMException((short)9, "Node type being imported is not supported");
            }
        }
        if (b) {
            for (Node node2 = node.getFirstChild(); node2 != null; node2 = node2.getNextSibling()) {
                ((Node)o).appendChild(this.importNode(node2, true, hashtable));
            }
        }
        if (((Node)o).getNodeType() == 5 || ((Node)o).getNodeType() == 6) {
            ((EntityReferenceImpl)o).setReadOnly(true, true);
        }
        return (Node)o;
    }
    
    public Node adoptNode(final Node node) {
        NodeImpl nodeImpl;
        try {
            nodeImpl = (NodeImpl)node;
        }
        catch (ClassCastException ex) {
            return null;
        }
        switch (nodeImpl.getNodeType()) {
            case 2: {
                final AttrImpl attrImpl = (AttrImpl)nodeImpl;
                attrImpl.getOwnerElement().removeAttributeNode(attrImpl);
                attrImpl.isSpecified(true);
                attrImpl.setOwnerDocument(this);
                break;
            }
            case 9:
            case 10: {
                throw new DOMException((short)9, "cannot adopt this type of node.");
            }
            case 5: {
                final Node parentNode = nodeImpl.getParentNode();
                if (parentNode != null) {
                    parentNode.removeChild(node);
                }
                Node firstChild;
                while ((firstChild = nodeImpl.getFirstChild()) != null) {
                    nodeImpl.removeChild(firstChild);
                }
                nodeImpl.setOwnerDocument(this);
                if (this.docType == null) {
                    break;
                }
                final Node namedItem = this.docType.getEntities().getNamedItem(nodeImpl.getNodeName());
                if (namedItem == null) {
                    break;
                }
                final EntityImpl entityImpl = (EntityImpl)namedItem;
                for (Node node2 = namedItem.getFirstChild(); node2 != null; node2 = node2.getNextSibling()) {
                    nodeImpl.appendChild(node2.cloneNode(true));
                }
                break;
            }
            case 1: {
                final Node parentNode2 = nodeImpl.getParentNode();
                if (parentNode2 != null) {
                    parentNode2.removeChild(node);
                }
                nodeImpl.setOwnerDocument(this);
                ((ElementImpl)nodeImpl).reconcileDefaultAttributes();
                break;
            }
            default: {
                final Node parentNode3 = nodeImpl.getParentNode();
                if (parentNode3 != null) {
                    parentNode3.removeChild(node);
                }
                nodeImpl.setOwnerDocument(this);
                break;
            }
        }
        return nodeImpl;
    }
    
    public Element getElementById(final String s) {
        return this.getIdentifier(s);
    }
    
    public void putIdentifier(final String s, final Element element) {
        if (element == null) {
            this.removeIdentifier(s);
            return;
        }
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.identifiers == null) {
            this.identifiers = new Hashtable();
        }
        this.identifiers.put(s, element);
    }
    
    public Element getIdentifier(final String s) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.identifiers == null) {
            return null;
        }
        return this.identifiers.get(s);
    }
    
    public void removeIdentifier(final String s) {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.identifiers == null) {
            return;
        }
        this.identifiers.remove(s);
    }
    
    public Enumeration getIdentifiers() {
        if (this.needsSyncData()) {
            this.synchronizeData();
        }
        if (this.identifiers == null) {
            this.identifiers = new Hashtable();
        }
        return this.identifiers.keys();
    }
    
    public Element createElementNS(final String s, final String s2) throws DOMException {
        if (this.errorChecking && !isXMLName(s2)) {
            throw new DOMException((short)5, "DOM002 Illegal character");
        }
        return new ElementNSImpl(this, s, s2);
    }
    
    public Attr createAttributeNS(final String s, final String s2) throws DOMException {
        if (this.errorChecking && !isXMLName(s2)) {
            throw new DOMException((short)5, "DOM002 Illegal character");
        }
        return new AttrNSImpl(this, s, s2);
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
            throw new DOMException((short)9, "DOM007 Not supported");
        }
        return new TreeWalkerImpl(node, n, nodeFilter, b);
    }
    
    void removeNodeIterator(final NodeIterator nodeIterator) {
        if (nodeIterator == null) {
            return;
        }
        if (this.iterators == null) {
            return;
        }
        this.iterators.removeElement(nodeIterator);
    }
    
    public Range createRange() {
        if (this.ranges == null) {
            this.ranges = new Vector();
        }
        final RangeImpl rangeImpl = new RangeImpl(this);
        this.ranges.addElement(rangeImpl);
        return rangeImpl;
    }
    
    void removeRange(final Range range) {
        if (range == null) {
            return;
        }
        if (this.ranges == null) {
            return;
        }
        this.ranges.removeElement(range);
    }
    
    void replacedText(final Node node) {
        if (this.ranges != null) {
            final Enumeration<RangeImpl> elements = this.ranges.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().receiveReplacedText(node);
            }
        }
    }
    
    void deletedText(final Node node, final int n, final int n2) {
        if (this.ranges != null) {
            final Enumeration<RangeImpl> elements = this.ranges.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().receiveDeletedText(node, n, n2);
            }
        }
    }
    
    void insertedText(final Node node, final int n, final int n2) {
        if (this.ranges != null) {
            final Enumeration<RangeImpl> elements = this.ranges.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().receiveInsertedText(node, n, n2);
            }
        }
    }
    
    void splitData(final Node node, final Node node2, final int n) {
        if (this.ranges != null) {
            final Enumeration<RangeImpl> elements = this.ranges.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().receiveSplitData(node, node2, n);
            }
        }
    }
    
    void removedChildNode(final Node node) {
        if (this.iterators != null) {
            final Enumeration<NodeIteratorImpl> elements = this.iterators.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().removeNode(node);
            }
        }
        if (this.ranges != null) {
            final Enumeration<RangeImpl> elements2 = this.ranges.elements();
            while (elements2.hasMoreElements()) {
                elements2.nextElement().removeNode(node);
            }
        }
    }
    
    public Event createEvent(final String s) throws DOMException {
        if ("Event".equals(s)) {
            return new EventImpl();
        }
        if ("MutationEvent".equals(s)) {
            return new MutationEventImpl();
        }
        throw new DOMException((short)9, "DOM007 Not supported");
    }
    
    public Object clone() throws CloneNotSupportedException {
        final DocumentImpl documentImpl = (DocumentImpl)super.clone();
        documentImpl.docType = null;
        documentImpl.docElement = null;
        return documentImpl;
    }
    
    public static boolean isXMLName(final String s) {
        return s != null && XMLCharacterProperties.validName(s);
    }
    
    protected void setUserData(final NodeImpl nodeImpl, final Object o) {
        if (this.userData == null) {
            this.userData = new Hashtable();
        }
        if (o == null) {
            this.userData.remove(nodeImpl);
        }
        else {
            this.userData.put(nodeImpl, o);
        }
    }
    
    protected Object getUserData(final NodeImpl nodeImpl) {
        if (this.userData == null) {
            return null;
        }
        return this.userData.get(nodeImpl);
    }
    
    protected void setEventListeners(final NodeImpl nodeImpl, final Vector vector) {
        if (this.eventListeners == null) {
            this.eventListeners = new Hashtable();
        }
        if (vector == null) {
            this.eventListeners.remove(nodeImpl);
            if (this.eventListeners.isEmpty()) {
                this.mutationEvents = false;
            }
        }
        else {
            this.eventListeners.put(nodeImpl, vector);
            this.mutationEvents = true;
        }
    }
    
    protected Vector getEventListeners(final NodeImpl nodeImpl) {
        if (this.eventListeners == null) {
            return null;
        }
        return this.eventListeners.get(nodeImpl);
    }
    
    protected boolean isKidOK(final Node node, final Node node2) {
        if (this.allowGrammarAccess && node.getNodeType() == 10) {
            return node2.getNodeType() == 1;
        }
        return 0x0 != (DocumentImpl.kidOK[node.getNodeType()] & 1 << node2.getNodeType());
    }
    
    protected void changed() {
        ++this.changes;
    }
    
    protected int changes() {
        return this.changes;
    }
    
    static {
        (DocumentImpl.kidOK = new int[13])[9] = 1410;
        final int[] kidOK = DocumentImpl.kidOK;
        final int n = 11;
        final int[] kidOK2 = DocumentImpl.kidOK;
        final int n2 = 6;
        final int[] kidOK3 = DocumentImpl.kidOK;
        final int n3 = 5;
        final int[] kidOK4 = DocumentImpl.kidOK;
        final int n4 = 1;
        final int n5 = 442;
        kidOK3[n3] = (kidOK4[n4] = n5);
        kidOK[n] = (kidOK2[n2] = n5);
        DocumentImpl.kidOK[2] = 40;
        final int[] kidOK5 = DocumentImpl.kidOK;
        final int n6 = 10;
        final int[] kidOK6 = DocumentImpl.kidOK;
        final int n7 = 7;
        final int[] kidOK7 = DocumentImpl.kidOK;
        final int n8 = 8;
        final int[] kidOK8 = DocumentImpl.kidOK;
        final int n9 = 3;
        final int[] kidOK9 = DocumentImpl.kidOK;
        final int n10 = 4;
        final int[] kidOK10 = DocumentImpl.kidOK;
        final int n11 = 12;
        final boolean b = false;
        kidOK9[n10] = (kidOK10[n11] = (b ? 1 : 0));
        kidOK7[n8] = (kidOK8[n9] = (b ? 1 : 0));
        kidOK5[n6] = (kidOK6[n7] = (b ? 1 : 0));
    }
}
