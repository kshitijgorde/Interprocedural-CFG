// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.verifier.statics;

import org.apache.bcel.verifier.exc.LocalVariableInfoInconsistentException;
import org.apache.bcel.generic.Type;
import java.util.Hashtable;

public class LocalVariableInfo
{
    private Hashtable types;
    private Hashtable names;
    
    public LocalVariableInfo() {
        this.types = new Hashtable();
        this.names = new Hashtable();
    }
    
    private void setName(final int offset, final String name) {
        this.names.put(Integer.toString(offset), name);
    }
    
    private void setType(final int offset, final Type t) {
        this.types.put(Integer.toString(offset), t);
    }
    
    public Type getType(final int offset) {
        return this.types.get(Integer.toString(offset));
    }
    
    public String getName(final int offset) {
        return this.names.get(Integer.toString(offset));
    }
    
    public void add(final String name, final int startpc, final int length, final Type t) throws LocalVariableInfoInconsistentException {
        for (int i = startpc; i <= startpc + length; ++i) {
            this.add(i, name, t);
        }
    }
    
    private void add(final int offset, final String name, final Type t) throws LocalVariableInfoInconsistentException {
        if (this.getName(offset) != null && !this.getName(offset).equals(name)) {
            throw new LocalVariableInfoInconsistentException("At bytecode offset '" + offset + "' a local variable has two different names: '" + this.getName(offset) + "' and '" + name + "'.");
        }
        if (this.getType(offset) != null && !this.getType(offset).equals(t)) {
            throw new LocalVariableInfoInconsistentException("At bytecode offset '" + offset + "' a local variable has two different types: '" + this.getType(offset) + "' and '" + t + "'.");
        }
        this.setName(offset, name);
        this.setType(offset, t);
    }
}
