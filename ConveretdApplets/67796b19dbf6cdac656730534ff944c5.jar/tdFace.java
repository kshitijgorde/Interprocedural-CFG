import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class tdFace
{
    public int m_nVertices;
    public int[] m_ndxVertex;
    public Color m_colorFront;
    public Color m_colorBack;
    
    public tdFace() {
    }
    
    public tdFace(final int nVertices, final int[] ndxVertex, final Color colorFront, final Color colorBack) {
        this.m_nVertices = nVertices;
        this.m_ndxVertex = ndxVertex;
        this.m_colorFront = colorFront;
        this.m_colorBack = colorBack;
    }
    
    public void specifyVertex(final int n, final int n2) {
        this.m_ndxVertex[n] = n2;
    }
}
