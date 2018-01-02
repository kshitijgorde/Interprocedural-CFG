// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

import org.jfree.util.Configuration;
import java.util.Iterator;
import java.util.ArrayList;

public class ClassFactoryCollector extends ClassFactoryImpl
{
    private ArrayList factories;
    
    public ClassFactoryCollector() {
        this.factories = new ArrayList();
    }
    
    public void addFactory(final ClassFactory classFactory) {
        this.factories.add(classFactory);
        if (this.getConfig() != null) {
            classFactory.configure(this.getConfig());
        }
    }
    
    public Iterator getFactories() {
        return this.factories.iterator();
    }
    
    public ObjectDescription getDescriptionForClass(final Class clazz) {
        for (int i = 0; i < this.factories.size(); ++i) {
            final ObjectDescription descriptionForClass = this.factories.get(i).getDescriptionForClass(clazz);
            if (descriptionForClass != null) {
                return descriptionForClass;
            }
        }
        return super.getDescriptionForClass(clazz);
    }
    
    public ObjectDescription getSuperClassObjectDescription(final Class clazz, ObjectDescription objectDescription) {
        for (int i = 0; i < this.factories.size(); ++i) {
            final ObjectDescription superClassObjectDescription = this.factories.get(i).getSuperClassObjectDescription(clazz, objectDescription);
            if (superClassObjectDescription != null) {
                if (objectDescription == null) {
                    objectDescription = superClassObjectDescription;
                }
                else if (this.getComparator().isComparable(objectDescription.getObjectClass(), superClassObjectDescription.getObjectClass()) && this.getComparator().compare(objectDescription.getObjectClass(), superClassObjectDescription.getObjectClass()) < 0) {
                    objectDescription = superClassObjectDescription;
                }
            }
        }
        return super.getSuperClassObjectDescription(clazz, objectDescription);
    }
    
    public Iterator getRegisteredClasses() {
        final ArrayList<Object> list = new ArrayList<Object>();
        for (int i = 0; i < this.factories.size(); ++i) {
            final Iterator registeredClasses = this.factories.get(i).getRegisteredClasses();
            while (registeredClasses.hasNext()) {
                list.add(registeredClasses.next());
            }
        }
        return list.iterator();
    }
    
    public void configure(final Configuration configuration) {
        if (this.getConfig() != null) {
            return;
        }
        super.configure(configuration);
        final Iterator<ClassFactory> iterator = this.factories.iterator();
        while (iterator.hasNext()) {
            iterator.next().configure(configuration);
        }
    }
    
    public boolean equals(final Object o) {
        return this == o || (o instanceof ClassFactoryCollector && super.equals(o) && this.factories.equals(((ClassFactoryCollector)o).factories));
    }
    
    public int hashCode() {
        return 29 * super.hashCode() + this.factories.hashCode();
    }
}
