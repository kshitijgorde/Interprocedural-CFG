// 
// Decompiled by Procyon v0.5.30
// 

package pa.a.a;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Component;

public class a extends Component
{
    private Image do;
    private int a;
    private int if;
    
    public a(final Image do1) {
        this.do = do1;
        this.a = this.do.getWidth(null);
        this.if = this.do.getHeight(null);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.a, this.if);
    }
    
    public void paint(final Graphics graphics) {
        if (this.do != null) {
            final Dimension size = this.getSize();
            final int width = size.width;
            for (int height = size.height, i = 0; i < height; i += this.if) {
                for (int j = 0; j < width; j += this.a) {
                    graphics.drawImage(this.do, j, i, this.getBackground(), this);
                }
            }
        }
    }
}
