// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.objects;

import javax.xml.transform.TransformerException;

public class XBoolean extends XObject
{
    public static XBoolean S_TRUE;
    public static XBoolean S_FALSE;
    boolean m_val;
    
    static {
        XBoolean.S_TRUE = new XBooleanStatic(true);
        XBoolean.S_FALSE = new XBooleanStatic(false);
    }
    
    public XBoolean(final boolean b) {
        this.m_val = b;
    }
    
    public boolean bool() {
        return this.m_val;
    }
    
    public boolean equals(final XObject obj2) throws TransformerException {
        if (obj2.getType() == 4) {
            return obj2.equals(this);
        }
        return this.m_val == obj2.bool();
    }
    
    public int getType() {
        return 1;
    }
    
    public String getTypeString() {
        return "#BOOLEAN";
    }
    
    public double num() {
        return this.m_val ? 1.0 : 0.0;
    }
    
    public Object object() {
        return new Boolean(this.m_val);
    }
    
    public String str() {
        return this.m_val ? "true" : "false";
    }
}
