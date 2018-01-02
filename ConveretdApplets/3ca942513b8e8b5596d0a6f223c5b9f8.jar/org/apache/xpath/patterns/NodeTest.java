// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.patterns;

import org.apache.xpath.XPathVisitor;
import org.apache.xpath.ExpressionOwner;
import javax.xml.transform.TransformerException;
import org.apache.xml.dtm.DTM;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import java.util.Vector;
import org.apache.xpath.objects.XNumber;
import org.apache.xpath.Expression;

public class NodeTest extends Expression
{
    static final long serialVersionUID = -5736721866747906182L;
    public static final String WILD = "*";
    public static final String SUPPORTS_PRE_STRIPPING = "http://xml.apache.org/xpath/features/whitespace-pre-stripping";
    protected int m_whatToShow;
    public static final int SHOW_BYFUNCTION = 65536;
    String m_namespace;
    protected String m_name;
    XNumber m_score;
    public static final XNumber SCORE_NODETEST;
    public static final XNumber SCORE_NSWILD;
    public static final XNumber SCORE_QNAME;
    public static final XNumber SCORE_OTHER;
    public static final XNumber SCORE_NONE;
    private boolean m_isTotallyWild;
    
    public int getWhatToShow() {
        return this.m_whatToShow;
    }
    
    public void setWhatToShow(final int what) {
        this.m_whatToShow = what;
    }
    
    public String getNamespace() {
        return this.m_namespace;
    }
    
    public void setNamespace(final String ns) {
        this.m_namespace = ns;
    }
    
    public String getLocalName() {
        return (null == this.m_name) ? "" : this.m_name;
    }
    
    public void setLocalName(final String name) {
        this.m_name = name;
    }
    
    public NodeTest(final int whatToShow, final String namespace, final String name) {
        this.initNodeTest(whatToShow, namespace, name);
    }
    
    public NodeTest(final int whatToShow) {
        this.initNodeTest(whatToShow);
    }
    
    public boolean deepEquals(final Expression expr) {
        if (!this.isSameClass(expr)) {
            return false;
        }
        final NodeTest nt = (NodeTest)expr;
        if (null != nt.m_name) {
            if (null == this.m_name) {
                return false;
            }
            if (!nt.m_name.equals(this.m_name)) {
                return false;
            }
        }
        else if (null != this.m_name) {
            return false;
        }
        if (null != nt.m_namespace) {
            if (null == this.m_namespace) {
                return false;
            }
            if (!nt.m_namespace.equals(this.m_namespace)) {
                return false;
            }
        }
        else if (null != this.m_namespace) {
            return false;
        }
        return this.m_whatToShow == nt.m_whatToShow && this.m_isTotallyWild == nt.m_isTotallyWild;
    }
    
    public NodeTest() {
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
    
    public XNumber getStaticScore() {
        return this.m_score;
    }
    
    public void setStaticScore(final XNumber score) {
        this.m_score = score;
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
    
    public double getDefaultScore() {
        return this.m_score.num();
    }
    
    public static int getNodeTypeTest(final int whatToShow) {
        if (0x0 != (whatToShow & 0x1)) {
            return 1;
        }
        if (0x0 != (whatToShow & 0x2)) {
            return 2;
        }
        if (0x0 != (whatToShow & 0x4)) {
            return 3;
        }
        if (0x0 != (whatToShow & 0x100)) {
            return 9;
        }
        if (0x0 != (whatToShow & 0x400)) {
            return 11;
        }
        if (0x0 != (whatToShow & 0x1000)) {
            return 13;
        }
        if (0x0 != (whatToShow & 0x80)) {
            return 8;
        }
        if (0x0 != (whatToShow & 0x40)) {
            return 7;
        }
        if (0x0 != (whatToShow & 0x200)) {
            return 10;
        }
        if (0x0 != (whatToShow & 0x20)) {
            return 6;
        }
        if (0x0 != (whatToShow & 0x10)) {
            return 5;
        }
        if (0x0 != (whatToShow & 0x800)) {
            return 12;
        }
        if (0x0 != (whatToShow & 0x8)) {
            return 4;
        }
        return 0;
    }
    
    public static void debugWhatToShow(final int whatToShow) {
        final Vector v = new Vector();
        if (0x0 != (whatToShow & 0x2)) {
            v.addElement("SHOW_ATTRIBUTE");
        }
        if (0x0 != (whatToShow & 0x1000)) {
            v.addElement("SHOW_NAMESPACE");
        }
        if (0x0 != (whatToShow & 0x8)) {
            v.addElement("SHOW_CDATA_SECTION");
        }
        if (0x0 != (whatToShow & 0x80)) {
            v.addElement("SHOW_COMMENT");
        }
        if (0x0 != (whatToShow & 0x100)) {
            v.addElement("SHOW_DOCUMENT");
        }
        if (0x0 != (whatToShow & 0x400)) {
            v.addElement("SHOW_DOCUMENT_FRAGMENT");
        }
        if (0x0 != (whatToShow & 0x200)) {
            v.addElement("SHOW_DOCUMENT_TYPE");
        }
        if (0x0 != (whatToShow & 0x1)) {
            v.addElement("SHOW_ELEMENT");
        }
        if (0x0 != (whatToShow & 0x20)) {
            v.addElement("SHOW_ENTITY");
        }
        if (0x0 != (whatToShow & 0x10)) {
            v.addElement("SHOW_ENTITY_REFERENCE");
        }
        if (0x0 != (whatToShow & 0x800)) {
            v.addElement("SHOW_NOTATION");
        }
        if (0x0 != (whatToShow & 0x40)) {
            v.addElement("SHOW_PROCESSING_INSTRUCTION");
        }
        if (0x0 != (whatToShow & 0x4)) {
            v.addElement("SHOW_TEXT");
        }
        final int n = v.size();
        for (int i = 0; i < n; ++i) {
            if (i > 0) {
                System.out.print(" | ");
            }
            System.out.print(v.elementAt(i));
        }
        if (0 == n) {
            System.out.print("empty whatToShow: " + whatToShow);
        }
        System.out.println();
    }
    
    private static final boolean subPartMatch(final String p, final String t) {
        return p == t || (null != p && (t == "*" || p.equals(t)));
    }
    
    private static final boolean subPartMatchNS(final String p, final String t) {
        return p == t || (null != p && ((p.length() > 0) ? (t == "*" || p.equals(t)) : (null == t)));
    }
    
    public XObject execute(final XPathContext xctxt, final int context) throws TransformerException {
        final DTM dtm = xctxt.getDTM(context);
        final short nodeType = dtm.getNodeType(context);
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
                return this.m_score;
            }
            case 64: {
                return subPartMatch(dtm.getNodeName(context), this.m_name) ? this.m_score : NodeTest.SCORE_NONE;
            }
            case 4096: {
                final String ns = dtm.getLocalName(context);
                return subPartMatch(ns, this.m_name) ? this.m_score : NodeTest.SCORE_NONE;
            }
            case 1:
            case 2: {
                return (this.m_isTotallyWild || (subPartMatchNS(dtm.getNamespaceURI(context), this.m_namespace) && subPartMatch(dtm.getLocalName(context), this.m_name))) ? this.m_score : NodeTest.SCORE_NONE;
            }
            default: {
                return NodeTest.SCORE_NONE;
            }
        }
    }
    
    public XObject execute(final XPathContext xctxt, final int context, final DTM dtm, final int expType) throws TransformerException {
        if (this.m_whatToShow == -1) {
            return this.m_score;
        }
        final int nodeBit = this.m_whatToShow & 1 << dtm.getNodeType(context) - 1;
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
                return this.m_score;
            }
            case 64: {
                return subPartMatch(dtm.getNodeName(context), this.m_name) ? this.m_score : NodeTest.SCORE_NONE;
            }
            case 4096: {
                final String ns = dtm.getLocalName(context);
                return subPartMatch(ns, this.m_name) ? this.m_score : NodeTest.SCORE_NONE;
            }
            case 1:
            case 2: {
                return (this.m_isTotallyWild || (subPartMatchNS(dtm.getNamespaceURI(context), this.m_namespace) && subPartMatch(dtm.getLocalName(context), this.m_name))) ? this.m_score : NodeTest.SCORE_NONE;
            }
            default: {
                return NodeTest.SCORE_NONE;
            }
        }
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        return this.execute(xctxt, xctxt.getCurrentNode());
    }
    
    public void fixupVariables(final Vector vars, final int globalsSize) {
    }
    
    public void callVisitors(final ExpressionOwner owner, final XPathVisitor visitor) {
        this.assertion(false, "callVisitors should not be called for this object!!!");
    }
    
    static {
        SCORE_NODETEST = new XNumber(-0.5);
        SCORE_NSWILD = new XNumber(-0.25);
        SCORE_QNAME = new XNumber(0.0);
        SCORE_OTHER = new XNumber(0.5);
        SCORE_NONE = new XNumber(Double.NEGATIVE_INFINITY);
    }
}
