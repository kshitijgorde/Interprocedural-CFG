// 
// Decompiled by Procyon v0.5.30
// 

package cue.lang.unicode;

import java.util.List;
import java.util.Iterator;
import cue.lang.c;
import java.util.Collection;
import java.util.regex.Pattern;

public final class b
{
    private static Pattern a;
    private static Pattern b;
    
    static {
        cue.lang.unicode.b.a = Pattern.compile("\\p{InLatin-1Supplement}");
        cue.lang.unicode.b.b = Pattern.compile("[\\p{InLatinExtended-A}\\p{InLatinExtended-B}\\p{InSpacingModifierLetters}\\p{InIPAExtensions}\\p{InCombiningDiacriticalMarks}]");
    }
    
    public static Character.UnicodeBlock a(final Collection collection) {
        boolean b = false;
        boolean b2 = false;
        final c c = new c();
        final Iterator<String> iterator = collection.iterator();
        while (iterator.hasNext()) {
            final String s;
            final Character.UnicodeBlock of;
            Character.UnicodeBlock unicodeBlock2;
            final Character.UnicodeBlock unicodeBlock = ((of = Character.UnicodeBlock.of((s = iterator.next()).codePointAt(0))) == Character.UnicodeBlock.BASIC_LATIN && cue.lang.unicode.b.b.matcher(s).find()) ? (unicodeBlock2 = Character.UnicodeBlock.LATIN_EXTENDED_A) : ((of == Character.UnicodeBlock.BASIC_LATIN && cue.lang.unicode.b.a.matcher(s).find()) ? (unicodeBlock2 = Character.UnicodeBlock.LATIN_1_SUPPLEMENT) : (unicodeBlock2 = of));
            final Character.UnicodeBlock unicodeBlock3 = unicodeBlock2;
            if (unicodeBlock == Character.UnicodeBlock.LATIN_1_SUPPLEMENT) {
                b = true;
            }
            if (unicodeBlock3 == Character.UnicodeBlock.LATIN_EXTENDED_A) {
                b2 = true;
            }
            c.a(unicodeBlock3, 1);
        }
        final List a;
        if ((a = c.a(1)).size() == 0) {
            return null;
        }
        final Character.UnicodeBlock unicodeBlock4;
        if ((unicodeBlock4 = a.get(0)) == Character.UnicodeBlock.BASIC_LATIN || unicodeBlock4 == Character.UnicodeBlock.LATIN_1_SUPPLEMENT) {
            if (b2) {
                return Character.UnicodeBlock.LATIN_EXTENDED_A;
            }
            if (b) {
                return Character.UnicodeBlock.LATIN_1_SUPPLEMENT;
            }
        }
        return unicodeBlock4;
    }
}
