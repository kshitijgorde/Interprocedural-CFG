// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.liteclient;

import com.diginet.digichat.client.h;
import java.util.Vector;
import java.util.StringTokenizer;
import java.awt.FontMetrics;
import java.awt.Dimension;
import com.esial.util.LanguageSupport;
import com.diginet.digichat.common.a6;
import com.diginet.digichat.client.User2;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Graphics;
import com.diginet.digichat.client.ChatMessage;
import com.diginet.digichat.client.a2;

public final class c6 extends a2
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
            final Font font2 = new Font(font.getName(), font.getStyle() | 0x1, font.getSize());
            graphics.setColor(chatMessage.p ? super.l.df.privateBackground : super.l.df.normalBackground);
            graphics.fillRect(0, n, size2.width, chatMessage.b - 1);
            graphics.setColor(Color.white);
            graphics.drawLine(0, n + chatMessage.b - 1, size2.width, n + chatMessage.b - 1);
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
                else if (chatMessage.n) {
                    graphics.setColor(super.l.df.flaggedMessages);
                }
                else if (chatMessage.o) {
                    graphics.setColor(new Color(153));
                }
                else {
                    graphics.setColor(super.l.df.normalMessages);
                }
                graphics.setFont(font2);
                graphics.drawString(chatMessage.j, a2.a, n5);
                if (chatMessage.p && super.n) {
                    final User2 user2 = (User2)super.l.aj.d(chatMessage.f);
                    if (user2 != null && user2.u(59) && ((a6)super.l.al.d(user2.b)).a()) {
                        final String translate = LanguageSupport.translate("(Private message from Guest Speaker)");
                        graphics.setColor(super.l.df.normalMessages);
                        graphics.drawString(translate, a2.a + fontMetrics.stringWidth(chatMessage.j + "######"), n5);
                    }
                    else {
                        final String translate2 = LanguageSupport.translate("(Single click on this message to reply)");
                        graphics.setColor(super.l.df.normalMessages);
                        graphics.drawString(translate2, a2.a + fontMetrics.stringWidth(chatMessage.j + "######"), n5);
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
                final boolean b = n6 + 1 >= n2 && n6 - height <= n3;
                while (i < chatMessage.d) {
                    int n8 = chatMessage.m[2 * i];
                    int n9 = chatMessage.m[2 * i + 1];
                    if (n9 > n7) {
                        n9 = n7;
                    }
                    else {
                        ++i;
                    }
                    if (n8 < k) {
                        n8 = k;
                    }
                    if (k >= n9 || n7 <= n8) {
                        break;
                    }
                    if (b) {
                        if (n8 != k) {
                            if (a == a2.a) {
                                while (k < n7) {
                                    if (chatMessage.i.charAt(k) != ' ') {
                                        break;
                                    }
                                    ++k;
                                }
                            }
                            String s;
                            try {
                                s = chatMessage.i.substring(k, n8);
                            }
                            catch (StringIndexOutOfBoundsException ex) {
                                s = chatMessage.i.substring(k, k);
                            }
                            graphics.drawString(s, a, n6);
                            a += fontMetrics.stringWidth(s);
                        }
                        graphics.setColor(Color.blue);
                        final String substring = chatMessage.i.substring(n8, n9);
                        final int stringWidth = fontMetrics.stringWidth(substring);
                        graphics.drawLine(a, n6 + 1, a + stringWidth, n6 + 1);
                        graphics.setColor(chatMessage.p ? super.l.df.privateBackground : super.l.df.normalBackground);
                        graphics.drawString(substring, a + 1, n6);
                        graphics.drawString(substring, a - 1, n6);
                        graphics.setColor(Color.blue);
                        graphics.drawString(substring, a, n6);
                        graphics.setColor(super.l.df.normalMessages);
                        a += stringWidth;
                    }
                    k = n9;
                }
                if (b && k != n7) {
                    String s2 = chatMessage.i.substring(k, n7);
                    if (a == a2.a) {
                        s2 = s2.trim();
                    }
                    graphics.drawString(s2, a, n6);
                }
                n6 += height;
            }
        }
        catch (Exception ex2) {}
    }
    
    public final synchronized void a(final ChatMessage chatMessage, final int n, final FontMetrics fontMetrics) {
        try {
            final String s = new String(chatMessage.i);
            int n2 = 0;
            chatMessage.l[0] = 0;
            chatMessage.c = 0;
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
                        final int stringWidth = fontMetrics.stringWidth(array[i].substring(n5, n7));
                        if (stringWidth <= n && n7 == length) {
                            break;
                        }
                        if (stringWidth < n) {
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
                for (int n8 = 1; n8 <= n3 && chatMessage.c < 30; ++n8) {
                    chatMessage.l[++chatMessage.c] = array2[n8] + n2;
                }
                n2 += array2[n3];
            }
            chatMessage.b = 6 + Math.max(fontMetrics.getHeight() * (chatMessage.c + 1), 24);
            chatMessage.i = "";
            for (int k = 0; k < array.length; ++k) {
                chatMessage.i += array[k];
            }
        }
        catch (Exception ex) {}
    }
    
    public c6(final h h, final boolean b) {
        super(h, b);
        a2.a = 12;
    }
}
