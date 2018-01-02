// 
// Decompiled by Procyon v0.5.30
// 

class bh extends bb
{
    protected float e;
    protected float f;
    protected boolean g;
    static float h;
    
    bh(final bo d) {
        this.e = 1.1f;
        this.f = 1.0f;
        super.d = d;
        try {
            this.g = !super.d.b().getParameter("SpinStyle").equalsIgnoreCase("wave");
        }
        catch (NullPointerException ex) {
            this.g = true;
        }
        try {
            bh.h = Integer.valueOf(super.d.b().getParameter("SpinSpeed"));
        }
        catch (NumberFormatException ex2) {
            bh.h = 5.0f;
        }
        catch (NullPointerException ex3) {
            bh.h = 5.0f;
        }
        bh.h = bn.b(bh.h, -10.0f, 10.0f) * 0.00127f;
    }
    
    boolean a(final float[] array) {
        if (bh.h == 0.0f) {
            super.d.c(this);
            return false;
        }
        if (!super.d.a(this)) {
            return false;
        }
        if (super.d.b().b() != null) {
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
            return false;
        }
        final r i = super.d.b().i();
        final t e = i.e();
        final float floatValue = (float)e.a("hFOV");
        final float n = bh.h * this.c();
        if (floatValue < 6.2831855f && Math.abs(array[0] + n * this.e) > floatValue / 2.0f) {
            this.e = -this.e;
        }
        final int n2 = 0;
        array[n2] += n * this.e;
        final float n3 = (float)e.a("vFOV") / 2.0f;
        final float n4 = n3 - i.a(array[3]) / 2.0f;
        if (array[1] > n4) {
            this.f = -Math.abs(this.f);
        }
        else if (array[1] < -n4) {
            this.f = Math.abs(this.f);
        }
        if (this.g || n3 < 1.5707964f) {
            final float n5 = (float)Math.log(Math.abs(array[1]) + 1.0f) * Math.abs(bh.h);
            if (array[1] > 0.0f) {
                final int n6 = 1;
                array[n6] -= n5;
            }
            else if (array[1] < 0.0f) {
                final int n7 = 1;
                array[n7] += n5;
            }
        }
        else if (Math.abs(array[1]) > n4) {
            final int n8 = 1;
            array[n8] += this.f * Math.abs(bh.h);
        }
        else {
            final double n9 = Math.asin(array[1] / n4) + this.f * Math.abs(bh.h);
            if (Math.abs(n9) > 1.5707963705062866) {
                this.f = -this.f;
            }
            array[1] = (float)Math.sin(n9) * n4;
        }
        return true;
    }
}
