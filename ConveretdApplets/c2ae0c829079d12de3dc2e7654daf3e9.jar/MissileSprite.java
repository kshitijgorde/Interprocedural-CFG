import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class MissileSprite extends RectSprite implements Statics
{
    protected int vy;
    protected int start_y;
    protected int stop_y;
    protected int ROWS;
    protected int COLS;
    Intersect[][] target;
    Intersect[][] theShield;
    Intersect theMystery;
    
    public MissileSprite(final int n, final int n2, final Color color, final int vy, final int start_y, final int stop_y, final Intersect[][] target, final int rows, final int cols, final Intersect[][] theShield, final Intersect theMystery) {
        super(n, n2, color);
        ((Sprite2D)this).setFill(true);
        this.vy = vy;
        this.start_y = start_y;
        this.stop_y = stop_y;
        this.target = target;
        this.theMystery = theMystery;
        this.ROWS = rows;
        this.COLS = cols;
        this.theShield = theShield;
        ((Sprite)this).suspend();
    }
    
    public void init(final int locx) {
        ((Sprite2D)this).locx = locx;
        ((Sprite2D)this).locy = this.start_y;
        ((Sprite)this).restore();
    }
    
    public void update() {
        if (((Sprite)this).active) {
            ((Sprite2D)this).locy += this.vy;
            if (((Sprite2D)this).locy < this.stop_y) {
                ((Sprite)this).suspend();
            }
            else {
                for (int i = 0; i < this.COLS; ++i) {
                    for (int j = 0; j < this.ROWS; ++j) {
                        if (this.target[j][i].intersect(((Sprite2D)this).locx, ((Sprite2D)this).locy, ((Sprite2D)this).locx + super.width, ((Sprite2D)this).locy + super.height)) {
                            this.target[j][i].hit();
                            ((Sprite)this).suspend();
                            break;
                        }
                    }
                }
            }
            if (this.theMystery.intersect(((Sprite2D)this).locx, ((Sprite2D)this).locy, ((Sprite2D)this).locx + super.width, ((Sprite2D)this).locy + super.height)) {
                this.theMystery.hit();
                ((Sprite)this).suspend();
            }
        }
        if (((Sprite)this).active) {
            for (int k = 0; k < 80; ++k) {
                for (int l = 0; l < 10; ++l) {
                    if (this.theShield[l][k].intersect(((Sprite2D)this).locx, ((Sprite2D)this).locy, ((Sprite2D)this).locx + super.width, ((Sprite2D)this).locy + super.height)) {
                        this.theShield[l][k].hit();
                        ((Sprite)this).suspend();
                        break;
                    }
                }
            }
        }
    }
}
