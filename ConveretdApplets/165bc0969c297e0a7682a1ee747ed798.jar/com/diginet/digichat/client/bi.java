// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.util.Vector;
import java.util.StringTokenizer;
import com.diginet.digichat.common.b1;
import java.awt.FontMetrics;
import java.awt.Dimension;
import com.diginet.digichat.network.v;
import com.esial.util.c;
import com.diginet.digichat.common.bg;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Graphics;
import com.diginet.digichat.util.s;

public final class bi extends a0 implements s
{
    private void setBack(final int n, final Graphics graphics) {
        switch (n) {
            case 1: {
                graphics.setColor(super.l.cc.o);
                break;
            }
            case 2: {
                graphics.setColor(super.l.cc.clrIMBack);
                break;
            }
            case 3: {
                graphics.setColor(super.l.cc.clrBaddyBack);
                break;
            }
            case 4: {
                graphics.setColor(super.l.cc.clrInvBack);
                break;
            }
        }
    }
    
    private void setSpec(final int n, final Graphics graphics) {
        switch (n) {
            case 1: {
                graphics.setColor(super.l.cc.n);
                break;
            }
            case 2: {
                graphics.setColor(super.l.cc.clrIMText);
                break;
            }
            case 3: {
                graphics.setColor(super.l.cc.clrBaddyText);
                break;
            }
            case 4: {
                graphics.setColor(super.l.cc.clrInvText);
                break;
            }
        }
    }
    
    private void setText(final bf bf, final Graphics graphics) {
        if (bf.clrText != null) {
            graphics.setColor(bf.clrText);
        }
        else if (bf.nType == 0) {
            graphics.setColor(super.l.cc.k);
        }
        else {
            this.setSpec(bf.nType, graphics);
        }
    }
    
    protected final synchronized void a(final bf bf, final int n, final Graphics graphics) {
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
            Font b = super.l.cc.b();
            final Font a = super.l.cc.a();
            if (bf.nStyle != -1 && b.getStyle() != bf.nStyle) {
                b = new Font(b.getName(), bf.nStyle, b.getSize());
            }
            if (bf.clrBack != null) {
                graphics.setColor(bf.clrBack);
            }
            else if (bf.nType != 0) {
                this.setBack(bf.nType, graphics);
            }
            else if (bf.n && super.l.cb) {
                graphics.setColor(super.l.cc.l);
            }
            else if (bf.t) {
                graphics.setColor(super.l.cc.l);
            }
            else {
                graphics.setColor(super.l.cc.m);
            }
            if (graphics.getColor() != super.l.cc.m && super.n && super.b != null) {
                graphics.fillRect(0, n, size2.width, 2);
                graphics.fillRect(size2.width - 2 - this.a(), n, 2, bf.b - 1);
                graphics.fillRect(0, n, 2, bf.b - 1);
                graphics.fillRect(0, n + bf.b - 1 - 2, size2.width, 2);
            }
            if (!super.n || super.b == null || bf.clrBack != null) {
                graphics.fillRect(0, n, size2.width, bf.b - 1);
            }
            graphics.setColor(Color.white);
            graphics.drawLine(0, n + bf.b - 1, size2.width, n + bf.b - 1);
            if (bf.h != null && n4 + 24 >= n2 && n4 <= n3) {
                graphics.drawImage(bf.h, 6, n4, super.j);
            }
            final FontMetrics fontMetrics = this.getFontMetrics(b);
            final int height = fontMetrics.getHeight();
            int n5 = n4 + fontMetrics.getAscent() - 2;
            int n6 = n5 + bf.nY;
            if (n5 >= n2 && n5 - height <= n3) {
                int n7 = 0;
                if (bf.imgStar != null) {
                    graphics.drawImage(bf.imgStar, a0.a, n4, super.j);
                    n7 = bf.imgStar.getWidth(super.j) + 4;
                }
                if (bf.clrName != null) {
                    graphics.setColor(bf.clrName);
                }
                else if (bf.nType != 0) {
                    this.setSpec(bf.nType, graphics);
                }
                else if (bf.r) {
                    graphics.setColor(super.l.cc.l);
                }
                else if (bf.n && !super.l.cb) {
                    graphics.setColor(super.l.cc.l);
                }
                else if (bf.o && !bf.s) {
                    graphics.setColor(new Color(153));
                }
                else if (bf.s) {
                    graphics.setColor(new Color(39168));
                }
                else {
                    graphics.setColor(super.l.cc.k);
                }
                graphics.setFont(a);
                final int n8;
                graphics.drawString(bf.j, n8 = a0.a + n7, n5);
                if (bf.strTime != null) {
                    final Color color = graphics.getColor();
                    graphics.setColor(super.l.cc.k);
                    final int height2;
                    graphics.drawString(bf.strTime, n8, n5 += (height2 = graphics.getFontMetrics().getHeight()));
                    n6 += height2;
                    graphics.setColor(color);
                }
                int stringWidth = 0;
                if (super.l.ca) {
                    graphics.setFont(super.l.cc.c());
                    graphics.drawString(bf.k, a0.a + n7 + this.getFontMetrics(a).stringWidth(String.valueOf(bf.j).concat(String.valueOf("##"))), n5);
                    stringWidth = this.getFontMetrics(super.l.cc.c()).stringWidth(bf.k);
                }
                if (bf.nType != 0 && super.n) {
                    final bd bd = (bd)super.l.ab.e(bf.f);
                    if (bd != null && bd.i(59) && ((bg)super.l.ac.e(bd.b)).a()) {
                        final String a2 = com.esial.util.c.a("(Private message from Guest Speaker)");
                        graphics.setColor(super.l.cc.k);
                        graphics.drawString(a2, a0.a + n7 + stringWidth + fontMetrics.stringWidth(String.valueOf(bf.j).concat(String.valueOf("######"))), n5);
                    }
                    else {
                        String s = null;
                        String a3 = null;
                        switch (bf.nType) {
                            case 2: {
                                s = com.esial.util.c.a("(Single click here for conversation through 1:1)");
                                break;
                            }
                            case 3: {
                                s = com.esial.util.c.a("(Single click here for conversation with your buddy)");
                                break;
                            }
                            case 4: {
                                if (bf.nLoc >= 0) {
                                    s = com.esial.util.c.a("(Double click here for play to game)");
                                    a3 = com.esial.util.c.a("(Single click here for accept or deny invitation)");
                                }
                                break;
                            }
                            default: {
                                s = com.esial.util.c.a("(Single click on this message to reply)");
                                break;
                            }
                        }
                        if (s != null) {
                            this.setSpec(bf.nType, graphics);
                            final int n9;
                            graphics.drawString(s, n9 = a0.a + n7 + stringWidth + fontMetrics.stringWidth(String.valueOf(bf.j).concat(String.valueOf("######"))), n5);
                            if (a3 != null) {
                                graphics.drawString(a3, n9, n5 + height);
                            }
                        }
                    }
                }
            }
            if (bf.nCheck != 0) {
                final int n10 = super.j.size().width - 14;
                final int n11 = n + 1;
                graphics.setColor(Color.white);
                graphics.fillRect(n10, n11, 13, 13);
                graphics.setColor(new Color(8421504));
                graphics.drawLine(n10, n11 + 11, n10, n11);
                graphics.drawLine(n10, n11, n10 + 11, n11);
                graphics.setColor(new Color(4210752));
                graphics.drawLine(n10 + 1, n11 + 10, n10 + 1, n11 + 1);
                graphics.drawLine(n10 + 1, n11 + 1, n10 + 11, n11 + 1);
                graphics.setColor(new Color(14209224));
                graphics.drawLine(n10 + 11, n11 + 1, n10 + 11, n11 + 11);
                graphics.drawLine(n10 + 11, n11 + 11, n10 + 1, n11 + 11);
                if (bf.nCheck == 2) {
                    graphics.setColor(Color.black);
                    for (int i = n10 + 3, n12 = n11 + 5, n13 = n10 + 10, n14 = n10 + 6; i < n13; n12 += ((++i < n14) ? 1 : -1)) {
                        graphics.drawLine(i, n12, i, n12 + 2);
                    }
                }
            }
            this.setText(bf, graphics);
            graphics.setFont(b);
            int j = 0;
            for (int k = 0; k < bf.c; ++k) {
                int a4 = a0.a;
                int n15 = bf.l[k];
                final int n16 = bf.l[k + 1];
                final int ax = super.l.smiles.ax(bf.i.substring(n15, n16));
                int n17 = 0;
                if (ax > 0 && 16 > fontMetrics.getHeight()) {
                    n17 = 16 - fontMetrics.getHeight();
                }
                final boolean b2 = n6 + 1 >= n2 && n6 - height - n17 <= n3;
                while (j < bf.d) {
                    int n18 = bf.m[2 * j];
                    int n19 = bf.m[2 * j + 1];
                    if (n19 > n16) {
                        n19 = n16;
                    }
                    else {
                        ++j;
                    }
                    if (n18 < n15) {
                        n18 = n15;
                    }
                    if (n15 >= n19 || n16 <= n18) {
                        break;
                    }
                    if (b2) {
                        if (n18 != n15) {
                            if (a4 == a0.a) {
                                while (n15 < n16 && bf.i.charAt(n15) == ' ') {
                                    ++n15;
                                }
                            }
                            String s2;
                            try {
                                s2 = bf.i.substring(n15, n18);
                            }
                            catch (StringIndexOutOfBoundsException ex) {
                                s2 = bf.i.substring(n15, n15);
                            }
                            graphics.drawString(s2, a4, n6);
                            a4 += fontMetrics.stringWidth(s2);
                        }
                        graphics.setColor(Color.blue);
                        final String substring = bf.i.substring(n18, n19);
                        final int stringWidth2 = fontMetrics.stringWidth(substring);
                        graphics.drawLine(a4, n6 + 1, a4 + stringWidth2, n6 + 1);
                        if (bf.nType != 0) {
                            this.setBack(bf.nType, graphics);
                        }
                        else if (bf.n && super.l.cb) {
                            graphics.setColor(super.l.cc.l);
                        }
                        else {
                            graphics.setColor(super.l.cc.m);
                        }
                        graphics.drawString(substring, a4 + 1, n6);
                        graphics.drawString(substring, a4 - 1, n6);
                        graphics.setColor(Color.blue);
                        graphics.drawString(substring, a4, n6);
                        this.setText(bf, graphics);
                        a4 += stringWidth2;
                    }
                    n15 = n19;
                }
                if (b2 && n15 != n16) {
                    String s3 = bf.i.substring(n15, n16);
                    if (a4 == a0.a) {
                        s3 = s3.trim();
                    }
                    if (ax < 1 || bf.nType == 4 || !v.a(super.l.as, 58)) {
                        graphics.drawString(s3, a4, n6);
                    }
                    else {
                        int n20 = 0;
                        final int length = s3.length();
                        int b3 = -1;
                        final int n21 = n6 + (height + n17 - fontMetrics.getHeight()) / 2;
                        final int n22 = n6 - fontMetrics.getAscent() + (height + n17 - 16) / 2;
                        for (int l = 0; l < ax; ++l) {
                            b3 = super.l.smiles.b(s3, ++b3);
                            if (b3 == -1 || b3 >= length) {
                                break;
                            }
                            int index = s3.indexOf(32, b3 + 1);
                            if (index == -1) {
                                index = length;
                            }
                            final b1 bx = super.l.smiles.bx(s3.substring(b3, index));
                            if (bx != null && bx.a != null) {
                                graphics.drawString(s3.substring(n20, b3), a4, n21);
                                final int n23 = a4 + fontMetrics.stringWidth(String.valueOf(s3.substring(n20, b3)).concat(String.valueOf(" ")));
                                graphics.drawImage(bx.a, n23, n22, super.j);
                                a4 = n23 + (bx.a.getWidth(null) + fontMetrics.charWidth(' '));
                                n20 = b3 + bx.x().length();
                            }
                            else {
                                graphics.drawString(s3.substring(n20, index), a4, n21);
                                a4 += fontMetrics.stringWidth(s3.substring(n20, index));
                                n20 = index;
                            }
                        }
                        graphics.drawString(s3.substring(n20, length), a4, n21);
                    }
                }
                n6 += height + n17;
            }
        }
        catch (Exception ex2) {}
    }
    
    public final synchronized void a(final bf bf, final int n, final FontMetrics fontMetrics) {
        try {
            final String s = new String(bf.i);
            int n2 = 0;
            bf.l[0] = 0;
            bf.c = 0;
            bf.nY = fontMetrics.getHeight();
            if (bf.nType == 4 && bf.nLoc >= 0) {
                bf.nY <<= 1;
            }
            if (bf.imgStar != null && bf.nY < bf.imgStar.getHeight(super.j)) {
                bf.nY = bf.imgStar.getHeight(super.j);
            }
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
                    array3[n4] = String.valueOf(array3[n4]).concat(String.valueOf("\n"));
                }
                int n5 = 0;
                final int length = array[i].length();
                while (true) {
                    if (n5 < length && array[i].charAt(n5) == ' ') {
                        ++n5;
                    }
                    else {
                        int n6 = n5;
                        int n7;
                        int j = n7 = length;
                        while (j > n6) {
                            int n8 = 0;
                            if (v.a(super.l.as, 58)) {
                                n8 = (16 + 2 * fontMetrics.charWidth(' ')) * super.l.smiles.ax(array[i].substring(n5, n7)) - super.l.smiles.a(array[i].substring(n5, n7), fontMetrics);
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
                        if (n3 >= 30 || j >= length) {
                            break;
                        }
                        continue;
                    }
                }
                array2[n3] = length;
                for (int n10 = 1; n10 <= n3 && bf.c < 30; ++n10) {
                    bf.l[++bf.c] = array2[n10] + n2;
                }
                n2 += array2[n3];
            }
            bf.i = "";
            for (int k = 0; k < array.length; ++k) {
                bf.i = String.valueOf(bf.i).concat(String.valueOf(array[k]));
            }
            int n11 = 0;
            if (16 > fontMetrics.getHeight()) {
                for (int l = 0; l < bf.c; ++l) {
                    if (super.l.smiles.ax(bf.i.substring(bf.l[l], bf.l[l + 1])) > 0) {
                        n11 += 16 - fontMetrics.getHeight();
                    }
                }
            }
            bf.b = 6 + fontMetrics.getHeight() * (bf.c + ((bf.strTime != null) ? 1 : 0)) + bf.nY + n11;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final String a(final Object o) {
        if (super.n) {
            return com.esial.util.c.a("Chat messages are displayed here.  Single-click on a private message to reply.");
        }
        return com.esial.util.c.a("Private messages are displayed here.");
    }
    
    public bi(final i i, final boolean b) {
        super(i, b);
        a0.a = 36;
    }
}
