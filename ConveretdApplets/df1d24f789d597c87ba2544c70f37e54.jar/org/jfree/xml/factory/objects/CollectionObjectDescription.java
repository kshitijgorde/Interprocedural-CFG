// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

import org.jfree.util.Log;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;

public class CollectionObjectDescription extends AbstractObjectDescription
{
    static /* synthetic */ Class class$java$util$Collection;
    static /* synthetic */ Class class$java$lang$Object;
    
    public CollectionObjectDescription(final Class clazz) {
        super(clazz);
        if (!((CollectionObjectDescription.class$java$util$Collection == null) ? (CollectionObjectDescription.class$java$util$Collection = class$("java.util.Collection")) : CollectionObjectDescription.class$java$util$Collection).isAssignableFrom(clazz)) {
            throw new ClassCastException("The given class is no Collection instance");
        }
    }
    
    private int parseParameterName(final String s) {
        try {
            return Integer.parseInt(s);
        }
        catch (Exception ex) {
            return -1;
        }
    }
    
    public Class getParameterDefinition(final String s) {
        if (s.equals("size")) {
            return Integer.TYPE;
        }
        if (this.parseParameterName(s) < 0) {
            return null;
        }
        return (CollectionObjectDescription.class$java$lang$Object == null) ? (CollectionObjectDescription.class$java$lang$Object = class$("java.lang.Object")) : CollectionObjectDescription.class$java$lang$Object;
    }
    
    public Iterator getParameterNames() {
        final Integer n = (Integer)this.getParameter("size");
        if (n == null) {
            return this.getDefinedParameterNames();
        }
        final ArrayList<String> list = new ArrayList<String>();
        list.add("size");
        for (int i = 0; i < n; ++i) {
            list.add(String.valueOf(i));
        }
        return list.iterator();
    }
    
    public Object createObject() {
        try {
            final Collection<Object> collection = this.getObjectClass().newInstance();
            for (int n = 0; this.getParameterDefinition(String.valueOf(n)) != null; ++n) {
                final Object parameter = this.getParameter(String.valueOf(n));
                if (parameter == null) {
                    break;
                }
                collection.add(parameter);
            }
            return collection;
        }
        catch (Exception ex) {
            Log.warn("Unable to instantiate Object", ex);
            return null;
        }
    }
    
    public void setParameterFromObject(final Object o) throws ObjectFactoryException {
        if (o == null) {
            throw new NullPointerException("Given object is null");
        }
        final Class objectClass = this.getObjectClass();
        if (!objectClass.isInstance(o)) {
            throw new ObjectFactoryException("Object is no instance of " + objectClass + "(is " + o.getClass() + ")");
        }
        final Iterator<Object> iterator = ((Collection)o).iterator();
        int n = 0;
        while (iterator.hasNext()) {
            this.setParameter(String.valueOf(n), iterator.next());
            ++n;
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
