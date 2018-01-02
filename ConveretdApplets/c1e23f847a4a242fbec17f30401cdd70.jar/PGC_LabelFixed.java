import java.awt.Dimension;
import java.awt.Label;

// 
// Decompiled by Procyon v0.5.30
// 

class PGC_LabelFixed extends Label
{
    private Dimension dimension;
    
    public PGC_LabelFixed() {
    }
    
    public PGC_LabelFixed(final String s) {
        super(s);
    }
    
    public PGC_LabelFixed(final Dimension minimumSize) {
        this.setMinimumSize(minimumSize);
    }
    
    public PGC_LabelFixed(final String s, final Dimension minimumSize) {
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
