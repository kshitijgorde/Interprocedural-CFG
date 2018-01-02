// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

public class ByteObjectDescription extends AbstractObjectDescription
{
    static /* synthetic */ Class class$java$lang$Byte;
    static /* synthetic */ Class class$java$lang$String;
    
    public ByteObjectDescription() {
        super((ByteObjectDescription.class$java$lang$Byte == null) ? (ByteObjectDescription.class$java$lang$Byte = class$("java.lang.Byte")) : ByteObjectDescription.class$java$lang$Byte);
        this.setParameterDefinition("value", (ByteObjectDescription.class$java$lang$String == null) ? (ByteObjectDescription.class$java$lang$String = class$("java.lang.String")) : ByteObjectDescription.class$java$lang$String);
    }
    
    public Object createObject() {
        return Byte.valueOf((String)this.getParameter("value"));
    }
    
    public void setParameterFromObject(final Object o) throws ObjectFactoryException {
        if (!(o instanceof Byte)) {
            throw new ObjectFactoryException("The given object is no java.lang.Byte.");
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
        return Byte.TYPE.equals(abstractObjectDescription.getObjectClass()) || ((ByteObjectDescription.class$java$lang$Byte == null) ? (ByteObjectDescription.class$java$lang$Byte = class$("java.lang.Byte")) : ByteObjectDescription.class$java$lang$Byte).equals(abstractObjectDescription.getObjectClass());
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
