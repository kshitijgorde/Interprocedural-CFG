// 
// Decompiled by Procyon v0.5.30
// 

package ru.kryshen.graphg;

import java.awt.Color;
import java.awt.FontMetrics;
import java.util.StringTokenizer;
import java.util.Map;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

public class RenderingManager
{
    Image buffer;
    Graphics offscreen;
    private static boolean aa;
    public static final int AA_OFF = 0;
    public static final int AA_TEXT = 1;
    public static final int AA_FULL = 2;
    public static final int AA_DEFAULT = 1;
    public static final int ALIGN_LEFT = 0;
    public static final int ALIGN_CENTER = 1;
    public static final int ALIGN_RIGHT = 2;
    
    public RenderingManager() {
        this.buffer = null;
        this.offscreen = null;
    }
    
    public void updateBuffer(final Component component, final Graphics graphics, final Dimension dimension) {
        if (this.buffer == null || this.buffer.getWidth(null) != dimension.width || this.buffer.getHeight(null) != dimension.height) {
            if (this.offscreen != null) {
                this.offscreen.dispose();
            }
            this.buffer = component.createImage(dimension.width, dimension.height);
            setAA(this.offscreen = this.buffer.getGraphics(), 1);
        }
        this.offscreen.setClip(graphics.getClip());
    }
    
    static void setAA(final Graphics aa, final int n) {
        if (RenderingManager.aa) {
            try {
                new Object() {
                    void setAA(final Graphics graphics) throws Throwable {
                        switch (n) {
                            case 1: {
                                ((Graphics2D)graphics).addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF));
                                ((Graphics2D)graphics).addRenderingHints(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
                                break;
                            }
                            case 2: {
                                ((Graphics2D)graphics).addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
                                break;
                            }
                            default: {
                                ((Graphics2D)graphics).addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF));
                                break;
                            }
                        }
                    }
                }.setAA(aa);
            }
            catch (Throwable t) {
                System.err.println("Graph Game: " + t);
                System.err.println("    Antialiasing is not supported by your Java VM.");
                RenderingManager.aa = false;
            }
        }
    }
    
    public static void drawStringInRect(final String s, final int n, final int n2, final int n3, final int n4, final int n5, final Graphics graphics) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n");
        final int height = fontMetrics.getHeight();
        int n6 = n2 + Math.round((n4 - height * stringTokenizer.countTokens()) / 2.0f) + fontMetrics.getAscent();
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            final int stringWidth = fontMetrics.stringWidth(nextToken);
            int n7 = 0;
            switch (n5) {
                case 0: {
                    n7 = n;
                    break;
                }
                case 2: {
                    n7 = n + n3 - stringWidth;
                    break;
                }
                default: {
                    n7 = n + (n3 - stringWidth) / 2;
                    break;
                }
            }
            graphics.drawString(nextToken, n7, n6);
            n6 += height;
        }
    }
    
    public static void drawStringBorder(final String s, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final Graphics graphics) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n");
        final int height = fontMetrics.getHeight();
        int n7 = Integer.MAX_VALUE;
        int n8 = Integer.MIN_VALUE;
        final int n9 = height * stringTokenizer.countTokens();
        final int n10 = n2 + Math.round((n4 - n9) / 2.0f);
        while (stringTokenizer.hasMoreTokens()) {
            final int stringWidth = fontMetrics.stringWidth(stringTokenizer.nextToken());
            int n11 = 0;
            switch (n5) {
                case 0: {
                    n11 = n;
                    break;
                }
                case 2: {
                    n11 = n + n3 - stringWidth;
                    break;
                }
                default: {
                    n11 = n + (n3 - stringWidth) / 2;
                    break;
                }
            }
            if (n11 < n7) {
                n7 = n11;
            }
            if (n11 + stringWidth > n8) {
                n8 = n11 + stringWidth;
            }
        }
        graphics.fillRect(n7 - n6, n10 - n6, n8 - n7 + n6 * 2, n9 + n6 * 2);
    }
    
    public static void drawStringInRect(final String s, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final Color color, final Graphics graphics) {
        if (n6 > 0) {
            final Color color2 = graphics.getColor();
            graphics.setColor(color);
            drawStringInRect(s, n + n6, n2 + n6, n3, n4, n5, graphics);
            graphics.setColor(color2);
        }
        drawStringInRect(s, n, n2, n3, n4, n5, graphics);
    }
    
    static {
        RenderingManager.aa = true;
    }
}
