// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Color;
import java.util.Hashtable;
import java.awt.Panel;

public final class bq extends Panel
{
    protected bn a;
    protected bn b;
    protected bn c;
    private Hashtable a;
    protected z a;
    private Color[] a;
    final x a;
    
    public bq(final x x) {
        this(x, 0, null);
    }
    
    public bq(final x a, int n, final Color[] a2) {
        this.a = a;
        if (a2 != null) {
            this.a = a2;
        }
        final Panel panel = new Panel();
        this.setBackground(new Color(238, 238, 238));
        this.a = new H(new aP(this.getBackground()), new Q(Color.white, Color.gray));
        this.b = new H(new aP(Color.red), new aP(this.getBackground()));
        this.c = new H(new aP(Color.blue), new aP(this.getBackground()));
        panel.setLayout(new GridLayout(8, 8));
        this.add(panel);
        this.a = new Hashtable();
        if (n == 0 && this.a == null) {
            final int[] array = { 15658734, 16777164, 14540253, 153, 10079487, 16711680, 39168, 6710988 };
            final int[] array2 = { 0, 128, 192, 255 };
            for (int i = 0; i < array.length; ++i) {
                final Color color = new Color(array[i]);
                final z z = new z(this, color);
                panel.add(z);
                this.a.put(color, z);
            }
            for (int j = 0; j < array2.length; ++j) {
                for (int k = 0; k < array2.length; ++k) {
                    for (int l = 0; l < array2.length; ++l) {
                        final Color color2 = new Color(array2[j], array2[k], array2[l]);
                        final z z2 = new z(this, color2);
                        panel.add(z2);
                        this.a.put(color2, z2);
                    }
                }
            }
        }
        else if (n == 1) {
            panel.setLayout(new GridLayout(8, 8));
            if (this.a != null) {
                Color color3;
                z z3;
                for (n = 0; n < this.a.length && this.a.size() < 72; ++n) {
                    color3 = this.a[n];
                    z3 = new z(this, color3);
                    panel.add(z3);
                    this.a.put(color3, z3);
                }
            }
        }
        if (a != null) {
            final Color a3 = a.a;
            final Object value;
            if ((value = this.a.get(a3)) == null) {
                if (this.a != null) {
                    this.a.a(false);
                }
                return;
            }
            if (this.a != null) {
                this.a.a(false);
            }
            (this.a = (z)value).a(true);
            if (this.a != null) {
                this.a.a(a3);
            }
        }
    }
    
    public final void paint(final Graphics graphics) {
        final Dimension size = this.size();
        final int n = size.width - 1;
        final int n2 = size.height - 1;
        final int n3 = n;
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
    
    public final Insets insets() {
        return new Insets(1, 1, 1, 1);
    }
    
    public final void a() {
        if (this.a != null) {
            this.a.a(false);
        }
    }
}
