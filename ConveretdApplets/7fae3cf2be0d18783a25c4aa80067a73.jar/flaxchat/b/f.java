// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.b;

import java.awt.TextComponent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import flaxchat.a.e;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Dimension;
import flaxchat.n;
import java.util.Hashtable;
import java.awt.event.ActionListener;
import flaxchat.i.i;

public class f extends i implements ActionListener
{
    private static Hashtable k;
    private n l;
    private Dimension m;
    private static String[] z;
    
    public f(final n l) {
        final boolean f = flaxchat.b.h.f;
        super(false);
        this.m = new Dimension(0, 10);
        this.l = l;
        this.a(this.m);
        this.setLayout(new GridLayout(0, 16, 0, 0));
        this.add(this.a(flaxchat.b.f.z[17]));
        this.add(this.a(flaxchat.b.f.z[10]));
        this.add(this.a(flaxchat.b.f.z[16]));
        this.add(this.a(flaxchat.b.f.z[8]));
        this.add(this.a(flaxchat.b.f.z[11]));
        this.add(this.a(flaxchat.b.f.z[12]));
        this.add(this.a(flaxchat.b.f.z[2]));
        this.add(this.a(flaxchat.b.f.z[3]));
        this.add(this.a(flaxchat.b.f.z[6]));
        this.add(this.a(flaxchat.b.f.z[14]));
        this.add(this.a(flaxchat.b.f.z[13]));
        this.add(this.a(flaxchat.b.f.z[15]));
        this.add(this.a(flaxchat.b.f.z[5]));
        this.add(this.a(flaxchat.b.f.z[4]));
        this.add(this.a(flaxchat.b.f.z[9]));
        this.add(this.a(flaxchat.b.f.z[7]));
        if (f) {
            int c = flaxchat.a.e.c;
            flaxchat.a.e.c = ++c;
        }
    }
    
    protected flaxchat.i.e a(final String s) {
        final Color b = b(s);
        final c c = new c(this, s, "");
        c.a(this);
        c.setBackground(b);
        c.a(f.z[0], b);
        c.a(f.z[1], s);
        return c;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.c((String)((flaxchat.i.e)actionEvent.getSource()).a((Object)f.z[1]));
        this.l.g().requestFocus();
    }
    
    public static Color b(final String s) {
        final Color color = f.k.get(String.valueOf('\u0003') + s);
        if (color == null) {
            return Color.black;
        }
        return color;
    }
    
    protected void c(String value) {
        value = String.valueOf(Integer.parseInt(value));
        final TextComponent g = this.l.g();
        final String substring = g.getText().substring(0, g.getCaretPosition());
        final int length = substring.length();
        if (substring.endsWith("\u0003")) {
            this.l.b(value);
            return;
        }
        if (substring.endsWith(",")) {
            final int lastIndex = substring.lastIndexOf(3);
            if (lastIndex == -1) {
                this.l.b(String.valueOf('\u0003') + value);
                return;
            }
            final String substring2 = substring.substring(lastIndex + 1, length - 1);
            if (substring2.length() > 2) {
                this.l.b(String.valueOf('\u0003') + value);
                return;
            }
            try {
                if (Integer.parseInt(substring2) > 16) {
                    this.l.b(String.valueOf('\u0003') + value);
                    return;
                }
                this.l.b(value);
                return;
            }
            catch (NumberFormatException ex) {
                this.l.b(value);
                return;
            }
        }
        final int lastIndex2 = substring.lastIndexOf(3);
        if (lastIndex2 == -1) {
            this.l.b(String.valueOf('\u0003') + value);
            return;
        }
        final String substring3 = substring.substring(lastIndex2 + 1, length);
        if (substring3.length() > 2) {
            this.l.b(String.valueOf('\u0003') + value);
            return;
        }
        try {
            if (Integer.parseInt(substring3) > 16) {
                this.l.b(String.valueOf('\u0003') + value);
                return;
            }
            this.l.b(String.valueOf(',') + value);
        }
        catch (NumberFormatException ex2) {
            this.l.b(String.valueOf('\u0003') + value);
        }
    }
    
    static {
        f.z = new String[] { z(z("\u0016b!")), z(z("\u0016b!?")), z(z("E8")), z(z("E9")), z(z("D=")), z(z("D<")), z(z("E6")), z(z("D;")), z(z("E=")), z(z("D:")), z(z("E?")), z(z("E:")), z(z("E;")), z(z("D>")), z(z("E7")), z(z("D?")), z(z("E<")), z(z("E>")) };
        (f.k = new Hashtable()).put(z(z("v>c")), Color.white);
        f.k.put(z(z("v>b")), Color.black);
        f.k.put(z(z("v>a")), new Color(0, 0, 139));
        f.k.put(z(z("v>`")), Color.green);
        f.k.put(z(z("v>g")), Color.red);
        f.k.put(z(z("v>f")), new Color(165, 42, 42));
        f.k.put(z(z("v>e")), new Color(128, 0, 128));
        f.k.put(z(z("v>d")), new Color(128, 128, 0));
        f.k.put(z(z("v>k")), Color.yellow);
        f.k.put(z(z("v>j")), Color.green);
        f.k.put(z(z("v?c")), new Color(0, 128, 128));
        f.k.put(z(z("v?b")), Color.cyan);
        f.k.put(z(z("v?a")), Color.blue);
        f.k.put(z(z("v?`")), Color.magenta);
        f.k.put(z(z("v?g")), Color.darkGray);
        f.k.put(z(z("v?f")), Color.lightGray);
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'y';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = 'u';
                    break;
                }
                case 1: {
                    c2 = '\u000e';
                    break;
                }
                case 2: {
                    c2 = 'S';
                    break;
                }
                case 3: {
                    c2 = '\u000e';
                    break;
                }
                default: {
                    c2 = 'y';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
