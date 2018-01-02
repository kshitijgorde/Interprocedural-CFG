import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

final class Player extends Ship
{
    private Data data;
    private boolean shooting;
    private int shootcount;
    
    public Player(final Data data) {
        this.shooting = false;
        this.data = data;
        super.yco = 270;
        super.xco = 180;
    }
    
    public void calcMove(final int n) {
        if (super.xco > this.data.getLimitBlock() || super.xco < 375 - this.data.getLimitBlock()) {
            super.xco += n;
        }
        if (super.xco < this.data.getLimitBlock() + 1 || super.xco > 374 - this.data.getLimitBlock()) {
            super.xco -= n;
        }
    }
    
    public Image getFace() {
        if (!super.exploding) {
            if (!this.shooting) {
                return this.data.getGraphic("player1G");
            }
            ++this.shootcount;
            if (this.shootcount == 5) {
                this.shootcount = 0;
                this.shooting = false;
            }
            return this.data.getGraphic("player2G");
        }
        else {
            this.explode(40);
            if (super.explodeCount % 4 == 0) {
                return this.data.getGraphic("explode1G");
            }
            return this.data.getGraphic("explode2G");
        }
    }
    
    public void randAttack() {
        if (this.data.getBulletsLeft() > 0 && !super.exploding) {
            for (int i = 0; i < 4; ++i) {
                if (!this.data.playerFire[i].isAlive()) {
                    this.data.addToBullets(-1);
                    this.data.playerFire[i].setXco(super.xco + 7);
                    this.data.playerFire[i].setYco(270);
                    this.data.playerFire[i].setDirection(-1);
                    this.data.playerFire[i].enable();
                    if (this.data.isMode(2)) {
                        this.data.playerFire[i + 4].setXco(super.xco + 7);
                        this.data.playerFire[i + 4].setYco(255);
                        this.data.playerFire[i + 4].setDirection(-1);
                        this.data.playerFire[i + 4].enable();
                    }
                    else if (this.data.isMode(3)) {
                        this.data.playerFire[i].setXco(super.xco);
                        this.data.playerFire[i].setYco(270);
                        this.data.playerFire[i].setDirection(-1);
                        this.data.playerFire[i + 4].setXco(super.xco + 20);
                        this.data.playerFire[i + 4].setYco(270);
                        this.data.playerFire[i + 4].setDirection(-1);
                        this.data.playerFire[i + 4].enable();
                    }
                    this.shooting = true;
                    this.shootcount = 0;
                    this.data.inv.shootS.play();
                    break;
                }
            }
        }
    }
}
