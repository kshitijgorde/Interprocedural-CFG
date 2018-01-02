// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.fontshowerawt;

public final class FontSamples
{
    private static final String arrows;
    private static final String currency;
    private static final String digits;
    private static final String esperanto;
    private static final String japaneseDigits;
    private static final String latinextended;
    private static final String lookalikes = "lookalikes: uvw wW gq9 2z 5s il17|!j oO08¤ ;:,\u201c\u2018\u2019\u201d\u201a\u201e. `'´\"{[()]} m nn rn";
    private static final String lowerAccents;
    private static final String lowerCase;
    private static final String punctuation;
    private static final String symbols;
    private static final String upperAccents;
    private static final String upperCase;
    private static final String[] fontSampleLines;
    private static final String[] pangrams;
    
    public static String[] getFontSampleLines() {
        return FontSamples.fontSampleLines;
    }
    
    public static String getFontSampleText() {
        final StringBuffer sb = new StringBuffer(1100);
        for (int i = 0; i < FontSamples.fontSampleLines.length; ++i) {
            if (i != 0) {
                sb.append('\n');
            }
            sb.append(FontSamples.fontSampleLines[i]);
        }
        return sb.toString();
    }
    
    private static String spaceOut(final String s) {
        final StringBuffer sb = new StringBuffer(s.length() * 2 - 1);
        for (int i = 0, n = s.length(); i < n; ++i) {
            if (i != 0) {
                sb.append(' ');
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
    
    static {
        arrows = "arrows: " + spaceOut("\u2190\u2191\u2192\u2193\u2194\u2195\u2196\u2197\u2198\u2199\u21d0\u21d1\u21d2\u21d3\u21d4\u21d5");
        currency = "currency: " + spaceOut("$\u20ac£¤¥¢\u20a1\u20a2\u20a3\u20a4\u20a5\u20a6\u20a7\u20a8\u20a9\u20aa\u20ab\u20ad\u20ae\u20af\u20b1\u20b2\u20b4\u20b5\u17db");
        digits = "digits: " + spaceOut("0123456789");
        esperanto = "Esperanto: " + spaceOut("cghjsu = \u0109\u011d\u0125\u0135\u015d\u016d\u00fb CGHJSU = \u0108\u011c\u0125\u0134\u015c\u016c\u00db");
        japaneseDigits = "Kanji digits: " + spaceOut("\u96f6\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d\u5341\u767e\u5343\u4e07\u61b6\u5146\u4eac\u5793\u30de\u30a4\u30ca\u30b9");
        latinextended = "Latin ext: " + spaceOut("¢£¤¥¡¿¦§¨¯°©®«»¹²³´µ¶·¸¼½¾±\u00d7\u00f7¬\u00dfªº");
        lowerAccents = "lower accents: " + spaceOut("\u00e0\u00e1\u00e2\u00e3\u00e4\u00e5\u00e6\u00e7\u00e8\u00e9\u00ea\u00eb\u011d\u00ec\u00ed\u00ee\u00ef\u00f0\u00f1\u00f2\u00f3\u00f4\u00f5\u00f6\u00f8\u00f9\u00fa\u00fb\u00fc\u00fd\u00fe\u00ff");
        lowerCase = "lower: " + spaceOut("abcdefghijklmnopqrstuvwxyz");
        punctuation = "punctuation: " + spaceOut("!\"#$%&'()*+,-./:;<=>?@[\\^_z{|}~");
        symbols = "symbols: " + spaceOut("\u3041\u30b0\u3302\u4e02\u4e70\u0921\u2208\u2209\u227a\u260e\u2642\u2646\u2661\u2665\u2714\u2718\u2741\u2766\u27a6\u27bd\u267b\u262d\u2779\u2466");
        upperAccents = "upper accents: " + spaceOut("\u00c0\u00c1\u00c2\u00c3\u00c4\u00c5\u00c6\u00c7\u00c8\u00c9\u00ca\u00cb\u011c\u00cc\u00cd\u00ce\u00cf\u00d0\u00d1\u00d2\u00d3\u00d4\u00d5\u00d6\u00d8\u00d9\u00da\u00db\u00dc\u00dd\u00de\u00ff");
        upperCase = "upper: " + spaceOut("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        pangrams = new String[] { "The quick brown fox jumped over the lazy dog's back.", "Pack my box with five dozen liquor jugs.", "Jackdaws love my big sphinx of quartz.", "Mr. Jock, TV quiz PhD, bags few lynx." };
        final String[] combined = { "lookalikes: uvw wW gq9 2z 5s il17|!j oO08¤ ;:,\u201c\u2018\u2019\u201d\u201a\u201e. `'´\"{[()]} m nn rn", FontSamples.lowerCase + "  " + FontSamples.digits, FontSamples.upperCase, FontSamples.lowerAccents, FontSamples.upperAccents, FontSamples.esperanto, FontSamples.japaneseDigits, FontSamples.punctuation, FontSamples.latinextended, FontSamples.symbols, FontSamples.arrows, FontSamples.currency };
        System.arraycopy(combined, 0, fontSampleLines = new String[combined.length + FontSamples.pangrams.length], 0, combined.length);
        System.arraycopy(FontSamples.pangrams, 0, FontSamples.fontSampleLines, combined.length, FontSamples.pangrams.length);
    }
}
