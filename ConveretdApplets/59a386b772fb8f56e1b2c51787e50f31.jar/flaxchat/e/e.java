// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.e;

import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.ComponentEvent;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.event.ComponentListener;
import java.awt.Panel;

public class e extends Panel implements ComponentListener
{
    private Dimension a;
    private Image b;
    public static boolean c;
    
    public e() {
        this.addComponentListener(this);
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.b = null;
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public e(final int n, final int n2) {
        this(new Dimension(n, n2));
    }
    
    public e(final Dimension a) {
        this.a = a;
    }
    
    public e(final LayoutManager layout) {
        this.setLayout(layout);
    }
    
    protected Image a() {
        if (this.b == null) {
            final Dimension size = this.getSize();
            this.b = this.createImage(size.width, size.height);
        }
        return this.b;
    }
    
    public final void paint(final Graphics graphics) {
        final Graphics graphics2 = this.a().getGraphics();
        this.a(graphics2);
        graphics.drawImage(this.a(), 0, 0, this);
        graphics2.dispose();
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void a(final Graphics graphics) {
        super.paint(graphics);
        g.a(graphics, this);
    }
    
    public Dimension getPreferredSize() {
        if (this.a != null) {
            return this.a;
        }
        return super.getPreferredSize();
    }
    
    public void a(final Dimension a) {
        this.a = a;
    }
}
