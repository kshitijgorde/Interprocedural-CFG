// 
// Decompiled by Procyon v0.5.30
// 

public class Rp extends jp
{
    public Rp(final int n) {
        super(n, false);
    }
    
    protected boolean b(final char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '%' || c == '&';
    }
}
