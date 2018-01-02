// 
// Decompiled by Procyon v0.5.30
// 

package viewer.cards.mozaic;

import java.awt.Component;
import viewer.ImageViewerApplet;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import viewer.RaisedPanel;

public class Mozaic_Panel extends RaisedPanel
{
    public Mozaic_Canvas canvas;
    
    public Mozaic_Panel(final int n) {
        super(20, 10, 10, 10, 5);
        this.setLayout(new BorderLayout());
        this.setBorder(true);
        this.setLabel(ImageViewerApplet.fileList[n]);
        this.add("Center", this.canvas = new Mozaic_Canvas(n));
    }
}
