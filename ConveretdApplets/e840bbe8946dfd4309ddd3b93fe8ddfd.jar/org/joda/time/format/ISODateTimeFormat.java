// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.format;

import org.joda.time.DateTimeZone;
import org.joda.time.DateTimeFieldType;
import java.util.HashSet;
import java.util.Collection;

public class ISODateTimeFormat
{
    private static DateTimeFormatter ye;
    private static DateTimeFormatter mye;
    private static DateTimeFormatter dme;
    private static DateTimeFormatter we;
    private static DateTimeFormatter wwe;
    private static DateTimeFormatter dwe;
    private static DateTimeFormatter dye;
    private static DateTimeFormatter hde;
    private static DateTimeFormatter mhe;
    private static DateTimeFormatter sme;
    private static DateTimeFormatter fse;
    private static DateTimeFormatter ze;
    private static DateTimeFormatter lte;
    private static DateTimeFormatter ym;
    private static DateTimeFormatter ymd;
    private static DateTimeFormatter ww;
    private static DateTimeFormatter wwd;
    private static DateTimeFormatter hm;
    private static DateTimeFormatter hms;
    private static DateTimeFormatter hmsl;
    private static DateTimeFormatter hmsf;
    private static DateTimeFormatter dh;
    private static DateTimeFormatter dhm;
    private static DateTimeFormatter dhms;
    private static DateTimeFormatter dhmsl;
    private static DateTimeFormatter dhmsf;
    private static DateTimeFormatter t;
    private static DateTimeFormatter tx;
    private static DateTimeFormatter tt;
    private static DateTimeFormatter ttx;
    private static DateTimeFormatter dt;
    private static DateTimeFormatter dtx;
    private static DateTimeFormatter wdt;
    private static DateTimeFormatter wdtx;
    private static DateTimeFormatter od;
    private static DateTimeFormatter odt;
    private static DateTimeFormatter odtx;
    private static DateTimeFormatter bd;
    private static DateTimeFormatter bt;
    private static DateTimeFormatter btx;
    private static DateTimeFormatter btt;
    private static DateTimeFormatter bttx;
    private static DateTimeFormatter bdt;
    private static DateTimeFormatter bdtx;
    private static DateTimeFormatter bod;
    private static DateTimeFormatter bodt;
    private static DateTimeFormatter bodtx;
    private static DateTimeFormatter bwd;
    private static DateTimeFormatter bwdt;
    private static DateTimeFormatter bwdtx;
    private static DateTimeFormatter dpe;
    private static DateTimeFormatter tpe;
    private static DateTimeFormatter dp;
    private static DateTimeFormatter ldp;
    private static DateTimeFormatter tp;
    private static DateTimeFormatter ltp;
    private static DateTimeFormatter dtp;
    private static DateTimeFormatter dotp;
    private static DateTimeFormatter ldotp;
    
    public static DateTimeFormatter forFields(final Collection collection, final boolean b, final boolean b2) {
        if (collection == null || collection.size() == 0) {
            throw new IllegalArgumentException("The fields must not be null or empty");
        }
        final HashSet set = new HashSet(collection);
        final int size = set.size();
        boolean b3 = false;
        final DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();
        if (set.contains(DateTimeFieldType.monthOfYear())) {
            b3 = dateByMonth(dateTimeFormatterBuilder, set, b, b2);
        }
        else if (set.contains(DateTimeFieldType.dayOfYear())) {
            b3 = dateByOrdinal(dateTimeFormatterBuilder, set, b, b2);
        }
        else if (set.contains(DateTimeFieldType.weekOfWeekyear())) {
            b3 = dateByWeek(dateTimeFormatterBuilder, set, b, b2);
        }
        else if (set.contains(DateTimeFieldType.dayOfMonth())) {
            b3 = dateByMonth(dateTimeFormatterBuilder, set, b, b2);
        }
        else if (set.contains(DateTimeFieldType.dayOfWeek())) {
            b3 = dateByWeek(dateTimeFormatterBuilder, set, b, b2);
        }
        else if (set.remove(DateTimeFieldType.year())) {
            dateTimeFormatterBuilder.append(yearElement());
            b3 = true;
        }
        else if (set.remove(DateTimeFieldType.weekyear())) {
            dateTimeFormatterBuilder.append(weekyearElement());
            b3 = true;
        }
        time(dateTimeFormatterBuilder, set, b, b2, b3, set.size() < size);
        if (!dateTimeFormatterBuilder.canBuildFormatter()) {
            throw new IllegalArgumentException("No valid format for fields: " + collection);
        }
        try {
            collection.retainAll(set);
        }
        catch (UnsupportedOperationException ex) {}
        return dateTimeFormatterBuilder.toFormatter();
    }
    
    private static boolean dateByMonth(final DateTimeFormatterBuilder dateTimeFormatterBuilder, final Collection collection, final boolean b, final boolean b2) {
        boolean b3 = false;
        if (collection.remove(DateTimeFieldType.year())) {
            dateTimeFormatterBuilder.append(yearElement());
            if (collection.remove(DateTimeFieldType.monthOfYear())) {
                if (collection.remove(DateTimeFieldType.dayOfMonth())) {
                    appendSeparator(dateTimeFormatterBuilder, b);
                    dateTimeFormatterBuilder.appendMonthOfYear(2);
                    appendSeparator(dateTimeFormatterBuilder, b);
                    dateTimeFormatterBuilder.appendDayOfMonth(2);
                }
                else {
                    dateTimeFormatterBuilder.appendLiteral('-');
                    dateTimeFormatterBuilder.appendMonthOfYear(2);
                    b3 = true;
                }
            }
            else if (collection.remove(DateTimeFieldType.dayOfMonth())) {
                checkNotStrictISO(collection, b2);
                dateTimeFormatterBuilder.appendLiteral('-');
                dateTimeFormatterBuilder.appendLiteral('-');
                dateTimeFormatterBuilder.appendDayOfMonth(2);
            }
            else {
                b3 = true;
            }
        }
        else if (collection.remove(DateTimeFieldType.monthOfYear())) {
            dateTimeFormatterBuilder.appendLiteral('-');
            dateTimeFormatterBuilder.appendLiteral('-');
            dateTimeFormatterBuilder.appendMonthOfYear(2);
            if (collection.remove(DateTimeFieldType.dayOfMonth())) {
                appendSeparator(dateTimeFormatterBuilder, b);
                dateTimeFormatterBuilder.appendDayOfMonth(2);
            }
            else {
                b3 = true;
            }
        }
        else if (collection.remove(DateTimeFieldType.dayOfMonth())) {
            dateTimeFormatterBuilder.appendLiteral('-');
            dateTimeFormatterBuilder.appendLiteral('-');
            dateTimeFormatterBuilder.appendLiteral('-');
            dateTimeFormatterBuilder.appendDayOfMonth(2);
        }
        return b3;
    }
    
    private static boolean dateByOrdinal(final DateTimeFormatterBuilder dateTimeFormatterBuilder, final Collection collection, final boolean b, final boolean b2) {
        boolean b3 = false;
        if (collection.remove(DateTimeFieldType.year())) {
            dateTimeFormatterBuilder.append(yearElement());
            if (collection.remove(DateTimeFieldType.dayOfYear())) {
                appendSeparator(dateTimeFormatterBuilder, b);
                dateTimeFormatterBuilder.appendDayOfYear(3);
            }
            else {
                b3 = true;
            }
        }
        else if (collection.remove(DateTimeFieldType.dayOfYear())) {
            dateTimeFormatterBuilder.appendLiteral('-');
            dateTimeFormatterBuilder.appendDayOfYear(3);
        }
        return b3;
    }
    
    private static boolean dateByWeek(final DateTimeFormatterBuilder dateTimeFormatterBuilder, final Collection collection, final boolean b, final boolean b2) {
        boolean b3 = false;
        if (collection.remove(DateTimeFieldType.weekyear())) {
            dateTimeFormatterBuilder.append(weekyearElement());
            if (collection.remove(DateTimeFieldType.weekOfWeekyear())) {
                appendSeparator(dateTimeFormatterBuilder, b);
                dateTimeFormatterBuilder.appendLiteral('W');
                dateTimeFormatterBuilder.appendWeekOfWeekyear(2);
                if (collection.remove(DateTimeFieldType.dayOfWeek())) {
                    appendSeparator(dateTimeFormatterBuilder, b);
                    dateTimeFormatterBuilder.appendDayOfWeek(1);
                }
                else {
                    b3 = true;
                }
            }
            else if (collection.remove(DateTimeFieldType.dayOfWeek())) {
                checkNotStrictISO(collection, b2);
                appendSeparator(dateTimeFormatterBuilder, b);
                dateTimeFormatterBuilder.appendLiteral('W');
                dateTimeFormatterBuilder.appendLiteral('-');
                dateTimeFormatterBuilder.appendDayOfWeek(1);
            }
            else {
                b3 = true;
            }
        }
        else if (collection.remove(DateTimeFieldType.weekOfWeekyear())) {
            dateTimeFormatterBuilder.appendLiteral('-');
            dateTimeFormatterBuilder.appendLiteral('W');
            dateTimeFormatterBuilder.appendWeekOfWeekyear(2);
            if (collection.remove(DateTimeFieldType.dayOfWeek())) {
                appendSeparator(dateTimeFormatterBuilder, b);
                dateTimeFormatterBuilder.appendDayOfWeek(1);
            }
            else {
                b3 = true;
            }
        }
        else if (collection.remove(DateTimeFieldType.dayOfWeek())) {
            dateTimeFormatterBuilder.appendLiteral('-');
            dateTimeFormatterBuilder.appendLiteral('W');
            dateTimeFormatterBuilder.appendLiteral('-');
            dateTimeFormatterBuilder.appendDayOfWeek(1);
        }
        return b3;
    }
    
    private static void time(final DateTimeFormatterBuilder dateTimeFormatterBuilder, final Collection collection, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        final boolean remove = collection.remove(DateTimeFieldType.hourOfDay());
        final boolean remove2 = collection.remove(DateTimeFieldType.minuteOfHour());
        final boolean remove3 = collection.remove(DateTimeFieldType.secondOfMinute());
        final boolean remove4 = collection.remove(DateTimeFieldType.millisOfSecond());
        if (!remove && !remove2 && !remove3 && !remove4) {
            return;
        }
        if (remove || remove2 || remove3 || remove4) {
            if (b2 && b3) {
                throw new IllegalArgumentException("No valid ISO8601 format for fields because Date was reduced precision: " + collection);
            }
            if (b4) {
                dateTimeFormatterBuilder.appendLiteral('T');
            }
        }
        Label_0266: {
            if (!remove || !remove2 || !remove3) {
                if (!remove || remove3 || remove4) {
                    if (b2 && b4) {
                        throw new IllegalArgumentException("No valid ISO8601 format for fields because Time was truncated: " + collection);
                    }
                    if (!remove) {
                        if ((remove2 && remove3) || (remove2 && !remove4)) {
                            break Label_0266;
                        }
                        if (remove3) {
                            break Label_0266;
                        }
                    }
                    if (b2) {
                        throw new IllegalArgumentException("No valid ISO8601 format for fields: " + collection);
                    }
                }
            }
        }
        if (remove) {
            dateTimeFormatterBuilder.appendHourOfDay(2);
        }
        else if (remove2 || remove3 || remove4) {
            dateTimeFormatterBuilder.appendLiteral('-');
        }
        if (b && remove && remove2) {
            dateTimeFormatterBuilder.appendLiteral(':');
        }
        if (remove2) {
            dateTimeFormatterBuilder.appendMinuteOfHour(2);
        }
        else if (remove3 || remove4) {
            dateTimeFormatterBuilder.appendLiteral('-');
        }
        if (b && remove2 && remove3) {
            dateTimeFormatterBuilder.appendLiteral(':');
        }
        if (remove3) {
            dateTimeFormatterBuilder.appendSecondOfMinute(2);
        }
        else if (remove4) {
            dateTimeFormatterBuilder.appendLiteral('-');
        }
        if (remove4) {
            dateTimeFormatterBuilder.appendLiteral('.');
            dateTimeFormatterBuilder.appendMillisOfSecond(3);
        }
    }
    
    private static void checkNotStrictISO(final Collection collection, final boolean b) {
        if (b) {
            throw new IllegalArgumentException("No valid ISO8601 format for fields: " + collection);
        }
    }
    
    private static void appendSeparator(final DateTimeFormatterBuilder dateTimeFormatterBuilder, final boolean b) {
        if (b) {
            dateTimeFormatterBuilder.appendLiteral('-');
        }
    }
    
    public static DateTimeFormatter dateParser() {
        if (ISODateTimeFormat.dp == null) {
            ISODateTimeFormat.dp = new DateTimeFormatterBuilder().append(dateElementParser()).appendOptional(new DateTimeFormatterBuilder().appendLiteral('T').append(offsetElement()).toParser()).toFormatter();
        }
        return ISODateTimeFormat.dp;
    }
    
    public static DateTimeFormatter localDateParser() {
        if (ISODateTimeFormat.ldp == null) {
            ISODateTimeFormat.ldp = dateElementParser().withZone(DateTimeZone.UTC);
        }
        return ISODateTimeFormat.ldp;
    }
    
    public static DateTimeFormatter dateElementParser() {
        if (ISODateTimeFormat.dpe == null) {
            ISODateTimeFormat.dpe = new DateTimeFormatterBuilder().append(null, new DateTimeParser[] { new DateTimeFormatterBuilder().append(yearElement()).appendOptional(new DateTimeFormatterBuilder().append(monthElement()).appendOptional(dayOfMonthElement().getParser()).toParser()).toParser(), new DateTimeFormatterBuilder().append(weekyearElement()).append(weekElement()).appendOptional(dayOfWeekElement().getParser()).toParser(), new DateTimeFormatterBuilder().append(yearElement()).append(dayOfYearElement()).toParser() }).toFormatter();
        }
        return ISODateTimeFormat.dpe;
    }
    
    public static DateTimeFormatter timeParser() {
        if (ISODateTimeFormat.tp == null) {
            ISODateTimeFormat.tp = new DateTimeFormatterBuilder().appendOptional(literalTElement().getParser()).append(timeElementParser()).appendOptional(offsetElement().getParser()).toFormatter();
        }
        return ISODateTimeFormat.tp;
    }
    
    public static DateTimeFormatter localTimeParser() {
        if (ISODateTimeFormat.ltp == null) {
            ISODateTimeFormat.ltp = new DateTimeFormatterBuilder().appendOptional(literalTElement().getParser()).append(timeElementParser()).toFormatter().withZone(DateTimeZone.UTC);
        }
        return ISODateTimeFormat.ltp;
    }
    
    public static DateTimeFormatter timeElementParser() {
        if (ISODateTimeFormat.tpe == null) {
            final DateTimeParser parser = new DateTimeFormatterBuilder().append(null, new DateTimeParser[] { new DateTimeFormatterBuilder().appendLiteral('.').toParser(), new DateTimeFormatterBuilder().appendLiteral(',').toParser() }).toParser();
            ISODateTimeFormat.tpe = new DateTimeFormatterBuilder().append(hourElement()).append(null, new DateTimeParser[] { new DateTimeFormatterBuilder().append(minuteElement()).append(null, new DateTimeParser[] { new DateTimeFormatterBuilder().append(secondElement()).appendOptional(new DateTimeFormatterBuilder().append(parser).appendFractionOfSecond(1, 9).toParser()).toParser(), new DateTimeFormatterBuilder().append(parser).appendFractionOfMinute(1, 9).toParser(), null }).toParser(), new DateTimeFormatterBuilder().append(parser).appendFractionOfHour(1, 9).toParser(), null }).toFormatter();
        }
        return ISODateTimeFormat.tpe;
    }
    
    public static DateTimeFormatter dateTimeParser() {
        if (ISODateTimeFormat.dtp == null) {
            ISODateTimeFormat.dtp = new DateTimeFormatterBuilder().append(null, new DateTimeParser[] { new DateTimeFormatterBuilder().appendLiteral('T').append(timeElementParser()).appendOptional(offsetElement().getParser()).toParser(), dateOptionalTimeParser().getParser() }).toFormatter();
        }
        return ISODateTimeFormat.dtp;
    }
    
    public static DateTimeFormatter dateOptionalTimeParser() {
        if (ISODateTimeFormat.dotp == null) {
            ISODateTimeFormat.dotp = new DateTimeFormatterBuilder().append(dateElementParser()).appendOptional(new DateTimeFormatterBuilder().appendLiteral('T').appendOptional(timeElementParser().getParser()).appendOptional(offsetElement().getParser()).toParser()).toFormatter();
        }
        return ISODateTimeFormat.dotp;
    }
    
    public static DateTimeFormatter localDateOptionalTimeParser() {
        if (ISODateTimeFormat.ldotp == null) {
            ISODateTimeFormat.ldotp = new DateTimeFormatterBuilder().append(dateElementParser()).appendOptional(new DateTimeFormatterBuilder().appendLiteral('T').append(timeElementParser()).toParser()).toFormatter().withZone(DateTimeZone.UTC);
        }
        return ISODateTimeFormat.ldotp;
    }
    
    public static DateTimeFormatter date() {
        return yearMonthDay();
    }
    
    public static DateTimeFormatter time() {
        if (ISODateTimeFormat.t == null) {
            ISODateTimeFormat.t = new DateTimeFormatterBuilder().append(hourMinuteSecondFraction()).append(offsetElement()).toFormatter();
        }
        return ISODateTimeFormat.t;
    }
    
    public static DateTimeFormatter timeNoMillis() {
        if (ISODateTimeFormat.tx == null) {
            ISODateTimeFormat.tx = new DateTimeFormatterBuilder().append(hourMinuteSecond()).append(offsetElement()).toFormatter();
        }
        return ISODateTimeFormat.tx;
    }
    
    public static DateTimeFormatter tTime() {
        if (ISODateTimeFormat.tt == null) {
            ISODateTimeFormat.tt = new DateTimeFormatterBuilder().append(literalTElement()).append(time()).toFormatter();
        }
        return ISODateTimeFormat.tt;
    }
    
    public static DateTimeFormatter tTimeNoMillis() {
        if (ISODateTimeFormat.ttx == null) {
            ISODateTimeFormat.ttx = new DateTimeFormatterBuilder().append(literalTElement()).append(timeNoMillis()).toFormatter();
        }
        return ISODateTimeFormat.ttx;
    }
    
    public static DateTimeFormatter dateTime() {
        if (ISODateTimeFormat.dt == null) {
            ISODateTimeFormat.dt = new DateTimeFormatterBuilder().append(date()).append(tTime()).toFormatter();
        }
        return ISODateTimeFormat.dt;
    }
    
    public static DateTimeFormatter dateTimeNoMillis() {
        if (ISODateTimeFormat.dtx == null) {
            ISODateTimeFormat.dtx = new DateTimeFormatterBuilder().append(date()).append(tTimeNoMillis()).toFormatter();
        }
        return ISODateTimeFormat.dtx;
    }
    
    public static DateTimeFormatter ordinalDate() {
        if (ISODateTimeFormat.od == null) {
            ISODateTimeFormat.od = new DateTimeFormatterBuilder().append(yearElement()).append(dayOfYearElement()).toFormatter();
        }
        return ISODateTimeFormat.od;
    }
    
    public static DateTimeFormatter ordinalDateTime() {
        if (ISODateTimeFormat.odt == null) {
            ISODateTimeFormat.odt = new DateTimeFormatterBuilder().append(ordinalDate()).append(tTime()).toFormatter();
        }
        return ISODateTimeFormat.odt;
    }
    
    public static DateTimeFormatter ordinalDateTimeNoMillis() {
        if (ISODateTimeFormat.odtx == null) {
            ISODateTimeFormat.odtx = new DateTimeFormatterBuilder().append(ordinalDate()).append(tTimeNoMillis()).toFormatter();
        }
        return ISODateTimeFormat.odtx;
    }
    
    public static DateTimeFormatter weekDate() {
        return weekyearWeekDay();
    }
    
    public static DateTimeFormatter weekDateTime() {
        if (ISODateTimeFormat.wdt == null) {
            ISODateTimeFormat.wdt = new DateTimeFormatterBuilder().append(weekDate()).append(tTime()).toFormatter();
        }
        return ISODateTimeFormat.wdt;
    }
    
    public static DateTimeFormatter weekDateTimeNoMillis() {
        if (ISODateTimeFormat.wdtx == null) {
            ISODateTimeFormat.wdtx = new DateTimeFormatterBuilder().append(weekDate()).append(tTimeNoMillis()).toFormatter();
        }
        return ISODateTimeFormat.wdtx;
    }
    
    public static DateTimeFormatter basicDate() {
        if (ISODateTimeFormat.bd == null) {
            ISODateTimeFormat.bd = new DateTimeFormatterBuilder().appendYear(4, 4).appendFixedDecimal(DateTimeFieldType.monthOfYear(), 2).appendFixedDecimal(DateTimeFieldType.dayOfMonth(), 2).toFormatter();
        }
        return ISODateTimeFormat.bd;
    }
    
    public static DateTimeFormatter basicTime() {
        if (ISODateTimeFormat.bt == null) {
            ISODateTimeFormat.bt = new DateTimeFormatterBuilder().appendFixedDecimal(DateTimeFieldType.hourOfDay(), 2).appendFixedDecimal(DateTimeFieldType.minuteOfHour(), 2).appendFixedDecimal(DateTimeFieldType.secondOfMinute(), 2).appendLiteral('.').appendFractionOfSecond(3, 9).appendTimeZoneOffset("Z", false, 2, 2).toFormatter();
        }
        return ISODateTimeFormat.bt;
    }
    
    public static DateTimeFormatter basicTimeNoMillis() {
        if (ISODateTimeFormat.btx == null) {
            ISODateTimeFormat.btx = new DateTimeFormatterBuilder().appendFixedDecimal(DateTimeFieldType.hourOfDay(), 2).appendFixedDecimal(DateTimeFieldType.minuteOfHour(), 2).appendFixedDecimal(DateTimeFieldType.secondOfMinute(), 2).appendTimeZoneOffset("Z", false, 2, 2).toFormatter();
        }
        return ISODateTimeFormat.btx;
    }
    
    public static DateTimeFormatter basicTTime() {
        if (ISODateTimeFormat.btt == null) {
            ISODateTimeFormat.btt = new DateTimeFormatterBuilder().append(literalTElement()).append(basicTime()).toFormatter();
        }
        return ISODateTimeFormat.btt;
    }
    
    public static DateTimeFormatter basicTTimeNoMillis() {
        if (ISODateTimeFormat.bttx == null) {
            ISODateTimeFormat.bttx = new DateTimeFormatterBuilder().append(literalTElement()).append(basicTimeNoMillis()).toFormatter();
        }
        return ISODateTimeFormat.bttx;
    }
    
    public static DateTimeFormatter basicDateTime() {
        if (ISODateTimeFormat.bdt == null) {
            ISODateTimeFormat.bdt = new DateTimeFormatterBuilder().append(basicDate()).append(basicTTime()).toFormatter();
        }
        return ISODateTimeFormat.bdt;
    }
    
    public static DateTimeFormatter basicDateTimeNoMillis() {
        if (ISODateTimeFormat.bdtx == null) {
            ISODateTimeFormat.bdtx = new DateTimeFormatterBuilder().append(basicDate()).append(basicTTimeNoMillis()).toFormatter();
        }
        return ISODateTimeFormat.bdtx;
    }
    
    public static DateTimeFormatter basicOrdinalDate() {
        if (ISODateTimeFormat.bod == null) {
            ISODateTimeFormat.bod = new DateTimeFormatterBuilder().appendYear(4, 4).appendFixedDecimal(DateTimeFieldType.dayOfYear(), 3).toFormatter();
        }
        return ISODateTimeFormat.bod;
    }
    
    public static DateTimeFormatter basicOrdinalDateTime() {
        if (ISODateTimeFormat.bodt == null) {
            ISODateTimeFormat.bodt = new DateTimeFormatterBuilder().append(basicOrdinalDate()).append(basicTTime()).toFormatter();
        }
        return ISODateTimeFormat.bodt;
    }
    
    public static DateTimeFormatter basicOrdinalDateTimeNoMillis() {
        if (ISODateTimeFormat.bodtx == null) {
            ISODateTimeFormat.bodtx = new DateTimeFormatterBuilder().append(basicOrdinalDate()).append(basicTTimeNoMillis()).toFormatter();
        }
        return ISODateTimeFormat.bodtx;
    }
    
    public static DateTimeFormatter basicWeekDate() {
        if (ISODateTimeFormat.bwd == null) {
            ISODateTimeFormat.bwd = new DateTimeFormatterBuilder().appendWeekyear(4, 4).appendLiteral('W').appendFixedDecimal(DateTimeFieldType.weekOfWeekyear(), 2).appendFixedDecimal(DateTimeFieldType.dayOfWeek(), 1).toFormatter();
        }
        return ISODateTimeFormat.bwd;
    }
    
    public static DateTimeFormatter basicWeekDateTime() {
        if (ISODateTimeFormat.bwdt == null) {
            ISODateTimeFormat.bwdt = new DateTimeFormatterBuilder().append(basicWeekDate()).append(basicTTime()).toFormatter();
        }
        return ISODateTimeFormat.bwdt;
    }
    
    public static DateTimeFormatter basicWeekDateTimeNoMillis() {
        if (ISODateTimeFormat.bwdtx == null) {
            ISODateTimeFormat.bwdtx = new DateTimeFormatterBuilder().append(basicWeekDate()).append(basicTTimeNoMillis()).toFormatter();
        }
        return ISODateTimeFormat.bwdtx;
    }
    
    public static DateTimeFormatter year() {
        return yearElement();
    }
    
    public static DateTimeFormatter yearMonth() {
        if (ISODateTimeFormat.ym == null) {
            ISODateTimeFormat.ym = new DateTimeFormatterBuilder().append(yearElement()).append(monthElement()).toFormatter();
        }
        return ISODateTimeFormat.ym;
    }
    
    public static DateTimeFormatter yearMonthDay() {
        if (ISODateTimeFormat.ymd == null) {
            ISODateTimeFormat.ymd = new DateTimeFormatterBuilder().append(yearElement()).append(monthElement()).append(dayOfMonthElement()).toFormatter();
        }
        return ISODateTimeFormat.ymd;
    }
    
    public static DateTimeFormatter weekyear() {
        return weekyearElement();
    }
    
    public static DateTimeFormatter weekyearWeek() {
        if (ISODateTimeFormat.ww == null) {
            ISODateTimeFormat.ww = new DateTimeFormatterBuilder().append(weekyearElement()).append(weekElement()).toFormatter();
        }
        return ISODateTimeFormat.ww;
    }
    
    public static DateTimeFormatter weekyearWeekDay() {
        if (ISODateTimeFormat.wwd == null) {
            ISODateTimeFormat.wwd = new DateTimeFormatterBuilder().append(weekyearElement()).append(weekElement()).append(dayOfWeekElement()).toFormatter();
        }
        return ISODateTimeFormat.wwd;
    }
    
    public static DateTimeFormatter hour() {
        return hourElement();
    }
    
    public static DateTimeFormatter hourMinute() {
        if (ISODateTimeFormat.hm == null) {
            ISODateTimeFormat.hm = new DateTimeFormatterBuilder().append(hourElement()).append(minuteElement()).toFormatter();
        }
        return ISODateTimeFormat.hm;
    }
    
    public static DateTimeFormatter hourMinuteSecond() {
        if (ISODateTimeFormat.hms == null) {
            ISODateTimeFormat.hms = new DateTimeFormatterBuilder().append(hourElement()).append(minuteElement()).append(secondElement()).toFormatter();
        }
        return ISODateTimeFormat.hms;
    }
    
    public static DateTimeFormatter hourMinuteSecondMillis() {
        if (ISODateTimeFormat.hmsl == null) {
            ISODateTimeFormat.hmsl = new DateTimeFormatterBuilder().append(hourElement()).append(minuteElement()).append(secondElement()).appendLiteral('.').appendFractionOfSecond(3, 3).toFormatter();
        }
        return ISODateTimeFormat.hmsl;
    }
    
    public static DateTimeFormatter hourMinuteSecondFraction() {
        if (ISODateTimeFormat.hmsf == null) {
            ISODateTimeFormat.hmsf = new DateTimeFormatterBuilder().append(hourElement()).append(minuteElement()).append(secondElement()).append(fractionElement()).toFormatter();
        }
        return ISODateTimeFormat.hmsf;
    }
    
    public static DateTimeFormatter dateHour() {
        if (ISODateTimeFormat.dh == null) {
            ISODateTimeFormat.dh = new DateTimeFormatterBuilder().append(date()).append(literalTElement()).append(hour()).toFormatter();
        }
        return ISODateTimeFormat.dh;
    }
    
    public static DateTimeFormatter dateHourMinute() {
        if (ISODateTimeFormat.dhm == null) {
            ISODateTimeFormat.dhm = new DateTimeFormatterBuilder().append(date()).append(literalTElement()).append(hourMinute()).toFormatter();
        }
        return ISODateTimeFormat.dhm;
    }
    
    public static DateTimeFormatter dateHourMinuteSecond() {
        if (ISODateTimeFormat.dhms == null) {
            ISODateTimeFormat.dhms = new DateTimeFormatterBuilder().append(date()).append(literalTElement()).append(hourMinuteSecond()).toFormatter();
        }
        return ISODateTimeFormat.dhms;
    }
    
    public static DateTimeFormatter dateHourMinuteSecondMillis() {
        if (ISODateTimeFormat.dhmsl == null) {
            ISODateTimeFormat.dhmsl = new DateTimeFormatterBuilder().append(date()).append(literalTElement()).append(hourMinuteSecondMillis()).toFormatter();
        }
        return ISODateTimeFormat.dhmsl;
    }
    
    public static DateTimeFormatter dateHourMinuteSecondFraction() {
        if (ISODateTimeFormat.dhmsf == null) {
            ISODateTimeFormat.dhmsf = new DateTimeFormatterBuilder().append(date()).append(literalTElement()).append(hourMinuteSecondFraction()).toFormatter();
        }
        return ISODateTimeFormat.dhmsf;
    }
    
    private static DateTimeFormatter yearElement() {
        if (ISODateTimeFormat.ye == null) {
            ISODateTimeFormat.ye = new DateTimeFormatterBuilder().appendYear(4, 9).toFormatter();
        }
        return ISODateTimeFormat.ye;
    }
    
    private static DateTimeFormatter monthElement() {
        if (ISODateTimeFormat.mye == null) {
            ISODateTimeFormat.mye = new DateTimeFormatterBuilder().appendLiteral('-').appendMonthOfYear(2).toFormatter();
        }
        return ISODateTimeFormat.mye;
    }
    
    private static DateTimeFormatter dayOfMonthElement() {
        if (ISODateTimeFormat.dme == null) {
            ISODateTimeFormat.dme = new DateTimeFormatterBuilder().appendLiteral('-').appendDayOfMonth(2).toFormatter();
        }
        return ISODateTimeFormat.dme;
    }
    
    private static DateTimeFormatter weekyearElement() {
        if (ISODateTimeFormat.we == null) {
            ISODateTimeFormat.we = new DateTimeFormatterBuilder().appendWeekyear(4, 9).toFormatter();
        }
        return ISODateTimeFormat.we;
    }
    
    private static DateTimeFormatter weekElement() {
        if (ISODateTimeFormat.wwe == null) {
            ISODateTimeFormat.wwe = new DateTimeFormatterBuilder().appendLiteral("-W").appendWeekOfWeekyear(2).toFormatter();
        }
        return ISODateTimeFormat.wwe;
    }
    
    private static DateTimeFormatter dayOfWeekElement() {
        if (ISODateTimeFormat.dwe == null) {
            ISODateTimeFormat.dwe = new DateTimeFormatterBuilder().appendLiteral('-').appendDayOfWeek(1).toFormatter();
        }
        return ISODateTimeFormat.dwe;
    }
    
    private static DateTimeFormatter dayOfYearElement() {
        if (ISODateTimeFormat.dye == null) {
            ISODateTimeFormat.dye = new DateTimeFormatterBuilder().appendLiteral('-').appendDayOfYear(3).toFormatter();
        }
        return ISODateTimeFormat.dye;
    }
    
    private static DateTimeFormatter literalTElement() {
        if (ISODateTimeFormat.lte == null) {
            ISODateTimeFormat.lte = new DateTimeFormatterBuilder().appendLiteral('T').toFormatter();
        }
        return ISODateTimeFormat.lte;
    }
    
    private static DateTimeFormatter hourElement() {
        if (ISODateTimeFormat.hde == null) {
            ISODateTimeFormat.hde = new DateTimeFormatterBuilder().appendHourOfDay(2).toFormatter();
        }
        return ISODateTimeFormat.hde;
    }
    
    private static DateTimeFormatter minuteElement() {
        if (ISODateTimeFormat.mhe == null) {
            ISODateTimeFormat.mhe = new DateTimeFormatterBuilder().appendLiteral(':').appendMinuteOfHour(2).toFormatter();
        }
        return ISODateTimeFormat.mhe;
    }
    
    private static DateTimeFormatter secondElement() {
        if (ISODateTimeFormat.sme == null) {
            ISODateTimeFormat.sme = new DateTimeFormatterBuilder().appendLiteral(':').appendSecondOfMinute(2).toFormatter();
        }
        return ISODateTimeFormat.sme;
    }
    
    private static DateTimeFormatter fractionElement() {
        if (ISODateTimeFormat.fse == null) {
            ISODateTimeFormat.fse = new DateTimeFormatterBuilder().appendLiteral('.').appendFractionOfSecond(3, 9).toFormatter();
        }
        return ISODateTimeFormat.fse;
    }
    
    private static DateTimeFormatter offsetElement() {
        if (ISODateTimeFormat.ze == null) {
            ISODateTimeFormat.ze = new DateTimeFormatterBuilder().appendTimeZoneOffset("Z", true, 2, 4).toFormatter();
        }
        return ISODateTimeFormat.ze;
    }
}
