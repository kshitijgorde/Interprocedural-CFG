// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

public class LongObjectDescription extends AbstractObjectDescription
{
    static /* synthetic */ Class class$java$lang$Long;
    static /* synthetic */ Class class$java$lang$String;
    
    public LongObjectDescription() {
        super((LongObjectDescription.class$java$lang$Long == null) ? (LongObjectDescription.class$java$lang$Long = class$("java.lang.Long")) : LongObjectDescription.class$java$lang$Long);
        this.setParameterDefinition("value", (LongObjectDescription.class$java$lang$String == null) ? (LongObjectDescription.class$java$lang$String = class$("java.lang.String")) : LongObjectDescription.class$java$lang$String);
    }
    
    public Object createObject() {
        return Long.valueOf((String)this.getParameter("value"));
    }
    
    public void setParameterFromObject(final Object o) throws ObjectFactoryException {
        if (!(o instanceof Long)) {
            throw new ObjectFactoryException("The given object is no java.lang.Long.");
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
        return Long.TYPE.equals(abstractObjectDescription.getObjectClass()) || ((LongObjectDescription.class$java$lang$Long == null) ? (LongObjectDescription.class$java$lang$Long = class$("java.lang.Long")) : LongObjectDescription.class$java$lang$Long).equals(abstractObjectDescription.getObjectClass());
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
