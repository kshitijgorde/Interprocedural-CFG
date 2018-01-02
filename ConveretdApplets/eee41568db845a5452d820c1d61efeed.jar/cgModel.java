import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class cgModel
{
    public int m_nVertices;
    public int m_nFaces;
    public cgVector[] m_vectorVertex;
    public cgFace[] m_face;
    
    public cgModel(final int nVertices, final int nFaces) {
        this.m_nVertices = nVertices;
        this.m_vectorVertex = new cgVector[nVertices];
        this.m_nFaces = nFaces;
        if (nFaces > 0) {
            this.m_face = new cgFace[nFaces];
            return;
        }
        this.m_face = null;
    }
    
    public cgModel(final cgObject[] array, final int n) {
        this.m_nVertices = 0;
        this.m_nFaces = 0;
        for (int i = 0; i < n; ++i) {
            array[i].transform();
            this.m_nVertices += array[i].m_nVertices;
            this.m_nFaces += array[i].m_model.m_nFaces;
        }
        this.m_vectorVertex = new cgVector[this.m_nVertices];
        this.m_face = new cgFace[this.m_nFaces];
        int n2 = 0;
        int n3 = 0;
        for (final cgObject cgObject : array) {
            final cgModel model = cgObject.m_model;
            for (int k = 0; k < cgObject.m_nVertices; ++k) {
                this.m_vectorVertex[k + n2] = new cgVector();
                this.m_vectorVertex[k + n2].m_dX = cgObject.m_vectorWorld[k].m_dX;
                this.m_vectorVertex[k + n2].m_dY = cgObject.m_vectorWorld[k].m_dY;
                this.m_vectorVertex[k + n2].m_dZ = cgObject.m_vectorWorld[k].m_dZ;
            }
            for (int l = 0; l < model.m_nFaces; ++l) {
                final cgFace cgFace = model.m_face[l];
                final cgFace cgFace2 = new cgFace();
                cgFace2.m_nVertices = cgFace.m_nVertices;
                cgFace2.m_ndxVertex = new int[cgFace.m_nVertices];
                for (int n4 = 0; n4 < cgFace.m_nVertices; ++n4) {
                    cgFace2.m_ndxVertex[n4] = cgFace.m_ndxVertex[n4] + n2;
                }
                cgFace2.m_colorFront = new Color(cgFace.m_colorFront.getRGB());
                if (cgFace.m_colorBack == null) {
                    cgFace2.m_colorBack = null;
                }
                else {
                    cgFace2.m_colorBack = new Color(cgFace.m_colorBack.getRGB());
                }
                this.m_face[l + n3] = cgFace2;
            }
            n2 += cgObject.m_nVertices;
            n3 += model.m_nFaces;
        }
    }
    
    public void specifyFace(final int n, final cgFace cgFace) {
        this.m_face[n] = cgFace;
    }
    
    public void specifyVertex(final int n, final cgVector cgVector) {
        this.m_vectorVertex[n] = cgVector;
    }
}
