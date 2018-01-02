// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.math.BigInteger;
import mindbright.security.RSAPublicKey;

public class SSHRSAPublicKeyString extends RSAPublicKey
{
    String user;
    String opts;
    
    public SSHRSAPublicKeyString(final String opts, final String user, final BigInteger e, final BigInteger n) {
        super(e, n);
        this.opts = opts;
        this.user = user;
    }
    
    public static SSHRSAPublicKeyString createKey(final String opts, final String pubKey) throws NoSuchElementException {
        final StringTokenizer tok = new StringTokenizer(pubKey);
        String user = null;
        final String bits = tok.nextToken();
        final String e = tok.nextToken();
        final String n = tok.nextToken();
        if (tok.hasMoreElements()) {
            user = tok.nextToken();
        }
        return new SSHRSAPublicKeyString(opts, user, new BigInteger(e), new BigInteger(n));
    }
    
    public String getOpts() {
        return this.opts;
    }
    
    public String getUser() {
        return this.user;
    }
    
    public String toString() {
        return ((this.opts != null) ? (this.opts + " ") : "") + this.bitLength() + " " + this.getE() + " " + this.getN() + " " + ((this.user != null) ? this.user : "");
    }
    
    public void toFile(final String fileName) throws IOException {
        final FileOutputStream fileOut = new FileOutputStream(fileName);
        final SSHDataOutputStream dataOut = new SSHDataOutputStream(fileOut);
        dataOut.writeBytes(this.toString());
        dataOut.close();
    }
}
