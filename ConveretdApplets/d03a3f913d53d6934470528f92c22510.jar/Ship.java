import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class Ship
{
    protected int xco;
    protected int yco;
    protected int explodeCount;
    protected boolean alive;
    protected boolean exploding;
    protected int direction;
    protected boolean defaultFace;
    protected int anim;
    
    public Ship() {
        this.direction = 1;
    }
    
    public void animate() {
        ++this.anim;
        if (this.anim > 5) {
            if (this.defaultFace) {
                this.defaultFace = false;
            }
            else {
                this.defaultFace = true;
            }
            this.anim = 0;
        }
    }
    
    public void attack() {
    }
    
    public void calcMove() {
    }
    
    public void changeDirection() {
        this.direction *= -1;
    }
    
    public void disable() {
        this.exploding = false;
        this.alive = false;
    }
    
    public void drop() {
        this.yco += 20;
    }
    
    public void enable() {
        this.alive = true;
        this.explodeCount = 0;
        this.exploding = false;
    }
    
    public void explode(final int n) {
        this.exploding = true;
        ++this.explodeCount;
        if (this.explodeCount > n) {
            this.disable();
        }
    }
    
    public int getDirection() {
        return this.direction;
    }
    
    public Image getFace() {
        return null;
    }
    
    public int getXco() {
        return this.xco;
    }
    
    public int getYco() {
        return this.yco;
    }
    
    public boolean isAlive() {
        return this.alive;
    }
    
    public boolean isExploding() {
        return this.exploding;
    }
    
    public void randAttack() {
    }
    
    public void setDirection(final int n) {
        if (n > 0) {
            this.direction = 1;
        }
        else {
            this.direction = -1;
        }
    }
    
    public void setXco(final int xco) {
        this.xco = xco;
    }
    
    public void setYco(final int yco) {
        this.yco = yco;
    }
}
