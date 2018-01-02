// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Color;
import java.util.Hashtable;
import java.awt.Panel;

public class cj extends Panel
{
    protected aa d;
    protected aa e;
    protected aa f;
    protected Hashtable i;
    protected x a;
    private final aZ c;
    
    public cj(final aZ c, Color[] array, final boolean b) {
        final Color[] array2 = new Color[72];
        final int[] array3 = { 15658734, 16777164, 14540253, 153, 10079487, 16711680, 39168, 6710988 };
        final int[] array4 = { 0, 128, 192, 255 };
        int n = 0;
        for (int i = 0; i < array3.length; ++i, ++n) {
            array2[n] = new Color(array3[i]);
        }
        for (int j = 0; j < array4.length; ++j) {
            for (int k = 0; k < array4.length; ++k) {
                for (int l = 0; l < array4.length; ++l, ++n) {
                    array2[n] = new Color(array4[j], array4[k], array4[l]);
                }
            }
        }
        if (array == null) {
            array = array2;
        }
        this.c = c;
        final Panel panel = new Panel();
        this.setBackground(new Color(238, 238, 238));
        this.d = new aA(new ap(1, 1, 1, 1, this.getBackground()), new s(1, Color.white, Color.gray));
        this.e = new aA(new ap(1, 1, 1, 1, Color.red), new ap(1, 1, 1, 1, this.getBackground()));
        this.f = new aA(new ap(1, 1, 1, 1, Color.blue), new ap(1, 1, 1, 1, this.getBackground()));
        panel.setLayout(new GridLayout(0, 9));
        final ar ar = new ar(20);
        this.add(panel);
        this.i = new Hashtable();
        int n2;
        int n3;
        for (n2 = 0, n3 = 0; n2 < array.length && n3 < 72; ++n2) {
            if (array[n2] != null) {
                ++n3;
                final x x = new x(this, array[n2]);
                panel.add(x);
                this.i.put(array[n2], x);
            }
        }
        if (n3 < 72 && b) {
            int n4 = 0;
            while (n3 < 72) {
                final x x2 = new x(this, array2[n4]);
                panel.add(x2);
                this.i.put(array2[n4], x2);
                ++n4;
                ++n3;
            }
        }
        this.setColor(c.getColorFromModel());
    }
    
    public void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final Insets insets = new Insets(1, 1, 1, 1);
        final Color color = graphics.getColor();
        graphics.translate(n, n2);
        graphics.setColor(Color.blue);
        graphics.fillRect(0, 0, n3 - insets.right, insets.top);
        graphics.fillRect(0, insets.top, insets.left, n4 - insets.top);
        graphics.fillRect(insets.left, n4 - insets.bottom, n3 - insets.left, insets.bottom);
        graphics.fillRect(n3 - insets.right, 0, insets.right, n4 - insets.bottom);
        graphics.translate(-n, -n2);
        graphics.setColor(color);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        this.a(graphics, 1, 1, size.width - 1, size.height - 1);
    }
    
    public Insets insets() {
        return new Insets(1, 1, 1, 1);
    }
    
    public void setColor(final Color color) {
        final x value = this.i.get(color);
        if (value == null) {
            return;
        }
        if (this.a != null) {
            this.a.setSelected(false);
        }
        (this.a = value).setSelected(true);
    }
    
    static aZ a(final cj cj) {
        return cj.c;
    }
}
