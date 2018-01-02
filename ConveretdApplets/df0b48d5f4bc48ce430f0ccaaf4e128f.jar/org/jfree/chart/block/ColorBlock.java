// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.block;

import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.util.PaintUtilities;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.awt.Paint;

public class ColorBlock extends AbstractBlock implements Block
{
    private transient Paint paint;
    
    public ColorBlock(final Paint paint, final double width, final double height) {
        if (paint == null) {
            throw new IllegalArgumentException("Null 'paint' argument.");
        }
        this.paint = paint;
        this.setWidth(width);
        this.setHeight(height);
    }
    
    public Paint getPaint() {
        return this.paint;
    }
    
    public void draw(final Graphics2D g2, final Rectangle2D area) {
        final Rectangle2D bounds = this.getBounds();
        g2.setPaint(this.paint);
        g2.fill(bounds);
    }
    
    public Object draw(final Graphics2D g2, final Rectangle2D area, final Object params) {
        this.draw(g2, area);
        return null;
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ColorBlock)) {
            return false;
        }
        final ColorBlock that = (ColorBlock)obj;
        return PaintUtilities.equal(this.paint, that.paint) && super.equals(obj);
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writePaint(this.paint, stream);
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.paint = SerialUtilities.readPaint(stream);
    }
}
