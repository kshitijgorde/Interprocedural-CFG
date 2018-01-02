// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import java.io.Serializable;

public class Attribute implements Serializable
{
    private static final long serialVersionUID = 2484220110589082382L;
    private String name;
    private Object value;
    
    public Attribute(final String name, final Object value) {
        this.name = null;
        this.value = null;
        this.name = name;
        this.value = value;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Object getValue() {
        return this.value;
    }
    
    public boolean equals(final Object object) {
        if (object == null) {
            return false;
        }
        if (!object.getClass().equals(this.getClass())) {
            return false;
        }
        final Attribute attr = (Attribute)object;
        return this.name.equals(attr.getName()) && this.value.equals(attr.getValue());
    }
    
    public int hashCode() {
        return this.name.hashCode();
    }
    
    public String toString() {
        final StringBuffer buffer = new StringBuffer(50);
        buffer.append(this.getClass().getName()).append(":");
        buffer.append(" name=").append(this.getName());
        buffer.append(" value=").append(this.getValue());
        return buffer.toString();
    }
}
