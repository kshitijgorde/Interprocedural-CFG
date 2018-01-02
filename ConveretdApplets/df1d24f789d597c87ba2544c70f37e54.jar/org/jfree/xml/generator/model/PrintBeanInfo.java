// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.generator.model;

import java.beans.PropertyDescriptor;
import java.beans.IntrospectionException;
import java.beans.IndexedPropertyDescriptor;
import java.beans.Introspector;

public class PrintBeanInfo
{
    public static void print(final Class clazz) {
        try {
            System.out.println("Class: " + clazz.getName());
            System.out.println("========================================================================");
            final PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clazz, clazz.getSuperclass()).getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; ++i) {
                System.out.println("Property: " + propertyDescriptors[i].getDisplayName());
                System.out.println("---------------------------------------------------------------------");
                System.out.println(" ( " + propertyDescriptors[i].getShortDescription() + ")");
                if (propertyDescriptors[i] instanceof IndexedPropertyDescriptor) {
                    final IndexedPropertyDescriptor indexedPropertyDescriptor = (IndexedPropertyDescriptor)propertyDescriptors[i];
                    System.out.println("  - idx-type   : " + indexedPropertyDescriptor.getIndexedPropertyType());
                    System.out.println("  - idx-read   : " + indexedPropertyDescriptor.getIndexedReadMethod());
                    System.out.println("  - idx-write  : " + indexedPropertyDescriptor.getIndexedWriteMethod());
                }
                else {
                    System.out.println("  - type       : " + propertyDescriptors[i].getPropertyType());
                    System.out.println("  - read       : " + propertyDescriptors[i].getReadMethod());
                    System.out.println("  - write      : " + propertyDescriptors[i].getWriteMethod());
                }
                System.out.println("  - bound      : " + propertyDescriptors[i].isBound());
                System.out.println("  - constrained: " + propertyDescriptors[i].isConstrained());
            }
        }
        catch (IntrospectionException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(final String[] array) throws Exception {
        for (int i = 0; i < array.length; ++i) {
            print(Class.forName(array[i]));
        }
    }
}
