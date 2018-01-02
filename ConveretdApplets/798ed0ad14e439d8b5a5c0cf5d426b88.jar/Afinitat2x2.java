// 
// Decompiled by Procyon v0.5.30
// 

class Afinitat2x2
{
    double[][] calculaMeu;
    double[][] max;
    double x;
    double y;
    double I;
    double Z;
    double w;
    double h;
    double round;
    double s2w;
    
    public Afinitat2x2() {
        this.round = -0.12345;
        this.s2w = -0.12345;
        this.max = new double[][] { { 1.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0 }, { 0.0, 0.0, 1.0 } };
        this.calculaMeu = new double[][] { { 1.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0 }, { 0.0, 0.0, 1.0 } };
    }
    
    public final void setBoundingBox(final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        this.setBounding(n, n2 + n4, n3, n4, n5, n6);
    }
    
    public final double[] getBoundingBox() {
        return new double[] { this.x, this.y - this.Z, this.I, this.Z, this.w, this.h };
    }
    
    public final void setBounding(final double x, final double y, final double i, final double z, final double w, final double h) {
        this.x = x;
        this.y = y;
        this.I = i;
        this.Z = z;
        this.w = w;
        this.h = h;
        this.calculaMeu[0][0] = i / w;
        this.calculaMeu[0][1] = 0.0;
        this.calculaMeu[1][0] = 0.0;
        this.calculaMeu[1][1] = -z / h;
        this.calculaMeu[0][2] = x;
        this.calculaMeu[1][2] = y;
        this.calculaMeu();
    }
    
    public final void zoom(final double n) {
        this.zoom(n, this.w / 2.0, this.h / 2.0);
    }
    
    public final void zoom(final double n, final double n2, final double n3) {
        final double[] s2w = this.s2w(n2, n3);
        this.setBounding(s2w[0] - n * this.I / 2.0, s2w[1] + n * this.Z / 2.0, n * this.I, n * this.Z, this.w, this.h);
    }
    
    public final void aspectRatio1() {
        final double max = Math.max(this.I / this.w, this.Z / this.h);
        final double n = this.x + this.I / 2.0;
        final double n2 = this.y - this.Z / 2.0;
        final double n3 = this.w * max;
        final double n4 = this.h * max;
        this.setBounding(n - n3 / 2.0, n2 + n4 / 2.0, n3, n4, this.w, this.h);
    }
    
    public final void translateE(final double n, final double n2) {
        this.setBounding(this.x + (this.calculaMeu[0][0] * n + this.calculaMeu[0][1] * n2), this.y + (this.calculaMeu[1][0] * n + this.calculaMeu[1][1] * n2), this.I, this.Z, this.w, this.h);
    }
    
    public final float[] w2sf(final double n, final double n2) {
        return new float[] { (float)(this.max[0][0] * n + this.max[0][1] * n2 + this.max[0][2]), (float)(this.max[1][0] * n + this.max[1][1] * n2 + this.max[1][2]) };
    }
    
    public final double[] w2s(final double n, final double n2) {
        return new double[] { this.max[0][0] * n + this.max[0][1] * n2 + this.max[0][2], this.max[1][0] * n + this.max[1][1] * n2 + this.max[1][2] };
    }
    
    public final int[] w2si(final double n, final double n2) {
        return new int[] { (int)Math.round(this.max[0][0] * n + this.max[0][1] * n2 + this.max[0][2]), (int)Math.round(this.max[1][0] * n + this.max[1][1] * n2 + this.max[1][2]) };
    }
    
    public final double[] s2w(final double n, final double n2) {
        return new double[] { this.calculaMeu[0][0] * n + this.calculaMeu[0][1] * n2 + this.calculaMeu[0][2], this.calculaMeu[1][0] * n + this.calculaMeu[1][1] * n2 + this.calculaMeu[1][2] };
    }
    
    public final double[] s2wd(final double n, final double n2) {
        return new double[] { this.calculaMeu[0][0] * n + this.calculaMeu[0][1] * n2, this.calculaMeu[1][0] * n + this.calculaMeu[1][1] * n2 };
    }
    
    private void calculaMeu() {
        final double n = this.calculaMeu[0][0] * this.calculaMeu[1][1] - this.calculaMeu[0][1] * this.calculaMeu[1][0];
        this.max[0][0] = this.calculaMeu[1][1] / n;
        this.max[0][1] = -this.calculaMeu[0][1] / n;
        this.max[0][2] = (this.calculaMeu[0][1] * this.calculaMeu[1][2] - this.calculaMeu[1][1] * this.calculaMeu[0][2]) / n;
        this.max[1][0] = -this.calculaMeu[1][0] / n;
        this.max[1][1] = this.calculaMeu[0][0] / n;
        this.max[1][2] = (this.calculaMeu[1][0] * this.calculaMeu[0][2] - this.calculaMeu[0][0] * this.calculaMeu[1][2]) / n;
    }
    
    public final Object clone() {
        final Afinitat2x2 afinitat2x2 = new Afinitat2x2();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                afinitat2x2.max[i][j] = this.max[i][j];
                afinitat2x2.calculaMeu[i][j] = this.calculaMeu[i][j];
            }
        }
        afinitat2x2.x = this.x;
        afinitat2x2.y = this.y;
        afinitat2x2.I = this.I;
        afinitat2x2.Z = this.Z;
        afinitat2x2.w = this.w;
        afinitat2x2.h = this.h;
        return afinitat2x2;
    }
}
