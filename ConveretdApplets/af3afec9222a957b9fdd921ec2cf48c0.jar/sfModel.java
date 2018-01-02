// 
// Decompiled by Procyon v0.5.30
// 

class sfModel
{
    public int m_nVertices;
    public int m_nFaces;
    public sfVector[] m_vectorVertex;
    public sfFace[] m_face;
    
    public sfModel(final int nVertices, final int nFaces) {
        this.m_nVertices = nVertices;
        this.m_nFaces = nFaces;
        this.m_vectorVertex = new sfVector[nVertices];
        this.m_face = new sfFace[nFaces];
    }
    
    public void specifyFace(final int n, final sfFace sfFace) {
        this.m_face[n] = sfFace;
    }
    
    public void specifyVertex(final int n, final sfVector sfVector) {
        this.m_vectorVertex[n] = sfVector;
    }
}
