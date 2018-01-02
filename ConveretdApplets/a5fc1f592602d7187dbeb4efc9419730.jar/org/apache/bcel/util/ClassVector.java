// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.util;

import org.apache.bcel.classfile.JavaClass;
import java.util.ArrayList;

public class ClassVector
{
    protected ArrayList vec;
    
    public ClassVector() {
        this.vec = new ArrayList();
    }
    
    public void addElement(final JavaClass clazz) {
        this.vec.add(clazz);
    }
    
    public JavaClass elementAt(final int index) {
        return this.vec.get(index);
    }
    
    public void removeElementAt(final int index) {
        this.vec.remove(index);
    }
    
    public JavaClass[] toArray() {
        final JavaClass[] classes = new JavaClass[this.vec.size()];
        this.vec.toArray(classes);
        return classes;
    }
}
