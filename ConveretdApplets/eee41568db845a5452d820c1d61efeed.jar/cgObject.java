// 
// Decompiled by Procyon v0.5.30
// 

class cgObject
{
    public cgModel m_model;
    public cgVector m_vector;
    public cgMatrix m_matrix;
    public int m_nVertices;
    public cgVector[] m_vectorWorld;
    public int[] m_nXview;
    public int[] m_nYview;
    public cgVector[] m_vectorView;
    public boolean[] m_bVisible;
    public cgVector[] m_vectorFaceNormal;
    public int m_nPolygons;
    public cgPolygon[] m_polygon;
    public boolean m_bSortPolys;
    
    private void calculateFaceNormals() {
        for (int i = 0; i < this.m_model.m_nFaces; ++i) {
            if (this.m_model.m_face[i].m_nVertices >= 3) {
                final cgVector cgVector = this.m_vectorWorld[this.m_model.m_face[i].m_ndxVertex[0]];
                final cgVector cgVector2 = this.m_vectorWorld[this.m_model.m_face[i].m_ndxVertex[1]];
                final cgVector cgVector3 = this.m_vectorWorld[this.m_model.m_face[i].m_ndxVertex[2]];
                final double n = cgVector.m_dX - cgVector2.m_dX;
                final double n2 = cgVector.m_dY - cgVector2.m_dY;
                final double n3 = cgVector.m_dZ - cgVector2.m_dZ;
                final double n4 = cgVector3.m_dX - cgVector2.m_dX;
                final double n5 = cgVector3.m_dY - cgVector2.m_dY;
                final double n6 = cgVector3.m_dZ - cgVector2.m_dZ;
                final double n7 = n2 * n6 - n3 * n5;
                final double n8 = n3 * n4 - n * n6;
                final double n9 = n * n5 - n2 * n4;
                final double sqrt = Math.sqrt(n7 * n7 + n8 * n8 + n9 * n9);
                if (sqrt > 0.0) {
                    this.m_vectorFaceNormal[i].m_dX = n7 / sqrt;
                    this.m_vectorFaceNormal[i].m_dY = n8 / sqrt;
                    this.m_vectorFaceNormal[i].m_dZ = n9 / sqrt;
                }
            }
        }
    }
    
    public void transform() {
        for (int i = 0; i < this.m_nVertices; ++i) {
            final double dx = this.m_model.m_vectorVertex[i].m_dX;
            final double dy = this.m_model.m_vectorVertex[i].m_dY;
            final double dz = this.m_model.m_vectorVertex[i].m_dZ;
            this.m_vectorWorld[i].m_dX = dx * this.m_matrix.m_dXx + dy * this.m_matrix.m_dYx + dz * this.m_matrix.m_dZx + this.m_vector.m_dX;
            this.m_vectorWorld[i].m_dY = dx * this.m_matrix.m_dXy + dy * this.m_matrix.m_dYy + dz * this.m_matrix.m_dZy + this.m_vector.m_dY;
            this.m_vectorWorld[i].m_dZ = dx * this.m_matrix.m_dXz + dy * this.m_matrix.m_dYz + dz * this.m_matrix.m_dZz + this.m_vector.m_dZ;
        }
        this.calculateFaceNormals();
    }
    
    public cgObject(final cgModel model) {
        this.m_bSortPolys = true;
        this.m_model = model;
        this.m_vector = new cgVector();
        this.m_matrix = new cgMatrix();
        this.m_nVertices = model.m_nVertices;
        this.m_vectorWorld = new cgVector[this.m_nVertices];
        this.m_vectorView = new cgVector[this.m_nVertices];
        for (int i = 0; i < this.m_nVertices; ++i) {
            this.m_vectorWorld[i] = new cgVector();
            this.m_vectorView[i] = new cgVector();
        }
        this.m_nXview = new int[this.m_nVertices];
        this.m_nYview = new int[this.m_nVertices];
        this.m_bVisible = new boolean[this.m_nVertices];
        this.m_nPolygons = model.m_nFaces;
        if (model.m_nFaces > 0) {
            this.m_polygon = new cgPolygon[this.m_nPolygons];
            this.m_vectorFaceNormal = new cgVector[this.m_nPolygons];
        }
        else {
            this.m_polygon = null;
            this.m_vectorFaceNormal = null;
        }
        for (int j = 0; j < this.m_nPolygons; ++j) {
            this.m_polygon[j] = new cgPolygon(model.m_face[j]);
            this.m_vectorFaceNormal[j] = new cgVector();
        }
        this.m_bSortPolys = true;
    }
    
    public cgObject(final cgObject cgObject) {
        this.m_bSortPolys = true;
        this.m_model = cgObject.m_model;
        this.m_vector = cgObject.m_vector;
        this.m_matrix = cgObject.m_matrix;
        this.m_nVertices = cgObject.m_nVertices;
        this.m_vectorWorld = cgObject.m_vectorWorld;
        this.m_nXview = cgObject.m_nXview;
        this.m_nYview = cgObject.m_nYview;
        this.m_vectorView = cgObject.m_vectorView;
        this.m_bVisible = cgObject.m_bVisible;
        this.m_vectorFaceNormal = cgObject.m_vectorFaceNormal;
        this.m_nPolygons = cgObject.m_nPolygons;
        this.m_polygon = cgObject.m_polygon;
        this.m_bSortPolys = cgObject.m_bSortPolys;
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
        this.calculateFaceNormals();
    }
}
