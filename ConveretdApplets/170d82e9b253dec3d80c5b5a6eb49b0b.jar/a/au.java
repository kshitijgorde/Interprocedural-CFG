// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Color;
import java.util.Hashtable;
import java.awt.Panel;

public class au extends Panel
{
    protected aq q;
    protected aq w;
    protected aq e;
    protected Hashtable q;
    private av q;
    protected final az q;
    private int q;
    
    public au(final az az) {
        this(az, 64);
    }
    
    private au(final az q, final int n) {
        this.q = 64;
        this.q = q;
        this.q = 64;
        this.setBackground(new Color(238, 238, 238));
        this.q = new as(new at(1, 1, 1, 1, this.getBackground()), new ar(1, Color.white, Color.gray));
        this.w = new as(new at(1, 1, 1, 1, Color.red), new at(1, 1, 1, 1, this.getBackground()));
        this.e = new as(new at(1, 1, 1, 1, Color.blue), new at(1, 1, 1, 1, this.getBackground()));
        this.q = new Hashtable();
        this.q(q.q());
        this.q();
    }
    
    public void q() {
        final Panel panel;
        (panel = new Panel()).setLayout(new GridLayout(Math.round(this.q / 8), 8));
        this.add(panel);
        this.q(panel);
    }
    
    private void q(final Panel panel) {
        final float n = 1.0f / (this.q / 3);
        final float n2 = 0.5f / this.q;
        float n3 = 0.0f;
        float n4 = 0.0f;
        for (int i = 1; i <= this.q; ++i) {
            n3 += n;
            n4 += n2;
            final Color color = new Color(Color.HSBtoRGB(n3, 1.0f, n4 + 0.5f));
            final av av = new av(this, color);
            panel.add(av);
            this.q.put(color, av);
        }
    }
    
    public void paint(Graphics graphics) {
        final Dimension size = this.size();
        final Graphics graphics2 = graphics;
        final int n = size.width - 1;
        final int n2 = size.height - 1;
        final int n3 = n;
        graphics = graphics2;
        final Insets insets = new Insets(1, 1, 1, 1);
        final Color color = graphics.getColor();
        graphics.translate(1, 1);
        graphics.setColor(Color.blue);
        graphics.fillRect(0, 0, n3 - insets.right, insets.top);
        graphics.fillRect(0, insets.top, insets.left, n2 - insets.top);
        graphics.fillRect(insets.left, n2 - insets.bottom, n3 - insets.left, insets.bottom);
        graphics.fillRect(n3 - insets.right, 0, insets.right, n2 - insets.bottom);
        graphics.translate(-1, -1);
        graphics.setColor(color);
    }
    
    public Insets insets() {
        return new Insets(1, 1, 1, 1);
    }
    
    public final void q(final Color color) {
        final av value;
        if ((value = this.q.get(color)) == null) {
            return;
        }
        if (this.q != null) {
            this.q.q(false);
        }
        (this.q = value).q(true);
    }
}