// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class cq
{
    private static int[] q;
    
    public static int q(final int n) {
        for (int i = 0; i < cq.q.length / 2; ++i) {
            if (n == cq.q[i * 2]) {
                return cq.q[i * 2 + 1];
            }
        }
        return -1;
    }
    
    static {
        cq.q = new int[] { 65793, 132353, 65794, 65792, 65795, 65537, 65796, 66562, 65797, 66563, 65798, 66564, 65799, 66565, 65800, 66566, 66049, 131073, 33620233, 65537, 33620234, 65537, 66305, 197120, 66306, 328192, 66307, 262656, 16974593, 65792, 66308, 131840, 66309, 0, 66310, 262400, 66561, 512, 50400771, 256, 66816, 256, 66817, 65536, 67073, 263680, 67074, 65536, 67329, 197632, 67330, 590593, 17236481, 590593, 17236482, 65537, 67331, 131328, 17236737, 131328, 17236738, 65536, 67332, 393728, 17236993, 393728, 17236994, 65536, 67333, 131584, 17237265, 131584, 17237266, 65536, 67334, 852992, 17237505, 198400, 67335, 918273, 17237761, 918273, 17237762, 65536, 67337, 461569, 17238273, 461569, 17238274, 65536, 67338, 196864, 67339, 262656, 17238785, 262656, 17238786, 65536, 16844556, 65792, 67341, 2425856, 17239297, 2425856, 17239298, 65536, 33621773, 66048, 537948369, 66048, 537948370, 65536, 33621774, 768, 33621775, 66048, 537948401, 66048, 537948402, 65536, 67342, 131072, 67584, 256, 67585, 131328, 67586, 0, 67587, 131072, 67840, 131072, 67841, 1116672, 67842, 131329, 67843, 525056, 67844, 721408, 33622276, 65536, 33622277, 65792, 68096, 65538, 68097, 131074, 33622529, 2, 68352, 720896, 68353, 393472, 68608, 1114624, 50400512, 769, 50400768, 131584, 50400769, 131072, 50400770, 131072, 4198416, 131072, 4198432, 0, 4198448, 197120, 4198464, 66048, 4198465, 131328, 4198466, 131328, 1074807297, 131328, 1074807298, 65536, 4198480, 131072, 4198496, 1792, 4198512, 393984, 1074819073, 393984, 1074819074, 65536, 4198514, 65536, 4198513, 131072, 4198528, 393728, 4198529, 65792, 4198544, 459264, 4202528, 197376, 4202544, 256, 17237506, 0, 4202560, 0, 4202561, 65536, 4202576, 196864, 17237249, 197120, 275795985, 197120, 275795986, 65536, 4202592, 197120, 1075863553, 197120, 1075863554, 65536, 4202608, 256 };
    }
}
