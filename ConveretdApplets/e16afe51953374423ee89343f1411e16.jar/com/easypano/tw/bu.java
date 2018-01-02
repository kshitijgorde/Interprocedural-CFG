// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Dimension;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;

public class bu extends Panel
{
    private f a;
    
    public bu() {
        this.a = null;
        this.setLayout(new BorderLayout());
        this.addMouseListener(new bf(this));
    }
    
    public void a(final f a) {
        bu bu = this;
        if (!h.q) {
            if ((this.a = a) != null) {
                return;
            }
            bu = this;
        }
        bu.setVisible(false);
    }
    
    public void a(final boolean b, int n, int n2, final Rectangle rectangle) {
        final boolean q = h.q;
        if (this.a != null && rectangle != null) {
            this.removeAll();
            this.setLocation(-100, -100);
            this.setVisible(b);
            Dimension preferredSize = null;
            final f a = this.a;
            Label_0081: {
                if (!q) {
                    if (a == null) {
                        break Label_0081;
                    }
                    this.add(this.a, a("/t)'Y\u001e"));
                    final f a2 = this.a;
                }
                preferredSize = a.getPreferredSize();
                this.setSize(preferredSize);
                this.doLayout();
            }
            if (preferredSize != null) {
                int n4;
                final int n3 = n4 = n + preferredSize.width;
                int width;
                final int n5 = width = rectangle.width;
                if (!q) {
                    if (n3 > n5) {
                        n -= preferredSize.width;
                    }
                    final int n6;
                    n4 = (n6 = n2 + preferredSize.height);
                    final int height;
                    width = (height = rectangle.height);
                }
                Label_0144: {
                    if (!q) {
                        if (n3 <= n5) {
                            break Label_0144;
                        }
                        n4 = n2;
                        width = du.a + du.a;
                    }
                    n2 = n4 - width;
                }
                this.setLocation(n, n2);
            }
            this.repaint();
            if (!q) {
                return;
            }
        }
        if (!b) {
            this.setVisible(b);
        }
    }
    
    public void setVisible(final boolean visible) {
        if (!visible) {
            this.setSize(1, 1);
        }
        super.setVisible(visible);
    }
    
    public void destroyResource() {
        this.a = null;
    }
    
    private static String a(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
        while (true) {
            Label_0089: {
                if (length > 1) {
                    break Label_0089;
                }
                char[] array2;
                char[] array = array2 = charArray;
                int n3;
                int n2 = n3 = n;
                while (true) {
                    final char c = array2[n3];
                    char c2 = '\0';
                    switch (n % 5) {
                        case 0: {
                            c2 = 'l';
                            break;
                        }
                        case 1: {
                            c2 = '\u0011';
                            break;
                        }
                        case 2: {
                            c2 = 'G';
                            break;
                        }
                        case 3: {
                            c2 = 'S';
                            break;
                        }
                        default: {
                            c2 = '<';
                            break;
                        }
                    }
                    array[n2] = (char)(c ^ c2);
                    ++n;
                    if (length != 0) {
                        break;
                    }
                    array = (array2 = charArray);
                    n2 = (n3 = length);
                }
            }
            if (n >= length) {
                return new String(charArray);
            }
            continue;
        }
    }
}
