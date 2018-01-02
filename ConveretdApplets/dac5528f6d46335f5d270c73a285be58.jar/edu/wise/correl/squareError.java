// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.correl;

import java.awt.Graphics;

public class squareError
{
    private static boolean showAsSquare;
    private static final int xoffset = 0;
    private static final int yoffset = 0;
    
    static {
        squareError.showAsSquare = false;
    }
    
    public static void setSquare(final boolean showAsSquare) {
        squareError.showAsSquare = showAsSquare;
    }
    
    public static boolean getSquare(final boolean b) {
        return squareError.showAsSquare;
    }
    
    public static void paint(final Graphics graphics, final int n, final int n2, final int n3) {
        if (squareError.showAsSquare) {
            graphics.drawRect(n, n2, n3 + 1, n3 + 1);
            graphics.drawLine(n, n2, n, n2 + n3);
        }
        else {
            graphics.drawLine(n, n2, n, n2 + n3);
        }
    }
}
