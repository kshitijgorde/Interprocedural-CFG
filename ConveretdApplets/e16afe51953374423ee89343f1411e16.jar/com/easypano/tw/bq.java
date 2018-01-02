// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Graphics;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.Cursor;
import com.easypano.tw.d.i;
import com.easypano.tw.d.p;
import com.easypano.tw.d.c;
import java.awt.LayoutManager;
import java.awt.Font;
import java.awt.Color;

public class bq extends bp
{
    final Color f;
    Font g;
    String h;
    String i;
    h j;
    h k;
    TWViewer l;
    
    public bq(final TWViewer l) {
        this.f = new Color(85, 85, 85);
        this.g = new Font(e("Tl(\n4w"), 1, 14);
        this.h = e("xq=\u0016a?*>\u0011,>`(\u0015\"`d'\tusj$");
        this.i = e("Ud:\u001f+qk&F\ryw=\u0013:|%\u001d\t.b%\u0019\n:i`;Fj>7|");
        this.j = new h();
        this.k = new h();
        this.l = l;
        this.setSize(370, 180);
        this.setLayout(null);
        this.setVisible(false);
        this.j.setBounds(20, 70, 330, 60);
        final c c = new c(this.j, true);
        this.j.e().a(e("Sj9\u001f)yb!\u0012{8f`Fi 5xKi 5|F\u001eqv0\u0016:~jeF\u0012~fgF\u001a|ii42wm=\u0015{B`:\u0003)f`-H"));
        this.j.a(c);
        this.k.setBounds(20, 140, 240, 30);
        final i i = new i(this.k);
        i.f(2);
        i.d(Color.blue);
        this.k.e().a(e("xq=\u0016a?*>\u0011,>`(\u0015\"`d'\tusj$"));
        this.k.a(i);
        this.k.setCursor(Cursor.getPredefinedCursor(12));
        this.k.a(new cs(this));
        this.add(this.j);
        this.add(this.k);
    }
    
    public void b(final String h) {
        this.k.e().a(h);
        this.h = h;
    }
    
    public void c(final String s) {
        this.j.e().a(s);
    }
    
    public void d(final String i) {
        this.i = i;
    }
    
    public void c(final Graphics graphics) {
        graphics.setColor(this.f);
        graphics.drawString(this.i, 20, 50);
    }
    
    public void a() {
        this.l = null;
    }
    
    private static String e(final String s) {
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
                            c2 = '\u0010';
                            break;
                        }
                        case 1: {
                            c2 = '\u0005';
                            break;
                        }
                        case 2: {
                            c2 = 'I';
                            break;
                        }
                        case 3: {
                            c2 = 'f';
                            break;
                        }
                        default: {
                            c2 = '[';
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
