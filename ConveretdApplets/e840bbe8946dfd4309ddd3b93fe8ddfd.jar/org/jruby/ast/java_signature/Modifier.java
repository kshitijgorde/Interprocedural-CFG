// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast.java_signature;

public enum Modifier
{
    PUBLIC("public"), 
    PROTECTED("protected"), 
    PRIVATE("private"), 
    STATIC("static"), 
    ABSTRACT("abstract"), 
    FINAL("final"), 
    NATIVE("native"), 
    SYNCHRONIZED("synchronized"), 
    TRANSIENT("transient"), 
    VOLATILE("volatile"), 
    STRICTFP("strictfp");
    
    private String name;
    
    private Modifier(final String name) {
        this.name = name;
    }
    
    public String toString() {
        return this.name;
    }
}
