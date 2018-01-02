// 
// Decompiled by Procyon v0.5.30
// 

package newstick.typer;

import java.awt.FontMetrics;
import java.awt.Point;

class PositionLine
{
    public Point point;
    public int fontHeight;
    public int fontAscent;
    public int fontDescent;
    
    public PositionLine(final Point point, final FontMetrics fontMetrics) {
        this.point = point;
        this.fontHeight = fontMetrics.getHeight();
        this.fontAscent = fontMetrics.getAscent();
        this.fontDescent = fontMetrics.getDescent();
    }
}
