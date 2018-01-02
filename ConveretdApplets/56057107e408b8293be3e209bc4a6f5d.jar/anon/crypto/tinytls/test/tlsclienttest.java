// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto.tinytls.test;

import java.io.InputStream;
import anon.crypto.tinytls.TinyTLS;
import logging.Log;
import logging.LogHolder;
import logging.SystemErrLog;

public class tlsclienttest
{
    public static void main(final String[] array) throws Exception {
        LogHolder.setLogInstance(new SystemErrLog());
        final TinyTLS tinyTLS = new TinyTLS("localhost", 3456);
        tinyTLS.checkRootCertificate(false);
        tinyTLS.startHandshake();
        final InputStream inputStream = tinyTLS.getInputStream();
        final byte[] array2 = new byte[1000000];
        tinyTLS.setSoTimeout(1000);
        try {
            int read;
            while ((read = inputStream.read()) > 0) {
                System.out.print((char)read);
            }
        }
        catch (Exception ex) {}
        tinyTLS.close();
    }
}
