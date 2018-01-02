import java.awt.Dimension;
import java.awt.Scrollbar;

// 
// Decompiled by Procyon v0.5.30
// 

public class dm extends Scrollbar
{
    public dm(final int n, final int n2, final int n3, final int n4) {
        super(1, n, n2, n3, n4);
    }
    
    public final Dimension getPreferredSize() {
        return new Dimension(10, 0);
    }
}
