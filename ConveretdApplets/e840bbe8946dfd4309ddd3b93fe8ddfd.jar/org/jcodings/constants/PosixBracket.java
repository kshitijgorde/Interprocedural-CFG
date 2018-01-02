// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings.constants;

import org.jcodings.util.BytesHash;

public class PosixBracket
{
    public static final byte[][] PBSNamesUpper;
    public static final byte[][] PBSNamesLower;
    public static final int[] PBSValues;
    public static final BytesHash<Integer> PBSTableUpper;
    
    static {
        PBSNamesUpper = new byte[][] { "Alnum".getBytes(), "Alpha".getBytes(), "Blank".getBytes(), "Cntrl".getBytes(), "Digit".getBytes(), "Graph".getBytes(), "Lower".getBytes(), "Print".getBytes(), "Punct".getBytes(), "Space".getBytes(), "Upper".getBytes(), "XDigit".getBytes(), "ASCII".getBytes(), "Word".getBytes() };
        PBSNamesLower = new byte[][] { "alnum".getBytes(), "alpha".getBytes(), "blank".getBytes(), "cntrl".getBytes(), "digit".getBytes(), "graph".getBytes(), "lower".getBytes(), "print".getBytes(), "punct".getBytes(), "space".getBytes(), "upper".getBytes(), "xdigit".getBytes(), "ascii".getBytes(), "word".getBytes() };
        PBSValues = new int[] { 13, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 14, 12 };
        PBSTableUpper = new BytesHash<Integer>(15);
        for (int i = 0; i < PosixBracket.PBSValues.length; ++i) {
            PosixBracket.PBSTableUpper.put(PosixBracket.PBSNamesUpper[i], PosixBracket.PBSValues[i]);
        }
    }
}
