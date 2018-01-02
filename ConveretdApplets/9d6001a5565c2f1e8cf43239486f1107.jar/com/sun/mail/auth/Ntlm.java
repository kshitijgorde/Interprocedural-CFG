// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.mail.auth;

import com.sun.mail.util.BASE64DecoderStream;
import java.security.GeneralSecurityException;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.spec.KeySpec;
import javax.crypto.spec.DESKeySpec;
import com.sun.mail.util.BASE64EncoderStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;
import java.io.PrintStream;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;

public class Ntlm
{
    private byte[] type1;
    private byte[] type3;
    private SecretKeyFactory fac;
    private Cipher cipher;
    private MD4 md4;
    private String hostname;
    private String ntdomain;
    private String username;
    private String password;
    private PrintStream debugout;
    private static char[] hex;
    static /* synthetic */ Class class$com$sun$mail$auth$Ntlm;
    
    private void init0() {
        this.type1 = new byte[256];
        this.type3 = new byte[256];
        System.arraycopy(new byte[] { 78, 84, 76, 77, 83, 83, 80, 0, 1 }, 0, this.type1, 0, 9);
        this.type1[12] = 3;
        this.type1[13] = -78;
        this.type1[28] = 32;
        System.arraycopy(new byte[] { 78, 84, 76, 77, 83, 83, 80, 0, 3 }, 0, this.type3, 0, 9);
        this.type3[12] = 24;
        this.type3[14] = 24;
        this.type3[20] = 24;
        this.type3[22] = 24;
        this.type3[32] = 64;
        this.type3[60] = 1;
        this.type3[61] = -126;
        try {
            this.fac = SecretKeyFactory.getInstance("DES");
            this.cipher = Cipher.getInstance("DES/ECB/NoPadding");
            this.md4 = new MD4();
        }
        catch (NoSuchPaddingException e) {
            assert false;
        }
        catch (NoSuchAlgorithmException e2) {
            assert false;
        }
    }
    
    public Ntlm(String ntdomain, String hostname, String username, final String password, final PrintStream debugout) {
        int i = hostname.indexOf(46);
        if (i != -1) {
            hostname = hostname.substring(0, i);
        }
        i = username.indexOf(92);
        if (i != -1) {
            ntdomain = username.substring(0, i).toUpperCase();
            username = username.substring(i + 1);
        }
        else if (ntdomain == null) {
            ntdomain = "";
        }
        this.ntdomain = ntdomain;
        this.hostname = hostname;
        this.username = username;
        this.password = password;
        this.debugout = debugout;
        this.init0();
    }
    
    private void copybytes(final byte[] dest, final int destpos, final String src, final String enc) {
        try {
            final byte[] x = src.getBytes(enc);
            System.arraycopy(x, 0, dest, destpos, x.length);
        }
        catch (UnsupportedEncodingException e) {
            assert false;
        }
    }
    
    public String generateType1Msg(final int flags) {
        final int dlen = this.ntdomain.length();
        this.type1[16] = (byte)(dlen % 256);
        this.type1[17] = (byte)(dlen / 256);
        this.type1[18] = this.type1[16];
        this.type1[19] = this.type1[17];
        if (dlen == 0) {
            final byte[] type1 = this.type1;
            final int n = 13;
            type1[n] &= 0xFFFFFFEF;
        }
        final int hlen = this.hostname.length();
        this.type1[24] = (byte)(hlen % 256);
        this.type1[25] = (byte)(hlen / 256);
        this.type1[26] = this.type1[24];
        this.type1[27] = this.type1[25];
        this.copybytes(this.type1, 32, this.hostname, "iso-8859-1");
        this.copybytes(this.type1, hlen + 32, this.ntdomain, "iso-8859-1");
        this.type1[20] = (byte)((hlen + 32) % 256);
        this.type1[21] = (byte)((hlen + 32) / 256);
        final byte[] msg = new byte[32 + hlen + dlen];
        System.arraycopy(this.type1, 0, msg, 0, 32 + hlen + dlen);
        if (this.debugout != null) {
            this.debugout.println("DEBUG NTLM: type 1 message: " + toHex(msg));
        }
        String result = null;
        try {
            result = new String(BASE64EncoderStream.encode(msg), "iso-8859-1");
        }
        catch (UnsupportedEncodingException e) {
            assert false;
        }
        return result;
    }
    
    private byte[] makeDesKey(final byte[] input, final int off) {
        final int[] in = new int[input.length];
        for (int i = 0; i < in.length; ++i) {
            in[i] = ((input[i] < 0) ? (input[i] + 256) : input[i]);
        }
        final byte[] out = { (byte)in[off + 0], (byte)((in[off + 0] << 7 & 0xFF) | in[off + 1] >> 1), (byte)((in[off + 1] << 6 & 0xFF) | in[off + 2] >> 2), (byte)((in[off + 2] << 5 & 0xFF) | in[off + 3] >> 3), (byte)((in[off + 3] << 4 & 0xFF) | in[off + 4] >> 4), (byte)((in[off + 4] << 3 & 0xFF) | in[off + 5] >> 5), (byte)((in[off + 5] << 2 & 0xFF) | in[off + 6] >> 6), (byte)(in[off + 6] << 1 & 0xFF) };
        return out;
    }
    
    private byte[] calcLMHash() throws GeneralSecurityException {
        final byte[] magic = { 75, 71, 83, 33, 64, 35, 36, 37 };
        final byte[] pwb = this.password.toUpperCase().getBytes();
        final byte[] pwb2 = new byte[14];
        int len = this.password.length();
        if (len > 14) {
            len = 14;
        }
        System.arraycopy(pwb, 0, pwb2, 0, len);
        final DESKeySpec dks1 = new DESKeySpec(this.makeDesKey(pwb2, 0));
        final DESKeySpec dks2 = new DESKeySpec(this.makeDesKey(pwb2, 7));
        final SecretKey key1 = this.fac.generateSecret(dks1);
        final SecretKey key2 = this.fac.generateSecret(dks2);
        this.cipher.init(1, key1);
        final byte[] out1 = this.cipher.doFinal(magic, 0, 8);
        this.cipher.init(1, key2);
        final byte[] out2 = this.cipher.doFinal(magic, 0, 8);
        final byte[] result = new byte[21];
        System.arraycopy(out1, 0, result, 0, 8);
        System.arraycopy(out2, 0, result, 8, 8);
        return result;
    }
    
    private byte[] calcNTHash() throws GeneralSecurityException {
        byte[] pw = null;
        try {
            pw = this.password.getBytes("UnicodeLittleUnmarked");
        }
        catch (UnsupportedEncodingException e) {
            assert false;
        }
        final byte[] out = this.md4.digest(pw);
        final byte[] result = new byte[21];
        System.arraycopy(out, 0, result, 0, 16);
        return result;
    }
    
    private byte[] calcResponse(final byte[] key, final byte[] text) throws GeneralSecurityException {
        assert key.length == 21;
        final DESKeySpec dks1 = new DESKeySpec(this.makeDesKey(key, 0));
        final DESKeySpec dks2 = new DESKeySpec(this.makeDesKey(key, 7));
        final DESKeySpec dks3 = new DESKeySpec(this.makeDesKey(key, 14));
        final SecretKey key2 = this.fac.generateSecret(dks1);
        final SecretKey key3 = this.fac.generateSecret(dks2);
        final SecretKey key4 = this.fac.generateSecret(dks3);
        this.cipher.init(1, key2);
        final byte[] out1 = this.cipher.doFinal(text, 0, 8);
        this.cipher.init(1, key3);
        final byte[] out2 = this.cipher.doFinal(text, 0, 8);
        this.cipher.init(1, key4);
        final byte[] out3 = this.cipher.doFinal(text, 0, 8);
        final byte[] result = new byte[24];
        System.arraycopy(out1, 0, result, 0, 8);
        System.arraycopy(out2, 0, result, 8, 8);
        System.arraycopy(out3, 0, result, 16, 8);
        return result;
    }
    
    public String generateType3Msg(final String challenge) {
        try {
            final byte[] type2 = BASE64DecoderStream.decode(challenge.getBytes());
            final byte[] nonce = new byte[8];
            System.arraycopy(type2, 24, nonce, 0, 8);
            final int ulen = this.username.length() * 2;
            this.type3[36] = (this.type3[38] = (byte)(ulen % 256));
            this.type3[37] = (this.type3[39] = (byte)(ulen / 256));
            final int dlen = this.ntdomain.length() * 2;
            this.type3[28] = (this.type3[30] = (byte)(dlen % 256));
            this.type3[29] = (this.type3[31] = (byte)(dlen / 256));
            final int hlen = this.hostname.length() * 2;
            this.type3[44] = (this.type3[46] = (byte)(hlen % 256));
            this.type3[45] = (this.type3[47] = (byte)(hlen / 256));
            int l = 64;
            this.copybytes(this.type3, l, this.ntdomain, "UnicodeLittleUnmarked");
            this.type3[32] = (byte)(l % 256);
            this.type3[33] = (byte)(l / 256);
            l += dlen;
            this.copybytes(this.type3, l, this.username, "UnicodeLittleUnmarked");
            this.type3[40] = (byte)(l % 256);
            this.type3[41] = (byte)(l / 256);
            l += ulen;
            this.copybytes(this.type3, l, this.hostname, "UnicodeLittleUnmarked");
            this.type3[48] = (byte)(l % 256);
            this.type3[49] = (byte)(l / 256);
            l += hlen;
            final byte[] lmhash = this.calcLMHash();
            final byte[] lmresponse = this.calcResponse(lmhash, nonce);
            final byte[] nthash = this.calcNTHash();
            final byte[] ntresponse = this.calcResponse(nthash, nonce);
            System.arraycopy(lmresponse, 0, this.type3, l, 24);
            this.type3[16] = (byte)(l % 256);
            this.type3[17] = (byte)(l / 256);
            l += 24;
            System.arraycopy(ntresponse, 0, this.type3, l, 24);
            this.type3[24] = (byte)(l % 256);
            this.type3[25] = (byte)(l / 256);
            l += 24;
            this.type3[56] = (byte)(l % 256);
            this.type3[57] = (byte)(l / 256);
            final byte[] msg = new byte[l];
            System.arraycopy(this.type3, 0, msg, 0, l);
            if (this.debugout != null) {
                this.debugout.println("DEBUG NTLM: type 3 message: " + toHex(msg));
            }
            String result = null;
            try {
                result = new String(BASE64EncoderStream.encode(msg), "iso-8859-1");
            }
            catch (UnsupportedEncodingException e) {
                assert false;
            }
            return result;
        }
        catch (GeneralSecurityException ex) {
            if (this.debugout != null) {
                this.debugout.println("DEBUG NTLM: " + ex);
            }
            return "";
        }
    }
    
    private static String toHex(final byte[] b) {
        final StringBuffer sb = new StringBuffer(b.length * 3);
        for (int i = 0; i < b.length; ++i) {
            sb.append(Ntlm.hex[b[i] >> 4 & 0xF]).append(Ntlm.hex[b[i] & 0xF]).append(' ');
        }
        return sb.toString();
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError().initCause(x);
        }
    }
    
    static {
        $assertionsDisabled = !((Ntlm.class$com$sun$mail$auth$Ntlm == null) ? (Ntlm.class$com$sun$mail$auth$Ntlm = class$("com.sun.mail.auth.Ntlm")) : Ntlm.class$com$sun$mail$auth$Ntlm).desiredAssertionStatus();
        Ntlm.hex = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    }
}
