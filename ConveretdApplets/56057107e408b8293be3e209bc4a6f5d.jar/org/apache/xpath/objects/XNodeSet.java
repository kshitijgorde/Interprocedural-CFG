// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.objects;

import org.w3c.dom.DocumentFragment;
import org.apache.xpath.XPathContext;
import org.apache.xpath.axes.ContextNodeList;
import org.w3c.dom.Text;
import org.apache.xpath.DOMHelper;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.StringVector;
import org.w3c.dom.traversal.NodeIterator;
import org.w3c.dom.Node;
import org.apache.xpath.NodeSet;

public class XNodeSet extends XObject
{
    static LessThanComparator S_LT;
    static LessThanOrEqualComparator S_LTE;
    static GreaterThanComparator S_GT;
    static GreaterThanOrEqualComparator S_GTE;
    static EqualComparator S_EQ;
    static NotEqualComparator S_NEQ;
    
    static {
        XNodeSet.S_LT = new LessThanComparator();
        XNodeSet.S_LTE = new LessThanOrEqualComparator();
        XNodeSet.S_GT = new GreaterThanComparator();
        XNodeSet.S_GTE = new GreaterThanOrEqualComparator();
        XNodeSet.S_EQ = new EqualComparator();
        XNodeSet.S_NEQ = new NotEqualComparator();
    }
    
    public XNodeSet() {
        super(new NodeSet());
    }
    
    public XNodeSet(final Node n) {
        super(new NodeSet());
        if (n != null) {
            ((NodeSet)super.m_obj).addNode(n);
        }
    }
    
    public XNodeSet(final NodeIterator val) {
        super(val);
    }
    
    public boolean bool() {
        return this.nodeset().nextNode() != null;
    }
    
    public boolean compare(final XObject obj2, final Comparator comparator) throws TransformerException {
        boolean result = false;
        final int type = obj2.getType();
        if (type == 4) {
            final NodeIterator list1 = this.nodeset();
            final NodeIterator list2 = ((XNodeSet)obj2).nodeset();
            StringVector node2Strings = null;
            Node node1;
            while ((node1 = list1.nextNode()) != null) {
                final String s1 = getStringFromNode(node1);
                if (node2Strings == null) {
                    Node node2;
                    while ((node2 = list2.nextNode()) != null) {
                        final String s2 = getStringFromNode(node2);
                        if (comparator.compareStrings(s1, s2)) {
                            result = true;
                            break;
                        }
                        if (node2Strings == null) {
                            node2Strings = new StringVector();
                        }
                        node2Strings.addElement(s2);
                    }
                }
                else {
                    for (int n = node2Strings.size(), i = 0; i < n; ++i) {
                        if (comparator.compareStrings(s1, node2Strings.elementAt(i))) {
                            result = true;
                            break;
                        }
                    }
                }
            }
        }
        else if (type == 1) {
            final double num1 = this.bool() ? 1.0 : 0.0;
            final double num2 = obj2.num();
            result = comparator.compareNumbers(num1, num2);
        }
        else if (type == 2) {
            final NodeIterator list1 = this.nodeset();
            final double num3 = obj2.num();
            Node node3;
            while ((node3 = list1.nextNode()) != null) {
                final double num4 = getNumberFromNode(node3);
                if (comparator.compareNumbers(num4, num3)) {
                    result = true;
                    break;
                }
            }
        }
        else if (type == 5) {
            final double num5 = obj2.num();
            if (!Double.isNaN(num5)) {
                final NodeIterator list3 = this.nodeset();
                Node node3;
                while ((node3 = list3.nextNode()) != null) {
                    final double num4 = getNumberFromNode(node3);
                    if (comparator.compareNumbers(num4, num5)) {
                        result = true;
                        break;
                    }
                }
            }
            else {
                final String s3 = obj2.str();
                final NodeIterator list4 = this.nodeset();
                Node node4;
                while ((node4 = list4.nextNode()) != null) {
                    final String s4 = getStringFromNode(node4);
                    if (comparator.compareStrings(s4, s3)) {
                        result = true;
                        break;
                    }
                }
            }
        }
        else if (type == 3) {
            final String s5 = obj2.str();
            final NodeIterator list5 = this.nodeset();
            Node node5;
            while ((node5 = list5.nextNode()) != null) {
                final String s6 = getStringFromNode(node5);
                if (comparator.compareStrings(s6, s5)) {
                    result = true;
                    break;
                }
            }
        }
        else {
            result = comparator.compareNumbers(this.num(), obj2.num());
        }
        return result;
    }
    
    public boolean equals(final XObject obj2) throws TransformerException {
        return this.compare(obj2, XNodeSet.S_EQ);
    }
    
    public static double getNumberFromNode(final Node n) {
        return XString.castToNum(getStringFromNode(n));
    }
    
    public static String getStringFromNode(final Node n) {
        switch (n.getNodeType()) {
            case 1:
            case 9: {
                return DOMHelper.getNodeData(n);
            }
            case 3:
            case 4: {
                return ((Text)n).getData();
            }
            case 2:
            case 7:
            case 8: {
                return n.getNodeValue();
            }
            default: {
                return DOMHelper.getNodeData(n);
            }
        }
    }
    
    public int getType() {
        return 4;
    }
    
    public String getTypeString() {
        return "#NODESET";
    }
    
    public boolean greaterThan(final XObject obj2) throws TransformerException {
        return this.compare(obj2, XNodeSet.S_GT);
    }
    
    public boolean greaterThanOrEqual(final XObject obj2) throws TransformerException {
        return this.compare(obj2, XNodeSet.S_GTE);
    }
    
    public boolean lessThan(final XObject obj2) throws TransformerException {
        return this.compare(obj2, XNodeSet.S_LT);
    }
    
    public boolean lessThanOrEqual(final XObject obj2) throws TransformerException {
        return this.compare(obj2, XNodeSet.S_LTE);
    }
    
    public NodeSet mutableNodeset() {
        NodeSet mnl;
        if (super.m_obj instanceof NodeSet) {
            mnl = (NodeSet)super.m_obj;
        }
        else {
            mnl = new NodeSet(this.nodeset());
            super.m_obj = mnl;
        }
        return mnl;
    }
    
    public NodeIterator nodeset() {
        final NodeIterator ns = (NodeIterator)super.m_obj;
        if (ns instanceof ContextNodeList) {
            if (((ContextNodeList)ns).isFresh()) {
                return ns;
            }
            try {
                return ((ContextNodeList)ns).cloneWithReset();
            }
            catch (CloneNotSupportedException cnse) {
                throw new RuntimeException(cnse.getMessage());
            }
        }
        return ns;
    }
    
    public boolean notEquals(final XObject obj2) throws TransformerException {
        return this.compare(obj2, XNodeSet.S_NEQ);
    }
    
    public double num() {
        final NodeIterator nl = this.nodeset();
        final Node node = nl.nextNode();
        return (node != null) ? getNumberFromNode(node) : Double.NaN;
    }
    
    public DocumentFragment rtree(final XPathContext support) {
        final DocumentFragment frag = support.getDOMHelper().getDOMFactory().createDocumentFragment();
        final NodeIterator nl = this.nodeset();
        Node node;
        while ((node = nl.nextNode()) != null) {
            frag.appendChild(node.cloneNode(true));
        }
        return frag;
    }
    
    public String str() {
        final NodeIterator nl = this.nodeset();
        final Node node = nl.nextNode();
        return (node != null) ? getStringFromNode(node) : "";
    }
}
