// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.security.Signature;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;

public class mh extends mi
{
    private PrivateKey a;
    
    public synchronized void a() {
        if (this.a == null) {
            try {
                final KeyPairGenerator instance = KeyPairGenerator.getInstance("DSA");
                instance.initialize(512, new mat());
                this.a = instance.generateKeyPair().getPrivate();
            }
            catch (NoSuchAlgorithmException ex) {
                System.err.println(ex);
            }
        }
    }
    
    public byte[] a(final byte[] array) {
        byte[] sign = new byte[0];
        if (this.a != null) {
            try {
                final Signature instance = Signature.getInstance("DSA");
                instance.initSign(this.a);
                instance.update(array);
                sign = instance.sign();
            }
            catch (Exception ex) {
                System.err.println(ex);
            }
        }
        return sign;
    }
}
