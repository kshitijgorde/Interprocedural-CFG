// 
// Decompiled by Procyon v0.5.30
// 

package en.products;

import java.util.Random;

public class LightRaysPersonal$LightMotion extends t
{
    public n in_dimension;
    public d in_time;
    public e in_type;
    public d in_speed;
    public d in_xscale;
    public d in_yscale;
    public f out_light;
    public c useMouse;
    public volatile int mouseX;
    public volatile int mouseY;
    public boolean mouseEntered;
    public Random rand;
    public float randX;
    public float randY;
    public float randDX;
    public float randDY;
    public float timeStamp;
    public boolean firstTime;
    
    public LightRaysPersonal$LightMotion(final LightRaysPersonal lightRaysPersonal) {
        this.in_dimension = new n("dimension", this, 1, false);
        this.in_time = new d("timer", this, 1, false);
        this.in_type = new e("motion type", this, 1, true);
        this.in_speed = new d("speed", this, 1, false);
        this.in_xscale = new d("xscale", this, 1, false);
        this.in_yscale = new d("yscale", this, 1, false);
        this.out_light = new f("light position", this, 2, false);
        this.useMouse = new c("use mouse", this, 1, false);
        this.mouseEntered = false;
        this.rand = new Random(System.currentTimeMillis());
        this.firstTime = true;
    }
    
    public final boolean initialize() {
        this.randX = this.in_dimension.d() / 2;
        this.randY = this.in_dimension.p() / 2;
        return true;
    }
    
    public final void connectorChanged(final v v) {
        if (v == this.in_type) {
            this.firstTime = true;
        }
    }
    
    public final boolean execute() {
        final int n = this.in_dimension.d() / 2;
        final int n2 = this.in_dimension.p() / 2;
        if (this.useMouse.p() && this.mouseEntered) {
            this.out_light.p(this.mouseX);
            this.out_light.d(this.mouseY);
        }
        else {
            switch (this.in_type.p()) {
                case 0: {
                    final double n3 = this.in_time.p() * this.in_speed.p() * 2 * 3.141592653589793;
                    this.out_light.p((float)(n * (this.in_xscale.p() * Math.sin(n3) + 1)));
                    this.out_light.d((float)(n2 * (this.in_yscale.p() * Math.cos(n3) + 1)));
                    break;
                }
                case 1: {
                    this.out_light.p((float)(n * (this.in_xscale.p() * Math.sin(this.in_time.p() * this.in_speed.p() * 2 * 3.141592653589793) + 1)));
                    this.out_light.d(this.in_dimension.p() * this.in_yscale.p() * 0.99f);
                    break;
                }
                case 2: {
                    final double n4 = this.in_time.p() * this.in_speed.p() * 2 * 3.141592653589793;
                    this.out_light.p(this.in_dimension.d() * this.in_xscale.p() * 0.99f);
                    this.out_light.d((float)(n2 * (this.in_yscale.p() * Math.sin(n4) + 1)));
                    break;
                }
                case 3: {
                    if (this.rand.nextFloat() > 0.98f || this.firstTime) {
                        this.randDX = (this.rand.nextFloat() - 0.5f) * this.in_speed.p() * 10;
                        this.randDY = (this.rand.nextFloat() - 0.5f) * this.in_speed.p() * 10;
                        this.firstTime = false;
                    }
                    this.randX += this.randDX;
                    this.randY += this.randDY;
                    if (this.randX > n + n * this.in_xscale.p()) {
                        this.randX = n + n * this.in_xscale.p();
                        this.randDX *= -1;
                    }
                    else if (this.randX < n - n * this.in_xscale.p()) {
                        this.randX = n - n * this.in_xscale.p();
                        this.randDX *= -1;
                    }
                    if (this.randY > n2 + n2 * this.in_yscale.p()) {
                        this.randY = n2 + n2 * this.in_yscale.p();
                        this.randDY *= -1;
                    }
                    else if (this.randY < n2 - n2 * this.in_yscale.p()) {
                        this.randY = n2 - n2 * this.in_yscale.p();
                        this.randDY *= -1;
                    }
                    this.out_light.p(this.randX);
                    this.out_light.d(this.randY);
                    break;
                }
                case 4: {
                    if (this.rand.nextFloat() > 0.96f || this.firstTime) {
                        this.randDX = (this.rand.nextFloat() - 0.5f) * 0.1f;
                        this.randDY = (this.rand.nextFloat() - 0.5f) * 0.1f;
                        this.firstTime = false;
                    }
                    this.randX += this.randDX;
                    this.randY += this.randDY;
                    final double n5 = this.in_time.p() * this.in_speed.p() * 0.01f;
                    this.out_light.p((float)(n * (this.in_xscale.p() * Math.sin(this.randX * n5) + 1)));
                    this.out_light.d((float)(n2 * (this.in_yscale.p() * Math.sin(this.randY * n5) + 1)));
                    break;
                }
                case 5: {
                    if (this.firstTime || this.in_time.p() - this.timeStamp > this.in_speed.p()) {
                        this.firstTime = false;
                        this.timeStamp = this.in_time.p();
                        this.randX = (this.rand.nextFloat() * 2 - 1) * n * this.in_xscale.p() * 0.99f + n;
                        this.randY = (this.rand.nextFloat() * 2 - 1) * n2 * this.in_yscale.p() * 0.99f + n2;
                    }
                    this.out_light.p(this.randX);
                    this.out_light.d(this.randY);
                    break;
                }
                case 6: {
                    this.out_light.p(this.in_xscale.p() * this.in_dimension.d() * 0.99f);
                    this.out_light.d(this.in_yscale.p() * this.in_dimension.p() * 0.99f);
                    break;
                }
            }
        }
        return true;
    }
}
