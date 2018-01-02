// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

import java.awt.geom.Line2D;

public class Line2DObjectDescription extends AbstractObjectDescription
{
    static /* synthetic */ Class class$java$awt$geom$Line2D;
    static /* synthetic */ Class class$java$lang$Float;
    
    public Line2DObjectDescription() {
        super((Line2DObjectDescription.class$java$awt$geom$Line2D == null) ? (Line2DObjectDescription.class$java$awt$geom$Line2D = class$("java.awt.geom.Line2D")) : Line2DObjectDescription.class$java$awt$geom$Line2D);
        this.setParameterDefinition("x1", (Line2DObjectDescription.class$java$lang$Float == null) ? (Line2DObjectDescription.class$java$lang$Float = class$("java.lang.Float")) : Line2DObjectDescription.class$java$lang$Float);
        this.setParameterDefinition("x2", (Line2DObjectDescription.class$java$lang$Float == null) ? (Line2DObjectDescription.class$java$lang$Float = class$("java.lang.Float")) : Line2DObjectDescription.class$java$lang$Float);
        this.setParameterDefinition("y1", (Line2DObjectDescription.class$java$lang$Float == null) ? (Line2DObjectDescription.class$java$lang$Float = class$("java.lang.Float")) : Line2DObjectDescription.class$java$lang$Float);
        this.setParameterDefinition("y2", (Line2DObjectDescription.class$java$lang$Float == null) ? (Line2DObjectDescription.class$java$lang$Float = class$("java.lang.Float")) : Line2DObjectDescription.class$java$lang$Float);
    }
    
    public Object createObject() {
        final Line2D.Float float1 = new Line2D.Float();
        float1.setLine(this.getFloatParameter("x1"), this.getFloatParameter("y1"), this.getFloatParameter("x2"), this.getFloatParameter("y2"));
        return float1;
    }
    
    private float getFloatParameter(final String s) {
        final Float n = (Float)this.getParameter(s);
        if (n == null) {
            return 0.0f;
        }
        return n;
    }
    
    public void setParameterFromObject(final Object o) throws ObjectFactoryException {
        if (!(o instanceof Line2D)) {
            throw new ObjectFactoryException("The given object is no java.awt.geom.Line2D.");
        }
        final Line2D line2D = (Line2D)o;
        final float n = (float)line2D.getX1();
        final float n2 = (float)line2D.getX2();
        final float n3 = (float)line2D.getY1();
        final float n4 = (float)line2D.getY2();
        this.setParameter("x1", new Float(n));
        this.setParameter("x2", new Float(n2));
        this.setParameter("y1", new Float(n3));
        this.setParameter("y2", new Float(n4));
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
