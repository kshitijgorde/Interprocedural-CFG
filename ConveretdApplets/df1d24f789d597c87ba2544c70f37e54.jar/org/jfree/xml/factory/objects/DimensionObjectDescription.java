// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

import java.awt.Dimension;

public class DimensionObjectDescription extends AbstractObjectDescription
{
    static /* synthetic */ Class class$java$awt$Dimension;
    static /* synthetic */ Class class$java$lang$Float;
    
    public DimensionObjectDescription() {
        super((DimensionObjectDescription.class$java$awt$Dimension == null) ? (DimensionObjectDescription.class$java$awt$Dimension = class$("java.awt.Dimension")) : DimensionObjectDescription.class$java$awt$Dimension);
        this.setParameterDefinition("width", (DimensionObjectDescription.class$java$lang$Float == null) ? (DimensionObjectDescription.class$java$lang$Float = class$("java.lang.Float")) : DimensionObjectDescription.class$java$lang$Float);
        this.setParameterDefinition("height", (DimensionObjectDescription.class$java$lang$Float == null) ? (DimensionObjectDescription.class$java$lang$Float = class$("java.lang.Float")) : DimensionObjectDescription.class$java$lang$Float);
    }
    
    public Object createObject() {
        final Dimension dimension = new Dimension();
        dimension.setSize(this.getFloatParameter("width"), this.getFloatParameter("height"));
        return dimension;
    }
    
    private float getFloatParameter(final String s) {
        final Float n = (Float)this.getParameter(s);
        if (n == null) {
            return 0.0f;
        }
        return n;
    }
    
    public void setParameterFromObject(final Object o) throws ObjectFactoryException {
        if (!(o instanceof Dimension)) {
            throw new ObjectFactoryException("The given object is no java.awt.geom.Dimension2D.");
        }
        final Dimension dimension = (Dimension)o;
        final float n = (float)dimension.getWidth();
        final float n2 = (float)dimension.getHeight();
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
