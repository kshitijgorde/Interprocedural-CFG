// 
// Decompiled by Procyon v0.5.30
// 

package de.bb.minissl;

import java.security.MessageDigest;
import java.util.Random;

class a extends Random
{
    static final a A;
    private MessageDigest B;
    
    static {
        A = new a();
    }
    
    a() {
        try {
            this.B = MessageDigest.getInstance("SHA");
        }
        catch (Exception ex) {}
        this.B.update(Long.toHexString(System.currentTimeMillis()).getBytes());
    }
    
    protected final synchronized int next(int i) {
        final byte[] bytes = Integer.toHexString(i).getBytes();
        this.B.update(bytes);
        final byte[] bytes2 = Long.toHexString(System.currentTimeMillis()).getBytes();
        this.B.update(bytes2);
        int n = 0;
        while (i > 7) {
            final byte[] digest = this.B.digest();
            n = (n << 8 | (0xFF & digest[0]));
            i -= 8;
            this.B.update(digest);
            this.B.update(bytes2);
            this.B.update(bytes);
        }
        if (i > 0) {
            final int n2 = n << 7;
            i = (1 << i) - 1;
            final byte[] digest2 = this.B.digest();
            n = (n2 | (i & digest2[0]));
            this.B.update(digest2);
            this.B.update(bytes2);
            this.B.update(bytes);
        }
        return n;
    }
}
