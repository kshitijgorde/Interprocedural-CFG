// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.io.IOException;

class VerifyMD5 implements HashVerifier, GlobalConstants
{
    RoResponse resp;
    
    public VerifyMD5(final RoResponse resp) {
        this.resp = resp;
    }
    
    public void verifyHash(final byte[] array, final long n) throws IOException {
        String s;
        try {
            if ((s = this.resp.getHeader("Content-MD5")) == null) {
                s = this.resp.getTrailer("Content-MD5");
            }
        }
        catch (IOException ex) {
            return;
        }
        if (s == null) {
            return;
        }
        final byte[] base64Decode = Codecs.base64Decode(s.trim().getBytes());
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != base64Decode[i]) {
                throw new IOException("MD5-Digest mismatch: expected " + hex(base64Decode) + " but calculated " + hex(array));
            }
        }
        if (GlobalConstants.DebugMods) {
            Util.logLine("CMD5M: hash successfully verified");
        }
    }
    
    private static String hex(final byte[] array) {
        final StringBuffer sb = new StringBuffer(array.length * 3);
        for (int i = 0; i < array.length; ++i) {
            sb.append(Character.forDigit(array[i] >>> 4 & 0xF, 16));
            sb.append(Character.forDigit(array[i] & 0xF, 16));
            sb.append(':');
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
