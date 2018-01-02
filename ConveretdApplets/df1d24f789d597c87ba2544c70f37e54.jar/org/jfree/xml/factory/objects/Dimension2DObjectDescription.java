// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

import java.awt.geom.Dimension2D;
import org.jfree.ui.FloatDimension;

public class Dimension2DObjectDescription extends AbstractObjectDescription
{
    static /* synthetic */ Class class$java$awt$geom$Dimension2D;
    static /* synthetic */ Class class$java$lang$Float;
    
    public Dimension2DObjectDescription() {
        super((Dimension2DObjectDescription.class$java$awt$geom$Dimension2D == null) ? (Dimension2DObjectDescription.class$java$awt$geom$Dimension2D = class$("java.awt.geom.Dimension2D")) : Dimension2DObjectDescription.class$java$awt$geom$Dimension2D);
        this.setParameterDefinition("width", (Dimension2DObjectDescription.class$java$lang$Float == null) ? (Dimension2DObjectDescription.class$java$lang$Float = class$("java.lang.Float")) : Dimension2DObjectDescription.class$java$lang$Float);
        this.setParameterDefinition("height", (Dimension2DObjectDescription.class$java$lang$Float == null) ? (Dimension2DObjectDescription.class$java$lang$Float = class$("java.lang.Float")) : Dimension2DObjectDescription.class$java$lang$Float);
    }
    
    public Object createObject() {
        final FloatDimension floatDimension = new FloatDimension();
        floatDimension.setSize(this.getFloatParameter("width"), this.getFloatParameter("height"));
        return floatDimension;
    }
    
    private float getFloatParameter(final String s) {
        final Float n = (Float)this.getParameter(s);
        if (n == null) {
            return 0.0f;
        }
        return n;
    }
    
    public void setParameterFromObject(final Object o) throws ObjectFactoryException {
        if (!(o instanceof Dimension2D)) {
            throw new ObjectFactoryException("The given object is no java.awt.geom.Dimension2D.");
        }
        final Dimension2D dimension2D = (Dimension2D)o;
        final float n = (float)dimension2D.getWidth();
        final float n2 = (float)dimension2D.getHeight();
        this.setParameter("width", new Float(n));
        this.setParameter("height", new Float(n2));
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
