// 
// Decompiled by Procyon v0.5.30
// 

class Matrix2D
{
    private int cols;
    private int rows;
    public double[][] element;
    
    public Matrix2D() {
        this(3, 3);
    }
    
    public Matrix2D(final int rows, final int cols) {
        this.cols = cols;
        this.rows = rows;
        this.element = new double[rows][cols];
        this.makeIdentity();
    }
    
    public int getCols() {
        return this.cols;
    }
    
    public int getRows() {
        return this.rows;
    }
    
    public void makeIdentity() {
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.cols; ++j) {
                if (i == j) {
                    this.element[i][j] = 1.0;
                }
                else {
                    this.element[i][j] = 0.0;
                }
            }
        }
    }
    
    public String toString() {
        String string = "";
        for (int i = 0; i < this.rows; ++i) {
            String s = string + '[';
            for (int j = 0; j < this.cols; ++j) {
                s += this.element[i][j];
                if (j < this.cols - 1) {
                    s += ',';
                }
            }
            string = s + ']';
        }
        return string;
    }
    
    public void setElement(final int n, final int n2, final double n3) {
        if (n >= 0 && n < this.rows && n2 >= 0 && n2 < this.cols) {
            this.element[n][n2] = n3;
        }
    }
    
    public double getElement(final int n, final int n2) {
        if (n >= 0 && n < this.rows && n2 >= 0 && n2 < this.cols) {
            return this.element[n][n2];
        }
        return 0.0;
    }
}
