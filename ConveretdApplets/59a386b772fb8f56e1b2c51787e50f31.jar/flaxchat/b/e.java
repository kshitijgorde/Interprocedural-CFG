// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.b;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import flaxchat.m;
import java.util.Hashtable;
import java.awt.event.ActionListener;
import flaxchat.d.j;

public class e extends j implements ActionListener
{
    public static Hashtable h;
    public static Hashtable i;
    private m j;
    private static String[] z;
    
    private static void a(final String s, final String s2) {
        e.h.put(s, s2);
        e.i.put(s2, s);
    }
    
    public e(final m j) {
        this.j = j;
    }
    
    protected void a() {
        int f = flaxchat.b.h.f;
        this.setLayout(new FlowLayout(0, 0, 3));
        this.b(e.z[3]);
        this.b(e.z[9]);
        this.b(e.z[8]);
        this.b(e.z[6]);
        this.b(e.z[4]);
        this.b(e.z[0]);
        this.b(e.z[7]);
        this.b(e.z[2]);
        this.b(e.z[5]);
        this.b(e.z[1]);
        if (flaxchat.e.e.c) {
            flaxchat.b.h.f = ++f;
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String s = e.h.get(actionEvent.getActionCommand());
        if (s == null) {
            return;
        }
        this.c(s);
    }
    
    protected void b(final String s) {
        final flaxchat.d.e a = this.a(s, null, s);
        a.a(this);
        this.add(a);
    }
    
    protected void c(final String s) {
        this.j.b(" " + s + " ");
        this.c();
    }
    
    static {
        e.z = new String[] { z(z("SsV\u0018NK\u007fK\u000f")), z(z("SsV\u0018NCyV\u001a\u0016Ss")), z(z("SsV\u0018NCdA")), z(z("SsV\u0018NS{Q\u0010\u0006")), z(z("SsV\u0018NTsY\u000f")), z(z("SsV\u0018NAx_\u000e\u001a")), z(z("SsV\u0018NSbA\u0010\u0006")), z(z("SsV\u0018NSyJ\u000e\fW")), z(z("SsV\u0018NHwH\f\u001a")), z(z("SsV\u0018NLwM\u001b\u000b")) };
        e.h = new Hashtable();
        e.i = new Hashtable();
        a(e.z[3], z(z("\u001a?")));
        a(e.z[9], z(z("\u001aR")));
        a(e.z[8], z(z("\u001aF")));
        a(e.z[6], z(z("\u0018?")));
        a(e.z[4], z(z("\u001b?")));
        a(e.z[0], z(z("\u001a<")));
        a(e.z[7], z(z("\u001a>")));
        a(e.z[2], z(z("\u001a1")));
        a(e.z[5], z(z("\u001a9")));
        a(e.z[1], z(z("\u001a)")));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'c';
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
                    c2 = '\u0016';
                    break;
                }
                case 2: {
                    c2 = '8';
                    break;
                }
                case 3: {
                    c2 = '|';
                    break;
                }
                default: {
                    c2 = 'c';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
