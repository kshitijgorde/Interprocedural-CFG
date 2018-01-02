// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.util;

import com.ibm.xslt4j.bcel.classfile.JavaClass;
import java.util.Stack;

public class ClassStack
{
    private Stack stack;
    
    public ClassStack() {
        this.stack = new Stack();
    }
    
    public void push(final JavaClass clazz) {
        this.stack.push(clazz);
    }
    
    public JavaClass pop() {
        return this.stack.pop();
    }
    
    public JavaClass top() {
        return this.stack.peek();
    }
    
    public boolean empty() {
        return this.stack.empty();
    }
}
