// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

import org.jfree.util.Configuration;
import java.util.ArrayList;
import java.util.Iterator;

public class ArrayClassFactory implements ClassFactory
{
    static /* synthetic */ Class array$Ljava$lang$Object;
    
    public ObjectDescription getDescriptionForClass(final Class clazz) {
        if (!clazz.isArray()) {
            return null;
        }
        return new ArrayObjectDescription(clazz);
    }
    
    public ObjectDescription getSuperClassObjectDescription(final Class clazz, final ObjectDescription objectDescription) {
        return null;
    }
    
    public Iterator getRegisteredClasses() {
        final ArrayList<Class> list = new ArrayList<Class>();
        list.add((ArrayClassFactory.array$Ljava$lang$Object == null) ? (ArrayClassFactory.array$Ljava$lang$Object = class$("[Ljava.lang.Object;")) : ArrayClassFactory.array$Ljava$lang$Object);
        return list.iterator();
    }
    
    public void configure(final Configuration configuration) {
    }
    
    public boolean equals(final Object o) {
        return this == o || o instanceof ArrayClassFactory;
    }
    
    public int hashCode() {
        return this.getClass().hashCode();
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
