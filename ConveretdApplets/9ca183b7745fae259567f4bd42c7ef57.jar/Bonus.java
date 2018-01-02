import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

final class Bonus extends Ship
{
    private Data data;
    private String face1;
    private String face2;
    
    public Bonus(final Data data) {
        this.data = data;
        this.setDirection(1);
        super.xco = -10;
        super.yco = 10;
        this.face1 = "bonus1G";
        this.face2 = "bonus2G";
    }
    
    public void calcMove() {
        if (this.isAlive()) {
            if (super.xco < 380) {
                super.xco += 3;
            }
            else {
                this.disable();
            }
        }
    }
    
    public Image getFace() {
        if (!super.exploding) {
            this.animate();
            if (super.defaultFace) {
                return this.data.getGraphic(this.face1);
            }
            return this.data.getGraphic(this.face2);
        }
        else {
            this.explode();
            if (super.explodeCount % 4 == 0) {
                return this.data.getGraphic("explode1G");
            }
            return this.data.getGraphic("explode2G");
        }
    }
    
    public void reset() {
        super.xco = -10;
        super.yco = 10;
    }
}
