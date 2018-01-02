// 
// Decompiled by Procyon v0.5.30
// 

package org.joni;

final class BitStatus
{
    public static final int BIT_STATUS_BITS_NUM = 32;
    
    public static int bsClear() {
        return 0;
    }
    
    public static int bsAll() {
        return -1;
    }
    
    public static boolean bsAt(final int stats, final int n) {
        return ((n < 32) ? (stats & 1 << n) : (stats & 0x1)) != 0;
    }
    
    public static int bsOnAt(int stats, final int n) {
        if (n < 32) {
            stats |= 1 << n;
        }
        else {
            stats |= 0x1;
        }
        return stats;
    }
    
    public static int bsOnAtSimple(int stats, final int n) {
        if (n < 32) {
            stats |= 1 << n;
        }
        return stats;
    }
    
    public static int bsOnOff(int v, final int f, final boolean negative) {
        if (negative) {
            v &= ~f;
        }
        else {
            v |= f;
        }
        return v;
    }
}
