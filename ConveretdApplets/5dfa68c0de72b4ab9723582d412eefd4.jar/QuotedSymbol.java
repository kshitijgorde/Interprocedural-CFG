// 
// Decompiled by Procyon v0.5.30
// 

class QuotedSymbol
{
    Symbol sym;
    
    QuotedSymbol(final Symbol sym) {
        this.sym = sym;
    }
    
    public String toString() {
        return "\"" + this.sym.toString();
    }
}
