// 
// Decompiled by Procyon v0.5.30
// 

class Object3D
{
    int maxVertices;
    int numVertices;
    Vertex[] vertices;
    double x;
    double y;
    double z;
    Matrix2D xRotationMatrix;
    Matrix2D yRotationMatrix;
    Matrix2D zRotationMatrix;
    
    public Object3D() {
        this(1282);
    }
    
    public Object3D(final int maxVertices) {
        this.maxVertices = maxVertices;
        this.vertices = new Vertex[maxVertices];
        this.numVertices = 0;
        final double x = 0.0;
        this.z = x;
        this.y = x;
        this.x = x;
    }
    
    public String toString() {
        String string = "";
        for (int i = 0; i < this.numVertices; ++i) {
            string += this.vertices[i].toString();
        }
        return string;
    }
    
    public void addVertex(final Vertex vertex) {
        if (this.numVertices < this.maxVertices) {
            this.vertices[this.numVertices] = new Vertex(vertex);
            ++this.numVertices;
        }
    }
    
    public void addVertex(final double n, final double n2, final double n3) {
        if (this.numVertices < this.maxVertices) {
            this.vertices[this.numVertices] = new Vertex(n, n2, n3);
            ++this.numVertices;
        }
    }
}
