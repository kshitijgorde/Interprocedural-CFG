// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

public class StringObjectDescription extends AbstractObjectDescription
{
    static /* synthetic */ Class class$java$lang$String;
    
    public StringObjectDescription() {
        super((StringObjectDescription.class$java$lang$String == null) ? (StringObjectDescription.class$java$lang$String = class$("java.lang.String")) : StringObjectDescription.class$java$lang$String);
        this.setParameterDefinition("value", (StringObjectDescription.class$java$lang$String == null) ? (StringObjectDescription.class$java$lang$String = class$("java.lang.String")) : StringObjectDescription.class$java$lang$String);
    }
    
    public Object createObject() {
        return String.valueOf(this.getParameter("value"));
    }
    
    public void setParameterFromObject(final Object o) throws ObjectFactoryException {
        if (!(o instanceof String)) {
            throw new ObjectFactoryException("The given object is no java.lang.String.");
        }
        this.setParameter("value", String.valueOf(o));
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
