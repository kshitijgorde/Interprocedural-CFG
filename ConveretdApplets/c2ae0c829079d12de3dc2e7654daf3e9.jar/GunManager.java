import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class GunManager extends Thread
{
    Intersect[][] theShield;
    Intersect[][] theTarget;
    public GunSprite gun;
    private int gun_width;
    private int gun_height;
    private MissileSprite missile;
    static int width;
    static int height;
    private int min_x;
    private int max_x;
    private int gun_min_x;
    private int gun_max_x;
    private int mis_min_x;
    private int mis_max_x;
    private int gun_y;
    static final int MISSILE_WIDTH = 2;
    static final int MISSILE_HEIGHT = 20;
    static final int MISSILE_SPEED = -10;
    static final Color MISSILE_COLOR;
    protected int ROWS;
    protected int COLS;
    GunSprite explosion;
    SpacedInvaders si;
    int locx;
    int locy;
    int hitcount;
    
    public GunManager(final int width, final int height, final Image image, final Image image2, final Intersect[][] theTarget, final SpacedInvaders si, final int rows, final int cols, final Intersect[][] theShield, final Intersect intersect) {
        GunManager.width = width;
        GunManager.height = height;
        this.ROWS = rows;
        this.COLS = cols;
        this.hitcount = 0;
        this.gun = new GunSprite(image, si);
        this.theShield = theShield;
        this.theTarget = theTarget;
        this.si = si;
        this.explosion = new GunSprite(image2, si);
        this.gun_width = image.getWidth((ImageObserver)si) / 2;
        this.gun_height = image.getHeight((ImageObserver)si);
        this.locy = 470;
        this.gun_y = height - this.gun_height;
        this.min_x = this.gun_width;
        this.max_x = width - this.gun_width;
        this.gun_min_x = 0;
        this.gun_max_x = width - 2 * this.gun_width;
        this.mis_min_x = this.min_x - 2;
        this.mis_max_x = this.max_x - 2;
        this.gun.setPosition(width / 2 - this.gun_width, this.gun_y);
        this.explosion.setPosition(width / 2 - this.gun_width, this.gun_y - 30);
        this.missile = new MissileSprite(2, 20, GunManager.MISSILE_COLOR, -10, height - this.gun_height, 0, theTarget, this.ROWS, this.COLS, this.theShield, intersect);
    }
    
    public void moveGun(final int locx) {
        this.locx = locx;
        this.gun.setPosition(locx - this.gun_width, this.gun_y);
        this.explosion.setPosition(locx - this.gun_width, this.gun_y - 30);
    }
    
    public void fireMissile(final int n) {
        if (!((Sprite)this.missile).isActive()) {
            this.missile.init(n);
        }
    }
    
    public void update() {
        this.missile.update();
        for (int i = 0; i < this.COLS; ++i) {
            for (int j = 0; j < this.ROWS; ++j) {
                if (this.theTarget[j][i].intersect(this.locx, this.locy, this.locx + GunManager.width, this.locy + GunManager.height)) {
                    this.gun.hit();
                    this.si.GAME_STATE = 2;
                }
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (((Sprite)this.gun).isActive()) {
            ((BitmapSprite)this.gun).paint(graphics);
            ((RectSprite)this.missile).paint(graphics);
        }
        else if (!((Sprite)this.gun).isActive() && this.hitcount > 0) {
            ((BitmapSprite)this.explosion).paint(graphics);
            --this.hitcount;
        }
    }
    
    public GunSprite getGun() {
        return this.gun;
    }
    
    public int getGunY() {
        return this.gun_y;
    }
    
    public void reset() {
        ((Sprite)this.missile).suspend();
        this.gun.setPosition(GunManager.width / 2 - this.gun_width, this.gun_y);
    }
    
    public void doExplosion() {
        this.hitcount = 15;
    }
    
    static {
        MISSILE_COLOR = Color.red;
    }
}
