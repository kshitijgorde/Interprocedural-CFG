// 
// Decompiled by Procyon v0.5.30
// 

package com.privylink.trustfield.iase.client.applet;

import com.privylink.trustfield.iase.client.TIASE;
import java.applet.Applet;

public class TrustFieldExtApp extends Applet
{
    private TIASE _$20588;
    private String _$1539;
    private String _$1540;
    private String _$1541;
    private String _$20590;
    private String _$1543;
    private static boolean _$1544;
    private byte[] _$20591;
    private byte[] _$20592;
    
    public TrustFieldExtApp() {
        this._$20591 = new byte[] { 14, -46, 97, 20, 59, -95, -95, 65, -96, 44, -105, -78, -69, 5, 91, 112, 10, 23, -31, -22, 4, 69, 35, 71, 14, 105, 85, 81, -25, 127, 68, 38, -25, -13, 18, 82, 53, 83, 124, 44, -72, 45, 56, -59, 116, 118, 19, -4, -49, -88, 69, -106, -70, 119, 4, 52, 87, -122, 123, -93, -125, -32, 37, 90, 110, 52, 47, -115, -2, -77, 100, -7, 30, 22, -92, -66, 113, -111, -38, 67, 48, 103, 94, -114, 57, 89, -122, -63, -63, -81, 93, 77, 57, -100, -2, 54, 125, 60, 106, -109, 109, -41, 10, 61, -40, 60, 45, 107, 97, -62, 33, 17, 12, -92, 58, 56, -39, 60, -78, -30, 65, 115, -45, -100, 75, -44, 61, -30, 45, 108, -66, -19 };
        this._$20592 = new byte[] { -50, -82, 109, -73, 10, 101, 27, 101 };
    }
    
    public final void init() {
        this._$1539 = null;
        this._$1540 = null;
        this._$1541 = null;
        this._$1543 = null;
        this._$20590 = null;
        this._$1539 = this.getParameter("SID");
        this._$1540 = this.getParameter("Challenge");
        this._$1541 = this.getParameter("Charset");
        this._$1543 = this.getParameter("Seqflag");
        this._$20590 = this.getParameter("browser");
        this._$20588 = new TIASE();
        this.KeyInit(this._$20591, this._$20592);
        super.init();
        if (this._$1539 != null) {
            this.storeSID(this._$1539);
        }
        if (this._$1540 != null) {
            this.storeChallenge(this._$1540);
        }
        if (this._$1541 != null) {
            this.setCharSet(this._$1541);
        }
        if (this._$1543 != null) {
            this.setSeqFlag(this._$1543);
        }
    }
    
    public void start() {
        TrustFieldExtApp._$1544 = this._$20588.isExpired();
        super.start();
    }
    
    public void stop() {
        this.destroy();
        super.stop();
    }
    
    public void destroy() {
        super.destroy();
    }
    
    public final void KeyInit(final byte[] n, final byte[] e) {
        try {
            if (TrustFieldExtApp._$1544) {
                return;
            }
            if (n == null || e == null) {
                throw new NullPointerException("java.lang.NullPointerException: Error occured in the KeyInit method.");
            }
            this._$20588.KeyInit(n, e);
        }
        catch (Exception err) {
            System.err.println(err.getMessage());
        }
    }
    
    public final void storeSID(final String SID_str) {
        try {
            if (TrustFieldExtApp._$1544) {
                return;
            }
            if (SID_str == null || SID_str.length() <= 0) {
                throw new NullPointerException("java.lang.NullPointerException: Error occured in the storeSID method.");
            }
            this._$20588.storeSID(SID_str);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public final String getSID() {
        if (TrustFieldExtApp._$1544) {
            return null;
        }
        return this._$20588.getSID();
    }
    
    public final void storeChallenge(final String Ra_str) {
        try {
            if (TrustFieldExtApp._$1544) {
                return;
            }
            if (Ra_str == null || Ra_str.length() <= 0) {
                throw new NullPointerException("java.lang.NullPointerException: Error occured in the storeChallenge method.");
            }
            this._$20588.storeChallenge(Ra_str);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public final String getChallenge() {
        if (TrustFieldExtApp._$1544) {
            return null;
        }
        return this._$20588.getChallenge();
    }
    
    public final void setCharSet(final String encoding) {
        try {
            if (TrustFieldExtApp._$1544) {
                return;
            }
            if (encoding == null || encoding.length() <= 0) {
                throw new NullPointerException("java.lang.NullPointerException: Error occured in the setCharSet method.");
            }
            this._$20588.setCharSet(encoding);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public final String getCharSet() {
        if (TrustFieldExtApp._$1544) {
            return null;
        }
        return this._$20588.getCharSet();
    }
    
    public final void setSeqFlag(final String SeqFlag_str) {
        try {
            if (TrustFieldExtApp._$1544) {
                return;
            }
            if (SeqFlag_str == null || SeqFlag_str.length() <= 0) {
                throw new NullPointerException("java.lang.NullPointerException: Error occured in the setSeqFlag method.");
            }
            this._$20588.setSeqFlag(SeqFlag_str);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public final String getSeqFlag() {
        if (TrustFieldExtApp._$1544) {
            return null;
        }
        return this._$20588.getSeqFlag();
    }
    
    public final String encryptHandshake() {
        if (TrustFieldExtApp._$1544) {
            return null;
        }
        return this._$20588.encryptHandshake();
    }
    
    public final String decryptHandshake(final String enc_res) {
        try {
            if (TrustFieldExtApp._$1544) {
                return null;
            }
            if (enc_res == null || enc_res.length() <= 0) {
                throw new NullPointerException("java.lang.NullPointerException: Error occured in the decryptHandshake method.");
            }
            return this._$20588.decryptHandshake(enc_res);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            return "FAILURE : handshake failed";
        }
    }
    
    public final void clearMsg() {
        if (TrustFieldExtApp._$1544) {
            return;
        }
        this._$20588.clearMsg();
    }
    
    public final void appendMsg(final String Msg_str) {
        try {
            if (TrustFieldExtApp._$1544) {
                return;
            }
            if (Msg_str == null || Msg_str.length() <= 0) {
                throw new NullPointerException("java.lang.NullPointerException: Error occured in the appendMsg method.");
            }
            this._$20588.appendMsg(Msg_str);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public final String encryptMsg() {
        if (TrustFieldExtApp._$1544) {
            return null;
        }
        return this._$20588.encryptMsg();
    }
    
    public final String encryptField(final String Field) {
        try {
            if (TrustFieldExtApp._$1544) {
                return null;
            }
            if (Field == null || Field.length() <= 0) {
                throw new NullPointerException("java.lang.NullPointerException: Error occured in the encryptField method.");
            }
            return this._$20588.encryptField(Field);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
    
    public final byte[] encryptField(final byte[] input, final int inputOffset, final int inputLength) {
        try {
            if (TrustFieldExtApp._$1544) {
                return null;
            }
            if (input == null || inputLength <= 0) {
                throw new NullPointerException("java.lang.NullPointerException: Error occured in the encryptField method.");
            }
            return this._$20588.encryptField(input, inputOffset, inputLength);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
    
    public final String decryptMsg(final String enc_pakt) {
        try {
            if (TrustFieldExtApp._$1544) {
                return null;
            }
            if (enc_pakt == null || enc_pakt.length() <= 0) {
                throw new NullPointerException("java.lang.NullPointerException: Error occured in the decryptMsg method.");
            }
            return this._$20588.decryptMsg(enc_pakt);
        }
        catch (Exception e) {
            if (e.getMessage() != null) {
                System.err.println(e.getMessage());
            }
            return "FAILURE : decryption failed";
        }
    }
    
    public final byte[] decryptMsg(final byte[] input, final int inputOffset, final int inputLength) {
        try {
            if (TrustFieldExtApp._$1544) {
                return null;
            }
            if (input == null || inputLength <= 0) {
                throw new NullPointerException("java.lang.NullPointerException: Error occured in the decryptMsg method.");
            }
            return this._$20588.decryptMsg(input, inputOffset, inputLength);
        }
        catch (Exception e) {
            if (e.getMessage() != null) {
                System.err.println(e.getMessage());
            }
            return null;
        }
    }
    
    public final void addPIN(final String PINvalue) {
        try {
            if (TrustFieldExtApp._$1544) {
                return;
            }
            this._$20588.addPIN(PINvalue);
        }
        catch (Exception e) {
            if (e.getMessage() != null) {
                System.err.println(e.getMessage());
            }
        }
    }
    
    public final void addNewPIN(final String NewPINvalue) {
        try {
            if (TrustFieldExtApp._$1544) {
                return;
            }
            this._$20588.addNewPIN(NewPINvalue);
        }
        catch (Exception e) {
            if (e.getMessage() != null) {
                System.err.println(e.getMessage());
            }
        }
    }
    
    public final boolean auth() {
        return !TrustFieldExtApp._$1544 && this._$20588.auth();
    }
    
    public final boolean pinch() {
        return !TrustFieldExtApp._$1544 && this._$20588.pinch();
    }
    
    public String getEPIN() {
        if (TrustFieldExtApp._$1544) {
            return null;
        }
        return this._$20588.getEPIN();
    }
    
    public void load_PublicKey(final String pubkey_in) {
        try {
            if (TrustFieldExtApp._$1544) {
                return;
            }
            if (pubkey_in == null || pubkey_in.length() <= 0) {
                throw new NullPointerException("java.lang.NullPointerException: Error occured in the load_PublicKey method.");
            }
            this._$20588.load_PublicKey(pubkey_in);
        }
        catch (Exception e) {
            if (e.getMessage() != null) {
                System.err.println(e.getMessage());
            }
        }
    }
    
    public final boolean convert() {
        return !TrustFieldExtApp._$1544 && this._$20588.convert();
    }
    
    public final boolean create() {
        return !TrustFieldExtApp._$1544 && this._$20588.create();
    }
    
    public void getVersion() {
        if (TrustFieldExtApp._$1544) {
            return;
        }
        this._$20588.getVersion();
    }
    
    public String getVersionStr() {
        if (TrustFieldExtApp._$1544) {
            return null;
        }
        return this._$20588.getVersionStr();
    }
    
    public String generateHash(final String in, final String Algo) {
        if (TrustFieldExtApp._$1544) {
            return null;
        }
        return this._$20588.generateHash(in, Algo);
    }
    
    public final String decryptString(final String input) {
        return this.decryptMsg(input);
    }
    
    public final byte[] decryptBytes(final byte[] input, final int inputOffset, final int inputLength) {
        return this.decryptMsg(input, inputOffset, inputLength);
    }
    
    public final String encryptString(final String input) {
        return this.encryptField(input);
    }
    
    public final byte[] encryptBytes(final byte[] input, final int inputOffset, final int inputLength) {
        return this.encryptField(input, inputOffset, inputLength);
    }
}
