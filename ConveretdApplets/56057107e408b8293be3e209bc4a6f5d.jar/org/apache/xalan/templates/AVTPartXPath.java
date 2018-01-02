// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.objects.XObject;
import org.w3c.dom.Node;
import org.apache.xml.utils.FastStringBuffer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.SourceLocator;
import org.apache.xpath.XPathContext;
import org.apache.xpath.XPathFactory;
import org.apache.xpath.compiler.XPathParser;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xpath.XPath;

public class AVTPartXPath extends AVTPart
{
    private XPath m_xpath;
    
    public AVTPartXPath(final String val, final PrefixResolver nsNode, final XPathParser xpathProcessor, final XPathFactory factory, final XPathContext liaison) throws TransformerException {
        this.m_xpath = new XPath(val, null, nsNode, 0, liaison.getErrorListener());
    }
    
    public AVTPartXPath(final XPath xpath) {
        this.m_xpath = xpath;
    }
    
    public boolean canTraverseOutsideSubtree() {
        return this.m_xpath.getExpression().canTraverseOutsideSubtree();
    }
    
    public void evaluate(final XPathContext xctxt, final FastStringBuffer buf, final Node context, final PrefixResolver nsNode) throws TransformerException {
        final XObject xobj = this.m_xpath.execute(xctxt, context, nsNode);
        if (xobj != null) {
            buf.append(xobj.str());
        }
    }
    
    public String getSimpleString() {
        return "{" + this.m_xpath.getPatternString() + "}";
    }
}
