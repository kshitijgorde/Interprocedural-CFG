import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class NameArea extends ImageMapArea
{
    String name;
    
    public void handleArg(final String name) {
        this.name = name;
        super.terminal = false;
    }
    
    public void highlight(final Graphics graphics, final boolean b) {
        super.highlight(graphics, b);
        this.showStatus(b ? this.name : null);
    }
}
