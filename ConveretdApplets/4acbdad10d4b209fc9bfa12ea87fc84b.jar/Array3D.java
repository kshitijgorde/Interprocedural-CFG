// 
// Decompiled by Procyon v0.5.30
// 

class Array3D
{
    private double[] array1;
    
    public double get_x() {
        return this.array1[0];
    }
    
    public void set_x(final double n) {
        this.array1[0] = n;
    }
    
    public double get_y() {
        return this.array1[1];
    }
    
    public void set_y(final double n) {
        this.array1[1] = n;
    }
    
    public Array3D() {
        this.array1 = new double[3];
    }
    
    public Array3D(final double n, final double n2, final double n3) {
        (this.array1 = new double[3])[0] = n;
        this.array1[1] = n2;
        this.array1[2] = n3;
    }
    
    public double get_z() {
        return this.array1[2];
    }
    
    public void set_z(final double n) {
        this.array1[2] = n;
    }
    
    public void set_Array3D(final double n, final double n2, final double n3) {
        this.array1[0] = n;
        this.array1[1] = n2;
        this.array1[2] = n3;
    }
}
