// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.gui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.StringTokenizer;
import java.util.Vector;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Canvas;

public class MultilineLabel extends Canvas
{
    private String text;
    private String[] lines;
    private Insets insets;
    private int selectionStart;
    private int selectionEnd;
    private Color selectionBackground;
    private int minRows;
    
    public MultilineLabel() {
        this.text = "";
        this.insets = new Insets(2, 3, 3, 3);
        this.selectionBackground = new Color(181, 213, 255);
        this.initComponents();
        this.setBackground(Color.white);
        this.setForeground(Color.black);
    }
    
    public void setSelectionBackground(final Color selectionBackground) {
        this.selectionBackground = selectionBackground;
        this.repaint();
    }
    
    public int viewToModel(final int n, final int n2) {
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        final int n3 = (n2 - this.insets.top) / fontMetrics.getHeight();
        if (n3 < 0 || this.lines == null) {
            return 0;
        }
        if (n3 >= this.lines.length) {
            return this.text.length();
        }
        int n4 = 0;
        for (int i = 0; i < n3; ++i) {
            n4 += this.lines[i].length();
        }
        for (int j = 1; j <= this.lines[n3].length(); ++j) {
            if (fontMetrics.stringWidth(this.lines[n3].substring(0, j)) + this.insets.left > n) {
                return n4 + j - 1;
            }
        }
        return n4 + this.lines[n3].length();
    }
    
    public void setText(final String text) {
        this.text = text;
        this.invalidate();
    }
    
    private void wrapText() {
        final String text = this.text;
        if (text == null) {
            return;
        }
        final int n = this.getSize().width - this.insets.left - this.insets.right;
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        final Vector vector = new Vector<String>();
        final StringTokenizer stringTokenizer = new StringTokenizer(text, " \n", true);
        final StringBuffer sb = new StringBuffer();
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (nextToken.equals("\n")) {
                sb.append(nextToken);
                vector.addElement(sb.toString());
                sb.setLength(0);
            }
            else if (fontMetrics.stringWidth((Object)sb + nextToken) <= n) {
                sb.append(nextToken);
            }
            else if (nextToken.equals(" ")) {
                sb.append(nextToken);
                vector.addElement(sb.toString());
                sb.setLength(0);
            }
            else {
                vector.addElement(sb.toString());
                sb.setLength(0);
                sb.append(nextToken);
            }
        }
        if (sb.length() > 0) {
            vector.addElement(sb.toString());
        }
        final String[] lines = new String[vector.size()];
        vector.copyInto(lines);
        this.lines = lines;
    }
    
    public String getText() {
        return this.text;
    }
    
    public synchronized void select(final int n, final int n2) {
        this.selectionStart = Math.min(this.text.length(), Math.max(0, n));
        this.selectionEnd = Math.min(this.text.length(), Math.max(n, n2));
        this.repaint();
    }
    
    public void invalidate() {
        this.lines = null;
        super.invalidate();
    }
    
    public void setInsets(final Insets insets) {
        this.insets = (Insets)insets.clone();
        this.invalidate();
    }
    
    public Insets getInsets() {
        return (Insets)this.insets.clone();
    }
    
    public Dimension getPreferredSize() {
        final Dimension dimension = new Dimension();
        final Insets insets = this.getInsets();
        if (this.lines == null) {
            this.wrapText();
        }
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        for (int i = 0; i < this.lines.length; ++i) {
            dimension.width = Math.max(dimension.width, fontMetrics.stringWidth(this.lines[i]));
        }
        dimension.height = fontMetrics.getHeight() * Math.max(this.minRows, this.lines.length);
        final Dimension dimension2 = dimension;
        dimension2.width += insets.left + insets.right;
        final Dimension dimension3 = dimension;
        dimension3.height += insets.top + insets.bottom;
        return dimension;
    }
    
    public void setMinRows(final int minRows) {
        this.minRows = minRows;
        this.invalidate();
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        graphics.setColor(Color.black);
        graphics.drawRect(0, -1, size.width - 1, size.height);
        if (this.text == null) {
            return;
        }
        if (this.lines == null) {
            this.invalidate();
            this.wrapText();
            Component parent;
            for (parent = this; parent.getParent() != null && !parent.getParent().isValid(); parent = parent.getParent()) {}
            parent.validate();
            return;
        }
        final String[] lines = this.lines;
        if (lines == null) {
            return;
        }
        final Insets insets = this.getInsets();
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        if (this.selectionEnd > this.selectionStart) {
            graphics.setColor(this.selectionBackground);
            int n = 0;
            int top = insets.top;
            final int height = fontMetrics.getHeight();
            for (int i = 0; i < lines.length; ++i) {
                final int n2 = n + lines[i].length();
                if (n2 >= this.selectionStart && n <= this.selectionEnd) {
                    final int max = Math.max(0, this.selectionStart - n);
                    graphics.fillRect(insets.left + fontMetrics.stringWidth(lines[i].substring(0, max)), top, fontMetrics.stringWidth(lines[i].substring(max, Math.max(0, Math.min(lines[i].length(), this.selectionEnd - n)))), height);
                }
                n = n2;
                top += height;
            }
        }
        graphics.setColor(this.getForeground());
        int n3 = insets.top + fontMetrics.getAscent();
        for (int j = 0; j < lines.length; ++j) {
            String substring = lines[j];
            if (substring.length() > 0 && substring.charAt(substring.length() - 1) == '\n') {
                substring = substring.substring(0, substring.length() - 1);
            }
            graphics.drawString(substring, insets.left, n3);
            n3 += fontMetrics.getHeight();
        }
    }
    
    private void initComponents() {
    }
}
