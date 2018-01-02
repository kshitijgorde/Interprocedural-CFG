// 
// Decompiled by Procyon v0.5.30
// 

class DottedSymbol
{
    Symbol sym;
    
    DottedSymbol(final Symbol sym) {
        this.sym = sym;
    }
    
    public String toString() {
        return ":" + this.sym.toString();
    }
}
