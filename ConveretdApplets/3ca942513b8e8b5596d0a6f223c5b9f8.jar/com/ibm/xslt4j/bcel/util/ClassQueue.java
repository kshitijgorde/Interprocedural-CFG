// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.util;

import com.ibm.xslt4j.bcel.classfile.JavaClass;
import java.util.ArrayList;

public class ClassQueue
{
    protected int left;
    private ArrayList vec;
    
    public ClassQueue() {
        this.left = 0;
        this.vec = new ArrayList();
    }
    
    public void enqueue(final JavaClass clazz) {
        this.vec.add(clazz);
    }
    
    public JavaClass dequeue() {
        final JavaClass clazz = this.vec.get(this.left);
        this.vec.remove(this.left++);
        return clazz;
    }
    
    public boolean empty() {
        return this.vec.size() <= this.left;
    }
}
