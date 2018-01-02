// 
// Decompiled by Procyon v0.5.30
// 

class Quaternion
{
    public double s;
    public double x;
    public double y;
    public double z;
    
    public Quaternion(final double s, final double x, final double y, final double z) {
        this.s = s;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Quaternion(final double angle, final double x, final double y, final double z, final boolean normalize) {
        if (normalize) {
            final double abs = Math.sqrt(x * x + y * y + z * z);
            final double sine = Math.sin(angle / 2.0) / abs;
            this.s = Math.cos(angle / 2.0);
            this.x = sine * x;
            this.y = sine * y;
            this.z = sine * z;
        }
        else {
            final double sine = Math.sin(angle / 2.0);
            this.s = Math.cos(angle / 2.0);
            this.x = sine * x;
            this.y = sine * y;
            this.z = sine * z;
        }
    }
    
    public void add(final Quaternion q) {
        this.s += q.s;
        this.x += q.x;
        this.y += q.y;
        this.z += q.z;
    }
    
    public Quaternion sum(final Quaternion q) {
        return new Quaternion(this.s + q.s, this.x + q.x, this.y + q.y, this.z + q.z);
    }
    
    public void conjugate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
    }
    
    public Quaternion conjugated() {
        return new Quaternion(this.s, -this.x, -this.y, -this.z);
    }
    
    public void multiply(final Quaternion q) {
        final double ns = this.s * q.s - this.x * q.x - this.y * q.y - this.z * q.z;
        final double nx = this.s * q.x + this.x * q.s + this.y * q.z - this.z * q.y;
        final double ny = this.s * q.y - this.x * q.z + this.y * q.s + this.z * q.x;
        final double nz = this.s * q.z + this.x * q.y - this.y * q.x + this.z * q.s;
        this.s = ns;
        this.x = nx;
        this.y = ny;
        this.z = nz;
    }
    
    public Quaternion product(final Quaternion q) {
        final double ns = this.s * q.s - this.x * q.x - this.y * q.y - this.z * q.z;
        final double nx = this.s * q.x + this.x * q.s + this.y * q.z - this.z * q.y;
        final double ny = this.s * q.y - this.x * q.z + this.y * q.s + this.z * q.x;
        final double nz = this.s * q.z + this.x * q.y - this.y * q.x + this.z * q.s;
        return new Quaternion(ns, nx, ny, nz);
    }
    
    public void normalize() {
        final double abs = Math.sqrt(this.s * this.s + this.x * this.x + this.y * this.y + this.z * this.z);
        this.s /= abs;
        this.x /= abs;
        this.y /= abs;
        this.z /= abs;
    }
    
    public Quaternion normalized() {
        final double abs = Math.sqrt(this.s * this.s + this.x * this.x + this.y * this.y + this.z * this.z);
        return new Quaternion(this.s / abs, this.x / abs, this.y / abs, this.z / abs);
    }
    
    public boolean equals(final Quaternion other) {
        return this.s == other.s && this.x == other.x && this.y == other.y && this.z == other.z;
    }
    
    public double[] rotated(final double[] vector) {
        final double sx = this.s * this.x;
        final double sy = this.s * this.y;
        final double sz = this.s * this.z;
        final double xx = this.x * this.x;
        final double xy = this.x * this.y;
        final double xz = this.x * this.z;
        final double yy = this.y * this.y;
        final double yz = this.y * this.z;
        final double zz = this.z * this.z;
        final double[][] matrix = { { 2.0 * (0.5 - yy - zz), 2.0 * (xy - sz), 2.0 * (xz + sy) }, { 2.0 * (xy + sz), 2.0 * (0.5 - xx - zz), 2.0 * (yz - sx) }, { 2.0 * (xz - sy), 2.0 * (yz + sx), 2.0 * (0.5 - xx - yy) } };
        final double[] new_vector = new double[3];
        for (int axis_index = 0; axis_index < 3; ++axis_index) {
            new_vector[axis_index] = matrix[axis_index][0] * vector[0] + matrix[axis_index][1] * vector[1] + matrix[axis_index][2] * vector[2];
        }
        return new_vector;
    }
}
