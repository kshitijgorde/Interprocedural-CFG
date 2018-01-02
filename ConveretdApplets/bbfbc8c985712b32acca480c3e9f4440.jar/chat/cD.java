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

public final class cD extends Panel
{
    protected cA a;
    protected cA b;
    protected cA c;
    private Hashtable a;
    protected L a;
    private Color[] a;
    final J a;
    boolean a;
    
    public cD(final J j) {
        this(j, 0, null);
    }
    
    public cD(J a, final int n, final Color[] a2) {
        this.a = a;
        if (a2 != null) {
            this.a = a2;
        }
        final Panel panel = new Panel();
        this.setBackground(new Color(238, 238, 238));
        this.a = new ac(new bK(1, 1, 1, 1, this.getBackground()), new al(1, Color.white, Color.gray));
        this.b = new ac(new bK(1, 1, 1, 1, Color.red), new bK(1, 1, 1, 1, this.getBackground()));
        this.c = new ac(new bK(1, 1, 1, 1, Color.blue), new bK(1, 1, 1, 1, this.getBackground()));
        panel.setLayout(new GridLayout(8, 8));
        this.add(panel);
        this.a = (n != 0);
        this.a = new Hashtable();
        if (this.a.a.c) {
            final L l = new L(this);
            panel.add(l);
            this.a.put(Color.white, l);
        }
        if (n == 0 && this.a == null) {
            final int[] array = { 15658734, 16777164, 14540253, 153, 10079487, 16711680, 39168, 6710988 };
            final int[] array2 = { 0, 128, 192, 255 };
            for (int i = 0; i < array.length; ++i) {
                final Color color = new Color(array[i]);
                final L j = new L(this, color);
                panel.add(j);
                this.a.put(color, j);
            }
            for (int k = 0; k < array2.length; ++k) {
                for (int n2 = 0; n2 < array2.length; ++n2) {
                    for (int n3 = 0; n3 < array2.length; ++n3) {
                        final Color color2 = new Color(array2[k], array2[n2], array2[n3]);
                        final L m = new L(this, color2);
                        panel.add(m);
                        this.a.put(color2, m);
                    }
                }
            }
        }
        else if (n == 1) {
            panel.setLayout(new GridLayout(8, 8));
            if (this.a != null) {
                for (int n4 = 0; n4 < this.a.length && this.a.size() < 72; ++n4) {
                    final Color color3 = this.a[n4];
                    final L l2 = new L(this, color3);
                    panel.add(l2);
                    this.a.put(color3, l2);
                }
            }
        }
        if (a != null) {
            final Color a3 = a.a;
            Object o;
            if (a3.equals(Color.white) && ((a = this.a).a.c && a.a)) {
                o = ((Panel)this.getComponent(0)).getComponent(0);
            }
            else {
                o = this.a.get(a3);
            }
            if (o == null) {
                if (this.a != null) {
                    this.a.a(false);
                }
                return;
            }
            if (this.a != null) {
                this.a.a(false);
            }
            (this.a = (L)o).a(true);
            if (this.a != null) {
                this.a.a(this.a.a ? null : a3, this.a);
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
