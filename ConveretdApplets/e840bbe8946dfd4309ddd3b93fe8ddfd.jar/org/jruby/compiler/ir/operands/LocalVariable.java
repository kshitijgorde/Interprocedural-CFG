// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.interpreter.InterpreterContext;

public class LocalVariable extends Variable
{
    public final String name;
    private int location;
    
    public LocalVariable(final String name, final int location) {
        this.name = name;
        this.location = location;
    }
    
    public void setLocation(final int slot) {
        this.location = slot;
    }
    
    public int getLocation() {
        return this.location;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String toString() {
        return this.name;
    }
    
    public int hashCode() {
        return this.name.hashCode();
    }
    
    public boolean isSelf() {
        return this.name.equals("%self");
    }
    
    public boolean equals(final Object obj) {
        return obj != null && obj instanceof LocalVariable && this.name.equals(((LocalVariable)obj).name);
    }
    
    public int compareTo(final Object arg0) {
        if (!(arg0 instanceof LocalVariable)) {
            return 0;
        }
        return this.name.compareTo(((LocalVariable)arg0).name);
    }
    
    public Object retrieve(final InterpreterContext interp) {
        return interp.getLocalVariable(this.location);
    }
    
    public Object store(final InterpreterContext interp, final Object value) {
        return interp.setLocalVariable(this.location, value);
    }
}
