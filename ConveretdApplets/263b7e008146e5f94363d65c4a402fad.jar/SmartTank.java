import gamelib.ActionBuffer;

// 
// Decompiled by Procyon v0.5.30
// 

class SmartTank extends Tank
{
    private Tank target;
    private float dif;
    
    SmartTank(final ActionBuffer actionBuffer, final Player player) {
        super(actionBuffer, player);
        this.target = null;
    }
    
    void activate() {
        super.activate();
        ((Field)super.buffer).tc.block = true;
        if (this.target == null || this.target.h <= 0.0f) {
            this.target = null;
            for (int i = 0; i < ((Field)super.buffer).tanks.length; ++i) {
                final Tank target = ((Field)super.buffer).tanks[i];
                if (target.owner != super.owner && target.h > 0.0f && (this.target == null || Math.abs(super.x - target.x) < Math.abs(super.x - this.target.x))) {
                    this.target = target;
                }
            }
            if (this.target != null) {
                if (super.x < this.target.x) {
                    super.a = 35.0f;
                }
                else {
                    super.a = -35.0f;
                }
                super.v = (int)(Math.abs(super.x - this.target.x) / 2.6f);
                this.dif = super.v;
            }
        }
        this.shoot();
    }
    
    void impactAt(final int n) {
        final int vics = ((Field)super.buffer).tanks[0].owner.vics;
        if (this.target != null) {
            if (vics == 0 || super.owner.vics / vics > 1.6) {
                this.dif /= 2.0f;
                if ((this.target.x > n && super.a > 0.0f) || (this.target.x < n && super.a < 0.0f)) {
                    super.v += Math.abs(this.dif);
                }
                else {
                    super.v -= Math.abs(this.dif);
                }
            }
            else {
                super.v = (int)(super.v * (0.3f + 0.7f * (super.x - this.target.x) / (super.x - n)));
            }
        }
    }
}
