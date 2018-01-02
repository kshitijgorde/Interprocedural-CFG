// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.objects;

import javax.xml.transform.TransformerException;
import org.apache.xml.utils.WrappedRuntimeException;

public class XBoolean extends XObject
{
    static final long serialVersionUID = -2964933058866100881L;
    public static final XBoolean S_TRUE;
    public static final XBoolean S_FALSE;
    private final boolean m_val;
    
    public XBoolean(final boolean b) {
        this.m_val = b;
    }
    
    public XBoolean(final Boolean b) {
        this.m_val = b;
        super.m_obj = b;
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
    
    public boolean bool() {
        return this.m_val;
    }
    
    public String str() {
        return this.m_val ? "true" : "false";
    }
    
    public Object object() {
        if (null == super.m_obj) {
            super.m_obj = new Boolean(this.m_val);
        }
        return super.m_obj;
    }
    
    public boolean equals(final XObject obj2) {
        if (obj2.getType() == 4) {
            return obj2.equals(this);
        }
        try {
            return this.m_val == obj2.bool();
        }
        catch (TransformerException te) {
            throw new WrappedRuntimeException(te);
        }
    }
    
    static {
        S_TRUE = new XBooleanStatic(true);
        S_FALSE = new XBooleanStatic(false);
    }
}
