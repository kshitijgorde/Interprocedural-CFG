// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import java.util.Vector;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xml.utils.FastStringBuffer;
import org.apache.xpath.XPathContext;
import java.io.Serializable;

public abstract class AVTPart implements Serializable, XSLTVisitable
{
    public abstract String getSimpleString();
    
    public abstract void evaluate(final XPathContext p0, final FastStringBuffer p1, final int p2, final PrefixResolver p3) throws TransformerException;
    
    public void setXPathSupport(final XPathContext support) {
    }
    
    public boolean canTraverseOutsideSubtree() {
        return false;
    }
    
    public abstract void fixupVariables(final Vector p0, final int p1);
    
    public abstract void callVisitors(final XSLTVisitor p0);
}
