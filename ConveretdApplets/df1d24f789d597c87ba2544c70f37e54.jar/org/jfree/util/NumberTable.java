// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.util;

import java.io.Serializable;

public class NumberTable extends ObjectTable implements Serializable
{
    public Number getNumber(final int n, final int n2) {
        return (Number)this.getObject(n, n2);
    }
    
    public void setNumber(final int n, final int n2, final Number n3) {
        this.setObject(n, n2, n3);
    }
    
    public boolean equals(final Object o) {
        return o instanceof NumberTable && super.equals(o);
    }
}
