// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath;

import javax.xml.transform.ErrorListener;
import org.apache.xml.utils.PrefixResolver;
import javax.xml.transform.SourceLocator;
import org.apache.xml.utils.PrefixResolverDefault;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.apache.xpath.objects.XObject;
import org.w3c.dom.traversal.NodeIterator;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Node;

public class CachedXPathAPI
{
    protected XPathContext xpathSupport;
    
    public CachedXPathAPI() {
        this.xpathSupport = new XPathContext();
    }
    
    public CachedXPathAPI(final CachedXPathAPI priorXPathAPI) {
        this.xpathSupport = priorXPathAPI.xpathSupport;
    }
    
    public XPathContext getXPathContext() {
        return this.xpathSupport;
    }
    
    public Node selectSingleNode(final Node contextNode, final String str) throws TransformerException {
        return this.selectSingleNode(contextNode, str, contextNode);
    }
    
    public Node selectSingleNode(final Node contextNode, final String str, final Node namespaceNode) throws TransformerException {
        final NodeIterator nl = this.selectNodeIterator(contextNode, str, namespaceNode);
        return nl.nextNode();
    }
    
    public NodeIterator selectNodeIterator(final Node contextNode, final String str) throws TransformerException {
        return this.selectNodeIterator(contextNode, str, contextNode);
    }
    
    public NodeIterator selectNodeIterator(final Node contextNode, final String str, final Node namespaceNode) throws TransformerException {
        final XObject list = this.eval(contextNode, str, namespaceNode);
        return list.nodeset();
    }
    
    public NodeList selectNodeList(final Node contextNode, final String str) throws TransformerException {
        return this.selectNodeList(contextNode, str, contextNode);
    }
    
    public NodeList selectNodeList(final Node contextNode, final String str, final Node namespaceNode) throws TransformerException {
        final XObject list = this.eval(contextNode, str, namespaceNode);
        return list.nodelist();
    }
    
    public XObject eval(final Node contextNode, final String str) throws TransformerException {
        return this.eval(contextNode, str, contextNode);
    }
    
    public XObject eval(final Node contextNode, final String str, final Node namespaceNode) throws TransformerException {
        final PrefixResolverDefault prefixResolver = new PrefixResolverDefault((namespaceNode.getNodeType() == 9) ? ((Document)namespaceNode).getDocumentElement() : namespaceNode);
        final XPath xpath = new XPath(str, null, prefixResolver, 0, null);
        final int ctxtNode = this.xpathSupport.getDTMHandleFromNode(contextNode);
        return xpath.execute(this.xpathSupport, ctxtNode, prefixResolver);
    }
    
    public XObject eval(final Node contextNode, final String str, final PrefixResolver prefixResolver) throws TransformerException {
        final XPath xpath = new XPath(str, null, prefixResolver, 0, null);
        final XPathContext xpathSupport = new XPathContext();
        final int ctxtNode = xpathSupport.getDTMHandleFromNode(contextNode);
        return xpath.execute(xpathSupport, ctxtNode, prefixResolver);
    }
}
