import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class Mystery
{
    Image MysteryImage;
    SpacedInvaders si;
    MysterySprite ms;
    int x;
    int y;
    
    public Mystery(final Image mysteryImage, final Image image, final SpacedInvaders si) {
        this.MysteryImage = mysteryImage;
        this.si = si;
        this.ms = new MysterySprite(this.MysteryImage, image, this.si);
        this.x = -60;
        this.y = 20;
    }
    
    public void moveMystery() {
        if (((Sprite)this.ms).active) {
            this.ms.setPosition(this.x, this.y);
            if (this.x < 500) {
                ++this.x;
            }
            else {
                this.x = -60;
                ((Sprite)this.ms).suspend();
            }
        }
        else {
            this.x = -60;
        }
    }
    
    public MysterySprite getTarget() {
        return this.ms;
    }
}
