// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.ui;

import java.util.StringTokenizer;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Component;

public class WrappedLabel extends Component
{
    public static final String Ident = "$Id: WrappedLabel.java,v 1.2 2009/06/12 20:34:24 blm Exp $";
    private String text;
    private float halign;
    private float valign;
    
    public WrappedLabel() {
        this("");
    }
    
    public WrappedLabel(final String s) {
        this(s, 0.0f, 0.5f);
    }
    
    public WrappedLabel(final String text, final float n, final float n2) {
        this.setAlignment(n, n2);
        this.setText(text);
    }
    
    public float getHAlignment() {
        return this.halign;
    }
    
    public float getVAlignment() {
        return this.valign;
    }
    
    public void paint(final Graphics graphics) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final Dimension size = this.getSize();
        final int height = fontMetrics.getHeight();
        final Vector wrapText = this.wrapText(fontMetrics, size.width, this.text);
        int maxAscent = fontMetrics.getMaxAscent();
        if (this.valign == 0.5f) {
            maxAscent += (size.height - wrapText.size() * height) / 2;
        }
        else if (this.valign == 1.0f) {
            maxAscent += size.height - wrapText.size() * height;
        }
        graphics.setColor(this.getForeground());
        final Enumeration<String> elements = wrapText.elements();
        while (elements.hasMoreElements()) {
            final String s = elements.nextElement();
            if (s.length() > 0) {
                int n;
                if (this.halign == 0.5f) {
                    n = (size.width - fontMetrics.stringWidth(s)) / 2;
                }
                else if (this.halign == 1.0f) {
                    n = size.width - fontMetrics.stringWidth(s);
                }
                else {
                    n = 0;
                }
                graphics.drawString(s, n, maxAscent);
            }
            maxAscent += height;
        }
    }
    
    private Vector wrapText(final FontMetrics fontMetrics, final int n, final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s.trim(), " \n", true);
        final Vector<String> vector = new Vector<String>();
        final StringBuffer sb = new StringBuffer();
        String s2 = "";
        int n2 = 0;
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            if (nextToken.equals(" ")) {
                s2 = nextToken;
            }
            else {
                int stringWidth;
                int n3;
                if (nextToken.equals("\n")) {
                    stringWidth = 0;
                    n3 = 1;
                    nextToken = "";
                }
                else {
                    stringWidth = fontMetrics.stringWidth(s2 + nextToken);
                    n3 = ((n2 + stringWidth >= n) ? 1 : 0);
                }
                if (n3 != 0) {
                    vector.addElement(sb.toString());
                    sb.setLength(0);
                    n2 = 0;
                    s2 = "";
                }
                sb.append(s2);
                sb.append(nextToken);
                n2 += stringWidth;
            }
        }
        if (sb.length() > 0) {
            vector.addElement(sb.toString());
        }
        return vector;
    }
    
    public Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
    
    public Dimension getMinimumSize() {
        final FontMetrics fontMetrics = this.getGraphics().getFontMetrics();
        final Dimension size = this.getSize();
        return new Dimension(size.width, this.wrapText(fontMetrics, size.width, this.text).size() * fontMetrics.getHeight());
    }
    
    public void setAlignment(final float halign, final float valign) {
        this.halign = halign;
        this.valign = valign;
        this.repaint();
    }
    
    public void setText(String text) {
        if (text == null) {
            text = "";
        }
        this.text = text;
        this.repaint();
    }
    
    public String getText() {
        return this.text;
    }
}
