import java.awt.Graphics;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class NewSwarm extends Thread
{
    Image[] alien;
    SpacedInvaders si;
    Intersect theTarget;
    Intersect[][] theShield;
    int gun_y;
    int lbound;
    int rbound;
    final int ROWS;
    final int COLS;
    final int OFFSET_X = 40;
    final int OFFSET_Y = 32;
    byte direction;
    final byte LEFT = 0;
    final byte RIGHT = 1;
    int down;
    int velocity;
    double fireFreq;
    int newLevelOffsety;
    int delay;
    Thread alienManager;
    UFO[][] theSwarm;
    int activeCol;
    int activeBoundary;
    Image explosion;
    
    public NewSwarm(final Image[] alien, final Image[] array, final Image explosion, final int lbound, final int rbound, final int rows, final int cols, final SpacedInvaders si) {
        this.alien = alien;
        this.si = si;
        this.lbound = lbound;
        this.rbound = rbound;
        this.activeBoundary = this.rbound;
        this.ROWS = rows;
        this.COLS = cols;
        this.direction = 1;
        this.down = 0;
        this.velocity = 1;
        this.fireFreq = 0.999;
        this.delay = 150;
        this.newLevelOffsety = 60;
        this.explosion = explosion;
        this.alienManager = new Thread(this);
        this.theSwarm = new UFO[this.ROWS][this.COLS];
        int n = 0;
        int n2 = 60;
        for (int i = 0; i < this.COLS; ++i) {
            for (int j = 0; j < this.ROWS; ++j) {
                if (j > 1) {
                    this.theSwarm[j][i] = new UFO(this.alien, this.explosion, 500, 400, this.si);
                }
                else {
                    this.theSwarm[j][i] = new UFO(array, this.explosion, 500, 400, this.si);
                }
                ((BitmapLoop)this.theSwarm[j][i]).setPosition(n, n2);
                ((Sprite)this.theSwarm[j][i]).setVisible(true);
                ((Moveable)this.theSwarm[j][i]).setVelocity(this.velocity, this.down);
                n2 += 32;
            }
            n += 40;
            n2 = 60;
        }
        this.getActiveCol();
    }
    
    public synchronized void move() {
        this.getActiveCol();
        if ((this.activeCol >= this.activeBoundary && this.direction == 1) || (this.activeCol <= this.activeBoundary && this.direction == 0)) {
            this.down = 16;
            this.switchDirection();
            this.switchBoundary();
            this.velocity = -this.velocity;
            for (int i = 0; i < this.COLS; ++i) {
                for (int j = 0; j < this.ROWS; ++j) {
                    ((Moveable)this.theSwarm[j][i]).setVelocity(this.velocity, this.down);
                    this.theSwarm[j][i].setFreq(this.fireFreq);
                    ((BitmapLoop)this.theSwarm[j][i]).updatePosition();
                    this.theSwarm[j][i].update();
                }
            }
            this.down = 0;
        }
        else {
            for (int k = 0; k < this.COLS; ++k) {
                for (int l = 0; l < this.ROWS; ++l) {
                    ((Moveable)this.theSwarm[l][k]).setVelocity(this.velocity, this.down);
                    this.theSwarm[l][k].setFreq(this.fireFreq);
                    ((BitmapLoop)this.theSwarm[l][k]).updatePosition();
                    this.theSwarm[l][k].update();
                }
            }
        }
    }
    
    private void getActiveCol() {
        if (this.direction == 1) {
            for (int i = 0; i < this.COLS; ++i) {
                for (int j = 0; j < this.ROWS; ++j) {
                    if (((Sprite)this.theSwarm[j][i]).isVisible()) {
                        this.activeCol = ((BitmapSprite)this.theSwarm[j][i]).locx;
                    }
                }
            }
            this.activeCol += 32;
        }
        else if (this.direction == 0) {
            for (int k = this.COLS - 1; k > -1; --k) {
                for (int l = 0; l < this.ROWS; ++l) {
                    if (((Sprite)this.theSwarm[l][k]).isVisible()) {
                        this.activeCol = ((BitmapSprite)this.theSwarm[l][k]).locx;
                    }
                }
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        for (int i = 0; i < this.COLS; ++i) {
            for (int j = 0; j < this.ROWS; ++j) {
                this.theSwarm[j][i].paint(graphics);
                ((RectSprite)this.theSwarm[j][i].missile).paint(graphics);
            }
        }
    }
    
    public UFO[][] getSwarm() {
        return this.theSwarm;
    }
    
    public void getTarget(final GunManager gunManager, final Intersect[][] theShield) {
        this.theTarget = (Intersect)gunManager.getGun();
        this.gun_y = gunManager.getGunY();
        this.theShield = theShield;
        for (int i = 0; i < this.COLS; ++i) {
            for (int j = 0; j < this.ROWS; ++j) {
                this.theSwarm[j][i].missile.getTarget(gunManager, this.theShield);
                this.theSwarm[j][i].setTarget(this.theShield);
                this.theSwarm[j][i].setFreq(this.fireFreq);
            }
        }
    }
    
    public void switchDirection() {
        if (this.direction == 0) {
            this.direction = 1;
        }
        else if (this.direction == 1) {
            this.direction = 0;
        }
    }
    
    public void switchBoundary() {
        if (this.activeBoundary == this.lbound) {
            this.activeBoundary = this.rbound;
        }
        else if (this.activeBoundary == this.rbound) {
            this.activeBoundary = this.lbound;
        }
    }
    
    public void setFireFreq(final double n, final boolean b) {
        this.fireFreq -= n;
        if (b) {
            if (this.direction == 0) {
                --this.velocity;
            }
            else {
                ++this.velocity;
            }
        }
    }
    
    public void run() {
        while (true) {
            this.move();
            Thread.currentThread();
            Thread.yield();
            try {
                Thread.sleep(this.delay);
            }
            catch (Exception ex) {}
        }
    }
    
    public void setSwarmDelay() {
        this.delay -= 2;
    }
    
    public synchronized void gameOver() {
        this.direction = 1;
        this.activeBoundary = this.rbound;
        this.down = 0;
        this.velocity = 1;
        this.fireFreq = 0.999;
        this.delay = 140;
        int n = 0;
        int n2 = this.newLevelOffsety;
        for (int i = 0; i < this.COLS; ++i) {
            for (int j = 0; j < this.ROWS; ++j) {
                ((Sprite)this.theSwarm[j][i]).suspend();
                ((Sprite)this.theSwarm[j][i].missile).suspend();
                this.theSwarm[j][i].setHitCount();
                ((BitmapLoop)this.theSwarm[j][i]).setPosition(n, n2);
                ((Moveable)this.theSwarm[j][i]).setVelocity(this.velocity, this.down);
                ((Sprite)this.theSwarm[j][i]).restore();
                n2 += 32;
            }
            n += 40;
            n2 = this.newLevelOffsety;
        }
    }
    
    public void setLevel() {
        if (this.si.GAME_STATE == 3) {
            this.newLevelOffsety += 15;
        }
        else {
            this.newLevelOffsety = 60;
        }
    }
}
