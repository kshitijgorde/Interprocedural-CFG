// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.util;

import java.util.StringTokenizer;
import java.util.Locale;

public class LocaleUtil
{
    public static Locale getLocale(final String localeString) {
        if (localeString == null || localeString.length() == 0) {
            return Locale.getDefault();
        }
        final String[] array;
        final String[] strings = array = new String[3];
        final int n = 0;
        final String[] array2 = strings;
        final int n2 = 1;
        final String[] array3 = strings;
        final int n3 = 2;
        final String s = "";
        array3[n3] = s;
        array[n] = (array2[n2] = s);
        final StringTokenizer tokenizer = new StringTokenizer(localeString, "_");
        for (int i = 0; i < strings.length && tokenizer.hasMoreTokens(); ++i) {
            strings[i] = tokenizer.nextToken();
        }
        return new Locale(strings[0], strings[1], strings[2]);
    }
}
