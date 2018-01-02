// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.e;

import java.awt.Dimension;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.Dialog;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Component;

public class j
{
    private int a;
    private static String[] z;
    
    private j(final int a) {
        this.a = a;
    }
    
    private void a(final int a) {
        this.a = a;
    }
    
    private int a() {
        return this.a;
    }
    
    private static Component a(final String s) {
        return a(s, 30);
    }
    
    private static Component a(final String s, final int n) {
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add(new e(n, 0), j.z[1]);
        panel.add(new Label(s, 0), j.z[0]);
        return panel;
    }
    
    private static flaxchat.d.e b(final String s) {
        final flaxchat.d.e e = new flaxchat.d.e(s);
        e.a(true);
        e.c(0);
        return e;
    }
    
    public static int a(final Component component, final String s, final String title, final String[] array, final String s2) {
        final boolean a = g.a;
        final Dialog c = flaxchat.e.c.c(component);
        c.setModal(true);
        c.setTitle(title);
        final StringBuffer sb = new StringBuffer();
        final k k = new k(c, sb);
        final e e = new e(new FlowLayout(2));
        int n = 0;
        while (true) {
            Label_0108: {
                if (!a) {
                    break Label_0108;
                }
                final String s3 = array[n];
                final flaxchat.d.e b = b(s3);
                b.a(k);
                b.c(s3);
                e.add(b);
                ++n;
            }
            if (n >= array.length) {
                c.setLayout(new BorderLayout());
                c.add(a(s), j.z[0]);
                c.add(e, j.z[4]);
                c.addWindowListener(new b(e, s2));
                a(c, component);
                int n2 = -1;
                final int componentCount = e.getComponentCount();
                final String string = sb.toString();
                int n3 = 0;
            Label_0244:
                while (true) {
                    Label_0237: {
                        if (!a) {
                            break Label_0237;
                        }
                        if (((flaxchat.d.e)e.getComponent(n3)).g().equals(string)) {
                            n2 = n3;
                            if (!a) {
                                break Label_0244;
                            }
                        }
                        ++n3;
                    }
                    if (n3 < componentCount) {
                        continue;
                    }
                    break;
                }
                final int n4 = n2;
                if (flaxchat.e.e.c) {
                    g.a = !a;
                }
                return n4;
            }
            continue;
        }
    }
    
    public static String a(final Component component, final String s) {
        return a(component, s, j.z[2]);
    }
    
    public static String a(final Component component, final String s, final String s2) {
        final String[] a = a(component, null, s2, new String[] { s }, new int[1]);
        if (a == null) {
            return null;
        }
        return a[0];
    }
    
    public static String[] a(final Component component, final String s, final String s2, final String[] array, final int[] array2) {
        return a(component, s, s2, array, array2, 10);
    }
    
    public static String[] a(final Component component, final String s, final String title, final String[] array, final int[] array2, final int n) {
        final boolean a = g.a;
        final TextField[] array3 = new TextField[array.length];
        final Dialog c = flaxchat.e.c.c(component);
        c.setTitle(title);
        c.setModal(true);
        final flaxchat.d.e b = b(j.z[3]);
        final flaxchat.d.e b2 = b(j.z[5]);
        final j j = new j(-1);
        final l l = new l(c, array3, array, array2, j);
        int n2 = 0;
        while (true) {
            Label_0149: {
                if (!a) {
                    break Label_0149;
                }
                e.c = !e.c;
                array3[n2] = new TextField(n);
                if (array[n2].startsWith("*")) {
                    array3[n2].setEchoChar('*');
                }
                array3[n2].addActionListener(l);
                ++n2;
            }
            if (n2 < array3.length) {
                continue;
            }
            break;
        }
        b.a(l);
        b2.a(new m(c, j));
        final e a2 = a(new Component[] { b, b2 });
        final e e = new e(new BorderLayout(0, 0));
        e.add(flaxchat.e.c.a(new e(new GridBagLayout()), array, array3), flaxchat.e.j.z[6]);
        c.setLayout(new BorderLayout());
        c.add(e, flaxchat.e.j.z[0]);
        c.add(a2, flaxchat.e.j.z[4]);
        if (s != null) {
            c.add(a(s, 3), flaxchat.e.j.z[6]);
        }
        a(c, component);
        if (j.a != 0) {
            return null;
        }
        final String[] array4 = new String[array3.length];
        int n3 = 0;
        while (true) {
            Label_0352: {
                if (!a) {
                    break Label_0352;
                }
                array4[n3] = array3[n3].getText();
                ++n3;
            }
            if (n3 >= array4.length) {
                return array4;
            }
            continue;
        }
    }
    
    public static void b(final Component component, final String s) {
        b(component, s, j.z[2]);
    }
    
    public static void b(final Component component, final String s, final String title) {
        final Dialog c = flaxchat.e.c.c(component);
        c.setModal(true);
        c.setTitle(title);
        final flaxchat.d.e b = b(j.z[3]);
        b.a(new n(c));
        final e a = a(new Component[] { b });
        c.setLayout(new BorderLayout());
        c.add(a(s), j.z[0]);
        c.add(a, j.z[4]);
        a(c, component);
    }
    
    public static int a(final Component component, final Component component2, final String title, final String[] array) {
        final boolean a = g.a;
        final Dialog c = flaxchat.e.c.c(component);
        c.setTitle(title);
        c.setModal(true);
        final j j = new j(-1);
        final o o = new o(c, j);
        final e e = new e(new FlowLayout());
        int n = 0;
        while (true) {
            Label_0107: {
                if (!a) {
                    break Label_0107;
                }
                final flaxchat.d.e b = b(array[n]);
                b.c(String.valueOf(n));
                b.a(o);
                e.add(b);
                ++n;
            }
            if (n >= array.length) {
                c.setLayout(new BorderLayout());
                c.add(component2, flaxchat.e.j.z[0]);
                c.add(e, flaxchat.e.j.z[4]);
                a(c, component);
                return j.a();
            }
            continue;
        }
    }
    
    private static void a(final Dialog dialog, final Component component) {
        dialog.pack();
        final Dimension size = dialog.getSize();
        final int n = (int)(g.a(dialog.getTitle(), dialog) * 1.2);
        size.width = ((n >= size.width) ? n : size.width);
        dialog.setSize(size.width + 20, size.height + 20);
        dialog.setLocation(g.a(g.a(component), dialog.getSize()));
        dialog.setVisible(true);
    }
    
    private static e a(final Component[] array) {
        final boolean a = g.a;
        final e e = new e(new FlowLayout(2));
        int n = 0;
        while (true) {
            Label_0037: {
                if (!a) {
                    break Label_0037;
                }
                e.add(array[n]);
                ++n;
            }
            if (n >= array.length) {
                return e;
            }
            continue;
        }
    }
    
    static void a(final j j, final int n) {
        j.a(n);
    }
    
    static {
        j.z = new String[] { z(z("SL\u0012\u0007\u0005b")), z(z("GL\u000f\u0007")), z(z("VE\u001d\u000b#xH\b")), z(z("DH\u0011\u0012\r")), z(z("CF\t\u0007\b")), z(z("FH\u0006\u0014\u0005\u00f7")), z(z("^F\u000e\u0007\b")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '`';
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
                    c2 = '\u0010';
                    break;
                }
                case 1: {
                    c2 = ')';
                    break;
                }
                case 2: {
                    c2 = '|';
                    break;
                }
                case 3: {
                    c2 = 's';
                    break;
                }
                default: {
                    c2 = '`';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
