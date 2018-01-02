import java.awt.LayoutManager;
import java.awt.Insets;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class _ extends a
{
    public _() {
    }
    
    public _(final int n, final Insets insets) {
        super(n, insets);
    }
    
    public _(final LayoutManager layoutManager) {
        super(layoutManager);
    }
    
    public _(final LayoutManager layoutManager, final int n, final Insets insets) {
        super(layoutManager, n, insets);
    }
    
    public abstract String j();
}
