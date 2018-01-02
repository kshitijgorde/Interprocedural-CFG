// 
// Decompiled by Procyon v0.5.30
// 

package sexy.gui;

import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.PixelGrabber;
import java.awt.Color;
import java.awt.Font;
import java.util.StringTokenizer;

public class SexyFont implements Cloneable
{
    public SexyImage mImage;
    public int[] mCharStarts;
    public int[] mCharWidths;
    public int mHeight;
    public int mAscent;
    
    public SexyFont() {
        this.mCharStarts = new int[256];
        this.mCharWidths = new int[256];
    }
    
    public SexyFont(final SexyImage mImage, final String s) {
        this.mCharStarts = new int[256];
        this.mCharWidths = new int[256];
        this.mImage = mImage;
        int n = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        this.mCharWidths[32] = new Integer(stringTokenizer.nextToken());
        this.mCharStarts[32] = -1;
        this.mAscent = new Integer(stringTokenizer.nextToken());
        this.mHeight = mImage.GetHeight();
        while (stringTokenizer.hasMoreElements()) {
            final String nextToken = stringTokenizer.nextToken();
            final String nextToken2 = stringTokenizer.nextToken();
            final char char1 = nextToken.charAt(0);
            final int intValue = new Integer(nextToken2);
            this.mCharStarts[char1] = n;
            this.mCharWidths[char1] = intValue;
            n += intValue;
        }
    }
    
    public SexyFont(final SexyApplet sexyApplet, final Font font) {
        this.mCharStarts = new int[256];
        this.mCharWidths = new int[256];
        final char c = ' ';
        final char c2 = sexyApplet.mAccentedChars ? '\u00ff' : '~';
        final Graphics graphics = sexyApplet.mResLoader.mTestImage.getGraphics();
        graphics.setFont(font);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        this.mHeight = fontMetrics.getHeight();
        this.mAscent = fontMetrics.getAscent();
        int n = 0;
        int n2 = 0;
        for (char c3 = c; c3 <= c2; ++c3) {
            this.mCharStarts[c3] = n2;
            this.mCharWidths[c3] = fontMetrics.charWidth(c3);
            if (this.mCharWidths[c3] > n) {
                n = this.mCharWidths[c3];
            }
            n2 += this.mCharWidths[c3] + 2;
        }
        final Image image = sexyApplet.createImage(n, this.mHeight);
        graphics.dispose();
        final Graphics graphics2 = image.getGraphics();
        final int[] array = new int[n2 * this.mHeight];
        for (char c4 = c; c4 < c2; ++c4) {
            graphics2.setFont(font);
            graphics2.setColor(new Color(0, 0, 0));
            graphics2.fillRect(0, 0, n, this.mHeight);
            graphics2.setColor(new Color(255, 255, 255));
            graphics2.drawString("" + c4, 0, this.mAscent);
            final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, n, this.mHeight, true);
            try {
                pixelGrabber.grabPixels();
            }
            catch (Exception ex) {}
            final int[] array2 = (int[])pixelGrabber.getPixels();
            for (int i = 0; i < this.mHeight; ++i) {
                int n3 = i * n;
                int n4 = i * n2 + this.mCharStarts[c4];
                for (int j = 0; j < this.mCharWidths[c4]; ++j) {
                    array[n4++] = (Math.max(array2[n3] & 0xFF, array2[n3] >> 8 & 0xFF) << 24 | 0xFFFFFF);
                    ++n3;
                }
            }
        }
        graphics2.dispose();
        this.mImage = new SexyImage(array, n2, this.mHeight);
    }
    
    public int GetHeight() {
        return this.mHeight;
    }
    
    public int GetAscent() {
        return this.mAscent;
    }
    
    public synchronized Object clone() throws CloneNotSupportedException {
        final SexyFont sexyFont = new SexyFont();
        sexyFont.mImage = this.mImage;
        for (int i = 0; i < this.mCharStarts.length; ++i) {
            sexyFont.mCharStarts[i] = this.mCharStarts[i];
        }
        for (int j = 0; j < this.mCharWidths.length; ++j) {
            sexyFont.mCharWidths[j] = this.mCharWidths[j];
        }
        sexyFont.mHeight = this.mHeight;
        sexyFont.mAscent = this.mAscent;
        return sexyFont;
    }
    
    public int StringWidth(final String s) {
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            n += this.mCharWidths[s.charAt(i) & '\u00ff'];
        }
        return n;
    }
}
