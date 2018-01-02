// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xml.utils.PrefixResolver;
import org.w3c.dom.Node;
import org.apache.xml.utils.FastStringBuffer;
import org.apache.xpath.XPathContext;

public class AVTPartSimple extends AVTPart
{
    private String m_val;
    
    public AVTPartSimple(final String val) {
        this.m_val = val;
    }
    
    public void evaluate(final XPathContext xctxt, final FastStringBuffer buf, final Node context, final PrefixResolver nsNode) {
        buf.append(this.m_val);
    }
    
    public String getSimpleString() {
        return this.m_val;
    }
}
