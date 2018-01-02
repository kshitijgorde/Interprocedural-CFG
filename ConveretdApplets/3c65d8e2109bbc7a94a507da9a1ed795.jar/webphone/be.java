// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.net.PasswordAuthentication;
import java.net.Authenticator;

class be extends Authenticator
{
    int a;
    aw if;
    
    public be(final aw if1) {
        this.a = 0;
        this.if = null;
        this.if = if1;
    }
    
    protected PasswordAuthentication getPasswordAuthentication() {
        try {
            if ((this.if.cH.length() < 1 || this.if.aI.length() < 1) && this.a < 3) {
                ++this.a;
                this.if.ct.asyncneedproxyauthui = 1;
                for (int i = 0; i < 1800; ++i) {
                    this.if.do(100L);
                    if (this.if.ct.asyncneedproxyauthui == 0) {
                        this.if.a(3, "WARNING,http proxydetect auth ui err");
                        break;
                    }
                    if (this.if.ct.asyncneedproxyauthui == 3) {
                        this.if.a(3, "WARNING,http proxydetect auth done");
                        break;
                    }
                    if (this.if.ct.asyncneedproxyauthui == 4) {
                        this.if.a(3, "WARNING,http proxydetect auth canceled");
                        break;
                    }
                }
            }
            if (this.if.aI.length() > 0 && this.if.cH.length() > 0) {
                this.if.a(4, "EVENT, proxy auth as " + this.if.aI);
                return new PasswordAuthentication(this.if.aI, this.if.cH.toCharArray());
            }
        }
        catch (Exception ex) {
            this.if.a(3, "http PasswordAuthentication", ex);
        }
        return new PasswordAuthentication("anonymous", "anonymouspwd".toCharArray());
    }
}
