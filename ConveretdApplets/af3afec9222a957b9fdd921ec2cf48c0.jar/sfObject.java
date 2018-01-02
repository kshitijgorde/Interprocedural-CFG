// 
// Decompiled by Procyon v0.5.30
// 

class sfObject
{
    public sfModel m_model;
    public sfVector m_vector;
    public sfMatrix m_matrix;
    public int m_nVertices;
    public sfVector[] m_vectorWorld;
    public int[] m_nXview;
    public int[] m_nYview;
    public sfVector[] m_vectorView;
    public boolean[] m_bVisible;
    public int m_nPolygons;
    public sfPolygon[] m_polygon;
    
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
    
    public sfObject(final sfModel model) {
        this.m_model = model;
        this.m_vector = new sfVector();
        this.m_matrix = new sfMatrix();
        this.m_nVertices = model.m_nVertices;
        this.m_vectorWorld = new sfVector[this.m_nVertices];
        this.m_vectorView = new sfVector[this.m_nVertices];
        for (int i = 0; i < this.m_nVertices; ++i) {
            this.m_vectorWorld[i] = new sfVector();
            this.m_vectorView[i] = new sfVector();
        }
        this.m_nXview = new int[this.m_nVertices];
        this.m_nYview = new int[this.m_nVertices];
        this.m_bVisible = new boolean[this.m_nVertices];
        this.m_nPolygons = model.m_nFaces;
        this.m_polygon = new sfPolygon[this.m_nPolygons];
        for (int j = 0; j < this.m_nPolygons; ++j) {
            this.m_polygon[j] = new sfPolygon(model.m_face[j]);
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
