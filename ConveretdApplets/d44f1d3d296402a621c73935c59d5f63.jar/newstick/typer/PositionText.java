// 
// Decompiled by Procyon v0.5.30
// 

package newstick.typer;

import java.awt.Rectangle;
import java.awt.Point;
import java.awt.FontMetrics;

class PositionText
{
    public PositionString title;
    public FontMetrics titleFontMet;
    public PositionString text;
    public FontMetrics textFontMet;
    
    private boolean containsTitle(final Point point) {
        return this.titleRect().contains(point);
    }
    
    public boolean contains(final Point point) {
        return (this.title != null && this.containsTitle(point)) || (this.text != null && this.containsText(point)) || (this.title != null && this.text != null && this.titleRect().union(this.textRect()).contains(point));
    }
    
    private boolean containsText(final Point point) {
        return this.textRect().contains(point);
    }
    
    private Rectangle titleRect() {
        return new Rectangle(new Point(this.title.getStartPoint().x, this.title.getStartPoint().y - this.titleFontMet.getAscent()), this.title.getDimension());
    }
    
    private Rectangle textRect() {
        return new Rectangle(new Point(this.text.getStartPoint().x, this.text.getStartPoint().y - this.textFontMet.getAscent()), this.text.getDimension());
    }
}
