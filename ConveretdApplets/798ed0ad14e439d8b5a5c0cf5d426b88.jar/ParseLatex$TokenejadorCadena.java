// 
// Decompiled by Procyon v0.5.30
// 

class ParseLatex$TokenejadorCadena extends ParseLatex$TokenejadorStream
{
    String charAt;
    int length;
    private final ParseLatex this$0;
    
    ParseLatex$TokenejadorCadena(final ParseLatex this$0, final String charAt) {
        super(this$0);
        this.this$0 = this$0;
        this.charAt = charAt;
        this.length = 0;
    }
    
    final char C() {
        return this.charAt.charAt(this.length++);
    }
    
    final void B() {
        --this.length;
    }
    
    final boolean D() {
        return this.length < this.charAt.length();
    }
    
    final boolean F() {
        return this.length >= this.charAt.length();
    }
    
    final int J() {
        return this.length;
    }
}
