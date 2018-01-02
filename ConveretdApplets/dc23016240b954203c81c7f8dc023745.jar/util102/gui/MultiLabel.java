// 
// Decompiled by Procyon v0.5.30
// 

package util102.gui;

import java.awt.Insets;
import java.awt.image.ImageObserver;
import java.util.Enumeration;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.util.StringTokenizer;
import java.util.Vector;
import java.awt.Font;
import java.awt.Image;
import java.awt.Canvas;

public class MultiLabel extends Canvas
{
    int topInsets;
    int bottomInsets;
    int leftInsets;
    int rightInsets;
    Image offscreenImg;
    String label;
    Font font;
    private int carWidth;
    private int width;
    private Vector lines;
    private boolean debug;
    
    public MultiLabel() {
        this(5, 5, 5, 5, "Label", 10);
    }
    
    public MultiLabel(final String s) {
        this(5, 5, 5, 5, s, 10);
    }
    
    public MultiLabel(final String s, final int n) {
        this(5, 5, 5, 5, s, n);
    }
    
    public MultiLabel(final int topInsets, final int leftInsets, final int bottomInsets, final int rightInsets, final String label, final int carWidth) {
        this.topInsets = 5;
        this.bottomInsets = 5;
        this.leftInsets = 5;
        this.rightInsets = 5;
        this.label = "Label";
        this.font = new Font("Dialog", 0, 12);
        this.carWidth = 10;
        this.debug = false;
        this.lines = new Vector();
        this.topInsets = topInsets;
        this.leftInsets = leftInsets;
        this.bottomInsets = bottomInsets;
        this.rightInsets = rightInsets;
        this.label = label;
        this.carWidth = carWidth;
        this.calcPixelWidth();
    }
    
    private void calcPixelWidth() {
        if (this.debug) {
            System.out.println("- Entre dans calcPixelWidth()");
        }
        this.width = this.getFontMetrics(this.font).stringWidth("a") * this.carWidth;
    }
    
    private void buildLines() {
        if (this.debug) {
            System.out.println("- Entre dans buildLines()");
        }
        final int max = Math.max(this.size().width - this.leftInsets - this.rightInsets, this.width);
        try {
            final FontMetrics fontMetrics = this.getFontMetrics(this.font);
            String string = "";
            this.lines.removeAllElements();
            final StringTokenizer stringTokenizer = new StringTokenizer(this.label, " ");
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.indexOf(10) != -1) {
                    final String substring = nextToken.substring(0, nextToken.indexOf(10));
                    final String substring2 = nextToken.substring(nextToken.indexOf(10) + 1, nextToken.length());
                    if (fontMetrics.stringWidth(string + " " + substring) < max) {
                        this.lines.addElement((string + " " + substring).trim());
                        string = substring2;
                        if (!this.debug) {
                            continue;
                        }
                        System.out.println("Ligne = " + string);
                    }
                    else {
                        this.lines.addElement(string.trim());
                        if (this.debug) {
                            System.out.println("Ligne ajoutee = " + string);
                        }
                        this.lines.addElement(substring);
                        string = substring2;
                    }
                }
                else if (fontMetrics.stringWidth(string + " " + nextToken) < max) {
                    string = string + " " + nextToken;
                    if (!this.debug) {
                        continue;
                    }
                    System.out.println("Ligne = " + string);
                }
                else {
                    this.lines.addElement(string.trim());
                    if (this.debug) {
                        System.out.println("Ligne ajoutee = " + string);
                    }
                    string = nextToken;
                }
            }
            this.lines.addElement(string.trim());
            if (this.debug) {
                System.out.println("Ligne ajoutee = " + string);
            }
            if (this.debug) {
                System.out.println("Nombre de lignes = " + this.lines.size());
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void paintLabel(final Graphics graphics) {
        if (this.debug) {
            System.out.println("- Entre dans paint(Graphics g)");
        }
        final Dimension size = this.size();
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, size.width, size.height);
        if (this.label != null) {
            this.buildLines();
            final FontMetrics fontMetrics = this.getFontMetrics(this.font);
            int ascent = fontMetrics.getAscent();
            graphics.setColor(this.getForeground());
            graphics.setFont(this.font);
            final Enumeration<String> elements = this.lines.elements();
            while (elements.hasMoreElements()) {
                graphics.drawString(elements.nextElement(), this.leftInsets, ascent + this.topInsets);
                ascent += fontMetrics.getHeight();
            }
        }
    }
    
    public final void paint(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.offscreenImg == null) {
            this.offscreenImg = this.createImage(size.width, size.height);
        }
        final Graphics graphics2 = this.offscreenImg.getGraphics();
        this.paintLabel(graphics2);
        graphics.drawImage(this.offscreenImg, 0, 0, this);
        graphics2.dispose();
    }
    
    public void invalidate() {
        super.invalidate();
        this.offscreenImg = null;
    }
    
    public Dimension minimumSize() {
        if (this.debug) {
            System.out.println("- Entre dans minimumSize()");
        }
        if (this.label != null) {
            this.calcPixelWidth();
            this.buildLines();
            final FontMetrics fontMetrics = this.getFontMetrics(this.font);
            final int width = this.width;
            final int n = fontMetrics.getHeight() * this.lines.size();
            if (width > 0 && n > 0) {
                return new Dimension(width + this.leftInsets + this.rightInsets, n + this.topInsets + this.bottomInsets);
            }
        }
        return super.minimumSize();
    }
    
    public Dimension preferredSize() {
        if (this.debug) {
            System.out.println("- Entre dans preferredSize()");
        }
        return this.minimumSize();
    }
    
    public void setLabel(final String label) {
        this.label = label;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public void setFont(final Font font) {
        this.font = font;
    }
    
    public Font getFont() {
        return this.font;
    }
    
    public void setWidth(final int carWidth) {
        this.carWidth = carWidth;
        this.calcPixelWidth();
    }
    
    public int getWidth() {
        return this.carWidth;
    }
    
    public void setInsets(final int topInsets, final int leftInsets, final int bottomInsets, final int rightInsets) {
        this.topInsets = topInsets;
        this.leftInsets = leftInsets;
        this.bottomInsets = bottomInsets;
        this.rightInsets = rightInsets;
    }
    
    public void setInsets(final Insets insets) {
        this.topInsets = insets.top;
        this.leftInsets = insets.left;
        this.bottomInsets = insets.bottom;
        this.rightInsets = insets.right;
    }
    
    public Insets getInsets() {
        return new Insets(this.topInsets, this.leftInsets, this.bottomInsets, this.rightInsets);
    }
}
