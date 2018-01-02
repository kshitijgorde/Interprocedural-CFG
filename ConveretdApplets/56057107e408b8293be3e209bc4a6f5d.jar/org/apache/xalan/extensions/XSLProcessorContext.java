// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.extensions;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import org.apache.xalan.transformer.ResultTreeHandler;
import org.xml.sax.SAXException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.traversal.NodeIterator;
import org.apache.xpath.objects.XNodeSet;
import org.apache.xpath.objects.XRTreeFrag;
import org.w3c.dom.DocumentFragment;
import org.apache.xpath.objects.XNumber;
import org.apache.xpath.objects.XBoolean;
import org.apache.xpath.objects.XString;
import org.apache.xpath.objects.XObject;
import org.apache.xml.utils.QName;
import org.w3c.dom.Node;
import org.apache.xalan.templates.Stylesheet;
import org.apache.xalan.transformer.TransformerImpl;

public class XSLProcessorContext
{
    private TransformerImpl transformer;
    private Stylesheet stylesheetTree;
    private Node sourceTree;
    private Node sourceNode;
    private QName mode;
    
    public XSLProcessorContext(final TransformerImpl transformer, final Stylesheet stylesheetTree, final Node sourceTree, final Node sourceNode, final QName mode) {
        this.transformer = transformer;
        this.stylesheetTree = stylesheetTree;
        this.mode = mode;
        this.sourceTree = sourceTree;
        this.sourceNode = sourceNode;
    }
    
    public Node getContextNode() {
        return this.sourceNode;
    }
    
    public QName getMode() {
        return this.mode;
    }
    
    public Node getSourceTree() {
        return this.sourceTree;
    }
    
    public Stylesheet getStylesheet() {
        return this.stylesheetTree;
    }
    
    public TransformerImpl getTransformer() {
        return this.transformer;
    }
    
    public void outputToResultTree(final Stylesheet stylesheetTree, final Object obj) throws TransformerException, MalformedURLException, FileNotFoundException, IOException {
        try {
            final ResultTreeHandler rtreeHandler = this.transformer.getResultTreeHandler();
            XObject value;
            if (obj instanceof XObject) {
                value = (XObject)obj;
            }
            else if (obj instanceof String) {
                value = new XString((String)obj);
            }
            else if (obj instanceof Boolean) {
                value = new XBoolean((boolean)obj);
            }
            else if (obj instanceof Double) {
                value = new XNumber((double)obj);
            }
            else if (obj instanceof DocumentFragment) {
                value = new XRTreeFrag((DocumentFragment)obj);
            }
            else if (obj instanceof Node) {
                value = new XNodeSet((Node)obj);
            }
            else if (obj instanceof NodeIterator) {
                value = new XNodeSet((NodeIterator)obj);
            }
            else {
                value = new XString(obj.toString());
            }
            final int type = value.getType();
            switch (type) {
                case 1:
                case 2:
                case 3: {
                    final String s = value.str();
                    rtreeHandler.characters(s.toCharArray(), 0, s.length());
                    break;
                }
                case 4: {
                    final NodeIterator nl = value.nodeset();
                    Node pos;
                    while ((pos = nl.nextNode()) != null) {
                        final Node top = pos;
                        while (pos != null) {
                            rtreeHandler.flushPending();
                            rtreeHandler.cloneToResultTree(pos, true);
                            Node nextNode = pos.getFirstChild();
                            while (nextNode == null) {
                                if (pos.getNodeType() == 1) {
                                    rtreeHandler.endElement("", "", pos.getNodeName());
                                }
                                if (top == pos) {
                                    break;
                                }
                                nextNode = pos.getNextSibling();
                                if (nextNode != null) {
                                    continue;
                                }
                                pos = pos.getParentNode();
                                if (top == pos) {
                                    if (pos.getNodeType() == 1) {
                                        rtreeHandler.endElement("", "", pos.getNodeName());
                                    }
                                    nextNode = null;
                                    break;
                                }
                            }
                            pos = nextNode;
                        }
                    }
                    break;
                }
                case 5: {
                    rtreeHandler.outputResultTreeFragment(value, this.transformer.getXPathContext());
                    break;
                }
            }
        }
        catch (SAXException e) {
            throw new TransformerException(e);
        }
    }
}
