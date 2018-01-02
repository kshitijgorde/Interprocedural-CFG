import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class RectSprite extends Sprite2D
{
    protected int width;
    protected int height;
    
    public RectSprite(final int width, final int height, final Color color) {
        super.locx = 0;
        super.locy = 0;
        this.width = width;
        this.height = height;
        super.color = color;
        ((Sprite)this).restore();
    }
    
    public RectSprite(final int locx, final int locy, final int width, final int height, final Color color) {
        super.locx = locx;
        super.locy = locy;
        this.width = width;
        this.height = height;
        super.color = color;
        super.fill = false;
        ((Sprite)this).restore();
    }
    
    public void update() {
    }
    
    public void paint(final Graphics graphics) {
        if (((Sprite)this).visible) {
            graphics.setColor(super.color);
            if (super.fill) {
                graphics.fillRect(super.locx, super.locy, this.width, this.height);
            }
            else {
                graphics.drawRect(super.locx, super.locy, this.width, this.height);
            }
        }
    }
}
