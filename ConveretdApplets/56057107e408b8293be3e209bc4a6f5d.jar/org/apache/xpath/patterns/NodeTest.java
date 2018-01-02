// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.patterns;

import org.apache.xpath.DOMHelper;
import org.apache.xml.utils.PrefixResolver;
import org.w3c.dom.Element;
import org.apache.xpath.WhitespaceStrippingElementMatcher;
import org.apache.xml.utils.XMLCharacterRecognizer;
import org.w3c.dom.Node;
import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import java.util.Vector;
import org.apache.xpath.objects.XNumber;
import org.apache.xpath.Expression;

public class NodeTest extends Expression
{
    public static final String WILD = "*";
    public static final String SUPPORTS_PRE_STRIPPING = "http://xml.apache.org/xpath/features/whitespace-pre-stripping";
    protected int m_whatToShow;
    public static final int SHOW_NAMESPACE = 4096;
    public static final int SHOW_BYFUNCTION = 65536;
    String m_namespace;
    String m_name;
    XNumber m_score;
    public static final XNumber SCORE_NODETEST;
    public static final XNumber SCORE_NSWILD;
    public static final XNumber SCORE_QNAME;
    public static final XNumber SCORE_OTHER;
    public static final XNumber SCORE_NONE;
    private boolean m_isTotallyWild;
    
    static {
        SCORE_NODETEST = new XNumber(-0.5);
        SCORE_NSWILD = new XNumber(-0.25);
        SCORE_QNAME = new XNumber(0.0);
        SCORE_OTHER = new XNumber(0.5);
        SCORE_NONE = new XNumber(Double.NEGATIVE_INFINITY);
    }
    
    public NodeTest() {
    }
    
    public NodeTest(final int whatToShow) {
        this.initNodeTest(whatToShow);
    }
    
    public NodeTest(final int whatToShow, final String namespace, final String name) {
        this.initNodeTest(whatToShow, namespace, name);
    }
    
    protected void calcScore() {
        if (this.m_namespace == null && this.m_name == null) {
            this.m_score = NodeTest.SCORE_NODETEST;
        }
        else if ((this.m_namespace == "*" || this.m_namespace == null) && this.m_name == "*") {
            this.m_score = NodeTest.SCORE_NODETEST;
        }
        else if (this.m_namespace != "*" && this.m_name == "*") {
            this.m_score = NodeTest.SCORE_NSWILD;
        }
        else {
            this.m_score = NodeTest.SCORE_QNAME;
        }
        this.m_isTotallyWild = (this.m_namespace == null && this.m_name == "*");
    }
    
    public static void debugWhatToShow(final int whatToShow) {
        final Vector v = new Vector();
        if ((whatToShow & 0x2) != 0x0) {
            v.addElement("SHOW_ATTRIBUTE");
        }
        if ((whatToShow & 0x8) != 0x0) {
            v.addElement("SHOW_CDATA_SECTION");
        }
        if ((whatToShow & 0x80) != 0x0) {
            v.addElement("SHOW_COMMENT");
        }
        if ((whatToShow & 0x100) != 0x0) {
            v.addElement("SHOW_DOCUMENT");
        }
        if ((whatToShow & 0x400) != 0x0) {
            v.addElement("SHOW_DOCUMENT_FRAGMENT");
        }
        if ((whatToShow & 0x200) != 0x0) {
            v.addElement("SHOW_DOCUMENT_TYPE");
        }
        if ((whatToShow & 0x1) != 0x0) {
            v.addElement("SHOW_ELEMENT");
        }
        if ((whatToShow & 0x20) != 0x0) {
            v.addElement("SHOW_ENTITY");
        }
        if ((whatToShow & 0x10) != 0x0) {
            v.addElement("SHOW_ENTITY_REFERENCE");
        }
        if ((whatToShow & 0x800) != 0x0) {
            v.addElement("SHOW_NOTATION");
        }
        if ((whatToShow & 0x40) != 0x0) {
            v.addElement("SHOW_PROCESSING_INSTRUCTION");
        }
        if ((whatToShow & 0x4) != 0x0) {
            v.addElement("SHOW_TEXT");
        }
        final int n = v.size();
        for (int i = 0; i < n; ++i) {
            if (i > 0) {
                System.out.print(" | ");
            }
            System.out.print(v.elementAt(i));
        }
        if (n == 0) {
            System.out.print("empty whatToShow: " + whatToShow);
        }
        System.out.println();
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        return this.execute(xctxt, xctxt.getCurrentNode());
    }
    
    public XObject execute(final XPathContext xctxt, final Node context) throws TransformerException {
        final short nodeType = context.getNodeType();
        if ((nodeType == 3 || nodeType == 4) && !context.isSupported("http://xml.apache.org/xpath/features/whitespace-pre-stripping", null)) {
            final Node parent = context.getParentNode();
            if (parent != null && parent.getNodeType() == 1) {
                final String data = context.getNodeValue();
                if (XMLCharacterRecognizer.isWhiteSpace(data)) {
                    final PrefixResolver resolver = xctxt.getNamespaceContext();
                    if (resolver instanceof WhitespaceStrippingElementMatcher) {
                        final WhitespaceStrippingElementMatcher wsem = (WhitespaceStrippingElementMatcher)resolver;
                        if (wsem.shouldStripWhiteSpace(xctxt, (Element)parent)) {
                            return NodeTest.SCORE_NONE;
                        }
                    }
                }
            }
        }
        if (this.m_whatToShow == -1) {
            return this.m_score;
        }
        final int nodeBit = this.m_whatToShow & 1 << nodeType - 1;
        switch (nodeBit) {
            case 256:
            case 1024: {
                return NodeTest.SCORE_OTHER;
            }
            case 128: {
                return this.m_score;
            }
            case 4:
            case 8: {
                return xctxt.getDOMHelper().shouldStripSourceNode(context) ? NodeTest.SCORE_NONE : this.m_score;
            }
            case 64: {
                return subPartMatch(context.getNodeName(), this.m_name) ? this.m_score : NodeTest.SCORE_NONE;
            }
            case 2: {
                final int isNamespace = this.m_whatToShow & 0x1000;
                if (isNamespace == 0) {
                    final DOMHelper dh = xctxt.getDOMHelper();
                    if (!dh.isNamespaceNode(context)) {
                        return (this.m_isTotallyWild || (subPartMatch(dh.getNamespaceOfNode(context), this.m_namespace) && subPartMatch(dh.getLocalNameOfNode(context), this.m_name))) ? this.m_score : NodeTest.SCORE_NONE;
                    }
                    return NodeTest.SCORE_NONE;
                }
                else {
                    if (xctxt.getDOMHelper().isNamespaceNode(context)) {
                        final String ns = context.getNodeValue();
                        return subPartMatch(ns, this.m_name) ? this.m_score : NodeTest.SCORE_NONE;
                    }
                    return NodeTest.SCORE_NONE;
                }
                break;
            }
            case 1: {
                final DOMHelper dh2 = xctxt.getDOMHelper();
                return (this.m_isTotallyWild || (subPartMatch(dh2.getNamespaceOfNode(context), this.m_namespace) && subPartMatch(dh2.getLocalNameOfNode(context), this.m_name))) ? this.m_score : NodeTest.SCORE_NONE;
            }
            default: {
                return NodeTest.SCORE_NONE;
            }
        }
    }
    
    public double getDefaultScore() {
        return this.m_score.num();
    }
    
    public String getLocalName() {
        return this.m_name;
    }
    
    public String getNamespace() {
        return this.m_namespace;
    }
    
    public int getWhatToShow() {
        return this.m_whatToShow;
    }
    
    public void initNodeTest(final int whatToShow) {
        this.m_whatToShow = whatToShow;
        this.calcScore();
    }
    
    public void initNodeTest(final int whatToShow, final String namespace, final String name) {
        this.m_whatToShow = whatToShow;
        this.m_namespace = namespace;
        this.m_name = name;
        this.calcScore();
    }
    
    private static final boolean subPartMatch(final String p, final String t) {
        return p == t || (p != null && (t == "*" || p.equals(t)));
    }
}
