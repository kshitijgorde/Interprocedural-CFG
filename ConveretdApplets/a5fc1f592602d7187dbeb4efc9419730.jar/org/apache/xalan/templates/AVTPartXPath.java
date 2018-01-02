// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.XPathVisitor;
import org.apache.xpath.ExpressionOwner;
import org.apache.xpath.objects.XObject;
import org.apache.xml.utils.FastStringBuffer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.SourceLocator;
import org.apache.xpath.XPathContext;
import org.apache.xpath.XPathFactory;
import org.apache.xpath.compiler.XPathParser;
import org.apache.xml.utils.PrefixResolver;
import java.util.Vector;
import org.apache.xpath.XPath;

public class AVTPartXPath extends AVTPart
{
    private XPath m_xpath;
    
    public void fixupVariables(final Vector vars, final int globalsSize) {
        this.m_xpath.fixupVariables(vars, globalsSize);
    }
    
    public boolean canTraverseOutsideSubtree() {
        return this.m_xpath.getExpression().canTraverseOutsideSubtree();
    }
    
    public AVTPartXPath(final XPath xpath) {
        this.m_xpath = xpath;
    }
    
    public AVTPartXPath(final String val, final PrefixResolver nsNode, final XPathParser xpathProcessor, final XPathFactory factory, final XPathContext liaison) throws TransformerException {
        this.m_xpath = new XPath(val, null, nsNode, 0, liaison.getErrorListener());
    }
    
    public String getSimpleString() {
        return "{" + this.m_xpath.getPatternString() + "}";
    }
    
    public void evaluate(final XPathContext xctxt, final FastStringBuffer buf, final int context, final PrefixResolver nsNode) throws TransformerException {
        final XObject xobj = this.m_xpath.execute(xctxt, context, nsNode);
        if (null != xobj) {
            xobj.appendToFsb(buf);
        }
    }
    
    public void callVisitors(final XSLTVisitor visitor) {
        this.m_xpath.getExpression().callVisitors(this.m_xpath, visitor);
    }
}
