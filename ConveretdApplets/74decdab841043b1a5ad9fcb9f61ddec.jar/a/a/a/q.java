// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.security.KeyFactory;
import java.security.Signature;
import sun.misc.BASE64Decoder;
import java.applet.Applet;
import java.net.URL;

public final class q
{
    private URL a;
    private String b;
    private String c;
    private String d;
    private long e;
    
    public q(final Applet applet) {
        try {
            this.a = applet.getDocumentBase();
            final n n = new n(applet);
            this.b = n.b(a.a.a.c.a);
            this.c = n.b(a.a.a.c.b);
            this.d = n.b(a.a.a.c.c);
            final String a;
            if ((a = n.a(a.a.a.c.d)) != null) {
                this.e = Long.valueOf(a);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final String a() {
        return this.b;
    }
    
    public final boolean b() {
        final URL a;
        if ((a = this.a) != null && !a.toString().startsWith("file:/") && !a.toString().startsWith("http://localhost") && !a.getHost().endsWith(this.c)) {
            f.b("Host isn't recognized: " + a + " (" + a.getHost() + ")");
            return false;
        }
        if (this.e > 0L && System.currentTimeMillis() / 1000L > this.e) {
            f.b("Partner has expired.");
            return false;
        }
        if (this.e > 0L && this.b.startsWith("JIRA")) {
            return true;
        }
        try {
            String s = this.b + "--" + this.c;
            if (this.e > 0L) {
                s = s + "--" + this.e;
            }
            final BASE64Decoder base64Decoder = new BASE64Decoder();
            final Signature instance;
            (instance = Signature.getInstance("SHA1withDSA", "SUN")).initVerify(KeyFactory.getInstance("DSA").generatePublic(new X509EncodedKeySpec(base64Decoder.decodeBuffer("MIIBuDCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZp\r\nRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fn\r\nxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuE\r\nC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJ\r\nFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImo\r\ng9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoDgYUAAoGBAPOb7uwX0GhLieKNQwakCxgrMhLG2sNJg1Ao\r\ngpT3MB6LW6T1GRgQCa8rkTabwPwBrVM3TdTv2MoZsVqei0M1cSPmDY3fNa7hHGjww3XLwL+HTYvp\r\nysF2fphkOGaeOO8J6Vp965a6Tl3xiUI9ow+RAwrxKIC7gGyUyHS3uL87banA"))));
            instance.update(s.getBytes());
            if (!instance.verify(base64Decoder.decodeBuffer(this.d))) {
                f.b("Failed to validate key.");
                return false;
            }
        }
        catch (Exception ex) {
            f.a("Error while validating key.", ex);
            return false;
        }
        return true;
    }
}
