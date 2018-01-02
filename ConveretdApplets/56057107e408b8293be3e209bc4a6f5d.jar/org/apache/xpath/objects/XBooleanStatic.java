// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.objects;

import javax.xml.transform.TransformerException;

public class XBooleanStatic extends XBoolean
{
    boolean m_val;
    
    public XBooleanStatic(final boolean b) {
        super(b);
        this.m_val = b;
    }
    
    public boolean equals(final XObject obj2) throws TransformerException {
        return this.m_val == obj2.bool();
    }
}
