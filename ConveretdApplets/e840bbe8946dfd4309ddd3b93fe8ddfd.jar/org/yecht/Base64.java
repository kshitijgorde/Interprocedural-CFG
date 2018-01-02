// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht;

public class Base64
{
    private static final byte[] b64_table;
    private static final int[] b64_xtable;
    
    public static byte[] enc(final Pointer _s, final int _len) {
        int len = _len;
        int i = 0;
        final byte padding = 61;
        final byte[] buff = new byte[1 + len * 4 / 3 + 6];
        final byte[] sb = _s.buffer;
        int s = _s.start;
        while (len >= 3) {
            buff[i++] = Base64.b64_table[0x3F & sb[s] >> 2];
            buff[i++] = Base64.b64_table[0x3F & ((sb[s] << 4 & 0x30) | (sb[s + 1] >> 4 & 0xF))];
            buff[i++] = Base64.b64_table[0x3F & ((sb[s + 1] << 2 & 0x3C) | (sb[s + 2] >> 6 & 0x3))];
            buff[i++] = Base64.b64_table[0x3F & sb[s + 2]];
            s += 3;
            len -= 3;
        }
        if (len == 2) {
            buff[i++] = Base64.b64_table[0x3F & sb[s] >> 2];
            buff[i++] = Base64.b64_table[0x3F & ((sb[s] << 4 & 0x30) | (sb[s + 1] >> 4 & 0xF))];
            buff[i++] = Base64.b64_table[0x3F & ((sb[s + 1] << 2 & 0x3C) | 0x0)];
            buff[i++] = padding;
        }
        else if (len == 1) {
            buff[i++] = Base64.b64_table[0x3F & sb[s] >> 2];
            buff[i++] = Base64.b64_table[0x3F & ((sb[s] << 4 & 0x30) | 0x0)];
            buff[i++] = padding;
            buff[i++] = padding;
        }
        buff[i++] = 10;
        buff[i] = 0;
        return buff;
    }
    
    public static byte[] dec(final Pointer _s, final int _len) {
        final byte[] sb = _s.buffer;
        int s = _s.start;
        int a = -1;
        int b = -1;
        int c = 0;
        final byte[] ptrb = new byte[_len];
        System.arraycopy(sb, s, ptrb, 0, _len);
        final int ptr = 0;
        int end = 0;
        int send;
        for (send = s + _len; s < send; s += 4) {
            while (sb[s] == 13 || sb[s] == 10) {
                ++s;
            }
            if ((a = Base64.b64_xtable[sb[s + 0]]) == -1) {
                break;
            }
            if ((b = Base64.b64_xtable[sb[s + 1]]) == -1) {
                break;
            }
            if ((c = Base64.b64_xtable[sb[s + 2]]) == -1) {
                break;
            }
            final int d;
            if ((d = Base64.b64_xtable[sb[s + 3]]) == -1) {
                break;
            }
            ptrb[end++] = (byte)(a << 2 | b >> 4);
            ptrb[end++] = (byte)(b << 4 | c >> 2);
            ptrb[end++] = (byte)(c << 6 | d);
        }
        if (a != -1 && b != -1) {
            if (s + 2 < send && sb[s + 2] == 61) {
                ptrb[end++] = (byte)(a << 2 | b >> 4);
            }
            if (c != -1 && s + 3 < send && sb[s + 3] == 61) {
                ptrb[end++] = (byte)(a << 2 | b >> 4);
                ptrb[end++] = (byte)(b << 4 | c >> 2);
            }
        }
        ptrb[end] = 0;
        return ptrb;
    }
    
    static {
        b64_table = BytecodeNodeHandler.bytes("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
        b64_xtable = new int[256];
        for (int i = 0; i < 256; ++i) {
            Base64.b64_xtable[i] = -1;
        }
        for (int i = 0; i < 64; ++i) {
            Base64.b64_xtable[Base64.b64_table[i]] = i;
        }
    }
}
