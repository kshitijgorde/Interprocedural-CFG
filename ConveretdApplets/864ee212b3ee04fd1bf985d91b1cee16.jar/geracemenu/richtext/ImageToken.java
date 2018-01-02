// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.richtext;

import geracemenu.TTComponent;
import sun.misc.Timeable;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Dimension;
import sun.misc.Timer;
import geracemenu.VImage;

public class ImageToken extends RichTextToken
{
    private VImage image;
    private Timer ticker;
    
    public Dimension getSize() {
        return this.image.getSize();
    }
    
    public void paint(final Graphics graphics, final Point point) {
        this.image.paint(graphics, point);
    }
    
    public ImageToken(final VImage vImage, final TextStyle textStyle) {
        this(vImage, textStyle, false);
    }
    
    public ImageToken(final VImage image, final TextStyle textStyle, final boolean b) {
        super(textStyle);
        this.image = image;
        if (b) {
            if (this == null) {
                throw null;
            }
            (this.ticker = new Timer(new ImageUpdate(), 100L)).cont();
        }
    }
    
    class ImageUpdate implements Timeable
    {
        public void tick(final Timer timer) {
            final TTComponent container = ((RichTextLine)ImageToken.this.container).getContainer();
            if (container.isShowing()) {
                container.repaintNow();
            }
        }
    }
}
