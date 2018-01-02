// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsID
{
    public int m0;
    public short m1;
    public short m2;
    public byte[] m3;
    public static final int sizeof = 16;
    static final String zeros = "00000000";
    
    public nsID() {
        this.m3 = new byte[8];
    }
    
    public nsID(final String s) {
        this.m3 = new byte[8];
        this.Parse(s);
    }
    
    public boolean Equals(final nsID nsID) {
        final int nsID_new = XPCOM.nsID_new();
        XPCOM.memmove(nsID_new, this, 16);
        final int nsID_new2 = XPCOM.nsID_new();
        XPCOM.memmove(nsID_new2, nsID, 16);
        final boolean b = XPCOM.nsID_Equals(nsID_new, nsID_new2) != 0;
        XPCOM.nsID_delete(nsID_new);
        XPCOM.nsID_delete(nsID_new2);
        return b;
    }
    
    public void Parse(final String s) {
        if (s == null) {
            throw new Error();
        }
        int i;
        for (i = 0; i < 8; ++i) {
            final int digit = Character.digit(s.charAt(i), 16);
            if (digit == -1) {
                throw new Error();
            }
            this.m0 = (this.m0 << 4) + digit;
        }
        if (s.charAt(i++) != '-') {
            throw new Error();
        }
        while (i < 13) {
            final int digit2 = Character.digit(s.charAt(i), 16);
            if (digit2 == -1) {
                throw new Error();
            }
            this.m1 = (short)((this.m1 << 4) + digit2);
            ++i;
        }
        if (s.charAt(i++) != '-') {
            throw new Error();
        }
        while (i < 18) {
            final int digit3 = Character.digit(s.charAt(i), 16);
            if (digit3 == -1) {
                throw new Error();
            }
            this.m2 = (short)((this.m2 << 4) + digit3);
            ++i;
        }
        if (s.charAt(i++) != '-') {
            throw new Error();
        }
        while (i < 21) {
            final int digit4 = Character.digit(s.charAt(i), 16);
            if (digit4 == -1) {
                throw new Error();
            }
            this.m3[0] = (byte)((this.m3[0] << 4) + digit4);
            ++i;
        }
        while (i < 23) {
            final int digit5 = Character.digit(s.charAt(i), 16);
            if (digit5 == -1) {
                throw new Error();
            }
            this.m3[1] = (byte)((this.m3[1] << 4) + digit5);
            ++i;
        }
        if (s.charAt(i++) != '-') {
            throw new Error();
        }
        while (i < 26) {
            final int digit6 = Character.digit(s.charAt(i), 16);
            if (digit6 == -1) {
                throw new Error();
            }
            this.m3[2] = (byte)((this.m3[2] << 4) + digit6);
            ++i;
        }
        while (i < 28) {
            final int digit7 = Character.digit(s.charAt(i), 16);
            if (digit7 == -1) {
                throw new Error();
            }
            this.m3[3] = (byte)((this.m3[3] << 4) + digit7);
            ++i;
        }
        while (i < 30) {
            final int digit8 = Character.digit(s.charAt(i), 16);
            if (digit8 == -1) {
                throw new Error();
            }
            this.m3[4] = (byte)((this.m3[4] << 4) + digit8);
            ++i;
        }
        while (i < 32) {
            final int digit9 = Character.digit(s.charAt(i), 16);
            if (digit9 == -1) {
                throw new Error();
            }
            this.m3[5] = (byte)((this.m3[5] << 4) + digit9);
            ++i;
        }
        while (i < 34) {
            final int digit10 = Character.digit(s.charAt(i), 16);
            if (digit10 == -1) {
                throw new Error();
            }
            this.m3[6] = (byte)((this.m3[6] << 4) + digit10);
            ++i;
        }
        while (i < 36) {
            final int digit11 = Character.digit(s.charAt(i), 16);
            if (digit11 == -1) {
                throw new Error();
            }
            this.m3[7] = (byte)((this.m3[7] << 4) + digit11);
            ++i;
        }
    }
    
    static String toHex(final int n, final int n2) {
        String s = Integer.toHexString(n).toUpperCase();
        final int length = s.length();
        if (length > n2) {
            s = s.substring(length - n2);
        }
        return String.valueOf("00000000".substring(0, Math.max(0, n2 - length))) + s;
    }
    
    public String toString() {
        return String.valueOf('{') + toHex(this.m0, 8) + '-' + toHex(this.m1, 4) + '-' + toHex(this.m2, 4) + '-' + toHex(this.m3[0], 2) + toHex(this.m3[1], 2) + '-' + toHex(this.m3[2], 2) + toHex(this.m3[3], 2) + toHex(this.m3[4], 2) + toHex(this.m3[5], 2) + toHex(this.m3[6], 2) + toHex(this.m3[7], 2) + '}';
    }
}
