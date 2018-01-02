// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.a;

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

public class k
{
    private int a;
    private static String[] z;
    
    private k(final int a) {
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
        panel.add(new e(n, 0), k.z[1]);
        panel.add(new Label(s, 0), k.z[0]);
        return panel;
    }
    
    private static flaxchat.i.e b(final String s) {
        final flaxchat.i.e e = new flaxchat.i.e(s);
        e.setFocusable(true);
        e.c(0);
        return e;
    }
    
    public static int a(final Component component, final String s, final String title, final String[] array, final String s2) {
        final boolean a = h.a;
        final Dialog c = flaxchat.a.c.c(component);
        c.setModal(true);
        c.setTitle(title);
        final StringBuffer sb = new StringBuffer();
        final l l = new l(c, sb);
        final e e = new e(new FlowLayout(2));
        int n = 0;
        while (true) {
            Label_0108: {
                if (!a) {
                    break Label_0108;
                }
                final String s3 = array[n];
                final flaxchat.i.e b = b(s3);
                b.a(l);
                b.c(s3);
                e.add(b);
                ++n;
            }
            if (n >= array.length) {
                c.setLayout(new BorderLayout());
                c.add(a(s), k.z[0]);
                c.add(e, k.z[2]);
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
                        if (((flaxchat.i.e)e.getComponent(n3)).l().equals(string)) {
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
                if (flaxchat.a.e.c != 0) {
                    h.a = !a;
                }
                return n4;
            }
            continue;
        }
    }
    
    public static String a(final Component component, final String s) {
        return a(component, s, k.z[3]);
    }
    
    public static String a(final Component component, final String s, final String s2) {
        final String[] a = a(component, null, s2, new String[] { s }, new int[1]);
        if (a == null) {
            return null;
        }
        return a[0];
    }
    
    public static String[] a(final Component component, final String s, final String s2, final String[] array, final int[] array2) {
        return a(component, s, s2, array, array2, 40);
    }
    
    public static String[] a(final Component component, final String s, final String title, final String[] array, final int[] array2, final int n) {
        final boolean a = h.a;
        final TextField[] array3 = new TextField[array.length];
        final Dialog c = flaxchat.a.c.c(component);
        c.setTitle(title);
        c.setModal(true);
        final flaxchat.i.e b = b(k.z[4]);
        final flaxchat.i.e b2 = b(k.z[5]);
        final k k = new k(-1);
        final m m = new m(c, array3, array, array2, k);
        int n2 = 0;
        while (true) {
            Label_0148: {
                if (!a) {
                    break Label_0148;
                }
                int c2 = e.c;
                e.c = ++c2;
                array3[n2] = new TextField(n);
                if (array[n2].startsWith("*")) {
                    array3[n2].setEchoChar('*');
                }
                array3[n2].addActionListener(m);
                ++n2;
            }
            if (n2 < array3.length) {
                continue;
            }
            break;
        }
        b.a(m);
        b2.a(new n(c, k));
        final e a2 = a(new Component[] { b, b2 });
        final e e = new e(new BorderLayout(0, 0));
        e.add(flaxchat.a.c.a(new e(new GridBagLayout()), array, array3), flaxchat.a.k.z[6]);
        c.setLayout(new BorderLayout());
        c.add(e, flaxchat.a.k.z[0]);
        c.add(a2, flaxchat.a.k.z[2]);
        if (s != null) {
            c.add(a(s, 3), flaxchat.a.k.z[6]);
        }
        a(c, component);
        if (k.a != 0) {
            return null;
        }
        final String[] array4 = new String[array3.length];
        int n3 = 0;
        while (true) {
            Label_0351: {
                if (!a) {
                    break Label_0351;
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
        b(component, s, k.z[3]);
    }
    
    public static void b(final Component component, final String s, final String title) {
        final Dialog c = flaxchat.a.c.c(component);
        c.setModal(true);
        c.setTitle(title);
        final flaxchat.i.e b = b(k.z[4]);
        b.a(new o(c));
        final e a = a(new Component[] { b });
        c.setLayout(new BorderLayout());
        c.add(a(s), k.z[0]);
        c.add(a, k.z[2]);
        a(c, component);
    }
    
    public static int a(final Component component, final Component component2, final String title, final String[] array) {
        final boolean a = h.a;
        final Dialog c = flaxchat.a.c.c(component);
        c.setTitle(title);
        c.setModal(true);
        final k k = new k(-1);
        final p p4 = new p(c, k);
        final e e = new e(new FlowLayout());
        int n = 0;
        while (true) {
            Label_0107: {
                if (!a) {
                    break Label_0107;
                }
                final flaxchat.i.e b = b(array[n]);
                b.c(String.valueOf(n));
                b.a(p4);
                e.add(b);
                ++n;
            }
            if (n >= array.length) {
                c.setLayout(new BorderLayout());
                c.add(component2, flaxchat.a.k.z[0]);
                c.add(e, flaxchat.a.k.z[2]);
                a(c, component);
                return k.a();
            }
            continue;
        }
    }
    
    private static void a(final Dialog dialog, final Component component) {
        dialog.pack();
        final Dimension size = dialog.getSize();
        final int n = (int)(h.a(dialog.getTitle(), dialog) * 1.2);
        size.width = ((n >= size.width) ? n : size.width);
        dialog.setSize(size.width + 20, size.height + 20);
        dialog.setLocation(h.a(h.a(component), dialog.getSize()));
        dialog.setVisible(true);
    }
    
    private static e a(final Component[] array) {
        final boolean a = h.a;
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
    
    static void a(final k k, final int n) {
        k.a(n);
    }
    
    static {
        k.z = new String[] { z(z("\u00137J\u001d\u001b\"")), z(z("\u00077W\u001d")), z(z("\u0003=Q\u001d\u0016")), z(z("\u0016>E\u0011=83P")), z(z("\u00043I\b\u0013")), z(z("\u00063^\u000e\u001b·")), z(z("\u001e=V\u001d\u0016")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '~';
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
                    c2 = 'P';
                    break;
                }
                case 1: {
                    c2 = 'R';
                    break;
                }
                case 2: {
                    c2 = '$';
                    break;
                }
                case 3: {
                    c2 = 'i';
                    break;
                }
                default: {
                    c2 = '~';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
