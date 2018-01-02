// 
// Decompiled by Procyon v0.5.30
// 

package rocketmania;

import java.awt.Rectangle;
import sexy.gui.SexyFont;
import sexy.gui.SexyGraphics;

public class SpacedFont
{
    static final int skTouchHack = 1;
    static final int JUSTIFY_LEFT = 0;
    static final int JUSTIFY_CENTRE = 1;
    static final int JUSTIFY_RIGHT = 2;
    private SexyGraphics mGraphics;
    private SexyFont mFont;
    private int mSpacing;
    private int mJustification;
    
    SpacedFont(final SexyGraphics mGraphics, final SexyFont mFont, final int mSpacing) {
        this.mGraphics = mGraphics;
        this.mFont = mFont;
        this.mSpacing = mSpacing;
        this.mJustification = 1;
    }
    
    void DrawString(final String s, final int n, final int n2) {
        final SexyGraphics sexyGraphics = new SexyGraphics(this.mGraphics);
        sexyGraphics.SetColorizeImages(true);
        int n3 = n;
        switch (this.mJustification) {
            case 1: {
                n3 -= this.StringWidth(s) / 2;
                break;
            }
            case 2: {
                n3 -= this.StringWidth(s);
                break;
            }
        }
        final int n4 = n2 - this.mFont.GetAscent();
        for (int i = 0; i < s.length(); ++i) {
            final char c = (char)(s.charAt(i) & '\u00ff');
            final int n5 = this.mFont.mCharStarts[c];
            final int n6 = this.mFont.mCharWidths[c];
            if (n6 > 0) {
                if (n5 >= 0) {
                    sexyGraphics.DrawImage(this.mFont.mImage, n3, n4, new Rectangle(n5, 0, n6, this.mFont.mHeight));
                }
                n3 += n6 + this.mSpacing;
            }
        }
    }
    
    public static int TouchHack() {
        return 1;
    }
    
    int StringWidth(final String s) {
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            final int n2 = this.mFont.mCharWidths[s.charAt(i) & '\u00ff'];
            if (n2 > 0) {
                n += n2 + this.mSpacing;
            }
        }
        return n;
    }
    
    void SetJustification(final int mJustification) {
        this.mJustification = mJustification;
    }
}
