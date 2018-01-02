// 
// Decompiled by Procyon v0.5.30
// 

class Vertex
{
    protected double x;
    protected double y;
    protected double z;
    
    public Vertex() {
        this(0.0, 0.0, 0.0);
    }
    
    public Vertex(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Vertex(final Vertex vertex) {
        this.x = vertex.x;
        this.y = vertex.y;
        this.z = vertex.z;
    }
    
    public String toString() {
        return "[" + this.x + "," + this.y + "," + this.z + "]";
    }
    
    public void matrixMultiply(final Matrix2D matrix2D) {
        if (matrix2D.getCols() != 3) {
            return;
        }
        final double x = this.x * matrix2D.element[0][0] + this.y * matrix2D.element[1][0] + this.z * matrix2D.element[2][0];
        final double y = this.x * matrix2D.element[0][1] + this.y * matrix2D.element[1][1] + this.z * matrix2D.element[2][1];
        final double z = this.x * matrix2D.element[0][2] + this.y * matrix2D.element[1][2] + this.z * matrix2D.element[2][2];
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
