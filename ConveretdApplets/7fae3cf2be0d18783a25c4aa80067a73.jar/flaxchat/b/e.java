// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.b;

import java.awt.Component;
import flaxchat.d.c;
import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import flaxchat.n;
import java.util.Hashtable;
import java.awt.event.ActionListener;
import flaxchat.i.j;

public class e extends j implements ActionListener
{
    public static Hashtable k;
    public static Hashtable l;
    private n m;
    private static String[] z;
    
    private static void a(final String s, final String s2) {
        e.k.put(s, s2);
        e.l.put(s2, s);
    }
    
    public e(final n m) {
        this.m = m;
    }
    
    protected void a() {
        final boolean f = flaxchat.b.h.f;
        this.setLayout(new FlowLayout(0, 0, 3));
        this.b(e.z[9]);
        this.b(e.z[0]);
        this.b(e.z[5]);
        this.b(e.z[4]);
        this.b(e.z[6]);
        this.b(e.z[1]);
        this.b(e.z[2]);
        this.b(e.z[8]);
        this.b(e.z[3]);
        this.b(e.z[7]);
        if (flaxchat.a.e.c != 0) {
            flaxchat.b.h.f = !f;
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String s = e.k.get(actionEvent.getActionCommand());
        if (s == null) {
            return;
        }
        this.c(s);
    }
    
    protected void b(final String s) {
        final flaxchat.i.e a = this.a(s, null, s);
        a.d(flaxchat.d.c.a(s));
        a.a(this);
        this.add(a);
    }
    
    protected void c(final String s) {
        this.m.b(" " + s + " ");
        this.c();
    }
    
    static {
        e.z = new String[] { z(z("SbTvULfOu\u0010")), z(z("SbTvUKnIa")), z(z("SbTvUShH`\u0017W")), z(z("SbTvUAi]`\u0001")), z(z("SbTvUSsC~\u001d")), z(z("SbTvUHfJb\u0001")), z(z("SbTvUTb[a")), z(z("SbTvUChTt\rSb")), z(z("SbTvUCuC")), z(z("SbTvUSjS~\u001d")) };
        e.k = new Hashtable();
        e.l = new Hashtable();
        a(e.z[9], z(z("\u001a.")));
        a(e.z[0], z(z("\u001aC")));
        a(e.z[5], z(z("\u001aW")));
        a(e.z[4], z(z("\u0018.")));
        a(e.z[6], z(z("\u001b.")));
        a(e.z[1], z(z("\u001a-")));
        a(e.z[2], z(z("\u001a/")));
        a(e.z[8], z(z("\u001a ")));
        a(e.z[3], z(z("\u001a(")));
        a(e.z[7], z(z("\u001a8")));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'x';
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
                    c2 = ' ';
                    break;
                }
                case 1: {
                    c2 = '\u0007';
                    break;
                }
                case 2: {
                    c2 = ':';
                    break;
                }
                case 3: {
                    c2 = '\u0012';
                    break;
                }
                default: {
                    c2 = 'x';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
