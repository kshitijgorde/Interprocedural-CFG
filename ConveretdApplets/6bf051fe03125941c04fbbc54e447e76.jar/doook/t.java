// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class t
{
    protected static final int[] a;
    
    public static int a(final int n) {
        for (int i = 0; i < t.a.length / 2; ++i) {
            if (n == t.a[2 * i]) {
                return t.a[2 * i + 1];
            }
        }
        return -1;
    }
    
    static {
        a = new int[] { 65793, 132353, 65794, 65792, 65795, 65537, 65796, 66562, 65797, 66563, 65798, 66564, 66049, 131072, 65799, 66565, 65800, 66566, 33620233, 65537, 33620234, 65537, 66305, 262656, 66306, 525312, 66307, 66048, 66308, 131840, 66309, 0, 66561, 512, 50400771, 256, 66816, 256, 66817, 65536, 67073, 329216, 67074, 65536, 67329, 197632, 67330, 459521, 67331, 66048, 67332, 393728, 67333, 131584, 67334, 591104, 67335, 263425, 67337, 197888, 67338, 196864, 67339, 131584, 16844556, 65792, 67341, 1245952, 33621774, 768, 33621775, 66048, 67584, 512, 67585, 131328, 67586, 0, 67587, 131328, 67840, 131072, 67841, 1116672, 67842, 131329, 67843, 721664, 33622276, 65536, 68096, 131074, 33622529, 2, 68352, 720896, 68353, 393472, 68608, 1114624, 50400512, 769, 50400768, 131584, 50400769, 131072, 50400770, 131072, 67342, 66048, 263168, 256, 263424, 65536, 263680, 0, 264192, 328448, 264448, 0, 327680, 256, 327936, 131840 };
    }
}
