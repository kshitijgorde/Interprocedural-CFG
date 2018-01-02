// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.util.Vector;
import java.util.StringTokenizer;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Dimension;
import com.diginet.digichat.network.v;
import com.diginet.digichat.common.bt;
import com.esial.util.d;
import com.diginet.digichat.common.a4;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import com.diginet.digichat.util.s;

public final class a6 extends az implements s
{
    protected final synchronized void a(final ay ay, final int n, final Graphics graphics) {
        try {
            Rectangle clipRect = graphics.getClipRect();
            if (clipRect == null) {
                final Dimension size = this.size();
                clipRect = new Rectangle(0, 0, size.width, size.height);
            }
            final int n2 = clipRect.y - 1;
            final int n3 = n2 + clipRect.height + 2;
            final Dimension size2 = this.size();
            final int n4 = 3 + n + 1;
            final Font b = super.l.ca.b();
            final Font a = super.l.ca.a();
            if (ay.p) {
                graphics.setColor(super.l.ca.o);
            }
            else if (ay.n && super.l.b9) {
                graphics.setColor(super.l.ca.l);
            }
            else if (ay.t) {
                graphics.setColor(super.l.ca.l);
            }
            else {
                graphics.setColor(super.l.ca.m);
            }
            if (graphics.getColor() != super.l.ca.m && super.n && super.b != null) {
                graphics.fillRect(0, n, size2.width, 2);
                graphics.fillRect(size2.width - 2 - this.a(), n, 2, ay.b - 1);
                graphics.fillRect(0, n, 2, ay.b - 1);
                graphics.fillRect(0, n + ay.b - 1 - 2, size2.width, 2);
            }
            if (!super.n || super.b == null) {
                graphics.fillRect(0, n, size2.width, ay.b - 1);
            }
            graphics.setColor(Color.white);
            graphics.drawLine(0, n + ay.b - 1, size2.width, n + ay.b - 1);
            if (ay.h != null && n4 + 24 >= n2 && n4 <= n3) {
                graphics.drawImage(ay.h, 6, n4, super.j);
            }
            final FontMetrics fontMetrics = this.getFontMetrics(b);
            final int height = fontMetrics.getHeight();
            final int n5 = n4 + fontMetrics.getAscent() - 2;
            int n6 = n5 + height;
            if (n5 >= n2 && n5 - height <= n3) {
                if (ay.p) {
                    graphics.setColor(super.l.ca.n);
                }
                else if (ay.r) {
                    graphics.setColor(super.l.ca.l);
                }
                else if (ay.n && !super.l.b9) {
                    graphics.setColor(super.l.ca.l);
                }
                else if (ay.o && !ay.s) {
                    graphics.setColor(new Color(153));
                }
                else if (ay.s) {
                    graphics.setColor(new Color(39168));
                }
                else {
                    graphics.setColor(super.l.ca.k);
                }
                graphics.setFont(a);
                graphics.drawString(ay.j, az.a, n5);
                int stringWidth = 0;
                if (super.l.b8) {
                    graphics.setFont(super.l.ca.c());
                    graphics.drawString(ay.k, az.a + this.getFontMetrics(a).stringWidth(ay.j + "##"), n5);
                    stringWidth = this.getFontMetrics(super.l.ca.c()).stringWidth(ay.k);
                }
                if (ay.p && super.n) {
                    final aw aw = (aw)super.l.aa.d(ay.f);
                    if (aw != null && aw.i(59) && ((a4)super.l.ab.d(aw.b)).a()) {
                        final String a2 = com.esial.util.d.a("(Private message from Guest Speaker)");
                        graphics.setColor(super.l.ca.k);
                        graphics.drawString(a2, az.a + stringWidth + fontMetrics.stringWidth(ay.j + "######"), n5);
                    }
                    else if (ay.g == -1) {
                        final String a3 = com.esial.util.d.a("(Single click on this message to reply)");
                        graphics.setColor(super.l.ca.k);
                        graphics.drawString(a3, az.a + stringWidth + fontMetrics.stringWidth(ay.j + "######"), n5);
                    }
                }
            }
            graphics.setColor(super.l.ca.k);
            graphics.setFont(b);
            int i = 0;
            for (int j = 0; j < ay.c; ++j) {
                int a4 = az.a;
                int k = ay.l[j];
                final int n7 = ay.l[j + 1];
                final int a5 = bt.a(ay.i.substring(k, n7));
                int n8 = 0;
                if (a5 > 0 && 16 > fontMetrics.getHeight()) {
                    n8 = 16 - fontMetrics.getHeight();
                }
                final boolean b2 = n6 + 1 >= n2 && n6 - height - n8 <= n3;
                while (i < ay.d) {
                    int n9 = ay.m[2 * i];
                    int n10 = ay.m[2 * i + 1];
                    if (n10 > n7) {
                        n10 = n7;
                    }
                    else {
                        ++i;
                    }
                    if (n9 < k) {
                        n9 = k;
                    }
                    if (k >= n10 || n7 <= n9) {
                        break;
                    }
                    if (b2) {
                        if (n9 != k) {
                            if (a4 == az.a) {
                                while (k < n7) {
                                    if (ay.i.charAt(k) != ' ') {
                                        break;
                                    }
                                    ++k;
                                }
                            }
                            String s;
                            try {
                                s = ay.i.substring(k, n9);
                            }
                            catch (StringIndexOutOfBoundsException ex) {
                                s = ay.i.substring(k, k);
                            }
                            graphics.drawString(s, a4, n6);
                            a4 += fontMetrics.stringWidth(s);
                        }
                        graphics.setColor(Color.blue);
                        final String substring = ay.i.substring(n9, n10);
                        final int stringWidth2 = fontMetrics.stringWidth(substring);
                        graphics.drawLine(a4, n6 + 1, a4 + stringWidth2, n6 + 1);
                        if (ay.p) {
                            graphics.setColor(super.l.ca.o);
                        }
                        else if (ay.n && super.l.b9) {
                            graphics.setColor(super.l.ca.l);
                        }
                        else {
                            graphics.setColor(super.l.ca.m);
                        }
                        graphics.drawString(substring, a4 + 1, n6);
                        graphics.drawString(substring, a4 - 1, n6);
                        graphics.setColor(Color.blue);
                        graphics.drawString(substring, a4, n6);
                        graphics.setColor(super.l.ca.k);
                        a4 += stringWidth2;
                    }
                    k = n10;
                }
                if (b2 && k != n7) {
                    String s2 = ay.i.substring(k, n7);
                    if (a4 == az.a) {
                        s2 = s2.trim();
                    }
                    if (a5 < 1 || !v.a(super.l.ar, 58)) {
                        graphics.drawString(s2, a4, n6);
                    }
                    else {
                        int n11 = 0;
                        final int length = s2.length();
                        int b3 = -1;
                        final int n12 = n6 + (height + n8 - fontMetrics.getHeight()) / 2;
                        final int n13 = n6 - fontMetrics.getAscent() + (height + n8 - 16) / 2;
                        for (int l = 0; l < a5; ++l) {
                            b3 = bt.b(s2, ++b3);
                            if (b3 == -1 || b3 >= length) {
                                break;
                            }
                            int index = s2.indexOf(32, b3 + 1);
                            if (index == -1) {
                                index = length;
                            }
                            final bt b4 = bt.b(s2.substring(b3, index));
                            if (b4 != null && b4.b != null) {
                                graphics.drawString(s2.substring(n11, b3), a4, n12);
                                final int n14 = a4 + fontMetrics.stringWidth(s2.substring(n11, b3) + " ");
                                graphics.drawImage(b4.b, n14, n13, super.j);
                                a4 = n14 + (16 + fontMetrics.charWidth(' '));
                                n11 = b3 + b4.r().length();
                            }
                            else {
                                graphics.drawString(s2.substring(n11, index), a4, n12);
                                a4 += fontMetrics.stringWidth(s2.substring(n11, index));
                                n11 = index;
                            }
                        }
                        graphics.drawString(s2.substring(n11, length), a4, n12);
                    }
                }
                n6 += height + n8;
            }
        }
        catch (Exception ex2) {}
    }
    
    public final synchronized void a(final ay ay, final int n, final FontMetrics fontMetrics) {
        try {
            final String s = new String(ay.i);
            int n2 = 0;
            ay.l[0] = 0;
            ay.c = 0;
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n");
            final Vector vector = new Vector<String>();
            while (stringTokenizer.hasMoreTokens()) {
                vector.addElement(stringTokenizer.nextToken());
            }
            final String[] array = new String[vector.size()];
            vector.copyInto(array);
            for (int i = 0; i < array.length; ++i) {
                final int[] array2 = new int[31];
                array2[0] = 0;
                int n3 = 0;
                if (i != array.length - 1) {
                    final String[] array3 = array;
                    final int n4 = i;
                    array3[n4] += "\n";
                }
                int n5 = 0;
                final int length = array[i].length();
                int j;
                do {
                    while (n5 < length && array[i].charAt(n5) == ' ') {
                        ++n5;
                    }
                    int n6 = n5;
                    int n7;
                    j = (n7 = length);
                    while (j > n6) {
                        int n8 = 0;
                        if (v.a(super.l.ar, 58)) {
                            n8 = (16 + 2 * fontMetrics.charWidth(' ')) * bt.a(array[i].substring(n5, n7)) - bt.a(array[i].substring(n5, n7), fontMetrics);
                        }
                        final int n9 = fontMetrics.stringWidth(array[i].substring(n5, n7)) + n8;
                        if (n9 <= n && n7 == length) {
                            break;
                        }
                        if (n9 < n) {
                            n6 = 1 + n7;
                        }
                        else {
                            j = n7;
                        }
                        n7 = (n6 + j) / 2;
                    }
                    final int lastIndex = array[i].lastIndexOf(32, n7);
                    if (lastIndex < n5) {
                        n5 = n7;
                    }
                    else {
                        n5 = lastIndex;
                    }
                    array2[++n3] = n5;
                } while (n3 < 30 && j < length);
                array2[n3] = length;
                for (int n10 = 1; n10 <= n3 && ay.c < 30; ++n10) {
                    ay.l[++ay.c] = array2[n10] + n2;
                }
                n2 += array2[n3];
            }
            ay.i = "";
            for (int k = 0; k < array.length; ++k) {
                ay.i += array[k];
            }
            int n11 = 0;
            if (16 > fontMetrics.getHeight()) {
                for (int l = 0; l < ay.c; ++l) {
                    if (bt.a(ay.i.substring(ay.l[l], ay.l[l + 1])) > 0) {
                        n11 += 16 - fontMetrics.getHeight();
                    }
                }
            }
            ay.b = 6 + fontMetrics.getHeight() * (ay.c + 1) + n11;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final String a(final Object o) {
        if (super.n) {
            return com.esial.util.d.a("Chat messages are displayed here.  Single-click on a private message to reply.");
        }
        return com.esial.util.d.a("Private messages are displayed here.");
    }
    
    public a6(final i i, final boolean b) {
        super(i, b);
        az.a = 36;
    }
}
