// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.capability;

public class DispatchClassLoader extends ClassLoader
{
    private String name;
    private byte[] code;
    
    DispatchClassLoader(final ClassLoader parent, final String name, final byte[] bytecode) {
        super(parent);
        this.name = null;
        this.code = null;
        this.name = name;
        this.code = bytecode;
    }
    
    DispatchClassLoader(final String name, final byte[] bytecode) {
        this.name = null;
        this.code = null;
        this.name = name;
        this.code = bytecode;
    }
    
    protected Class findClass(final String name) throws ClassNotFoundException {
        if (!name.equals(this.name)) {
            throw new ClassNotFoundException("Class not found: " + name + "(I'm a dispatch loader, I only know " + this.name + ")");
        }
        return this.defineClass(name, this.code, 0, this.code.length);
    }
}
