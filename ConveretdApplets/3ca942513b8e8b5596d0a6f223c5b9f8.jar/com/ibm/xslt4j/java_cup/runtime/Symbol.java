// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.java_cup.runtime;

public class Symbol
{
    public int sym;
    public int parse_state;
    boolean used_by_parser;
    public int left;
    public int right;
    public Object value;
    
    public Symbol(final int id, final int l, final int r, final Object o) {
        this(id);
        this.left = l;
        this.right = r;
        this.value = o;
    }
    
    public Symbol(final int id, final Object o) {
        this(id, -1, -1, o);
    }
    
    public Symbol(final int id, final int l, final int r) {
        this(id, l, r, null);
    }
    
    public Symbol(final int sym_num) {
        this(sym_num, -1);
        this.left = -1;
        this.right = -1;
        this.value = null;
    }
    
    Symbol(final int sym_num, final int state) {
        this.used_by_parser = false;
        this.sym = sym_num;
        this.parse_state = state;
    }
    
    public String toString() {
        return "#" + this.sym;
    }
}
