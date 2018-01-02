// 
// Decompiled by Procyon v0.5.30
// 

package jlog.$Y_B;

import java.awt.Rectangle;

public class $Z_B implements Cloneable
{
    public float x;
    public float y;
    public float width;
    public float height;
    
    public Rectangle $S1B(final float n, final float n2) {
        return new Rectangle((int)(this.x * n + 0.5f), (int)(this.y * n2 + 0.5f), (int)(this.width * n + 0.5f), (int)(this.height * n2 + 0.5f));
    }
    
    public void $Z6(final float n, final float n2) {
        this.x *= n;
        this.y *= n2;
        this.width *= n;
        this.height *= n2;
    }
    
    public $Z_B(final float x, final float y, final float width, final float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public $Z_B(final Rectangle rectangle) {
        this.reshape(rectangle);
    }
    
    public $Z_B create() {
        try {
            return ($Z_B)this.clone();
        }
        catch (Exception ex) {
            throw new RuntimeException(ex.toString());
        }
    }
    
    public boolean equals(final Object o) {
        if (o instanceof $Z_B) {
            final $Z_B $z_B = ($Z_B)o;
            if ($z_B.x == this.x && $z_B.y == this.y && $z_B.width == this.width && $z_B.height == this.height) {
                return true;
            }
        }
        return false;
    }
    
    public Rectangle getRectangle() {
        return new Rectangle((int)this.x, (int)this.y, (int)this.width, (int)this.height);
    }
    
    public void reshape(final Rectangle rectangle) {
        this.x = rectangle.x;
        this.y = rectangle.y;
        this.width = rectangle.width;
        this.height = rectangle.height;
    }
}
