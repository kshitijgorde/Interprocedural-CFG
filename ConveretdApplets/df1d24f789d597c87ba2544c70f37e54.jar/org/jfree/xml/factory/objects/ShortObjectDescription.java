// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

public class ShortObjectDescription extends AbstractObjectDescription
{
    static /* synthetic */ Class class$java$lang$Short;
    static /* synthetic */ Class class$java$lang$String;
    
    public ShortObjectDescription() {
        super((ShortObjectDescription.class$java$lang$Short == null) ? (ShortObjectDescription.class$java$lang$Short = class$("java.lang.Short")) : ShortObjectDescription.class$java$lang$Short);
        this.setParameterDefinition("value", (ShortObjectDescription.class$java$lang$String == null) ? (ShortObjectDescription.class$java$lang$String = class$("java.lang.String")) : ShortObjectDescription.class$java$lang$String);
    }
    
    public Object createObject() {
        return Short.valueOf((String)this.getParameter("value"));
    }
    
    public void setParameterFromObject(final Object o) throws ObjectFactoryException {
        if (!(o instanceof Short)) {
            throw new ObjectFactoryException("The given object is no java.lang.Short.");
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
        return Short.TYPE.equals(abstractObjectDescription.getObjectClass()) || ((ShortObjectDescription.class$java$lang$Short == null) ? (ShortObjectDescription.class$java$lang$Short = class$("java.lang.Short")) : ShortObjectDescription.class$java$lang$Short).equals(abstractObjectDescription.getObjectClass());
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
