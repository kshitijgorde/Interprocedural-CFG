// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

public class DoubleObjectDescription extends AbstractObjectDescription
{
    static /* synthetic */ Class class$java$lang$Double;
    static /* synthetic */ Class class$java$lang$String;
    
    public DoubleObjectDescription() {
        super((DoubleObjectDescription.class$java$lang$Double == null) ? (DoubleObjectDescription.class$java$lang$Double = class$("java.lang.Double")) : DoubleObjectDescription.class$java$lang$Double);
        this.setParameterDefinition("value", (DoubleObjectDescription.class$java$lang$String == null) ? (DoubleObjectDescription.class$java$lang$String = class$("java.lang.String")) : DoubleObjectDescription.class$java$lang$String);
    }
    
    public Object createObject() {
        return Double.valueOf((String)this.getParameter("value"));
    }
    
    public void setParameterFromObject(final Object o) throws ObjectFactoryException {
        if (!(o instanceof Double)) {
            throw new ObjectFactoryException("The given object is no java.lang.Double.");
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
        return Double.TYPE.equals(abstractObjectDescription.getObjectClass()) || ((DoubleObjectDescription.class$java$lang$Double == null) ? (DoubleObjectDescription.class$java$lang$Double = class$("java.lang.Double")) : DoubleObjectDescription.class$java$lang$Double).equals(abstractObjectDescription.getObjectClass());
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
