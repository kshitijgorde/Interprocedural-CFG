// 
// Decompiled by Procyon v0.5.30
// 

class w extends r
{
    protected float e;
    protected float f;
    protected boolean g;
    protected float h;
    
    w(final bb d) {
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
            this.h = Integer.valueOf(super.d.b().getParameter("SpinSpeed"));
        }
        catch (Exception ex2) {
            this.h = 5.0f;
        }
        this.h = ba.a(this.h, -10.0f, 10.0f) * 0.00127f;
    }
    
    boolean a(final float[] array) {
        if (this.h == 0.0f) {
            super.d.b(this);
            return false;
        }
        if (!super.d.c(this)) {
            return false;
        }
        if (super.d.b().i() != null) {
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
            return false;
        }
        final k h = super.d.b().h();
        final m b = h.b();
        final float floatValue = (float)b.a("hFOV");
        final float n = this.h * this.b();
        if (floatValue < 6.2831855f && Math.abs(array[0] + n * this.e) > floatValue / 2.0f) {
            this.e = -this.e;
        }
        final int n2 = 0;
        array[n2] += n * this.e;
        final float n3 = (float)b.a("vFOV") / 2.0f;
        final float n4 = n3 - h.a(array[3]) / 2.0f;
        if (array[1] > n4) {
            this.f = -Math.abs(this.f);
        }
        else if (array[1] < -n4) {
            this.f = Math.abs(this.f);
        }
        if (this.g || n3 < 1.5707964f) {
            final float n5 = (float)Math.log(Math.abs(array[1]) + 1.0f) * Math.abs(this.h);
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
            array[n8] += this.f * Math.abs(this.h);
        }
        else {
            final double n9 = Math.asin(array[1] / n4) + this.f * Math.abs(this.h);
            if (Math.abs(n9) > 1.5707963705062866) {
                this.f = -this.f;
            }
            array[1] = (float)Math.sin(n9) * n4;
        }
        return true;
    }
}
