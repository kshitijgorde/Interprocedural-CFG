// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.e;

import java.awt.Dimension;
import java.awt.List;

public class d extends List
{
    private Dimension a;
    
    public Dimension getPreferredSize() {
        if (this.a != null) {
            return this.a;
        }
        return super.getPreferredSize();
    }
    
    public void a(final Dimension a) {
        this.a = a;
    }
}
