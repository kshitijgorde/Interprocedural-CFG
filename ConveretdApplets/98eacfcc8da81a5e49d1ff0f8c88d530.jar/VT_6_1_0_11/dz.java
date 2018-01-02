// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import javax.swing.JComponent;
import com.hw.client.util.c;
import java.awt.Dimension;

public final class dz extends cY
{
    private l e;
    
    public dz(final l e, final du du) {
        super(1, du, e.a());
        this.e = e;
        this.a(new M(e, this));
        com.hw.client.util.c.a(this, new Dimension(240, 48));
    }
    
    protected final dy a() {
        return new dn(this, this.e());
    }
    
    static l a(final dz dz) {
        return dz.e;
    }
}
