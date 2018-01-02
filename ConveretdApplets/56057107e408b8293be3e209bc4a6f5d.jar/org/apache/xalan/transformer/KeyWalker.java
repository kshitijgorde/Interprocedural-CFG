// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import org.w3c.dom.traversal.NodeIterator;
import org.apache.xpath.objects.XObject;
import org.apache.xml.utils.QName;
import java.util.Vector;
import javax.xml.transform.TransformerException;
import org.apache.xpath.DOMHelper;
import org.apache.xalan.templates.KeyDeclaration;
import org.w3c.dom.Node;
import org.apache.xpath.axes.LocPathIterator;
import org.w3c.dom.NamedNodeMap;
import org.apache.xpath.axes.DescendantOrSelfWalker;

public class KeyWalker extends DescendantOrSelfWalker
{
    transient NamedNodeMap m_attrs;
    transient boolean m_foundAttrs;
    transient int m_attrPos;
    String m_lookupKey;
    
    public KeyWalker(final LocPathIterator locPathIterator) {
        super(locPathIterator);
    }
    
    public short acceptNode(final Node testNode) {
        final KeyIterator ki = (KeyIterator)super.m_lpi;
        final Vector keys = ki.getKeyDeclarations();
        final QName name = ki.getName();
        try {
            final String lookupKey = this.m_lookupKey;
            for (int nDeclarations = keys.size(), i = 0; i < nDeclarations; ++i) {
                final KeyDeclaration kd = keys.elementAt(i);
                if (kd.getName().equals(name)) {
                    ki.getXPathContext().setNamespaceContext(ki.getPrefixResolver());
                    final double matchScore;
                    final double score = matchScore = kd.getMatch().getMatchScore(ki.getXPathContext(), testNode);
                    kd.getMatch();
                    if (matchScore != Double.NEGATIVE_INFINITY) {
                        final XObject xuse = kd.getUse().execute(ki.getXPathContext(), testNode, ki.getPrefixResolver());
                        if (xuse.getType() != 4) {
                            final String exprResult = xuse.str();
                            ((KeyIterator)super.m_lpi).addRefNode(exprResult, testNode);
                            if (lookupKey.equals(exprResult)) {
                                return 1;
                            }
                        }
                        else {
                            final NodeIterator nl = xuse.nodeset();
                            short result = -1;
                            Node useNode;
                            while ((useNode = nl.nextNode()) != null) {
                                super.m_lpi.getDOMHelper();
                                final String exprResult2 = DOMHelper.getNodeData(useNode);
                                ((KeyIterator)super.m_lpi).addRefNode(exprResult2, testNode);
                                if (exprResult2 != null && lookupKey.equals(exprResult2)) {
                                    result = 1;
                                }
                            }
                            if (result != -1) {
                                return result;
                            }
                        }
                    }
                }
            }
        }
        catch (TransformerException ex) {}
        return 2;
    }
    
    protected Node getNextNode() {
        if (!this.m_foundAttrs) {
            this.m_attrs = this.getCurrentNode().getAttributes();
            this.m_foundAttrs = true;
        }
        if (this.m_attrs != null) {
            if (this.m_attrPos < this.m_attrs.getLength()) {
                return this.m_attrs.item(this.m_attrPos++);
            }
            this.m_attrs = null;
        }
        final Node next = super.getNextNode();
        if (next != null) {
            this.m_foundAttrs = false;
        }
        return next;
    }
    
    public Node nextNode() {
        final Node node = super.nextNode();
        if (node == null) {
            ((KeyIterator)super.m_lpi).setLookForMoreNodes(false);
        }
        return node;
    }
    
    public void setRoot(final Node root) {
        this.m_attrs = null;
        this.m_foundAttrs = false;
        this.m_attrPos = 0;
        super.setRoot(root);
    }
}
