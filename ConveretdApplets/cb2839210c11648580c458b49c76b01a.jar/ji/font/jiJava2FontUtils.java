// 
// Decompiled by Procyon v0.5.30
// 

package ji.font;

import java.io.InputStream;
import ji.io.a5;
import ji.io.h;
import ji.io.ac;
import java.awt.GraphicsEnvironment;
import ji.v1event.a6;
import java.awt.image.PixelGrabber;
import ji.util.d;
import java.awt.Color;
import java.text.AttributedCharacterIterator;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.awt.Graphics2D;
import java.awt.Image;
import ji.v1event.af;
import ji.image.dw;
import java.awt.Font;
import java.awt.Component;
import java.awt.image.BufferedImage;

public class jiJava2FontUtils
{
    static String[] availFonts;
    static Object bufferMemSync;
    static BufferedImage charSet;
    static int charSetW;
    static int charSetH;
    static int selStartOld;
    static final int MEM_LIM = 5242880;
    
    public final Image drawString(final String s, final Component component, final String s2, final int charSetW, final int charSetH, final int n, final Font font, final boolean b, final boolean b2, final int[] array, final int n2, final int selStartOld, final int n3, final dw dw, final int n4, final af af) {
        boolean b3 = false;
        synchronized (jiJava2FontUtils.bufferMemSync) {
            try {
                int n5 = 0;
                int n6 = charSetH;
                int n7 = 0;
                int n8 = 0;
                if (jiJava2FontUtils.charSet == null) {
                    n8 = 1;
                }
                else {
                    if (jiJava2FontUtils.charSetW < charSetW) {
                        n8 = 1;
                    }
                    if (jiJava2FontUtils.charSetH < charSetH) {
                        n8 = 1;
                    }
                    if (jiJava2FontUtils.selStartOld != selStartOld) {
                        n8 = 1;
                    }
                }
                if (charSetW * charSetH > 5242880 && dw != null) {
                    b3 = true;
                    n5 = (n6 = 5242880 / charSetW);
                    n8 = 1;
                }
                for (int i = 1; i != 0; i = 1, n8 = 0) {
                    i = 0;
                    if (n8 != 0) {
                        if (jiJava2FontUtils.charSet != null) {
                            jiJava2FontUtils.charSet.flush();
                            jiJava2FontUtils.charSet = null;
                        }
                        if (selStartOld >= 0 && n3 >= 0) {
                            jiJava2FontUtils.charSet = new BufferedImage(charSetW, n6, 1);
                        }
                        else {
                            jiJava2FontUtils.charSet = new BufferedImage(charSetW, n6, 12);
                        }
                        jiJava2FontUtils.charSetW = charSetW;
                        jiJava2FontUtils.charSetH = charSetH;
                        jiJava2FontUtils.selStartOld = selStartOld;
                    }
                    final Graphics2D graphics2D = (Graphics2D)jiJava2FontUtils.charSet.getGraphics();
                    final AttributedString attributedString = new AttributedString(s2);
                    attributedString.addAttribute(TextAttribute.FONT, font);
                    try {
                        if (selStartOld >= 0 && n3 >= 0) {
                            attributedString.addAttribute(TextAttribute.BACKGROUND, new Color(n2), selStartOld, n3 + 1);
                        }
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if (b) {
                        attributedString.addAttribute(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
                    }
                    if (b2) {
                        attributedString.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    }
                    graphics2D.setColor(Color.white);
                    graphics2D.fillRect(0, 0, charSetW, n6);
                    graphics2D.setColor(Color.black);
                    graphics2D.setFont(font);
                    graphics2D.drawString(attributedString.getIterator(), 0, 78 * n / 100 - n7);
                    d.a(component, jiJava2FontUtils.charSet);
                    if (dw != null) {
                        int max = Math.max(5242880 / charSetW, 1);
                        final int[] array2 = new int[max * charSetW];
                        for (int j = 0; j < n6; j += max) {
                            final int n9 = charSetH - j;
                            if (n9 < max) {
                                max = n9;
                            }
                            new PixelGrabber(jiJava2FontUtils.charSet, 0, j, charSetW, max, array2, 0, charSetW).grabPixels();
                            dw.a(j + n7, j.a(charSetW, max, array2, n4), charSetW, max);
                            if (max < charSetH) {
                                showPercent(af, 100 * n6 / charSetH, component);
                            }
                        }
                        if (max < charSetH) {
                            showPercent(af, 0, component);
                        }
                    }
                    else {
                        new PixelGrabber(jiJava2FontUtils.charSet, 0, 0, charSetW, charSetH, array, 0, charSetW).grabPixels();
                    }
                    if (n6 < charSetH) {
                        n6 += n5;
                        n7 += n5;
                        if (n6 > charSetH) {
                            n6 = charSetH;
                        }
                    }
                }
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
                if (jiJava2FontUtils.charSet != null) {
                    jiJava2FontUtils.charSet.flush();
                    jiJava2FontUtils.charSet = null;
                }
            }
            if (b3 && jiJava2FontUtils.charSet != null) {
                jiJava2FontUtils.charSet.flush();
                jiJava2FontUtils.charSet = null;
            }
        }
        // monitorexit(jiJava2FontUtils.bufferMemSync)
        return null;
    }
    
    public static final void showPercent(final af af, final int n, final Object o) {
        if (af != null) {
            af.a(new a6(o, 4, "".concat(String.valueOf(String.valueOf(n)))));
        }
    }
    
    public void releaseTempMemory() {
        if (jiJava2FontUtils.charSet != null) {
            jiJava2FontUtils.charSet.flush();
            jiJava2FontUtils.charSet = null;
            jiJava2FontUtils.charSetW = 0;
            jiJava2FontUtils.charSetH = 0;
        }
    }
    
    public String[] getFontFamilyNames() {
        if (jiJava2FontUtils.availFonts == null) {
            jiJava2FontUtils.availFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        }
        return jiJava2FontUtils.availFonts;
    }
    
    public int getActualFontWidth(final Component component, final Font font, final String s) {
        final Graphics2D graphics2D = (Graphics2D)new BufferedImage(1, 1, 1).getGraphics();
        graphics2D.setFont(font);
        return graphics2D.getFontMetrics().stringWidth(s);
    }
    
    public static Image getBufferedImage(final int n, final int n2, final int n3) {
        return new BufferedImage(n, n2, n3);
    }
    
    public Font createFontFromTTF(final String s, final int n, final int n2, final Object o, final String s2) {
        Font font = null;
        if (ac.d(s, s2)) {
            if (d.cs()) {
                h.d(s2, String.valueOf(String.valueOf(new StringBuffer("Creating font custom TTF: ").append(s).append("..."))));
            }
            ac ac = null;
            InputStream inputStream = null;
            try {
                ac = new ac(s, true, false, 0, false, o, s2);
                inputStream = new a5(ac, o);
                font = Font.createFont(0, inputStream);
                if (font != null && n2 > 1) {
                    font = font.deriveFont(n, n2);
                }
                if (d.cs()) {
                    h.d(s2, String.valueOf(String.valueOf(new StringBuffer("Created font custom TTF: ").append(s).append("/").append(font))));
                }
                return font;
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return font;
            }
            finally {
                try {
                    if (inputStream != null) {
                        ((a5)inputStream).close();
                    }
                    if (ac != null) {
                        ac.a(o);
                    }
                }
                catch (Exception ex2) {}
            }
        }
        h.d(s2, "ERROR: Unable to find font file: ".concat(String.valueOf(String.valueOf(s))));
        return font;
    }
    
    static {
        jiJava2FontUtils.availFonts = null;
        jiJava2FontUtils.bufferMemSync = new Object();
        jiJava2FontUtils.charSet = null;
        jiJava2FontUtils.charSetW = 0;
        jiJava2FontUtils.charSetH = 0;
        jiJava2FontUtils.selStartOld = -1;
    }
}
