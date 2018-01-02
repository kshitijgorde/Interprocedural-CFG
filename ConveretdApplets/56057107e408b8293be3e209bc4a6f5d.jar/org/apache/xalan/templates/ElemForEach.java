// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xalan.transformer.ResultTreeHandler;
import org.apache.xpath.objects.XObject;
import org.xml.sax.SAXException;
import javax.xml.transform.SourceLocator;
import org.xml.sax.ContentHandler;
import org.apache.xalan.stree.SaxEventDispatch;
import org.apache.xalan.transformer.StackGuard;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xml.utils.NodeVector;
import org.apache.xpath.axes.ContextNodeList;
import org.apache.xpath.NodeSet;
import org.apache.xalan.transformer.NodeSorter;
import org.w3c.dom.traversal.NodeIterator;
import org.apache.xpath.VariableStack;
import org.apache.xpath.XPathContext;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.QName;
import org.apache.xalan.transformer.TransformerImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import java.util.Vector;
import org.apache.xpath.XPath;

public class ElemForEach extends ElemTemplateElement
{
    private XPath m_selectExpression;
    protected Vector m_sortElems;
    
    public ElemForEach() {
        this.m_selectExpression = null;
        this.m_sortElems = null;
    }
    
    public Node appendChild(final Node newChild) throws DOMException {
        final int type = ((ElemTemplateElement)newChild).getXSLToken();
        if (type == 64) {
            this.setSortElem((ElemSort)newChild);
            return newChild;
        }
        return super.appendChild(newChild);
    }
    
    public void execute(final TransformerImpl transformer, final Node sourceNode, final QName mode) throws TransformerException {
        transformer.pushCurrentTemplateRuleIsNull(true);
        try {
            if (TransformerImpl.S_DEBUG) {
                transformer.getTraceManager().fireTraceEvent(sourceNode, mode, this);
            }
            this.transformSelectedNodes(transformer, sourceNode, this, mode);
        }
        finally {
            transformer.popCurrentTemplateRuleIsNull();
        }
    }
    
    public String getNodeName() {
        return "for-each";
    }
    
    public XPath getSelect() {
        return this.m_selectExpression;
    }
    
    public XPath getSelectOrDefault() {
        return (this.m_selectExpression == null) ? this.getStylesheetRoot().m_selectDefault : this.m_selectExpression;
    }
    
    public ElemSort getSortElem(final int i) {
        return this.m_sortElems.elementAt(i);
    }
    
    public int getSortElemCount() {
        return (this.m_sortElems == null) ? 0 : this.m_sortElems.size();
    }
    
    protected ElemTemplateElement getTemplateMatch() {
        return this;
    }
    
    public int getXSLToken() {
        return 28;
    }
    
    boolean needToPushParams() {
        return false;
    }
    
    void popParams(final XPathContext xctxt, final int savedSearchStart) {
        final VariableStack vars = xctxt.getVarStack();
        vars.popElemFrame();
    }
    
    int pushParams(final TransformerImpl transformer, final XPathContext xctxt, final Node sourceNode, final QName mode) throws TransformerException {
        final VariableStack vars = xctxt.getVarStack();
        vars.pushElemFrame();
        return -1;
    }
    
    void reMarkParams(final XPathContext xctxt) {
    }
    
    public void setSelect(final XPath xpath) {
        this.m_selectExpression = xpath;
    }
    
    public void setSortElem(final ElemSort sortElem) {
        if (this.m_sortElems == null) {
            this.m_sortElems = new Vector();
        }
        this.m_sortElems.addElement(sortElem);
    }
    
    protected NodeIterator sortNodes(final XPathContext xctxt, final Vector keys, NodeIterator sourceNodes) throws TransformerException {
        final NodeSorter sorter = new NodeSorter(xctxt);
        NodeSet nodeList;
        if (sourceNodes instanceof NodeSet) {
            nodeList = (NodeSet)sourceNodes;
            nodeList.setShouldCacheNodes(true);
            nodeList.runTo(-1);
        }
        else {
            nodeList = (NodeSet)(sourceNodes = new NodeSet(sourceNodes));
            ((ContextNodeList)sourceNodes).setCurrentPos(0);
        }
        xctxt.pushContextNodeList((ContextNodeList)sourceNodes);
        try {
            sorter.sort(nodeList, keys, xctxt);
            nodeList.setCurrentPos(0);
        }
        finally {
            xctxt.popContextNodeList();
        }
        return sourceNodes;
    }
    
    public void transformSelectedNodes(final TransformerImpl transformer, final Node sourceNode, ElemTemplateElement template, final QName mode) throws TransformerException {
        try {
            final boolean rdebug = TransformerImpl.S_DEBUG;
            final XPathContext xctxt = transformer.getXPathContext();
            final XPath selectPattern = this.getSelectOrDefault();
            final XObject selectResult = selectPattern.execute(xctxt, sourceNode, this);
            if (rdebug) {
                transformer.getTraceManager().fireSelectedEvent(sourceNode, this, "test", selectPattern, selectResult);
            }
            final Vector keys = transformer.processSortKeys(this, sourceNode);
            NodeIterator sourceNodes = selectResult.nodeset();
            if (keys != null) {
                sourceNodes = this.sortNodes(xctxt, keys, sourceNodes);
            }
            final int savedSearchStart = this.pushParams(transformer, xctxt, sourceNode, mode);
            final SourceLocator savedLocator = xctxt.getSAXLocator();
            xctxt.pushContextNodeList((ContextNodeList)sourceNodes);
            transformer.pushElemTemplateElement(null);
            final ResultTreeHandler rth = transformer.getResultTreeHandler();
            final StylesheetRoot sroot = this.getStylesheetRoot();
            final TemplateList tl = sroot.getTemplateListComposed();
            final StackGuard guard = transformer.getStackGuard();
            final boolean check = StackGuard.m_recursionLimit > -1;
            final boolean quiet = transformer.getQuietConflictWarnings();
            final boolean needToFindTemplate = template == null;
            try {
                Node child;
                while ((child = sourceNodes.nextNode()) != null) {
                    if (needToFindTemplate) {
                        template = tl.getTemplate(xctxt, child, mode, -1, quiet);
                        if (template == null) {
                            switch (child.getNodeType()) {
                                case 0:
                                case 10: {
                                    template = sroot.getDefaultRule();
                                    break;
                                }
                                case 1:
                                case 2:
                                case 3: {
                                    if (child.isSupported("http://xml.apache.org/xalan/features/feed-events", "1.0")) {
                                        ((SaxEventDispatch)child).dispatchCharactersEvent(rth);
                                        continue;
                                    }
                                    final String data = child.getNodeValue();
                                    rth.characters(data.toCharArray(), 0, data.length());
                                    continue;
                                }
                                case 8: {
                                    template = sroot.getDefaultRootRule();
                                    break;
                                }
                                default: {
                                    continue;
                                }
                            }
                        }
                    }
                    try {
                        transformer.pushPairCurrentMatched(template, child);
                        if (check) {
                            guard.push(this, child);
                        }
                        if (rdebug) {
                            transformer.getTraceManager().fireTraceEvent(child, mode, template);
                        }
                        if (template.isCompiledTemplate()) {
                            template.execute(transformer, child, mode);
                        }
                        else {
                            for (ElemTemplateElement t = template.m_firstChild; t != null; t = t.m_nextSibling) {
                                xctxt.setSAXLocator(t);
                                transformer.setCurrentElement(t);
                                t.execute(transformer, child, mode);
                            }
                        }
                        this.reMarkParams(xctxt);
                    }
                    catch (TransformerException ex) {
                        throw ex;
                    }
                    finally {
                        transformer.popCurrentMatched();
                        if (check) {
                            guard.pop();
                        }
                    }
                }
            }
            finally {
                xctxt.setSAXLocator(savedLocator);
                xctxt.popContextNodeList();
                transformer.popElemTemplateElement();
                this.popParams(xctxt, savedSearchStart);
            }
        }
        catch (SAXException se) {
            transformer.getErrorListener().fatalError(new TransformerException(se));
        }
    }
}
