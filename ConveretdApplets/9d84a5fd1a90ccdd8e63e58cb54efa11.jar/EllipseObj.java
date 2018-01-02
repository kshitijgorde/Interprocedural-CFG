import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class EllipseObj extends RectObj
{
    public EllipseObj(final Map parent) {
        super(parent);
    }
    
    public void draw() {
        if (super.g == null) {
            super.g = super.m_map.getGraphics();
        }
        if (super.g != null) {
            final Color colorCurrent = super.g.getColor();
            super.g.setColor(this.getColor());
            super.g.drawOval(super.x - super.width, super.y - super.height, super.width * 2, super.height * 2);
            super.g.setColor(colorCurrent);
        }
    }
}
