import java.awt.Dimension;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class setPanel extends Panel
{
    Dimension myDimension;
    
    setPanel(final int width, final int height) {
        this.myDimension = new Dimension(425, 82);
        this.myDimension.width = width;
        this.myDimension.height = height;
    }
    
    public Dimension getPreferredSize() {
        return this.myDimension;
    }
    
    public Dimension getMinimumSize() {
        return this.myDimension;
    }
}
