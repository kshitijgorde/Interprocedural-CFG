// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.collision;

import org.jbox2d.common.XForm;
import java.util.ArrayList;
import org.jbox2d.common.Vec2;
import java.util.List;

public class PolygonDef extends ShapeDef
{
    public List<Vec2> vertices;
    
    public PolygonDef() {
        this.type = ShapeType.POLYGON_SHAPE;
        this.vertices = new ArrayList<Vec2>();
    }
    
    public void setAsBox(final float hx, final float hy) {
        this.vertices.clear();
        this.vertices.add(new Vec2(-hx, -hy));
        this.vertices.add(new Vec2(hx, -hy));
        this.vertices.add(new Vec2(hx, hy));
        this.vertices.add(new Vec2(-hx, hy));
    }
    
    public void setAsBox(final float hx, final float hy, final Vec2 center, final float angle) {
        this.setAsBox(hx, hy);
        final XForm xf = new XForm();
        xf.position.set(center);
        xf.R.set(angle);
        for (int i = 0; i < this.vertices.size(); ++i) {
            this.vertices.get(i).set(XForm.mul(xf, this.vertices.get(i)));
        }
    }
    
    public int vertexCount() {
        return this.vertices.size();
    }
}
