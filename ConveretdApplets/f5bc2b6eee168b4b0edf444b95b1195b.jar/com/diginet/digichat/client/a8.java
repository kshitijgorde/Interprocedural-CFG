// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.util.Vector;
import java.util.StringTokenizer;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Dimension;
import com.diginet.digichat.network.t;
import com.diginet.digichat.common.b5;
import com.esial.util.LanguageSupport;
import com.diginet.digichat.common.a6;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import com.diginet.digichat.util.p;

public final class a8 extends a2 implements p
{
    protected final synchronized void a(final ChatMessage chatMessage, final int n, final Graphics graphics) {
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
            final Font font = super.l.df.getFont();
            final Font boldFont = super.l.df.getBoldFont();
            if (chatMessage.p) {
                graphics.setColor(super.l.df.privateBackground);
            }
            else if (chatMessage.n && super.l.c0) {
                graphics.setColor(super.l.df.flaggedMessages);
            }
            else if (chatMessage.t) {
                graphics.setColor(super.l.df.flaggedMessages);
            }
            else {
                graphics.setColor(super.l.df.normalBackground);
            }
            if (graphics.getColor() != super.l.df.normalBackground && super.n && super.b != null) {
                graphics.fillRect(0, n, size2.width, 2);
                graphics.fillRect(size2.width - 2 - this.a(), n, 2, chatMessage.b - 1);
                graphics.fillRect(0, n, 2, chatMessage.b - 1);
                graphics.fillRect(0, n + chatMessage.b - 1 - 2, size2.width, 2);
            }
            if (!super.n || super.b == null) {
                graphics.fillRect(0, n, size2.width, chatMessage.b - 1);
            }
            if (chatMessage.u) {
                if (graphics.getColor() != super.l.df.flaggedMessages) {
                    graphics.setColor(super.l.df.flaggedMessages);
                }
                else {
                    graphics.setColor(super.l.df.privateBackground);
                }
                graphics.fillRect(0, n, size2.width - this.a() - 7, 3);
                graphics.fillRect(size2.width - this.a() - 7, n, 3, chatMessage.b - 1);
                graphics.fillRect(0, n + 3, 3, chatMessage.b - 4);
                graphics.fillRect(3, n + chatMessage.b - 4, size2.width - this.a() - 7, 3);
            }
            graphics.setColor(Color.white);
            graphics.drawLine(0, n + chatMessage.b - 1, size2.width, n + chatMessage.b - 1);
            if (chatMessage.h != null && n4 + 24 >= n2 && n4 <= n3) {
                graphics.drawImage(chatMessage.h, 6, n4, super.j);
            }
            final FontMetrics fontMetrics = this.getFontMetrics(font);
            final int height = fontMetrics.getHeight();
            final int n5 = n4 + fontMetrics.getAscent() - 2;
            int n6 = n5 + height;
            if (n5 >= n2 && n5 - height <= n3) {
                if (chatMessage.p) {
                    graphics.setColor(super.l.df.privateMessages);
                }
                else if (chatMessage.r) {
                    graphics.setColor(super.l.df.flaggedMessages);
                }
                else if (chatMessage.n && !super.l.c0) {
                    graphics.setColor(super.l.df.flaggedMessages);
                }
                else if (chatMessage.o && !chatMessage.s) {
                    graphics.setColor(new Color(153));
                }
                else if (chatMessage.s) {
                    graphics.setColor(new Color(39168));
                }
                else {
                    graphics.setColor(super.l.df.normalMessages);
                }
                graphics.setFont(boldFont);
                graphics.drawString(chatMessage.j, a2.a, n5);
                int stringWidth = 0;
                if (super.l.cz) {
                    graphics.setFont(super.l.df.getDateFont());
                    graphics.drawString(chatMessage.k, a2.a + this.getFontMetrics(boldFont).stringWidth(chatMessage.j + "##"), n5);
                    stringWidth = this.getFontMetrics(super.l.df.getDateFont()).stringWidth(chatMessage.k);
                }
                if (chatMessage.p && super.n) {
                    final User2 user2 = (User2)super.l.aj.d(chatMessage.f);
                    if (user2 != null && user2.u(59) && ((a6)super.l.al.d(user2.b)).a()) {
                        final String translate = LanguageSupport.translate("(Private message from Guest Speaker)");
                        graphics.setColor(super.l.df.normalMessages);
                        graphics.drawString(translate, a2.a + stringWidth + fontMetrics.stringWidth(chatMessage.j + "######"), n5);
                    }
                    else if (chatMessage.g == -1) {
                        final String translate2 = LanguageSupport.translate("(Single click on this message to reply)");
                        graphics.setColor(super.l.df.normalMessages);
                        graphics.drawString(translate2, a2.a + stringWidth + fontMetrics.stringWidth(chatMessage.j + "######"), n5);
                    }
                }
            }
            graphics.setColor(super.l.df.normalMessages);
            graphics.setFont(font);
            int i = 0;
            for (int j = 0; j < chatMessage.c; ++j) {
                int a = a2.a;
                int k = chatMessage.l[j];
                final int n7 = chatMessage.l[j + 1];
                final int a2 = b5.a(chatMessage.i.substring(k, n7));
                int n8 = 0;
                if (a2 > 0 && 16 > fontMetrics.getHeight()) {
                    n8 = 16 - fontMetrics.getHeight();
                }
                final boolean b = n6 + 1 >= n2 && n6 - height - n8 <= n3;
                while (i < chatMessage.d) {
                    int n9 = chatMessage.m[2 * i];
                    int n10 = chatMessage.m[2 * i + 1];
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
                    if (b) {
                        if (n9 != k) {
                            if (a == com.diginet.digichat.client.a2.a) {
                                while (k < n7) {
                                    if (chatMessage.i.charAt(k) != ' ') {
                                        break;
                                    }
                                    ++k;
                                }
                            }
                            String s;
                            try {
                                s = chatMessage.i.substring(k, n9);
                            }
                            catch (StringIndexOutOfBoundsException ex) {
                                s = chatMessage.i.substring(k, k);
                            }
                            graphics.drawString(s, a, n6);
                            a += fontMetrics.stringWidth(s);
                        }
                        graphics.setColor(Color.blue);
                        final String substring = chatMessage.i.substring(n9, n10);
                        final int stringWidth2 = fontMetrics.stringWidth(substring);
                        graphics.drawLine(a, n6 + 1, a + stringWidth2, n6 + 1);
                        if (chatMessage.p) {
                            graphics.setColor(super.l.df.privateBackground);
                        }
                        else if (chatMessage.n && super.l.c0) {
                            graphics.setColor(super.l.df.flaggedMessages);
                        }
                        else {
                            graphics.setColor(super.l.df.normalBackground);
                        }
                        graphics.drawString(substring, a + 1, n6);
                        graphics.drawString(substring, a - 1, n6);
                        graphics.setColor(Color.blue);
                        graphics.drawString(substring, a, n6);
                        graphics.setColor(super.l.df.normalMessages);
                        a += stringWidth2;
                    }
                    k = n10;
                }
                if (b && k != n7) {
                    String s2 = chatMessage.i.substring(k, n7);
                    if (a == com.diginet.digichat.client.a2.a) {
                        s2 = s2.trim();
                    }
                    if (a2 < 1 || !t.a(super.l.a5, 58)) {
                        graphics.drawString(s2, a, n6);
                    }
                    else {
                        int n11 = 0;
                        final int length = s2.length();
                        int b2 = -1;
                        final int n12 = n6 + (height + n8 - fontMetrics.getHeight()) / 2;
                        final int n13 = n6 - fontMetrics.getAscent() + (height + n8 - 16) / 2;
                        for (int l = 0; l < a2; ++l) {
                            b2 = b5.b(s2, ++b2);
                            if (b2 == -1 || b2 >= length) {
                                break;
                            }
                            int index = s2.indexOf(32, b2 + 1);
                            if (index == -1) {
                                index = length;
                            }
                            final b5 b3 = b5.b(s2.substring(b2, index));
                            if (b3 != null && b3.b != null) {
                                graphics.drawString(s2.substring(n11, b2), a, n12);
                                final int n14 = a + fontMetrics.stringWidth(s2.substring(n11, b2) + " ");
                                graphics.drawImage(b3.b, n14, n13, super.j);
                                a = n14 + (16 + fontMetrics.charWidth(' '));
                                n11 = b2 + b3.getName().length();
                            }
                            else {
                                graphics.drawString(s2.substring(n11, index), a, n12);
                                a += fontMetrics.stringWidth(s2.substring(n11, index));
                                n11 = index;
                            }
                        }
                        graphics.drawString(s2.substring(n11, length), a, n12);
                    }
                }
                n6 += height + n8;
            }
        }
        catch (Exception ex2) {}
    }
    
    public final synchronized void a(final ChatMessage chatMessage, int n, final FontMetrics fontMetrics) {
        try {
            final String s = new String(chatMessage.i);
            int n2 = 0;
            chatMessage.l[0] = 0;
            chatMessage.c = 0;
            n = (chatMessage.u ? (n - 3) : n);
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
                        if (t.a(super.l.a5, 58)) {
                            n8 = (16 + 2 * fontMetrics.charWidth(' ')) * b5.a(array[i].substring(n5, n7)) - b5.a(array[i].substring(n5, n7), fontMetrics);
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
                for (int n10 = 1; n10 <= n3 && chatMessage.c < 30; ++n10) {
                    chatMessage.l[++chatMessage.c] = array2[n10] + n2;
                }
                n2 += array2[n3];
            }
            chatMessage.i = "";
            for (int k = 0; k < array.length; ++k) {
                chatMessage.i += array[k];
            }
            int n11 = 0;
            if (16 > fontMetrics.getHeight()) {
                for (int l = 0; l < chatMessage.c; ++l) {
                    if (b5.a(chatMessage.i.substring(chatMessage.l[l], chatMessage.l[l + 1])) > 0) {
                        n11 += 16 - fontMetrics.getHeight();
                    }
                }
            }
            chatMessage.b = 6 + fontMetrics.getHeight() * (chatMessage.c + 1) + n11;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final String a(final Object o) {
        if (super.n) {
            return LanguageSupport.translate("Chat messages are displayed here.  Single-click on a private message to reply.");
        }
        return LanguageSupport.translate("Private messages are displayed here.");
    }
    
    public a8(final h h, final boolean b) {
        super(h, b);
        a2.a = 36;
    }
}
