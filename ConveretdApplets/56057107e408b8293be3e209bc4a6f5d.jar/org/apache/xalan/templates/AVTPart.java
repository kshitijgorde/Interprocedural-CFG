// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.TransformerException;
import org.apache.xml.utils.PrefixResolver;
import org.w3c.dom.Node;
import org.apache.xml.utils.FastStringBuffer;
import org.apache.xpath.XPathContext;
import java.io.Serializable;

public abstract class AVTPart implements Serializable
{
    public boolean canTraverseOutsideSubtree() {
        return false;
    }
    
    public abstract void evaluate(final XPathContext p0, final FastStringBuffer p1, final Node p2, final PrefixResolver p3) throws TransformerException;
    
    public abstract String getSimpleString();
    
    public void setXPathSupport(final XPathContext support) {
    }
}
