import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class cgPolygon
{
    public int m_nVertices;
    public int[] m_nX;
    public int[] m_nY;
    public Color m_color;
    public double m_dDistance;
    
    public void draw(final Graphics graphics) {
        graphics.setColor(this.m_color);
        if (this.m_nVertices >= 3) {
            graphics.fillPolygon(this.m_nX, this.m_nY, this.m_nVertices);
            return;
        }
        graphics.drawLine(this.m_nX[0], this.m_nY[0], this.m_nX[1], this.m_nY[1]);
    }
    
    public cgPolygon(final cgFace cgFace) {
        this.m_nVertices = cgFace.m_nVertices;
        this.m_nX = new int[this.m_nVertices];
        this.m_nY = new int[this.m_nVertices];
    }
}
