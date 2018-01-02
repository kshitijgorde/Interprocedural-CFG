// 
// Decompiled by Procyon v0.5.30
// 

public class Latex2BoxCalc extends Latex2Box
{
    private LatexACapsa parse;
    
    public Latex2BoxCalc() {
        this.parse = new LatexACapsa();
    }
    
    public final AbstractBox parse(final String s) {
        return this.parse.parse(s);
    }
}
