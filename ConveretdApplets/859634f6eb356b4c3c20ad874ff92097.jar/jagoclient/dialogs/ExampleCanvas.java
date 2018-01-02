// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.dialogs;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Canvas;

class ExampleCanvas extends Canvas
{
    GetFontSize GFS;
    
    public ExampleCanvas(final GetFontSize gfs) {
        this.GFS = gfs;
    }
    
    public void paint(final Graphics graphics) {
        this.GFS.example(graphics, this.getSize().width, this.getSize().height);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(200, 100);
    }
}
