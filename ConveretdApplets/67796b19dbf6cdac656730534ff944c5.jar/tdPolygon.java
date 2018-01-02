import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class tdPolygon
{
    public int m_nVertices;
    public int[] m_nX;
    public int[] m_nY;
    public Color m_color;
    public double m_dDistance;
    
    public void draw(final Graphics graphics) {
        graphics.setColor(this.m_color);
        graphics.fillPolygon(this.m_nX, this.m_nY, this.m_nVertices);
    }
    
    public tdPolygon(final tdFace tdFace) {
        this.m_nVertices = tdFace.m_nVertices;
        this.m_nX = new int[this.m_nVertices];
        this.m_nY = new int[this.m_nVertices];
    }
}
