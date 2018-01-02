// 
// Decompiled by Procyon v0.5.30
// 

class Quaternion
{
    double scalar;
    double[] vector;
    
    Quaternion() {
        this.scalar = 1.0;
        this.vector = new double[3];
    }
    
    Quaternion(final String s, double n) {
        n = n * 3.141592653589793 / 180.0;
        if (s.equals("X")) {
            this.vector = new double[] { 1.0, 0.0, 0.0 };
        }
        if (s.equals("Y")) {
            this.vector = new double[] { 0.0, 1.0, 0.0 };
        }
        if (s.equals("Z")) {
            this.vector = new double[] { 0.0, 0.0, 1.0 };
        }
        this.vector = this.scale(Math.sin(n / 2.0));
        this.scalar = Math.cos(n / 2.0);
    }
    
    public Quaternion abs() {
        final Quaternion quaternion = new Quaternion();
        quaternion.scalar = Math.pow(this.scalar * this.scalar + this.dot(this.vector), 0.5);
        quaternion.vector = new double[3];
        return quaternion;
    }
    
    public Quaternion add(final Quaternion quaternion) {
        final Quaternion quaternion2 = new Quaternion();
        quaternion2.scalar = this.scalar + quaternion.scalar;
        quaternion2.vector = this.add(quaternion.vector);
        return quaternion2;
    }
    
    private double[] add(final double[] array) {
        return new double[] { this.vector[0] + array[0], this.vector[1] + array[1], this.vector[2] + array[2] };
    }
    
    private double[] add(final double[] array, final double[] array2) {
        return new double[] { array[0] + array2[0], array[1] + array2[1], array[2] + array2[2] };
    }
    
    public Quaternion conj() {
        final Quaternion quaternion = new Quaternion();
        quaternion.scalar = this.scalar;
        quaternion.vector = new double[] { -this.vector[0], -this.vector[1], -this.vector[2] };
        return quaternion;
    }
    
    private double[] cross(final double[] array) {
        return new double[] { this.vector[1] * array[2] - this.vector[2] * array[1], this.vector[2] * array[0] - this.vector[0] * array[2], this.vector[0] * array[1] - this.vector[1] * array[0] };
    }
    
    private double dot(final double[] array) {
        return this.vector[0] * array[0] + this.vector[1] * array[1] + this.vector[2] * array[2];
    }
    
    public Quaternion inv() {
        final Quaternion quaternion = new Quaternion();
        final double n = this.scalar * this.scalar + this.dot(this.vector);
        quaternion.scalar = 1.0 / n * this.scalar;
        quaternion.vector = this.scale(-(1.0 / n));
        return quaternion;
    }
    
    public Quaternion mult(final Quaternion quaternion) {
        final Quaternion quaternion2 = new Quaternion();
        quaternion2.scalar = this.scalar * quaternion.scalar - this.dot(quaternion.vector);
        quaternion2.vector = this.add(this.add(this.scale(quaternion.vector), this.scale(quaternion.scalar)), this.cross(quaternion.vector));
        return quaternion2;
    }
    
    public Quaternion mult(final double[] array) {
        final Quaternion quaternion = new Quaternion();
        quaternion.scalar = -this.dot(array);
        quaternion.vector = this.add(this.add(this.scale(array), this.scale(0.0)), this.cross(array));
        return quaternion;
    }
    
    public Quaternion norm() {
        final Quaternion quaternion = new Quaternion();
        quaternion.scalar = this.scalar * this.scalar + this.dot(this.vector);
        quaternion.vector = new double[3];
        return quaternion;
    }
    
    public void output() {
        System.out.println(" scalar= " + this.scalar + " vector= [" + this.vector[0] + ", " + this.vector[1] + ", " + this.vector[2] + "]");
    }
    
    private double[] scale(final double n) {
        return new double[] { n * this.vector[0], n * this.vector[1], n * this.vector[2] };
    }
    
    private double[] scale(final double[] array) {
        return new double[] { this.scalar * array[0], this.scalar * array[1], this.scalar * array[2] };
    }
    
    public Quaternion substract(final Quaternion quaternion) {
        final Quaternion quaternion2 = new Quaternion();
        quaternion2.scalar = this.scalar - quaternion.scalar;
        quaternion2.vector = this.subtract(quaternion.vector);
        return quaternion2;
    }
    
    private double[] subtract(final double[] array) {
        return new double[] { this.vector[0] - array[0], this.vector[1] - array[1], this.vector[2] - array[2] };
    }
}
