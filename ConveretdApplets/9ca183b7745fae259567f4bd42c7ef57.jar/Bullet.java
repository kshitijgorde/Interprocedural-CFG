import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

final class Bullet extends Ship
{
    private Data data;
    private int speed;
    private int type;
    
    public Bullet(final Data data) {
        this.type = 1;
        this.data = data;
        super.yco = 0;
        super.xco = 0;
    }
    
    public void calcMove() {
        if (super.yco > 0 && super.yco < 300) {
            super.yco += this.speed * this.getDirection();
        }
        else {
            this.disable();
        }
    }
    
    public void calcMove(final int n) {
        if (super.yco > 0 && super.yco < 300) {
            super.yco += n * this.getDirection();
        }
        else {
            this.disable();
            this.data.addToBullets(1);
        }
    }
    
    public void calcMove(final int n, final int n2) {
        if (super.yco > 0 && super.yco < 300) {
            super.yco += n * this.getDirection();
        }
        else {
            this.disable();
            this.data.addToBullets(1, n2);
        }
    }
    
    public Image getFace() {
        if (!this.data.superBullets || this.type == 1) {
            return this.data.getGraphic("bulletG");
        }
        return this.data.getGraphic("bullet2G");
    }
    
    public void randAttack() {
    }
    
    public void setFamily(final int n) {
    }
    
    public void setSpeed(final int speed) {
        this.speed = speed;
    }
    
    public void setType(final int type) {
        this.type = type;
    }
}
