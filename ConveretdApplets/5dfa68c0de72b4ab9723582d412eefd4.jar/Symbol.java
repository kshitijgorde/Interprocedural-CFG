// 
// Decompiled by Procyon v0.5.30
// 

class Symbol
{
    String pname;
    Function fcn;
    Object value;
    
    Symbol(final String pname) {
        this.pname = pname;
    }
    
    public String toString() {
        return this.pname;
    }
}
