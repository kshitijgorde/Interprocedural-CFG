// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.data;

import java.util.Hashtable;
import java.io.Serializable;

public class SymbolTable implements Serializable
{
    private Hashtable symbols;
    private SymbolTable parent;
    
    SymbolTable() {
        this(null);
    }
    
    SymbolTable(final SymbolTable parent) {
        this.parent = parent;
        this.symbols = new Hashtable();
    }
    
    SymbolTable getParent() {
        return this.parent;
    }
    
    public synchronized MathObject get(final String s) {
        if (s == null) {
            return null;
        }
        final MathObject value = this.symbols.get(s);
        if (value != null) {
            return value;
        }
        if (this.parent != null) {
            return this.parent.get(s);
        }
        return null;
    }
    
    public synchronized void add(final MathObject mathObject) {
        if (mathObject == null) {
            throw new NullPointerException("Can't put a null symbol in SymbolTable.");
        }
        this.add(mathObject.getName(), mathObject);
    }
    
    public synchronized void add(final String s, final MathObject mathObject) {
        if (mathObject == null) {
            throw new NullPointerException("Can't put a null symbol in SymbolTable.");
        }
        if (s == null) {
            throw new NullPointerException("Can't put unnamed MathObject in SymbolTable.");
        }
        this.symbols.put(s, mathObject);
    }
    
    public synchronized void remove(final String s) {
        if (s != null) {
            this.symbols.remove(s);
        }
    }
}
