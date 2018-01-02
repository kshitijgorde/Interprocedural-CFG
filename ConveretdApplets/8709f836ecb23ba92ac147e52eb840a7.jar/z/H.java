// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.awt.Paint;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

public class H extends JPanel
{
    private boolean a;
    private Color b;
    private Color c;
    private static /* synthetic */ boolean d;
    
    public H() {
        this.a = false;
    }
    
    public final void a(final Color b) {
        this.b = b;
    }
    
    public final void b(final Color c) {
        this.c = c;
    }
    
    public final void a(final boolean a) {
        this.a = a;
    }
    
    public void paintComponent(final Graphics graphics) {
        if (!H.d && this.isOpaque()) {
            throw new AssertionError();
        }
        final Graphics2D graphics2D = (Graphics2D)graphics;
        final GradientPaint paint = this.a ? new GradientPaint(0.0f, 0.0f, this.b, 0.0f, this.getHeight(), this.c) : new GradientPaint(0.0f, 0.0f, this.b, this.getWidth(), 0.0f, this.c);
        final Paint paint2 = graphics2D.getPaint();
        graphics2D.setPaint(paint);
        graphics2D.fill(graphics2D.getClip());
        graphics2D.setPaint(paint2);
        super.paintComponent(graphics);
    }
    
    static {
        H.d = !H.class.desiredAssertionStatus();
    }
}
