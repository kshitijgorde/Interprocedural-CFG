// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

import java.util.Iterator;
import org.jfree.util.Log;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ArrayObjectDescription extends AbstractObjectDescription
{
    static /* synthetic */ Class class$java$lang$Object;
    
    public ArrayObjectDescription(final Class clazz) {
        super(clazz);
        if (!clazz.isArray()) {
            throw new IllegalArgumentException("Need an array class");
        }
    }
    
    public Object createObject() {
        try {
            final Integer n = (Integer)this.getParameter("size");
            if (n == null) {
                final ArrayList list = new ArrayList<Object>();
                for (int n2 = 0; this.getParameterDefinition(String.valueOf(n2)) != null; ++n2) {
                    final Object parameter = this.getParameter(String.valueOf(n2));
                    if (parameter == null) {
                        break;
                    }
                    list.add(parameter);
                }
                final Object instance = Array.newInstance(this.getObjectClass().getComponentType(), list.size());
                for (int i = 0; i < list.size(); ++i) {
                    Array.set(instance, i, list.get(i));
                }
                return instance;
            }
            final Object instance2 = Array.newInstance(this.getObjectClass().getComponentType(), (int)n);
            for (int j = 0; j < n; ++j) {
                Array.set(instance2, j, this.getParameter(String.valueOf(j)));
            }
            return instance2;
        }
        catch (Exception ex) {
            Log.warn("Unable to instantiate Object", ex);
            return null;
        }
    }
    
    public void setParameterFromObject(final Object o) throws ObjectFactoryException {
        if (o == null) {
            throw new ObjectFactoryException("Given object is null.");
        }
        if (!o.getClass().isArray()) {
            throw new ObjectFactoryException("Given object is no array");
        }
        if (!this.getObjectClass().isAssignableFrom(o.getClass())) {
            throw new ObjectFactoryException("Given object is incompatible with base class");
        }
        final int length = Array.getLength(o);
        this.setParameter("size", new Integer(length));
        for (int i = 0; i < length; ++i) {
            this.setParameter(String.valueOf(i), Array.get(o, i));
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
        return (ArrayObjectDescription.class$java$lang$Object == null) ? (ArrayObjectDescription.class$java$lang$Object = class$("java.lang.Object")) : ArrayObjectDescription.class$java$lang$Object;
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
    
    public ObjectDescription getInstance() {
        return new ArrayObjectDescription(this.getObjectClass());
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
