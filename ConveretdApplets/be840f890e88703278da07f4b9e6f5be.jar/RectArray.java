// 
// Decompiled by Procyon v0.5.30
// 

class RectArray
{
    private boolean Updated;
    private float[] Values;
    private int Rows;
    private int Cols;
    
    public RectArray(final int rows, final int cols) {
        this.Values = new float[rows * cols];
        this.Rows = rows;
        this.Cols = cols;
        this.Updated = true;
    }
    
    public RectArray(final int rows) {
        this.Values = new float[rows];
        this.Rows = rows;
        this.Cols = 1;
    }
    
    public RectArray() {
        this.Values = new float[1];
        this.Rows = 1;
        this.Cols = 1;
    }
    
    public float GetValue() {
        return this.Values[0];
    }
    
    public float GetValue(final int n) {
        if (this.Rows == 1) {
            return this.GetValue();
        }
        return this.Values[n];
    }
    
    public float GetValue(final int n, final int n2) {
        if (this.Cols == 1) {
            return this.GetValue(n);
        }
        return this.Values[n * this.Cols + n2];
    }
    
    public void SetValue(final int n, final int n2, final float n3) {
        this.Updated = true;
        this.Values[n * this.Cols + n2] = n3;
    }
    
    public void SetValue(final int n, final float n2) {
        if (n2 != this.Values[n]) {
            this.Updated = true;
        }
        this.Values[n] = n2;
    }
    
    public void SetValue(final float n) {
        if (n != this.Values[0]) {
            this.Updated = true;
        }
        this.Values[0] = n;
    }
    
    public int Width() {
        return this.Cols;
    }
    
    public int Length() {
        return this.Rows;
    }
    
    public float GetMax() {
        float n = this.Values[0];
        for (int i = 0; i < this.Rows * this.Cols; ++i) {
            if (this.Values[i] > n) {
                n = this.Values[i];
            }
        }
        return n;
    }
    
    public float GetMin() {
        float n = this.Values[0];
        for (int i = 0; i < this.Rows * this.Cols; ++i) {
            if (this.Values[i] < n) {
                n = this.Values[i];
            }
        }
        return n;
    }
    
    public void SetChanged() {
        this.Updated = true;
    }
    
    public void ResetChanged() {
        this.Updated = false;
    }
    
    public boolean IsChanged() {
        return this.Updated;
    }
}
