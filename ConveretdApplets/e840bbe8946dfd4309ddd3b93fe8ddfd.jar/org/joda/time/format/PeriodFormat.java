// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.format;

public class PeriodFormat
{
    private static PeriodFormatter cEnglishWords;
    
    public static PeriodFormatter getDefault() {
        if (PeriodFormat.cEnglishWords == null) {
            final String[] array = { " ", ",", ",and ", ", and " };
            PeriodFormat.cEnglishWords = new PeriodFormatterBuilder().appendYears().appendSuffix(" year", " years").appendSeparator(", ", " and ", array).appendMonths().appendSuffix(" month", " months").appendSeparator(", ", " and ", array).appendWeeks().appendSuffix(" week", " weeks").appendSeparator(", ", " and ", array).appendDays().appendSuffix(" day", " days").appendSeparator(", ", " and ", array).appendHours().appendSuffix(" hour", " hours").appendSeparator(", ", " and ", array).appendMinutes().appendSuffix(" minute", " minutes").appendSeparator(", ", " and ", array).appendSeconds().appendSuffix(" second", " seconds").appendSeparator(", ", " and ", array).appendMillis().appendSuffix(" millisecond", " milliseconds").toFormatter();
        }
        return PeriodFormat.cEnglishWords;
    }
}
