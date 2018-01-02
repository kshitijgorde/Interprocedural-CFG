// 
// Decompiled by Procyon v0.5.30
// 

package en.products;

public class LightRaysPersonal$LightPulsate extends t
{
    public d in_speed;
    public d in_time;
    public d in_max;
    public d in_min;
    public d output;
    
    public LightRaysPersonal$LightPulsate(final LightRaysPersonal lightRaysPersonal) {
        this.in_speed = new d("speed", this, 1, false);
        this.in_time = new d("timer", this, 1, false);
        this.in_max = new d("max amplitude", this, 1, false);
        this.in_min = new d("min amplitude", this, 1, false);
        this.output = new d("output", this, 2, false);
    }
    
    public final boolean execute() {
        final double n = 6.283185307179586 * this.in_time.p() * this.in_speed.p();
        if (this.in_speed.p() == 0) {
            this.output.p(this.in_max.p());
        }
        else {
            this.output.p((float)((0.5 * Math.cos(n) + 0.5) * (this.in_max.p() - this.in_min.p()) + this.in_min.p()));
        }
        return true;
    }
}
