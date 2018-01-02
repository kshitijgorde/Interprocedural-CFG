// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

import org.jfree.util.Log;
import org.jfree.util.ReadOnlyIterator;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import org.jfree.util.Configuration;
import java.util.HashMap;

public abstract class AbstractObjectDescription implements ObjectDescription, Cloneable
{
    private Class className;
    private HashMap parameters;
    private HashMap parameterDefs;
    private Configuration config;
    static /* synthetic */ Class class$java$lang$Boolean;
    static /* synthetic */ Class class$java$lang$Byte;
    static /* synthetic */ Class class$java$lang$Character;
    static /* synthetic */ Class class$java$lang$Short;
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$java$lang$Long;
    static /* synthetic */ Class class$java$lang$Float;
    static /* synthetic */ Class class$java$lang$Double;
    
    public AbstractObjectDescription(final Class className) {
        this.className = className;
        this.parameters = new HashMap();
        this.parameterDefs = new HashMap();
    }
    
    public Class getParameterDefinition(final String s) {
        return this.parameterDefs.get(s);
    }
    
    public void setParameterDefinition(final String s, final Class clazz) {
        if (clazz == null) {
            this.parameterDefs.remove(s);
        }
        else {
            this.parameterDefs.put(s, clazz);
        }
    }
    
    public static Class convertPrimitiveClass(final Class clazz) {
        if (!clazz.isPrimitive()) {
            return clazz;
        }
        if (clazz == Boolean.TYPE) {
            return (AbstractObjectDescription.class$java$lang$Boolean == null) ? (AbstractObjectDescription.class$java$lang$Boolean = class$("java.lang.Boolean")) : AbstractObjectDescription.class$java$lang$Boolean;
        }
        if (clazz == Byte.TYPE) {
            return (AbstractObjectDescription.class$java$lang$Byte == null) ? (AbstractObjectDescription.class$java$lang$Byte = class$("java.lang.Byte")) : AbstractObjectDescription.class$java$lang$Byte;
        }
        if (clazz == Character.TYPE) {
            return (AbstractObjectDescription.class$java$lang$Character == null) ? (AbstractObjectDescription.class$java$lang$Character = class$("java.lang.Character")) : AbstractObjectDescription.class$java$lang$Character;
        }
        if (clazz == Short.TYPE) {
            return (AbstractObjectDescription.class$java$lang$Short == null) ? (AbstractObjectDescription.class$java$lang$Short = class$("java.lang.Short")) : AbstractObjectDescription.class$java$lang$Short;
        }
        if (clazz == Integer.TYPE) {
            return (AbstractObjectDescription.class$java$lang$Integer == null) ? (AbstractObjectDescription.class$java$lang$Integer = class$("java.lang.Integer")) : AbstractObjectDescription.class$java$lang$Integer;
        }
        if (clazz == Long.TYPE) {
            return (AbstractObjectDescription.class$java$lang$Long == null) ? (AbstractObjectDescription.class$java$lang$Long = class$("java.lang.Long")) : AbstractObjectDescription.class$java$lang$Long;
        }
        if (clazz == Float.TYPE) {
            return (AbstractObjectDescription.class$java$lang$Float == null) ? (AbstractObjectDescription.class$java$lang$Float = class$("java.lang.Float")) : AbstractObjectDescription.class$java$lang$Float;
        }
        if (clazz == Double.TYPE) {
            return (AbstractObjectDescription.class$java$lang$Double == null) ? (AbstractObjectDescription.class$java$lang$Double = class$("java.lang.Double")) : AbstractObjectDescription.class$java$lang$Double;
        }
        throw new IllegalArgumentException("Class 'void' is not allowed here");
    }
    
    public void setParameter(final String s, final Object o) {
        if (this.getParameterDefinition(s) == null) {
            throw new IllegalArgumentException("No such Parameter defined: " + s + " in class " + this.getObjectClass());
        }
        final Class convertPrimitiveClass = convertPrimitiveClass(this.getParameterDefinition(s));
        if (!convertPrimitiveClass.isAssignableFrom(o.getClass())) {
            throw new ClassCastException("In Object " + this.getObjectClass() + ": Value is not assignable: " + o.getClass() + " is not assignable from " + convertPrimitiveClass);
        }
        this.parameters.put(s, o);
    }
    
    public synchronized Iterator getParameterNames() {
        final ArrayList<Comparable> list = new ArrayList<Comparable>(this.parameterDefs.keySet());
        Collections.sort(list);
        return new ReadOnlyIterator(list.iterator());
    }
    
    protected Iterator getDefinedParameterNames() {
        return new ReadOnlyIterator(this.parameters.keySet().iterator());
    }
    
    public Object getParameter(final String s) {
        return this.parameters.get(s);
    }
    
    public Class getObjectClass() {
        return this.className;
    }
    
    public ObjectDescription getInstance() {
        try {
            final AbstractObjectDescription abstractObjectDescription = (AbstractObjectDescription)super.clone();
            abstractObjectDescription.parameters = (HashMap)this.parameters.clone();
            return abstractObjectDescription;
        }
        catch (Exception ex) {
            Log.error("Should not happen: Clone Error: ", ex);
            return null;
        }
    }
    
    public ObjectDescription getUnconfiguredInstance() {
        try {
            final AbstractObjectDescription abstractObjectDescription = (AbstractObjectDescription)super.clone();
            abstractObjectDescription.parameters = (HashMap)this.parameters.clone();
            abstractObjectDescription.config = null;
            return abstractObjectDescription;
        }
        catch (Exception ex) {
            Log.error("Should not happen: Clone Error: ", ex);
            return null;
        }
    }
    
    public void configure(final Configuration config) {
        if (config == null) {
            throw new NullPointerException("The given configuration is null");
        }
        this.config = config;
    }
    
    public Configuration getConfig() {
        return this.config;
    }
    
    public boolean equals(final Object o) {
        return this == o || (o instanceof AbstractObjectDescription && this.className.equals(((AbstractObjectDescription)o).className));
    }
    
    public int hashCode() {
        return this.className.hashCode();
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
