// 
// Decompiled by Procyon v0.5.30
// 

package java_cup.runtime;

public class Symbol
{
    public int sym;
    public int parse_state;
    boolean used_by_parser;
    public int left;
    public int right;
    public Object value;
    
    public Symbol(final int n) {
        this(n, -1);
        this.left = -1;
        this.right = -1;
        this.value = null;
    }
    
    Symbol(final int sym, final int parse_state) {
        this.used_by_parser = false;
        this.sym = sym;
        this.parse_state = parse_state;
    }
    
    public Symbol(final int n, final int n2, final int n3) {
        this(n, n2, n3, null);
    }
    
    public Symbol(final int n, final int left, final int right, final Object value) {
        this(n);
        this.left = left;
        this.right = right;
        this.value = value;
    }
    
    public Symbol(final int n, final Object o) {
        this(n, -1, -1, o);
    }
    
    public String toString() {
        return "#" + this.sym;
    }
}
