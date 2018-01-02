// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.http;

public class C extends Exception
{
    private static final long B = -8905888873656993709L;
    private String A;
    
    public C(final String s) {
        super("HTTP response code: " + s);
        this.A(s);
    }
    
    public String A() {
        return this.A;
    }
    
    public void A(final String a) {
        this.A = a;
    }
}
