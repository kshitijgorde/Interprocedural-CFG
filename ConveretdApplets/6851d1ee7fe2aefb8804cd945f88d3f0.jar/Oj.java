// 
// Decompiled by Procyon v0.5.30
// 

public class Oj extends c
{
    public Oj(final int n) {
        super(n, false);
    }
    
    protected boolean _(final char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '%' || c == '&';
    }
}
