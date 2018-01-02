// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.ui;

import java.io.Serializable;
import org.jfree.util.ObjectTable;

public class TextAnchorTable extends ObjectTable implements Serializable
{
    public TextAnchor getAnchor(final int n, final int n2) {
        return (TextAnchor)this.getObject(n, n2);
    }
    
    public void setAnchor(final int n, final int n2, final TextAnchor textAnchor) {
        this.setObject(n, n2, textAnchor);
    }
    
    public boolean equals(final Object o) {
        return o instanceof TextAnchorTable && super.equals(o);
    }
    
    public int hashCode() {
        return super.hashCode();
    }
}
