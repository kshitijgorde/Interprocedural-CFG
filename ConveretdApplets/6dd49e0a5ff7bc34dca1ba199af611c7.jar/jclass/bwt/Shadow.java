// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import java.awt.Color;
import java.awt.Graphics;

public class Shadow
{
    static void drawTopLines(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final Color color) {
        graphics.setColor(color);
        for (int i = 0; i < n; ++i) {
            graphics.drawLine(n2 + i, n3 + i, n2 + n4 - (i + 1), n3 + i);
            graphics.drawLine(n2 + i, n3 + i + 1, n2 + i, n3 + n5 - (i + 1));
        }
    }
    
    static void drawBottomLines(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final Color color) {
        graphics.setColor(color);
        for (int i = 1; i <= n; ++i) {
            graphics.drawLine(n2 + i - 1, n3 + n5 - i, n2 + n4 - i, n3 + n5 - i);
            graphics.drawLine(n2 + n4 - i, n3 + i - 1, n2 + n4 - i, n3 + n5 - i);
        }
    }
    
    static void drawNormal(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final Color color, final Color color2) {
        drawTopLines(graphics, n, n2, n3, n4, n5, color);
        drawBottomLines(graphics, n, n2, n3, n4, n5, color2);
    }
    
    static void drawEtched(final Graphics graphics, final int n, final int n2, final int n3, int n4, int n5, final Color color, final Color color2) {
        n4 -= n;
        n5 -= n;
        graphics.setColor(color);
        for (int i = 0; i < n / 2; ++i) {
            graphics.drawRect(n2 + i, n3 + i, n4, n5);
        }
        graphics.setColor(color2);
        for (int j = 0; j < n / 2; ++j) {
            graphics.drawRect(n2 + n / 2 + j, n3 + n / 2 + j, n4, n5);
        }
    }
    
    public static void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final Color color, final Color color2) {
        BWTUtil.brighter(color);
        BWTUtil.darker(color);
        draw(graphics, n, n2, n3, n4, n5, n6, BWTUtil.brighter(color), BWTUtil.darker(color), color2);
    }
    
    public static void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final Color color, final Color color2, final Color color3) {
        final Color color4 = graphics.getColor();
        switch (n) {
            case 5: {
                drawNormal(graphics, n2, n3, n4, n5, n6, color3, color3);
                break;
            }
            case 3: {
                drawNormal(graphics, n2, n3, n4, n5, n6, color2, color);
                break;
            }
            case 4: {
                drawNormal(graphics, n2, n3, n4, n5, n6, color, color2);
                break;
            }
            case 1: {
                drawEtched(graphics, n2, n3, n4, n5, n6, color2, color);
                break;
            }
            case 2: {
                drawEtched(graphics, n2, n3, n4, n5, n6, color, color2);
                break;
            }
            case 7: {
                drawNormal(graphics, n2, n3, n4, n5, n6, color, color2);
                drawNormal(graphics, n2 - 1, n3, n4, n5, n6, color3, color3);
                break;
            }
            case 6: {
                drawNormal(graphics, n2, n3, n4, n5, n6, color2, color);
                drawNormal(graphics, n2 - 1, n3, n4, n5, n6, color3, color3);
                break;
            }
        }
        graphics.setColor(color4);
    }
}
