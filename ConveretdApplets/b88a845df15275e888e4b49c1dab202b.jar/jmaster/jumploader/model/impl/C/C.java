// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.impl.C;

import jmaster.jumploader.model.api.common.IAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jmaster.jumploader.model.api.common.IAttributeSet;

public class C implements IAttributeSet
{
    private Map B;
    private List A;
    
    public C() {
        this.B = new HashMap();
        this.A = new ArrayList();
    }
    
    public String toString() {
        return "AttributeSet,\r\nattributeCount=" + this.getAttributeCount() + "\r\n" + "";
    }
    
    public IAttribute createAttribute(final String s, final Object value) {
        D d = (D)this.getAttributeByName(s);
        if (d != null) {
            d.setValue(value);
        }
        else {
            d = new D(s, value);
            this.B.put(s, d);
            this.A.add(d);
        }
        return d;
    }
    
    public IAttribute setAttribute(final String s, final Object o) {
        return this.createAttribute(s, o);
    }
    
    public IAttribute getAttributeAt(final int n) {
        return this.A.get(n);
    }
    
    public IAttribute getAttributeByName(final String s) {
        return this.B.get(s);
    }
    
    public int getAttributeCount() {
        return this.B.size();
    }
    
    public void removeAttribute(final IAttribute attribute) {
        this.B.remove(attribute.getName());
        this.A.remove(attribute);
    }
    
    public void clear() {
        this.B.clear();
        this.A.clear();
    }
    
    public IAttribute createStringAttribute(final String s, final String s2) {
        return this.createAttribute(s, s2);
    }
    
    public IAttribute setStringAttribute(final String s, final String s2) {
        return this.setAttribute(s, s2);
    }
}
