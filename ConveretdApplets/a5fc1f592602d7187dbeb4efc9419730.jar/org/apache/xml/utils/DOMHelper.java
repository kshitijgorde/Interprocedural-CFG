// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

import org.w3c.dom.DocumentType;
import org.w3c.dom.Entity;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Text;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.apache.xml.dtm.ref.DTMNodeProxy;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.xml.res.XMLMessages;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import java.util.Vector;
import java.util.Hashtable;

public class DOMHelper
{
    Hashtable m_NSInfos;
    protected static final NSInfo m_NSInfoUnProcWithXMLNS;
    protected static final NSInfo m_NSInfoUnProcWithoutXMLNS;
    protected static final NSInfo m_NSInfoUnProcNoAncestorXMLNS;
    protected static final NSInfo m_NSInfoNullWithXMLNS;
    protected static final NSInfo m_NSInfoNullWithoutXMLNS;
    protected static final NSInfo m_NSInfoNullNoAncestorXMLNS;
    protected Vector m_candidateNoAncestorXMLNS;
    protected Document m_DOMFactory;
    
    public DOMHelper() {
        this.m_NSInfos = new Hashtable();
        this.m_candidateNoAncestorXMLNS = new Vector();
        this.m_DOMFactory = null;
    }
    
    public static Document createDocument() {
        try {
            final DocumentBuilderFactory dfactory = DocumentBuilderFactory.newInstance();
            dfactory.setNamespaceAware(true);
            dfactory.setValidating(true);
            final DocumentBuilder docBuilder = dfactory.newDocumentBuilder();
            final Document outNode = docBuilder.newDocument();
            return outNode;
        }
        catch (ParserConfigurationException pce) {
            throw new RuntimeException(XMLMessages.createXMLMessage("ER_CREATEDOCUMENT_NOT_SUPPORTED", null));
        }
    }
    
    public boolean shouldStripSourceNode(final Node textNode) throws TransformerException {
        return false;
    }
    
    public String getUniqueID(final Node node) {
        return "N" + Integer.toHexString(node.hashCode()).toUpperCase();
    }
    
    public static boolean isNodeAfter(final Node node1, final Node node2) {
        if (node1 == node2 || isNodeTheSame(node1, node2)) {
            return true;
        }
        boolean isNodeAfter = true;
        Node parent1 = getParentOfNode(node1);
        Node parent2 = getParentOfNode(node2);
        if (parent1 == parent2 || isNodeTheSame(parent1, parent2)) {
            if (null != parent1) {
                isNodeAfter = isNodeAfterSibling(parent1, node1, node2);
            }
        }
        else {
            int nParents1 = 2;
            int nParents2 = 2;
            while (parent1 != null) {
                ++nParents1;
                parent1 = getParentOfNode(parent1);
            }
            while (parent2 != null) {
                ++nParents2;
                parent2 = getParentOfNode(parent2);
            }
            Node startNode1 = node1;
            Node startNode2 = node2;
            if (nParents1 < nParents2) {
                for (int adjust = nParents2 - nParents1, i = 0; i < adjust; ++i) {
                    startNode2 = getParentOfNode(startNode2);
                }
            }
            else if (nParents1 > nParents2) {
                for (int adjust = nParents1 - nParents2, i = 0; i < adjust; ++i) {
                    startNode1 = getParentOfNode(startNode1);
                }
            }
            Node prevChild1 = null;
            Node prevChild2 = null;
            while (null != startNode1) {
                if (startNode1 == startNode2 || isNodeTheSame(startNode1, startNode2)) {
                    if (null == prevChild1) {
                        isNodeAfter = (nParents1 < nParents2);
                        break;
                    }
                    isNodeAfter = isNodeAfterSibling(startNode1, prevChild1, prevChild2);
                    break;
                }
                else {
                    prevChild1 = startNode1;
                    startNode1 = getParentOfNode(startNode1);
                    prevChild2 = startNode2;
                    startNode2 = getParentOfNode(startNode2);
                }
            }
        }
        return isNodeAfter;
    }
    
    public static boolean isNodeTheSame(final Node node1, final Node node2) {
        if (node1 instanceof DTMNodeProxy && node2 instanceof DTMNodeProxy) {
            return ((DTMNodeProxy)node1).equals(node2);
        }
        return node1 == node2;
    }
    
    private static boolean isNodeAfterSibling(final Node parent, final Node child1, final Node child2) {
        boolean isNodeAfterSibling = false;
        final short child1type = child1.getNodeType();
        final short child2type = child2.getNodeType();
        if (2 != child1type && 2 == child2type) {
            isNodeAfterSibling = false;
        }
        else if (2 == child1type && 2 != child2type) {
            isNodeAfterSibling = true;
        }
        else if (2 == child1type) {
            final NamedNodeMap children = parent.getAttributes();
            final int nNodes = children.getLength();
            boolean found1 = false;
            boolean found2 = false;
            for (int i = 0; i < nNodes; ++i) {
                final Node child3 = children.item(i);
                if (child1 == child3 || isNodeTheSame(child1, child3)) {
                    if (found2) {
                        isNodeAfterSibling = false;
                        break;
                    }
                    found1 = true;
                }
                else if (child2 == child3 || isNodeTheSame(child2, child3)) {
                    if (found1) {
                        isNodeAfterSibling = true;
                        break;
                    }
                    found2 = true;
                }
            }
        }
        else {
            Node child4 = parent.getFirstChild();
            boolean found3 = false;
            boolean found4 = false;
            while (null != child4) {
                if (child1 == child4 || isNodeTheSame(child1, child4)) {
                    if (found4) {
                        isNodeAfterSibling = false;
                        break;
                    }
                    found3 = true;
                }
                else if (child2 == child4 || isNodeTheSame(child2, child4)) {
                    if (found3) {
                        isNodeAfterSibling = true;
                        break;
                    }
                    found4 = true;
                }
                child4 = child4.getNextSibling();
            }
        }
        return isNodeAfterSibling;
    }
    
    public short getLevel(Node n) {
        short level = 1;
        while (null != (n = getParentOfNode(n))) {
            ++level;
        }
        return level;
    }
    
    public String getNamespaceForPrefix(final String prefix, final Element namespaceContext) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2         /* namespaceContext */
        //     1: astore          parent
        //     3: aconst_null    
        //     4: astore          namespace
        //     6: aload_1         /* prefix */
        //     7: ldc             "xml"
        //     9: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    12: ifeq            22
        //    15: ldc             "http://www.w3.org/XML/1998/namespace"
        //    17: astore          namespace
        //    19: goto            146
        //    22: aload_1         /* prefix */
        //    23: ldc             "xmlns"
        //    25: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    28: ifeq            38
        //    31: ldc             "http://www.w3.org/2000/xmlns/"
        //    33: astore          namespace
        //    35: goto            146
        //    38: aload_1         /* prefix */
        //    39: ldc             ""
        //    41: if_acmpne       49
        //    44: ldc             "xmlns"
        //    46: goto            68
        //    49: new             Ljava/lang/StringBuffer;
        //    52: dup            
        //    53: invokespecial   java/lang/StringBuffer.<init>:()V
        //    56: ldc             "xmlns:"
        //    58: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    61: aload_1         /* prefix */
        //    62: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    65: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //    68: astore          declname
        //    70: goto            116
        //    73: iload_3        
        //    74: iconst_1       
        //    75: if_icmpne       109
        //    78: aload           parent
        //    80: checkcast       Lorg/w3c/dom/Element;
        //    83: aload           declname
        //    85: invokeinterface org/w3c/dom/Element.getAttributeNode:(Ljava/lang/String;)Lorg/w3c/dom/Attr;
        //    90: astore          attr
        //    92: aload           attr
        //    94: ifnull          109
        //    97: aload           attr
        //    99: invokeinterface org/w3c/dom/Node.getNodeValue:()Ljava/lang/String;
        //   104: astore          namespace
        //   106: goto            146
        //   109: aload           parent
        //   111: invokestatic    org/apache/xml/utils/DOMHelper.getParentOfNode:(Lorg/w3c/dom/Node;)Lorg/w3c/dom/Node;
        //   114: astore          parent
        //   116: aconst_null    
        //   117: aload           parent
        //   119: if_acmpeq       146
        //   122: aconst_null    
        //   123: aload           namespace
        //   125: if_acmpne       146
        //   128: aload           parent
        //   130: invokeinterface org/w3c/dom/Node.getNodeType:()S
        //   135: dup            
        //   136: istore_3        /* type */
        //   137: iconst_1       
        //   138: if_icmpeq       73
        //   141: iload_3         /* type */
        //   142: iconst_5       
        //   143: if_icmpeq       73
        //   146: aload           namespace
        //   148: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name              Signature
        //  -----  ------  ----  ----------------  --------------------------------
        //  0      149     0     this              Lorg/apache/xml/utils/DOMHelper;
        //  0      149     1     prefix            Ljava/lang/String;
        //  0      149     2     namespaceContext  Lorg/w3c/dom/Element;
        //  137    12      3     type              I
        //  3      146     4     parent            Lorg/w3c/dom/Node;
        //  6      143     5     namespace         Ljava/lang/String;
        //  70     76      6     declname          Ljava/lang/String;
        //  92     17      7     attr              Lorg/w3c/dom/Attr;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public String getNamespaceOfNode(final Node n) {
        final short ntype = n.getNodeType();
        NSInfo nsInfo;
        boolean hasProcessedNS;
        if (2 != ntype) {
            final Object nsObj = this.m_NSInfos.get(n);
            nsInfo = ((nsObj == null) ? null : ((NSInfo)nsObj));
            hasProcessedNS = (nsInfo != null && nsInfo.m_hasProcessedNS);
        }
        else {
            hasProcessedNS = false;
            nsInfo = null;
        }
        String namespaceOfPrefix;
        if (hasProcessedNS) {
            namespaceOfPrefix = nsInfo.m_namespace;
        }
        else {
            namespaceOfPrefix = null;
            final String nodeName = n.getNodeName();
            final int indexOfNSSep = nodeName.indexOf(58);
            String prefix;
            if (2 == ntype) {
                if (indexOfNSSep <= 0) {
                    return namespaceOfPrefix;
                }
                prefix = nodeName.substring(0, indexOfNSSep);
            }
            else {
                prefix = ((indexOfNSSep >= 0) ? nodeName.substring(0, indexOfNSSep) : "");
            }
            boolean ancestorsHaveXMLNS = false;
            boolean nHasXMLNS = false;
            if (prefix.equals("xml")) {
                namespaceOfPrefix = "http://www.w3.org/XML/1998/namespace";
            }
            else {
                Node parent;
                Object nsObj2 = null;
                for (parent = n; null != parent && null == namespaceOfPrefix && (null == nsInfo || nsInfo.m_ancestorHasXMLNSAttrs != 2); nsInfo = ((nsObj2 == null) ? null : ((NSInfo)nsObj2))) {
                    final int parentType = parent.getNodeType();
                    if (null == nsInfo || nsInfo.m_hasXMLNSAttrs) {
                        boolean elementHasXMLNS = false;
                        if (parentType == 1) {
                            final NamedNodeMap nnm = parent.getAttributes();
                            for (int i = 0; i < nnm.getLength(); ++i) {
                                final Node attr = nnm.item(i);
                                final String aname = attr.getNodeName();
                                if (aname.charAt(0) == 'x') {
                                    final boolean isPrefix = aname.startsWith("xmlns:");
                                    if (aname.equals("xmlns") || isPrefix) {
                                        if (n == parent) {
                                            nHasXMLNS = true;
                                        }
                                        elementHasXMLNS = true;
                                        ancestorsHaveXMLNS = true;
                                        final String p = isPrefix ? aname.substring(6) : "";
                                        if (p.equals(prefix)) {
                                            namespaceOfPrefix = attr.getNodeValue();
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if (2 != parentType && null == nsInfo && n != parent) {
                            nsInfo = (elementHasXMLNS ? DOMHelper.m_NSInfoUnProcWithXMLNS : DOMHelper.m_NSInfoUnProcWithoutXMLNS);
                            this.m_NSInfos.put(parent, nsInfo);
                        }
                    }
                    if (2 == parentType) {
                        parent = getParentOfNode(parent);
                    }
                    else {
                        this.m_candidateNoAncestorXMLNS.addElement(parent);
                        this.m_candidateNoAncestorXMLNS.addElement(nsInfo);
                        parent = parent.getParentNode();
                    }
                    if (null != parent) {
                        nsObj2 = this.m_NSInfos.get(parent);
                    }
                }
                final int nCandidates = this.m_candidateNoAncestorXMLNS.size();
                if (nCandidates > 0) {
                    if (!ancestorsHaveXMLNS && null == parent) {
                        for (int j = 0; j < nCandidates; j += 2) {
                            final Object candidateInfo = this.m_candidateNoAncestorXMLNS.elementAt(j + 1);
                            if (candidateInfo == DOMHelper.m_NSInfoUnProcWithoutXMLNS) {
                                this.m_NSInfos.put(this.m_candidateNoAncestorXMLNS.elementAt(j), DOMHelper.m_NSInfoUnProcNoAncestorXMLNS);
                            }
                            else if (candidateInfo == DOMHelper.m_NSInfoNullWithoutXMLNS) {
                                this.m_NSInfos.put(this.m_candidateNoAncestorXMLNS.elementAt(j), DOMHelper.m_NSInfoNullNoAncestorXMLNS);
                            }
                        }
                    }
                    this.m_candidateNoAncestorXMLNS.removeAllElements();
                }
            }
            if (2 != ntype) {
                if (null == namespaceOfPrefix) {
                    if (ancestorsHaveXMLNS) {
                        if (nHasXMLNS) {
                            this.m_NSInfos.put(n, DOMHelper.m_NSInfoNullWithXMLNS);
                        }
                        else {
                            this.m_NSInfos.put(n, DOMHelper.m_NSInfoNullWithoutXMLNS);
                        }
                    }
                    else {
                        this.m_NSInfos.put(n, DOMHelper.m_NSInfoNullNoAncestorXMLNS);
                    }
                }
                else {
                    this.m_NSInfos.put(n, new NSInfo(namespaceOfPrefix, nHasXMLNS));
                }
            }
        }
        return namespaceOfPrefix;
    }
    
    public String getLocalNameOfNode(final Node n) {
        final String qname = n.getNodeName();
        final int index = qname.indexOf(58);
        return (index < 0) ? qname : qname.substring(index + 1);
    }
    
    public String getExpandedElementName(final Element elem) {
        final String namespace = this.getNamespaceOfNode(elem);
        return (null != namespace) ? (namespace + ":" + this.getLocalNameOfNode(elem)) : this.getLocalNameOfNode(elem);
    }
    
    public String getExpandedAttributeName(final Attr attr) {
        final String namespace = this.getNamespaceOfNode(attr);
        return (null != namespace) ? (namespace + ":" + this.getLocalNameOfNode(attr)) : this.getLocalNameOfNode(attr);
    }
    
    public boolean isIgnorableWhitespace(final Text node) {
        final boolean isIgnorable = false;
        return isIgnorable;
    }
    
    public Node getRoot(Node node) {
        Node root = null;
        while (node != null) {
            root = node;
            node = getParentOfNode(node);
        }
        return root;
    }
    
    public Node getRootNode(final Node n) {
        final int nt = n.getNodeType();
        return (9 == nt || 11 == nt) ? n : n.getOwnerDocument();
    }
    
    public boolean isNamespaceNode(final Node n) {
        if (2 == n.getNodeType()) {
            final String attrName = n.getNodeName();
            return attrName.startsWith("xmlns:") || attrName.equals("xmlns");
        }
        return false;
    }
    
    public static Node getParentOfNode(final Node node) throws RuntimeException {
        final short nodeType = node.getNodeType();
        Node parent;
        if (2 == nodeType) {
            final Document doc = node.getOwnerDocument();
            final DOMImplementation impl = doc.getImplementation();
            if (impl != null && impl.hasFeature("Core", "2.0")) {
                parent = ((Attr)node).getOwnerElement();
                return parent;
            }
            final Element rootElem = doc.getDocumentElement();
            if (null == rootElem) {
                throw new RuntimeException(XMLMessages.createXMLMessage("ER_CHILD_HAS_NO_OWNER_DOCUMENT_ELEMENT", null));
            }
            parent = locateAttrParent(rootElem, node);
        }
        else {
            parent = node.getParentNode();
        }
        return parent;
    }
    
    public Element getElementByID(final String id, final Document doc) {
        return null;
    }
    
    public String getUnparsedEntityURI(final String name, final Document doc) {
        String url = "";
        final DocumentType doctype = doc.getDoctype();
        if (null != doctype) {
            final NamedNodeMap entities = doctype.getEntities();
            if (null == entities) {
                return url;
            }
            final Entity entity = (Entity)entities.getNamedItem(name);
            if (null == entity) {
                return url;
            }
            final String notationName = entity.getNotationName();
            if (null != notationName) {
                url = entity.getSystemId();
                if (null == url) {
                    url = entity.getPublicId();
                }
            }
        }
        return url;
    }
    
    private static Node locateAttrParent(final Element elem, final Node attr) {
        Node parent = null;
        final Attr check = elem.getAttributeNode(attr.getNodeName());
        if (check == attr) {
            parent = elem;
        }
        if (null == parent) {
            for (Node node = elem.getFirstChild(); null != node; node = node.getNextSibling()) {
                if (1 == node.getNodeType()) {
                    parent = locateAttrParent((Element)node, attr);
                    if (null != parent) {
                        break;
                    }
                }
            }
        }
        return parent;
    }
    
    public void setDOMFactory(final Document domFactory) {
        this.m_DOMFactory = domFactory;
    }
    
    public Document getDOMFactory() {
        if (null == this.m_DOMFactory) {
            this.m_DOMFactory = createDocument();
        }
        return this.m_DOMFactory;
    }
    
    public static String getNodeData(final Node node) {
        final FastStringBuffer buf = StringBufferPool.get();
        String s;
        try {
            getNodeData(node, buf);
            s = ((buf.length() > 0) ? buf.toString() : "");
        }
        finally {
            StringBufferPool.free(buf);
        }
        return s;
    }
    
    public static void getNodeData(final Node node, final FastStringBuffer buf) {
        switch (node.getNodeType()) {
            case 1:
            case 9:
            case 11: {
                for (Node child = node.getFirstChild(); null != child; child = child.getNextSibling()) {
                    getNodeData(child, buf);
                }
                break;
            }
            case 3:
            case 4: {
                buf.append(node.getNodeValue());
                break;
            }
            case 2: {
                buf.append(node.getNodeValue());
            }
        }
    }
    
    static {
        m_NSInfoUnProcWithXMLNS = new NSInfo(false, true);
        m_NSInfoUnProcWithoutXMLNS = new NSInfo(false, false);
        m_NSInfoUnProcNoAncestorXMLNS = new NSInfo(false, false, 2);
        m_NSInfoNullWithXMLNS = new NSInfo(true, true);
        m_NSInfoNullWithoutXMLNS = new NSInfo(true, false);
        m_NSInfoNullNoAncestorXMLNS = new NSInfo(true, false, 2);
    }
}
