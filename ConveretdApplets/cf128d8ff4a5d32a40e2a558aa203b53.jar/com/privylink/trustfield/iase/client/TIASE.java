// 
// Decompiled by Procyon v0.5.30
// 

package com.privylink.trustfield.iase.client;

import com.privylink.trustfield.iase.client.crypto.MD5;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.Calendar;
import java.util.Date;
import com.privylink.trustfield.iase.client.crypto.TDES;
import com.privylink.trustfield.iase.client.crypto.RSA;

public class TIASE
{
    private String _$1539;
    private String _$1540;
    private String _$2511;
    private String _$1541;
    private short _$2512;
    private String _$1543;
    private byte[] _$2513;
    private byte[] _$2514;
    private byte[] _$2515;
    private byte[] _$2516;
    private byte[] _$2517;
    private int _$2518;
    private byte[] _$2519;
    private int _$2520;
    private byte[] _$2521;
    private int _$2522;
    private byte[] _$2523;
    private int[] _$2524;
    private RSA _$2525;
    private TDES _$2526;
    private boolean _$1544;
    private boolean _$2527;
    
    public TIASE() {
        this._$2515 = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 };
        this._$2527 = false;
        this._$1539 = null;
        this._$1540 = null;
        this._$1541 = null;
        this._$1543 = null;
        this._$2511 = null;
        this._$2511 = new String();
        this._$2513 = new byte[16];
        this._$2518 = 128;
        this._$2512 = 0;
        this._$2525 = new RSA();
        this._$2526 = new TDES();
        (this._$2524 = new int[64])[0] = 65;
        this._$2524[1] = 66;
        this._$2524[2] = 67;
        this._$2524[3] = 68;
        this._$2524[4] = 69;
        this._$2524[5] = 70;
        this._$2524[6] = 71;
        this._$2524[7] = 72;
        this._$2524[8] = 73;
        this._$2524[9] = 74;
        this._$2524[10] = 75;
        this._$2524[11] = 76;
        this._$2524[12] = 77;
        this._$2524[13] = 78;
        this._$2524[14] = 79;
        this._$2524[15] = 80;
        this._$2524[16] = 81;
        this._$2524[17] = 82;
        this._$2524[18] = 83;
        this._$2524[19] = 84;
        this._$2524[20] = 85;
        this._$2524[21] = 86;
        this._$2524[22] = 87;
        this._$2524[23] = 88;
        this._$2524[24] = 89;
        this._$2524[25] = 90;
        this._$2524[26] = 97;
        this._$2524[27] = 98;
        this._$2524[28] = 99;
        this._$2524[29] = 100;
        this._$2524[30] = 101;
        this._$2524[31] = 102;
        this._$2524[32] = 103;
        this._$2524[33] = 104;
        this._$2524[34] = 105;
        this._$2524[35] = 106;
        this._$2524[36] = 107;
        this._$2524[37] = 108;
        this._$2524[38] = 109;
        this._$2524[39] = 110;
        this._$2524[40] = 111;
        this._$2524[41] = 112;
        this._$2524[42] = 113;
        this._$2524[43] = 114;
        this._$2524[44] = 115;
        this._$2524[45] = 116;
        this._$2524[46] = 117;
        this._$2524[47] = 118;
        this._$2524[48] = 119;
        this._$2524[49] = 120;
        this._$2524[50] = 121;
        this._$2524[51] = 122;
        this._$2524[52] = 48;
        this._$2524[53] = 49;
        this._$2524[54] = 50;
        this._$2524[55] = 51;
        this._$2524[56] = 52;
        this._$2524[57] = 53;
        this._$2524[58] = 54;
        this._$2524[59] = 55;
        this._$2524[60] = 56;
        this._$2524[61] = 57;
        this._$2524[62] = 45;
        this._$2524[63] = 94;
        this._$1544 = false;
        if (this._$2527) {
            Date exp = new Date();
            final Date now = new Date();
            final Calendar expdate = Calendar.getInstance();
            expdate.set(2003, 6, 1, 0, 0, 0);
            exp = expdate.getTime();
            this._$1544 = false;
            long dt = exp.getTime() - now.getTime();
            System.out.println("Company Name : PrivyLink Private Limited");
            System.out.println("Product Name : TrustField IASE");
            System.out.println("Product Version : Version 1.3 build 002");
            System.out.println("");
            if (dt < 0) {
                this._$1544 = true;
                System.out.println("=== This evaluation copy has expired. ===");
                throw new RuntimeException("Validity Expired");
            }
            dt /= 86400000;
            if (dt < 1) {
                System.out.println("=== Last day of validity. ===");
            }
            else {
                System.out.println(String.valueOf(String.valueOf(new StringBuffer("=== ").append(dt).append(" day(s) of validity left. ==="))));
            }
            System.out.println("");
        }
    }
    
    public final void KeyInit(final byte[] n, final byte[] e) {
        if (this._$1544) {
            return;
        }
        try {
            if (n == null || e == null) {
                throw new NullPointerException("java.lang.NullPointerException: Error occured in the KeyInit method.");
            }
            this._$2517 = n;
            this._$2516 = e;
        }
        catch (Exception err) {
            System.err.println(err.getMessage());
        }
    }
    
    public final void storeSID(final String SID_str) {
        try {
            if (SID_str == null || SID_str.length() <= 0) {
                throw new NullPointerException("java.lang.NullPointerException: Error occured in the storeSID method.");
            }
            this._$1539 = SID_str;
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public final String getSID() {
        return this._$1539;
    }
    
    public final void storeChallenge(final String Ra_str) {
        try {
            if (Ra_str == null || Ra_str.length() <= 0) {
                throw new NullPointerException("java.lang.NullPointerException: Error occured in the storeChallenge method.");
            }
            this._$1540 = Ra_str;
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public final String getChallenge() {
        return this._$1540;
    }
    
    public final void setCharSet(final String encoding) {
        try {
            if (encoding == null || encoding.length() <= 0) {
                throw new NullPointerException("java.lang.NullPointerException: Error occured in the setCharSet method.");
            }
            this._$1541 = encoding;
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public final String getCharSet() {
        return this._$1541;
    }
    
    public final void setSeqFlag(final String SeqFlag_str) {
        try {
            if (SeqFlag_str == null || SeqFlag_str.length() <= 0) {
                throw new NullPointerException("java.lang.NullPointerException: Error occured in the setSeqFlag method.");
            }
            this._$1543 = SeqFlag_str;
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public final String getSeqFlag() {
        return this._$1543;
    }
    
    public final String encryptHandshake() {
        try {
            if (this._$1540.length() < 32) {
                return "FAILURE : server challenge not stored";
            }
            byte[] Ra_bin = new byte[16];
            byte[] Ra_hex = new byte[32];
            final byte[] req_bin = new byte[48];
            this._$2514 = new byte[16];
            Ra_hex = this._$1540.getBytes();
            Ra_bin = this._$2543(Ra_hex);
            this._$2514 = this._$2544();
            this._$2545();
            for (int i = 0; i < 16; ++i) {
                req_bin[i] = Ra_bin[i];
            }
            for (int i = 0; i < 16; ++i) {
                req_bin[i + 16] = this._$2514[i];
            }
            for (int i = 0; i < 16; ++i) {
                req_bin[i + 32] = this._$2513[i];
            }
            this._$2525.RSAEncryptInit(this._$2516, this._$2517);
            final byte[] enc_req_bin = this._$2525.RSAEncrypt(req_bin);
            byte[] enc_req_hex = new byte[enc_req_bin.length * 2];
            enc_req_hex = this._$2548(enc_req_bin);
            final String enc_req_str = this._$2549(enc_req_hex);
            return enc_req_str;
        }
        catch (Exception e) {
            System.err.println(String.valueOf(String.valueOf(e.toString())).concat(": Error occured in encryptHandshake method."));
            return null;
        }
    }
    
    public final String decryptHandshake(final String enc_res) {
        try {
            if (enc_res == null || enc_res.length() <= 0) {
                throw new NullPointerException();
            }
            final int enc_res_len = enc_res.length();
            if (enc_res_len < 48) {
                return "FAILURE : handshake failed";
            }
            byte[] enc_res_hex = new byte[enc_res_len];
            byte[] enc_res_bin = new byte[enc_res_len / 2];
            final byte[] res_bin = new byte[enc_res_bin.length];
            final byte[] res_Rb_bin = new byte[16];
            enc_res_hex = enc_res.getBytes();
            enc_res_bin = this._$2543(enc_res_hex);
            this._$2526.DES3CBCInit(this._$2513, this._$2515);
            this._$2526.DES3CBCDecrypt(enc_res_bin, res_bin);
            for (int i = 0; i < 16; ++i) {
                res_Rb_bin[i] = res_bin[i];
            }
            for (int i = 0; i < 16; ++i) {
                if (res_Rb_bin[i] != this._$2514[i]) {
                    return "FAILURE : handshake failed";
                }
            }
            this._$2512 = this._$2557(res_bin[16], res_bin[17]);
            return "handshake passed";
        }
        catch (Exception e) {
            System.err.println(String.valueOf(String.valueOf(e.toString())).concat(": Error occured in decryptHandshake method."));
            return "FAILURE : handshake failed";
        }
    }
    
    public final void clearMsg() {
        this._$2511 = "";
    }
    
    public final void appendMsg(final String Msg_str) {
        try {
            if (Msg_str == null || Msg_str.length() <= 0) {
                throw new NullPointerException("java.lang.NullPointerException: Error occured in the appendMsg method.");
            }
            this._$2511 = String.valueOf(String.valueOf(this._$2511)).concat(String.valueOf(String.valueOf(Msg_str)));
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public final String encryptMsg() {
        return this._$2558(this._$2511);
    }
    
    public final String encryptField(final String Field) {
        try {
            if (Field == null || Field.length() <= 0) {
                throw new NullPointerException("java.lang.NullPointerException: Error occured in the encryptField method.");
            }
            return this._$2558(Field);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
    
    public final byte[] encryptField(final byte[] input, final int inputOffset, final int inputLength) {
        return this._$2558(input, inputOffset, inputLength);
    }
    
    public final String decryptMsg(final String enc_pakt) {
        final int i = 0;
        try {
            if (enc_pakt == null || enc_pakt.length() <= 0) {
                throw new NullPointerException();
            }
            final int encpaktlen = enc_pakt.length();
            if (encpaktlen < 4) {
                return "FAILURE : decryption failed";
            }
            byte[] enc_pakt_hex = new byte[encpaktlen];
            byte[] enc_pakt_bin = new byte[encpaktlen / 2];
            final byte[] pakt_bin = new byte[encpaktlen / 2];
            enc_pakt_hex = enc_pakt.getBytes();
            enc_pakt_bin = this._$2543(enc_pakt_hex);
            this._$2526.DES3CBCInit(this._$2513, this._$2515);
            this._$2526.DES3CBCDecrypt(enc_pakt_bin, pakt_bin);
            final short pakt_seq = this._$2557(pakt_bin[0], pakt_bin[1]);
            if (this._$1543 == null || this._$1543.compareTo("OFF") != 0) {
                if (this._$2512 - 10 > pakt_seq || pakt_seq > this._$2512 + 10) {
                    return "FAILURE : decryption failed";
                }
                if (this._$2512 < pakt_seq) {
                    this._$2512 = pakt_seq;
                }
            }
            else {
                this._$2512 = pakt_seq;
            }
            final byte[] final_bin = this._$2566(pakt_bin, 2);
            final String msg_str = this._$2549(final_bin);
            return msg_str;
        }
        catch (Exception e) {
            System.err.println(String.valueOf(String.valueOf(e.toString())).concat(": Error occured in decryptMsg method."));
            return "FAILURE : decryption failed";
        }
    }
    
    public final byte[] decryptMsg(final byte[] input, final int inputOffset, final int inputLength) {
        final int i = 0;
        try {
            if (input == null || inputLength <= 0) {
                throw new NullPointerException();
            }
            if (inputLength < 4) {
                return null;
            }
            final byte[] enc_pakt_bin = new byte[inputLength];
            final byte[] pakt_bin = new byte[inputLength];
            System.arraycopy(input, inputOffset, enc_pakt_bin, 0, inputLength);
            this._$2526.DES3CBCInit(this._$2513, this._$2515);
            this._$2526.DES3CBCDecrypt(enc_pakt_bin, pakt_bin);
            final short pakt_seq = this._$2557(pakt_bin[0], pakt_bin[1]);
            if (this._$1543 == null || this._$1543.compareTo("OFF") != 0) {
                if (this._$2512 - 10 > pakt_seq || pakt_seq > this._$2512 + 10) {
                    return null;
                }
                if (this._$2512 < pakt_seq) {
                    this._$2512 = pakt_seq;
                }
            }
            else {
                this._$2512 = pakt_seq;
            }
            final byte[] final_bin = this._$2566(pakt_bin, 2);
            return final_bin;
        }
        catch (Exception e) {
            System.err.println(String.valueOf(String.valueOf(e.toString())).concat(": Error occured in decryptMsg method."));
            return null;
        }
    }
    
    public final void addPIN(final String PINvalue) {
        this._$2519 = null;
        this._$2520 = 0;
        try {
            if (PINvalue == null || PINvalue.length() <= 0) {
                throw new NullPointerException();
            }
            this._$2520 = PINvalue.length();
            this._$2519 = new byte[this._$2520];
            this._$2519 = PINvalue.getBytes();
        }
        catch (Exception e) {
            System.err.println(String.valueOf(String.valueOf(e.toString())).concat(": Error occured in addPIN method."));
        }
    }
    
    public final void addNewPIN(final String NewPINvalue) {
        this._$2522 = 0;
        this._$2521 = null;
        try {
            if (NewPINvalue == null || NewPINvalue.length() <= 0) {
                throw new NullPointerException();
            }
            this._$2522 = NewPINvalue.length();
            this._$2521 = new byte[this._$2522];
            this._$2521 = NewPINvalue.getBytes();
        }
        catch (Exception e) {
            System.err.println(String.valueOf(String.valueOf(e.toString())).concat(": Error occured in addNewPIN method."));
        }
    }
    
    public final boolean auth() {
        byte[] rb = new byte[16];
        this._$2523 = null;
        try {
            byte[] Ra_hex = new byte[32];
            byte[] Ra_bin = new byte[16];
            Ra_hex = this._$1540.getBytes();
            Ra_bin = this._$2543(Ra_hex);
            if (this._$2520 > 24 || this._$2520 <= 0) {
                return false;
            }
            rb = this._$2544();
            final byte[] Ra_Rb_PIN = new byte[35 + this._$2520];
            Ra_Rb_PIN[0] = 16;
            for (int i = 0; i < 16; ++i) {
                Ra_Rb_PIN[i + 1] = rb[i];
            }
            Ra_Rb_PIN[17] = 16;
            for (int i = 0; i < 16; ++i) {
                Ra_Rb_PIN[i + 2 + 16] = Ra_bin[i];
            }
            Ra_Rb_PIN[34] = (byte)this._$2520;
            for (int i = 0; i < this._$2520; ++i) {
                Ra_Rb_PIN[i + 3 + 32] = this._$2519[i];
            }
            final int critical_blk_len = this._$2520 + 3 + 32;
            final byte[] PKCS_msg = new byte[this._$2518];
            PKCS_msg[0] = 0;
            PKCS_msg[1] = 2;
            final int curr_ptr = this._$2518 - critical_blk_len;
            final Date d = new Date();
            final Random r = new Random(d.getTime());
            for (int i = 2; i < curr_ptr - 1; ++i) {
                PKCS_msg[i] = (byte)r.nextInt();
                while (PKCS_msg[i] == 0) {
                    PKCS_msg[i] = (byte)r.nextInt();
                }
            }
            PKCS_msg[curr_ptr - 1] = 0;
            for (int i = curr_ptr; i < curr_ptr + critical_blk_len; ++i) {
                PKCS_msg[i] = Ra_Rb_PIN[i - curr_ptr];
            }
            return this._$2575(PKCS_msg);
        }
        catch (Exception e) {
            System.err.println(String.valueOf(String.valueOf(e.toString())).concat(": Error occured in auth method."));
            return false;
        }
    }
    
    public final boolean pinch() {
        byte[] rb = new byte[16];
        this._$2523 = null;
        try {
            byte[] Ra_hex = new byte[32];
            byte[] Ra_bin = new byte[16];
            Ra_hex = this._$1540.getBytes();
            Ra_bin = this._$2543(Ra_hex);
            if (this._$2520 > 24 || this._$2520 <= 0) {
                return false;
            }
            if (this._$2522 > 24 || this._$2522 <= 0) {
                return false;
            }
            rb = this._$2544();
            final byte[] Ra_Rb_PIN = new byte[35 + this._$2520 + 1 + this._$2522];
            Ra_Rb_PIN[0] = 16;
            for (int i = 0; i < 16; ++i) {
                Ra_Rb_PIN[i + 1] = rb[i];
            }
            Ra_Rb_PIN[17] = 16;
            for (int i = 0; i < 16; ++i) {
                Ra_Rb_PIN[i + 2 + 16] = Ra_bin[i];
            }
            Ra_Rb_PIN[34] = (byte)this._$2520;
            for (int i = 0; i < this._$2520; ++i) {
                Ra_Rb_PIN[i + 3 + 32] = this._$2519[i];
            }
            Ra_Rb_PIN[35 + this._$2520] = (byte)this._$2522;
            for (int i = 0; i < this._$2522; ++i) {
                Ra_Rb_PIN[i + 4 + 32 + this._$2520] = this._$2521[i];
            }
            final int critical_blk_len = this._$2520 + this._$2522 + 4 + 32;
            final byte[] PKCS_msg = new byte[this._$2518];
            PKCS_msg[0] = 0;
            PKCS_msg[1] = 2;
            final int curr_ptr = this._$2518 - critical_blk_len;
            final Date d = new Date();
            final Random r = new Random(d.getTime());
            for (int i = 2; i < curr_ptr - 1; ++i) {
                PKCS_msg[i] = (byte)r.nextInt();
                while (PKCS_msg[i] == 0) {
                    PKCS_msg[i] = (byte)r.nextInt();
                }
            }
            PKCS_msg[curr_ptr - 1] = 0;
            for (int i = curr_ptr; i < curr_ptr + critical_blk_len; ++i) {
                PKCS_msg[i] = Ra_Rb_PIN[i - curr_ptr];
            }
            return this._$2575(PKCS_msg);
        }
        catch (Exception e) {
            System.err.println(String.valueOf(String.valueOf(e.toString())).concat(": Error occured in pinch method."));
            return false;
        }
    }
    
    public String getEPIN() {
        if (this._$2523 == null) {
            if (this._$2520 <= 0 || this._$2520 > 24) {
                return "Invalid PIN length";
            }
            if (this._$2522 <= 0 || this._$2522 > 24) {
                return "Invalid New PIN length";
            }
        }
        return this._$2549(this._$2523);
    }
    
    public void load_PublicKey(final String pubkey_in) {
        try {
            if (pubkey_in == null || pubkey_in.length() <= 0) {
                throw new NullPointerException();
            }
            String pubkey;
            if (pubkey_in.regionMatches(0, "30", 0, 2)) {
                pubkey = this._$2588(pubkey_in);
            }
            else {
                pubkey = new String(pubkey_in);
                this._$2518 = 128;
            }
            int PKey_length = pubkey.length();
            final byte[] PKey = new byte[PKey_length + 1];
            byte[] PKey_tmp = new byte[PKey_length + 1];
            PKey_tmp = pubkey.getBytes();
            int i;
            if (PKey_length % 2 != 0) {
                ++PKey_length;
                PKey[0] = 0;
                i = 1;
            }
            else {
                i = 0;
            }
            for (int j = 0; i < PKey_length; ++i, ++j) {
                PKey[i] = PKey_tmp[j];
            }
            byte[] BinKey = new byte[PKey_length / 2];
            BinKey = this._$2543(PKey);
            int low = this._$2589(BinKey[1]);
            int high = this._$2589(BinKey[2]);
            int len_n = high * 256 + low;
            low = this._$2589(BinKey[3]);
            high = this._$2589(BinKey[4]);
            int len_e = high * 256 + low;
            if (len_n % 8 == 0) {
                len_n /= 8;
            }
            else {
                len_n = len_n / 8 + 1;
            }
            if (len_e % 8 == 0) {
                len_e /= 8;
            }
            else {
                len_e = len_e / 8 + 1;
            }
            final byte[] PK_n = new byte[len_n];
            for (i = 0; i <= len_n - 1; ++i) {
                PK_n[i] = BinKey[5 + i];
            }
            final byte[] PK_e = new byte[len_e];
            for (i = 0; i <= len_e - 1; ++i) {
                PK_e[i] = BinKey[5 + len_n + i];
            }
            this.KeyInit(PK_n, PK_e);
        }
        catch (Exception e) {
            System.err.println(String.valueOf(String.valueOf(e.toString())).concat(": Error occured in load_PublicKey method."));
        }
    }
    
    private final byte[] _$2548(final byte[] bin) {
        try {
            if (bin == null || bin.length <= 0) {
                throw new NullPointerException();
            }
            final int binlen = bin.length;
            final byte[] hex = new byte[binlen * 2];
            for (int i = 0; i < binlen; ++i) {
                byte temp = (byte)(bin[i] >>> 4);
                temp &= 0xF;
                hex[i * 2] = (byte)((temp > 9) ? (temp - 10 + 65) : (temp + 48));
                temp = (byte)(bin[i] & 0xF);
                hex[i * 2 + 1] = (byte)((temp > 9) ? (temp - 10 + 65) : (temp + 48));
            }
            return hex;
        }
        catch (Exception e) {
            System.err.println(String.valueOf(String.valueOf(e.toString())).concat(": Error occured while converting the binary data."));
            return null;
        }
    }
    
    private final byte[] _$2543(final byte[] hex) {
        byte low = 0;
        byte high = 0;
        try {
            if (hex == null || hex.length <= 0) {
                throw new NullPointerException();
            }
            final int hexlen = hex.length;
            final byte[] bin = new byte[hexlen / 2];
            for (int i = 0; i < hexlen / 2; ++i) {
                low = 0;
                high = 0;
                if (hex[2 * i] >= 48 && hex[2 * i] <= 57) {
                    high = (byte)(hex[2 * i] - 48);
                }
                if (hex[2 * i] >= 65 && hex[2 * i] <= 70) {
                    high = (byte)(hex[2 * i] - 65 + 10);
                }
                if (hex[2 * i + 1] >= 48 && hex[2 * i + 1] <= 57) {
                    low = (byte)(hex[2 * i + 1] - 48);
                }
                if (hex[2 * i + 1] >= 65 && hex[2 * i + 1] <= 70) {
                    low = (byte)(hex[2 * i + 1] - 65 + 10);
                }
                bin[i] = (byte)(high * 16 + low);
            }
            return bin;
        }
        catch (Exception e) {
            System.err.println(String.valueOf(String.valueOf(e.toString())).concat(": Error occured while converting the hex data."));
            return null;
        }
    }
    
    private byte[] _$2544() {
        final Date d = new Date();
        try {
            final Random randomGenerator = new Random(d.getTime());
            final byte[] Rb_bin = new byte[16];
            for (int i = 0; i < 16; ++i) {
                Rb_bin[i] = (byte)(randomGenerator.nextInt() & 0xFF);
            }
            return Rb_bin;
        }
        catch (Exception e) {
            System.err.println(e.toString());
            return null;
        }
    }
    
    private void _$2545() {
        final Date d = new Date();
        final Random r = new Random();
        try {
            final Random randomGenerator = new Random(d.getTime() + r.nextInt());
            byte b;
            do {
                final byte[] $2513 = this._$2513;
                final int n = 0;
                b = (byte)(randomGenerator.nextInt() & 0xFF);
                $2513[n] = b;
            } while (b == 0);
            for (int i = 1; i < 16; ++i) {
                this._$2513[i] = (byte)(randomGenerator.nextInt() & 0xFF);
            }
        }
        catch (Exception e) {
            System.err.println(e.toString());
        }
    }
    
    private short _$2557(final byte high, final byte low) {
        int num_bin = high << 8 | 0xFF;
        num_bin &= (0xFFFFFF00 | low);
        return (short)num_bin;
    }
    
    private byte[] _$2598(final short num) {
        try {
            final byte[] num_byte = { 0, (byte)num };
            final int temp_num = num >>> 8;
            num_byte[0] = (byte)temp_num;
            return num_byte;
        }
        catch (Exception e) {
            System.err.println(e.toString());
            return null;
        }
    }
    
    private String _$2558(final String InMsg) {
        int retval = 0;
        try {
            if (InMsg == null || InMsg.length() <= 0) {
                throw new NullPointerException();
            }
            int paktlen = InMsg.length() + 2;
            final int padding = (8 - paktlen % 8) % 8;
            paktlen += padding;
            final byte[] pakt_bin = new byte[paktlen];
            final byte[] enc_pakt_bin = new byte[paktlen];
            byte[] enc_pakt_hex = new byte[paktlen * 2];
            byte[] seq_bin = new byte[2];
            ++this._$2512;
            seq_bin = this._$2598(this._$2512);
            pakt_bin[0] = seq_bin[0];
            pakt_bin[1] = seq_bin[1];
            final byte[] buffer = InMsg.getBytes();
            for (int i = 0, j = 2; i < buffer.length; ++i, ++j) {
                pakt_bin[j] = buffer[i];
            }
            for (int i = 0; i < padding; ++i) {
                pakt_bin[paktlen - 1 - i] = 0;
            }
            this._$2526.DES3CBCInit(this._$2513, this._$2515);
            retval = this._$2526.DES3CBCEncrypt(pakt_bin, enc_pakt_bin);
            if (retval == 1) {
                System.err.println("Error occured during encryption process.");
                return null;
            }
            enc_pakt_hex = this._$2548(enc_pakt_bin);
            final String enc_pakt_str = this._$2549(enc_pakt_hex);
            return enc_pakt_str;
        }
        catch (Exception e) {
            System.err.println(String.valueOf(String.valueOf(e.toString())).concat(": Error occured during encryption process."));
            return null;
        }
    }
    
    private byte[] _$2558(final byte[] input, final int inputOffset, final int inputLength) {
        final byte[] buffer = new byte[inputLength];
        int retval = 0;
        try {
            if (input == null || inputLength <= 0) {
                throw new NullPointerException();
            }
            int paktlen = inputLength + 2;
            final int padding = (8 - paktlen % 8) % 8;
            paktlen += padding;
            final byte[] pakt_bin = new byte[paktlen];
            final byte[] enc_pakt_bin = new byte[paktlen];
            byte[] seq_bin = new byte[2];
            ++this._$2512;
            seq_bin = this._$2598(this._$2512);
            pakt_bin[0] = seq_bin[0];
            pakt_bin[1] = seq_bin[1];
            System.arraycopy(input, inputOffset, buffer, 0, inputLength);
            for (int i = 0, j = 2; i < buffer.length; ++i, ++j) {
                pakt_bin[j] = buffer[i];
            }
            for (int i = 0; i < padding; ++i) {
                pakt_bin[paktlen - 1 - i] = 0;
            }
            this._$2526.DES3CBCInit(this._$2513, this._$2515);
            retval = this._$2526.DES3CBCEncrypt(pakt_bin, enc_pakt_bin);
            if (retval == 1) {
                System.err.println("Error occured during encryption process.");
                return null;
            }
            return enc_pakt_bin;
        }
        catch (Exception e) {
            System.err.println(String.valueOf(String.valueOf(e.toString())).concat(": Error occured during encryption process."));
            return null;
        }
    }
    
    private String _$2549(final byte[] inbyte) {
        try {
            if (inbyte == null || inbyte.length <= 0) {
                throw new NullPointerException("java.lang.NullPointerException: Error occured while converting the data type of data.");
            }
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
        String output;
        if (this._$1541 == null) {
            output = new String(inbyte);
        }
        else {
            try {
                output = new String(inbyte, this._$1541);
            }
            catch (UnsupportedEncodingException ioe) {
                return "FAILURE: Unsupported Encoding";
            }
        }
        return output;
    }
    
    private final String _$2612(final String s) {
        final ByteArrayOutputStream out = new ByteArrayOutputStream(s.length() * 4 / 3);
        try {
            for (int i = 0; i < s.length() - 3; i += 3) {
                final int c1 = s.charAt(i) >> 2 & '?';
                final int c2 = (s.charAt(i) << 4 & '0') | (s.charAt(i + 1) >> 4 & '\u000f');
                final int c3 = (s.charAt(i + 1) << 2 & '<') | (s.charAt(i + 2) >> 6 & '\u0003');
                final int c4 = s.charAt(i + 2) & '?';
                out.write(this._$2524[c1]);
                out.write(this._$2524[c2]);
                out.write(this._$2524[c3]);
                out.write(this._$2524[c4]);
            }
            final int len = s.length();
            if (len % 3 == 1) {
                final int c1 = s.charAt(len - 1) >> 2 & '?';
                final int c2 = s.charAt(len - 1) << 4 & '0';
                out.write(this._$2524[c1]);
                out.write(this._$2524[c2]);
                out.write(126);
                out.write(126);
            }
            else if (len % 3 == 2) {
                final int c1 = s.charAt(len - 2) >> 2 & '?';
                final int c2 = (s.charAt(len - 2) << 4 & '0') | (s.charAt(len - 1) >> 4 & '\u000f');
                final int c3 = s.charAt(len - 1) << 2 & '<';
                out.write(this._$2524[c1]);
                out.write(this._$2524[c2]);
                out.write(this._$2524[c3]);
                out.write(126);
            }
            else {
                final int c1 = s.charAt(len - 3) >> 2 & '?';
                final int c2 = (s.charAt(len - 3) << 4 & '0') | (s.charAt(len - 2) >> 4 & '\u000f');
                final int c3 = (s.charAt(len - 2) << 2 & '<') | (s.charAt(len - 1) >> 6 & '\u0003');
                final int c4 = s.charAt(len - 1) & '?';
                out.write(this._$2524[c1]);
                out.write(this._$2524[c2]);
                out.write(this._$2524[c3]);
                out.write(this._$2524[c4]);
            }
            return out.toString();
        }
        catch (Exception e) {
            System.err.println(e.toString());
            return null;
        }
    }
    
    private final String _$2619(final String s) {
        final ByteArrayOutputStream tmp = new ByteArrayOutputStream(s.length());
        final ByteArrayOutputStream out = new ByteArrayOutputStream(s.length() * 3 / 4);
        int numPaddingBytes = 0;
        try {
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
                    tmp.write(s.charAt(i) - 'A');
                }
                else if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                    tmp.write(s.charAt(i) - 'a' + '\u001a');
                }
                else if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    tmp.write(s.charAt(i) - '0' + '4');
                }
                else if (s.charAt(i) == '-') {
                    tmp.write(62);
                }
                else if (s.charAt(i) == '^') {
                    tmp.write(63);
                }
                else if (s.charAt(i) == '~') {
                    ++numPaddingBytes;
                }
            }
            final String s2 = tmp.toString();
            int i;
            for (i = 0; i < s.length() - 4; i += 4) {
                final int c1 = (s2.charAt(i) << 2 & '\u00fc') | (s2.charAt(i + 1) >> 4 & '\u0003');
                final int c2 = (s2.charAt(i + 1) << 4 & '\u00f0') | (s2.charAt(i + 2) >> 2 & '\u000f');
                final int c3 = (s2.charAt(i + 2) << 6 & '\u00c0') | s2.charAt(i + 3);
                out.write(c1);
                out.write(c2);
                out.write(c3);
            }
            final int c1 = (s2.charAt(i) << 2 & '\u00fc') | (s2.charAt(i + 1) >> 4 & '\u0003');
            out.write(c1);
            if (numPaddingBytes <= 1) {
                final int c2 = (s2.charAt(i + 1) << 4 & '\u00f0') | (s2.charAt(i + 2) >> 2 & '\u000f');
                out.write(c2);
                if (numPaddingBytes == 0) {
                    final int c3 = (s2.charAt(i + 2) << 6 & '\u00c0') | s2.charAt(i + 3);
                    out.write(c3);
                }
            }
            return out.toString();
        }
        catch (Exception e) {
            System.err.println(e.toString());
            return null;
        }
    }
    
    private final boolean _$2575(final byte[] PKCS_msg) {
        try {
            if (PKCS_msg == null || PKCS_msg.length <= 0) {
                throw new NullPointerException();
            }
            this._$2525.RSAEncryptInit(this._$2516, this._$2517);
            final byte[] enc_PIN = this._$2525.RSAEncrypt(PKCS_msg);
            this._$2523 = new byte[2 * enc_PIN.length];
            this._$2523 = this._$2548(enc_PIN);
            final String temp2 = this._$2549(this._$2523);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(String.valueOf(String.valueOf(e.toString())).concat(": Error occured while encrypting a message."));
            return false;
        }
        return true;
    }
    
    private final int _$2589(final byte b) {
        return b & 0xFF;
    }
    
    private final int _$2624(final byte field) {
        int fInt = 0;
        int modval = 0;
        try {
            fInt = this._$2589(field);
            modval = fInt % 128;
            if (modval < 15) {
                return modval;
            }
            return fInt;
        }
        catch (Exception e) {
            System.err.println(e.toString());
            return -1;
        }
    }
    
    private final int _$2628(final byte[] indata, final int offset, final int len) {
        int value = 0;
        int i = 0;
        try {
            for (i = 0; i < len; ++i) {
                value *= 256;
                value += this._$2589(indata[offset + i]);
            }
            return value;
        }
        catch (Exception e) {
            System.err.println(e.toString());
            return -1;
        }
    }
    
    private final String _$2588(final String PubKey) {
        int modlen = 0;
        int explen = 0;
        int pflen = 0;
        int mflen = 0;
        int eflen = 0;
        int ptrpos = 0;
        int oldbkeylen = 0;
        int explenPos = 0;
        int i = 0;
        int mflenCount = 0;
        int eflenCount = 0;
        try {
            final int pubkey_len = PubKey.length();
            byte[] newKey = new byte[pubkey_len + 1];
            newKey = PubKey.getBytes();
            byte[] newBinKey = new byte[pubkey_len / 2];
            newBinKey = this._$2543(newKey);
            pflen = this._$2624(newBinKey[1]);
            if (this._$2589(newBinKey[3 + pflen]) > 128) {
                mflen = (mflenCount = this._$2624(newBinKey[3 + pflen]));
                ptrpos = 3 + pflen + 1;
            }
            else {
                ptrpos = 3 + pflen;
                mflen = 0;
                mflenCount = 1;
            }
            modlen = this._$2628(newBinKey, ptrpos, mflenCount);
            this._$2518 = modlen;
            if (this._$2589(newBinKey[5 + pflen + mflen + modlen]) > 128) {
                eflen = this._$2624(newBinKey[5 + pflen + mflen + modlen]);
                explenPos = 6 + pflen + mflen + modlen;
                eflenCount = eflen;
            }
            else {
                eflen = 0;
                eflenCount = 1;
                explenPos = 5 + pflen + mflen + modlen;
            }
            explen = this._$2628(newBinKey, explenPos, eflenCount);
            oldbkeylen = 5 + explen + modlen;
            final byte[] oldBinKey = new byte[oldbkeylen];
            oldBinKey[0] = 1;
            oldBinKey[1] = (byte)(modlen * 8 % 256);
            oldBinKey[2] = (byte)(modlen * 8 / 256);
            ptrpos += mflen;
            for (i = 0; i < modlen; ++i) {
                oldBinKey[5 + i] = newBinKey[ptrpos + i];
            }
            oldBinKey[3] = (byte)(explen * 8 % 256);
            oldBinKey[4] = (byte)(explen * 8 / 256);
            ptrpos = ptrpos + modlen + 2 + eflen;
            for (i = 0; i < explen; ++i) {
                oldBinKey[5 + modlen + i] = newBinKey[ptrpos + i];
            }
            byte[] oldKey = new byte[oldBinKey.length * 2];
            oldKey = this._$2548(oldBinKey);
            return this._$2549(oldKey);
        }
        catch (Exception e) {
            System.err.println(e.toString());
            return null;
        }
    }
    
    public final boolean convert() {
        return this.pinch();
    }
    
    public final boolean create() {
        return this.auth();
    }
    
    public void getVersion() {
        System.out.println("Version 1.3 build 002");
        System.out.println("Version Date: 05 February 2004");
    }
    
    public String getVersionStr() {
        final StringBuffer version = new StringBuffer();
        version.append("Version 1.3 build 002\n");
        version.append("Version Date: 05 February 2004\n");
        return version.toString();
    }
    
    public String generateHash(final String in, final String Algo) {
        String outStr = null;
        try {
            if (Algo.compareTo("MD5") != 0 && Algo.compareTo("md5") != 0) {
                throw new Exception("HASH Algo not Supported");
            }
            final MD5 MD5obj = new MD5();
            final byte[] out = new byte[16];
            MD5obj.md5_algo(in.length(), in.getBytes(), out);
            outStr = new String(this._$2548(out));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        }
        return outStr;
    }
    
    public boolean isEval() {
        return this._$2527;
    }
    
    public boolean isExpired() {
        return this._$1544;
    }
    
    private byte[] _$2566(final byte[] input, final int inputOffset) {
        int length = 0;
        byte[] output = null;
        final int inputLen = input.length;
        if (this._$2651(input)) {
            final int paddingLen = this._$2589(input[inputLen - 1]);
            length = inputLen - paddingLen - inputOffset;
        }
        else {
            int i;
            for (i = 0, i = inputLen - 1; input[i] == 0; --i) {}
            length = i + 1 - inputOffset;
        }
        output = new byte[length];
        System.arraycopy(input, inputOffset, output, 0, length);
        return output;
    }
    
    private boolean _$2651(final byte[] input) {
        final int inputLen = input.length;
        final int paddingLen = this._$2589(input[inputLen - 1]);
        if (paddingLen < 1 || paddingLen > 8) {
            return false;
        }
        int i = inputLen - 1;
        for (int j = 0; j < paddingLen; ++j) {
            if (this._$2589(input[i]) != paddingLen) {
                return false;
            }
            --i;
        }
        return true;
    }
}
