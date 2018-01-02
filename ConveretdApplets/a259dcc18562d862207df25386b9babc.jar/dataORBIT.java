// 
// Decompiled by Procyon v0.5.30
// 

class dataORBIT
{
    public double[] initialX;
    public double[] initialY;
    public int[] direction;
    public int[] color;
    public int[] solver;
    public int[] startPointer;
    public int[] endPointer;
    public int count;
    public int dataFree;
    public float[] X;
    public float[] Y;
    public float[] T;
    public boolean[] specifyRange;
    public double[] rangeMin;
    public double[] rangeMax;
    
    public dataORBIT(final int n, final int n2) {
        this.count = -1;
        this.dataFree = 0;
        this.initialX = new double[n];
        this.initialY = new double[n];
        this.direction = new int[n];
        this.color = new int[n];
        this.solver = new int[n];
        this.startPointer = new int[n];
        this.endPointer = new int[n];
        this.X = new float[n2];
        this.Y = new float[n2];
        this.T = new float[n2];
        this.specifyRange = new boolean[n];
        this.rangeMin = new double[n];
        this.rangeMax = new double[n];
    }
    
    public int SelectOrbit(final float n, final float n2) {
        if (this.count < 0) {
            return -1;
        }
        float n3 = Float.MAX_VALUE;
        int n4 = -1;
        for (int i = 0; i < this.dataFree; ++i) {
            final float n5 = (this.X[i] - n) * (this.X[i] - n) + (this.Y[i] - n2) * (this.Y[i] - n2);
            if (n5 < n3) {
                n3 = n5;
                n4 = i;
            }
        }
        if (n4 < 0) {
            return -1;
        }
        if (this.endPointer[this.count] < n4) {
            return -1;
        }
        int n6;
        for (n6 = 0; this.endPointer[n6] < n4; ++n6) {}
        return n6;
    }
    
    public int GetIndexofSiblingOrbit(final int n) {
        final int n2 = n + this.direction[n];
        if (this.initialX[n] == this.initialX[n2] && this.initialY[n] == this.initialY[n2] && this.direction[n] * this.direction[n2] == -1) {
            return n2;
        }
        return -1;
    }
    
    public void DeleteOrbit(int n) {
        if (n < 0) {
            return;
        }
        final int getIndexofSiblingOrbit = this.GetIndexofSiblingOrbit(n);
        int n2;
        if (getIndexofSiblingOrbit == -1) {
            n2 = 1;
        }
        else {
            n2 = 2;
            if (getIndexofSiblingOrbit < n) {
                n = getIndexofSiblingOrbit;
            }
        }
        this.dataFree = this.startPointer[n];
        for (int i = n; i <= this.count; ++i) {
            this.initialX[i] = this.initialX[i + n2];
            this.initialY[i] = this.initialY[i + n2];
            this.direction[i] = this.direction[i + n2];
            this.solver[i] = this.solver[i + n2];
            this.specifyRange[i] = this.specifyRange[i + 1];
            this.rangeMin[i] = this.rangeMin[i + n2];
            this.rangeMax[i] = this.rangeMax[i + n2];
            this.startPointer[i] = this.dataFree;
            for (int j = this.startPointer[i + n2]; j <= this.endPointer[i + n2]; ++j) {
                this.X[this.dataFree] = this.X[j];
                this.Y[this.dataFree] = this.Y[j];
                this.T[this.dataFree] = this.T[j];
                ++this.dataFree;
            }
            this.endPointer[i] = this.dataFree - 1;
        }
        this.count -= n2;
    }
}
