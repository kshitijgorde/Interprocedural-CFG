// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet.a;

public class a
{
    private int a;
    private float[] if;
    private int for;
    private int do;
    
    public a(final int a) {
        this.a = a;
        this.if = new float[a];
        this.if();
    }
    
    public void if() {
        this.for = 0;
        this.do = 0;
    }
    
    public float a(final float n) {
        if (this.do < this.a) {
            this.if[this.do] = n;
            ++this.do;
        }
        else {
            this.if[this.for] = n;
            this.for = (this.for + 1) % this.a;
        }
        return this.a();
    }
    
    public float a(final int n) {
        if (this.do == 0) {
            return 0.0f;
        }
        return this.if[(this.for + this.do - n) % this.a];
    }
    
    public float a() {
        if (this.do == 0) {
            return 0.0f;
        }
        float n = 0.0f;
        for (int i = 0; i < this.do; ++i) {
            n += this.if[(i + this.for) % this.do];
        }
        return n / this.do;
    }
    
    public float for() {
        if (this.do < 1) {
            return 0.0f;
        }
        float min = this.if[this.for % this.do];
        for (int i = 1; i < this.do; ++i) {
            final float n = this.if[(i + this.for) % this.do];
            if (n != Float.MIN_VALUE) {
                min = Math.min(min, n);
            }
        }
        return min;
    }
    
    public float do() {
        if (this.do < 1) {
            return 0.0f;
        }
        float max = this.if[this.for % this.do];
        for (int i = 1; i < this.do; ++i) {
            final float n = this.if[(i + this.for) % this.do];
            if (n != Float.MIN_VALUE) {
                max = Math.max(max, n);
            }
        }
        return max;
    }
}
