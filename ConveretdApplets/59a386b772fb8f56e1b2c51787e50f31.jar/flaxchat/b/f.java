// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.b;

import java.awt.TextComponent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import flaxchat.e.e;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Dimension;
import flaxchat.m;
import java.util.Hashtable;
import java.awt.event.ActionListener;
import flaxchat.d.i;

public class f extends i implements ActionListener
{
    private static Hashtable h;
    private m i;
    private Dimension j;
    private static String[] z;
    
    public f(final m i) {
        final int f = flaxchat.b.h.f;
        super(false);
        this.j = new Dimension(0, 10);
        this.i = i;
        this.setLayout(new GridLayout(0, 16, 0, 0));
        this.add(this.a(flaxchat.b.f.z[7]));
        this.add(this.a(flaxchat.b.f.z[12]));
        this.add(this.a(flaxchat.b.f.z[14]));
        this.add(this.a(flaxchat.b.f.z[13]));
        this.add(this.a(flaxchat.b.f.z[1]));
        this.add(this.a(flaxchat.b.f.z[6]));
        this.add(this.a(flaxchat.b.f.z[4]));
        this.add(this.a(flaxchat.b.f.z[10]));
        this.add(this.a(flaxchat.b.f.z[15]));
        this.add(this.a(flaxchat.b.f.z[5]));
        this.add(this.a(flaxchat.b.f.z[11]));
        this.add(this.a(flaxchat.b.f.z[3]));
        this.add(this.a(flaxchat.b.f.z[0]));
        this.add(this.a(flaxchat.b.f.z[8]));
        this.add(this.a(flaxchat.b.f.z[9]));
        this.add(this.a(flaxchat.b.f.z[2]));
        if (f != 0) {
            flaxchat.e.e.c = !flaxchat.e.e.c;
        }
    }
    
    protected flaxchat.d.e a(final String s) {
        final Color b = b(s);
        final c c = new c(this, s, "");
        c.a(this);
        c.setBackground(b);
        c.a(f.z[17], b);
        c.a(f.z[16], s);
        return c;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.c((String)((flaxchat.d.e)actionEvent.getSource()).a((Object)f.z[16]));
        this.i.g().requestFocus();
    }
    
    public static Color b(final String s) {
        final Color color = f.h.get(String.valueOf('\u0003') + s);
        if (color == null) {
            return Color.black;
        }
        return color;
    }
    
    protected void c(String value) {
        value = String.valueOf(Integer.parseInt(value));
        final TextComponent g = this.i.g();
        final String substring = g.getText().substring(0, g.getCaretPosition());
        final int length = substring.length();
        if (substring.endsWith("\u0003")) {
            this.i.b(value);
            return;
        }
        if (substring.endsWith(",")) {
            final int lastIndex = substring.lastIndexOf(3);
            if (lastIndex == -1) {
                this.i.b(String.valueOf('\u0003') + value);
                return;
            }
            final String substring2 = substring.substring(lastIndex + 1, length - 1);
            if (substring2.length() > 2) {
                this.i.b(String.valueOf('\u0003') + value);
                return;
            }
            try {
                if (Integer.parseInt(substring2) > 16) {
                    this.i.b(String.valueOf('\u0003') + value);
                    return;
                }
                this.i.b(value);
                return;
            }
            catch (NumberFormatException ex) {
                this.i.b(value);
                return;
            }
        }
        final int lastIndex2 = substring.lastIndexOf(3);
        if (lastIndex2 == -1) {
            this.i.b(String.valueOf('\u0003') + value);
            return;
        }
        final String substring3 = substring.substring(lastIndex2 + 1, length);
        if (substring3.length() > 2) {
            this.i.b(String.valueOf('\u0003') + value);
            return;
        }
        try {
            if (Integer.parseInt(substring3) > 16) {
                this.i.b(String.valueOf('\u0003') + value);
                return;
            }
            this.i.b(String.valueOf(',') + value);
        }
        catch (NumberFormatException ex2) {
            this.i.b(String.valueOf('\u0003') + value);
        }
    }
    
    public Dimension getPreferredSize() {
        return this.j;
    }
    
    static {
        f.z = new String[] { z(z("\u001a\"")), z(z("\u001b$")), z(z("\u001a%")), z(z("\u001a!")), z(z("\u001b&")), z(z("\u001b)")), z(z("\u001b%")), z(z("\u001b ")), z(z("\u001a#")), z(z("\u001a$")), z(z("\u001b'")), z(z("\u001a ")), z(z("\u001b!")), z(z("\u001b#")), z(z("\u001b\"")), z(z("\u001b(")), z(z("H|t\u000e")), z(z("H|t")) };
        (f.h = new Hashtable()).put(z(z("( 6")), Color.white);
        f.h.put(z(z("( 7")), Color.black);
        f.h.put(z(z("( 4")), new Color(0, 0, 139));
        f.h.put(z(z("( 5")), Color.green);
        f.h.put(z(z("( 2")), Color.red);
        f.h.put(z(z("( 3")), new Color(165, 42, 42));
        f.h.put(z(z("( 0")), new Color(128, 0, 128));
        f.h.put(z(z("( 1")), new Color(128, 128, 0));
        f.h.put(z(z("( >")), Color.yellow);
        f.h.put(z(z("( ?")), Color.green);
        f.h.put(z(z("(!6")), new Color(0, 128, 128));
        f.h.put(z(z("(!7")), Color.cyan);
        f.h.put(z(z("(!4")), Color.blue);
        f.h.put(z(z("(!5")), Color.magenta);
        f.h.put(z(z("(!2")), Color.darkGray);
        f.h.put(z(z("(!3")), Color.lightGray);
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '\u000f';
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
                    c2 = '+';
                    break;
                }
                case 1: {
                    c2 = '\u0010';
                    break;
                }
                case 2: {
                    c2 = '\u0006';
                    break;
                }
                case 3: {
                    c2 = '?';
                    break;
                }
                default: {
                    c2 = '\u000f';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
