// 
// Decompiled by Procyon v0.5.30
// 

package buddysoft.b;

import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

public class a extends Panel
{
    private Image do;
    protected Graphics if;
    protected int width;
    protected int height;
    protected buddysoft.a.a a;
    
    public void invalidate() {
        super.invalidate();
        this.a();
    }
    
    public void paint(final Graphics graphics) {
        if (this.do == null) {
            this.width = this.size().width;
            this.height = this.size().height;
            this.do = this.createImage(this.width, this.height);
            this.if = this.do.getGraphics();
        }
        graphics.drawImage(this.do, 0, 0, null);
    }
    
    protected void a() {
        if (this.do != null) {
            this.if.dispose();
            this.if = null;
            this.do.flush();
            this.do = null;
        }
    }
    
    protected void a(final Graphics graphics, final String s) {
        graphics.setFont(new Font("Verdana", 0, 11));
        graphics.setColor(this.a.g);
        final FontMetrics fontMetrics = this.getFontMetrics(graphics.getFont());
        graphics.drawString(s, this.width / 2 - fontMetrics.stringWidth(s) / 2, this.height / 2 + fontMetrics.getHeight() / 2 - fontMetrics.getMaxDescent());
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void finalize() {
        this.a();
        System.gc();
    }
    
    public void removeNotify() {
        super.removeNotify();
        this.a();
    }
}
