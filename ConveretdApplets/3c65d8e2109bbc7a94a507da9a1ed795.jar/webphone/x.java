// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.crypto.SecretKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.Key;
import javax.crypto.spec.PBEParameterSpec;
import java.security.spec.KeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.Cipher;

public class x
{
    Cipher if;
    Cipher int;
    String new;
    aw for;
    int a;
    byte[] do;
    
    x(final aw for1) {
        this.if = null;
        this.int = null;
        this.new = "xx45tgds2rgfvs";
        this.for = null;
        this.a = 3;
        this.do = new byte[] { -87, -101, -56, 50, 86, 52, -29, 3 };
        try {
            this.for = for1;
            this.new = aw.bB;
            final SecretKey generateSecret = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(new PBEKeySpec(this.new.toCharArray(), this.do, this.a));
            this.if = Cipher.getInstance(generateSecret.getAlgorithm());
            this.int = Cipher.getInstance(generateSecret.getAlgorithm());
            final PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(this.do, this.a);
            this.if.init(1, generateSecret, pbeParameterSpec);
            this.int.init(2, generateSecret, pbeParameterSpec);
        }
        catch (Exception ex) {
            this.for.if("des ctor", ex);
        }
    }
    
    public String if(final String s) {
        try {
            return new BASE64Encoder().encode(this.if.doFinal(s.getBytes("UTF8")));
        }
        catch (Exception ex) {
            this.for.if("des encrypt", ex);
            return s;
        }
    }
    
    public String a(final String s) {
        try {
            return new String(this.int.doFinal(new BASE64Decoder().decodeBuffer(s)), "UTF8");
        }
        catch (Exception ex) {
            this.for.if("des decrypt", ex);
            return s;
        }
    }
}
