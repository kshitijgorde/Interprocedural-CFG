// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.util;

import java.awt.Font;
import java.io.Serializable;

public class FontTable extends ObjectTable implements Serializable
{
    public Font getFont(final int n, final int n2) {
        return (Font)this.getObject(n, n2);
    }
    
    public void setFont(final int n, final int n2, final Font font) {
        this.setObject(n, n2, font);
    }
    
    public boolean equals(final Object o) {
        return o instanceof FontTable && super.equals(o);
    }
}
