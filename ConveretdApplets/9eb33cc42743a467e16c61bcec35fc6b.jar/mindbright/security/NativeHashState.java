// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.security;

import java.security.MessageDigest;

public class NativeHashState extends ch
{
    public MessageDigest m6;
    
    public final void i3(String s) throws Exception {
        if (s.equals("SHA1")) {
            s = "SHA";
        }
        this.m6 = MessageDigest.getInstance(s);
    }
    
    public final void c3(final byte[] array, final int n, final int n2) {
        this.m6.update(array, n, n2);
    }
    
    public final byte[] nd() {
        return this.m6.digest();
    }
}
