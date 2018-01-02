// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xml.utils.TreeWalker;
import org.w3c.dom.traversal.NodeIterator;
import org.apache.xalan.transformer.ResultTreeHandler;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import org.xml.sax.SAXException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Attr;
import org.apache.xalan.transformer.TreeWalker2Result;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xml.utils.QName;
import org.apache.xalan.transformer.TransformerImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.apache.xpath.XPath;

public class ElemCopyOf extends ElemTemplateElement
{
    public XPath m_selectExpression;
    
    public ElemCopyOf() {
        this.m_selectExpression = null;
    }
    
    public Node appendChild(final Node newChild) throws DOMException {
        this.error(4, new Object[] { newChild.getNodeName(), this.getNodeName() });
        return null;
    }
    
    public void execute(final TransformerImpl transformer, final Node sourceNode, final QName mode) throws TransformerException {
        try {
            if (TransformerImpl.S_DEBUG) {
                transformer.getTraceManager().fireTraceEvent(sourceNode, mode, this);
            }
            final XPathContext xctxt = transformer.getXPathContext();
            final XObject value = this.m_selectExpression.execute(xctxt, sourceNode, this);
            if (TransformerImpl.S_DEBUG) {
                transformer.getTraceManager().fireSelectedEvent(sourceNode, this, "select", this.m_selectExpression, value);
            }
            final ResultTreeHandler handler = transformer.getResultTreeHandler();
            if (value != null) {
                final int type = value.getType();
                switch (type) {
                    case 1:
                    case 2:
                    case 3: {
                        final String s = value.str();
                        handler.characters(s.toCharArray(), 0, s.length());
                        break;
                    }
                    case 4: {
                        final NodeIterator nl = value.nodeset();
                        final TreeWalker tw = new TreeWalker2Result(transformer, handler);
                        Node pos;
                        while ((pos = nl.nextNode()) != null) {
                            final short t = pos.getNodeType();
                            if (t == 9) {
                                for (Node child = pos.getFirstChild(); child != null; child = child.getNextSibling()) {
                                    tw.traverse(child);
                                }
                            }
                            else if (t == 2) {
                                handler.addAttribute((Attr)pos);
                            }
                            else {
                                tw.traverse(pos);
                            }
                        }
                        break;
                    }
                    case 5: {
                        handler.outputResultTreeFragment(value, transformer.getXPathContext());
                        break;
                    }
                    default: {
                        final String s = value.str();
                        handler.characters(s.toCharArray(), 0, s.length());
                        break;
                    }
                }
            }
        }
        catch (SAXException e) {
            throw new TransformerException(e);
        }
    }
    
    public String getNodeName() {
        return "copy-of";
    }
    
    public XPath getSelect() {
        return this.m_selectExpression;
    }
    
    public int getXSLToken() {
        return 74;
    }
    
    public void setSelect(final XPath expr) {
        this.m_selectExpression = expr;
    }
}
