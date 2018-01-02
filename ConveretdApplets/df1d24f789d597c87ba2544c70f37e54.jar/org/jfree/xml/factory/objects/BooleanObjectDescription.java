// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

public class BooleanObjectDescription extends AbstractObjectDescription
{
    static /* synthetic */ Class class$java$lang$Boolean;
    static /* synthetic */ Class class$java$lang$String;
    
    public BooleanObjectDescription() {
        super((BooleanObjectDescription.class$java$lang$Boolean == null) ? (BooleanObjectDescription.class$java$lang$Boolean = class$("java.lang.Boolean")) : BooleanObjectDescription.class$java$lang$Boolean);
        this.setParameterDefinition("value", (BooleanObjectDescription.class$java$lang$String == null) ? (BooleanObjectDescription.class$java$lang$String = class$("java.lang.String")) : BooleanObjectDescription.class$java$lang$String);
    }
    
    public Object createObject() {
        return Boolean.valueOf((String)this.getParameter("value"));
    }
    
    public void setParameterFromObject(final Object o) throws ObjectFactoryException {
        if (!(o instanceof Boolean)) {
            throw new ObjectFactoryException("The given object is no java.lang.Boolean. ");
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
        return Boolean.TYPE.equals(abstractObjectDescription.getObjectClass()) || ((BooleanObjectDescription.class$java$lang$Boolean == null) ? (BooleanObjectDescription.class$java$lang$Boolean = class$("java.lang.Boolean")) : BooleanObjectDescription.class$java$lang$Boolean).equals(abstractObjectDescription.getObjectClass());
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
