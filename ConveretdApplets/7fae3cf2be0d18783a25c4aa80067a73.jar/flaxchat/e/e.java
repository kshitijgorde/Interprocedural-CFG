// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.e;

import java.awt.Component;
import flaxchat.o;
import java.awt.Dimension;
import java.awt.Container;
import flaxchat.d.b;
import flaxchat.n;

public class e extends a
{
    private n b;
    private int c;
    private int d;
    private static String[] z;
    
    public e(final n b) {
        this.c = flaxchat.d.b.a(e.z[4], 22);
        this.d = 150;
        this.b = b;
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        synchronized (container.getTreeLock()) {
            final o t = this.b.t();
            final Container w = this.b.w();
            t.getPreferredSize();
            w.getPreferredSize();
            // monitorexit(container.getTreeLock())
            return container.getSize();
        }
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        synchronized (container.getTreeLock()) {
            final o t = this.b.t();
            final Container w = this.b.w();
            t.getPreferredSize();
            w.getPreferredSize();
            // monitorexit(container.getTreeLock())
            return container.getSize();
        }
    }
    
    public void layoutContainer(final Container container) {
        final int a = flaxchat.e.a.a;
        synchronized (container.getTreeLock()) {
            final flaxchat.e.b b = (flaxchat.e.b)this.b.t().getLayout();
            final String a2 = b.a();
            final Component v = this.b.v();
            final Container w = this.b.w();
            final o t = this.b.t();
            final Dimension preferredLayoutSize = b.preferredLayoutSize(t);
            Dimension preferredLayoutSize2 = null;
            Label_0133: {
                if (w == null) {
                    preferredLayoutSize2 = new Dimension();
                    if (a == 0) {
                        break Label_0133;
                    }
                    int c = flaxchat.a.e.c;
                    flaxchat.a.e.c = ++c;
                }
                preferredLayoutSize2 = w.getLayout().preferredLayoutSize(w);
                if (preferredLayoutSize2.height <= 0) {
                    preferredLayoutSize2.height = this.c;
                }
            }
            final Dimension size = container.getSize();
            Label_0720: {
                if (e.z[0].equals(a2)) {
                    if (preferredLayoutSize.height <= 0) {
                        preferredLayoutSize.height = this.c;
                    }
                    t.setBounds(0, 0, size.width, preferredLayoutSize.height);
                    if (w != null) {
                        w.setBounds(0, preferredLayoutSize.height, size.width, preferredLayoutSize2.height);
                    }
                    v.setBounds(0, preferredLayoutSize.height + preferredLayoutSize2.height, size.width, size.height - (preferredLayoutSize.height + preferredLayoutSize2.height));
                    if (a == 0) {
                        break Label_0720;
                    }
                }
                if (e.z[3].equals(a2)) {
                    if (preferredLayoutSize.height <= 0) {
                        preferredLayoutSize.height = this.c;
                    }
                    if (w != null) {
                        w.setBounds(0, 0, size.width, preferredLayoutSize2.height);
                    }
                    v.setBounds(0, preferredLayoutSize2.height, size.width, size.height - (preferredLayoutSize.height + preferredLayoutSize2.height));
                    t.setBounds(0, size.height - preferredLayoutSize.height, size.width, preferredLayoutSize.height);
                    if (a == 0) {
                        break Label_0720;
                    }
                }
                if (e.z[1].equals(a2)) {
                    if (preferredLayoutSize.width > this.d) {
                        preferredLayoutSize.width = this.d;
                    }
                    final int n = 0;
                    final int n2 = 0;
                    final int width = size.width;
                    final int height = preferredLayoutSize2.height;
                    if (w != null) {
                        w.setBounds(n, n2, width, height);
                    }
                    t.setBounds(size.width - preferredLayoutSize.width, preferredLayoutSize2.height, preferredLayoutSize.width, size.height - preferredLayoutSize2.height);
                    v.setBounds(0, preferredLayoutSize2.height, size.width - preferredLayoutSize.width, size.height - preferredLayoutSize2.height);
                    if (a == 0) {
                        break Label_0720;
                    }
                }
                if (e.z[2].equals(a2)) {
                    if (preferredLayoutSize.width > this.d) {
                        preferredLayoutSize.width = this.d;
                    }
                    final int n3 = 0;
                    final int n4 = 0;
                    final int width2 = size.width;
                    final int height2 = preferredLayoutSize2.height;
                    if (w != null) {
                        w.setBounds(n3, n4, width2, height2);
                    }
                    t.setBounds(0, preferredLayoutSize2.height, preferredLayoutSize.width, size.height - preferredLayoutSize2.height);
                    v.setBounds(preferredLayoutSize.width, preferredLayoutSize2.height, size.width - preferredLayoutSize.width, size.height - preferredLayoutSize2.height);
                }
            }
        }
        // monitorexit(container.getTreeLock())
    }
    
    static {
        e.z = new String[] { z(z(">}o\u0019\u001f")), z(z("5sn\u0019")), z(z("'wn\u0019")), z(z("#}h\u0019\u001f")), z(z("\u0012gi\u0019\u0018\u001e<u\b\u001e\u0017zi")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'w';
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
                    c2 = 'p';
                    break;
                }
                case 1: {
                    c2 = '\u0012';
                    break;
                }
                case 2: {
                    c2 = '\u001d';
                    break;
                }
                case 3: {
                    c2 = 'm';
                    break;
                }
                default: {
                    c2 = 'w';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
