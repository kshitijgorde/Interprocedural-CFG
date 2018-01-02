import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class UFOMissile extends RectSprite implements Statics
{
    protected int vy;
    protected int start_y;
    protected int stop_y;
    protected int ROWS;
    protected int COLS;
    Intersect target;
    Intersect[][] theShield;
    
    public UFOMissile(final int n, final int n2, final Color color, final int vy, final int start_y, final int stop_y) {
        super(n, n2, color);
        ((Sprite2D)this).setFill(true);
        this.vy = vy;
        this.start_y = start_y;
        this.stop_y = stop_y;
        ((Sprite)this).suspend();
    }
    
    public void init(final int n, final int locy) {
        ((Sprite2D)this).locx = n + 10;
        ((Sprite2D)this).locy = locy;
        ((Sprite)this).restore();
    }
    
    public void getTarget(final GunManager gunManager, final Intersect[][] theShield) {
        this.target = (Intersect)gunManager.getGun();
        this.theShield = theShield;
    }
    
    public void update() {
        if (((Sprite)this).active) {
            ((Sprite2D)this).locy += this.vy;
            if (((Sprite2D)this).locy > this.stop_y) {
                ((Sprite)this).suspend();
            }
            else {
                for (int i = 0; i < 80; ++i) {
                    for (int j = 0; j < 10; ++j) {
                        if (this.theShield[j][i].intersect(((Sprite2D)this).locx, ((Sprite2D)this).locy, ((Sprite2D)this).locx + super.width, ((Sprite2D)this).locy + super.height)) {
                            this.theShield[j][i].hit();
                            ((Sprite)this).suspend();
                            break;
                        }
                    }
                }
            }
            if (((Sprite)this).active && this.target.intersect(((Sprite2D)this).locx, ((Sprite2D)this).locy, ((Sprite2D)this).locx + super.width, ((Sprite2D)this).locy + super.height)) {
                this.target.hit();
                ((Sprite)this).suspend();
            }
        }
    }
}
