// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Iterator;
import java.awt.FontMetrics;
import java.util.List;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Font;

public class h
{
    private static final Font a;
    
    public static void a(final Graphics2D graphics2D, final int n, final int n2, final String s) {
        final Font font = graphics2D.getFont();
        final Object renderingHint = graphics2D.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setFont(h.a);
        graphics2D.setColor(Color.WHITE);
        final int a = a(n);
        final List a2 = a(graphics2D, s, a);
        final FontMetrics fontMetrics = graphics2D.getFontMetrics();
        final int height = fontMetrics.getHeight();
        final int n3 = (a2.size() - 1) * height;
        final Rectangle rectangle = new Rectangle(n / 8, n2 - 46 - n3, a, 46 + n3);
        final int x = rectangle.x;
        int n4 = rectangle.y + ((a2.size() == 1) ? 16 : 27);
        final Iterator<String> iterator = a2.iterator();
        for (int i = 0; i < a2.size(); ++i, n4 += height + 4) {
            final String s2 = iterator.next();
            graphics2D.drawString(s2, n / 2 - fontMetrics.stringWidth(s2) / 2, n4);
        }
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, renderingHint);
        graphics2D.setFont(font);
    }
    
    public static TreeMap a(final Graphics2D graphics2D, final int n, final TreeMap treeMap) {
        int a = a(n);
        a -= 30;
        final TreeMap<Integer, m> treeMap2 = new TreeMap<Integer, m>();
        final Font font = graphics2D.getFont();
        graphics2D.setFont(h.a);
        for (final m m : treeMap.values()) {
            if (m.c.length() == 0) {
                continue;
            }
            final List a2 = a(graphics2D, m.c, a);
            if (a2.size() <= 2) {
                treeMap2.put(m.a, m);
            }
            else {
                final int n2 = (m.b - m.a) / a(m.c);
                int a3 = m.a;
                while (!a2.isEmpty()) {
                    final int n3 = 0;
                    int n4;
                    String s;
                    if (a2.size() > 1) {
                        n4 = n3 + a(a2.get(0)) + a(a2.get(1));
                        s = a2.get(0) + " " + a2.get(1);
                        a2.remove(0);
                        a2.remove(0);
                        if (!a2.isEmpty() && !b(s)) {
                            s += " ...";
                        }
                    }
                    else {
                        n4 = n3 + a(a2.get(0));
                        s = a2.get(0);
                        a2.remove(0);
                    }
                    final int n5 = n4 * n2;
                    final m i = new m(a3, a3 + n5, s);
                    a3 += n5;
                    treeMap2.put(i.a, i);
                }
            }
        }
        graphics2D.setFont(font);
        return treeMap2;
    }
    
    private static int a(final int n) {
        return Math.min(350, n * 3 / 4 + 1);
    }
    
    private static int a(final String s) {
        return s.length();
    }
    
    private static List a(final Graphics graphics, final String s, final int n) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
        final ArrayList<String> list = new ArrayList<String>();
        String s2 = "";
        while (stringTokenizer.hasMoreTokens()) {
            final String trim = stringTokenizer.nextToken().trim();
            if (trim.length() == 0) {
                continue;
            }
            final String s3 = (s2.length() > 0) ? (s2 + " " + trim) : trim;
            if (fontMetrics.stringWidth(s3) > n || b(s2)) {
                list.add(s2);
                s2 = trim;
            }
            else {
                s2 = s3;
            }
        }
        if (s2.length() < 15 && !list.isEmpty()) {
            final int n2 = list.size() - 1;
            final String string = list.get(n2) + " " + s2;
            list.remove(n2);
            list.add(n2, string);
        }
        else {
            list.add(s2);
        }
        return list;
    }
    
    private static boolean b(final String s) {
        return s.endsWith(".") || s.endsWith("!") || s.endsWith("?");
    }
    
    static {
        a = new Font("Arial", 0, 24);
    }
}
