// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

import java.lang.reflect.Modifier;

public class ClassLoaderObjectDescription extends AbstractObjectDescription
{
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class class$java$lang$String;
    
    public ClassLoaderObjectDescription() {
        super((ClassLoaderObjectDescription.class$java$lang$Object == null) ? (ClassLoaderObjectDescription.class$java$lang$Object = class$("java.lang.Object")) : ClassLoaderObjectDescription.class$java$lang$Object);
        this.setParameterDefinition("class", (ClassLoaderObjectDescription.class$java$lang$String == null) ? (ClassLoaderObjectDescription.class$java$lang$String = class$("java.lang.String")) : ClassLoaderObjectDescription.class$java$lang$String);
    }
    
    public Object createObject() {
        try {
            return this.getClass().getClassLoader().loadClass((String)this.getParameter("class")).newInstance();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public void setParameterFromObject(final Object o) throws ObjectFactoryException {
        if (o == null) {
            throw new ObjectFactoryException("The Object is null.");
        }
        try {
            if (!Modifier.isPublic(o.getClass().getConstructor((Class<?>[])new Class[0]).getModifiers())) {
                throw new ObjectFactoryException("The given object has no public default constructor. [" + o.getClass() + "]");
            }
            this.setParameter("class", o.getClass().getName());
        }
        catch (Exception ex) {
            throw new ObjectFactoryException("The given object has no default constructor. [" + o.getClass() + "]", ex);
        }
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
