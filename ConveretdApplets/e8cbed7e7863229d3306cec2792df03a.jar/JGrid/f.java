// 
// Decompiled by Procyon v0.5.30
// 

package JGrid;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.Hashtable;
import java.awt.Color;

public class f
{
    protected d[] else;
    protected boolean for;
    boolean long;
    protected static int[] if;
    protected static int[] goto;
    protected static int null;
    protected static boolean do;
    protected static boolean try;
    protected static Color new;
    protected static Color int;
    protected static Color void;
    protected static Color a;
    protected static Color char;
    protected static Color case;
    protected static Color byte;
    
    public f(final Object[] array, final int[] array2, final int n, final Hashtable hashtable) {
        this.for = false;
        this.long = true;
        this.else = new d[array.length];
        for (int i = 0; i < array.length; ++i) {
            final Object o = array[array2[i]];
            final String string = String.valueOf(n) + "," + array2[i];
            if (n == -1 || ((this.else[i] = this.a(o, string, hashtable)) == null && (this.else[i] = this.a(o, "," + array2[i], hashtable)) == null && (this.else[i] = this.a(o, new Integer(n).toString(), hashtable)) == null)) {
                final String string2 = o.toString();
                final int index;
                if (string2 != null && (index = string2.indexOf(94)) != -1) {
                    this.else[i] = new c(string2.substring(0, index), string2.substring(index + 1));
                }
                else {
                    this.else[i] = new c(string2, null);
                }
                if (n == -1) {
                    ((c)this.else[i]).b = 1;
                }
            }
        }
    }
    
    private d a(final Object value, final String s, final Hashtable hashtable) {
        d d = null;
        final String s2 = hashtable.get(s);
        if (s2 != null) {
            if (s2.charAt(0) != ',') {
                final String substring = s2.substring(0, s2.indexOf(44));
                try {
                    d = (d)Class.forName("JGrid.J" + substring + "Cell").newInstance();
                    d.setValue(value);
                }
                catch (Exception ex) {}
            }
            else {
                final String string = value.toString();
                final int index;
                if (value != null && (index = string.indexOf(94)) != -1) {
                    d = new c(string.substring(0, index), string.substring(index + 1));
                }
                else {
                    d = new c(string, null);
                }
            }
            d.setFeature(s2.substring(s2.indexOf(44) + 1));
        }
        return d;
    }
    
    public static void a(final int n) {
        f.do = (n % 2 == 0);
        f.try = (n > 0);
    }
    
    public void a(final boolean b) {
        this.for = b;
        for (int i = 0; i < f.null; ++i) {
            this.else[i].if = b;
        }
        this.if(true);
    }
    
    public void a(final Graphics graphics, final e e) {
        if (this.long) {
            final Rectangle clipRect = graphics.getClipRect();
            final int height = clipRect.height;
            final int x = clipRect.x;
            final int y = clipRect.y;
            for (int i = 0; i < f.null; ++i) {
                if (f.goto[i] != 0) {
                    final int n = f.if[i] + 1;
                    if (f.try) {
                        graphics.setColor(f.case);
                        graphics.drawLine(f.if[i], y, f.if[i], y + height);
                    }
                    this.else[i].draw(new Rectangle(n, y, f.goto[i], height), graphics, e);
                }
            }
            final int n2 = f.if[f.null - 1] + f.goto[f.null - 1];
            final int n3 = y + height - 1;
            graphics.setColor(f.case);
            if (f.do) {
                graphics.drawLine(x, n3, n2, n3);
            }
            if (f.try) {
                graphics.drawLine(x, y, x, n3);
                graphics.drawLine(n2, y, n2, n3);
            }
            this.long = false;
        }
    }
    
    public void if(final boolean b) {
        this.long = b;
        if (this.else != null) {
            for (int i = 0; i < this.else.length; ++i) {
                this.else[i].for = b;
            }
        }
    }
    
    public static void a(final int[] goto1, final int[] if1) {
        f.goto = goto1;
        f.null = goto1.length;
        f.if = if1;
    }
    
    static {
        f.do = true;
        f.try = true;
        f.new = Color.black;
        f.int = Color.white;
        f.void = Color.white;
        f.a = new Color(0, 0, 128);
        f.char = Color.black;
        f.case = Color.gray;
        f.byte = Color.white;
    }
}
