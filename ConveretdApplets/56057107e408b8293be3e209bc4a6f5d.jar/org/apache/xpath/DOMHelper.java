// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath;

import javax.xml.transform.TransformerException;
import org.w3c.dom.Text;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Entity;
import org.w3c.dom.DOMImplementation;
import org.apache.xml.utils.FastStringBuffer;
import org.apache.xml.utils.StringBufferPool;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.xalan.res.XSLMessages;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import java.util.Vector;
import org.apache.xml.utils.NSInfo;
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
    
    static {
        m_NSInfoUnProcWithXMLNS = new NSInfo(false, true);
        m_NSInfoUnProcWithoutXMLNS = new NSInfo(false, false);
        m_NSInfoUnProcNoAncestorXMLNS = new NSInfo(false, false, 2);
        m_NSInfoNullWithXMLNS = new NSInfo(true, true);
        m_NSInfoNullWithoutXMLNS = new NSInfo(true, false);
        m_NSInfoNullNoAncestorXMLNS = new NSInfo(true, false, 2);
    }
    
    public DOMHelper() {
        this.m_NSInfos = new Hashtable();
        this.m_candidateNoAncestorXMLNS = new Vector();
        this.m_DOMFactory = null;
    }
    
    public Document createDocument() {
        try {
            final DocumentBuilderFactory dfactory = DocumentBuilderFactory.newInstance();
            dfactory.setNamespaceAware(true);
            dfactory.setValidating(true);
            final DocumentBuilder docBuilder = dfactory.newDocumentBuilder();
            final Document outNode = docBuilder.newDocument();
            return outNode;
        }
        catch (ParserConfigurationException ex) {
            throw new RuntimeException(XSLMessages.createXPATHMessage(54, null));
        }
    }
    
    public Document getDOMFactory() {
        if (this.m_DOMFactory == null) {
            this.m_DOMFactory = this.createDocument();
        }
        return this.m_DOMFactory;
    }
    
    public Element getElementByID(final String id, final Document doc) {
        return null;
    }
    
    public String getExpandedAttributeName(final Attr attr) {
        final String namespace = this.getNamespaceOfNode(attr);
        return (namespace != null) ? (String.valueOf(namespace) + ":" + this.getLocalNameOfNode(attr)) : this.getLocalNameOfNode(attr);
    }
    
    public String getExpandedElementName(final Element elem) {
        final String namespace = this.getNamespaceOfNode(elem);
        return (namespace != null) ? (String.valueOf(namespace) + ":" + this.getLocalNameOfNode(elem)) : this.getLocalNameOfNode(elem);
    }
    
    public short getLevel(Node n) {
        short level = 1;
        while ((n = this.getParentOfNode(n)) != null) {
            ++level;
        }
        return level;
    }
    
    public String getLocalNameOfNode(final Node n) {
        final String qname = n.getNodeName();
        final int index = qname.indexOf(58);
        return (index < 0) ? qname : qname.substring(index + 1);
    }
    
    public String getNamespaceForPrefix(final String prefix, final Element namespaceContext) {
        Node parent = namespaceContext;
        String namespace = null;
        if (prefix.equals("xml")) {
            namespace = "http://www.w3.org/XML/1998/namespace";
        }
        else if (prefix.equals("xmlns")) {
            namespace = "http://www.w3.org/2000/xmlns/";
        }
        else {
            final String declname = (prefix == "") ? "xmlns" : ("xmlns:" + prefix);
            int type;
            while (parent != null && namespace == null && ((type = parent.getNodeType()) == 1 || type == 5)) {
                if (type == 1) {
                    final Attr attr = ((Element)parent).getAttributeNode(declname);
                    if (attr != null) {
                        namespace = attr.getNodeValue();
                        break;
                    }
                }
                parent = this.getParentOfNode(parent);
            }
        }
        return namespace;
    }
    
    public String getNamespaceOfNode(final Node n) {
        final short ntype = n.getNodeType();
        NSInfo nsInfo;
        boolean hasProcessedNS;
        if (ntype != 2) {
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
            if (ntype == 2) {
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
                for (parent = n; parent != null && namespaceOfPrefix == null && (nsInfo == null || nsInfo.m_ancestorHasXMLNSAttrs != 2); nsInfo = ((nsObj2 == null) ? null : ((NSInfo)nsObj2))) {
                    final int parentType = parent.getNodeType();
                    if (nsInfo == null || nsInfo.m_hasXMLNSAttrs) {
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
                        if (parentType != 2 && nsInfo == null && n != parent) {
                            nsInfo = (elementHasXMLNS ? DOMHelper.m_NSInfoUnProcWithXMLNS : DOMHelper.m_NSInfoUnProcWithoutXMLNS);
                            this.m_NSInfos.put(parent, nsInfo);
                        }
                    }
                    if (parentType == 2) {
                        parent = this.getParentOfNode(parent);
                    }
                    else {
                        this.m_candidateNoAncestorXMLNS.addElement(parent);
                        this.m_candidateNoAncestorXMLNS.addElement(nsInfo);
                        parent = parent.getParentNode();
                    }
                    if (parent != null) {
                        nsObj2 = this.m_NSInfos.get(parent);
                    }
                }
                final int nCandidates = this.m_candidateNoAncestorXMLNS.size();
                if (nCandidates > 0) {
                    if (!ancestorsHaveXMLNS && parent == null) {
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
            if (ntype != 2) {
                if (namespaceOfPrefix == null) {
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
                for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
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
                break;
            }
        }
    }
    
    public Node getParentOfNode(final Node node) throws RuntimeException {
        final short nodeType = node.getNodeType();
        Node parent;
        if (nodeType == 2) {
            final Document doc = node.getOwnerDocument();
            final DOMImplementation impl = doc.getImplementation();
            if (impl != null && impl.hasFeature("Core", "2.0")) {
                parent = ((Attr)node).getOwnerElement();
                return parent;
            }
            final Element rootElem = doc.getDocumentElement();
            if (rootElem == null) {
                throw new RuntimeException(XSLMessages.createXPATHMessage(56, null));
            }
            parent = this.locateAttrParent(rootElem, node);
        }
        else {
            parent = node.getParentNode();
        }
        return parent;
    }
    
    public Node getRoot(Node node) {
        Node root = null;
        while (node != null) {
            root = node;
            node = this.getParentOfNode(node);
        }
        return root;
    }
    
    public Node getRootNode(final Node n) {
        final int nt = n.getNodeType();
        return (nt == 9 || nt == 11) ? n : n.getOwnerDocument();
    }
    
    public String getUniqueID(final Node node) {
        return "N" + Integer.toHexString(node.hashCode()).toUpperCase();
    }
    
    public String getUnparsedEntityURI(final String name, final Document doc) {
        String url = "";
        final DocumentType doctype = doc.getDoctype();
        if (doctype != null) {
            final NamedNodeMap entities = doctype.getEntities();
            if (entities == null) {
                return url;
            }
            final Entity entity = (Entity)entities.getNamedItem(name);
            if (entity == null) {
                return url;
            }
            final String notationName = entity.getNotationName();
            if (notationName != null) {
                url = entity.getSystemId();
                if (url == null) {
                    url = entity.getPublicId();
                }
            }
        }
        return url;
    }
    
    public boolean isIgnorableWhitespace(final Text node) {
        final boolean isIgnorable = false;
        return isIgnorable;
    }
    
    public boolean isNamespaceNode(final Node n) {
        if (n.getNodeType() == 2) {
            final String attrName = n.getNodeName();
            return attrName.startsWith("xmlns:") || attrName.equals("xmlns");
        }
        return false;
    }
    
    public boolean isNodeAfter(final Node node1, final Node node2) {
        if (node1 == node2) {
            return true;
        }
        boolean isNodeAfter = true;
        Node parent1 = this.getParentOfNode(node1);
        Node parent2 = this.getParentOfNode(node2);
        if (parent1 == parent2) {
            if (parent1 != null) {
                isNodeAfter = isNodeAfterSibling(parent1, node1, node2);
            }
        }
        else {
            int nParents1 = 2;
            int nParents2 = 2;
            while (parent1 != null) {
                ++nParents1;
                parent1 = this.getParentOfNode(parent1);
            }
            while (parent2 != null) {
                ++nParents2;
                parent2 = this.getParentOfNode(parent2);
            }
            Node startNode1 = node1;
            Node startNode2 = node2;
            if (nParents1 < nParents2) {
                for (int adjust = nParents2 - nParents1, i = 0; i < adjust; ++i) {
                    startNode2 = this.getParentOfNode(startNode2);
                }
            }
            else if (nParents1 > nParents2) {
                for (int adjust = nParents1 - nParents2, i = 0; i < adjust; ++i) {
                    startNode1 = this.getParentOfNode(startNode1);
                }
            }
            Node prevChild1 = null;
            Node prevChild2 = null;
            while (startNode1 != null) {
                if (startNode1 == startNode2) {
                    if (prevChild1 == null) {
                        isNodeAfter = (nParents1 < nParents2);
                        break;
                    }
                    isNodeAfter = isNodeAfterSibling(startNode1, prevChild1, prevChild2);
                    break;
                }
                else {
                    prevChild1 = startNode1;
                    startNode1 = this.getParentOfNode(startNode1);
                    prevChild2 = startNode2;
                    startNode2 = this.getParentOfNode(startNode2);
                }
            }
        }
        return isNodeAfter;
    }
    
    private static boolean isNodeAfterSibling(final Node parent, final Node child1, final Node child2) {
        boolean isNodeAfterSibling = false;
        final short child1type = child1.getNodeType();
        final short child2type = child2.getNodeType();
        if (child1type != 2 && child2type == 2) {
            isNodeAfterSibling = false;
        }
        else if (child1type == 2 && child2type != 2) {
            isNodeAfterSibling = true;
        }
        else if (child1type == 2) {
            final NamedNodeMap children = parent.getAttributes();
            final int nNodes = children.getLength();
            boolean found1 = false;
            boolean found2 = false;
            for (int i = 0; i < nNodes; ++i) {
                final Node child3 = children.item(i);
                if (child1 == child3) {
                    if (found2) {
                        isNodeAfterSibling = false;
                        break;
                    }
                    found1 = true;
                }
                else if (child2 == child3) {
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
            while (child4 != null) {
                if (child1 == child4) {
                    if (found4) {
                        isNodeAfterSibling = false;
                        break;
                    }
                    found3 = true;
                }
                else if (child2 == child4) {
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
    
    private Node locateAttrParent(final Element elem, final Node attr) {
        Node parent = null;
        final Attr check = elem.getAttributeNode(attr.getNodeName());
        if (check == attr) {
            parent = elem;
        }
        if (parent == null) {
            for (Node node = elem.getFirstChild(); node != null; node = node.getNextSibling()) {
                if (node.getNodeType() == 1) {
                    parent = this.locateAttrParent((Element)node, attr);
                    if (parent != null) {
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
    
    public boolean shouldStripSourceNode(final Node textNode) throws TransformerException {
        return false;
    }
}
