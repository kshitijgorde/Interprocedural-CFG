// 
// Decompiled by Procyon v0.5.30
// 

package b;

public class k
{
    float do;
    float a;
    float for;
    float if;
    
    public void a() {
        final float n = 0.0f;
        this.a = n;
        this.do = n;
        final float n2 = 0.0f;
        this.for = n2;
        this.if = n2;
    }
    
    public void a(final float[] array, final int n) {
        for (int i = 0; i < n; ++i) {
            final float a = this.a;
            this.a = this.do;
            this.do = array[i];
            final float for1 = this.for * c.int[1] + this.if * c.int[2] + this.do * c.case[0] + this.a * c.case[1] + a * c.case[2];
            array[i] = for1;
            this.if = this.for;
            this.for = for1;
        }
    }
}
