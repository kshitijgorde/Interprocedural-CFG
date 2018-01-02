// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

public class c
{
    private N a;
    
    public c() {
    }
    
    public static void a(final byte[] array, int n, final byte[] array2, int n2, final byte[] array3, int n3) {
        array2[n2++] = (byte)(0xD0 | (array[n] & 0x3C) >> 2);
        array2[n2++] = (byte)((array[n] & 0x3) << 6 | (array[n] & 0xC0) >> 6 | (array[++n] & 0xF) << 2);
        array2[n2++] = (byte)((array[n] & 0xF0) >> 1 | (array[++n] & 0x1) << 7 | (array[n] & 0x38) >> 3);
        array2[n2++] = (byte)((array[n] & 0x6) << 5 | (array[n] & 0xC0) >> 4 | (array[++n] & 0x3) << 4 | (array[n] & 0x30) >> 4);
        array2[n2++] = (byte)((array[n] & 0xC) << 4 | (array[n] & 0xC0) >> 3 | (array[++n] & 0x1) << 5 | (array[n] & 0xE) >> 1);
        array2[n2++] = (byte)((array[n] & 0xF0) >> 3 | (array[++n] & 0x7) << 5 | (array[n] & 0x10) >> 4);
        array2[n2++] = (byte)((array[n] & 0x8) << 4 | (array[n] & 0x60) | (array[++n] & 0x1F));
        array2[n2++] = (byte)((array[--n] & 0x80) | (array[++n] & 0xE0) >> 1 | (array[++n] & 0x7) << 1 | (array[n] & 0x20) >> 5);
        array2[n2++] = (byte)((array[n] & 0x18) << 3 | (array[n] & 0xC0) >> 3 | (array[++n] & 0x1) << 5 | (array[n] & 0xE) >> 1);
        array2[n2++] = (byte)((array[n] & 0x70) << 1 | (array[n] & 0x80) >> 5 | (array[++n] & 0x3) << 3 | (array[n] & 0x18) >> 3);
        array2[n2++] = (byte)((array[n] & 0x4) << 5 | (array[n] & 0xE0) >> 1 | (array[++n] & 0x7) << 1 | (array[n] & 0x20) >> 5);
        array2[n2++] = (byte)((array[n] & 0x18) << 3 | (array[n] & 0xC0) >> 3 | (array[++n] & 0x1) << 5 | (array[n] & 0xE) >> 1);
        array2[n2++] = (byte)((array[n] & 0xF0) >> 3 | (array[++n] & 0x7) << 5 | (array[n] & 0x10) >> 4);
        array2[n2++] = (byte)((array[n] & 0x8) << 4 | (array[n] & 0x60) | (array[++n] & 0x1F));
        array2[n2++] = (byte)((array[--n] & 0x80) | (array[++n] & 0xE0) >> 1 | (array[++n] & 0x7) << 1 | (array[n] & 0x20) >> 5);
        array2[n2++] = (byte)((array[n] & 0x18) << 3 | (array[n] & 0xC0) >> 3 | (array[++n] & 0x1) << 5 | (array[n] & 0xE) >> 1);
        array2[n2++] = (byte)((array[n] & 0x70) << 1 | (array[n] & 0x80) >> 5 | (array[++n] & 0x3) << 3 | (array[n] & 0x18) >> 3);
        array2[n2++] = (byte)((array[n] & 0x4) << 5 | (array[n] & 0xE0) >> 1 | (array[++n] & 0x7) << 1 | (array[n] & 0x20) >> 5);
        array2[n2++] = (byte)((array[n] & 0x18) << 3 | (array[n] & 0xC0) >> 3 | (array[++n] & 0x1) << 5 | (array[n] & 0xE) >> 1);
        array2[n2++] = (byte)((array[n] & 0xF0) >> 3 | (array[++n] & 0x7) << 5 | (array[n] & 0x10) >> 4);
        array2[n2++] = (byte)((array[n] & 0x8) << 4 | (array[n] & 0x60) | (array[++n] & 0x1F));
        array2[n2++] = (byte)((array[--n] & 0x80) | (array[++n] & 0xE0) >> 1 | (array[++n] & 0x7) << 1 | (array[n] & 0x20) >> 5);
        array2[n2++] = (byte)((array[n] & 0x18) << 3 | (array[n] & 0xC0) >> 3 | (array[++n] & 0x1) << 5 | (array[n] & 0xE) >> 1);
        array2[n2++] = (byte)((array[n] & 0x70) << 1 | (array[n] & 0x80) >> 5 | (array[++n] & 0x3) << 3 | (array[n] & 0x18) >> 3);
        array2[n2++] = (byte)((array[n] & 0x4) << 5 | (array[n] & 0xE0) >> 1 | (array[++n] & 0x7) << 1 | (array[n] & 0x20) >> 5);
        array2[n2++] = (byte)((array[n] & 0x18) << 3 | (array[n] & 0xC0) >> 3 | (array[++n] & 0x1) << 5 | (array[n] & 0xE) >> 1);
        array2[n2++] = (byte)((array[n] & 0xF0) >> 3 | (array[++n] & 0x7) << 5 | (array[n] & 0x10) >> 4);
        array2[n2++] = (byte)((array[n] & 0x8) << 4 | (array[n] & 0x60) | (array[++n] & 0x1F));
        array2[n2++] = (byte)((array[--n] & 0x80) | (array[++n] & 0xE0) >> 1 | (array[++n] & 0x7) << 1 | (array[n] & 0x20) >> 5);
        array2[n2++] = (byte)((array[n] & 0x18) << 3 | (array[n] & 0xC0) >> 3 | (array[++n] & 0x1) << 5 | (array[n] & 0xE) >> 1);
        array2[n2++] = (byte)((array[n] & 0x70) << 1 | (array[n] & 0x80) >> 5 | (array[++n] & 0x3) << 3 | (array[n] & 0x18) >> 3);
        array2[n2++] = (byte)((array[n] & 0x4) << 5 | (array[n] & 0xE0) >> 1 | (array[++n] & 0x7) << 1 | (array[n] & 0x20) >> 5);
        array2[n2] = (byte)((array[n] & 0x18) << 3 | (array[n] & 0xC0) >> 3 | (array[++n] & 0x1) << 5 | (array[n] & 0xE) >> 1);
        array3[n3++] = (byte)(0xD0 | (array[n] & 0xC0) >> 6 | (array[++n] & 0x3) << 2);
        array3[n3++] = (byte)((array[--n] & 0x30) << 2 | (array[++n] & 0xFC) >> 2);
        array3[n3++] = (byte)((array[++n] & 0x1F) << 3 | (array[n] & 0x80) >> 7 | (array[++n] & 0x3) << 1);
        array3[n3++] = (byte)((array[--n] & 0x60) << 1 | (array[++n] & 0x3C) | (array[++n] & 0x3));
        array3[n3++] = (byte)((array[--n] & 0xC0) | (array[++n] & 0x1C) << 1 | (array[n] & 0xE0) >> 5);
        array3[n3++] = (byte)((array[++n] & 0x7F) << 1 | (array[++n] & 0x1));
        array3[n3++] = (byte)((array[--n] & 0x80) | (array[++n] & 0x6) << 4 | (array[n] & 0xF0) >> 4 | (array[++n] & 0x1) << 4);
        array3[n3++] = (byte)((array[--n] & 0x8) << 4 | (array[++n] & 0xE) << 3 | (array[n] & 0x70) >> 3 | (array[++n] & 0x2) >> 1);
        array3[n3++] = (byte)((array[--n] & 0x80) >> 1 | (array[++n] & 0x1) << 7 | (array[n] & 0x1C) << 1 | (array[n] & 0xE0) >> 5);
        array3[n3++] = (byte)((array[++n] & 0x7) << 5 | (array[n] & 0x38) >> 1 | (array[n] & 0x80) >> 7 | (array[++n] & 0x1) << 1);
        array3[n3++] = (byte)((array[--n] & 0x40) << 1 | (array[++n] & 0x70) >> 3 | (array[n] & 0xE) << 3 | (array[++n] & 0x2) >> 1);
        array3[n3++] = (byte)((array[--n] & 0x80) >> 1 | (array[++n] & 0x1) << 7 | (array[n] & 0x1C) << 1 | (array[n] & 0xE0) >> 5);
        array3[n3++] = (byte)((array[++n] & 0x7F) << 1 | (array[++n] & 0x1));
        array3[n3++] = (byte)((array[--n] & 0x80) | (array[++n] & 0x6) << 4 | (array[n] & 0xF0) >> 4 | (array[++n] & 0x1) << 4);
        array3[n3++] = (byte)((array[--n] & 0x8) << 4 | (array[++n] & 0xE) << 3 | (array[n] & 0x70) >> 3 | (array[++n] & 0x2) >> 1);
        array3[n3++] = (byte)((array[--n] & 0x80) >> 1 | (array[++n] & 0x1) << 7 | (array[n] & 0x1C) << 1 | (array[n] & 0xE0) >> 5);
        array3[n3++] = (byte)((array[++n] & 0x7) << 5 | (array[n] & 0x38) >> 1 | (array[n] & 0x80) >> 7 | (array[++n] & 0x1) << 1);
        array3[n3++] = (byte)((array[--n] & 0x40) << 1 | (array[++n] & 0x70) >> 3 | (array[n] & 0xE) << 3 | (array[++n] & 0x2) >> 1);
        array3[n3++] = (byte)((array[--n] & 0x80) >> 1 | (array[++n] & 0x1) << 7 | (array[n] & 0x1C) << 1 | (array[n] & 0xE0) >> 5);
        array3[n3++] = (byte)((array[++n] & 0x7F) << 1 | (array[++n] & 0x1));
        array3[n3++] = (byte)((array[--n] & 0x80) | (array[++n] & 0x6) << 4 | (array[n] & 0xF0) >> 4 | (array[++n] & 0x1) << 4);
        array3[n3++] = (byte)((array[--n] & 0x8) << 4 | (array[++n] & 0xE) << 3 | (array[n] & 0x70) >> 3 | (array[++n] & 0x2) >> 1);
        array3[n3++] = (byte)((array[--n] & 0x80) >> 1 | (array[++n] & 0x1) << 7 | (array[n] & 0x1C) << 1 | (array[n] & 0xE0) >> 5);
        array3[n3++] = (byte)((array[++n] & 0x7) << 5 | (array[n] & 0x38) >> 1 | (array[n] & 0x80) >> 7 | (array[++n] & 0x1) << 1);
        array3[n3++] = (byte)((array[--n] & 0x40) << 1 | (array[++n] & 0x70) >> 3 | (array[n] & 0xE) << 3 | (array[++n] & 0x2) >> 1);
        array3[n3++] = (byte)((array[--n] & 0x80) >> 1 | (array[++n] & 0x1) << 7 | (array[n] & 0x1C) << 1 | (array[n] & 0xE0) >> 5);
        array3[n3++] = (byte)((array[++n] & 0x7F) << 1 | (array[++n] & 0x1));
        array3[n3++] = (byte)((array[--n] & 0x80) | (array[++n] & 0x6) << 4 | (array[n] & 0xF0) >> 4 | (array[++n] & 0x1) << 4);
        array3[n3++] = (byte)((array[--n] & 0x8) << 4 | (array[++n] & 0xE) << 3 | (array[n] & 0x70) >> 3 | (array[++n] & 0x2) >> 1);
        array3[n3++] = (byte)((array[--n] & 0x80) >> 1 | (array[++n] & 0x1) << 7 | (array[n] & 0x1C) << 1 | (array[n] & 0xE0) >> 5);
        array3[n3++] = (byte)((array[++n] & 0x7) << 5 | (array[n] & 0x38) >> 1 | (array[n] & 0x80) >> 7 | (array[++n] & 0x1) << 1);
        array3[n3++] = (byte)((array[--n] & 0x40) << 1 | (array[++n] & 0x70) >> 3 | (array[n] & 0xE) << 3 | (array[++n] & 0x2) >> 1);
        array3[n3] = (byte)((array[--n] & 0x80) >> 1 | (array[++n] & 0x1) << 7 | (array[n] & 0x1C) << 1 | (array[n] & 0xE0) >> 5);
    }
    
    public c(final N a) {
        this.a = a;
    }
    
    public void a(final cH ch) {
        switch (ch.b(4)) {
            case 0: {
                ch.a(1);
            }
            case 1: {
                ch.a(1);
            }
            case 2: {
                ch.a(4);
            }
            case 3: {
                ch.a(4);
            }
            case 4: {
                ch.a(4);
            }
            case 5: {
                ch.a(4);
            }
            case 6: {
                ch.a(4);
            }
            case 7: {
                ch.a(4);
            }
            case 8: {
                ch.a(8);
            }
            case 9: {
                this.a.a(ch);
            }
            case 10: {
                ch.a(16);
            }
            case 11: {
                ch.a(16);
            }
            case 12: {
                ch.a(32);
            }
            case 13: {
                ch.a(32);
            }
            case 14: {
                ch.a(64);
            }
            case 15: {
                ch.a(64);
                break;
            }
        }
    }
}
