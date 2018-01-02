// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings.unicode;

import org.jcodings.util.BytesHash;

public class UnicodeCTypeNames
{
    private static final byte[][] CTypeNameTable;
    static final BytesHash<Integer> CTypeNameHash;
    
    private static BytesHash<Integer> initializeCTypeNameTable() {
        final BytesHash<Integer> table = new BytesHash<Integer>();
        for (int i = 0; i < UnicodeCTypeNames.CTypeNameTable.length; ++i) {
            table.putDirect(UnicodeCTypeNames.CTypeNameTable[i], i);
        }
        return table;
    }
    
    static {
        CTypeNameTable = new byte[][] { "NEWLINE".getBytes(), "Alpha".getBytes(), "Blank".getBytes(), "Cntrl".getBytes(), "Digit".getBytes(), "Graph".getBytes(), "Lower".getBytes(), "Print".getBytes(), "Punct".getBytes(), "Space".getBytes(), "Upper".getBytes(), "XDigit".getBytes(), "Word".getBytes(), "Alnum".getBytes(), "ASCII".getBytes(), "Any".getBytes(), "Assigned".getBytes(), "C".getBytes(), "Cc".getBytes(), "Cf".getBytes(), "Cn".getBytes(), "Co".getBytes(), "Cs".getBytes(), "L".getBytes(), "Ll".getBytes(), "Lm".getBytes(), "Lo".getBytes(), "Lt".getBytes(), "Lu".getBytes(), "M".getBytes(), "Mc".getBytes(), "Me".getBytes(), "Mn".getBytes(), "N".getBytes(), "Nd".getBytes(), "Nl".getBytes(), "No".getBytes(), "P".getBytes(), "Pc".getBytes(), "Pd".getBytes(), "Pe".getBytes(), "Pf".getBytes(), "Pi".getBytes(), "Po".getBytes(), "Ps".getBytes(), "S".getBytes(), "Sc".getBytes(), "Sk".getBytes(), "Sm".getBytes(), "So".getBytes(), "Z".getBytes(), "Zl".getBytes(), "Zp".getBytes(), "Zs".getBytes(), "Arabic".getBytes(), "Armenian".getBytes(), "Bengali".getBytes(), "Bopomofo".getBytes(), "Braille".getBytes(), "Buginese".getBytes(), "Buhid".getBytes(), "Canadian_Aboriginal".getBytes(), "Cherokee".getBytes(), "Common".getBytes(), "Coptic".getBytes(), "Cypriot".getBytes(), "Cyrillic".getBytes(), "Deseret".getBytes(), "Devanagari".getBytes(), "Ethiopic".getBytes(), "Georgian".getBytes(), "Glagolitic".getBytes(), "Gothic".getBytes(), "Greek".getBytes(), "Gujarati".getBytes(), "Gurmukhi".getBytes(), "Han".getBytes(), "Hangul".getBytes(), "Hanunoo".getBytes(), "Hebrew".getBytes(), "Hiragana".getBytes(), "Inherited".getBytes(), "Kannada".getBytes(), "Katakana".getBytes(), "Kharoshthi".getBytes(), "Khmer".getBytes(), "Lao".getBytes(), "Latin".getBytes(), "Limbu".getBytes(), "Linear_B".getBytes(), "Malayalam".getBytes(), "Mongolian".getBytes(), "Myanmar".getBytes(), "New_Tai_Lue".getBytes(), "Ogham".getBytes(), "Old_Italic".getBytes(), "Old_Persian".getBytes(), "Oriya".getBytes(), "Osmanya".getBytes(), "Runic".getBytes(), "Shavian".getBytes(), "Sinhala".getBytes(), "Syloti_Nagri".getBytes(), "Syriac".getBytes(), "Tagalog".getBytes(), "Tagbanwa".getBytes(), "Tai_Le".getBytes(), "Tamil".getBytes(), "Telugu".getBytes(), "Thaana".getBytes(), "Thai".getBytes(), "Tibetan".getBytes(), "Tifinagh".getBytes(), "Ugaritic".getBytes(), "Yi".getBytes() };
        CTypeNameHash = initializeCTypeNameTable();
    }
}
