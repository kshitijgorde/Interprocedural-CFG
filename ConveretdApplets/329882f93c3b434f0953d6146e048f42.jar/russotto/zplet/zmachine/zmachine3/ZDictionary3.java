// 
// Decompiled by Procyon v0.5.30
// 

package russotto.zplet.zmachine.zmachine3;

import russotto.zplet.zmachine.ZMachine;
import russotto.zplet.zmachine.ZDictionary;

public class ZDictionary3 extends ZDictionary
{
    ZMachine zm;
    String separators;
    int nentries;
    int wtable_addr;
    int dict_address;
    int entry_length;
    
    public ZDictionary3(final ZMachine zm, final int dict_address) {
        this.zm = zm;
        this.dict_address = dict_address;
        final int n = zm.memory_image[dict_address] & 0xFF;
        final char[] array = new char[n];
        for (int i = 0; i < n; ++i) {
            array[i] = (char)zm.memory_image[dict_address + i + 1];
        }
        this.separators = new String(array);
        this.entry_length = zm.memory_image[dict_address + n + 1];
        this.nentries = (zm.memory_image[dict_address + n + 2] << 8 | (zm.memory_image[dict_address + n + 3] & 0xFF));
        this.wtable_addr = dict_address + n + 4;
    }
    
    public ZDictionary3(final ZMachine zMachine) {
        this(zMachine, zMachine.header.dictionary());
    }
    
    public boolean parse_word(final int n, final int n2, final int n3, final int n4) {
        int n5 = 0;
        int n6 = this.nentries - 1;
        if (this.zm.memory_image[n4] == this.zm.memory_image[n4 + 1]) {
            return true;
        }
        final short[] encode_word = this.zm.encode_word(n2, n3, 2);
        final int n7 = (encode_word[0] & 0xFFFF) << 16 | (encode_word[1] & 0xFFFF);
        int n8 = (n6 + n5) / 2;
        int n9;
        int n10;
        while (true) {
            n9 = this.wtable_addr + n8 * this.entry_length;
            n10 = ((this.zm.memory_image[n9] & 0xFF) << 24 | (this.zm.memory_image[n9 + 1] & 0xFF) << 16 | (this.zm.memory_image[n9 + 2] & 0xFF) << 8 | (this.zm.memory_image[n9 + 3] & 0xFF));
            if (n7 < n10) {
                if (n5 == n8) {
                    break;
                }
                n6 = n8 - 1;
                n8 = (n5 + n8) / 2;
            }
            else {
                if (n7 <= n10) {
                    break;
                }
                if (n6 == n8) {
                    break;
                }
                n5 = n8 + 1;
                n8 = (n8 + n6 + 1) / 2;
            }
        }
        if (n7 != n10) {
            n9 = 0;
        }
        final int n11 = n4 + (this.zm.memory_image[n4 + 1] & 0xFF) * 4 + 2;
        this.zm.memory_image[n11] = (byte)((n9 & 0xFF00) >> 8);
        this.zm.memory_image[n11 + 1] = (byte)(n9 & 0xFF);
        this.zm.memory_image[n11 + 2] = (byte)n3;
        this.zm.memory_image[n11 + 3] = (byte)(n2 - n + 1);
        final byte[] memory_image = this.zm.memory_image;
        final int n12 = n4 + 1;
        ++memory_image[n12];
        return this.zm.memory_image[n4] == this.zm.memory_image[n4 + 1];
    }
    
    public void tokenise(final int n, final int n2, final int n3) {
        int n4 = n2;
        if ((this.zm.memory_image[n3] & 0xFF) < 1) {
            this.zm.fatal("Parse buffer less than 1 word (6 bytes)");
        }
        this.zm.memory_image[n3 + 1] = 0;
        int n5 = n;
        int n6 = 0;
        boolean b = false;
        while (!b && n4-- > 0) {
            final char c = (char)this.zm.memory_image[n5 + n6];
            if (this.separators.indexOf(c) != -1) {
                if (n6 > 0) {
                    this.parse_word(n, n5, n6, n3);
                }
                b = this.parse_word(n, n5 + n6, 1, n3);
                n5 = n5 + n6 + 1;
                n6 = 0;
            }
            else if (c == ' ') {
                if (n6 > 0) {
                    b = this.parse_word(n, n5, n6, n3);
                }
                n5 = n5 + n6 + 1;
                n6 = 0;
            }
            else {
                ++n6;
            }
        }
        if (!b && n6 > 0) {
            this.parse_word(n, n5, n6, n3);
        }
    }
}
