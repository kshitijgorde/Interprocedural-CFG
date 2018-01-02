// 
// Decompiled by Procyon v0.5.30
// 

package KJEgraph;

import java.awt.Color;

public class AutoColor
{
    private static final int COLOR_COUNT = 21;
    private static final int[] R;
    private static final int[] G;
    private static final int[] B;
    private int _iColorIndex;
    
    static {
        R = new int[] { 127, 127, 255, 159, 95, 255, 0, 191, 0, 255, 255, 0, 127, 127, 0, 0, 0, 96, 191, 255, 160 };
        G = new int[] { 127, 31, 255, 223, 0, 127, 127, 191, 0, 0, 255, 255, 0, 0, 127, 0, 200, 255, 255, 255, 200 };
        B = new int[] { 255, 95, 191, 223, 127, 127, 191, 255, 127, 255, 0, 255, 127, 0, 127, 255, 255, 255, 191, 144, 239 };
    }
    
    public static Color getColor(final String s) {
        if (s.length() != 6) {
            return Color.black;
        }
        return new Color(Character.digit(s.charAt(1), 16) + Character.digit(s.charAt(0), 16) * 16, Character.digit(s.charAt(3), 16) + Character.digit(s.charAt(2), 16) * 16, Character.digit(s.charAt(5), 16) + Character.digit(s.charAt(4), 16) * 16);
    }
    
    public static Color indexColor(int n) {
        n %= 21;
        return new Color(AutoColor.R[n], AutoColor.G[n], AutoColor.B[n]);
    }
    
    public Color nextColor() {
        this._iColorIndex %= 21;
        return indexColor(this._iColorIndex++);
    }
    
    public void reset() {
        this._iColorIndex = 0;
    }
}
