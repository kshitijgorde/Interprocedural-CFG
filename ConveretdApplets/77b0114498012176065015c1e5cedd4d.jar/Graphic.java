import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class Graphic extends Sprite
{
    public static final int IMAGE = 12;
    public Image image;
    
    public void set(final int n, final Object o) {
        switch (n) {
            case 12: {
                this.image = (Image)o;
            }
            default: {
                super.set(n, o);
            }
        }
    }
    
    public void draw(final Graphics graphics) {
        if (super.visible) {
            graphics.drawImage(this.image, super.x + ((super.xAnchor == Sprite.WEST || super.constrain) ? 0 : ((super.xAnchor == Sprite.EAST) ? (super.width - this.image.getWidth(null)) : ((super.width - this.image.getWidth(null)) / 2))), super.y + ((super.yAnchor == Sprite.NORTH || super.constrain) ? 0 : ((super.yAnchor == Sprite.SOUTH) ? (super.height - this.image.getHeight(null)) : ((super.height - this.image.getHeight(null)) / 2))), super.x + ((super.xAnchor == Sprite.WEST || super.constrain) ? 0 : ((super.xAnchor == Sprite.EAST) ? (super.width - this.image.getWidth(null)) : ((super.width - this.image.getWidth(null)) / 2))) + (super.constrain ? super.width : this.image.getWidth(null)), super.y + ((super.yAnchor == Sprite.NORTH || super.constrain) ? 0 : ((super.yAnchor == Sprite.SOUTH) ? (super.height - this.image.getHeight(null)) : ((super.height - this.image.getHeight(null)) / 2))) + (super.constrain ? super.height : this.image.getHeight(null)), (super.xFace == Sprite.RIGHT) ? 0 : this.image.getWidth(null), (super.yFace == Sprite.UP) ? 0 : this.image.getHeight(null), (super.xFace == Sprite.RIGHT) ? this.image.getWidth(null) : 0, (super.yFace == Sprite.UP) ? this.image.getHeight(null) : 0, null);
        }
    }
}
