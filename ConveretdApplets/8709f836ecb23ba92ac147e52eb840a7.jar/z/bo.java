// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

final class bo extends JPanel
{
    private /* synthetic */ boolean a;
    private /* synthetic */ int b;
    private /* synthetic */ int c;
    private /* synthetic */ int d;
    private /* synthetic */ Color e;
    private /* synthetic */ int f;
    private /* synthetic */ int g;
    
    bo(final aO ao, final boolean a, final int b, final int c, final int d, final Color e, final int f, final int g) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
    }
    
    protected final void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        if (this.a) {
            final bo bo = this;
            final int b = this.b;
            final int c = this.c;
            final int d = this.d;
            final Color e = this.e;
            final int n = d;
            final int n2 = c;
            final int n3 = b;
            this = bo;
            graphics.setColor(e);
            final int n4 = this.getWidth() - this.getInsets().left - this.getInsets().right - 2 * this.f;
            int top = this.getInsets().top;
            for (int i = this.getComponentCount(); i > n2; i -= n2) {
                top += n3 + this.g;
                graphics.fillRect(this.getInsets().left + this.f, top, n4, n);
            }
        }
    }
}
