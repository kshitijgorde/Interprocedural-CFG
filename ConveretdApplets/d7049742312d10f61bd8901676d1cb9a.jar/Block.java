import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class Block
{
    public int style;
    public Color color;
    public double x;
    public double y;
    public int height;
    public int width;
    
    public Block() {
        this.style = 0;
        this.color = Color.red;
        this.x = 0.0;
        this.y = 0.0;
        this.width = 1;
        this.height = 1;
    }
    
    public Block(final int style, final Color color, final double x, final double y, final int[] array) {
        this.setStyle(style);
        this.setColor(color);
        this.setX(x);
        this.setY(y);
        this.setWidth(array[0]);
        this.setHeight(array[1]);
    }
    
    public final void setStyle(final int style) {
        if (style <= 6) {
            this.style = style;
            return;
        }
        this.style = 0;
    }
    
    public final void setColor(final Color color) {
        this.color = color;
    }
    
    public final double roundToHalf(double n, double n2) {
        n = (int)(n2 * 100.0) / 100;
        n2 = (int)(n2 * 100.0) % 100;
        if (n2 >= 25.0 && n2 < 75.0) {
            n += 0.5;
        }
        else if (n2 >= 75.0) {
            ++n;
        }
        return n;
    }
    
    public final void setX(final double n) {
        this.x = this.roundToHalf(this.x, n);
        if (this.x > 500.0 || this.x < 0.0) {
            this.x = 0.0;
        }
    }
    
    public final void setY(final double n) {
        this.y = this.roundToHalf(this.y, n);
        if (this.y > 500.0 || this.y < 0.0) {
            this.y = 0.0;
        }
    }
    
    public final void setWidth(final int width) {
        if (width <= 4 && width >= 1) {
            this.width = width;
            return;
        }
        this.width = 1;
    }
    
    public final void setHeight(final int height) {
        if (height <= 4 && height >= 1) {
            this.height = height;
            return;
        }
        this.height = 1;
    }
    
    public final int getStyle() {
        return this.style;
    }
    
    public final Color getColor() {
        return this.color;
    }
    
    public final double getX() {
        return this.x;
    }
    
    public final double getY() {
        return this.y;
    }
    
    public final int getWidth() {
        return this.width;
    }
    
    public final int getHeight() {
        return this.height;
    }
}
