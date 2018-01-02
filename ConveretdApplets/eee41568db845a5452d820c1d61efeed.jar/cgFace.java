import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class cgFace
{
    public final int FRONT_FULL_BRIGHTNESS = 1;
    public final int BACK_FULL_BRIGHTNESS = 2;
    public final int ANIMATED = 4;
    public int m_nVertices;
    public int[] m_ndxVertex;
    public Color m_colorFront;
    public Color m_colorBack;
    public int m_nFlags;
    public int m_nDistanceVertex;
    
    public cgFace() {
        this.m_nDistanceVertex = -1;
    }
    
    public cgFace(final int nVertices, final int[] ndxVertex, final Color colorFront, final Color colorBack) {
        this.m_nDistanceVertex = -1;
        this.m_nVertices = nVertices;
        this.m_ndxVertex = ndxVertex;
        this.m_colorFront = colorFront;
        this.m_colorBack = colorBack;
        this.m_nFlags = 0;
        this.m_nDistanceVertex = -1;
    }
    
    public cgFace(final int nVertices, final int[] ndxVertex, final Color colorFront, final Color colorBack, final int nDistanceVertex) {
        this.m_nDistanceVertex = -1;
        this.m_nVertices = nVertices;
        this.m_ndxVertex = ndxVertex;
        this.m_colorFront = colorFront;
        this.m_colorBack = colorBack;
        this.m_nFlags = 0;
        this.m_nDistanceVertex = nDistanceVertex;
    }
    
    public cgFace(final int nVertices, final int[] ndxVertex, final Color colorFront, final Color colorBack, final boolean b, final boolean b2) {
        this.m_nDistanceVertex = -1;
        this.m_nVertices = nVertices;
        this.m_ndxVertex = ndxVertex;
        this.m_colorFront = colorFront;
        this.m_colorBack = colorBack;
        this.m_nFlags = 0;
        this.m_nDistanceVertex = -1;
        if (b) {
            this.m_nFlags |= 0x1;
        }
        if (b2) {
            this.m_nFlags |= 0x2;
        }
    }
    
    public void specifyVertex(final int n, final int n2) {
        this.m_ndxVertex[n] = n2;
    }
}
