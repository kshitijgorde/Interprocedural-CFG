import java.awt.Dimension;
import java.awt.Button;

// 
// Decompiled by Procyon v0.5.30
// 

class PGC_ButtonFixed extends Button
{
    private Dimension dimension;
    
    public PGC_ButtonFixed() {
    }
    
    public PGC_ButtonFixed(final String s) {
        super(s);
    }
    
    public PGC_ButtonFixed(final Dimension minimumSize) {
        this.setMinimumSize(minimumSize);
    }
    
    public PGC_ButtonFixed(final String s, final Dimension minimumSize) {
        super(s);
        this.setMinimumSize(minimumSize);
    }
    
    public void setMinimumSize(final Dimension dimension) {
        this.dimension = dimension;
    }
    
    public Dimension getMinimumSize() {
        return this.minimumSize();
    }
    
    public Dimension getMaximumSize() {
        return this.minimumSize();
    }
    
    public Dimension getPreferredSize() {
        return this.minimumSize();
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public Dimension minimumSize() {
        final Dimension minimumSize = super.minimumSize();
        if (this.dimension != null) {
            if (this.dimension.width != -1) {
                minimumSize.width = this.dimension.width;
            }
            if (this.dimension.height != -1) {
                minimumSize.height = this.dimension.height;
            }
        }
        return minimumSize;
    }
}
