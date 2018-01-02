import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class tdModel
{
    public int m_nVertices;
    public int m_nFaces;
    public tdVector[] m_vectorVertex;
    public tdFace[] m_face;
    
    public tdModel(final int nVertices, final int nFaces) {
        this.m_nVertices = nVertices;
        this.m_nFaces = nFaces;
        this.m_vectorVertex = new tdVector[nVertices];
        this.m_face = new tdFace[nFaces];
    }
    
    public tdModel(final tdObject[] array, final int n) {
        this.m_nVertices = 0;
        this.m_nFaces = 0;
        for (int i = 0; i < n; ++i) {
            array[i].transform();
            this.m_nVertices += array[i].m_nVertices;
            this.m_nFaces += array[i].m_model.m_nFaces;
        }
        this.m_vectorVertex = new tdVector[this.m_nVertices];
        this.m_face = new tdFace[this.m_nFaces];
        int n2 = 0;
        int n3 = 0;
        for (final tdObject tdObject : array) {
            final tdModel model = tdObject.m_model;
            for (int k = 0; k < tdObject.m_nVertices; ++k) {
                this.m_vectorVertex[k + n2] = new tdVector();
                this.m_vectorVertex[k + n2].m_dX = tdObject.m_vectorWorld[k].m_dX;
                this.m_vectorVertex[k + n2].m_dY = tdObject.m_vectorWorld[k].m_dY;
                this.m_vectorVertex[k + n2].m_dZ = tdObject.m_vectorWorld[k].m_dZ;
            }
            for (int l = 0; l < model.m_nFaces; ++l) {
                final tdFace tdFace = model.m_face[l];
                final tdFace tdFace2 = new tdFace();
                tdFace2.m_nVertices = tdFace.m_nVertices;
                tdFace2.m_ndxVertex = new int[tdFace.m_nVertices];
                for (int n4 = 0; n4 < tdFace.m_nVertices; ++n4) {
                    tdFace2.m_ndxVertex[n4] = tdFace.m_ndxVertex[n4] + n2;
                }
                tdFace2.m_colorFront = new Color(tdFace.m_colorFront.getRGB());
                if (tdFace.m_colorBack == null) {
                    tdFace2.m_colorBack = null;
                }
                else {
                    tdFace2.m_colorBack = new Color(tdFace.m_colorBack.getRGB());
                }
                this.m_face[l + n3] = tdFace2;
            }
            n2 += tdObject.m_nVertices;
            n3 += model.m_nFaces;
        }
    }
    
    public void specifyFace(final int n, final tdFace tdFace) {
        this.m_face[n] = tdFace;
    }
    
    public void specifyVertex(final int n, final tdVector tdVector) {
        this.m_vectorVertex[n] = tdVector;
    }
}
