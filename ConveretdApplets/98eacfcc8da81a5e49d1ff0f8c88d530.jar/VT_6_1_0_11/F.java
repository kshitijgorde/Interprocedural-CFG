// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import java.awt.Dimension;
import com.hw.client.util.c;
import javax.swing.event.ChangeListener;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.BoundedRangeModel;
import java.awt.Image;
import javax.swing.JPanel;

public final class F extends JPanel
{
    private int a;
    private Image b;
    private Image c;
    private int d;
    private int e;
    private BoundedRangeModel f;
    private bH g;
    
    public F(final String s, final String s2, final int n, final int n2) {
        this.a = 0;
        final DefaultBoundedRangeModel defaultBoundedRangeModel;
        (defaultBoundedRangeModel = new DefaultBoundedRangeModel()).setMinimum(0);
        defaultBoundedRangeModel.setMaximum(100);
        this.a(s, s2, defaultBoundedRangeModel);
    }
    
    public F(final String s, final String s2, final BoundedRangeModel boundedRangeModel) {
        this.a = 0;
        this.a(s, s2, boundedRangeModel);
    }
    
    private void a(final String s, final String s2, final BoundedRangeModel boundedRangeModel) {
        this.g = new bH(this, this);
        BoundedRangeModel f = boundedRangeModel;
        if (f == null) {
            f = new DefaultBoundedRangeModel();
        }
        if (this.f != f) {
            if (this.f != null) {
                this.f.removeChangeListener(this.g);
            }
            (this.f = f).addChangeListener(this.g);
        }
        this.setOpaque(false);
        final ImageIcon a = com.hw.client.util.c.a(s);
        this.d = a.getIconWidth();
        this.e = a.getIconHeight();
        this.b = a.getImage();
        this.c = com.hw.client.util.c.a(s2).getImage();
        com.hw.client.util.c.a(this, new Dimension(this.d, this.e));
    }
    
    public final void a(final int value) {
        this.f.setValue(value);
    }
    
    public final void paintComponent(final Graphics graphics) {
        final int value = this.f.getValue();
        final int n = this.e * (((this.a == 0) ? value : (this.f.getMinimum() + this.f.getMaximum() - value)) - this.f.getMinimum()) / (this.f.getMaximum() - this.f.getMinimum());
        graphics.drawImage(this.c, 0, 0, this.d, this.e, 0, 0, this.d, this.e, this);
        graphics.drawImage(this.b, 0, 0, this.d, n, 0, 0, this.d, n, this);
    }
    
    public final void b(final int n) {
        this.a = 1;
    }
}
