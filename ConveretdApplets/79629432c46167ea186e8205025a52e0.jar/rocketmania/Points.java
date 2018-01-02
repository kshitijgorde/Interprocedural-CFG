// 
// Decompiled by Procyon v0.5.30
// 

package rocketmania;

import java.awt.Color;
import sexy.gui.SexyGraphics;
import sexy.gui.SexyFont;
import sexy.gui.Widget;

class Points extends Widget
{
    static final int skTouchHack = 1;
    static final int JUSTIFY_CENTRE = 0;
    static final int JUSTIFY_RIGHT = 1;
    static final int JUSTIFY_LEFT = 2;
    public int mLife;
    String mString;
    SexyFont mFont;
    
    public void Draw(final SexyGraphics sexyGraphics) {
        sexyGraphics.SetFont(this.mFont);
        sexyGraphics.SetColor(Color.orange);
        sexyGraphics.DrawString(this.mString, 0, this.mFont.mAscent);
    }
    
    public Points(final RocketManiaApplet rocketManiaApplet, final SexyFont mFont, final String mString, final int n, final int n2, final int mLife, final int n3) {
        super(rocketManiaApplet.mWidgetManager);
        this.mFont = mFont;
        this.mString = mString;
        final int stringWidth = this.mFont.StringWidth(mString);
        int n4 = n;
        switch (n3) {
            case 0: {
                n4 -= stringWidth / 2;
                break;
            }
            case 1: {
                n4 -= stringWidth;
                break;
            }
        }
        if (n4 < 0) {
            n4 = 0;
        }
        if (n4 + stringWidth > rocketManiaApplet.mWidth) {
            n4 = rocketManiaApplet.mWidth - stringWidth;
        }
        this.Resize(n4, n2 - this.mFont.mAscent, stringWidth, this.mFont.mHeight);
        this.mLife = mLife;
    }
    
    public static int TouchHack() {
        return 1;
    }
}
