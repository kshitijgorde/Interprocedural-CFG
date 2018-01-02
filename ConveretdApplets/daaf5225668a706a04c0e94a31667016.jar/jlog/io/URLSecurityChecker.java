// 
// Decompiled by Procyon v0.5.30
// 

package jlog.io;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.net.URL;

public class URLSecurityChecker
{
    SecurityManager $CPD;
    Object $DPD;
    public String msg;
    
    public void $T3C(final URL url) throws IOException {
        if (this.$CPD == null) {
            return;
        }
        try {
            if (url.getProtocol().equalsIgnoreCase("file")) {
                this.$CPD.checkRead(url.getFile(), this.$DPD);
            }
            else {
                this.$CPD.checkConnect(url.getHost(), -1, this.$DPD);
            }
        }
        catch (SecurityException ex) {
            String s = this.msg;
            final int index = this.msg.indexOf(36);
            if (index != -1) {
                s = String.valueOf(this.msg.substring(0, index)) + url.toExternalForm() + this.msg.substring(index + 1);
            }
            throw new FileNotFoundException(s);
        }
    }
    
    public URLSecurityChecker() {
        this.$CPD = null;
        this.$DPD = null;
        this.msg = "SecurityException for file $";
        this.$CPD = System.getSecurityManager();
        if (this.$CPD != null) {
            this.$CPD.getSecurityContext();
        }
    }
}
