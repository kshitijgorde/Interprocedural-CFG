// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.common;

public class w
{
    protected static final int[] a;
    
    public static int a(final int n) {
        for (int i = 0; i < w.a.length / 2; ++i) {
            if (n == w.a[2 * i]) {
                return w.a[2 * i + 1];
            }
        }
        return -1;
    }
    
    static {
        a = new int[] { 65793, 132097, 65794, 65792, 65795, 65537, 65796, 66562, 65797, 66563, 65798, 66564, 66049, 131072, 65799, 66565, 65800, 66566, 33620233, 65537, 33620234, 65537, 66305, 131584, 66306, 66048, 66307, 66048, 66308, 131840, 66309, 0, 66561, 512, 50400771, 256, 66816, 256, 66817, 65536, 67073, 132096, 67074, 65536, 67329, 197632, 67330, 328449, 67331, 65792, 67332, 197120, 67333, 66048, 67334, 197376, 67335, 65793, 67337, 196864, 67338, 196864, 67339, 66048, 16844556, 65792, 67341, 1114880, 33621774, 768, 33621775, 66048, 67584, 256, 67585, 131328, 67586, 0, 67587, 131072, 67840, 131072, 67841, 1116672, 67842, 131329, 67843, 393728, 33622276, 65536, 68096, 131074, 33622529, 2, 68352, 655360, 68353, 393472, 68608, 1114624, 50400512, 769, 50400768, 131584, 50400769, 131072, 50400770, 131072 };
    }
}
