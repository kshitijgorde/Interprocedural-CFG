// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

public class FloatObjectDescription extends AbstractObjectDescription
{
    static /* synthetic */ Class class$java$lang$Float;
    static /* synthetic */ Class class$java$lang$String;
    
    public FloatObjectDescription() {
        super((FloatObjectDescription.class$java$lang$Float == null) ? (FloatObjectDescription.class$java$lang$Float = class$("java.lang.Float")) : FloatObjectDescription.class$java$lang$Float);
        this.setParameterDefinition("value", (FloatObjectDescription.class$java$lang$String == null) ? (FloatObjectDescription.class$java$lang$String = class$("java.lang.String")) : FloatObjectDescription.class$java$lang$String);
    }
    
    public Object createObject() {
        return Float.valueOf((String)this.getParameter("value"));
    }
    
    public void setParameterFromObject(final Object o) throws ObjectFactoryException {
        if (!(o instanceof Float)) {
            throw new ObjectFactoryException("The given object is no java.lang.Float.");
        }
        this.setParameter("value", String.valueOf(o));
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractObjectDescription)) {
            return false;
        }
        final AbstractObjectDescription abstractObjectDescription = (AbstractObjectDescription)o;
        return Float.TYPE.equals(abstractObjectDescription.getObjectClass()) || ((FloatObjectDescription.class$java$lang$Float == null) ? (FloatObjectDescription.class$java$lang$Float = class$("java.lang.Float")) : FloatObjectDescription.class$java$lang$Float).equals(abstractObjectDescription.getObjectClass());
    }
    
    public int hashCode() {
        return this.getObjectClass().hashCode();
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
