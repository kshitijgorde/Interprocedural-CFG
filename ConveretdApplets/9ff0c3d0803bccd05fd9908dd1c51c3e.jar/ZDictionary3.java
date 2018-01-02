// 
// Decompiled by Procyon v0.5.30
// 

class ZDictionary3 extends ZDictionary
{
    ZMachine zm;
    String separators;
    int nentries;
    int wtable_addr;
    int dict_address;
    int entry_length;
    
    ZDictionary3(final ZMachine zm, final int dict_address) {
        this.zm = zm;
        this.dict_address = dict_address;
        final int n = zm.memory_image[dict_address] & 0xFF;
        final char[] separray = new char[n];
        for (int i = 0; i < n; ++i) {
            separray[i] = (char)zm.memory_image[dict_address + i + 1];
        }
        this.separators = new String(separray);
        this.entry_length = zm.memory_image[dict_address + n + 1];
        this.nentries = (zm.memory_image[dict_address + n + 2] << 8 | (zm.memory_image[dict_address + n + 3] & 0xFF));
        this.wtable_addr = dict_address + n + 4;
    }
    
    ZDictionary3(final ZMachine zm) {
        this(zm, zm.header.dictionary());
    }
    
    boolean parse_word(final int textloc, final int wordloc, final int wordlength, final int parseloc) {
        int first = 0;
        int last = this.nentries - 1;
        if (this.zm.memory_image[parseloc] == this.zm.memory_image[parseloc + 1]) {
            return true;
        }
        final short[] encword = this.zm.encode_word(wordloc, wordlength, 2);
        final int encint = (encword[0] & 0xFFFF) << 16 | (encword[1] & 0xFFFF);
        int middle = (last + first) / 2;
        int dictloc;
        int dictint;
        while (true) {
            dictloc = this.wtable_addr + middle * this.entry_length;
            dictint = ((this.zm.memory_image[dictloc] & 0xFF) << 24 | (this.zm.memory_image[dictloc + 1] & 0xFF) << 16 | (this.zm.memory_image[dictloc + 2] & 0xFF) << 8 | (this.zm.memory_image[dictloc + 3] & 0xFF));
            if (encint < dictint) {
                if (first == middle) {
                    break;
                }
                last = middle - 1;
                middle = (first + middle) / 2;
            }
            else {
                if (encint <= dictint || last == middle) {
                    break;
                }
                first = middle + 1;
                middle = (middle + last + 1) / 2;
            }
        }
        if (encint != dictint) {
            dictloc = 0;
        }
        final int parseentry = parseloc + (this.zm.memory_image[parseloc + 1] & 0xFF) * 4 + 2;
        this.zm.memory_image[parseentry] = (byte)((dictloc & 0xFF00) >> 8);
        this.zm.memory_image[parseentry + 1] = (byte)(dictloc & 0xFF);
        this.zm.memory_image[parseentry + 2] = (byte)wordlength;
        this.zm.memory_image[parseentry + 3] = (byte)(wordloc - textloc + 1);
        final byte[] memory_image = this.zm.memory_image;
        final int n = parseloc + 1;
        ++memory_image[n];
        return this.zm.memory_image[parseloc] == this.zm.memory_image[parseloc + 1];
    }
    
    void tokenise(final int textloc, final int textlength, final int parseloc) {
        int textleft = textlength;
        if ((this.zm.memory_image[parseloc] & 0xFF) < 1) {
            this.zm.fatal("Parse buffer less than 1 word (6 bytes)");
        }
        this.zm.memory_image[parseloc + 1] = 0;
        int wordloc = textloc;
        int wordlength = 0;
        boolean pbfull = false;
        while (!pbfull && textleft-- > 0) {
            final char ch = (char)this.zm.memory_image[wordloc + wordlength];
            if (this.separators.indexOf(ch) != -1) {
                if (wordlength > 0) {
                    this.parse_word(textloc, wordloc, wordlength, parseloc);
                }
                pbfull = this.parse_word(textloc, wordloc + wordlength, 1, parseloc);
                wordloc = wordloc + wordlength + 1;
                wordlength = 0;
            }
            else if (ch == ' ') {
                if (wordlength > 0) {
                    pbfull = this.parse_word(textloc, wordloc, wordlength, parseloc);
                }
                wordloc = wordloc + wordlength + 1;
                wordlength = 0;
            }
            else {
                ++wordlength;
            }
        }
        if (!pbfull && wordlength > 0) {
            this.parse_word(textloc, wordloc, wordlength, parseloc);
        }
    }
}
