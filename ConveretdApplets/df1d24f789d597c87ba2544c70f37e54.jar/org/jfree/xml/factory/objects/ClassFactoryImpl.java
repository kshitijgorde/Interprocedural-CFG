// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

import java.util.Iterator;
import org.jfree.util.Configuration;
import java.util.HashMap;

public abstract class ClassFactoryImpl implements ClassFactory
{
    private HashMap classes;
    private ClassComparator comparator;
    private Configuration config;
    
    public ClassFactoryImpl() {
        this.classes = new HashMap();
        this.comparator = new ClassComparator();
    }
    
    public ClassComparator getComparator() {
        return this.comparator;
    }
    
    public ObjectDescription getDescriptionForClass(final Class clazz) {
        final ObjectDescription objectDescription = this.classes.get(clazz);
        if (objectDescription == null) {
            return null;
        }
        return objectDescription.getInstance();
    }
    
    public ObjectDescription getSuperClassObjectDescription(final Class clazz, ObjectDescription objectDescription) {
        for (final Class clazz2 : this.classes.keySet()) {
            if (clazz2.isAssignableFrom(clazz)) {
                final ObjectDescription objectDescription2 = this.classes.get(clazz2);
                if (objectDescription == null) {
                    objectDescription = objectDescription2;
                }
                else {
                    if (!this.comparator.isComparable(objectDescription.getObjectClass(), objectDescription2.getObjectClass()) || this.comparator.compare(objectDescription.getObjectClass(), objectDescription2.getObjectClass()) >= 0) {
                        continue;
                    }
                    objectDescription = objectDescription2;
                }
            }
        }
        if (objectDescription == null) {
            return null;
        }
        return objectDescription.getInstance();
    }
    
    protected void registerClass(final Class clazz, final ObjectDescription objectDescription) {
        this.classes.put(clazz, objectDescription);
        if (this.config != null) {
            objectDescription.configure(this.config);
        }
    }
    
    public Iterator getRegisteredClasses() {
        return this.classes.keySet().iterator();
    }
    
    public void configure(final Configuration config) {
        if (config == null) {
            throw new NullPointerException("The given configuration is null");
        }
        if (this.config != null) {
            return;
        }
        this.config = config;
        final Iterator<ObjectDescription> iterator = this.classes.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().configure(config);
        }
    }
    
    public Configuration getConfig() {
        return this.config;
    }
    
    public boolean equals(final Object o) {
        return this == o || (o instanceof ClassFactoryImpl && this.classes.equals(((ClassFactoryImpl)o).classes));
    }
    
    public int hashCode() {
        return this.classes.hashCode();
    }
}
