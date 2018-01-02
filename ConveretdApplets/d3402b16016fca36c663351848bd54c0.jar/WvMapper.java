import java.awt.Dimension;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class WvMapper
{
    private float degMinX;
    private float degMinY;
    private float degMaxX;
    private float degMaxY;
    private float degRangeX;
    private float degRangeY;
    private Rectangle baseRect;
    
    public int getPosX(final int n) {
        return Math.round(this.baseRect.x + this.baseRect.width * (n - this.degMinX) / this.degRangeX);
    }
    
    public WvMapper(final WvScope wvScope, final Dimension dimension) {
        final int width = dimension.width;
        final int height = dimension.height;
        this.degMinX = wvScope.getPanMin();
        this.degMaxX = wvScope.getPanMax();
        this.degMinY = wvScope.getTiltMin();
        this.degMaxY = wvScope.getTiltMax();
        this.degRangeX = this.degMaxX - this.degMinX;
        this.degRangeY = this.degMaxY - this.degMinY;
        this.baseRect = new Rectangle();
        this.baseRect.x = 0;
        this.baseRect.y = 0;
        this.baseRect.width = width;
        this.baseRect.height = height;
    }
    
    protected void mapRect(final WvScope wvScope, Rectangle intersection) {
        final int panMin = wvScope.getPanMin();
        final int tiltMax = wvScope.getTiltMax();
        final int degRangeX = wvScope.getDegRangeX();
        final int degRangeY = wvScope.getDegRangeY();
        intersection.x = Math.round(this.baseRect.width * (panMin - this.degMinX) / this.degRangeX);
        intersection.y = Math.round(this.baseRect.height * (this.degMaxY - tiltMax) / this.degRangeY);
        intersection.width = Math.round(this.baseRect.width * degRangeX / this.degRangeX);
        intersection.height = Math.round(this.baseRect.height * degRangeY / this.degRangeY);
        intersection.translate(this.baseRect.x, this.baseRect.y);
        intersection = intersection.intersection(this.baseRect);
    }
    
    public void mapRect(final int n, final int n2, final int n3, final Rectangle rectangle) {
        rectangle.width = this.getWidth(n3);
        rectangle.height = rectangle.width * 3 / 4;
        rectangle.x = this.getPosX(n) - rectangle.width / 2;
        rectangle.y = this.getPosY(n2) - rectangle.height / 2;
    }
    
    public int getTiltValue(final int n) {
        return Math.round(this.degMaxY - (n - this.baseRect.y) * this.degRangeY / this.baseRect.height);
    }
    
    public int getPanValue(final int n) {
        return Math.round(this.degRangeX * (n - this.baseRect.x) / this.baseRect.width + this.degMinX);
    }
    
    public int getZoomValue(final int n) {
        return Math.round(n * this.degRangeX / this.baseRect.width);
    }
    
    public int getWidth(final int n) {
        return Math.round(this.baseRect.width * n / this.degRangeX);
    }
    
    public int getPosY(final int n) {
        return Math.round(this.baseRect.y + this.baseRect.height * (this.degMaxY - n) / this.degRangeY);
    }
}
