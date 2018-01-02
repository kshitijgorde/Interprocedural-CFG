// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

public class IntegerObjectDescription extends AbstractObjectDescription
{
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$java$lang$String;
    
    public IntegerObjectDescription() {
        super((IntegerObjectDescription.class$java$lang$Integer == null) ? (IntegerObjectDescription.class$java$lang$Integer = class$("java.lang.Integer")) : IntegerObjectDescription.class$java$lang$Integer);
        this.setParameterDefinition("value", (IntegerObjectDescription.class$java$lang$String == null) ? (IntegerObjectDescription.class$java$lang$String = class$("java.lang.String")) : IntegerObjectDescription.class$java$lang$String);
    }
    
    public Object createObject() {
        return Integer.valueOf((String)this.getParameter("value"));
    }
    
    public void setParameterFromObject(final Object o) throws ObjectFactoryException {
        if (!(o instanceof Integer)) {
            throw new ObjectFactoryException("The given object is no java.lang.Integer.");
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
        return Integer.TYPE.equals(abstractObjectDescription.getObjectClass()) || ((IntegerObjectDescription.class$java$lang$Integer == null) ? (IntegerObjectDescription.class$java$lang$Integer = class$("java.lang.Integer")) : IntegerObjectDescription.class$java$lang$Integer).equals(abstractObjectDescription.getObjectClass());
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
