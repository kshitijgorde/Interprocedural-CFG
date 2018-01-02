import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Bil
{
    private final boolean ns;
    private int x;
    private int y;
    private Image bil;
    private int x2;
    private int y2;
    private int Height;
    private int Width;
    
    public Bil(final int x, final int y, final boolean ns, final Image bil, final int height, final int width) {
        this.ns = ns;
        this.x = x;
        this.y = y;
        this.bil = bil;
        this.Height = height;
        this.Width = width;
        this.x2 = x + width;
        this.y2 = y + height;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getMaxX() {
        return this.x2;
    }
    
    public int getMaxY() {
        return this.y2;
    }
    
    public Image getImage() {
        return this.bil;
    }
    
    public boolean NS() {
        return this.ns;
    }
    
    public int getHeight() {
        return this.Height;
    }
    
    public int getWidth() {
        return this.Width;
    }
    
    public void setX(final int x) {
        this.x = x;
        this.x2 = x + this.Width;
    }
    
    public void setY(final int y) {
        this.y = y;
        this.y2 = y + this.Height;
    }
    
    public void setNum(byte b) {
        if (b < 7) {
            this.y = 15;
        }
        else if (b < 13) {
            this.y = 55;
        }
        else if (b < 19) {
            this.y = 95;
        }
        else if (b < 25) {
            this.y = 135;
        }
        else if (b < 31) {
            this.y = 174;
        }
        else {
            this.y = 215;
        }
        this.y2 = this.y + this.Height;
        for (int i = 8; i < 209; i += 40, --b) {
            switch (b) {
                case 1:
                case 7:
                case 13:
                case 19:
                case 25:
                case 31: {
                    this.x = i;
                    this.x2 = this.x + this.Width;
                    i = 210;
                    break;
                }
            }
        }
    }
}
