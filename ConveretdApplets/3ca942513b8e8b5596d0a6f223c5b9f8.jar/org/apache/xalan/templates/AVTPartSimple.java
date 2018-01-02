// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xml.utils.PrefixResolver;
import org.apache.xml.utils.FastStringBuffer;
import org.apache.xpath.XPathContext;
import java.util.Vector;

public class AVTPartSimple extends AVTPart
{
    static final long serialVersionUID = -3744957690598727913L;
    private String m_val;
    
    public AVTPartSimple(final String val) {
        this.m_val = val;
    }
    
    public String getSimpleString() {
        return this.m_val;
    }
    
    public void fixupVariables(final Vector vars, final int globalsSize) {
    }
    
    public void evaluate(final XPathContext xctxt, final FastStringBuffer buf, final int context, final PrefixResolver nsNode) {
        buf.append(this.m_val);
    }
    
    public void callVisitors(final XSLTVisitor visitor) {
    }
}
