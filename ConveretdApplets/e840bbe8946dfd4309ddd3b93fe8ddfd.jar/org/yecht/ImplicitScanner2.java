// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht;

public class ImplicitScanner2
{
    private static final byte[] _ImplicitScanner_actions;
    private static final short[] _ImplicitScanner_key_offsets;
    private static final char[] _ImplicitScanner_trans_keys;
    private static final byte[] _ImplicitScanner_single_lengths;
    private static final byte[] _ImplicitScanner_range_lengths;
    private static final short[] _ImplicitScanner_index_offsets;
    private static final short[] _ImplicitScanner_indicies;
    private static final short[] _ImplicitScanner_trans_targs;
    private static final byte[] _ImplicitScanner_trans_actions;
    private static final byte[] _ImplicitScanner_eof_actions;
    static final int ImplicitScanner_start = 1;
    static final int ImplicitScanner_error = 0;
    static final int ImplicitScanner_en_main = 1;
    
    public static void tryTagImplicit(final Node n, final boolean taguri) {
        String tid = "";
        switch (n.kind) {
            case Str: {
                final Data.Str s = (Data.Str)n.data;
                tid = matchImplicit(s.ptr, s.len);
                break;
            }
            case Seq: {
                tid = "seq";
                break;
            }
            case Map: {
                tid = "map";
                break;
            }
        }
        if (taguri) {
            n.type_id = Parser.taguri("yaml.org,2002", tid);
        }
        else {
            n.type_id = tid;
        }
    }
    
    public static boolean tagcmp(final String tag1, final String tag2) {
        if (tag1 == tag2) {
            return true;
        }
        if (tag1 == null || tag2 == null) {
            return false;
        }
        if (tag1.equals(tag2)) {
            return true;
        }
        int slen1 = tag1.indexOf(35);
        if (slen1 == -1) {
            slen1 = tag1.length();
        }
        int slen2 = tag2.indexOf(35);
        if (slen2 == -1) {
            slen2 = tag2.length();
        }
        return tag1.substring(0, slen1).equals(tag2.substring(0, slen2));
    }
    
    private static byte[] init__ImplicitScanner_actions_0() {
        return new byte[] { 0, 1, 0, 1, 1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7, 1, 8, 1, 9, 1, 10, 1, 11, 1, 12, 1, 13, 1, 14, 1, 15, 1, 17, 1, 18, 2, 14, 16 };
    }
    
    private static short[] init__ImplicitScanner_key_offsets_0() {
        return new short[] { 0, 0, 19, 23, 25, 27, 28, 29, 30, 35, 37, 39, 44, 48, 55, 59, 61, 63, 64, 65, 66, 70, 72, 73, 74, 75, 77, 79, 80, 82, 84, 88, 90, 91, 93, 95, 96, 98, 100, 103, 108, 110, 112, 114, 116, 120, 122, 124, 125, 127, 129, 130, 132, 134, 138, 140, 142, 144, 146, 152, 158, 160, 162, 163, 165, 167, 168, 170, 172, 176, 181, 187, 192, 197, 198, 200, 201, 202, 203, 204, 205, 206, 210, 211, 212, 213, 214, 218, 219, 220, 222, 223, 224, 225, 226, 228, 229, 230, 231, 233, 235, 236, 237, 237, 245, 252, 258, 261, 263, 267, 270, 272, 279, 284, 284, 284, 292, 299, 306, 314, 318, 319, 319, 320, 320, 320, 325, 330, 335, 341, 341, 341, 341, 341 };
    }
    
    private static char[] init__ImplicitScanner_trans_keys_0() {
        return new char[] { '+', '-', '.', '0', '<', '=', 'F', 'N', 'O', 'T', 'Y', 'f', 'n', 'o', 't', 'y', '~', '1', '9', '.', '0', '1', '9', 'I', 'i', 'N', 'n', 'F', 'f', 'n', '.', 'E', 'e', '0', '9', '+', '-', '0', '9', ',', '.', ':', '0', '9', '0', '5', '6', '9', ',', '0', '9', 'A', 'F', 'a', 'f', '.', '0', '1', '9', 'I', 'i', 'N', 'n', 'F', 'f', 'n', 'I', 'N', 'i', 'n', 'A', 'a', 'N', 'a', 'n', '0', '9', '0', '9', '-', '0', '9', '0', '9', '\t', ' ', '0', '9', '0', '9', ':', '0', '9', '0', '9', ':', '0', '9', '0', '9', '\t', ' ', '.', '\t', ' ', '+', '-', 'Z', '0', '9', '0', '9', '0', '9', '0', '9', '\t', ' ', '0', '9', '0', '9', '0', '9', ':', '0', '9', '0', '9', ':', '0', '9', '0', '9', '+', '-', '.', 'Z', '0', '9', '0', '9', '0', '9', '0', '9', '+', '-', '0', 'Z', '1', '9', '+', '-', '0', 'Z', '1', '9', '0', '9', '0', '9', ':', '0', '9', '0', '9', ':', '0', '9', '0', '9', '+', '-', '.', 'Z', '+', '-', 'Z', '0', '9', ',', '-', '.', ':', '0', '9', ',', '.', ':', '0', '9', ',', '.', ':', '0', '9', '<', 'A', 'a', 'L', 'S', 'E', 'l', 's', 'e', 'O', 'U', 'o', 'u', 'L', 'L', 'l', 'l', 'F', 'N', 'f', 'n', 'F', 'f', 'R', 'r', 'U', 'E', 'u', 'e', 'E', 'e', 'S', 's', 'a', 'o', 'u', 'f', 'n', 'r', 'e', ',', '.', ':', 'x', '0', '7', '8', '9', ',', '.', ':', '0', '7', '8', '9', ',', '.', 'E', 'e', '0', '9', ',', '0', '9', '0', '9', '.', ':', '0', '9', ',', '0', '9', '.', ':', ',', '0', '9', 'A', 'F', 'a', 'f', ',', '.', ':', '0', '9', ',', '.', ':', 'x', '0', '7', '8', '9', ',', '.', ':', '0', '7', '8', '9', ',', '.', ':', '0', '7', '8', '9', ',', '-', '.', ':', '0', '7', '8', '9', '\t', ' ', 'T', 't', ':', ':', ',', '.', ':', '0', '9', ',', '.', ':', '0', '9', ',', '.', ':', '0', '9', ',', '-', '.', ':', '0', '9', '\0' };
    }
    
    private static byte[] init__ImplicitScanner_single_lengths_0() {
        return new byte[] { 0, 17, 2, 2, 2, 1, 1, 1, 3, 2, 0, 3, 0, 1, 2, 2, 2, 1, 1, 1, 4, 2, 1, 1, 1, 0, 0, 1, 0, 0, 2, 0, 1, 0, 0, 1, 0, 0, 3, 5, 0, 0, 0, 0, 2, 0, 0, 1, 0, 0, 1, 0, 0, 4, 0, 0, 0, 0, 4, 4, 0, 0, 1, 0, 0, 1, 0, 0, 4, 3, 4, 3, 3, 1, 2, 1, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 4, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 2, 2, 1, 1, 0, 4, 3, 4, 1, 0, 2, 1, 2, 1, 3, 0, 0, 4, 3, 3, 4, 4, 1, 0, 1, 0, 0, 3, 3, 3, 4, 0, 0, 0, 0, 0 };
    }
    
    private static byte[] init__ImplicitScanner_range_lengths_0() {
        return new byte[] { 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 2, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 1, 1, 1, 1, 1, 0, 3, 1, 0, 0, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0 };
    }
    
    private static short[] init__ImplicitScanner_index_offsets_0() {
        return new short[] { 0, 0, 19, 23, 26, 29, 31, 33, 35, 40, 43, 45, 50, 53, 58, 62, 65, 68, 70, 72, 74, 79, 82, 84, 86, 88, 90, 92, 94, 96, 98, 102, 104, 106, 108, 110, 112, 114, 116, 120, 126, 128, 130, 132, 134, 138, 140, 142, 144, 146, 148, 150, 152, 154, 159, 161, 163, 165, 167, 173, 179, 181, 183, 185, 187, 189, 191, 193, 195, 200, 205, 211, 216, 221, 223, 226, 228, 230, 232, 234, 236, 238, 243, 245, 247, 249, 251, 256, 258, 260, 263, 265, 267, 269, 271, 274, 276, 278, 280, 283, 286, 288, 290, 291, 298, 304, 310, 313, 315, 319, 322, 325, 330, 335, 336, 337, 344, 350, 356, 363, 368, 370, 371, 373, 374, 375, 380, 385, 390, 396, 397, 398, 399, 400 };
    }
    
    private static short[] init__ImplicitScanner_indicies_0() {
        return new short[] { 0, 2, 3, 4, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 5, 1, 19, 20, 21, 1, 22, 23, 1, 24, 25, 1, 26, 1, 26, 1, 25, 1, 27, 28, 28, 27, 1, 29, 29, 1, 30, 1, 31, 32, 33, 31, 1, 34, 35, 1, 36, 36, 36, 36, 1, 37, 20, 21, 1, 38, 39, 1, 40, 41, 1, 42, 1, 42, 1, 41, 1, 22, 43, 23, 44, 1, 45, 45, 1, 46, 1, 47, 1, 46, 1, 48, 1, 49, 1, 50, 1, 51, 1, 52, 1, 53, 53, 54, 1, 55, 1, 56, 1, 57, 1, 58, 1, 59, 1, 60, 1, 61, 1, 62, 62, 63, 1, 62, 62, 64, 64, 65, 1, 66, 1, 67, 1, 68, 1, 65, 1, 62, 62, 63, 1, 69, 1, 70, 1, 71, 1, 72, 1, 73, 1, 74, 1, 75, 1, 76, 1, 77, 77, 78, 79, 1, 80, 1, 81, 1, 82, 1, 83, 1, 77, 77, 78, 83, 84, 1, 77, 77, 78, 79, 84, 1, 85, 1, 86, 1, 87, 1, 88, 1, 89, 1, 90, 1, 91, 1, 92, 1, 77, 77, 93, 83, 1, 77, 77, 83, 93, 1, 31, 94, 32, 33, 31, 1, 31, 32, 33, 95, 1, 31, 32, 33, 96, 1, 97, 1, 98, 99, 1, 100, 1, 101, 1, 102, 1, 103, 1, 104, 1, 102, 1, 102, 105, 102, 106, 1, 107, 1, 18, 1, 108, 1, 18, 1, 109, 110, 111, 110, 1, 102, 1, 102, 1, 112, 113, 1, 114, 1, 110, 1, 115, 1, 110, 1, 116, 117, 1, 110, 1, 110, 1, 99, 1, 102, 106, 1, 111, 110, 1, 113, 1, 117, 1, 1, 118, 32, 33, 119, 118, 31, 1, 118, 32, 33, 118, 31, 1, 120, 27, 28, 28, 32, 1, 120, 120, 1, 30, 1, 121, 33, 35, 1, 121, 121, 1, 121, 33, 1, 36, 36, 36, 36, 1, 21, 32, 33, 21, 1, 1, 1, 118, 32, 33, 119, 122, 123, 1, 118, 32, 33, 124, 96, 1, 118, 32, 33, 125, 95, 1, 118, 94, 32, 33, 118, 31, 1, 53, 53, 126, 127, 1, 128, 1, 1, 129, 1, 1, 1, 21, 32, 33, 130, 1, 21, 32, 33, 131, 1, 21, 32, 33, 132, 1, 21, 94, 32, 33, 21, 1, 1, 1, 1, 1, 1, 0 };
    }
    
    private static short[] init__ImplicitScanner_trans_targs_0() {
        return new short[] { 2, 0, 14, 20, 115, 125, 73, 130, 74, 81, 86, 89, 94, 97, 98, 99, 100, 101, 132, 3, 103, 112, 4, 7, 5, 6, 102, 8, 9, 10, 107, 11, 105, 12, 108, 110, 111, 15, 16, 19, 17, 18, 113, 21, 23, 22, 114, 24, 26, 27, 28, 29, 119, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 44, 40, 121, 41, 120, 43, 46, 47, 48, 49, 50, 51, 52, 53, 54, 58, 124, 55, 122, 57, 123, 59, 61, 62, 63, 64, 65, 66, 67, 68, 69, 25, 70, 71, 129, 75, 78, 76, 77, 131, 79, 80, 82, 84, 83, 85, 87, 133, 88, 90, 92, 91, 93, 95, 96, 104, 13, 106, 109, 116, 72, 117, 118, 45, 60, 42, 56, 126, 127, 128 };
    }
    
    private static byte[] init__ImplicitScanner_trans_actions_0() {
        return new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    }
    
    private static byte[] init__ImplicitScanner_eof_actions_0() {
        return new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 13, 9, 15, 15, 17, 11, 19, 11, 7, 13, 23, 25, 13, 9, 9, 9, 27, 31, 31, 29, 29, 37, 13, 13, 13, 13, 35, 33, 5, 1, 3 };
    }
    
    public static String matchImplicit(final Pointer ptr, final int len) {
        String tag = "str";
        final int have = 0;
        final int nread = 0;
        int p = ptr.start;
        int pe = p + len;
        int eof = p + len;
        final int tokstart = -1;
        final int tokend = -1;
        byte[] data = ptr.buffer;
        if (len == 0) {
            data = new byte[] { 126 };
            p = 0;
            pe = 1;
            eof = 1;
        }
        int cs = 1;
        int _trans = 0;
        int _goto_targ = 0;
    Label_0439:
        while (true) {
            switch (_goto_targ) {
                case 0: {
                    if (p == pe) {
                        _goto_targ = 4;
                        continue;
                    }
                    if (cs == 0) {
                        _goto_targ = 5;
                        continue;
                    }
                }
                case 1: {
                    int _keys = ImplicitScanner2._ImplicitScanner_key_offsets[cs];
                    _trans = ImplicitScanner2._ImplicitScanner_index_offsets[cs];
                    int _klen = ImplicitScanner2._ImplicitScanner_single_lengths[cs];
                    Label_0396: {
                        if (_klen > 0) {
                            int _lower = _keys;
                            int _upper = _keys + _klen - 1;
                            while (_upper >= _lower) {
                                final int _mid = _lower + (_upper - _lower >> 1);
                                if (data[p] < ImplicitScanner2._ImplicitScanner_trans_keys[_mid]) {
                                    _upper = _mid - 1;
                                }
                                else {
                                    if (data[p] <= ImplicitScanner2._ImplicitScanner_trans_keys[_mid]) {
                                        _trans += _mid - _keys;
                                        break Label_0396;
                                    }
                                    _lower = _mid + 1;
                                }
                            }
                            _keys += _klen;
                            _trans += _klen;
                        }
                        _klen = ImplicitScanner2._ImplicitScanner_range_lengths[cs];
                        if (_klen > 0) {
                            int _lower = _keys;
                            int _upper = _keys + (_klen << 1) - 2;
                            while (_upper >= _lower) {
                                final int _mid = _lower + (_upper - _lower >> 1 & 0xFFFFFFFE);
                                if (data[p] < ImplicitScanner2._ImplicitScanner_trans_keys[_mid]) {
                                    _upper = _mid - 2;
                                }
                                else {
                                    if (data[p] <= ImplicitScanner2._ImplicitScanner_trans_keys[_mid + 1]) {
                                        _trans += _mid - _keys >> 1;
                                        break Label_0396;
                                    }
                                    _lower = _mid + 2;
                                }
                            }
                            _trans += _klen;
                        }
                    }
                    _trans = ImplicitScanner2._ImplicitScanner_indicies[_trans];
                    cs = ImplicitScanner2._ImplicitScanner_trans_targs[_trans];
                }
                case 2: {
                    if (cs == 0) {
                        _goto_targ = 5;
                        continue;
                    }
                    if (++p != pe) {
                        _goto_targ = 1;
                        continue;
                    }
                    break Label_0439;
                }
                case 4: {
                    break Label_0439;
                }
                default: {
                    return tag;
                }
            }
        }
        if (p == eof) {
            int __acts = ImplicitScanner2._ImplicitScanner_eof_actions[cs];
            int __nacts = ImplicitScanner2._ImplicitScanner_actions[__acts++];
            while (__nacts-- > 0) {
                switch (ImplicitScanner2._ImplicitScanner_actions[__acts++]) {
                    case 0: {
                        tag = "null";
                        continue;
                    }
                    case 1: {
                        tag = "bool#yes";
                        continue;
                    }
                    case 2: {
                        tag = "bool#no";
                        continue;
                    }
                    case 3: {
                        tag = "int#hex";
                        continue;
                    }
                    case 4: {
                        tag = "int#oct";
                        continue;
                    }
                    case 5: {
                        tag = "int#base60";
                        continue;
                    }
                    case 6: {
                        tag = "int";
                        continue;
                    }
                    case 7: {
                        tag = "float#fix";
                        continue;
                    }
                    case 8: {
                        tag = "float#exp";
                        continue;
                    }
                    case 9: {
                        tag = "float#base60";
                        continue;
                    }
                    case 10: {
                        tag = "float#inf";
                        continue;
                    }
                    case 11: {
                        tag = "float#neginf";
                        continue;
                    }
                    case 12: {
                        tag = "float#nan";
                        continue;
                    }
                    case 13: {
                        tag = "timestamp#ymd";
                        continue;
                    }
                    case 14: {
                        tag = "timestamp#iso8601";
                        continue;
                    }
                    case 15: {
                        tag = "timestamp#spaced";
                        continue;
                    }
                    case 16: {
                        tag = "timestamp";
                        continue;
                    }
                    case 17: {
                        tag = "default";
                        continue;
                    }
                    case 18: {
                        tag = "merge";
                        continue;
                    }
                }
            }
        }
        return tag;
    }
    
    static {
        _ImplicitScanner_actions = init__ImplicitScanner_actions_0();
        _ImplicitScanner_key_offsets = init__ImplicitScanner_key_offsets_0();
        _ImplicitScanner_trans_keys = init__ImplicitScanner_trans_keys_0();
        _ImplicitScanner_single_lengths = init__ImplicitScanner_single_lengths_0();
        _ImplicitScanner_range_lengths = init__ImplicitScanner_range_lengths_0();
        _ImplicitScanner_index_offsets = init__ImplicitScanner_index_offsets_0();
        _ImplicitScanner_indicies = init__ImplicitScanner_indicies_0();
        _ImplicitScanner_trans_targs = init__ImplicitScanner_trans_targs_0();
        _ImplicitScanner_trans_actions = init__ImplicitScanner_trans_actions_0();
        _ImplicitScanner_eof_actions = init__ImplicitScanner_eof_actions_0();
    }
}
