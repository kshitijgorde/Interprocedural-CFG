// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.tz;

import java.text.DateFormatSymbols;
import java.util.Locale;
import java.util.HashMap;

public class DefaultNameProvider implements NameProvider
{
    private HashMap iByLocaleCache;
    
    public DefaultNameProvider() {
        this.iByLocaleCache = this.createCache();
    }
    
    public String getShortName(final Locale locale, final String s, final String s2) {
        final String[] nameSet = this.getNameSet(locale, s, s2);
        return (nameSet == null) ? null : nameSet[0];
    }
    
    public String getName(final Locale locale, final String s, final String s2) {
        final String[] nameSet = this.getNameSet(locale, s, s2);
        return (nameSet == null) ? null : nameSet[1];
    }
    
    private synchronized String[] getNameSet(final Locale locale, final String s, final String s2) {
        if (locale == null || s == null || s2 == null) {
            return null;
        }
        HashMap<Object, HashMap> cache = this.iByLocaleCache.get(locale);
        if (cache == null) {
            this.iByLocaleCache.put(locale, cache = (HashMap<Object, HashMap>)this.createCache());
        }
        HashMap<String, String[]> cache2 = cache.get(s);
        if (cache2 == null) {
            cache.put(s, cache2 = (HashMap<String, String[]>)this.createCache());
            final String[][] zoneStrings = new DateFormatSymbols(locale).getZoneStrings();
            int i = 0;
            while (i < zoneStrings.length) {
                final String[] array = zoneStrings[i];
                if (array != null && array.length == 5 && s.equals(array[0])) {
                    cache2.put(array[2], new String[] { array[2], array[1] });
                    if (array[2].equals(array[4])) {
                        cache2.put(array[4] + "-Summer", new String[] { array[4], array[3] });
                        break;
                    }
                    cache2.put(array[4], new String[] { array[4], array[3] });
                    break;
                }
                else {
                    ++i;
                }
            }
        }
        return cache2.get(s2);
    }
    
    private HashMap createCache() {
        return new HashMap(7);
    }
}
