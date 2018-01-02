// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xpath.regex;

import java.text.CharacterIterator;

public class BMPattern
{
    char[] pattern;
    int[] shiftTable;
    boolean ignoreCase;
    
    public BMPattern(final String pat, final boolean ignoreCase) {
        this(pat, 256, ignoreCase);
    }
    
    public BMPattern(final String pat, final int tableSize, final boolean ignoreCase) {
        this.pattern = pat.toCharArray();
        this.shiftTable = new int[tableSize];
        this.ignoreCase = ignoreCase;
        final int length = this.pattern.length;
        for (int i = 0; i < this.shiftTable.length; ++i) {
            this.shiftTable[i] = length;
        }
        for (int j = 0; j < length; ++j) {
            char ch = this.pattern[j];
            final int diff = length - j - 1;
            int index = ch % this.shiftTable.length;
            if (diff < this.shiftTable[index]) {
                this.shiftTable[index] = diff;
            }
            if (this.ignoreCase) {
                ch = Character.toUpperCase(ch);
                index = ch % this.shiftTable.length;
                if (diff < this.shiftTable[index]) {
                    this.shiftTable[index] = diff;
                }
                ch = Character.toLowerCase(ch);
                index = ch % this.shiftTable.length;
                if (diff < this.shiftTable[index]) {
                    this.shiftTable[index] = diff;
                }
            }
        }
    }
    
    public int matches(final CharacterIterator iterator, final int start, final int limit) {
        if (this.ignoreCase) {
            return this.matchesIgnoreCase(iterator, start, limit);
        }
        final int plength = this.pattern.length;
        if (plength == 0) {
            return start;
        }
        int index = start + plength;
    Label_0091_Outer:
        while (index <= limit) {
            int pindex = plength;
            final int nindex = index + 1;
            while (true) {
                char ch;
                while ((ch = iterator.setIndex(--index)) == this.pattern[--pindex]) {
                    if (pindex == 0) {
                        return index;
                    }
                    if (pindex > 0) {
                        continue Label_0091_Outer;
                    }
                    index += this.shiftTable[ch % this.shiftTable.length] + 1;
                    if (index < nindex) {
                        index = nindex;
                        continue Label_0091_Outer;
                    }
                    continue Label_0091_Outer;
                }
                continue;
            }
        }
        return -1;
    }
    
    public int matches(final String str, final int start, final int limit) {
        if (this.ignoreCase) {
            return this.matchesIgnoreCase(str, start, limit);
        }
        final int plength = this.pattern.length;
        if (plength == 0) {
            return start;
        }
        int index = start + plength;
    Label_0089_Outer:
        while (index <= limit) {
            int pindex = plength;
            final int nindex = index + 1;
            while (true) {
                char ch;
                while ((ch = str.charAt(--index)) == this.pattern[--pindex]) {
                    if (pindex == 0) {
                        return index;
                    }
                    if (pindex > 0) {
                        continue Label_0089_Outer;
                    }
                    index += this.shiftTable[ch % this.shiftTable.length] + 1;
                    if (index < nindex) {
                        index = nindex;
                        continue Label_0089_Outer;
                    }
                    continue Label_0089_Outer;
                }
                continue;
            }
        }
        return -1;
    }
    
    public int matches(final char[] chars, final int start, final int limit) {
        if (this.ignoreCase) {
            return this.matchesIgnoreCase(chars, start, limit);
        }
        final int plength = this.pattern.length;
        if (plength == 0) {
            return start;
        }
        int index = start + plength;
    Label_0087_Outer:
        while (index <= limit) {
            int pindex = plength;
            final int nindex = index + 1;
            while (true) {
                char ch;
                while ((ch = chars[--index]) == this.pattern[--pindex]) {
                    if (pindex == 0) {
                        return index;
                    }
                    if (pindex > 0) {
                        continue Label_0087_Outer;
                    }
                    index += this.shiftTable[ch % this.shiftTable.length] + 1;
                    if (index < nindex) {
                        index = nindex;
                        continue Label_0087_Outer;
                    }
                    continue Label_0087_Outer;
                }
                continue;
            }
        }
        return -1;
    }
    
    int matchesIgnoreCase(final CharacterIterator iterator, final int start, final int limit) {
        final int plength = this.pattern.length;
        if (plength == 0) {
            return start;
        }
        int nindex;
        for (int index = start + plength; index <= limit; index = nindex) {
            int pindex = plength;
            nindex = index + 1;
            char ch;
            do {
                char ch2;
                ch = (ch2 = iterator.setIndex(--index));
                char ch3 = this.pattern[--pindex];
                if (ch2 != ch3) {
                    ch2 = Character.toUpperCase(ch2);
                    ch3 = Character.toUpperCase(ch3);
                    if (ch2 != ch3 && Character.toLowerCase(ch2) != Character.toLowerCase(ch3)) {
                        break;
                    }
                }
                if (pindex == 0) {
                    return index;
                }
            } while (pindex > 0);
            index += this.shiftTable[ch % this.shiftTable.length] + 1;
            if (index < nindex) {}
        }
        return -1;
    }
    
    int matchesIgnoreCase(final String text, final int start, final int limit) {
        final int plength = this.pattern.length;
        if (plength == 0) {
            return start;
        }
        int nindex;
        for (int index = start + plength; index <= limit; index = nindex) {
            int pindex = plength;
            nindex = index + 1;
            char ch;
            do {
                char ch2;
                ch = (ch2 = text.charAt(--index));
                char ch3 = this.pattern[--pindex];
                if (ch2 != ch3) {
                    ch2 = Character.toUpperCase(ch2);
                    ch3 = Character.toUpperCase(ch3);
                    if (ch2 != ch3 && Character.toLowerCase(ch2) != Character.toLowerCase(ch3)) {
                        break;
                    }
                }
                if (pindex == 0) {
                    return index;
                }
            } while (pindex > 0);
            index += this.shiftTable[ch % this.shiftTable.length] + 1;
            if (index < nindex) {}
        }
        return -1;
    }
    
    int matchesIgnoreCase(final char[] chars, final int start, final int limit) {
        final int plength = this.pattern.length;
        if (plength == 0) {
            return start;
        }
        int nindex;
        for (int index = start + plength; index <= limit; index = nindex) {
            int pindex = plength;
            nindex = index + 1;
            char ch;
            do {
                char ch2;
                ch = (ch2 = chars[--index]);
                char ch3 = this.pattern[--pindex];
                if (ch2 != ch3) {
                    ch2 = Character.toUpperCase(ch2);
                    ch3 = Character.toUpperCase(ch3);
                    if (ch2 != ch3 && Character.toLowerCase(ch2) != Character.toLowerCase(ch3)) {
                        break;
                    }
                }
                if (pindex == 0) {
                    return index;
                }
            } while (pindex > 0);
            index += this.shiftTable[ch % this.shiftTable.length] + 1;
            if (index < nindex) {}
        }
        return -1;
    }
}
