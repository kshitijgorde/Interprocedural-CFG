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
    
    public int getPosX(final int i) {
        return Math.round(this.baseRect.x + this.baseRect.width * (i - this.degMinX) / this.degRangeX);
    }
    
    public WvMapper(final WvScope wvscope, final Dimension dimension) {
        final int i = dimension.width;
        final int j = dimension.height;
        this.degMinX = wvscope.getPanMin();
        this.degMaxX = wvscope.getPanMax();
        this.degMinY = wvscope.getTiltMin();
        this.degMaxY = wvscope.getTiltMax();
        this.degRangeX = this.degMaxX - this.degMinX;
        this.degRangeY = this.degMaxY - this.degMinY;
        this.baseRect = new Rectangle();
        this.baseRect.x = 0;
        this.baseRect.y = 0;
        this.baseRect.width = i;
        this.baseRect.height = j;
    }
    
    protected void mapRect(final WvScope wvscope, Rectangle rectangle) {
        final int i = wvscope.getPanMin();
        final int j = wvscope.getTiltMax();
        final int k = wvscope.getDegRangeX();
        final int l = wvscope.getDegRangeY();
        rectangle.x = Math.round(this.baseRect.width * (i - this.degMinX) / this.degRangeX);
        rectangle.y = Math.round(this.baseRect.height * (this.degMaxY - j) / this.degRangeY);
        rectangle.width = Math.round(this.baseRect.width * k / this.degRangeX);
        rectangle.height = Math.round(this.baseRect.height * l / this.degRangeY);
        rectangle.translate(this.baseRect.x, this.baseRect.y);
        rectangle = rectangle.intersection(this.baseRect);
    }
    
    public void mapRect(final int i, final int j, final int k, final Rectangle rectangle) {
        rectangle.width = this.getWidth(k);
        rectangle.height = rectangle.width * 3 / 4;
        rectangle.x = this.getPosX(i) - rectangle.width / 2;
        rectangle.y = this.getPosY(j) - rectangle.height / 2;
    }
    
    public int getTiltValue(final int i) {
        return Math.round(this.degMaxY - (i - this.baseRect.y) * this.degRangeY / this.baseRect.height);
    }
    
    public int getPanValue(final int i) {
        return Math.round(this.degRangeX * (i - this.baseRect.x) / this.baseRect.width + this.degMinX);
    }
    
    public int getZoomValue(final int i) {
        return Math.round(i * this.degRangeX / this.baseRect.width);
    }
    
    public int getWidth(final int i) {
        return Math.round(this.baseRect.width * i / this.degRangeX);
    }
    
    public int getPosY(final int i) {
        return Math.round(this.baseRect.y + this.baseRect.height * (this.degMaxY - i) / this.degRangeY);
    }
}
