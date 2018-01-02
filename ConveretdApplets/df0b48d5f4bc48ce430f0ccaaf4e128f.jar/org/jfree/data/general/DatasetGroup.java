// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.general;

import java.io.Serializable;

public class DatasetGroup implements Cloneable, Serializable
{
    private static final long serialVersionUID = -3640642179674185688L;
    private String id;
    
    public DatasetGroup() {
        this.id = "NOID";
    }
    
    public DatasetGroup(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Null 'id' argument.");
        }
        this.id = id;
    }
    
    public String getID() {
        return this.id;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DatasetGroup)) {
            return false;
        }
        final DatasetGroup that = (DatasetGroup)obj;
        return this.id.equals(that.id);
    }
}
