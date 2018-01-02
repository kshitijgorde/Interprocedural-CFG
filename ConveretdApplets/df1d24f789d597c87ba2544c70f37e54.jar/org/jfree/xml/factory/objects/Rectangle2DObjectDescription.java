// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

import java.awt.geom.Rectangle2D;

public class Rectangle2DObjectDescription extends AbstractObjectDescription
{
    static /* synthetic */ Class class$java$awt$geom$Rectangle2D;
    static /* synthetic */ Class class$java$lang$Float;
    
    public Rectangle2DObjectDescription() {
        super((Rectangle2DObjectDescription.class$java$awt$geom$Rectangle2D == null) ? (Rectangle2DObjectDescription.class$java$awt$geom$Rectangle2D = class$("java.awt.geom.Rectangle2D")) : Rectangle2DObjectDescription.class$java$awt$geom$Rectangle2D);
        this.setParameterDefinition("width", (Rectangle2DObjectDescription.class$java$lang$Float == null) ? (Rectangle2DObjectDescription.class$java$lang$Float = class$("java.lang.Float")) : Rectangle2DObjectDescription.class$java$lang$Float);
        this.setParameterDefinition("height", (Rectangle2DObjectDescription.class$java$lang$Float == null) ? (Rectangle2DObjectDescription.class$java$lang$Float = class$("java.lang.Float")) : Rectangle2DObjectDescription.class$java$lang$Float);
        this.setParameterDefinition("x", (Rectangle2DObjectDescription.class$java$lang$Float == null) ? (Rectangle2DObjectDescription.class$java$lang$Float = class$("java.lang.Float")) : Rectangle2DObjectDescription.class$java$lang$Float);
        this.setParameterDefinition("y", (Rectangle2DObjectDescription.class$java$lang$Float == null) ? (Rectangle2DObjectDescription.class$java$lang$Float = class$("java.lang.Float")) : Rectangle2DObjectDescription.class$java$lang$Float);
    }
    
    public Object createObject() {
        final Rectangle2D.Float float1 = new Rectangle2D.Float();
        float1.setRect(this.getFloatParameter("x"), this.getFloatParameter("y"), this.getFloatParameter("width"), this.getFloatParameter("height"));
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
        if (!(o instanceof Rectangle2D)) {
            throw new ObjectFactoryException("The given object is no java.awt.geom.Rectangle2D.");
        }
        final Rectangle2D rectangle2D = (Rectangle2D)o;
        final float n = (float)rectangle2D.getX();
        final float n2 = (float)rectangle2D.getY();
        final float n3 = (float)rectangle2D.getWidth();
        final float n4 = (float)rectangle2D.getHeight();
        this.setParameter("x", new Float(n));
        this.setParameter("y", new Float(n2));
        this.setParameter("width", new Float(n3));
        this.setParameter("height", new Float(n4));
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
