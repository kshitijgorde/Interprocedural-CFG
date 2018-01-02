// 
// Decompiled by Procyon v0.5.30
// 

class tdObject
{
    public tdModel m_model;
    public tdVector m_vector;
    public tdMatrix m_matrix;
    public int m_nVertices;
    public tdVector[] m_vectorWorld;
    public int[] m_nXview;
    public int[] m_nYview;
    public tdVector[] m_vectorView;
    public boolean[] m_bVisible;
    public int m_nPolygons;
    public tdPolygon[] m_polygon;
    
    public void transform() {
        for (int i = 0; i < this.m_nVertices; ++i) {
            final double dx = this.m_model.m_vectorVertex[i].m_dX;
            final double dy = this.m_model.m_vectorVertex[i].m_dY;
            final double dz = this.m_model.m_vectorVertex[i].m_dZ;
            this.m_vectorWorld[i].m_dX = dx * this.m_matrix.m_dXx + dy * this.m_matrix.m_dYx + dz * this.m_matrix.m_dZx + this.m_vector.m_dX;
            this.m_vectorWorld[i].m_dY = dx * this.m_matrix.m_dXy + dy * this.m_matrix.m_dYy + dz * this.m_matrix.m_dZy + this.m_vector.m_dY;
            this.m_vectorWorld[i].m_dZ = dx * this.m_matrix.m_dXz + dy * this.m_matrix.m_dYz + dz * this.m_matrix.m_dZz + this.m_vector.m_dZ;
        }
    }
    
    public tdObject(final tdModel model) {
        this.m_model = model;
        this.m_vector = new tdVector();
        this.m_matrix = new tdMatrix();
        this.m_nVertices = model.m_nVertices;
        this.m_vectorWorld = new tdVector[this.m_nVertices];
        this.m_vectorView = new tdVector[this.m_nVertices];
        for (int i = 0; i < this.m_nVertices; ++i) {
            this.m_vectorWorld[i] = new tdVector();
            this.m_vectorView[i] = new tdVector();
        }
        this.m_nXview = new int[this.m_nVertices];
        this.m_nYview = new int[this.m_nVertices];
        this.m_bVisible = new boolean[this.m_nVertices];
        this.m_nPolygons = model.m_nFaces;
        this.m_polygon = new tdPolygon[this.m_nPolygons];
        for (int j = 0; j < this.m_nPolygons; ++j) {
            this.m_polygon[j] = new tdPolygon(model.m_face[j]);
        }
    }
    
    public void translate() {
        for (int i = 0; i < this.m_nVertices; ++i) {
            this.m_vectorWorld[i].m_dX = this.m_model.m_vectorVertex[i].m_dX + this.m_vector.m_dX;
            this.m_vectorWorld[i].m_dY = this.m_model.m_vectorVertex[i].m_dY + this.m_vector.m_dY;
            this.m_vectorWorld[i].m_dZ = this.m_model.m_vectorVertex[i].m_dZ + this.m_vector.m_dZ;
        }
    }
    
    public void rotate() {
        for (int i = 0; i < this.m_nVertices; ++i) {
            final double dx = this.m_model.m_vectorVertex[i].m_dX;
            final double dy = this.m_model.m_vectorVertex[i].m_dY;
            final double dz = this.m_model.m_vectorVertex[i].m_dZ;
            this.m_vectorWorld[i].m_dX = dx * this.m_matrix.m_dXx + dy * this.m_matrix.m_dYx + dz * this.m_matrix.m_dZx;
            this.m_vectorWorld[i].m_dY = dx * this.m_matrix.m_dXy + dy * this.m_matrix.m_dYy + dz * this.m_matrix.m_dZy;
            this.m_vectorWorld[i].m_dZ = dx * this.m_matrix.m_dXz + dy * this.m_matrix.m_dYz + dz * this.m_matrix.m_dZz;
        }
    }
}
