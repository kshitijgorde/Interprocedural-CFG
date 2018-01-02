// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

import java.awt.geom.Point2D;

public class Point2DObjectDescription extends AbstractObjectDescription
{
    static /* synthetic */ Class class$java$awt$geom$Point2D;
    static /* synthetic */ Class class$java$lang$Float;
    
    public Point2DObjectDescription() {
        super((Point2DObjectDescription.class$java$awt$geom$Point2D == null) ? (Point2DObjectDescription.class$java$awt$geom$Point2D = class$("java.awt.geom.Point2D")) : Point2DObjectDescription.class$java$awt$geom$Point2D);
        this.setParameterDefinition("x", (Point2DObjectDescription.class$java$lang$Float == null) ? (Point2DObjectDescription.class$java$lang$Float = class$("java.lang.Float")) : Point2DObjectDescription.class$java$lang$Float);
        this.setParameterDefinition("y", (Point2DObjectDescription.class$java$lang$Float == null) ? (Point2DObjectDescription.class$java$lang$Float = class$("java.lang.Float")) : Point2DObjectDescription.class$java$lang$Float);
    }
    
    public Object createObject() {
        final Point2D.Float float1 = new Point2D.Float();
        float1.setLocation(this.getFloatParameter("x"), this.getFloatParameter("y"));
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
        if (!(o instanceof Point2D)) {
            throw new ObjectFactoryException("The given object is no java.awt.geom.Point2D.");
        }
        final Point2D point2D = (Point2D)o;
        final float n = (float)point2D.getX();
        final float n2 = (float)point2D.getY();
        this.setParameter("x", new Float(n));
        this.setParameter("y", new Float(n2));
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
