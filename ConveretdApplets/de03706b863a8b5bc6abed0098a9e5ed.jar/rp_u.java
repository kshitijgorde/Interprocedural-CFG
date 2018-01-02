// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_u extends RuntimeException
{
    public int a;
    
    public rp_u(final String s, final int a, final String s2) {
        super("XML Parse Exception during parsing of " + ((s == null) ? "the XML definition" : ("a " + s + " element")) + " at line " + a + ": " + s2);
        this.a = a;
    }
}
