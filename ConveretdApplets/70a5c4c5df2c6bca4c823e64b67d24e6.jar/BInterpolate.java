// 
// Decompiled by Procyon v0.5.30
// 

public class BInterpolate
{
    BMatrix InterpolationMatrix;
    double offset;
    int length;
    
    BInterpolate(final int length) {
        this.length = length;
        this.offset = -length / 2.0;
        this.InterpolationMatrix = this.makeInterpolationMatrix(length);
    }
    
    final BMatrix makeInterpolationMatrix(final int n) {
        final BMatrix bMatrix = new BMatrix(n);
        final BVector bVector = new BVector(n);
        final BVector bVector2 = new BVector(n);
        for (int i = 0; i < n; ++i) {
            bVector.zero();
            bVector.set(0, 1.0);
            bVector2.zero();
            bVector2.set(1, 1.0);
            for (int j = 0; j < n; ++j) {
                if (i != j) {
                    bVector2.set(0, -this.offset - j);
                    bVector.mult(bVector, bVector2);
                }
            }
            bVector.scale(1.0 / bVector.eval(i + this.offset));
            bMatrix.sety(i, bVector);
        }
        return bMatrix;
    }
    
    final void makeInterpolator(final BVector bVector, final BVector bVector2) {
        bVector.mult(bVector2, this.InterpolationMatrix);
    }
}
